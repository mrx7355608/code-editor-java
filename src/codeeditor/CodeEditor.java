package codeeditor;

import editor.EditorController;
import editor.EditorFile;
import editor.EditorModel;
import editor.EditorView;
import file_handling.FileController;
import file_handling.FileView;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import keyboard_shortcuts.KeyboardShortcuts;

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
        EditorView view = new EditorView();
        EditorFile newFile = new EditorFile();
        model.setFile(newFile);
        EditorController editorController = new EditorController(view, model);
        super.add(view, BorderLayout.CENTER);

        // FileIO setup
        FileView fileView = new FileView();
        FileController fileController = new FileController(fileView);

        // Main controller setup
        MainController mainController = new MainController(editorController, fileController);

        // Keyboard shortcuts setup
        super.addKeyListener(new KeyboardShortcuts(mainController));

        // JMenu Setup
        Menu menu = new Menu(mainController);
        super.setJMenuBar(menu);

        super.setFocusable(true);
        super.requestFocusInWindow();
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
