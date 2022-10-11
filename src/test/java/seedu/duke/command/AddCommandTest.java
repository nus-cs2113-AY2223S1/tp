package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.exception.IllegalValueException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddCommandTest {

    @Test
    public void addCommand_invalidFoodDescription_throwsException() {
        String command = "add food";
        int calories = 100;
        String fullCommand;
        final String[] invalidFoodNames = { "", " ", "[]\\[;]" };
        for (String foodName : invalidFoodNames) {
            fullCommand = String.format("%s /%s /%d",
                    command, foodName, calories);
            Command c = Parser.parse(fullCommand);
            try {
                c.execute();
                fail();
            } catch (IllegalValueException e) {
                assertEquals("Please provide valid food description inputs!", e.getMessage());
            }
        }
    }


    @Test
    void execute_negativeCalories_exceptionThrown() {
        String command = "add food";
        String foodName = "chicken rice";
        int calories = -112;
        String fullCommand = String.format("%s /%s /%d",
                command, foodName, calories);
        Command c = Parser.parse(fullCommand);
        try {
            c.execute();
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Calories inputs need to be positive integer values!", e.getMessage());
        }

    }

}