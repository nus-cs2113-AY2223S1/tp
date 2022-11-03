package seedu.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.exception.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CarparkListTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");
    private final String validSaveString =
            "1 || Marina || Suntec City || 1.29375 103.85718 || 2822 || 2822 0 0 || false || LTA || 03-11-2022 "
                   + "00:56:14 \n2 || Marina || Marina Square || 1.29115 103.85728 || 1801 || 1801 0 0 || false || "
                   + "LTA || 03-11-2022 00:56:14 \n3 || Marina || Raffles City || 1.29382 103.85319 || 896 || 896 0 0"
                   + " || false || LTA || 03-11-2022 00:47:22 \n4 || Marina || The Esplanade || 1.29011 103.85561 || "
                   + "756 || 756 0 0 || false || LTA || 03-11-2022 00:56:14 \n5 || Marina || Millenia Singapore || 1"
                   + ".29251 103.86009 || 1269 || 1269 0 0 || false || LTA || 03-11-2022 00:56:14 \n";

    private final String validNewSaveString = "5 || Marina || Millenia Singapore || 1.29251 103.86009 || 2200 || 2200" +
            " 0 0 || false || LTA || 03-11-2022 00:56:14 \n6 || Marina || Singapore Flyer || 1.28944 103.86311 || " +
            "286 || 286 0 0 || false || LTA || 02-11-2022 23:20:37 \n7 || Orchard || Orchard Point || 1.30135 103" +
            ".84061 || 187 || 187 0 0 || false || LTA || 03-11-2022 00:47:22 ";

    @Test
    void findCarparkValidStringTest() throws NoFileFoundException, NoCarparkFoundException, FileWriteException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals(carparkList.findCarpark("3").toString(),
                "CarparkID 3 at Raffles City: 522 lots available");
    }

    @Test
    void findCarparkNoneFoundTest() throws NoFileFoundException, FileWriteException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertThrows(NoCarparkFoundException.class, () -> carparkList.findCarpark("50"));
    }

    @Test
    void toStringTest() throws NoFileFoundException, FileWriteException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at Suntec City\n"
                        + "@|faint -->|@ @|yellow 1882|@ available lots total\n"
                        + "CarparkID @|yellow,bold 2|@ at Marina Square\n"
                        + "@|faint -->|@ @|yellow 1003|@ available lots total\n"
                        + "CarparkID @|yellow,bold 3|@ at Raffles City\n@|faint -->|@ @|yellow 522|@ "
                        + "available lots total\n",
                carparkList.toString());
    }

    @Test
    void carparkListStringLoadTest() throws DuplicateCarparkIdException, InvalidFormatException,
            NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validSaveString);
        Carpark carpark = carparkList.findCarpark("5");
        Assertions.assertEquals("Millenia Singapore", carpark.getDevelopment());
    }

    @Test
    void carparkListSaveStringTest() throws DuplicateCarparkIdException, InvalidFormatException {
        CarparkList carparkList = new CarparkList(validSaveString);
        Assertions.assertEquals(validSaveString, carparkList.getSaveString());
    }

    @Test
    void carparkListUpdateTest() throws DuplicateCarparkIdException, InvalidFormatException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validSaveString);
        CarparkList carparkListNew = new CarparkList(validNewSaveString);
        carparkList.update(carparkListNew);
        Carpark newCarpark = carparkList.findCarpark("7");
        Carpark oldCarparkUpdated = carparkList.findCarpark("5");
        Carpark oldCarparkNotUpdated = carparkList.findCarpark("2");

        Assertions.assertEquals(187, newCarpark.getAvailableLots());
        Assertions.assertEquals(2200, oldCarparkUpdated.getAvailableLots());
        Assertions.assertEquals(1801, oldCarparkNotUpdated.getAvailableLots());
    }
}
