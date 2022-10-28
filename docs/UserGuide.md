# User Guide

## Table of Contents
* [Introduction](#Introduction)
* [Getting Started](#getting-started)
* [SkyControl's Features](#SkyControl's-Features)
  * [Add a passenger detail: `passenger add`](#Add-a-passenger-detail-passenger-add)
  * [Delete a passenger detail: `passenger delete`](#Delete-a-passenger-detail-passenger-delete)
  * [Display passenger detail logbook: `passenger list`](#Display-passenger-detail-logbook-passenger-list)
  * [Add a flight detail: `flight add`](#add-a-flight-detail-flight-add)
  * [Delay a flight: `delay`](#delay-a-flight-delay)
  * [Display flight detail logbook: `flight list`](#display-flight-detail-logbook-flight-list)
  * [Command Summary](#Command-Summary)
* [Frequently Asked Quesitions](#Frequently-Asked-Questions)

---

## Introduction

SkyControl is a software which optimizes the use of the Command Line Interface(CLI) to manage flights and passengers in an airport terminal. 

---

## Getting Started

1. Please ensure that you have **Java 11 or above** present in your computer
2. Proceed to download the latest version of `SkyControl.jar` from
[here](https://github.com/AY2223S1-CS2113-T17-1/tp/releases).
3. Move the file to your desired folder that you have designated as the **main folder** for SkyControl.
4. Open a terminal and type java -jar SkyControl.jar (include the entire filepath for the jar file)
5. Once the program has been successfully initialised, you should see SkyControl greetings as seen below.

````
  Welcome to
   _____ _             _____            _             _
  / ____| |           / ____|          | |           | |
 | (___ | | ___   _  | |     ___  _ __ | |_ _ __ ___ | |
  \___ \| |/ / | | | | |    / _ \| '_ \| __| '__/ _ \| |
  ____) |   <| |_| | | |___| (_) | | | | |_| | | (_) | |
 |_____/|_|\_\\__, |  \_____\___/|_| |_|\__|_|  \___/|_|
               __/ |
              |___/
````
6. After SkyControl has been booted up successfully, enter a command below the welcome display
and hit the <kbd>Enter</kbd> button to execute it. Reflected below is an example.  

**Input Command**  

`bye`  
  
**Output**  
  

````
Welcome to
   _____ _             _____            _             _
  / ____| |           / ____|          | |           | |
 | (___ | | ___   _  | |     ___  _ __ | |_ _ __ ___ | |
  \___ \| |/ / | | | | |    / _ \| '_ \| __| '__/ _ \| |
  ____) |   <| |_| | | |___| (_) | | | | |_| | | (_) | |
 |_____/|_|\_\\__, |  \_____\___/|_| |_|\__|_|  \___/|_|
               __/ |
              |___/

bye
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Thank you, come again! :)
````

---

## SkyControl's Features 

**Take Note:**

+ All commands entered into the terminal are **not case-sensitive.**  
i.e. `Passenger add PASSENGER_DETAIL` or `Passenger Add PASSENGER_DETAIL` are acceptable commands
+ Words in `UPPER_CASE` represent a single or multiple parameters for the command. 
i.e. `flight add FLIGHT_DETAIL`  

**NOTE:** The exact format to be followed for each parameter can be found in the 
[FAQ](##Frequently-Asked-Questions) section.

---
## Add a passenger detail: `passenger add`
**Function**: Adds the passenger to the passenger list, given that the flight number of the 
passenger already exists in the flight list and the seat number is not already occupied by another
passenger in the passenger list.
 
**Format**: `passenger add n/PASSENGER_NAME dt/DEPARTURE_TIME fn/FLIGHT_NUMBER gn/GATE_NUMBER 
bg/BOARDING_GROUP sn/SEAT_NUMBER bt/BOARDING_TIME`

Example:  

**Input**

`passenger add n/Ivan Theng dt/2145 fn/sq832 gn/05 bg/01 sn/17d bt/2100`

**Output**
````
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Passenger IVAN THENG of SQ832 17D has been added.
````

---

## Delete a passenger detail: `passenger delete`
**Explanation**: As the name of the feature suggests, executing this command should
delete the detail of a single passenger. It is used to remove a passenger from the passenger list.  


**Function**: Deletes a passenger from the current passenger list.

**Usage**: `passenger delete n/PASSENGER_NAME fn/FLIGHT_NUMBER sn/SEAT_NUMBER dt/DEPARTURE_TIME`

Example:

**Input**

`passenger delete n/Ivan Theng fn/sq832 sn/17d dt/2145`

**Output**
````
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Passenger IVAN THENG from SQ832 of seat number 17D have been
deleted from the passenger list.
0 passenger(s) left on the passenger list.
````  
---

## Display passenger detail logbook: `passenger list`
**Explanation**: As the name of the feature suggests, execute this command should
list the current passenger(s) that is/are present in the logbook.


**Function**: Displays a list in table form of all the current passengers that are listed in the logbook.
**Usage**: `passenger list`

Example:  
  

**Input**  
  
`passenger list`  
  
**Output**  
  
_If passenger details logbook has/have existing passenger(s)_.
````
+------------------------------------------------------------------------------------------------------------------------------+
|                                                  PASSENGER DETAILS LOGBOOK                                                   |
+------------------------------------------------------------------------------------------------------------------------------+
|           NAME           | DEPARTURE DATE | DEPARTURE TIME | FLIGHT NUM | GATE NUM | BOARDING GRP | SEAT NUM | BOARDING TIME |
+------------------------------------------------------------------------------------------------------------------------------+
| IVAN THENG               | 26-10-22       | 2145           | SQ832      | 05       | 1            | 17D      | 2100          |
+------------------------------------------------------------------------------------------------------------------------------+
````

_If passenger details logbook is empty_.
````
+------------------------------------------------------------------------------------------------------------------------------+
|                                                  PASSENGER DETAILS LOGBOOK                                                   |
+------------------------------------------------------------------------------------------------------------------------------+
|           NAME           | DEPARTURE DATE | DEPARTURE TIME | FLIGHT NUM | GATE NUM | BOARDING GRP | SEAT NUM | BOARDING TIME |
+------------------------------------------------------------------------------------------------------------------------------+
|                                          The passenger details logbook is empty.                                             |
+------------------------------------------------------------------------------------------------------------------------------+
````

---

## Add a flight detail: `flight add`

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
---

## Delay a flight: `delay`

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
---

## Modify flight attributes: `modify`
**Function**: The Airside Operations Manager is able to modify **only** the flight number and boarding gate of 
a specified flight in the flight list. The changes in the flight list are also 
propagated to the passenger list as well.

**NOTE:** The flight detail cannot be modified if the new flight number/gate number already exists in the flight list.

**Format** : 

For flight number modification: `modify FLIGHT_NUMBER fn/NEW_FLIGHT_NUMBER`

For gate number modification: `modify FLIGHT_NUMBER gn/NEW_GATE_NUMBER`

Example:

**Input**

`modify SQ832 fn/SQ654`

`modify SQ654 gn/08`

**Output**
````
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Flight number of flight SQ832 is updated to SQ654.

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Gate number of flight SQ654 is updated to 08.

````
---

## Display flight detail logbook: `flight list`

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

---

## Command Summary

| Command                | Format                                                                                                                               | Example                                                                  |
|:-----------------------|:-------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------|
| `add`                  | `passenger add n/PASSENGER_NAME dt/DEPARTURE_TIME fn/FLIGHT_NUMBER gn/GATE_NUMBER bg/BOARDING_GATE sn/SEAT_NUMBER bt/BOARDING_TIME ` | `passenger add n/Ivan Theng dt/2145 fn/sq832 gn/05 bg/01 sn/17d bt/2100` |
| `delete`               | `passenger delete n/PASSENGER_NAME fn/FLIGHT_NUMBER sn/SEAT_NUMBER dt/DEPARTURE_TIME`                                                | `passenger delete n/Ivan Theng fn/sq832 sn/17d dt/2145`                  |
| `list`                 | `passenger list`                                                                                                                     | `passenger list`                                                         |
| `modify flight number` | `modify FLIGHT_NUMBER fn/NEW_FLIGHT_NUMBER`                                                                                          | `modify SQ832 fn/SQ654`                                                  |
| `modify gate number`   | `modify FLIGHT_NUMBER gn/NEW_GATE_NUMBER`                                                                                            | `modify SQ654 gn/08`                                                     |

---

## Frequently Asked Questions

**Q**: What do I need to take note of for the format of each parameter?

**A**: Depicted below is a table of parameters that have
specified formats to follow in order for the command to be successful,
an error may be generated if these standards are not adhered to.

A table of parameters is depicted below:  

| Parameter          | Format to adhere by                                                                                                                                                                                         | Example                             |
|:-------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------------------------------------|
| PASSENGER_NAME     | Input name should be no more than 24 characters                                                                                                                                                             | `Ivan Lim`                          |
| DEPARTURE_TIME     | Input departure time should be in 24 Hours format                                                                                                                                                           | `2100`                              |
| FLIGHT_NUMBER      | Input flight number should start with 2 letter character, followed either by <br/>Two numbers for international flights <br/>Three numbers for regional flights<br/> Four numbers for domestic flights <br> | `SQ12`</br>`SQ123`</br>`SQ1234`<br> |
| GATE_NUMBER        | Input gate number should be between number 00 to 99                                                                                                                                                         | `05`                                |
| BOARDING_GROUP     | Input boarding Group should not be more than 10 and should be in digit form                                                                                                                                 | `01`                                |
| SEAT_NUMBER        | Input Seat number should range between 00A to 99Z                                                                                                                                                           | `B01`                               |
| BOARDING_TIME      | Input boarding time should be in 24 Hours format                                                                                                                                                            | `2015`                              |
| ADD_PARAMETER_HERE | Add format parameter instruction here                                                                                                                                                                       | `Add parameter example here`        |

**Q**: Why can't I input a passenger detail at the start of the program or during the run of the program?  

**A**: A flight detail with a designated flight numbers has to be **recorded or exist** in the flight
list before a passenger flying with an airline of the same flight number can be added into the passenger logbook.

**Q**: Can I enter 2 flights with the same flight number in the input list?

**A**: No. SkyControl lists are only valid for 1 day and hence we prohibit duplicate flight numbers.