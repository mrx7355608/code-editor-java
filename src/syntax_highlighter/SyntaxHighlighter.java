
package syntax_highlighter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import static syntax_highlighter.TokenType.KEYWORD;


public class SyntaxHighlighter {
    private final Tokenizer tokenizer = new Tokenizer();
    
    public void highlight(String code, JTextPane textPane) {
        String text=  textPane.getText();
        text = text.replaceAll("\r\n", "\n").replaceAll("\r", "\n");
        ArrayList<Token> tokens = tokenizer.tokenize(text);
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
        
        StyledDocument sd = (StyledDocument) textPane.getDocument();
    
        Style style = textPane.addStyle("Keyword", null);
        StyleConstants.setForeground(style, Color.RED);
        
        Style style2 = textPane.addStyle("Assignment", null);
        StyleConstants.setForeground(style2, Color.BLUE);
        
        Style style3 = textPane.addStyle("Bracket", null);
        StyleConstants.setForeground(style3, Color.GREEN);
        
        Style style4 = textPane.addStyle("Operator", null);
        StyleConstants.setForeground(style4, Color.ORANGE);

        for (Token token : tokens) {
            System.out.println(token.value + "  " + token.column);
            Pattern p = Pattern.compile(Pattern.quote(token.value));
            Matcher m = p.matcher(text);
            while (m.find()) {
                switch (token.type) {
                    case BRACKET ->
                        sd.setCharacterAttributes(token.column, token.value.length(), textPane.getStyle("Bracket"), true);
                    case KEYWORD ->
                        sd.setCharacterAttributes(token.column, token.value.length(), textPane.getStyle("Keyword"), true);
                    case ASSIGNMENT ->
                        sd.setCharacterAttributes(token.column, token.value.length(), textPane.getStyle("Assignment"), true);
                    case OPERATOR ->
                        sd.setCharacterAttributes(token.column, token.value.length(), textPane.getStyle("Operator"), true);    
                }
            }
        }
    }
    
}
