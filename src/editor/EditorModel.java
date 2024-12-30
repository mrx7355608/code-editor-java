/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editor;


/**
 *
 * @author ghost
 */
public class EditorModel {
    private EditorFile file;

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
    
    public EditorFile getFile() {
        return this.file;
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
}
