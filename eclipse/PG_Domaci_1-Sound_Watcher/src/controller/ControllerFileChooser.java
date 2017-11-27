package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Model;
import view.View;

public class ControllerFileChooser implements EventHandler<ActionEvent>
{
	public View view;
	public Model model;
	
	public ControllerFileChooser(View view, Model model) {
		super();
		this.view = view;
		this.model = model;
	}

	@Override
	public void handle(ActionEvent arg0) {
		
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Give me some wave to watch!");
		 fileChooser.getExtensionFilters().add(new ExtensionFilter("Wave file", "*.wav"));
		 File file = fileChooser.showOpenDialog(new Stage());		 
		 if(file != null)
		 {
			 view.getLblFile().setText(file.getAbsolutePath());
			 model.setFile(file);

		 }
		 else
		 {
			 System.out.println("nesto nije ok");
		 }
	}

}
