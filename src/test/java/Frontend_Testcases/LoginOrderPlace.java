package Frontend_Testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class LoginOrderPlace extends JohnsonBase {
	@Test(priority = 1)
	
	public void miniCartProceedToCheckout() {
		application.validateElementPresentText("cartButton_xpath", "Cart Button");
		application.click("cartButton_xpath");
		application.validateElementPresent("productsInCart_xpath");
		application.validateElementPresent("qtyOfProduct_xpath");
		String qty = application.getStringText("qtyOfProduct_xpath");
		log(qty);
		application.validateElementPresent("miniCartCheckoutButton_xpath");
		application.click("miniCartCheckoutButton_xpath");
		application.wait(10);
	}
	
	@Test(priority = 2)
	
	public void loginShippingAddress() {
		WebElement shippingAddress = application.getElement("changeAddress_xpath");
		if(shippingAddress.isDisplayed()) {
			test.log(Status.INFO, "Shipping address is already present");
		}else {
			application.validateElementPresentText("shippingAddressTitle_xpath", "Shipping Address Title");
			application.scrollTo("firstNameText_xpath");
			application.wait(2);
			application.type("shippingFirstName_id", readExcelData("first_name", "Login_OrderPlace"));
			application.wait(2);
			application.type("shippingLastName_id", readExcelData("last_name", "Login_OrderPlace"));
			application.wait(2);
			application.type("shippingEmail_id", readExcelData("email", "Login_OrderPlace"));
			application.wait(2);
			application.type("shippingPhoneNumber_id", readExcelData("phone_number", "Login_OrderPlace"));
			application.wait(2);
			application.type("shippingStreetAddress_name", readExcelData("street_address", "Login_OrderPlace"));
			application.wait(2);
			
			application.type("shippingStreetAddress2_name", readExcelData("street_address2", "Login_OrderPlace"));
			application.scrollTo("shippingCityText_xpath");
			application.wait(2);
			application.type("shippingPincode_id", readExcelData("pincode", "Login_OrderPlace"));
			application.wait(10);
			application.selectFromDown("shippingCountryDropDown_id", "country", "Login_OrderPlace");
			application.scrollTo("shippingAddressContinue_xpath");
			application.wait(3);
			application.click("shippingAddressContinue_xpath");
			application.wait(2);
			application.scrollByValue("-1000");
			application.wait(2);
			application.validateElementPresent("shippingChangeAddress_xpath");
			application.wait(3);
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test(priority = 3)
	public void loginShippingMethod() {
		application.click("shippingMethodEditButton_xpath");
		application.validateElementPresentText("freeShippingMethodElement_xpath" , "Free shipping");
		application.validateElementPresentText("fixedShippingMethodElement_xpath" , "Fixed shipping");
		List<String> selectShippingMethod = application.gettextFromExcel("shipping_method","Login_OrderPlace");
		log("selectShippingMethod");
		String freeShipping = "free";
		String fixedShipping = "fixed";
		if(selectShippingMethod.equals(freeShipping)) {
			application.click("freeShippingMethod_xpath");
		}else if(selectShippingMethod.equals(fixedShipping)) {
			application.click("fixedShippingMethod_xpath");
		}else {
			test.log(Status.INFO, "Shipping method is not defined in excel");
		}
		application.wait(5);
		application.validateElementPresent("cancelButtonShippingMethodPopup_xpath");
		application.validateElementPresent("updateButtonShippingMethodPopup_xpath");
		application.click("updateButtonShippingMethodPopup_xpath");
		application.wait(10);
	}
	
	@Test(priority = 4)
	
	public void loginReviewOrder() {
		application.scrollTo("reviewOrderText_xpath");
		application.click("razorpayRadioButton_xpath");
		application.wait(2);
		application.validateElementPresent("billingShippingSameValidation_xpath");
		String billingShipping = application.readExcelDataAsString("billing_shipping","Login_OrderPlace");
		log(billingShipping);
		if(billingShipping.toLowerCase().equals("same")) {
			test.log(Status.INFO, "Billing and Shipping address must be same");
		}else if(billingShipping.toLowerCase().equals("not same")) {
			application.click("billingShippingSameRadioButton_xpath");
			application.validateElementPresent("billingFirstName_xpath");
			application.type("billingFirstName_xpath", readExcelData("billing_firstname", "Login_OrderPlace"));
			application.validateElementPresent("billingLastName_xpath");
			application.type("billingLastName_xpath", readExcelData("billing_lastname", "Login_OrderPlace"));
			application.validateElementPresent("billingPhoneNumber_xpath");
			application.type("billingPhoneNumber_xpath", readExcelData("billing_phone_number", "Login_OrderPlace"));
			application.validateElementPresent("billingStreetAddress_xpath");
			application.type("billingStreetAddress_xpath", readExcelData("billing_street_address", "Login_OrderPlace"));
			application.scrollTo("billingPostalCode_xpath");
			application.validateElementPresent("billingStreetAddress2_xpath");
			application.type("billingStreetAddress2_xpath", readExcelData("billing_street_address2", "Login_OrderPlace"));
			application.validateElementPresent("billingPostalCode_xpath");
			application.type("billingPostalCode_xpath", readExcelData("billing_pincode", "Login_OrderPlace"));
			application.wait(5);
			application.scrollTo("saveBillingAddress_xpath");
			application.validateElementPresent("saveBillingAddress_xpath");
			application.wait(3);
		}
		application.scrollTo("couponcodeShippingMethod_xpath");
		application.wait(2);
		application.validateElementPresent("couponcodeShippingMethod_xpath");
		application.click("couponcodeShippingMethod_xpath");
		application.validateElementPresent("shippingMethodCouponCodeText_xpath");
		application.type("shippingMethodCoupon_xpath", readExcelData("coupon_code", "Login_OrderPlace"));
		application.validateElementPresent("couponApplyButton_xpath");
		application.click("couponApplyButton_xpath");
		application.wait(5);
		application.click("reviewOrderButton_xpath");
		application.wait(5);
	}
	@Test(priority = 5)
	
	public void guestCheckoutPlaceOrder() {
		application.scrollTo("validateItemInPlaceOrder_xpath");
		application.validateElementPresent("validateItemInPlaceOrder_xpath");
		application.validateElementPresent("totalPrice_xpath");
		application.scrollTo("grandTotalPlaceOrder_xpath");
		application.validateElementPresent("discountAppliedText_xpath");
		application.validateElementPresent("grandTotalPlaceOrder_xpath");
		
		application.validateElementPresent("placeOrderButton_xpath");
		application.click("placeOrderButton_xpath");
		application.wait(30);
	}
	@Test(priority = 6)
	public void thankyouPage() {
		application.waitForPageToLoad(driver , 200);
		application.validateElementPresent("thankyouText_xpath");
		//application.validateElementPresent("homePageSignin_xpath");
		application.validateElementPresent("orderNumberText_xpath");
		application.validateElementPresent("guestOrderThankyoushippingInfoText");
		application.validateElementPresent("guestOrderThankyoushippingMethodText");
		application.validateElementPresent("quickCheckoutText_xpath");
		application.validateElementPresent("guestOrderThankyouFirstNameText_xpath");
		application.validateElementPresent("guestOrderThankyouLastNameText_xpath");
		application.validateElementPresent("guestOrderThankyouEmailText_xpath");
		application.scrollTo("guestOrderThankyouNewsLetterText_xpath");
		application.validateElementPresent("guestOrderThankyouPasswordText_xpath");
		application.validateElementPresent("guestOrderThankyouNewsLetterText_xpath");
		application.validateElementPresent("guestOrderThankyouCheckoutButton_xpath");
		application.wait(5);
	}
}
