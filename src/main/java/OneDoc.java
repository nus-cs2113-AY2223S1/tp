public class OneDoc {

    protected PatientList patientsList;
    protected VisitList visitsList;
    protected PrescriptionList prescriptionsList;
    protected static Parser parser;
    private static MainMenuState mainMenuState;

    public OneDoc() {
        patientsList = new PatientList();
        visitsList = new VisitList();
        prescriptionsList = new PrescriptionList();
        parser = new Parser(patientsList, visitsList, prescriptionsList);

        mainMenuState = MainMenuState.INVALID;
    }

    public static void main(String[] args) {
        new OneDoc();
        run();
    }

    public static void subMenuRun() {
        SubMenuState subMenuState = SubMenuState.IN_SUB_MENU;

        UI.printSubMenu(mainMenuState);
        while (true) {
            String input = UI.readLine();
            switch (mainMenuState) {
            case PATIENT:
                subMenuState = parser.patientParser(input);
                break;
            case VISIT:
                subMenuState = parser.visitParser(input);
                break;
            case PRESCRIPTION:
                subMenuState = parser.prescriptionParser(input);
                break;
            default:
                break;
            }

            if (subMenuState == SubMenuState.BACK_TO_MAIN) {
                break;
            } else if (subMenuState == SubMenuState.EXIT) {
                mainMenuState = MainMenuState.EXIT;
                break;
            }
        }
    }

    public static void mainMenuRun() {
        while (mainMenuState != MainMenuState.EXIT) {
            Messages.printMainMenu();
            String input = UI.readLine();
            mainMenuState = parser.mainMenuParser(input);

            if (mainMenuState == MainMenuState.EXIT) {
                break;
            } else if (mainMenuState == MainMenuState.INVALID) {
                continue;
            }

            subMenuRun();
        }
    }

    public static void run() {
        Messages.welcomeMessage();
        mainMenuRun();
        Messages.printExitMessage();
    }
}

