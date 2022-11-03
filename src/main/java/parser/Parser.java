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

    public final String indexFlag = " i/";
    public final String employeeFlag = " e/";
    public final String healthFlag = " h/";
    public final String dateFlag = " d/";

    public final String nameFlag = " n/";

    public final String serviceFlag = " s/";
    public final String petFlag = " p/";

    public final String speciesFlag = " s/";

    public final String descriptionFlag = " d/";

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
            return numberInInput(input, indexFlag);
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
            if (id == null || id.length() > 4) {
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
            return numberInInput(input, employeeFlag);
        } catch (DukeException e) {
            throw new DukeException();
        }
    }

    public int isHealthy(String input) throws DukeException {
        try {
            return numberInInput(input, healthFlag);
        } catch (DukeException e) {
            System.out.println("Please enter 0 or 1 for health status");
            throw new DukeException();
        }
    }

}