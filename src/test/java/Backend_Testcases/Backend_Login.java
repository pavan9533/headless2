package Backend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class Backend_Login extends JohnsonBase {
	@Test
	
	public void backendLogin() {
		application.launchBrowser(readExcelData("browser", "OpenBrowser"));
		application.navigate("backend", "URL");
		application.type("backendUsername_name", readExcelData("username", "BackendLogin"));
		application.wait(3);
		application.type("backendPassword_name", readExcelData("password", "BackendLogin"));
		application.wait(3);
		application.click("backendSubmit_xpath");
		generateScreenshots();
		//application.waitForPageLoad();
	}
}
