package parser;

import appointment.Appointment;
import appointment.AppointmentList;
import command.Command;
import command.EmptyCommand;
import command.appointmentcommand.AddAppointmentCommand;
import command.appointmentcommand.RemoveAppointmentCommand;
import command.appointmentcommand.ViewAppointmentCommand;
import exception.DukeException;
import pet.Pet;

public class AppointmentParser {
    private int lengthOfSignature;
    private Parser parser;

    public AppointmentParser(Parser parser, int lengthOfSignature) {
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }

    public Command parseAppointment(String input) {
        try {
            if (!input.contains(" ")) {
                if (input.equals("view")) {
                    return new ViewAppointmentCommand();
                }
                throw new DukeException();
            }

            String type = input.substring(0,input.indexOf(" "));
            String statement = input.substring(input.indexOf(" "));
            switch (type) {
            case AddAppointmentCommand.COMMAND_WORD:
                return prepareAddAppointment(statement);
            case RemoveAppointmentCommand.COMMAND_WORD:
                return prepareRemoveAppointment(statement);
            default:
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("Sorry, unrecognized appointment operation.");
            return new EmptyCommand();
        }
    }


    public Command prepareRemoveAppointment(String input) {
        try {
            int index = input.indexOf(parser.indexFlag);
            if (index == -1 || !input.substring(0, index).isEmpty()) {
                throw new DukeException();
            }

            String indexStr = input.substring(index + lengthOfSignature);
            if (indexStr.isEmpty()) {
                throw new DukeException();
            }

            int appointmentId = -1;
            try {
                appointmentId = Integer.parseInt(indexStr);
            } catch (NumberFormatException nfe) {
                System.out.println("Sorry, pls enter an integer for removing appointment");
                return new EmptyCommand();
            }

            Appointment appointment = AppointmentList.findAppointment(appointmentId);
            if (appointment == null) {
                System.out.println("Sorry, pls enter a valid index for removing appointment");
                return new EmptyCommand();
            }
            return new RemoveAppointmentCommand(appointmentId);
        } catch (DukeException e) {
            System.out.println("Sorry, input invalid for removing an appointment");
            return new EmptyCommand();
        }
    }

    public Command prepareAddAppointment(String input) {
        try {
            int s = input.indexOf(parser.serviceFlag);
            int p = input.indexOf(parser.petFlag);
            int d = input.indexOf(parser.dateFlag);

            if (s > p || p > d || s == -1 || p == -1 || d == -1) {
                throw new DukeException();
            }

            if (!input.substring(0,s).isEmpty()) {
                throw new DukeException();
            }

            String service = input.substring(s + lengthOfSignature, p);
            String petIdStr = input.substring(p, d);
            int petId = parser.numberInInput(petIdStr, parser.petFlag);
            String appointmentDate = input.substring(d + lengthOfSignature);

            return new AddAppointmentCommand(petId, appointmentDate, service);
        } catch (DukeException e) {
            System.out.println("Sorry, format of parameters entered for adding an appointment is invalid");
            return new EmptyCommand();
        }
    }

}
