package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

public class FilterGUIController {
	@FXML
	private TextField IDnum;
	@FXML
	private TextField DateStart;
	@FXML
	private TextField TimeStart;
	@FXML
	private TextField DateEnd;
	@FXML
	private TextField TimeEnd;
	@FXML
	private Button FilterButton;

	
	public class DatabaseConnection {

	    // Method to establish connection to the database
	    public static Connection getConnection() throws SQLException {
	        
	    	String password = "Ypeztyx1";
			String server = "JEFFLAPTOP\\SQLSERVERTET";
			String database = "master";
			String username = "DoctorFixit";
				String connection = "jdbc:sqlserver://" + server + ":1433;"
				        + "databaseName=" + database + ";"
				        + "user=" + username + ";"
				        + "password=" + password + ";";
				
	        return DriverManager.getConnection(connection);
	        
	    }
	}
	
	
	// Event Listener on Button[#FilterButton].onAction
	@SuppressWarnings("null")
	@FXML
	public void FilterAction(ActionEvent event) {
		
		Statement stmt = null;
		
		if(DateStart.getText()== null && DateEnd.getText()== null && TimeStart.getText() == null && TimeEnd.getText() == null) { // Filter by ID
			int ID = Integer.parseInt(IDnum.getText());
		 try {
		        ResultSet rs = stmt.executeQuery("SELECT * FROM Access WHERE userID = '" + ID + "'");
		        if(rs.next()) {
		        	
		        
		        }
				 
			 } catch(SQLException sqle) {
				 System.out.println("Could not find Value " + sqle);
		    }
		} else if(IDnum.getText() == null && TimeStart.getText() == null && TimeEnd.getText() == null) { // Filter by Date
			ResultSet rs;
			try {
				rs = stmt.executeQuery("SELECT * FROM Access WHERE DateTime >= '"+DateStart.getText()+"T00:00:00.00' and DateTime <= '"+DateEnd.getText()+"T24:00:00.00'");
			   if(rs.next()) {
			        
			   }
			} catch (SQLException sqle) {
				System.out.println("Could not find Value " + sqle);
			}
			     
		
	} else if(IDnum.getText() == null && DateEnd.getText()== null) {// Filter by Time on a specific day
		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM Access WHERE DateTime >= '"+DateStart.getText()+"T"+TimeStart.getText()+"' and DateTime <= '"+DateStart.getText()+"T"+TimeEnd.getText()+"'");
		   if(rs.next()) {
		        
		   }
		} catch (SQLException sqle) {
			System.out.println("Could not find Value " + sqle);
		}
		
	}
		
}
		
		
		
}
