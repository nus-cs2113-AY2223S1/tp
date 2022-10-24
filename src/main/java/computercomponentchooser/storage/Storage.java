package computercomponentchooser.storage;

import computercomponentchooser.Build;
import computercomponentchooser.BuildManager;
import computercomponentchooser.Ui;
import computercomponentchooser.components.Component;
import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Gpu;
import computercomponentchooser.components.Motherboard;
import computercomponentchooser.components.Drive;
import computercomponentchooser.components.Memory;
import computercomponentchooser.components.PowerSupply;
import computercomponentchooser.components.Monitor;
import computercomponentchooser.components.Other;
import computercomponentchooser.components.Cooler;
import computercomponentchooser.components.Case;


import computercomponentchooser.exceptions.DuplicateBuildException;
import computercomponentchooser.exceptions.NegativeNumberException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.Scanner;

/**
 * Storage class that handles the saving and loading of the builds.
 */
public class Storage {

    /**
     * The relative path to the file directory that stores the builds files.
     */
    private static final String FILE_DIRECTORY = "data";

    /**
     * The relative path to the file that stores the build names.
     */
    private static String BUILD_FILE_PATH = "data/builds.txt";

    /**
     * The relative path to the file that stores the build parts.
     * The name of the file is the name of the build.
     */
    private static String COMPONENT_FILE_PATH;
    static final int TYPE_PARAMETER = 0;

    static final int NAME_PARAMETER = 1;

    static final int PRICE_PARAMETER = 2;

    static final int POWER_PARAMETER = 3;

    /**
     * Initializes the storage class.
     */
    public Storage() {

    }

    /**
     * Gets the desired text from a position of the line saved in the file delimited by /.
     *
     * @param line the line to be read
     * @param mode the desired position of the text to be retrieved
     * @return the desired text
     */
    private static String getParameter(String line, int mode) {
        String[] lineSplit = line.split("/");
        return lineSplit[mode];
    }

    /**
     * Saves all the existing builds in the build manager to the file.
     *
     * @param buildManager the build manager that contains all the builds
     * @throws IOException if there is an error in writing to the file
     */
    public void saveBuild(BuildManager buildManager) throws IOException {
        Path fileDirectory = Paths.get(FILE_DIRECTORY);
        if (!Files.exists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }
        Path file = Paths.get(BUILD_FILE_PATH);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }

