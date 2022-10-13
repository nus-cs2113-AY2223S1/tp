package seedu.duke;

import java.util.List;
import java.util.Scanner;

public class InputManager {
    private static Scanner scanner = new Scanner(System.in);

    public static String receiveInputLine() {
        String inputLine = scanner.nextLine();
        while (inputLine.trim().isEmpty()) {
            inputLine = scanner.nextLine();
        }
        return inputLine;
    }

    public static String[] receiveValidRegisterInputs(List<String> existingUsernames) {
        Ui.showEnterUsernamePrompt();
        String username = receiveInputLine();
        while (existingUsernames.contains(username)) {
            Ui.showInvalidUsernamePrompt();
            username = receiveInputLine();
        }
        Ui.showEnterPasswordPrompt();
        String password = receiveInputLine();
        while (password.length() < 8) {
            Ui.showInvalidPasswordPrompt();
            password = receiveInputLine();
        }
        String[] registerInputs = new String[2];
        registerInputs[0] = username;
        registerInputs[1] = password;
        return registerInputs;
    }

    public static String[] receiveLoginInputs() {
        Ui.showEnterUsernamePrompt();
        String username = receiveInputLine();
        Ui.showEnterPasswordPrompt();
        String password = receiveInputLine();
        String[] loginInputs = new String[2];
        loginInputs[0] = username;
        loginInputs[1] = password;
        return loginInputs;
    }

}
