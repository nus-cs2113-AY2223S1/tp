import java.util.ArrayList;

public class PrescriptionList {
    private final ArrayList<Prescription> prescriptionsList;

    public PrescriptionList() {
        prescriptionsList = new ArrayList<>();
    }

    public void add(String patientId, String medicine, String dosage, String timeInterval) {
        Prescription prescription = new Prescription(patientId, medicine, dosage, timeInterval);
        prescriptionsList.add(prescription);
        Messages.printAddPrescriptionMessage(prescription.toString());

    }

    public void add(String patientId, String medicine, String dosage, String timeInterval, boolean isActive) {
        Prescription prescription = new Prescription(patientId, medicine, dosage, timeInterval, isActive);
        prescriptionsList.add(prescription);
        Messages.printAddPrescriptionMessage(prescription.toString());
    }

    public boolean isEmpty() {
        return prescriptionsList.isEmpty();
    }

    public void viewAll() {
        if (isEmpty()) {
            System.out.println(Messages.NO_PRESCRIPTION_MESSAGE);
            return;
        }

        Messages.printViewAllPrescriptionsMessage();
        Messages.printLine();
        for (int i = 0; i < prescriptionsList.size(); i++) {
            System.out.println("Prescription " + (i + 1));
            System.out.println(prescriptionsList.get(i));
            if (i != prescriptionsList.size() - 1) {
                System.out.println();
            }
        }
        Messages.printLine();
    }

    // TODO one potential improvement is to make this three different methods
    //  edit(String id, String medicine), edit(String id, String dosage)
    //  and edit(String id, String timeInterval)
    public void edit(int prescriptionNumber, String medicine, String dosage, String timeInterval) {
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

        if (!timeInterval.isEmpty()) {
            prescriptionEdited.setTimeInterval(timeInterval);
        }

        Messages.printEditPrescriptionMessage(prescriptionEdited.toString());
    }
}
