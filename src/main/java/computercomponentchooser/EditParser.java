package computercomponentchooser;

import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Motherboard;
import computercomponentchooser.components.Drive;
import computercomponentchooser.components.Gpu;
import computercomponentchooser.components.Memory;
import computercomponentchooser.components.Monitor;
import computercomponentchooser.components.PowerSupply;
import computercomponentchooser.components.Other;
import computercomponentchooser.components.Cooler;
import computercomponentchooser.components.Case;

import computercomponentchooser.exceptions.BlankStringException;
import computercomponentchooser.exceptions.UnknownCommandException;
import computercomponentchooser.export.ExportText;

import static computercomponentchooser.ComputerComponentChooser.storage;


public class EditParser {

    static final int COMMAND_PARAMETER = 0;

    static final int TYPE_PARAMETER = 1;

    static final int NAME_PARAMETER = 2;

    static final int PRICE_PARAMETER = 3;

    static final int POWER_PARAMETER = 4;

    public static String buildName;

    private final BuildManager buildManager;

    public EditParser(BuildManager buildManager) {
        this.buildManager = buildManager;
    }

    public static String getParameter(String line, int mode) {
        String[] lineSplit = line.split("/");
        return lineSplit[mode];
    }

    public static boolean checkBack(String line) {
        String back = getParameter(line, COMMAND_PARAMETER).toLowerCase();
        return back.equals("back");
    }

    public void parse(String line) {
        String command = getParameter(line, COMMAND_PARAMETER).toLowerCase();
        Build editBuild = buildManager.getBuild(buildName);

        try {
            switch (command) {
            case "add":
                parseAdd(editBuild, line);
                break;
            case "delete":
                parseDelete(editBuild, line);
                break;
            case "list":
                parseList(editBuild);
                break;
            case "edit":
                parseEdit(line);
                break;
            case "view":
                parseView(editBuild, line);
                break;
            case "check":
                parseCheck(editBuild);
                break;
            case "info":
                parseInfo(editBuild);
                break;
            case "back":
                break;
            case "export":
                parseExport(editBuild);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | ArrayIndexOutOfBoundsException | NullPointerException |
                 BlankStringException e) {
            System.out.println(e.getMessage());
            Ui.printLine();
        }
    }

    public void parseAdd(Build editBuild, String line) throws BlankStringException {
        String name = getParameter(line, NAME_PARAMETER);
        String type = getParameter(line, TYPE_PARAMETER);
        String price = getParameter(line, PRICE_PARAMETER);
        String power = getParameter(line, POWER_PARAMETER);
        if (name.isBlank() || type.isBlank() || price.isBlank() || power.isBlank()) {
            throw new BlankStringException();
        }
        switch (type) {
        case "cpu":
            Cpu cpu = new Cpu(name, price, power,
                    getParameter(line, 5), getParameter(line, 6));
            editBuild.addComponent(type, cpu);
            break;
        case "memory":
            Memory memory = new Memory(name, price, power, getParameter(line, 5),
                    getParameter(line, 6));
            editBuild.addComponent(type, memory);
            break;
        case "motherboard":
            Motherboard motherboard = new Motherboard(name, price, power, getParameter(line, 5),
                    getParameter(line, 6), getParameter(line, 7), getParameter(line, 8));
            editBuild.addComponent(type, motherboard);
            break;
        case "powersupply":
            PowerSupply powersupply = new PowerSupply(name, price, power);
            editBuild.addComponent(type, powersupply);
            break;
        case "gpu":
            Gpu gpu = new Gpu(name, price, power, getParameter(line, 5),
                    getParameter(line, 6));
            editBuild.addComponent(type, gpu);
            break;
        case "drive":
            Drive drive = new Drive(name, price, power, getParameter(line, 5),
                    getParameter(line, 6));
            editBuild.addComponent(type, drive);
            break;
        case "monitor":
            Monitor monitor = new Monitor(name, price, power, getParameter(line, 5),
                    getParameter(line, 6), getParameter(line, 7));
            editBuild.addComponent(type, monitor);
            break;
        case "cooler":
            Cooler cooler = new Cooler(name, price, power, getParameter(line, 5),
                    getParameter(line, 6), getParameter(line, 7));
            editBuild.addComponent(type, cooler);
            break;
        case "case":
            Case case1 = new Case(name, price, power, getParameter(line, 5),
                    getParameter(line, 6));
            editBuild.addComponent(type, case1);
            break;
        case "other":
            Other other = new Other(name, price, power);
            editBuild.addComponent(type, other);
            break;
        default:
            break;
        } try {
            storage.saveComponent(editBuild);
        } catch (Exception e) {
            System.out.println("Error saving build");
        }

        Ui.printLine();
        System.out.println("You have added " + name);
        Ui.printLine();
    }

    public void parseDelete(Build editBuild, String line) {
        String name = getParameter(line, NAME_PARAMETER);
        String type = getParameter(line, TYPE_PARAMETER);
        editBuild.deleteComponent(type, name);
        try {
            storage.saveComponent(editBuild);
        } catch (Exception e) {
            System.out.println("Error saving build");
        }
        Ui.printLine();
        System.out.println("You have removed " + name);
        Ui.printLine();
    }

    public void parseList(Build editBuild) {
        Ui.printLine();
        if (editBuild.getAllComponents().size() == 0) {
            System.out.println("You have no components");
            Ui.printLine();
            return;
        }
        System.out.println("Computer parts for " + buildName + ":");
        System.out.print(editBuild);
        Ui.printLine();
    }

    public void parseEdit(String line) {
        buildName = getParameter(line, TYPE_PARAMETER);
        Ui.printLine();
        System.out.println("You are now editing " + buildName);
        Ui.printLine();
    }

    public void parseView(Build editBuild, String line) {
        String name = getParameter(line, NAME_PARAMETER);
        String type = getParameter(line, TYPE_PARAMETER);
        Ui.printLine();
        System.out.println(editBuild.getComponent(type, name).getDetails());
        Ui.printLine();
    }

    public void parseCheck(Build editBuild) {
        Ui.printLine();
        System.out.println("Compatibility Info:");
        System.out.print(editBuild.getCompatibilityInfo());
        Ui.printLine();
    }

    public void parseInfo(Build editBuild) {
        Ui.printLine();
        System.out.println("Build Info:");
        System.out.print(editBuild.getBuildInfo());
        Ui.printLine();
    }

    public void parseExport(Build editBuild) {
        Ui.printLine();
        System.out.println("Exporting build to text file...");
        try {
            ExportText.exportBuildText(editBuild);
        } catch (Exception e) {
            System.out.println("Error exporting build");
        }
        Ui.printLine();
    }
}

