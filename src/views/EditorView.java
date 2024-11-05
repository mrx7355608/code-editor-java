package views;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class EditorView extends JPanel {

    private final JTextPane editor;

    public EditorView() {
        editor = new JTextPane();
        editor.setPreferredSize(new Dimension(800, 400));        
        super.add(editor);
        super.setSize(800, 400);
    }
    
    public void attachKeyListener(KeyListener listener) {
        this.editor.addKeyListener(listener);
    }
    
    public String getEditorContent() {
        return this.editor.getText();
    }
    
}
