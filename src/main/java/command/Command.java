package command;

import appointment.AppointmentList;

public abstract class Command {

    Command() {
    }
    public abstract void execute(AppointmentList appointmentList);
    public abstract boolean isExit();
}
