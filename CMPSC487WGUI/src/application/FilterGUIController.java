package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
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

	// Event Listener on Button[#FilterButton].onAction
	@SuppressWarnings("null")
	@FXML
	public void FilterAction(ActionEvent event) {
		
		Statement stmt = null;
		
		if(DateStart== null && DateEnd== null && TimeStart == null && TimeEnd == null) { // Filter by ID
			int ID = Integer.parseInt("IDnum");
		 try {
		        ResultSet rs = stmt.executeQuery("SELECT * FROM Access WHERE userID = '" + ID + "'");
		        if(rs.next()) {
		        	
		        
		        }
				 
			 } catch(SQLException sqle) {
				 System.out.println("Could not find Value " + sqle);
		    }
		} else if(IDnum == null && TimeStart == null && TimeEnd == null) { // Filter by Date
			ResultSet rs;
			try {
				rs = stmt.executeQuery("SELECT * FROM Access WHERE DateTime >= '"+DateStart+"T00:00:00.00' and DateTime <= '"+DateEnd+"T24:00:00.00'");
			   if(rs.next()) {
			        
			   }
			} catch (SQLException sqle) {
				System.out.println("Could not find Value " + sqle);
			}
			     
		
	} else if(IDnum == null && DateEnd== null) {// Filter by Time on a specific day
		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM Access WHERE DateTime >= '"+DateStart+"T"+TimeStart+"' and DateTime <= '"+DateStart+"T"+TimeEnd+"'");
		   if(rs.next()) {
		        
		   }
		} catch (SQLException sqle) {
			System.out.println("Could not find Value " + sqle);
		}
		
	}
		
}
		
		
		
}
