package projectSauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.Integer.parseInt;

public class InventorySauceDemo extends PageObject {

    @FindBy(className = "bm-burger-button")
    private WebElement menu;

    @FindBy(xpath = "//*[@id = 'logout_sidebar_link']")
    private WebElement logout;

    @FindBy(xpath = "//*[@id='inventory_container']/div[@class='inventory_list']/child::div")
    private List<WebElement> inventoryList;
    @FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_name']")
    private List<WebElement> inventoryItemNames;
    @FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_desc']")
    private List<WebElement> inventoryItemDescriptions;
    @FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//div[@class='inventory_item_price']")
    private List<WebElement> inventoryItemPrices;
    @FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//button[@class='add-to-cart-button']")
    private List<WebElement> inventoryAddToCart;
    @FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_list']/child::div//button[@class='remove-from-cart-button']")
    private List<WebElement> inventoryRemoveFromCart;
    @FindBy(xpath = "//*[@data-icon= 'shopping-cart']")
    private WebElement shoppingCart;
    @FindBy(xpath = "//*[@class = 'fa-layers-counter shopping_cart_badge']")
    private WebElement shoppingCartCounter;
    @FindBy(xpath = "//*[@class = 'product_sort_container']")
    private WebElement filter;
    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[1]/div[3]/select/option[1]")
    private WebElement nameAsc;
    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[1]/div[3]/select/option[2]")
    private WebElement nameDesc;
    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[1]/div[3]/select/option[3]")
    private WebElement priceAsc;
    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[1]/div[3]/select/option[4]")
    private WebElement priceDesc;


    private String url = "https://www.saucedemo.com/inventory.html";

    public InventorySauceDemo(WebDriver driver) {
        super(driver);
    }

    public void clickMenuTab(){
        menu.click();
    }

    /**
     * Log out button is only visible when user opened the menu tab by clicking on it
     */
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

    /**
     * For testing purposes, can be removed
     */
    public void printList(){
        for (WebElement we: inventoryItemNames){
            System.out.println(">" + we.getText() + "<");
        }
    }

    /** Find index of item to get it's details
     *
     * Currently using a List per item's attribute e.g. Description, price, add to cart and remove button.
     * This can be removed once I make finding an item's attribute more effective, until then I'm using workaround method
     * @param item = item's name. This will be compared with List of item's name
     * @return return the item's index value
     */
    public int findItemIndex(String item){
        for (int x = 0; x<inventoryItemNames.size();x++){
            // Check position
            // System.out.println("times "+ x + ":"+inventoryItemNames.get(x).getText()+" compare with "+ item);
            if (inventoryItemNames.get(x).getText().equals(item)) {
                return x;
            }
        }
        // TODO need to throw not found error
        return - 1;
    }

