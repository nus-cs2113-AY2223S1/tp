package seedu.duke.records;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Record {
    private LocalDate date;

    public Record(LocalDate date) {
        this.date = date;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        return "Data is recorded on: " + getDate();
    }
}
