package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerModifBook;
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

public class BoundaryModifBook implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private GridPane gridpane ;
	@FXML
	private TextField titoloT;
	@FXML 
	private TextField numeroPagineT;
	@FXML
	private TextField codeIsbnT;
	@FXML
	private TextField editoreT;
	@FXML
	private TextField autoreT;
	@FXML
	private TextField linguaT;
	@FXML
	private ListView<String> categoriaTF ;
	@FXML
	private DatePicker dataP;
	@FXML
	private TextField recensioneT;
	@FXML 
	private TextArea descrizioneT;
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
	private Label titoloL;
	@FXML
	private Label numeroPagineL;
	@FXML
	private Label codeIsbnL;
	@FXML
	private Label editoreL;
	@FXML
	private Label autoreL;
	@FXML
	private Label linguaL;
	@FXML
	private Label categoriaL;
	@FXML
	private Label dataL;
	@FXML
	private Label recensioneL;
	@FXML
	private Label descrizioneL;
	@FXML
	private Label disponibilitaL;
	@FXML
	private Label prezzoL;
	@FXML
	private Label copieRimanentiL;
	
	@FXML
	private Label labelT;
	@FXML
	private Label labelNP;
	@FXML
	private Label labelCod;
	@FXML
	private Label labelE;
	@FXML
	private Label labelA;
	@FXML
	private Label labelL;
	@FXML
	private Label labelCat;
	@FXML
	private Label labelD;
	@FXML
	private Label labelR;
	@FXML
	private Label labelDesc;
	@FXML
	private Label labelDisp;
	@FXML
	private Label labelP;
	@FXML
	private Label labelCopie;

	private ControllerModifBook cMB;
	private ControllerSystemState vis= ControllerSystemState.getIstance();
	protected float prezzo ;
	protected int copie;
	protected Scene scene;
	protected int np;
	private String[] infoGen=new String[7];
	private String[] infoCostoDisp=new String[7];
	private ObservableList<String> items = FXCollections.observableArrayList();


	@FXML
	private void aggiorna() throws SQLException, NullPointerException
	{
		String t=titoloT.getText();
		np=Integer.parseInt(numeroPagineT.getText());
		String cod=codeIsbnT.getText();
		String ed=editoreT.getText();
		String a=autoreT.getText();
		String l=linguaT.getText();
		String c= categoriaTF.getSelectionModel().getSelectedItem();
		LocalDate d=dataP.getValue();
		String r=recensioneT.getText();
		boolean disp=disponibilitaC.isSelected();
		String desc=descrizioneT.getText();
		

		
		
		if(disp)
		{
			infoCostoDisp[3]=String.valueOf(1);
			//
		}
		else {
			infoCostoDisp[3]=String.valueOf(0);
		}
		prezzo=Float.parseFloat(prezzoT.getText());
		copie=Integer.parseInt(copieRimanentiT.getText());
		
		infoGen[0]=t;
		infoGen[2]=a;
		infoGen[3]=l;
		infoGen[4]=ed;
		infoGen[5]=c;
		infoCostoDisp[0]=String.valueOf(np);
		infoCostoDisp[1]=cod;
		infoCostoDisp[4]=String.valueOf(prezzo);
		infoCostoDisp[5]=String.valueOf(copie);
		
		

		

		cMB.checkData(infoGen,r,desc,d,infoCostoDisp);
		
		
	}
		
		
	@FXML
	private void annulla() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("bookPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/* settare valori textArea*/
		cMB=new ControllerModifBook();
		
		try {
			labelT.setText(cMB.getLibriById(vis.getId()).get(0).getTitolo());
			labelNP.setText(""+cMB.getLibriById(vis.getId()).get(0).getNumeroPagine());
			labelCod.setText(cMB.getLibriById(vis.getId()).get(0).getCodIsbn());
			labelE.setText(cMB.getLibriById(vis.getId()).get(0).getEditore());
			labelA.setText(cMB.getLibriById(vis.getId()).get(0).getAutore());
			labelL.setText(cMB.getLibriById(vis.getId()).get(0).getLingua());
			labelCat.setText(""+cMB.getLibriById(vis.getId()).get(0).getCategoria());
			labelR.setText(cMB.getLibriById(vis.getId()).get(0).getRecensione());
			labelP.setText(""+cMB.getLibriById(vis.getId()).get(0).getPrezzo());
			labelCopie.setText(""+cMB.getLibriById(vis.getId()).get(0).getCopieRim());
			labelD.setText(cMB.getLibriById(vis.getId()).get(0).getDataPubb().toString());
			labelDesc.setText(cMB.getLibriById(vis.getId()).get(0).getDesc());
			labelDisp.setText(String.valueOf(cMB.getLibriById(vis.getId()).get(0).getDisponibilita()));





		} catch (SQLException   e) 	
		{
			Log.LOGGER.log(Level.SEVERE,e,()->"result"+e);
		} 
		categoriaTF.setItems(items);
		items.add("ADOLESCENTI_RAGAZZI");
		items.add("ARTE");
		items.add("CINEMA_FOTOGRAFIA");
		items.add("BIOGRAFIE");
		items.add("DIARI_MEMORIE");
		items.add("CALENDARI_AGENDE");
		items.add("DIRITTO");
		items.add("DIZINARI_OPERE");
		items.add("ECONOMIA");
		items.add("FAMIGLIA");
		items.add("SALUTE_BENESSERE");
		
		items.add("FANTASCIENZA_FANTASY");
		items.add("FUMETTI_MANGA");
		items.add("GIALLI_THRILLER");
		items.add("COMPUTER_GIOCHI");
		items.add("HUMOR");
		items.add("INFORMATICA");
		items.add("WEB_DIGITAL_MEDIA");
		items.add("LETTERATURA_NARRATIVA");
		items.add("LIBRI_BAMBINI");
		items.add("LIBRI_SCOLASTICI");
		items.add("LIBRI_UNIVERSITARI");
		items.add("RICETTARI_GENERALI");
		
		items.add("LINGUISTICA_SCRITTURA");
		items.add("POLITICA");
		items.add("RELIGIONE");
		items.add("ROMANZI_ROSA");
		items.add("SCIENZE");
		items.add("TECNOLOGIA_MEDICINA");
		


	}

	
			


}
