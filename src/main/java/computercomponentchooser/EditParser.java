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
import computercomponentchooser.exceptions.NegativeNumberException;
import computercomponentchooser.exceptions.UnknownCommandException;
import computercomponentchooser.exceptions.UnlistedBuildException;
import computercomponentchooser.exceptions.UnlistedComponentException;
import computercomponentchooser.exceptions.UnlistedComponentTypeException;
import computercomponentchooser.export.ExportText;

import static computercomponentchooser.ComputerComponentChooser.storage;


/**
 * Handles the parsing of user input in the edit mode.
 */
public class EditParser {

    static final int COMMAND_PARAMETER = 0;

    static final int TYPE_PARAMETER = 1;

    static final int NAME_PARAMETER = 2;

    static final int PRICE_PARAMETER = 3;

    static final int POWER_PARAMETER = 4;

    public static String buildName;

    private final BuildManager buildManager;

    /**
     * Initializes a new EditParser object.
     *
     * @param buildManager The buildManager to be used.
     */
    public EditParser(BuildManager buildManager) {
        this.buildManager = buildManager;
    }

    /**
     * Gets the desired text from a position of the user input delimited by /.
     *
     * @param line The user input.
     * @param mode The desired position of the input to be retrieved.
     * @return The desired text at the desired position.
     */
    public static String getParameter(String line, int mode) throws ArrayIndexOutOfBoundsException {
        assert mode >= 0;
        String[] lineSplit = line.split("/");
        return lineSplit[mode];
    }

    /**
     * Checks if the user input is bye.
     *
     * @param line The user input.
     * @return A boolean value indicating whether the user input is bye.
     */
    public static boolean checkBack(String line) {
        String back = getParameter(line, COMMAND_PARAMETER).toLowerCase().trim();
        if (back.equals("back")) {
            Ui.printEditLine();
            System.out.println("Back to main mode.");
            Ui.printEditLine();
        }
        return back.equals("back");
    }

