package projectSauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventorySauceDemo extends PageObject {

    @FindBy(className = "bm-burger-button")
    private WebElement menu;

    @FindBy(xpath = "//*[@id = 'logout_sidebar_link']")
    private WebElement logout;
    private String url = "https://www.saucedemo.com/inventory.html";

    public InventorySauceDemo(WebDriver driver) {
        super(driver);

    }

    public void clickMenuTab(){
        menu.click();
    }
    public void clickLogout(){
        clickMenuTab();
        logout.click();
        //return new LoginSauceDemo(driver);
    }

    public String getInventoryPage(){
        return url;
    }
    public boolean isInitialised(){
        return menu.isDisplayed();
    }
}
