import java.util.ArrayList;

public class PrescriptionList {
    private final ArrayList<Prescription> prescriptionsList;

    public PrescriptionList() {
        prescriptionsList = new ArrayList<>();
    }

    public void add(String patientId, String medicine, String dosage, int timeInterval) {
        Prescription prescription = new Prescription(patientId, medicine, dosage, timeInterval);
        prescriptionsList.add(prescription);
    }

    public void add(String patientId, String medicine, String dosage, int timeInterval, boolean isActive) {
        Prescription prescription = new Prescription(patientId, medicine, dosage, timeInterval, isActive);
        prescriptionsList.add(prescription);
    }

    public boolean isEmpty() {
        return prescriptionsList.isEmpty();
    }

    public void viewAll() {
        if (isEmpty()) {
            System.out.println(Messages.noPrescriptionMessage);
            return;
        }

        for (Prescription prescription : prescriptionsList) {
            System.out.println(prescription);
            System.out.println();
        }
    }
}
