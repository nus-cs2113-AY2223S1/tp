package computercomponentchooser;

import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Memory;
import computercomponentchooser.components.Motherboard;
import computercomponentchooser.exceptions.UnknownCommandException;


public class EditParser {

    static final int COMMAND_PARAMETER = 0;

    static final int NAME_PARAMETER = 1;
    static final int TYPE_PARAMETER = 2;

    static final int PRICE_PARAMETER = 3;

    static final int POWER_PARAMETER = 4;

    static final int CONTENT_PARAMETER = 5;
    public static String buildName;

    private final BuildManager buildManager;

    public EditParser(BuildManager buildManager) {
        this.buildManager = buildManager;
    }

    private static String getParameter(String line, int mode) {
        String[] lineSplit = line.split(" ");
        return lineSplit[mode];
    }

    public static boolean checkBack(String line) {
        String back = getParameter(line, COMMAND_PARAMETER).toLowerCase();
        return back.equals("back");
    }

    public void parse(String line) {
        String command = getParameter(line, COMMAND_PARAMETER).toLowerCase();

        String name;
        String type;
        String price;
        String power;
        Build editBuild;
        try {
            switch (command) {
            case "add":
                name = getParameter(line, NAME_PARAMETER);
                type = getParameter(line, TYPE_PARAMETER);
                price = getParameter(line, PRICE_PARAMETER);
                power = getParameter(line, POWER_PARAMETER);
                switch (type) {
                case "cpu":
                    Cpu cpu = new Cpu(name, price, power,
                            getParameter(line, 5), getParameter(line, 6));
                    editBuild = buildManager.getBuild(buildName);
                    editBuild.addComponent(type, cpu);
                    break;
                case "memory":
                    Memory memory = new Memory(name, price, power, getParameter(line, 5),
                            getParameter(line, 6));
                    editBuild = buildManager.getBuild(buildName);
                    editBuild.addComponent(type, memory);
                    break;
                case "motherboard":
                    Motherboard motherboard = new Motherboard(name, price, power, getParameter(line, 5),
                            getParameter(line, 6), getParameter(line, 7));
                    editBuild = buildManager.getBuild(buildName);
                    editBuild.addComponent(type, motherboard);
                    break;
                default:
                    break;
                }

                Ui.printLine();
                System.out.println("You have added " + name);
                Ui.printLine();
                break;
            case "delete":
                name = getParameter(line, NAME_PARAMETER);
                type = getParameter(line, TYPE_PARAMETER);
                editBuild = buildManager.getBuild(buildName);
                editBuild.deleteComponent(type, name);
                Ui.printLine();
                System.out.println("You have removed " + name);
                Ui.printLine();
                break;
            case "list":
                editBuild = buildManager.getBuild(buildName);
                Ui.printLine();
                System.out.println("Computer parts for " + buildName + ":");
                System.out.print(editBuild.toString());
                Ui.printLine();
                break;
            case "edit":
                buildName = getParameter(line, NAME_PARAMETER);
                Ui.printLine();
                System.out.println("You are now editing " + buildName);
                Ui.printLine();
                break;
            case "check":
            case "back":
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
            Ui.printLine();
        }
    }

//    public String parseCpu(String line, int mode) {
//        String[] lineSplit = line.split(" ", 8);
//        return lineSplit[mode];
//    }
//
//    public String addCpu(String line, int mode) {
//        String[] lineSplit = line.split(" ", 8);
//        return lineSplit[mode];
//    }
}

