package parser;
import command.*;
import exception.DukeException;

public class Parser {
    private final int lengthOfSignature = 3;
    private static ServiceParser serviceParser;
    private static EmployeeParser employeeParser;
    private static PetParser petParser;
    private static AppointmentParser appointmentParser;
    private static TaskParser taskParser;

    public Parser(){
        serviceParser = new ServiceParser(this, lengthOfSignature);
        employeeParser = new EmployeeParser(this, lengthOfSignature);
        petParser = new PetParser(this, lengthOfSignature);
        appointmentParser = new AppointmentParser(this, lengthOfSignature);
        taskParser = new TaskParser(this, lengthOfSignature);
    }


    public Command parseCommand(String input) throws DukeException{
        input = input.trim();
        if (!input.contains(" ")) {
            if (input.equals("bye")) {
                return new EndCommand();
            }
            throw new DukeException();
        }

        int indexOfSpace = input.indexOf(" ");
        String type = input.substring(0,indexOfSpace);
        String statement = input.substring(indexOfSpace).trim();

        switch (type) {
        case "appointment":
            return appointmentParser.parseAppointment(statement);
        case "pet":
            return petParser.parsePet(statement);
        case "employee":
            return employeeParser.parseEmployee(statement);
        case "service":
            return serviceParser.parseService(statement);
        case "task":
            return taskParser.parseTask(statement);
        default:
            throw new DukeException();
        }
    }


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