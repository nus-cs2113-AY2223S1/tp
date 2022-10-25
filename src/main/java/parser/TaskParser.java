package parser;

import command.Command;

public class TaskParser {
    private int lengthOfSignature;
    private Parser parser;

    public TaskParser(Parser parser, int lengthOfSignature){
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }

    public Command parseTask(String input){
        if (input.equals("view")){

        }
    }
}
