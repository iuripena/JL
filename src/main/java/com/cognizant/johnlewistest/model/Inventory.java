package com.cognizant.johnlewistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Inventory {
    @JsonProperty
    private Product[] products;

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory inventory = (Inventory) o;
        return Arrays.equals(getProducts(), inventory.getProducts());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getProducts());
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + Arrays.toString(products) +
                '}';
    }
}
