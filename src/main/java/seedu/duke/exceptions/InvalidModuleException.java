package seedu.duke.exceptions;

public class InvalidModuleException extends Exception{
    @Override
    public String getMessage(){
        return "Module is invalid!";
    }
}
