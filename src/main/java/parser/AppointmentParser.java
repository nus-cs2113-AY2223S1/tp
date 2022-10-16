package parser;

import command.*;

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
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddAppointmentCommand.COMMAND_WORD:
            return prepareAddAppointment(statement);
        case RemoveAppointmentCommand.COMMAND_WORD:
            return prepareRemoveAppointment(statement);
        /*
        case AllocateAppointmentCommand.COMMAND_WORD:
            return prepareAllocateAppointment(statement);
        break;
        case SetAppointmentStatusCommand.COMMAND_WORD:
            return prepareSetAppointmentStatusCommand(statement);
            break;
         */
        default:
            System.out.println("Error: unrecognized appointment operation");
            return new EndCommand();
        }
    }


    public Command prepareRemoveAppointment(String input){
        input = " " + input;
        int index = parser.indexOfRemove(input);
        if(index == -1){
            System.out.println("Error: index entered invalid for removing an appointment");
            return new EndCommand();
        }

        return new RemoveAppointmentCommand(index);
    }

    public Command prepareAddAppointment(String input){
        int s = input.indexOf("s/");
        int p = input.indexOf(" p/");
        int d = input.indexOf(" d/");

        if(s > p || p > d || s == -1 || p == -1 || d == -1){
            System.out.println("Error: format of parameters entered for adding an appointment is invalid");
            return new EndCommand();
        }

        // String service = input.substring(s + lengthOfSignature, p);
        String petName = input.substring(p + lengthOfSignature, d);
        String appointmentDate = input.substring(d + lengthOfSignature);

        return new AddAppointmentCommand(petName, appointmentDate);
    }

}
