package employee;

import command.employeecommand.AddEmployeeCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeListTest {



    @Test
    void addTheFirstEmployee() {
        int numOfEmployee = EmployeeList.employees.size();
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Yogurt");
        addEmployeeCommand.execute();
        int numOfEmployeeAfterAdd = EmployeeList.employees.size();
        assertEquals(numOfEmployeeAfterAdd - numOfEmployee, 1);
    }

    @Test
    void findEmployee() {
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Jinwen");
        addEmployeeCommand.execute();
        Employee employeeFound = EmployeeList.findEmployee(Employee.id);
        assertEquals(employeeFound.getEmployeeId(), Employee.id);
    }




    @Test
    void listEmployee() {
    }

    @Test
    void viewEmployeeTasks() {
    }
}