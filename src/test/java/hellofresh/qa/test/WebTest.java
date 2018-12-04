package hellofresh.qa.test;

import hellofresh.qa.tools.BaseTest;
import hellofresh.qa.tools.TestListener;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * TestNG test class
 */
@Listeners({TestListener.class})
@Epic("Regression Tests")
@Feature("UI Login & Checkout Tests")
public class WebTest extends BaseTest
{
    String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";

    @Test
    @Description("Test to verify a new Customer account creation")
    @Title("Sign-up Test")
    public void signUpTest(){
        reusable.clickLogin();
        reusable.createNewCustomer(email);
        reusable.fillCustomerData(
                conf.readProperty("testdata.firstname"),
                conf.readProperty("testdata.lastname"),
                conf.readProperty("testdata.password"));
        reusable.validateLogin();
        reusable.userSignOut();
    }

    @Test(dependsOnMethods = "signUpTest")
    @Description("Test to verify the login functionality for existing Customer")
    @Title("Login Test")
    @Severity(SeverityLevel.BLOCKER)
    public void loginTest(){
        reusable.clickLogin();
        reusable.loginExistingCustomer(email);
        reusable.validateLogin();
        reusable.userSignOut();

    }

    @Test(dependsOnMethods = "loginTest")
    @Description("Test to verify the checkout workflow for existing Customer")
    @Title("Checkout Workflow Test")
    public void checkoutTest()
    {
        reusable.clickLogin();
        reusable.loginExistingCustomer(email);
        reusable.validateLogin();
        reusable.checkout();
        reusable.checkoutValidation();
        reusable.userSignOut();
    }
}