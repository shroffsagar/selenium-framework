package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import tests.helper.TestHelper;

public class W3SchoolTest extends TestHelper {

    @Test
    public void w3schoolAlertPage() {
        driver.get("https://www.w3schools.com/jsref/met_win_alert.asp");
        driver.findElement(By.cssSelector(".w3-example a[href*='tryit']")).click();
        driver.switchToTab("Tryit");
        driver.switchToIFrame(By.cssSelector("#iframecontainer iframe"));
        driver.findElement(By.xpath("//button[normalize-space(.)='Try it']")).click();
        driver.acceptAlert("Hello! I am an alert box!");
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tryhome")).click();
        driver.switchToTab("W3Schools Online Web");
        driver.findElement(By.xpath("//*[@id='main']//a[normalize-space(.)='Learn HTML']")).click();
        driver.close();
        driver.switchToTab("Tryit");
        driver.close();
        driver.switchToTab("Window");
    }
}
