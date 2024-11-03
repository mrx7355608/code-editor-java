
package views;

import controllers.ConsoleController;
import controllers.EditorController;
import event_handlers.ConsoleMouseListener;
import event_handlers.EditorKeyListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;


public class MainView extends JFrame {
    public MainView() {
        super.setSize(800, 700);
        super.setTitle("Hello");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        
        this.setupEditor();
        this.setupConsole();
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
        EditorController controller = new EditorController(view);
        EditorKeyListener listener = new EditorKeyListener(controller);
        
        view.attachKeyListener(listener);
        super.add(view, BorderLayout.NORTH);
    }
}
