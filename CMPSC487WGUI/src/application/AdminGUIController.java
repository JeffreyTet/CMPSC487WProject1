package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.conf.IntegerProperty;
import com.mysql.cj.conf.StringProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

public class AdminGUIController {
	@FXML
	private Button UserButton;
	@FXML
	private Button ActiveButton;
	@FXML
	private Button FilterButton;
	@FXML
	private TableView<User> UserTable = new TableView<>();
	@FXML
	private TableColumn<User,Integer> UserIDCol;
	@FXML
	private TableColumn<User,String> UserNameCol;
	@FXML
	private TableColumn<User,String> UserStatusCol;
	@FXML
	private TableColumn<User,String> UserTypeCol;
	@FXML
	private TableView<Access> AccessTable = new TableView<>();
	@FXML
	private TableColumn<Access,Integer> AccessIDCol;
	@FXML
	private TableColumn<Access,String> AccessNameCol;
	@FXML
	private TableColumn<Access,Integer> DateTimeCol;
	@FXML
	private TableColumn<Access,String> AccessStatusCol;
	
	

	
	
	
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
	
	
	@FXML
	public void AddorDeletePopup() throws IOException{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/AddorDeleteGUI.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void SuspentionPopup() throws IOException{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/SuspentionGUI.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void FilterPopup() throws IOException{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/FilterGUI.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	// Event Listener on Button[#UserButton].onAction
	@FXML
	public void AddorDelete(ActionEvent event) {
		try {
			AddorDeletePopup();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#ActiveButton].onAction
	@FXML
	public void ActivateSuspend(ActionEvent event) {
		try {
			SuspentionPopup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#FilterButton].onAction
	@FXML
	public void Filter(ActionEvent event) {
		try {
			FilterPopup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	public void buildDataUser() throws SQLException{
		try {
		Statement stmt = null;
		ResultSet resultSet = stmt.executeQuery("select * from UserAccess") ;
		
		ObservableList dbDataUser = FXCollections.observableArrayList(databaseArrayListUser(resultSet));
		 for(int i=0 ; i<resultSet.getMetaData().getColumnCount(); i++) {
	            TableColumn column = new TableColumn<>();
	            switch (resultSet.getMetaData().getColumnName(i+1)) {
	                case "userID":
	                    column.setText("ID");
	                    break;
	                case "Username":
	                    column.setText("Name");
	                    break;
	                case "UserStatus":
	                    column.setText("Status");
	                    break;
	                case "UserType":
	                    column.setText("UserType");
	                    break;
	                default: column.setText(resultSet.getMetaData().getColumnName(i+1)); 
	                    break;
	            }
	            column.setCellValueFactory(new PropertyValueFactory<>(resultSet.getMetaData().getColumnName(i+1))); 
	            UserTable.getColumns().add(column);
	        }

	        //Filling up tableView with data
		 	UserTable.setItems(dbDataUser);
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	public void buildDataAccess() throws SQLException{
		try {
		Statement stmt = null;
		ResultSet resultSet = stmt.executeQuery("select * from Access") ;
		
		ObservableList dbDataAccess = FXCollections.observableArrayList(databaseArrayListAccess(resultSet));
		 for(int i=0 ; i<resultSet.getMetaData().getColumnCount(); i++) {
	            TableColumn column = new TableColumn<>();
	            switch (resultSet.getMetaData().getColumnName(i+1)) {
	                case "userID":
	                    column.setText("ID");
	                    break;
	                case "Username":
	                    column.setText("Name");
	                    break;
	                case "accesstime":
	                    column.setText("DateTime");
	                    break;
	                case "access":
	                    column.setText("AccessStatus");
	                    break;
	                default: column.setText(resultSet.getMetaData().getColumnName(i+1)); 
	                    break;
	            }
	            column.setCellValueFactory(new PropertyValueFactory<>(resultSet.getMetaData().getColumnName(i+1))); 
	            AccessTable.getColumns().add(column);
	        }

	        //Filling up tableView with data
		 	AccessTable.setItems(dbDataAccess);
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public class User{
		SimpleIntegerProperty id = new SimpleIntegerProperty();
		SimpleStringProperty username = new SimpleStringProperty();
		SimpleStringProperty userStatus = new SimpleStringProperty();
		SimpleStringProperty userType = new SimpleStringProperty();
		
		public SimpleIntegerProperty idProperty() {
			return id;
		}
		
		public SimpleStringProperty usernameProperty() {
			return username;
		}
		public SimpleStringProperty userStatusProperty() {
			return userStatus;
		}
		public SimpleStringProperty userTypeProperty() {
			return userType;
		}
		
		public User(int idValue, String usernameValue, String userStatusValue, String userTypeValue) {
			id.setValue(idValue);
			username.setValue(usernameValue);
			userStatus.setValue(userStatusValue);
			userType.setValue(userTypeValue);
		}
		User(){}
		
	}
	public class Access{
		SimpleIntegerProperty id = new SimpleIntegerProperty();
		SimpleStringProperty username = new SimpleStringProperty();
		SimpleIntegerProperty datetime = new SimpleIntegerProperty();
		SimpleStringProperty access = new SimpleStringProperty();
		
		public SimpleIntegerProperty idProperty() {
			return id;
		}
		
		public SimpleStringProperty usernameProperty() {
			return username;
		}
		public SimpleIntegerProperty datetimeProperty() {
			return datetime;
		}
		public SimpleStringProperty accessProperty() {
			return access;
		}
		
		public Access(int idValue, String usernameValue, int datetimeValue, String accessValue) {
			id.setValue(idValue);
			username.setValue(usernameValue);
			datetime.setValue(datetimeValue);
			access.setValue(accessValue);
		}
		Access(){}
		
	}
	
	private ArrayList databaseArrayListUser(ResultSet resultSet) throws SQLException{
		ArrayList<User> data = new ArrayList<>();
		while(resultSet.next()) {
			User user = new User();
			user.id.setValue(resultSet.getInt("userID"));
			user.username.setValue(resultSet.getString("Username"));
			user.userStatus.setValue(resultSet.getString("UserStatus"));
			user.userType.setValue(resultSet.getString("UserType"));
			data.add(user);
			
		}
		
		return data;
	}
	private ArrayList databaseArrayListAccess(ResultSet resultSet) throws SQLException{
		ArrayList<Access> data = new ArrayList<>();
		while(resultSet.next()) {
			Access access = new Access();
			access.id.setValue(resultSet.getInt("userID"));
			access.username.setValue(resultSet.getString("Username"));
			access.datetime.setValue(resultSet.getInt("accesstime"));
			access.access.setValue(resultSet.getString("access"));
			data.add(access);
			
		}
		
		return data;
	}
}

