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
	
	private AccountDatabase accountDatabase = new AccountDatabase();
	
	@FXML
	private TextArea messageArea;
	
	@FXML
	private TextField openingDeposit, openingFirstName, openingLastName;
	
	@FXML
	private TextField openDay, openMonth, openYear;
	
	@FXML
	private RadioButton openChecking, openSavings, openMoneyMarket;
	
	@FXML
	private CheckBox openLoyal, openDirectDeposit;
	
	@FXML
	private Button openAccount;
	
	@FXML
	/**
	 * Event handler for the open account button
	 * @param event
	 */
	void openAccount(ActionEvent event) {
		
		// get initial deposit amount
		double openDepositAmount;
		try {
			openDepositAmount = Double.parseDouble(openingDeposit.getText());
		}
		catch (Exception e) {
			messageArea.appendText("Make sure to enter a number value for initial deposit\n");
			return;
		}
		
		// get account holder name
		String firstName;
		String lastName;
		try {
			firstName = openingFirstName.getText();
			lastName = openingLastName.getText();
		}
		catch (Exception e) {
			messageArea.appendText("Make sure to enter a first and last name\n");
			return;
		}
		
		if(openChecking.isSelected()) {
			Profile checkingsNewProfile = new Profile(firstName, lastName);
			
			String dateString = openDay.getText() + "/" + openMonth.getText() + "/" + openYear.getText();
			Date checkingsDateOpened = new Date(dateString);
			if(!checkingsDateOpened.isValid()) {
				messageArea.appendText("Date is invalid\n");
				return;
			}
			
			boolean directDeposit;
			if(openDirectDeposit.isPressed()) {
				directDeposit = true;
			}
			else {
				directDeposit = false;
			}

			
			Checking newChecking = new Checking(checkingsNewProfile, openDepositAmount,checkingsDateOpened, directDeposit);
			
			boolean checkingAddable = accountDatabase.add(newChecking);
			
			if(!checkingAddable) {
				messageArea.appendText("Accountis already in the database.\n");
			}
			else {
				messageArea.appendText("Account opened and added to the database\n");
			}
		}
		else if(openSavings.isSelected()) {
			Profile savingsNewProfile = new Profile(firstName, lastName);
			
			String dateString = openDay.getText() + "/" + openMonth.getText() + "/" + openYear.getText();
			Date savingsDateOpened = new Date(dateString);
			if(!savingsDateOpened.isValid()) {
				messageArea.appendText("Date is invalid\n");
				return;
			}
			
			boolean isLoyal;
			if(openLoyal.isPressed()) {
				isLoyal = true;
			}
			else {
				isLoyal = false;
			}
			
			Savings newSavings = new Savings(savingsNewProfile,openDepositAmount, savingsDateOpened, isLoyal);
			boolean savingsAddable = accountDatabase.add(newSavings);
			
			if(!savingsAddable) {
				messageArea.appendText("Account is already in the database.\n");
			}
			else {
				messageArea.appendText("Account opened and added to the database\n");
			}
		}
		else if(openMoneyMarket.isSelected()) {
			Profile moneyNewProfile = new Profile(firstName,lastName);
			
			String dateString = openDay.getText() + "/" + openMonth.getText() + "/" + openYear.getText();
			Date moneyDateOpened = new Date(dateString);
			if(!moneyDateOpened.isValid()) {
				messageArea.appendText("Date is invalid\n");
				return;
			}
			
			MoneyMarket newMoneyMarket = new MoneyMarket(moneyNewProfile,openDepositAmount, moneyDateOpened, 0);
			boolean moneyMarketAddable = accountDatabase.add(newMoneyMarket);
			
			if(!moneyMarketAddable) {
				messageArea.appendText("Account is already in the database.\n");
			}
			else {
				messageArea.appendText("Account opened and added to the database\n");
			}
		}
		else {
			messageArea.appendText("Select an account type\n");
		}
	}
		
	@FXML
	/**
	 * Event handler for the checking radio button
	 * @param event
	 */
	void openCheckingButtonPress(ActionEvent e) {
		openSavings.setSelected(false);
		openMoneyMarket.setSelected(false);
		
		openDirectDeposit.setDisable(false);
		openLoyal.setSelected(false);
		openLoyal.setDisable(true);
	}
	
	@FXML
	/**
	 * Event handler for the savings radio button
	 * @param event
	 */
	void openSavingsButtonPress(ActionEvent e) {
		openChecking.setSelected(false);
		openMoneyMarket.setSelected(false);
		
		openLoyal.setDisable(false);
		openDirectDeposit.setSelected(false);
		openDirectDeposit.setDisable(true);
	}
	
	@FXML
	/**
	 * Event handler for the money market radio button
	 * @param event
	 */
	void openMoneyMarketButtonPress(ActionEvent e) {
		openChecking.setSelected(false);
		openSavings.setSelected(false);
		
		openLoyal.setSelected(false);
		openLoyal.setDisable(true);
		openDirectDeposit.setSelected(false);
		openDirectDeposit.setDisable(true);
	}
		

		
}
	
	
	
	
	
