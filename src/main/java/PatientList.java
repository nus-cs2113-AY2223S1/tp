import java.util.ArrayList;

public class PatientList {
    public ArrayList<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
    }

    public void addPatient(String name, String birthDate, String gender) {
        patients.add(new Patient(name, birthDate, gender));
    }

}
