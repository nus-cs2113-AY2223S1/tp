package seedu.duke.command;

import seedu.duke.timetable.Lesson;

public class DeleteCommand extends Command {
    Lesson lesson;

    public DeleteCommand(String[] parameters, CommandType commandType, boolean isDeleteModule, Lesson lesson) {
        super(parameters, commandType);
        this.lesson = lesson;
        this.universityName = parameters[1].substring(2);
        if (isDeleteModule) {
            this.moduleCode = parameters[2].substring(2);
        }
    }

    @Override
    public Lesson getLesson() {
        return lesson;
    }

}
