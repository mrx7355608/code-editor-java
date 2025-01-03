/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.search;

import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author bugsbunny
 */
public class SearchController {

    private final SearchView view;

    public SearchController(SearchView view) {
        this.view = view;
    }

    public void applyTheme(HashMap<String, Color> theme) {
        this.view.setBackground(theme.get("BACKGROUND"));
        this.view.setForeground(theme.get("FOREGROUND"));
        this.view.getTextField().setBackground(theme.get("BACKGROUND"));
        this.view.getTextField().setForeground(theme.get("FOREGROUND"));
    }

    public void showSearchView() {
        this.view.setVisible(true);
    }

}
