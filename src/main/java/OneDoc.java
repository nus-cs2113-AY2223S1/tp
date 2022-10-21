public class OneDoc {

    private static UI ui;
    protected PatientList patientsList;
    protected VisitList visitsList;
    protected PrescriptionList prescriptionsList;
    protected static Parser parser;
    protected Storage storage;
    private static MainMenuState mainMenuState;

    public OneDoc() {
        ui = new UI();
        patientsList = new PatientList();
        visitsList = new VisitList();
        prescriptionsList = new PrescriptionList();
        parser = new Parser(patientsList, visitsList, prescriptionsList, ui);
        storage = new Storage();
        storage.loadData(patientsList, visitsList, prescriptionsList);
        mainMenuState = MainMenuState.INVALID;
    }

    public static void main(String[] args) {
        new OneDoc();
        run();
    }

    public static void subMenuRun() {
        SubMenuState subMenuState = SubMenuState.IN_SUB_MENU;

        ui.printSubMenu(mainMenuState);
        while (true) {
            String input = ui.readLine();
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
            ui.printMainMenu();
            String input = ui.readLine();
            mainMenuState = parser.mainMenuParser(input);

            if (mainMenuState == MainMenuState.EXIT) {
                break;
            } else if (mainMenuState == MainMenuState.INVALID) {
                ui.printInvalidMainMenuErrorMessage();
                continue;
            }

            subMenuRun();
        }
    }

    public static void run() {
        ui.printWelcomeMessage();
        mainMenuRun();
        ui.printExitMessage();
    }
}

