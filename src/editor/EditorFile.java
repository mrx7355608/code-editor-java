
package editor;

import java.util.Random;

public class EditorFile {
    private String code;
    private boolean saved;
    private String name;
    private String path;

    public EditorFile(String name, String path, String code, boolean saved) {
        this.code = code;
        this.saved = saved;
        this.name = name;
        this.path = path;
    }

    public EditorFile() {
        this.saved = false;
        this.path = null;
        this.code = this.getSampleCode();
        this.name = this.generateRandomName();
    }
    
    private String getSampleCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Main {\n");
        sb.append("    public static void main(String[] args) {\n");
        sb.append("        System.out.println(\"Hello World!\");\n");
        sb.append("    }\n");
        sb.append("}");
        return sb.toString();
    }
    
    private String generateRandomName() {
        Random random = new Random();
        int randomNum = random.nextInt(0, 5);
        
        // Print the shortened UUID
        String filename = "Untitled-" + randomNum + ".java";
        return filename;
    }

    public String getCode() {
        return code;
    }

    public boolean isSaved() {
        return saved;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
