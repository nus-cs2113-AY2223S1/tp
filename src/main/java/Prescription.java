public class Prescription {
    private String medicine;
    private int timeInterval;
    private final int patientId;
    private int dosage;
    private boolean isActive;

    public Prescription(int patientId, String medicine, int timeInterval, int dosage) {
        this.patientId = patientId;
        this.medicine = medicine;
        this.timeInterval = timeInterval;
        this.dosage = dosage;
        this.isActive = true;
    }

    public Prescription(int patientId, String medicine, int timeInterval, int dosage, boolean isActive) {
        this.patientId = patientId;
        this.medicine = medicine;
        this.timeInterval = timeInterval;
        this.dosage = dosage;
        this.isActive = isActive;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    private void setActive(boolean active) {
        isActive = active;
    }
}
