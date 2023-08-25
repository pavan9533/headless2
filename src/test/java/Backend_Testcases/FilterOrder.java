package Backend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class FilterOrder extends JohnsonBase {
	
	@Test
	
	public void filterOrder() {
		application.click("salesButton_xpath");
		application.wait(2);
		application.click("salesOrders_xpath");
		application.wait(15);
		application.clearFilter("clearfilterbutton_xpath");
		application.wait(15);
		application.click("filterButton_xpath");
		application.type("orderID_name", readExcelData("order_id", "Filter Order"));
		application.click("applyFiltersButton_xpath");
		application.wait(5);
		application.validateOrderID("validateOrderID", "order_id", "Filter Order");
		application.wait(15);
	}

}
