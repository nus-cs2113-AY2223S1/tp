package task;

import appointment.Appointment;
import appointment.AppointmentList;
import employee.Employee;
import employee.EmployeeList;

public class Task {

    static int idCounter = 4000;

    public final int taskId;

    public int appointmentId;

    public int employeeId;

    public int petId;
    public String taskDescription;
    public boolean isDone;

    public Task(int appointmentId, int employeeId, String taskDescription) {
        this.taskId = ++idCounter;
        this.appointmentId = appointmentId;
        this.employeeId = employeeId;
        this.taskDescription = taskDescription;

        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        if (isDone) {
            return "Done";
        } else {
            return "Not Done";
        }
    }

    public void printTask() {
        System.out.println("Task ID:" + this.taskId);
        System.out.println("Description: " + this.taskDescription);
        Employee employee = EmployeeList.findEmployee(this.employeeId);
        if (employee != null) {
            System.out.println("Employee: " + employee.getEmployeeName());
        }

        Appointment appointment = AppointmentList.findAppointment(this.appointmentId);
        if (appointment != null) {
            appointment.printAppointmentDetails();
        }
        System.out.println("Status: " + this.getStatus());
    }

}
