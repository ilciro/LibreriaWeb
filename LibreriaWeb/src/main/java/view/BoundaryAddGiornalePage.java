package view;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerAddGiornalePage;
import model.Log;
import raccolta.Giornale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryAddGiornalePage implements Initializable  {
	@FXML
	private Pane pane;
	@FXML
	private GridPane gridpane;
	@FXML
	private Label titoloL;
	@FXML
	private Label tipologiaL;
	@FXML
	private Label editoreL;
	@FXML
	private Label linguaL;
	@FXML
	private Label dataL;
	@FXML
	private Label disponibilitaL;
	@FXML
	private Label prezzoL;
	@FXML
	private Label copieRimanentiL;
	@FXML
	private TextField titoloT;
	@FXML
	private TextField tipologiaT;
	@FXML
	private TextField editoreT;
	@FXML
	private TextField linguaT;
	@FXML
	private DatePicker dataP;
	@FXML
	private CheckBox disponibilitaC;
	@FXML
	private TextField prezzoT;
	@FXML
	private TextField copieRimanentiT;
	@FXML
	private Button buttonC;
	@FXML
	private Button buttonA;
	
	private ControllerAddGiornalePage cAGP; 
	protected float prezzo ; 
	protected int copie ; 
	protected Scene scene;
	private String[] info=new String[5];
	
	@FXML
	private void conferma() throws SQLException
	{
		String title=titoloT.getText();
		String type=tipologiaT.getText();
		String editor=editoreT.getText();
		String language=linguaT.getText();
		LocalDate date=dataP.getValue();
		boolean disp=disponibilitaC.isSelected();

		int dispo;

		
		
		if(disp)
		{
			dispo=1;
			//disponibile
		}
		else {
			dispo=0;
		}
		prezzo=Float.parseFloat(prezzoT.getText());
		copie=Integer.parseInt(copieRimanentiT.getText());
		info[0]=title;
		info[1]=type;
		info[2]=language;
		info[4]=editor;
		Giornale giornale = new Giornale(info,date, copie,dispo,prezzo,0);
		boolean esito=cAGP.checkData(giornale);
		
		Log.LOGGER.log(Level.INFO,"Esito : {0}",esito);
		
		

		
	}
	@FXML
	private void annulla() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("giornalePage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
	
	
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				cAGP=new ControllerAddGiornalePage();

	}



}
