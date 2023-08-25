package Frontend_Testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class AddToCart extends JohnsonBase{
	@Test
	
	public void simpleProductAddToCartListingPage() {
		application.waitForPageToLoad(driver , 200);
		application.wait(5);
		application.selectCategoryFromExcel("category","Add_To_Cart", "selectRefrigeratorCategory_xpath", 
				"selectWashingCategory_xpath", "selectAcCategory_xpath", "selectMicrowavesCategory_xpath", "selectDishwasherCategory_xpath", "selectPurifierCategory_xpath");
		application.wait(5);
		application.scrollTo("1stproductname_xpath");
		application.wait(3);
		application.getText("1stproductname_xpath");
		application.click("1stAddToCartButton_xpath");
		application.validatePincodePopup("popupPincode_xpath");
		application.wait(2);
		application.click("popupCancel_xpath");
		application.wait(3);
		application.validatePincodePopupAfterCancel("popupPincode_xpath");	
		application.wait(3);
		application.click("1stAddToCartButton_xpath");
		application.wait(2);
		application.validatePincodePopup("popupPincode_xpath");
		application.type("popupPincode_xpath", readExcelData("invalid_pincode", "Add_To_Cart"));
		application.click("checkPincodeButton_xpath");
		application.wait(3);
		application.validatePincodefield("popupPincode_xpath","pindcodeErrorValidation_xpath");
		application.clear("popupPincode_xpath");
		application.type("popupPincode_xpath", readExcelData("valid_pincode", "Add_To_Cart"));
		application.wait(2);
		application.click("checkPincodeButton_xpath");
		application.wait(2);
		application.validateAddToCartPopup("validatePopUp_xpath");
		application.wait(3);
		application.validateCartItem("validateCartItem_xpath");
		application.scrollByValue("-1000");
		application.validateCartItem("validateCartItem_xpath");
		application.wait(3);	
		}
	@Test

	public void simpleProductAddToCartDetailPage() {
	    application.waitForPageToLoad(driver,200);
	    application.click("logo_xpath");
	    application.wait(5);
	    application.selectCategoryFromExcel("category", "Add_To_Cart", "selectRefrigeratorCategory_xpath", "selectWashingCategory_xpath", "selectAcCategory_xpath", "selectMicrowavesCategory_xpath", "selectDishwasherCategory_xpath", "selectPurifierCategory_xpath");
	    application.wait(5);
	    try {
	    application.scrollTo("1stproductname_xpath");
	    application.wait(5);
	    String productText = application.getStringText("1stproductname_xpath");
	    application.scrollTo("1stViewDetailButton_xpath");
	    application.click("1stViewDetailButton_xpath");
	    application.wait(5);
	    
	    String nextPageUrl = driver.getCurrentUrl();
	    
	    String nextPageProductText = application.getStringText("productname_xpath");
	    driver.navigate().back(); // Go back to the previous page
	    application.waitForPageToLoad(driver,200);
	    application.wait(5);
	    
	    application.scrollByValue("-300");
	    application.wait(3);
	    application.validateProductText(productText, nextPageProductText);

	    driver.navigate().to(nextPageUrl);
	    application.waitForPageToLoad(driver,200);
	    application.wait(5);

//	    application.validateWishlistIcon("wishlistIcon_xpath");
	    application.scrollByValue("200");
	    
	    //Invalid Data
	    application.type("enterPincode_xpath", readExcelData("invalid_pincode", "Add_To_Cart"));
	    application.wait(2);
	    application.click("applyPincodeButton_xpath");
	    application.validatePincodefield("enterPincode_xpath", "pincodeErrorlocator_xpath");
	    //Valid data
	    application.clear("enterPincode_xpath");
	    application.type("enterPincode_xpath", readExcelData("valid_pincode", "Add_To_Cart"));
	    application.wait(2);
	    application.click("applyPincodeButton_xpath");
	    application.validateCorrectPincode("correctPincodeText_xpath" , "changePincodeButton_xpath");
	    application.wait(3);
	    application.click("changePincodeButton_xpath");
	    application.wait(2);
	    application.type("enterPincode_xpath", readExcelData("changed_valid_pincode", "Add_To_Cart"));
	    application.wait(2);
	    application.click("applyPincodeButton_xpath");
	    application.wait(2);
	    application.click("detailPageAddToCartButton_xpath");
	    application.wait(1);
	    try{
	 	    application.validateAddToCartPopup();
	 	    generateScreenshots();
	    }catch(Exception e){
	    	application.validatePincodePopupAfterCancel("popupPincode_xpath");
	    }
	   application.wait(5);
	   application.scrollByValue("-1000");
	   application.validateCartItem("validateCartItem_xpath");
	   generateScreenshots();
	   
	    }catch(Exception e) {
//			WebElement ele = null;
//			try {
//				 ele = driver.findElement(getLocator("noProductsFound_xpath"));
//				 if(ele.isDisplayed()) {
//						test.log(Status.INFO, "No Products are found");
//						generateScreenshots();
//					}else {
//						test.log(Status.FAIL, "NoProductsfound message not visible");
//						generateScreenshots();
//					}
//			}catch (Exception ee) {
//				test.log(Status.PASS, "Products are visible");
//			}
//			
//		}
	    
	}
	}
	
	@Test
	
	public void configurableAddToCartListingPage(){
		application.waitForPageToLoad(driver , 200);
		application.click("logo_xpath");
		application.wait(5);
		application.selectCategoryFromExcel("category","Add_To_Cart", "selectRefrigeratorCategory_xpath", "selectWashingCategory_xpath", 
				"selectAcCategory_xpath", "selectMicrowavesCategory_xpath", "selectDishwasherCategory_xpath", "selectPurifierCategory_xpath");
		application.wait(5);
		application.scrollByValue("600");
		application.wait(2);
		application.scrollTo("configurableProductName_xpath");
		application.click("listingConfigurableAddToCartButton_xpath");
		application.wait(5);
		application.validateConfigurablePopup();
		application.wait(3);
		application.scrollTo("listingPageConfigurableProductName_xpath");
		application.scrollTo("brownButton_xpath");
		application.selectColorFromExcel("brownButton_xpath","whiteButton_xpath","product_colour", "Add_To_Cart");
		application.wait(2);
		application.selectFromDown("selectSize_xpath", "product_size" , "Add_To_Cart");
		application.wait(5);
		application.click("listingConfigurableAddToCartButton_xpath");
		application.wait(5);
		application.validatePincodePopup("popupPincode_xpath");
		application.type("popupPincode_xpath", readExcelData("invalid_pincode", "Add_To_Cart"));
		application.click("checkPincodeButton_xpath");
		application.wait(3);
		application.validatePincodefield("popupPincode_xpath","pindcodeErrorValidation_xpath");
		application.clear("popupPincode_xpath");
		application.type("popupPincode_xpath", readExcelData("valid_pincode", "Add_To_Cart"));
		application.wait(2);
		application.click("checkPincodeButton_xpath");
		application.wait(2);
		application.validateAddToCartPopup("validatePopUp_xpath");
		application.wait(3);
		application.validateCartItem("validateCartItem_xpath");
		application.wait(2);
	}
	
	@Test
	
	public void configurableAddToCartProductDetailPage() {
		application.waitForPageToLoad(driver , 200);
		application.click("logo_xpath");
		application.wait(5);
		application.selectCategoryFromExcel("category","Add_To_Cart", "selectRefrigeratorCategory_xpath", "selectWashingCategory_xpath", "selectAcCategory_xpath", "selectMicrowavesCategory_xpath", "selectDishwasherCategory_xpath", "selectPurifierCategory_xpath");
		application.wait(5);
		application.scrollByValue("800");
		application.wait(2);
		application.scrollTo("configurableProductName_xpath");
	    application.wait(5);
	    String productText = application.getStringText("configurableProductName_xpath");
	    application.click("configurableProductViewDetail_xpath");
	    application.wait(3);
	    application.scrollTo("productname_xpath");	
	    application.wait(5);
	    
	    String nextPageUrl = driver.getCurrentUrl();
	    
	    String nextPageProductText = application.getStringText("productname_xpath");
	    driver.navigate().back(); // Go back to the previous page
	    application.waitForPageToLoad(driver , 200);
	    application.wait(5);
	    
	    application.scrollByValue("-300");
	    application.wait(3);
	    application.validateProductText(productText, nextPageProductText);

	    driver.navigate().to(nextPageUrl);
	    application.waitForPageToLoad(driver ,200);
	    application.wait(5);

//	    application.validateWishlistIcon("wishlistIcon_xpath");
	    application.scrollByValue("200");
	    
	    //Invalid Data
	    application.type("enterPincode_xpath", readExcelData("invalid_pincode", "Add_To_Cart"));
	    application.wait(2);
	    application.click("applyPincodeButton_xpath");
	    application.validatePincodefield("enterPincode_xpath", "pincodeErrorlocator_xpath");
	    application.click("detailPageAddToCartButton_xpath");
	    application.validateConfigurablePopup();
	    //Valid data
	    application.clear("enterPincode_xpath");
	    application.type("enterPincode_xpath", readExcelData("valid_pincode", "Add_To_Cart"));
	    application.wait(2);
	    application.click("applyPincodeButton_xpath");
	    application.validateCorrectPincode("correctPincodeText_xpath" , "changePincodeButton_xpath");
	    application.wait(3);
	    application.click("changePincodeButton_xpath");
	    application.wait(2);
	    application.type("enterPincode_xpath", readExcelData("changed_valid_pincode", "Add_To_Cart"));
	    application.wait(2);
	    application.click("applyPincodeButton_xpath");
	    application.validateConfigurablePopup();
	    application.scrollTo("brownButton_xpath");
		application.selectColorFromExcel("brownButton_xpath","whiteButton_xpath","product_colour", "Add_To_Cart");
		application.wait(2);
		application.selectFromDown("selectSize_xpath", "product_size" , "Add_To_Cart");
		application.wait(5);
		application.scrollTo("detailPageAddToCartButton_xpath");
	    application.click("detailPageAddToCartButton_xpath");
	    application.wait(1);
	    try{
	 	    application.validateAddToCartPopup();
	    }catch(Exception e){
	    	application.validatePincodePopupAfterCancel("popupPincode_xpath");
	    }
	   application.wait(5);
	}
}
