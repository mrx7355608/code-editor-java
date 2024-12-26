package codeeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class MainView extends JPanel {

    private final JTextPane editor;

    public MainView() {
        editor = new JTextPane();
        editor.setCaretColor(Color.white);
        editor.setForeground(Color.white);
        editor.setFont(new Font("Courier New", Font.PLAIN, 18));
        editor.setBackground(new Color(20,20,20));
        super.setBackground(Color.red);
        super.setLayout(new BorderLayout());
        super.add(editor, BorderLayout.CENTER);
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
