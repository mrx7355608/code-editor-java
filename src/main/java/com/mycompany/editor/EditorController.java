/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.editor;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import com.mycompany.syntax_highlighter.SyntaxHighlightController;
import com.mycompany.themes.Theme;

/**
 * EditorController manages interactions between user and the code editor.
 * It allows EditorModel and EditorView to communicate with each other so that
 * code editor can work like a charm.
 * 
 * @author bugsbunny
 */

public class EditorController {

    private final EditorView view;
    private final EditorModel model;
    private final SyntaxHighlightController syntaxHighlighter;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ConcurrentHashMap<Object, Future<?>> delayedMap = new ConcurrentHashMap<>();

    public EditorController(EditorView view, EditorModel model) {
        this.view = view;
        this.model = model;
        this.syntaxHighlighter = new SyntaxHighlightController(view.getTextPane());
        this.attachKeylistenerOnView();
    }

    private void attachKeylistenerOnView() {
        this.view.getTextPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleLineNumbers(e);
                
                final Future<?> prev = delayedMap.put("test", scheduler.schedule(() -> {
                    try {
                        model.setCode(view.getEditorContent());
                        model.pushToUndoStack(view.getEditorContent());
//                        syntaxHighlighter.highlight();
                    } finally {
                        delayedMap.remove("test");
                    }
                }, 300, TimeUnit.MILLISECONDS));

                if (prev != null) {
                    prev.cancel(true);
                }
            }
        });

    }

    private void handleLineNumbers(KeyEvent e) {
        if (isEnterPressed(e)) {
            model.increamentLines();
            view.updateLineNumbers();
        }
        else if (isSpacePressed(e)) {
            int linesInTextPane = view.getTextPane().getText().split("\n").length;
            if (linesInTextPane < model.getLineNumbers()) {
                model.decreamentLines();
                view.updateLineNumbers();
            }
        }
    }

    public void updateUI() {
        String code = this.model.getCode();
        this.view.getTextPane().setText(code);
//        this.syntaxHighlighter.highlight();
    }
    
    public void cut() {
        this.view.getTextPane().cut();
    }
    
    public void copy() {
        this.view.getTextPane().copy();
    }
    
    public void paste() {
        this.view.getTextPane().paste();
    }
    
    public void undo() {
        this.model.performUndo();
        this.updateUI();
    }
    
    public void redo() {
        this.model.performRedo();
        this.updateUI();
    }

    public EditorModel getModel() {
        return this.model;
    }
    
    private boolean isSpacePressed(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
    }
    
    private boolean isEnterPressed(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_ENTER;
    }
}
