import java.util.ArrayList;

public class PrescriptionList {
    private final ArrayList<Prescription> prescriptionList;

    public PrescriptionList() {
        prescriptionList = new ArrayList<>();
    }

    public void add(String patientId, String medicine, int timeInterval, String dosage) {
        Prescription prescription = new Prescription(patientId, medicine, timeInterval, dosage);
        prescriptionList.add(prescription);
    }
}
