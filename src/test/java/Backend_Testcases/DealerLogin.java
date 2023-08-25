package Backend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class DealerLogin extends JohnsonBase{
	@Test
	
	public void dealaerLogin() {
		application.launchBrowser(readExcelData("browser", "OpenBrowser"));
		application.navigate("backend", "URL");
		application.type("backendUsername_name", readExcelData("dealer_username", "BackendLogin"));
		application.wait(3);
		application.type("backendPassword_name", readExcelData("dealer_password", "BackendLogin"));
		application.wait(3);
		application.click("backendSubmit_xpath");
		application.validateElementPresent("dealerLoginValidationText_xpath");
	}

}
