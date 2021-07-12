package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
		
    public static void main(String[] args) {
    	//launches application
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
    
    	//displays the .FXML scenebuilder file 
    	Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("TimerTim");
		primaryStage.setScene(scene);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setX(1110);
		primaryStage.setY(0);
		primaryStage.setResizable(false);
		primaryStage.show();
		
    }
}
