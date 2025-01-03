package com.mycompany.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;

/**
 *
 * EditorView class is responsible for displaying code and line numbers
 *
 * @author bugsbunny
 */
public class EditorView extends JScrollPane {

    /**
     * JTextPane editor: displays text in our code editor
     */
    private final JTextPane editor;

    /**
     * Displays line numbers on the editor
     */
    private final JList lineNumbersView;

    /**
     * A panel in which editor and line numbers views will be added and then
     * this panel will be used as a view port view of JScrollPane. All of this
     * is necessary so that both views can be scrolled simultaneously
     */
    private final JPanel panel;

    public EditorView(DefaultListModel lineNumbersModel) {
        // Init editor
        editor = new JTextPane();
        editor.setFont(new Font("Courier New", Font.PLAIN, 16));

        // Line numbers
        lineNumbersView = new JList(lineNumbersModel);
        lineNumbersView.setFont(new Font("Courier New", Font.PLAIN, 14));

        // Panel for this
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(editor, BorderLayout.CENTER);
        panel.add(lineNumbersView, BorderLayout.WEST);

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

    public JList getLineNumbersView() {
        return this.lineNumbersView;
    }

    public void setLineNumbersViewBorderColor(Color c) {
        Border border = BorderFactory.createMatteBorder(0, 0, 0, 1, c);
        this.lineNumbersView.setBorder(border);
    }

    /**
     * It re-renders the "lineNumbersView (JList)" component
     */
    public void reRenderLineNumbers() {
        this.lineNumbersView.revalidate();
    }
}
