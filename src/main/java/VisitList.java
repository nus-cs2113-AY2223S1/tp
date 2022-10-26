import java.util.ArrayList;

public class VisitList {
    private final ArrayList<Visit> visitList;

    public VisitList() {
        visitList = new ArrayList<>();
    }

    public ArrayList<Visit> getVisits() {
        return visitList;
    }

    public void addVisit(UI ui, String id, String dateOfVisit, String timeOfVisit, String reason) {
        assert id != null : "id should not be null";
        assert dateOfVisit != null : "date of visit should not be null";
        assert timeOfVisit != null : "time of visit should not be null";
        Visit visit = new Visit(id,dateOfVisit,timeOfVisit,reason);
        visitList.add(visit);
        ui.printAddVisitMessage(visit.toString());
    }

    public void addVisit(UI ui, String id, String dateOfVisit, String timeOfVisit) {
        assert id != null : "id should not be null";
        assert dateOfVisit != null : "date of visit should not be null";
        assert timeOfVisit != null : "time of visit should not be null";
        Visit visit = new Visit(id,dateOfVisit,timeOfVisit);
        visitList.add(visit);
        ui.printAddVisitMessage(visit.toString());
    }

    public void loadVisit(String id, String dateOfVisit, String timeOfVisit, String reason) {
        Visit visit = new Visit(id, dateOfVisit, timeOfVisit, reason);
        visitList.add(visit);
    }

    public void editReason(UI ui, int index, String reason) {
        assert reason != null : "reason should not be null, use deleteReason instead";
        visitList.get(index - 1).setReason(reason);
        ui.printEditVisitReasonMessage(visitList.get(index - 1).toString());
    }

    public void deleteReason(UI ui, int index) {
        visitList.get(index - 1).setReason("");
        ui.printDeleteVisitReasonMessage(visitList.get(index - 1).toString());
    }

    public boolean isEmpty() {
        return visitList.size() == 0;
    }

    public int getTotalVisits() {
        return visitList.size();
    }

    public void viewAll(UI ui) {
        if (isEmpty()) {
            System.out.println("There are no visits in the system right now!");
            return;
        }
        System.out.println("Here are the list of visits in the system:");
        for (int i = 0; i < getTotalVisits(); i++) {
            ui.printLine();
            System.out.println("VisitIndex #" + (i + 1) + ")");
            System.out.println(visitList.get(i));
        }
        ui.printLine();
    }

    public void viewPatient(UI ui, String id) {
        if (isEmpty()) {
            System.out.println("There are no visits in the system right now!");
            return;
        }
        int noOfPatientVisits = 0;
        for (int i = 0; i < getTotalVisits(); i++) {
            if (visitList.get(i).getId().equals(id)) {
                if (noOfPatientVisits == 0) {
                    System.out.println("Here are the list of visits for Patient with ID: " + id);
                }
                noOfPatientVisits++;
                ui.printLine();
                System.out.println("VisitIndex #" + (i + 1) + ")");
                System.out.println(visitList.get(i));
            }
        }
        if (noOfPatientVisits == 0) {
            System.out.println("Sorry, Patient with ID " + id + " has no visits recorded yet!");
        }
        ui.printLine();
    }

    public void viewVisit(UI ui, int index) {
        if (isEmpty()) {
            System.out.println("There are no visits in the system right now!");
            return;
        }
        if (index < 1 || index > getTotalVisits()) {
            System.out.println("There is no such visit in the system with index " + index + "!");
        } else {
            System.out.println("Here is the visit with VisitIndex " + index + ":");
            ui.printLine();
            System.out.println(visitList.get(index - 1));
        }
        ui.printLine();
    }
}
