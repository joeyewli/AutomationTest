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
        driver.manage().deleteAllCookies();
        System.out.println("===================================");
       // driver.close();
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
        inventoryPage = new InventorySauceDemo(driver);
        assertTrue(inventoryPage.isInitialised());
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

        // This shows all 6 items along with item name, description and price
        //List<WebElement> inventory  = driver.findElementsByXPath("//*[@id='inventory_container']//div[@class='inventory_list']/child::div");
        List<WebElement> inventory  = driver.findElements(By.xpath("//*[@id='inventory_container']/div[@class='inventory_list']/child::div"));
        for (WebElement we: inventory){
            System.out.println(we.getText());
        }

        /* This shows all 6 item's name
        List<WebElement> inventory  = driver.findElementsByXPath("//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_name']");
        for (WebElement we: inventory){
            System.out.println(we.getText()+"------------------------------");
        }
        */
        /* This shows all 6 item's description
        List<WebElement> inventory  = driver.findElementsByXPath("//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_desc']");
        for (WebElement we: inventory){
            System.out.println(we.getText()+"------------------------------");
        }
        */
        /*
        List<WebElement> inventory  = driver.findElementsByXPath("//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_price']");
        for (WebElement we: inventory){
            System.out.println(we.getText()+"------------------------------");
        }
        */

/*
        List<WebElement> inventory = driver.findElementsByXPath("//*[@id='inventory_container']//div[@class='inventory_list']/child::div");
        //List<WebElement> inventory = driver.findElementsByXPath("//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_name']");
        //WebElement inventory  = driver.findElementByXPath("//*[@id='inventory_container']/child::div");
        //System.out.println(inventory.getText());

//        for (WebElement we : inventory) {
//            System.out.println(we.getText()+"------------------------------");
///*
//            System.out.println(we.findElement(By.xpath("//div[@class='inventory_item_name']")).getText());
//            System.out.println(we.findElement(By.xpath("//div[@class='inventory_item_desc']")).getText());
//            System.out.println(we.findElement(By.xpath("//div[@class='inventory_item_price']")).getText());
//*
//        }
        System.out.println(inventory.get(3).getText());
        System.out.println(inventory.get(3).toString());

//        System.out.println(inventory.get(3).findElement(By.xpath("//div[@class='inventory_item_name']")).getText()+"--1");
//        System.out.println(inventory.get(3).findElement(By.xpath("//div[@class='inventory_item_desc']")).getText()+"--2");
//        System.out.println(inventory.get(3).findElement(By.xpath("//div[@class='inventory_item_price']")).getText()+"--3");
        System.out.println(inventory.contains("Sauce Labs Bike Light"));
*/
    }


/*
        Iterator<WebElement> itr = inventory.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
*/

}
