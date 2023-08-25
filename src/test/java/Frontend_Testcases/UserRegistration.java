package Frontend_Testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestBase.JohnsonBase;

public class UserRegistration extends JohnsonBase{
		@Test(priority = 1)
		
	public void userRegistrationErrorMessageValidation() {
			application.waitForPageToLoad(driver , 200);
			application.validateText("homePageSignin_xpath", "Sign In");
			application.click("homePageSignin_xpath");
			application.wait(5);
			application.scrollTo("signupButton_xpath");
			application.validateText("signupButton_xpath", "SIGN UP");
			application.click("signupButton_xpath");
			application.wait(5);
			application.scrollByValue("300");
			application.wait(2);
			application.click("createAccountButton_xpath");
			application.wait(3);
			application.validateFieldRequired("userRegFirstNameFieldRequired_xpath");
			application.validateFieldRequired("userReglastNameFieldRequired_xpath");
			application.validateFieldRequired("userRegemailFieldRequired_xpath");
			application.validateFieldRequired("userRegpasswordFieldRequired_xpath");
			application.wait(2);
			application.type("firstname_name", readExcelData("invalid_first_name", "Create_Account"));
			application.click("lastname_name");
			application.type("lastname_name", readExcelData("invalid_last_name", "Create_Account"));
			application.click("email_name");
//			application.validateErrortext("lastname_name" , "lastNameFieldRequired_xpath");
			application.type("email_name", readExcelData("invalid_mail_ID", "Create_Account"));
			application.click("createpassword_name");
//			application.validateEmailIputField("email_name", "emailFieldRequired_xpath");
			application.wait(5);
			application.type("createpassword_name", readExcelData("invalid_password", "Create_Account"));
			application.wait(2);
			application.click("createAccountButton_xpath");
			application.wait(2);
			application.validateErrortext("firstname_name" , "userRegFirstNameFieldRequired_xpath");
			application.validateErrortext("lastname_name" , "userReglastNameFieldRequired_xpath");
			application.validateEmailIputField("email_name", "userRegemailFieldRequired_xpath");
			application.validatePassword("createpassword_name", "userRegpasswordFieldRequired_xpath");
			application.wait(2);
			application.clear("createpassword_name");
			application.type("createpassword_name", readExcelData("invalid_password2", "Create_Account"));
			application.wait(2);
			application.click("createAccountButton_xpath");
			application.validatePassword("createpassword_name", "userRegpasswordFieldRequired_xpath");
			application.wait(5);
		
	}
		@SuppressWarnings("unlikely-arg-type")
		@Test(priority = 2)
		public void userRegistration() {
			application.waitForPageToLoad(driver , 200);
			application.refreshPage();
			application.type("firstname_name", readExcelData("valid_first_name", "Create_Account"));
			application.click("lastname_name");
			application.wait(2);
			application.type("lastname_name", readExcelData("valid_last_name", "Create_Account"));
			application.scrollByValue("300");
			application.click("email_name");
			application.wait(2);
			application.type("email_name", readExcelData("valid_mail_ID", "Create_Account"));
			application.click("createpassword_name");
			application.wait(2);
			application.type("createpassword_name", readExcelData("valid_password", "Create_Account"));
			application.wait(2);
			application.scrollByValue("300");
			String newsletter = application.readExcelDataAsString("newsletter", "Create_Account");
			
			if(newsletter.equals("yes")) {
				application.click("userRegNewsletterCheckbox_xpath");
				test.log(Status.INFO, "NewsLetter subscribed");
			}else if(newsletter.equals("no")) {
				test.log(Status.INFO, "NewsLetter not subscribed");
			}
			application.click("createAccountButton_xpath");
			application.wait(10);
			application.waitForPageToLoad(driver, 200);
			try {
				application.validateAccountDuplicate("userRegAccountDuplicateError_xpath");
			}catch(Exception e) {
				application.wait(15);
				application.scrollTo("homePageSignin_xpath");
				application.getText("validLoginText_xpath");
				application.validateLogin("validLoginText_xpath");
				application.wait(5);
			}
		
		}
		
}
