package task;

import appointment.Appointment;
import employee.Employee;
import employee.EmployeeList;
import exception.DukeException;
import ui.Ui;
import java.util.ArrayList;
import java.util.Objects;

import static appointment.AppointmentList.findAppointment;

public class TaskList {

    static ArrayList<Task> tasks = new ArrayList<>();

    //view every single task in the clinic
    public static void viewTasks() {
        System.out.println("Here are all the tasks for the clinic:");
        for (Task task : tasks) {
            Ui.showLine();
            task.printTask();
        }
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Function adds a new Task to the overall TaskList and to the associated Appointment and Employee's Task list.
     * @param task Constructed by AddTaskCommand and passed in to be added to the overall list.
     * @throws DukeException
     */
    public static void addTask(Task task) throws DukeException {

        // Find the appointment that the Task is associated with
        Appointment appointment = findAppointment(task.getAppointmentId());
        if (appointment == null) {
            Task.idCounter--;
            throw new DukeException();
        }

        // Find the employee that the Task is associated with
        Employee employee = EmployeeList.findEmployee(task.getEmployeeId());
        if (employee == null) {
            Task.idCounter--;
            throw new DukeException();
        }

        // After successfully finding the associated Appointment and Employee
        // Then, add Task to overall TaskList and to the respective Appointment and Employee
        tasks.add(task);
        appointment.addTaskToAppointment(task);
        appointment.updateAppointmentStatus();
        employee.addTaskToEmployee(task);

        System.out.print("Got it. I've added this task: ");
        System.out.println(task.getTaskDescription());
        System.out.print("Performed by: ");
        System.out.println(Objects.requireNonNull(EmployeeList.findEmployee(task.getEmployeeId())).getEmployeeName());
        System.out.println("Appointment: ID " + task.getAppointmentId());
        System.out.println("Now you have " + tasks.size() + " task in the list.");
    }

    /**
     * Function reassigns a task from one employee to another.
     * @param taskId Id of the task to be reassigned.
     * @param employeeId Id of the employee the task is to be reassigned to.
     * @throws DukeException
     */
    // assign task to be done by another person
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
            // Find and remove task from original employee
            Employee originalEmployee = EmployeeList.findEmployee(taskToReassign.getEmployeeId());
            assert originalEmployee != null;
            originalEmployee.removeTaskFromEmployee(taskId);
            // Find and add task to the new employee's task list
            Employee newEmployee = EmployeeList.findEmployee(employeeId);
            assert newEmployee != null;
            newEmployee.addTaskToEmployee(taskToReassign);
            System.out.print("Got it. Task " + taskId + " has been reassigned from ");
            System.out.print(originalEmployee.getEmployeeName());
            System.out.println(" to " + newEmployee.getEmployeeName() + "!");
            // Change employeeId in the reassigned task
            taskToReassign.setEmployeeId(employeeId);
        }
    }

    /**
     * Function finds and removes a Task from the overall TaskList and from the associated appointment and employee.
     * @param taskId Id of the task to be removed.
     */
    public static void removeTask(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                // remove from overall task list
                tasks.remove(task);
                // remove from appointment
                Appointment associatedAppointment = findAppointment(task.getAppointmentId());
                assert associatedAppointment != null;
                associatedAppointment.removeTaskFromAppointment(taskId);
                // remove from employee
                Employee associatedEmployee = EmployeeList.findEmployee(task.getEmployeeId());
                assert associatedEmployee != null;
                associatedEmployee.removeTaskFromEmployee(taskId);
                System.out.print("Got it. I've removed this task: ");
                task.printTask();
                System.out.println("Now you have " + tasks.size() + " task in the list.");
                break;
            }
        }
        System.out.println("Sorry, no corresponding task found.");
    }

    /**
     * Helper function to find a task from the overall TaskList by task ID.
     * @param taskId Id of the task to be found.
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
     * Finds and marks a task as completed.
     * @param taskId Id of the task to be marked as completed.
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
