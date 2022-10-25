package command;


public class EndCommand extends Command{

    public EndCommand(){

    }

    @Override
    public void execute() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
