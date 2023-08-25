package Backend_Testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class InventoryManagement extends JohnsonBase{

	@Test
	public void inventoryUpload() throws Exception, Exception {
		application.click("dealerButton_xpath");
		application.wait(2);
		application.click("dealerUploadInventoryButton_xpath");
		application.validateElementPresent("uploadInventoryButton_xpath");
		//application.click("uploadInventoryButton_xpath");
		application.uploadInventory("uploadInventoryButton_xpath");
		try {
			WebElement successfulMessage = application.getText1("uploadInventoryValidation_xpath");
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.textToBePresentInElement(successfulMessage, "Imported Successfully"));
		}catch(Exception e ) {
			test.log(Status.FAIL, "pop up is not displayed");
			generateScreenshots();
		}
		
	}
	@Test
	public void checkInventory() {
		application.click("dealerButton_xpath");
		application.wait(2);
		application.click("dealerUploadInventoryButton_xpath");
		application.wait(5);
		application.validateElementPresent("uploadInventoryButton_xpath");
		application.validateElementPresent("uploadInventorySearchBySKU_xpath");
		application.type("uploadInventorySearchBySKU_xpath", readExcelData("sku", "Inventory_Management"));
		application.clickEnter("uploadInventorySearchBySKU_xpath");
		application.wait(5);
		String qtyExpected =  application.readExcelDataAsString("current_stock", "Inventory_Management");
		String actualQty = null;
		try {
			actualQty = application.getStringText("validateInventory_xpath");
		}catch(Exception e) {
			test.log(Status.FAIL, "QTY is empty in upload inventory against the provided SKU.");
			generateScreenshots();
		}
		log(actualQty);
		log(qtyExpected);
		
		if(actualQty.equals(qtyExpected)) {
			test.log(Status.PASS, "Quantity is uploaded.");
			generateScreenshots();
			
		}else if(!actualQty.equals(qtyExpected)) {
			test.log(Status.PASS,actualQty+ " Quantity is uploaded.");
			generateScreenshots();
		}
		application.wait(5);
	}
}
