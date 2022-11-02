package seedu.duke.records;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Record {
    private LocalDate date;

    public Record(LocalDate date) {
        this.date = date;
    }

    public String getDateString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Data is recorded on: " + getDateString();
    }
}
