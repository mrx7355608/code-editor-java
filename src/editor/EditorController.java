/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editor;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import syntax_highlighter.SyntaxHighlightController;
import themes.Theme;


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
        this.applyTheme();
        this.attachKeylistenerOnView();
    }

    private void attachKeylistenerOnView() {
        this.view.getTextPane().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 1. After a certain delay, add the buffer to EditorModel's "code" field
                final Future<?> prev = delayedMap.put("test", scheduler.schedule(() -> {
                    try {
                        updateCode();
                        syntaxHighlighter.highlight();
                    } finally {
                        delayedMap.remove("test");
                    }
                }, 300, TimeUnit.MILLISECONDS));

                if (prev != null) {
                    prev.cancel(true);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

    }

    private void applyTheme() {
        HashMap<String, Color> theme = Theme.hope();
        theme.forEach((type, color) -> {
            JTextPane textPane = this.view.getTextPane();
            Style style = textPane.addStyle(type, null);
            StyleConstants.setForeground(style, color);

        });
    }

    public void updateCode() {
        String editorContent = this.view.getEditorContent();
        this.model.setCode(editorContent);
    }

    public String getCode() {
        return this.model.getCode();
    }
}
