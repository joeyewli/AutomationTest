package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.InventoryPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InventoryTest extends FunctionalTest {
    protected WebDriver driver = getDriver();
    private InventoryPage inventoryPage;
    @Before
    public void setup(){
        System.out.println("--------------------INVENTORY SET UP-------------------");
        inventoryPage = new InventoryPage(driver);

    }
    @After("@addingItems")
    public void cleanUp2(){
        System.out.println("--------------------INVENTORY CLEAN UP-------------------");
        i_remove_items(inventoryPage.getItemsInCart());
        //driver.manage().deleteAllCookies();
        // driver.quit();
    }
    @After("@logout")
    public void cleanUp(){
        System.out.println("--------------------INVENTORY (LOGOUT) CLEAN UP-------------------");
        //driver.manage().deleteAllCookies();
       // driver.quit();
    }

    @Then("^I will see (\\d+) inventory items$")
    public void i_will_see_inventory_items(int arg1) {
        //inventoryPage.printList();
        //System.out.println(inventoryPage.findItemIndex("Sauce Labs Onesie"));
        //System.out.println(inventoryPage.getDescriptionFor("Test.allTheThings() T-Shirt (Red)"));

        System.out.println(inventoryPage.getPriceFor("Test.allTheThings() T-Shirt (Red)"));
    }

    @When("^I filter to (.*)$")
    public void i_filter_to(String filterOption) {
        inventoryPage.selectFilter(filterOption);
    }

    @Then("^(.*) - filter option is selected$")
    public void filter_option_is_selected(String filterOption) {
        // assertEquals(filterOption,inventoryPage.getFilterSelection());
    }
    @Then("^teardown$")
    public void teardown() {
        driver.quit();

    }
    @Then("^(.*) is passed$")
    public void result_is_passed(String expectedResult) {

        if ("name asc".equals(expectedResult)) {
            assertTrue(inventoryPage.isInventoryNamesAsc());
        } else if ("name desc".equals(expectedResult)) {
            assertTrue(inventoryPage.isInventoryNamesDesc());
        } else if ("price asc".equals(expectedResult)) {
            assertTrue(inventoryPage.isInventoryPriceAsc());
        } else if ("price desc".equals(expectedResult)) {
            assertTrue(inventoryPage.isInventoryPriceDesc());
        } else {
            System.out.println("Result need to be added to method");
        /*
        switch(expectedResult){
            case "name asc": assertTrue(inventoryPage.isInventoryNamesAsc()); break;
            case "name desc": assertTrue(inventoryPage.isInventoryNamesDesc()); break;
            case "price asc": assertTrue(inventoryPage.isInventoryPriceAsc()); break;
            case "price desc": assertTrue(inventoryPage.isInventoryPriceDesc()); break;
            default: System.out.println("Result need to be added to method"); break;
            */
        }
    }
    @When("^I added (\\d+) item\\(s\\)$")
    public void i_added_item_s(int x) {
        //assertTrue(inventoryPage.isInitialised());
        inventoryPage.addXItems(x);
    }

    @Then("^there will be (\\d+) item\\(s\\) in the cart$")
    public void there_will_be_items_in_the_cart(int x) {
        assertEquals(x, inventoryPage.getItemsInCart());
    }

    @Then("^I can see the item has (\\d+) remove item$")
    public void i_can_see_the_items_has_remove_item(int x) {
        assertTrue(inventoryPage.removeButtonAvailable());
    }

    @When("^I remove (\\d+) item\\(s\\)$")
    public void i_remove_items(int x) {
        inventoryPage.removeXItems(x);

    }


}
