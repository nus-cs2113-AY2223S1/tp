package seedu.duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModuleList {
    public static ArrayList<Module> modules = new ArrayList<>();

    public static void add(Module mod) {
        modules.add(mod);
        UI.addMessage(mod.getCourse(), mod.getSemesterTaken(), mod.getGrade());
    }
    public static void delete(String modCode) {
        int indexCounter = 0;
        for (Module mod : modules) {
            if (mod.course.matches(modCode)) {
                modules.remove(indexCounter);
                UI.deleteMessage(modCode);
                return;
            }
            indexCounter += 1;
        }
    }
    public static void view(String semester) {
        ArrayList<Module> matchingModules = new ArrayList<Module>();
        for (Module mod : modules) {
            if (mod.semesterTaken.matches(semester)) {
                matchingModules.add(mod);
            }
        }
        UI.listMessage(matchingModules, semester);
    }
    public static void mc(String semester) {
        ArrayList<Module> matchingModules = new ArrayList<Module>();
        int mcCounter = 0;
        for (Module mod : modules) {
            if (mod.semesterTaken.matches(semester)) {
                mcCounter += mod.mcs;
            }
        }
        UI.mcMessage(semester, mcCounter);
    }
}
