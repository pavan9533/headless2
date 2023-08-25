package Backend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class CheckOrderStatus extends JohnsonBase{
	@Test(priority = 2)
	
	public void checkOrderStatus() {
		application.click("salesButton_xpath");
		application.click("salesOrders_xpath");
		application.wait(15);
		application.clearFilter("clearFiltersButton_xpath");
		application.wait(5);
		application.click("salesOrderFilters_xpath");
		application.wait(3);
		application.type("filtersOrderId_xpath", readExcelData("order_id", "Check_Order_Status"));
		application.wait(3);
		application.click("applyFilter_xpath");
		application.wait(10);
		String numberOflineItems = application.countRowsInTableAsString("numberOfLineItems_xpath");
		log("Number of line items for the order are "+numberOflineItems);
		application.click("orderViewButton_xpath");
		application.wait(15);
		application.validateOrderStatus("orderStatus_xpath");
		application.wait(5);
	}
	
	@Test(priority = 1)
	
	public void checkOrderStatusFromGrid() {
		application.click("salesButton_xpath");
		application.click("salesOrders_xpath");
		application.wait(15);
		application.clearFilter("clearFiltersButton_xpath");
		application.wait(5);
		application.click("salesOrderFilters_xpath");
		application.wait(3);
		application.type("filtersOrderId_xpath", readExcelData("order_id", "Check_Order_Status"));
		application.wait(3);
		application.click("applyFilter_xpath");
		application.wait(10);
		String numberOflineItems = application.countRowsInTableAsString("numberOfLineItems_xpath");
		log("Number of line items for the order are "+numberOflineItems);
		application.validateOrderStatus("orderStatuscolumn_xpath");
		application.wait(5);
	}
}
