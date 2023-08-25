package Frontend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class OpenFrontend extends JohnsonBase{
	@Test
	public void openFrontend() {
		application.launchBrowser(readExcelData("browser", "OpenBrowser"));
		application.navigate("Frontend", "URL");
		application.validateTitle("title_xpath");
		application.waitForPageToLoad(driver, 200);
	}
}
