import java.util.ArrayList;

/**
 * PrescriptionList is a class that handles the array operations for Prescription objects.
 */
public class PrescriptionList {
    private final ArrayList<Prescription> prescriptionsList;

    public PrescriptionList() {
        prescriptionsList = new ArrayList<>();
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptionsList;
    }

    /**
     * Add a new prescription to the list.
     * It will only be added successfully if the prescription does not previously exist in the list.
     * @param ui is the user interface of OneDoc
     * @param patientId is a String of the patient's identification number
     * @param medicine is a String of medicine name
     * @param dosage is a String of the dosage
     * @param timeInterval is a String of the time interval
     */
    public void add(UI ui, String patientId, String medicine, String dosage, String timeInterval) {
        assert patientId != null : "ID should not be null";
        assert medicine != null : "medicine should not be null";
        assert dosage != null : "dosage should not be null";
        assert timeInterval != null : "time interval should not be null";
        Prescription prescription = new Prescription(patientId, medicine, dosage, timeInterval);

        if (!prescriptionsList.contains(prescription)) {
            prescriptionsList.add(prescription);
            ui.printMessageAndObject(prescription.toString(),UI.ADD_PRESCRIPTION,
                    prescriptionsList.indexOf(prescription), UI.PRESCRIPTION);
        } else {
            ui.printMessageAndObject(prescription.toString(),UI.DUPLICATE_PRESCRIPTION_MESSAGE,
                    prescriptionsList.indexOf(prescription), UI.PRESCRIPTION);
        }
    }

    public void loadPrescription(String patientID,
                                 String medicine, String dosage, String timeInterval, boolean active) {
        Prescription prescription = new Prescription(patientID, medicine, dosage, timeInterval, active);
        prescriptionsList.add(prescription);
    }

    public boolean isEmpty() {
        return prescriptionsList.isEmpty();
    }

    /**
     * Show the list of all prescriptions from all patients.
     * @param ui is the user interface.
     */
    public void viewAll(UI ui) {
        if (isEmpty()) {
            ui.printNoPrescriptionMessage();
            return;
        }

        ui.printViewAllPrescriptionsMessage();
        for (int i = 0; i < prescriptionsList.size(); i++) {
            ui.printPrescriptionWithIndex(i + 1, prescriptionsList.get(i).toString());
        }
    }

    /**
     * Show the list of both active and inactive prescriptions of a patient with a specific patientId.
     * @param ui is the user interface instance.
     * @param patientId is the ID of the patient.
     */
    public void viewPatientPrescription(UI ui, String patientId) {
        if (isEmpty() || !hasPatientPrescription(patientId)) {
            ui.printNoMatchingPrescriptionMessage();
            return;
        }

        ui.printViewAllPrescriptionsMessage();
        for (int i = 0; i < prescriptionsList.size(); i++) {
            if (prescriptionsList.get(i).isMatchedPatient(patientId)) {
                ui.printPrescriptionWithIndex(i + 1, prescriptionsList.get(i).toString());
            }
        }
    }

    /**
     * Show the list of active prescriptions of a patient with a specific patientId.
     * @param ui is the user interface instance.
     * @param patientId is the ID of the patient.
     */
    public void viewActivePatientPrescription(UI ui, String patientId) {
        if (isEmpty() || !hasActivePatientPrescription(patientId)) {
            ui.printNoMatchingActivePrescriptionMessage();
            return;
        }

        ui.printViewAllActivePrescriptionsMessage();
        for (int i = 0; i < prescriptionsList.size(); i++) {
            if (prescriptionsList.get(i).isMatchedPatientActive(patientId)) {
                ui.printPrescriptionWithIndex(i + 1, prescriptionsList.get(i).toString());
            }
        }
    }

