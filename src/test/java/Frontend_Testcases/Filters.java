package Frontend_Testcases;

import org.testng.annotations.Test;

import TestBase.JohnsonBase;

public class Filters extends JohnsonBase{
	
	
	@Test
	public void filter() {
		application.wait(3);
		application.selectCategoryFromExcel("category","Add_To_Cart", "selectRefrigeratorCategory_xpath", 
				"selectWashingCategory_xpath", "selectAcCategory_xpath", "selectMicrowavesCategory_xpath", "selectDishwasherCategory_xpath", "selectPurifierCategory_xpath");
		application.wait(5);
		application.scrollByValue("300");
		application.validateElementPresent("filtersLocator_xpath");
		application.click("priceFilter_xpath");
		application.selectFilter("filter_type", "filter_value", "Filters" ,"filtersBlocks_xpath");
		application.wait(10);
		application.scrollByValue("300");
		application.wait(2);
		application.validatePriceRange("listingPageAllPrices_xpath", "filter_value" , "Filters");
		application.wait(5);
	}
}
