package syntax_highlighter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;

public class TokenizerTest {
    Tokenizer instance = new Tokenizer();

    @Test
    public void testKeywordsAndIdentifiers() {
        System.out.println("Should tokenize identifiers and keywords");
        String code = """
                      class Main {
                          public static void main(String[] args) {
                          }
                      }
                      """;

        ArrayList<Token> result = instance.tokenize(code);

        assertEquals("class", result.get(0).value);
        assertEquals("Main", result.get(1).value);
        assertEquals("public", result.get(3).value);
        assertEquals("static", result.get(4).value);
        assertEquals("args", result.get(11).value);
    }

    @Test
    public void testSingleLineComments() {
        System.out.println("Should tokenize single line comments");
        String code = """
                      // this is a comment
                      // this is another comment
                      class Main {}
                      """;

        ArrayList<Token> result = instance.tokenize(code);
        
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        // Using regular Array here cuz it's easy to write
        String[] expTokens = {"// this is a comment", "// this is another comment", "class", "Main", "{", "}"};
        assertEquals(Arrays.asList(expTokens), resultString);
    }
    
    @Test
    public void testMultiLineComments() {
        System.out.println("Should tokenize multi line comments");
        String code = """
                      /** 
                      * this is a multi
                      * line comment that
                      * tokenizer should ignore
                      */
                      class Main {}
                      """;

        ArrayList<Token> result = instance.tokenize(code);
        
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        // Using regular Array here cuz it's easy to write
        String[] expTokens = {"/**\n* this is a multi\n* line comment that\n* tokenizer should ignore\n*/", "class", "Main", "{", "}"};
        assertEquals(Arrays.asList(expTokens), resultString);
    }
    
    @Test
    public void testAlphaNumericIdentifiers() {
        System.out.println("Should identify alpha-numeric words as a single token");
        String code = """
                      class Main {
                        public void method1() {}
                        public void method2() { String value1; }
                      }
                      """;

        ArrayList<Token> result = instance.tokenize(code);
        
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        // Using regular Array here cuz it's easy to write
        String[] expTokens = {"class", "Main", "{",
            "public", "void", "method1", "(", ")", "{", "}",
            "public", "void", "method2", "(", ")", "{", "String", "value1",  "}", "}",
        };
        assertEquals(Arrays.asList(expTokens), resultString);
    }

    @Test
    public void testBrackets() {
        System.out.println("Should tokenize brackets");
        String code = """
                      if (a > b) {}
                      else {}
                      """;
        
        ArrayList<Token> result = instance.tokenize(code);
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        List<String> expTokense = List.of(
                "if", "(", "a", ">", "b", ")", "{", "}",
                "else", "{", "}");
        assertEquals(expTokense, resultString);
    }

    @Test
    public void testOperators() {
        System.out.println("Should tokenize operators");
        String code = """
                      int sum = a + b;
                      int sub = a - b;
                      """;
        
        ArrayList<Token> result = instance.tokenize(code);
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        List<String> expTokense = List.of(
                "int", "sum", "=", "a", "+", "b",
                "int", "sub", "=", "a", "-", "b");
        assertEquals(expTokense, resultString);
    }
    
    @Test
    public void testStringLiterals() {
        System.out.println("Should tokenize string literals");
        String code = """
                      int sum = a + b;
                      String name = "fawad";
                      """;

        ArrayList<Token> result = instance.tokenize(code);
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        List<String> expTokense = List.of(
                "int", "sum", "=", "a", "+", "b",
                "String", "name", "=", "\"fawad\"");
        assertEquals(expTokense, resultString);
    }
    
    @Test
    public void testCharLiterals() {
        System.out.println("Should tokenize char literals");
        String code = """
                      int sum = 4;
                      char grade = 'A';
                      """;

        ArrayList<Token> result = instance.tokenize(code);
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        List<String> expTokense = List.of(
                "int", "sum", "=", "4",
                "char", "grade", "=", "'A'");
        assertEquals(expTokense, resultString);

    }
    
    @Test
    public void testComparators() {
        System.out.println("Should tokenize comparators");
        String code = """
                      int sum = a + b;
                      if (sum >= b) {}
                      else if (sum <= b) {}
                      else if (sum == b) {}
                      a = b;
                      """;
        
        ArrayList<Token> result = instance.tokenize(code);
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        List<String> expTokense = List.of(
                "int", "sum", "=", "a", "+", "b",
                "if", "(", "sum", ">=", "b", ")", "{", "}",
                "else", "if", "(", "sum", "<=", "b", ")", "{", "}",
                "else", "if", "(", "sum", "==", "b", ")", "{", "}",
                "a", "=", "b"
                );
        assertEquals(expTokense, resultString);
    }
    
    @Test
    public void testNumbers() {
        System.out.println("Should tokenize integers and float numbers");
        String code = """
                      int a = 56;
                      int b = 71.4;
                      """;
        
        ArrayList<Token> result = instance.tokenize(code);
        List<String> resultString = result.stream().map(Token::getValue).collect(Collectors.toList());
        List<String> expTokense = List.of(
                "int", "a", "=", "56",
                "int", "b", "=", "71.4"
                );
        assertEquals(expTokense, resultString);
    }

    @Test
    public void testGetLines() {
        System.out.println("Should calculate number of lines correctly");
        String code = """
                      // this is a comment
                      class Main {
                          public static void main() {}
                      }""";

        instance.tokenize(code);

        int expResult = 4;
        int result = instance.getLines();
        assertEquals(expResult, result);
    }

}
