package actions;

import java.util.ArrayList;

import gui.MainScreen;
import gui.Sonogram;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import processing.Calculator;
import processing.WavFileReader;

public class SonogramEvent implements EventHandler<ActionEvent> {

	long sampleRate;

	@Override
	public void handle(ActionEvent event) {
		String file = MainScreen.get().directory.getText();
		int sliceSize = Integer.parseInt(MainScreen.get().sliceSize.getText());

		ArrayList<Double> parametri = calculateParameters(file, sliceSize);
		new Sonogram(parametri);
	}

	public ArrayList<Double> calculateParameters(String file, int sliceSize) {
		ArrayList<Double> parametri = new ArrayList<>();

		ArrayList<double[]> matrix = matrica(file, sliceSize);
		double sampleCounter = 0;

		for (int i = 0; i < matrix.size(); i++) {
			double[] column = matrix.get(i);
			double T = ((double)column.length-1.0)/(double)sampleRate;

			double max = Double.MIN_VALUE;
			for (int j = 1; j < column.length; j++) {
				if (max < column[j])
					max = column[j];
			}

			
			for (int j = 1; j <= column.length / 2; j++) {
				sampleCounter += 2;
				double t = sampleCounter/(double)sampleRate;
				int hz = (int) (j / T);
				
				double magnituda = column[j] * (1.0 / max);

				
				parametri.add((double) hz);
				parametri.add(t);
				parametri.add(magnituda);
			}

		}

		return parametri;
	}

	public ArrayList<double[]> matrica(String file, int width) {
		ArrayList<double[]> matrix = new ArrayList<>();
		Calculator calculator = new Calculator();
		WavFileReader reader = new WavFileReader(file);

		sampleRate = reader.getSampleRate();
		ArrayList<Double> frames = reader.getFrames();
		ArrayList<ArrayList<Double>> slices = new ArrayList<>();
		ArrayList<Double> slice = new ArrayList<>();


		for (int i = 0; i < frames.size(); i++) {
			if (i % width == 0) {
				slices.add(slice);
				slice = new ArrayList<>();
			}
			slice.add(frames.get(i));
		}

		for (int i = 0; i < slices.size(); i++) {
			ArrayList<Double> slice2 = slices.get(i);
			double[] a = new double[slice2.size()];
			double[] b = new double[slice2.size()];

			for (int j = 0; j < slice2.size(); j++) {
				a[j] = slice2.get(j);
				b[j] = 0;
			}
			
			calculator.dft(a, b);
			double[] c = calculator.calcMagnitude(a, b);
			matrix.add(c);
		}

		return matrix;
	}
	
	public static double[] HammingWindow(double[] signal_in) {
		int size = signal_in.length;
		for (int i = 0; i < size; i++) {

			signal_in[i] = (signal_in[i] * (0.54 - 0.46 * Math.cos(2.0 * Math.PI * i / size)));
		}
		return signal_in;
	}


}
