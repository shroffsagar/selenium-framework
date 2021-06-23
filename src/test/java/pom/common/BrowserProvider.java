package pom.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.common.EnvContext.BROWSER_TYPE;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static pom.common.EnvContext.waitTimeout;

public class BrowserProvider {

    private static final BrowserProvider instance = new BrowserProvider();

    private BrowserProvider(){}

    public static BrowserProvider getInstance(){
        return instance;
    }

    public Browser getBrowser(){
        Browser browser = BrowserRegistry.getCurrentRunningBrowserFromRegistry();
        if(browser == null){
            browser = newBrowser(EnvContext.defaultBrowser);
        }
        return browser;
    }

    public Browser newBrowser (BROWSER_TYPE browserType) {
        WebDriver driver = newDriver(browserType);
        FluentWait<WebDriver> wait = new WebDriverWait(driver, waitTimeout).pollingEvery(Duration.ofSeconds(1));
        Actions actions = new Actions(driver);
        Browser browser = new Browser(driver, wait, actions);
        BrowserRegistry.registerBrowserToRegistry(browser);
        return browser;
    }

    private WebDriver newDriver(BROWSER_TYPE browserType) {
        WebDriver driver;
        switch (browserType){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if(EnvContext.isHeadless) chromeOptions.addArguments("headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case SAFARI:
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setAutomaticInspection(false);
                driver = new SafariDriver(safariOptions);
                break;
            default:
                throw new IllegalArgumentException(browserType.name()+" is not supported");
        }
        driver.manage().timeouts().implicitlyWait(waitTimeout, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
