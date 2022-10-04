import java.util.Scanner;

public class Parser {
    // todo add exit message
    private final String exitMessage = "";
    private final String patients = "1";
    private final String visits = "2";
    private final String prescription = "3";

    public void mainMenuParser() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        if (!line.equals(exitMessage)) {
            // todo change add to each case accordingly
            // String line = checkInputForMainMenu(line);
            switch (line) {
            case patients:
                Messages.printPatientMenuMessage();
                break;
            case visits:
                Messages.printVisitsMenuMessage();
                break;
            case prescription:
                Messages.printMedicineMenuMessage();
                break;
            default:
                break;
            }
        }
    }

    //todo add:
    //    private static String  checkInputForMainMenu(String input);
    //    public static void patientParser();
    //    public static void visitsParser();
    //    public static void medicineParser();
    //    public void addPatientParser(String input) {}

}
