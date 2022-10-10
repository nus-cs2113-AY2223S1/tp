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

    public String getAppointmentStatus() {
        if (appointmentStatus == AppointmentStatus.PENDING) {
            return "PENDING";
        }
        if (appointmentStatus == AppointmentStatus.PROCESSING) {
            return "PROCESSING";
        }
        // control should never reach here
        return "";
    }

    public static void listAppointment() {
        for (Appointment appointment : appointments) {
            System.out.print(appointment.appointmentId + " ");
            System.out.print(appointment.petName + " ");
            // System.out.print(appointment.service.name + " ");
            System.out.print(appointment.appointmentDate.format(dataTimeFormatter) + " ");
            System.out.println(appointment.getAppointmentStatus());
        }
    }

}
