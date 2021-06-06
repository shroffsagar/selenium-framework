package tests.extensions.usage.sample;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.pages.w3school.W3SchoolHome;
import pom.pages.w3school.tryit.TryItPage;
import tests.common.UITest;

import java.util.Arrays;

public class W3SchoolTest extends UITest {

    @Test
    public void w3schoolAlertPage() {
        TryItPage alertPage = new TryItPage(TryItPage.Component.alert);
        alertPage.open();
        alertPage.tryOnAlertAndAcceptIt();
        W3SchoolHome w3SchoolHome = alertPage.clickOnW3SchoolHome();
        w3SchoolHome.clickOnLearnHtml();
        w3SchoolHome.menuBar.openMenu("Tutorials");
        boolean result = w3SchoolHome.menuBar.menuListHasFollowingItems("HTML and CSS", Arrays.asList("Learn HTML", "Learn CSS", "Learn Bootstrap", "Learn W3.CSS", "Learn Colors", "Learn Icons", "Learn Graphics", "Learn SVG", "Learn Canvas", "Learn How To", "Learn Sass"));
        Assert.assertEquals(result, true);
        result = w3SchoolHome.menuBar.menuListHasFollowingItems("Programming", Arrays.asList("Learn Python", "Learn Java", "Learn C++", "Learn C#", "Learn R", "Learn Kotlin"));
        Assert.assertEquals(result, true);
        w3SchoolHome.close();
        alertPage.switchToTab();
    }

    @Test
    public void w3schoolSelect(){
        TryItPage selectPage = new TryItPage(TryItPage.Component.select);
        selectPage.open();
        selectPage.selectCar("Opel");
        Assert.assertEquals(selectPage.getSelectedCar(), "Opel");
        selectPage.selectCar("Volvo");
        Assert.assertEquals(selectPage.getSelectedCar(), "Volvo");
    }

    @Test
    public void w3schoolDragAndDrop() throws InterruptedException {
        TryItPage dragAndDropPage = new TryItPage(TryItPage.Component.drag_and_drop);
        dragAndDropPage.open();
        dragAndDropPage.tryDragdrop();
        dragAndDropPage.runJavascript();
        dragAndDropPage.imgUnderDropZone.doesNotExists();
        dragAndDropPage.tryDragdrop();
    }
}
