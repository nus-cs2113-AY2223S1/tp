package appointment;

import command.appointmentcommand.AddAppointmentCommand;
import command.appointmentcommand.RemoveAppointmentCommand;
import command.petcommand.AddPetCommand;
import command.servicecommand.AddServiceCommand;
import exception.DukeException;
import org.junit.jupiter.api.Test;
import pet.Pet;
import pet.PetList;
import service.Service;
import service.ServiceList;

import javax.print.DocFlavor;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class AppointmentListTest {

    @Test
    void listAppointment() {
        Pet pet = new Pet("Yuhuan", "cat", true);
        Pet pet2 = new Pet("Benben", "Sexydog", true);
        PetList.pets.add(pet);
        PetList.pets.add(pet2);
        AddServiceCommand addServiceCommand = new AddServiceCommand("Trim");
        addServiceCommand.execute();

        Appointment appointment1 = new Appointment(2001, new Date(), "Trim");
        Appointment appointment2 = new Appointment(2002, new Date(), "Trim");
        AppointmentList.appointments.add(appointment1);
        AppointmentList.appointments.add(appointment2);
        assertNotNull(AppointmentList.appointments);
        AppointmentList.listAppointment();
    }

    @Test
    void findAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Feed");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Meow", "cat", true);
        addPetCommand.execute();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.idCounter, "11-28", "Feed");
        addAppointmentCommand.execute();
        Appointment foundAppointment = AppointmentList.findAppointment(Appointment.idCounter);
        assertEquals(foundAppointment.appointmentId, Appointment.idCounter);
        Appointment notFoundAppointment = AppointmentList.findAppointment(Appointment.idCounter + 1);
        assertNull(notFoundAppointment);
    }

    @Test
    void addAnAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Trim");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Yuhuan", "cat", true);
        addPetCommand.execute();
        int numOfAppointment = AppointmentList.appointments.size();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.idCounter, "2022-11-28", "Trim");
        addAppointmentCommand.execute();
        int numOfAppointmentAfterAdd = AppointmentList.appointments.size();
        assertEquals(numOfAppointmentAfterAdd - numOfAppointment, 1);
    }

    @Test
    void addNullDateAppointmentTest() {
        assertThrows(DukeException.class,
                ()->{
                Pet pet = new Pet("Yuhuan", "cat", true);
                Appointment appointment = new Appointment(2001, null, "Meow meow");
                AppointmentList.addAppointment(appointment);
                });
    }

    @Test
    void addInvalidServiceAppointmentTest() {
        assertThrows(DukeException.class,
                ()->{
                    Pet pet = new Pet("Yuhuan", "cat", true);
                    Appointment appointment = new Appointment(2001, new Date(), "Non exist service");
                    AppointmentList.addAppointment(appointment);
                });
    }

    @Test
    void addInvalidPetAppointmentTest() {
        assertThrows(DukeException.class,
                ()->{
                    AddServiceCommand addServiceCommand = new AddServiceCommand("Trim");
                    addServiceCommand.execute();
                    Appointment appointment = new Appointment(2000, null, "Trim");
                    AppointmentList.addAppointment(appointment);
                });
    }


    @Test
    void removeAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Brush");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Mimi", "cat", true);
        addPetCommand.execute();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.idCounter, "2022-11-28", "Brush");
        addAppointmentCommand.execute();
        int numOfAppointments = AppointmentList.appointments.size();
        RemoveAppointmentCommand removeAppointmentCommand = new RemoveAppointmentCommand(Appointment.idCounter);
        removeAppointmentCommand.execute();
        int numOfAppointmentsAfterRemove = AppointmentList.appointments.size();
        assertEquals(-1,numOfAppointmentsAfterRemove - numOfAppointments);
    }

    @Test
    void intToAppointmentStatusTest() {
        assertEquals(AppointmentList.intToAppointmentStatus(0), AppointmentStatus.PENDING);
        assertEquals(AppointmentList.intToAppointmentStatus(1), AppointmentStatus.PROCESSING);
        assertEquals(AppointmentList.intToAppointmentStatus(2), AppointmentStatus.PROCESSED);
        assertNull(AppointmentList.intToAppointmentStatus(-1));
    }

    @Test
    void updateAppointmentStatus() {
        Pet pet = new Pet("Yuhuan", "cat", true);
        Pet pet2 = new Pet("Benben", "Sexydog", true);
        PetList.pets.add(pet);
        PetList.pets.add(pet2);
        AddServiceCommand addServiceCommand = new AddServiceCommand("Hug");
        addServiceCommand.execute();
        Appointment appointment1 = new Appointment(2001, new Date(), "Hug");
        Appointment appointment2 = new Appointment(2002, new Date(), "Hug");
        AppointmentList.appointments.add(appointment1);
        AppointmentList.appointments.add(appointment2);
        assertEquals(AppointmentList.updateAppointmentStatus(Appointment.idCounter), true);
        assertEquals(AppointmentList.updateAppointmentStatus(0000), false);
    }

    @Test
    void viewAppointmentTasks() {
    }
}