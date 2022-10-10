package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class PassengerList {
    private static final String NAME_DELIMITER = "n/";
    private static final String DEPARTURE_DATE_DELIMITER = " dod/";
    private static final String DEPARTURE_TIME_DELIMITER = " dt/";
    private static final String FLIGHT_NUMBER_DELIMITER = " fn/";
    private static final String GATE_NUMBER_DELIMITER = " gn/";
    private static final String BOARDING_GROUP_DELIMITER = " bg/";
    private static final String SEAT_NUMBER_DELIMITER = " sn/";
    private static final String BOARDING_TIME_DELIMITER = " bt/";

    private ArrayList<Passenger> passengers;

    public PassengerList() {
        passengers = new ArrayList<Passenger>();
    }

    public void addPassenger(String passengerDetails) {
        String name = getPassengerName(passengerDetails);
        String departureDate = getDepartureDate(passengerDetails);
        String departureTime = getDepartureTime(passengerDetails);
        String flightNumber = getFlightNumber(passengerDetails);
        String gateNumber = getGateNumber(passengerDetails);
        int boardingGroup = getBoardingGroup(passengerDetails);
        String seatNumber = getSeatNumber(passengerDetails);
        String boardingTime = getBoardingTime(passengerDetails);
        Passenger passenger = new Passenger(name, departureDate, departureTime, flightNumber, gateNumber,
                boardingGroup, seatNumber, boardingTime);
        passengers.add(passenger);
        Ui.showAddedPassenger(passenger);
    }

    private String getPassengerName(String passengerDetails) {
        String name = getSubstringBetweenDelimiters(passengerDetails, NAME_DELIMITER, DEPARTURE_DATE_DELIMITER);
        return name;
    }

    private String getDepartureDate(String passengerDetails) {
        String departureDate = getSubstringBetweenDelimiters(passengerDetails,
                DEPARTURE_DATE_DELIMITER, DEPARTURE_TIME_DELIMITER);
        return departureDate;
    }

    private String getDepartureTime(String passengerDetails) {
        String departureTime = getSubstringBetweenDelimiters(passengerDetails,
                DEPARTURE_TIME_DELIMITER, FLIGHT_NUMBER_DELIMITER);
        return departureTime;
    }

    private String getFlightNumber(String passengerDetails) {
        String flightNumber = getSubstringBetweenDelimiters(passengerDetails,
                FLIGHT_NUMBER_DELIMITER, GATE_NUMBER_DELIMITER);
        return flightNumber;
    }

    private String getGateNumber(String passengerDetails) {
        String gateNumber = getSubstringBetweenDelimiters(passengerDetails,
                GATE_NUMBER_DELIMITER, BOARDING_GROUP_DELIMITER);
        return gateNumber;
    }

    private int getBoardingGroup(String passengerDetails) {
        String boardingGroup = getSubstringBetweenDelimiters(passengerDetails,
                BOARDING_GROUP_DELIMITER, SEAT_NUMBER_DELIMITER);
        return Integer.parseInt(boardingGroup);
    }

    private String getSeatNumber(String passengerDetails) {
        String seatNumber = getSubstringBetweenDelimiters(passengerDetails,
                SEAT_NUMBER_DELIMITER, BOARDING_TIME_DELIMITER);
        return seatNumber;
    }

    private String getBoardingTime(String passengerDetails) {
        int startIndex = passengerDetails.indexOf(BOARDING_TIME_DELIMITER) + BOARDING_TIME_DELIMITER.length();
        String seatNumber = passengerDetails.substring(startIndex);
        return seatNumber;
    }

    private String getSubstringBetweenDelimiters(String inputString, String startDelimiter, String endDelimiter) {
        int startIndex = inputString.indexOf(startDelimiter) + startDelimiter.length();
        int endIndex = inputString.lastIndexOf(endDelimiter);
        String outputString = inputString.substring(startIndex, endIndex);
        return outputString.toUpperCase();
    }

}
