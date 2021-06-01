package extensions.org.openqa.selenium.support.ui.FluentWait;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

@Extension
public class FluentWaitExt {

    public static void waitForElementTextToMatch(@This FluentWait<WebDriver> thiz, By locator, String regex){
        thiz.until(ExpectedConditions.textMatches(locator, Pattern.compile(regex)));
    }
}
