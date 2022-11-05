import java.util.Scanner;

public class UI {
    public static final String FILE_WRITER_CREATION_ERROR_MESSAGE = "Error! File writer could not be created";
    public static final String WRITE_ERROR_MESSAGE = "Error! Data could not be written into data file!";
    public static final String FILE_WRITER_CLOSURE_ERROR_MESSAGE = "Error! File writer could not be closed!";
    public static final String MISSING_DATA_FILES_ERROR_MESSAGE = "Error! Data files could not be found!";
    public static final String PRESCRIPTION_LOADED_MESSAGE =
            "Valid prescription data found in prescription.txt loaded successfully!";
    public static final String INVALID_PRESCRIPTION_DATA_MESSAGE =
            "Invalid lines detected while reading prescription.txt! They will be discarded.";
    public static final String VISIT_LOADED_MESSAGE = "Valid visit data found in visit.txt loaded successfully!";
    public static final String INVALID_VISIT_DATA_MESSAGE =
            "Invalid lines detected while reading visit.txt! They will be discarded.";
    public static final String PATIENT_LOADED_MESSAGE = "Valid patient data found in patient.txt loaded successfully!";
    public static final String INVALID_PATIENT_DATA_MESSAGE =
            "Invalid lines detected while reading patient.txt! They will be discarded.";
    public static final String FILE_CREATION_ERROR_MESSAGE = "Error! Data files could not be created";
    public static final String PATIENT_RETRIEVED = "The patient with the supplied ID was found! Here are the details of the patient: ";
    private static Scanner scanner;

    protected static final String LOGO =
            System.lineSeparator()
            + "                        _"
            + System.lineSeparator()
            + "                       | |"
            + System.lineSeparator()
            + "  ___  _ __   ___    __| | ___   ___"
            + System.lineSeparator()
            + " / _ \\| '_ \\ / _ \\  / _` |/ _ \\ / __|"
            + System.lineSeparator()
            + "| (_) | | | |  __/ | (_| | (_) | (__"
            + System.lineSeparator()
            + " \\___/|_| |_|\\___|  \\__,_|\\___/ \\___|";
    private static final String WELCOME_MESSAGE = "Hello welcome to" + System.lineSeparator() + LOGO;
    private static final String MAIN_MENU = "Please choose one of the following options:" + System.lineSeparator()
            + "1 - Patients" + System.lineSeparator()
            + "2 - Visits" + System.lineSeparator()
            + "3 - Prescription" + System.lineSeparator()
            + "bye - Quit OneDoc";
    private static final String EXIT_MESSAGE = "Goodbye!";

    // General Invalid Error Message
    private static final String INVALID_MAIN_MENU_COMMAND_MESSAGE = "Incorrect input. Please type 1, 2, 3 or bye";
    private static final String INDEX_OUT_OF_RANGE_MESSAGE = "The index number is out of range. Try again.";

    // Patient
    private static final String PATIENT_START_MESSAGE = "This is the Patient Main Menu!";
    private static final String PATIENT_MAIN_MENU = "List of commands: "
            + UI.PATIENT_ADD
            + UI.PATIENT_VIEW_ALL
            + UI.PATIENT_RETRIEVE
            + UI.PATIENT_EDIT
            + UI.RETURN_TO_MAIN
            + UI.EXIT_PROGRAM;

    // Prescription
    private static final String NO_PRESCRIPTION_MESSAGE = "There are currently no prescriptions in the record.";
    private static final String PRESCRIPTION_START_MESSAGE = "This is the Prescription Main Menu!";
    private static final String PRESCRIPTION_MAIN_MENU = "List of commands:"
                    + UI.PRESCRIPTION_ADD
                    + UI.PRESCRIPTION_EDIT
                    + UI.PRESCRIPTION_VIEW_ALL
                    + UI.PRESCRIPTION_VIEW_PATIENT
                    + UI.PRESCRIPTION_VIEW_ACTIVE
                    + UI.PRESCRIPTION_CHANGE_ACTIVE
                    + UI.PRESCRIPTION_CHANGE_INACTIVE
                    + UI.RETURN_TO_MAIN
                    + UI.EXIT_PROGRAM;
    private static final String NO_MATCHING_PRESCRIPTION_MESSAGE = "There are currently no prescriptions from this "
            + "patient.";
    private static final String NO_MATCHING_ACTIVE_PRESCRIPTION_MESSAGE = "There are currently no active prescriptions "
            + "from this patient.";

