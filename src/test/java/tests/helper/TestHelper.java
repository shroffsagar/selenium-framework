package tests.helper;

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

public class TestHelper extends TestListenerAdapter
{

    protected WebDriver driver;

    @BeforeClass
    protected void startTests() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.setWait(new WebDriverWait(driver, 10).pollingEvery(Duration.ofSeconds(2)));
    }

    @AfterClass
    protected void endTests() {
        String debug = System.getenv("debug");
        if (debug == null || debug.equalsIgnoreCase("true") == false)
            if (driver != null) driver.quit();
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        TestHelper testInstance = ((TestHelper)tr.getInstance());
        testInstance.endTests();
        testInstance.startTests();
    }
}
