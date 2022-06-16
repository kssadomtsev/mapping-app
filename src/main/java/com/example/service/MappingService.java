package com.example.service;

import com.example.model.ProductFileDto;
import com.example.exception.MappingException;
import com.example.model.Product;
import com.example.model.ProductAttribute;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MappingService {
    private FileProcessorService fileProcessorService;

    private static final String MAPPING_ERROR_MESSAGE = "Error has occurred during mapping process";

    public MappingService(String sourceFilePath, String outputFilePath) {
        this.fileProcessorService = new FileProcessorService(sourceFilePath, outputFilePath);
    }

    public MappingService() {
    }

    public void map() throws MappingException {
        ProductFileDto productFileDto = fileProcessorService.readSourceFile();
        List<Product> products = processMapping(productFileDto.getProductList(), productFileDto.getMapping());
        fileProcessorService.writeToOutputFile(products);
    }

    public List<Product> processMapping(List<String> productList, Map<String, ProductAttribute> mapping) throws MappingException {
        Map<String, Integer> productCount = countProducts(productList);
        List<Product> result = mapProducts(mapping, productCount);
        return result.stream().sorted(Comparator.comparing(Product::getVersion)).collect(Collectors.toList());
    }

    private List<Product> mapProducts(Map<String, ProductAttribute> mapping, Map<String, Integer> productCount) throws MappingException {
        List<Product> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : productCount.entrySet()) {
            ProductAttribute productAttribute = mapping.get(entry.getKey());
            if (Objects.isNull(productAttribute)) {
                throw new MappingException(MAPPING_ERROR_MESSAGE);
            }
            result.add(new Product(productAttribute.getVersion(), productAttribute.getEdition(), entry.getValue()));
        }
        return result;
    }

    public Map<String, Integer> countProducts(List<String> productList) {
        Map<String, Integer> productCount = new HashMap<>();
        for (String produceCode : productList) {
            productCount.merge(produceCode, 1, Integer::sum);
        }
        return productCount;
    }
}
