/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author bugsbunny
 */
public class SearchView extends JPanel {
    
    private final JTextField searchField;
    private Highlighter highlighter;
    
    // Majboori
    public SearchView(JTextPane textPane) {
        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton closeButton = new JButton("Close");
        
        // Add an action that closes the search bar on Close button
        searchButton.addActionListener((ActionEvent e) -> {
            highlightText(textPane, searchField.getText());
        });
        
        // Add an action that closes the search bar on Close button
        closeButton.addActionListener((ActionEvent e) -> {
            closeSearch();
            removeHighlights();
        });

        searchField.setPreferredSize(new Dimension(500, 30));
        this.add(new JLabel("Search:"), BorderLayout.WEST);
        this.add(searchField, BorderLayout.CENTER);
        this.add(searchButton, BorderLayout.EAST);
        this.add(closeButton, BorderLayout.EAST);
        this.setVisible(false);
    }
    
    public void closeSearch() {
        this.setVisible(false);
    }
    
    public JTextField getTextField() {
        return this.searchField;
    }
    
    private void highlightText(JTextPane textArea, String word) {
        // Reset previous highlights
        highlighter = textArea.getHighlighter();
        highlighter.removeAllHighlights();

        String text = textArea.getText();
        int index = text.indexOf(word);

        // Highlight all occurrences of the word
        while (index >= 0) {
            try {
                int endIndex = index + word.length();
                highlighter.addHighlight(index, endIndex, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                index = text.indexOf(word, endIndex);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void removeHighlights() {
        if (this.highlighter != null) {
            highlighter.removeAllHighlights();
        }
    }
}