    public String getDescriptionFor(String item) {
        try {
            return inventoryItemDescriptions.get(findItemIndex(item)).getText();
        } catch (IndexOutOfBoundsException e){
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
     *
     * @param item = index value to find cart
     */
    public void addItemToCart(String item) {
        try {
           // System.out.println(inventoryAddToCart.get(findItemIndex(item)).getText());
            inventoryAddToCart.get(findItemIndex(item)).click();
          //  System.out.println(inventoryAddToCart.get(findItemIndex(item)).getText());
          //  System.out.println(inventoryRemoveFromCart.get(findItemIndex(item)).getText());
        } catch (IndexOutOfBoundsException e) {
        }
    }
//This only appears if added to cart already
    public void removeItemFromCart(String item) {
        try {
            inventoryRemoveFromCart.get(findItemIndex(item)).click();
        } catch (IndexOutOfBoundsException e) {
        }
    }
    public int getItemsInCart(){
        // must be above 0 otherwise error (can't locate element as it only spans when at least 1 item is added)
        return parseInt(shoppingCartCounter.getText());
    }
    public boolean removeButtonAvailable(){
        // must be above 0 otherwise error (can't locate element as it only spans when at least 1 item is added)
        return inventoryRemoveFromCart.size() > 0;
    }

    /**because the number of "Add to Cart" items in List decrease each time it's been click (button change to "Remove" hence List no longer counts it)
     * we can't use the index value to find the respective item's add to cart button.
     * This is fine for this method as we only add X number of items, not adding specific items.
     *
     * @param x = amount of items to remove
     */
    public void addXItems(int x){
        // TODO throw exception
        // x must be 6 items or below as there are only 6 items for this site
      // System.out.println("===========Number: "+ inventoryAddToCart.size());
        for (int i = 0; i< x; i++){
            inventoryAddToCart.get(0).click();
        }
    }

    /**Similar to addXItems
     *
     * @param x = number of items to remove
     */
    public void removeXItems(int x){
        //TODO throw exception
        // x must be 6 items or below as there are only 6 items for this site
        // x can't be higher than the current "remove" buttons
        for (int i = 0; i< x; i++){
            inventoryRemoveFromCart.get(0).click();
        }
    }

    /** Select by click on Web elements - require more initialisation than Select functionality
     * More work needed to select by filter option,, harder to manage when filter options are changed.
     */
    public void selectFilter(){
        filter.click();
        nameAsc.click();
        filter.click();
        nameDesc.click();
        filter.click();
        priceAsc.click();
        priceDesc.click();
    }

    /** Filter by Select - useful for drop down list
     *
     * sauce demo drop down seem to be faulty, can't filter properly when option selected via automation.
     * @param filterOption = product filter dropdown list options
     */
    public void selectFilter(String filterOption){
        //TODO need to throw nosuchElementException incase user enter a invalid filter option
        Select dropdownFilter = new Select(filter);
        dropdownFilter.selectByVisibleText(filterOption);
    }

    /**Can't be tested due to saucedemon filter isn't working via automation
     * @return
     * false if i-1 (previous) item name is smaller than i-1 (current) item then return false
     * true when all item is smaller than the previous one
     */
    public boolean isInventoryNamesDesc(){
        for(int i = 1; i<inventoryItemNames.size();i++){
            // throw error if inventory is ever 1 item
            // if i item name is bigger than i-1 (previous) then return false
            if(inventoryItemNames.get(i-1).getText().compareTo(inventoryItemNames.get(i).getText())<0){
                return false;
            }
        }
        return true;
    }

    /**Can't be tested due to saucedemon filter isn't working via automation
     * @return
     * false if i-1 (previous) item name is bigger than i-1 (current) item then return false
     * true when all item is bigger than the previous one
     */
    public boolean isInventoryNamesAsc(){
        for(int i = 1; i<inventoryItemNames.size();i++){
            //TODO throw error if inventory is ever 1 item
            if(inventoryItemNames.get(i-1).getText().compareTo(inventoryItemNames.get(i).getText())>0){
                return false;
            }
        }
        return true;
    }
    /**Can't be tested due to saucedemon filter isn't working via automation
     * @return
     * false if i-1 (previous) item name is smaller than i-1 (current) item then return false
     * true when all item is smaller than the previous one
     */
    public boolean isInventoryPriceDesc(){
        for(int i = 1; i<inventoryItemNames.size();i++){
            //TODO throw error if inventory is ever 1 item
            if(parseInt(inventoryItemNames.get(i-1).getText()) < parseInt(inventoryItemNames.get(i).getText())){
                return false;
            }
        }
        return true;
    }

    /** Can't be tested due to saucedemon filter isn't working via automation
     * @return
     * false if i-1 (previous) item name is bigger than i-1 (current) item then return false
     * true when all item is bigger than the previous one
     */
    public boolean isInventoryPriceAsc(){
        for(int i = 1; i<inventoryItemNames.size();i++){
            //TODO throw error if inventory is ever 1 item
            if(parseInt(inventoryItemNames.get(i-1).getText()) > parseInt(inventoryItemNames.get(i).getText())){
                return false;
            }
        }
        return true;
    }

}
