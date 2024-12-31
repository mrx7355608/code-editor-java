
package com.mycompany.keyboard_shortcuts;

import com.mycompany.codeeditor.MainController;
import javax.swing.*;
import java.awt.event.*;

public class KeyboardShortcuts {
    private final JTextPane editor;
    private final MainController mainController;
    
    public KeyboardShortcuts(JTextPane editor, MainController controller) {
        this.editor = editor;
        this.mainController = controller;
        this.initializeShortcuts();
    }
    

    private void initializeShortcuts() {
        InputMap inputMap = editor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = editor.getActionMap();

        // Defining keystrokes
        KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
        KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        KeyStroke ctrlZ = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);
        KeyStroke ctrlR = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        
        // Binding shortcuts to action names
        inputMap.put(ctrlN, "new");
        inputMap.put(ctrlO, "open");
        inputMap.put(ctrlS, "save");
        inputMap.put(ctrlZ, "undo");
        inputMap.put(ctrlR, "redo");
        
        // Binding action names to their action
        actionMap.put("new", new ShortcutAction(mainController::newFile));
        actionMap.put("open", new ShortcutAction(mainController::openFile));
        actionMap.put("save", new ShortcutAction(mainController::saveFile));
        actionMap.put("undo", new ShortcutAction(mainController::undo));
        actionMap.put("redo", new ShortcutAction(mainController::redo));
    }
}