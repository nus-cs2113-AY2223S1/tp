import java.util.ArrayList;

public class Patient {
    private String name;
    private String birthDate;
    private String gender;
    private final String id;
    public ArrayList<Visit> visits;
    public ArrayList<Prescription> prescriptions;


    public Patient(String name, String birthDate, String gender, String id) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.visits = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    // todo add public void addVisit(); public void addMedicine();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
