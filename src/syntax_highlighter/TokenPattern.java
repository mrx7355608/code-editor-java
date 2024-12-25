/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syntax_highlighter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author bugsbunny
 */
public class TokenPattern {
    public static HashMap<String, TokenType> getKeywordsMap() {
        HashMap<String, TokenType> keywords = new HashMap();
        
        keywords.put("return", TokenType.KEYWORD);
        
        return keywords;
    }
}
