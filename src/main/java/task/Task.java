package task;

public class Task {

    private static int id = 0;

    public final int taskId;

    public int appointmentId;

    public int employeeId;
    public String taskDescription;
    public boolean isDone;

    public Task(int appointmentId, int employeeId, String taskDescription) {
        this.taskId = ++id;
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
        System.out.println("Task" + this.taskId + ":");
        System.out.println(this.taskDescription);
        System.out.println("Performed by: " + this.employeeId);
        System.out.println("Appointment: " + this.appointmentId);
        System.out.println("Status: " + this.getStatus());
    }

}
