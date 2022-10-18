package seedu.duke;

import java.util.ArrayList;

public class ModuleList {
    public static ArrayList<Module> modules = new ArrayList<>();
    public static int viewCount;
    public static int mcsCount;

    public void add(Module mod) {
        boolean isRepeat = checkRepetition(mod);
        if (isRepeat) {
            UI.repetitionMessage(mod.getCourse());
        } else {
            int before = modules.size();
            modules.add(mod);
            UI.addMessage(mod.getCourse(), mod.getSemesterTaken(), mod.getGrade());
            int after = modules.size();

            try {
                assert before + 1 == after : "module list size should be increased by 1 after add";
            } catch (AssertionError e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean checkRepetition(Module mod) {
        for (Module m : modules) {
            if (m.getCourse().matches(mod.getCourse())) {
                return true;
            }
        }
        return false;
    }

    public void delete(String modCode) {
        int indexCounter = 0;
        boolean isFound = false;
        for (Module mod : modules) {
            if (mod.getCourse().matches(modCode)) {
                int before = modules.size();
                modules.remove(indexCounter);
                UI.deleteMessage(modCode);
                isFound = true;
                int after = modules.size();
                try {
                    assert before == after + 1 : "module list size should be decreased by 1 after delete";
                } catch (AssertionError e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            indexCounter += 1;
        }
        if (!isFound) {
            UI.notFoundDeletionMessage(modCode);
        }
    }

    public void view(String semester) {
        ArrayList<Module> matchingModules = new ArrayList<>();
        viewCount = 0;
        for (Module mod : modules) {
            if (mod.getSemesterTaken().matches(semester)) {
                matchingModules.add(mod);
                viewCount++;  // increments the count for this particular semester (For JUnit Purpose)
            }
        }
        printResponse(semester, matchingModules);
    }

    private static void printResponse(String semester, ArrayList<Module> matchingModules) {
        if (matchingModules.isEmpty()) {
            UI.emptyListMessage(semester);
        } else {
            UI.listMessage(matchingModules, semester);
        }
    }

    public static void mc(String semester) {
        mcsCount = 0;
        for (Module mod : modules) {
            if (mod.getSemesterTaken().matches(semester)) {
                mcsCount += mod.getMcs();
            }
        }
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
