package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class UITest extends TestListenerAdapter
{
    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;

    @BeforeClass
    protected void startTests() {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10).pollingEvery(Duration.ofSeconds(2));
        UITestRegistry.getInstance().registerUITest(this);
    }

    @AfterClass
    protected void endTests() {
        String debug = System.getenv("debug");
        if (debug == null || !debug.equalsIgnoreCase("true"))
            if (driver != null) {
                driver.quit();
                UITestRegistry.getInstance().removeCurrentRunningTest();
            }
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        UITest testInstance = ((UITest)tr.getInstance());
        testInstance.endTests();
        testInstance.startTests();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public FluentWait<WebDriver> getWait(){
        return wait;
    }
}
