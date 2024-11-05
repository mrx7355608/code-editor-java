package views;

import controllers.ConsoleController;
import controllers.EditorController;
import controllers.FileController;
import event_handlers.ConsoleMouseListener;
import event_handlers.EditorKeyListener;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import models.EditorModel;
import models.FileModel;

public class MainView extends JFrame {

    public MainView() {
        this.useSystemLookAndFeel();

        super.setSize(800, 700);
        super.setTitle("Hello");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());

        this.setupFileIO();
        this.setupEditor();
        this.setupConsole();
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

    private void setupConsole() {
        ConsoleView view = new ConsoleView();
        ConsoleController controller = new ConsoleController(view);
        ConsoleMouseListener mouseListener = new ConsoleMouseListener(controller);

        view.addClickHandler(mouseListener);
        super.add(view, BorderLayout.SOUTH);
    }

    private void setupEditor() {
        EditorView view = new EditorView();
        EditorModel model = new EditorModel();
        EditorController controller = new EditorController(view, model);
        EditorKeyListener listener = new EditorKeyListener(controller);

        view.attachKeyListener(listener);
        super.add(view, BorderLayout.NORTH);
    }

    private void setupFileIO() {
        FileView view = new FileView();
        FileModel model = new FileModel();
        FileController controller = new FileController(model, view);
        super.setJMenuBar(view);
    }
}