        FileWriter fileWriter = new FileWriter(BUILD_FILE_PATH);
        Map<String, Build> builds = buildManager.getBuilds();
        for (String build : builds.keySet()) {
            fileWriter.write(build + "\n");
        }
        fileWriter.close();
    }

    /**
     * Deletes the text file of the build.
     *
     * @param name name of the build
     * @param buildManager buildManager that contains all the builds
     * @throws IOException if the file is not found
     */
    public void deleteBuild(String name, BuildManager buildManager) throws IOException {
        Path fileDirectory = Paths.get(FILE_DIRECTORY);
        if (!Files.exists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }
        COMPONENT_FILE_PATH = FILE_DIRECTORY + "/" + name + ".txt";
        Path file = Paths.get(COMPONENT_FILE_PATH);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        File componentFile = new File(COMPONENT_FILE_PATH);
        if (componentFile.exists()) {
            if (componentFile.delete()) { //Deletion of the whole file is not working
                System.out.println("Deleted the file: " + componentFile.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        }
        file = Paths.get(BUILD_FILE_PATH);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        FileWriter fileWriter = new FileWriter(BUILD_FILE_PATH);
        Map<String, Build> builds = buildManager.getBuilds();
        for (String build : builds.keySet()) {
            if (!build.equals(name)) {
                fileWriter.write(build + "\n");
            }
        }
        fileWriter.close();
    }

    /**
     * Saves all the components in the build to the file.
     * The name of the file is the name of the build.
     *
     * @param build the build that contains all the components
     * @throws IOException if the file cannot be created
     */
    public void saveComponent(Build build) throws IOException {
        COMPONENT_FILE_PATH = FILE_DIRECTORY + "/" + build.getName() + ".txt";
        Path fileDirectory = Paths.get(FILE_DIRECTORY);

        if (Files.notExists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }

        Path file = Paths.get(COMPONENT_FILE_PATH);

        if (Files.notExists(file)) {
            Files.createFile(file);
        }
        FileWriter fileWriter = new FileWriter(COMPONENT_FILE_PATH);
        for (Component component : build.getAllComponents()) {
            fileWriter.write(component.saveAsString() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Loads all the builds from the file that contains all the build names.
     *
     * @param buildManager the build manager that contains all the builds
     * @throws FileNotFoundException if the file is not found
     * @throws DuplicateBuildException if the build already exists
     */
    public void loadBuild(BuildManager buildManager) throws FileNotFoundException, DuplicateBuildException {

        File file = new File(BUILD_FILE_PATH);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String buildName = scanner.nextLine();
                Build build = new Build(buildName);
                buildManager.addBuild(build);
            }
            scanner.close();
        }
    }

    /**
     * Loads all the components from the file to their respective build.
     *
     * @param buildManager the build manager that contains all the builds
     * @throws FileNotFoundException if the file is not found
     */
    public void loadComponent(BuildManager buildManager) throws FileNotFoundException {
        File buildFile = new File(BUILD_FILE_PATH);
        if (buildFile.exists()) {
            for (String buildName : buildManager.getBuilds().keySet()) {
                File file = new File(FILE_DIRECTORY + "/" + buildName + ".txt");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Build build = buildManager.getBuilds().get(buildName);
                    String type = getParameter(line, TYPE_PARAMETER);
                    String name = getParameter(line, NAME_PARAMETER);
                    String price = getParameter(line, PRICE_PARAMETER);
                    String power = getParameter(line, POWER_PARAMETER);
                    switch (type) {
                    case "cpu":
                        loadCpu(line, name, price, power, build);
                        break;
                    case "memory":
                        loadMemory(line, name, price, power, build);
                        break;
                    case "motherboard":
                        loadMotherboard(line, name, price, power, build);
                        break;
                    case "powersupply":
                        loadPowerSupply(name, price, power, build);
                        break;
                    case "gpu":
                        loadGpu(line, name, price, power, build);
                        break;
                    case "drive":
                        loadDrive(line, name, price, power, build);
                        break;
                    case "monitor":
                        loadMonitor(line, name, price, power, build);
                        break;
                    case "cooler":
                        loadCooler(line, name, price, power, build);
                        break;
                    case "case":
                        loadCase(line, name, price, power, build);
                        break;
                    case "other":
                        loadOther(name, price, power, build);
                        break;
                    default:
                        break;
                    }
                }
                scanner.close();
            }
        }
    }

    /**
     * Loads the cpu from the file.
     *
     * @param line the line that contains the cpu
     * @param name the name of the cpu
     * @param price the price of the cpu
     * @param power the power of the cpu
     * @param build the build that the cpu is added to
     */
    private void loadCpu(String line, String name, String price, String power, Build build) {
        try {
            Cpu cpu = new Cpu(name, price, power, getParameter(line, 4), getParameter(line, 5));
            build.addComponent("cpu", cpu);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the memory from the file.
     *
     * @param line the line that contains the memory
     * @param name the name of the memory
     * @param price the price of the memory
     * @param power the power of the memory
     * @param build the build that the memory is added to
     */
    private static void loadMemory(String line, String name, String price, String power, Build build) {
        try {
            Memory memory = new Memory(name, price, power, getParameter(line, 4),
                    getParameter(line, 5));
            build.addComponent("memory", memory);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the motherboard from the file.
     *
     * @param line the line that contains the motherboard
     * @param name the name of the motherboard
     * @param price the price of the motherboard
     * @param power the power of the motherboard
     * @param build the build that the motherboard is added to
     */
    private static void loadMotherboard(String line, String name, String price, String power, Build build) {
        try {
            Motherboard motherboard = new Motherboard(name, price, power, getParameter(line, 4),
                    getParameter(line, 5), getParameter(line, 6), getParameter(line, 7));
            build.addComponent("motherboard", motherboard);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the power supply from the file.
     *
     * @param name the name of the power supply
     * @param price the price of the power supply
     * @param power the power of the power supply
     * @param build the build that the power supply is added to
     */
    private static void loadPowerSupply(String name, String price, String power, Build build) {
        try {
            PowerSupply powersupply = new PowerSupply(name, price, power);
            build.addComponent("powersupply", powersupply);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the gpu from the file.
     *
     * @param line the line that contains the gpu
     * @param name the name of the gpu
     * @param price the price of the gpu
     * @param power the power of the gpu
     * @param build the build that the gpu is added to
     */
    private static void loadGpu(String line, String name, String price, String power, Build build) {
        try {
            Gpu gpu = new Gpu(name, price, power, getParameter(line, 4),
                    getParameter(line, 5));
            build.addComponent("gpu", gpu);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the drive from the file.
     *
     * @param line the line that contains the drive
     * @param name the name of the drive
     * @param price the price of the drive
     * @param power the power of the drive
     * @param build the build that the drive is added to
     */
    private static void loadDrive(String line, String name, String price, String power, Build build) {
        try {
            Drive drive = new Drive(name, price, power, getParameter(line, 4),
                    getParameter(line, 5));
            build.addComponent("drive", drive);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the monitor from the file.
     *
     * @param line the line that contains the monitor
     * @param name the name of the monitor
     * @param price the price of the monitor
     * @param power the power of the monitor
     * @param build the build that the monitor is added to
     */
    private static void loadMonitor(String line, String name, String price, String power, Build build) {
        try {
            Monitor monitor = new Monitor(name, price, power, getParameter(line, 4),
                    getParameter(line, 5), getParameter(line, 6));
            build.addComponent("monitor", monitor);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the cooler from the file.
     *
     * @param line the line that contains the cooler
     * @param name the name of the cooler
     * @param price the price of the cooler
     * @param power the power of the cooler
     * @param build the build that the cooler is added to
     */
    private static void loadCooler(String line, String name, String price, String power, Build build) {
        try {
            Cooler cooler = new Cooler(name, price, power, getParameter(line, 4),
                    getParameter(line, 5), getParameter(line, 6));
            build.addComponent("cooler", cooler);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the case from the file.
     *
     * @param line the line that contains the case
     * @param name the name of the case
     * @param price the price of the case
     * @param power the power of the case
     * @param build the build that the case is added to
     */
    private static void loadCase(String line, String name, String price, String power, Build build) {
        try {
            Case case1 = new Case(name, price, power, getParameter(line, 4),
                    getParameter(line, 5));
            build.addComponent("case", case1);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }

    /**
     * Loads the other from the file.
     *
     * @param name the name of the other
     * @param price the price of the other
     * @param power the power of the other
     * @param build the build that the other is added to
     */
    private static void loadOther(String name, String price, String power, Build build) {
        try {
            Other other = new Other(name, price, power);
            build.addComponent("other", other);
        } catch (NegativeNumberException | NumberFormatException e) {
            Ui.printLine();
            System.out.println("Error when loading.");
            Ui.printLine();
        }
    }
}
