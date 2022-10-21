import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static final String PATIENT_FILE_NAME = "/patient.txt";
    private static final String VISIT_FILE_NAME = "/visit.txt";
    private static final String PRESCRIPTION_FILE_NAME = "/prescription.txt";

    private File patientFile;
    private File visitFile;
    private File prescriptionFile;

    public Storage() {
        String filePath = retrieveFilePath();
        patientFile = new File(filePath + PATIENT_FILE_NAME);
        visitFile = new File(filePath + VISIT_FILE_NAME);
        prescriptionFile = new File(filePath + PRESCRIPTION_FILE_NAME);
    }

    private static String retrieveFilePath() {
        Path path = Paths.get("src", "main", "java", "data");
        boolean directoryExists = Files.exists(path);
        if (!directoryExists) {
            new File(path.toString()).mkdirs();
        }
        return path.toString();
    }

    private void createDataFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            UI.printErrorMessage("Error! Data files could not be created");
        }
    }

    public void loadData(PatientList patientList, VisitList visitList, PrescriptionList prescriptionList) {
        System.out.println("Loading existing data...");
        if (!patientFile.exists()) {
            createDataFile(patientFile);
        }
        loadPatients(patientList);

        if (!visitFile.exists()) {
            createDataFile(visitFile);
        }

        if (!prescriptionFile.exists()) {
            createDataFile(prescriptionFile);
        }
    }

    private void loadPatients(PatientList patientList) {
        Scanner fileScanner = setScanner(patientFile);
        if (fileScanner == null) {
            return;
        }
        while (fileScanner.hasNext()) {
            String input = fileScanner.nextLine().trim();
            String[] inputs = input.split(" \\| ", 4);
            System.out.println(inputs[0] + " " + inputs[1] + " " + inputs[2] + " " + inputs[3]);
            patientList.loadPatient(inputs[0], inputs[1], inputs[2], inputs[3]);
        }
    }

    private Scanner setScanner(File file) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            UI.printErrorMessage("Error! Data files could not be found!");
        }
        return fileScanner;
    }
}
