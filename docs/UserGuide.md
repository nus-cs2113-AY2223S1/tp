# User Guide for PlanIt

## Introduction

PlanIt is a Command Line Interface (CLI) based desktop application which will help School of Computing students to plan their modules and credits every semester. They can also check if they are eligible for NOC or SEP using this application.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest JAR file from {Add link here later}
3. Copy the file to the folder you want to use as the home folder for your program.
4. Double-click the file to start the app.
5. Type the commands in the command box and press Enter to execute it. E.g typing help and pressing Enter will open the help window.

Some example commands you can try:
- add : Adds a particular module to a Semester 
- delete : Removes a particular module from a semester 
- view : View all modules taken for a particular semester 
- mcs : View total Modular credits for a particular semester 
- find : Finds any module information
- check : Check eligibility for SEP/NOC
- exit:  Exits the app

## Features 

### Notes about format of input:
1. Words in UPPER_CASE are the parameters to be supplied by the user. 
2. YEAR_NUMBER_SEMESTER_NUMBER should be in the form YXSZ where X is the year number and Z is the semester number. For example, Year 2 Semester 1 will be Y2S1. 
3. Grade can be either the letter grade  (eg. A+, A, A-), the S/U grade (eg. S, U) or Nil (eg. “-”) if the module has not been completed and has no grade yet. 
4. Parameters can be in any order. e.g. if the command specifies m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER , s/YEAR_NUMBER_SEMESTER_NUMBER m/MODULE_CODE  is also acceptable. 
5. Every part of the command should be separated by a spacing “ “.

### Feature #1 : Adds a module - add

This lets the user add a module to the system.

###### Format of input: `add m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER mc/NUMBER_OF_MCS g/GRADE`

#### Example of Input and Output:
Input : `add m/CS2111 s/Y2S1 mc/4 g/b+`

Output:
```
----------------------------------------
CS2111 has been added to Y2S1 as completed
----------------------------------------
```

Input : `add m/CS2112 s/Y2S1 mc/4 g/-`

Output:
```
----------------------------------------
CS2112 has been added to Y2S1 as incomplete!
----------------------------------------
```

Input : `add m/CS2113 s/Y2S1 mc/4 g/S`

Output:
```
----------------------------------------
CS2113 has been added to Y2S1 as completed
----------------------------------------
```

Input : `add m/CS2113 s/Y2S1 mc/4 g/S`

Output:
```
----------------------------------------
This CS2113 module is already in your plan.
Choose another module to add or delete the one in the plan and add it again.
----------------------------------------
```

### Feature #2 : Deletes a module - delete

This allows the user to delete a module that they have previously added to their system.

###### Format of input: `delete m/MODULE_CODE`

#### Example of Input and Output:
Input : `delete m/CS2113`

Output:
```
----------------------------------------
CS2113 has been deleted from your plan
----------------------------------------
```

### Feature #3 : View all modules in a semester - view

This shows the user what modules they have added into the system for a particular semester.

###### Format of input: `view s/YEAR_NUMBER_SEMESTER_NUMBER`

#### Example of Input and Output:
Input : `view s/Y2S1`

Output:
```
----------------------------------------
These are your mods for Y2S1
1. CS2112 Y2S1 - 4
2. CS2111 Y2S1 B+ 4
3. CS2115 Y2S1 S 4
----------------------------------------
```

### Feature #4 : View modular credits taken in a semester - mcs

This allows the student to view their MCs taken for the semester

###### Format of input: `mcs s/YEAR_NUMBER_SEMESTER_NUMBER`

#### Example of Input and Output:
Input : `mcs s/Y2S2`

Output:
```
----------------------------------------
You have 4 mcs for Y2S1
----------------------------------------
```

### Feature #5 : Find module information - find

Find module information from added modules.

###### Format of input: `find <KEYWORD>`

#### Example of Input and Output:
Input : `find cs2113`

Output:
```
----------------------------------------
These are your matching modules:
1. CS2112 Y2S1 - 4
----------------------------------------
```

### Feature #6 : Checks eligibility for NOC/SEP - check

Checks whether the user is eligible for SEP or NOC

###### Format of input: `check <PROGRAM>`

#### Example of Input and Output:
Input : `check NOC`

Output:
```
----------------------------------------
You are ineligible for NOC!
----------------------------------------
```

Input : `check SEP`

Output:
```
----------------------------------------
You are eligible for SEP!
----------------------------------------
```

### Feature #7 : Exits the program - exit

Exits the program

###### Format of input: `exit`

#### Example of Input and Output:
Input : `exit`

Output:
```
----------------------------------------
Thank you for using PlanIt!
----------------------------------------
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Add module `add m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER mc/NUMBER_OF_MCS g/GRADE`
* Deletes module `delete m/MODULE_CODE`
* View modules `view s/YEAR_NUMBER_SEMESTER_NUMBER`
* Calculate MCs `mcs s/YEAR_NUMBER_SEMESTER_NUMBER`
* Finds module information `find <KEYWORD>`
* Checks for program eligibility `check <PROGRAM>`
* Exits the App `exit`