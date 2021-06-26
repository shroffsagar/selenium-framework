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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BrowserProvider {

    private static final BrowserProvider instance = new BrowserProvider();

    private BrowserProvider(){}

    public static BrowserProvider getInstance(){
        return instance;
    }

    public Browser getBrowser(){
        Browser browser = BrowserRegistry.getCurrentRunningBrowserFromRegistry();
        if(browser == null){
            browser = newBrowser(Env.Run.browser);
        }
        return browser;
    }

    public Browser newBrowser (Browser.Type browserType) {
        WebDriver driver = newDriver(browserType);
        FluentWait<WebDriver> wait = new WebDriverWait(driver, Env.Run.Wait.timeout).pollingEvery(Duration.ofSeconds(1));
        Actions actions = new Actions(driver);
        Browser browser = new Browser(driver, wait, actions);
        BrowserRegistry.registerBrowserToRegistry(browser);
        return browser;
    }

    private WebDriver newDriver(Browser.Type browserType) {
        WebDriver driver;
        switch (browserType){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if(Env.Run.Options.headless) chromeOptions.addArguments("headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case SAFARI:
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setAutomaticInspection(Env.Run.Options.inspect);
                driver = new SafariDriver(safariOptions);
                break;
            default:
                throw new IllegalArgumentException(browserType.name()+" is not supported");
        }
        driver.manage().timeouts().implicitlyWait(Env.Run.Wait.timeout, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
