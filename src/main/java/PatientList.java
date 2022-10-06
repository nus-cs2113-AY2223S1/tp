import java.util.ArrayList;

public class PatientList {
    public ArrayList<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
    }

    public void addPatient(String name, String birthDate, String gender, String id) {
        patients.add(new Patient(name, birthDate, gender, id));
    }

    public Patient findPatient(String id) {
        for (Patient patient: patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public void modifyPatientDetails(String id, String name, String birthDate, String gender) {
        Patient patientToBeModified = findPatient(id);
        if (patientToBeModified == null) {
            Messages.printLine();
            System.out.println("Sorry! No patient exists in the system with the supplied ID!");
            Messages.printLine();
        }
        Messages.printLine();
        if (!name.isEmpty()) {
            patientToBeModified.setName(name);
        }
        if (!birthDate.isEmpty()) {
            patientToBeModified.setBirthDate(birthDate);
        }
        if (!gender.isEmpty()) {
            patientToBeModified.setGender(gender);
        }
        System.out.println();
        System.out.println("The patient's details has been modified! Here are the new patient details!");
        System.out.println(patientToBeModified);
        Messages.printLine();
    }

}
