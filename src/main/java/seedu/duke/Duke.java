package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandBye;

public class Duke {
    private Parser parser;
    private Ui ui;
    private Storage storage;
    private PropertyList propertyList;
    private ClientList clientList;


    public void run() {
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
                command = parser.parseCommand(userInputText);
                command.execute(ui, storage, propertyList, clientList);
                isCommandBye = (command instanceof CommandBye);
            } catch (MissingCommandAddDetailException e) {
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
            }
        } while (!isCommandBye);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
