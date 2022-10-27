package seedu.duke.commands;

import seedu.duke.Module;
import seedu.duke.ModuleList;
import seedu.duke.UI;
import seedu.duke.exceptions.InvalidInputContentException;

import java.util.ArrayList;
import java.util.List;

public class Check extends Command {
    public static int MC_MINIMUM_NOC = 70;
    public static int SEMESTER_MINIMUM_NOC = 4;
    public static double CAP_MINIMUM_SEP = 3.0;
    public static int SEMESTER_MINIMUM_SEP = 2;
    private ArrayList<Module> modules = ModuleList.modules;
    private String type;

    /**
     * Constructor to initialize object of Check Command class
     * Depending on the input it will either check for NOC or SEP
     * @param input input entered by user. Format: String
     */
    public Check(String input) throws InvalidInputContentException {
        this.type = input.toUpperCase();
        switch (type) {
        case "NOC":
        case "SEP":
            break;
        default:
            throw new InvalidInputContentException();
        }
    }

    @Override
    public void execute(ModuleList moduleList) {
        if (type.equals("NOC")) {
            //obtained >70 MCs, completed four semesters of study
            if (checkNOC()) {
                UI.NOCEligibleMessage();
            } else {
                UI.NOCIneligibleMessage();
            }
        } else if (type.equals("SEP")) {
            //completed two semesters of study cap above 3.0
            if (checkSEP()) {
                UI.SEPEligibleMessage();
            } else {
                UI.SEPIneligibleMessage();
            }
        }
    }

    /**
     * Function to find the current semester the user is on depending on the grade of the modules.
     * @return the semesters which have modules completed.
     */
    public int findCurrentSemester() {
        List<String> semesters = new ArrayList<>();
        for (Module mod: modules) {
            String semesterTaken = mod.getSemesterTaken();
            if (!mod.getGrade().matches("-") && !semesters.contains(semesterTaken)) {
                semesters.add(mod.getSemesterTaken());
            }
        }
        return semesters.size();
    }

    /**
     * Function to check if the user is eligible for NOC
     * @return true if eligible, false otherwise
     */
    public boolean checkNOC() {
        return checkNOCMc() && checkNOCSem();
    }

    /**
     * Function to check if the user fulfills the semester requirements for NOC
     * @return true if fulfilled, false otherwise
     */
    public boolean checkNOCSem() {
        int currentSemester = findCurrentSemester();
        return currentSemester >= SEMESTER_MINIMUM_NOC;
    }

    /**
     * Function to check if the user fulfills the MC requirements for NOC
     * @return true if fulfilled, false otherwise
     */
    public boolean checkNOCMc() {
        int totalMCs = 0;
        for (Module mod: modules) {
            totalMCs += mod.getMcs();
            if (totalMCs >= MC_MINIMUM_NOC) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to check if the user is eligible for SEP
     * @return true if eligible, false otherwise
     */
    public boolean checkSEP() {
        return checkSEPCAP() && checkSEPSem();
    }

    /**
     * Function to check if the user fulfills the CAP requirements for SEP
     * @return true if fulfilled, false otherwise
     */
    public boolean checkSEPCAP() {
        double totalCAP = 0;
        int currentSemester = findCurrentSemester();
        for (Module mod: modules) {
            int semesterTaken = convertSem(mod.getSemesterTaken());
            if (currentSemester > semesterTaken) {
                double CAP = convertCAP(mod.getGrade());
                totalCAP += CAP;
            }
        }
        return totalCAP >= CAP_MINIMUM_SEP;
    }

    /**
     * Function to check if the user fulfills the semester requirements for SEP
     * @return true if fulfilled, false otherwise
     */
    public boolean checkSEPSem() {
        int currentSemester = findCurrentSemester();
        return currentSemester >= SEMESTER_MINIMUM_SEP;
    }

    /**
     * Function to convert semester as a string into an integer
     * @param semester the semester of a module  Format: String
     * @return the semester in the form of an integer
     */
    private static int convertSem(String semester) {
        switch (semester) {
        case "Y1S1":
            return 1;
        case "Y1S2":
            return 2;
        case "Y2S1":
            return 3;
        case "Y2S2":
            return 4;
        case "Y3S1":
            return 5;
        case "Y3S2":
            return 6;
        case "Y4S1":
            return 7;
        case "Y4S2":
            return 8;
        default:
            return 0;
        }
    }

    /**
     * Function to convert a letter grade into CAP
     * @param grade The letter grade of a module. Format: String
     * @return The equivalent CAP of the module.
     */
    private static double convertCAP(String grade) {
        switch (grade) {
        case "F":
            return 0.0;
        case "D":
            return 1.0;
        case "D+":
            return 1.5;
        case "C":
            return 2.0;
        case "C+":
            return 2.5;
        case "B-":
            return 3.0;
        case "B":
            return 3.5;
        case "B+":
            return 4.0;
        case "A-":
            return 4.5;
        default:
            return 5.0;
        }
    }
}
