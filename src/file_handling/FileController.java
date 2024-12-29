package file_handling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileController {

    private final FileView view;

    public FileController(FileView view) {
        this.view = view;
    }

    public File createNewFile(String path) {
        File newFile = this.view.showSaveFileDialogueBox();
        return newFile;
    }

    public void saveFile(String path, String newData) {
        try {
            // 1. If file exists, save the content
            if (this.fileExists(path)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                    writer.write(newData);
                }
                return;
            }

            // 2. Otherwise, create a new file and save content in it
            File newFile = this.createNewFile(path);
            if (newFile != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                    writer.write(newData);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String openFile() {
        try {
            // Show Open file UI
            File selectedFile = this.view.showOpenFileDialogueBox();
            if (selectedFile == null) {
                return null;
            }
            
            // Create a file reader
            String path = selectedFile.getAbsolutePath();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder fileData = new StringBuilder();

            // Start reading lines from the file
            String line = reader.readLine();

            // Read until end of the file
            while (line != null) {
                fileData.append(line);
                line = reader.readLine();
            }

            return fileData.toString();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    private boolean fileExists(String path) {
        return path == null || Files.exists(Paths.get(path));
    }
}
