package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class ModuleList {
    public static ArrayList<Module> modules = new ArrayList<>();
    public static int viewCount;
    public static int mcsCount;

    public ModuleList() {

    }

    /**
     * Constructor to load the moduleList with input from file
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
     * Function to add a module to the moduleList if it does not already exist
     * @param mod The module to be added to the list
     * @param isFromFile to check if the add command came from a module previously loaded in file. This decides whether to display add message or not
     */
    public void add(Module mod, boolean isFromFile) {
        boolean isRepeat = checkRepetition(mod);
        if (isRepeat) {
            UI.repetitionMessage(mod.getCourse());
        } else {
            int before = modules.size();
            modules.add(mod);
            if (isFromFile == false) {
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
     * function to check if a given module already exists in the moduleList
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
     * function to delete a module from the moduleList. If the module is not found, an appropriate message is displayed
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
     * Function to find a list of all modules taken in a particular semester
     * The function then calls the printResponse() method to print the appropriate response message
     * @param semester The semester for which the modules need to be printed
     */
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

    /**
     * Function to print the response for a command to print a list of modules in a particular semester
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
     * For checking whether the module contains the keyword in its fields and return a boolean result.
     * @param keyword the word to search for in existing modules
     * @param mod the existing module to be checked
     * @return true if mod contains the keyword specified in its field
     */
    public boolean findMatch(String keyword, Module mod) {
        try {
            return mod.getCourse().equals(keyword) || mod.getGrade().equals(keyword) ||
                    mod.getSemesterTaken().equals(keyword) || (mod.getMcs() == Integer.parseInt(keyword));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * For checking is there is any existing modules with the keyword specified in its field and print the appropriate message.
     * @param keyword the word to search for in existing modules
     */
    public void find(String keyword) {
        ArrayList<Module> matchingModules = new ArrayList<>();
        for (Module mod: modules) {
            if (findMatch(keyword, mod)) {
                matchingModules.add(mod);
            }
        }
        findMsg(matchingModules);
    }

    /**
     * Printing the respective message with respect to the size of the matchingModules array list
     * @param matchingModules the array list of modules containing the keyword
     */
    public void findMsg(ArrayList<Module> matchingModules) {
        if (matchingModules.size() == 0) {
            UI.emptyFindMessage();
        } else {
            UI.findMessage(matchingModules);
        }
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
