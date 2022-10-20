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
            System.out.println((i + 1) + ")");
            System.out.println(prescriptionsList.get(i));
            ui.printLine();
        }
    }

    // TODO one potential improvement is to make this three different methods
    //  edit(String id, String medicine), edit(String id, String dosage)
    //  and edit(String id, String timeInterval)
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

    public void activatePres(UI ui, String prescriptionNumber) {
        Integer index = getIndex(ui, prescriptionNumber);
        if (index == null) {
            return;
        }

        Prescription prescriptionEdited = prescriptionsList.get(index);
        prescriptionEdited.setActive();

        ui.printActivatePrescriptionMessage(prescriptionEdited.toString());
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

    public void deactivatePres(UI ui, String prescriptionNumber) {
        Integer index = getIndex(ui, prescriptionNumber);
        if (index == null) {
            return;
        }

        Prescription prescriptionEdited = prescriptionsList.get(index);
        prescriptionEdited.setInactive();

        ui.printDeactivatePrescriptionMessage(prescriptionEdited.toString());
    }

    private boolean isInvalidIndex(int index) {
        return (index < 0 || index >= prescriptionsList.size());
    }
}
