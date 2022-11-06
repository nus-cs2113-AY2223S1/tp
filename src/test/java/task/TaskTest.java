package task;

import command.appointmentcommand.AddAppointmentCommand;
import command.employeecommand.AddEmployeeCommand;
import command.petcommand.AddPetCommand;
import command.servicecommand.AddServiceCommand;
import command.taskcommand.AddTaskCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    void TaskStatus() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 service
        AddServiceCommand addServiceCommand = new AddServiceCommand("Tooth Surgery");
        addServiceCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(2001, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 1 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();

        // Newly created, check if not done
        Task task = new Task(3001, 1001, "Set up equipment");
        assertEquals("Not Done", task.getStatus());

        // Set done, check if done
        task.setDone();
        assertEquals("Done", task.getStatus());
    }

    @Test
    void TaskGetters() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 service
        AddServiceCommand addServiceCommand = new AddServiceCommand("Tooth Surgery");
        addServiceCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(2001, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 1 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();

        Task task = new Task(3001, 1001, "Set up equipment");

        assertEquals(Task.idCounter, task.getTaskId());
        assertEquals("Set up equipment", task.getTaskDescription());
        assertEquals(1001, task.getEmployeeId());
        assertEquals(3001, task.getAppointmentId());
    }
}
