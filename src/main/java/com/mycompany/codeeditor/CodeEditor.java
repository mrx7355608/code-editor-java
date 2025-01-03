package com.mycompany.codeeditor;

import com.mycompany.console.ConsoleController;
import com.mycompany.console.ConsoleView;
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
import com.mycompany.search.SearchController;
import com.mycompany.search.SearchView;
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
        EditorModel editorModel = new EditorModel();
        EditorView editorView = new EditorView(editorModel.getLineNumbersModel());
        EditorFile newFile = new EditorFile();
        editorModel.setFile(newFile);
        EditorController editorController = new EditorController(editorView, editorModel);

        // FileIO setup
        FileView fileView = new FileView();
        FileController fileController = new FileController(fileView);

        // Console Setup
        ConsoleView consoleView = new ConsoleView();
        ConsoleController consoleController = new ConsoleController(consoleView);

        // Search setup
        SearchView searchView = new SearchView(editorView.getTextPane());
        SearchController searchController = new SearchController(searchView);

        // Main controller setup
        MainController mainController = new MainController(
                editorController, 
                fileController, 
                consoleController, 
                searchController
        );

        // Keyboard shortcuts setup
        KeyboardShortcuts s = new KeyboardShortcuts(editorView.getTextPane(), mainController);

        // JMenu Setup
        MenuBar menu = new MenuBar(mainController);

        // Load and apply theme
        HashMap<String, Color> theme = ThemeManager.diamonHead();
        menu.applyTheme(theme);
        editorController.applyTheme(theme);
        consoleController.applyTheme(theme);
        searchController.applyTheme(theme);

        // Split pane to make window adjustable
        SplitPane splitPane = new SplitPane(editorView, consoleView);

        super.add(splitPane, BorderLayout.CENTER);
        super.add(searchView, BorderLayout.SOUTH);
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
