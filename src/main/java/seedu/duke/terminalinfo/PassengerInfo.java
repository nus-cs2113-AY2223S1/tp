package seedu.duke.terminalinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PassengerInfo {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
    private static Date date = new Date();
    private static String dateToday = formatter.format(date);
    private static String departureDate = dateToday;
    private String name;
    private String departureTime;
    private String flightNumber;
    private String gateNumber;
    private int boardingGroup;
    private String seatNumber;
    private String boardingTime;

    public PassengerInfo(String name, String departureTime, String flightNumber,
                         String gateNumber, int boardingGroup, String seatNumber, String boardingTime) {
        this.name = name;
        this.departureTime = departureTime;
        this.flightNumber = flightNumber;
        this.gateNumber = gateNumber;
        this.boardingGroup = boardingGroup;
        this.seatNumber = seatNumber;
        this.boardingTime = boardingTime;
    }

    public String getName() {
        return name;
    }

    public static String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public int getBoardingGroup() {
        return boardingGroup;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public void setFlightNumber(String newFlightNum) {
        this.flightNumber = newFlightNum;
    }

    public void setGateNumber(String newGateNum) {
        this.gateNumber = newGateNum;
    }
}
