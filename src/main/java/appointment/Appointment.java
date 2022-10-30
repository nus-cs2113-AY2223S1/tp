package appointment;

import task.Task;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Appointment {
    private static int id = 3000;
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public final int appointmentId;
    public int petId;
    public String service;

    private Date appointmentDate;
    private AppointmentStatus appointmentStatus;

    private ArrayList<Task> tasks = new ArrayList<>();

    public Appointment(int petId, Date appointmentDate, String service) {
        this.appointmentId = ++id;
        this.petId = petId;
        this.appointmentStatus = AppointmentStatus.PENDING;
        this.appointmentDate = appointmentDate;
        this.service = service;
    }

    // check appointment date format
    public static Date checkFormattedDate(String appointmentDateStr) {
        Date formattedDate = null;
        try {
            formattedDate = formatter.parse(appointmentDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid appointment date format! Pls follow yyyy-MM-dd!");
            return null;
        }
        return formattedDate;
    }

    // view tasks for a find appointment
    public void viewTasks() {
        System.out.println("Appointment " + appointmentId + " Task List:");
        for (Task task: tasks) {
            System.out.println("________________________");
            task.printTask();
        }
    }

    public void addTaskToAppointment(Task task) {
        this.tasks.add(task);
    }

    // update appointment status after changing task
    public void updateAppointmentStatus() {
        appointmentStatus = AppointmentStatus.PROCESSING;
        boolean isDone = true;
        for (Task task: tasks) {
            if (task.isDone == false) {
                isDone = false;
                break;
            }
        }
        if (isDone) {
            appointmentStatus = AppointmentStatus.PROCESSED;
        }
    }

    public String getAppointmentStatus() {
        if (appointmentStatus == AppointmentStatus.PENDING) {
            return "PENDING";
        }
        if (appointmentStatus == AppointmentStatus.PROCESSING) {
            return "PROCESSING";
        }
        if (appointmentStatus == AppointmentStatus.PROCESSED) {
            return "PROCESSED";
        }
        // control should never reach here
        return "";
    }

    public String getAppointmentDateStr() {
        String dateStr = formatter.format(appointmentDate);
        return dateStr;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }
}