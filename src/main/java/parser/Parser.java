package parser;

import command.Command;
import command.EndCommand;
import exception.DukeException;

public class Parser {
    private final int lengthOfSignature = 3;
    private static ServiceParser serviceParser;
    private static EmployeeParser employeeParser;
    private static PetParser petParser;
    private static AppointmentParser appointmentParser;
    private static TaskParser taskParser;

    public final String INDEX_FLAG = " i/";
    public final String EMPLOYEE_FLAG = " e/";
    public final String HEALTH_FLAG = " h/";
    public final String DATE_FLAG = " d/";

    public final String NAME_FLAG = " n/";

    public final String SERVICE_FLAG = " s/";
    public final String PET_FLAG = " p/";

    public final String SPECIES_FLAG = " s/";

    public final String DESCRIPTION_FLAG = " d/";

    public Parser() {
        serviceParser = new ServiceParser(this, lengthOfSignature);
        employeeParser = new EmployeeParser(this, lengthOfSignature);
        petParser = new PetParser(this, lengthOfSignature);
        appointmentParser = new AppointmentParser(this, lengthOfSignature);
        taskParser = new TaskParser(this, lengthOfSignature);
    }


    public Command parseCommand(String input) throws DukeException {
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
            return numberInInput(input, INDEX_FLAG);
        } catch (DukeException e) {
            System.out.println("Please enter a valid index");
            throw new DukeException();
        }
    }

    public int numberInInput(String input, String format) throws DukeException {
        if (!input.contains(format) || input.indexOf(format) != 0) {
            throw new DukeException();
        }

        String id = input.substring(input.indexOf(format) + lengthOfSignature);

        try {
            if (id == null) {
                throw new DukeException();
            }
            isInt(id);
        } catch (DukeException e) {
            throw new DukeException();
        }

        return Integer.parseInt(id);
    }

    public boolean isInt(String input) throws DukeException {
        Boolean strResult = input.matches("\\d+");
        if (strResult) {
            return true;
        }

        throw new DukeException();
    }

    public int employeeId(String input) throws DukeException {
        try {
            return numberInInput(input, EMPLOYEE_FLAG);
        } catch (DukeException e) {
            throw new DukeException();
        }
    }

    public int isHealthy(String input) throws DukeException {
        try {
            return numberInInput(input, HEALTH_FLAG);
        } catch (DukeException e) {
            System.out.println("Please enter 0 or 1 for health status");
            throw new DukeException();
        }
    }

}