package parser;

import command.Command;
import command.EmptyCommand;
import command.appointmentCommand.AddAppointmentCommand;
import command.appointmentCommand.RemoveAppointmentCommand;
import command.appointmentCommand.ViewAppointmentCommand;
import exception.DukeException;

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
            /*
            case AllocateAppointmentCommand.COMMAND_WORD:
                return prepareAllocateAppointment(statement);
            break;
            */
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
            int index = parser.indexOfInput(input);
            return new RemoveAppointmentCommand(index);
        } catch (DukeException e) {
            System.out.println("Sorry, index entered invalid for removing an appointment");
            return new EmptyCommand();
        }
    }

    public Command prepareAddAppointment(String input) {
        try {
            int s = input.indexOf(" s/");
            int p = input.indexOf(" p/");
            int d = input.indexOf(" d/");

            if (s > p || p > d || s == -1 || p == -1 || d == -1) {
                throw new DukeException();
            }

            String service = input.substring(s + lengthOfSignature, p);
            String petName = input.substring(p + lengthOfSignature, d);
            String appointmentDate = input.substring(d + lengthOfSignature);
            return new AddAppointmentCommand(petName, appointmentDate, service);
        } catch (DukeException e) {
            System.out.println("Sorry, format of parameters entered for adding an appointment is invalid");
            return new EmptyCommand();
        }
    }

    /*
    public Command prepareAllocateAppointment(String input){
        int i = input.indexOf(" i/");
        int n = input.indexOf(" n/");

        if(i > n || i == -1 || n == -1){
            System.out.println("invalid input");
            return new EndCommand();
        }

        String index = input.substring(i + lengthOfSignature, n);
        String name = input.substring(n + lengthOfSignature);

        return new AllocateApointmentCommand(index, name);
    }
*/
}
