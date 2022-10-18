package parser;
import command.*;
import exception.DukeException;

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
        try {
            if (!input.contains(" ")) {
                if (input.equals("bye")) {
                    return new EndCommand();
                }

                throw new DukeException();
            }   
        } catch (DukeException e) {
            System.out.println("Sorry, only one parameter received and it is not bye");
            return new EmptyCommand();
        }


        int indexOfSpace = input.indexOf(" ");
        String type = input.substring(0,indexOfSpace);
        String statement = input.substring(indexOfSpace).trim();


        try {
            switch (type) {
            case "appointment":
                return appointmentParser.parseAppointment(statement);
            case "pet":
                return petParser.parsePet(statement);
            case "employee":
                return employeeParser.parseEmployee(statement);
            case "service":
                return serviceParser.parseService(statement);
            default:
                throw new DukeException();
            }
        } catch (DukeException e){
            System.out.println("Sorry, unrecognized operation");
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

    public int indexOfInput(String input) throws DukeException {
        try {
            return numberInInput(input, " i/");
        } catch (DukeException e){
            throw new DukeException();
        }
    }

    public int numberInInput(String input, String format) throws DukeException {
        if(!input.contains(format)){
            throw new DukeException();
        }

        String id = input.substring(input.indexOf(format)+lengthOfSignature);

        try{
            isInt(id);
        } catch (DukeException e){
            throw new DukeException();
        }

        return Integer.parseInt(id);
    }


    public boolean isInt(String input) throws DukeException {
        Boolean strResult = input.matches("\\d?");
        if(strResult) {
            return true;
        }

        throw new DukeException();
    }

    public int isStatus(String input) throws DukeException {
        try {
            return numberInInput(input, " s/");
        } catch (DukeException e){
            throw new DukeException();
        }
    }

    public int isHealthy(String input) throws DukeException {
        try{
            return numberInInput(input, " h/");
        } catch (DukeException e){
            throw new DukeException();
        }
    }

}