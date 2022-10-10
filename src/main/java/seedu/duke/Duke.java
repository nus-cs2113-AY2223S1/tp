package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandBye;

import java.io.IOException;

public class Duke {
    private Parser parser;
    private Ui ui;
    private Storage storage;
    private PropertyList propertyList;
    private ClientList clientList;


    public void run() throws IOException {
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage();
        this.propertyList = new PropertyList();
        this.clientList = new ClientList();

        Command command;
        boolean isCommandBye = false;

        do {
            try {
                String userInputText = ui.readCommand();
                command = parser.parseCommand(userInputText, clientList);
                command.execute(ui, storage, propertyList, clientList);
                isCommandBye = (command instanceof CommandBye);
            } catch (EmptyCommandAddDetailException e) {
                ui.showMissingCommandAddDetailMessage();
            } catch (UndefinedSubCommandAddTypeException e) {
                ui.showUndefinedSubCommandAddTypeMessage();
            } catch (EmptyClientDetailException e) {
                ui.showEmptyClientDetailMessage();
            } catch (MissingClientFlagException | IncorrectAddClientFlagOrderException
                     | MissingClientDetailException e) {
                ui.showAddClientWrongFormatMessage();
            } catch (InvalidContactNumberException e) {
                ui.showInvalidContactNumberMessage();
            } catch (InvalidEmailException e) {
                ui.showInvalidEmailMessage();
            } catch (InvalidBudgetFormatException e) {
                ui.showInvalidBudgetFormatMessage();
            } catch (EmptyCommandDeleteDetailException e) {
                ui.showMissingCommandDeleteDetailMessage();
            } catch (UndefinedSubCommandDeleteTypeException e) {
                ui.showUndefinedSubCommandDeleteTypeMessage();
            } catch (InvalidClientIndexDeleteException e) {
                ui.showInvalidClientIndexDeleteMessage();
            } catch (EmptyClientIndexDeleteException e) {
                ui.showEmptyClientIndexDeleteMessage();
            }
        } while (!isCommandBye);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
