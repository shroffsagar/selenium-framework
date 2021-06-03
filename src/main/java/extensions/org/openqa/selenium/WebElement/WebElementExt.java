package extensions.org.openqa.selenium.WebElement;

import helper.CommonUtils;
import helper.UITest;
import helper.UITestRegistry;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

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
        WebDriver driver = UITestRegistry.getInstance().getCurrentRunningUITest().getDriver();
        driver.executeScript("arguments[0].click()", thiz);
    }

    public static void dragAndDropUsingJs(@This WebElement source, WebElement destination) {
        String dragAndDropJS = CommonUtils.readFileFromClasspath("js/dragAndDrop.js");
        WebDriver driver = UITestRegistry.getInstance().getCurrentRunningUITest().getDriver();
        source.waitForElementToBeDisplayed();
        destination.waitForElementToBeDisplayed();
        driver.executeScript(dragAndDropJS,source,destination);
    }

    public static void waitForElementToBeDisplayed(@This WebElement elm){
        FluentWait<WebDriver> wait = UITestRegistry.getInstance().getCurrentRunningUITest().getWait();
        wait.until(ExpectedConditions.visibilityOf(elm));
    }

    public static void doesNotExists(@This WebElement thiz){
        WebDriver driver = UITestRegistry.getInstance().getCurrentRunningUITest().getDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean elmDoesNotExists = false;
        try { thiz.click(); }
        catch (NoSuchElementException exception) {  elmDoesNotExists = true; }
        finally { driver.manage().timeouts().implicitlyWait(UITest.defaultImplicitWait, TimeUnit.SECONDS); }
        Assert.assertEquals(elmDoesNotExists, true);
    }
}
