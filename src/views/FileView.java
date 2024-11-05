/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author ghost
 */
public class FileView extends JMenuBar {

    public JMenuItem newFileItem;
    public JMenuItem saveFileItem;
    public JMenuItem openItem;
    public JMenuItem saveAsItem;
    public JMenuItem exitItem;
    public JMenuItem cutItem;
    public JMenuItem pasteItem;
    public JMenuItem copyItem;

    public FileView() {
        JMenu fileMenu = new JMenu("File");
        newFileItem = new JMenuItem("New file");
        openItem = new JMenuItem("Open file");
        saveFileItem = new JMenuItem("Save");
        saveAsItem = new JMenuItem("Save as");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(newFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(openItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(exitItem);
        this.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        this.add(editMenu);
    }

}
