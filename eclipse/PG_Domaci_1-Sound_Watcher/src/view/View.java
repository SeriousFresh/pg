package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

public class View extends Stage{

	private Button btnFile;
	private Button btnHisto;
	private Button btnSono;
	private Label lblFile;
	private Model model;
	private TextField tfWidth;
	private ToggleGroup tg;

	public View(Model model)
	{
		super();
		this.model = model;
		this.setTitle("Sound Watcher");
		
		BorderPane borderPane = new BorderPane();
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		
		btnFile = new Button("Give me a wave to watch!");
		btnHisto = new Button("Watch Histogram");
		btnSono = new Button("Watch Sonogram");
	    lblFile = new Label("I have nothing to watch! :(");
	    Label lblType = new Label("Choose wisely:");
	    
	    tg = new ToggleGroup();
	    RadioButton rbNN = new RadioButton("Hanning");
	    RadioButton rbMM = new RadioButton("Hamming");
	    RadioButton rbNone = new RadioButton("Neither");
	    rbNN.setSelected(false);
	    rbMM.setSelected(false);
	    rbNone.setSelected(true);
	    rbNN.setToggleGroup(tg);
	    rbMM.setToggleGroup(tg);
	    rbNone.setToggleGroup(tg);
	    VBox vbox2 = new VBox();
	    vbox2.setPadding(new Insets(10,10,10,10));
		
	    Label lblWidth = new Label("Set width of window:");
	    tfWidth = new TextField("5");
	    VBox vbox3 = new VBox();
	    vbox3.setPadding(new Insets(10,10,10,10));
	    
		hbox.getChildren().addAll(btnHisto, btnSono);
		borderPane.setBottom(hbox);
		vbox2.getChildren().addAll(lblType, rbNN, rbMM, rbNone);
		vbox3.getChildren().addAll(lblWidth, tfWidth);
		vbox.getChildren().addAll(btnFile, lblFile, vbox2, vbox3);
		borderPane.setCenter(vbox);
		borderPane.setPadding(new Insets(30,30,30,30));
		
		this.setHeight(300);
		this.setWidth(400);
		this.setResizable(false);
		
		Scene scene = new Scene(borderPane);
		this.setScene(scene);
		
		this.show();
		
		
	}

	public Button getBtnFile() {
		return btnFile;
	}

	public void setBtnFile(Button btnFile) {
		this.btnFile = btnFile;
	}

	public Button getBtnHisto() {
		return btnHisto;
	}

	public void setBtnHisto(Button btnHisto) {
		this.btnHisto = btnHisto;
	}

	public Button getBtnSono() {
		return btnSono;
	}

	public void setBtnSono(Button btnSono) {
		this.btnSono = btnSono;
	}

	public Label getLblFile() {
		return lblFile;
	}

	public void setLblFile(Label lblFile) {
		this.lblFile = lblFile;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public TextField getTfWidth() {
		return tfWidth;
	}

	public void setTfWidth(TextField tfWidth) {
		this.tfWidth = tfWidth;
	}

	public ToggleGroup getTg() {
		return tg;
	}

	public void setTg(ToggleGroup tg) {
		this.tg = tg;
	}
	
}
