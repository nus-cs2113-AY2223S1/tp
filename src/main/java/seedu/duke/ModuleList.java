package seedu.duke;

import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ModuleList {
    public static ArrayList<Module> modules = new ArrayList<>();
    public static int viewCount;
    public static int mcsCount;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Constructor of ModuleList class to initialize an object of class moduleList.
     */
    public ModuleList() {

    }

    /**
     * Constructor of ModuleList class to initialize an object of class moduleList.
     * @param modules array of modules
     */
    public ModuleList(ArrayList<Module> modules) {
        ModuleList.modules = modules;
    }

    /**
     * Constructor to load the moduleList with input from file.
     * @param fileReader Scanner input loaded from the file
     * @throws InvalidInputFormatException exception thrown when input is invalid
     * @throws InvalidInputContentException exception thrown when input content is empty
     */
    public ModuleList(Scanner fileReader) throws InvalidInputFormatException, InvalidInputContentException {
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            Module module = Parser.parseFileInput(line);
            add(module, true);
        }
    }

    /**
     * Function to add a module to the moduleList if it does not already exist.
     * @param mod The module to be added to the list
     * @param isFromFile to check if the add command came from a module previously loaded in file.
     *                   This decides whether to display add message or not
     */
    public void add(Module mod, boolean isFromFile) {
        boolean isRepeat = checkRepetition(mod);
        if (isRepeat) {
            UI.repetitionMessage(mod.getCourse());
        } else {
            int before = modules.size();
            modules.add(mod);
            if (!isFromFile) {
                UI.addMessage(mod.getCourse(), mod.getSemesterTaken(), mod.getGrade());
            }
            int after = modules.size();

            try {
                assert before + 1 == after : "module list size should be increased by 1 after add";
            } catch (AssertionError e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Function to check if a given module already exists in the moduleList.
     * @param mod the module that needs to be searched for in the moduleList
     * @return true if the module already exists. False if it does not. Format: boolean
     */
    public boolean checkRepetition(Module mod) {
        for (Module m : modules) {
            if (m.getCourse().matches(mod.getCourse())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to delete a module from the moduleList. If the module is not found, an appropriate message is displayed.
     * @param modCode the module which has to be deleted from the moduleList.
     */
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

    /**
     * Function to find a list of all modules taken in a particular semester.
     * The function then calls the printResponse() method to print the appropriate response message.
     * @param semester The semester for which the modules need to be printed
     */
    public void view(String semester) {
        if (semester.equals("all")) {
            viewAll();
        } else {
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
    }

    /**
     * Function to find lists of all modules taken in every semester.
     * If there is no modules in moduleList, will print no modules found message
     */
    public void viewAll() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 2; j++) {
                ArrayList<Module> matchingModules = new ArrayList<>();
                String sem = "Y" + i + "S" + j;
                for (Module mod : modules) {
                    if (mod.getSemesterTaken().matches(sem)) {
                        matchingModules.add(mod);
                    }
                }
                if (!matchingModules.isEmpty()) {
                    printResponse(sem, matchingModules);
                }
            }
        }
        if (modules.isEmpty()) {
            UI.noModulesFoundMessage();
        }
    }

    /**
     * Function to clear modules in a particular or all semester.
     * Prints modules have been clear message if there is modules in semester.
     * Prints no modules found message if there is no modules in module list.
     * @param semester the semester to be cleared
     */
    public void clear(String semester) {
        boolean isFound = false;
        if (semester.equals("all")) {
            isFound = true;
            clearAll();
            UI.allClearedMessage();

        } else {
            ArrayList<Module> updatedModules = new ArrayList<>();
            for (Module mod : modules) {
                if (mod.getSemesterTaken().equals(semester)) {
                    isFound = true;
                } else {
                    updatedModules.add(mod);
                }
            }
            modules = updatedModules;

        }
        if (!isFound) {
            UI.notFoundClearMessage(semester);
        } else if (!semester.equals("all")) {
            UI.semesterClearedMessage(semester);
        }
    }

    /**
     * Function to generate an empty module list.
     */
    public void clearAll() {
        modules = new ArrayList<>();
    }

    /**
     * Function to print the response for a command to print a list of modules in a particular semester.
     * If the list is empty, an empty list message is displayed. Otherwise, the list is displayed.
     * @param semester Semester for which the list of modules needs to be displayed
     * @param matchingModules a Collection of all modules taken in the particular semester that need to be printed
     */
    private void printResponse(String semester, ArrayList<Module> matchingModules) {
        if (matchingModules.isEmpty()) {
            UI.emptyListMessage(semester);
        } else {
            UI.listMessage(matchingModules, semester);
        }
    }

    /**
     * Function to calculate the number of Mcs in a particular semester and print it with appropriate message.
     * @param semester the semester for which the mcs need to be calculated and printed
     */
    public void mc(String semester) {
        mcsCount = 0;
        for (Module mod : modules) {
            if (mod.getSemesterTaken().matches(semester)) {
                mcsCount += mod.getMcs();
            }
        }
        UI.mcMessage(semester, mcsCount);
    }

    /**
     * Function to find total Graded Mcs (Not S/U/-) in plan.
     * @return totalGradedMc Returns total graded Mcs taken in whole plan
     */
    public int totalGradedMcs() {
        int totalGradedMc = 0;
        for (Module mod : modules) {
            if (!(mod.getGrade().equals("-") || mod.getGrade().equals("S") || mod.getGrade().equals("U"))) {
                totalGradedMc += mod.getMcs();
            }
        }
        return totalGradedMc;
    }

    /**
     * Function to find total Mcs taken in plan.
     * @return totalMcs Returns total Mcs taken in whole plan
     */
    public int totalMcs() {
        int totalMcs = 0;
        for (Module mod : modules) {
            totalMcs += mod.getMcs();
        }
        return totalMcs;
    }

    /**
     * Function to find total S/U Module Mcs in plan.
     * @return totalSuMcs Returns total S/U Module Mcs taken in whole plan
     */
    public int totalSuMcs() {
        int totalSuMcs = 0;
        for (Module mod : modules) {
            if (mod.getGrade().equals("S") || mod.getGrade().equals("U")) {
                totalSuMcs += mod.getMcs();
            }
        }
        return totalSuMcs;
    }

    /**
     * Function to find total ungraded Mcs (-) in plan.
     * @return totalUngradedMcs Returns total ungraded Mcs taken in whole plan
     */
    public int totalUngradedMcs() {
        int totalUngradedMcs = 0;
        for (Module mod : modules) {
            if (mod.getGrade().equals("-")) {
                totalUngradedMcs += mod.getMcs();
            }
        }
        return totalUngradedMcs;
    }

    /**
     * Function to help compute total MCs needed to graduate.
     * @return 160 minus the total MCs taken and minus ungraded Mcs (-)
     */
    public int mcsForGraduation() {
        int mcsNeededForGraduation = 160;
        return  mcsNeededForGraduation - totalMcs() - totalUngradedMcs();
    }

    /**
     * Function to Calculates CAP.
     * @return CAP based on modules in plan
     */
    public double calculateCap() {
        double numerator = 0.0;
        for (Module mod: modules) {
            numerator += gradePoint(mod.getGrade()) * mod.getMcs();
        }
        return numerator / totalGradedMcs();
    }

    /**
     * Function to convert a grade into grade point.
     * @param grade The letter grade of a module. Format: String
     * @return The equivalent gradePoint of the grade
     */
    public double gradePoint(String grade) {
        switch (grade) {
        case "A+":
        case "A":
            return 5.0;
        case "A-":
            return 4.5;
        case "B+":
            return 4.0;
        case "B":
            return 3.5;
        case "B-":
            return 3.0;
        case "C+":
            return 2.5;
        case "C":
            return 2.0;
        case "D+":
            return 1.5;
        case "D":
            return 1.0;
        default:
            return 0.0;
        }
    }

    /**
     * For checking whether the module contains the keyword in its fields and return a boolean result.
     * @param keyword the word to search for in existing modules
     * @param mod the existing module to be checked
     * @return true if mod contains the keyword specified in its field
     */
    public static boolean findMatch(String keyword, Module mod) {
        try {
            return mod.getCourse().contains(keyword) || mod.getGrade().contains(keyword)
                    || mod.getSemesterTaken().contains(keyword) || (mod.getMcs() == Integer.parseInt(keyword));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * For checking is there is any existing modules with the keyword specified in its field
     * and print the appropriate message.
     * @param keyword the word to search for in existing modules
     */
    public void find(String keyword) {
        ArrayList<Module> matchingModules = findMatchingModules(keyword);
        findMsg(matchingModules);
    }

    /**
     * Function that helps to fina matching modules based on keyword given.
     * @param keyword the word to search for in existing modules
     * @return returns the array list with matching modules
     */
    public static ArrayList<Module> findMatchingModules(String keyword) {
        ArrayList<Module> matchingModules = new ArrayList<>();
        for (Module mod: modules) {
            if (findMatch(keyword, mod)) {
                matchingModules.add(mod);
            }
        }
        return matchingModules;
    }

    /**
     * Printing the respective message with respect to the size of the matchingModules array list.
     * @param matchingModules the array list of modules containing the keyword
     */
    public void findMsg(ArrayList<Module> matchingModules) {
        if (matchingModules.size() == 0) {
            UI.emptyFindMessage();
        } else {
            UI.findMessage(matchingModules);
        }
    }

    /**
     * Function to help count number of modules in array list.
     * @return Returns total number of Modules in modules Array
     */
    public int getCount() {
        return modules.size();
    }

    /**
     * Function to help get view count.
     * @return Returns total number of Modules for the recent View command executed
     */
    public int getViewCount() {
        return viewCount;
    }

    /**
     * Function to help get MC Count.
     * @return Returns total number of MCs for the recent mcs command executed
     */
    public int getMcsCount() {
        return mcsCount;
    }


}
