package appointment;

import service.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private static int id = 0;
    private static DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public final int appointmentId;
    public String petName;

    //    private LocalDateTime appointmentDate;
    private String appointmentDate;
    private AppointmentStatus appointmentStatus;
    private Service service;

    // Service class not implemented yet
    // How to create a LocalDateTime:
    // LocalDateTime newDate = LocalDateTime.of(2021, Month.NOVEMBER, 19, 1, 1, 45);
    public Appointment(String petName, String appointmentDate, Service service) {
        this.appointmentId = ++id;
        this.petName = petName;
        this.appointmentStatus = AppointmentStatus.PENDING;
        this.appointmentDate = appointmentDate;
        this.service = service;
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

    public String getAppointmentDate() {
        return appointmentDate;
//        return appointmentDate.format(dataTimeFormatter);
    }
}