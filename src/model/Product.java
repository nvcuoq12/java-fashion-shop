package model;

public class Product {
    private String id;
    private String name;
    private Category category;
    private double price;

    public Product(String id, String name, Category category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setCategory(Category category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + category.getName() + " | " + price + " VND";
    }
}
