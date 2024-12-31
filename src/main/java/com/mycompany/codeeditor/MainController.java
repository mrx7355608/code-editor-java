/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.codeeditor;

import com.mycompany.editor.EditorController;
import com.mycompany.editor.EditorFile;
import com.mycompany.file_handling.FileController;

/**
 *
 * @author bugsbunny
 */
public class MainController {

    public EditorController editorController;
    public FileController fileController;

    public MainController(EditorController editorController, FileController fileController) {
        this.editorController = editorController;
        this.fileController = fileController;
    }

    public void newFile() {
        EditorFile newFile = new EditorFile();
        this.editorController.getModel().setFile(newFile);
        this.editorController.updateUI();
    }

    public void saveFile() {
        EditorFile file = this.editorController.getModel().getFile();
        EditorFile savedFile = this.fileController.saveFile(file);
        this.editorController.getModel().setFile(savedFile);
    }

    public void openFile() {
        EditorFile file = this.fileController.openFile();
        this.editorController.getModel().setFile(file);
        this.editorController.updateUI();
    }

    public void undo() {
        this.editorController.undo();
    }

    public void redo() {
        this.editorController.redo();
    }
    
    public void cut() {
        this.editorController.cut();
    }
    
    public void copy() {
        this.editorController.copy();
    }
    
    public void paste() {
        this.editorController.paste();
    }

}
