# User Guide

## Table of Contents
* [Introduction](#Introduction)
* [Getting Started](#getting-started)
* [SkyControl's Features](#SkyControl's-Features)
  * [Add a passenger detail: `passenger add`](#Add-a-passenger-detail-passenger-add)
  * [Delete a passenger detail: `passenger delete`](#Delete-a-passenger-detail-passenger-delete)
  * [Display passenger detail logbook: `passenger list`](#Display-passenger-detail-logbook-passenger-list)
  * [Command Summary](#Command Summary)
* [Frequently Asked Quesitions](#Frequently-Asked-Questions)

---

## Introduction

{Give a product intro}  

---

## Getting Started

{Give steps to get started quickly}

1. Please ensure that you have **Java 11 or above** present in your computer
2. Proceed to download the latest version of `SkyControl-v2_0` from
[here](https://github.com/AY2223S1-CS2113-T17-1/tp/releases).
3. Move the file to your desired folder that you have designated as **main folder** for SkyControl.
4. Open a terminal and type java -jar SkyControl-v2_0.jar
5. Once the program have been successfully initialised, you should see SkyControl greetings as seen below.

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
6. After which SkyControl have booted up successfully, enter a command below `SkyControl` welcome display
and hit the <kbd>Enter</kbd> button to execute it. Reflected below is an example.  

---

## SkyControl's Features 

**Take Note:**

+ All commands entered into the terminal are **not case-sensitive.**  
i.e. `Passenger add [PASSENGER DETAIL]` or `Passenger Add [PASSENGER DETAIL]` are acceptable commands
+ Words in `[UPPER CASE]` are shown as a single or multiple parameter for the command. 
i.e. `flight add [PASSENGER DETAIL]`  

---
### Add a passenger detail: `passenger add`
**Explanation**:  
**Function**:  
**Usage**:

Example:
**Input**  
**Output**  
---

## Delete a passenger detail: `passenger delete`
**Explanation**: As the name of the feature suggests, execute this command should
delete the detail of a single passenger. It is used to remove a passenger from the passenger list.  


**Function**: Deletes a passenger from the current passenger list.
**Usage**: `passenger delete n/[PASSENGER NAME] fn/[FLIGHT NUMBER] sn/[SEAT NUMBER] dt/[DEPARTURE TIME]`

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

## Command Summary

| Command  | Format                                                                                                                                             | Example                                                                  |
|:---------|:---------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------|
| `add`    | `passenger add n/[PASSENGER NAME] dt/[DEPARTURE TIME] fn/[FLIGHT NUMBER] gn/[GATE NUMBER] bg/[BOARDING GATE] sn/[SEAT NUMBER] bt/[BOARDING TIME] ` | `passenger add n/Ivan Theng dt/2145 fn/sq832 gn/05 bg/01 sn/17d bt/2100` |
| `delete` | `passenger delete n/[PASSENGER NAME] fn/[FLIGHT NUMBER] sn/[SEAT NUMBER] dt/[DEPARTURE TIME]`                                                      | `passenger delete n/Ivan Theng fn/sq832 sn/17d dt/2145`                  |
| `list`   | `passenger list`                                                                                                                                   | `passenger list`                                                         |

---

## Frequently Asked Questions

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}  