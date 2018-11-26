package projectSauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@SuppressWarnings("WeakerAccess")
public class LoginSauceDemo extends PageObject {

    @FindBy(xpath = "//*[@placeholder = 'Username']")
    private WebElement username;

    @FindBy(xpath = "//*[@placeholder = 'Password']")
    private WebElement password;

    @FindBy(xpath = "//*[@value = 'LOGIN']")
    private static WebElement login;

    @FindBy(xpath = "//*[@data-test = 'error']")
    private WebElement errorMessage;

    private String url = "https://www.saucedemo.com/index.html";

    public LoginSauceDemo(WebDriver driver) {
        super(driver);
    }

    public String getLoginPage() {
        return url;
    }

    public void setUsername(String un) {
        username.clear();
        username.sendKeys(un);
    }

    public void setPassword(String pw) {
        password.sendKeys(pw);
    }

    public static void clickLogin() {
        login.click();
      //  return new InventorySauceDemo(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}


