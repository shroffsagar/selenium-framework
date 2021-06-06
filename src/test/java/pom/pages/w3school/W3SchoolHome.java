package pom.pages.w3school;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.pages.w3school.tutorial.W3SchoolTutorialHome;

public class W3SchoolHome extends W3SchoolBasePage {

    @FindBy(xpath = "//*[@id='main']//a[normalize-space(.)='Learn HTML']")
    public WebElement learnHtmlButton;

    public W3SchoolHome() {
        super("", "W3Schools Online Web");
    }

    public W3SchoolTutorialHome clickOnLearnHtml() {
        learnHtmlButton.click();
        W3SchoolTutorialHome tutorialHome = new W3SchoolTutorialHome();
        tutorialHome.waitForPageToOpen();
        return tutorialHome;
    }
}
