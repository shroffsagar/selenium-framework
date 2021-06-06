package tests.common;

import org.openqa.selenium.WebDriver;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import pom.common.Browser;
import pom.common.BrowserProvider;
import pom.common.BrowserRegistry;

public class UITest extends TestListenerAdapter
{
    @AfterClass
    protected void endTests() {
        String debug = System.getenv("debug");
        if (debug == null || !debug.equalsIgnoreCase("true")) {
            Browser browser = BrowserProvider.getInstance().getBrowser();
            browser.quit();
        }
    }
}
