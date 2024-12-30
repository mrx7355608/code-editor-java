/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codeeditor;

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

    public MainController(EditorController editorController, FileController fileController) {
        this.editorController = editorController;
        this.fileController = fileController;
    }
    
    public void newFile() {
        EditorFile newFile = this.fileController.createNewFile();        
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
    
}
