package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.helper.TestHelper;

public class WikiTest extends TestHelper {

    @Test
    public void welcomePage() {
        driver.get("https://www.wikipedia.org/");
        WebElement lnkEnglish = driver.findElement(By.xpath("//*[@id='js-link-box-en']"));
        lnkEnglish.click();
        WebElement welcomeMsg = driver.findElement(By.id("mp-welcome"));
        driver.getWait().waitForElementTextToMatch(By.id("mp-welcome"), "Welcome to Wiki.*");
        Assert.assertEquals(welcomeMsg.getText(), "Welcome to Wikipedia,");
        driver.clickUsingJS(By.cssSelector("#footer-places-statslink a"));
        driver.close();
    }

    @Test
    public void test2(){
        driver.get("https://www.wikipedia.org/");
        WebElement lnkEnglish = driver.findElement(By.xpath("//*[@id='js-link-box-en']"));
        lnkEnglish.click();
    }
}
