package seedu.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.exception.InvalidFormatException;

public class CarparkTest {
    private static final String validCarparkId = "KU3";
    private static final String validArea = "test";
    private static final String validDevelopment = "BLK 331-341 UBI AVENUE 1";
    private static final String validLocation = "1.326421438 103.9024516";
    private static final String validAvailableLots = "205";
    private static final String validAllAvailableLots = "205 0 0";
    private static final String validIsFavourited = "false";
    private static final String validAgency = "HDB";
    private static final String validUpdateTime = "28-10-2022 15:19:40";
    private static final String invalidTime = "288-10-2022 15:19:40";
    private static final String invalidInt = "2.3";
    private static final String invalidTestString = "";
    private static final String invalidFormatString = "asd";
    private static final String invalidDelimitersString = "2 || Marina || Marina Square || 1.29115 103.85728 "
            + "|| 1756 || 1756 0 0 || false || LTA |||| 02-11-2022 23:20:37 ";
    private static final String CARPARK_PARAM_FORMAT_STRING = "%s || %s || %s || %s ||"
            + " %s || %s || %s || %s || %s \n";

    private static final String DEFAULT_CARPARK_STRING = String.format(CARPARK_PARAM_FORMAT_STRING, validCarparkId,
            validArea, validDevelopment, validLocation, validAvailableLots, validAllAvailableLots, validIsFavourited,
            validAgency, validUpdateTime);

    @Test
    void carparkConstructorTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        boolean checkCarparkId = carpark.getCarparkId().equals(validCarparkId);
        boolean checkArea = carpark.getArea().equals(validArea);
        boolean checkDevelopment = carpark.getDevelopment().equals(validDevelopment);
        boolean checkLocation = carpark.getLocation().equals(validLocation);
        boolean checkAvailableLots = Integer.toString(carpark.getAvailableLots()).equals(validAvailableLots);
        boolean checkAgency = carpark.getAgency().equals(validAgency);
        Assertions.assertTrue(checkCarparkId && checkArea && checkDevelopment && checkLocation
                && checkAvailableLots && checkAgency);
    }

    @Test
    void carparkConstructorInvalidFormatTest() {
        String invalidTimeString = String.format(CARPARK_PARAM_FORMAT_STRING, validCarparkId,
                validArea, validDevelopment, validLocation, validAvailableLots, validAllAvailableLots,
                validIsFavourited, validAgency, invalidTime);
        String invalidIntString = String.format(CARPARK_PARAM_FORMAT_STRING, validCarparkId,
                validArea, validDevelopment, validLocation, invalidInt, validAllAvailableLots,
                validIsFavourited, validAgency, validUpdateTime);
        String invalidBlankValueString = String.format(CARPARK_PARAM_FORMAT_STRING, invalidTestString,
                validArea, validDevelopment, validLocation, validAvailableLots, validAllAvailableLots,
                validIsFavourited, validAgency, validUpdateTime);
        Assertions.assertThrows(InvalidFormatException.class, () -> Carpark.parseCarpark(invalidTimeString));
        Assertions.assertThrows(InvalidFormatException.class, () -> Carpark.parseCarpark(invalidIntString));
        Assertions.assertThrows(InvalidFormatException.class, () -> Carpark.parseCarpark(invalidBlankValueString));
        Assertions.assertThrows(InvalidFormatException.class, () -> Carpark.parseCarpark(invalidFormatString));
        Assertions.assertThrows(InvalidFormatException.class, () -> Carpark.parseCarpark(invalidDelimitersString));
    }

    @Test
    void carparkStringFormatTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        Assertions.assertEquals(
                "CarparkID KU3 at BLK 331-341 UBI AVENUE 1: 205 lots available", carpark.toString());
    }

    @Test
    void carparkDetailViewStringFormatTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        Assertions.assertEquals(
                "===========================================\n"
                + "BLK 331-341 UBI AVENUE 1\n"
                + "===========================================\n"
                + "Carpark code: KU3\n"
                + "Favourited: No\n"
                + "Number of available lots (total): 205\n"
                + "  Cars: 205 lots\n"
                + "  Motorcycles: 0 lots\n"
                + "  Heavy Vehicles: 0 lots\n"
                + "Last Updated: 28-10-2022 15:19:40\n"
                + "===========================================", carpark.getDetailViewString());
    }

    @Test
    void carparkListViewStringFormatTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        Assertions.assertEquals("CarparkID @|yellow,bold KU3|@ at BLK 331-341 UBI AVENUE 1\n"
                        + "@|faint -->|@ @|yellow 205|@ available lots total",
                carpark.getListViewString());
    }

    @Test
    void carparkSaveStringTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        Assertions.assertEquals(
                DEFAULT_CARPARK_STRING, carpark.getSaveString());
    }

    @Test
    void carparkSetAvailableLotsTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        Assertions.assertThrows(InvalidFormatException.class, () -> carpark.setAvailableLots("-3"));
    }

    @Test
    void carparkInvalidCarparkIdTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        Assertions.assertThrows(InvalidFormatException.class, () -> carpark.setCarparkId("AR2L24"));
        Assertions.assertThrows(InvalidFormatException.class, () -> carpark.setCarparkId("AR*24"));
        Assertions.assertThrows(InvalidFormatException.class, () -> carpark.setCarparkId("24-"));
        Assertions.assertThrows(InvalidFormatException.class, () -> carpark.setCarparkId(""));
    }

    @Test
    void carparkValidCarparkIdTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        try {
            carpark.setCarparkId("L2a");
            carpark.setCarparkId("300000000000000000000000000004");
            carpark.setCarparkId("aaaaaaaaaaaaaaaaaaaaaassssssssss333333333");
            carpark.setCarparkId("A2");
        } catch (InvalidFormatException e) {
            Assertions.fail();
        }
    }

    @Test
    void addCarparkLotTypeTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        carpark.addCarparkLotType(LotType.MOTORCYCLE, 30);
        Assertions.assertEquals(235, carpark.getAvailableLots());
        carpark.addCarparkLotType(LotType.CAR, 200);
        Assertions.assertEquals(230, carpark.getAvailableLots());
    }

    @Test
    void carparkLotTypeParseTest() throws InvalidFormatException {
        Carpark carpark = Carpark.parseCarpark(DEFAULT_CARPARK_STRING);
        Assertions.assertThrows(InvalidFormatException.class, () -> carpark.setLotType("Invalid"));
    }
}
