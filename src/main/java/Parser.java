import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private final PatientList patientList;
    private final VisitList visitList;
    private final PrescriptionList prescriptionList;
    private final UI ui;
    private final Storage storage;

    public Parser(PatientList patientList, VisitList visitList, PrescriptionList prescriptionList, UI ui) {
        this.patientList = patientList;
        this.visitList = visitList;
        this.prescriptionList = prescriptionList;
        this.ui = ui;
        this.storage = new Storage();
    }

    public Parser(PatientList patientList, VisitList visitList, PrescriptionList prescriptionList, UI ui,
                  Storage storage) {
        this.patientList = patientList;
        this.visitList = visitList;
        this.prescriptionList = prescriptionList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parse the main menu input and retrieve the corresponding main menu state.
     * @param input is a String given by user
     * @return `MainMenuState` enum
     */
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

    private boolean shouldShowSubMenu(String input) {
        return input.equalsIgnoreCase(HELP_COMMAND);
    }

    public SubMenuState patientParser(String input) {
        if (shouldExit(input)) {
            return SubMenuState.EXIT;
        }
        if (shouldBackToMain(input)) {
            return SubMenuState.BACK_TO_MAIN;
        }
        if (shouldShowSubMenu(input)) {
            return SubMenuState.HELP;
        }
        try {
            String inputLower = input.toLowerCase().replace(" ", "");
            Matcher matcherAdd = patientAddMatcher(input);
            Matcher matcherRetrieve = patientRetrieveMatcher(input);
            Matcher matcherEdit = patientEditMatcher(input);
            if (inputLower.startsWith(VIEW_ALL_COMMAND)) {
                checkViewAllCommand(inputLower, "patient");
                patientList.listPatients(ui);
            } else if (inputLower.startsWith(ADD_COMMAND)) {
                errorIfNoMatchPatient(matcherAdd, ADD_COMMAND);
                String patientId = matcherAdd.group(4).toUpperCase();
                errorForPatientID(patientId, false);
                patientList.addPatient(ui, matcherAdd.group(1), matcherAdd.group(3),
                        matcherAdd.group(2), patientId);
                storage.savePatientData(patientList);
            } else if (inputLower.startsWith(RETRIEVE_PATIENT_COMMAND)) {
                errorIfNoMatchPatient(matcherRetrieve, RETRIEVE_PATIENT_COMMAND);
                patientList.retrievePatient(ui, matcherRetrieve.group(1).toUpperCase());
            } else if (inputLower.startsWith(EDIT_COMMAND)) {
                errorIfNoMatchPatient(matcherEdit, EDIT_COMMAND);
                parseEditPatient(matcherEdit.group(1).toUpperCase(), matcherEdit.group(2), matcherEdit.group(3));
            } else {
                errorIfNoMatchPatient(null, "default");
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
        if (shouldShowSubMenu(input)) {
            return SubMenuState.HELP;
        }
        try {
            String inputLower = input.toLowerCase().replace(" ", "");
            Matcher matcherAdd = addVisitMatcher(input);
            Matcher matcherEdit = editVisitMatcher(input);
            Matcher matcherDelete = deleteReasonMatcher(input);
            Matcher matcherViewPatient = viewVisitPatientMatcher(input);
            Matcher matcherViewVisit = viewOneVisitMatcher(input);
            if (inputLower.startsWith(VIEW_ALL_COMMAND)) {
                checkViewAllCommand(inputLower, "visit");
                visitList.viewAll(ui);
            } else if (inputLower.startsWith(ADD_COMMAND)) {
                errorIfNoMatchVisit(matcherAdd, ADD_COMMAND);
                String patientId = matcherAdd.group(1).toUpperCase();
                errorForPatientID(patientId, true);
                assert !patientId.contains(" ");
                parseAddVisit(matcherAdd, patientId);
            } else if (inputLower.startsWith(EDIT_COMMAND)) {
                errorIfNoMatchVisit(matcherEdit, EDIT_COMMAND);
                String reason = matcherEdit.group(2);
                errorIfReasonEmpty(reason);
                visitList.editReason(ui, Integer.parseInt(matcherEdit.group(1)), reason);
                storage.saveVisitData(visitList);
            } else if (inputLower.startsWith(DELETE_REASON_COMMAND.toLowerCase())
                    || inputLower.startsWith("delete")) {
                errorIfNoMatchVisit(matcherDelete, DELETE_REASON_COMMAND);
                visitList.deleteReason(ui, Integer.parseInt(matcherDelete.group(1)));
            } else if (inputLower.startsWith(VIEW_PATIENT_COMMAND.toLowerCase())) {
                errorIfNoMatchVisit(matcherViewPatient, VIEW_PATIENT_COMMAND);
                String patientId = matcherViewPatient.group(1).toUpperCase();
                errorForPatientID(patientId, true);
                assert !patientId.contains(" ");
                visitList.viewPatient(ui, patientId);
            } else if (inputLower.startsWith(VIEW_VISIT_COMMAND.toLowerCase())) {
                errorIfNoMatchVisit(matcherViewVisit, VIEW_VISIT_COMMAND);
                visitList.viewVisit(ui, Integer.parseInt(matcherViewVisit.group(1)));
            } else {
                errorIfNoMatchVisit(null, "default");
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
        if (shouldShowSubMenu(input)) {
            return SubMenuState.HELP;
        }
        try {
            String inputLower = input.toLowerCase().replace(" ", "");
            Matcher matcherAdd = addPrescriptionMatcher(input);
            Matcher matcherEdit = editPrescriptionMatcher(input);
            Matcher matcherViewPatient = viewPrescriptionPatientMatcher(input);
            Matcher matcherViewActive = viewPrescriptionActiveMatcher(input);
            Matcher matcherChangeActive = changePrescriptionActiveMatcher(input);
            Matcher matcherChangeInactive = changePrescriptionInactiveMatcher(input);
            if (inputLower.startsWith(VIEW_ALL_COMMAND)) {
                checkViewAllCommand(inputLower, "prescription");
                prescriptionList.viewAll(ui);
            } else if (inputLower.startsWith(ADD_COMMAND)) {
                errorIfNoMatchPrescription(matcherAdd, ADD_COMMAND);
                String patientId = matcherAdd.group(1).toUpperCase();
                errorForPatientID(patientId, true);
                assert !patientId.contains(" ");
                prescriptionList.add(ui, patientId, matcherAdd.group(2),
                        matcherAdd.group(3), matcherAdd.group(4));
                storage.savePrescriptionData(prescriptionList);
            } else if (inputLower.startsWith(EDIT_COMMAND)) {
                errorIfNoMatchPrescription(matcherEdit, EDIT_COMMAND);
                parseEditPrescription(Integer.parseInt(matcherEdit.group(1)),
                        matcherEdit.group(2), matcherEdit.group(3));
            } else if (inputLower.startsWith(VIEW_PATIENT_PRES_COMMAND.toLowerCase())
                    || inputLower.startsWith(VIEW_PATIENT_COMMAND.toLowerCase())) {
                errorIfNoMatchPrescription(matcherViewPatient, VIEW_PATIENT_PRES_COMMAND);
                String patientId = matcherViewPatient.group(1);
                errorForPatientID(patientId, true);
                assert !patientId.contains(" ");
                prescriptionList.viewPatientPrescription(ui, patientId);
            } else if (inputLower.startsWith(VIEW_ACT_PATIENT_PRES_COMMAND.toLowerCase())) {
                errorIfNoMatchPrescription(matcherViewActive, VIEW_ACT_PATIENT_PRES_COMMAND);
                String patientId = matcherViewActive.group(1);
                errorForPatientID(patientId, true);
                assert !patientId.contains(" ");
                prescriptionList.viewActivePatientPrescription(ui, patientId);
            } else if (inputLower.startsWith(ACTIVATE_COMMAND.toLowerCase())) {
                errorIfNoMatchPrescription(matcherChangeActive, ACTIVATE_COMMAND);
                prescriptionList.activatePrescription(ui, matcherChangeActive.group(1));
                storage.savePrescriptionData(prescriptionList);
            } else if (inputLower.startsWith(DEACTIVATE_COMMAND.toLowerCase())) {
                errorIfNoMatchPrescription(matcherChangeInactive, DEACTIVATE_COMMAND);
                prescriptionList.deactivatePrescription(ui, matcherChangeInactive.group(1));
                storage.savePrescriptionData(prescriptionList);
            } else {
                errorIfNoMatchPrescription(null, "default");
            }
        } catch (OneDocException e) {
            System.out.println("Incorrect format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected issue: " + e.getMessage());
        }

        return SubMenuState.IN_SUB_MENU;
    }

    public void errorIfNoMatchPatient(Matcher matcher, String message) throws OneDocException {
        if (matcher == null || !matcher.find()) {
            switch (message) {
            case ADD_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PATIENT_ADD + HELP_MESSAGE);
            case EDIT_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PATIENT_EDIT
                        + "\n\tn - The name should be one or two space-separated words"
                        + "\n\tg - The gender should be one letter, M or F"
                        + "\n\td - The date of birth should be formatted as DD-MM-YYYY"
                        + HELP_MESSAGE);
            case RETRIEVE_PATIENT_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PATIENT_RETRIEVE
                        + "\n\ti - The id can be a sequence of numbers or letters without any spaces"
                        + HELP_MESSAGE);
            default:
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + UI.PATIENT_ADD
                        + UI.PATIENT_EDIT
                        + UI.PATIENT_RETRIEVE
                        + UI.PATIENT_VIEW_ALL
                        + UI.RETURN_TO_MAIN
                        + UI.EXIT_PROGRAM);
            }
        }
    }

    public void errorIfNoMatchVisit(Matcher matcher, String message) throws OneDocException {
        if (matcher == null || !matcher.find()) {
            switch (message) {
            case ADD_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.VISIT_ADD + HELP_MESSAGE);
            case EDIT_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.VISIT_EDIT + HELP_MESSAGE);
            case DELETE_REASON_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.VISIT_DELETE_REASON
                        + "\n\tx - The index should be a displayed number next to the visit"
                        + HELP_MESSAGE);
            case VIEW_PATIENT_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.VISIT_VIEW_PATIENT
                        + "\n\ti - The id can be a sequence of numbers or letters without any spaces"
                        + HELP_MESSAGE);
            case VIEW_VISIT_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.VISIT_VIEW
                        + "\n\tx - The index should be a displayed number next to the visit"
                        + HELP_MESSAGE);
            default:
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + UI.VISIT_ADD
                        + UI.VISIT_EDIT
                        + UI.VISIT_DELETE_REASON
                        + UI.VISIT_VIEW_ALL
                        + UI.VISIT_VIEW_PATIENT
                        + UI.VISIT_VIEW
                        + UI.RETURN_TO_MAIN
                        + UI.EXIT_PROGRAM);
            }
        }
    }

    public void errorIfNoMatchPrescription(Matcher matcher, String message) throws OneDocException {
        if (matcher == null || !matcher.find()) {
            switch (message) {
            case ADD_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PRESCRIPTION_ADD + HELP_MESSAGE);
            case EDIT_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PRESCRIPTION_EDIT
                        + "\n\tx - The index should be a displayed number next to the prescription"
                        + "\n\tn - The prescription name can be multiple words, including -"
                        + "\n\td - The dosage should be a number followed by an amount, i.e. 10 mg"
                        + "\n\tt - The time instruction explains how to take the dosage, "
                        + "with any number of words"
                        + HELP_MESSAGE);
            case VIEW_PATIENT_PRES_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PRESCRIPTION_VIEW_PATIENT
                        + "\n\ti - The id can be a sequence of numbers or letters without any spaces"
                        + HELP_MESSAGE);
            case VIEW_ACT_PATIENT_PRES_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PRESCRIPTION_VIEW_ACTIVE
                        + "\n\ti - The id can be a sequence of numbers or letters without any spaces"
                        + HELP_MESSAGE);
            case ACTIVATE_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PRESCRIPTION_CHANGE_ACTIVE
                        + "\n\tx - The index should be a displayed number next to the prescription"
                        + HELP_MESSAGE);
            case DEACTIVATE_COMMAND:
                throw new OneDocException(ERROR_MESSAGE + UI.PRESCRIPTION_CHANGE_INACTIVE
                        + "\n\tx - The index should be a displayed number next to the prescription"
                        + HELP_MESSAGE);
            default:
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + UI.PRESCRIPTION_ADD
                        + UI.PRESCRIPTION_EDIT
                        + UI.PRESCRIPTION_VIEW_ALL
                        + UI.PRESCRIPTION_VIEW_PATIENT
                        + UI.PRESCRIPTION_VIEW_ACTIVE
                        + UI.PRESCRIPTION_CHANGE_ACTIVE
                        + UI.PRESCRIPTION_CHANGE_INACTIVE
                        + UI.RETURN_TO_MAIN
                        + UI.EXIT_PROGRAM);
            }
        }
    }

    private void checkViewAllCommand(String inputLower, String type) {
        if (inputLower.length() > VIEW_ALL_COMMAND.length()) {
            System.out.println("A viewall command will print all "
                    + type + "s, and anything after it will be disregarded.");
        }
    }

    private void errorForPatientID(String patientId, boolean errorIfNotExist) throws OneDocException {
        Patient patientIdFound = patientList.findPatient(patientId);
        if (patientIdFound == null && errorIfNotExist) {
            throw new OneDocException("That patient ID doesn't exist!");
        } else if (patientIdFound != null && !errorIfNotExist) {
            throw new OneDocException("That patient ID already exists! Please choose a new one");
        }
    }

    private void errorIfReasonEmpty(String reason) throws OneDocException {
        if (reason.isEmpty()) {
            throw new OneDocException("Please don't use edit to put in an empty reason! "
                    + "Use deleteReason");
        }
    }

    private void parseAddVisit(Matcher matcher, String patientId) {
        String reason = matcher.group(4);
        if (reason == null || reason.isEmpty()) {
            visitList.addVisit(ui, patientId, matcher.group(2), matcher.group(3));
            storage.saveVisitData(visitList);
        } else {
            visitList.addVisit(ui, patientId, matcher.group(2),
                    matcher.group(3), matcher.group(4));
            storage.saveVisitData(visitList);
        }
    }

    private Matcher patientAddMatcher(String input) {
        Pattern patientAddPattern = Pattern.compile(
                "^" + ADD_COMMAND + "\\s*n/" + PATIENT_NAME_REGEX + "g/" + GENDER_REGEX
                        + "d/" + DATE_REGEX + "i/" + ID_REGEX + "$",
                Pattern.CASE_INSENSITIVE);
        return patientAddPattern.matcher(input);
    }

    private  Matcher patientRetrieveMatcher(String input) {
        Pattern patientRetrievePattern = Pattern.compile(
                "^" + RETRIEVE_PATIENT_COMMAND + "\\s*i/" + ID_REGEX + "$", Pattern.CASE_INSENSITIVE);
        return patientRetrievePattern.matcher(input);
    }

    private static Matcher patientEditMatcher(String input) {
        Pattern patientEditPattern = Pattern.compile(
                "^" + EDIT_COMMAND + "\\s*i/" + ID_REGEX + "(n|g|d)/\\s*([\\w-\\s]+)$",
                Pattern.CASE_INSENSITIVE);
        return patientEditPattern.matcher(input);
    }

    private void parseEditPatient(String id, String type, String input) throws OneDocException {
        switch (type) {
        case "n":
            Pattern matchName = Pattern.compile("^" + PATIENT_NAME_REGEX + "$",
                    Pattern.CASE_INSENSITIVE);
            if (matchName.matcher(input).find()) {
                patientList.modifyPatientDetails(ui, id, input, "", "");
                storage.savePatientData(patientList);
            } else {
                throw new OneDocException("Name is incorrectly formatted! "
                        + "Please use First and Last name or just one name");
            }
            break;
        case "d":
            Pattern matchDob = Pattern.compile("^" + DATE_REGEX + "$", Pattern.CASE_INSENSITIVE);
            if (matchDob.matcher(input).find()) {
                patientList.modifyPatientDetails(ui, id, "", input, "");
                storage.savePatientData(patientList);
            } else {
                throw new OneDocException("DOC is incorrectly formatted! Please use DD-MM-YYYY format");
            }
            break;
        case "g":
            Pattern matchGender = Pattern.compile("^" + GENDER_REGEX + "$", Pattern.CASE_INSENSITIVE);
            if (matchGender.matcher(input).find()) {
                patientList.modifyPatientDetails(ui, id, "", "", input);
                storage.savePatientData(patientList);
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
                "^" + ADD_COMMAND + "\\s*i/" + ID_REGEX + "d/" + DATE_REGEX + "t/" + TIME_REGEX
                        + OPTIONAL_REASON_REGEX + "$", Pattern.CASE_INSENSITIVE);
        return addVisitPattern.matcher(input);
    }

    private static Matcher editVisitMatcher(String input) {
        Pattern editVisitPattern = Pattern.compile(
                "^" + EDIT_COMMAND + "\\s*x/" + INDEX_REGEX + "r/"
                        + REASON_REGEX + "$", Pattern.CASE_INSENSITIVE);
        return editVisitPattern.matcher(input);
    }

    private static Matcher deleteReasonMatcher(String input) {
        Pattern deleteReasonPattern = Pattern.compile(
                "^" + DELETE_REASON_COMMAND + "\\s*x/"
                        + INDEX_REGEX + "$", Pattern.CASE_INSENSITIVE);
        return deleteReasonPattern.matcher(input);
    }

    private static Matcher viewVisitPatientMatcher(String input) {
        Pattern viewVisitPatientPattern = Pattern.compile(
                "^" + VIEW_PATIENT_COMMAND + "\\s*i/"
                        + ID_REGEX + "$", Pattern.CASE_INSENSITIVE);
        return viewVisitPatientPattern.matcher(input);
    }

    private static Matcher viewOneVisitMatcher(String input) {
        Pattern viewOneVisitPattern = Pattern.compile(
                "^" + VIEW_VISIT_COMMAND + "\\s*x/"
                        + INDEX_REGEX + "$", Pattern.CASE_INSENSITIVE);
        return viewOneVisitPattern.matcher(input);
    }

    private static Matcher addPrescriptionMatcher(String input) {
        Pattern addPrescriptionPattern = Pattern.compile(
                "^" + ADD_COMMAND + "\\s*i/" + ID_REGEX + "n/" + PRESCRIPTION_NAME_REGEX
                        + "d/" + DOSAGE_REGEX + "t/" + TIME_INSTRUCTION_REGEX + "$",
                Pattern.CASE_INSENSITIVE);
        return addPrescriptionPattern.matcher(input);
    }

    private static Matcher editPrescriptionMatcher(String input) {
        Pattern editPrescriptionPattern = Pattern.compile(
                "^" + EDIT_COMMAND + "\\s*x/"  + INDEX_REGEX + "(n|d|t)/\\s*(.+)$",
                Pattern.CASE_INSENSITIVE);
        return editPrescriptionPattern.matcher(input);
    }

    private static Matcher viewPrescriptionPatientMatcher(String input) {
        Pattern viewPrescriptionPatientPattern = Pattern.compile(
                "^" + VIEW_PATIENT_PRES_COMMAND + "\\s*i/"
                        + ID_REGEX + "$", Pattern.CASE_INSENSITIVE);
        return viewPrescriptionPatientPattern.matcher(input);
    }

    private static Matcher viewPrescriptionActiveMatcher(String input) {
        Pattern viewPrescriptionActivePattern = Pattern.compile(
                "^" + VIEW_ACT_PATIENT_PRES_COMMAND + "\\s*i/"
                        + ID_REGEX + "$", Pattern.CASE_INSENSITIVE);
        return viewPrescriptionActivePattern.matcher(input);
    }

    private static Matcher changePrescriptionActiveMatcher(String input) {
        Pattern changePrescriptionActivePattern = Pattern.compile(
                "^" + ACTIVATE_COMMAND + "\\s*x/" + INDEX_REGEX
                        + "$", Pattern.CASE_INSENSITIVE);
        return changePrescriptionActivePattern.matcher(input);
    }

    private static Matcher changePrescriptionInactiveMatcher(String input) {
        Pattern changePrescriptionInactivePattern = Pattern.compile(
                "^" + DEACTIVATE_COMMAND + "\\s*x/" + INDEX_REGEX
                        + "$", Pattern.CASE_INSENSITIVE);
        return changePrescriptionInactivePattern.matcher(input);
    }

    private void parseEditPrescription(int id, String type, String input) throws OneDocException {
        switch (type) {
        case "n":
            Pattern matchName = Pattern.compile("^" + PRESCRIPTION_NAME_REGEX + "$",
                    Pattern.CASE_INSENSITIVE);
            if (matchName.matcher(input).find()) {
                prescriptionList.edit(ui, id, input, "", "");
                storage.savePrescriptionData(prescriptionList);
            } else {
                throw new OneDocException("Prescription name is incorrectly formatted! "
                        + "The prescription name can be multiple words, including - and /");
            }
            break;
        case "d":
            Pattern matchDosage = Pattern.compile("^" + DOSAGE_REGEX + "$",
                    Pattern.CASE_INSENSITIVE);
            if (matchDosage.matcher(input).find()) {
                prescriptionList.edit(ui, id, "", input, "");
                storage.savePrescriptionData(prescriptionList);
            } else {
                throw new OneDocException("Dosage is incorrectly formatted! "
                        + "The dosage can be a number followed by an amount, i.e. 10 mg");
            }
            break;
        case "t":
            Pattern matchTimeInst = Pattern.compile("^" + TIME_INSTRUCTION_REGEX + "$",
                    Pattern.CASE_INSENSITIVE);
            if (matchTimeInst.matcher(input).find()) {
                prescriptionList.edit(ui, id, "", "", input);
                storage.savePrescriptionData(prescriptionList);
            } else {
                throw new OneDocException("Time instruction is incorrectly formatted! "
                        + "Please use words and numbers to describe the time instruction");
            }
            break;
        default:
            throw new OneDocException("Type is incorrectly formatted!"
                    + "Please use n/ for name, d/ for dosage, and t/ for time instruction");  
        }
    }
    
    public static boolean isPatientInputValid(String[] inputs) {
        if (inputs == null) {
            return false;
        }
        if (inputs.length != 4) {
            return false;
        }
        if (!inputs[2].equals("M") && !inputs[2].equals("F")) {
            return false;
        }
        return !inputs[0].isEmpty() && !inputs[1].isEmpty() && !inputs[3].isEmpty();
    }

    public static boolean isVisitInputValid(String[] inputs, PatientList patientList) {
        if (inputs == null) {
            return false;
        }
        if (inputs.length != 4) {
            return false;
        }
        if (inputs[0].isEmpty() || inputs[2].isEmpty() || inputs[3].isEmpty()) {
            return false;
        }
        return patientList.containsPatientID(inputs[0]);
    }

    public static boolean isPrescriptionInputValid(String[] inputs, PatientList patientList) {
        if (inputs == null) {
            return false;
        }
        if (inputs.length != 5) {
            return false;
        }
        if (!inputs[4].equals("T") && !inputs[4].equals("F")) {
            return false;
        }
        if (inputs[0].isEmpty() || inputs[1].isEmpty() || inputs[2].isEmpty() || inputs[3].isEmpty()) {
            return false;
        }
        return patientList.containsPatientID(inputs[0]);
    }

    private static final String MAIN_PATIENT_COMMAND = "1";
    private static final String MAIN_VISIT_COMMAND = "2";
    private static final String MAIN_PRESCRIPTION_COMMAND = "3";
    private static final String EXIT_COMMAND = "bye";
    private static final String VIEW_ALL_COMMAND = "viewall";
    private static final String HELP_COMMAND = "help";
    private static final String BACK_TO_MAIN_COMMAND = "main";
    private static final String ADD_COMMAND = "add";
    private static final String EDIT_COMMAND = "edit";
    private static final String RETRIEVE_PATIENT_COMMAND = "retrieve";
    private static final String DELETE_REASON_COMMAND = "deleteReason";
    private static final String VIEW_PATIENT_COMMAND = "viewPatient";
    private static final String VIEW_PATIENT_PRES_COMMAND = "viewPatientPres";
    private static final String VIEW_ACT_PATIENT_PRES_COMMAND = "viewActPatientPres";
    private static final String ACTIVATE_COMMAND = "activate";
    private static final String DEACTIVATE_COMMAND = "deactivate";
    private static final String VIEW_VISIT_COMMAND = "viewVisit";
    private static final String ID_REGEX = "\\s*(\\w+)\\s*";
    private static final String PATIENT_NAME_REGEX = "\\s*(\\w+\\s*\\w+|\\w+)\\s*";
    private static final String GENDER_REGEX = "\\s*(M|F)\\s*";
    private static final String DATE_REGEX = "\\s*(\\d\\d-\\d\\d-\\d\\d\\d\\d)\\s*";
    private static final String TIME_REGEX = "\\s*(\\d\\d:\\d\\d)\\s*";
    private static final String PRESCRIPTION_NAME_REGEX =
            "\\s*((?:[\\w.+\\\\\\/-]+\\s*)*[\\w.+\\\\\\/-]+)\\s*";
    private static final String DOSAGE_REGEX = "\\s*(\\d+(?:\\.\\d+)?\\s*\\w+)\\s*";
    private static final String TIME_INSTRUCTION_REGEX =
            "\\s*((?:[\\w.+\\\\\\/-]+\\s*)*[\\w.+\\\\\\/-]+)\\s*";
    private static final String OPTIONAL_REASON_REGEX =
            "(?:r/\\s*((?:\\w+\\s*)*\\w+))*\\s*";
    private static final String REASON_REGEX = "\\s*((?:\\w+\\s*)*\\w+)\\s*";
    private static final String INDEX_REGEX = "\\s*(\\d+)\\s*";

    private static final String ERROR_MESSAGE = "Your input is incorrect! Please format it as such:";
    private static final String HELP_MESSAGE = "\nIf you want to see the whole list of commands, type help!";
}
