package parser;
import command.*;
import exception.DukeException;
import seedu.duke.Duke;

public class Parser {
    private final int lengthOfSignature = 3;
    private ServiceParser serviceParser;
    private EmployeeParser employeeParser;
    private PetParser petParser;
    private AppointmentParser appointmentParser;

    public Parser(){
        serviceParser = new ServiceParser(this, lengthOfSignature);
        employeeParser = new EmployeeParser(this, lengthOfSignature);
        petParser = new PetParser(this, lengthOfSignature);
        appointmentParser = new AppointmentParser(this, lengthOfSignature);
    }


    public Command parseCommand(String input) {
        input = input.trim();

        if(!input.contains(" ")){
            if(input.equals("bye")){
                return new EndCommand();
            }
            
            System.out.println("Error: only one parameter received and it is not bye");
            return new EmptyCommand();
        }


        int indexOfSpace = input.indexOf(" ");
        String type = input.substring(0,indexOfSpace);
        String statement = input.substring(indexOfSpace).trim();

        switch(type) {
        case "appointment":
            return appointmentParser.parseAppointment(statement);
        case "pet":
            return petParser.parsePet(statement);
        case "employee":
            return employeeParser.parseEmployee(statement);
        case "service":
            return serviceParser.parseService(statement);
        default:
            System.out.println("Error: unrecognized operation");
            return new EmptyCommand();
        }
    }


    /*

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
     */


    public int indexOfRemove(String input){
        try {
            return numberInInput(input, " i/");
        } catch (DukeException e){
            System.out.println("Error: signature \"i/\" not found.");
            return -1;
        }
    }

    public int numberInInput(String input, String format) throws DukeException {
        if(!input.contains(format)){
            throw new DukeException();
        }

        String id = input.substring(input.indexOf(format)+lengthOfSignature);
        if(!isInt(id)){
            return -1;
        }

        return Integer.parseInt(id);
    }


    public boolean isInt(String input){
        Boolean strResult = input.matches("\\d?");
        if(strResult) {
            return true;
        }
        return false;
    }

    public int isStatus(String input){
        try {
            return numberInInput(input, " s/");
        } catch (DukeException e){
            System.out.println("Error: signature \"s/\" not found.");
            return -1;
        }
    }

    public int isHealthy(String input){
        try{
            return numberInInput(input, " h/");
        } catch (DukeException e){
            System.out.println("Error: signature \"h/\" not found.");
            return -1;
        }
    }

}