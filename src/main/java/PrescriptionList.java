import java.util.ArrayList;

public class PrescriptionList {
    private final ArrayList<Prescription> prescriptionList;

    public PrescriptionList() {
        prescriptionList = new ArrayList<>();
    }

    public void add(int patientId, String medicine, int timeInterval, int dosage) {
        Prescription prescription = new Prescription(patientId, medicine, timeInterval, dosage);
        prescriptionList.add(prescription);
    }
}
