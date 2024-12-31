
package com.mycompany.file_handling;

import java.io.File;
import javax.swing.JFileChooser;


public class FileView {
    private final JFileChooser fileChooser;
    
    public FileView() {
        this.fileChooser = new JFileChooser();
        
        // Set the default directory
        this.fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    }
    
    public File showOpenFileDialogueBox() {
        int result = this.fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get selected file
            File selectedFile = this.fileChooser.getSelectedFile();
            return selectedFile;
        }
        
        return null;
    }
    
    public File showSaveFileDialogueBox() {
        int result = this.fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get selected file
            File selectedFile = this.fileChooser.getSelectedFile();
            return selectedFile;
        }
        
        return null;
    }
}
