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
            System.out.println("Prescription #" + (i + 1));
            System.out.println(prescriptionsList.get(i));
            ui.printLine();
        }
    }

    public void viewPatientPrescription(UI ui, String patientId) {
        if (isEmpty() || hasPatientPrescription(patientId)) {
            ui.printNoMatchingPrescriptionMessage();
            return;
        }

        ui.printViewAllPrescriptionsMessage();
        ui.printLine();
        for (int i = 0; i < prescriptionsList.size(); i++) {
            if (prescriptionsList.get(i).isMatchedPatient(patientId)) {
                System.out.println("Prescription #" + (i + 1));
                System.out.println(prescriptionsList.get(i));
                ui.printLine();
            }
        }
    }

    public void viewActivePatientPrescription(UI ui, String patientId) {
        if (isEmpty() || hasPatientPrescription(patientId)) {
            ui.printNoMatchingActivePrescriptionMessage();
            return;
        }

        ui.printViewAllPrescriptionsMessage();
        ui.printLine();
        for (int i = 0; i < prescriptionsList.size(); i++) {
            if (prescriptionsList.get(i).isMatchedPatientActive(patientId)) {
                System.out.println("Prescription #" + (i + 1));
                System.out.println(prescriptionsList.get(i));
                ui.printLine();
            }
        }
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
        String newMedicine = medicine.isEmpty() ? prescriptionEdited.getMedicine() : medicine;
        String newDosage = dosage.isEmpty() ? prescriptionEdited.getDosage() : dosage;
        String newTimeInterval = timeInterval.isEmpty() ? prescriptionEdited.getTimeInterval() : timeInterval;
        String patientId =  prescriptionEdited.getPatientId();

        prescriptionEdited.setInactive();
        Prescription newPrescription = new Prescription(patientId, newMedicine, newDosage, newTimeInterval);

        prescriptionsList.add(newPrescription);

        ui.printEditPrescriptionMessage(newPrescription.toString());
    }

    private boolean hasPatientPrescription(String patientId) {
        for (Prescription prescription : prescriptionsList) {
            if (prescription.isMatchedPatientActive(patientId)) {
                return true;
            }
        }

        return false;
    }
}
