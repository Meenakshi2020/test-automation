package hellofresh.qa.tools;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class Configuration {

    public Config config = ConfigFactory.load("default.conf");
    private Logger logger = LoggerFactory.getLogger(Configuration.class);

    private static WebDriver driver;

    /**
     * Method to initialize the Web driver and launch the web browser
     * WebDriverManager library is used to automate the Selenium WebDriver binaries management.
     */
    private void setupDriver()
    {
        String browserName = config.getString("conf.browserName");
        switch (browserName){
            case "InternetExplorer":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
    }

    /**
     * One liner method to maximize the browser window
     */
    private void maximizeWindow()
    {
        driver.manage().window().maximize();
    }

    /**
     * One liner method to provide the implicit wait
     */
    private void implicitWait(int time)
    {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    /**
     * One liner method to delete the cookies
     */
    private void deleteAllCookies()
    {
        driver.manage().deleteAllCookies();
    }

    /**
     * Method to setup the test environment before the Suite tests execution
     */
    public void initializeDriver()
    {
        logger.info("Setting up the environment...");
        setupDriver();
        maximizeWindow();
        implicitWait(30);
        deleteAllCookies();
    }

    /**
     * Method to quit the browser after the test Suite execution
     */
    public void quitBrowser() {
        logger.info("Closing the browser...");
        driver.quit();
    }

    /**
     * Method to share the driver instance
     * @return WebDriver instance
     */
    public WebDriver getWebDriver()
    {
        return driver;
    }

    /**
     * Method to navigate to any specified URL
     * @param urlPath is the propertyName defined in default.conf for the specified URL
     */
    public void navigateURL(String urlPath)
    {
        String url = config.getString("conf." + urlPath);

        if(url != null)
        {
            driver.get(url);
        }
        else
        {
            logger.info("Failure: URL not found");
            Assert.fail("Failure: URL not found");
        }
    }

    /**
     * Read property value from configuration file default.conf
     * @param property String
     * @return property value
     */
    public String readProperty(String property)
    {
        return config.getString(property);
    }
}
