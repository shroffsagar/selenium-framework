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
import java.util.function.Function;

@Extension
public class WebDriverExt {

    public static void switchToTab(@This WebDriver thiz, String tabName) {
        Function<? super WebDriver, ?> tabIsLoaded = new Function<WebDriver, Object>() {
            @Override
            public Object apply(WebDriver driver) {
                Set<String> windowHandleNames = driver.getWindowHandles();
                boolean foundDesiredTab = false;
                for (String windowHandleName : windowHandleNames) {
                    String title = driver.switchTo().window(windowHandleName).getTitle();
                    if (title.toLowerCase().contains(tabName.toLowerCase())) {
                        foundDesiredTab = true;
                        break;
                    }
                }
                return foundDesiredTab;
            }
        };
        Browser browser = BrowserProvider.getInstance().getBrowser();
        browser.getWait().until(tabIsLoaded);
        browser.getWait().pageToLoad();
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

    public static Object executeScript(@This WebDriver thiz, String script, Object ... args){
        return ((JavascriptExecutor)thiz).executeScript(script, args);
    }

}
