package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
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
	private TableView UserTable;
	@FXML
	private TableColumn UserIDCol;
	@FXML
	private TableColumn UserNameCol;
	@FXML
	private TableColumn UserStatusCol;
	@FXML
	private TableColumn UserTypeCol;
	@FXML
	private TableView AccessTable;
	@FXML
	private TableColumn AccessIDCol;
	@FXML
	private TableColumn AccessNameCol;
	@FXML
	private TableColumn DateTimeCol;
	@FXML
	private TableColumn AccessStatusCol;
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
}

