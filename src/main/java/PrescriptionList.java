import java.util.ArrayList;

public class PrescriptionList {
    private final ArrayList<Prescription> prescriptionsList;

    public PrescriptionList() {
        prescriptionsList = new ArrayList<>();
    }

    public void add(UI ui, String patientId, String medicine, String dosage, String timeInterval) {
        assert patientId != null : "ID should not be null";
        assert medicine != null : "medicine should not be null";
        assert dosage != null : "dosage should not be null";
        assert timeInterval != null : "time interval should not be null";
        Prescription prescription = new Prescription(patientId, medicine, dosage, timeInterval);
        prescriptionsList.add(prescription);
        ui.printAddPrescriptionMessage(prescription.toString());

    }

    public void add(UI ui, String patientId, String medicine, String dosage, String timeInterval, boolean isActive) {
        assert patientId != null : "ID should not be null";
        assert medicine != null : "medicine should not be null";
        assert dosage != null : "dosage should not be null";
        assert timeInterval != null : "time interval should not be null";
        Prescription prescription = new Prescription(patientId, medicine, dosage, timeInterval, isActive);
        prescriptionsList.add(prescription);
        ui.printAddPrescriptionMessage(prescription.toString());
    }

    public boolean isEmpty() {
        return prescriptionsList.isEmpty();
    }

    public void viewAll(UI ui) {
        if (isEmpty()) {
            ui.printNoPrescriptionMessage();
            return;
        }

        ui.printViewAllPrescriptionsMessage();
        ui.printLine();
        for (int i = 0; i < prescriptionsList.size(); i++) {
            System.out.println("Prescription " + (i + 1));
            System.out.println(prescriptionsList.get(i));
            if (i != prescriptionsList.size() - 1) {
                System.out.println();
            }
        }
        ui.printLine();
    }

    // TODO one potential improvement is to make this three different methods
    //  edit(String id, String medicine), edit(String id, String dosage)
    //  and edit(String id, String timeInterval)
    public void edit(UI ui, int prescriptionNumber, String medicine, String dosage, String timeInterval) {
        assert medicine != null : "medicine should not be null";
        assert dosage != null : "dosage should not be null";
        assert timeInterval != null : "time interval should not be null";

        if (prescriptionNumber < 1 || prescriptionNumber > prescriptionsList.size()) {
            ui.printIndexOutOfRangeErrorMessage();
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

        ui.printEditPrescriptionMessage(prescriptionEdited.toString());
    }
}
