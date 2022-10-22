package appointment;

import java.util.ArrayList;

public class AppointmentList {
    static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void listAppointment() {
        System.out.println("Here are the appointments in your employee list:");
        for (Appointment appointment : appointments) {
            System.out.print(appointment.appointmentId + " ");
            System.out.print(appointment.petName + " ");
            System.out.print(appointment.service + " ");
            System.out.print(appointment.getAppointmentDate() + " ");
            System.out.println(appointment.getAppointmentStatus());
        }
    }

    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.print("Got it. I've added this appointment:");
        System.out.println("Pet " + appointment.petName + " | " + "Service " + appointment.service);
        System.out.println("Now you have " + appointments.size() + " appointments in the list.");

    }

    public static void removeAppointment(int appointmentId) {
        boolean removeFlag = false;
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {
                appointments.remove(appointment);
                System.out.print("Noted. I've removed this appointment:");
                System.out.println("Pet " + appointment.petName + " | " + "Service " + appointment.service);
                System.out.println("Now you have " + (appointments.size()) + " appointments in the list.");
                removeFlag = true;
                break;
            }
        }
        if (!removeFlag) {
            System.out.println("Sorry, no corresponding appointment found.");
        }
    }

    public static void setAppointmentStatus(int appointmentId, int appointmentStatus) {
        boolean setFlag = false;
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {
                switch (appointmentStatus) {
                case 0:
                    appointment.setAppointmentStatus(AppointmentStatus.PENDING);
                    setFlag = true;
                    break;
                case 1:
                    appointment.setAppointmentStatus(AppointmentStatus.PROCESSING);
                    setFlag = true;
                    break;
                }
                System.out.print("Noted. I've set this service: ");
                System.out.print("Pet " + appointment.petName + " | " + "Service " + appointment.service);
                System.out.print("as " + appointment.getAppointmentStatus());
            }
        }
        if (!setFlag) {
            System.out.println("Sorry, no corresponding appointment found.");
        }
    }
}
