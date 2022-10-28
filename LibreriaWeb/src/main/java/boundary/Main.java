package boundary;

import java.io.FileNotFoundException;
import java.sql.SQLException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.CreateDefaultDB;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) {
		Scene scene;

		try {
			Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
			scene = new Scene(root);
			primaryStage.setTitle("Benvenuto nella homePage");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			
		}

	}
	

	public static void main(String[] args)  {
		//uso status per vedere se trigger creati
		 
		

		
		try {
			CreateDefaultDB.createDefaultDB();			

		} catch (FileNotFoundException |SQLException  eFile) {
			eFile.getCause();

		}
		

		launch(args);
		
		
		
	}
}