    /**
     * Parses the user input and executes the desired command.
     *
     * @param line The user input.
     */
    public void parse(String line) {
        String command = getParameter(line, COMMAND_PARAMETER).toLowerCase().trim();
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
        } catch (UnknownCommandException | UnlistedBuildException | UnlistedComponentException
                 | BlankStringException e) {
            Ui.printEditLine();
            System.out.println(e.getMessage());
            Ui.printEditLine();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            Ui.printEditLine();
            System.out.println("Please enter a command with the correct number of parameters");
            Ui.printEditLine();
        }
    }

    /**
     * Parses the user input and executes the add command on the desired build.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @throws BlankStringException If the user input is blank.
     */
    public void parseAdd(Build editBuild, String line) throws BlankStringException, ArrayIndexOutOfBoundsException {
        String type = getParameter(line, TYPE_PARAMETER);
        String name = getParameter(line, NAME_PARAMETER);
        String price = getParameter(line, PRICE_PARAMETER);
        String power = getParameter(line, POWER_PARAMETER);
        if (name.isBlank() || type.isBlank() || price.isBlank() || power.isBlank()) {
            throw new BlankStringException();
        }
        try {
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
                throw new UnlistedComponentTypeException();
            }
            try {
                storage.saveComponent(editBuild);
            } catch (Exception e) {
                System.out.println("Error saving build");
            }
            Ui.printEditLine();
            System.out.println("You have added " + name);
            Ui.printEditLine();
        } catch (NumberFormatException e) {
            Ui.printEditLine();
            System.out.println("Please input the numbers correctly.");
            Ui.printEditLine();
        } catch (NegativeNumberException | UnlistedComponentTypeException e) {
            Ui.printEditLine();
            System.out.println(e.getMessage());
            Ui.printEditLine();
        }
    }

    /**
     * Parses the user input and executes the add command on the desired build for cpu.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddCpu(Build editBuild, String line, String type, String name, String price, String power)
            throws NegativeNumberException, NumberFormatException {
        Cpu cpu = new Cpu(name, price, power, getParameter(line, 5), getParameter(line, 6));
        editBuild.addComponent(type, cpu);
    }

    /**
     * Parses the user input and executes the add command on the desired build for memory.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddMemory(Build editBuild, String line, String type, String name, String price, String power)
            throws NegativeNumberException, NumberFormatException {
        Memory memory = new Memory(name, price, power, getParameter(line, 5),
                getParameter(line, 6));
        editBuild.addComponent(type, memory);
    }

    /**
     * Parses the user input and executes the add command on the desired build for motherboard.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddMotherboard(Build editBuild, String line, String type, String name, String price,
                                    String power) throws NegativeNumberException, NumberFormatException {
        Motherboard motherboard = new Motherboard(name, price, power, getParameter(line, 5),
                getParameter(line, 6), getParameter(line, 7), getParameter(line, 8));
        editBuild.addComponent(type, motherboard);
    }

    /**
     * Parses the user input and executes the add command on the desired build for powersupply.
     *
     * @param editBuild The build to be edited.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power provided by the power supply to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddPowerSupply(Build editBuild, String type, String name, String price, String power) throws
            NegativeNumberException, NumberFormatException {
        PowerSupply powersupply = new PowerSupply(name, price, power);
        editBuild.addComponent(type, powersupply);
    }

    /**
     * Parses the user input and executes the add command on the desired build for gpu.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddGpu(Build editBuild, String line, String type, String name, String price, String power)
            throws NegativeNumberException, NumberFormatException {
        Gpu gpu = new Gpu(name, price, power, getParameter(line, 5),
                getParameter(line, 6));
        editBuild.addComponent(type, gpu);
    }

    /**
     * Parses the user input and executes the add command on the desired build for drive.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddDrive(Build editBuild, String line, String type, String name, String price, String power)
            throws NegativeNumberException, NumberFormatException {
        Drive drive = new Drive(name, price, power, getParameter(line, 5),
                getParameter(line, 6));
        editBuild.addComponent(type, drive);
    }

    /**
     * Parses the user input and executes the add command on the desired build for monitor.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddMonitor(Build editBuild, String line, String type, String name, String price, String power)
            throws NegativeNumberException, NumberFormatException {
        Monitor monitor = new Monitor(name, price, power, getParameter(line, 5),
                getParameter(line, 6), getParameter(line, 7));
        editBuild.addComponent(type, monitor);
    }

    /**
     * Parses the user input and executes the add command on the desired build for cooler.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddCooler(Build editBuild, String line, String type, String name, String price, String power)
            throws NegativeNumberException, NumberFormatException {
        Cooler cooler = new Cooler(name, price, power, getParameter(line, 5),
                getParameter(line, 6), getParameter(line, 7));
        editBuild.addComponent(type, cooler);
    }

    /**
     * Parses the user input and executes the add command on the desired build for case.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddCase(Build editBuild, String line, String type, String name, String price, String power)
            throws NegativeNumberException, NumberFormatException {
        Case case1 = new Case(name, price, power, getParameter(line, 5),
                getParameter(line, 6));
        editBuild.addComponent(type, case1);
    }

    /**
     * Parses the user input and executes the add command on the desired build for other components not given a
     * specific type.
     *
     * @param editBuild The build to be edited.
     * @param type The type of component to be added.
     * @param name The name of the component to be added.
     * @param price The price of the component to be added.
     * @param power The power consumption of the component to be added.
     * @throws NumberFormatException If the user input is not a number.
     * @throws NegativeNumberException If the user input is a negative number.
     */
    public void parseAddOther(Build editBuild, String type, String name, String price, String power) throws
            NegativeNumberException, NumberFormatException {
        Other other = new Other(name, price, power);
        editBuild.addComponent(type, other);
    }

    /**
     * Parses the user input and executes the delete command on the desired build by deleting the specified component.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @throws UnlistedComponentException If the component to be deleted is not in the build.
     */
    public void parseDelete(Build editBuild, String line) throws UnlistedComponentException {
        String name = getParameter(line, NAME_PARAMETER);
        String type = getParameter(line, TYPE_PARAMETER);
        if (!editBuild.doesComponentExist(name)) {
            throw new UnlistedComponentException();
        }
        editBuild.deleteComponent(type, name);
        try {
            storage.saveComponent(editBuild);
        } catch (Exception e) {
            System.out.println("Error saving build");
        }
        Ui.printEditLine();
        System.out.println("You have removed " + name);
        Ui.printEditLine();
    }

    /**
     * Parses the user input and executes the edit command on the desired build by listing the components of the build
     * along with their specifications.
     *
     * @param editBuild The build to be edited.
     */
    public void parseList(Build editBuild) {
        Ui.printEditLine();
        if (editBuild.getAllComponents().size() == 0) {
            System.out.println("You have no components");
            Ui.printEditLine();
            return;
        }
        System.out.println("Computer parts for " + buildName + ":");
        System.out.print(editBuild);
        Ui.printEditLine();
    }

    /**
     * Parses the user input and executes the edit command on the desired build. This method is called when the user
     * is in main mode and wants to edit a build.
     *
     * @param line The user input.
     * @throws UnlistedBuildException If the build to be edited is not listed.
     */
    public void parseEdit(String line) throws UnlistedBuildException {
        buildName = getParameter(line, TYPE_PARAMETER);
        if (!buildManager.doesBuildExist(buildName)) {
            throw new UnlistedBuildException();
        }
        Ui.printEditLine();
        System.out.println("You are now editing " + buildName);
        Ui.printEditLine();
    }

    /**
     * Parses the user input and executes the view command on the desired build by viewing the specifications of the
     * specified component in the build.
     *
     * @param editBuild The build to be edited.
     * @param line The user input.
     * @throws UnlistedBuildException If the build to be viewed is not listed.
     * @throws UnlistedComponentException If the component to be viewed is not listed.
     */
    public void parseView(Build editBuild, String line) throws UnlistedBuildException, UnlistedComponentException {
        String name = getParameter(line, NAME_PARAMETER);
        String type = getParameter(line, TYPE_PARAMETER);
        if (!editBuild.doesComponentExist(name)) {
            throw new UnlistedComponentException();
        }
        Ui.printEditLine();
        System.out.println(editBuild.getComponent(type, name).getDetails());
        Ui.printEditLine();
    }

    /**
     * Parses the user input and executes the checkout command on the desired build by listing the compatabilities
     * of the components in the build.
     *
     * @param editBuild The build to be edited.
     */
    public void parseCheck(Build editBuild) {
        Ui.printEditLine();
        System.out.println("Compatibility Info:");
        System.out.print(editBuild.getCompatibilityInfo());
        Ui.printEditLine();
    }

    /**
     * Parses the user input and executes the info command on the desired build by listing an overview of the
     * aggregated specifications of the build.
     *
     * @param editBuild The build to be edited.
     */
    public void parseInfo(Build editBuild) {
        Ui.printEditLine();
        System.out.println("Build Info:");
        System.out.print(editBuild.getBuildInfo());
        Ui.printEditLine();
    }

    /**
     * Parses the user input and executes the export command on the desired build by exporting the build to a text file.
     *
     * @param editBuild The build to be edited.
     */
    public void parseExport(Build editBuild) {
        Ui.printEditLine();
        System.out.println("Exporting build to text file...");
        try {
            ExportText.exportBuildText(editBuild);
        } catch (Exception e) {
            System.out.println("Error exporting build");
        }
        Ui.printEditLine();
    }
}

