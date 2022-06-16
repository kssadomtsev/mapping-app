package com.example.service;

import com.example.exception.MappingException;
import com.example.model.Product;
import com.example.model.ProductAttribute;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class MappingServiceTest {
    private MappingService mappingService;

    @Before
    public void setUp() {
        mappingService = new MappingService();
    }

    @Test
    public void processMapping() throws Exception {
        List<String> productList = List.of("CVCD", "SDFD", "DDDF", "SDFD");
        Map<String, ProductAttribute> mapping = Map.ofEntries(
                Map.entry("CVCD", new ProductAttribute(1, "X")),
                Map.entry("SDFD", new ProductAttribute(2, "Z")),
                Map.entry("DDDF", new ProductAttribute(1))
        );
        List<Product> expected = List.of(new Product(1, "X", 1),
                new Product(1, 1),
                new Product(2, "Z", 2));
        List<Product> products = mappingService.processMapping(productList, mapping);
        Assert.assertEquals(expected, products);
    }

    @Test(expected = MappingException.class)
    public void processMappingWithIncorrectData_expectMappingException() throws Exception {
        List<String> productList = List.of("CVCD", "SDFD", "DDDF", "SDFD");
        Map<String, ProductAttribute> mapping = Map.ofEntries(
                Map.entry("CVCD", new ProductAttribute(1, "X")),
                Map.entry("DDDF", new ProductAttribute(1))
        );
        mappingService.processMapping(productList, mapping);
    }

    @Test
    public void countProducts(){
        List<String> productList = List.of("CVCD", "SDFD", "DDDF", "SDFD");
        Map<String, Integer> expected = Map.ofEntries(
                Map.entry("CVCD", 1),
                Map.entry("SDFD", 2),
                Map.entry("DDDF", 1)
        );
        Map<String, Integer> productCount = mappingService.countProducts(productList);
        Assert.assertEquals(expected, productCount);


    }
}