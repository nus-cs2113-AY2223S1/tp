package seedu.duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import seedu.duke.command.Command;
import seedu.duke.command.Database;
import seedu.duke.command.DatabaseStorage;
import seedu.duke.command.ListCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.FavouriteCommand;
import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.exceptions.UniversityNotFoundException;
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
        System.out.print(Ui.greetUser());
        System.out.print(Ui.printCommands());
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
                    System.out.print(Ui.printCommands());
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
                        } else if (viewCommand.getViewOption().equals("UNIVERSITY")) {
                            userUniversityListManager.displayUniversity(viewCommand.getUniversityName());
                        }
                    } catch (InvalidUserCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ADD:
                    try {
                        ModuleMapping moduleMapping = Database.findPuMapping(newUserCommand.getModuleCode());
                        Module puModule = moduleMapping.getPartnerUniversityModule();
                        Module nusModule = moduleMapping.getNusModule();
                        UserModuleMapping userModuleToAdd = new UserModuleMapping(puModule.getCode(),
                                puModule.getTitle(), nusModule.getCode(), nusModule.getTitle(),
                                nusModule.getCredit(), puModule.getCredit(), puModule.getUniversity().getName(),
                                puModule.getUniversity().getCountry());
                        userUniversityListManager.addModule(newUserCommand.getUniversityName(), userModuleToAdd);
                        UserStorageParser.storeCreatedLists(userUniversityListManager);
                    } catch (ModuleNotFoundException | NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case LIST:
                    try {
                        ListCommand listCommand = (ListCommand) newUserCommand;

                        if (listCommand.getListOption().equals("UNIVERSITIES")) {
                            ArrayList<University> universities = Database.getUniversities();
                            Ui.printUniversitiesInDatabase(universities);
                        } else if (listCommand.getListOption().equals("MODULES")) {
                            ArrayList<ModuleMapping> moduleMappings = Database.getModuleMappings();
                            Ui.printMappings(moduleMappings);
                        } else if (listCommand.getListOption().equals("module")) {
                            ArrayList<ModuleMapping> moduleMappings = Database
                                    .findNusMapping(newUserCommand.getModuleCode());
                            Ui.printMappings(moduleMappings);
                        } else if (listCommand.getListOption().equals("university")) {
                            ArrayList<ModuleMapping> moduleMappings = Database
                                    .findUniversityMapping(newUserCommand.getUniversityName());
                            Ui.printMappings(moduleMappings);
                        }
                    } catch (ModuleNotFoundException | UniversityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case FAVOURITE:
                    try {
                        FavouriteCommand favouriteCommand = (FavouriteCommand) newUserCommand;
                        if (favouriteCommand.getFavouriteOption().equals("VIEW")) {
                            userUniversityListManager.displayFavourites();
                        } else if (favouriteCommand.getFavouriteOption().equals("ADD")) {
                            String universityName = favouriteCommand.getUniversityName();
                            userUniversityListManager.addFavourite(universityName);
                            System.out.print(Ui.printFavouriteListAddedAcknowledgement(universityName));
                        } else if (favouriteCommand.getFavouriteOption().equals("DELETE")) {
                            String universityName = favouriteCommand.getUniversityName();
                            userUniversityListManager.deleteFavourite(universityName);
                            System.out.print(Ui.printFavouriteListDeletedAcknowledgement(universityName));
                        }
                    } catch (NoSuchElementException e) {
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