package employee;

import task.Task;

import java.util.ArrayList;

public class Employee {
    private static int id = 0;
    private final int employeeId;
    private String employeeName;

    private ArrayList<Task> tasks = new ArrayList<>();

    public Employee(String employeeName) {
        this.employeeId = ++id;
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
            System.out.println("________________________");
            task.printTask();
        }
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }


}
