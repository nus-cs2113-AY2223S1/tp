import java.util.ArrayList;

public class Visit {
    private String id;

    private String dateOfVisit;

    private String timeOfVisit;

    private String reason;

    public Visit(String id, String dateOfVisit, String timeOfVisit, String reason) {
        this.id = id;
        this.dateOfVisit = dateOfVisit;
        this.timeOfVisit = timeOfVisit;
        this.reason = reason;
    }

    public Visit(String id, String dateOfVisit, String timeOfVisit) {
        this.id = id;
        this.dateOfVisit = dateOfVisit;
        this.timeOfVisit = timeOfVisit;
        this.reason = "";
    }

    public String getId() {
        return id;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public String getTimeOfVisit() {
        return timeOfVisit;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    @Override
    public String toString() {
        String message = "";
        message += "ID: " + getId();
        message += "\n";
        message += "Date: " + getDateOfVisit();
        message += "\n";
        message += "Time: " + getTimeOfVisit();
        message += "\n";
        message += getReason() == "" ? "NIL" : getReason();
        return message;
    }
}
