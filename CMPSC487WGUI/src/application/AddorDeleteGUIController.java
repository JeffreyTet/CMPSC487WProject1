package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class AddorDeleteGUIController {
	@FXML
	private TextField IDnum;
	@FXML
	private TextField UserName;
	@FXML
	private CheckBox Admin = new CheckBox();
	@FXML
	private Button AddButton;
	@FXML
	private Button DeleteButton;
	@FXML
	private Label UserStatus;

	// Event Listener on Button[#AddButton].onAction
	@SuppressWarnings("null")
	@FXML
	public void AddUser(ActionEvent event) {
		int ID = Integer.parseInt("IDnum");
		 try {
			 	Statement stmt = null;
			 	Admin.setIndeterminate(false);
			 if (Admin.isIndeterminate() && !Admin.isSelected()) { //Student Adding
		        ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + ID + "' and UserStatus = 'Active' and UserType ='Student'");
		        if(rs.next()) {
		        	UserStatus.setText("User Not Valid or Already Exists");
		        } else {
		          stmt.executeQuery("Insert into UserAccess value( " + ID +"," + UserName +", 'Active', 'Student'");
		          UserStatus.setText("User Added");
		        }
			 } else if(Admin.isIndeterminate() && Admin.isSelected()) { // Admin Adding
				 ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + ID + "' and UserStatus = 'Active' and UserType = 'Admin'");
			        if(rs.next()) {
			        	UserStatus.setText("User Not Valid or Already Exists");
						}
			        } else {
			        	 stmt.executeQuery("Insert into UserAccess value( " + ID +"," + UserName +", 'Active', 'Admin'");
				          UserStatus.setText("User Added");
			        }
				 
			 }catch(SQLException sqle) {
		        System.out.println("Could not search ID. " + sqle);
		    }
	}
	// Event Listener on Button[#DeleteButton].onAction
	@SuppressWarnings("null")
	@FXML
	public void DeleteUser(ActionEvent event) {
		int ID = Integer.parseInt("IDnum");
		 try {
			 	Statement stmt = null;
			 	Admin.setIndeterminate(false);
			 if (Admin.isIndeterminate() && !Admin.isSelected()) { //Student Delete
		        ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + ID + "'and UserType ='Student'");
		        if(rs.next()) {
		        	stmt.executeQuery("Delete from UserAccess where userID = " + ID +"and Username = '" + UserName +"' ");
		          UserStatus.setText("User Removed");
		        } else {
		          UserStatus.setText("User Not Valid or Doesn't Exist");
		        }
			 } else if(Admin.isIndeterminate() && Admin.isSelected()) { // Admin Delete
				 ResultSet rs = stmt.executeQuery("SELECT * FROM UserAccess WHERE userID = '" + ID + "'and UserType = 'Admin'");
			        if(rs.next()) {
			        	stmt.executeQuery("Delete from UserAccess where userID = " + ID +"and Username = '" + UserName +"' ");
				          UserStatus.setText("Admin Removed");
				     } else {
				          UserStatus.setText("User Not Valid or Doesn't Exist");
				     }
			 }
		 }catch(SQLException sqle) {
		        System.out.println("Could not search ID. " + sqle);
		    }
	}
}
