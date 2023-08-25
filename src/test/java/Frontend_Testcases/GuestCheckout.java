package Frontend_Testcases;

import org.openqa.selenium.remote.html5.AddApplicationCache;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class GuestCheckout extends JohnsonBase{

	@Test
	public void guestShippingAddress() {
		application.scrollTo("cartButton_xpath");
		application.click("cartButton_xpath");
		application.click("miniCartCheckout_xpath");
		application.wait(5);
		application.validateElementPresentText("guestCheckoutTitle_xpath", "Guest Checkout Title");
		application.validateElementPresentText("shippingAddressTitle_xpath", "Shipping Address Title");
		application.scrollTo("shippingAddressContinue_xpath");
		application.wait(3);
		application.click("shippingAddressContinue_xpath");
		application.scrollTo("firstNameText_xpath");
		application.wait(3);
		application.validateElementPresentText("firstNameFieldRequired_xpath", "First name Field required");
		application.validateElementPresentText("lastNameFieldRequired_xpath", "last name Field required");
		application.scrollTo("shippingEmailText_xpath");
		application.wait(3);
		application.validateElementPresentText("emailFieldRequired_xpath", "Email Field Required");
		application.validateElementPresentText("phoneFieldRequired_xpath", "Phone number Field Required");
		//application.scrollTo("shippingStreetAddress_xpath");
		//application.wait(3);
		application.validateElementPresentText("streetAddressFieldRequired_xpath", "Street address Field Required");
		application.scrollTo("shippingCityText_xpath");
		application.wait(3);
		application.validateElementPresentText("zipcodeFieldRequired_xpath", "Pincode required Field");
		application.validateElementPresentText("cityFieldRequired_xpath", "City Required Field");
		application.validateElementPresentText("stateFieldRequired_xpath", "State Required Field");
		application.scrollTo("shippingCountryText_xpath");
		application.wait(3);
		application.selectFromDown("shippingCountryDropDown_id", "country", "Guest_Checkout");
		application.wait(3);
		application.scrollTo("firstNameText_xpath");
		application.wait(2);
		application.type("shippingFirstName_id", readExcelData("first_name", "Guest_Checkout"));
		application.wait(2);
		application.type("shippingLastName_id", readExcelData("last_name", "Guest_Checkout"));
		application.wait(2);
		application.type("shippingEmail_id", readExcelData("email", "Guest_Checkout"));
		application.wait(2);
		application.type("shippingPhoneNumber_id", readExcelData("phone_number", "Guest_Checkout"));
		application.wait(2);
		application.type("shippingStreetAddress_name", readExcelData("street_address", "Guest_Checkout"));
		application.wait(2);
		
		application.type("shippingStreetAddress2_name", readExcelData("street_address2", "Guest_Checkout"));
		application.scrollTo("shippingCityText_xpath");
		application.wait(2);
		application.type("shippingPincode_id", readExcelData("pincode", "Guest_Checkout"));
		application.wait(2);
//		application.type("shippingCity_id", readExcelData("city", "Guest_Checkout"));
//		application.wait(2);
		//application.selectFromDown("shippingState_xpath", "state" , "Guest_Checkout" );
		application.type("shippingState_id", readExcelData("state", "Guest_Checkout"));
		application.wait(2);
		application.selectFromDown("shippingCountryDropDown_id", "country", "Guest_Checkout");
		application.scrollTo("shippingAddressContinue_xpath");
		application.wait(3);
		application.click("shippingAddressContinue_xpath");
		application.wait(2);
		application.scrollByValue("-1000");
		application.wait(2);
		application.validateElementPresent("shippingChangeAddress_xpath");
		application.click("shippingChangeAddress_xpath");
		application.wait(2);
		application.validateElementPresentText("changeShippingFirstName_xpath" , "First name input field");
		application.validateElementPresentText("changeShippingLastName_xpath" , "Last name input field");
		application.validateElementPresentText("changeShippingEmail_xpath" , "Email input field");
		application.validateElementPresentText("changeShippingPhoneNumber_xpath" , "Phone number");
		application.validateElementPresentText("changeShippingStreetAddress_xpath" , "Street address");
		application.validateElementPresentText("changeShippingStreetAddress2_xpath" , "Street address 2");
		application.validateElementPresentText("changeShippingPincode_xpath" , "Pincode");
		application.scrollTo("changeShippingCountry_xpath");
		application.validateElementPresentText("chanageShippingCity_xpath" , "City");
		application.validateElementPresent("changeShippingState_xpath");
		application.validateElementPresent("changeShippingCountry_xpath");
		application.validateElementPresent("changeShippingPopupCancel_xpath");
		application.click("changeShippingPopupCancel_xpath");
		application.wait(2);
	}
	@Test(priority =2)
	public void guestCheckoutShippingMethod() {
		
		application.validateElementPresentText("freeShippingMethodElement_xpath" , "Free shipping");
		application.validateElementPresentText("fixedShippingMethodElement_xpath" , "Fixed shipping");
		String selectShippingMethod = application.readExcelDataAsString("shipping_method","Login_OrderPlace");
		log("selectShippingMethod");
		String freeShipping = "free";
		String fixedShipping = "fixed";
		if(selectShippingMethod.toLowerCase().equals(freeShipping)) {
			application.click("freeShippingMethod_xpath");
		}else if(selectShippingMethod.toLowerCase().equals(fixedShipping)) {
			application.click("fixedShippingMethod_xpath");
		}else {
			test.log(Status.INFO, "Shipping method is not defined in excel");
		}
		application.validateElementPresent("shippingMethodContinue_xpath");
		application.click("shippingMethodContinue_xpath");
		application.wait(3);
		
		application.validateElementPresent("shippingMethodEditButton_xpath");
		application.click("shippingMethodEditButton_xpath");
		application.wait(2);
		
		application.validateElementPresent("freeShippingMethodPopup_xpath");
		application.validateElementPresent("fixedShippingMethodPopup_xpath");
		application.validateElementPresent("cancelButtonShippingMethodPopup_xpath");
		application.validateElementPresent("updateButtonShippingMethodPopup_xpath");
		application.click("cancelButtonShippingMethodPopup_xpath");
		application.wait(40);
	}
	
	@Test(priority=3)
	public void guestCheckoutReviewOrder() {
		application.scrollTo("reviewOrderText_xpath");
		application.click("razorpayRadioButton_xpath");
		application.wait(2);
		application.validateElementPresent("billingShippingSameValidation_xpath");
		String billingShipping = application.readExcelDataAsString("billing_shipping","Guest_Checkout");
		log(billingShipping);
		if(billingShipping.toLowerCase().equals("same")) {
			test.log(Status.INFO, "Billing and Shipping address must be same");
		}else if(billingShipping.toLowerCase().equals("not same")) {
			application.click("billingShippingSameRadioButton_xpath");
			application.validateElementPresent("billingFirstName_xpath");
			application.type("billingFirstName_xpath", readExcelData("billing_firstname", "Guest_Checkout"));
			application.validateElementPresent("billingLastName_xpath");
			application.type("billingLastName_xpath", readExcelData("billing_lastname", "Guest_Checkout"));
			application.validateElementPresent("billingPhoneNumber_xpath");
			application.type("billingPhoneNumber_xpath", readExcelData("billing_phone_number", "Guest_Checkout"));
			application.validateElementPresent("billingStreetAddress_xpath");
			application.type("billingStreetAddress_xpath", readExcelData("billing_street_address", "Guest_Checkout"));
			application.scrollTo("billingPostalCode_xpath");
			application.validateElementPresent("billingStreetAddress2_xpath");
			application.type("billingStreetAddress2_xpath", readExcelData("billing_street_address2", "Guest_Checkout"));
			application.validateElementPresent("billingPostalCode_xpath");
			application.type("billingPostalCode_xpath", readExcelData("billing_pincode", "Guest_Checkout"));
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
		application.type("shippingMethodCoupon_xpath", readExcelData("coupon_code", "Guest_Checkout"));
		application.validateElementPresent("couponApplyButton_xpath");
		application.click("couponApplyButton_xpath");
		application.wait(5);
		application.click("reviewOrderButton_xpath");
		application.wait(5);
	}
	@Test(priority = 4)
	
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
//	
//	
//	public void guestCheckoutPaymentGateway() {
//		application.wait(5);
//		application.type("gatewayPhoneNumnber_xpath", readExcelData("mobile", "Payment_Gateway"));
//		application.click("gatewayproceedButton_xpath");
//		application.wait(2);
//		application.click("gatewayCardOption_xpath");
//		application.wait(2);
//		application.type("gatewayCardNumberLocator_xpath", "gatewayCardNumber");
//		application.type("gatewayCardExpiry_xpath", "gatewayCardExpiry");
//		application.type("gatewayCardCvv_xpath", "gatewayCardCvv");
//		application.wait(2);
//		application.click("gatewayPayNowButton_xpath");
//		application.wait(10);
//	}
	
	@Test(priority = 5)
	public void thankyouPage() {
		application.waitForPageToLoad(driver , 200);
		application.validateElementPresent("thankyouText_xpath");
		application.validateElementPresent("homePageSignin_xpath");
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
