/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.codeeditor;

import com.mycompany.codeeditor.MainController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author bugsbunny
 */
public class MenuBar extends JMenuBar {

    private final MainController mainController;
    private Color bgColor;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu optionsMenu;
    private JMenu runMenu;

    static class MyMenu extends JMenu {

        private final Border border = new LineBorder(Color.RED);

        public MyMenu(String s) {
            super(s);
        }

        @Override
        public JPopupMenu getPopupMenu() {
            JPopupMenu menu = super.getPopupMenu();
            menu.setBorder(border);
            return menu;
        }
    }

    public MenuBar(MainController mainController) {
        this.mainController = mainController;
        this.add(this.createFileMenu());
        this.add(this.createEditMenu());
        this.add(this.createRunMenu());
        this.add(this.createOptionsMenu());
        this.setBorder(BorderFactory.createLineBorder(bgColor));
    }

    private JMenu createFileMenu() {
        fileMenu = new MyMenu("File");
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
        editMenu = new MyMenu("Edit");
        JMenuItem item1 = new JMenuItem("Find");
        JMenuItem item2 = new JMenuItem("Cut");
        JMenuItem item3 = new JMenuItem("Copy");
        JMenuItem item4 = new JMenuItem("Paste");
        JMenuItem item5 = new JMenuItem("Undo");
        JMenuItem item6 = new JMenuItem("Redo");

        item2.addActionListener((ActionEvent e) -> {
            this.mainController.cut();
        });
        item3.addActionListener((ActionEvent e) -> {
            this.mainController.copy();
        });
        item4.addActionListener((ActionEvent e) -> {
            this.mainController.paste();
        });
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
        runMenu = new MyMenu("Run");
        JMenuItem item1 = new JMenuItem("Run");
        JMenuItem item2 = new JMenuItem("Compile");

        runMenu.add(item1);
        runMenu.add(item2);

        return runMenu;
    }

    private JMenu createOptionsMenu() {
        optionsMenu = new MyMenu("Options");
        JMenuItem item1 = new JMenuItem("Change font");
        JMenuItem item2 = new JMenuItem("Change theme");

        optionsMenu.add(item1);
        optionsMenu.add(item2);

        return optionsMenu;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, super.getWidth(), super.getHeight());
    }

    public void applyTheme(HashMap<String, Color> theme) {
        this.bgColor = theme.get("BACKGROUND");
        this.setBackground(theme.get("BACKGROUND"));
        this.setForeground(theme.get("FOREGROUND"));
        for (Component component : this.getComponents()) {
            component.setBackground(theme.get("BACKGROUND"));
            component.setForeground(theme.get("FOREGROUND"));
        }

        this.applyThemeToSubMenu(fileMenu, theme);
        this.applyThemeToSubMenu(editMenu, theme);
        this.applyThemeToSubMenu(runMenu, theme);
        this.applyThemeToSubMenu(optionsMenu, theme);
    }

    private void applyThemeToSubMenu(JMenu menu, HashMap<String, Color> theme) {
        LineBorder border = new LineBorder(theme.get("BACKGROUND"));
        menu.setBorder(border);

        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem item = menu.getItem(i);
            item.setOpaque(true);
            item.setBackground(theme.get("BACKGROUND"));
            item.setForeground(theme.get("FOREGROUND"));
        }
    }
}
