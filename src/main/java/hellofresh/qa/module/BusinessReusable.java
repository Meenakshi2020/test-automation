package hellofresh.qa.module;

import hellofresh.qa.pageobject.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hellofresh.qa.tools.Configuration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


/**
 * Class to have the methods defined with actual business logic for application under test
 */
public class BusinessReusable extends Configuration
{
	private Configuration conf = new Configuration();
	private WebDriver driver = conf.getWebDriver();
	private Long timeout = Long.parseLong(conf.config.getString("conf.timeout"));
	private WebDriverWait wait = new WebDriverWait(driver, timeout, 50);
	private Logger logger = LoggerFactory.getLogger(BusinessReusable.class);
	private PageObject po = new PageObject(driver, wait);
	private String firstname = "";
	private String lastname = "";
	private String password = "";

	/**
	 * Method to navigate to home page
	 */
	public void navigateToPage()
	{
		try {
			conf.navigateURL("homePageURL");
		}
		catch(Exception e)
		{
			logger.info("An exception occurred \n" + e.getMessage());
			
		}
	}

	/**
	 * Method to click on Login button
	 */
	public void clickLogin()
	{
		try {
			po.clickLogin();
		}
		catch(Exception e)
		{
			logger.info("An exception occurred \n" + e.getMessage());
			
		}
	}

	/**
	 * Method to insert existing Customer credentials to login
	 * @param email
	 */
	public void loginExistingCustomer(String email)
	{
		try {
			po.enterEmailToLogin(email);
			po.enterPassword(password);
			po.submitLogin();
		}
		catch(Exception e)
		{
			logger.info("An exception occurred \n" + e.getMessage());
			
		}
	}

	/**
	 * Method to create a new Customer
	 * @param email
	 */
	public void createNewCustomer(String email)
	{
		try {
			po.enterEmailToSignUp(email);
			logger.info("Customer email is [" + email + "]");
			po.submitCreate();
		}
		catch(Exception e)
		{
			logger.info("An exception occurred \n" + e.getMessage());
		}
	}

	/**
	 * Method to fill in the Customer data for a new Customer
	 * @param fname firstname
	 * @param lname lastname
	 * @param passwd password
	 */
	public void fillCustomerData(String fname, String lname, String passwd)
	{
		try {
			firstname = fname;
			lastname = lname;
			password = passwd;
			po.selectTitle();
			po.enterFirstName(fname);
			po.enterLastName(lname);
			po.enterPassword(passwd);
			po.selectDOB(conf.readProperty("testdata.dayOfBirth"), conf.readProperty("testdata.monthOfBirth"), conf.readProperty("testdata.yearOfBirth"));
			po.enterCompany(conf.readProperty("testdata.company"));
			po.enterAddress1(conf.readProperty("testdata.address1"));
			po.enterAddress2(conf.readProperty("testdata.address2"));
			po.enterCity(conf.readProperty("testdata.city"));
			po.selectState(conf.readProperty("testdata.state"));
			po.enterPostcode(conf.readProperty("testdata.postcode"));
			po.enterOtherText(conf.readProperty("testdata.other"));
			po.enterPhone(conf.readProperty("testdata.homephone"));
			po.enterMobileNumber(conf.readProperty("testdata.mobilephone"));
			po.enterAlias(conf.readProperty("testdata.alias"));
			po.submitAccount();
		}
		catch(Exception e)
		{
			logger.info("An exception occurred \n" + e.getMessage());
		}
	}

	/**
	 * Method to validate the successful login
	 */
	public void validateLogin()
	{
		try {
			assertEquals(po.getHeading(), "MY ACCOUNT");
			assertEquals(po.getAccount(), firstname + " " + lastname);
			assertTrue(po.getAccountInfo().contains("Welcome to your account."));
			po.checkLogout();
			assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
		}
		catch(Exception e)
		{
			logger.info("An exception occurred \n" + e.getMessage());
		}
	}

	/**
	 * Method to perform steps required for order checkout workflow
	 */
	public void checkout()
	{
		try
		{
			po.clickWomenCategory();
			po.clickItemLink();
			po.clickItemLink();
			po.clickSubmit();
			po.clickProceedToCheckout();
			po.clickCartNavigationCheckout();
			po.clickProcessAddress();
			po.clickUniformCgv();
			po.clickProcessCarrier();
			po.clickBankWire();
			po.clickCardNavigationButton();
		}
		catch(Exception e)
		{
			logger.info("An exception occurred \n" + e.getMessage());
		}
	}

	/**
	 * Method to validate if the order checkout is done successfully
	 */
	public void checkoutValidation() {
		try {
			assertEquals(po.getHeading(), "ORDER CONFIRMATION");
			po.checkStepDone();
			po.checkStepEnd();
			assertTrue(po.checkOrderComplete().contains("Your order on My Store is complete."));
			assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
		}
		catch(Exception e)
		{
			logger.info("An exception occurred \n" + e.getMessage());
		}
	}

	/**
	 * Method to click on Signout link
	 */
	public void userSignOut() {
		po.clickSignout();
	}
}