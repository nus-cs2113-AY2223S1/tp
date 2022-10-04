import java.util.Scanner;

public class Parser {
    // todo add exit message
    private final static String exitMessage = "";
    private final static String patients = "1";
    private final static String visits = "2";
    private final static String prescription = "3";

    public static void mainMenuParser() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        if (!line.equals(exitMessage)){
            // todo change
//            String line = checkInputForMainMenu(line);
            switch (line){
                case patients: {
                    Messages.printPatientMenuMessage();
//                    patientParser();
                    break;
                }
                case visits: {
                    Messages.printVisitsMenuMessage();
//                    visitsParser();
                    break;
                }
                case prescription: {
                    Messages.PrintMedicineMenuMessage();
//                    prescriptionParser();
                    break;
                }

            }
        }
    }

    //todo add:
//    private static String  checkInputForMainMenu(String input);
//    public static void patientParser();
//    public static void visitsParser();
//    public static void medicineParser();

    public static void addPatientParser(String input) {
        // add regex check
    }



}
