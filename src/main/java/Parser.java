import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String MAIN_PATIENT_COMMAND = "1";
    private static final String MAIN_VISIT_COMMAND = "2";
    private static final String MAIN_PRESCRIPTION_COMMAND = "3";
    private static final String EXIT_COMMAND = "bye";

    private static final String VIEW_ALL_COMMAND = "viewall";
    private static final String BACK_TO_MAIN_COMMAND = "main";

    private final PatientList patientList;
    private final VisitList visitList;
    private final PrescriptionList prescriptionList;
    private final UI ui;

    public Parser(PatientList patientList, VisitList visitList, PrescriptionList prescriptionList, UI ui) {
        this.patientList = patientList;
        this.visitList = visitList;
        this.prescriptionList = prescriptionList;
        this.ui = ui;
    }

    public MainMenuState mainMenuParser(String input) {
        switch (input.toLowerCase()) {
        case MAIN_PATIENT_COMMAND:
            return MainMenuState.PATIENT;
        case MAIN_VISIT_COMMAND:
            return MainMenuState.VISIT;
        case MAIN_PRESCRIPTION_COMMAND:
            return MainMenuState.PRESCRIPTION;
        case EXIT_COMMAND:
            return MainMenuState.EXIT;
        default:
            return MainMenuState.INVALID;
        }

    }

    private boolean shouldExit(String input) {
        return input.equalsIgnoreCase(EXIT_COMMAND);
    }

    private boolean shouldBackToMain(String input) {
        return input.equalsIgnoreCase(BACK_TO_MAIN_COMMAND);
    }

    public SubMenuState patientParser(String input) {
        if (shouldExit(input)) {
            return SubMenuState.EXIT;
        }

        if (shouldBackToMain(input)) {
            return SubMenuState.BACK_TO_MAIN;
        }

        try {
            boolean matchesView = input.equalsIgnoreCase(VIEW_ALL_COMMAND);
            Matcher matcherAdd = patientAddMatcher(input);
            Matcher matcherRetrieve = patientRetrieveMatcher(input);
            Matcher matcherEdit = patientEditMatcher(input);
            if (matchesView) {
                patientList.listPatients(ui);
            } else if (matcherAdd.find()) {
                patientList.addPatient(ui, matcherAdd.group(1), matcherAdd.group(3),
                        matcherAdd.group(2), matcherAdd.group(4));
            } else if (matcherRetrieve.find()) {
                patientList.retrievePatient(ui, matcherRetrieve.group(1));
            } else if (matcherEdit.find()) {
                parseEditPatient(matcherEdit.group(1), matcherEdit.group(2), matcherEdit.group(3));
            } else {
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + UI.PATIENT_ADD
                        + "\nn - The name should be one of two words"
                        + "\ng - The gender should be one letter, M or F"
                        + "\nd - The date of birth should be formatted as DD-MM-YYYY"
                        + "\ni - The id can be a sequence of numbers or letters"
                        + UI.PATIENT_EDIT
                        + "\nn/g/d - Please edit only one aspect of a patient at a time"
                        + UI.PATIENT_RETRIEVE
                        + UI.PATIENT_VIEW_ALL);
            }
        } catch (OneDocException e) {
            System.out.println("Incorrect format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected issue: " + e.getMessage());
        }

        return SubMenuState.IN_SUB_MENU;
    }

    public SubMenuState visitParser(String input) {
        if (shouldExit(input)) {
            return SubMenuState.EXIT;
        }

        if (shouldBackToMain(input)) {
            return SubMenuState.BACK_TO_MAIN;
        }

        try {
            boolean matchesView = input.equalsIgnoreCase(VIEW_ALL_COMMAND);
            Matcher matcherAdd = addVisitMatcher(input);
            Matcher matcherEdit = editVisitMatcher(input);
            Matcher matcherViewPatient = viewVisitPatientMatcher(input);
            Matcher matcherViewVisit = viewOneVisitMatcher(input);
            if (matchesView) {
                visitList.viewAll(ui);
            } else if (matcherAdd.find()) {
                String patientId = matcherAdd.group(1);
                checkPatientExists(patientId);
                String reason = matcherAdd.group(4);
                if (reason.isEmpty()) {
                    visitList.addVisit(ui, patientId, matcherAdd.group(2), matcherAdd.group(3));
                } else {
                    visitList.addVisit(ui, patientId, matcherAdd.group(2),
                            matcherAdd.group(3), matcherAdd.group(4));
                }
            } else if (matcherEdit.find()) {
                String patientId = matcherAdd.group(1);
                checkPatientExists(patientId);
                visitList.editReason(ui, patientId, matcherEdit.group(2));
            } else if (matcherViewPatient.find()) {
                String patientId = matcherAdd.group(1);
                checkPatientExists(patientId);
                visitList.viewPatient(ui, patientId);
            } else if (matcherViewVisit.find()) {
                String patientId = matcherAdd.group(1);
                checkPatientExists(patientId);
                //visitList.viewVisit(ui, patientId, matcherViewVisit.group(2));
            } else {
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + UI.VISIT_ADD
                        + "\nd - The date should be formatted as DD-MM-YYYY"
                        + "\nt - The time should be formatted as HH:MM"
                        + "\nr - The reason is optional, and can be any number of words"
                        + UI.VISIT_EDIT
                        + "\nr - The reason can be added or edited with any number of words"
                        + UI.VISIT_VIEW_ALL
                        + UI.VISIT_VIEW_PATIENT
                        + UI.VISIT_VIEW
                        + "\nINDEX - The index should be relative to all the visits of a patient");
            }
        } catch (OneDocException e) {
            System.out.println("Incorrect format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected issue: " + e.getMessage());
        }

        return SubMenuState.IN_SUB_MENU;
    }

    public SubMenuState prescriptionParser(String input) {
        if (shouldExit(input)) {
            return SubMenuState.EXIT;
        }

        if (shouldBackToMain(input)) {
            return SubMenuState.BACK_TO_MAIN;
        }

        try {
            boolean matchesView = input.equalsIgnoreCase(VIEW_ALL_COMMAND);
            Matcher matcherAdd = addPrescriptionMatcher(input);
            Matcher matcherEdit = editPrescriptionMatcher(input);
            Matcher matcherViewPatient = viewPrescriptionPatientMatcher(input);
            Matcher matcherViewActive = viewPrescriptionActiveMatcher(input);
            Matcher matcherChangeActive = changePrescriptionActiveMatcher(input);
            Matcher matcherChangeInactive = changePrescriptionInactiveMatcher(input);
            if (matchesView) {
                prescriptionList.viewAll(ui);
            } else if (matcherAdd.find()) {
                String patientId = matcherAdd.group(1);
                checkPatientExists(patientId);
                prescriptionList.add(ui, patientId, matcherAdd.group(2),
                        matcherAdd.group(3), matcherAdd.group(4));
            } else if (matcherEdit.find()) {
                parseEditPrescription(Integer.valueOf(matcherEdit.group(1)),
                        matcherEdit.group(2), matcherEdit.group(3));
            } else if (matcherViewPatient.find()) {
                String patientId = matcherViewPatient.group(1);
                checkPatientExists(patientId);
                prescriptionList.viewPatientPrescription(ui, patientId);
            } else if (matcherViewActive.find()) {
                String patientId = matcherViewActive.group(1);
                checkPatientExists(patientId);
                prescriptionList.viewActivePatientPrescription(ui, patientId);
            } else if (matcherChangeActive.find()) {
                //index is matcherChangeActive.group(1),
                prescriptionList.activatePrescription(ui, matcherChangeActive.group(1));
            } else if (matcherChangeInactive.find()) {
                //index is matcherChangeInactive.group(1),
                prescriptionList.deactivatePrescription(ui, matcherChangeInactive.group(1));
            } else {
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + UI.PRESCRIPTION_ADD
                        + "\nn - The prescription name should be one or two words"
                        + "\nd - The dosage should be a number followed by an amount"
                        + "\nt - The time instruction should be instructions on how to take, with any number of words"
                        + UI.PRESCRIPTION_EDIT
                        + "\nn/d/t - Please edit only one aspect of a prescription at a time"
                        + UI.PRESCRIPTION_VIEW_ALL
                        + UI.PRESCRIPTION_VIEW_PATIENT
                        + UI.PRESCRIPTION_VIEW_ACTIVE
                        + UI.PRESCRIPTION_CHANGE_ACTIVE
                        + UI.PRESCRIPTION_CHANGE_INACTIVE
                        + "\nINDEX - The index should be relative to all the visits of a patient");
            }
        } catch (OneDocException e) {
            System.out.println("Incorrect format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected issue: " + e.getMessage());
        }

        return SubMenuState.IN_SUB_MENU;
    }

    private void checkPatientExists(String patientId) throws OneDocException {
        if (patientList.findPatient(patientId) == null) {
            throw new OneDocException("That patient ID doesn't exist!");
        }
    }

    private static Matcher patientAddMatcher(String input) {
        Pattern patientAddPattern = Pattern.compile(
                "^add\\s*n/\\s*(\\w+\\s*\\w+|\\w+)\\s*g/\\s*(M|F)\\s*"
                        + "d/\\s*(\\d\\d-\\d\\d-\\d\\d\\d\\d)\\s*i/\\s*(\\w+)\\s*$",
                Pattern.CASE_INSENSITIVE);
        return patientAddPattern.matcher(input);
    }

    private static Matcher patientRetrieveMatcher(String input) {
        Pattern patientRetrievePattern = Pattern.compile(
                "^retrieve\\s*i/\\s*(\\w+)\\s*$", Pattern.CASE_INSENSITIVE);
        return patientRetrievePattern.matcher(input);
    }

    private static Matcher patientEditMatcher(String input) {
        Pattern patientEditPattern = Pattern.compile(
                "^edit\\s*i/\\s*(\\w+)\\s*(n|g|d)/\\s*([\\w-\\s]+)$", Pattern.CASE_INSENSITIVE);
        return patientEditPattern.matcher(input);
    }

    private void parseEditPatient(String id, String type, String input) throws OneDocException {
        switch (type) {
        case "n":
            Pattern matchName = Pattern.compile("^(\\w+\\s*\\w+|\\w+)$", Pattern.CASE_INSENSITIVE);
            if (matchName.matcher(input).find()) {
                patientList.modifyPatientDetails(ui, id, input, "", "");
            } else {
                throw new OneDocException("Name is incorrectly formatted! "
                        + "Please use First and Last name or just one name");
            }
            break;
        case "d":
            Pattern matchDob = Pattern.compile("^(\\d\\d-\\d\\d-\\d\\d\\d\\d)$", Pattern.CASE_INSENSITIVE);
            if (matchDob.matcher(input).find()) {
                patientList.modifyPatientDetails(ui, id, "", input, "");
            } else {
                throw new OneDocException("DOC is incorrectly formatted! Please use DD-MM-YYYY format");
            }
            break;
        case "g":
            Pattern matchGender = Pattern.compile("^(M|F)$", Pattern.CASE_INSENSITIVE);
            if (matchGender.matcher(input).find()) {
                patientList.modifyPatientDetails(ui, id, "", "", input);
            } else {
                throw new OneDocException("Gender is incorrectly formatted! Please use only one letter, M or F");
            }
            break;
        default:
            throw new OneDocException("Type is incorrectly formatted!"
                    + "Please use n/ for name, g/ for gender, and d/ for DOB");
        }
    }

    private static Matcher addVisitMatcher(String input) {
        Pattern addVisitPattern = Pattern.compile(
                "^add\\s*i/\\s*(\\w+)\\s*d/\\s*(\\d\\d-\\d\\d-\\d\\d\\d\\d)\\s*t/\\s*(\\d\\d:\\d\\d)\\s*"
                        + "(?:r/\\s*((?:\\w+\\s*)*\\w+))*\\s*$", Pattern.CASE_INSENSITIVE);
        return addVisitPattern.matcher(input);
    }

    private static Matcher editVisitMatcher(String input) {
        Pattern editVisitPattern = Pattern.compile(
                "^edit\\s*i/\\s*(\\w+)\\s*r/\\s*((?:\\w+\\s*)*\\w+)\\s*$", Pattern.CASE_INSENSITIVE);
        return editVisitPattern.matcher(input);
    }

    private static Matcher viewVisitPatientMatcher(String input) {
        Pattern viewVisitPatientPattern = Pattern.compile(
                "^viewPatient\\s*i/\\s*(\\w+)\\s*$", Pattern.CASE_INSENSITIVE);
        return viewVisitPatientPattern.matcher(input);
    }

    private static Matcher viewOneVisitMatcher(String input) {
        Pattern viewOneVisitPattern = Pattern.compile(
                "^viewVisit\\s*i/\\s*(\\w+)\\s*(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
        return viewOneVisitPattern.matcher(input);
    }

    private static Matcher addPrescriptionMatcher(String input) {
        Pattern addPrescriptionPattern = Pattern.compile(
                "^add\\s*i/\\s*(\\w+)\\s*n/\\s*(\\w+\\s*\\w+|\\w+)\\s*"
                        + "d/\\s*(\\d+\\s*\\w+)\\s*t/\\s*((?:\\w+\\s*)*\\w+)\\s*$",
                Pattern.CASE_INSENSITIVE);
        return addPrescriptionPattern.matcher(input);
    }

    private static Matcher editPrescriptionMatcher(String input) {
        Pattern editPrescriptionPattern = Pattern.compile(
                "^edit\\s*i/\\s*(\\d+)\\s*(n|d|t)/\\s*([\\w-\\s]+)$",
                Pattern.CASE_INSENSITIVE);
        return editPrescriptionPattern.matcher(input);
    }

    private static Matcher viewPrescriptionPatientMatcher(String input) {
        Pattern viewPrescriptionPatientPattern = Pattern.compile(
                "^viewPatientPres\\s*i/\\s*(\\w+)\\s*$", Pattern.CASE_INSENSITIVE);
        return viewPrescriptionPatientPattern.matcher(input);
    }

    private static Matcher viewPrescriptionActiveMatcher(String input) {
        Pattern viewPrescriptionActivePattern = Pattern.compile(
                "^viewActPatientPres\\s*i/\\s*(\\w+)\\s*$", Pattern.CASE_INSENSITIVE);
        return viewPrescriptionActivePattern.matcher(input);
    }

    private static Matcher changePrescriptionActiveMatcher(String input) {
        Pattern changePrescriptionActivePattern = Pattern.compile(
                "^activate\\s*i/\\s*(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
        return changePrescriptionActivePattern.matcher(input);
    }

    private static Matcher changePrescriptionInactiveMatcher(String input) {
        Pattern changePrescriptionInactivePattern = Pattern.compile(
                "^deactivate\\s*i/\\s*(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
        return changePrescriptionInactivePattern.matcher(input);
    }

    private void parseEditPrescription(int id, String type, String input) throws OneDocException {
        switch (type) {
        case "n":
            Pattern matchName = Pattern.compile("^(\\w+\\s*\\w+|\\w+)$", Pattern.CASE_INSENSITIVE);
            if (matchName.matcher(input).find()) {
                prescriptionList.edit(ui, id, input, "", "");
            } else {
                throw new OneDocException("Prescription name is incorrectly formatted! "
                        + "Please use one or two names without dashes or special characters");
            }
            break;
        case "d":
            Pattern matchDosage = Pattern.compile("^(\\d+\\s*\\w+)$", Pattern.CASE_INSENSITIVE);
            if (matchDosage.matcher(input).find()) {
                prescriptionList.edit(ui, id, "", input, "");
            } else {
                throw new OneDocException("Dosage is incorrectly formatted! "
                        + "Please use [amount] [portion] format, i.e. 10 mg");
            }
            break;
        case "t":
            Pattern matchTimeInst = Pattern.compile("^((?:\\w+\\s*)*\\w+)\\s*$", Pattern.CASE_INSENSITIVE);
            if (matchTimeInst.matcher(input).find()) {
                prescriptionList.edit(ui, id, "", "", input);
            } else {
                throw new OneDocException("Time instruction is incorrectly formatted! "
                        + "Please use words and numbers to describe the time interval");
            }
            break;
        default:
            throw new OneDocException("Type is incorrectly formatted!"
                    + "Please use n/ for name, d/ for dosage, and t/ for time interval");
        }
    }

}
