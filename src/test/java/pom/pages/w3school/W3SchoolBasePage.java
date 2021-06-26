package pom.pages.w3school;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pom.common.WebPage;

import java.util.ArrayList;
import java.util.List;

public class W3SchoolBasePage extends WebPage
{
    public MenuBar menuBar;

    public W3SchoolBasePage(String relativeUrl, String title){
        super(relativeUrl,title);
        menuBar = new MenuBar();
        PageFactory.initElements(driver, menuBar);
    }

    public class MenuBar extends WebPage {

        @FindBy(xpath = "//*[contains(@id,'nav_')][not(contains(@style,'display: none;'))]//*[contains(@class,'w3-content')]")
        public WebElement openedMenuContainer;

        public void openMenu(String menuName){
            WebElement menu = driver.findElement(By.xpath("//*[contains(@id, 'navbtn')][normalize-space(.)='"+menuName+"']"));
            menu.click();
            openedMenuContainer.waitForElementToBeDisplayed();
        }

        public List<String> getMenuListItem(String listHeader){
            String xpath = "//*[contains(@id,'nav_')][not(contains(@style,'display: none;'))]//*[contains(@class,'w3-content')]//*[contains(@class,'w3-col')]//h3[normalize-space(.)='"+listHeader+"']/ancestor::*[contains(@class,'w3-col')]/*[contains(@class,'w3-margin-top') or contains(@class, 'w3-bar-item')]";
            List<WebElement> colValues = driver.findElements(By.xpath(xpath));
            boolean isStartTagFound = false;
            List<String> actualMenuSubItems = new ArrayList<>();
            for(WebElement colValue: colValues){
                if (colValue.getText().equals(listHeader)){
                    isStartTagFound = true;
                }
                else if(colValue.getTagName() == "h3"){
                    break;
                }
                else if(isStartTagFound){
                    actualMenuSubItems.add(colValue.getText());
                }
            }
            return actualMenuSubItems;
        }
    }
}
