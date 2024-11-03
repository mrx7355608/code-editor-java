
package views;

import controllers.ConsoleController;
import event_handlers.ConsoleMouseListener;
import javax.swing.JFrame;


public class MainView extends JFrame {
    public MainView() {
        super.setSize(800, 700);
        super.setTitle("Hello");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setupConsole();
    }
    
    private void setupConsole() {
        ConsoleView view = new ConsoleView();
        ConsoleController controller = new ConsoleController(view);
        ConsoleMouseListener mouseListener = new ConsoleMouseListener(controller);
        
        view.addClickHandler(mouseListener);
        super.add(view);
    }
}
