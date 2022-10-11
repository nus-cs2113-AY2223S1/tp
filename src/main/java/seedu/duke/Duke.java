package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandBye;
import seedu.duke.exception.EmptyClientDetailException;
import seedu.duke.exception.EmptyClientIndexDeleteException;
import seedu.duke.exception.EmptyCommandAddDetailException;
import seedu.duke.exception.EmptyCommandDeleteDetailException;
import seedu.duke.exception.EmptyCommandPairUnpairDetailsException;
import seedu.duke.exception.ExistingPairException;
import seedu.duke.exception.IncorrectAddClientFlagOrderException;
import seedu.duke.exception.IncorrectPairUnpairFlagOrderException;
import seedu.duke.exception.InvalidBudgetFormatException;
import seedu.duke.exception.InvalidClientIndexDeleteException;
import seedu.duke.exception.InvalidContactNumberException;
import seedu.duke.exception.InvalidEmailException;
import seedu.duke.exception.MissingClientDetailException;
import seedu.duke.exception.MissingClientFlagException;
import seedu.duke.exception.MissingPairUnpairFlagException;
import seedu.duke.exception.NoExistingPairException;
import seedu.duke.exception.NotIntegerException;
import seedu.duke.exception.NotValidIndexException;
import seedu.duke.exception.UndefinedSubCommandAddTypeException;
import seedu.duke.exception.UndefinedSubCommandDeleteTypeException;

import java.io.IOException;

public class Duke {
    private Parser parser;
    private Ui ui;
    private Storage storage;
    private PropertyList propertyList;
    private ClientList clientList;
    private PairingList pairingList;


    public void run() throws IOException {
        this.ui = new Ui();
        this.propertyList = new PropertyList();
        this.clientList = new ClientList();
        this.parser = new Parser(clientList, propertyList, pairingList);
        this.pairingList = new PairingList();
        this.storage = new Storage(clientList, propertyList, pairingList);

        Command command;
        boolean isCommandBye = false;

        ui.showWelcomeMessage();

        do {
            try {
//                System.exit(0); //to pass CI
                String userInputText = ui.readCommand();
                command = parser.parseCommand(userInputText);
                command.execute(ui, storage, propertyList, clientList, pairingList);
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
            } catch (EmptyCommandPairUnpairDetailsException e) {
                ui.showEmptyCommandPairUnpairDetailsMessage();
            } catch (MissingPairUnpairFlagException | IncorrectPairUnpairFlagOrderException e) {
                ui.showPairUnpairWrongFormatMessage();
            } catch (NotValidIndexException e) {
                ui.showNotValidIndexMessage();
            } catch (NotIntegerException e) {
                ui.showNotIntegerMessage();
            } catch (ExistingPairException e) {
                ui.showExistingPairMessage();
            } catch (NoExistingPairException e) {
                ui.showNoExistingPairMessage();
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
