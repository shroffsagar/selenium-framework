package extensions.org.openqa.selenium.WebElement;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import pom.common.Browser;
import pom.common.BrowserProvider;
import pom.common.Env;
import utils.CommonUtils;

import java.util.concurrent.TimeUnit;

//TODO: Learning perspective implement these extension api through OOPs.
@Extension
public class WebElementExt {

    public static void selectByVisibleText(@This WebElement thiz, String value) {
        Select select = new Select(thiz);
        select.selectByVisibleText(value);
    }

    public static String getSelectedValue(@This WebElement thiz) {
        if (thiz.getTagName().equalsIgnoreCase("select")) return new Select(thiz).getFirstSelectedOption().getText();
        else return thiz.getText();
    }

    public static void clickUsingJS(@This WebElement thiz) {
        WebDriver driver = BrowserProvider.getInstance().getBrowser().getDriver();
        driver.executeScript("arguments[0].click()", thiz);
    }

    public static void dragAndDropUsingJs(@This WebElement source, WebElement destination) {
        String dragAndDropJS = CommonUtils.readFileFromClasspath("js/dragAndDrop.js");
        Browser browser = BrowserProvider.getInstance().getBrowser();
        WebDriver driver = browser.getDriver();
        source.waitForElementToBeDisplayed();
        destination.waitForElementToBeDisplayed();
        driver.executeScript(dragAndDropJS,source,destination);
    }

    public static void waitForElementToBeDisplayed(@This WebElement elm){
        Browser browser = BrowserProvider.getInstance().getBrowser();
        FluentWait<WebDriver> wait = browser.getWait();
        wait.until(ExpectedConditions.visibilityOf(elm));
    }

    public static void shouldNotExist(@This WebElement thiz){
        Browser browser = BrowserProvider.getInstance().getBrowser();
        WebDriver driver = browser.getDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elmDoesNotExists = false;
        try { thiz.click(); }
        catch (NoSuchElementException exception) {  elmDoesNotExists = true; }
        finally { driver.manage().timeouts().implicitlyWait(Env.Run.Wait.timeout, TimeUnit.SECONDS); }
        if(elmDoesNotExists != true)
            throw new InvalidElementStateException("Expected "+thiz+" to be not present but it exists");
    }

    public static void rightClick(@This WebElement thiz){
        Browser browser = BrowserProvider.getInstance().getBrowser();
        WebDriver driver = browser.getDriver();
        new Actions(driver).contextClick(thiz).build().perform();
    }

    public static void click(@This WebElement thiz){
        Browser browser = BrowserProvider.getInstance().getBrowser();
        thiz.waitForElementToBeDisplayed();
        browser.getWait().until(ExpectedConditions.elementToBeClickable(thiz));
        thiz.click();
        browser.getWait().pageToLoad();
    }

    public static void copy(@This WebElement thiz){
        Browser browser = BrowserProvider.getInstance().getBrowser();
        Actions actions = browser.getActions();
        Platform platform = ((RemoteWebDriver)browser.getDriver()).getCapabilities().getPlatform();
        Keys cmdCtrl = platform.is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        actions.keyDown(thiz, cmdCtrl)
                .sendKeys("a")
                .sendKeys("c")
                .keyUp(thiz, cmdCtrl)
                .build().perform();
    }

    public static void paste(@This WebElement thiz){
        Browser browser = BrowserProvider.getInstance().getBrowser();
        Actions actions = browser.getActions();
        Platform platform = ((RemoteWebDriver)browser.getDriver()).getCapabilities().getPlatform();
        Keys cmdCtrl = platform.is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        actions.click(thiz).keyDown(cmdCtrl)
                .sendKeys("v")
                .keyUp(cmdCtrl)
                .build().perform();
    }

}
