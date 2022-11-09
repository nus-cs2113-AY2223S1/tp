package employee;


import appointment.Appointment;
import command.appointmentcommand.AddAppointmentCommand;
import command.employeecommand.AddEmployeeCommand;
import command.petcommand.AddPetCommand;
import command.servicecommand.AddServiceCommand;
import command.taskcommand.AddTaskCommand;
import org.junit.jupiter.api.Test;
import pet.Pet;
import task.Task;
import task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {
    @Test
    public void initEmployee() {
        Employee newEmployee = new Employee("Yogurt");
        assertEquals(newEmployee.getEmployeeName(), "Yogurt");
    }

    @Test
    public void addInvalidTaskToEmployeeTest() {
        Employee newEmployee = new Employee("Yogurt");
        int numOfTask = TaskList.getTasks().size();
        newEmployee.addTaskToEmployee(new Task(3002, 1002, "wash"));
        int numOfTaskAfterAdd = TaskList.getTasks().size();
        assertEquals(numOfTaskAfterAdd - numOfTask, 0);
    }

    @Test
    public void removeInvalidTaskToEmployeeTest() {
        Employee newEmployee = new Employee("Yogurt");
        int numOfTask = TaskList.getTasks().size();
        newEmployee.removeTaskFromEmployee(4002);
        int numOfTaskAfterRemove = TaskList.getTasks().size();
        assertEquals(numOfTaskAfterRemove - numOfTask, 0);
    }

    /*
    @Test
    public void viewTasksTest() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Feed");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Meow", "cat", true);
        addPetCommand.execute();
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Yogurt");
        addEmployeeCommand.execute();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(2003, "2022-11-28", "Feed");
        addAppointmentCommand.execute();
        AddTaskCommand addTaskCommand = new AddTaskCommand(3001, 1003, "water");
        addTaskCommand.execute();
        Employee newEmployee = new Employee("Yogurt");
        newEmployee.addTaskToEmployee(new Task(3001, 1003, "wash"));
        addTaskCommand.execute();
        int numOfTask = TaskList.getTasks().size();
        newEmployee.viewTasks();
        int numOfTaskAfterView = TaskList.getTasks().size();
        assertEquals(numOfTaskAfterView - numOfTask, 0);
    }
     */
}