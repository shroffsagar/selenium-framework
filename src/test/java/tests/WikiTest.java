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
        WebElement lnkEnglish = driver.findElement(By.xpath("//*[@id='js-link-box-en']"));
        lnkEnglish.click();
        WebElement welcomeMsg = driver.findElement(By.id("mp-welcome"));
        wait.waitForElementTextToMatch(By.id("mp-welcome"), "Welcome to Wiki.*");
        Assert.assertEquals(welcomeMsg.getText(), "Welcome to Wikipedia,");
        driver.findElement(By.cssSelector("#footer-places-statslink a")).clickUsingJS();
        driver.close();
    }
}
