package Frontend_Testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class Logout extends JohnsonBase{
	
	@Test
	public void logout() {
		application.validateLogin("validLoginText_xpath");
		application.click("validLoginText_xpath");
		application.wait(2);
		application.getText("signOutButton_xpath");
		application.validateText("signOutButton_xpath", "SIGN OUT");
		application.click("signOutButton_xpath");
		application.wait(5);
		application.validateLogout("homePageSignin_xpath");
		application.wait(2);
		application.scrollByValue("200");
		application.scrollByValue("-300");
		application.wait(5);
		
	}
}
