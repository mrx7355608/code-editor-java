package com.mycompany.codeeditor;

import com.mycompany.editor.EditorController;
import com.mycompany.editor.EditorFile;
import com.mycompany.editor.EditorModel;
import com.mycompany.editor.EditorView;
import com.mycompany.file_handling.FileController;
import com.mycompany.file_handling.FileView;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.mycompany.keyboard_shortcuts.KeyboardShortcuts;
import com.mycompany.themes.ThemeManager;
import java.awt.Color;
import java.util.HashMap;

public class CodeEditor extends JFrame {

    public CodeEditor() {
        this.useSystemLookAndFeel();

        // JFrame setup
        super.setTitle("GenzEditor");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setSize(800, 600);
        super.setLocationRelativeTo(null);
//        super.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Editor setup
        EditorModel model = new EditorModel();
        EditorView view = new EditorView(model.getLineNumbersModel());
        EditorFile newFile = new EditorFile();
        model.setFile(newFile);
        EditorController editorController = new EditorController(view, model);

        // FileIO setup
        FileView fileView = new FileView();
        FileController fileController = new FileController(fileView);

        // Main controller setup
        MainController mainController = new MainController(editorController, fileController);

        // Keyboard shortcuts setup
        KeyboardShortcuts s = new KeyboardShortcuts(view.getTextPane(), mainController);

        // JMenu Setup
        MenuBar menu = new MenuBar(mainController);

        // Load and apply theme
        HashMap<String, Color> theme = ThemeManager.diamonHead();
        editorController.applyTheme(theme);
        menu.applyTheme(theme);

        super.add(view, BorderLayout.CENTER);
        super.setJMenuBar(menu);
    }

    private void useSystemLookAndFeel() {
        // Adjusts editor looks according to the system, meaning
        // buttons, textbox etc will appear as they are in currently running
        // operating system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("[ERROR] " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CodeEditor().setVisible(true);
    }

}
