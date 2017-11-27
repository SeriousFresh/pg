package view;


import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class ViewHistogram extends Stage{

	private BarChart<String, Number> barChart;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ViewHistogram(double[] z, int sampleRate, double period, double maxSampleRate, double minSampleRate, int sampleNum)
	{
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		barChart = new BarChart<>(xAxis, yAxis);
		xAxis.setLabel("Hz");
		yAxis.setLabel("Magnitude");
		
		XYChart.Series series = new XYChart.Series();
		series.setName("Magnitude");
		
		double max = z[0];
		for(int i = 1; i < z.length; i++)
			if(max < z[i])
				max = z[i];
		
		ArrayList<XYChart.Data<String, Number>> listXY = new ArrayList<>();
		for(int i = 1; i <= z.length / 2; i++)
		{
			Integer X = (int)(i / period);
			z[i] = z[i] * ((100.0)/max);
			XYChart.Data<String, Number> dataXY = new XYChart.Data<String, Number>(X.toString() + "Hz", z[i]);
			listXY.add(dataXY);
		}
		
		series.getData().addAll(listXY);
		ScrollPane sp = new ScrollPane();
		sp.setContent(barChart);
		Scene sc = new Scene(sp, 800, 800);
		barChart.getData().addAll(series);
		
		this.setResizable(false);
	    this.setScene(sc);
	    this.show();
		
;	}

	public BarChart<String, Number> getBarChart() {
		return barChart;
	}

	public void setBarChart(BarChart<String, Number> barChart) {
		this.barChart = barChart;
	}

}
