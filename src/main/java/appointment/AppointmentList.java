package appointment;

import employee.Employee;
import exception.DukeException;
import pet.Pet;
import pet.PetList;
import service.Service;
import service.ServiceList;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

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

    public static Appointment findAppointment(int appointmentId) {
        // return appointment based on id
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {
                return appointment;
            }
        }
        return null;
    }


    public static void addAppointment(Appointment appointment) throws DukeException {

        // appointment should refer to one existing service
        Service service = ServiceList.findService(appointment.service);
        if (service == null) {
            throw new DukeException();
        }

        // appointment could refer to a new pet
        // in that case, add a new pet accordingly
        Pet pet = PetList.findPet(appointment.petName);
        if (pet == null) {

        }
        appointments.add(appointment);
        System.out.print("Got it. I've added this appointment: ");
        System.out.println("Pet " + appointment.petName + " | " + "Service " + appointment.service);
        System.out.println("Now you have " + appointments.size() + " appointments in the list.");

    }

    public static void removeAppointment(int appointmentId) {
        boolean removeFlag = false;
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {
                appointments.remove(appointment);
                System.out.print("Noted. I've removed this appointment: ");
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

    private static AppointmentStatus intToAppointmentStatus(int statusIdx) {
        switch (statusIdx) {
        case 0:
            // fall through
        case 1:
            return AppointmentStatus.PROCESSING;
        case 2:
            return AppointmentStatus.PROCESSED;
        default:
            return AppointmentStatus.PENDING;
        }
    }

    public static void updateAppointmentStatus(int appointmentId) {
        boolean setFlag = false;
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {

                appointment.updateAppointmentStatus();
                System.out.print("Noted. I've set this service: ");
                System.out.print("Pet " + appointment.petName + " | " + "Service " + appointment.service);
                System.out.println(" as " + appointment.getAppointmentStatus());
                setFlag = true;
            }
        }
        if (!setFlag) {
            System.out.println("Sorry, no corresponding appointment found.");
        }
    }

    public static void viewAppointmentTasks(int appointmentId) {
        AppointmentList.findAppointment(appointmentId).viewTasks();
    }

}
