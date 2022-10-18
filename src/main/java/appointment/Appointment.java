package appointment;

import service.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private static int id = 0;

    public final int appointmentId;
    public String petName;
    public String service;

    private String appointmentDate;
    private AppointmentStatus appointmentStatus;

    public Appointment(String petName, String appointmentDate, String service) {
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
    }
}