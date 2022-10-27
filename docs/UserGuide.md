# User Guide for Yet Another Module Organizer/Manager (YAMOM) v1.0

## Introduction

YAMOM is a lightweight, simplified Module Organizer and Manager application that provides simple and intuitive interface for timetable organization. 

YAMOM is designed for users who are proficient in Command Line Interface (CLI).


## Table of Contents

- [User Guide for Yet Another Module Organizer/Manager (YAMOM) v1.0](#user-guide-for-yet-another-module-organizermanager-yamom-v10)
  - [Introduction](#introduction)
  - [Table of Contents](#table-of-contents)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [Getting help: `help`](#getting-help-help)
    - [Exiting YAMOM: `bye`](#exiting-yamom-bye)
    - [Add a module: `add`](#add-a-module-add)
    - [Removing a module: `delete`](#removing-a-module-delete)
    - [Search for Modules: `search`](#search-for-modules-search)
    - [Changing semester to plan: `semester`](#changing-semester-to-plan-semester)
    - [Viewing user timetable: `view`](#viewing-user-timetable-view)
    - [List out selected module: `list`](#listing-out-all-selected-modules-list)
    - [Select a timetable slot: `select`](#select-a-timetable-slot-select)
    - [Export Timetable: `export`](#creating-an-nusmod-link-export)
    - [Import Timetable: `import`](#importing-a-timetable-import)
<!--    - [Feature X: `COMMAND`](#feature-x-command) -->
  - [Application Data](#application-data)
    - [Data Storage](#data-storage)
    - [Transfer to another computer](#transfer-to-another-computer)
    - [Transfer to NUSMODs (for NUS students)](#transfer-to-nusmods-for-nus-students)
  - [FAQ](#faq)
  - [Command summary](#command-summary)
  - [Credits](#credits)

## Quick Start

1. Ensure that you have Java 11 (recommended) or above installed the computer.
2. Download the latest version of `YAMOM` jar file from [here](https://github.com/AY2223S1-CS2113-F11-3/tp/releases).
3. Copy the file to the folder you want to use as the *home folder* for the application. (for example: `C://YAMOM`)
4. Open the command line in that folder and run the command `java -jar [filename].jar`.
5. You will receive a greeting if the application runs successfully.

```
Hello from
                      
__ __ _____ _____ _____ _____
|  |  |  _  |     |     |     |
|_   _|     | | | |  |  | | | |
  |_| |__|__|_|_|_|_____|_|_|_|

How can I help you today?
Enter "help" to get started!
Sem [1] >> 
```

## Features 

> Notes about the command format:
> - Words in `UPPERCASE` information to be supplied by the user.
    e.g. in `select [MODULE]`, `[MODULE]` are expected input for the command in the form of `select CS1010`.
> - Extraneous parameters will be rejected.

### Getting help: `help`

Displays the list of functionalities available by YAMOM.

Format: `help`

Example of usage:
`help`

Sample Output:
```
help
--------------------------------------
Here are all the commands available in YAMOM!

add	: add a module into YAMOM timetable.
delete	: remove a module from YAMOM timetable.
list	: List out all the selected modules and lesson slots.
bye	: Exit YAMOM!
export	: Generates an NUSMod Link to be exported to the browser.
get	: Show all details of a module.
help	: List out all commands and  their respective usages in YAMOM
import	: Imports a timetable  from an NUSMod timetable sharing link
search	: List out all modules that contains a search term
	 * the search term can either be module code or a keyword in module title.
semester	: Select another semester to plan and organize timetable
select	: Select a module slot
view	: Display current user timetable

Usage :
	add [MODULE_CODE]
	delete [MODULE_CODE]
	list
	bye
	export
	get [MODULE_CODE]
	help
	import [NUSMods_LINK]
	search (/code [MODULE_CODE] | /title [KEYWORD])
	semester [SEMESTER_SELECTED]
	select /module <MODULE_CODE> /type <LESSON_TYPE> /code <CLASS_NO>
	view (fancy|simple)

Note: [ ] are required elements, < > are optional elements
For more detailed guide, please visit https://ay2223s1-cs2113-f11-3.github.io/tp/
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

* The `MODULE_CODE` is not case-sensitive, but has to be an exact match

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
   Error! 	Wrong format, should be: add [MODULE_CODE]
   Module is invalid! Please enter a valid module code.
   Each module of study has a unique module code consisting of a two-
   or three-letter prefix that generally denotes the discipline,
   and four digits, the first of which indicates the level of the module
   (e.g., 1000 indicates a Level 1 module and 2000, a Level 2 module).
   --------------------------------------
   ```
   
2. More than one module
   - Currently, YAMOM only support adding one module at a time
   ```
   add CS1231 CS2101
   --------------------------------------
   Error! 	Wrong format, should be: add [MODULE_CODE]
   Unknown command, try again.
   --------------------------------------
   ```

### Removing a module: `delete`

Removes a module from the user timetable

Format: `delete MODULE_CODE`

* The `MODULE_CODE` is not case-sensitive, but has to be an exact match

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

Format: `search (/code [MODULE_CODE] | /title [KEYWORD]) </level [MODULE_LEVEL]> </sem [MODULE_SEMESTER]>`

* The `MODULE_CODE` will be the module code of interest, can be partial module code
* The `KEYWORD` will be a keyword in the module title
* The `MODULE_LEVEL` will be the first digit of the module code (indicate level of module), a single digit number
* The `MODULE_SEMESTER` will be the semester of interest
* At least one of `/code [MODULE_CODE]` or `/title [KEYWORD]` must be present for search
* `/level [MODULE_LEVEL` and `/sem [MODULE_SEMESTER]` are optional search fields

Example of usage:

`search /code cs`

`search /title programming /level 1`

`search /code CS /title algorithm /level 5 /sem 2`

Sample Output:
```
search /code CG /level 2
--------------------------------------------------------------------------------
Search Result:
Total 5 module(s) found

CG2023 Signals and Systems
CG2027 Transistor-level Digital Circuits
CG2028 Computer Organization
CG2111A Engineering Principles and Practice II
CG2271 Real-Time Operating Systems

To get full details of the module, type 'get <module code>'
-------------------------------------------------------------------------------- 
```

Possible Error:
1. Module code does not have a match
   * YAMOM will return an empty list of modules
   ```
   search /code XX
   --------------------------------------
   Module search list
   --------------------------------------
   ```
2. The search is not in the expected format
   * YAMOM will prompt for the right search format

    ```   
    search ABC
    --------------------------------------------------------------------------------
    Error! 	Wrong format given, should be
    search (/code [MODULE_CODE] | /title [KEYWORD]) </level [MODULE_LEVEL]> </sem [MODULE_SEMESTER]>
    -------- ------------------------------------------------------------------------
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

Possible Error:
1. User did not input a valid semester
```
semester 0
--------------------------------------------------------------------------------
Error! 	Wrong format, should be: semester [SEMESTER_SELECTED]
Not a valid semester.
--------------------------------------------------------------------------------
```

### Viewing user timetable: `view`

Prints out the current user timetable.

Format: `view /(simple|fancy)`  
`view /simple` is equivalent to `view`  

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
Error! 	Your timetable is empty.
Please select your modules first before viewing.
--------------------------------------
```
 - Random parameters:
```
view timetable
--------------------------------------
Error! 	Unknown command. Maybe you meant "view".
--------------------------------------
```
 - Forgot backslash `/`:
```
view fancy
--------------------------------------
Error! 	Unknown command. Maybe you forgot a "/".
--------------------------------------
```
 - Contains both `/fancy` and `/simple`:
```
view /simple /fancy
--------------------------------------
Error! 	Timetable cannot be both simple and fancy!
--------------------------------------
```
### Listing out all selected modules: `list`

Displays list of all selected modules and slots.

Format: `list`

Example of usage:

`list`

Sample Output:
```
list
--------------------------------------------------------------------------------
CS1010	Programming Methodology
	LABORATORY=B03, SECTIONAL_TEACHING=1, TUTORIAL=01

CS2040C	Data Structures and Algorithms
	LABORATORY=02, LECTURE=1


Here's a list of your current selected module(s)!
--------------------------------------------------------------------------------

```

Possible Error:
- Empty timetable
```
list
--------------------------------------------------------------------------------
You currently have no selected module(s)!
--------------------------------------------------------------------------------
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

### Creating an NUSMod Link: `export`

Creates a sharable NUSMod Link

Format: `export`

Example of usage:

`export`

Sample Output:
```
export
--------------------------------------------------------------------------------
Here is your NUSMod Link:
	https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01
--------------------------------------------------------------------------------
```

### Importing a timetable: `import`

Import a timetable into YAMOM through a shareable NUSmod Link.

Format: `import [NUSMOD_LINK]`

* The `NUSMOD_LINK` need to be in the format of `https://nusmods.com/timetable/sem-SEMESTER_NUMBER/share?MODULE_INFO&MODULE_INFO`

Example of usage:

`import https://nusmods.com/timetable/sem-1/share?GEA1000=TUT:D01command Use YAMOM /when now`

Sample Output:
```
import https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01
--------------------------------------------------------------------------------
Timetable imported to YAMOM!
--------------------------------------------------------------------------------
```

Possible error:
- No link given
```
import 
--------------------------------------------------------------------------------
Error! 	No nusmod link given
--------------------------------------------------------------------------------
```

- Wrong link format
```
import www.google.com
--------------------------------------------------------------------------------
Error! 	Error processing NUSMod Link, Kindly ensure that the link is in the format of 
https://nusmods.com/timetable/sem-SEMESTER_NUMBER/share?MODULE_INFO&MODULE_INFO
--------------------------------------------------------------------------------
```
<!--
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
-->
> Note:

## Application Data

> Warning:
> Be careful when handling the data file, any unexpected changes in the file may lead to data loss and crash subsequent runs of YAMOM.

### Data Storage
The user data is stored in `duke.txt` under the data folder in the home directory

### Transfer to another computer
Copy the application file with the corresponding data file (remember to create the `data` folder under the home directory)

### Transfer to NUSMODs (for NUS students)
(Recommended)
Enter the command `export` and the current timetable will be exported as a sharable NUSMod Link

Alternatively:
The NUSMOD url is available in `duke.txt`, copy the content of the file and paste it in the browser to view the timetable on NUSMOD

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: YAMOM is designed with portability in mind. Simply copy the JAR file and the data folder over to your other computer and you are good to go.
Alternatively, you can export the timetable, copy the link generated, and import on the other computer

## Command summary

| Action                           | Format                                                                                         | Example                                                               |
|----------------------------------|------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|
| Add a module                     | `add [MODULE_CODE]`                                                                            | `add CS2101`                                                          |
| Delete a module                  | `delete [MODULE_CODE]`                                                                         | `delete CS2102`                                                       |
| Exit application                 | `bye`                                                                                          | `bye`                                                                 |
| Read more details about a module | `get [MODULE_CODE]`                                                                            | `get CS2103`                                                          |
| Find module by keyword           | `search /code [MODULE_CODE] /title [KEYWORD] </level [MODULE_LEVEL]> </sem [MODULE_SEMESTER]>` | `search /code cs /level 2 /sem 1`                                     |
| Seek help                        | `help`                                                                                         | `help`                                                                |
| Import modules from NUSMods URL  | `import [URL]`                                                                                 | `import https://nusmods.com/timetable/sem-1/share?CS2113=LEC:1,TUT:4` |
| Export modules to NUSMods URL    | `export`                                                                                       | `export`                                                              |
| Change semester                  | `semester [SEMESTER]`                                                                          | `semester 2`                                                          |
| View timetable                   | `view /fancy` OR `view /simple`                                                                | `view /fancy`                                                         |
| List selected modules            | `list`                                                                                         | `list`                                                                |
| Add module timetable slot        | `select /module <MODULE_CODE> /type <LESSON_TYPE> /code <CLASS_NO>`                            | `select /module CS1010 /type tutorial /code 1`                        |


## Credits
Credits to Owen Leong (owenl131), Hao Yi (CheahHaoYi), Koh Ngiap Hin (konnh) for snippets of code from their Individual Projects to be integrated into YAMOM
