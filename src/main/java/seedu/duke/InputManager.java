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
        BasicUi.showEnterUsernamePrompt();
        String username = receiveInputLine();
        while (existingUsernames.contains(username)) {
            BasicUi.showInvalidUsernamePrompt();
            username = receiveInputLine();
        }
        BasicUi.showEnterPasswordPrompt();
        String password = receiveInputLine();
        while (password.length() < 8) {
            BasicUi.showInvalidPasswordPrompt();
            password = receiveInputLine();
        }
        String[] registerInputs = new String[2];
        registerInputs[0] = username;
        registerInputs[1] = password;
        return registerInputs;
    }

    public static String[] receiveLoginInputs() {
        BasicUi.showEnterUsernamePrompt();
        String username = receiveInputLine();
        BasicUi.showEnterPasswordPrompt();
        String password = receiveInputLine();
        String[] loginInputs = new String[2];
        loginInputs[0] = username;
        loginInputs[1] = password;
        return loginInputs;
    }

}
