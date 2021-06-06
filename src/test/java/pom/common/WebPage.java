package pom.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static pom.common.EnvContext.baseUrl;

public class WebPage {

    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;
    protected Actions actions;
    protected String url, title;

    public WebPage(String relativeUrl, String title){
        this();
        this.url = (relativeUrl.isBlank()) ? baseUrl: baseUrl + "/"+ relativeUrl;
        this.title = title;
    }

    public WebPage(){
        Browser browser = BrowserProvider.getInstance().getBrowser();
        driver = browser.getDriver();
        wait = browser.getWait();
        actions = browser.getActions();
        PageFactory.initElements(driver, this);
    }

    public WebPage open() {
        driver.get(url);
        waitForPageToOpen();
        return this;
    }

    public WebPage waitForPageToOpen(){
        wait.until(ExpectedConditions.titleContains(title));
        return this;
    }

    public void close() {
        driver.close();
    }

    public void switchToTab() {
        driver.switchToTab(this.title);
    }

}
