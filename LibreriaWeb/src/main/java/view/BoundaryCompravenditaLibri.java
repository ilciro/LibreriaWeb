package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerCompravenditaLibri;
import controller.ControllerSystemState;
import controller.ControllerVisualizzaLibro;
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

public class BoundaryCompravenditaLibri implements Initializable {
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
	private TableColumn<Raccolta, SimpleStringProperty> autore = new TableColumn<>("Autore");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> categoria = new TableColumn<>("Categoria");
	@FXML
	private TableColumn<Raccolta, SimpleFloatProperty> prezzo = new TableColumn<>("Prezzo");
	@FXML
	private TableColumn<Raccolta, SimpleIntegerProperty> idLibro = new TableColumn<>("Id Libro");

	@FXML
	private Button buttonL;
	@FXML
	private TextField entryText;
	@FXML
	private Button buttonV;
	@FXML
	private Button buttonA;
	@FXML
	private Button buttonI;

	private ControllerCompravenditaLibri cCV;
	private ControllerVisualizzaLibro cVL;
	private ControllerSystemState vis = ControllerSystemState.getIstance() ;
	protected Scene scene;



	@FXML
	private void verifica() throws  IOException, SQLException {
		try
		{
			String i = entryText.getText();
			
			
			
			if( cCV.disponibilitaLibro(i)) {
				
				
				cVL.setID(i);
				Stage stage;
				Parent root=null;
				stage = (Stage) buttonV.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("visualizzaBookPage.fxml"));
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

	@FXML
	private void procedi() throws IOException, SQLException {
		try
		{
			String i = entryText.getText();
			if( cCV.disponibilitaLibro(i)) {
				cVL.setID(i);
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

	@FXML
	private void vediListaLibri() throws SQLException {

		table.setItems(cCV.getLibri());

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cCV = new ControllerCompravenditaLibri();
		cVL = new ControllerVisualizzaLibro();

		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		editore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
		categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		idLibro.setCellValueFactory(new PropertyValueFactory<>("id"));


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
		
		if(!vis.getIsLogged())
			cCV.setTipoUser("UTENTE");
		
		String tipoU=cCV.retTipoUser();
		
		if( vis.getIsLogged() &&  tipoU.equalsIgnoreCase("ADMIN")) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePageAfterLogin.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else if( vis.getIsLogged() && (tipoU.equalsIgnoreCase("SCRITTORE") || tipoU.equalsIgnoreCase("EDITORE")) ) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePageAfterLoginES.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}	
		else if((tipoU.equalsIgnoreCase("UTENTE") ) )
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}


	}
}


