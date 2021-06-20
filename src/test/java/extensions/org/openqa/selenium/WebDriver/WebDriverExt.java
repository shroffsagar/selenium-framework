package extensions.org.openqa.selenium.WebDriver;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import pom.common.Browser;
import pom.common.BrowserProvider;

import java.util.Set;

@Extension
public class WebDriverExt {

    public static void switchToTab(@This WebDriver thiz, String tabName) {
        Set<String> windowHandleNames = thiz.getWindowHandles();
        boolean foundDesiredTab = false;
        for (String windowHandleName : windowHandleNames) {
            String title = thiz.switchTo().window(windowHandleName).getTitle();
            if (title.toLowerCase().contains(tabName.toLowerCase())) {
                foundDesiredTab = true;
                break;
            }
        }
        if (foundDesiredTab == false) {
            throw new NoSuchWindowException("Fail to switch to tab: " + tabName);
        }
    }

    public static void switchToIFrame(@This WebDriver thiz, By locator) {
        thiz.switchTo().frame(thiz.findElement(locator));
    }

    public static void switchToIFrame(@This WebDriver thiz, WebElement iframe) {
        thiz.switchTo().frame(iframe);
    }

    public static void acceptAlert(@This WebDriver thiz, String alertText) {
        Browser browser = BrowserProvider.getInstance().getBrowser();
        FluentWait<WebDriver> wait = browser.getWait();
        Alert w3schoolAlert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(w3schoolAlert.getText().contains(alertText));
        w3schoolAlert.accept();
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }

    public static void executeScript(@This WebDriver thiz, String script, Object ... args){
        ((JavascriptExecutor)thiz).executeScript(script, args);
    }

}
