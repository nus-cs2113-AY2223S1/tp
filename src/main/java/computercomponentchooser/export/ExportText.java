package computercomponentchooser.export;

import computercomponentchooser.Build;
import computercomponentchooser.BuildManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Exports the builds to a text file.
 */
public class ExportText {

    /**
     * The file directory that the files are stored in.
     */
    private static final String FILE_DIRECTORY = "data";

    /**
     * The file name of the text file that contains all the builds.
     */
    private static String EXPORT_TEXT_FILE_PATH = "data/exportAllBuildsText.txt";
    private static String COMPONENT_FILE_PATH;

    private final BuildManager buildManager;

    /**
     * Initializes a new ExportText object.
     *
     * @param buildManager The buildManager to be used.
     */
    public ExportText(BuildManager buildManager) {
        this.buildManager = buildManager;
    }

    /**
     * Exports all the builds to a text file.
     *
     * @param buildManager The buildManager to be used.
     * @throws IOException If there is an error in writing to the file.
     */
    public static void exportAllBuildsText(BuildManager buildManager) throws IOException {
        Path fileDirectory = Paths.get(FILE_DIRECTORY);
        if (!Files.exists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }
        Path file = Paths.get(EXPORT_TEXT_FILE_PATH);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        FileWriter fileWriter = new FileWriter(EXPORT_TEXT_FILE_PATH);
        for (Build build : buildManager.getBuilds().values()) {
            fileWriter.write("____________________________________________________________\n");
            fileWriter.write("Info: \n" + build.getBuildInfo() + "\n");
            fileWriter.write("Components list: \n" + build.toString());
            fileWriter.write("____________________________________________________________");
        }
        fileWriter.close();
    }

    /**
     * Export a single build to a text file where the name of the file is 'export_' concatenated with the name of the
     * build.
     *
     * @param build The build to be exported.
     * @throws IOException If there is an error in writing to the file.
     */
    public static void exportBuildText(Build build) throws IOException {
        Path fileDirectory = Paths.get(FILE_DIRECTORY);
        if (!Files.exists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }
        String fileName = "data/export_" + build.getName() + ".txt";
        Path file = Paths.get(fileName);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write("____________________________________________________________\n");
        fileWriter.write("Info: \n" + build.getBuildInfo() + "\n");
        fileWriter.write("Components list: \n" + build.toString());
        fileWriter.write("____________________________________________________________");
        fileWriter.close();
    }
}
