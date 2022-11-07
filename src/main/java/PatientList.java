import java.util.ArrayList;

public class PatientList {
    private ArrayList<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
    }

    public void addPatient(UI ui, String name, String birthDate, String gender, String id) {
        assert name != null : "name of patient should not be null!";
        assert birthDate != null : "birthdate of patient should not be null!";
        assert gender != null : "gender of patient should not be null!";
        assert id != null : "id of patient should not be null!";
        Patient patient = new Patient(name, birthDate, gender, id);
        patients.add(patient);
        ui.printMessageAndObject(patient.toString(), UI.PATIENT_ADDED, patients.indexOf(patient), UI.PATIENT);
    }

    public void loadPatient(String name, String birthDate, String gender, String id) {
        Patient patient = new Patient(name, birthDate, gender, id);
        patients.add(patient);
    }

    public void retrievePatient(UI ui, String id) {
        for (Patient patient : patients) {
            if (patient.getId().equalsIgnoreCase(id)) {
                ui.printMessageAndObject(patient.toString(),
                        UI.PATIENT_RETRIEVED, patients.indexOf(patient), UI.PATIENT);
                return;
            }
        }
        ui.printNoPatientFound();
    }

    public Patient findPatient(String id) {
        assert id != null : "id of patient should not be null!";
        for (Patient patient : patients) {
            if (patient.getId().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    public void modifyPatientDetails(UI ui, String id, String name, String birthDate, String gender) {
        Patient patientToBeModified = findPatient(id);
        if (patientToBeModified == null) {
            ui.printNoPatientFound();
            return;
        }
        if (!name.isEmpty()) {
            patientToBeModified.setName(name);
        }
        if (!birthDate.isEmpty()) {
            patientToBeModified.setBirthDate(birthDate);
        }
        if (!gender.isEmpty()) {
            patientToBeModified.setGender(gender);
        }
        ui.printMessageAndObject(patientToBeModified.toString(),UI.PATIENT_EDITED,
                patients.indexOf(patientToBeModified), UI.PATIENT);
    }

    public boolean isEmpty() {
        return patients.size() == 0;
    }

    public int getTotalNumberofPatients() {
        return patients.size();
    }

    public Patient getPatient(int patientNumber) {
        return patients.get(patientNumber);
    }

    public boolean containsPatientID(String id) {
        for (Patient patient: patients) {
            if (patient.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void listPatients(UI ui) {
        if (isEmpty()) {
            ui.printNoPatientsMessage();
            return;
        }
        ui.printPatientList(this);
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

}
