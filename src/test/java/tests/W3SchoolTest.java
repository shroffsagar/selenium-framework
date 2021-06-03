package tests;

import helper.UITest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class W3SchoolTest extends UITest {

    @FindBy(css="#drag1[draggable]") WebElement img_w3schoolDraggable;
    @FindBy(css="#div1[ondrop]") WebElement w3schoolDropTarget;
    @FindBy(css="#div1[ondrop] img") WebElement img_underDropZone;
    @FindBy(css=".trytopnav .w3-hover-text-green") WebElement btn_RunJS;

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

    @Test
    public void w3schoolSelect(){
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
        driver.switchToIFrame(By.id("iframeResult"));
        driver.findElement(By.id("cars")).selectByVisibleText("Opel");
        Assert.assertEquals(driver.findElement(By.id("cars")).getSelectedValue(), "Opel");
        driver.findElement(By.id("cars")).selectByVisibleText("Volvo");
        Assert.assertEquals(driver.findElement(By.id("cars")).getSelectedValue(), "Volvo");
    }

    @Test
    public void w3schoolDragAndDrop() throws InterruptedException {
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml5_draganddrop");
        driver.switchToIFrame(By.id("iframeResult"));
        img_w3schoolDraggable.dragAndDropUsingJs(w3schoolDropTarget);
        img_underDropZone.waitForElementToBeDisplayed();
        driver.switchTo().defaultContent();
        btn_RunJS.click();
        driver.switchToIFrame(By.id("iframeResult"));
        img_underDropZone.doesNotExists();
        img_w3schoolDraggable.dragAndDropUsingJs(w3schoolDropTarget);
        img_underDropZone.waitForElementToBeDisplayed();
    }
}
