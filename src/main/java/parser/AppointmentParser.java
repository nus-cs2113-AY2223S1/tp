package parser;

import command.*;
import command.appointmentCommand.AddAppointmentCommand;
import command.appointmentCommand.RemoveAppointmentCommand;
import command.appointmentCommand.SetAppointmentStatusCommand;
import command.appointmentCommand.ViewAppointmentCommand;

public class AppointmentParser {
    private int lengthOfSignature;
    private Parser parser;

    public AppointmentParser(Parser parser, int lengthOfSignature){
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }

    public Command parseAppointment(String input){
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewAppointmentCommand();
            }
            System.out.println("Error: too little parameters entered for appointment operation");
            return new EndCommand();
        }
        if(input.equals("view")){
            return new ViewAppointmentCommand();
        }


        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" "));
        switch(type) {
        case AddAppointmentCommand.COMMAND_WORD:
            return prepareAddAppointment(statement);
        case RemoveAppointmentCommand.COMMAND_WORD:
            return prepareRemoveAppointment(statement);
        /*
        case AllocateAppointmentCommand.COMMAND_WORD:
            return prepareAllocateAppointment(statement);
        break;
        */
        case SetAppointmentStatusCommand.COMMAND_WORD:
            return prepareSetAppointmentStatusCommand(statement);
        default:
            System.out.println("Error: unrecognized appointment operation");
            return new EndCommand();
        }
    }


    public Command prepareRemoveAppointment(String input){
        int index = parser.indexOfRemove(input);
        if(index == -1){
            System.out.println("Error: index entered invalid for removing an appointment");
            return new EndCommand();
        }

        return new RemoveAppointmentCommand(index);
    }

    public Command prepareAddAppointment(String input){
        int s = input.indexOf(" s/");
        int p = input.indexOf(" p/");
        int d = input.indexOf(" d/");

        if(s > p || p > d || s == -1 || p == -1 || d == -1){
            System.out.println("Error: format of parameters entered for adding an appointment is invalid");
            return new EndCommand();
        }

        String service = input.substring(s + lengthOfSignature, p);
        String petName = input.substring(p + lengthOfSignature, d);
        String appointmentDate = input.substring(d + lengthOfSignature);

        return new AddAppointmentCommand(petName, appointmentDate, service);
    }

    public Command prepareSetAppointmentStatusCommand(String input) {
        int i = input.indexOf(" i/");
        int s = input.indexOf(" s/");

        if (i > s || i == -1 || s == -1) {
            System.out.println("invalid input");
            return new EndCommand();
        }

        String index = input.substring(i + lengthOfSignature, s);
        int indexInt = parser.isStatus(index);

        if (indexInt == -1) {
            System.out.println("Error: index entered invalid for setting appointment status");
            return new EndCommand();
        }

        String status = input.substring(s + lengthOfSignature);
        int statusInt = parser.isStatus(status);

        if (statusInt == -1 || (statusInt != 1 && statusInt != 0)) {
            System.out.println("Error: status entered invalid for setting appointment status");
            return new EndCommand();
        }

        return new SetAppointmentStatusCommand(Integer.parseInt(index),
                Integer.parseInt(status));
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
