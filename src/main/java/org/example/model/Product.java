package org.example.model;

public class Product {
    private long id;
    private String name;
    private float price;
    private int quantity;
    private boolean accessibility;
    private boolean active;
    private String image;
    private String[] images;
    private String description;
    private float width;
    private float height;

    private String category;
    private Author author;

    public Product(long id, String name, float price, int quantity, boolean accessibility, boolean active, String image, String[] images, String description, float width, float height, Author author,String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.accessibility = accessibility;
        this.active = active;
        this.image = image;
        this.images = images;
        this.description = description;
        this.width = width;
        this.height = height;
        this.author = author;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAccessibility() {
        return accessibility;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
