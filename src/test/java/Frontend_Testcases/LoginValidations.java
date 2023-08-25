package Frontend_Testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class LoginValidations extends JohnsonBase{
	@Test(priority = 1)
	public void blankUsernameBlankPassword() {
		application.validateCriticalElements("homePageSignin_xpath");
		application.wait(3);
		application.click("homePageSignin_xpath");
		application.validateElementPresentText("emailID_xpath" , "Email input field");
		application.validateElementPresentText("password_xpath","password input field");
		application.scrollByValue("400");
		application.click("signInButton_xpath");
		application.wait(5);
		String expectedBlankFirstNameError = "This is a required field.";
		String expectedBlankPassNameError = "This is a required field.";
		String blankFirstNameError = null;
		String blankPasswordError = null;
		
		try {
			 blankFirstNameError = application.getStringText("blankUserError_xpath"); 
		}catch(Exception e ) {
			test.log(Status.FAIL, "FirstName Error is not displayed");
			generateScreenshots();
		}
		try {
			blankPasswordError = application.getStringText("blankPassError_xpath"); 
		}catch(Exception e ) {
			test.log(Status.FAIL, "Password Error is not displayed");
			generateScreenshots();
		}
		if(blankFirstNameError.equals(expectedBlankFirstNameError)) {
			test.log(Status.PASS, blankFirstNameError+" Error is displayed for blank input in Username.");
			generateScreenshots();
		}else if(!blankFirstNameError.equals(expectedBlankFirstNameError)) {
			test.log(Status.FAIL, blankFirstNameError+" Error is displayed for blank input in Username.");
			generateScreenshots();
		}
		
		if(blankPasswordError.equals(expectedBlankPassNameError)) {
			test.log(Status.PASS, blankPasswordError+" Error is displayed for blank input in Password.");
			generateScreenshots();
		}else if (!blankPasswordError.equals(expectedBlankPassNameError)){
			test.log(Status.FAIL, blankPasswordError+" Error is displayed for blank input in Password.");
			generateScreenshots();
		}
		application.wait(5);
	}
	@Test(priority = 2)
	public void validUsernameInvalidPassword() {
		application.refreshPage();
		application.wait(5);
		application.scrollTo("homePageSignin_xpath");
		application.validateCriticalElements("homePageSignin_xpath");
		application.wait(3);
		application.click("homePageSignin_xpath");
		application.validateElementPresentText("emailID_xpath" , "Email input field");
		application.validateElementPresentText("password_xpath","password input field");
		application.scrollByValue("300");
		application.type("emailID_xpath", readExcelData("valid_username", "Login_Validations"));
		application.wait(2);
		application.type("password_xpath", readExcelData("invalid_password", "Login_Validations"));
		application.wait(2);
		application.click("signInButton_xpath");
		String expectedInvalidPasswordError = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		String InvalidPasswordError = null;
		try {
			InvalidPasswordError = application.getStringText("invalidPasswordError_xpath"); 
		}catch(Exception e ) {
			test.log(Status.FAIL, "Invalid Password Error is not displayed");
			generateScreenshots();
		}
		if(InvalidPasswordError.equals(expectedInvalidPasswordError)) {
			test.log(Status.PASS, InvalidPasswordError+" Error is displayed for valid Username and Invalid Password");
			generateScreenshots();
		}else if(!InvalidPasswordError.equals(expectedInvalidPasswordError)) {
			test.log(Status.FAIL, InvalidPasswordError+" Error is displayed for valid Username and Invalid Password");
			generateScreenshots();
		}
		application.wait(5);
	}
	
	@Test(priority = 3)
	
	public void invalidUsernameValidPassword() {
		application.refreshPage();
		application.wait(5);
		application.scrollTo("homePageSignin_xpath");
		application.validateCriticalElements("homePageSignin_xpath");
		application.wait(3);
		application.click("homePageSignin_xpath");
		application.validateElementPresentText("emailID_xpath" , "Email input field");
		application.validateElementPresentText("password_xpath","password input field");
		application.scrollByValue("300");
		application.type("emailID_xpath", readExcelData("invalid_username", "Login_Validations"));
		application.wait(2);
		application.type("password_xpath", readExcelData("valid_password", "Login_Validations"));
		application.wait(2);
		application.click("signInButton_xpath");
		String expectedInvalidUsernameError = "Email should not contain special characters other than . and @.";
		String invalidUsernameError = null;
		try {
			invalidUsernameError = application.getStringText("userNameError_xpath"); 
		}catch(Exception e ) {
			test.log(Status.FAIL, "Invalid Username Error is not displayed");
			generateScreenshots();
		}
		if(invalidUsernameError.equals(expectedInvalidUsernameError)) {
			test.log(Status.PASS, invalidUsernameError+" Error is displayed for Invalid Username and valid Password");
			generateScreenshots();
		}else if(!invalidUsernameError.equals(expectedInvalidUsernameError)) {
			test.log(Status.FAIL, invalidUsernameError+" Error is displayed for Invalid Username and valid Password");
			generateScreenshots();
		}
		application.wait(5);
	}
	
	@Test(priority = 4)
	
	public void validUsernameValidPassword() {
		application.refreshPage();
		application.wait(5);
		application.scrollTo("homePageSignin_xpath");
		application.validateCriticalElements("homePageSignin_xpath");
		application.wait(3);
		
		application.click("homePageSignin_xpath");
		application.validateElementPresentText("emailID_xpath" , "Email input field");
		application.validateElementPresentText("password_xpath","password input field");
		application.scrollByValue("300");
		application.type("emailID_xpath", readExcelData("valid_username", "Login_Validations"));
		application.wait(2);
		application.type("password_xpath", readExcelData("valid_password", "Login_Validations"));
		application.wait(2);
		application.click("signInButton_xpath");
		application.wait(30);
		application.refreshPage();
		application.validateLogin("validLoginText_xpath");	
	}
}
