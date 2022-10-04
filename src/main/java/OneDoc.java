import java.util.ArrayList;
import java.util.Scanner;

public class OneDoc {
    protected PatientList patientsList = new PatientList();
    public static void main(String[] args) {
        Messages.welcomeMessage();
        Parser.mainMenuParser();
    }
}
