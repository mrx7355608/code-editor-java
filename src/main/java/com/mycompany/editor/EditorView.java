package com.mycompany.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

    public EditorView(DefaultListModel lineNumbersModel) {
        // Init editor
        editor = new JTextPane();
        editor.setFont(new Font("Courier New", Font.PLAIN, 16));

        // Line numbers
        lineNumbersView = new JList(lineNumbersModel);
        lineNumbersView.setFont(new Font("Courier New", Font.PLAIN, 14));

        // Panel for this
        JPanel panel = new JPanel();
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

    /**
     * It re-renders the "lineNumbersView (JList)" component
     */
    public void updateLineNumbers() {
        this.lineNumbersView.revalidate();
    }
}
