package command;

public abstract class Command {

    public Command() {
    }

    public abstract void execute();

    public abstract boolean isExit();
}
