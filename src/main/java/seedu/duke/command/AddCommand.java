package seedu.duke.command;

import seedu.duke.timetable.Lesson;

public class AddCommand extends Command {
    Lesson lesson;

    public AddCommand(String[] parameters, CommandType commandType, Lesson lesson) {
        super(parameters, commandType);
        this.lesson = lesson;
        this.universityName = parameters[1].substring(2);
        this.moduleCode = parameters[2].substring(2);
    }

    @Override
    public Lesson getLesson() {
        return lesson;
    }

}
