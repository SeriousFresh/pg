package controller;

import model.Model;
import view.View;

public class Controller {
	
	private View view;
	private Model model;
	public Controller(View view, Model model)
	{
		this.setView(view);
		this.model = model;
		view.getBtnFile().setOnAction(new ControllerFileChooser(view, model));
		view.getBtnHisto().setOnAction(new ControllerHistogram(view, model));
		view.getBtnSono().setOnAction(new ControllerSonogram(model, view));
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
