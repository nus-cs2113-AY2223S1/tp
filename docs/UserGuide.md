# User Guide

## Introduction

SkyControl is a software which optimizes the use of the Command Line Interface (CLI)to manage flights and passengers in an airport terminal.

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a flight: `flight add`](#adding-a-flight-flight-add)
    - [List Flights](#list-flights-flight-list)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 


### Adding a flight: `flight add`

Format: `flight add fn/FLIGHT_NUMBER a/AIRLINE d/DESTINATION t/DEPARTURE_TIME gn/GATE_NUMBER c/CHECKIN_ROW_DOOR`

* The `FLIGHT_NUMBER` must consist of 2 alphabets followed by 1-4 digits.
* The `DEAPRTURE_TIME` must be in 24HR time format e.g. 1600.
* `GATE_NUMBER` must be a numerical number.

Example of usage:
```
flight add fn/sq712 a/singapore airlines d/bangkok t/1600 gn/05 c/03-03
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Flight added!

flight add fn/KE644 a/KOREA AIR d/KOREA t/0500 gn/22 c/10-04
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Flight added!
```

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
