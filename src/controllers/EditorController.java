/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    public void saveFile() {}
    
    public void createFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showDialog(null, "Create file") == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            System.out.println(path);
        }
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
