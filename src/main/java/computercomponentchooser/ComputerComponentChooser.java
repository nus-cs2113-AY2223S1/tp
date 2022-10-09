package computercomponentchooser;

import java.util.Scanner;

public class ComputerComponentChooser {
    public static void main(String[] args) {
        Ui.startSession();

        Scanner in = new Scanner(System.in);

        BuildManager buildManager = new BuildManager();

        Ui.readLine(in);

        Ui.endSession();
    }

}
