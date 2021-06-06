package pom.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static pom.common.EnvContext.browser_type;
import static pom.common.EnvContext.wait_timeout;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.common.EnvContext.BROWSER_TYPE;

public class BrowserProvider {

    private static final BrowserProvider instance = new BrowserProvider();

    private BrowserProvider(){}

    public static BrowserProvider getInstance(){
        return instance;
    }

    public Browser getBrowser(){
        Browser browser = BrowserRegistry.getCurrentRunningBrowser();
        if(browser == null){
            browser = newBrowser(browser_type);
            BrowserRegistry.registerCurrentRunningBrowser(browser);
        }
        return browser;
    }

    private Browser newBrowser (BROWSER_TYPE browserType) {
        WebDriver driver;
        switch (browserType){
            case CHROME:
                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalArgumentException(browserType.name()+" is not supported");
        }
        driver = configureBrowser(driver);
        FluentWait<WebDriver> wait = new WebDriverWait(driver, wait_timeout).pollingEvery(Duration.ofSeconds(1));
        Actions actions = new Actions(driver);
        return new Browser(driver, wait, actions);
    }

    private WebDriver configureBrowser(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(wait_timeout, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
