package controller;

import javafx.event.EventHandler;
import javafx.scene.chart.ScatterChart;
import javafx.scene.input.ScrollEvent;
import view.ViewSonogram;

public class ControllerSonogramScroll implements EventHandler<ScrollEvent>{

	private ViewSonogram view;
	
	public ControllerSonogramScroll(ViewSonogram view) {
		super();
		this.view = view;
	
	}

	@Override
	public void handle(ScrollEvent event) 
	{
		ScatterChart<String, Number> scatterChart = view.getScatterChart();
		
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
	
		scatterChart.setMinWidth(scatterChart.getWidth() * scaleFactor);
		scatterChart.setMinHeight(scatterChart.getHeight() * scaleFactor);

	}

}
