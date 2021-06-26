package tests.common;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pom.common.Browser;
import pom.common.BrowserProvider;
import pom.common.Env;

import java.io.ByteArrayInputStream;

public class UITest {
    protected SoftAssert soft = new SoftAssert();

    static {
        Env.load();
    }

    @Parameters("run.browser")
    @BeforeClass
    public void startTests(@Optional("") String browser) {
        //overrides if explicitly passed from test-suite.
        if (browser.isBlank() == false)
            Env.Run.browser = Browser.Type.valueOf(browser);
        BrowserProvider.getInstance().newBrowser(Env.Run.browser);
    }

    @AfterMethod
    public void attachFailureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            WebDriver driver = BrowserProvider.getInstance().getBrowser().getDriver();
            Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @AfterClass
    public void endTests() {
        Browser browser = BrowserProvider.getInstance().getBrowser();
        browser.quit();
    }

}
