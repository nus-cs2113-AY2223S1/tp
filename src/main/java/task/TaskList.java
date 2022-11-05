package task;

import appointment.Appointment;
import appointment.AppointmentList;
import employee.Employee;
import employee.EmployeeList;
import exception.DukeException;
import task.Task;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Objects;

import static appointment.AppointmentList.findAppointment;

public class TaskList {

    static String LINE_DIVIDER = "_______________________________________";
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void viewTasks() {
        System.out.println("Here are all the tasks for the clinic:");
        for (Task task : tasks) {
            System.out.println(LINE_DIVIDER);
            task.printTask();
        }
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Function to add a new Task to the overall task list and the respective associated appointment & employee
     * @param task
     * @throws DukeException
     */
    public static void addTask(Task task) throws DukeException {

        // To find the appointment that the task is allocated to
        Appointment appointment = findAppointment(task.getAppointmentId());
        if (appointment == null) {
            Task.id--;
            throw new DukeException();
        }

        // To find the employee that the task is allocated to
        Employee employee = EmployeeList.findEmployee(task.getEmployeeId());
        if (employee == null) {
            Task.id--;
            throw new DukeException();
        }

        // After both the appointment and employee are found, the task is added to the overall task list
        // and also added under the associated appointment and employee
        // Appointment and Employee thus aggregates Task
        tasks.add(task);
        appointment.addTaskToAppointment(task);
        appointment.updateAppointmentStatus();
        employee.addTaskToEmployee(task);

        System.out.print("Got it. I've added this task: ");
        System.out.println(task.getTaskDescription());
        System.out.println("Performed by: " + Objects.requireNonNull(EmployeeList.findEmployee(task.getEmployeeId())).getEmployeeName());
        System.out.println("Appointment: ID " + task.getAppointmentId() );
        System.out.println("Now you have " + tasks.size() + " task in the list.");
    }

    /**
     * Function to reassign an existing Task to another Employee
     * @param taskId
     * @param employeeId
     * @throws DukeException
     */
    public static void reassignTask(int taskId, int employeeId) throws DukeException {
        if (TaskList.findTask(taskId) == null) {
            throw new DukeException();
        }
        if (EmployeeList.findEmployee(employeeId) == null) {
            throw new DukeException();
        }
        Task taskToReassign = TaskList.findTask(taskId);

        // Remove from original Employee's task list
        if (taskToReassign != null) {
            EmployeeList.findEmployee(taskToReassign.getEmployeeId()).removeTaskFromEmployee(taskId);
            // Add to new Employee's task list
            EmployeeList.findEmployee(employeeId).addTaskToEmployee(taskToReassign);
            System.out.print("Got it. Task " + taskId + " has been reassigned from ");
            System.out.print(EmployeeList.findEmployee(taskToReassign.getEmployeeId()).getEmployeeName());
            System.out.println(" to " + EmployeeList.findEmployee(employeeId).getEmployeeName() + "!");
            // Change employeeId in taskToReassign
            taskToReassign.setEmployeeId(employeeId);
        }
    }

    /**
     * Function to delete a Task from the overall task list and the respective associated appointment and employee
     * @param taskId
     */
    public static void removeTask(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                // remove from overall task list
                tasks.remove(task);
                // remove from appointment
                findAppointment(task.getAppointmentId()).removeTaskFromAppointment(taskId);
                // remove from employee
                EmployeeList.findEmployee(task.getEmployeeId()).removeTaskFromEmployee(taskId);
                System.out.print("Got it. I've removed this task: ");
                task.printTask();
                System.out.println("Now you have " + tasks.size() + " task in the list.");
                break;
            }
        }
        System.out.println("Sorry, no corresponding task found.");
    }

    /**
     * Helper function to find a particular task from the list of tasks by its ID
     * @param taskId
     * @return
     */
    public static Task findTask(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

    /**
     * Function to mark a task as completed
     * @param taskId
     */
    public static void finishTask(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.setDone();
                System.out.print("Got it. I've finished this task: ");
                System.out.println(task.getTaskDescription());
                return;
            }
        }
        System.out.println("Sorry, no corresponding task found.");
    }

}
