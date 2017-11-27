package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewRecord extends Stage{

	public ViewRecord()
	{
		super();
		this.setTitle("Record");

		GridPane gpMain = new GridPane();
		
		gpMain.setPadding(new Insets(10,10,10,10));
		gpMain.setHgap(10);
		
		this.setHeight(300);
		this.setWidth(300);
		this.setResizable(false);
		
		Scene scene = new Scene(gpMain);
		this.setScene(scene);
	}
	
}
