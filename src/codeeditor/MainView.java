package codeeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneLayout;

public class MainView extends JScrollPane {

    private final JTextPane editor;

    public MainView() {
        editor = new JTextPane();
        editor.setCaretColor(Color.white);
        editor.setForeground(Color.white);
        editor.setFont(new Font("Courier New", Font.PLAIN, 18));
        editor.setBackground(new Color(20,20,20));
        super.setBackground(Color.red);
        super.setLayout(new ScrollPaneLayout());
        super.setViewportView(editor);
    }
    
    public void attachKeyListener(KeyListener listener) {
        this.editor.addKeyListener(listener);
    }
    
    public String getEditorContent() {
        return this.editor.getText();
    }
    
    public JTextPane getTextPane() {
        return this.editor;
    }
    
}
