package views;

import controllers.ConsoleController;
import controllers.EditorController;
import controllers.MenuController;
import event_handlers.ConsoleMouseListener;
import event_handlers.EditorKeyListener;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import models.EditorModel;

public class MainView extends JFrame {
    
    EditorController editorController;
    ConsoleController consoleController;
    

    public MainView() {
        this.useSystemLookAndFeel();

        super.setSize(800, 700);
        super.setTitle("Hello");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);
        
        this.setupEditor();
        this.setupConsole();
        this.setupMenuBar();
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
        consoleController = new ConsoleController(view);
        ConsoleMouseListener mouseListener = new ConsoleMouseListener(consoleController, editorController);

        view.addClickHandler(mouseListener);
        super.add(view, BorderLayout.SOUTH);
    }

    private void setupEditor() {
        EditorModel model = new EditorModel();
        EditorView view = new EditorView(model);
        editorController = new EditorController(view, model);
        EditorKeyListener listener = new EditorKeyListener(editorController);

        view.attachKeyListener(listener);
        super.add(view, BorderLayout.NORTH);
    }
    
    private void setupMenuBar() {
        MenuView view = new MenuView();
        MenuController controller = new MenuController(editorController, consoleController, view);
        controller.addFileMenuActionHandlers();
        super.setJMenuBar(view);
    }
}
