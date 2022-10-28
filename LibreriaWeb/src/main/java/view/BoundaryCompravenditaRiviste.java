package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerCompravenditaRiviste;
import controller.ControllerSystemState;
import controller.ControllerVisualizzaRivista;
import model.Log;
import raccolta.Raccolta;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryCompravenditaRiviste implements Initializable {

	private ControllerCompravenditaRiviste cCR;
	private ControllerVisualizzaRivista cVR;
	private ControllerSystemState vis = ControllerSystemState.getIstance() ;

	
	@FXML
	private Pane panel;
	@FXML
	private Label header;
	@FXML
	private TableView<Raccolta> table;
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> titolo = new TableColumn<>("Titolo");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> editore = new TableColumn<>("Editore");
		@FXML
	private TableColumn<Raccolta, SimpleStringProperty> dataPubb = new TableColumn<>("DataPubblicazione");
	@FXML
	private TableColumn<Raccolta, SimpleIntegerProperty> categoria = new TableColumn<>("tipologia");
	@FXML
	private TableColumn<Raccolta, SimpleFloatProperty> prezzo = new TableColumn<>("Prezzo");
	@FXML
	private TableColumn<Raccolta, SimpleIntegerProperty> idRivista = new TableColumn<>("Id Rivista" );
	@FXML
	private Button buttonL;
	@FXML
	private TextField entryText;
	@FXML
	private Button buttonV;
	@FXML
	private Button buttonI;
	@FXML
	private Button buttonA;
	@FXML
	
	protected Scene scene;
	
	@FXML
	private void vediListaRiviste() throws SQLException {
		table.setItems(cCR.getRiviste());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cCR = new ControllerCompravenditaRiviste();
		cVR = new ControllerVisualizzaRivista();

		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		editore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		dataPubb.setCellValueFactory(new PropertyValueFactory<>("dataPubb"));
		categoria.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
		prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		idRivista.setCellValueFactory(new PropertyValueFactory<>("id"));

	}

	
	@FXML
	private void torna() throws IOException {
		/*
		 * Si vede utente "generico " loggato 
		 * se non � loggato si inposta come tipo "Utente"
		 * qui viene usato utente "UTENtE" come quello che non puo fare nulla (utente base)
		 * per restituire tipo vedere controllerCompravenditaLibri
		 * 
		 * FATTO QUESTO per aggirare il problema del nullPointer EXcoeption di cCV.retTipoUser
		 * 
		 */
		boolean status=false;
		
		status=vis.getIsLogged();
		

		if(status)
		{
			cCR.setTipoUser("UTENTE");
		}

		String tipoU=cCR.retTipoUser();
		if( vis.getIsLogged() &&  tipoU.equalsIgnoreCase("A")) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePageAfterLogin.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
			 if( vis.getIsLogged() && (tipoU.equalsIgnoreCase("W") || tipoU.equalsIgnoreCase("E")) ) {
				Stage stage;
				Parent root;
				stage = (Stage) buttonI.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("homePageAfterLoginES.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			 else if((tipoU.equalsIgnoreCase("UTENTE") && status) || !vis.getIsLogged())				{
					
						Stage stage;
						Parent root;
						stage = (Stage) buttonI.getScene().getWindow();
						root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					}

	}

	
	@FXML
	private void verifica() throws  IOException, SQLException {
		try
		{
			String i = entryText.getText();
			vis.setId(Integer.parseInt(i));
		if( cCR.disponibilitaRiviste(i)) {
			cVR.setID(i);
			Stage stage;
			Parent root;
			stage = (Stage) buttonV.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("visualizzaMagazinePage.fxml"));
			stage.setTitle("Benvenuto nella schermata del riepilogo ordine");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
			
		else
		{
			
			Log.LOGGER.log(Level.SEVERE,"id not found or not available");

		}
		}
		catch (NumberFormatException e)
		{
			e.getMessage();
		}

	}

	@FXML
	private void procedi() throws IOException, SQLException {
		try
		{
			String i = entryText.getText();
		if( cCR.disponibilitaRiviste(i)) {
			cVR.setID(i);
			Stage stage;
			Parent root;
			stage = (Stage) buttonA.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("acquista.fxml"));
			stage.setTitle("Benvenuto nella schermata del riepilogo ordine");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			
			Log.LOGGER.log(Level.SEVERE,"id not found or not avalaible");

		}
		}
		catch (NumberFormatException e)
		{
			e.getMessage();
		}

		
	}


}
