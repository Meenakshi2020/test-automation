package hellofresh.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page object class to define the locators/XPATH for the web elements
 *
 */
public class PageObject
{
	private WebDriver driver;
	private WebDriverWait wait;
    private Logger log = LoggerFactory.getLogger(PageObject.class);

	// PageFactory is used to implement the Page Object Model Framework
	public PageObject(WebDriver driver, WebDriverWait wait)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = wait;
    }

    // Page Web elements
    @FindBy(className = "login")
	private WebElement login; // Login

    @FindBy(id = "email_create")
    private WebElement createEmail;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "SubmitCreate")
    private WebElement submitCreate;

    @FindBy(id = "id_gender2")
    private WebElement gender;

    @FindBy(id = "customer_firstname")
    private WebElement firstname;

    @FindBy(id = "customer_lastname")
    private WebElement lastname;

    @FindBy(id = "passwd")
    private WebElement password;

    @FindBy(id = "days")
    private WebElement dayOfBirth;

    @FindBy(id = "months")
    private WebElement monthOfBirth;

    @FindBy(id = "years")
    private WebElement yearOfBirth;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "address2")
    private WebElement address2;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "id_state")
    private WebElement id_state;

    @FindBy(id = "postcode")
    private WebElement postcode;

    @FindBy(id = "other")
    private WebElement other;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "phone_mobile")
    private WebElement phone_mobile;

    @FindBy(id = "alias")
    private WebElement alias;

    @FindBy(id = "submitAccount")
    private WebElement submitAccount;

    @FindBy(className = "account")
    private WebElement account;

    @FindBy(className = "info-account")
    private WebElement infoAccount;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLogin;

    @FindBy(className = "logout")
    private WebElement logout;

    @FindBy(css = "h1")
    private WebElement heading;

    @FindBy(linkText = "Women")
    private WebElement categoryWomen;

    @FindBy(xpath = "//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
    private WebElement itemLink;

    @FindBy(xpath = "//li[@class='step_done step_done_last four']")
    private WebElement stepDone;

    @FindBy(xpath = "//li[@id='step_end' and @class='step_current last']")
    private WebElement stepEnd;

    @FindBy(xpath = "//*[@class='cheque-indent']/strong")
    private WebElement orderComplete;

    @FindBy(name = "Submit")
    private WebElement submitChoice;

    @FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
    private WebElement proceedToCheckout;

    @FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
    private WebElement cartNavigationCheckout;

    @FindBy(name = "processAddress")
    private WebElement processAddress;

    @FindBy(id = "uniform-cgv")
    private WebElement uniformCgv;

    @FindBy(name = "processCarrier")
    private WebElement processCarrier;

    @FindBy(className = "bankwire")
    private WebElement bankwire;

    @FindBy(xpath = "//*[@id='cart_navigation']/button")
    private WebElement cartNavigationButton;


	/**
	 * Method to enter email address
	 * @param email
	 */
	public void enterEmailToSignUp(String email)
	{
        log.info("Enter email [" + email + "]");
        createEmail.sendKeys(email);
	}

    /**
     * Method to enter email address
     * @param customerEmail
     */
    public void enterEmailToLogin(String customerEmail)
    {
        log.info("Enter email to login [" + customerEmail + "]");
        email.sendKeys(customerEmail);
    }

    /**
     * Method to enter firstname
     * @param fname
     */
    public void enterFirstName(String fname)
    {
        log.info("Customer firstname [" + fname + "]");
        firstname.sendKeys(fname);
    }

    /**
     * Method to enter lastname
     * @param lname
     */
    public void enterLastName(String lname)
    {
        log.info("Customer lastname [" + lname + "]");
        lastname.sendKeys(lname);
    }

    /**
     * Method to enter password
     * @param CustomerPassword
     */
    public void enterPassword(String CustomerPassword)
    {
        password.sendKeys(CustomerPassword);
    }

    /**
     * Method to select date of birth
     * @param day
     * @param month
     * @param year
     */
    public void selectDOB(String day, String month, String year)
    {
        log.info("Customer DOB [" + day + "/" + month + "/" + year + "]");
        Select chooseDay = new Select(dayOfBirth);
        chooseDay.selectByValue(day);

        Select chooseMonth = new Select(monthOfBirth);
        chooseMonth.selectByValue(month);

        Select chooseYear = new Select(yearOfBirth);
        chooseYear.selectByValue(year);
    }

    /**
     * Method to enter company
     * @param customerCompany String
     */
    public void enterCompany(String customerCompany)
    {
        log.info("Customer company [" + customerCompany + "]");
        company.sendKeys(customerCompany);
    }

    /**
     * Method to enter address line 1
     * @param address String
     */
    public void enterAddress1(String address)
    {
        log.info("Customer Address 1 [" + address + "]");
        address1.sendKeys(address);
    }

    /**
     * Method to enter address line 2
     * @param address String
     */
    public void enterAddress2(String address)
    {
        log.info("Customer Address 2 [" + address + "]");
        address2.sendKeys(address);
    }

    /**
     * Method to enter city
     * @param customerCity String
     */
    public void enterCity(String customerCity)
    {
        log.info("Customer City [" + customerCity + "]");
        city.sendKeys(customerCity);
    }

    /**
     * Method to select State
     * @param customerState String
     */
    public void selectState(String customerState)
    {
        log.info("Customer State [" + customerState + "]");
        Select chooseState = new Select(id_state);
        chooseState.selectByVisibleText(customerState);
    }

    /**
     * Method to enter Postcode
     * @param customerPostcode String
     */
    public void enterPostcode(String customerPostcode)
    {
        log.info("Customer Postcode [" + "]");
        postcode.sendKeys(customerPostcode);
    }

    /**
     * Method to enter other text
     * @param otherText String
     */
    public void enterOtherText(String otherText)
    {
        log.info("Other [" + otherText + "]");
        other.sendKeys(otherText);
    }

    /**
     * Method to enter phone number
     * @param customerPhone String
     */
    public void enterPhone(String customerPhone)
    {
        log.info("Customer Phone [" + customerPhone + "]");
        phone.sendKeys(customerPhone);
    }

    /**
     * Method to enter mobile number in input textbox
     * @param customerMobile String
     */
    public void enterMobileNumber(String customerMobile)
    {
        log.info("Customer Mobile [" + customerMobile + "]");
        phone_mobile.sendKeys(customerMobile);
    }

    /**
     * Method to enter text in the alias textbox
     * @param customerAlias String
     */
    public void enterAlias(String customerAlias)
    {
        log.info("Customer alias [" + customerAlias + "]");
        alias.sendKeys(customerAlias);
    }

    /**
     * Method to click on Submit Account
     */
    public void submitAccount()
    {
        log.info("Submit Account");
        submitAccount.click();
    }

    /**
     * Method to click on Submit button to create the account
     */
    public void submitCreate()
    {
        log.info("Submit to create account");
        submitCreate.click();
    }

    /**
     * Method to click on Login button
     */
    public void submitLogin()
    {
        log.info("Click login");
        submitLogin.click();
    }

    /**
     * Method to get the Account text
     * @return Account text
     */
    public String getAccount()
    {
        return account.getText();
    }

    /**
     * Method to retrieve the account info text
     * @return Account info String
     */
    public String getAccountInfo()
    {
        log.info("Account info [" + infoAccount.getText() + "]");
        return infoAccount.getText();
    }

    /**
     * Method to verify if Signout button is displayed
     * @return true/false
     */
    public boolean checkLogout()
    {
        log.info("Verify if Signout is visible");
        return logout.isDisplayed();
    }

    /**
     * Method to check if the Step done item is displayed
     * @return true/false
     */
    public boolean checkStepDone()
    {
        return stepDone.isDisplayed();
    }

    /**
     * Method to check if the Step end item is displayed
     * @return true/false
     */
    public boolean checkStepEnd()
    {
        return stepEnd.isDisplayed();
    }

    /**
     *Method to retrieve the text of the Order
     * @return Order completion text
     */
    public String checkOrderComplete()
    {
        log.info("Order status is [" + orderComplete.getText() + "]");
        return orderComplete.getText();
    }

    /**
     * Method to click on the Signout button
     */
    public void clickSignout()
    {
        log.info("Click Signout...");
        logout.click();
    }

    /**
     * Method to click on the Women category
     */
    public void clickWomenCategory()
    {
        log.info("Choose category");
        wait.until(ExpectedConditions.visibilityOf(categoryWomen)).click();
    }

    /**
     * Method to click on the selected item link
     */
    public void clickItemLink()
    {
        log.info("Click on item");
        itemLink.click();
    }

    /**
     * Method to click on Submit button
     */
    public void clickSubmit()
    {
        log.info("Click Submit");
        wait.until(ExpectedConditions.visibilityOf(submitChoice)).click();
    }

    /**
     * Method to click on Proceed to checkout button
     */
    public void clickProceedToCheckout()
    {
        log.info("Click Proceed to Checkout");
        proceedToCheckout.click();
    }

    /**
     * Method to click on the Cart Navigation Checkout button
     */
    public void clickCartNavigationCheckout()
    {
        log.info("Click Checkout");
        wait.until(ExpectedConditions.visibilityOf(cartNavigationCheckout)).click();
    }

    /**
     * Method to click on the Process address link
     */
    public void clickProcessAddress()
    {
        log.info("Click Process Address");
        wait.until(ExpectedConditions.visibilityOf(processAddress)).click();
    }

    /**
     * Method to click on UniformCGV
     */
    public void clickUniformCgv()
    {
        wait.until(ExpectedConditions.visibilityOf(uniformCgv)).click();
    }

    /**
     * Method to click on the Process Carrier button
     */
    public void clickProcessCarrier()
    {
        processCarrier.click();
    }

    /**
     * Method to click on the Bankwire button
     */
    public void clickBankWire()
    {
        wait.until(ExpectedConditions.visibilityOf(bankwire)).click();
    }

    /**
     * Method to click on the CardNavigation button
     */
    public void clickCardNavigationButton()
    {
        wait.until(ExpectedConditions.visibilityOf(cartNavigationButton)).click();
    }

    /**
     * Method to select the title
     */
    public void selectTitle()
    {
        wait.until(ExpectedConditions.visibilityOf(gender)).click();
    }

    /**
     * Method to retrieve the heading text
     * @return Heading String
     */
    public String getHeading()
    {
        return wait.until(ExpectedConditions.visibilityOf(heading)).getText();
    }

    /**
     * Method to click Login
     */
    public void clickLogin()
    {
        log.info("Click login");
        wait.until(ExpectedConditions.visibilityOf(login)).click();
    }
}