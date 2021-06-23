package pom.pages.w3school.tryit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pom.common.Browser;
import pom.common.BrowserProvider;
import pom.pages.w3school.W3SchoolBasePage;
import pom.pages.w3school.W3SchoolHome;

public class TryItPage extends W3SchoolBasePage {

    @FindBy(css = ".trytopnav .w3-hover-text-green") public WebElement runJsBtn;
    @FindBy(id = "iframeResult") public WebElement rightsidePanelFrame;
    @FindBy(id = "tryhome") public WebElement w3SchoolHomeIcon;
    @FindBy(xpath = "//button[normalize-space(.)='Try it']") public WebElement tryAlertButton;
    @FindBy(id = "cars") public WebElement tryCarDropdown;
    @FindBy(css="#drag1[draggable]") public WebElement tryDragSourceImg;
    @FindBy(css="#div1[ondrop]") public WebElement tryDropTarget;
    @FindBy(css="#div1[ondrop] img") public WebElement imgUnderDropZone;

    public TryItPage(Component component) {
        super(component.url, "Tryit Editor");
    }

    public static void open(Component component) {
        BrowserProvider.getInstance().getBrowser().open(getUrl(component.url));
    }

    public TryItPage runJavascript() {
        runJsBtn.click();
        return this;
    }

    public W3SchoolHome clickOnW3SchoolHome() {
        w3SchoolHomeIcon.click();
        driver.switchToTab("W3Schools Online Web");
        return new W3SchoolHome();
    }

    public TryItPage tryOnAlertAndAcceptIt() {
        driver.switchToIFrame(rightsidePanelFrame);
        tryAlertButton.click();
        driver.acceptAlert("Hello! I am an alert box!");
        driver.switchTo().defaultContent();
        return this;
    }

    public TryItPage selectCar(String car) {
        driver.switchToIFrame(rightsidePanelFrame);
        tryCarDropdown.selectByVisibleText(car);
        driver.switchTo().defaultContent();
        return this;
    }

    public String getSelectedCar(){
        try {
            driver.switchToIFrame(rightsidePanelFrame);
            return tryCarDropdown.getSelectedValue();
        }
        finally {
            driver.switchTo().defaultContent();
        }
    }

    public TryItPage tryDragdrop() {
        driver.switchToIFrame(rightsidePanelFrame);
        tryDragSourceImg.dragAndDropUsingJs(tryDropTarget);
        imgUnderDropZone.waitForElementToBeDisplayed();
        driver.switchTo().defaultContent();
        return this;
    }

    public enum Component {
        alert("jsref/tryit.asp?filename=tryjsref_alert"),
        drag_and_drop("html/tryit.asp?filename=tryhtml5_draganddrop"),
        select("tags/tryit.asp?filename=tryhtml_select");

        String url;
        private Component(String url)
        {
            this.url = url;
        }
    }
}
