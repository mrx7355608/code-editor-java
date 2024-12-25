package syntax_highlighter;

import java.util.ArrayList;

public class Tokenizer {

    private ArrayList<Token> tokens = new ArrayList();
    private int start = 0;
    private int current = 0;
    private int line = 1;
    private String sourceCode;

    public ArrayList<Token> tokenize(String sourceCode) {
        this.sourceCode = sourceCode;
        this.reset();
        
        while (!isAtEnd()) {
            // Move start pointer to the current pointer position after
            // creating a token
            start = current;

            // Get current character
            char c = this.currentChar();

            /**
             * If newline character is found, then increment "lines" variable
             *
             * WARNING: ======= This condition should be checked first because
             * somehow the "newline" character is also identified as a Space
             * character
             *
             */
            if (this.isNewline(c)) {
                line++;
                System.out.println("newline detected at: " + current);
                current++;
                continue;
            }

            if (this.isWhitespace(c)) {
                System.out.println("space detected at: " + current);
                current++;
                continue;
            }

            // Handle brackets
            switch (c) {
                case '(' ->
                    this.addToken("(", TokenType.BRACKET);
                case ')' ->
                    this.addToken(")", TokenType.BRACKET);
                case '[' ->
                    this.addToken("[", TokenType.BRACKET);
                case ']' ->
                    this.addToken("]", TokenType.BRACKET);
                case '{' ->
                    this.addToken("{", TokenType.BRACKET);
                case '}' ->
                    this.addToken("}", TokenType.BRACKET);
            }

            // Handle operators
            switch (c) {
                case '+' ->
                    this.addToken("+", TokenType.OPERATOR);
                case '-' ->
                    this.addToken("-", TokenType.OPERATOR);
                case '*' ->
                    this.addToken("%", TokenType.OPERATOR);
                case '%' ->
                    this.addToken("%", TokenType.OPERATOR);
                case '/' -> {
                    if (this.nextChar() != '/') {
                        this.addToken("/", TokenType.OPERATOR);
                    } else {
                        this.ignoreSingleLineComment();
                    }
                }
            }

            // Handle comparators
            switch (c) {
                case '>' -> {
                    String value = this.matchNextChar('=') ? ">=" : ">";
                    this.addToken(value, TokenType.COMPARATOR);
                    current += 2;
                    continue;
                }
                case '<' -> {
                    String value = this.matchNextChar('=') ? "<=" : "<";
                    this.addToken(value, TokenType.COMPARATOR);
                    current += 2;
                    continue;
                }
                case '=' -> {
                    String value = matchNextChar('=') ? "==" : "=";

                    if ("==".equals(value)) {
                        this.addToken(value, TokenType.COMPARATOR);
                        current += 2;
                        continue;
                    } else {
                        this.addToken(value, TokenType.ASSIGNMENT);
                        current++;
                        continue;
                    }
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
                this.addToken(TokenType.KEYWORD);
                continue;
            }

            current++;

        }

        return tokens;
    }
    
    public void reset() {
        this.line = 1;
        this.current = 0;
        this.start = 0;
        this.tokens = new ArrayList();
    }

    private boolean matchNextChar(char c) {
        return this.nextChar() == c;
    }

    private void addToken(TokenType type) {
        String value = sourceCode.substring(start, current);
        Token token = new Token(value, type, line, start);
        tokens.add(token);
    }

    private void addToken(String value, TokenType type) {
        Token token = new Token(value, type, line, start);
        tokens.add(token);
    }

    private char nextChar() {
        return this.sourceCode.charAt(current + 1);
    }

    private char currentChar() {
        return sourceCode.charAt(current);
    }

    /**
     * #################### CLASSIFIERS ####################
     *
     * Below functions help in identifying tokens as keywords, comments,
     * identifiers, etc.
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

        String value = sourceCode.substring(start, current + 1);
        this.addToken(value, TokenType.STRING_LITERAL);
    }

    /**
     * ####################### CHARACTER DETECTORS #######################
     *
     * Below functions detect whether a character is a newline, a space, a
     * letter or any other thing
     */
    private boolean isNewline(char c) {
        return c == '\n' || c == '\r';
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
