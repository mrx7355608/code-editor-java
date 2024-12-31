
package com.mycompany.editor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


public class EditorControllerTest {
    
    @Mock
    private EditorView view;
    private EditorModel model;
    private EditorController editorController;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        editorController = new EditorController(view, model);
    }

    @Test
    public void testCutCallsViewCut() {
        // Arrange: Set up expectations
        doNothing().when(view.getTextPane()).cut();  // Mocking the cut() method in the view

        // Act: Perform the action
        editorController.cut();

        // Assert: Verify that the cut() method on the view was called once
        verify(view.getTextPane(), times(1)).cut();
    }

    
}
