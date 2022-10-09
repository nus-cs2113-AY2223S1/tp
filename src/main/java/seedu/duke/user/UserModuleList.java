package seedu.duke.user;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class of module list that manages the modules that the user is interested in.
 * This is based on NUS Modules that user wishes to map overseas in the future
 */

public class UserModuleList {
    private ArrayList<UserModule> modules;

    public void setModules(ArrayList<UserModule> modules) {
        this.modules = modules;
    }

    public UserModuleList() {
        this.modules = new ArrayList<>();
    }

    public ArrayList<UserModule> getModules() {
        return modules;
    }

    /**
     * Method to find current modules user stored by code.
     * @param input input code of module
     * @return true if module is found, false otherwise
     */
    public boolean findModuleByCode(UserModule input) {
        for (UserModule module : modules) {
            if (module.getNusCode().equals(input.getNusCode())
                && module.getPuCode().equals(input.getPuCode())) {
                return true;
            }
        }
        return false;
    }

    public void addModule(UserModule input) {
        if (findModuleByCode(input)) {
            System.out.println("Error module already added");
        } else {
            modules.add(input);
        }
    }

    public void displayAll() {
        if (modules.size() == 0) {
            System.out.println("No current modules saved");
        } else {
            for (int i = 0; i < modules.size(); i++) {
                System.out.print(i + 1);
                System.out.print(" : ");
                modules.get(i).printModule();
            }
        }
    }

    public void deleteModuleByPuCode(String input) {
        assert input.length() > 0 : "Deleting PU code cannot be empty";
        boolean isFound = false;
        for (int i = 0; i < modules.size(); ++i) {
            if (modules.get(i).getPuCode().equals(input)) {
                isFound = true;
                deleteModule(i + 1);
            }
        }
        if (!isFound) {
            System.out.println("No such modules found");
            throw new NoSuchElementException();
        }
    }

    public void deleteModule(int index) {
        index--;
        if (index < 0 || index >= modules.size()) {
            System.out.println("index not within range");
            throw new IndexOutOfBoundsException();
        } else {
            System.out.println("Deleting module:");
            System.out.println(modules.get(index).toString());
            modules.remove(index);
            System.out.println("Modules left for current school are: ");
            displayAll();
        }
    }

    public void updateModule(String puCode, String nusCode, String updates) {
        assert puCode.length() > 0 : "PU module code length cannot be null";
        assert nusCode.length() > 0 : "NUS module code length cannot be null";
        boolean isUpdated = false;
        for (UserModule module : modules) {
            if (module.getNusCode().equals(nusCode)
                    && module.getPuCode().equals(puCode)) {
                isUpdated = true;
                module.setComment(updates);
            }
        }
        if (!isUpdated) {
            System.out.println("No such modules found");
        }
    }

    /**
     * Method to find current modules user stored by keyword.
     * @param keyword input keyword
     * @return true if any module containing keyword is found, false otherwise
     */
    public boolean findModuleByTitle(String keyword) {
        int counter = 0;
        for (UserModule module : modules) {
            if (module.getPuTitle().contains(keyword) || module.getNusTitle().contains(keyword)) {
                counter++;
                System.out.println(module);
            }
        }
        if (counter == 0) {
            System.out.println("No modules with such keyword found");
            return false;
        }
        return true;
    }
}
