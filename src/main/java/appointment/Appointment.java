package appointment;

import pet.PetList;
import task.Task;
import ui.Ui;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Appointment {

    public static int idCounter = 3000;
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public final int appointmentId;
    public int petId;
    public String service;

    private Date appointmentDate;
    private AppointmentStatus appointmentStatus;

    public ArrayList<Task> tasks = new ArrayList<>();

    public Appointment(int petId, Date appointmentDate, String service) {
        this.appointmentId = ++idCounter;
        this.petId = petId;
        this.appointmentStatus = AppointmentStatus.PENDING;
        this.appointmentDate = appointmentDate;
        this.service = service;
    }

    public void printAppointmentDetails() {
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Service: " + service);
        System.out.println("Date: " + appointmentDate);
    }

    // check appointment date format
    public static Date checkFormattedDate(String appointmentDateStr) {
        Date formattedDate;
        try {
            formatter.setLenient(false);
            formattedDate = formatter.parse(appointmentDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid appointment date format! Pls follow yyyy-M(M)-d(d)!");
            return null;
        }
        String appointmentYearStr = appointmentDateStr.split("-")[0];
        int appointmentYear = Integer.parseInt(appointmentYearStr);
        Date currentDate = new Date(System.currentTimeMillis());
        if (formattedDate.compareTo(currentDate) > 0 && appointmentYear < 10000) {
            return formattedDate;
        }
        System.out.println("Pls enter valid appointment date!");
        return null;
    }

    // view tasks for a find appointment
    public void viewTasks() {
        System.out.println("Appointment " + appointmentId + " Task List:");
        for (Task task: tasks) {
            Ui.showLine();
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
            PetList.findPetById(petId).changePetStatus();
        }
    }

    public String getAppointmentStatus() {
        if (appointmentStatus == AppointmentStatus.PENDING) {
            return "PENDING";
        } else if (appointmentStatus == AppointmentStatus.PROCESSING) {
            return "PROCESSING";
        } else if (appointmentStatus == AppointmentStatus.PROCESSED) {
            return "PROCESSED";
        } else {
            return "";
        }
    }

    public String getAppointmentDateStr() {
        String dateStr = formatter.format(appointmentDate);
        return dateStr;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void removeTaskFromAppointment(int taskId) {
        for (Task task: tasks) {
            if (task.getTaskId() == taskId) {
                tasks.remove(task);
                break;
            }
        }
    }
}