    /**
     * Edit an existing prescription's detail.
     * The prescription will be edited only if it has a valid prescription number, and it is unique.
     * @param ui is the user interface of OneDoc
     * @param prescriptionNumber is the prescription index shown to user in viewings, ranging from 1 to the size of list
     * @param medicine is a String of medicine name
     * @param dosage is a String of the dosage
     * @param timeInterval is a String of the time interval
     */
    public void edit(UI ui, int prescriptionNumber, String medicine, String dosage, String timeInterval) {
        assert medicine != null : "medicine should not be null";
        assert dosage != null : "dosage should not be null";
        assert timeInterval != null : "time interval should not be null";

        int index = prescriptionNumber - 1;

        if (isInvalidIndex(index)) {
            ui.printIndexOutOfRangeErrorMessage();
            return;
        }

        Prescription prescriptionEdited = prescriptionsList.get(index);

        String newMedicine = medicine.isEmpty() ? prescriptionEdited.getMedicine() : medicine;
        String newDosage = dosage.isEmpty() ? prescriptionEdited.getDosage() : dosage;
        String newTimeInterval = timeInterval.isEmpty() ? prescriptionEdited.getTimeInterval() : timeInterval;

        Prescription newPrescription = new Prescription(prescriptionEdited.getPatientId(), newMedicine, newDosage,
                newTimeInterval);

        // Check if the updated record has a duplicate in the list already.
        if (prescriptionsList.contains(newPrescription)) {
            ui.printMessageAndObject(newPrescription.toString(),UI.DUPLICATE_PRESCRIPTION_MESSAGE,
                    prescriptionsList.indexOf(newPrescription),UI.PRESCRIPTION);
            return;
        }

        if (!medicine.isEmpty()) {
            prescriptionEdited.setMedicine(medicine);
        }

        if (!dosage.isEmpty()) {
            prescriptionEdited.setDosage(dosage);
        }

        if (!timeInterval.isEmpty()) {
            prescriptionEdited.setTimeInterval(timeInterval);
        }

        ui.printMessageAndObject(prescriptionEdited.toString(),UI.EDIT_PRESCRIPTION,index, UI.PRESCRIPTION);
    }

    private boolean hasPatientPrescription(String patientId) {
        for (Prescription prescription : prescriptionsList) {
            if (prescription.isMatchedPatient(patientId)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasActivePatientPrescription(String patientId) {
        for (Prescription prescription : prescriptionsList) {
            if (prescription.isMatchedPatientActive(patientId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Set the prescription status as active.
     * The prescription will only be activated if the prescription number is valid.
     * @param ui is the user interface of OneDoc
     * @param prescriptionNumber is the prescription index shown to user in viewings, ranging from 1 to the size of list
     */
    public void activatePrescription(UI ui, String prescriptionNumber) {
        Integer index = getIndex(ui, prescriptionNumber);
        if (index == null) {
            return;
        }

        Prescription prescriptionEdited = prescriptionsList.get(index);
        prescriptionEdited.setActive();

        ui.printMessageAndObject(prescriptionEdited.toString(),UI.ACTIVATE_PRESCRIPTION,index,UI.PRESCRIPTION);
    }

    /**
     * Set the prescription status as inactive.
     * The prescription will only be deactivated if the prescription number is valid.
     * @param ui is the user interface of OneDoc
     * @param prescriptionNumber is the prescription index shown to user in viewings, ranging from 1 to the size of list
     */
    public void deactivatePrescription(UI ui, String prescriptionNumber) {
        Integer index = getIndex(ui, prescriptionNumber);
        if (index == null) {
            return;
        }

        Prescription prescriptionEdited = prescriptionsList.get(index);
        prescriptionEdited.setInactive();
        ui.printMessageAndObject(prescriptionEdited.toString(),UI.DEACTIVATE_PRESCRIPTION,
                prescriptionsList.indexOf(prescriptionEdited), UI.PRESCRIPTION);
    }

    private Integer getIndex(UI ui, String prescriptionNumber) {
        int index;

        try {
            index = Integer.parseInt(prescriptionNumber) - 1;
        } catch (NumberFormatException e) {
            // Parser class have blocked all inputs that are not integer.
            return null;
        }

        if (isInvalidIndex(index)) {
            ui.printIndexOutOfRangeErrorMessage();
            return null;
        }
        return index;
    }

    private boolean isInvalidIndex(int index) {
        return (index < 0 || index >= prescriptionsList.size());
    }

    public int getTotalPrescriptions() {
        return prescriptionsList.size();
    }
}
