package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerModifRivista;
import controller.ControllerSystemState;
import model.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryModifRivista implements Initializable{
		@FXML
		private Pane pane;
		@FXML
		private Label header;
		@FXML
		private GridPane grid;
		@FXML
		private Label labelT;
		@FXML
		private Label labelTipo;
		@FXML
		private Label labelA;
		@FXML
		private Label labelLingua;
		@FXML
		private Label labelE;
		@FXML
		private Label labelDesc;
		@FXML
		private Label labelData;
		@FXML
		private Label labelDisp;
		@FXML
		private Label labelP;
		@FXML
		private Label labelCopie;
		@FXML
		private TextField titoloTF;
		@FXML
		private ListView<String> tipologiaTF;
		@FXML
		private TextField autoreTF;
		@FXML
		private TextField linguaTF;
		@FXML
		private TextField editoreTF;
		@FXML
		private TextArea descTA;
		@FXML
		private DatePicker datePick;
		@FXML
		private CheckBox dispCheck;
		@FXML
		private TextField prezzoTF;
		@FXML
		private TextField copieTF;
		@FXML
		private Button buttonAdd;
		@FXML
		private Button buttonI;
		
		@FXML
		private Label titoloV;
		@FXML
		private Label tipologiaV;
		@FXML
		private Label autoreV;
		@FXML
		private Label linguaV;
		@FXML
		private Label editoreV;
		@FXML
		private Label descV;
		@FXML
		private Label dataV;
		@FXML
		private Label dispV;
		@FXML
		private Label prezzoV;
		@FXML
		private Label copieV;
		
		private ControllerModifRivista cMRP;
		private ControllerSystemState vis= ControllerSystemState.getIstance();
		protected float prezzo;
		protected int copie ;
		protected Scene scene;
		private String[] info=new String[5];
		private ObservableList<String> items = FXCollections.observableArrayList();

		
		
		@FXML
		private void aggiungi() throws SQLException
		{
			String t=titoloTF.getText();
			String tipologia=tipologiaTF.getSelectionModel().getSelectedItem();
			String autore=autoreTF.getText();
			String l=linguaTF.getText();
			String e=editoreTF.getText();
			String desc=descTA.getText();
			LocalDate d=datePick.getValue();
			boolean disp=dispCheck.isPressed();

			
			int dispo;
			
			if(disp)
			{
				dispo=1;
				//disponibile
			}
			else {
				dispo=0;
			}
			prezzo=Float.parseFloat(prezzoTF.getText());
			copie=Integer.parseInt(copieTF.getText());
			
			info[0]=t;
			info[1]=tipologia;
			info[2]=autore;
			info[3]=l;
			info[4]=e;

			cMRP.checkData(info,d,dispo,prezzo,copie,vis.getId(),desc);
			
			
		}
		
		@FXML 
		private void torna() throws IOException
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("rivistaPage.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			cMRP=new ControllerModifRivista();
			
			
			
			
			try {
				titoloV.setText(cMRP.getRivistaById(vis.getId()).get(0).getTitolo());
				tipologiaV.setText(cMRP.getRivistaById(vis.getId()).get(0).getTipologia());
				autoreV.setText(cMRP.getRivistaById(vis.getId()).get(0).getAutore());
				linguaV.setText(cMRP.getRivistaById(vis.getId()).get(0).getLingua());
				editoreV.setText(cMRP.getRivistaById(vis.getId()).get(0).getEditore());
				descV.setText(cMRP.getRivistaById(vis.getId()).get(0).getDescrizione());
				dataV.setText(cMRP.getRivistaById(vis.getId()).get(0).getDataPubb().toString());
				dispV.setText(""+cMRP.getRivistaById(vis.getId()).get(0).getDisp());
				prezzoV.setText(""+cMRP.getRivistaById(vis.getId()).get(0).getPrezzo());
				copieV.setText(""+cMRP.getRivistaById(vis.getId()).get(0).getCopieRim());
			}
			catch (SQLException  e) {
			 
				Log.LOGGER.log(Level.SEVERE,e,()->"result"+e);

			} 			 tipologiaTF.setItems(items);
			 items.add("SETTIMANALE");
			 items.add("BISETTIMANALE");
			 items.add("MENSILE");
			 items.add("BIMESTRALE");
			 items.add("TRIMESTRALE");
			 items.add("ANNUALE");
			 items.add("ESTIVO");
			 items.add("INVERNALE");
			 items.add("SPORT");
			 items.add("CINEMATOGRAFIA");
			 items.add("GOSSIP");
			 items.add("TELEVISIVA");
			 items.add("MILITARE");
			 items.add("INFORMATICA");


	

			
		}

		
}
