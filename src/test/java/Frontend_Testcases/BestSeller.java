package Frontend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class BestSeller extends JohnsonBase{
	
	@Test
	public void bestSellerAddToCart() {
		application.scrollTo("bestSellerProduct_xpath");
		application.wait(2);
		application.bestSellerClickAddToCartForProduct("bestSellerProductNameLocator_xpath", "bestSellerAddtoCart_xpath" , 
				"bestSellerViewDetailsButton_xpath", "best_seller_Product_name", "Homepage" , "bestSellerSliderLocator_xpath");
		application.wait(5);
		application.typeAfterSwitch("bestSellerPincodeInput_xpath", readExcelData("pincode", "Homepage"));
		application.click("bestSellerPincodeCheckButton_xpath");
		application.wait(1);
		application.validateAddToCartPopup("validatePopUp_xpath");
		application.wait(5);
		application.scrollTo("cartButton_xpath");
		application.validateCartItem("validateCartItem_xpath");
	}

}
