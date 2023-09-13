package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.CheckBox;

public class LoginGUIController {
	@FXML
	private Button Signin;
	@FXML
	private CheckBox Admin = new CheckBox();
	@FXML
	private Label Access;
	@FXML
	private Button Signout;
	@FXML
	private TextField IDnum;
	
	
	
	
 public class DatabaseConnection {

	    // Method to establish connection to the database
	    public static Connection getConnection() throws SQLException {
	        
				String password = "Ypeztyx1";
				String server = "JEFFLAPTOP\SQLSERVERTET";
				String database = "master";
				String username = "DoctorFixit";
				String connection = "jdbc:sqlserver://" + server + ":1433;"
				        + "databaseName=" + database + ";"
				        + "user=" + username + ";"
				        + "password=" + password + ";";
		
				
	        return DriverManager.getConnection(connection);
	        
	    }
	}
	
	@FXML
	public void AdminPopup() throws IOException{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/AdminGUI.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#Signin].onAction
	@FXML
	@SuppressWarnings("null")
	public void SigninAction(ActionEvent event) {

		int ID = Integer.parseInt(IDnum.getText());
		 try {
			 	Statement stmt = null;
			 	Admin.setIndeterminate(false);
			 if (Admin.isIndeterminate() && !Admin.isSelected()) { //Student login
		        ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + ID + "' and UserStatus = 'Active' and UserType ='Student'");
		        if(rs.next()) {
		        	stmt.executeQuery("Insert into Access value( " + ID +", Username.UserAccess, Currenttime() , 'IN')");
		          Access.setText("Access Granted");
		        } else {
		            Access.setText("Access Denied");
		        }
			 } else if(Admin.isIndeterminate() && Admin.isSelected()) { // Admin login
				 ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + ID + "' and UserStatus = 'Active' and UserType = 'Admin'");
			        if(rs.next()) {
			        	stmt.executeQuery("Insert into Access value( " + ID +", Username.UserAccess, Currenttime() , 'IN')");
			        	try {
							AdminPopup();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
			        } else {
			            Access.setText("Access Denied");
			        }
				 
			 }
		    } catch(SQLException sqle) {
		        System.out.println("Could not search ID. " + sqle);
		    }
		 }
	// Event Listener on Button[#Signout].onAction
	@SuppressWarnings("null")
	@FXML
	public void SignoutAction(ActionEvent event) {
		int ID = Integer.parseInt(IDnum.getText());
		 try {
			 
			 	Statement stmt = null;
			 	Admin.setIndeterminate(false);
			 if (Admin.isIndeterminate() && !Admin.isSelected()) { //Student login
		        ResultSet rs = stmt.executeQuery("SELECT * FROM Access WHERE userID = '" + ID + "'");
		        if(rs.next()) {
		        	stmt.executeQuery("Insert into Access value( " + ID +", Username, Currenttime() , 'Out')");
		          Access.setText("Swipe Successful");
		        } else {
		            Access.setText("Swipe Failure");
		        }
			 } else if(Admin.isIndeterminate() && Admin.isSelected()) { // Admin login
				 ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + IDnum + "'");
			        if(rs.next()) {
			        	stmt.executeQuery("Insert into Access value( " + ID +", Username, Currenttime() , 'Out')");
			        	Access.setText("Swipe Successful");
			        	
			        } else {
			            Access.setText("Swipe Failure");
			        }
				 
			 }
		    } catch(SQLException sqle) {
		        System.out.println("Could not search ID. " + sqle);
		    }
}
	

	
}