package seedu.duke;

public class BasicUi {
    private static final String INDENTATION = "    ";

    public static void showWelcomeMessage() {
        final String LOGO = "\n"
                +
                "    _____                                              __  __                                         \n"
                +
                "   / ____|                                            |  \\/  |                                        \n"
                +
                "  | |     _   _  _ __  _ __  ___  _ __    ___  _   _  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \n"
                +
                "  | |    | | | || '__|| '__|/ _ \\| '_ \\  / __|| | | | | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\n"
                +
                "  | |____| |_| || |   | |  |  __/| | | || (__ | |_| | | |  | || (_| || | | || (_| || (_| ||  __/| |   \n"
                +
                "   \\_____|\\__,_||_|   |_|   \\___||_| |_| \\___| \\__, | |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|   \n"
                +
                "                                                __/ |                                __/ |            \n"
                +
                "                                               |___/                                |___/             \n";
        showStandardOutput(INDENTATION + "Welcome to " + LOGO);
    }

    public static void showExitMessage() {
        final String EXIT_MESSAGE = "Bye. Thank you for your support!";
        showStandardOutput(INDENTATION + EXIT_MESSAGE);
    }

    public static void showExceptionMessage(String exceptionMessage) {
        showStandardOutput(INDENTATION + exceptionMessage);
    }

    public static void showEnterUsernamePrompt() {
        final String MESSAGE = "Please enter your username: ";
        System.out.print(MESSAGE);
    }

    public static void showEnterPasswordPrompt() {
        BasicUi.showEmptyLine();
        final String MESSAGE = "Please enter your password: ";
        System.out.print(MESSAGE);
    }

    public static void showInvalidUsernamePrompt() {
        showEmptyLine();
        final String MESSAGE = "Username duplicated, Please enter another username: ";
        System.out.print(MESSAGE);
    }

    public static void showInvalidPasswordPrompt() {
        showEmptyLine();
        final String MESSAGE = "Password too short, Please enter another password: ";
        System.out.print(MESSAGE);
    }

    public static void showCurrencyEntry() {
        final String MESSAGE = "Entering the currency portal";
        showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showCurrencyOptions() {
        final String MESSAGE = "Would you like to get information about a currency/currencies or check a conversion rate?"
                +
                "\n"
                +
                INDENTATION
                +
                "You can do the following commands : info, conversion, exit, list.";

        System.out.println(INDENTATION + MESSAGE);
    }

    public static void showCurrencyInfo(CurrencyStructure currency) {
        final String[] MESSAGES = { "Abbreviation: " + currency.getAbbrName(), 
                "Full Name: " + currency.getFullName(),
                "Symbol: " + currency.getSymbol(),
                "Exchange rate with USD: " + String.format("%.2f", currency.getRate())};
        final String MESSAGE = assembleMultipleLinesOutput(MESSAGES);
        showStandardOutput(MESSAGE);
    }

    public static void showIncorrectCurrencyEntry() {
        final String MESSAGE = "Incorrect currency command, enter EXIT to leave currency portal";
        showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showIncorrectCurrencyInfo(String s) {
        final String MESSAGE = "Incorrect currency command, please enter in the format: " + s + " symbol";
        showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showIncorrectCurrencyConversion() {
        final String MESSAGE = "Incorrect currency command, please enter one of these formats: conversion symbol or conversion symbol symbol";
        showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showStandardOutput(String output) {
        printSplitLine();
        System.out.println();
        System.out.println(output);
        printSplitLine();
        System.out.println();
    }

    public static void showEmptyLine() {
        System.out.println();
    }

    public static String assembleMultipleLinesOutput(String[] lines) {
        String output = "";
        for (int i = 0; i < lines.length - 1; i++) {
            output += INDENTATION + lines[i] + "\n";
        }
        output += INDENTATION + lines[lines.length - 1];
        return output;
    }

    private static void printSplitLine() {
        System.out.println(INDENTATION + "____________________________________________________________");
    }
}
