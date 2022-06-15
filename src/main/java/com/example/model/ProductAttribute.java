package com.example.model;

import java.util.Objects;

/**
 * Product attribute model
 */
public class ProductAttribute {
    private Integer version;
    private String edition;

    public ProductAttribute(Integer version, String edition) {
        this.version = version;
        this.edition = edition;
    }

    public ProductAttribute(Integer version) {
        this.version = version;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAttribute that = (ProductAttribute) o;
        return Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getEdition(), that.getEdition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVersion(), getEdition());
    }
}
