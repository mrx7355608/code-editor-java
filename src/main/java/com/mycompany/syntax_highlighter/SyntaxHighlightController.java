
package com.mycompany.syntax_highlighter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import javax.swing.text.AttributeSet;


public class SyntaxHighlightController {
    private final Tokenizer tokenizer = new Tokenizer();
    private final JTextPane textPane;

    public SyntaxHighlightController(JTextPane textPane) {
        this.textPane = textPane;
    }
    
    public void highlight() {
        String text=  textPane.getText();
        text = text.replaceAll("\r\n", "\n").replaceAll("\r", "\n");
        ArrayList<Token> tokens = tokenizer.tokenize(text);        
        
        StyledDocument sd = textPane.getStyledDocument();

        for (Token token : tokens) {
            Pattern p = Pattern.compile(Pattern.quote(token.value));
            Matcher m = p.matcher(text);
            while (m.find()) {
                int start = token.column;
                int end = token.value.length();
                AttributeSet s = textPane.getStyle(token.type.toString());
                sd.setCharacterAttributes(start, end, s, true);
            }
        }
    }
    
}
