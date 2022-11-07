package seedu.duke.command;

import seedu.duke.timetable.Lesson;

public abstract class Command {
    CommandType commandType;
    String universityName = null;
    String moduleCode = null;
    String[] parameters;

    public Command(String[] parameters, CommandType commandType) {
        this.parameters = parameters;
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getUniversityName() {
        return universityName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public Lesson getLesson() {
        return null;
    }
}
