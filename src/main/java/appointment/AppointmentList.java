package appointment;

import exception.DukeException;
import pet.Pet;
import pet.PetList;
import service.Service;
import service.ServiceList;

import java.util.ArrayList;

public class AppointmentList {
    static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void listAppointment() {
        System.out.println("Here are the appointments in your list:");
        for (Appointment appointment : appointments) {
            System.out.print(appointment.appointmentId + " ");
            System.out.print(appointment.petId + " ");
            System.out.print(PetList.findPetById(appointment.petId) + " ");
            System.out.print(appointment.service + " ");
            System.out.print(appointment.getAppointmentDateStr() + " ");
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

        // appointment should have a valid date
        if (appointment.getAppointmentDate() == null) {
            Appointment.idCounter--;
            throw new DukeException();
        }

        // appointment should refer to one existing service
        Service service = ServiceList.findService(appointment.service);
        if (service == null) {
            System.out.println("Sorry, no corresponding service found to add the appointment.");
            Appointment.idCounter--;
            throw new DukeException();
        }

        // appointment should refer to one existing pet
        Pet pet = PetList.findPetById(appointment.petId);
        if (pet == null) {
            System.out.println("Sorry, no corresponding pet found to add the appointment.");
            Appointment.idCounter--;
            throw new DukeException();
        }

        appointments.add(appointment);
        System.out.print("Got it. I've added this appointment: ");
        System.out.println("Pet " + appointment.petId + " | " + "Service " + appointment.service);
        System.out.println("Now you have " + appointments.size() + " appointments in the list.");

    }

    public static void removeAppointment(int appointmentId) {
        boolean removeFlag = false;
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {
                appointments.remove(appointment);
                System.out.print("Noted. I've removed this appointment: ");
                System.out.println("Pet " + appointment.petId + " | " + "Service " + appointment.service);
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
                System.out.print("Pet " + appointment.petId + " | " + "Service " + appointment.service);
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
