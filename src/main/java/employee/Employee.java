package employee;

import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class Employee {
    public static int idCounter = 1000;
    private final int employeeId;
    private String employeeName;

    private ArrayList<Task> tasks = new ArrayList<>();

    public Employee(String employeeName) {
        this.employeeId = ++idCounter;
        this.employeeName = employeeName;
    }

    public void addTaskToEmployee(Task task) {
        tasks.add(task);
    }

    public void removeTaskFromEmployee(int taskId) {
        tasks.removeIf(task -> task.getTaskId() == taskId);
    }

    public void viewTasks() {
        System.out.println("Employee " + employeeName + "'s Task List:");
        for (Task task: tasks) {
            Ui.showLine();
            task.printTask();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }


}
