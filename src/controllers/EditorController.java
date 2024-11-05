/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

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
    
    public void createFile() {}
    public void openFile() {}
    
    public String getCode() {
        return this.model.getCode();
    }
    
}
