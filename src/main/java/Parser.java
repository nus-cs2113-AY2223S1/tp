import command.*;

public class Parser {
    private final int lengthOfSignature = 3;
    public Command parseCommand(String input){
        input = input.trim();

        if(!input.contains(" ")){
            if(input == "bye"){
                return new EndCommand();
            }
            System.out.println("input invalid");
            return new EndCommand();
        }

        int indexOfSpace = input.indexOf(" ");
        String type = input.substring(0,indexOfSpace);
        String statement = input.substring(indexOfSpace).trim();

        switch(type) {
        case "appointment":
            return parseAppointment(statement);
            break;
        case "task":
            return parseTask(statement);
            break;
        case "pet":
            return parsePet(statement);
            break;
        case "employee":
            return parseEmployee(statement);
            break;
        case "service":
            return parseService(statement);
            break;
        default:
            System.out.println("input invalid");
            return new EndCommand();
        }
    }

    public Command parseAppointment(String input){
        if(!input.contains(" ")){
            if(input == "view"){
                return new ViewAppointmentCommand();
            }
            System.out.println("input invalid");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddAppointmentCommand.COMMAND_WORD:
            return prepareAddAppointment(statement);
        break;
        case RemoveAppointmentCommand.COMMAND_WORD:
            return prepareRemoveAppointment(statement);
        break;
        case AllocateAppointmentCommand.COMMAND_WORD:
            return prepareAllocateAppointment(statement);
        break;
        case SetAppointmentStatusCommand.COMMAND_WORD:
            return prepareSetAppointmentStatusCommand(statement);
            break;
        default:
            return new EndCommand();
        }
    }


    public Command parseService(String input){
        if(!input.contains(" ")){
            if(input == "view"){
                return new ViewServiceCommand();
            }
            System.out.println("input invalid");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddServiceCommand.COMMAND_WORD:
            return prepareAddService(statement);
        break;
        case RemoveServiceCommand.COMMAND_WORD:
            return prepareRemoveService(statement);
        break;
        default:
            return new EndCommand();
        }
    }

    public Command prepareAddService(String input){
        int d = input.indexOf(" d/");

        if(d == -1){
            System.out.println("invalid input");
            return new EndCommand();
        }

        String description = input.substring(d + lengthOfSignature);

        return new AddServiceCommand(description);

    }


    public Command prepareRemoveService(String input){
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        return new RemoveServiceCommand(index);
    }

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
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        return new RemoveAppointmentCommand(index);
    }

    public Command prepareRemovePet(String input){
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        return new RemovePetCommand(index);
    }

    public int indexOfRemove(String input){
        if(!input.contains(" i/")){
            return -1;
        }

        String id = input.substring(input.indexOf(" i/")+lengthOfSignature);
        if(!isInt(id)){
            return -1;
        }

        int appointmentId = Integer.parseInt(id);
        return appointmentId;
    }


    public Command prepareAddAppointment(String input){
        int s = input.indexOf(" s/");
        int p = input.indexOf(" p/");
        int d = input.indexOf(" d/");

        if(s > p || p > d || s == -1 || p == -1 || d == -1){
            System.out.println("invalid input");
            return new EndCommand();
        }

        String service = input.substring(s + lengthOfSignature, p);
        String petName = input.substring(p + lengthOfSignature, d);
        String appointmentDate = input.substring(d + lengthOfSignature);

        return new AddApointmentCommand(petName, appointmentDate, service);
    }

    public boolean isInt(String val){
        Boolean strResult = val.matches("\\d?");
        if(strResult == true) {
            return true;
        }
        return false;
    }


    public Command parseTask(String input){

    }
    public Command prepareAddPet(String input){
        int startOfN = input.indexOf(" n/");
        int startOfS = input.indexOf(" s/");

        if(startOfN > startOfS || startOfN == -1|| startOfS == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        String name = input.substring(startOfN + lengthOfSignature, startOfS);
        String status = input.substring(startOfS + lengthOfSignature);
        return new AddPetCommand(name, status);
    }


    public Command parsePet(String input){
        if(!input.contains(" ")){
            if(input == "view"){
                return new AppointmentViewCommand();
            }
            System.out.println("input invalid");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddPetCommand.COMMAND_WORD:
            return prepareAddPet(statement);
            break;
        case RemoveAppointmentCommand.COMMAND_WORD:
            return prepareRemovePet(statement);
            break;
        case ListPetsCommand.COMMAND_WORD:
            return new ListPetsCommand();
            break;
        case AllocateAppointmentCommand.COMMAND_WORD:
            return prepareAllocateAppointment(statement);
            break;
        default:
            System.out.println("input invalid");
            return new EndCommand();
        }
    }

    public Command parseEmployee(String input){
        if(!input.contains(" ")){
            if(input == "view"){
                return new ViewEmployeeCommand();
            }
            System.out.println("input invalid");
            return new EndCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case AddEmployeeCommand.COMMAND_WORD:
            return prepareAddEmployee(statement);
            break;
        case RemoveEmployeeCommand.COMMAND_WORD:
            return prepareRemoveEmployee(statement);
            break;
        default:
            System.out.println("input invalid");
            return new EndCommand();
        }
    }

    public Command prepareAddEmployee(String input){
        int startOfN = input.indexOf(" n/");

        if(startOfN == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        String name = input.substring(startOfN + lengthOfSignature);
        return new AddEmployeeCommand(name);
    }


    public Command prepareRemoveEmployee(String input){
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new EndCommand();
        }

        return new RemoveEmployeeCommand(index);
    }



}
