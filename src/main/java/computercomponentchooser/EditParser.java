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
import computercomponentchooser.exceptions.UnlistedBuildException;
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
        assert mode >= 0;
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
        } catch (UnknownCommandException | UnlistedBuildException | ArrayIndexOutOfBoundsException
                 | NullPointerException | BlankStringException e) {
            Ui.printLine();
            System.out.println(e.getMessage());
            Ui.printLine();
        }
    }

    public void parseAdd(Build editBuild, String line) throws BlankStringException {
        String type = getParameter(line, TYPE_PARAMETER);
        String name = getParameter(line, NAME_PARAMETER);
        String price = getParameter(line, PRICE_PARAMETER);
        String power = getParameter(line, POWER_PARAMETER);
        if (name.isBlank() || type.isBlank() || price.isBlank() || power.isBlank()) {
            throw new BlankStringException();
        }
        switch (type) {
        case "cpu":
            parseAddCpu(editBuild, line, type, name, price, power);
            break;
        case "memory":
            parseAddMemory(editBuild, line, type, name, price, power);
            break;
        case "motherboard":
            parseAddMotherboard(editBuild, line, type, name, price, power);
            break;
        case "powersupply":
            parseAddPowerSupply(editBuild, type, name, price, power);
            break;
        case "gpu":
            parseAddGpu(editBuild, line, type, name, price, power);
            break;
        case "drive":
            parseAddDrive(editBuild, line, type, name, price, power);
            break;
        case "monitor":
            parseAddMonitor(editBuild, line, type, name, price, power);
            break;
        case "cooler":
            parseAddCooler(editBuild, line, type, name, price, power);
            break;
        case "case":
            parseAddCase(editBuild, line, type, name, price, power);
            break;
        case "other":
            parseAddOther(editBuild, type, name, price, power);
            break;
        default:
            break;
        } try {
            storage.saveComponent(editBuild);
        } catch (Exception e) {
            System.out.println("Error saving build");
        }
        System.out.println("You have added " + name);
        Ui.printLine();
    }

    public void parseAddCpu(Build editBuild, String line, String type, String name, String price, String power) {
        Cpu cpu = new Cpu(name, price, power, getParameter(line, 5), getParameter(line, 6));
        editBuild.addComponent(type, cpu);
    }

    public void parseAddMemory(Build editBuild, String line, String type, String name, String price, String power) {
        Memory memory = new Memory(name, price, power, getParameter(line, 5),
                getParameter(line, 6));
        editBuild.addComponent(type, memory);
    }

    public void parseAddMotherboard(Build editBuild, String line, String type, String name, String price,
                                    String power) {
        Motherboard motherboard = new Motherboard(name, price, power, getParameter(line, 5),
                getParameter(line, 6), getParameter(line, 7), getParameter(line, 8));
        editBuild.addComponent(type, motherboard);
    }

    public void parseAddPowerSupply(Build editBuild, String type, String name, String price,
                                    String power) {
        PowerSupply powersupply = new PowerSupply(name, price, power);
        editBuild.addComponent(type, powersupply);
    }

    public void parseAddGpu(Build editBuild, String line, String type, String name, String price, String power) {
        Gpu gpu = new Gpu(name, price, power, getParameter(line, 5),
                getParameter(line, 6));
        editBuild.addComponent(type, gpu);
    }

    public void parseAddDrive(Build editBuild, String line, String type, String name, String price, String power) {
        Drive drive = new Drive(name, price, power, getParameter(line, 5),
                getParameter(line, 6));
        editBuild.addComponent(type, drive);
    }

    public void parseAddMonitor(Build editBuild, String line, String type, String name, String price, String power) {
        Monitor monitor = new Monitor(name, price, power, getParameter(line, 5),
                getParameter(line, 6), getParameter(line, 7));
        editBuild.addComponent(type, monitor);
    }

    public void parseAddCooler(Build editBuild, String line, String type, String name, String price, String power) {
        Cooler cooler = new Cooler(name, price, power, getParameter(line, 5),
                getParameter(line, 6), getParameter(line, 7));
        editBuild.addComponent(type, cooler);
    }

    public void parseAddCase(Build editBuild, String line, String type, String name, String price, String power) {
        Case case1 = new Case(name, price, power, getParameter(line, 5),
                getParameter(line, 6));
        editBuild.addComponent(type, case1);
    }

    public void parseAddOther(Build editBuild, String type, String name, String price, String power) {
        Other other = new Other(name, price, power);
        editBuild.addComponent(type, other);
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

    public void parseEdit(String line) throws UnlistedBuildException {
        buildName = getParameter(line, TYPE_PARAMETER);
        if (!BuildManager.doesBuildExist(buildName)) {
            throw new UnlistedBuildException();
        }
        Ui.printLine();
        System.out.println("You are now editing " + buildName);
        Ui.printLine();
    }

    public void parseView(Build editBuild, String line) throws UnlistedBuildException {
        String name = getParameter(line, NAME_PARAMETER);
        String type = getParameter(line, TYPE_PARAMETER);
        Ui.printLine();
        if (!BuildManager.doesBuildExist(name)) {
            throw new UnlistedBuildException();
        }
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

