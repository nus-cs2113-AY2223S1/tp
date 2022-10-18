package command;

public class EmptyCommand extends Command {

    @Override
    public void execute() {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
