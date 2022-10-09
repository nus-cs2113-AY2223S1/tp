import java.util.ArrayList;

public class VisitList {
    private final ArrayList<Visit> visitList;

    public VisitList() {
        visitList = new ArrayList<>();
    }


    public void addVisit(String id, String dateOfVisit, String timeOfVisit, String reason) {
        visitList.add(new Visit(id,dateOfVisit,timeOfVisit,reason));
    }

    public void addVisit(String id, String dateOfVisit, String timeOfVisit) {
        visitList.add(new Visit(id,dateOfVisit,timeOfVisit));
    }

    public void editReason(String id, String reason) {
        for (int i = 0; i < visitList.size(); i++) {
            if (visitList.get(i).getId() == id) {
                visitList.get(i).setReason(reason);
            }
        }
    }

    public boolean isEmpty() {
        return visitList.size() == 0;
    }

    public int getTotalVisits() {
        return visitList.size();
    }

    public void viewAll() {
        if (isEmpty()) {
            System.out.println("There are no visits in the system right now!");
            return;
        }
        System.out.println("Here are the list of visits in the system");
        for (int i = 0; i < getTotalVisits(); i++) {
            Messages.printLine();
            System.out.println((i + 1) + ")");
            System.out.println(visitList.get(i));
        }
        Messages.printLine();
    }

}
