package views;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class EditorView extends JPanel {

    private final JTextPane editor;
    private final JMenuBar menuBar;

    public EditorView() {
        editor = new JTextPane();
        editor.setPreferredSize(new Dimension(800, 400));
        
        // Create menu bar
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFileItem = new JMenuItem("New file");
        JMenuItem openFileItem = new JMenuItem("Open file");
        JMenuItem saveFileItem = new JMenuItem("Save");
        JMenuItem exitFileItem = new JMenuItem("Exit");
        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(exitFileItem);
        menuBar.add(fileMenu);
        
        super.add(editor);
        super.setSize(800, 400);
    }
    
    public void attachKeyListener(KeyListener listener) {
        this.editor.addKeyListener(listener);
    }
    
    public String getEditorContent() {
        return this.editor.getText();
    }
    
    public JMenuBar getMenuBar() {
        return this.menuBar;
    }
    
}
