package extensions.org.openqa.selenium.WebDriver;

import helper.UITestRegistry;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

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
            Assert.fail("Fail to switch to tab: " + tabName);
        }
    }

    public static void switchToIFrame(@This WebDriver thiz, By locator) {
        thiz.switchTo().frame(thiz.findElement(locator));
    }

    public static void acceptAlert(@This WebDriver thiz, String alertText) {
        FluentWait<WebDriver> wait = UITestRegistry.getInstance().getCurrentRunningUITest().getWait();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert w3schoolAlert = thiz.switchTo().alert();
        Assert.assertTrue(w3schoolAlert.getText().contains(alertText));
        w3schoolAlert.accept();
    }
}
