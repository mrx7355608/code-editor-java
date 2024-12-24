/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syntax_highlighter;

/**
 *
 * @author bugsbunny
 */
public class TokenPattern {
    public String pattern;
    public TokenType type;

    public TokenPattern(String pattern, TokenType type) {
        this.pattern = pattern;
        this.type = type;
    }

}
