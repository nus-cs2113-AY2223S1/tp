# User Guide

## Table of Contents

* [Introduction](#introduction)
* [Getting Started](#getting-started)
* [SkyControl's Features](#skycontrols-features)
    * [Add a passenger detail: `passenger add`](#add-a-passenger-detail-passenger-add)
    * [Delete a passenger detail: `passenger delete`](#delete-a-passenger-detail-passenger-delete)
    * [Display passenger detail logbook: `passenger list`](#display-passenger-detail-logbook-passenger-list)
    * [Add a flight detail: `flight add`](#add-a-flight-detail-flight-add)
    * [Delete a flight detail: `flight delete`](#delete-a-flight-detail-flight-delete)
    * [Delay a flight: `delay`](#delay-a-flight-delay)
    * [Display flight detail logbook: `flight list`](#display-flight-detail-logbook-flight-list)
    * [Command Summary](#command-summary)
* [Frequently Asked Questions](#frequently-asked-questions)

---

## Introduction

SkyControl is a program which optimizes the use of the Command Line Interface (CLI) to manage flights and passengers in
an airport terminal for the present operation day.

SkyControl allows an Airport Operations Planning & Airside Manager to add, delete, update flight and passenger
information, add delays to flights
and list out both flight and passenger details by entering commands into the CLI.

It aims to provide an ease of access to the manager, to access the relevant flight or passenger information
through a CLI which is faster than a Graphical User Interface (GUI).

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
[FAQ](#frequently-asked-questions) section.

---

## Add a passenger detail: `passenger add`

**Function**: Adds the passenger to the passenger list, given that the flight number of the
passenger already exists in the flight list and the seat number is not already occupied by another
passenger in the passenger list.

**Format**: `passenger add n/PASSENGER_NAME fn/FLIGHT_NUMBER bg/BOARDING_GROUP sn/SEAT_NUMBER`

Example:

**Input**

`passenger add n/Ivan Theng fn/sq832 bg/01 sn/17d`

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

**Usage**: `passenger delete n/PASSENGER_NAME fn/FLIGHT_NUMBER sn/SEAT_NUMBER`

Example:

**Input**

`passenger delete n/Ivan Theng fn/sq832 sn/17d`

**Output**

_If passenger exists in the current passenger logbook_.

````
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Passenger IVAN THENG from SQ832 of seat number 17D have been
deleted from the passenger list.
0 passenger(s) left on the passenger list.
````  

_If passenger **does not** exist in the current passenger logbook_.

```
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
The system is unable to delete the specified passenger 
as he/she is not found in the passenger list or his/her 
detail have been input incorrectly.
```

---

## Display passenger detail logbook: `passenger list`

**Explanation**: As the name of the feature suggests, execute this command should
list the current passenger(s) that is/are present in the logbook.

**Function**: Displays a list in table form of all the current passengers that are the logbook.

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
| IVAN THENG               | 26-10-22       | 2145           | SQ832      | 05       | 1            | 17D      | 2115          |
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

**Function**: Adds a flight to the flight list

**Usage**: `flight add fn/FLIGHT_NUMBER a/AIRLINE d/DESTINATION dt/DEPARTURE_TIME gn/GATE_NUMBER c/CHECKIN_ROW_DOOR`

Example:

**Input**

`flight add fn/sq832 a/Singapore Airlines d/bangkok dt/1600 gn/05 c/03-03`

**Output**

```
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Flight added!
```

---

## Delete a flight detail: `flight delete`

**Function**: This command allows the deletion of a flight detail from the flight list logbook, provided that
the flight exists in the logbook.

**Usage**: `flight delete FLIGHT_NUMBER`

Example:

**Input**:

`flight delete sq832`

**Output**:

_If flight exists in the current flight logbook_.

```
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
FLIGHT SQ832 HAS BEEN DELETED.
```

_If flight **does not** exist in the current flight logbook_.

```
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
FLIGHT SQ832 NOT FOUND.
```

---

## Delay a flight: `delay`

**Function**: In the event a flight is suddenly delayed on the same day,
this delay function allows the user to indicate a new departure time for the flight while making sure
the gate number is still available at the new departure time.

**NOTE**: New Departure time should be later than the existing departure time.

**Usage**: `delay FLIGHT_NUMBER dt/NEW_DEPARTURE_TIME`

Example:

**Input**:

`delay sq832 dt/1700`

**Output**:

```
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Departure time of flight SQ832 is delayed from 1600 to 1700.
```

---

## Modify flight attributes: `modify`

**Function**: The Airside Operations Manager is able to modify **only** the flight number and boarding gate of
a specified flight in the flight list. The changes in the flight list are also
propagated to the passenger list as well.

**NOTE:** The flight detail cannot be modified if the new flight number/gate number already exists in the flight list.

**Usage** :

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

**Function**: Displays a list in table form of all the current flights that are in the logbook.

**Usage**: `flight list`

Example:

**Input**:

`flight list`

**Output**:

_If flight details logbook has/have existing flight(s)_.

```
+----------------------------------------------------------------------------------------------------------------------------------+
|                                                  FLIGHT DETAILS LOGBOOK FOR TERMINAL 1                                         |
+----------------------------------------------------------------------------------------------------------------------------------+
| FLIGHT NUM | DEPARTURE DATE |        AIRLINE         |      DESTINATION      | DEPARTURE TIME | GATE NUM |  CHECK-IN ROW/DOOR  |
+----------------------------------------------------------------------------------------------------------------------------------+
|      SQ832 |       23-10-22 |     SINGAPORE AIRLINES |               BANGKOK |           1600 |       05 |               03-03 |
+----------------------------------------------------------------------------------------------------------------------------------+
```

_If flight details logbook is empty_.

```
+----------------------------------------------------------------------------------------------------------------------------------+
|                                                  FLIGHT DETAILS LOGBOOK FOR TERMINAL 1                                         |
+----------------------------------------------------------------------------------------------------------------------------------+
| FLIGHT NUM | DEPARTURE DATE |        AIRLINE         |      DESTINATION      | DEPARTURE TIME | GATE NUM |  CHECK-IN ROW/DOOR  |
+----------------------------------------------------------------------------------------------------------------------------------+
|                                             The flight details logbook is empty.                                               |
+----------------------------------------------------------------------------------------------------------------------------------+
```

---

## Command Summary

| Command                | Format                                                                                                    | Example                                                              |
|:-----------------------|:----------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------|
| `passenger add`        | `passenger add n/PASSENGER_NAME fn/FLIGHT_NUMBER bg/BOARDING_GATE sn/SEAT_NUMBER bt/BOARDING_TIME `       | `passenger add n/Ivan Theng fn/sq832 bg/01 sn/17d bt/2100`           |
| `flight add`           | `flight add fn/FLIGHT_NUMBER a/AIRLINE d/DESTINATION dt/DEPARTURE_TIME gn/GATE_NUMBER c/CHECKIN_ROW_DOOR` | `flight add fn/KE632 a/Korea Airlines d/Korea dt/1200 gn/32 c/12-03` |
| `passenger delete`     | `passenger delete n/PASSENGER_NAME fn/FLIGHT_NUMBER sn/SEAT_NUMBER`                                       | `passenger delete n/Ivan Theng fn/sq832 sn/17d`                      |
| `flight delete`        | `flight delete fn/FLIGHT_NUMBER`                                                                          | `flight delete ke632`                                                |
| `passenger list`       | `passenger list`                                                                                          | `passenger list`                                                     |
| `flight list`          | `flight list`                                                                                             | `flight list`                                                        |
| `modify flight number` | `modify FLIGHT_NUMBER fn/NEW_FLIGHT_NUMBER`                                                               | `modify SQ832 fn/SQ654`                                              |
| `modify gate number`   | `modify FLIGHT_NUMBER gn/NEW_GATE_NUMBER`                                                                 | `modify SQ654 gn/08`                                                 |
| `delay`                | `delay FLIGHT_NUMBER dt/NEW_DEPARTURE_TIME`                                                               | `delay KE632 dt/2100`                                                |

---

## Frequently Asked Questions

**Q**: What do I need to take note of for the format of each parameter?

**A**: Depicted below is a table of parameters that have
specified formats to follow in order for the command to be successful,
an error may be generated if these standards are not adhered to.

**Table of parameters:**

| Parameter          | Format to adhere by                                                                                                                                                                                         | Example                             |
|:-------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------------------------------------|
| PASSENGER_NAME     | Input name should be no more than 24 characters                                                                                                                                                             | `Ivan Lim`                          |
| DEPARTURE_TIME     | Input departure time should be in 24 Hours format                                                                                                                                                           | `2100`                              |
| NEW_DEPARTURE_TIME | Input departure time should be in 24 Hours format and later than the existing departure time                                                                                                                | `2200`                              |
| FLIGHT_NUMBER      | Input flight number should start with 2 letter character, followed either by <br/>Two numbers for international flights <br/>Three numbers for regional flights<br/> Four numbers for domestic flights <br> | `SQ12`</br>`SQ123`</br>`SQ1234`<br> |
| NEW_FLIGHT_NUMBER  | Input flight number should follow FLIGHT_NUMBER constraints but must not be the same flight code                                                                                                            | `KE356`                             |
| GATE_NUMBER        | Input gate number should be 2 digits and between ranges 00 and 99                                                                                                                                           | `05`                                |
| NEW_GATE_NUMBER    | Input gate number should follow GATE_NUMBER constraints but must not be the same value                                                                                                                      | `22`                                |
| BOARDING_GROUP     | Input boarding Group should not be more than 10 and should be in digit form                                                                                                                                 | `01`                                |
| SEAT_NUMBER        | Input Seat number should range between 00A to 99Z                                                                                                                                                           | `B01`                               |

<br>

**Q**: Why can't I input a passenger detail at the start of the program or during the run of the program?

**A**: A flight detail with a designated flight numbers has to be **recorded or exist** in the flight
list before a passenger flying with an airline of the same flight number can be added into the passenger logbook.

<br>

**Q**: Can I enter 2 flights with the same flight number in the input list?

**A**: No. SkyControl lists are only valid for 1 day and hence we prohibit duplicate flight numbers.

<br>

**Q**: Can I use SkyControl to save a flight/ passenger registered in a different airport terminal or on a different
day?

**A**: No SkyControl is currently limited to a single airport terminal and can only log flights/passengers within the
same day.
However, this can be a future implementation outside the CS2113 curriculum.  
  
**Q**: How is boarding time deduced?  
  
**A**: Boarding time for any airline would always be set to **45 minutes** before departure time.  
  
**Q**: Do I have to manually update the Boarding time for every passenger if I were to set a delay on a flight?  
  
**A**: Thankful, you do not need too, SkyControl would automatically update the Boarding time for every
passenger if a delay in departure time is present.

<br>