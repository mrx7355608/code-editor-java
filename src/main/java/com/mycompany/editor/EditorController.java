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
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import com.mycompany.syntax_highlighter.SyntaxHighlightController;
import javax.swing.Action;
import javax.swing.JList;
import javax.swing.JTextPane;

/**
 * EditorController manages interactions between user and the code editor. It
 * allows EditorModel and EditorView to communicate with each other so that code
 * editor can work like a charm.
 *
 * @author bugsbunny
 */
public class EditorController {

    private final EditorView view;
    private final EditorModel model;
    private final SyntaxHighlightController syntaxHighlighter;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ConcurrentHashMap<Object, Future<?>> delayedMap = new ConcurrentHashMap<>();

    public EditorController(EditorView view, EditorModel model, SyntaxHighlightController syntaxHighlighter) {
        this.view = view;
        this.model = model;
        this.syntaxHighlighter = syntaxHighlighter;
        this.attachKeylistenerOnView();
    }

    public void applyTheme(HashMap<String, Color> theme) {
        // Apply theme to textpane's background and foreground colors
        this.view.getTextPane().setBackground(theme.get("BACKGROUND"));
        this.view.getTextPane().setForeground(theme.get("FOREGROUND"));

        // Apply theme to textpane's cursor
        this.view.getTextPane().setCaretColor(theme.get("CURSOR"));

        // Apply theme to text pane for syntax highlighting
        theme.forEach((category, color) -> {
            Style style = this.view.getTextPane().addStyle(category, null);
            StyleConstants.setForeground(style, color);
        });

        // Apply theme to line numbers
        JList lineNumbersView = this.view.getLineNumbersView();
        lineNumbersView.setBackground(theme.get("BACKGROUND"));
        lineNumbersView.setForeground(theme.get("FOREGROUND"));
        this.view.setLineNumbersViewBorderColor(theme.get("BORDER"));
    }

    private void handleLineNumbers(KeyEvent e) {
        if (isEnterPressed(e)) {
            model.increamentLines();
            view.reRenderLineNumbers();
        } else if (isSpacePressed(e) || isDelPressed(e)) {
            int linesInTextPane = view.getTextPane().getText().split("\n").length;
            if (linesInTextPane < model.getLineNumbers()) {
                model.decreamentLines();
                view.reRenderLineNumbers();
            }
        }
    }

    private void attachKeylistenerOnView() {
        this.view.getTextPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown()) {
                    return;
                }

                handleLineNumbers(e);

                final Future<?> prev = delayedMap.put("test", scheduler.schedule(() -> {
                    try {
                        model.setCode(view.getEditorContent());
                        model.pushToUndoStack(view.getEditorContent());
                        syntaxHighlighter.highlight();
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

    public JTextPane getTextPane() {
        return this.view.getTextPane();
    }

    private boolean isSpacePressed(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
    }
    
    private boolean isDelPressed(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_DELETE;
    }

    private boolean isEnterPressed(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_ENTER;
    }

    public void customPasteAction() {
        // Get pasted code from textPane
        String code = view.getEditorContent();
        
        // If number of lines in textPane are greater than lines in EditorModel
        // then increase the lines in model and line numbers view
        int linesInTextPane = code.split("\n").length;
        if (linesInTextPane > model.getLineNumbers()) {
            model.increaseLinesOnPaste(linesInTextPane);
            view.reRenderLineNumbers();
        }
        
        // Update code inside model
        this.model.setCode(code);
        this.syntaxHighlighter.highlight();
    }

    public void bindCustomPasteAction() {
        JTextPane textPane = this.view.getTextPane();
        Action action = textPane.getActionMap().get("paste-from-clipboard");
        textPane.getActionMap().put("paste-from-clipboard",
            new PasteAction(action, this::customPasteAction)
        );
    }
}
