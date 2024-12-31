/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

/**
 * Manages undo and redo operations of the code editor
 * 
 * It is a singleton class, meaning that only one object of this
 * class will be shared across the whole application.
 */
public class UndoRedoManager {
    
    private final Stack undoStack;
    private final Stack redoStack;
    private static UndoRedoManager instance;
    
    private UndoRedoManager() {
        this.undoStack = new Stack();
        this.redoStack = new Stack();
    }
    
    public static UndoRedoManager getInstance() {
        if (instance == null) {
            instance = new UndoRedoManager();
        }
        
        return instance;
    }
    
    public Stack getUndoStack() {
        return this.undoStack;
    }
    
    public Stack getRedoStack() {
        return this.redoStack;
    }
}
