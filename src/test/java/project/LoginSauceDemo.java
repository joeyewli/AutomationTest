package project;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import cucumber.api.java.After;

import gherkin.ast.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class LoginSauceDemo {
    private String user;
    private String website;
    private static FirefoxDriver driver;

    @Before
    public static void setUp() {
        System.out.println("--------------------------------SET UP -------------------------");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\joeye\\Documents\\Automation Test\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
    }

    @After
    public void tidyUp(){
        System.out.println("--------------------------------~FINISHED-------------------------");
        driver.quit();
    }


    @Given("^I am a (.*)$")
    public void i_am_a_User(String user) {
        this.user = user;
    }

    @Given("^I am on (.*)$")
    public void i_am_on_(String website) {
        this.website = website;
        driver.get(website);
    }

    @When("^I enter my username (.*)$")
    public void i_enter_my_username(String un) {
        WebElement username =  driver.findElementByXPath("//*[@placeholder = 'Username']");
        username.sendKeys(un);
    }

    @When("^I enter my password (.*)$")
    public void i_enter_my_password(String pw) {
        WebElement password =  driver.findElementByXPath("//*[@placeholder = 'Password']");
        password.sendKeys(pw);
    }
    @When("^press the (.*) button$")
    public void press_the_X_button(String x) {
        WebElement button  =  driver.findElementByXPath("//*[@Value = '" + x + "']");
        button.click();
    }


    @Then("^I will login to a page (.*)$")
    public void i_will_login_to_a_page(String webpage) {
        assertEquals(webpage, driver.getCurrentUrl());
    }
    @Then("^I return to (.*)$")
    public void i_return_to_(String website) {

    }
    @Then("^the Title = (.*)$")
    public void the_Title_equals(String title) {
        assertEquals(title , driver.getTitle());
    }
    @Then("^I will get an error message (.*)$")
    public void i_will_get_an_error_message(String message) {
       WebElement m = driver.findElementByXPath("//*[@data-test = 'error']");
       assertEquals(message , m.getText());
    }

    @Given("^I logged in as Standard_User$")
    public void i_logged_in_as_Standard_User() {
        i_am_on_("https://www.saucedemo.com/");
        i_enter_my_username("standard_user");
        i_enter_my_password("secret_sauce");
        press_the_X_button("LOGIN");
    }

    @When("^I press logout$")
    public void i_press_logout() {

        WebElement button  = driver.findElementByXPath("//*[@class = 'bm-burger-button']");
        button.click();
        button  =  driver.findElementByXPath("//*[@id = 'logout_sidebar_link']");
        button.click();
    }


}
