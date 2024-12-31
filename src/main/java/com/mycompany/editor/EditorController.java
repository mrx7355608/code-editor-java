/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.editor;

import com.mycompany.core.Stack;
import com.mycompany.core.UndoRedoManager;
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

public class EditorController {

    private final EditorView view;
    private final EditorModel model;
    private final SyntaxHighlightController syntaxHighlighter;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ConcurrentHashMap<Object, Future<?>> delayedMap = new ConcurrentHashMap<>();
    private final UndoRedoManager undoRedoHandler = UndoRedoManager.getInstance();
    private final Stack undoStack = undoRedoHandler.getUndoStack();
    private final Stack redoStack = undoRedoHandler.getRedoStack();

    public EditorController(EditorView view, EditorModel model) {
        this.view = view;
        this.model = model;
        this.syntaxHighlighter = new SyntaxHighlightController(view.getTextPane());
        this.applyTheme();
        this.attachKeylistenerOnView();
    }

    private void attachKeylistenerOnView() {
        this.view.getTextPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Increaes / Decreases line numbers
                handleLineNumbers(e);
                
                // Execute certain actions after user stops typing for 300ms
                final Future<?> prev = delayedMap.put("test", scheduler.schedule(() -> {
                    try {
                        String editorContent = view.getEditorContent();
//                        model.setCode(editorContent);
                        syntaxHighlighter.highlight();
                        undoStack.push(editorContent);
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
        if (e.getKeyChar() == '\n') {
            view.increamentLineNumbers();
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            int linesInTextPane = view.getTextPane().getText().split("\n").length;
            if (linesInTextPane < view.getLineNumbers()) {
                view.decreamentLineNumbers();
            }
        }
    }

    private void applyTheme() {
        HashMap<String, Color> theme = Theme.hope();
        theme.forEach((type, color) -> {
            JTextPane textPane = this.view.getTextPane();
            Style style = textPane.addStyle(type, null);
            StyleConstants.setForeground(style, color);

        });
    }

    public void updateUI() {
        String code = this.model.getCode();
        this.view.getTextPane().setText(code);
        this.syntaxHighlighter.highlight();
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

    public EditorModel getModel() {
        return this.model;
    }
}
