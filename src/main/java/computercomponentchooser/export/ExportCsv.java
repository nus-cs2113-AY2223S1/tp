package computercomponentchooser.export;

import computercomponentchooser.Build;
import computercomponentchooser.BuildManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class ExportCsv {
    private static final String FILE_DIRECTORY = "data";
    private static String EXPORT_TEXT_FILE_PATH = "data/exportAllBuildsCSV.csv";

    private final BuildManager buildManager;

    public ExportCsv(BuildManager buildManager) {
        this.buildManager = buildManager;
    }

    public static void exportAllBuildsCSV(BuildManager buildManager) throws IOException {
        Path fileDirectory = Paths.get(FILE_DIRECTORY);
        if (!Files.exists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }
        Path file = Paths.get(EXPORT_TEXT_FILE_PATH);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        FileWriter fileWriter = new FileWriter(EXPORT_TEXT_FILE_PATH);
        fileWriter.write("Build Name, Component Name, Price, Power\n");
        for (Build build : buildManager.getBuilds().values()) {
            fileWriter.write(build.toCsv());
        }
        fileWriter.close();
    }
}
