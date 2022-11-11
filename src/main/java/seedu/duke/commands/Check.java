package seedu.duke.commands;

import seedu.duke.Module;
import seedu.duke.ModuleList;
import seedu.duke.UI;
import seedu.duke.exceptions.InvalidInputContentException;

import java.util.ArrayList;

public class Check extends Command {
    public static int MC_MINIMUM_NOC = 70;
    public static int SEMESTER_MINIMUM_NOC = 4;
    public static int SEMESTER_MAXIMUM_NOC = 7;
    public static double CAP_MINIMUM_SEP = 3.0;
    public static int SEMESTER_MINIMUM_SEP = 2;
    public static int SEMESTER_MAXIMUM_SEP = 6;
    private final ArrayList<Module> modules = ModuleList.modules;
    private String type;

    /**
     * Constructor to initialize object of Check Command class.
     * Depending on the input it will either check for NOC or SEP.
     * @param input input entered by user.
     */
    public Check(String input) throws InvalidInputContentException {
        this.type = input.toUpperCase().trim();
        switch (type) {
        case "NOC":
        case "SEP":
            break;
        default:
            throw new InvalidInputContentException();
        }
    }

    /**
     * Constructor to initialize object of Check Command class.
     */
    public Check() {
    }

    /**
     * Checking the eligibility of program by overriding the method in the parent command class.
     * @param moduleList the moduleList created in main
     */
    @Override
    public void execute(ModuleList moduleList) {
        switch (type) {
        case "NOC":   // obtained >70 MCs, completed four semesters of study
            if (checkNoc()) {
                UI.nocEligibleMessage();
            } else {
                UI.nocIneligibleMessage();
            }
            break;
        case "SEP":   //completed two semesters of study, cap above 3.0
            if (checkSep()) {
                UI.sepEligibleMessage();
            } else {
                UI.sepIneligibleMessage();
            }
            break;
        default:
        }
    }

    /**
     * Function to find the current semester based on the latest graded semester.
     * @return latest graded semester
     */
    public int findCurrentSemester() {
        int latestGradedSemester = 1;
        for (Module mod: modules) {
            if (!(mod.getGrade().equals("-")) && (convertSemToNum(mod.getSemesterTaken()) >= latestGradedSemester)) {
                latestGradedSemester = convertSemToNum(mod.getSemesterTaken());
            }
        }
        return latestGradedSemester;
    }

    /**
     * Function to find the current semester based on the latest graded semester.
     * @return latest graded semester
     */
    public String findCurrentSemesterInString() {
        return convertNumToSem(findCurrentSemester());
    }

    /**
     * Function to check if the user is eligible for NOC.
     * @return true if eligible, false otherwise
     */
    public boolean checkNoc() {
        return checkNocMc() && checkNocSem();
    }

    /**
     * Function to check if the user fulfills the semester requirements for NOC.
     * @return true if fulfilled, false otherwise
     */
    public boolean checkNocSem() {
        int currentSemester = findCurrentSemester();
        return (currentSemester >= SEMESTER_MINIMUM_NOC) && (currentSemester <= SEMESTER_MAXIMUM_NOC);
    }

    /**
     * Function to check if the user fulfills the MC requirements for NOC.
     * @return true if fulfilled, false otherwise
     */
    public boolean checkNocMc() {
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
     * Function to check if the user is eligible for SEP.
     * @return true if eligible, false otherwise
     */
    public boolean checkSep() {
        return checkSepCap() && checkSepSem();
    }

    /**
     * Function to check if the user fulfills the CAP requirements for SEP.
     * @return true if fulfilled, false otherwise
     */
    public boolean checkSepCap() {
        ModuleList ml = new ModuleList(modules);
        double cap = ml.calculateCap();
        return cap >= CAP_MINIMUM_SEP;
    }

    /**
     * Function to check if the user fulfills the semester requirements for SEP.
     * @return true if fulfilled, false otherwise
     */
    public boolean checkSepSem() {
        int currentSemester = findCurrentSemester();
        return (currentSemester >= SEMESTER_MINIMUM_SEP) && (currentSemester <= SEMESTER_MAXIMUM_SEP);
    }

    /**
     * Function to convert semester as a string into an integer.
     * @param semester the semester of a module.
     * @return the semester in the form of an integer
     */
    private static int convertSemToNum(String semester) {
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
     * Function to convert semester as a Number back to String.
     * @param semesterNumber the semester of a module.
     * @return the semester in the form of a String
     */
    private static String convertNumToSem(int semesterNumber) {
        switch (semesterNumber) {
        case 1:
            return "Y1S1";
        case 2:
            return "Y1S2";
        case 3:
            return "Y2S1";
        case 4:
            return "Y2S2";
        case 5:
            return "Y3S1";
        case 6:
            return "Y3S2";
        case 7:
            return "Y4S1";
        case 8:
            return "Y4S2";
        default:
            return "Y1S1";
        }
    }
}
