package projectSauceDemo;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepsSauceDemo {
    private LoginSauceDemo loginPage;
    private InventorySauceDemo inventoryPage;
    protected static WebDriver driver;
    private String user;
    private String website;

    @Before
    public void setUp() {
        System.out.println("--------------------------------SET UP -------------------------");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\joeye\\Documents\\Automation Test\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @After
    public void cleanUp(){
        driver.manage().deleteAllCookies();// driver.close();
        driver.quit();
    }

    @Given("^I am a (.*)$")
    public void i_am_a_User(String user) {
        this.user = user;
    }

    @Given("^I am on the Login page$")
    public void i_am_on_the_Login_page() {
        loginPage = new LoginSauceDemo(driver);
        driver.get(loginPage.getLoginPage());
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
        LoginSauceDemo.clickLogin();
        if(!driver.getCurrentUrl().equalsIgnoreCase(loginPage.getLoginPage())){
            inventoryPage = new InventorySauceDemo(driver);
            assertTrue(inventoryPage.isInitialised());
        }

    }

    @Then("^I will login to the inventory page$")
    public void i_will_login_to_the_inventory_Page() {
        assertEquals(inventoryPage.getInventoryPage(), driver.getCurrentUrl());
    }

    @Then("^I will remain at the Login page$")
    public void i_will_remain_at_the_Login_Page() {
        assertEquals(loginPage.getLoginPage(),driver.getCurrentUrl());
    }

    @Then("^I will return to the Login Page$")
    public void i_will_return_to_the_Login_Page(){
        assertEquals(loginPage.getLoginPage(),driver.getCurrentUrl());

    }

    @Then("^the Title = (.*)$")
    public void the_Title_equals(String title) {
        assertEquals(title , driver.getTitle());
    }

    @Then("^I will get an error message (.*)$")
    public void i_will_get_an_error_message(String message) {
        assertEquals(message , loginPage.getErrorMessage());
    }

    @Given("^I logged in as Standard_User$")
    public void i_logged_in_as_Standard_User() {
        i_am_on_the_Login_page();
        i_enter_my_username("standard_user");
        i_enter_my_password("secret_sauce");
        press_the_Login_button();
    }

    @When("^I press logout$")
    public void i_press_logout() {
        inventoryPage.clickLogout();
    }

    @Then("^I will see (\\d+) inventory items$")
    public void i_will_see_inventory_items(int arg1) {
        //inventoryPage.printList();
        //System.out.println(inventoryPage.findItemIndex("Sauce Labs Onesie"));
        //System.out.println(inventoryPage.getDescriptionFor("Test.allTheThings() T-Shirt (Red)"));
        System.out.println(inventoryPage.getPriceFor("Test.allTheThings() T-Shirt (Red)"));

    }
    @Then ("Test$")
    public void test(){

/*
        List<WebElement> elements = driver.findElements(By.xpath("//*[@data-icon]"));
        for(WebElement el:elements)
        {
            System.out.println(el.getText() + "=Text: Class=" +el.getAttribute("shopping=cart"));
        }
*/
    }

    @When("^I added (\\d+) item\\(s\\)$")
    public void i_added_item_s(int x)   {
        inventoryPage.addXItems(x);
    }

    @Then("^there will be (\\d+) item\\(s\\) in the cart$")
    public void there_will_be_items_in_the_cart(int x)  {
        assertEquals(x, inventoryPage.getItemsInCart());

    }
    @Then("^I can see the item has (\\d+) remove item$")
    public void i_can_see_the_items_has_remove_item(int x)   {
        assertTrue(inventoryPage.removeButtonAvailable());
    }
    @When("^I remove (\\d+) item\\(s\\)$")
    public void i_remove_items(int x)   {
        inventoryPage.removeXItems(x);

    }


}