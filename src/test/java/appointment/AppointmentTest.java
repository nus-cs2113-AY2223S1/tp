package appointment;

import command.petcommand.AddPetCommand;
import command.servicecommand.AddServiceCommand;
import command.taskcommand.AddTaskCommand;
import employee.Employee;
import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


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
            task.setDone();
            assertEquals(task.getStatus(), "Done");
            appointment.updateAppointmentStatus();
            assertEquals(appointment.getAppointmentStatus(), "PROCESSED");
        } catch (DukeException e) {
            // no-op
        }
    }

    @Test
    public void checkInvalidFormattedDateTest() {
        String validDate = "2023-1-1";
        Date date1 = Appointment.checkFormattedDate(validDate);
        assertEquals(Appointment.formatter.format(date1), "2023-01-01");

        String oldDate = "2022-1-1";
        Date date2 = Appointment.checkFormattedDate(oldDate);
        assertNull(date2);

        String invalideMonthDate = "2022-13-1";
        Date date3 = Appointment.checkFormattedDate(invalideMonthDate);
        assertNull(date3);

        String invalidDayDate = "2022-02-30";
        Date date4 = Appointment.checkFormattedDate(invalidDayDate);
        assertNull(date4);

        String largeDate = "22222-1-1";
        Date date5 = Appointment.checkFormattedDate(largeDate);
        assertNull(date5);
    }

    @Test
    public void removeTaskFromAppointmentTest() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Trim");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Yuhuan", "cat", true);
        addPetCommand.execute();
        Appointment appointment = new Appointment(2001, new Date(), "Trim");
        AddTaskCommand addTaskCommand = new AddTaskCommand(3001, 1001, "Cuddle with Yuhuan");
        addTaskCommand.execute();
        appointment.removeTaskFromAppointment(4001);
        assertEquals(appointment.tasks.size(), 0);
    }
}