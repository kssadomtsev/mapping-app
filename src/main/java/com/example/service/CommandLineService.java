package com.example.service;


import com.example.exception.MappingException;

public class CommandLineService {
    private MappingService mappingService;
    private static final CommandLineService singletonInstance = new CommandLineService();


    private CommandLineService() {
    }

    /**
     * Get only one singleton instance
     */
    public static CommandLineService getInstance() {
        return singletonInstance;
    }

    public void initCommandLineService(String sourceFilePath, String outputFilePath) {
        this.mappingService = new MappingService(sourceFilePath, outputFilePath);
    }

    public void start(){
        try {
            mappingService.map();
        } catch (MappingException e) {
            System.out.println(e.getMessage());
        }
    }
}
