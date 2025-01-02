/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.console;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author bugsbunny
 */
public class ConsoleView extends JPanel {
    
    private final JTextPane textPane;
    
    public ConsoleView() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setText("Output will be shown here.\n");
        textPane.setBorder(BorderFactory.createEmptyBorder());
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        
        super.setLayout(new BorderLayout());
        super.add(scrollPane, BorderLayout.CENTER);
    }
    
    public JTextPane getTextPane() {
        return this.textPane;
    }
    
}
