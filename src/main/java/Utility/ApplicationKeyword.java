package Utility;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ApplicationKeyword extends ValidationKeyword{
	
	
		
	
	public ApplicationKeyword() {
		String path = System.getProperty("user.dir")+"\\Properties\\env.properties";
		envprop = new Properties();
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(path);
			envprop.load(file);
			String environment = envprop.getProperty("env")+".properties";
			path =  System.getProperty("user.dir")+"\\Properties\\"+environment;
			file = new FileInputStream(path);
			prop.load(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		softAssert = new SoftAssert();
	}
	
	
	
	public void setReport(ExtentTest test) {
		this.test = test;
	}
	

	    public void uploadInventory(String uploadLocator) throws InterruptedException, Exception  {
	    	
	        String filePath = System.getProperty("user.dir")+"\\Excel\\sample_inventory.csv";

	        // Click on the upload button to trigger the native file upload dialog
	        WebElement uploadButton = driver.findElement(getLocator(uploadLocator));
	        uploadButton.click();

	        // Wait for the file upload dialog to appear
	        Thread.sleep(2000); // Adjust the wait time as needed

	        // Set the file path to the clipboard
	        StringSelection stringSelection = new StringSelection(filePath);
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	        // Simulate keyboard actions to paste the file path and press Enter
	        Robot robot = new Robot();
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);

	        // Wait for the file to be uploaded (add appropriate wait time if needed)
	        Thread.sleep(5000); // Example: Wait for 5 seconds after pressing Enter

	        // Continue with any further actions or verifications after the file upload
	    }
	


	

	

	
	public void validateConfigurablePopup() {
	    try {
	        Alert alert = driver.switchTo().alert();
	        String popupText = alert.getText();
	        
	        String expectedText = "Please Select All Variants";
	        
	        if (popupText.equals(expectedText)) {
	            test.log(Status.PASS, "Popup text is as expected: " + popupText);
	            generateScreenshots();
	        } else {
	            test.log(Status.FAIL, "Popup text is not as expected. Expected: " + expectedText + ", Actual: " + popupText);
	            generateScreenshots();
	        }
	        
	        alert.accept(); // Accept the alert after validation
	        
	    } catch (NoAlertPresentException e) {
	        test.log(Status.INFO, "No alert found");
	        // Handle the case when no alert is present
	    }
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void clickAssignDealerButton(String tableLocator, String orderIDColumn, String subOrderIDColumn, String nameOfSheet) {
	    String orderIDs = readExcelData(orderIDColumn, nameOfSheet).get(0);
	    String subOrderIDs = readExcelData(subOrderIDColumn, nameOfSheet).get(0);

	    WebElement table = driver.findElement(getLocator(tableLocator));
	    List<WebElement> rows = table.findElements(By.tagName("tr"));

	    // Iterate over the rows of the table
	    for (int i = 0; i < rows.size(); i++) {
	        WebElement row = rows.get(i);
	        List<WebElement> columns = row.findElements(By.tagName("td"));
	        int lastColumnIndex = columns.size() - 1; // Get the index of the last column

	        if (columns.size() >= 3) { // Check if there are at least three columns
	            String orderIDValue = columns.get(1).getText(); // Assuming the OrderID is in the second column
	            String subOrderIDValue = columns.get(2).getText(); // Assuming the SubOrderID is in the third column

	            if (orderIDValue.equals(orderIDs) && subOrderIDValue.equals(subOrderIDs)) {
	                // Click the "Assign" button in the last column
	                int assignButtonIndex = lastColumnIndex - 1; // Assuming the assign button is the second-to-last column
	                WebElement assignButton = columns.get(assignButtonIndex).findElement(getLocator("assignButton_xpath"));
	                assignButton.click();
	                log("Assign button is clicked");
	                break; // Exit the rows loop after finding a match
	            }
	        }
	    }
	}

//	public void clickAssignDealerButton(String tableLocator, String orderIDColumn, String subOrderIDColumn, String nameOfSheet) {
//	    List<String> orderIDs = readExcelData(orderIDColumn, nameOfSheet);
//	    List<String> subOrderIDs = readExcelData(subOrderIDColumn, nameOfSheet);
//
//	    WebElement table = driver.findElement(getLocator(tableLocator));
//	    List<WebElement> rows = table.findElements(By.tagName("tr"));
//
//	    // Iterate over the rows of the table
//	    for (int i = 0; i < rows.size(); i++) {
//	        WebElement row = rows.get(i);
//	        List<WebElement> columns = row.findElements(By.tagName("td"));
//	        int lastColumnIndex = columns.size() - 1; // Get the index of the last column
//
//	        if (columns.size() >= 2) { // Check if there are at least two columns
//	            String orderIDValue = columns.get(1).getText(); // Assuming the OrderID is in the second column
//
//	            if (orderIDValue.equals(orderIDs.get(i))) {
//	                // Iterate over the subOrderIDs list
//	                for (int j = 0; j < subOrderIDs.size(); j++) {
//	                    String subOrderIDValue = columns.get(2).getText(); // Assuming the SubOrderID is in the third column
//
//	                    if (subOrderIDValue.equals(subOrderIDs.get(j))) {
//	                        // Click the "Assign" button in the last column
//	                        int assignButtonIndex = lastColumnIndex - 1; // Assuming the assign button is the second-to-last column
//	                        WebElement assignButton = columns.get(assignButtonIndex).findElement(getLocator("(//a[text()='Assign'])[" + (i + 1) + "]"));
//	                        assignButton.click();
//	                        log("Assign button is clicked");
//
//	                        break; // Exit the subOrderIDs loop after finding a match
//	                    }
//	                }
//
//	                break; // Exit the rows loop after finding a match with the orderID
//	            }
//	        }
//	    }
//	}

	public void selectDealer(String tableLocator, String dealerCode, String nameOfSheet) {
	    WebElement table = driver.findElement(getLocator(tableLocator));
	    List<WebElement> rows = table.findElements(By.tagName("tr"));

	    // Iterate over the rows of the table
	    for (WebElement row : rows) {
	        List<WebElement> columns = row.findElements(By.tagName("td"));

	        if (columns.size() >= 2) { // Check if there are at least two columns
	            String dealerCodeValue = columns.get(1).getText(); // Assuming the dealer code is in the second column

	            if (dealerCodeValue.equals(dealerCode)) {
	                // Find the radio button in the first column and click it
	                WebElement radioButtonColumn = columns.get(0);
	                WebElement radioButton = radioButtonColumn.findElement(getLocator("assignDealerRadioButton_xpath"));
	                radioButton.click();
	                test.log(Status.PASS, "Dealer Assign radio button is clicked");
	                generateScreenshots();
	                break; // Exit the loop after selecting the dealer
	            }
	        }
	    }
	}
	
	public void clickOrderViewButton(String tableLocator, String orderIDColumn, String subOrderIDColumn, String nameOfSheet) {
	    List<String> orderIDs = readExcelData(orderIDColumn, nameOfSheet).subList(0, 1);
	    List<String> subOrderIDs = readExcelData(subOrderIDColumn, nameOfSheet).subList(0, 1);

	    WebElement table = driver.findElement(getLocator(tableLocator));
	    List<WebElement> rows = table.findElements(By.tagName("tr"));

	    // Iterate over the rows of the table
	    for (int i = 0; i < rows.size(); i++) {
	        WebElement row = rows.get(i);
	        List<WebElement> columns = row.findElements(By.tagName("td"));
	        int lastColumnIndex = columns.size() - 1; // Get the index of the last column

	        if (columns.size() >= 3) { // Check if there are at least three columns
	            String orderIDValue = columns.get(1).getText(); // Assuming the OrderID is in the second column
	            String subOrderIDValue = columns.get(2).getText(); // Assuming the SubOrderID is in the third column

	            if (orderIDValue.equals(orderIDs.get(0)) && subOrderIDValue.equals(subOrderIDs.get(0))) {
	                // Click the "Order View" button in the last column
	                int orderViewButtonIndex = lastColumnIndex; // Assuming the order view button is in the last column
	                WebElement orderViewButton = columns.get(orderViewButtonIndex).findElement(getLocator("orderViewButtons_xpath"));
	                orderViewButton.click();
	                log("Order View button is clicked");
	                break; // Exit the rows loop after finding a match
	            }
	        }
	    }
	}

	
	public void bestSellerClickAddToCartForProduct(String productNameLocator, String addToCartLocator, String viewDetailsLocator, String columnName, String nameOfSheet, String sliderLocator) {
	    List<String> productNames = readExcelData(columnName, nameOfSheet);
	    WebElement slider = driver.findElement(getLocator(sliderLocator));

	    // Iterate over the products in the slider
	    for (int i = 0; i < productNames.size(); i++) {
	        String productName = productNames.get(i);
	        WebElement product = slider.findElements(getLocator(productNameLocator)).get(i);

	        if (productName.contains(productName)) {
	            // Find the corresponding Add to Cart button
	            WebElement addToCartButton = product.findElement(getLocator(addToCartLocator));

	            // Click on the Add to Cart button
	            addToCartButton.click();

	            // Optionally, you can also click on the View Details button if needed
	            // WebElement viewDetailsButton = product.findElement(getLocator(viewDetailsLocator));
	            // viewDetailsButton.click();

	            break;
	        }
	    }
	}
	
	@SuppressWarnings("unlikely-arg-type")
//	public String getPrice(String tableLocator, String orderIDColumn, String subOrderIDColumn, String nameOfSheet) {
//	    String orderID = readExcelData(orderIDColumn, nameOfSheet).get(0);
//	    String subOrderID = readExcelData(subOrderIDColumn, nameOfSheet).get(0);
//
//	    WebElement table = driver.findElement(getLocator(tableLocator));
//	    List<WebElement> rows = table.findElements(By.tagName("tr"));
//
//	    // Iterate over the rows of the table
//	    for (WebElement row : rows) {
//	        List<WebElement> columns = row.findElements(By.tagName("td"));
//
//	        if (columns.size() >= 3) { // Check if there are at least three columns
//	            String orderIDValue = columns.get(1).getText(); // Assuming the OrderID is in the first column
//	            String subOrderIDValue = columns.get(2).getText(); // Assuming the SubOrderID is in the second column
//
//	            if (orderIDValue.equals(orderID) && subOrderIDValue.equals(subOrderID)) {
//	                String price = columns.get(6).getText(); // Assuming the Price is in the third column
//	                return price;
//	            }
//	        }
//	    }
//
//	    // Return null if no matching orderID and subOrderID found
//	    return null;
//	}
	public String getPrice(String tableLocator, String orderIDColumn, String subOrderIDColumn, String nameOfSheet) {
	    String orderID = readExcelData(orderIDColumn, nameOfSheet).get(0);
	    String subOrderID = readExcelData(subOrderIDColumn, nameOfSheet).get(0);

	    WebElement table = driver.findElement(getLocator(tableLocator));
	    List<WebElement> rows = table.findElements(By.tagName("tr"));

	    // Iterate over the rows of the table
	    for (WebElement row : rows) {
	        List<WebElement> columns = row.findElements(By.tagName("td"));

	        if (columns.size() >= 3) { // Check if there are at least three columns
	            String orderIDValue = columns.get(1).getText(); // Assuming the OrderID is in the first column
	            String subOrderIDValue = columns.get(2).getText(); // Assuming the SubOrderID is in the second column

	            if (orderIDValue.equals(orderID) && subOrderIDValue.equals(subOrderID)) {
	                String price = columns.get(6).getText(); // Assuming the Price is in the seventh column

	                // Remove non-digit characters from the price string
	                price = price.replaceAll("[^\\d.]", "");

	                return price;
	            }
	        }
	    }

	    // Return null if no matching orderID and subOrderID found
	    return null;
	}
	public void modifyEditableColumn(String tableLocator, int editableColumnIndex,int priceColumnIndex, String price, String editableLocator) {
	    WebElement table = driver.findElement(getLocator(tableLocator));
	    List<WebElement> rows = table.findElements(By.tagName("tr"));

	    for (WebElement row : rows) {
	        List<WebElement> columns = row.findElements(By.tagName("td"));

	        if (columns.size() >= editableColumnIndex + 1) {
	            // Get the price value from the price column
	            String priceValue = columns.get(priceColumnIndex).getText();

	            // Remove special characters from the price values
	            String cleanedPriceValue = priceValue.replaceAll("[^0-9.]", "");
	            String cleanedPrice = price.replaceAll("[^0-9.]", "");
	            log("expected "+cleanedPrice);
	            log("actual "+cleanedPriceValue);
	            

	            // If the cleaned price value matches the cleaned provided price
	            if (cleanedPriceValue.equals(cleanedPrice)) {
	                // Modify the editable column
	                WebElement editableElement = columns.get(editableColumnIndex).findElement(getLocator(editableLocator));
	                editableElement.clear();
	                editableElement.sendKeys("0");
	                test.log(Status.PASS, "other QTY is changed to zero");
	            }
	        }
	    }
	}

//	public void modifyEditableColumn(String tableLocator, int editableColumnIndex, String price, String editableLocator) {
//	    WebElement table = driver.findElement(getLocator(tableLocator));
//	    List<WebElement> rows = table.findElements(By.tagName("tr"));
//
//	    for (WebElement row : rows) {
//	        List<WebElement> columns = row.findElements(By.tagName("td"));
//
//	        if (columns.size() > editableColumnIndex) {
//	            WebElement editableElement = columns.get(editableColumnIndex).findElement(getLocator(editableLocator));
//	            editableElement.clear();
//	            editableElement.sendKeys("0");
//	        }
//	    }
//	}
//	public void selectFilter(String filterType, String filterValue, String filterLocator) {
//	    WebElement filterBlock = driver.findElement(getLocator(filterLocator));
//	    WebElement filterElement = filterBlock.findElement(By.xpath("//span[text()='" + filterType + "']"));
//	    filterElement.click();
//	    WebElement filterOption = filterBlock.findElement(By.xpath("//span[text()='" + filterValue + "']"));
//	    filterOption.click();
//	}
	
	public void selectFilter(String filterTypeColumnName, String filterValueColumnName, String filterSheetName, String filterLocator) {
        List<String> filterTypes = readExcelData(filterTypeColumnName, filterSheetName);
        List<String> filterValues = readExcelData(filterValueColumnName, filterSheetName);

        WebElement filterBlock = driver.findElement(getLocator(filterLocator));

        for (int i = 0; i < filterTypes.size(); i++) {
            String filterType = filterTypes.get(i);
            String filterValue = filterValues.get(i);

            WebElement filterElement = filterBlock.findElement(By.xpath("//span[text()='" + filterType + "']"));
            filterElement.click();
            WebElement filterOption = filterBlock.findElement(By.xpath("//span[text()='" + filterValue + "']"));
            filterOption.click();
        }
    }
//	 public void validatePriceRange(String priceListLocator, String priceRange) {
//	        String[] rangeValues = priceRange.split("-");
//	        double minRange = Double.parseDouble(rangeValues[0].trim());
//	        double maxRange = Double.parseDouble(rangeValues[1].trim());
//
//	        List<WebElement> priceElements = driver.findElements(getLocator(priceListLocator));
//
//	        for (WebElement priceElement : priceElements) {
//	            double priceValue = Double.parseDouble(priceElement.getText().replaceAll("[^\\d.]", ""));
//	            if (priceValue > minRange || priceValue < maxRange) {
//	            	test.log(Status.PASS, "Prices range between "+priceRange);
//	            	generateScreenshots();
//	            }else if((priceValue < minRange || priceValue > maxRange)){
//	            	test.log(Status.FAIL, "Price does not range between "+priceRange);
//	            	generateScreenshots();
//	            }
//	        }
//	    }
	
	 public void validatePriceRange(String priceListLocator, String priceRangeColumn, String priceSheetName) {
	        List<String> priceRanges = readExcelData(priceRangeColumn, priceSheetName);

	        List<WebElement> priceElements = driver.findElements(getLocator(priceListLocator));

	        for (int i = 0; i < priceElements.size(); i++) {
	            WebElement priceElement = priceElements.get(i);
	            String priceText = priceElement.findElement(By.tagName("h3")).getText();
	            double priceValue = Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));

	            String[] rangeValues = priceRanges.get(i).split("-");
	            double minRange = Double.parseDouble(rangeValues[0].trim());
	            double maxRange = Double.parseDouble(rangeValues[1].trim());

	            if (priceValue >= minRange && priceValue <= maxRange) {
	                test.log(Status.PASS, "Price " + priceText + " is within the range " + priceRanges.get(i));
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, "Price " + priceText + " is NOT within the range " + priceRanges.get(i));
	                generateScreenshots();
	            }
	            scrollByValue("500");
	            wait(5);
	        }
	    }

}

