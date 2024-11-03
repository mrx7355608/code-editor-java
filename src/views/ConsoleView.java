package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class ConsoleView extends JPanel {
    private final JTextPane consolePane;
    private final JButton runBtn;

    public ConsoleView() {
        // Create a text pane to display code execution output
        consolePane = new JTextPane();
        consolePane.setEditable(false);
        consolePane.setPreferredSize(new Dimension(800, 300));

        // Run Button to trigger code execution
        runBtn = new JButton("Run");

        super.add(runBtn);
        super.add(consolePane);
    }

    public void addClickHandler(MouseListener mouseListener) {
        runBtn.addMouseListener(mouseListener);
    }
    
    public void displayText(String text) {
        consolePane.setText(text);
    }

}
