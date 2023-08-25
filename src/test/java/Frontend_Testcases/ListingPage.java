package Frontend_Testcases;

import org.testng.annotations.Test;
import TestBase.JohnsonBase;

public class ListingPage extends JohnsonBase{
	
	@Test
	
	public void listingPage() {
		application.wait(3);
		application.validateElementPresent("showingResultsMessage_xpath");
		application.validateElementsPresent("listingPageProducts_xpath");
		application.validateNumberOfListingPageProducts("showingResultsNumberValidation_xpath","listingPageProducts_xpath".getClass());
		application.validateElementsPresent("filtersLocator_xpath");
		application.validateElementPresent("headerLocator_xpath");
		application.validateElementPresent("footerLocator_xpath");
	}

}
