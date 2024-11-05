/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import views.ConsoleView;

/**
 *
 * @author ghost
 */
public class ConsoleController {
    private final ConsoleView view;
    
    public ConsoleController(ConsoleView view) {
        this.view = view;
    }
    
    private boolean compileCode(String filePath) {
        try {
            // Create process
            ProcessBuilder compilationProcessBuilder = new ProcessBuilder("javac", filePath);
            
            // Start compile process
            Process compileProcess = compilationProcessBuilder.start();

            // Create error stream to catch errors
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
            StringBuilder errorMessage = new StringBuilder();
            String line;
            while ((line = errorReader.readLine()) != null) {
                errorMessage.append(line).append("\n");
            }
            
            // Wait for compile process to finish
            compileProcess.waitFor();

            // If there's any compile error, display it on console view
            if (errorMessage.length() > 0) {
                this.view.displayText("Compilation Errors:\n" + errorMessage.toString());
                return false;
            }
            
            return true;
        } catch (IOException | InterruptedException ex) {
            System.out.println("[ERROR]" + ex.getMessage());
            return false;
        }
    }
    
    private void runCode(String filePath) {
        try {
            ProcessBuilder runProcessBuilder = new ProcessBuilder("java", filePath);
            Process runProcess = runProcessBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line2;
            while ((line2 = reader.readLine()) != null) {
                output.append(line2).append("\n");
            }
            runProcess.waitFor();

            this.view.displayText("Output:\n" + output.toString());

        } catch (IOException | InterruptedException ex) {
            System.out.println("[ERROR]" + ex.getMessage());
        }
    }
    
    public void executeCode() {
        String filePath = "src/views/Test.java";
        if (this.compileCode(filePath)) {
            this.runCode(filePath);
        }
    }

}
