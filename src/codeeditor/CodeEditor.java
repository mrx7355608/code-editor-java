package codeeditor;

import editor.KeyboardListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CodeEditor extends JFrame {

    public CodeEditor() {
        this.useSystemLookAndFeel();
        
        // JFrame setup
        super.setTitle("GenzEditor");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Main Controller setup
        MainModel model = new MainModel();
        MainView view = new MainView();
        MainController mainController = new MainController(view, model);
        view.attachKeyListener(new KeyboardListener(mainController));
        super.add(view, BorderLayout.CENTER);
        
        // JMenu Setup
        Menu menu = new Menu(mainController);
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
