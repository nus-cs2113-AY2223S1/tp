public class Prescription {
    private String medicine;
    private String timeInterval;
    private final String patientId;
    private String dosage;
    private boolean isActive;

    private static final String ACTIVE = "Active";
    private static final String INACTIVE = "Inactive";

    public Prescription(String patientId, String medicine, String dosage, String timeInterval) {
        this (patientId, medicine, dosage, timeInterval, true);
    }

    public Prescription(String patientId, String medicine, String dosage, String timeInterval, boolean isActive) {
        this.patientId = patientId;
        this.medicine = medicine;
        this.dosage = dosage;
        this.timeInterval = timeInterval;
        this.isActive = isActive;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setActive() {
        isActive = true;
    }

    public void setInactive() {
        isActive = false;
    }

    private String getStatusString() {
        return isActive ? ACTIVE : INACTIVE;
    }

    public boolean isMatchedPatient(String patientId) {
        return this.patientId.equalsIgnoreCase(patientId);
    }

    public boolean isMatchedPatientActive(String patientId) {
        return isMatchedPatient(patientId) && isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "\tID: " + patientId + System.lineSeparator()
                + "\tMedicine: " + medicine + System.lineSeparator()
                + "\tDosage: " + dosage + System.lineSeparator()
                + "\tTime Interval: " + timeInterval + System.lineSeparator()
                + "\tStatus: " + getStatusString();
    }
}
