package seedu.duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModuleList {
    public static ArrayList<Module> modules = new ArrayList<>();
    public static int viewCount;
    public static int mcsCount;

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
        viewCount = 0;
        for (Module mod : modules) {
            if (mod.semesterTaken.matches(semester)) {
                matchingModules.add(mod);
                viewCount++;  // increments the count for this particular semester (For JUnit Purpose)
            }
        }
        UI.listMessage(matchingModules, semester);
    }
    public static void mc(String semester) {
        ArrayList<Module> matchingModules = new ArrayList<Module>();
        mcsCount = 0;
        for (Module mod : modules) {
            if (mod.semesterTaken.matches(semester)) {
                System.out.println("mcsCount from screeeen?? " + mcsCount);
                System.out.println("mod.mcs from screeeen?? " + mod.mcs);
                mcsCount =  mcsCount + mod.mcs;
            }
        }
        System.out.println("mcsCount from screeeen " + mcsCount);
        UI.mcMessage(semester, mcsCount);
    }

    // Returns total number of Modules in modules Array
    public int getCount() {
        return modules.size();
    }

    // Returns total number of Modules for the recent View command executed
    public int getViewCount() {
        return viewCount;
    }

    // Returns total number of MCs for the recent mcs command executed
    public int getMcsCount() {
        return mcsCount;
    }


}
