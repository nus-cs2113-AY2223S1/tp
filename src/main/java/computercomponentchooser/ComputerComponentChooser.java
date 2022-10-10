package computercomponentchooser;

public class ComputerComponentChooser {

    static BuildManager buildManager = new BuildManager();

    public static void main(String[] args) {
        Ui.startSession();

        Ui.readLine();

        Ui.endSession();
    }

}
