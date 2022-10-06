import java.util.ArrayList;

public class PatientList {
    public ArrayList<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
    }

    public void addPatient(String name, String birthDate, String gender, String id) {
        patients.add(new Patient(name, birthDate, gender, id));
    }

    public void retrievePatient(String id) {
        for (Patient patient: patients) {
            if (patient.getId().equals(id)) {
                Messages.printLine();
                System.out.println("The patient with the supplied ID was found! Here are the details of the patient: ");
                System.out.println(patient);
                Messages.printLine();
                return;
            }
        }
        Messages.printLine();
        System.out.println("Sorry, no patient exists with the supplied ID!");
        Messages.printLine();
    }

}
