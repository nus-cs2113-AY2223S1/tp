package appointment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    @Test
    public void initAppointmentStatusWithPending() {
        Appointment appointment = new Appointment("Bobby", "11-28", "Massage");
        assertEquals(appointment.getAppointmentStatus(), "PENDING");
    }
}