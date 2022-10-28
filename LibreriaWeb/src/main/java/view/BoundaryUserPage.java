package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerCancUser;
import controller.ControllerModifUser;
import controller.ControllerSystemState;
import controller.ControllerUserPage;
import model.Log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryUserPage implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private Button buttonA;
	@FXML
	private Button buttonM;
	@FXML
	private Button buttonDel;
	@FXML
	private Button buttonC;
	@FXML
	private Button indietro;
	@FXML
	private Button buttonP;
	@FXML
	private TextArea elencoUtenti;
	@FXML
	private Label idL;
	@FXML
	private TextField utenteTF;
	
	private ControllerUserPage cUP;
	private ControllerCancUser cCU;
	private ControllerModifUser cMPU;
	
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	protected Scene scene ;
	
	protected int max = 0;

	@FXML
	private void aggiungi() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("addUserPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		
	}
	@FXML
	private void modifica() throws IOException, SQLException
	{
		vis.setId(Integer.parseInt(utenteTF.getText()));
		max=cMPU.prendiIdMax();
		
		Log.LOGGER.log(Level.INFO,"Utenti massimi {0}",max);

		
		if(Integer.parseInt(utenteTF.getText())<1)// && Integer.parseInt(utenteTF.getText())>max)
		{
	
			
			Log.LOGGER.log(Level.SEVERE,"Utente non trovato");

			
			

		}
		else if (Integer.parseInt(utenteTF.getText())>max)// && Integer.parseInt(utenteTF.getText())>max)
		{
	
			
			Log.LOGGER.log(Level.SEVERE,"id utente non trovato in quanto maggiore del numero utenti");

			
			

		}

		else {
			
		
		Log.LOGGER.log(Level.INFO,"Id in BoundaryUserPage : {0}",vis.getId());
		Stage stage;
		Parent root;
		stage = (Stage) buttonM.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("modUserPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}

	}
	@FXML
	private void cancella() throws NumberFormatException, SQLException
	{
		
			cCU.cancellaUtente(Integer.parseInt(utenteTF.getText()));
		
	}
	@FXML
	private void torna() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) indietro.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	@FXML
	private void prendi() throws IOException,NullPointerException, SQLException {
		String line="";
		
			cUP.getUtenti();
		
		elencoUtenti.clear();
		
		
		try (BufferedReader reader = new BufferedReader(new FileReader("ReportFinale\\riepilogoUtenti.txt")))
		{
			
			while((line=reader.readLine())!=null)
			{
				elencoUtenti.appendText(line.concat("\n"));
                Log.LOGGER.log(Level.INFO,line);
                line = reader.readLine();
			}
		}
       
		    	    
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cUP=new ControllerUserPage();
		cCU=new ControllerCancUser();
		cMPU=new ControllerModifUser();
	}
	
	
	
	
	
	
	
	
	
	}
