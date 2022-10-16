package parser;
import command.*;

public class Parser {
    private final int lengthOfSignature = 3;
    public Command parseCommand(String input){
        input = input.trim();

        if(!input.contains(" ")){
            if(input.equals("bye")){
                return new EndCommand();
            }
            
            System.out.println("Error: only one parameter received and it is not bye");
            return new EndCommand();
        }

        int indexOfSpace = input.indexOf(" ");
        String type = input.substring(0,indexOfSpace);
        String statement = input.substring(indexOfSpace).trim();

        switch(type) {
        case "appointment":
            return parseAppointment(statement);
        case "pet":
            return parsePet(statement);
        case "employee":
            return parseEmployee(statement);
        case "service":
            return parseService(statement);
        default:
            System.out.println("Error: unrecognized operation");
            return new EndCommand();
        }
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


    public Command parseService(String input){
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewServiceCommand();
            }
            System.out.println("Error: too little parameters entered for service operation");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddServiceCommand.COMMAND_WORD:
            return prepareAddService(statement);
        case RemoveServiceCommand.COMMAND_WORD:
            return prepareRemoveService(statement);
        default:
            System.out.println("Error: unrecognized service operation");
            return new EndCommand();
        }
    }

    public Command prepareAddService(String input){
        int d = input.indexOf("d/");

        if(d == -1){
            System.out.println("Error: no description entered");
            return new EndCommand();
        }

        String description = input.substring(d + lengthOfSignature);

        return new AddServiceCommand(description);

    }


    public Command prepareRemoveService(String input){
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("Error: index entered invalid for removing a service");
            return new EndCommand();
        }

        return new RemoveServiceCommand(index);
    }

    /*
    public Command prepareSetAppointmentStatusCommand(String input){
        int i = input.indexOf(" i/");
        int s = input.indexOf(" s/");

        if(i > s || i == -1 || s == -1){
            System.out.println("invalid input");
            return new EndCommand();
        }

        String index = input.substring(i + lengthOfSignature, s);
        String status = input.substring(s + lengthOfSignature);

        return new AllocateApointmentCommand(index, status);
    }

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

    private int numOfSpace(String input){
        int num = 0;
        boolean contain = input.contains(" ");
        while(contain){
            num++;
            input = input.substring(input.indexOf(" ")).trim();
            contain = input.contains(" ");
        }
        return num;
    }


    public Command prepareRemoveAppointment(String input){
        input = " " + input;
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("Error: index entered invalid for removing an appointment");
            return new EndCommand();
        }

        return new RemoveAppointmentCommand(index);
    }

    /*
    public Command prepareRemovePet(String input){
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        return new RemovePetCommand(index);
    }
     */

    public int indexOfRemove(String input){
        if(!input.contains("i/")){
            return -1;
        }

        String id = input.substring(input.indexOf(" i/")+lengthOfSignature);
        if(!isInt(id)){
            return -1;
        }

        return Integer.parseInt(id);
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

    public boolean isInt(String val){
        Boolean strResult = val.matches("\\d?");
        if(strResult) {
            return true;
        }
        return false;
    }



    public Command prepareAddPet(String input){
        int startOfN = input.indexOf("n/");
        int startOfS = input.indexOf(" s/");

        if(startOfN > startOfS || startOfN == -1|| startOfS == -1){
            System.out.println("Error: format of parameters entered for adding a pet is invalid");
            return new EndCommand();
        }

        String name = input.substring(startOfN -1 + lengthOfSignature, startOfS);
        String species = input.substring(startOfS + lengthOfSignature);
        return new AddPetCommand(name, species, true);
    }


    public Command parsePet(String input){
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewPetCommand();
            }
            System.out.println("Error: too little parameters entered for pet operation");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddPetCommand.COMMAND_WORD:
            return prepareAddPet(statement);
        default:
            System.out.println("Error: unrecognized pet operation");
            return new EndCommand();
        }
    }

    public Command parseEmployee(String input){
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewEmployeeCommand();
            }
            System.out.println("Error: too little parameters entered for employee operation");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddEmployeeCommand.COMMAND_WORD:
            return prepareAddEmployee(statement);
        case RemoveEmployeeCommand.COMMAND_WORD:
            return prepareRemoveEmployee(statement);
        default:
            System.out.println("Error: unrecognized employee operation");
            return new EndCommand();
        }
    }

    public Command prepareAddEmployee(String input){
        int startOfN = input.indexOf("n/");

        if(startOfN == -1){
            System.out.println("Error: format of parameters entered for adding an employee is invalid");
            return new EndCommand();
        }

        String name = input.substring(startOfN + lengthOfSignature - 1);
        return new AddEmployeeCommand(name);
    }


    public Command prepareRemoveEmployee(String input){
        int index = indexOfRemove(input);

        if(index == -1){
            System.out.println("Error: index entered invalid for removing an employee ");
            return new EndCommand();
        }

        return new RemoveEmployeeCommand(index);
    }



}