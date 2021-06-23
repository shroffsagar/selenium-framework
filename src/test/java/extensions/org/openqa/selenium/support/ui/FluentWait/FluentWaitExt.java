package extensions.org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Predicate;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import pom.common.BrowserProvider;

import java.util.function.Function;
import java.util.regex.Pattern;

@Extension
public class FluentWaitExt {

    private static final Function<? super WebDriver, ?> pageIsLoaded = new Function<WebDriver, Object>() {
        @Override
        public Object apply(WebDriver driver) {
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        }
    };

    public static void elementTextToMatch(@This FluentWait<WebDriver> thiz, By locator, String regex) {
        thiz.until(ExpectedConditions.textMatches(locator, Pattern.compile(regex)));
    }

    public static void pageToLoad(@This FluentWait<WebDriver> thiz){
        WebDriver driver = BrowserProvider.getInstance().getBrowser().getDriver();
        thiz.until(pageIsLoaded);
    }
}
