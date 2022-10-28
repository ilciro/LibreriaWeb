package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerCancGiornale;
import controller.ControllerGiornalePage;
import controller.ControllerSystemState;
import raccolta.Giornale;
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

public class BoundaryGiornaliPage implements Initializable{
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private TableView<Giornale>table=new TableView<>();
	@FXML
	private TableColumn<Giornale, SimpleStringProperty> titolo = new TableColumn<>("Titolo");
	@FXML
	private TableColumn<Giornale, SimpleStringProperty> tipologia = new TableColumn<>("Tipologia");
	@FXML
	private TableColumn<Giornale, SimpleStringProperty> editore = new TableColumn<>("Editore");
	@FXML
	private TableColumn<Giornale, SimpleFloatProperty> prezzo = new TableColumn<>("Prezzo");
	@FXML
	private TableColumn<Giornale, SimpleIntegerProperty> id = new TableColumn<>("ID Prodotto");
	
	
	@FXML
	private Button buttonAdd;
	@FXML
	private Button buttonDel;
	@FXML
	private Button modB;
	@FXML
	private Button buttonB;
	@FXML
	private Button buttonGL;
	
	private ControllerGiornalePage cGP;
	private ControllerCancGiornale cCG;
	protected Scene scene;
	protected int identity;
	
	
	@FXML
	private void prendiValori() {
		
		ControllerSystemState.getIstance().setId(table.getSelectionModel().getSelectedItem().getId());

		
	}
	
	@FXML
	private void aggiungi() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonAdd.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("addGiornalePage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	@FXML
	private void cancella() throws SQLException {
		identity=ControllerSystemState.getIstance().getId();
		cCG.cancella(identity);
		
	}
	@FXML
	private void modifica() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) modB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("modGiornalePage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	@FXML
	private void indietro() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) buttonB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
		
	}
	@FXML
	private void genera() throws SQLException {
		table.setItems(cGP.getGiornaliS());
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		cGP=new ControllerGiornalePage();
		cCG=new ControllerCancGiornale();
		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		tipologia.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
		editore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));

		
	}
	
	/*
	 * 
	 */
	

}
