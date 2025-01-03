/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.codeeditor;

import com.mycompany.console.ConsoleController;
import com.mycompany.editor.EditorController;
import com.mycompany.editor.EditorFile;
import com.mycompany.file_handling.FileController;
import com.mycompany.search.SearchController;
import javax.swing.JTextPane;

/**
 *
 * @author bugsbunny
 */
public class MainController {

    public EditorController editorController;
    public FileController fileController;
    public ConsoleController consoleController;
    public SearchController searchController;

    public MainController(
            EditorController editorController,
            FileController fileController,
            ConsoleController consoleController,
            SearchController searchController
    ) {
        this.editorController = editorController;
        this.fileController = fileController;
        this.consoleController = consoleController;
        this.searchController = searchController;
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
    
    public void compile() {
        System.out.println("compiling...");
        String filepath = this.editorController.getModel().getFile().getPath();
        if (filepath == null) {
            System.out.println("filepath missing");
            return;
        }
        this.consoleController.compileCode(filepath);
    }
    
    public void compileAndRun() {
        System.out.println("compile and run...");
        String filepath = this.editorController.getModel().getFile().getPath();
        if (filepath == null) {
            System.out.println("filepath missing");
            return;
        }
        this.consoleController.runCode(filepath);
    }
    
    public void search() {
        this.searchController.showSearchView();
    }

}
