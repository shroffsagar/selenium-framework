package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import helper.UITest;

public class WikiTest extends UITest {

    @Test
    public void welcomePage() {
        driver.get("https://www.wikipedia.org/");
        driver.findElement(By.xpath("//*[@id='js-link-box-en']")).click();
        wait.waitForElementTextToMatch(By.id("mp-welcome"), "Welcome to Wiki.*");
        String actualWelcomeMsg = driver.findElement(By.id("mp-welcome")).getText();
        Assert.assertEquals(actualWelcomeMsg, "Welcome to Wikipedia,");
        driver.findElement(By.cssSelector("#footer-places-statslink a")).clickUsingJS();
        driver.close();
    }
}
