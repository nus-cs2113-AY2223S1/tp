import java.lang.reflect.Array;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    static int id = 0;
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public final int appointmentId;
    public String petName;

    private LocalDateTime appointmentDate;
    private AppointmentStatus appointmentStatus;
    // private Service service;

    // Service class not implemented yet
    // How to create a LocalDateTime:
    // LocalDateTime newDate = LocalDateTime.of(2021, Month.NOVEMBER, 19, 1, 1, 45);
    public Appointment(String petName, LocalDateTime appointmentDate, Service service) {
        this.appointmentId = ++id;
        this.petName = petName;
        this.appointmentStatus = AppointmentStatus.PENDING;
        this.appointmentDate = appointmentDate;
        // this.service = service;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }


}
