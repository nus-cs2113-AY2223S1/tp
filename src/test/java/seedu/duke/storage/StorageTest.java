package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    protected ArrayList<FlightInfo> flights = new ArrayList<>();
    protected ArrayList<PassengerInfo> passengers = new ArrayList<>();
    protected static final String testFlightString = "SQ832|SINGAPORE AIRLINES|BANGKOK|1600|05|03-03\n";
    protected static final String testPassengerString = "JORDAN|1600|SQ832|05|1|17D|1515\n";
    protected Path path1;
    protected File file1;
    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        try {
            path1 = tempDir.resolve("testfile1.txt");
        } catch (InvalidPathException ipe) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }

        file1 = path1.toFile();
    }

    private void modifiedInsertFlightsIntoFile() throws IOException {
        flights.add(new FlightInfo("SQ832", "SINGAPORE AIRLINES", "BANGKOK", "1600"
                , "05", "03-03"));
        FileWriter fw = new FileWriter(file1);
        for (int i = 0; i < flights.size(); i++) {
            String formattedString = flights.get(i).convertToFileFormat();
            assertEquals(formattedString, testFlightString);
            String[] details = formattedString.split("\\|");
            assertEquals("SQ832", details[0]);
            assertEquals("SINGAPORE AIRLINES", details[1]);
            assertEquals("BANGKOK", details[2]);
            assertEquals("1600", details[3]);
            assertEquals("05", details[4]);
            assertEquals("03-03\n", details[5]);
            fw.write(flights.get(i).convertToFileFormat());
        }
        fw.close();
    }

    @Test
    void checkInsertFlightsIntoFile() {
        try {
            modifiedInsertFlightsIntoFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void modifiedInsertPassengersIntoFile() throws IOException {
        passengers.add(new PassengerInfo("JORDAN", "1600", "SQ832",
                "05", 1, "17D", "1515"));
        FileWriter fw = new FileWriter(file1);
        for (int i = 0; i < passengers.size(); i++) {
            String formattedString = passengers.get(i).convertToFileFormat();
            assertEquals(formattedString, testPassengerString);
            String[] details = formattedString.split("\\|");
            assertEquals("JORDAN", details[0]);
            assertEquals("1600", details[1]);
            assertEquals("SQ832", details[2]);
            assertEquals("05", details[3]);
            assertEquals("1", details[4]);
            assertEquals("17D", details[5]);
            assertEquals("1515\n", details[6]);
            fw.write(passengers.get(i).convertToFileFormat());
        }
        fw.close();
    }

    @Test
    void checkInsertPassengersIntoFile() {
        try {
            modifiedInsertPassengersIntoFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
