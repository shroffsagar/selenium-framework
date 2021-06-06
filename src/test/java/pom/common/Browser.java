package pom.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

public class Browser
{
    private WebDriver driver;
    private FluentWait<WebDriver> wait;
    private Actions actions;

    public Browser(WebDriver driver, FluentWait<WebDriver> wait, Actions actions){
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public FluentWait<WebDriver> getWait() {
        return wait;
    }

    public Actions getActions() {
        return actions;
    }

    public void quit(){
        driver.quit();;
        BrowserRegistry.removeBrowserFromRegistry();
    }
}
