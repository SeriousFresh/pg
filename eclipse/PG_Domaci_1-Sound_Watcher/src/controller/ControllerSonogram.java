package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.Model;
import view.View;
import view.ViewSonogram;
import wave.WavFile;
import wave.WavFileException;

public class ControllerSonogram implements EventHandler<ActionEvent>{

	private Model model;
	private View view;
	
	public ControllerSonogram(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}
	@Override
	public void handle(ActionEvent arg0) {
		
		if(model.getFile() == null || view.getTfWidth().getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Missing file");
			alert.showAndWait();
			return;
		}
		
		double sampleRate = 0;
		double period = 0;
		double magnitude = 0;
		double time = 0;
		int freq = 0;
		
		ArrayList<Double> arrayFreq = new ArrayList<Double>();
		ArrayList<Double> arrayTime = new ArrayList<Double>();
		ArrayList<Double> arrayMagn = new ArrayList<Double>();
		
		ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> frames = new ArrayList<Double>();
		ArrayList<ArrayList<Double>> slices = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> slice = new ArrayList<Double>();
		
		WavFile wavFile;
		try {
			wavFile = WavFile.openWavFile(model.getFile());
			int numChannels = wavFile.getNumChannels();
			double[] buffer = new double[100 * numChannels];
			int framesNum;
			do
			{
				framesNum = wavFile.readFrames(buffer, 100);
				for(int i = 0; i < framesNum * numChannels; i++)
					frames.add(buffer[i]);
			}while(framesNum != 0);
			sampleRate = wavFile.getSampleRate();
			
		} catch (IOException | WavFileException e) {
			e.printStackTrace();
		}
		
		int width = Integer.parseInt(view.getTfWidth().getText());
		
		for(int i = 0; i < frames.size(); i++)
		{
			
			if(i % width == 0)
			{
				slices.add(slice);
				slice = new ArrayList<>();
			}
			slice.add(frames.get(i));
		}
		
		for (int i = 0; i < slices.size(); i++) 
		{
			ArrayList<Double> slice2 = slices.get(i);
			double[] a = new double[slice2.size()];
			double[] b = new double[slice2.size()];

			for (int j = 0; j < slice2.size(); j++) {
				a[j] = slice2.get(j);
				b[j] = 0;
			}
			
			dft(1, a.length, a, b);
			ArrayList<Double> c = new ArrayList<Double>();
			for(int j2 = 0; j2 < a.length; j2++)
				c.add(Math.sqrt(Math.pow(a[j2], 2) + Math.pow(b[j2], 2)));
			
			matrix.add(c);
		}
		
		double sampleCounter = 0;
		
		for(int i = 0; i < matrix.size(); i++)
		{
			ArrayList<Double> column = matrix.get(i); 
			period = ((double)column.size() - 1.0) / (double)sampleRate;
			
			//double max = column.get(0);
			double max = Double.MIN_VALUE;
			for (int j = 1; j < column.size(); j++) 
			{
				if (max < column.get(j))
					max = column.get(j);
			}
			
			for (int j = 1; j <= column.size() / 2; j++) 
			{
				sampleCounter += 2;
				time  = sampleCounter / (double)sampleRate;
				freq = (int) (j / period);
				magnitude = column.get(j) * (1.0 / max);

				arrayFreq.add((double) freq);
				arrayTime.add(time);
				arrayMagn.add(magnitude);
			}
		}
		
		ViewSonogram viewSonogram = new ViewSonogram(arrayFreq, arrayTime, arrayMagn);
		viewSonogram.getScatterChart().setOnScroll(new ControllerSonogramScroll(viewSonogram));
	}
	
	public void dft(int dir, int m, double[] x1, double[] y1) 
	{
		int i, k;
		double arg;
		double cosarg, sinarg;

		double[] x2 = new double[m];
		double[] y2 = new double[m];

		for (i = 0; i < m; i++) {
			x2[i] = 0;
			y2[i] = 0;
			arg = -dir * 2.0 * 3.141592654 * (double) i / (double) m;

			for (k = 0; k < m; k++) {
				cosarg = Math.cos(k * arg);
				sinarg = Math.sin(k * arg);
				x2[i] += (x1[k] * cosarg - y1[k] * sinarg);
				y2[i] += (x1[k] * sinarg + y1[k] * cosarg);
			}
		}

		if (dir == 1) {
			for (i = 0; i < m; i++) {
				x1[i] = x2[i] / (double) m;
				y1[i] = y2[i] / (double) m;
			}
		} else {
			for (i = 0; i < m; i++) {
				x1[i] = x2[i];
				y1[i] = y2[i];
			}
		}

	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}

}
