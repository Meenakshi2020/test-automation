package hellofresh.qa.tools;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {

    private Logger log = LoggerFactory.getLogger(TestListener.class);
    private static String getTestMethodName(ITestResult iTestResult)
    {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value="Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver)
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value="{0}", type = "text/plain")
    public static String saveTextLog (String message)
    {
        return message;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult)
    {
        log.info(getTestMethodName(iTestResult) + " FAILED");

        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((Configuration)testClass).getWebDriver();

        // allure ScreenshotRobot and SaveTestLog
        if(driver instanceof WebDriver)
        {
            saveScreenshotPNG(driver);
            log.info("Screenshot captured for test case: " + getTestMethodName(iTestResult));
        }

        // Save a log on allure
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");

    }

    @Override
    public void onTestStart(ITestResult iTestResult)
    {
        log.info("Test started: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult)
    {
        log.info("Test passed: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult)
    {
        log.info("Test skipped: " + getTestMethodName(iTestResult));
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult)
    {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    @Override
    public void onStart(ITestContext iTestContext)
    {
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext)
    {
        log.info("Test finished.");
    }
}
