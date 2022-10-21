import java.util.ArrayList;

public class VisitList {
    private final ArrayList<Visit> visitList;

    public VisitList() {
        visitList = new ArrayList<>();
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

    //TODO: in future version, should think about how to edit reason when we have 2 visit records of the same ID
    public void editReason(UI ui, String id, String reason) {
        assert id != null : "id should not be null";
        int i = 0;
        for (i = 0; i < visitList.size(); i++) {
            if (visitList.get(i).getId().equals(id)) {
                visitList.get(i).setReason(reason);
                break;
            }
        }
        ui.printEditVisitReasonMessage(visitList.get(i).toString());
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
                    System.out.printf("Here are the list of visits for Patient with ID: %s\n",id);
                }
                noOfPatientVisits++;
                ui.printLine();
                System.out.println("VisitIndex #" + (i + 1) + ")");
                System.out.println(visitList.get(i));
            }
        }
        if (noOfPatientVisits == 0) {
            System.out.printf("Sorry, Patient with ID %s has no visits recorded yet!\n",id);
        }
        ui.printLine();
    }

    public void viewVisit(UI ui, int index) {
        if (isEmpty()) {
            System.out.println("There are no visits in the system right now!");
            return;
        }
        if (index < 1 || index > getTotalVisits()) {
            System.out.printf("There is no such visit in the system with index %d!\n",index);
        } else {
            System.out.printf("Here is the visit with VisitIndex %d:\n",index);
            ui.printLine();
            System.out.println(visitList.get(index - 1));
        }
        ui.printLine();
    }
}
