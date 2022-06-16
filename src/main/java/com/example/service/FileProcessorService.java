package com.example.service;


import com.example.model.ProductFileDto;
import com.example.model.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Provides file relation operations such as parsing source data
 * file and writing calculation result into output file
 */
public class FileProcessorService {
    private final String sourceFilePath;
    private final String outputFilePath;
    private final ObjectMapper mapper;

    public FileProcessorService(String sourceFilePath, String outputFilePath) {
        this.sourceFilePath = sourceFilePath;
        this.outputFilePath = outputFilePath;
        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public ProductFileDto readSourceFile() {
        ProductFileDto result = null;
        try {
            result = mapper.readValue(Paths.get(sourceFilePath).toFile(), ProductFileDto.class);
        } catch (IOException e) {
            System.out.printf("IO error was occurred while access to file %s, error message: %s%n", this.sourceFilePath, e.getMessage());
        }
        return result;
    }

    public void writeToOutputFile(List<Product> list){
        try {
            mapper.writeValue(Paths.get(outputFilePath).toFile(), list);
        } catch (IOException e) {
            System.out.printf("IO error was occurred while access to file %s, error message: %s%n", this.outputFilePath, e.getMessage());
        }
    }
}
