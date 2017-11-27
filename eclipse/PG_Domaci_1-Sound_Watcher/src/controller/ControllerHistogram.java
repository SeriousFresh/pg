package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import model.Model;
import view.View;
import view.ViewHistogram;
import wave.WavFile;
import wave.WavFileException;

public class ControllerHistogram implements EventHandler<ActionEvent>
{
	private View view;
	private Model model;
	
	public ControllerHistogram(View view, Model model) {
		super();
		this.view = view;
		this.model = model;
	}

	@Override
	public void handle(ActionEvent event) {
	
		
		if(model.getFile() == null || view.getTfWidth().getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Missing file");
			alert.showAndWait();
			return;
		}
		
		ArrayList<Double> frames = new ArrayList<Double>();
		
		try {
			
			WavFile wavFile = WavFile.openWavFile(model.getFile());
			int numChannels = wavFile.getNumChannels();
			double[] buffer = new double[100 * numChannels];
			int framesNum;
			do
			{
				framesNum = wavFile.readFrames(buffer, 100);
				for(int i = 0; i < framesNum * numChannels; i++)
					frames.add(buffer[i]);
			}while(framesNum != 0);
				
			int sampleRate = (int) wavFile.getSampleRate();
			double period =  Double.parseDouble(view.getTfWidth().getText()) / 1000;
			
			double maxPeriod = (double) frames.size() / (double) sampleRate;
			maxPeriod /=  (double) wavFile.getNumChannels();
			if(period > maxPeriod)
				period = maxPeriod;
			
			double maxSampleRate = sampleRate/2;
			double minSampleRate = 1/period;
			
			int sampleNumber = (int) (sampleRate * period);
			double[] x = new double[sampleNumber];
			double[] y = new double[sampleNumber];
			
			for(int i = 0; i < sampleNumber; i++)
			{
				x[i] = frames.get(i);
				y[i] = 0;
			}
			System.out.println(((RadioButton) view.getTg().getSelectedToggle()).getText());
			if(((RadioButton) view.getTg().getSelectedToggle()).getText().equals("Hamming"))
			{
				System.out.println("mm");
				int len = x.length;
				for(int i = 0; i < len; i++)
				{
					x[i] = x[i] * (0.54 - 0.46 * Math.cos(2.0 * Math.PI * i / len));
				}
			}
			if(((RadioButton) view.getTg().getSelectedToggle()).getText().equals("Hanning"))
			{
				System.out.println("nn");
				int len = x.length;
				for(int i = 0; i < len; i++)
				{
					x[i] = x[i] * 0.5 * (1 -  Math.cos(2.0 * Math.PI * i / len));
				}
			}
			
			dft(1, x.length, x, y);
			
			double[] z = new double[x.length];
			for(int i = 0; i < z.length; i++)
				z[i] = Math.sqrt(Math.pow(x[i], 2) + Math.pow(y[i], 2));
			
			wavFile.close();
			
			ViewHistogram h = new ViewHistogram(z, sampleRate, period, maxSampleRate, minSampleRate, sampleNumber);
			h.getBarChart().setOnScroll(new ControllerHistogramScroll(h));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WavFileException e) {
			e.printStackTrace();
		}
		
	
		
		
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
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
