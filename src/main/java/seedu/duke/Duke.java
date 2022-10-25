package seedu.duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import seedu.duke.command.Command;
import seedu.duke.command.CreateCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.AddCommand;
import seedu.duke.command.FavouriteCommand;
import seedu.duke.command.DatabaseStorage;
import seedu.duke.command.ListCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.Database;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.exceptions.TimetableNotFoundException;
import seedu.duke.exceptions.UniversityNotFoundException;
import seedu.duke.module.Module;
import seedu.duke.parser.CommandParser;
import seedu.duke.timetable.TimetableManager;
import seedu.duke.ui.Ui;
import seedu.duke.module.ModuleMapping;
import seedu.duke.university.University;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserUniversityListManager;
import seedu.duke.parser.UserStorageParser;

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
        TimetableManager timetableManager = UserStorageParser.getSavedTimetables();

        while (!shouldExit) {
            try {
                String userInput = Ui.getUserInput();
                Command newUserCommand = CommandParser.getUserCommand(userInput);
                switch (newUserCommand.getCommandType()) {
                case EXIT:
                    executeExitCommand();
                    break;
                case HELP:
                    executeHelpCommand();
                    break;
                case DELETE:
                    executeDeleteCommand(userUniversityListManager, timetableManager, (DeleteCommand) newUserCommand);
                    break;
                case CREATE:
                    executeCreateCommand(userUniversityListManager, timetableManager, (CreateCommand) newUserCommand);
                    break;
                case VIEW:
                    executeViewCommand(userUniversityListManager, timetableManager, (ViewCommand) newUserCommand);
                    break;
                case ADD:
                    executeAddCommand(userUniversityListManager, timetableManager, (AddCommand) newUserCommand);
                    break;
                case LIST:
                    executeListCommand((ListCommand) newUserCommand);
                    break;
                case FAVOURITE:
                    executeFavouriteCommand(userUniversityListManager, (FavouriteCommand) newUserCommand);
                    break;
                default:
                    break;
                }
            } catch (InvalidUserCommandException | InvalidModuleException | ModuleNotFoundException e) {
                Ui.printExceptionMessage(e);
            }
        }
    }

    /**
     * Processes the help command and prints out help message.
     */
    private static void executeHelpCommand() {
        System.out.print(Ui.printCommands());
    }

    /**
     * Processes the exit command and prints out goodbye message and exit duke's loop.
     */
    private static void executeExitCommand() {
        System.out.print(Ui.sayByeToUser());
        setShouldExit();
    }

    /**
     * Processes the delete command and execute it on either the timetables or user university lists
     * as specified by the user.
     *
     * @param userUniversityListManager User university lists' manager
     * @param timetableManager User timetables' manager
     * @param deleteCommand The delete command to be executed
     *
     * @throws InvalidUserCommandException if user provided an invalid university name.
     */
    private static void executeDeleteCommand(UserUniversityListManager userUniversityListManager,
                                             TimetableManager timetableManager, DeleteCommand deleteCommand)
            throws InvalidUserCommandException {
        try {
            if (deleteCommand.getLesson() != null) {
                timetableManager.deleteLesson(deleteCommand.getLesson());
                UserStorageParser.storeTimetable(timetableManager);
            } else {
                if (deleteCommand.getModuleCode() == null) {
                    userUniversityListManager.deleteList(deleteCommand.getUniversityName());
                } else {
                    userUniversityListManager.deleteModule(deleteCommand.getUniversityName(),
                            deleteCommand.getModuleCode());
                    timetableManager.deleteTimetable(deleteCommand.getUniversityName());
                    UserStorageParser.storeTimetable(timetableManager);
                }
                UserStorageParser.storeCreatedLists(userUniversityListManager);
            }
        } catch (NoSuchElementException | TimetableNotFoundException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Processes the create command and creates a university list and the corresponding timetable for that university.
     * Updates user storage.
     *
     * @param userUniversityListManager User university lists' manager
     * @param timetableManager User timetables' manager
     * @param createCommand The create command to be executed
     */
    private static void executeCreateCommand(UserUniversityListManager userUniversityListManager,
                                             TimetableManager timetableManager, CreateCommand createCommand) {
        userUniversityListManager.createList(createCommand.getUniversityName());
        timetableManager.createTimetable(createCommand.getUniversityName(), false);
        UserStorageParser.storeCreatedLists(userUniversityListManager);
        UserStorageParser.storeTimetable(timetableManager);
    }

    /**
     * Processes the view command and calls Ui to display the appropriate user created item user wants to view.
     *
     * @param userUniversityListManager User university lists' manager
     * @param timetableManager User timetables' manager
     * @param viewCommand The view command to be executed
     */
    private static void executeViewCommand(UserUniversityListManager userUniversityListManager,
                                           TimetableManager timetableManager, ViewCommand viewCommand) {
        try {
            if (viewCommand.getViewOption().equals("LISTS")) {
                userUniversityListManager.displayAll();
            } else if (viewCommand.getViewOption().equals("DELETE_HISTORY")) {
                userUniversityListManager.getUserDeletedModules().displayAll();
            } else if (viewCommand.getViewOption().equals("UNIVERSITY")) {
                userUniversityListManager.displayUniversity(viewCommand.getUniversityName());
                timetableManager.printTimetable(viewCommand.getUniversityName());
            } else if (viewCommand.getViewOption().equals("TIMETABLES")) {
                timetableManager.printAllTimetables();
            }
        } catch (InvalidUserCommandException | TimetableNotFoundException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Processes the add command and execute it on either the timetables or user university lists
     * as specified by the user.
     *
     * @param userUniversityListManager User university lists' manager
     * @param timetableManager User timetables' manager
     * @param addCommand The delete command to be executed
     *
     * @throws InvalidUserCommandException if user provided an invalid university name.
     */

    private static void executeAddCommand(UserUniversityListManager userUniversityListManager,
                                          TimetableManager timetableManager, AddCommand addCommand)
            throws InvalidUserCommandException {
        try {
            if (addCommand.getLesson() != null) {
                timetableManager.addLesson(addCommand.getLesson(), false);
                UserStorageParser.storeTimetable(timetableManager);
            } else {
                ModuleMapping moduleMapping = Database.findPuMapping(addCommand.getModuleCode());
                Module puModule = moduleMapping.getPartnerUniversityModule();
                Module nusModule = moduleMapping.getNusModule();
                UserModuleMapping userModuleToAdd = new UserModuleMapping(puModule.getCode(),
                        puModule.getTitle(), nusModule.getCode(), nusModule.getTitle(),
                        nusModule.getCredit(), puModule.getCredit(), puModule.getUniversity().getName(),
                        puModule.getUniversity().getCountry());
                userUniversityListManager.addModule(addCommand.getUniversityName(), userModuleToAdd);
                UserStorageParser.storeCreatedLists(userUniversityListManager);
            }
        } catch (ModuleNotFoundException | NoSuchElementException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Processes the list command and calls Ui to display the appropriate lists user wants to see from database.
     *
     * @param listCommand The list command to be executed
     */
    private static void executeListCommand(ListCommand listCommand) {
        try {
            if (listCommand.getListOption().equals("UNIVERSITIES")) {
                ArrayList<University> universities = Database.getUniversities();
                Ui.printUniversitiesInDatabase(universities);
            } else if (listCommand.getListOption().equals("MODULES")) {
                ArrayList<ModuleMapping> moduleMappings = Database.getModuleMappings();
                Ui.printMappings(moduleMappings);
            } else if (listCommand.getListOption().equals("module")) {
                ArrayList<ModuleMapping> moduleMappings = Database
                        .findNusMapping(listCommand.getModuleCode());
                Ui.printMappings(moduleMappings);
            } else if (listCommand.getListOption().equals("university")) {
                ArrayList<ModuleMapping> moduleMappings = Database
                        .findUniversityMapping(listCommand.getUniversityName());
                Ui.printMappings(moduleMappings);
            }
        } catch (ModuleNotFoundException | UniversityNotFoundException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Processes the favourite command and execute it on the user university list as specified by the user.
     *
     * @param userUniversityListManager User university lists' manager
     * @param favouriteCommand The delete command to be executed
     *
     * @throws InvalidUserCommandException if user provided an invalid university name.
     */
    private static void executeFavouriteCommand(UserUniversityListManager userUniversityListManager,
                                                FavouriteCommand favouriteCommand) throws InvalidUserCommandException {
        try {
            if (favouriteCommand.getFavouriteOption().equals("VIEW")) {
                userUniversityListManager.displayFavourites();
            } else if (favouriteCommand.getFavouriteOption().equals("ADD")) {
                String universityName = favouriteCommand.getUniversityName();
                userUniversityListManager.addFavourite(universityName);
                UserStorageParser.storeCreatedLists(userUniversityListManager);
            } else if (favouriteCommand.getFavouriteOption().equals("DELETE")) {
                String universityName = favouriteCommand.getUniversityName();
                userUniversityListManager.deleteFavourite(universityName);
                UserStorageParser.storeCreatedLists(userUniversityListManager);
            }
        } catch (NoSuchElementException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Sets shouldExit to exit the while loop in main logic.
     */
    private static void setShouldExit() {
        shouldExit = true;
    }
}