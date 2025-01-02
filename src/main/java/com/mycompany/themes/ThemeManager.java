
package com.mycompany.themes;

import java.awt.Color;
import java.util.HashMap;
import static com.mycompany.utils.TokenType.*;


public class ThemeManager {
    public static HashMap<String, Color> hope() {
        HashMap<String, Color> theme = new HashMap();
        
        theme.put(KEYWORD.toString(), Color.RED);
        theme.put(ACCESS_MODIFIER.toString(), Color.ORANGE);
        theme.put(ASSIGNMENT.toString(), Color.WHITE);
        
        theme.put(BRACKET.toString(), Color.LIGHT_GRAY);
        theme.put(COMPARATOR.toString(), Color.CYAN);
        theme.put(OPERATOR.toString(), Color.MAGENTA);
        
        theme.put(STRING_LITERAL.toString(), Color.GREEN);
        theme.put(NUMBER_LITERAL.toString(), Color.MAGENTA);
        theme.put(CHAR_LITERAL.toString(), Color.GREEN);
        theme.put(IDENTIFIER.toString(), Color.WHITE);
        
        theme.put(COMMENT.toString(), Color.GRAY);
        
        theme.put("BACKGROUND", new Color(40, 40, 40));
        theme.put("FOREGROUND", Color.WHITE);
        theme.put("CURSOR", Color.BLUE);
        
        return theme;
    }
    
    public static HashMap<String, Color> diamonHead() {
        HashMap<String, Color> theme = new HashMap();
        
        theme.put(KEYWORD.toString(), Color.RED);
        theme.put(ACCESS_MODIFIER.toString(), Color.ORANGE);
        theme.put(ASSIGNMENT.toString(), Color.WHITE);
        
        theme.put(BRACKET.toString(), Color.LIGHT_GRAY);
        theme.put(COMPARATOR.toString(), Color.CYAN);
        theme.put(OPERATOR.toString(), Color.MAGENTA);
        
        theme.put(STRING_LITERAL.toString(), Color.GREEN);
        theme.put(NUMBER_LITERAL.toString(), Color.MAGENTA);
        theme.put(CHAR_LITERAL.toString(), Color.GREEN);
        theme.put(IDENTIFIER.toString(), Color.WHITE);
        
        theme.put(COMMENT.toString(), Color.GRAY);
        
        theme.put("BACKGROUND", new Color(40, 40, 40));
        theme.put("FOREGROUND", Color.WHITE);
        theme.put("CURSOR", Color.YELLOW);
        theme.put("BORDER", new Color(60, 60, 60));
        
        return theme;
    }
}
