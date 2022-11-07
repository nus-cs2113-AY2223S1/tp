import java.util.ArrayList;

/**
 * VisitList is a class that handles the array operations for Visit objects
 */
public class VisitList {
    private final ArrayList<Visit> visitList;

    public VisitList() {
        visitList = new ArrayList<>();
    }

    public ArrayList<Visit> getVisits() {
        return visitList;
    }
    /**
     * Adds a new visit to the list
     * It will only be added if there is not an exact duplicate of this visit present in the list with the same id, dateOfVisit and timeOfVisit.
     * @param ui is the user interface of OneDoc
     * @param id is a String of the patient's identification number
     * @param dateOfVisit is a String of the date of visit
     * @param timeOfVisit is a String of the time of visit
     * @param reason is a String of the patient's reason for visit
     */
    public void addVisit(UI ui, String id, String dateOfVisit, String timeOfVisit, String reason) {
        assert id != null : "id should not be null";
        assert dateOfVisit != null : "date of visit should not be null";
        assert timeOfVisit != null : "time of visit should not be null";
        if (checkDuplicateVisit(id,dateOfVisit,timeOfVisit)) {
            System.out.println(UI.DUPLICATE_VISIT_MESSAGE);
        } else {
            Visit visit = new Visit(id,dateOfVisit,timeOfVisit,reason);
            visitList.add(visit);
            ui.printMessageAndObject(visit.toString(),UI.ADD_VISIT,visitList.indexOf(visit), UI.VISIT);
        }
    }

    public void addVisit(UI ui, String id, String dateOfVisit, String timeOfVisit) {
        assert id != null : "id should not be null";
        assert dateOfVisit != null : "date of visit should not be null";
        assert timeOfVisit != null : "time of visit should not be null";
        if (checkDuplicateVisit(id,dateOfVisit,timeOfVisit)) {
            System.out.println(UI.DUPLICATE_VISIT_MESSAGE);
        } else {
            Visit visit = new Visit(id,dateOfVisit,timeOfVisit);
            visitList.add(visit);
            ui.printMessageAndObject(visit.toString(),UI.ADD_VISIT,visitList.indexOf(visit), UI.VISIT);
        }
    }

    /**
     * Checks if the visit is a duplicate of another in the list of visits
     * @param id is a String of the patient's identification number
     * @param dateOfVisit is a String of the date of visit
     * @param timeOfVisit is a String of the time of visit
     * @return a boolean whether it is a duplicate visit or not
     */
    public boolean checkDuplicateVisit(String id, String dateOfVisit, String timeOfVisit) {
        for (int i = 0; i < getTotalVisits(); i++) {
            if (visitList.get(i).getId().equals(id)
                    && visitList.get(i).getDateOfVisit().equals(dateOfVisit)
                    && visitList.get(i).getTimeOfVisit().equals(timeOfVisit)) {
                return true;
            }
        }
        return false;
    }

    public void loadVisit(String id, String dateOfVisit, String timeOfVisit, String reason) {
        Visit visit = new Visit(id, dateOfVisit, timeOfVisit, reason);
        visitList.add(visit);
    }


    /**
     * Edits reason for an existing visit
     * @param ui is the user interface of OneDoc
     * @param index is an integer, referring to the index of the visit in the overall list of visits
     * @param reason is a String of the patient's reason for visit
     */
    public void editReason(UI ui, int index, String reason) {
        assert reason != null : "reason should not be null, use deleteReason instead";
        if (index < 1 || index > getTotalVisits()) {
            System.out.println("There is no such visit in the system with index " + index + "!");
        } else {
            visitList.get(index - 1).setReason(reason);
            ui.printMessageAndObject(visitList.get(index - 1).toString(), UI.EDIT_VISIT_REASON, index - 1,
                    UI.VISIT);
        }
    }

    /**
     * Deletes reason for an existing Visit in the list
     * @param ui is the user interface of OneDoc
     * @param index is an integer, referring to the index of the visit in the overall list of visits
     */
    public void deleteReason(UI ui, int index) {
        if (index < 1 || index > getTotalVisits()) {
            System.out.println("There is no such visit in the system with index " + index + "!");
        } else if (visitList.get(index - 1).getReason().equals("")) {
            System.out.println("Visit #" + index + " already has no reason stated!");
        } else {
            visitList.get(index - 1).setReason("");
            ui.printMessageAndObject(visitList.get(index - 1).toString(),UI.DELETE_VISIT_REASON, index - 1,
                    UI.VISIT);
        }
    }

    public boolean isEmpty() {
        return visitList.size() == 0;
    }

    public int getTotalVisits() {
        return visitList.size();
    }

    /**
     * Shows all the visits in the list of visits
     * @param ui is the user interface of OneDoc
     */
    public void viewAll(UI ui) {
        if (isEmpty()) {
            System.out.println("There are no visits in the system right now!");
            return;
        }
        System.out.println("Here are the list of visits in the system:");
        for (int i = 0; i < getTotalVisits(); i++) {
            ui.printLine();
            System.out.println("\tVisit #" + (i + 1));
            System.out.println(visitList.get(i));
        }
        ui.printLine();
    }

    /**
     * Shows all the visits belonging to a specific patient
     * @param ui is the user interface of OneDoc
     * @param id is a String of the patient's identification number
     */
    public void viewPatient(UI ui, String id) {
        if (isEmpty()) {
            System.out.println("There are no visits in the system right now!");
            return;
        }
        int noOfPatientVisits = 0;
        for (int i = 0; i < getTotalVisits(); i++) {
            if (visitList.get(i).getId().equalsIgnoreCase(id)) {
                if (noOfPatientVisits == 0) {
                    System.out.println("Here are the list of visits for Patient with ID: " + id);
                }
                noOfPatientVisits++;
                ui.printLine();
                System.out.println("\tVisit #" + (i + 1));
                System.out.println(visitList.get(i));
            }
        }
        if (noOfPatientVisits == 0) {
            System.out.println("Sorry, Patient with ID " + id + " has no visits recorded yet!");
        }
        ui.printLine();
    }

    /**
     * Shows a specific visit in the list of visits
     * @param ui is the user interface of OneDoc
     * @param index is an integer, referring to the index of the visit in the overall list of visits
     */
    public void viewVisit(UI ui, int index) {
        if (isEmpty()) {
            System.out.println("There are no visits in the system right now!");
            return;
        }
        if (index < 1 || index > getTotalVisits()) {
            System.out.println("There is no such visit in the system with index " + index + "!");
        } else {
            System.out.println("Here is Visit #" + index);
            ui.printLine();
            System.out.println(visitList.get(index - 1));
        }
        ui.printLine();
    }
}
