package extensions.org.openqa.selenium.WebElement;

import helper.UITestRegistry;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@Extension
public class WebElementExt {

    public static void selectByVisibleText(@This WebElement thiz, String value){
        Select select = new Select(thiz);
        select.selectByVisibleText(value);
    }

    public static String getSelectedValue(@This WebElement thiz){
        if(thiz.getTagName().equalsIgnoreCase("select")) return new Select(thiz).getFirstSelectedOption().getText();
        else return thiz.getText();
    }

    public static void clickUsingJS(@This WebElement thiz) {
        WebDriver driver = UITestRegistry.getInstance().getCurrentRunningUITest().getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", thiz);
    }
}
