package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.ExerciseList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.food.FoodList;

class RemoveCommandTest {

    private FoodList foodList = new FoodList();
    private Biometrics biometrics = new Biometrics();
    private Ui ui = new Ui();
    private ExerciseList exerciseList = new ExerciseList();
    @BeforeEach
    public void setUp() throws Exception {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("add food /cola /100");
        commandList.add("add food /chicken rice /250");
        commandList.add("add food /ice cream /300");

        for (String input : commandList) {
            Command c = Parser.parse(input);
            c.setData(ui, biometrics, exerciseList, foodList);
            c.execute();
        }
    }

    @Test
    void execute_RemoveWithNoParameter_exceptionThrown() {
        String command = "remove food";
        assertInvalidRemoveCommand(command, "Invalid remove food input");
    }


    @Test
    void execute_RemoveWithNonIntegerIndex_exceptionThrown() {
        String command = "remove food /0x";
        assertInvalidRemoveCommand(command, "Invalid remove food input");
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