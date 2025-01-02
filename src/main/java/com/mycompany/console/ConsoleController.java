/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.console;

import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author bugsbunny
 */
public class ConsoleController {
    private final ConsoleView view;

    public ConsoleController(ConsoleView view) {
        this.view = view;
    }
    
    public void applyTheme(HashMap<String, Color> theme) {
        this.view.getTextPane().setBackground(theme.get("BACKGROUND"));
        this.view.getTextPane().setForeground(theme.get("FOREGROUND"));
    }
}
