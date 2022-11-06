# User Guide for PlanIt

## Introduction

PlanIt is a Command Line Interface (CLI) based desktop application which will help NUS students to plan their modules and credits every semester. They can also check if they are eligible for NOC or SEP using this application.

Note that it is user's own responsibility to check and ensure that the module code that they entered is valid and fulfills any pre-requisites needed.


## Quick Start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest JAR file from [here](https://github.com/AY2223S1-CS2113-T17-2/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your program.
4. Run in command prompt or double-click the file to start the app.
5. Type the commands in the command box and press Enter to execute it. E.g. typing `help` and pressing Enter will open the help window.

Some example commands you can try:
* Add a Module : `add m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER mc/NUMBER_OF_MCS g/GRADE`
* Delete a module : `delete m/MODULE_CODE`
* View modules in a semester : `view s/YEAR_NUMBER_SEMESTER_NUMBER`
* View all modules taken : `view all`
* Clears modules in a semesters : `clear s/YEAR_NUMBER_SEMESTER_NUMBER`
* Clears all modules taken : `clear all`
* Calculate MCs taken : `mcs s/YEAR_NUMBER_SEMESTER_NUMBER`
* Finds module information : `find <KEYWORD>`
* Checks for SEP/NOC program eligibility : `check <PROGRAM>`
* Overview of your Plan (MCs, CAP, Eligibility) : `overview`
* Exits the App : `exit`

## Features 

### Notes about format of input:
1. Words in UPPER_CASE are the parameters to be supplied by the user. 
2. YEAR_NUMBER_SEMESTER_NUMBER should be in the form YXSZ where X is the year number and Z is the semester number. For example, Year 2 Semester 1 will be Y2S1. 
3. Grade can be either the letter grade  (eg. A+, A, A-), the S/U grade (eg. S, U) or Nil ("-") if the module has not been completed and has no grade yet. 
4. Parameters can be in any order. e.g. if the command specifies m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER , s/YEAR_NUMBER_SEMESTER_NUMBER m/MODULE_CODE  is also acceptable. 
5. Every part of the command should be separated by a spacing " ".

### Feature #1 : Adds a module - add

This lets the user add a module to the system.

###### Format of input: `add m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER mc/NUMBER_OF_MCS g/GRADE`

#### Example of Input and Output:
Input : `add m/cs2113 s/y2s1 mc/4 g/a+`

Output:
```
----------------------------------------
CS2113 has been added to Y2S1 as completed
----------------------------------------
```

Input : `add m/CS2113 s/Y2S1 mc/4 g/-`

Output:
```
----------------------------------------
CS2113 has been added to Y2S1 as incomplete!
----------------------------------------
```

Input : `add m/CS2113 s/Y2S1 mc/4 g/S`

Output:
```
----------------------------------------
CS2113 has been added to Y2S1 as completed
----------------------------------------
```

Input : `add m/CS2113 s/Y2S1 mc/4 g/a`

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

Input: `delete m/cs2113`

Output:
```
----------------------------------------
The module CS2113 is not found in your plan!!
Please add the module first before you want to delete.
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
These are your module(s) for Y2S1
1. CS2113 Y2S1 A+ 4
2. CS2030 Y2S1 A 4
----------------------------------------
```

Input: `view s/y2s2`

Output:
```
----------------------------------------
There is no module allocated in Y2S2!!
----------------------------------------
```

###### Format of input: `view all`

Input: `view all`

Output: 
```
----------------------------------------
These are your module(s) for Y1S1
1. CS1010 Y1S1 A 4
These are your module(s) for Y1S2
1. CS2040C Y1S2 B+ 4
These are your module(s) for Y2S1
1. CS2113 Y2S1 A+ 4
2. CS2030 Y2S1 A 4
----------------------------------------
```

### Feature #4 : Clears all modules in semester(s) - clear

This clears modules in a particular semester or all semesters.

###### Format of input: `clear s/YEAR_NUMBER_SEMESTER_NUMBER`

#### Example of Input and Output:
Input : `clear s/Y1S2`

Output:
```
----------------------------------------
Successfully cleared all modules for Y1S2.
----------------------------------------
```

Input : `clear s/Y2S2`

Output:
```
----------------------------------------
No modules found in Y2S2!
Please add the modules to the semester first before you want to clear.
----------------------------------------
```

###### Format of input: `clear all`

Input: `clear all`

Output:
```
----------------------------------------
Successfully cleared all modules in your plan!
----------------------------------------
```

### Feature #5 : View modular credits taken in a semester - mcs

This allows the student to view their MCs taken for the semester.

###### Format of input: `mcs s/YEAR_NUMBER_SEMESTER_NUMBER`

#### Example of Input and Output:
Input : `mcs s/y2s1`

Output:
```
----------------------------------------
You have 8 mcs for Y2S1
----------------------------------------
```

