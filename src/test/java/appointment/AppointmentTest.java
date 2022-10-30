package appointment;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AppointmentTest {

    @Test
    public void initAppointmentStatusWithPending() {
        Date date = new Date();
        Appointment appointment = new Appointment(3003, date, "Massage");
        assertEquals(appointment.getAppointmentStatus(), "PENDING");
    }
}