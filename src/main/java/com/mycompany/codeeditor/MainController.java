/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.codeeditor;

import com.mycompany.core.Node;
import com.mycompany.core.Stack;
import com.mycompany.core.UndoRedoManager;
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
    private final UndoRedoManager undoRedoHandler = UndoRedoManager.getInstance();
    private final Stack undoStack = undoRedoHandler.getUndoStack();
    private final Stack redoStack = undoRedoHandler.getRedoStack();

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
        Node topNode = this.undoStack.pop();
        if (topNode == null) {
            return;
        }
        
        this.redoStack.push(topNode.data);
        
        String newData = this.undoStack.peek().data;

        if (newData == null) {
            this.editorController.getModel().setCode("");
            this.editorController.updateUI();
        } else {
            this.editorController.getModel().setCode(newData);
            this.editorController.updateUI();
        }
    }

    public void redo() {
        Node topNode = this.redoStack.pop();
        if (topNode == null) {
            return;
        }
        
        this.undoStack.push(topNode.data);
        
        String newData = this.redoStack.peek().data;
        
        if (newData == null) {
            this.editorController.getModel().setCode("");
            this.editorController.updateUI();
        } else {
            this.editorController.getModel().setCode(newData);
            this.editorController.updateUI();
        }
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
