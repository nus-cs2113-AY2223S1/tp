package computercomponentchooser;

import computercomponentchooser.components.Component;

import java.util.HashMap;
import java.util.Map;


public class Build {
    private String name;
    private LinkedHashMap2D<Component> components = new LinkedHashMap2D<Component>();
//    ArrayList<String> contents;

//    public Build(String name) {
//        this.name = name;
//        this.contents = new ArrayList<>();
//    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public ArrayList<String> getContents() {
//        return contents;
//    }
//
//    public void addContents(String content) {
//        contents.add(content);
//    }
//
//    public void deleteContents(String content) {
//        contents.remove(content);
//    }
//
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        int i = 1;
//        for (String name : contents) {
//            sb.append(i).append(". ").append(name).append(System.lineSeparator());
//            i++;
//        }
//        return sb.toString();
//    }

    public Build(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ArrayList<String> getContents() {
//        return contents;
//    }

    public void addComponent(String type, Component component) {
        switch (type) {
        case "cpu":
            components.addElement("cpu", component.getName(), component);
        }
    }

    public void deleteComponent(String type, String name) {
        components.removeElement(type, name);
    }
//
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String name : components.getNameList()) {
            sb.append(i).append(". ").append(name).append(System.lineSeparator());
            i++;
        }
        return sb.toString();
    }


}
