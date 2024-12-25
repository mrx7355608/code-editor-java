package codeeditor;

import editor.EditorView;
import editor.EditorController;
import editor.EditorKeyListener;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import editor.EditorModel;

public class MainView extends JFrame {

    EditorController editorController;

    public MainView() {
        this.useSystemLookAndFeel();

        super.setSize(1000, 700);
        super.setTitle("Hello");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);

        this.setupEditor();
    }

    private void useSystemLookAndFeel() {
        // Adjusts editor styles according to the system styles, meaning
        // Window type buttons, textbox etc on Windows machines.
        // Linux type stuff on Linux machines.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupEditor() {
        EditorModel model = new EditorModel();
        EditorView view = new EditorView(model);
        editorController = new EditorController(view, model);
        EditorKeyListener listener = new EditorKeyListener(editorController);

        view.attachKeyListener(listener);
        super.add(view, BorderLayout.NORTH);
    }

}
