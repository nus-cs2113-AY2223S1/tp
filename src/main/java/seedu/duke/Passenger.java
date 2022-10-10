package seedu.duke;

public class Passenger {
    private String name;
    private String departureDate;
    private String departureTime;
    private String flightNumber;
    private String gateNumber;
    private int boardingGroup;
    private String seatNumber;
    private String boardingTime;

    public Passenger(String name, String departureDate, String departureTime, String flightNumber,
                     String gateNumber, int boardingGroup, String seatNumber, String boardingTime) {
        this.name = name;
        this.departureDate = departureDate;
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

    public String getDepartureDate() {
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
}