### Feature #6 : Find module information - find

This feature can be used to find module information 
from added modules. They keyword can be from module code, semester, mcs, or grade. 
Even partial keywords can be entered. 

###### Format of input: `find <KEYWORD>`

#### Example of Input and Output:
Input : `find cs`

Output:
```
----------------------------------------
These are your matching module(s):
1. CS2113 Y2S1 A+ 4
2. CS2030 Y2S1 B 4
3. CS1010 Y1S1 A 4
----------------------------------------
```

Input : `find a`

Output:
```
----------------------------------------
These are your matching module(s):
1. CS2113 Y2S1 A+ 4
2. CS1010 Y1S1 A 4
----------------------------------------
```

Input : `find 4`

Output:
```
----------------------------------------
These are your matching modules:
1. CS2040 Y2S1 - 4
----------------------------------------
```

Input : `find y2s2`

Output:
```
----------------------------------------
There are no existing modules that match your keyword inputted.
----------------------------------------
```

### Feature #7 : Checks eligibility for NOC/SEP - check

Checks whether the user is eligible for SEP or NOC.
The specific eligibility for these programs can be found in the glossary.

###### Format of input: `check <PROGRAM>`

#### Example of Input and Output:
Input : `check NOC`

Output 1 - When eligible for NOC:
```
----------------------------------------
You are eligible for NOC!
----------------------------------------
```

Output 2 - When ineligible for NOC:
```
----------------------------------------
Sorry, You are ineligible for NOC.

These may be possible reasons for ineligibility:
 * You have yet to complete 4 semesters of study
 * You are currently in your final academic semester
 * You have yet to obtain more than 70MC
----------------------------------------
```

Input : `check SEP`

Output 1 - When eligible for SEP:
```
----------------------------------------
You are eligible for SEP!
----------------------------------------
```

### Feature #8 : Opens the help manual - help

Helps users get their feet into PlanIT.
Opens a list of all the commands of the program.

###### Format of input: `help`

#### Example of Input and Output:
Input : `help`

Output:
```
----------------------------------------
Here's PlanIT! Command Summary for your reference :
* Add a Module : add m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER mc/NUMBER_OF_MCS g/GRADE 
* Delete a module : delete m/MODULE_CODE 
* View modules in a semester : view s/YEAR_NUMBER_SEMESTER_NUMBER 
* View all modules taken : view all 
* Clears modules in a semesters : clear s/YEAR_NUMBER_SEMESTER_NUMBER 
* Clears all modules taken : clear all 
* Calculate MCs taken : mcs s/YEAR_NUMBER_SEMESTER_NUMBER 
* Finds module information : find <KEYWORD> 
* Checks for SEP/NOC program eligibility : check <PROGRAM> 
* Overview of your Plan (MCs, CAP, Eligibility) : overview 
* Exits the App : exit 
----------------------------------------
```

### Feature #9 : Gets an overview of Student Profile - overview

View MCs taken, CAP, Graduation fulfillment and eligibility for SEP and NOC.

###### Format of input: `overview`

#### Example of Input and Output:
Input : `overview`

Output :
```
----------------------------------------
Here’s an overview of your Profile:

* Current Semester: Y1S1

* Total MCs : 4
* Total Graded MCs : 4
* Total Ungraded (-) MCs : 0
* Total S/U MCs : 0

* Cumulative Average Point (CAP) : 5.00

* MCs Needed needed for graduation : 156

* Eligibility for NOC : No
* Eligibility for SEP : No
----------------------------------------
```

### Feature #10 : Exits the program - exit

Exits the program

###### Format of input: `exit`

#### Example of Input and Output:
Input : `exit`

Output:
```
----------------------------------------
Thank you for using PlanIT!
See you again next time!
----------------------------------------
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: After you use our application, your data is stored in a file called "data.txt" which is in the 
same directory as the application file. You can just transfer this file to another computer and save it in the
same directory as your application. It will transfer all the information in the program running on the other computer.

**Q**: What if I forget how to use PlanIt?

**A**: You can always view the commands available in PlanIt through the `help` input.

## Command Summary

* Add a Module : `add m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER mc/NUMBER_OF_MCS g/GRADE`
* Delete a module : `delete m/MODULE_CODE`
* View modules in a semester : `view s/YEAR_NUMBER_SEMESTER_NUMBER`
* View all modules taken : `view all`
* Clears modules in a semesters : `clear s/YEAR_NUMBER_SEMESTER_NUMBER`
* Clears all modules taken : `clear all`
* Calculate MCs taken : `mcs s/YEAR_NUMBER_SEMESTER_NUMBER`
* Finds module information : `find <KEYWORD>`
* Checks for SEP/NOC program eligibility : `check <PROGRAM>`
* Overview of your Plan (MCs, CAP, Eligibility) : `overview`
* Exits the App : `exit`