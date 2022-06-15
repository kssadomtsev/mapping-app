package com.example.service;

import com.example.dto.ProductFileDto;
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
        List<Product> products = mapProducts(productFileDto.getProductList(), productFileDto.getMapping());
        fileProcessorService.writeToOutputFile(products);
    }

    public List<Product> mapProducts(List<String> productList, Map<String, ProductAttribute> mapping) throws MappingException {
        Map<String, Integer> productCount = new HashMap<>();
        for (String produceCode : productList) {
            productCount.merge(produceCode, 1, Integer::sum);
        }
        List<Product> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : productCount.entrySet()) {
            ProductAttribute productAttribute = mapping.get(entry.getKey());
            if (Objects.isNull(productAttribute)) {
                throw new MappingException(MAPPING_ERROR_MESSAGE);
            }
            result.add(new Product(productAttribute.getVersion(), productAttribute.getEdition(), entry.getValue()));
        }
        return result.stream().sorted(Comparator.comparing(Product::getVersion)).collect(Collectors.toList());
    }
}
