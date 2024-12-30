package codeeditor;

import editor.EditorController;
import editor.EditorModel;
import editor.EditorView;
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
        EditorModel model = new EditorModel();
        EditorView view = new EditorView();
        EditorController mainController = new EditorController(view, model);
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
