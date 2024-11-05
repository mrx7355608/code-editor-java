/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_handlers;

import controllers.ConsoleController;
import controllers.EditorController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author ghost
 */
public class ConsoleMouseListener implements MouseListener {

    private final ConsoleController consoleController;

    public ConsoleMouseListener(
            ConsoleController controller1
    ) {
        this.consoleController = controller1;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        // 1. If file has been saved, simply execute the code.
//        if (this.editorController.isFileSaved()) {
//            this.consoleController.executeCode();
//            return;
//        }
//
//        // 2. Otherwise, check if user has selected a file already
//        // 2a. If file has already been selected, then save the file
//        //     and execute the code.
//        if (this.fileController.getFilePath() != null) {
//            this.fileController.saveFile();
//            this.consoleController.executeCode();
//            return;
//        }
//        
//        // 2b. If no file has been selected then, prompt the user to
//        //     select a file or a location using JFileChooser.
//        Boolean hasSelectedFile = this.fileController.showFileDialogBox();
//        if (!hasSelectedFile) {
//            return;
//        }
//        
//        // 3. If file already exists on that location, save and
//        //    execute the code.
//        File file = new File(this.fileController.getFilePath());
//        if (file.exists()) {
//            this.fileController.saveFile();
//            this.consoleController.executeCode();
//        }
//        
//        try {
//            // 4. Otherwise, create a file on the selected location
//            Boolean fileCreated = file.createNewFile();
//            if (!fileCreated) {
//                System.out.println("[ERROR] File not created");
//                return;
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(ConsoleMouseListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        // 4. Write user's code on the newly created file
//        FileWriter writer;
//        try {
//            writer = new FileWriter(file.getAbsolutePath());
//            writer.write(this.editorController.getCode());
//        } catch (IOException ex) {
//            Logger.getLogger(ConsoleMouseListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        // 5. Lastly, execute the code
        this.consoleController.executeCode();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
