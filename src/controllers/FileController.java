/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import models.FileModel;
import views.FileView;

/**
 *
 * @author ghost
 */
public class FileController {
    private final FileModel model;
    private final FileView view;

    public FileController(FileModel model, FileView view) {
        this.model = model;
        this.view = view;
        this.setupFileMenuAction();
    }
    
    public void saveFile() {}
    public Boolean showFileDialogBox() { return false; }
    
    public String getFilePath() {
        return this.model.getFilePath();
    }
    
    private void setupFileMenuAction() {
        this.view.openItem.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            System.out.println("hello");
        });
    }
}
