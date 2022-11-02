package seedu.duke.command;

import seedu.duke.exceptions.InvalidCommentException;
import seedu.duke.timetable.Lesson;
import seedu.duke.ui.Ui;

public class AddCommand extends Command {
    Lesson lesson;
    String comment;

    boolean hasComment;

    private static final int START_INDEX_COMMENT = 6;
    boolean isValidComment;

    public AddCommand(String[] parameters, CommandType commandType, Lesson lesson, String comment) {
        super(parameters, commandType);
        this.lesson = lesson;
        this.universityName = parameters[1].substring(2);
        this.moduleCode = parameters[2].substring(2);

        if (parameters.length == 4) {
            this.hasComment = true;
            this.isValidComment = isValidComment(comment);
            if (this.isValidComment) {
                this.comment = comment.substring(START_INDEX_COMMENT, comment.length() - 1);
            } else {
                this.comment = "";
            }

        } else {
            this.hasComment = false;
            this.comment = "";
        }
    }

    private boolean isValidComment(String comment) {
        if (!comment.startsWith("note/{")) {
            return false;
        } else if (!comment.endsWith("}")) {
            return false;
        } else if (comment.length() <= 7) {
            return false;
        }
        return true;
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

    public boolean getValidatedComment() {
        return isValidComment;
    }
}
