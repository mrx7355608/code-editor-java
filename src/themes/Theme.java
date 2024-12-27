
package themes;

import java.awt.Color;
import java.util.HashMap;
import static utils.TokenType.*;


public class Theme {
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
        
        return theme;
    }
}
