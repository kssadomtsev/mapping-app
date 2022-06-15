package com.example;

import com.example.service.CommandLineService;


/**
 * Serves for running Mapping application
 */
public class App {

    /**
     * Runs the application
     */
    private static void runApp(String[] args) {
        if (args.length != 2) {
            System.out.println("Missed source and output filename arguments");
        } else {
            CommandLineService commandLineService = CommandLineService.getInstance();
            commandLineService.initCommandLineService(args[0], args[1]);
            commandLineService.start();
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        runApp(args);
    }
}
