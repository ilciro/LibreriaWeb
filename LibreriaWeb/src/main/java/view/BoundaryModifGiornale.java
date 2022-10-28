package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerModifGiornale;
import controller.ControllerSystemState;
import model.Log;
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

public class BoundaryModifGiornale implements Initializable {
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
	@FXML
	private Label labelT;
	@FXML
	private Label labelTipo;
	@FXML
	private Label labelE;
	@FXML
	private Label labelLingua;
	@FXML
	private Label labelData;
	@FXML
	private Label labelDisp;
	@FXML
	private Label labelP;
	@FXML
	private Label labelCopie;
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	protected float prezzo ;
	protected int copie;
	protected Scene scene;
	private ControllerModifGiornale cMG;
	private String [] info=new String[4];
	@FXML
	private void conferma() throws SQLException 
	{
		String t=titoloT.getText();
		String tipo=tipologiaT.getText();
		String ed=editoreT.getText();
		String l=linguaT.getText();

		LocalDate d=dataP.getValue();
		boolean disp=disponibilitaC.isPressed();
		
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
		
		info[0]=t;
		info[1]=tipo;
		info[2]=ed;
		info[3]=l;
		
		cMG.checkData(info,d,dispo,prezzo,copie);
		
		
		

		
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
		
		cMG=new ControllerModifGiornale();
		try {
			labelT.setText(cMG.getGiornaliById(vis.getId()).get(0).getTitolo());
			labelTipo.setText(cMG.getGiornaliById(vis.getId()).get(0).getTipologia());
			labelE.setText(cMG.getGiornaliById(vis.getId()).get(0).getEditore());
			labelLingua.setText(cMG.getGiornaliById(vis.getId()).get(0).getLingua());
			labelData.setText(cMG.getGiornaliById(vis.getId()).get(0).getDataPubb().toString());
			labelDisp.setText(String.valueOf(cMG.getGiornaliById(vis.getId()).get(0).getDisponibilita()));
			labelP.setText(String.valueOf(cMG.getGiornaliById(vis.getId()).get(0).getPrezzo()));
			labelCopie.setText(String.valueOf(cMG.getGiornaliById(vis.getId()).get(0).getCopieRimanenti()));

		} catch (SQLException  e) {
		 
			Log.LOGGER.log(Level.SEVERE,e,()->"result"+e);

		} 


		
	}
	
	
	








}



