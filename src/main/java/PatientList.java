import java.util.ArrayList;

public class PatientList {
    private ArrayList<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
    }

    public void addPatient(UI ui, String name, String birthDate, String gender, String id) {
        Patient patient = new Patient(name, birthDate, gender, id);
        patients.add(patient);
        ui.printPatientAddedMessage(patient);
    }

    public void loadPatient(String name, String birthDate, String gender, String id) {
        Patient patient = new Patient(name, birthDate, gender, id);
        patients.add(patient);
    }

    public void printIndexOfPatient(Patient patient) {
        System.out.print("(" + patients.indexOf(patient) + ")\n");
    }

    public void retrievePatient(UI ui, String id) {
        for (Patient patient : patients) {
            if (patient.getId().equalsIgnoreCase(id)) {
                System.out.println("The patient with the supplied ID was found! Here are the details of the patient: ");
                ui.printLine();
                printIndexOfPatient(patient);
                System.out.println(patient);
                ui.printLine();
                return;
            }
        }
        ui.printLine();
        System.out.println("Sorry, no patient exists with the supplied ID!");
    }

    public Patient findPatient(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    // TODO one potential improvement is to make this three different methods
    //  modifyPatientName(String id, String name), modifyPatientDOB(String id, String birthDate)
    //  and modifyPatientGender(String id, String gender)
    public void modifyPatientDetails(UI ui, String id, String name, String birthDate, String gender) {
        Patient patientToBeModified = findPatient(id);
        if (patientToBeModified == null) {
            ui.printLine();
            System.out.println("Sorry! No patient exists in the system with the supplied ID!");
            ui.printLine();
        }
        ui.printLine();
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
        ui.printPatientEditedMessage(patientToBeModified);
    }

    public boolean isEmpty() {
        return patients.size() == 0;
    }

    public int getTotalNumberofPatients() {
        return patients.size();
    }

    private Patient getPatient(int patientNumber) {
        return patients.get(patientNumber);
    }

    public void listPatients(UI ui) {
        if (isEmpty()) {
            System.out.println("There are no patients in the system right now!");
            return;
        }
        System.out.println("Here are the list of patients in the system");
        for (int i = 0; i < getTotalNumberofPatients(); i++) {
            ui.printLine();
            System.out.println((i + 1) + ")");
            System.out.println(getPatient(i));
        }

        ui.printLine();

    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

}
