package task;

import appointment.AppointmentList;
import command.appointmentcommand.AddAppointmentCommand;
import command.employeecommand.AddEmployeeCommand;
import command.petcommand.AddPetCommand;
import command.servicecommand.AddServiceCommand;
import command.taskcommand.AddTaskCommand;
import command.taskcommand.ReassignTaskCommand;
import command.taskcommand.RemoveTaskCommand;
import employee.EmployeeList;
import org.junit.jupiter.api.Test;
import pet.PetList;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void viewTasks() {
    }

    @Test
    void addTaskSuccess() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 service
        AddServiceCommand addServiceCommand = new AddServiceCommand("Tooth Surgery");
        addServiceCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(PetList.pets.get(PetList.pets.size()-1).petId, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 1 employees
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();

        int TaskListSize = TaskList.getTasks().size();
        int EmployeeTaskListSize = EmployeeList.findEmployee(EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-1).getEmployeeId()).getTasks().size();
        int AppointmentTaskListSize = AppointmentList.findAppointment(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId).getTasks().size();

        // Add a task associated to the above appointment and employee
        AddTaskCommand addTaskCommand = new AddTaskCommand(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId, EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-1).getEmployeeId(), "Set up equipment");
        addTaskCommand.execute();

        int TaskListSizeAfterAdd = TaskList.getTasks().size();
        int EmployeeTaskListSizeAfterAdd = EmployeeList.findEmployee(EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-1).getEmployeeId()).getTasks().size();
        int AppointmentTaskListSizeAfterAdd = AppointmentList.findAppointment(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId).getTasks().size();

        // To check if the Task is successfully added to three places, TaskList, Employee's TaskList and Appointment's TaskList
        assertEquals(1, TaskListSizeAfterAdd - TaskListSize);
        assertEquals(1, EmployeeTaskListSizeAfterAdd - EmployeeTaskListSize);
        assertEquals(1, AppointmentTaskListSizeAfterAdd - AppointmentTaskListSize);
    }

    @Test
    void reassignTaskSuccess() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 service
        AddServiceCommand addServiceCommand = new AddServiceCommand("Tooth Surgery");
        addServiceCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(PetList.pets.get(PetList.pets.size()-1).petId, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 2 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();
        AddEmployeeCommand addEmployeeCommand2 = new AddEmployeeCommand("Sally");
        addEmployeeCommand2.execute();
        // Add Task, to be done by Tom
        AddTaskCommand addTaskCommand = new AddTaskCommand(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId, EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-2).getEmployeeId(), "Set up equipment");
        addTaskCommand.execute();

        int TomTasklistSize = Objects.requireNonNull(EmployeeList.findEmployee(EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size() - 2).getEmployeeId())).getTasks().size();
        int SallyTasklistSize = Objects.requireNonNull(EmployeeList.findEmployee(EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size() - 1).getEmployeeId())).getTasks().size();

        // Reassign Task from Tom to Sally
        ReassignTaskCommand reassignTaskCommand = new ReassignTaskCommand(TaskList.getTasks().get(TaskList.getTasks().size()-1).getTaskId(), EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size() - 1).getEmployeeId());
        reassignTaskCommand.execute();

        int TomTasklistSizeAfterReassign = Objects.requireNonNull(EmployeeList.findEmployee(EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size() - 2).getEmployeeId())).getTasks().size();
        int SallyTasklistSizeAfterReassign = Objects.requireNonNull(EmployeeList.findEmployee(EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size() - 1).getEmployeeId())).getTasks().size();

        assertEquals(1, SallyTasklistSizeAfterReassign - SallyTasklistSize);
        assertEquals(-1, TomTasklistSizeAfterReassign - TomTasklistSize);
    }

    @Test
    void removeTaskSuccess() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 service
        AddServiceCommand addServiceCommand = new AddServiceCommand("Tooth Surgery");
        addServiceCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(PetList.pets.get(PetList.pets.size()-1).petId, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 1 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();

        // Add a task associated to the above appointment and employee
        AddTaskCommand addTaskCommand = new AddTaskCommand(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId, EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-1).getEmployeeId(), "Set up equipment");
        addTaskCommand.execute();

        int TaskListSizeAfterAdd = TaskList.getTasks().size();
        int EmployeeTaskListSizeAfterAdd = EmployeeList.findEmployee(EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-1).getEmployeeId()).getTasks().size();
        int AppointmentTaskListSizeAfterAdd = AppointmentList.findAppointment(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId).getTasks().size();

        // Remove the above added task
        RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(TaskList.getTasks().get(TaskList.getTasks().size()-1).getTaskId());
        removeTaskCommand.execute();

        int TaskListSizeAfterRemove = TaskList.getTasks().size();
        int EmployeeTaskListSizeAfterRemove = EmployeeList.findEmployee(EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-1).getEmployeeId()).getTasks().size();
        int AppointmentTaskListSizeAfterRemove = AppointmentList.findAppointment(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId).getTasks().size();

        assertEquals(-1, TaskListSizeAfterRemove - TaskListSizeAfterAdd);
        assertEquals(-1, EmployeeTaskListSizeAfterRemove - EmployeeTaskListSizeAfterAdd);
        assertEquals(-1, AppointmentTaskListSizeAfterRemove - AppointmentTaskListSizeAfterAdd);

    }

    @Test
    void findTaskSuccessAndFail() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 service
        AddServiceCommand addServiceCommand = new AddServiceCommand("Tooth Surgery");
        addServiceCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(PetList.pets.get(PetList.pets.size()-1).petId, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 1 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();

        // Add a task associated to the above appointment and employee
        AddTaskCommand addTaskCommand = new AddTaskCommand(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId, EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-1).getEmployeeId(), "Set up equipment");
        addTaskCommand.execute();

        assertEquals("Set up equipment", TaskList.findTask(TaskList.getTasks().get(TaskList.getTasks().size()-1).getTaskId()).getTaskDescription());
        assertEquals(null, TaskList.findTask(987654321)
        );
    }

    @Test
    void finishTask() {
        // Add 1 pet
        AddPetCommand addPetCommand = new AddPetCommand("koko", "cat", Boolean.FALSE);
        addPetCommand.execute();
        // Add 1 service
        AddServiceCommand addServiceCommand = new AddServiceCommand("Tooth Surgery");
        addServiceCommand.execute();
        // Add 1 appointment
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(PetList.pets.get(PetList.pets.size()-1).petId, "2022-12-01", "Tooth Surgery");
        addAppointmentCommand.execute();
        // Add 1 employee
        AddEmployeeCommand addEmployeeCommand = new AddEmployeeCommand("Tom");
        addEmployeeCommand.execute();

        // Add a task associated to the above appointment and employee
        AddTaskCommand addTaskCommand = new AddTaskCommand(AppointmentList.appointments.get(AppointmentList.appointments.size()-1).appointmentId, EmployeeList.employeeArrayList().get(EmployeeList.employeeArrayList().size()-1).getEmployeeId(), "Set up equipment");
        addTaskCommand.execute();
        assertEquals("Not Done", Objects.requireNonNull(TaskList.findTask(TaskList.getTasks().get(TaskList.getTasks().size()-1).getTaskId())).getStatus());
        TaskList.finishTask(TaskList.getTasks().get(TaskList.getTasks().size()-1).getTaskId());
        assertEquals("Done", Objects.requireNonNull(TaskList.findTask(TaskList.getTasks().get(TaskList.getTasks().size()-1).getTaskId())).getStatus());
    }

}