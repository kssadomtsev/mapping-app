package com.example.service;

import com.example.exception.MappingException;
import com.example.model.Product;
import com.example.model.ProductAttribute;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MappingServiceTest {
    private MappingService mappingService;

    @Before
    public void setUp() {
        mappingService = new MappingService();
    }

    @Test
    public void mapProducts() throws Exception {
        List<String> productList = List.of("CVCD", "SDFD", "DDDF", "SDFD");
        Map<String, ProductAttribute> mapping = Map.ofEntries(
                Map.entry("CVCD", new ProductAttribute(1, "X")),
                Map.entry("SDFD", new ProductAttribute(2, "Z")),
                Map.entry("DDDF", new ProductAttribute(1))
        );
        List<Product> expected = List.of(new Product(1, "X", 1),
                new Product(1, 1),
                new Product(2, "Z", 2));
        List<Product> products = mappingService.mapProducts(productList, mapping);
        Assert.assertEquals(expected, products);
    }

    @Test(expected = MappingException.class)
    public void mapProductsWithIncorrectData_expectMappingException() throws Exception {
        List<String> productList = List.of("CVCD", "SDFD", "DDDF", "SDFD");
        Map<String, ProductAttribute> mapping = Map.ofEntries(
                Map.entry("CVCD", new ProductAttribute(1, "X")),
                Map.entry("DDDF", new ProductAttribute(1))
        );
        mappingService.mapProducts(productList, mapping);
    }
}