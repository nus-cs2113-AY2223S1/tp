package task;

import appointment.AppointmentList;
import command.appointmentcommand.AddAppointmentCommand;
import command.employeecommand.AddEmployeeCommand;
import command.petcommand.AddPetCommand;
import command.taskcommand.AddTaskCommand;
import command.taskcommand.ReassignTaskCommand;
import command.taskcommand.RemoveTaskCommand;
import employee.EmployeeList;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void viewTasks() {
    }

    @Test
    void addTask() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(2001, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 1 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();

        int TaskListSize = TaskList.getTasks().size();
        int EmployeeTaskListSize = EmployeeList.findEmployee(1001).getTasks().size();
        int AppointmentTaskListSize = AppointmentList.findAppointment(3001).getTasks().size();

        // Add a task associated to the above appointment and employee
        AddTaskCommand addTaskCommand = new AddTaskCommand(3001, 1001, "Set up equipment");
        addTaskCommand.execute();

        int TaskListSizeAfterAdd = TaskList.getTasks().size();
        int EmployeeTaskListSizeAfterAdd = EmployeeList.findEmployee(1001).getTasks().size();
        int AppointmentTaskListSizeAfterAdd = AppointmentList.findAppointment(3001).getTasks().size();

        // To check if the Task is successfully added to three places, TaskList, Employee's TaskList and Appointment's TaskList
        assertEquals(1, TaskListSizeAfterAdd - TaskListSize);
        assertEquals(1, EmployeeTaskListSizeAfterAdd - EmployeeTaskListSize);
        assertEquals(1, AppointmentTaskListSizeAfterAdd - AppointmentTaskListSize);
    }

    @Test
    void reassignTask() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(2001, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 2 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();
        AddEmployeeCommand addEmployeeCommand2 = new AddEmployeeCommand("Sally");
        addEmployeeCommand2.execute();
        // Add Task, to be done by Tom
        AddTaskCommand addTaskCommand = new AddTaskCommand(3001, 1001, "Set up equipment");
        addTaskCommand.execute();

        int TomTasklistSize = Objects.requireNonNull(EmployeeList.findEmployee(1001)).getTasks().size();
        int SallyTasklistSize = Objects.requireNonNull(EmployeeList.findEmployee(1002)).getTasks().size();

        // Reassign Task from Tom to Sally
        ReassignTaskCommand reassignTaskCommand = new ReassignTaskCommand(4001, 1002);

        int TomTasklistSizeAfterReassign = Objects.requireNonNull(EmployeeList.findEmployee(1001)).getTasks().size();
        int SallyTasklistSizeAfterReassign = Objects.requireNonNull(EmployeeList.findEmployee(1002)).getTasks().size();

        assertEquals(1, SallyTasklistSizeAfterReassign - SallyTasklistSize);
        assertEquals(-1, TomTasklistSizeAfterReassign - TomTasklistSize);
    }

    @Test
    void removeTask() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(2001, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 1 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();

        // Add a task associated to the above appointment and employee
        AddTaskCommand addTaskCommand = new AddTaskCommand(3001, 1001, "Set up equipment");
        addTaskCommand.execute();

        int TaskListSizeAfterAdd = TaskList.getTasks().size();
        int EmployeeTaskListSizeAfterAdd = EmployeeList.findEmployee(1001).getTasks().size();
        int AppointmentTaskListSizeAfterAdd = AppointmentList.findAppointment(3001).getTasks().size();

        // Remove the above added task
        RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(4001);
        removeTaskCommand.execute();

        int TaskListSizeAfterRemove = TaskList.getTasks().size();
        int EmployeeTaskListSizeAfterRemove = EmployeeList.findEmployee(1001).getTasks().size();
        int AppointmentTaskListSizeAfterRemove = AppointmentList.findAppointment(3001).getTasks().size();

        assertEquals(-1, TaskListSizeAfterRemove - TaskListSizeAfterAdd);
        assertEquals(-1, EmployeeTaskListSizeAfterRemove - EmployeeTaskListSizeAfterAdd);
        assertEquals(-1, AppointmentTaskListSizeAfterRemove - AppointmentTaskListSizeAfterAdd);

    }

    @Test
    void findTask() {
        assertEquals(null, TaskList.findTask(987654321));
    }

}