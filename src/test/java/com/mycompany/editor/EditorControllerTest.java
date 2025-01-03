/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.editor;

import com.mycompany.syntax_highlighter.SyntaxHighlightController;
import javax.swing.JTextPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author bugsbunny
 */
public class EditorControllerTest {
    
    private static EditorView mockView;
    private static EditorModel mockModel;
    private static SyntaxHighlightController mockSyntaxHighlighter;
    private static JTextPane mockTextPane;
    
    
    @BeforeAll
    public static void setUpClass() {
        mockView = mock(EditorView.class);
        mockModel = mock(EditorModel.class);
        mockTextPane = mock(JTextPane.class);
        mockSyntaxHighlighter = mock(SyntaxHighlightController.class);
        
        when(mockView.getTextPane()).thenReturn(mockTextPane);
        doNothing().when(mockView).addKeyListener(null);
    }

    /**
     * Test of updateUI method, of class EditorController.
     */
    @Test
    public void testUpdateUI() {
        System.out.println("updateUI");
        EditorController instance = new EditorController(mockView, mockModel, mockSyntaxHighlighter);
        
        when(mockModel.getCode()).thenReturn("class Main {}");
        doNothing().when(mockTextPane).setText("class Main {}");
        
        instance.updateUI();
        assertEquals("class Main {}", mockModel.getCode());
        verify(mockTextPane, times(1)).setText("class Main {}");
    }

    /**
     * Test of cut method, of class EditorController.
     */
    @Test
    public void testCut() {
        System.out.println("cut");
        EditorController instance = new EditorController(mockView, mockModel, mockSyntaxHighlighter);
        
        // Mock textPane.cut() method
        doNothing().when(mockTextPane).cut();
        
        // Call cut() method on textPane and verify that it has been invoked
        instance.cut();
        verify(mockTextPane, times(1)).cut();
    }

    /**
     * Test of copy method, of class EditorController.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        EditorController instance = new EditorController(mockView, mockModel, mockSyntaxHighlighter);
        
        // Mock textPane.copy() method
        doNothing().when(mockTextPane).copy();
        
        // Call copy() method on textPane and verify that it has been invoked
        instance.copy();
        verify(mockTextPane, times(1)).copy();
    }

    /**
     * Test of paste method, of class EditorController.
     */
    @Test
    public void testPaste() {
        System.out.println("paste");
        EditorController instance = new EditorController(mockView, mockModel, mockSyntaxHighlighter);
        
        // Mock textPane.paste() method
        doNothing().when(mockTextPane).paste();
        
        // Call paste() method on textPane and verify that it has been invoked
        instance.paste();
        verify(mockTextPane, times(1)).paste();
    }

    /**
     * Test of undo method, of class EditorController.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        EditorController instance = new EditorController(mockView, mockModel, mockSyntaxHighlighter);

        doNothing().when(mockTextPane).setText("");
        
        instance.undo();
        verify(mockModel, times(1)).performUndo();
    }

    /**
     * Test of redo method, of class EditorController.
     */
    @Test
    public void testRedo() {
        System.out.println("redo");
        EditorController instance = new EditorController(mockView, mockModel, mockSyntaxHighlighter);
        
        // Mock view.updateUI() method
        doNothing().when(mockTextPane).setText("");
        
        instance.redo();
        verify(mockModel, times(1)).performRedo();
    }

    /**
     * Test of getModel method, of class EditorController.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        EditorController instance = new EditorController(mockView, mockModel, mockSyntaxHighlighter);
        
        EditorModel result = instance.getModel();
        assertEquals(mockModel, result);
    }
    
}
