/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ghost
 */
public class EditorFile {
    private String name;
    private String path;
    private String code;
    private Boolean isSaved;
    
    public EditorFile() {
        this.name = "Untitled.java";
        this.path = null;
        this.code = this.getSampleCode();
        this.isSaved = false;
    }
    
    public EditorFile(String name, String path) {
        this.name = name;
        this.path = path;
        this.code = this.getSampleCode();
        this.isSaved = false;
    }

    public EditorFile(String name, String path, String code) {
        this.name = name;
        this.path = path;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getCode() {
        return code;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }
    
    private String getSampleCode() {
        StringBuilder sampleCode = new StringBuilder();
        sampleCode.append("class HelloWorld {\n");
        sampleCode.append("    public static void main(String[] args) {\n");
        sampleCode.append("        System.out.println(\"Hello World\");\n");
        sampleCode.append("    }\n");
        sampleCode.append("}\n");
        return sampleCode.toString();
    }
    
}
