package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

class RemoveCommandTest {

    private FoodList foodList = new FoodList();
    private Biometrics biometrics = new Biometrics();
    private Ui ui = new Ui();
    private ExerciseList exerciseList = new ExerciseList();
    private RecordList recordList = new RecordList();
    private final Storage storage = new Storage();

    @BeforeEach
    public void setUp() throws Exception {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("add food /cola /100");
        commandList.add("add food /chicken rice /250");
        commandList.add("add food /ice cream /300");

        for (String input : commandList) {
            Command c = Parser.parse(input);
            c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
            c.execute();
        }
    }

    @Test
    void execute_RemoveWithNoParameter_exceptionThrown() {
        String command = "remove food";
        assertInvalidRemoveCommand(command, "INVALID_NUMBER_INPUT");
    }


    @Test
    void execute_RemoveWithNonIntegerIndex_exceptionThrown() {
        String command = "remove food /0x";
        assertInvalidRemoveCommand(command, "Index should be numerical");
    }

    private void assertInvalidRemoveCommand(String input, String expectedMessage) {
        Command command = Parser.parse(input);
        try {
            command.execute();
            fail();
        } catch (Exception e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

}