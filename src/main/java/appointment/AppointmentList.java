package appointment;

import exception.DukeException;
import pet.Pet;
import pet.PetList;
import service.Service;
import service.ServiceList;

import java.util.ArrayList;

public class AppointmentList {
    public static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void listAppointment() {
        System.out.println("Here are the appointments in your list:");
        int count = 0;
        for (Appointment appointment : appointments) {
            System.out.print(++count + ". ");
            System.out.print("ID:" + appointment.appointmentId + "\t");
            System.out.print("PetID:" + appointment.petId + "\t");
            System.out.print("Service:" + appointment.service + "\t");
            System.out.print("Date:" + appointment.getAppointmentDateStr() + "\t");
            System.out.println("Status:" + appointment.getAppointmentStatus());
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
        System.out.println("Pet " + pet.name + "(" + pet.petId + ")" + "\t" + "Service " + appointment.service);
        System.out.println("Now you have " + appointments.size() + " appointments in the list.");

    }

    public static void removeAppointment(int appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {
                appointments.remove(appointment);
                System.out.print("Noted. I've removed this appointment: ");
                System.out.println("Pet " + appointment.petId + " | " + "Service " + appointment.service);
                System.out.println("Now you have " + (appointments.size()) + " appointments in the list.");
                return;
            }
        }
        System.out.println("Sorry, no corresponding appointment found.");
    }

    public static AppointmentStatus intToAppointmentStatus(int statusIdx) {
        switch (statusIdx) {
        case 0:
            return AppointmentStatus.PENDING;
        case 1:
            return AppointmentStatus.PROCESSING;
        case 2:
            return AppointmentStatus.PROCESSED;
        default:
            return null;
        }
    }

    public static Boolean updateAppointmentStatus(int appointmentId) {

        for (Appointment appointment : appointments) {
            if (appointment.appointmentId == appointmentId) {

                appointment.updateAppointmentStatus();
                System.out.print("Noted. I've set this service: ");
                System.out.print("Pet " + appointment.petId + " | " + "Service " + appointment.service);
                System.out.println(" as " + appointment.getAppointmentStatus());
                return true;
            }
        }
        System.out.println("Sorry, no corresponding appointment found.");
        return false;
    }

}
