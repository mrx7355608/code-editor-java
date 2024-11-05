/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ghost
 */
public class EditorModel {
    private EditorFile file;

    public EditorModel() {
        this.file = new EditorFile();
    }

    public String getCode() {
        return this.file.getCode();
    }

    public Boolean getIsSaved() {
        return this.file.getIsSaved();
    }

    public void setCode(String code) {
        this.file.setCode(code);
    }

    public void setIsSaved(Boolean isSaved) {
        this.file.setIsSaved(isSaved);
    }
    
    public String getFilePath() {
        return this.file.getPath();
    }

    public void setFile(EditorFile file) {
        this.file = file;
    }
    
}
