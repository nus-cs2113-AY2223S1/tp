package command.petcommand;

import appointment.Appointment;
import appointment.AppointmentList;
import command.Command;
import command.appointmentcommand.RemoveAppointmentCommand;
import pet.Pet;
import pet.PetList;
import service.Service;
import task.Task;
import task.TaskList;

import java.util.LinkedList;
import java.util.Queue;

public class RemovePetCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    private int index;

    public RemovePetCommand(int index) {
        this.index = index;
    }

    public void execute() {
        Pet pet = PetList.findPetById(index);

        Queue<Integer> appointmentIdList = new LinkedList<>();
        if (pet != null) {
            System.out.println("Noted. I've removed this pet:");
            System.out.println(pet.name);
            System.out.println("Now you have " + (PetList.pets.size() - 1) + " pets in the pet list.");

            for (Appointment appointment : AppointmentList.appointments) {
                if (appointment.petId == index) {
                    appointmentIdList.offer(appointment.appointmentId);
                }
            }

            int appointmentId;
            while (appointmentIdList.size() > 0){
                appointmentId = appointmentIdList.poll();
                new RemoveAppointmentCommand(appointmentId).execute();
            }

            PetList.pets.remove(pet);
        } else if (pet == null) {
            System.out.println("Pet not found! Please enter a valid index");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
