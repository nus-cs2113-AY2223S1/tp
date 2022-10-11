package seedu.duke;

import java.util.Scanner;

public class InputManager {
    private static final String Null = null;
    private static Scanner scanner = new Scanner(System.in);

    public static String receiveInputLine(){
        String inputLine = scanner.nextLine();
        while (inputLine.trim().isEmpty()) {
            inputLine = scanner.nextLine();
        }
        return inputLine;
    }

    public static String receiveRegisterInputs(){
        // need implementation
        return Null;
    }

    public static String[] receiveLoginInputs(){
        Ui.showEnterUsernamePrompt();
        String username = receiveInputLine();
        Ui.showEmptyLine();
        Ui.showEnterPasswordPrompt();
        String password = receiveInputLine();
        String[] loginInputs = new String[2];
        loginInputs[0] = username;
        loginInputs[1] = password;
        return loginInputs;
    }

}
