package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.Scanner;


public class Main extends Application {
	
	public class DatabaseConnection {

	    // Method to establish connection to the database
	    public static Connection getConnection() throws SQLException {
	        
				String password = "BlueT1215";
				String server = "JEFFLAPTOP\\SQLSERVERTET";
				String database = "master";
				String username = "JEFFLAPTOP\\Jeff";
				String connection = "jdbc:sqlserver://" + server + ":1433;"
				        + "databaseName=" + database + ";"
				        + "user=" + username + ";"
				        + "password=" + password + ";";
				
	        return DriverManager.getConnection(connection);
	        
	    }
	}
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/application/LoginGUI.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
