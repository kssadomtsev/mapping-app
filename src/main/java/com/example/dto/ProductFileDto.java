package com.example.dto;


import com.example.model.ProductAttribute;

import java.util.List;
import java.util.Map;


/**
 * Source data representation model
 */
public class ProductFileDto {
    private List<String> productList;
    private Map<String, ProductAttribute> mapping;

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }

    public Map<String, ProductAttribute> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, ProductAttribute> mapping) {
        this.mapping = mapping;
    }
}
