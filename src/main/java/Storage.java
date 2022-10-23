import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
        loadVisits(visitList);

        if (!prescriptionFile.exists()) {
            createDataFile(prescriptionFile);
        }
        loadPrescriptions(prescriptionList);
    }

    private void loadPatients(PatientList patientList) {
        Scanner fileScanner = setScanner(patientFile);
        if (fileScanner == null) {
            return;
        }
        while (fileScanner.hasNext()) {
            String input = fileScanner.nextLine().trim();
            String[] inputs = input.split(" \\| ", 4);
            patientList.loadPatient(inputs[0], inputs[1], inputs[2], inputs[3]);
        }
    }

    private void loadVisits(VisitList visitList) {
        Scanner fileScanner = setScanner(visitFile);
        if (fileScanner == null) {
            return;
        }
        while (fileScanner.hasNext()) {
            String input = fileScanner.nextLine().trim();
            String[] inputs = input.split(" \\| ", 4);
            visitList.loadVisit(inputs[0], inputs[2], inputs[3], inputs[1]);
        }
    }

    private void loadPrescriptions(PrescriptionList prescriptionList) {
        Scanner fileScanner = setScanner(prescriptionFile);
        if (fileScanner == null) {
            return;
        }
        while (fileScanner.hasNext()) {
            String input =  fileScanner.nextLine().trim();
            String[] inputs = input.split(" \\| ", 5);
            boolean active = (inputs[4].equals("T"));
            prescriptionList.loadPrescription(inputs[0], inputs[1], inputs[2], inputs[3], active);
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

    public void savePatientData(PatientList patientList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(patientFile);
        } catch (IOException e) {
            UI.printErrorMessage("Error! File writer could not be created");
            return;
        }

        try {
            for (Patient patient : patientList.getPatients()) {
                logPatientIntoDataFile(fileWriter, patient);
            }
        } catch (IOException e) {
            UI.printErrorMessage("Error! Data could not be written into data file!");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                UI.printErrorMessage("Error! File writer could not be closed!");
                return;
            }
        }
    }

    public void savePrescriptionData(PrescriptionList prescriptionList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(prescriptionFile);
        } catch (IOException e) {
            UI.printErrorMessage("Error! File writer could not be created");
            return;
        }

        try {
            for (Prescription prescription: prescriptionList.getPrescriptions()) {
                logPrescriptionIntoDataFile(fileWriter, prescription);
            }
        } catch (IOException e) {
            UI.printErrorMessage("Error! Data could not be written into data file!");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                UI.printErrorMessage("Error! File writer could not be closed!");
                return;
            }
        }
    }

    public void saveVisitData(VisitList visitList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(visitFile);
        } catch (IOException e) {
            UI.printErrorMessage("Error! File writer could not be created");
            return;
        }

        try {
            for (Visit visit: visitList.getVisits()) {
                logVisitIntoDataFile(fileWriter, visit);
            }
        } catch (IOException e) {
            UI.printErrorMessage("Error! Data could not be written into data file!");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                UI.printErrorMessage("Error! File writer could not be closed!");
                return;
            }
        }
    }

    private void logVisitIntoDataFile(FileWriter fileWriter, Visit visit) throws IOException {
        fileWriter.write(visit.getId());
        fileWriter.write(" | ");
        fileWriter.write(visit.getReason());
        fileWriter.write(" | ");
        fileWriter.write(visit.getDateOfVisit());
        fileWriter.write(" | ");
        fileWriter.write(visit.getTimeOfVisit());
        fileWriter.write(System.lineSeparator());
    }

    private void logPrescriptionIntoDataFile(FileWriter fileWriter, Prescription prescription) throws IOException {
        fileWriter.write(prescription.getPatientId());
        fileWriter.write(" | ");
        fileWriter.write(prescription.getMedicine());
        fileWriter.write(" | ");
        fileWriter.write(prescription.getDosage());
        fileWriter.write(" | ");
        fileWriter.write(prescription.getTimeInterval());
        fileWriter.write(" | ");
        String activeStatus = prescription.isActive() ? "T" : "F";
        fileWriter.write(activeStatus);
        fileWriter.write(System.lineSeparator());
    }

    private void logPatientIntoDataFile(FileWriter fileWriter, Patient patient) throws IOException {
        fileWriter.write(patient.getName());
        fileWriter.write(" | ");
        fileWriter.write(patient.getBirthDate());
        fileWriter.write(" | ");
        fileWriter.write(patient.getGender());
        fileWriter.write(" | ");
        fileWriter.write(patient.getId());
        fileWriter.write(System.lineSeparator());
    }
}
