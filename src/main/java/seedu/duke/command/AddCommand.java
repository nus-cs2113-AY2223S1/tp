package seedu.duke.command;

import seedu.duke.timetable.Lesson;

public class AddCommand extends Command {
    Lesson lesson;
    String comment;

    boolean hasComment;

    public AddCommand(String[] parameters, CommandType commandType, Lesson lesson) {
        super(parameters, commandType);
        this.lesson = lesson;
        this.universityName = parameters[1].substring(2);
        this.moduleCode = parameters[2].substring(2);
        this.hasComment = false;
        if (parameters.length == 4) {
            this.hasComment = true;
            this.comment = parameters[3].substring(5);
        }
    }

    @Override
    public Lesson getLesson() {
        return lesson;
    }

    public String getComment() {
        return comment;
    }

    public boolean hasComment() {
        return hasComment;
    }

}
