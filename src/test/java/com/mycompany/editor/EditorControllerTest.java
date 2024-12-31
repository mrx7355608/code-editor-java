
package com.mycompany.editor;

import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class EditorControllerTest {
    
    @Mock
    private EditorView view;
    private EditorModel model;
    private EditorController editorController;
    
    @BeforeAll
    @DisplayName("Setup")
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
