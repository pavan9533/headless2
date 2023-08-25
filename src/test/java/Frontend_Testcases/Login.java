package Frontend_Testcases;


import org.testng.annotations.Test;

import TestBase.JohnsonBase;


public class Login extends JohnsonBase {
	
	@Test
	
	public void login() throws Exception {
		application.waitForPageToLoad(driver , 200);
		application.validateCriticalElements("homePageSignin_xpath");
		application.wait(3);
		application.click("homePageSignin_xpath");
		application.wait(5);
		application.validateCriticalElements("emailID_xpath");    
		application.type("emailID_xpath", readExcelData("username", "Login"));
		application.validateCriticalElements("password_xpath");
		application.wait(2);
		application.type("password_xpath", readExcelData("password", "Login"));
		application.wait(3);
		application.validateCriticalElements("signInButton_xpath");
		application.click("signInButton_xpath");
		application.wait(10);
		application.getText("validLoginText_xpath");
		application.validateLogin("validLoginText_xpath");
		log("test");
	}	
}
