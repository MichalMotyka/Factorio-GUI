package org.example.model;

public class ProductRow {
    private final long id;
    private final String name;
    private final String url;
    private final float quantity;

    public ProductRow(long id, String name, String url, float quantity) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public float getQuantity() {
        return quantity;
    }
}
