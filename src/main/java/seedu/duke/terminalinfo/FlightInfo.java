package seedu.duke.terminalinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightInfo {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
    private static Date date = new Date();

    private static String dateToday = formatter.format(date);
    private static String departureDate = dateToday;

    protected String flightNum;
    protected String airline;
    protected String destination;
    protected String departureTime;
    protected String gateNum;
    protected String checkLn;

    public FlightInfo(String flightNum, String airline, String destination, String departureTime,
                      String gateNum, String checkIn) {
        this.flightNum = flightNum;
        this.airline = airline;
        this.destination = destination;
        this.departureTime = departureTime;
        this.gateNum = gateNum;
        this.checkLn = checkIn;
    }

    //@@author Franky4566
    public String getFlightNumber() {
        return flightNum;
    }

    public String getAirline() {
        return airline;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getGateNum() {
        return gateNum;
    }

    public String getCheckLn() {
        return checkLn;
    }

    @Override
    public String toString() {
        System.out.format("| %10s | %14s | %22s |%22s | %14s | %8s | %19s |", this.flightNum, this.departureDate,
                this.airline, this.destination, this.departureTime, this.gateNum, this.checkLn);
        return "";
    }

    public void setDepartureTime(String newDepartureTime) {
        this.departureTime = newDepartureTime;
    }

    //@@author shengiv
    public void setFlightNum(String newFlightNum) {
        this.flightNum = newFlightNum;
    }

    public void setGateNum(String newGateNum) {
        this.gateNum = newGateNum;
    }

    public String convertToFileFormat() {
        String flightDetails = (this.flightNum + "|" + this.airline + "|" + this.destination
                + "|" + this.departureTime + "|" + this.gateNum + "|" + this.checkLn + "\n");
        return flightDetails;
    }
}
