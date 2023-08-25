package Frontend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;
import Utility.ApplicationKeyword;

public class MyAccount extends JohnsonBase{
	
	@Test
	
	public void myAccountAddressBook() {
		application.getText("homePageSignin_xpath");
		application.validateHiText("homePageSignin_xpath");
		application.click("homePageSignin_xpath");
		application.validateElementPresent("myAccountButton_xpath");
		application.click("myAccountButton_xpath");
		application.wait(3);
		application.validateElementPresent("myAccountRedirectionValidation_xpath");
		application.validateElementPresent("nameDisplayValidation_xpath");
		//application.validatePasswordVisiblity("passwordvisibility_xpath");
		application.wait(2);
		application.validateElementPresent("addressBook_xpath");
		application.click("addressBook_xpath");
		application.wait(5);
		application.validateElementPresent("addressBookAddAddresButton_xpath");
		application.click("addressBookAddAddresButton_xpath");
		application.wait(2);
		//application.validateAddAddressPopup();
	}
	
	@Test
	public void myAccountOrderHistory() {
		application.validateElementPresent("orderHistoryButton_xpath");
		application.click("orderHistoryButton_xpath");
		//application.
	}

}
