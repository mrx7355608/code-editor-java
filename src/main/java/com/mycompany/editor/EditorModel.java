package com.mycompany.editor;

import com.mycompany.core.Node;
import com.mycompany.core.Stack;
import javax.swing.DefaultListModel;

/**
 * EditorModel is responsible for storing our editor's data like code, filename,
 * file path, etc.
 *
 * @author bugsbunny
 */
public class EditorModel {

    /**
     * EditorFile class is used to simplify the code manipulation.
     */
    private EditorFile file;

    /**
     * Stacks for managing undo and redo operations
     */
    private final Stack undoStack = new Stack();
    private final Stack redoStack = new Stack();

    /**
     * When enter is pressed, the value "lines" is incremented, and when a line
     * is removed in UI, value of "lines" is decreased
     *
     * The "lineNumbersModel" attribute depends on "lines" attribute because the
     * value of "lines" is converted to a String and added in the
     * "lineNumbersModel" as an element causing update in UI.
     */
    private int lines = 1;
    private final DefaultListModel lineNumbersModel;

    /**
     * Constructor initializes the "lineNumbersModel" with value of 1 because by
     * default there is 1 line at least in the editor
     */
    public EditorModel() {
        this.lineNumbersModel = new DefaultListModel();
        this.lineNumbersModel.addElement("01");
    }

    public void performUndo() {
        // Remove the topmost node from the stack
        Node removedNode = this.undoStack.pop();

        // If the removedNode is null, meaning that stack was already empty, so
        // exit out of this method and do nothing
        if (removedNode == null) {
            return;
        }

        // Otherwise, get the Node to which TOP is pointing at after pop()
        // operation.
        Node currentTopNode = this.undoStack.peek();

        // If currentTopNode is null, meaning that stack has now become empty,
        // so set the code to be an empty string
        if (currentTopNode == null) {
            this.file.setCode("");
        } else {
            this.file.setCode(currentTopNode.data);
        }

        // Lastly, push the removedNode in the redoStack, for performing redo
        // operations
        this.redoStack.push(removedNode.data);
    }

    public void performRedo() {
        // Remove the topmost node from the stack
        Node removedNode = this.redoStack.pop();

        // If the removedNode is null, meaning that stack was already empty, so
        // exit out of this method and do nothing
        if (removedNode == null) {
            return;
        }

        // Otherwise, set the code.
        this.file.setCode(removedNode.data);

        // Lastly push the removedNode into undoStack
        this.undoStack.push(removedNode.data);
    }

    public void pushToUndoStack(String data) {
        this.undoStack.push(data);
    }

    public EditorFile getFile() {
        return this.file;
    }

    public String getName() {
        return this.file.getName();
    }

    public String getPath() {
        return this.file.getPath();
    }

    public String getCode() {
        return this.file.getCode();
    }

    public Boolean getIsSaved() {
        return this.file.isSaved();
    }

    public DefaultListModel getLineNumbersModel() {
        return this.lineNumbersModel;
    }

    public int getLineNumbers() {
        return this.lines;
    }

    public void setFile(EditorFile file) {
        this.file = file;
    }

    public Boolean isNewlyCreated() {
        return this.file.getPath() == null;
    }

    public void setName(String name) {
        this.file.setName(name);
    }

    public void setPath(String path) {
        this.file.setPath(path);
    }

    public void setCode(String code) {
        this.file.setCode(code);
    }

    public void setIsSaved(Boolean isSaved) {
        this.file.setSaved(isSaved);
    }

    public void increamentLines() {
        String lineStr = String.format("%02d", ++this.lines);
        this.lineNumbersModel.addElement(lineStr);
    }

    public void decreamentLines() {
        String lineStr = String.format("%02d", this.lines--);
        this.lineNumbersModel.removeElement(lineStr);
    }

    public void increaseLinesOnPaste(int linesInTextPane) {
        for (int i = this.lines + 1; i <= linesInTextPane; i++) {
            System.out.println("---looping---");
            String lineStr = String.format("%02d", i);
            this.lineNumbersModel.addElement(lineStr);
            this.lines++;
        }
    }
}
