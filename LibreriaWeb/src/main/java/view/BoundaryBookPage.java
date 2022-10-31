package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerBookPage;
import controller.ControllerCancLibro;
import controller.ControllerSystemState;
import raccolta.Libro;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Log;

public class BoundaryBookPage implements Initializable {
	
	@FXML
	private Pane pane ;
	@FXML
	private Label header;
	@FXML
	private TableView<Libro> table = new TableView<>();
	@FXML
	private TableColumn<Libro, SimpleStringProperty> titolo = new TableColumn<>("Titolo");
	@FXML
	private TableColumn<Libro, SimpleStringProperty> codice = new TableColumn<>("Codice Isbn");
	@FXML
	private TableColumn<Libro, SimpleStringProperty> editore = new TableColumn<>("Editore");
	@FXML
	private TableColumn<Libro, SimpleStringProperty> autore = new TableColumn<>("Autore");
	@FXML
	private TableColumn<Libro, SimpleStringProperty> categoria = new TableColumn<>("Categoria");
	@FXML
	private TableColumn<Libro, SimpleFloatProperty> prezzo = new TableColumn<>("Prezzo");
	@FXML
	private TableColumn<Libro, SimpleIntegerProperty> id = new TableColumn<>("ID Prodotto");
	@FXML
	private Button buttonAdd;
	@FXML
	private Button modB;
	@FXML
	private Button buttonDel;
	@FXML
	private Button buttonB;
	@FXML
	private Button buttonGL;
	private ControllerBookPage cBP;
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private ControllerCancLibro cCL;
	protected int identity;
	protected Scene scene;
	@FXML
	private void aggiungi() throws IOException 
	{	
		Stage stage;
		Parent root;
		stage = (Stage) buttonAdd.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("addBookPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		// apro la boundary di aggiunta dei dati e inserimento nel db
	}
	
	@FXML
	private void modifica() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) modB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("modBookPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		//appro una prima schemata di allert che mi chede l'id del libro da modificare
		// poi quella schermata di aller mi riporta alla pagina di modifica dove mostro tutti
		// i dati del libro gia' presenti nel mio9 db e poi li modifico 
	}
	
	@FXML
	private void cancella() throws SQLException 
	{
		identity=ControllerSystemState.getIstance().getId();
		cCL.cancella(identity);
		// come nella modifica si apre una schemrata che chiedi il codice del libro da modiciare
		// inserito quel codice io posso clicare ok o indietro
		// se clicco ok allora apro la pagina di modifica altrimenti niente
		
	}
	
	@FXML
	private void indietro() 
	{
		Stage stage;
		Parent root = null;
		stage = (Stage) buttonB.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
		} catch (IOException e) {
			Log.LOGGER.log(Level.SEVERE,"eccezione ottenuta" ,e.getCause());

		}
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void genera() throws SQLException
	{
		table.setItems(cBP.getLibriS());
	}
	
	@FXML
	private void pippo() {
		vis.setId(table.getSelectionModel().getSelectedItem().getId());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cBP=new ControllerBookPage();
		cCL=new ControllerCancLibro();
		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		codice.setCellValueFactory(new PropertyValueFactory<>("codIsbn"));
		editore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
		categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));


	}
	
}
