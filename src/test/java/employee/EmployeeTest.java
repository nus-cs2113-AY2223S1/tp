package employee;


import appointment.Appointment;
import command.taskcommand.AddTaskCommand;
import org.junit.jupiter.api.Test;
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

}