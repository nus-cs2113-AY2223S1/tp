public class Messages {
    protected static final String logo = "ıllıllı OneDoc ıllıllı";
    private static final String welcomeMessage = "Hello welcome to\n"
            + logo;
    private static final String mainMenuMessage = "Please choose one+ "
            + " of the following options:\n"
            + "1 - Patients\n"
            + "2 - Visits\n"
            + "3 - Prescription";

    public static void welcomeMessage() {
        System.out.println(welcomeMessage);
        System.out.println(mainMenuMessage);
    }

    public static void printPatientMenuMessage() {

    }

    public static void printVisitsMenuMessage() {

    }

    public static void printPrescriptionMenuMessage() {

    }

    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
}
