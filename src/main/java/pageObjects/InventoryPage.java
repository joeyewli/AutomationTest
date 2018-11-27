package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.Integer.parseInt;

@SuppressWarnings("ALL")
public class InventoryPage extends PageObject {

    @FindBy(className = "bm-burger-button")
    private WebElement menu;

    @FindBy(xpath = "//*[@id = 'logout_sidebar_link']")
    private WebElement logout;

    //@FindBy(xpath = "//*[@id='inventory_container']/div[@class='inventory_list']/child::div")
    @FindBy(xpath = "//*[@class='inventory_list']/child::div")
    private List<WebElement> inventoryList;

    //@FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_name']")
    @FindBy(xpath = "//*[@class='inventory_list']/child::div//*[contains(@class,'inventory_item_name')]")
    private List<WebElement> inventoryItemNames;

    //@FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_desc']")
    @FindBy(xpath = "//*[@class='inventory_list']/child::div//*[contains(@class,'inventory_item_desc')]")
    private List<WebElement> inventoryItemDescriptions;

    //@FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_price']")
    @FindBy(xpath = "//*[@class='inventory_list']/child::div//*[contains(@class,'inventory_item_price')]")
    private List<WebElement> inventoryItemPrices;

    //@FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//button[@class='add-to-cart-button']")
    @FindBy(xpath = "//*[@class='inventory_list']/child::div//*[contains(@class,'add-to-cart-button')]")
    private List<WebElement> inventoryAddToCart;

    //@FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//button[@class='remove-from-cart-button']")
    @FindBy(xpath = "//*[@class='inventory_list']/child::div//*[contains(@class,'remove-from-cart-button')]")
    private List<WebElement> inventoryRemoveFromCart;

    @FindBy(xpath = "//*[@data-icon= 'shopping-cart']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//*[@class = 'fa-layers-counter shopping_cart_badge']")
    private WebElement shoppingCartCounter;

    @FindBy(xpath = "//*[@class = 'product_sort_container']")
    private WebElement filter;

    //@FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[1]/div[3]/select/option[1]")
    //@FindBy(xpath = "//*[@class = 'product_sort_container']/child::option[1]")
    @FindBy(xpath = "//option[contains(text(),'Name (A to Z)')]")
    private WebElement nameAsc;

    //@FindBy(xpath = "//*[@class = 'product_sort_container']/child::option[2]")
    @FindBy(xpath = "//option[contains(text(),'Name (Z to A)')]")
    private WebElement nameDesc;

    //@FindBy(xpath = "//*[@class = 'product_sort_container']/child::option[3]")
    @FindBy(xpath = "//option[contains(text(),'Price (low to high)')]")
    private WebElement priceAsc;

    //@FindBy(xpath = "//*[@class = 'product_sort_container']/child::option[4]")
    @FindBy(xpath = "//option[contains(text(),'Price (high to low)')]")
    private WebElement priceDesc;


    private String url = "https://www.saucedemo.com/inventory.html";

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage clickMenuTab() {
        menu.click();
        return new InventoryPage(driver);
    }

    /**
     * Log out button is only visible when user opened the menu tab by clicking on it
     */
    public LoginPage clickLogout() {
        System.out.println("TEST TEST TEST");
        clickMenuTab();
        logout.click();
        return new LoginPage(driver);
    }

    public String getInventoryPage() {
        return url;
    }

    public boolean isInitialised() {
        return menu.isDisplayed();
    }

    /**
     * TODO: For testing purposes, can be removed
     */
    public InventoryPage printList() {
        for (WebElement we : inventoryItemNames) {
            System.out.println(">" + we.getText() + "<");
        }
        return new InventoryPage(driver);
    }

    /**
     * Find index of item to get it's details
     * <p>
     * Currently using a List per item's attribute e.g. Description, price, add to cart and remove button.
     * This can be removed once I make finding an item's attribute more effective, until then I'm using workaround method
     *
     * @param item = item's name. This will be compared with List of item's name
     * @return return the item's index value
     */
    public int findItemIndex(String item) {
        for (int x = 0; x < inventoryItemNames.size(); x++) {
            // Check position
            // System.out.println("times "+ x + ":"+inventoryItemNames.get(x).getText()+" compare with "+ item);
            if (inventoryItemNames.get(x).getText().equals(item)) {
                return x;
            }
        }
        // TODO need to throw not found error
        return -1;
    }

    public String getDescriptionFor(String item) {
        try {
            return inventoryItemDescriptions.get(findItemIndex(item)).getText();
        } catch (IndexOutOfBoundsException e) {
        }
        return "Item not found";
    }

    public String getPriceFor(String item) {
        try {
            return inventoryItemPrices.get(findItemIndex(item)).getText();
        } catch (IndexOutOfBoundsException e) {
        }
        return "Item not found";
    }

