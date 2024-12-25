package syntax_highlighter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void testKeywordsAndIdentifiers() {
        System.out.println("Should tokenize identifiers and keywords");
        String code = """
                      class Main {
                          public static void main(String[] args) {
                          }
                      }
                      """;

        Tokenizer instance = new Tokenizer(code);
        ArrayList<String> result = instance.tokenize();

        assertEquals("class", result.get(0));
        assertEquals("Main", result.get(1));
        assertEquals("public", result.get(3));
        assertEquals("static", result.get(4));
        assertEquals("args", result.get(11));
    }

    @Test
    public void testSingleLineComments() {
        System.out.println("Should ignore single line comments");
        String code = """
                      // this is a comment
                      class Main {}
                      """;

        Tokenizer instance = new Tokenizer(code);
        ArrayList<String> result = instance.tokenize();

        // Using regular Array here cuz it's easy to write
        String[] expTokens = {"class", "Main", "{", "}"};
        assertEquals(Arrays.asList(expTokens), result);
    }

    @Test
    public void testBrackets() {
        System.out.println("Should tokenize brackets");
        String code = """
                      if (a > b) {}
                      else {}
                      """;
        Tokenizer instance = new Tokenizer(code);
        ArrayList<String> result = instance.tokenize();
        List<String> expTokense = List.of(
                "if", "(", "a", ">", "b", ")", "{", "}",
                "else", "{", "}");
        assertEquals(expTokense, result);
    }

    @Test
    public void testOperators() {
        System.out.println("Should tokenize operators");
        String code = """
                      int sum = a + b;
                      int sub = a - b;
                      """;
        Tokenizer instance = new Tokenizer(code);
        ArrayList<String> result = instance.tokenize();
        List<String> expTokense = List.of(
                "int", "sum", "=", "a", "+", "b",
                "int", "sub", "=", "a", "-", "b");
        assertEquals(expTokense, result);
    }
    
    @Test
    public void testStringLiterals() {
        System.out.println("Should tokenize string literals");
        String code = """
                      int sum = a + b;
                      String name = "fawad";
                      """;
        Tokenizer instance = new Tokenizer(code);
        ArrayList<String> result = instance.tokenize();
        List<String> expTokense = List.of(
                "int", "sum", "=", "a", "+", "b",
                "String", "name", "=", "\"fawad\"");
        assertEquals(expTokense, result);
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
        Tokenizer instance = new Tokenizer(code);
        ArrayList<String> result = instance.tokenize();
        List<String> expTokense = List.of(
                "int", "sum", "=", "a", "+", "b",
                "if", "(", "sum", ">=", "b", ")", "{", "}",
                "else", "if", "(", "sum", "<=", "b", ")", "{", "}",
                "else", "if", "(", "sum", "==", "b", ")", "{", "}",
                "a", "=", "b"
                );
        assertEquals(expTokense, result);
    }

    @Test
    public void testGetLines() {
        System.out.println("Should calculate number of lines correctly");
        String code = """
                      // this is a comment
                      class Main {
                          public static void main() {}
                      }""";

        Tokenizer instance = new Tokenizer(code);
        instance.tokenize();

        int expResult = 4;
        int result = instance.getLines();
        assertEquals(expResult, result);
    }

}