package pom.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.common.EnvContext.BROWSER_TYPE;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static pom.common.EnvContext.wait_timeout;

public class BrowserProvider {

    private static final BrowserProvider instance = new BrowserProvider();

    private BrowserProvider(){}

    public static BrowserProvider getInstance(){
        return instance;
    }

    public Browser getBrowser(){
        Browser browser = BrowserRegistry.getCurrentRunningBrowserFromRegistry();
        if(browser == null){
            browser = newBrowser(EnvContext.browser_type);
        }
        return browser;
    }

    public Browser newBrowser (BROWSER_TYPE browserType) {
        WebDriver driver;
        switch (browserType){
            case CHROME:
                driver = new ChromeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException(browserType.name()+" is not supported");
        }
        driver = configureBrowser(driver);
        FluentWait<WebDriver> wait = new WebDriverWait(driver, wait_timeout).pollingEvery(Duration.ofSeconds(1));
        Actions actions = new Actions(driver);
        Browser browser = new Browser(driver, wait, actions);
        BrowserRegistry.registerBrowserToRegistry(browser);
        return browser;
    }

    private WebDriver configureBrowser(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(wait_timeout, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
