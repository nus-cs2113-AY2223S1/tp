package appointment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentListTest {

    @Test
    void listAppointment() {

    }

    @Test
    void findAppointment() {
        Appointment appointment = new Appointment("Bobby", "11-28", "Massage");
        AppointmentList.addAppointment(appointment);
        Appointment foundAppointment = AppointmentList.findAppointment(1);
        assertEquals(foundAppointment.appointmentId, 1);
    }

    @Test
    void addAppointment() {
        Appointment appointment = new Appointment("Bobby", "11-28", "Massage");
        AppointmentList.addAppointment(appointment);
        assertEquals(AppointmentList.appointments.get(0).appointmentId, 1);
    }

    @Test
    void removeAppointment() {
        Appointment appointment = new Appointment("Bobby", "11-28", "Massage");
        AppointmentList.addAppointment(appointment);
        AppointmentList.removeAppointment(1);
        assertEquals(AppointmentList.appointments.size(), 0);
    }

    @Test
    void updateAppointmentStatus() {
    }

    @Test
    void viewAppointmentTasks() {
    }
}