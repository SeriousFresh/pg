package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Model;

public class View extends Stage{
	
	private Label lblChoose;
	private Label lblRecord;
	private Button btnAdd;
	private ChoiceBox<String> cbSounds;
	private Model model;
	
	public View(Model model)
	{
		super();
		this.setModel(model);
		
		this.setTitle("Sound LPC processor");
		
		GridPane gpMain = new GridPane();
		
		lblChoose = new Label("Choose:");
		setLblRecord(new Label());
		btnAdd = new Button("+");
		cbSounds = new ChoiceBox<String>(FXCollections.observableArrayList(model.getSoundPaths()));
		cbSounds.getSelectionModel().selectFirst();
		
		gpMain.add(lblChoose, 0, 0);
		gpMain.add(cbSounds, 0, 1);
		gpMain.add(btnAdd, 1, 1);
		gpMain.add(lblRecord, 2, 1);
		gpMain.setPadding(new Insets(10,10,10,10));
		gpMain.setHgap(10);
		
		this.setHeight(300);
		this.setWidth(300);
		this.setResizable(false);
		
		Scene scene = new Scene(gpMain);
		this.setScene(scene);
		new Controller(this, model);
		this.show();
	}

	public Label getLblChoose() {
		return lblChoose;
	}

	public void setLblChoose(Label lblChoose) {
		this.lblChoose = lblChoose;
	}

	public Button getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}

	public ChoiceBox<String> getCbSounds() {
		return cbSounds;
	}

	public void setCbSounds(ChoiceBox<String> cbSounds) {
		this.cbSounds = cbSounds;
	}

	public Label getLblRecord() {
		return lblRecord;
	}

	public void setLblRecord(Label lblRecord) {
		this.lblRecord = lblRecord;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
