package com.mycompany.editor;

import javax.swing.DefaultListModel;

/**
 * EditorModel is responsible for storing our editor's data like code filename,
 * file path, etc.
 *
 * @author bugsbunny
 */
public class EditorModel {
    
    /**
     * EditorFile class is used to simplify the code manipulation especially
     * opening and saving files
     */
    private EditorFile file;
    
    /**
     * 
     * When enter is pressed, the value "lines" is incremented, and when a line
     * is removed in UI, value of "lines" is decreased
     * 
     * The "lineNumbersModel" attribute depends on "lines" attribute because
     * the value of "lines" is converted to a String and added in the 
     * "lineNumbersModel" as an element causing update in UI.
     * 
     */
    private int lines = 1;
    private final DefaultListModel lineNumbersModel;
    
    /**
     * Constructor initializes the "lineNumbersModel" with value of 1 because
     * by default there is 1 line at least in the editor
     */
    public EditorModel() {
        this.lineNumbersModel = new DefaultListModel();
        this.lineNumbersModel.addElement(String.valueOf(this.lines));
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
        this.lineNumbersModel.addElement(++this.lines);
    }
    
    public void decreamentLines() {
        this.lineNumbersModel.removeElement(this.lines--);
    }
}
