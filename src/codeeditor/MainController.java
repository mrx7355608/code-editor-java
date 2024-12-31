/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codeeditor;

import core.Node;
import core.Stack;
import core.UndoRedoManager;
import editor.EditorController;
import editor.EditorFile;
import file_handling.FileController;

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
        this.editorController.setFile(newFile);
        this.editorController.updateUI();
    }

    public void saveFile() {
        EditorFile file = this.editorController.getFile();
        EditorFile savedFile = this.fileController.saveFile(file);
        this.editorController.setFile(savedFile);
    }

    public void openFile() {
        EditorFile file = this.fileController.openFile();
        this.editorController.setFile(file);
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
