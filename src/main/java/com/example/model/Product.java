package com.example.model;

import java.util.Objects;

/**
 * Product model
 */
public class Product {
    private Integer version;
    private String edition;
    private Integer quantity;

    public Product(Integer version, String edition, Integer quantity) {
        this.version = version;
        this.edition = edition;
        this.quantity = quantity;
    }

    public Product(Integer version, Integer quantity) {
        this.version = version;
        this.quantity = quantity;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getVersion(), product.getVersion()) && Objects.equals(getEdition(), product.getEdition()) && Objects.equals(getQuantity(), product.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVersion(), getEdition(), getQuantity());
    }
}
