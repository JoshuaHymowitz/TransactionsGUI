package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
	    primaryStage.setTitle("Transaction Manager");
        primaryStage.setScene(new Scene(root, 300,275));
        primaryStage.show();
        
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
