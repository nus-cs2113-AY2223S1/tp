import java.util.ArrayList;

public class PatientList {
    public static ArrayList<Patient> patients = new ArrayList<>();

    public static void addPatient(String name, String birthDate,String gender){
        patients.add(new Patient(name,birthDate,gender));
    }

}
