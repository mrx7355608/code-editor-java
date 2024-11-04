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
    private String code;
    private Boolean isSaved;

    public EditorModel() {
        this.code = "";
        this.isSaved = false;
    }

    public String getCode() {
        return code;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }
    
    
}
