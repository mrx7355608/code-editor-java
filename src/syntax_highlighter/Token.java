/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syntax_highlighter;

/**
 *
 * @author bugsbunny
 */
public class Token {
    public String value;
    public TokenType type;
    public int line;
    public int column;

    public Token(String token, TokenType tokenType, int line, int column) {
        this.value = token;
        this.type = tokenType;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Token{" + "value=" + value + ", type=" + type + ", line=" + line + ", column=" + column + '}';
    }   
    
}
