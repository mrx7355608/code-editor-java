
package syntax_highlighter;

import java.util.ArrayList;

public class Tokenizer {
    private final ArrayList<String> tokens = new ArrayList();
    private int start = 0;
    private int current = 0;
    private int line = 1;
    private final String sourceCode;
    
    public Tokenizer(String sourceCode) {
        this.sourceCode = sourceCode;
    }
    
    public ArrayList<String> tokenize() {
        
        while (!isAtEnd()) {
            start = current;
            
            // 1. Check for reserved keywords and identifiers
            char c = this.getCurrentChar();
            
            if (this.isNewline(c)) {
                line++;
            }
            
            
            if (this.isAlpha(c)) {
                this.scanKeywordOrIdentifier();
                this.addToken();
            }
            
            current++;
            
        }
        
        return tokens;
    }
    
    private void addToken() {
        tokens.add(sourceCode.substring(start, current));
    }
    
    private void scanKeywordOrIdentifier() {
        while(isAlpha(this.getCurrentChar())) {
            current++;
        }
    }
    
    private boolean isNewline(char c) {
        return c == '\n';
    }
    
    private boolean isAlpha(char c) {
        return Character.isLetter(c);
    }
    
    private boolean isAtEnd() {
        return current >= sourceCode.length();
    }
    
    private char getCurrentChar() {
        return sourceCode.charAt(current);
    }
    
    public int getLines() {
        return this.line;
    }
}
