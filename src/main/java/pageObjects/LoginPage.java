package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@SuppressWarnings("WeakerAccess")
public class LoginPage extends PageObject {

    @FindBy(xpath = "//*[@placeholder = 'Username']")
    private WebElement username;

    @FindBy(xpath = "//*[@placeholder = 'Password']")
    private WebElement password;

    @FindBy(xpath = "//*[@value = 'LOGIN']")
    private WebElement login;

    @FindBy(xpath = "//*[@data-test = 'error']")
    private WebElement errorMessage;

    private String url = "https://www.saucedemo.com/index.html";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getLoginPage() {
        return url;
    }

    public LoginPage setUsername(String un) {
        username.clear();
        username.sendKeys(un);
        return new LoginPage(driver);
    }

    public LoginPage setPassword(String pw) {
        password.clear();
        password.sendKeys(pw);
        return new LoginPage(driver);
    }

    public InventoryPage clickLogin() {
        login.click();
        return new InventoryPage(driver);
    }
    public LoginPage clickLoginExpectingFailure() {
        login.click();
        return new LoginPage(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}


