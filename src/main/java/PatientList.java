import java.util.ArrayList;

public class PatientList {
    private ArrayList<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
    }

    public void addPatient(String name, String birthDate, String gender, String id) {
        patients.add(new Patient(name, birthDate, gender, id));
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

    public void listPatients() {
        if (isEmpty()) {
            System.out.println("There are no patients in the system right now!");
            return;
        }
        System.out.println("Here are the list of patients in the system");
        for (int i = 0; i < getTotalNumberofPatients(); i++) {
            Messages.printLine();
            System.out.println((i + 1) + ")");
            System.out.println(getPatient(i));
        }
        Messages.printLine();
    }

}
