package com.mycompany.file_handling;

import com.mycompany.editor.EditorFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileController {

    private final FileView view;

    public FileController(FileView view) {
        this.view = view;
    }

    public EditorFile saveFile(EditorFile file) {
        String path = file.getPath();
        String newData = file.getCode();
        
        // 1. If file exists, save the content
        if (this.fileExists(path)) {
            System.out.println("Saving existing file");
            this.writeToFile(path, newData);
            file.setSaved(true);
            return file;
        }

        System.out.println("Creating new file...");
        
        // 2. Otherwise, ask user where to save new file and save content in it
        File selectedFile = this.view.showSaveFileDialogueBox();
        if (selectedFile != null) {
            String selectedFilePath = selectedFile.getAbsolutePath();
            String name = this.extractNameFromPath(selectedFilePath);
            this.writeToFile(selectedFilePath, newData);
            return new EditorFile(name, selectedFilePath, newData, true);
        }

        return file;

    }

    public EditorFile openFile() {
        try {
            // Show Open file UI
            File selectedFile = this.view.showOpenFileDialogueBox();
            if (selectedFile == null) {
                return null;
            }

            // Extract file details
            String path = selectedFile.getAbsolutePath();
            String name = selectedFile.getName();
            
            // Create a file reader
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder fileData = new StringBuilder();

            // Read the file content
            String line = reader.readLine();
            while (line != null) {
                fileData.append(line).append('\n');
                line = reader.readLine();
            }
            
            // Create an EditorFile instnace for ease of handling
            EditorFile file = new EditorFile(name, path, fileData.toString(), true);
            return file;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    private boolean fileExists(String path) {
        if (path == null) {
            return false;
        } else if (!Files.exists(Paths.get(path))) {
            return false;
        }

        return true;
    }

    private void writeToFile(String path, String newData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(newData);
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String extractNameFromPath(String path) {
        String[] splittedName = path.split("\\\\");
        return splittedName[splittedName.length - 1];
    }
}
