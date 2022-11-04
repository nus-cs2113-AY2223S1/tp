package employee;

import command.employeecommand.AddEmployeeCommand;
import command.employeecommand.RemoveEmployeeCommand;
import command.employeecommand.ViewEmployeeCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void listEmployeeTest() {
        int numOfEmployee = EmployeeList.employees.size();
        ViewEmployeeCommand viewEmployeeCommand = new ViewEmployeeCommand();
        viewEmployeeCommand.execute();
        int numOfEmployeeAfterView = EmployeeList.employees.size();
        assertEquals(numOfEmployee, numOfEmployeeAfterView);
    }

    @Test
    void removeTheFirstEmployeeTest() {
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Yogurt");
        addEmployeeCommand.execute();
        int numOfEmployee = EmployeeList.employees.size();
        RemoveEmployeeCommand removeEmployeeCommand = new RemoveEmployeeCommand(1001);
        removeEmployeeCommand.execute();
        int numOfEmployeeAfterRemove = EmployeeList.employees.size();
        assertEquals(numOfEmployee - numOfEmployeeAfterRemove, 1);
    }

    @Test
    void viewEmployeeTasks() {

    }
}