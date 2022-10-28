package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;

import controller.ControllerModifUser;
import controller.ControllerSystemState;
import model.Log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryModifUserPage implements Initializable{
	@FXML
	private  Pane pane;
	@FXML
	private Label header;
	@FXML
	private GridPane grid;
	@FXML
	private Label nomeL;
	@FXML
	private Label cognomeL;
	@FXML
	private Label emailL;
	@FXML
	private Label pwdL;
	@FXML
	private Label descL;
	@FXML
	private Label dataL;
	@FXML
	private Label ruoloL;
	@FXML
	private TextField nomeTF;
	@FXML
	private TextField cognomeTF;
	@FXML
	private TextField emailTF;
	@FXML
	private PasswordField pwdTF;
	@FXML
	private TextArea descTA;
	@FXML
	private DatePicker dataN;
	@FXML
	private TextField ruoloTF;
	@FXML
	private Button modB;
	@FXML
	private Button annB;
	@FXML
	private Label nomeV;
	@FXML
	private Label cognomeV;
	@FXML
	private Label emailV;
	@FXML
	private PasswordField passV;
	@FXML
	private Label descV;
	@FXML
	private Label dataV;
	@FXML
	private Label ruoloV;
	
	
	protected String n;
	protected String c;
	protected String e;
	protected String p;
	protected String d;
	protected String r;
	protected LocalDate data;
	protected Scene scene;
	
	private ControllerModifUser cMUP;
	
	@FXML
	private void modUtente() throws NullPointerException, SQLException {
		

		n=nomeTF.getText();
		c=cognomeTF.getText();
		e=emailTF.getText();
		p=pwdTF.getText();
		d=descTA.getText();
		data=dataN.getValue();
		r=ruoloTF.getText();
		
		cMUP.aggiornaUtenteByAdmin(n,c,e,p,d,data,r);
		
	}
	@FXML
	private void torna() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) annB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
		stage.setTitle("Benvenuto nella schermata del login");
		scene = new Scene(root);
		stage.setScene(scene);


	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// query con id
		cMUP=new ControllerModifUser();
		int id=ControllerSystemState.getIstance().getId();

		
		try {
			cMUP.prendiLista(id);

			nomeV.setText(cMUP.prendiLista(id).getNome());
			cognomeV.setText(cMUP.prendiLista(id).getCognome());
			emailV.setText(cMUP.prendiLista(id).getEmail());
			passV.setText(cMUP.prendiLista(id).getPassword());
			descV.setText(cMUP.prendiLista(id).getDescrizione());
			passV.setText(cMUP.prendiLista(id).getPassword());
			descV.setText(cMUP.prendiLista(id).getDescrizione());
			dataV.setText(cMUP.prendiLista(id).getDataDiNascita().toString());
			ruoloV.setText(cMUP.prendiLista(id).getIdRuolo());
			nomeTF.setText(cMUP.prendiLista(id).getNome());
			cognomeTF.setText(cMUP.prendiLista(id).getCognome());
			emailTF.setText(cMUP.prendiLista(id).getEmail());
			pwdTF.setText(cMUP.prendiLista(id).getPassword());
			descTA.setText(cMUP.prendiLista(id).getDescrizione());
			ruoloTF.setText(cMUP.prendiLista(id).getIdRuolo());
			
		} catch (SQLException eMod) {
			Log.LOGGER.log(Level.SEVERE,eMod,()->"result"+eMod);
		}
	}	


}
