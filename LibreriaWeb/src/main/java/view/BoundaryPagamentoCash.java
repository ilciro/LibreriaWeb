package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerPagamentoCash;
import controller.ControllerSystemState;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryPagamentoCash implements Initializable{
	@FXML
	private Pane panel;
	@FXML
	private GridPane grid;
	@FXML
	private Label header;
	@FXML
	private Label labelN;
	@FXML
	private Label labelC;
	@FXML
	private Label labelVP;
	@FXML
	private Label labelCom;
	@FXML
	private TextField nomeTF;
	@FXML
	private TextField cognomeTF;
	@FXML
	private TextField viaTF;
	@FXML
	private TextArea eventualiArea;
	@FXML
	private Button buttonI;
	@FXML
	private Button buttonA;

	private ControllerPagamentoCash cPC;

	protected String n; 
	protected String c;
	protected String v;
	protected String com;
	
	protected Scene scene;
	private static ControllerSystemState vis = ControllerSystemState.getIstance();

	@FXML
	private void procediCash() throws IOException {
		
		vis.setMetodoP("cash");
		try {

			n = nomeTF.getText();
			c = cognomeTF.getText();
			v = viaTF.getText();
			com = eventualiArea.getText();

			if (n.equals("") || c.equals("") || v.equals("")) {
				
				Log.LOGGER.log(Level.SEVERE,"Pagamento con contanti NON andato a buon fine !!!");


				Stage stage;
				Parent root;
				stage = (Stage) buttonI.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("pagamentoContrassegno.fxml"));
				stage.setTitle("Benvenuto nella schermata del riepilogo ordine");
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				throw new IOException();

			} else {

				cPC.controlla(n, c, v, com);
				
				Log.LOGGER.log(Level.INFO,"{0}.","Pagamento contanti effettuato con successo");

				if(vis.getIsPickup()) 
				{
					Stage stage;
					Parent root;
					stage = (Stage) buttonI.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("scegliNegozio.fxml"));
					stage.setTitle("Benvenuto nella schermata per scegliere il negozio");
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();	
				}
				else
				{
				Stage stage;
				Parent root;
				stage = (Stage) buttonI.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("download.fxml"));
				stage.setTitle("Benvenuto nella schermata per il download");
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				}
			}
		} catch (Exception e) {
			e.getCause();
		}

	}

	@FXML
	private void annullaCash() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("acquista.fxml"));
		stage.setTitle("benvenuto nella schermata del riepilogo ordine");

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				try {
					cPC = new ControllerPagamentoCash();
				} catch (Exception e) {
					Log.LOGGER.log(Level.SEVERE,e,()->"result"+e);

					
				}

	}

}
