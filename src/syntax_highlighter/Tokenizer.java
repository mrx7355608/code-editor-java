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

            char c = this.currentChar();

            
            /**
             * If newline character is found, then increment "lines" variable
             * 
             * WARNING:
             * =======
             * This condition should be checked first because somehow the
             * "newline" character is also identified as a Space character
             * 
            */ 
            if (this.isNewline(c)) {
                line++;
                current++;
                continue;
            }
            
            if (this.isWhitespace(c)) {
                current++;
                continue;
            }

            // Handle brackets
            switch (c) {
                case '(' ->
                    this.addToken("(");
                case ')' ->
                    this.addToken(")");
                case '[' ->
                    this.addToken("[");
                case ']' ->
                    this.addToken("]");
                case '{' ->
                    this.addToken("{");
                case '}' ->
                    this.addToken("}");
            }

            // Handle operators
            switch (c) {
                case '+' ->
                    this.addToken("+");
                case '-' ->
                    this.addToken("-");
                case '*' ->
                    this.addToken("%");
                case '%' ->
                    this.addToken("%");
                case '/' -> {
                    if (this.nextChar() != '/') {
                        this.addToken("/");
                    } else {
                        this.ignoreSingleLineComment();
                    }
                }
            }
            
            // Handle comparators
            switch (c) {
                case '>' -> {
                    this.addToken(matchNextChar('=') ? ">=" : ">");
                    current += 2;
                    continue;
                }
                case '<' -> {
                    this.addToken(matchNextChar('=') ? "<=" : "<");
                    current += 2;
                    continue;
                }
                case '=' -> {
                    this.addToken(matchNextChar('=') ? "==" : "=");
                    current += 2;
                    continue;
                }
            }
            
            // Handle String literals
            if (c == '"') {
                this.tokenizeString();
                current++;
                continue;
            }

            // If a letter is found, then check if it's an identifier or a
            // keyword
            if (this.isAlpha(c)) {
                this.scanKeywordOrIdentifier();
                this.addToken();
                continue;
            }

            current++;

        }

        return tokens;
    }

    private boolean matchNextChar(char c) {
        return this.nextChar() == c;
    }
    private void addToken() {
        tokens.add(sourceCode.substring(start, current));
    }
    
    private void addToken(String value) {
        tokens.add(value);
    }

    private char nextChar() {
        return this.sourceCode.charAt(current + 1);
    }
    
    private char currentChar() {
        return sourceCode.charAt(current);
    }

    /**
     * ####################
     *     CLASSIFIERS
     * ####################
     * 
     * Below functions help in identifying tokens as keywords,
     * comments, identifiers, etc.
     */
    private void ignoreSingleLineComment() {
        while (this.nextChar() != '\n') {
            this.current++;
        }
    }
    
    private void scanKeywordOrIdentifier() {
        while (isAlpha(this.currentChar())) {
            current++;
        }
    }
    
    private void tokenizeString() {
        // Increment so that current will point to the letter that is present
        // after the " symbol
        // Example: "fawad"
        // When this method is invoked, the "current" is pointing at first " symbol
        // which will create some weird tokens, so I incremented it once so that
        // "current" will point to "f" character
        current++;
        
        while (this.currentChar() != '"' && !this.isAtEnd()) {
            current++;
        }
        
        this.addToken(sourceCode.substring(start, current + 1));
    }
    
    /**
     * #######################
     *   CHARACTER DETECTORS
     * #######################
     * 
     * Below functions detect whether a character is a 
     * newline, a space, a letter or any other thing
     */
    private boolean isNewline(char c) {
        return c == '\n';
    }

    private boolean isAlpha(char c) {
        return Character.isLetter(c);
    }

    private boolean isAtEnd() {
        return current >= sourceCode.length();
    }
    
    private boolean isWhitespace(char c) {
        return Character.isWhitespace(c);
    }
    
    // ######################
    //        GETTERS      
    // ######################
    
    public int getLines() {
        return this.line;
    }
}
