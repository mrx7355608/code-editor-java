/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.codeeditor;

import com.mycompany.console.ConsoleView;
import com.mycompany.editor.EditorView;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author bugsbunny
 */
public class SplitPane extends JSplitPane {

    public SplitPane(EditorView editorView, ConsoleView consoleView) {
        super(JSplitPane.VERTICAL_SPLIT, editorView, consoleView);
        this.setResizeWeight(0.75);  // 75% of space to code editor, 25% to output console
        this.setDividerLocation(300);  // Start with 300px for the code editor's height
        this.setOneTouchExpandable(true);
        this.setBorder(null);
        
        // This piece of code just creates a somewhat decent divider for the
        // splitpane
        this.setUI(new BasicSplitPaneUI() {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    public void setBorder(Border b) {
                    }

                    @Override
                    public void paint(Graphics g) {
                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, getSize().width, getSize().height);
                        super.paint(g);
                    }
                };
            }
        });
    }
}
