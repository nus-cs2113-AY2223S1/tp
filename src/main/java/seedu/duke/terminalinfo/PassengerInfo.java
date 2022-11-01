package seedu.duke.terminalinfo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class PassengerInfo {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
    private static Date date = new Date();
    private static String dateToday = formatter.format(date);
    private static String departureDate = dateToday;
    private static final int MID_INDEX = 2;
    public static final int BOARDING_TIME_OFFSET = 45;
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

    public PassengerInfo() {

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

    public String convertToFileFormat() {
        String passengerDetails = (this.name + "|" + this.departureTime + "|" + this.flightNumber
                + "|" + this.gateNumber + "|" + this.boardingGroup
                + "|" + this.seatNumber + "|" + this.boardingTime + "\n");
        return passengerDetails;
    }

    public void setDepartureTime(String newDepartureTime) {
        this.departureTime = newDepartureTime;
    }

    //@@author ivanthengwr
    public String getFormattedBoardingTime(String reformatDepartureTime) {
        LocalTime flightDepartureTime = LocalTime.parse(reformatDepartureTime);
        LocalTime formatBoardingTime = flightDepartureTime
                .minus(Duration.ofMinutes(BOARDING_TIME_OFFSET));
        String boardingTime = reformatBoardingTime(formatBoardingTime);
        return boardingTime;
    }

    private String reformatBoardingTime(LocalTime boardingTime) {
        String reformatBoardingTime = boardingTime.toString();
        reformatBoardingTime = reformatBoardingTime.replace(":", "");
        return reformatBoardingTime;
    }

    public String reformatDepartureTime(String departureTime) {
        StringBuilder formatDepartureTime = new StringBuilder(departureTime);
        formatDepartureTime.insert(MID_INDEX,":");
        return formatDepartureTime.toString();
    }

    public void setBoardingTime(String newBoardingTime) {
        this.boardingTime = newBoardingTime;
    }
}
