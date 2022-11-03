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
            UI.printErrorMessage(UI.FILE_CREATION_ERROR_MESSAGE);
        }
    }

    public void loadData(PatientList patientList, VisitList visitList, PrescriptionList prescriptionList) {
        if (!patientFile.exists()) {
            createDataFile(patientFile);
        }
        loadPatients(patientList);

        if (!visitFile.exists()) {
            createDataFile(visitFile);
        }
        loadVisits(visitList, patientList);

        if (!prescriptionFile.exists()) {
            createDataFile(prescriptionFile);
        }
        loadPrescriptions(prescriptionList, patientList);
        savePatientData(patientList);
        saveVisitData(visitList);
        savePrescriptionData(prescriptionList);
    }


    private void loadPatients(PatientList patientList) {
        Scanner fileScanner = setScanner(patientFile);
        if (fileScanner == null) {
            return;
        }
        boolean loaded = false;
        boolean invalid = false;
        while (fileScanner.hasNext()) {
            String input = fileScanner.nextLine().trim();
            String[] inputs = input.split(" \\| ", 4);
            if (Parser.isPatientInputValid(inputs)) {
                patientList.loadPatient(inputs[0], inputs[1], inputs[2], inputs[3]);
                loaded = true;
            } else {
                invalid = true;
            }
        }
        if (invalid) {
            System.out.println(UI.INVALID_PATIENT_DATA_MESSAGE);
        }
        if (loaded) {
            System.out.println(UI.PATIENT_LOADED_MESSAGE);
        }
    }

    private void loadVisits(VisitList visitList, PatientList patientList) {
        Scanner fileScanner = setScanner(visitFile);
        if (fileScanner == null) {
            return;
        }
        boolean loaded = false;
        boolean invalid = false;
        while (fileScanner.hasNext()) {
            String input = fileScanner.nextLine().trim();
            String[] inputs = input.split(" \\| ", 4);
            if (Parser.isVisitInputValid(inputs, patientList)) {
                visitList.loadVisit(inputs[0], inputs[2], inputs[3], inputs[1]);
                loaded = true;
            } else {
                invalid = true;
            }
        }
        if (invalid) {
            System.out.println(UI.INVALID_VISIT_DATA_MESSAGE);
        }
        if (loaded) {
            System.out.println(UI.VISIT_LOADED_MESSAGE);
        }
    }

    private void loadPrescriptions(PrescriptionList prescriptionList, PatientList patientList) {
        Scanner fileScanner = setScanner(prescriptionFile);
        if (fileScanner == null) {
            return;
        }
        boolean loaded = false;
        boolean invalid = false;
        while (fileScanner.hasNext()) {
            String input =  fileScanner.nextLine().trim();
            String[] inputs = input.split(" \\| ", 5);
            if (Parser.isPrescriptionInputValid(inputs, patientList)) {
                boolean active = (inputs[4].equals("T"));
                prescriptionList.loadPrescription(inputs[0], inputs[1], inputs[2], inputs[3], active);
                loaded = true;
            } else {
                invalid = true;
            }
        }
        if (invalid) {
            System.out.println(UI.INVALID_PRESCRIPTION_DATA_MESSAGE);
        }
        if (loaded) {
            System.out.println(UI.PRESCRIPTION_LOADED_MESSAGE);
        }
    }

    private Scanner setScanner(File file) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            UI.printErrorMessage(UI.MISSING_DATA_FILES_ERROR_MESSAGE);
        }
        return fileScanner;
    }

    public void savePatientData(PatientList patientList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(patientFile);
        } catch (IOException e) {
            UI.printErrorMessage(UI.FILE_WRITER_CREATION_ERROR_MESSAGE);
            return;
        }

        try {
            logPatients(patientList, fileWriter);
        } catch (IOException e) {
            UI.printErrorMessage(UI.WRITE_ERROR_MESSAGE);
        } finally {
            closeFileWriter(fileWriter);
        }
    }

    private void logPatients(PatientList patientList, FileWriter fileWriter) throws IOException {
        for (Patient patient : patientList.getPatients()) {
            logPatientIntoDataFile(fileWriter, patient);
        }
    }

    private static void closeFileWriter(FileWriter fileWriter) {
        try {
            fileWriter.close();
        } catch (IOException e) {
            UI.printErrorMessage(UI.FILE_WRITER_CLOSURE_ERROR_MESSAGE);
        }
    }

    public void savePrescriptionData(PrescriptionList prescriptionList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(prescriptionFile);
        } catch (IOException e) {
            UI.printErrorMessage(UI.FILE_WRITER_CREATION_ERROR_MESSAGE);
            return;
        }

        try {
            logPrescriptions(prescriptionList, fileWriter);
        } catch (IOException e) {
            UI.printErrorMessage(UI.WRITE_ERROR_MESSAGE);
        } finally {
            closeFileWriter(fileWriter);
        }
    }

    private void logPrescriptions(PrescriptionList prescriptionList, FileWriter fileWriter) throws IOException {
        for (Prescription prescription: prescriptionList.getPrescriptions()) {
            logPrescriptionIntoDataFile(fileWriter, prescription);
        }
    }

    public void saveVisitData(VisitList visitList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(visitFile);
        } catch (IOException e) {
            UI.printErrorMessage(UI.FILE_WRITER_CREATION_ERROR_MESSAGE);
            return;
        }

        try {
            logVisits(visitList, fileWriter);
        } catch (IOException e) {
            UI.printErrorMessage(UI.WRITE_ERROR_MESSAGE);
        } finally {
            closeFileWriter(fileWriter);
        }
    }

    private void logVisits(VisitList visitList, FileWriter fileWriter) throws IOException {
        for (Visit visit: visitList.getVisits()) {
            logVisitIntoDataFile(fileWriter, visit);
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
