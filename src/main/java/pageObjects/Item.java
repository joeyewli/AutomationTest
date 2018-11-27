package pageObjects;

public class Item {
    private String name, description;
    private double price;
    public Item(String name, double price, String description){
        this.name = name;
        this.price =price;
        this.description = description;
    }
}
