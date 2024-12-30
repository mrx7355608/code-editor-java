/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codeeditor;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author bugsbunny
 */
public class Menu extends JMenuBar {

    private final MainController mainController;

    public Menu(MainController mainController) {
        this.mainController = mainController;
        super.add(this.createFileMenu());
        super.add(this.createEditMenu());
        super.add(this.createRunMenu());
        super.add(this.createOptionsMenu());
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("New");
        JMenuItem item2 = new JMenuItem("Open");
        JMenuItem item3 = new JMenuItem("Save");
        JMenuItem item4 = new JMenuItem("Save As");
        JMenuItem item5 = new JMenuItem("Close");

        item1.addActionListener((ActionEvent e) -> {
            this.mainController.newFile();
        });
        item2.addActionListener((ActionEvent e) -> {
            this.mainController.openFile();
        });
        item3.addActionListener((ActionEvent e) -> {
            this.mainController.saveFile();
        });
        fileMenu.add(item1);
        fileMenu.add(item2);
        fileMenu.add(item3);
        fileMenu.add(item4);
        fileMenu.add(item5);

        return fileMenu;
    }

    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        JMenuItem item1 = new JMenuItem("Find");
        JMenuItem item2 = new JMenuItem("Cut");
        JMenuItem item3 = new JMenuItem("Copy");
        JMenuItem item4 = new JMenuItem("Paste");
        JMenuItem item5 = new JMenuItem("Undo");
        JMenuItem item6 = new JMenuItem("Redo");

        item5.addActionListener((ActionEvent e) -> {
            this.mainController.undo();
        });
        item6.addActionListener((ActionEvent e) -> {
            this.mainController.redo();
        });

        editMenu.add(item1);
        editMenu.add(item2);
        editMenu.add(item3);
        editMenu.add(item4);
        editMenu.add(item5);
        editMenu.add(item6);

        return editMenu;
    }

    private JMenu createRunMenu() {
        JMenu runMenu = new JMenu("Run");
        JMenuItem item1 = new JMenuItem("Run");
        JMenuItem item2 = new JMenuItem("Compile");

        runMenu.add(item1);
        runMenu.add(item2);

        return runMenu;
    }

    private JMenu createOptionsMenu() {
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem item1 = new JMenuItem("Change font");
        JMenuItem item2 = new JMenuItem("Change theme");

        optionsMenu.add(item1);
        optionsMenu.add(item2);

        return optionsMenu;
    }

}
