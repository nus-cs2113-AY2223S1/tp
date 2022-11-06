package employee;

import command.appointmentcommand.AddAppointmentCommand;
import command.employeecommand.AddEmployeeCommand;
import command.employeecommand.RemoveEmployeeCommand;
import command.employeecommand.ViewEmployeeCommand;
import command.petcommand.AddPetCommand;
import command.servicecommand.AddServiceCommand;
import command.taskcommand.AddTaskCommand;
import org.junit.jupiter.api.Test;
import pet.Pet;
import task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeListTest {



    @Test
    void addTheFirstEmployeeTest() {
        int numOfEmployee = EmployeeList.employees.size();
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Yogurt");
        addEmployeeCommand.execute();
        int numOfEmployeeAfterAdd = EmployeeList.employees.size();
        assertEquals(numOfEmployeeAfterAdd - numOfEmployee, 1);
    }

    @Test
    void findEmployeeTest() {
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Jinwen");
        addEmployeeCommand.execute();
        Employee employeeFound = EmployeeList.findEmployee(Employee.idCounter);
        assertEquals(employeeFound.getEmployeeId(), Employee.idCounter);
    }

    @Test
    void findInvalidEmployeeTest() {
        Employee employeeFound = EmployeeList.findEmployee(1099);
        assertNull(employeeFound);
    }


    @Test
    void listEmployeeTest() {
        int numOfEmployee = EmployeeList.employees.size();
        ViewEmployeeCommand viewEmployeeCommand = new ViewEmployeeCommand();
        viewEmployeeCommand.execute();
        int numOfEmployeeAfterView = EmployeeList.employees.size();
        assertEquals(numOfEmployee, numOfEmployeeAfterView);
    }

    @Test
    void removeInvalidEmployeeTest() {
        int numOfEmployee = EmployeeList.employees.size();
        RemoveEmployeeCommand removeEmployeeCommand = new RemoveEmployeeCommand(1099);
        removeEmployeeCommand.execute();
        int numOfEmployeeAfterRemove = EmployeeList.employees.size();
        assertEquals(numOfEmployee - numOfEmployeeAfterRemove, 0);
    }

    @Test
    void removeTheFirstEmployeeTest() {
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Yogurt");
        addEmployeeCommand.execute();
        int numOfEmployee = EmployeeList.employees.size();
        RemoveEmployeeCommand removeEmployeeCommand = new RemoveEmployeeCommand(1002);
        removeEmployeeCommand.execute();
        int numOfEmployeeAfterRemove = EmployeeList.employees.size();
        // different os may operate the tests in different order
        if (numOfEmployee == numOfEmployeeAfterRemove) {
            assertEquals(numOfEmployee - numOfEmployeeAfterRemove, 0);
        } else {
            assertEquals(numOfEmployee - numOfEmployeeAfterRemove, 1);
        }
    }

    @Test
    void removeTwoEmployeeTest() {
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Yogurt");
        addEmployeeCommand.execute();
        addEmployeeCommand.execute();
        int numOfEmployee = EmployeeList.employees.size();
        RemoveEmployeeCommand removeEmployeeCommand = new RemoveEmployeeCommand(1001);
        removeEmployeeCommand.execute();
        removeEmployeeCommand.execute();
        int numOfEmployeeAfterRemove = EmployeeList.employees.size();
        // different os may operate the tests in different order
        if (numOfEmployee == numOfEmployeeAfterRemove) {
            assertEquals(numOfEmployee - numOfEmployeeAfterRemove, 0);
        } else if (numOfEmployee == numOfEmployeeAfterRemove + 1) {
            assertEquals(numOfEmployee - numOfEmployeeAfterRemove, 1);
        } else {
            assertEquals(numOfEmployee - numOfEmployeeAfterRemove, 2);
        }
    }

    /*
    @Test
    void viewEmployeeTasks() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Feed");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Meow", "cat", true);
        addPetCommand.execute();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.idCounter, "2022-11-28", "Feed");
        addAppointmentCommand.execute();
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Yogurt");
        addEmployeeCommand.execute();
        AddTaskCommand addTaskCommand = new AddTaskCommand(3002, 1002, "water");
        addTaskCommand.execute();
        int numOfTask = TaskList.getTasks().size();
        EmployeeList.viewEmployeeTasks(1003);
        int numOfTaskAfterView = TaskList.getTasks().size();
        assertEquals(numOfTaskAfterView - numOfTask, 0);
    }
     */
}