/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editor;

import editor.EditorController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import syntax_highlighter.SyntaxHighlighter;

/**
 *
 * @author ghost
 */
public class EditorKeyListener implements KeyListener {

    private final EditorController controller;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ConcurrentHashMap<Object, Future<?>> delayedMap = new ConcurrentHashMap<>();
    private final SyntaxHighlighter syntaxHighlighter;

    public EditorKeyListener(EditorController controller) {
        this.controller = controller;
        this.syntaxHighlighter = new SyntaxHighlighter();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        // 1. After a certain delay, add the buffer to EditorModel's "code" field
        final Future<?> prev = delayedMap.put("test", scheduler.schedule(() -> {
            try {
                this.controller.updateCode();
                this.syntaxHighlighter.highlight(controller.getCode(), controller.getView());
            } finally {
                delayedMap.remove("test");
            }
        }, 300, TimeUnit.MILLISECONDS));

        if (prev != null) {
            prev.cancel(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
