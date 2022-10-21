package seedu.duke.authentication;

import seedu.duke.BasicUi;

public class AuthenticationUi {
    private static final String INDENTATION = "    ";

    // Maybe be improved
    public static void showPromptInfo() {
        String promptInfo = "Would you like to register, login, or work with currencies?";
        System.out.println(INDENTATION + promptInfo);
        System.out.println();
    }

    public static void showRegisterInfo() {
        final String MESSAGE = "Nice, we will be creating you a new wallet right away, but first of all, \n"
                +
                INDENTATION
                +
                "please first add your username and then a password for your wallet.";
        BasicUi.showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showRegisterSuccessInfo() {
        final String MESSAGE = "Nice, a new wallet has been created for you, \n"
                +
                INDENTATION
                +
                "You can now login to it";
        BasicUi.showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showLoginInfo() {
        final String MESSAGE = "Please enter your username and password to login your wallet";
        BasicUi.showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showLoginResults(Boolean isLoginSuccess) {
        final String MESSAGE = isLoginSuccess ? "Login successfully."
                : "Username or Password not correct. Please try again.";
        BasicUi.showStandardOutput(INDENTATION + MESSAGE);
    }
}
