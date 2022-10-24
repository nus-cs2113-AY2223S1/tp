# User Guide for Yet Another Module Organizer/Manager (YAMOM) v1.0

## Introduction

YAMOM is a lightweight, simplified Module Organizer and Manager application that provides simple and intuitive interface for timetable organization. 

YAMOM is designed for users who are proficient in Command Line Interface (CLI).


## Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features)
    1. [Getting help: `help`](#getting-help-help)
    2. [Exiting: `bye`](#exiting-yamom-bye)
    3. [Add a module: `add`](#add-a-module-add)
    4. [Remove a module: `delete`](#removing-a-module-delete)
    5. [Search for module: `search`](#search-for-modules-search)
    6. [View timetable: `view`](#viewing-user-timetable-view)
    7. [Select module slot: `select`](#select-a-timetable-slot-select)
    8. [Future Feature: `COMMAND`](#feature-x-command)
3. [Application Data](#application-data)
   1. [Data Storage](#data-storage)
   2. [Transfer to Another Computer](#transfer-to-another-computer)
   3. [Transfer to NUSMods](#transfer-to-nusmods-for-nus-students)
4. [Frequently Asked Questions (FAQ)](#faq)
5. [Command summary](#command-summary)
6. [Credits](#credits)

## Quick Start

1. Ensure that you have Java 11 (reccomended) or above installed the computer.
2. Download the latest version of `YAMOM` jar file from [here](https://github.com/AY2223S1-CS2113-F11-3/tp/releases).
3. Copy the file to the folder you want to use as the *home folder* for the application. (for example: `C://YAMOM`)
4. Open the command line in that folder and run the command `java -jar [filename].jar`.
5. You will receive a greeting if the application runs successfully.



## Features 

> Notes about the command format:
> - Words in `UPPERCASE` information to be supplied by the user.
    e.g. in `select [MODULE]`, `[MODULE]` are expected input for the command in the form of `select CS1010`.
> - Extraneous parameters will be rejected.

### Getting help: `help`

Displays the list of funtionalities available by YAMOM.

Format: `help`

Example of usage:
`help`

Sample Output:
```
help
--------------------------------------
help: lists all functions!
add: add a module into your planner!
delete: delete MODULE - remove a module from your planner!
bye: exit Yet Another Module Organiser / Manager (YAMOM)!
search: [MODULE_CODE][MODULE_NAME] - returns modules that match the search terms!
view: displays current timetable!
select: select /module [MODULE_CODE] /type [LESSON_TYPE] /code [CLASS_NO] - select slot for modules!

--------------------------------------
```

### Exiting YAMOM: `bye`

Ends the programme. 

Format: `bye`

Example of usage:

`bye`

Sample Output:
```
bye
--------------------------------------
--------------------------------------
Bye bye, See you again
--------------------------------------
```

> Note: 
> - The user data will be processed and stored locally on the computer
> - The user data will be available when YAMOM is activated again

### Add a module: `add`

Adds a module from available database into the user timetable

Format: `add MODULE_CODE`

* The `MODULE_CDOE` is not case-sensitive, but has to be an exact match

Example of usage:

`add CS1010`

`add cs2113`

Sample Output:
```
add CS1010
--------------------------------------
CS1010 has been added
--------------------------------------
```

Possible Error: 
1. Wrong module format
   - The module code must be an exact match (CS2030 instead of cs203) else nothing will be added
   ```
   add cs203
   --------------------------------------
   --------------------------------------
   ```
   
2. More than one module
   - Currently, YAMOM only support adding one module at a time
   ```
   add CS1231 CS2101
   --------------------------------------
   Sorry, I do not understand your command. Enter "help" for the available commands
   --------------------------------------
   ```

### Removing a module: `delete`

Removes a module from the user timetable

Format: `delete MODULE_CODE`

* The `MODULE_CDOE` is not case-sensitive, but has to be an exact match

Example of usage:

`delete CS2040`

`delete cs3219`

Sample Output:
```
delete CS1010
--------------------------------------
CS1010 has been deleted
--------------------------------------
```

Possible Error:

* The error for delete is similar to the command `add`, see [add](#add-a-module-add)

### Search for Modules: `search`

List out all modules that matches the module code.

Format: `search KEYWORD`

* The `KEYWORD` will be the module code of interest.
* The `KEYWORD` can be the faculty code or the module number
* The `KEYWORD` is not case-sensitive

Example of usage:

`search cs`

`search 1010`

`search GEA1000`

Sample Output:
```
search CG
--------------------------------------
Module search list
CG1111
CG1111A
CG1112
CG2023
CG2027
CG2028
CG2111A
CG2271
CG3002
CG3207
CG4001
CG4002
CG4003
--------------------------------------
```

Possible Error:
1. Module code does not have a match
   * YAMOM will return an empty list of modules
   ```
   search XX
   --------------------------------------
   Module search list
   --------------------------------------
   ```
> Note:
> - The list of modules can get very long for a generic search

### Changing semester to plan: `semester`

Select semester to plan for and organise.

Format: `semester SEMESTER_SELECTED`

Example of usage: 

`semester 1`

Sample Output:

```
semester 3
--------------------------------------
You are now planning for special term I
--------------------------------------
```
### Viewing user timetable: `view`

Prints out the current user timetable.

Format: `view`

Example of usage:

`view`

Sample Output:
```
view
--------------------------------------
                                                                    
          : Mon      : Tues     : Wed      : Thur     : Fri         
====================================================================
   0800   :          :          :          +----------+----------+  
   0830   :          :          :          |CS2040    |CS2040    |  
   0900   :          :          :          +-TUT[01]--+ LAB[1C]  |  
   0930   :          :          :          :          |          |  
   1000   :          :          +----------+          +----------+  
   1030   :          :          |CS2040    |          :             
   1100   :          :          | LEC[1]   |          :             
   1130   :          :          |          |          :             
   1200   :          :          +----------+          :             
   1230   :          :          :          :          :             
   1300   :          :          :          :          :             
                                                                    

--------------------------------------
```

Possible Error:

- Empty timetable:
```
view
--------------------------------------
Your timetable is empty. Please select lessons first before viewing.
--------------------------------------
```
### Feature X: `list`

Displays list of all selected modules and slots.

Format: `list`

Example of usage:

`list`

Sample Output:
```

```

### Select a timetable slot: `select`

Selects a timeslot to be added to the user timetable.

Format: `select /module MODULE_CODE /type LESSON_TYPE /code CLASS_NO`

* The `CLASS_NO` will be a number.
* The `LESSON_TYPE` can be either *lecture*, *tutorial*.
* The `MODULE_CODE` need to be an exact match

Example of usage:

`select /module CS2040 /type lecture /code 1`

`select /module CS2030 /type tutorial /code 2`

Sample Output:
```
select /module CS2040 /type lecture /code 2
--------------------------------------
Slot selected successfully
--------------------------------------
```

### Feature X: `COMMAND`

{Description of the command}.

Format: `command ARG1 /option ARG2`

* The `ARG1` can be {condition}.
* The `ARG2` cannot {condition}.  

Example of usage: 

`command Use YAMOM /when now`

`command Leave YAMOM /when get milk`

Sample Output:
```

```

Possible Error:

> Note:

## Application Data

> Warning:
> Be careful when handling the data file, any unexpected changes in the file may lead to data loss and crash subsequent runs of YAMOM.

### Data Storage
The user data is stored in `duke.txt` under the data folder in the home directory

### Transfer to another computer
Copy the application file with the corresponding data file (remember to create the `data` folder under the home directory)

### Transfer to NUSMODs (for NUS students)
The NUSMOD url is available in `duke.txt`, copy the content of the file and paste it in the browser to view the timetable on NUSMOD

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: YAMOM is designed with portability in mind. Simply copy the JAR file and the data folder over to your other computer and you are good to go.

## Command summary

| Action                    | Format                                                        | Example                                        |
|---------------------------|---------------------------------------------------------------|------------------------------------------------|
| Add a module              | `add MODULE_CODE`                                             | `add CS2101`                                   |
| Delete a module           | `delete MODULE_CODE`                                          | `delete CS2102`                                |
| Exit application          | `bye`                                                         | `bye`                                          |
| Find module by keyword    | `find KEYWORD`                                                | `find cs2103`                                  |
| Seek help                 | `help`                                                        | `help`                                         |
| View timetable            | `view`                                                        | `view`                                         |
| Add module timetable slot | `select /module MODULE_CODE /type LESSON_TYPE /code CLASS_NO` | `select /module CS1010 /type tutorial /code 1` |


## Credits
Credits to Owen Leong (owenl131), Hao Yi (CheahHaoYi), Koh Ngiap Hin (konnh) for snippets of code from their Individual Projects to be integrated into YAMOM
