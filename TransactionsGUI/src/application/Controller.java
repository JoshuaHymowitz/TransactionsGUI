package application;

import java.io.File;
import TransactionManagement.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;

public class Controller {
	
	@FXML
	private TextArea messageArea;
	
	@FXML
	private TextField openingDeposit, openingName;
	
	@FXML
	private RadioButton openChecking, openSavings, openMoneyMarket;
	
	@FXML
	private CheckBox openLoyal, openDirectDeposit;
	
	@FXML
	private Button openAccount;
	
	@FXML
	/**
	 * Event handler for the open account button
	 */
	void openAccount(ActionEvent event) {
		
		try {
			double depositAmount = Double.parseDouble(openingDeposit.getText());
			//messageArea.appendText(String.valueOf(depositAmount));
			System.out.println("Success");
			
		}
		
		catch (Exception e) {
			//messageArea.appendText("Make sure to enter a number in the initial deposit box");
			System.out.println("Exception");
		}
		
		
	}
	
	
	
	
	
}