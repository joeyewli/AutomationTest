package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import sun.rmi.runtime.Log;

public class PageObject {
    protected WebDriver driver;
    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public LoginPage navigateToHomePage(){
    driver.navigate().to("https://www.saucedemo.com/index.html");
    return new LoginPage(driver);
    }

}

