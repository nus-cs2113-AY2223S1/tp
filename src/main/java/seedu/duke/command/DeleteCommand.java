package seedu.duke.command;

import seedu.duke.timetable.Lesson;

public class DeleteCommand extends Command {
    Lesson lesson;

    boolean hasDeleteComment;

    String checker;
    public DeleteCommand(String[] parameters, CommandType commandType, boolean isDeleteModule, Lesson lesson) {
        super(parameters, commandType);
        this.lesson = lesson;
        this.universityName = parameters[1].substring(2);
        if (isDeleteModule) {
            this.moduleCode = parameters[2].substring(2);
        }
        hasDeleteComment = false;
        if (parameters.length == 4) {
            this.hasDeleteComment = true;
            this.checker = parameters[3].substring(5);
        }
    }

    @Override
    public Lesson getLesson() {
        return lesson;
    }

    public boolean hasDeleteComment() {
        return hasDeleteComment;
    }

    public String getChecker() {
        return checker;
    }

}
