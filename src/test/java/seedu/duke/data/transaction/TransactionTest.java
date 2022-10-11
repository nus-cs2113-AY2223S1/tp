package seedu.duke.data.transaction;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    LocalDate date = LocalDate.of(2022, 1, 1);
    Transaction transaction = new Income("Milked cows in the farm", 50,
            "Salary", date);

    @Test
    public void testGetDescription() {
        assertEquals("Milked cows in the farm", transaction.getDescription());
    }

    @Test
    public void testSetDescription() {
        transaction.setDescription("Helped the cows to wash their ass");
        assertEquals("Helped the cows to wash their ass", transaction.getDescription());
    }

    @Test
    public void testGetAmount() {
        assertEquals(50, transaction.getAmount());
    }

    @Test
    public void testSetAmount() {
        transaction.setAmount(500);
        assertEquals(500, transaction.getAmount());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Salary", transaction.getCategory());
    }

    @Test
    public void testSetCategory() {
        transaction.setCategory("Love");
        assertEquals("Love", transaction.getCategory());
    }
}
