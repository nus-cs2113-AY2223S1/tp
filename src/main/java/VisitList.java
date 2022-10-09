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

}
