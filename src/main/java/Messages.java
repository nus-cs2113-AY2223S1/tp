public class Messages {
    protected static final String logo = "ıllıllı OneDoc ıllıllı";
    private static final String welcomeMessage = "Hello welcome to\n"
            + logo;
    private static final String mainMenuMessage = "Please choose one+ "
            + " of the following options:\n"
            + "1 - Patients\n"
            + "2 - Visits\n"
            + "3 - Prescription";

    // Prescription
    public static final String NO_PRESCRIPTION_MESSAGE = "There are currently no prescriptions in the record.";
    public static final String PRESCRIPTION_MAIN_MENU =
            "This is the Prescription Main Menu!" + System.lineSeparator()
            + "List of commands:" + System.lineSeparator()
            + "To add a prescription: add i/ID n/[name] d/[dosage] t/[time interval]" + System.lineSeparator()
            + "To edit a prescription: edit i/[index] (n/[name] or d/[dosage] or t/[time interval])"
            + System.lineSeparator()
            + "To view all prescription records: 'viewAll'";

    public static final String VISITS_MAIN_MENU =
            "This is the Visits Main Menu!" + System.lineSeparator()
            + "List of commands:" + System.lineSeparator()
            + "To add a visit: add i/[ID] d/[date] t/[time] r/[reason]" + System.lineSeparator()
            + "To edit a visit's reason: edit i/[ID] r/[reason]"
            + System.lineSeparator()
            + "To view all visit records: 'viewAll'";
    public static final String INDEX_OUT_OF_RANGE_MESSAGE = "The index number is out of range. Try again.";

    public static void welcomeMessage() {
        System.out.println(welcomeMessage);
        System.out.println(mainMenuMessage);
    }

    public static void printPatientMenuMessage() {
        System.out.println("This is the Patient Main Menu!");
        System.out.println("List of commands: ");
        System.out.println("To add a patient: 'add n/[name] g/[M/F] d/[DOB] i/[ID]'");
        System.out.println("To list all patients: 'list'");
        System.out.println("To retrieve information about a specific patient: 'retrieve i/[ID]'");
        System.out.println("To edit a patient's records: 'edit i/ID (n/[name] or g/[M/F] or d/[DOB])'");
    }


    public static void printVisitsMenuMessage() {
        System.out.println(VISITS_MAIN_MENU);
    }

    public static void printAddVisitMessage(String visitString) {
        System.out.println("You have added a visit!");
        printLine();
        System.out.println(visitString);
        printLine();
    }

    public static void printEditVisitReasonMessage(String visitString) {
        System.out.println("You have edited reason for the visit. Here's the updated visit!");
        printLine();
        System.out.println(visitString);
        printLine();
    }

    public static void printViewAllVisitsMessage() {
        System.out.println("Here are all the visits:");
    }

    public static void printPrescriptionMenuMessage() {
        System.out.println(PRESCRIPTION_MAIN_MENU);
    }

    public static void printAddPrescriptionMessage(String prescriptionString) {
        System.out.println("You have added a prescription!");
        printLine();
        System.out.println(prescriptionString);
        printLine();
    }

    public static void printEditPrescriptionMessage(String prescriptionString) {
        System.out.println("You have edited the prescription!");
        printLine();
        System.out.println(prescriptionString);
        printLine();
    }

    public static void printViewAllPrescriptionsMessage() {
        System.out.println("Here are all the prescriptions:");
    }

    public static void printExitMessage() {
        System.out.println("Goodbye!");
    }

    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void printPatientAddedMessage(Patient patient) {
        System.out.println("Ok! I've added a patient! The patient's details are as follows:");
        printLine();
        System.out.println(patient);
        printLine();
    }

    public static void printPatientEditedMessage(Patient patient) {
        System.out.print("Alright, I've modified the details of the patient!");
        System.out.println("Here are the new details of the patient: ");
        printLine();
        System.out.println(patient);
        printLine();
    }

}
