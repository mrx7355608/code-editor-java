/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package editor;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


/**
 *
 * @author bugsbunny
 */
public class EditorControllerTest {
    private EditorView mockView;
    private EditorModel mockModel;
    private EditorController instance;
    
    @Before
    public void setup() {
        mockView = mock(EditorView.class);
        mockModel = mock(EditorModel.class);
        instance = new EditorController(mockView, mockModel);
        
    }
    
    @Test
    public void testSomething() {
    }
    
}
