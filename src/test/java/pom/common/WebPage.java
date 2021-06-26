package pom.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class WebPage {

    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;
    protected Actions actions;
    protected String url, title;

    public WebPage(String relativeUrl, String title){
        this();
        this.url = getUrl(relativeUrl);
        this.title = title;
        waitForPageToOpen();
    }

    public WebPage(){
        Browser browser = BrowserProvider.getInstance().getBrowser();
        driver = browser.getDriver();
        wait = browser.getWait();
        actions = browser.getActions();
        PageFactory.initElements(driver, this);
    }

    public WebPage waitForPageToOpen(){
        wait.pageToLoad();
        wait.until(ExpectedConditions.titleContains(title));
        return this;
    }

    public void close() {
        driver.close();
    }

    public void switchToTab() {
        driver.switchToTab(this.title);
    }

    public static String getUrl(String relativeUrl){
        return (relativeUrl.isBlank()) ? Env.App.url: Env.App.url + "/"+ relativeUrl;
    }
}
