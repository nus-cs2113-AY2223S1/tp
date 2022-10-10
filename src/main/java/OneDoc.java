import java.util.ArrayList;
import java.util.Scanner;

public class OneDoc {

    protected PatientList patientsList;
    protected VisitList visitsList;
    protected PrescriptionList prescriptionsList;
    protected static Parser parser;

    public OneDoc() {
        patientsList = new PatientList();
        visitsList = new VisitList();
        prescriptionsList = new PrescriptionList();
        parser = new Parser(patientsList, visitsList);
    }

    public static void main(String[] args) {
        new OneDoc().run();
    }

    public static void run() {
        Messages.welcomeMessage();
        parser.mainMenuParser();
    }
}

