package employee;


import appointment.Appointment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {
    @Test
    public void initEmployee() {
        Employee newEmployee = new Employee("Yogurt");
        assertEquals(newEmployee.getEmployeeName(), "Yogurt");
    }
}