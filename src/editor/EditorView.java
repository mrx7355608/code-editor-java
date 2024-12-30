package editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneLayout;

public class EditorView extends JScrollPane {

    private final JTextPane editor;

    public EditorView() {
        editor = new JTextPane();
        editor.setCaretColor(Color.white);
        editor.setForeground(Color.white);
        editor.setFont(new Font("Courier New", Font.PLAIN, 18));
        editor.setBackground(new Color(50,50,50));
        super.setLayout(new ScrollPaneLayout());
        super.setViewportView(editor);
    }
    
    public String getEditorContent() {
        return this.editor.getText();
    }
    
    public JTextPane getTextPane() {
        return this.editor;
    }
    
}
