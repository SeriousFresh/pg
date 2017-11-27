package controller;

import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.input.ScrollEvent;
import view.ViewHistogram;

public class ControllerHistogramScroll implements EventHandler<ScrollEvent>{

	private ViewHistogram view;
	
	public ControllerHistogramScroll(ViewHistogram view) {
		super();
		this.view = view;
	
	}

	@Override
	public void handle(ScrollEvent event) 
	{
		BarChart<String, Number> barChart = view.getBarChart();
		
		double scaleD = 1.1;
		double scaleFactor;
		event.consume();
		if (event.getDeltaY() == 0) 
		{
			return;
		}
		if(event.getDeltaY() > 0)
			scaleFactor = scaleD;
		else
			scaleFactor = 1 / scaleD;
	
		barChart.setMinWidth(barChart.getWidth() * scaleFactor);
		barChart.setMinHeight(barChart.getHeight() * scaleFactor);

	}

}
