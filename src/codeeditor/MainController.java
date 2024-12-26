/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codeeditor;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import syntax_highlighter.SyntaxHighlightController;
import themes.Theme;

/**
 *
 * @author ghost
 */
public class MainController {

    private final MainView view;
    private final MainModel model;
    private final SyntaxHighlightController syntaxHighlightController;

    public MainController(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
        this.syntaxHighlightController = new SyntaxHighlightController(this.view.getTextPane());
        this.applyTheme();
    }
    
    public void highlight() {
        this.syntaxHighlightController.highlight();
    }
    
    private void applyTheme() {
        HashMap<String, Color> theme =  Theme.hope();
        theme.forEach((type, color) -> {
            JTextPane textPane = this.view.getTextPane();            
            Style style = textPane.addStyle(type, null);
            StyleConstants.setForeground(style, color);
            
        });
    }

    public void updateCode() {
        String editorContent = this.view.getEditorContent();
        this.model.setCode(editorContent);
    }
    
    public String getCode() {
        return this.model.getCode();
    }
}
