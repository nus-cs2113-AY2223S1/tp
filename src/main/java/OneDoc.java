import java.util.ArrayList;
import java.util.Scanner;

public class OneDoc {

    protected PatientList patientsList;
    protected static Parser parser;

    public OneDoc() {
        patientsList = new PatientList();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new OneDoc().run();
    }

    public static void run() {
        Messages.welcomeMessage();
        parser.mainMenuParser();
    }
}

