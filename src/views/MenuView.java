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
public class MenuView extends JMenuBar {
    
    public final JMenuItem newFileItem;
    public final JMenuItem openFileItem;
    public final JMenuItem saveFileItem;
    public final JMenuItem exitFileItem;
    public final JMenuItem runCodeItem;
    
    public MenuView() {
        JMenu fileMenu = new JMenu("File");
        newFileItem = new JMenuItem("New file");
        openFileItem = new JMenuItem("Open file");
        saveFileItem = new JMenuItem("Save");
        runCodeItem = new JMenuItem("Run");
        exitFileItem = new JMenuItem("Exit");
        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(exitFileItem);
        fileMenu.add(runCodeItem);
        
        super.add(fileMenu);
    }
    
}
