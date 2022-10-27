package appointment;

import command.appointmentcommand.AddAppointmentCommand;
import exception.DukeException;
import org.junit.jupiter.api.Test;
import seedu.duke.Duke;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AppointmentListTest {

    @Test
    void listAppointment() {

    }

    @Test
    void findAppointment() throws DukeException {
        Appointment appointment = new Appointment("Bobby", "11-28", "Massage");
        AppointmentList.addAppointment(appointment);
        Appointment foundAppointment = AppointmentList.findAppointment(1);
        assertEquals(foundAppointment.appointmentId, 1);
    }

    @Test
    void addAnAppointment() {
        int numOfAppointment = AppointmentList.appointments.size();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand("Bobby", "11-28", "Massage");
        addAppointmentCommand.execute();
        int numOfAppointmentAfterAdd = AppointmentList.appointments.size();
        assertEquals(numOfAppointmentAfterAdd - numOfAppointment, 1);
    }

    @Test
    void removeAppointment() throws DukeException{
        Appointment appointment = new Appointment("Bobby", "11-28", "Massage");
        AppointmentList.addAppointment(appointment);
        int numOfAppointments = AppointmentList.appointments.size();
        AppointmentList.removeAppointment(1);
        int numOfAppointmentsAfterRemove = AppointmentList.appointments.size();
        assertEquals(numOfAppointmentsAfterRemove - numOfAppointments, -1);
    }

    @Test
    void updateAppointmentStatus() {
    }

    @Test
    void viewAppointmentTasks() {
    }
}