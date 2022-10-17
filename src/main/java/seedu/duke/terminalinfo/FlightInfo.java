package seedu.duke.terminalinfo;

public class FlightInfo {
    protected String flightNum;
    protected String airline;
    protected String destination;
    protected String departureTime;
    protected String gateNum;
    protected String terminal;
    protected String checkLn;

    public FlightInfo(String flightNum, String airline, String destination, String departureTime,
                      String gateNum, String terminal, String checkIn) {
        this.flightNum = flightNum;
        this.airline = airline;
        this.destination = destination;
        this.departureTime = departureTime;
        this.gateNum = gateNum;
        this.terminal = terminal;
        this.checkLn = checkIn;
    }

    public String getFlightNum() {
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

    public String getTerminal() {
        return terminal;
    }

    public String getCheckLn() {
        return checkLn;
    }

    @Override
    public String toString() {
        System.out.format("| %10s | %27s |%13s | %14s | %8s | %8s | %18s |", this.flightNum,
                this.airline, this.destination, this.departureTime, this.gateNum, this.terminal, this.checkLn);
        return "";
    }
}
