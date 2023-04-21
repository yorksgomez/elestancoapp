package co.app.mercaditodesofi.model;

public class Product {

    //Variables
    private int id;
    private String name,
                   description,
                   imgUrl,
                   category,
                   subcategory;
    private int price;

    //Constructors
    public Product(int id, String name, String description, String imgUrl, int price, String subcategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.subcategory = subcategory;
    }

    public Product() {  }

    //Overrided

    @Override
    public String toString() {
        return "name=" + name + ";description=" + description + ";imgUrl=" + imgUrl + ";price=" + price;
    }

    //Setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getSubcategory() { return subcategory; }

    public void setSubcategory(String subcategory) { this.subcategory = subcategory; }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
