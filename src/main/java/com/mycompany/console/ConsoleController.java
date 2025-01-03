/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.console;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author bugsbunny
 */
public class ConsoleController {

    private final ConsoleView view;

    public ConsoleController(ConsoleView view) {
        this.view = view;
    }

    public void applyTheme(HashMap<String, Color> theme) {
        this.view.getTextPane().setBackground(theme.get("BACKGROUND"));
        this.view.getTextPane().setForeground(theme.get("FOREGROUND"));
    }

    public void compileCode(String filepath) {
        ProcessBuilder processBuilder = new ProcessBuilder("javac", filepath);

        try {
            // Start the process
            Process process = processBuilder.start();

            // Capture the compile error
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorMessage = new StringBuilder();

            // Read the output line by line
            String line;
            while ((line = errorReader.readLine()) != null) {
                errorMessage.append(line).append("\n");
            }

            // Wait for the process to exit and capture the exit code
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                errorMessage.append("Process exited with code: ").append(String.valueOf(exitCode));
                this.view.getTextPane().setForeground(Color.red);
                this.view.getTextPane().setText(errorMessage.toString());
            } else {
                this.view.getTextPane().setForeground(Color.GREEN); // not working for some reasons
                this.view.getTextPane().setText("COMPILED SUCCESSFULLY");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void runCode(String filepath) {
        ProcessBuilder processBuilder = new ProcessBuilder("java", filepath);
        
        try {
            // Start the process
            Process process = processBuilder.start();

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder errorMessage = new StringBuilder();
            StringBuilder inputMessage = new StringBuilder();

            // Read the error line by line
            String line;
            while ((line = errorReader.readLine()) != null) {
                errorMessage.append(line).append("\n");
            }
            
            // Read the output line by line
            String line2;
            while ((line2 = inputReader.readLine()) != null) {
                inputMessage.append(line2).append("\n");
            }

            // Wait for the process to exit and capture the exit code
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                errorMessage.append("Process exited with code: ").append(String.valueOf(exitCode));
                this.view.getTextPane().setForeground(Color.red);
                this.view.getTextPane().setText(errorMessage.toString());
            } else {
                this.view.getTextPane().setText(inputMessage.toString());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
