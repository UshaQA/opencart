package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"regression","master"})
	public void verify_account_registration() {
		logger.info("*******starting TC001_AccountRegistrationTest*********");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on my account link");
			hp.clickRegister();
			logger.info("clicked on registration link");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info("Entering cust info");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com");// randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password = randomAlphaNumeric();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			logger.info("clicked on continue");

			String confmsg = regpage.getConfirmationMsg();

			logger.info("validating expected msg");
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
			logger.info("Test passed");

		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("*******finishing TC001_AccountRegistrationTest*********");

	}

}
