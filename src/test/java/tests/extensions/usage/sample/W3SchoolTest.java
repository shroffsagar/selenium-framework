package tests.extensions.usage.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pom.pages.w3school.W3SchoolHome;
import pom.pages.w3school.tryit.TryItPage;
import tests.common.UITest;

import java.util.Arrays;

@Severity(SeverityLevel.CRITICAL)
public class W3SchoolTest extends UITest {

    @Description("Test alert interaction on w3school")
    @Test(description = "TC-101 - Ability to handle alert on w3school")
    public void w3schoolAlertPage() {
        TryItPage alertPage = new TryItPage(TryItPage.Component.alert);
        alertPage.open();
        alertPage.tryOnAlertAndAcceptIt();
        W3SchoolHome w3SchoolHome = alertPage.clickOnW3SchoolHome();
        w3SchoolHome.clickOnLearnHtml();
        w3SchoolHome.menuBar.openMenu("Tutorials");
        soft.assertEquals(w3SchoolHome.menuBar.getMenuListItem("HTML and CSS"), Arrays.asList("Learn HTML", "Learn CSS", "Learn Bootstrap", "Learn W3.CSS", "Learn Colors", "Learn Icons", "Learn Graphics", "Learn SVG", "Learn Canvas", "Learn How To", "Learn Sass"));
        soft.assertEquals(w3SchoolHome.menuBar.getMenuListItem("Programming"), Arrays.asList("Learn Python", "Learn Java", "Learn C++", "Learn C#", "Learn R", "Learn Kotlin"));
        w3SchoolHome.close();
        alertPage.switchToTab();
        soft.assertAll("Mismatch between expected and actual values");
    }

    @Test
    public void w3schoolSelect() {
        TryItPage selectPage = new TryItPage(TryItPage.Component.select);
        selectPage.open();
        selectPage.selectCar("Opel");
        soft.assertEquals(selectPage.getSelectedCar(), "Opel");
        selectPage.selectCar("Volvo");
        soft.assertEquals(selectPage.getSelectedCar(), "Volvo");
        soft.assertAll();
    }

    @Test
    public void w3schoolDragAndDrop() throws InterruptedException {
        TryItPage dragAndDropPage = new TryItPage(TryItPage.Component.drag_and_drop);
        dragAndDropPage.open();
        dragAndDropPage.tryDragdrop();
        dragAndDropPage.runJavascript();
        dragAndDropPage.imgUnderDropZone.shouldNotExist();
        dragAndDropPage.tryDragdrop();
    }
}
