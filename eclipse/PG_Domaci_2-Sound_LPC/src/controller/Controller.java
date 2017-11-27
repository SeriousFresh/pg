package controller;

import model.Model;
import view.View;

public class Controller {
	
	private View view;
	private Model model;

	public Controller(View view, Model model)
	{
		this.setView(view);
		this.setModel(model);
		view.getBtnAdd().setOnAction(new ControllerRecord(view));
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
