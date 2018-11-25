package projectSauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    private String url = "https://www.saucedemo.com/inventory.html";

    public InventorySauceDemo(WebDriver driver) {
        super(driver);

    }

    public void clickMenuTab(){
        menu.click();
    }
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

    public void printList(){
        for (WebElement we: inventoryItemNames){
            System.out.println(">" + we.getText() + "<");
        }
    }
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
        if(inventoryRemoveFromCart.size()>0) return true;
        else return false;
    }
    public void addXItems(int x){
        // x must be 6 items or below as there are only 6 items for this site
      // System.out.println("===========Number: "+ inventoryAddToCart.size());
        for (int i = 0; i< x; i++){
            inventoryAddToCart.get(0).click();
        }
    }
    public void removeXItems(int x){
        // x must be 6 items or below as there are only 6 items for this site
        for (int i = 0; i< x; i++){
            inventoryRemoveFromCart.get(0).click();
        }
    }

}
