package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.InventoryPage;
import pageObjects.LoginPage;


import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest extends FunctionalTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    protected WebDriver driver = getDriver();
    private String user;
    private String website;
    private WebDriverWait wait;

    @Before
    public void setup(){
        System.out.println("--------------------login SET UP-------------------");

    }
    @After()
    public void cleanUp2() {
        System.out.println("--------------------Login CLEAN UP-------------------");
        //driver.manage().deleteAllCookies();
        // driver.quit();
    }

    @Given("^I am a (.*)$")
    public void i_am_a_User(String user) {
        this.user = user;
    }

    @Given("^I am on the Login page$")
    public void i_am_on_the_Login_page() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToHomePage();
    }

    @When("^I enter my username (.*)$")
    public void i_enter_my_username(String un) {
        loginPage.setUsername(un);
    }

    @When("^I enter my password (.*)$")
    public void i_enter_my_password(String pw) {
        loginPage.setPassword(pw);
    }

    @When("^press the LOGIN button$")
    public void press_the_Login_button() {
        inventoryPage = loginPage.clickLogin();
        assertTrue(inventoryPage.isInitialised());
    }

    @When("^press the LOGIN button expecting failure$")
    public void press_the_Login_button_expecting_failure() {
        loginPage.clickLoginExpectingFailure();
    }
    @Then("^I will login to the inventory page$")
    public void i_will_login_to_the_inventory_Page() {
        assertEquals(inventoryPage.getInventoryPage(), driver.getCurrentUrl());
    }

    @Then("^I will remain at the Login page$")
    public void i_will_remain_at_the_Login_Page() {
        assertEquals(loginPage.getLoginPage(), driver.getCurrentUrl());
    }

    @Then("^I will return to the Login Page$")
    public void i_will_return_to_the_Login_Page() {
        assertEquals(loginPage.getLoginPage(), driver.getCurrentUrl());

    }

    @Then("^the Title = (.*)$")
    public void the_Title_equals(String title) {
        assertEquals(title, driver.getTitle());
    }

    @Then("^I will get an error message (.*)$")
    public void i_will_get_an_error_message(String message) {
        assertEquals(message, loginPage.getErrorMessage());
    }

    @Given("^I logged in as Standard_User$")
    public void i_logged_in_as_Standard_User() {
        i_am_on_the_Login_page();
        loginPage.setUsername("standard_user")
                .setPassword("secret_sauce");
        press_the_Login_button();
        assertTrue(inventoryPage.isInitialised());
//        i_enter_my_username("standard_user");
//        i_enter_my_password("secret_sauce");
    }

    @When("^I press logout$")
    public void i_press_logout() {
        loginPage = inventoryPage.clickLogout();
    }


    @Then("Test$")
    public void test() {
        //inventoryPage.printList();
        //inventoryPage.addXItems(6);
        //inventoryPage.removeXItems(6);
        inventoryPage.selectFilter();


        /*
        driver.get("https://sdlsit.appiancloud.com");
        driver.findElement(By.xpath("//*[@id=\"un\"]")).sendKeys("JoeLi.PM@sdl.com");
        driver.findElement(By.xpath("//*[@id=\"pw\"]")).sendKeys("Appian2018");
        driver.findElement(By.xpath("/html/body/div[3]/div/form[2]/div[4]/div/div[2]/input")).click();
        //driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[1]/a[3]")).click();
        //driver.findElement(By.xpath("//*[@id='report-body']/div/main/div/div/div/div/div/div[4]/h2")).click();
        //driver.findElement(By.xpath("//*[@id=\"report-body\"]/div/main/div/div/div/div/div/div[4]/div/div/div/div/div[1]/div[1]/div[2]/div/div")).sendKeys("Adobe Systems Incorporated");
        WebElement a =driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div/tempo-report/div[3]/div/main/div/div/div/div/div/div[4]/div/div/div[1]/div[1]/div[2]/div/div/input"));
        a.click();
        a.sendKeys("Adobe Systems Incorporated");
        //driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        //a.wait(2000,TimeUnit.SECONDS);
       // wait = new WebDriverWait(driver, 10);
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div/tempo-report/div[3]/div/main/div/div/div/div/div/div[4]/div/div/div[1]/div[1]/div[2]/div/div/div")));

        //driver.manage().timeouts().setScriptTimeout(3000,SECONDS);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.sendKeys(Keys.RETURN);



        //a.click();
//        Select b = new Select(driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div/tempo-report/div[3]/div/main/div/div/div/div/div/div[4]/div/div/div[1]/div[1]/div[2]/div/div/input")));
       // b.selectByVisibleText("Adobe Systems Incorporated");
*/
    }



}
