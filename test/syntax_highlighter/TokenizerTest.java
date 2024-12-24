/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package syntax_highlighter;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bugsbunny
 */
public class TokenizerTest {
    StringBuilder code = new StringBuilder();
    
    public TokenizerTest() {
        code.append("public class Main {\n");
        code.append("   public static void main(String[] args) {\n");
        code.append("       System.out.println(\"Hellow word\");\n");
        code.append("   }\n");
        code.append("}");
        
    }
    
    @Test
    public void testTokenize() {
        Tokenizer instance = new Tokenizer(code.toString());
        String expToken1 = "class";
        ArrayList<String> tokens = instance.tokenize();
        assertEquals(expToken1, tokens.get(1));
    }

    @Test
    public void testGetLines() {
        Tokenizer instance = new Tokenizer(code.toString());
        instance.tokenize();
        int expResult = 5;
        int result = instance.getLines();
        assertEquals(expResult, result);
    }
    
}
