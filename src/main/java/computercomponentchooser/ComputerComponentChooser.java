package computercomponentchooser;

public class ComputerComponentChooser {
    public static void main(String[] args) {
        Ui.startSession();

        BuildManager buildManager = new BuildManager();

        Ui.readLine();

        Ui.endSession();
    }

}
