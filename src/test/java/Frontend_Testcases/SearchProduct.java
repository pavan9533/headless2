package Frontend_Testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class SearchProduct extends JohnsonBase{
	@Test(priority = 2)
	public void searchProductValid() {
		application.clear("searchbox_name");
		application.type("searchbox_name", readExcelData("valid_search", "Search_Product"));
		application.wait(5);
		application.validateSearchBox("searchbox_name", "suggestion_xpath" ,"errorMessage_xpath" );
		application.wait(5);
		application.click("searchbutton_xpath");
		application.waitForPageToLoad(driver, 200);
	}
	@Test(priority = 1)
	public void searchProductInvalid() {
		application.click("searchbox_name");
		application.click("searchbutton_xpath");
		application.validateSearchBoxError("searchbox_name", "errorMessage_xpath");
		application.wait(5);
		application.type("searchbox_name", readExcelData("invalid_search", "Search_Product"));
		application.wait(5);
		application.validateSearchBoxError("searchbox_name", "errorMessage_xpath");
		//application.validateSearchBox("searchbox_name", "suggestion_xpath" , "errorMessage_xpath");
		application.wait(5);
		application.click("searchClear_xpath");		
	}
	
	@Test
	
	public void searchProduct() {
		application.type("searchbox_name", readExcelData("valid_search", "Search_Product"));
		application.wait(5);
		application.click("searchbutton_xpath");
		application.waitForPageToLoad(driver , 200);
	}

}
