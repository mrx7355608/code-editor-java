package syntax_highlighter;

import utils.TokenType;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private ArrayList<Token> tokens = new ArrayList();
    private int start = 0;
    private int current = 0;
    private int line = 1;
    private String sourceCode;
    private final String keywordRegex = "\\b(abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|if|implements|import|instanceof|int|String|interface|long|native|new|package|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\\b";
    private final String accessModifierRegex = "\\b(public|protected|private)\\b";

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
                    switch (this.nextChar()) {
                        case '/' ->
                            this.tokenizeSingleLineComment();
                        case '*' -> 
                            this.tokenizeMultiLineComment();
                        default ->
                            this.addToken("/", TokenType.OPERATOR);
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
                this.tokenizeStringLiteral();
                current++;
                continue;
            }
            
            // Handle Char literals
            if (c == '\'') {
                this.tokenizeCharLiteral();
                current++;
                continue;
            }

            // Handle number literals
            if (isDigit(c)) {
                this.scanNumber();
                continue;
            }

            // If a letter is found, then check if it's an identifier or a
            // keyword
            if (this.isAlpha(c)) {
                this.scanWord();
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
    private void tokenizeSingleLineComment() {
        while (this.nextChar() != '\n') {
            this.current++;
        }
        
        String value = this.sourceCode.substring(start, current + 1);
        this.addToken(value, TokenType.COMMENT);
    }
    
    private void tokenizeMultiLineComment() {
        current++;
        while (this.currentChar() != '/' && !this.isAtEnd()) {
            if (this.isNewline(this.currentChar())) line++;
            this.current++;
        }
        
        String value = this.sourceCode.substring(start, current + 1);
        this.addToken(value, TokenType.COMMENT);
    }

    private void scanWord() {
        while (isAlpha(this.currentChar()) || this.isDigit(this.currentChar())) {
            current++;
        }

        // Classify word as keyword or access_modif, or an identifier
        String word = this.sourceCode.substring(start, current);
        Matcher m1 = Pattern.compile(keywordRegex).matcher(word);
        Matcher m2 = Pattern.compile(accessModifierRegex).matcher(word);
        if (m1.matches()) {
            this.addToken(word, TokenType.KEYWORD);
        } else if (m2.matches()) {
            this.addToken(word, TokenType.ACCESS_MODIFIER);
        } else {
            this.addToken(word, TokenType.IDENTIFIER);
        }
    }

    private void scanNumber() {
        while ((this.isDigit(this.currentChar())
                || this.isDecimal(this.currentChar()))
                && !this.isAtEnd()) {
            current++;
        }
        String numberStr = this.sourceCode.substring(start, current);
        this.addToken(numberStr, TokenType.NUMBER_LITERAL);
    }

    private void tokenizeStringLiteral() {
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
    
    private void tokenizeCharLiteral() {
        current++;
        while (this.currentChar() != '\'' && !this.isAtEnd()) {
            current++;
        }
        
        String value = this.sourceCode.substring(start, current + 1);
        this.addToken(value, TokenType.CHAR_LITERAL);
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

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isDecimal(char c) {
        return c == '.';
    }

    // ######################
    //        GETTERS      
    // ######################
    public int getLines() {
        return this.line;
    }
}
