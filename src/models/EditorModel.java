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
    
    public EditorFile getFile() {
        return this.file;
    }

    public void setFile(EditorFile file) {
        this.file = file;
    }
    
}
