package computercomponentchooser.export;

import computercomponentchooser.Build;
import computercomponentchooser.BuildManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class ExportText {
    private static final String FILE_DIRECTORY = "data";
    private static String EXPORT_TEXT_FILE_PATH = "data/exportAllBuildsText.txt";
    private static String COMPONENT_FILE_PATH;

    private final BuildManager buildManager;

    public ExportText(BuildManager buildManager) {
        this.buildManager = buildManager;
    }

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
