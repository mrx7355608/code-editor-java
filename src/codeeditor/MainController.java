/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codeeditor;

import editor.EditorController;
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
    
}
