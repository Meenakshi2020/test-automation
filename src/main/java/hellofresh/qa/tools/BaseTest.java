package hellofresh.qa.tools;

import hellofresh.qa.module.BusinessReusable;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Date;

/**
 * Base class for all the testcase classes
 */
public class BaseTest {
    protected Configuration conf = new Configuration();
    protected BusinessReusable reusable;
    protected String timestamp = String.valueOf(new Date().getTime());
    WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp()
    {
        conf.initializeDriver();
        this.driver = conf.getWebDriver();
        reusable = new BusinessReusable();
        reusable.navigateToPage();
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        conf.quitBrowser();
    }

}
