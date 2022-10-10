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
            System.out.println(Messages.NO_PRESCRIPTION_MESSAGE);
            return;
        }

        for (int i = 0; i < prescriptionsList.size(); i++) {
            System.out.println("Prescription " + (i + 1));
            System.out.println(prescriptionsList.get(i));
            System.out.println();
        }
    }

    public void edit(int prescriptionNumber, String medicine, String dosage, int timeInterval) {
        if (prescriptionNumber < 1 || prescriptionNumber > prescriptionsList.size()) {
            System.out.println(Messages.INDEX_OUT_OF_RANGE_MESSAGE);
            return;
        }

        int index = prescriptionNumber - 1;

        Prescription prescriptionEdited = prescriptionsList.get(index);

        if (!medicine.isEmpty()) {
            prescriptionEdited.setMedicine(medicine);
        }

        if (!dosage.isEmpty()) {
            prescriptionEdited.setDosage(dosage);
        }

        if (timeInterval != Utils.NULL_INT) {
            prescriptionEdited.setTimeInterval(timeInterval);
        }

        System.out.println(Messages.PRESCRIPTION_MODIFIED_MESSAGE);
        System.out.println(prescriptionEdited);
    }
}
