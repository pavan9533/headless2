package Backend_Testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class AssignDealer extends JohnsonBase{
	@Test
	public void assignDealer() {
		application.click("salesButton_xpath");
		application.click("salesOrders_xpath");
		application.wait(10);
		application.clearFilter("clearFiltersButton_xpath");
		application.wait(5);
		application.click("salesOrderFilters_xpath");
		application.wait(3);
		application.type("filtersOrderId_xpath", readExcelData("order_id", "Order_Management"));
		application.wait(3);
		application.click("applyFilter_xpath");
		application.wait(10);
		application.clickAssignDealerButton("orderGridTableLocator_xpath", "order_id" , "sub_order_id","Order_Management" );
		application.wait(5);
		application.scrollTo("dealerCodeInput_xpath");
		application.type("dealerCodeInput_xpath", readExcelData("dealer_code", "Check_Order_Status"));
		application.validateElementPresent("searchAndAddButton_xpath");
		application.click("searchAndAddButton_xpath");
		//application.selectDealer("assignDealerSelectDealer_xpath","dealer_code", "Check_Order_Status");
		try {
		WebElement notFound = application.getText1("notFoundMessage_xpath");
			if(notFound.isDisplayed()) {
				test.log(Status.FAIL, "Dealer is not available");
				generateScreenshots();
			}
		}catch(Exception e) {
			test.log(Status.INFO, "Not found is not present");
		}
		try {
		WebElement dealerFound =application.getText1("assignDealerSelectDealer_xpath");
			if(dealerFound.isDisplayed()) {
				test.log(Status.PASS, "Dealer is found");
				generateScreenshots();
			}
		}catch(Exception e ) {
			test.log(Status.FAIL, "Dealer is not found");
			generateScreenshots();
		}
		application.click("assignDealerRadioButton_xpath");
		application.validateElementPresent("assignDealerButton_xpath");
		application.click("assignDealerButton_xpath");
		application.wait(10);
	}
}
