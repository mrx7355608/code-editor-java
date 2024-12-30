package editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneLayout;

public class EditorView extends JScrollPane {

    private final JTextPane editor;
    private final JList list;
    private final DefaultListModel listModel;
    private int lines = 1;

    public EditorView() {
        // Init editor
        editor = new JTextPane();
        editor.setCaretColor(Color.white);
        editor.setForeground(Color.white);
        editor.setFont(new Font("Courier New", Font.PLAIN, 16));
        editor.setBackground(new Color(50, 50, 50));
        
        // Line numbers
        listModel = new DefaultListModel<String>();
        listModel.addElement("1");
        list = new JList(listModel);
        list.setFont(new Font("Courier New", Font.PLAIN, 16));
        
        // Panel for this
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(editor, BorderLayout.CENTER);
        panel.add(list, BorderLayout.WEST);
        
        super.setLayout(new ScrollPaneLayout());
        super.setViewportView(panel);
        super.getVerticalScrollBar().setUnitIncrement(16);        
    }

    public String getEditorContent() {
        return this.editor.getText();
    }

    public JTextPane getTextPane() {
        return this.editor;
    }
    
    public void increamentLineNumbers() {
        this.listModel.addElement(++this.lines);
        this.list.revalidate();
    }
    
    public int getLineNumbers() {
        return this.lines;
    }
    
    public void decreamentLineNumbers() {
        this.listModel.removeElement(this.lines--);
        this.list.revalidate();
    }
}
