package seedu.duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import seedu.duke.command.Command;
import seedu.duke.command.Database;
import seedu.duke.command.DatabaseStorage;
import seedu.duke.command.ViewCommand;
import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.module.Module;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.module.ModuleMapping;
import seedu.duke.university.University;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserUniversityListManager;
import seedu.duke.userstorage.UserStorageParser;

public class Duke {

    private static boolean shouldExit = false;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.err.close();
        System.out.println(Ui.greetUser());
        System.out.println(Ui.printCommands());
        DatabaseStorage.loadDatabase();
        UserUniversityListManager userUniversityListManager = UserStorageParser.getSavedLists();

        while (!shouldExit) {
            try {
                String userInput = Ui.getUserInput();
                Command newUserCommand = CommandParser.getUserCommand(userInput);
                switch (newUserCommand.getCommandType()) {
                case EXIT:
                    exit();
                    break;
                case HELP:
                    System.out.println(Ui.printCommands());
                    break;
                case DELETE:
                    try {
                        if (newUserCommand.getModuleCode() == null) {
                            userUniversityListManager.deleteList(newUserCommand.getUniversityName());
                        } else {
                            userUniversityListManager.deleteModule(newUserCommand.getUniversityName(),
                                    newUserCommand.getModuleCode());
                        }
                        UserStorageParser.storeCreatedLists(userUniversityListManager);
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case CREATE:
                    userUniversityListManager.createList(newUserCommand.getUniversityName());
                    break;
                case VIEW:
                    try {
                        ViewCommand viewCommand = (ViewCommand) newUserCommand;
                        if (viewCommand.getViewOption().equals("LISTS")) {
                            userUniversityListManager.displayAll();
                        } else if (viewCommand.getViewOption().equals("DELETE")) {
                            userUniversityListManager.getUserDeletedModules().displayAll();
                        }else if (viewCommand.getViewOption().equals("MODULES")) {
                            ArrayList<ModuleMapping> moduleMappings = Database.getModuleMappings();
                            Ui.printModulesInDatabase(moduleMappings);
                        } else if (viewCommand.getViewOption().equals("UNIVERSITY")) {
                            userUniversityListManager.displayUniversity(viewCommand.getUniversityName());
                        } else if (viewCommand.getViewOption().equals("UNIVERSITIES")) {
                            ArrayList<University> universities = Database.getUniversities();
                            Ui.printUniversitiesInDatabase(universities);
                        } else if (viewCommand.getViewOption().equals("DATABASE")) {
                            ArrayList<ModuleMapping> moduleMappings = Database.getModuleMappings().stream()
                                    .filter(moduleMapping -> moduleMapping.getPartnerUniversityModule()
                                            .getUniversity().getName().equals(viewCommand.getUniversityName()))
                                    .collect(Collectors.toCollection(ArrayList::new));
                            Ui.printUniversityName(viewCommand.getUniversityName());
                            Ui.printModulesInDatabase(moduleMappings);
                        }
                    } catch (InvalidUserCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ADD:
                    try {
                        ModuleMapping moduleMapping = Database.findModuleMapping(newUserCommand.getModuleCode());
                        Module puModule = moduleMapping.getPartnerUniversityModule();
                        Module nusModule = moduleMapping.getNusModule();
                        UserModuleMapping userModuleToAdd = new UserModuleMapping(puModule.getCode(),
                                puModule.getTitle(), nusModule.getCode(), nusModule.getTitle(), nusModule.getCredit(),
                                puModule.getCredit(), puModule.getUniversity().getName(),
                                puModule.getUniversity().getCountry());
                        userUniversityListManager.addModule(newUserCommand.getUniversityName(), userModuleToAdd);
                        UserStorageParser.storeCreatedLists(userUniversityListManager);
                    } catch (ModuleNotFoundException | NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
                }
            } catch (InvalidUserCommandException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private static void exit() {
        setShouldExit();
    }

    private static void setShouldExit() {
        shouldExit = true;
    }
}