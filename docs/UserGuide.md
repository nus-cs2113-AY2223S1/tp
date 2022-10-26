# User Guide

## Introduction

SkyControl is a software which optimizes the use of the Command Line Interface (CLI)to manage flights and passengers in an airport terminal.

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a flight: `flight add`](#adding-a-flight-flight-add)
    - [Delaying a flight: `delay`](#delaying-a-flight-delay)
    - [List Flights](#list-flights-flight-list)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 


### Adding a flight: `flight add`

Format: `flight add fn/FLIGHT_NUMBER a/AIRLINE d/DESTINATION dt/DEPARTURE_TIME gn/GATE_NUMBER c/CHECKIN_ROW_DOOR`

* The `FLIGHT_NUMBER` must consist of 2 alphabets followed by 1-4 digits.
* The `DEAPRTURE_TIME` must be in 24HR time format e.g. 1600.
* `GATE_NUMBER` must be a numerical number.

Example of usage:
```
flight add fn/sq712 a/singapore airlines d/bangkok dt/1600 gn/05 c/03-03
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Flight added!

flight add fn/KE644 a/KOREA AIR d/KOREA dt/0500 gn/22 c/10-04
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Flight added!
```
<br>

### Delaying a flight: `delay`

Format: `delay FLIGHT_NUMBER dt/NEW_DEPARTURE_TIME`

* `FLIGHT_NUMBER` must be an existing flight in the list.
* `NEW_DEPARTURE_TIME` is in 24HR time format and must be on the same day but later than the existing flight departure time.

Example of usage:
```
delay sq712 dt/1200
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Stop! Please enter a valid departure time for flight SQ712
Time must be later than 1600.

delay sq712 dt/1700
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Departure time of flight SQ712 is delayed from 1600 to 1700.

```
<br>

### List flights: `flight list`

Format: `flight list`

Example of usage:
```
flight list
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

+----------------------------------------------------------------------------------------------------------------------------------+
|                                                  FLIGHT DETAILS LOGBOOK FOR TERMINAL 1                                         |
+----------------------------------------------------------------------------------------------------------------------------------+
| FLIGHT NUM | DEPARTURE DATE |        AIRLINE         |      DESTINATION      | DEPARTURE TIME | GATE NUM |  CHECK-IN ROW/DOOR  |
+----------------------------------------------------------------------------------------------------------------------------------+
|      SQ712 |       23-10-22 |     SINGAPORE AIRLINES |               BANGKOK |           1600 |       05 |               03-03 |
+----------------------------------------------------------------------------------------------------------------------------------+
|      KE644 |       23-10-22 |              KOREA AIR |                 KOREA |           0500 |       22 |               10-04 |
+----------------------------------------------------------------------------------------------------------------------------------+
```


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

<br>

## Command Summary

{Give a 'cheat sheet' of commands here}

<br>
