package views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
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
        editor.setText(this.model.getCode());
    }
    
}