    /**
     * @param item = index value to find cart
     */
    public InventoryPage addItemToCart(String item) {
        try {
            // System.out.println(inventoryAddToCart.get(findItemIndex(item)).getText());
            inventoryAddToCart.get(findItemIndex(item)).click();
            //  System.out.println(inventoryAddToCart.get(findItemIndex(item)).getText());
            //  System.out.println(inventoryRemoveFromCart.get(findItemIndex(item)).getText());
        } catch (IndexOutOfBoundsException e) {
        }
        return new InventoryPage(driver);
    }

    //This only appears if added to cart already
    public InventoryPage removeItemFromCart(String item) {
        try {
            inventoryRemoveFromCart.get(findItemIndex(item)).click();
        } catch (IndexOutOfBoundsException e) {
        }
        return new InventoryPage(driver);
    }

    public int getItemsInCart() {
        // must be above 0 otherwise error (can't locate element as it only spans when at least 1 item is added)
        return parseInt(shoppingCartCounter.getText());
    }

    public boolean removeButtonAvailable() {
        // must be above 0 otherwise error (can't locate element as it only spans when at least 1 item is added)
        return inventoryRemoveFromCart.size() > 0;
    }

    /**
     * because the number of "Add to Cart" items in List decrease each time it's been click (button change to "Remove" hence List no longer counts it)
     * we can't use the index value to find the respective item's add to cart button.
     * This is fine for this method as we only add X number of items, not adding specific items.
     *
     * @param x = amount of items to remove
     */
    public InventoryPage addXItems(int x) {
        // TODO throw exception
        // x must be 6 items or below as there are only 6 items for this site
        // System.out.println("===========Number: "+ inventoryAddToCart.size());
        for (int i = 0; i < x; i++) {
            inventoryAddToCart.get(0).click();
        }
        return new InventoryPage(driver);
    }

    /**
     * Similar to addXItems
     *
     * @param x = number of items to remove
     */
    public InventoryPage removeXItems(int x) {
        //TODO throw exception
        // x must be 6 items or below as there are only 6 items for this site
        // x can't be higher than the current "remove" buttons
        for (int i = 0; i < x; i++) {
            inventoryRemoveFromCart.get(0).click();
        }
        return new InventoryPage(driver);
    }


    /**
     * Select by click on Web elements - require more initialisation than Select functionality
     * More work needed to select by filter option,, harder to manage when filter options are changed.
     */
    public InventoryPage selectFilter() {
        filter.click();
        nameAsc.click();
        filter.click();
        nameDesc.click();
        filter.click();
        priceAsc.click();
        priceDesc.click();
        return new InventoryPage(driver);
    }

    /**
     * Filter by Select - useful for drop down list
     * <p>
     * sauce demo drop down seem to be faulty, can't filter properly when option selected via automation.
     *
     * @param filterOption = product filter dropdown list options
     */
    public InventoryPage selectFilter(String filterOption) {
        //TODO need to throw nosuchElementException incase user enter a invalid filter option
        Select dropdownFilter = new Select(filter);
        dropdownFilter.selectByVisibleText(filterOption);
        return new InventoryPage(driver);
    }


    /**
     * Can't be tested due to saucedemon filter isn't working via automation
     *
     * @return false if i-1 (previous) item name is smaller than i-1 (current) item then return false
     * true when all item is smaller than the previous one
     */
    public boolean isInventoryNamesDesc() {
        for (int i = 1; i < inventoryItemNames.size(); i++) {
            // throw error if inventory is ever 1 item
            // if i item name is bigger than i-1 (previous) then return false
            if (inventoryItemNames.get(i - 1).getText().compareTo(inventoryItemNames.get(i).getText()) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Can't be tested due to saucedemon filter isn't working via automation
     *
     * @return false if i-1 (previous) item name is bigger than i-1 (current) item then return false
     * true when all item is bigger than the previous one
     */
    public boolean isInventoryNamesAsc() {
        for (int i = 1; i < inventoryItemNames.size(); i++) {
            //TODO throw error if inventory is ever 1 item
            if (inventoryItemNames.get(i - 1).getText().compareTo(inventoryItemNames.get(i).getText()) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Can't be tested due to saucedemon filter isn't working via automation
     *
     * @return false if i-1 (previous) item name is smaller than i-1 (current) item then return false
     * true when all item is smaller than the previous one
     */
    public boolean isInventoryPriceDesc() {
        for (int i = 1; i < inventoryItemNames.size(); i++) {
            //TODO throw error if inventory is ever 1 item
            if (parseInt(inventoryItemNames.get(i - 1).getText()) < parseInt(inventoryItemNames.get(i).getText())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Can't be tested due to saucedemon filter isn't working via automation
     *
     * @return false if i-1 (previous) item name is bigger than i-1 (current) item then return false
     * true when all item is bigger than the previous one
     */
    public boolean isInventoryPriceAsc() {
        for (int i = 1; i < inventoryItemNames.size(); i++) {
            //TODO throw error if inventory is ever 1 item
            if (parseInt(inventoryItemNames.get(i - 1).getText()) > parseInt(inventoryItemNames.get(i).getText())) {
                return false;
            }
        }
        return true;
    }

}
