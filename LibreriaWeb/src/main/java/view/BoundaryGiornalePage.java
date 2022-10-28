package view;

import java.net.URL;
import java.util.ResourceBundle;

import raccolta.Giornale;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class BoundaryGiornalePage implements Initializable{
	
	@FXML
	private Pane panel;
	@FXML
	private Label header;
	@FXML
	private TableView<Giornale> table;
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		tipologia.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
		editore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		
	}
}