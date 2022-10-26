package seedu.duke.commands;

import seedu.duke.Module;
import seedu.duke.ModuleList;
import seedu.duke.UI;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

import java.util.ArrayList;
import java.util.List;

public class Check extends Command {
    public static int MC_MINIMUM_NOC = 70;
    public static int SEMESTER_MINIMUM_NOC = 4;
    public static double CAP_MINIMUM_SEP = 3.0;
    public static int SEMESTER_MINIMUM_SEP = 2;
    private static ArrayList<Module> modules = ModuleList.modules;
    private String type;
    public Check(String input) {
        this.type = input;
        switch (input) {
            // obtained >70 MCs, completed four semesters of study
        case "NOC":
            if (checkNOC()) {
                UI.NOCEligibleMessage();
            } else {
                UI.NOCIneligibleMessage();
            }
            break;
//            completed two semesters of study cap above 3.0
        case "SEP":
            if (checkSEP()) {
                UI.SEPEligibleMessage();
            } else {
                UI.SEPIneligibleMessage();
            }
            break;
        default:
        }
    }

    public static int findCurrentSemester() {
        List<String> semesters = new ArrayList<>();
        for (Module mod: modules) {
            String semesterTaken = mod.getSemesterTaken();
            if (!mod.getGrade().matches("-") && !semesters.contains(semesterTaken)) {
                semesters.add(mod.getSemesterTaken());
            };
        }
        return semesters.size();
    }

    public static boolean checkNOC() {
        if (checkNOCMc() && checkNOCSem()) {
            return true;
        }
        return false;
    }

    public static boolean checkNOCSem() {
        int currentSemester = findCurrentSemester();
        if (currentSemester >= SEMESTER_MINIMUM_NOC) {
            return true;
        }
        return false;
    }

    public static boolean checkNOCMc() {
        int totalMCs = 0;
        for (Module mod: modules) {
            totalMCs += mod.getMcs();
            if (totalMCs >= MC_MINIMUM_NOC) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSEP() {
        if (checkSEPCAP() && checkSEPSem()) {
            return true;
        }
        return false;
    }

    public static boolean checkSEPCAP() {
        double totalCAP = 0;
        int currentSemester = findCurrentSemester();
        for (Module mod: modules) {
            int semesterTaken = convertSem(mod.getSemesterTaken());
            if (currentSemester > semesterTaken) {
                double CAP = convertCAP(mod.getGrade());
                totalCAP += CAP;
            }
        }
        if (totalCAP >= CAP_MINIMUM_SEP) {
            return true;
        }
        return false;
    }

    public static boolean checkSEPSem() {
        int currentSemester = findCurrentSemester();
        if (currentSemester >= SEMESTER_MINIMUM_SEP) {
            return true;
        }
        return false;
    }

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
