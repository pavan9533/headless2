package Backend_Testcases;

import org.openqa.selenium.remote.html5.AddApplicationCache;
import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class VerifyAccountCreated extends JohnsonBase {
		
	@Test
	
	public void verifyAccountCreated() {
		application.wait(5);
		application.click("customersTab_id");
		application.wait(5);
		application.click("allCustomerstab_xpath");
		application.wait(5);
		application.clearFilter("customerFilterClear_xpath");
		application.wait(5);
		application.click("allCustomersfilterButton_xpath");
		application.wait(2);
		application.type("allCustomersEmailFilter_name", readExcelData("mail ID", "Create Account"));
		application.wait(2);
		application.click("allCustomersfilterButton_xpath");
		application.click("allCustomersApplyFilter_xpath");
		application.wait(5);
		application.scrollTo("customerGrid_xpath");
		
		application.validateElementPresent("customerGrid_xpath");
		
		application.wait(5);
		log("Customer created in Backend");
	}
}
