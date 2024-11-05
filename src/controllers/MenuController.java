/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import views.MenuView;

/**
 *
 * @author ghost
 */
public class MenuController {
    private final EditorController editorController;
    private final ConsoleController consoleController;
    private final MenuView view;

    public MenuController(EditorController editorController, ConsoleController consoleController, MenuView view) {
        this.editorController = editorController;
        this.consoleController = consoleController;
        this.view = view;
    }
    
    public void addFileMenuActionHandlers() {
        this.view.newFileItem.addActionListener((ActionEvent e) -> {
            this.editorController.createFile();
        });
        this.view.openFileItem.addActionListener((ActionEvent e) -> {
            this.editorController.openFile();
        });
        this.view.runCodeItem.addActionListener((ActionEvent e) -> {
            this.consoleController.executeCode(this.editorController.getFilePath());
        });
    }
    
}
