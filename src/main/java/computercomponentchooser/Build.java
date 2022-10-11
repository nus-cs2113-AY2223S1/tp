package computercomponentchooser;

import java.util.ArrayList;


public class Build {
    String name;
    ArrayList<String> contents;

    public Build(String name) {
        this.name = name;
        this.contents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public void addContents(String content) {
        contents.add(content);
    }

    public void deleteContents(String content) {
        contents.remove(content);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String name : contents) {
            sb.append(i).append(". ").append(name).append(System.lineSeparator());
            i++;
        }
        return sb.toString();
    }
}
