package appointment;

import java.util.ArrayList;

public class AppointmentList {
    static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void listAppointment() {
        for (Appointment appointment : appointments) {
            System.out.print(appointment.appointmentId + " ");
            System.out.print(appointment.petName + " ");
            // System.out.print(appointment.service.name + " ");
            System.out.print(appointment.getAppointmentDate() + " ");
            System.out.println(appointment.getAppointmentStatus());
        }
    }

    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public static void removeAppointment(int appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {
                appointments.remove(appointment);
                break;
            }
        }
    }
}
