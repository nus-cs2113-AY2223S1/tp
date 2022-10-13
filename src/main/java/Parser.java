import command.*;


public class Parser {
    private final int lengthOfSignature = 3;
    public command parseCommand(String input){
        input = input.trim();

        if(!input.contains(" ")){
            if(input == "bye"){
                return new ExitCommand();
            }
            System.out.println("input invalid");
            return new ExitCommand();
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
        case "staff":
            return parseStaff(statement);
            break;
        default:
            System.out.println("input invalid");
            return new ExitCommand();
        }
    }

    public command parseAppointment(String input){
        if(!input.contains(" ")){
            if(input == "view"){
                return new AppointmentViewCommand();
            }
            System.out.println("input invalid");
            return new ExitCommand();
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
        case ViewAppointmentCommand.COMMAND_WORD:
            return new ViewAppointmentCommand();
        break;
        case AllocateAppointmentCommand.COMMAND_WORD:
            return prepareAllocateAppointment(statement);
        break;
        default:
            return new ExitCommand();
        }
    }

    public command prepareAllocateAppointment(String input){

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


    public command prepareRemoveAppointment(String input){
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new ExitCommand();
        }

        return new RemoveAppointmentCommand(index);
    }

    public command prepareRemovePet(String input){
        int index = indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new ExitCommand();
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


    public command prepareAddAppointment(String input){
        int s = input.indexOf(" s/");
        int p = input.indexOf(" p/");
        int d = input.indexOf(" d/");

        if(s > p || p > d || s == -1 || p == -1 || d == -1){
            System.out.println("invalid input");
            return new ExitCommand();
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


    public command parseTask(String input){

    }
    public command prepareAddPet(String input){
        int startOfN = input.indexOf(" n/");
        int startOfS = input.indexOf(" s/");

        if(startOfN > startOfS || startOfN == -1|| startOfS == -1){
            System.out.println("input invalid");
            return new ExitCommand();
        }

        String name = input.substring(startOfN + lengthOfSignature, startOfS);
        String status = input.substring(startOfS + lengthOfSignature);
        return new AddPetCommand(name, status);
    }


    public command parsePet(String input){
        if(!input.contains(" ")){
            if(input == "view"){
                return new viewPetCommand();
            }
            System.out.println("input invalid");
            return new ExitCommand();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" ")).trim();
        switch(type) {
        case add:
            return prepareAddPet(statement);
        break;
        case RemoveAppointmentCommand.COMMAND_WORD:
            return prepareRemovePet(statement);
        break;
        case ListPetsCommand.COMMAND_WORD:
            return new ListPetsCommand();
            break;
        default:
            System.out.println("input invalid");
            return new ExitCommand();
        }
    }



}
