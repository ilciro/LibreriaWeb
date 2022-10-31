package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerSystemState;
import controller.ControllerVisualizzaRivista;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Log;

public class BoundaryVisualizzaRivista implements Initializable{

	@FXML
	private Pane pane;
	@FXML
	private GridPane gridpane ;
	@FXML
	private Label labelTitolo;
	@FXML
	private Label labelTipologia;
	@FXML
	private Label labelEditore;
	@FXML
	private Label labelLingua;
	@FXML
	private Label labelDate;
	@FXML
	private Label labelDisp;
	@FXML
	private Label labelPrezzo;
	@FXML
	private Label labelCopieRimanenti;
	@FXML
	private Button buttonBack;
	@FXML
	private Button buttonA;
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
	private Label disponibbilitaL;
	@FXML
	private Label prezzoL;
	@FXML
	private Label copieRimanentiL;
	
	private ControllerVisualizzaRivista cVR;
	protected int i;
	protected Scene scene;
	private ControllerSystemState vis = ControllerSystemState.getIstance() ;

	public BoundaryVisualizzaRivista()
	{
		cVR = new ControllerVisualizzaRivista();
	}
	
	@FXML
	private void acquista() throws IOException
	{
		
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("acquista.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo ordine");

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void annulla() throws IOException
	{
		if (!vis.getIsSearch()) {
		Stage stage;
		Parent root;
		stage = (Stage) buttonBack.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("compravenditaRivista.fxml"));

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		else
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonBack.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("ricercaPage.fxml"));

			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		i = vis.getId();
		
		try {
			labelTitolo.setText(cVR.getData(i).getTitolo());
			labelEditore.setText(cVR.getData(i).getEditore());
			labelLingua.setText(cVR.getData(i).getLingua());
			labelDate.setText(""+cVR.getData(i).getDataPubb());
			labelDisp.setText(""+cVR.getData(i).getDisp());
			labelPrezzo.setText(cVR.getData(i).getPrezzo()+"");
			labelCopieRimanenti.setText(cVR.getData(i).getCopieRim()+"");
		} catch (SQLException e) {
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta" ,e.getCause());
		
		
		}
	}
	
}
