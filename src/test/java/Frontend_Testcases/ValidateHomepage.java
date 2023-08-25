package Frontend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class ValidateHomepage extends JohnsonBase{
	@Test
	
	public void validateHomepage() {
		application.validateElementPresentText("homepageCategoryHamBurgerMenu_xpath" , "Category HamBurgerMenu");
		application.validateElementPresent("homepageHeaderMail_xpath");
		application.validateElementPresent("homepageHeaderFreeShipping_xpath");
		application.validateElementPresent("homepageHeaderMoneybackGaurantee_xpath");
		application.validateElementPresent("homepageHeaderOnlineSupport_xpath");
		application.validateElementPresent("homepageHeaderMobileNumber_xpath");
		application.validateElementPresent("websiteLogo_xpath");
		application.validateElementPresent("homepageAllCategoriesText_xpath");
		application.validateElementPresent("cartButton_xpath");
		application.validateElementPresent("homePageSignin_xpath");
		application.validateElementPresent("selectRefrigeratorCategory_xpath");
		application.validateElementPresent("selectWashingCategory_xpath");
		application.validateElementPresent("selectAcCategory_xpath");
		application.validateElementPresent("selectMicrowavesCategory_xpath");
		application.validateElementPresent("selectDishwasherCategory_xpath");
		application.validateElementPresent("selectPurifierCategory_xpath");
		application.validateElementPresentText("bannerNextButton_xpath", "Banner Next Button");
		application.click("bannerNextButton_xpath");
		application.validateElementPresentText("bannerPreviousButton_xpath", "Banner Previous Button");
		application.wait(2);
		application.click("bannerPreviousButton_xpath");
		application.wait(2);
		application.scrollByValue("300");
		application.validateElementPresent("homepageFreeDeliveryText_xpath");
		application.validateElementPresent("homepageBigSavingText_xpath");
		application.validateElementPresent("homepageOnlineSupportText_xpath");
		application.validateElementPresent("homepageMoneyBackText_xpath");
		application.wait(2);
		application.scrollByValue("300");
		application.validateElementPresentText("newArrivalsBanner_xpath" , "New Arrivals Banner");
		
	}

}
