package Backend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class OrderInvoice extends JohnsonBase{
	@Test
	
	public void OrderInvoice() {
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
	    String price = application.getPrice("orderGridTableLocator_xpath", "order_id", "sub_order_id", "Order_Management");
	    if (price != null) {
	        log(price);
	        application.validateElementPresent("rowIsPresentValidation_xpath");
	        application.validateElementPresentText("orderViewButton_xpath", "View Button");
	        application.click("orderViewButton_xpath");
	        application.wait(5);
	        String orderStatus = application.getStringText("orderStatusDetailPage_xpath");
	        log("Order status of the order is " + orderStatus);
	        application.validateElementPresent("invoiceButton_xpath");
	        application.click("invoiceButton_xpath");
	        application.wait(5);
	        String orderStatusInvoicePage = application.getStringText("orderStatusDetailPage_xpath");
	        log("Order status in Invoice page " + orderStatusInvoicePage);
	        application.scrollTo("paymentMethodText_xpath");
	        application.wait(2);
	        //application.scrollTo("invoicePageTable_xpath");
	        application.modifyEditableColumn("invoicePageTable_xpath", 3,7, price, "invoiceQtyInput_xpath");
	        application.wait(10);
	    } else {
	        log("Price not found for the given orderID and subOrderID.");
	    }
	}

}
