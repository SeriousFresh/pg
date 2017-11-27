package view;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class ViewSonogram extends Stage{

	private ScatterChart<String, Number> scatterChart;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ViewSonogram(ArrayList<Double> freq, ArrayList<Double> time, ArrayList<Double> magnitude) 
	{
		ScrollPane scrollPane = new ScrollPane();
		
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time");
		yAxis.setLabel("Frequency");

		scatterChart = new ScatterChart(xAxis, yAxis);
		XYChart.Series seriesXY = new XYChart.Series();
		ArrayList<XYChart.Data<Number, Number>> listXY = new ArrayList<XYChart.Data<Number, Number>>();
		
		for(int i = 0; i < freq.size(); i++)
		{
			XYChart.Data<Number, Number> data = new XYChart.Data(time.get(i), freq.get(i));

			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[]{
				0.0, 0.0,
            	10.0, 5.0,
            	5.0, 10.0 });
			triangle.setStyle("-fx-fill: red;");

			data.setNode(triangle);
			data.getNode().setOpacity(magnitude.get(i));

			listXY.add(data);
	 	}
		
		seriesXY.getData().addAll(listXY);
		scatterChart.getData().add(seriesXY);
		scatterChart.setMinWidth(800);
		scatterChart.setMinHeight(600);
		scatterChart.setLegendVisible(false);
		/*
		 * scatterChart.setMinWidth(800);
		scatterChart.setMinHeight(600);
		 */
		
		scrollPane.setContent(scatterChart);
		Scene scene = new Scene(scrollPane, 800, 800);
		this.setScene(scene);
		this.show();
	}

	public ScatterChart<String, Number> getScatterChart() {
		return scatterChart;
	}

	public void setScatterChart(ScatterChart<String, Number> scatterChart) {
		this.scatterChart = scatterChart;
	}

}
