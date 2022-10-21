package computercomponentchooser.storage;

import computercomponentchooser.Build;
import computercomponentchooser.BuildManager;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.Scanner;

public class Storage {
    private static final String FILE_DIRECTORY = "data";
    private static String BUILD_FILE_PATH = "data/builds.txt";
    private static String COMPONENT_FILE_PATH;
    static final int TYPE_PARAMETER = 0;

    static final int NAME_PARAMETER = 1;

    static final int PRICE_PARAMETER = 2;

    static final int POWER_PARAMETER = 3;

    public Storage() {

    }

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
     * Suppose to delete the txt file of the build all together but it doesn't work.
     *
     * @param name name of the build
     * @param buildManager buildManager
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
     * Saves all the components in the build to the file with its name.
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
     * Loads all the builds from the file.
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
     * Loads all the components from the file to the respective build.
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
                        loadPowersupply(name, price, power, build);
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

    private static void loadOther(String name, String price, String power, Build build) {
        Other other = new Other(name, price, power);
        build.addComponent("other", other);
    }

    private static void loadCase(String line, String name, String price, String power, Build build) {
        Case case1 = new Case(name, price, power, getParameter(line, 4),
                getParameter(line, 5));
        build.addComponent("case", case1);
    }

    private static void loadCooler(String line, String name, String price, String power, Build build) {
        Cooler cooler = new Cooler(name, price, power, getParameter(line, 4),
                getParameter(line, 5), getParameter(line, 6));
        build.addComponent("cooler", cooler);
    }

    private static void loadMonitor(String line, String name, String price, String power, Build build) {
        Monitor monitor = new Monitor(name, price, power, getParameter(line, 4),
                getParameter(line, 5), getParameter(line, 6));
        build.addComponent("monitor", monitor);
    }

    private static void loadDrive(String line, String name, String price, String power, Build build) {
        Drive drive = new Drive(name, price, power, getParameter(line, 4),
                getParameter(line, 5));
        build.addComponent("drive", drive);
    }

    private static void loadGpu(String line, String name, String price, String power, Build build) {
        Gpu gpu = new Gpu(name, price, power, getParameter(line, 4),
                getParameter(line, 5));
        build.addComponent("gpu", gpu);
    }

    private static void loadPowersupply(String name, String price, String power, Build build) {
        PowerSupply powersupply = new PowerSupply(name, price, power);
        build.addComponent("powersupply", powersupply);
    }

    private static void loadMotherboard(String line, String name, String price, String power, Build build) {
        Motherboard motherboard = new Motherboard(name, price, power, getParameter(line, 4),
                getParameter(line, 5), getParameter(line, 6));
        build.addComponent("motherboard", motherboard);
    }

    private static void loadMemory(String line, String name, String price, String power, Build build) {
        Memory memory = new Memory(name, price, power, getParameter(line, 4),
                getParameter(line, 5));
        build.addComponent("memory", memory);
    }

    private void loadCpu(String line, String name, String price, String power, Build build) {
        Cpu cpu = new Cpu(name, price, power, getParameter(line, 4), getParameter(line, 5));
        build.addComponent("cpu", cpu);
    }
}
