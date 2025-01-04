/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.editor;

import javax.swing.DefaultListModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bugsbunny
 */
public class EditorModelTest {

    private final EditorModel instance;

    public EditorModelTest() {
        EditorFile file = new EditorFile();
        instance = new EditorModel();
        instance.setFile(file);
    }

    /**
     * Test of performUndo method, of class EditorModel.
     */
    @Test
    public void testPerformUndo() {
        System.out.println("performUndo");
        String code1 = "class Main {}";
        String code2 = "class TestFile {}";
        String code3 = "class NewFile {}";

        instance.setCode(code1);
        instance.setCode(code2);
        instance.setCode(code3);
        instance.pushToUndoStack(code1);
        instance.pushToUndoStack(code2);
        instance.pushToUndoStack(code3);

        // 1st undo operation
        instance.performUndo();
        String result1 = instance.getCode();
        assertEquals(code2, result1);

        // 2nd undo operation
        instance.performUndo();
        String result2 = instance.getCode();
        assertEquals(code1, result2);

        // 3rd undo operation
        instance.performUndo();
        String result3 = instance.getCode();
        assertEquals("", result3);

        // 4th undo operation
        instance.performUndo();
        String result4 = instance.getCode();
        assertEquals("", result4);

    }

    /**
     * Test of performRedo method, of class EditorModel.
     */
    @Test
    public void testPerformRedo() {
        System.out.println("performRedo");
        String code1 = "class Main {}";
        String code2 = "class TestFile {}";
        String code3 = "class NewFile {}";

        instance.setCode(code1);
        instance.setCode(code2);
        instance.setCode(code3);
        instance.pushToUndoStack(code1);
        instance.pushToUndoStack(code2);
        instance.pushToUndoStack(code3);

        // 1st redo operation
        instance.performUndo();
        instance.performUndo();
        instance.performRedo();
        String result1 = instance.getCode();
        assertEquals(code2, result1);

        // 2nd redo operation
        instance.performRedo();
        String result2 = instance.getCode();
        assertEquals(code3, result2);

        // 2nd redo operation
        instance.performRedo();
        String result3 = instance.getCode();
        assertEquals(code3, result3);
    }

    /**
     * Test of increamentLines method, of class EditorModel.
     */
    @Test
    public void testIncreamentLines() {
        System.out.println("increamentLines");
        
        EditorModel myInstance = new EditorModel();
        DefaultListModel listModel = myInstance.getLineNumbersModel();

        myInstance.increamentLines();
        myInstance.increamentLines();
        myInstance.increamentLines();
        myInstance.increamentLines();

        int exp = 5;
        assertEquals(exp, myInstance.getLineNumbers());
        assertEquals(exp, listModel.getSize());
        assertEquals("01", listModel.getElementAt(0));
        assertEquals("02", listModel.getElementAt(1));
        assertEquals("03", listModel.getElementAt(2));
        assertEquals("04", listModel.getElementAt(3));
    }
    
    @Test
    public void testGetLineNumbers() {
        System.out.println("Should have 1 line by default");
        EditorModel myInstance = new EditorModel();
        assertEquals(1, myInstance.getLineNumbers());
    }
    
    @Test
    public void testIncreamentLinesOnPaste() {
        System.out.println("increamentLinesOnPaste");

        EditorModel myInstance = new EditorModel();
        DefaultListModel listModel = myInstance.getLineNumbersModel();

        myInstance.increaseLinesOnPaste(5);

        int exp = 5;
        assertEquals(exp, myInstance.getLineNumbers());
        assertEquals(exp, listModel.getSize());
        assertEquals("01", listModel.getElementAt(0));
        assertEquals("02", listModel.getElementAt(1));
    }
    
    @Test
    public void testDecreaseLines() {
        System.out.println("decreaseLines");

        EditorModel myInstance = new EditorModel();
        DefaultListModel listModel = myInstance.getLineNumbersModel();

        myInstance.increamentLines();
        myInstance.increamentLines();
        myInstance.increamentLines();
        myInstance.increamentLines();
        myInstance.increamentLines();
        myInstance.increamentLines();
        myInstance.decreaseLinesToSyncWithTextPane(4);

        int exp = 4;
        assertEquals(exp, myInstance.getLineNumbers());
        assertEquals(exp, listModel.getSize());
        assertEquals("03", listModel.getElementAt(2));
        assertEquals("04", listModel.getElementAt(3));
    }

}
