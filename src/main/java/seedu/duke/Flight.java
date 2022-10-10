package seedu.duke;

public class Flight {
    private static final String SPACER = "          ";
    private static final String LARGE_SPACER = "                    ";
    protected String flightNum;
    protected String airline;
    protected String destination;
    protected String departureTime;
    protected String gateNum;
    protected String terminal;
    protected String seat;

    public Flight(String flightNum, String airline, String destination, String departureTime,
                   String gateNum, String terminal, String seat) {
        this.flightNum = flightNum;
        this.airline = airline;
        this.destination = destination;
        this.departureTime = departureTime;
        this.gateNum = gateNum;
        this.terminal = terminal;
        this.seat = seat;
    }

    @Override
    public String toString() {
        System.out.format("%5s %25s %15s %15s %20s %15s %15s", this.flightNum,
                this.airline, this.destination, this.departureTime, this.gateNum, this.terminal, this.seat);
        return "";
    }

}
