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
	Date tempDate = new Date("1/1/2000"); // used for making accounts to be compared, the date is not checked
	
	@FXML
	private TextArea messageArea;
	
	@FXML
	private TextField openingDeposit, openingFirstName, openingLastName;
	
	@FXML
	private TextField closingFirstName, closingLastName;
	
	@FXML
	private TextField openDay, openMonth, openYear;
	
	@FXML
	private RadioButton openChecking, openSavings, openMoneyMarket;
	
	@FXML
	private RadioButton closeChecking, closeSavings, closeMoneyMarket;
	
	@FXML
	private CheckBox openLoyal, openDirectDeposit;
	
	@FXML
	private Button openAccount;
	
	@FXML
	private Button closeAccount;
	
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
		accountDatabase.printAccounts();
	}
		
	@FXML
	/**
	 * Event handler for the open checking radio button
	 * @param event
	 */
	void openCheckingButtonPress(ActionEvent event) {
		openSavings.setSelected(false);
		openMoneyMarket.setSelected(false);
		
		openDirectDeposit.setDisable(false);
		openLoyal.setSelected(false);
		openLoyal.setDisable(true);
	}
	
	@FXML
	/**
	 * Event handler for the open savings radio button
	 * @param event
	 */
	void openSavingsButtonPress(ActionEvent event) {
		openChecking.setSelected(false);
		openMoneyMarket.setSelected(false);
		
		openLoyal.setDisable(false);
		openDirectDeposit.setSelected(false);
		openDirectDeposit.setDisable(true);
	}
	
	@FXML
	/**
	 * Event handler for the open money market radio button
	 * @param event
	 */
	void openMoneyMarketButtonPress(ActionEvent event) {
		openChecking.setSelected(false);
		openSavings.setSelected(false);
		
		openLoyal.setSelected(false);
		openLoyal.setDisable(true);
		openDirectDeposit.setSelected(false);
		openDirectDeposit.setDisable(true);
	}
	
	@FXML
	void closeAccount(ActionEvent event) {
		// get account holder name
		String firstName;
		String lastName;
		try {
			firstName = closingFirstName.getText();
			lastName = closingLastName.getText();
		}
		catch (Exception e) {
			messageArea.appendText("Make sure to enter a first and last name\n");
			return;
		}
		
		if(closeChecking.isSelected()) {
			Profile checkingDeleteProfile = new Profile(firstName, lastName);
			
			Checking deleteChecking = new Checking(checkingDeleteProfile,1,tempDate, true);
			boolean checkingRemovability = accountDatabase.remove(deleteChecking);
			
			if(!checkingRemovability) {
				messageArea.appendText("Account does not exist.\n");
			}
			else {
				messageArea.appendText("Account closed and removed from the database\n");
			}
			
		}
		else if(closeSavings.isSelected()) {
			Profile savingsDeleteProfile = new Profile(firstName,lastName);
			
			Savings deleteSavings = new Savings(savingsDeleteProfile,1,tempDate, true);
			boolean savingsRemovability = accountDatabase.remove(deleteSavings);
			
			if(!savingsRemovability) {
				messageArea.appendText("Account does not exist.\n");
			}
			else {
				messageArea.appendText("Account closed and removed from the database\n");
			}
			
		}
		else if(closeMoneyMarket.isSelected()) {
			Profile moneyDeleteProfile = new Profile(firstName,lastName);

			MoneyMarket deleteMoney = new MoneyMarket(moneyDeleteProfile,1,tempDate, 1);
			boolean moneyMarketRemovability = accountDatabase.remove(deleteMoney);
			
			if(!moneyMarketRemovability) {
				messageArea.appendText("Account does not exist.\n");
			}
			else {
				messageArea.appendText("Account closed and removed from the database\n");
			}
		}
		else {
			messageArea.appendText("Select an account type\n");
		}
		accountDatabase.printAccounts();
	}
	
	@FXML
	/**
	 * Event handler for the open checking radio button
	 * @param event
	 */
	void closeCheckingButtonPress(ActionEvent event) {
		closeSavings.setSelected(false);
		closeMoneyMarket.setSelected(false);
	}
	
	@FXML
	/**
	 * Event handler for the close savings radio button
	 * @param event
	 */
	void closeSavingsButtonPress(ActionEvent event) {
		closeChecking.setSelected(false);
		closeMoneyMarket.setSelected(false);
	}
	
	@FXML
	/**
	 * Event handler for the close money market radio button
	 * @param event
	 */
	void closeMoneyMarketButtonPress(ActionEvent event) {
		closeChecking.setSelected(false);
		closeSavings.setSelected(false);
	}
	
	
		

		
}
	
	
	
	
	
