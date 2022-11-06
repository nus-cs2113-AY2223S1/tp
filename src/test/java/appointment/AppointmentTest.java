package appointment;

import employee.Employee;
import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.*;
import employee.Employee;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AppointmentTest {

    @Test
    public void initAppointmentStatusWithPendingTest() {
        Date date = new Date();
        Appointment appointment = new Appointment(3003, date, "Massage");
        assertEquals(appointment.getAppointmentStatus(), "PENDING");
    }

    @Test
    public void updateAppointmentStatusTest() {
        Date date = new Date();
        Appointment appointment = new Appointment(3003, date, "Massage");
        Employee employee = new Employee("Qiqi son");
        Task task = new Task(3001, 1001, "Walk yuhuan");

        try {
            TaskList.addTask(task);
            assertEquals(appointment.getAppointmentStatus(), "PROCESSING");
            task.setDone();
            assertEquals(task.getStatus(), "Done");
            assertEquals(appointment.getAppointmentStatus(), "PROCESSED");
        } catch (DukeException e) {
            // no-op
        }
    }

    @Test
    public void checkFormattedDateTest() {

    }

}