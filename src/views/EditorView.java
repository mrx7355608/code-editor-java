package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import models.EditorModel;

public class EditorView extends JPanel {

    private final JTextPane editor;
    private final EditorModel model;

    public EditorView(EditorModel model) {
        this.model = model;
        
        editor = new JTextPane();
        editor.setPreferredSize(new Dimension(800, 400));
        editor.setCaretColor(Color.blue);
        editor.setFont(new Font("Courier New", Font.PLAIN, 18));
        
        super.add(editor);
        super.setSize(800, 400);
    }
    
    public void attachKeyListener(KeyListener listener) {
        this.editor.addKeyListener(listener);
    }
    
    public String getEditorContent() {
        return this.editor.getText();
    }
    
    
    public void update() {
        editor.setText(this.model.getFile().getCode());
    }
    
    public JTextPane getTextPane() {
        return this.editor;
    }
    
}