    public static final String DUPLICATE_PRESCRIPTION_MESSAGE = "The prescription is already existing.";
    private static final String PRESCRIPTION_INDEX_FORMAT = "Prescription #";
    private static final String ALL_PRESCRIPTIONS_HEADING = "Here are all the prescriptions:";
    public static final String ALL_ACTIVE_PRESCRIPTIONS_HEADING = "Here are all the active prescriptions:";

    // Visit
    private static final String VISIT_START_MESSAGE = "This is the Visits Main Menu!";
    private static final String VISIT_MAIN_MENU = "List of commands:"
                    + UI.VISIT_ADD
                    + UI.VISIT_EDIT
                    + UI.VISIT_DELETE_REASON
                    + UI.VISIT_VIEW_ALL
                    + UI.VISIT_VIEW_PATIENT
                    + UI.VISIT_VIEW
                    + UI.RETURN_TO_MAIN
                    + UI.EXIT_PROGRAM;

    public static final String DUPLICATE_VISIT_MESSAGE =
            "There is already an existing visit record with the same ID, date, and time."
                    + System.lineSeparator()
                    + "Use editReason if you're trying to add a reason to existing visit or"
                    + System.lineSeparator()
                    + "deleteReason if you're trying to delete reason for an existing visit."
                    + System.lineSeparator()
                    + "Else, please try adding another visit with unique details.";

    public UI() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printMainMenu() {
        System.out.println(MAIN_MENU);
    }

    public void printInvalidMainMenuErrorMessage() {
        System.out.println(INVALID_MAIN_MENU_COMMAND_MESSAGE);
    }

    public void printIndexOutOfRangeErrorMessage() {
        System.out.println(INDEX_OUT_OF_RANGE_MESSAGE);
    }

    /**
     * Print welcome message on start of the sub menu.
     * @param mainMenuState is an enum representing which sub menu the program is at.
     */
    public void printSubMenuStart(MainMenuState mainMenuState) {
        switch (mainMenuState) {
        case PATIENT:
            printPatientStartMessage();
            break;
        case VISIT:
            printVisitStartMessage();
            break;
        case PRESCRIPTION:
            printPrescriptionStartMessage();
            break;
        default:
            break;
        }
    }

    /**
     * Print the corresponding sub menu according to the mainMenuState.
     * @param mainMenuState is an enum representing which sub menu the program is at.
     */
    public void printSubMenu(MainMenuState mainMenuState) {
        switch (mainMenuState) {
        case PATIENT:
            printPatientMenu();
            printLine();
            break;
        case VISIT:
            printVisitsMenu();
            printLine();
            break;
        case PRESCRIPTION:
            printPrescriptionMenu();
            printLine();
            break;
        default:
            break;
        }
    }

    private void printPatientStartMessage() {
        System.out.println(PATIENT_START_MESSAGE);
    }

    private void printPatientMenu() {
        System.out.println(PATIENT_MAIN_MENU);
    }

    public void printMessageAndObject(String object, String message, int index, String type) {
        System.out.println(message);
        printObject(object, index, type);
        printLine();
    }

    public void printObject(String object, int index, String type) {
        printLine();
        System.out.println("\t" + type + " #" + (index + 1));
        System.out.println(object);
    }

    public static void printErrorMessage(String errorMessage) {
        printLine();
        System.out.println("\t" + errorMessage);
        printLine();
    }

    private void printVisitStartMessage() {
        System.out.println(VISIT_START_MESSAGE);
    }

    private void printVisitsMenu() {
        System.out.println(VISIT_MAIN_MENU);
    }

    private void printPrescriptionStartMessage() {
        System.out.println(PRESCRIPTION_START_MESSAGE);
    }

    private void printPrescriptionMenu() {
        System.out.println(PRESCRIPTION_MAIN_MENU);
    }

    public void printNoPrescriptionMessage() {
        System.out.println(NO_PRESCRIPTION_MESSAGE);
    }

    public void printViewAllPrescriptionsMessage() {
        System.out.println(ALL_PRESCRIPTIONS_HEADING);
        printLine();
    }

    public void printViewAllActivePrescriptionsMessage() {
        System.out.println(ALL_ACTIVE_PRESCRIPTIONS_HEADING);
        printLine();
    }

    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    // Patient commands
    public static final String PATIENT_ADD = "\n* To add a patient: add n/[name] g/[M/F] d/[DOB] i/[ID]"
            + "\n\tn - The name should be one or two space-separated words"
            + "\n\tg - The gender should be one letter, M or F"
            + "\n\td - The date of birth should be formatted as DD-MM-YYYY"
            + "\n\ti - The id can be a sequence of numbers or letters without any spaces";
    public static final String PATIENT_EDIT = "\n* To edit a patient's information: "
            + "edit i/[ID] (n/[name] or g/[M/F] or d/[DOB])"
            + "\n\tn/g/d - Please edit only one aspect of a patient at a time";
    public static final String PATIENT_RETRIEVE = "\n* To retrieve information about a specific patient:"
            + " retrieve i/[ID]";
    public static final String PATIENT_VIEW_ALL = "\n* To list all patients: viewall";

