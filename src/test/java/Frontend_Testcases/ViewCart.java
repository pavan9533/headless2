package Frontend_Testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class ViewCart extends JohnsonBase{
	@Test
	
	public void viewCart() {
	    application.validateElementPresent("cartButton_xpath");
	    application.click("cartButton_xpath");
	    application.wait(3);
	    application.validateElementPresent("viewCartButton_xpath");
	    application.click("viewCartButton_xpath");
	    application.wait(5);
	    application.validateElementPresentText("viewCartProducts_xpath", "View Cart Button");
	    application.validateElementPresentText("increaseQtyButton_xpath", "Increase Quantity Button");
	    application.validateElementPresentText("decreaseQtyButton_xpath", "Decrease Quantity button");
	    application.validateElementPresentText("qty_name", "Quantity input Field");

	    String qty = application.getAttributeValue("qty_name", "value");
	    application.click("increaseQtyButton_xpath");
	    application.wait(3);
	    String increasedQty = application.getAttributeValue("qty_name", "value");

	    if (Integer.parseInt(increasedQty) == Integer.parseInt(qty) + 1) {
	        test.log(Status.PASS, "Quantity is increased on clicking the increase quantity button.");
	        generateScreenshots();
	    } else {
	        test.log(Status.FAIL, "Quantity is not increased after clicking the increase quantity button.");
	        generateScreenshots();
	    }

	    String qty2 = application.getAttributeValue("qty_name", "value");
	    application.click("decreaseQtyButton_xpath");
	    application.wait(3);
	    String decreasedQty = application.getAttributeValue("qty_name", "value");

	    if (Integer.parseInt(decreasedQty) == Integer.parseInt(qty2) - 1) {
	        test.log(Status.PASS, "Quantity is decreased on clicking the decrease quantity button.");
	        generateScreenshots();
	    } else {
	        test.log(Status.FAIL, "Quantity is not decreased after clicking the decrease quantity button.");
	        generateScreenshots();
	    }
	

		application.wait(3);
		String Total = application.getStringText("totalAmount_xpath");
		application.validateElementPresent("enterCouponValidation_xpath");
		application.click("enterCouponValidation_xpath");
		application.wait(2);
		application.type("couponCodeInput_id", readExcelData("coupon_code", "View_Cart"));
		application.validateElementPresent("applyCoupon_xpath");
		application.click("applyCoupon_xpath");
		application.wait(3);
		application.validateElementPresent("validateDiscountApplied_xpath");
		String cartPrice = application.getStringText("cartPriceTotal_xpath");
		String discountPrice = application.getStringText("discountPrice_xpath");
		cartPrice = cartPrice.replaceAll("[^\\d]", "");
		discountPrice = discountPrice.replaceAll("[^\\d]", "");
		Total = Total.replaceAll("[^\\d]", "");
		log(cartPrice);
		log(discountPrice);
		log(Total);
		double calculatedTotal = Double.parseDouble(cartPrice) + Double.parseDouble(discountPrice);
		double expectedTotal = Double.parseDouble(Total) +  Double.parseDouble(discountPrice);
//		System.out.println(calculatedTotal);
//		System.out.println(expectedTotal);
		if (calculatedTotal == expectedTotal) {
		    test.log(Status.PASS, "Discount coupon is calculated in the total amount");
		    generateScreenshots();
		} else {
		    test.log(Status.FAIL, "Discount coupon is not calculated in the total amount");
		    generateScreenshots();
		}
		application.validateElementPresentText("viewCartCheckoutButton_xpath", "Proceed to Checkout button");
		application.click("viewCartCheckoutButton_xpath");
		application.wait(5);
		application.validateElementPresentText("checkoutRedirection_xpath", "Checkout page Title");
		log("Redirected to Checkout page");
	}
}
