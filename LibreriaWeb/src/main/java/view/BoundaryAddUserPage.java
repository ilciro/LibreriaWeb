package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerAddUserPage;
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

public class BoundaryAddUserPage implements Initializable{
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
	private Button insB;
	@FXML
	private Button annB;
	
	private ControllerAddUserPage cAUP;
	
	protected Scene scene;
	@FXML
	private void inserisciUtente() throws SQLException {
		

		
			cAUP.insUtente(nomeTF.getText(),cognomeTF.getText(),emailTF.getText(),pwdTF.getText(),descTA.getText(),dataN.getValue());
		
		
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
		ruoloTF.setText("UTENTE");
		
		cAUP=new ControllerAddUserPage();
	}

}