    // Visit commands
    public static final String VISIT_ADD = "\n* To add a visit: add i/[ID] d/[date] t/[time] (optional: r/[reason])"
            + "\n\td - The date should be formatted as DD-MM-YYYY"
            + "\n\tt - The time should be formatted as HH:MM"
            + "\n\tr - The reason is optional, and can be any number of words";
    public static final String VISIT_EDIT = "\n* To edit a visit's reason: edit x/[index] r/[reason]"
            + "\n\tx - The index should be a displayed number next to the visit"
            + "\n\tr - The reason can be added or edited with any number of words";
    public static final String VISIT_DELETE_REASON = "\n* To delete a visit's reason: deleteReason x/[index]";
    public static final String VISIT_VIEW_ALL = "\n* To list all visits: viewall";
    public static final String VISIT_VIEW_PATIENT = "\n* To list all visits of one patient: viewPatient i/[ID]";
    public static final String VISIT_VIEW = "\n* To view a patient's specific visit: viewVisit x/[index]";
    public static final String PATIENT_ADDED = "Ok! I've added a patient! The patient's details are as follows:";
    public static final String PATIENT_EDITED = "Alright, I've modified the details of the patient! \nHere are the "
            + "new details of the patient: ";
    public static final String ADD_VISIT = "You have added a visit!";
    public static final String EDIT_VISIT_REASON = "You have edited reason for the visit. Here's the updated visit!";
    public static final String DELETE_VISIT_REASON = "You have deleted the reason for the visit. Here's the "
            + "updated visit!";
    public static final String ADD_PRESCRIPTION = "You have added a prescription!";
    public static final String EDIT_PRESCRIPTION = "You have edited the prescription!";
    public static final String DEACTIVATE_PRESCRIPTION = "Ok, I've deactivated the prescription below:";
    public static final String ACTIVATE_PRESCRIPTION = "You have edited the prescription!";

    // Prescription commands
    public static final String PRESCRIPTION_ADD = "\n* To add a prescription: add i/ID "
            + "n/[name] d/[dosage] t/[time interval]"
            + "\n\tn - The prescription name can be multiple words, including -"
            + "\n\td - The dosage should be a number followed by an amount, i.e. 10 mg"
            + "\n\tt - The time instruction explains how to take the dosage,"
            + "with any number of words";
    public static final String PRESCRIPTION_EDIT = "\n* To edit a prescription: edit x/[index] "
            + "(n/[name] or d/[dosage] or t/[time interval])"
            + "\n\tn/d/t - Please edit only one aspect of a prescription at a time";

    public static final String PRESCRIPTION_VIEW_ALL = "\n* To list all prescriptions: viewall";

    public static final String PRESCRIPTION_VIEW_PATIENT = "\n* To list all prescriptions of one patient: "
            + "viewPatientPres i/[ID]";

    public static final String PRESCRIPTION_VIEW_ACTIVE = "\n* To list all active prescriptions of one "
            + "patient: viewActPatientPres i/[ID]";

    public static final String PRESCRIPTION_CHANGE_ACTIVE = "\n* To change a prescription record to be "
            + "active: activate x/[index]";

    public static final String PRESCRIPTION_CHANGE_INACTIVE = "\n* To change a prescription record to be "
            + "inactive: deactivate x/[index]";
    public static final String RETURN_TO_MAIN = "\n* To return to main menu: main";

    public static final String EXIT_PROGRAM = "\n* To quit OneDoc: bye";
    public static final String PRESCRIPTION = "Prescription";
    public static final String VISIT = "Visit";
    public static final String PATIENT = "Patient";

    /**
     * Print message that there are no prescription from the patient.
     */
    public void printNoMatchingPrescriptionMessage() {
        System.out.println(NO_MATCHING_PRESCRIPTION_MESSAGE);
    }

    /**
     * Print message that there are no active prescription from the patient.
     */
    public void printNoMatchingActivePrescriptionMessage() {
        System.out.println(NO_MATCHING_ACTIVE_PRESCRIPTION_MESSAGE);
    }

    /**
     * Print the prescription details with index.
     * @param index the index number starting from 1 onward
     * @param prescription a String that represents the object
     */
    public void printPrescriptionWithIndex(int index, String prescription) {
        System.out.println("\t" + PRESCRIPTION_INDEX_FORMAT + index);
        System.out.println(prescription);
        printLine();
    }
}

