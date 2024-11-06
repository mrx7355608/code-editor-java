/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import models.EditorFile;
import models.EditorModel;
import views.EditorView;

/**
 *
 * @author ghost
 */
public class EditorController {

    private final EditorView view;
    private final EditorModel model;

    public EditorController(EditorView view, EditorModel model) {
        this.view = view;
        this.model = model;
    }

    public void updateCode() {
        // 1. Get text written in the Editor
        String editorContent = this.view.getEditorContent();

        // 2. Update the code inside the editor model
        this.model.setCode(editorContent);
    }

    public void saveFile() {
        // 1. If file is new, then ask user, where to save this file.
        if (this.model.getFile().isNewlyCreated()) {
            System.out.println("Saving new file");
            
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // a. If user cancels the selection, close the file dialogue box
            if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
                chooser.cancelSelection();
                return;
            }
            
            // b. Otherwise, if user selects a directory, get the folder path
            //    and create a file path from it
            String folderPath = chooser.getSelectedFile().getAbsolutePath();
            Path filePath = Path.of(folderPath, this.model.getFileName());

            // c. Create a new file at the selected location and write the
            //    code content to the file path
            try {
                Files.createFile(filePath);
                Files.writeString(filePath, this.model.getCode(), StandardOpenOption.WRITE);
            } catch (IOException ex) {
                Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // 2. Otherwise, the file already exists on the user's system
            //    So, just get the file path and write the code on that location
            
            System.out.println("Saving existing file");
            try {
                Files.writeString(Path.of(this.model.getFilePath()), this.model.getCode(), StandardOpenOption.WRITE);
            } catch (IOException ex) {
                Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        

    }

    public void createFile() {
        EditorFile newFile = new EditorFile();
        this.model.setFile(newFile);
        this.view.update();
    }

    public void openFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String filePath = chooser.getSelectedFile().getAbsolutePath();
            String fileContent = this.loadFileContent(filePath);
            String name = chooser.getSelectedFile().getName();
            this.model.setFile(new EditorFile(name, filePath, fileContent));
            this.view.update();
        }
    }

    private String loadFileContent(String path) {
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(path)));
            return fileContent;
        } catch (IOException ex) {
            Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getCode() {
        return this.model.getCode();
    }

    public String getFilePath() {
        return this.model.getFilePath();
    }

}
