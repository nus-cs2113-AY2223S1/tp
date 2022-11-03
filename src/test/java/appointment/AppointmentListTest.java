package appointment;

import command.appointmentcommand.AddAppointmentCommand;
import command.appointmentcommand.RemoveAppointmentCommand;
import command.petcommand.AddPetCommand;
import command.servicecommand.AddServiceCommand;
import org.junit.jupiter.api.Test;
import pet.Pet;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class AppointmentListTest {

    @Test
    void listAppointment() {

    }

    @Test
    void findAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Feed");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Meow", "cat", true);
        addPetCommand.execute();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.id, "11-28", "Feed");
        addAppointmentCommand.execute();
        Appointment foundAppointment = AppointmentList.findAppointment(Appointment.id);
        if (foundAppointment != null) {
            assertEquals(foundAppointment.appointmentId, Appointment.id);
        } else {
            assertNull(foundAppointment);
        }
    }

    @Test
    void addAnAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Trim");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Yuhuan", "cat", true);
        addPetCommand.execute();
        int numOfAppointment = AppointmentList.appointments.size();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.id, "11-28", "Trim");
        addAppointmentCommand.execute();
        int numOfAppointmentAfterAdd = AppointmentList.appointments.size();
        assertEquals(numOfAppointmentAfterAdd - numOfAppointment, 1);
    }

    @Test
    void removeAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Brush");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Mimi", "cat", true);
        addPetCommand.execute();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.id, "11-28", "Brush");
        addAppointmentCommand.execute();
        int numOfAppointments = AppointmentList.appointments.size();
        RemoveAppointmentCommand removeAppointmentCommand = new RemoveAppointmentCommand(Appointment.id);
        removeAppointmentCommand.execute();
        int numOfAppointmentsAfterRemove = AppointmentList.appointments.size();
        assertEquals(-1,numOfAppointmentsAfterRemove - numOfAppointments);
    }

    @Test
    void updateAppointmentStatus() {
    }

    @Test
    void viewAppointmentTasks() {
    }
}