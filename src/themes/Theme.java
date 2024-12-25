
package themes;

import java.awt.Color;
import java.util.HashMap;
import enums.TokenType;


public class Theme {
    public static HashMap<TokenType, Color> hope() {
        HashMap<TokenType, Color> theme = new HashMap();
        
        theme.put(TokenType.KEYWORD, Color.BLUE);
        theme.put(TokenType.ACCESS_MODIFIER, Color.ORANGE);
        theme.put(TokenType.ASSIGNMENT, Color.WHITE);
        
        theme.put(TokenType.BRACKET, Color.LIGHT_GRAY);
        theme.put(TokenType.COMPARATOR, Color.CYAN);
        theme.put(TokenType.OPERATOR, Color.WHITE);
        
        theme.put(TokenType.STRING_LITERAL, Color.GREEN);
        theme.put(TokenType.NUMBER_LITERAL, Color.MAGENTA);
        theme.put(TokenType.CHAR_LITERAL, Color.GREEN);
        theme.put(TokenType.IDENTIFIER, Color.WHITE);
        
        return theme;
    }
}
