package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

public class SuspentionGUIController {
	@FXML
	private TextField IDnum;
	@FXML
	private Button ActivateButton;
	@FXML
	private Button SusButton;
	@FXML
	private Label Status;

	// Event Listener on Button[#ActivateButton].onAction
	@SuppressWarnings("null")
	@FXML
	public void Activation(ActionEvent event) {
		int ID = Integer.parseInt("IDnum");
		 try {
			 	Statement stmt = null;
			
		        ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + ID + "' and UserStatus = 'Suspended' and UserType ='Student'");
		        if(rs.next()) {
		        	stmt.executeQuery("Update UserAccess set UserStatus = 'Active' where userID = '"+ ID + "' and UserStatus = 'Suspended'");
		        	Status.setText("User Active");
		        } else {
		            Status.setText("User not found");
		        }
			 
		    } catch(SQLException sqle) {
		        System.out.println("Could not search ID. " + sqle);
		    }
	}
	// Event Listener on Button[#SusButton].onAction
	@SuppressWarnings("null")
	@FXML
	public void Suspention(ActionEvent event) {
		int ID = Integer.parseInt("IDnum");
		 try {
			 	Statement stmt = null;
			
		        ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + ID + "' and UserStatus = 'Active' and UserType ='Student'");
		        if(rs.next()) {
		        	stmt.executeQuery("Update UserAccess set UserStatus = 'Suspended' where userID = '"+ ID + "' and UserStatus = 'Active'");
		        	Status.setText("User Suspended");
		        } else {
		        	 Status.setText("User not found");
		        }
			 
		    } catch(SQLException sqle) {
		        System.out.println("Could not search ID. " + sqle);
		    }
	}
}
