package seedu.duke.id;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class IdGeneratorTest {

    @Test
    void generateId_generateTwice_twoIdAreDifferent() {
        assertNotEquals(IdGenerator.generateId(), IdGenerator.generateId());
    }
}