package codeeditor;

import editor.KeyboardListener;
import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CodeEditor extends JFrame {

    public CodeEditor() {
        this.useSystemLookAndFeel();
        this.initUI();
        this.initFeatures();
    }
    
    private void initUI() {
        // JFrame setup
        super.setTitle("GenzEditor");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // JMenu Setup
        Menu menu = new Menu();
        System.out.println(menu.getComponentCount());
        super.setJMenuBar(menu);
    }
    
    private void initFeatures() {
        MainModel model = new MainModel();
        MainView view = new MainView();
        MainController controller = new MainController(view, model);
        
        view.attachKeyListener(new KeyboardListener(controller));
        super.add(view, BorderLayout.CENTER);
    }
    
    private void useSystemLookAndFeel() {
        // Adjusts editor styles according to the system styles, meaning
        // Window type buttons, textbox etc on Windows machines.
        // Linux type stuff on Linux machines.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CodeEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new CodeEditor().setVisible(true);
    }

}
