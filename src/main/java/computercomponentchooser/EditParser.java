package computercomponentchooser;

import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Memory;
import computercomponentchooser.components.Motherboard;
import computercomponentchooser.components.PowerSupply;
import computercomponentchooser.components.Gpu;
import computercomponentchooser.components.Drive;
import computercomponentchooser.exceptions.UnknownCommandException;


public class EditParser {

    static final int COMMAND_PARAMETER = 0;

    static final int NAME_PARAMETER = 1;
    static final int TYPE_PARAMETER = 2;

    static final int PRICE_PARAMETER = 3;

    static final int POWER_PARAMETER = 4;

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
                case "powersupply":
                    PowerSupply powersupply = new PowerSupply(name, price, power);
                    editBuild = buildManager.getBuild(buildName);
                    editBuild.addComponent(type, powersupply);
                    break;
                case "gpu":
                    Gpu gpu = new Gpu(name, price, power, getParameter(line, 5),
                            getParameter(line, 6));
                    editBuild = buildManager.getBuild(buildName);
                    editBuild.addComponent(type, gpu);
                    break;
                case "drive":
                    Drive drive = new Drive(name, price, power, getParameter(line, 5),
                            getParameter(line, 6));
                    editBuild = buildManager.getBuild(buildName);
                    editBuild.addComponent(type, drive);
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
            case "view":
                name = getParameter(line, NAME_PARAMETER);
                type = getParameter(line, TYPE_PARAMETER);
                editBuild = buildManager.getBuild(buildName);
                Ui.printLine();
                System.out.println(editBuild.getComponent(type, name).getDetails());
                Ui.printLine();
                break;
            case "check":
                editBuild = buildManager.getBuild(buildName);
                Ui.printLine();
                System.out.println("Compatibility Info:");
                System.out.print(editBuild.getCompatibilityInfo());
                Ui.printLine();
                break;
            case "info":
                editBuild = buildManager.getBuild(buildName);
                Ui.printLine();
                System.out.println("Build Info:");
                System.out.print(editBuild.getBuildInfo());
                Ui.printLine();
                break;
            case "back":
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println(e.getMessage());
            Ui.printLine();
        }
    }
}

