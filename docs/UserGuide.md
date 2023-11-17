

# User Guide for Yet Another Module Organiser/Manager (YAMOM) v2.1

[//]: # (CAA - 29/10/2022 11:30hrs, Deen)

## Introduction

YAMOM is a lightweight, simplified Module Organizer and Manager application that provides simple and intuitive interface
for timetable organization. YAMOM is designed for users who are proficient in Command Line Interface (CLI). Module data in YAMOM is for AY22/23 and is correct as of 1 Oct 2022. 

## Table of Contents

- [User Guide for Yet Another Module Organiser/Manager (YAMOM) v2.1](#user-guide-for-yet-another-module-organisermanager-yamom-v21)
  - [Introduction](#introduction)
  - [Table of Contents](#table-of-contents)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [Seek help: `help`](#seek-help-help)
    - [Exit YAMOM: `bye`](#exit-yamom-bye)
    - [Add a module: `add`](#add-a-module-add)
    - [Remove a module: `remove`](#remove-a-module-remove)
    - [Search for Modules: `search`](#search-for-modules-search)
    - [Read more details about a module: `info`](#read-more-details-about-a-module-info)
    - [Change semester to plan: `semester`](#change-semester-to-plan-semester)
    - [View user timetable: `timetable`](#view-user-timetable-timetable)
    - [List out all selected modules: `list`](#list-out-all-selected-modules-list)
    - [Select a timetable slot: `select`](#select-a-timetable-slot-select)
    - [Export current timetable: `export`](#export-current-timetable-export)
    - [Import a timetable: `import`](#import-a-timetable-import)
  - [Application Data](#application-data)
    - [Data Storage](#data-storage)
    - [Data Loading](#data-loading)
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
6. YAMOM will always start planning for semester 1 when the application is run.
7. Data will be automatically saved after each command and persisted between runs of YAMOM.
8. What to do next?
   - First, ensure you are planning for the right [semester](#change-semester-to-plan-semester).
   - Do you have an NUSMods share link to [import](#import-a-timetable-import)?
   - If not, do you know what modules you want to add to your timetable? If so proceed to [add](#add-a-module-add) them to your timetable.
   - Otherwise, you might want to [search](#search-for-modules-search) for your desired modules and find more [info](#read-more-details-about-a-module-info) about them.
   - After you have added your modules, [select](#select-a-timetable-slot-select) your desired lecture/tutorial/lab/etc. slots. Not sure which slot to select? Head back to [info](#read-more-details-about-a-module-info) for any details you need.
   - Once done, [list](#list-out-all-selected-modules-list) your selected modules to verify your choice and view your [timetable](#view-user-timetable-timetable).
   - Added a module wrongly? No worries, just [remove](#remove-a-module-remove) it.
   - Need your timetable on mobile? Mobile devices are typically not CLI friendly, so you have no choice but to [export](#export-current-timetable-export) your data back to NUSMods.
   - If stuck or confused, remember [help](#seek-help-help) will always be given to those who ask for it. 


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
> - The first word of each command specifies the command type.
> - Words in `UPPERCASE` information to be supplied by the user.
    e.g. in `add [ MODULE_CODE ]`, `MODULE_CODE` is an expected parameter for the command, such as `add CS1010`.
> - Extraneous parameters will be rejected or ignored.
> - Parameters surrounded by square brackets, `[ ]` are required parameters.
> - Parameters surrounded by angle brackets, `< >` are optional parameters.
> - Parameters split by the pipe character `|` denotes either of the parameters can be used. Some commands support the usage of both parameters while some does not.
> - Named parameters starting with forward slash `/` such as `/module`, `/type` and `/code` in `select [ /module MODULE_CODE ] [ /type LESSON_TYPE ] [ /code CLASS_NO ]` can appear in any order.
> - Parameters must be separated by a space. For example, `search /title programming/code cs` will not give the desired result. The correct input should be `search /title programming /code cs`.
> - The parameter keyword has to come immediately after the forward slash `/`. E.g. `search /title cs` is accepted while `search / title cs` will not be accepted.
> - If a named parameter is provided multiple times, e.g. `search /title cs /title ma`, only one of the values will be used. It is not guaranteed which of the duplicates will be used. 
> - The commands and parameters are case-insensitive. E.g. `search /code cs1` is the same as `SEARCH /CODE CS1`.

### Seek help: `help`

Displays the list of functionalities available by YAMOM.

Format: `help`

Example of usage:
`help`

Sample Output:

```
Sem [1] >> help
--------------------------------------------------------------------------------
Processing "help" ...

Here are all the commands available in YAMOM!

add      : Add a module into YAMOM timetable.
bye      : Exit YAMOM.
export   : Generates an NUSMod Link to be exported to the browser.
help     : List out all commands and their respective usages in YAMOM.
import   : Imports a timetable from an NUSMod timetable sharing link.
info     : Show all details of a module.
list     : List out all the selected modules and lesson slots.
remove   : Remove a module from YAMOM timetable.
search   : List out all modules that contains a search term.
select   : Select a module lesson slot.
semester : Select another semester to plan and organize timetable.
timetable : Display current timetable.

Usage :
      add [ MODULE_CODE ]
      bye
      export
      help
      import [ NUSMODS_LINK ]
      info [ MODULE_CODE ]
      list
      remove [ MODULE_CODE ]
      search [ /code PARTIAL_MODULE_CODE | /title KEYWORD ] < /level MODULE_LEVEL > < /sem SEMESTER >
             * the search term can either be module code or a keyword in module title, or both.
             * MODULE_LEVEL and SEMESTER should be a single digit number specified in the User Guide.
      select [ /module MODULE_CODE ] [ /type LESSON_TYPE ] [ /code CLASS_NO ]
      semester [ SEMESTER ]
      timetable < /fancy | /simple >

Note: [ ] are required elements,
      < > are optional elements,
       |  denotes either of the arguments can be used.
For more detailed guide, please visit https://ay2223s1-cs2113-f11-3.github.io/tp/
--------------------------------------------------------------------------------
```

### Exit YAMOM: `bye`

Ends the programme.  
The NUSMods export link of all semesters will be displayed.

Format: `bye`

Example of usage:

`bye`

Sample Output:

```
Sem [1] >> bye
--------------------------------------------------------------------------------
Shutting down......
--------------------------------------------------------------------------------
Processing "bye" ...

These are your export links:
https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01
https://nusmods.com/timetable/sem-2/share?
https://nusmods.com/timetable/st-i/share?
https://nusmods.com/timetable/st-ii/share?
Bye bye, See you again
--------------------------------------------------------------------------------
```

> Note:
> - The user data will be processed and stored locally on the computer.
> - The user data will be available when YAMOM is activated again.

### Add a module: `add`

Adds a module from available database into the user timetable.

Format: `add [ MODULE_CODE ]`

* The `MODULE_CODE` is not case-sensitive, but has to be an exact match.

Example of usage:

`add CS1010`

`add cs2113`

Sample Output:

```
Sem [1] >> add CS2040
--------------------------------------------------------------------------------
Processing "add cs2040" ...

CS2040 has been added
--------------------------------------------------------------------------------
```

Possible Error:

1. Wrong module format:
    - The module code must be an exact match (CS2030 instead of cs203) else nothing will be added.
   
   ```
   Sem [1] >> add cs203
   --------------------------------------------------------------------------------
   Processing "add cs203" ...
   
   Error! 	Wrong format, should be: add [MODULE_CODE]
   Module is invalid! Please enter a valid module code.
   Each module of study has a unique module code consisting of a two-
   or three-letter prefix that generally denotes the discipline,
   and four digits, the first of which indicates the level of the module
   (e.g., 1000 indicates a Level 1 module and 2000, a Level 2 module).
   --------------------------------------------------------------------------------
   ```

2. More than one module:
    - Currently, YAMOM only support adding one module at a time.
   
   ```
   Sem [1] >> add CS1231 CS2101
   --------------------------------------------------------------------------------
   Processing "add CS1231 CS2101" ...
   
   Error! 	Wrong format, should be: add [MODULE_CODE]
   Unknown command, try again.
   --------------------------------------------------------------------------------
   ```

3. No module code provided:
    ```
   Sem [1] >> add
    --------------------------------------------------------------------------------
    Processing "add" ...

    Error! 	Wrong format, should be: add [ MODULE_CODE ]
    Your command is incomplete.
    --------------------------------------------------------------------------------
   ```

### Remove a module: `remove`

Removes a module from the user timetable.

Format: `remove [ MODULE_CODE ]`

* The `MODULE_CODE` is not case-sensitive, but has to be an exact match.

Example of usage:

`remove CS1010`

`remove cs3219`

Sample Output:

```
Sem [1] >> remove cs2040
--------------------------------------------------------------------------------
Processing "remove cs2040" ...

CS2040 has been deleted!
--------------------------------------------------------------------------------
```

Possible Error:

1. The module indicated is not in the currently selected list:
    ```
   Sem [1] >> remove cs3219
    --------------------------------------------------------------------------------
    Processing "remove cs3219" ...

    CS3219 does not exist in current list of selected list modules!
    --------------------------------------------------------------------------------
   ```

2. The module indicated is not a valid module:
    ```
   Sem [1] >> remove XX1234
    --------------------------------------------------------------------------------
    Processing "remove XX1234" ...
    
    Error! 	Module not found in database! Please enter a valid module code!
    --------------------------------------------------------------------------------
   ```

* The other errors for remove is similar to the command `add`, see [add](#add-a-module-add).

### Search for Modules: `search`

List out all modules that matches the module code.

Format: `search [ /code PARTIAL_MODULE_CODE | /title KEYWORD ] < /level MODULE_LEVEL > < /sem SEMESTER >`

* The `PARTIAL_MODULE_CODE` will be the module code of interest, can be partial module code.
* The `KEYWORD` will be a keyword in the module title.
* The `MODULE_LEVEL` will be the first digit of the module code (indicate level of module), an integer from 1 to 8 inclusive.
* The `MODULE_SEMESTER` will be the semester of interest, an integer from 1 to 4 inclusive.
* At least one of `/code PARTIAL_MODULE_CODE` or `/title KEYWORD` must be present for search.
* `/level MODULE_LEVEL` and `/sem SEMESTER` are optional search fields.

Example of usage:

`search /code cs`

`search /title programming /level 1`

`search /code CS /title algorithm /level 5 /sem 2`

Sample Output:

```
Sem [1] >> search /code CG /level 2
--------------------------------------------------------------------------------
Processing "search /code CG /level 2" ...

Search Result:
Total 5 module(s) found

CG2023    Signals and Systems
CG2027    Transistor-level Digital Circuits
CG2028    Computer Organization
CG2111A   Engineering Principles and Practice II
CG2271    Real-Time Operating Systems

To get full details of the module, type 'info <module code>'
-------------------------------------------------------------------------------- 
```

> Note:
> - The list of modules can get very long for a generic search.

Possible Error:

1. Module code does not have a match:
    * YAMOM will return an empty list of modules
   
    ```
    Sem [1] >> search /code XX
    --------------------------------------------------------------------------------
    Processing "search /code XX" ...
    
    Search Result:
    No module found
    --------------------------------------------------------------------------------
    ```
2. The search is not in the expected format:
    * YAMOM will prompt for the right search format

    ```   
    Sem [1] >> search ABC
    --------------------------------------------------------------------------------
    Processing "search ABC" ...

    Error!  Wrong format given, should be 
            search [ /code PARTIAL_MODULE_CODE | /title KEYWORD ] < /level MODULE_LEVEL > < /sem SEMESTER >
                 * the search term can either be module code or a keyword in module title.
                 * MODULE_LEVEL and SEMESTER should be a single digit number.
    --------------------------------------------------------------------------------
    ```

### Read more details about a module: `info`

Show all the details of a module.

Format: `info [ MODULE_CODE ]`

* The `MODULE_CODE` is not case-sensitive, but has to be an exact match.

Example of usage:

`info CS1010`

`info cs2113`

Sample Output:

```
Sem [1] >> info CS2113
--------------------------------------------------------------------------------
Processing "info cs2113" ...

Code         : CS2113
Name         : Software Engineering & Object-Oriented Programming
Description  : This module introduces the necessary skills for systematic and
               rigorous development of software systems. It covers requirements,
               design, implementation, quality assurance, and project management
               aspects of small-to-medium size multi-person software projects.
               The module uses the Object Oriented Programming paradigm.
               Students of this module will receive hands-on practice of tools
               commonly used in the industry, such as test automation tools,
               build automation tools, and code revisioning tools will be
               covered.
Credits      : 4
Department   : Computer Science
Faculty      : Computing
Workload     : [2, 1, 0, 3, 4]
Semesters    : [1, 2]
Prerequisite : CS2040C or ((CS2030 or its equivalent) and CS2040/S)
Preclusion   : CS2103, CS2103T, (CS2113T for CS2113), (CS2113 for CS2113T)
Corequisite  : CS2101 Effective Communication for Computing Professionals is
               co-requisite for CS2113T. Students exempted from CS2101 will take
               CS2113 which does not have CS2101 as co-req. Otherwise, CS2113
               and CS2113T are identical.
Schedule     : 
                                                                               
          : Mon      : Tues     : Wed      : Thur                : Fri         
===============================================================================
   1100   :          :          :          :                     +----------+  
   1130   :          :          :          :                     |CS2113    |  
   1200   :          :          +----------+                     +-TUT[4]---+  
   1230   :          :          |CS2113    |                     :             
   1300   :          :          +-TUT[1]---+                     :             
   1330   :          :          |CS2113    |                     :             
   1400   :          :          +-TUT[2]---+                     :             
   1430   :          :          :          :                     :             
   1500   :          :          :          :                     :             
   1530   :          :          :          :                     :             
   1600   :          :          :          :                     +----------+  
   1630   :          :          :          :                     |CS2113    |  
   1700   :          :          :          +----------+----------+ LEC[1]   |  
   1730   :          :          :          |CS2113    |CS2113    |          |  
   1800   :          :          :          +-TUT[3]---+-TUT[5]---+----------+  
   1830   :          :          :          :                     :             
   1900   :          :          :          :                     :             
                                                                               

--------------------------------------------------------------------------------
```

Possible Error:

1. Module code does not have a match:
    * YAMOM will throw an error message and prompt for the right module code.
   
    ```
    Sem [1] >> info XX
    --------------------------------------------------------------------------------
    Processing "info XX" ...

    Error! 	Module not found! Please enter a valid module code! Try searching if you do not remember the exact module code.
    --------------------------------------------------------------------------------
    ```
2. The module code is not given:
    * YAMOM will prompt to enter a module code.

    ```   
    Sem [1] >> info
    --------------------------------------------------------------------------------
    Processing "info" ...

    Error! 	Please enter a module code!
    --------------------------------------------------------------------------------
    ```

### Change semester to plan: `semester`

Select semester to plan for and organise.

Format: `semester SEMESTER`

* `SEMESTER` is an integer from 1 to 4, or `ST1`, or `ST2`.

note: semester 3 and 4 are used to represent special term I and II.


Example of usage:

`semester 1` to change to semester 1.

`semester ST1` to change to semester 3 (Special Term I).

`semester 3` also to change to semester 3 (Special Term I).

Sample Output:

```
Sem [1] >> semester 3
--------------------------------------------------------------------------------
Processing "semester 3" ...

You are now planning for special term I
--------------------------------------------------------------------------------
Sem [ST1] >> 
```

Possible Error:

1. User did not input a valid semester:

    ```
    Sem [1] >> semester 0
    --------------------------------------------------------------------------------
    Processing "semester 0" ...
    
    Error! 	Wrong format, should be: semester [ SEMESTER ]
    Not a valid semester.
    --------------------------------------------------------------------------------
    ```

2. User supplied too many parameters:
    ```
   Sem [1] >> semester special term 2 2
    --------------------------------------------------------------------------------
    Processing "semester special term 2 2" ...

    Error! 	Wrong format, should be: semester [ SEMESTER ]
    Not a valid semester.
    --------------------------------------------------------------------------------
   ```
   
### View user timetable: `timetable`

Prints out the user timetable for the current timetable.

Format: `timetable < /fancy | /simple >`

> Note: Fancy mode features sleek unicode characters and coloured output. Some terminals (mainly Windows terminals) do not support fancy mode. If you run `timetable /fancy` on terminals that do not support it, the output may not be readable. If neither of the options `/fancy` or `/simple` are specified, then YAMOM will attempt to decide the best view for you.

> Note: If you have too many lessons overlapping in terms of timing, the width of your timetable may overflow the window size and thus the timetable will be difficult to view. In that case, increase your window width or use the `select` command to resolve some of the timing collisions.

Example of usage:

`timetable`
`timetable /simple`
`timetable /fancy`

Sample Output:

Assuming user has CS2040 in their timetable:
```
Sem [1] >> timetable /simple
--------------------------------------------------------------------------------
Processing "timetable /simple" ...

                                                                    
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
   1330   :          :          :          :          :             
   1400   :          :          :          :          :             
   1430   :          :          :          :          :             
   1500   :          :          :          :          :             
   1530   :          :          :          :          :             
   1600   :          :          :          :          :             
   1630   :          :          :          :          :             
   1700   :          :          :          +----------+             
   1730   :          :          :          |CS2040    |             
   1800   :          :          :          +-LEC[1]---+             
   1830   :          :          :          :          :             
   1900   :          :          :          :          :             
                                                                    

--------------------------------------------------------------------------------
```

```
Sem [1] >> timetable /fancy
--------------------------------------------------------------------------------
Processing "timetable /fancy" ...

                                                                    
          : Mon      : Tues     : Wed      : Thur     : Fri         
====================================================================
   0800   :          :          :          ┌──────────┬──────────┐  
   0830   :          :          :          │CS2040    │CS2040    │  
   0900   :          :          :          └─TUT[01]──┤ LAB[1C]  │  
   0930   :          :          :          :          │          │  
   1000   :          :          ┌──────────┐          └──────────┘  
   1030   :          :          │CS2040    │          :             
   1100   :          :          │ LEC[1]   │          :             
   1130   :          :          │          │          :             
   1200   :          :          └──────────┘          :             
   1230   :          :          :          :          :             
   1300   :          :          :          :          :             
   1330   :          :          :          :          :             
   1400   :          :          :          :          :             
   1430   :          :          :          :          :             
   1500   :          :          :          :          :             
   1530   :          :          :          :          :             
   1600   :          :          :          :          :             
   1630   :          :          :          :          :             
   1700   :          :          :          ┌──────────┐             
   1730   :          :          :          │CS2040    │             
   1800   :          :          :          └─LEC[1]───┘             
   1830   :          :          :          :          :             
   1900   :          :          :          :          :             
                                                                    

--------------------------------------------------------------------------------
```
Assuming user has no modules in their timetable:
```
Sem [1] >> timetable
--------------------------------------------------------------------------------
Processing "timetable" ...

Your timetable is empty.
Please select your modules first before viewing.
--------------------------------------------------------------------------------
```

Possible Error:

1. Random parameters:

    ```
    Sem [1] >> timetable show
    --------------------------------------------------------------------------------
    Processing "timetable show" ...
    
    Error! 	Unknown command. Maybe you meant "timetable < /fancy | /simple >".
    --------------------------------------------------------------------------------
    ```

2. Forgot backslash `/`:

    ```
    Sem [1] >> timetable fancy
    --------------------------------------------------------------------------------
    Processing "timetable fancy" ...
    
    Error! 	Unknown command. Maybe you forgot a "/".
    --------------------------------------------------------------------------------
    ```

3. Contains both `/fancy` and `/simple`:

    ```
    Sem [1] >> timetable /simple /fancy
    --------------------------------------------------------------------------------
    Processing "timetable /simple /fancy" ...
    
    Error! 	Timetable cannot be both simple and fancy!
    --------------------------------------------------------------------------------
    ```

### List out all selected modules: `list`

Displays list of all selected modules and slots.

Format: `list`

Example of usage:

`list`

Sample Output:

Assuming the user has selected the CS1010 and CS2113:
```
Sem [1] >> list
--------------------------------------------------------------------------------
Processing "list" ...

CS1010   Programming Methodology
         LAB[B03]  Thur 1000-1200, COM1-B108, weeks: 3 - 13
         SEC[1]    Mon 1200-1400, I3-AUD, weeks: 1 - 13
         TUT[01]   Wed 0800-0900, COM1-0201, weeks: 3 - 13

CS2113   Software Engineering & Object-Oriented Programming
         LEC[1]    Fri 1600-1800, LT19, weeks: 1 - 13
         TUT[4]    Fri 1100-1200, COM1-0210, weeks: 3 - 13


Here's a list of your current selected module(s)!
--------------------------------------------------------------------------------
```
Assuming empty list of modules:
```
Sem [1] >> list
--------------------------------------------------------------------------------
Processing "list" ...

You currently have no selected module(s)!
--------------------------------------------------------------------------------
```

Possible Error:

1. Additional parameters:

    ```
    Sem [1] >> list modules
    --------------------------------------------------------------------------------
    Processing "list modules" ...
    
    Error! 	0 arguments expected
    --------------------------------------------------------------------------------
    ```

### Select a timetable slot: `select`

Selects a timeslot to be added to the user timetable.

Format: `select [ /module MODULE_CODE ] [ /type LESSON_TYPE ] [ /code CLASS_NO ]`

* The `CLASS_NO` will be an alphanumeric String. This needs to be an exact match and is not case-sensitive. For example, if the code is `03`, then `/code 3` will not be accepted. Similarly, for code `T01`, `/code T1`, `/code 01` and `/code 1` will not be accepted, while `/code T01` and `/code t01` are valid.
* The `LESSON_TYPE` can be any of the following spelt out or in short form (not case-sensitive). Here are some non-exhaustive examples:  
  *  TUTORIAL                   e.g. *tut, tutorial*
  *  TUTORIAL_TYPE_2            e.g. *tut2, tutorial2*
  *  LECTURE                    e.g. *lecture, lec*
  *  RECITATION                 e.g. *rec, recitation*
  *  DESIGN_LECTURE             e.g. *dlec, design_lecture*
  *  PACKAGED_LECTURE           e.g. *plec, packaged_lecture*
  *  PACKAGED_TUTORIAL          e.g. *ptut, packaged_tutorial*
  *  SECTIONAL_TEACHING         e.g. *sec, sectional*
  *  WORKSHOP                   e.g. *workshop, wksh*
  *  LABORATORY                 e.g. *lab, laboratory*
  *  MINI_PROJECT               e.g. *proj, mini_project*
  *  SEMINAR_STYLE_MODULE_CLASS e.g. *sem, seminar, seminar_style_module*
* The `LESSON_TYPE` need not be an exact match, and is not case-sensitive.
* The `MODULE_CODE` need to be an exact match.

Example of usage:

`select /module CS2040 /type lecture /code 1`

Sample Output:

```
Sem [1] >> select /module CS2040 /type lecture /code 1
--------------------------------------------------------------------------------
Processing "select /module CS2040 /type lecture /code 1" ...

Slot selected successfully!
--------------------------------------------------------------------------------
```

Possible Error:

1. The module code is not in the list of selected modules (assuming that cs2040 is not in the list of selected modules):

    ```
    Sem [1] >> select /module CS2040 /type lecture /code 2
    --------------------------------------------------------------------------------
    Processing "select /module CS2040 /type lecture /code 2" ...

    Error!  You need to add the module to your timetable first. Use the add command.
        e.g. add CS2040
    --------------------------------------------------------------------------------
    ```

2. The module code is in the list of selected modules, but the lesson type and class no is not valid:

    ```
    Sem [1] >> select /module CS2040 /type lecture /code 2
    --------------------------------------------------------------------------------
    Processing "select /module CS2040 /type lecture /code 2" ...
    
    Error!  Class code 2 does not exist for LECTURE for CS2040 in semester 1
    --------------------------------------------------------------------------------
    ```

3. The search is not in the expected format:

    ```
    Sem [1] >> select /mod CS2040 /type lecture /number 2 
    --------------------------------------------------------------------------------
    Processing "select /mod CS2040 /type lecture /number 2" ...
    
    Error! 	Wrong format given, should be 
        select [ /module MODULE_CODE ] [ /type LESSON_TYPE ] [ /code CLASS_NO ]
        Missing parameter /module
    --------------------------------------------------------------------------------
    ```

### Export current timetable: `export`

Creates a sharable NUSMod Link.

Format: `export`

Example of usage:

`export`

Sample Output:

```
Sem [1] >> export
--------------------------------------------------------------------------------
Processing "export" ...

Here is your NUSMod Link:
      https://nusmods.com/timetable/sem-1/share?CS2040=LAB:1C,LEC:1,TUT:01
--------------------------------------------------------------------------------
```

### Import a timetable: `import`

Import a timetable into YAMOM through a shareable NUSMod Link. The current semester will be 
set to the semester indicated in the link provided (only for `import` command).

Format: `import [ NUSMOD_LINK ]`

* The `NUSMOD_LINK` need to be in the format
  of `https://nusmods.com/timetable/[sem|st]-SEMESTER_NUMBER/share?MODULE_INFO&MODULE_INFO`
* For normal semesters, `sem`, `SEMESTER_NUMBER` ranges from 1 to 2 included.
* For special terms, `st`, `SEMESTER_NUMBER` is either `i` or `ii` to represent special term 1 and 2 respectively.
* Information about different modules (i.e. `MODULE_INFO`) are separated by `&`.
* `MODULE_INFO` consists of `MODULE_CODE=LESSONS_INFO`.
* `LESSONS_INFO` consists of `LESSON_TYPE_SHORT_FORM:LESSON_NUMBER` which are separated by `,`.
* `LESSON_TYPE_SHORT_FORM` can be the following:
  * `TUT`  representing TUTORIAL
  * `TUT2` representing TUTORIAL_TYPE_2
  * `LEC`  representing LECTURE
  * `REC`  representing RECITATION
  * `DLEC` representing DESIGN_LECTURE
  * `PLEC` representing PACKAGED_LECTURE
  * `PTUT` representing PACKAGED_TUTORIAL
  * `SEC`  representing SECTIONAL_TEACHING
  * `WKSH` representing WORKSHOP
  * `LAB`  representing LABORATORY
  * `PROJ` representing MINI_PROJECT
  * `SEM`  representing SEMINAR_STYLE_MODULE_CLASS  
  note: other lesson types are currently not supported. Preliminary checks based on the first 4 characters have been implemented to ensure that 
  * `LESSON_TYPE_SHORT_FORM` is of the above-mentioned form. E.g. `TUTA` will be accepted as representing TUTORIAL.
* `LESSON_NUMBER` can vary and is not of a certain form. Take note `01` is not the same as `1`. 
* If `SEMESTER_NUMBER` is incorrect, the whole link will not be parsed.
* If `MODULE_CODE` is incorrect, that module will not be added.
* If there are duplicated `MODULE_CODE`, only the first module information will be parsed.
* If `LESSONS_INFO` is incorrect, that lesson will not be added.
* If there are duplicated `LESSON_TYPE` within the lesson information, only the last lesson 
information of that `LESSON_TYPE` will be saved although all will be parsed.
* The other parts of the link that is valid will still be parsed and a success message will be displayed.

Example of usage:

`import https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01`

Sample Output:

```
Sem [1] >> import https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01
--------------------------------------------------------------------------------
Processing "import https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01" ...

Semester 1 timetable imported.
CS1010 added.
The following lessons were added:
LABORATORY:B03
SECTIONAL_TEACHING:1
TUTORIAL:01

Please check that the format of the link provided is correct if there are missing modules or lessons.
Please visit https://ay2223s1-cs2113-f11-3.github.io/tp/UserGuide.html#import-a-timetable-import
for more information.
Timetable imported to YAMOM!
--------------------------------------------------------------------------------
```

Possible error:

1. No link given:

    ```
    Sem [1] >> import 
    --------------------------------------------------------------------------------
    Processing "import" ...
    
    Error! 	No NUSMod link given.
    --------------------------------------------------------------------------------
    ```

2. Wrong link format:

    ```
    Sem [1] >> import www.google.com
    --------------------------------------------------------------------------------
    Processing "import www.google.com" ...
    
    Error! 	The link you supplied is not valid.
    --------------------------------------------------------------------------------
    ```

## Application Data

> Warning:
> Be careful when handling the data file, any unexpected changes in the file may lead to data loss, 
> although it will not crash subsequent runs of YAMOM.

### Data Storage

The user data will be saved in the same directory where the jar file is being run from. The user **should not** modify the data file in any way. The data is stored as NUSMods links for each semester. The data will be overwritten each time so there will not be any persistent corrupt data file.

### Data Loading

The data of the previous saved state in the form of links will be loaded every time the application starts.
How the links in the data file is parsed is similar to command `import` just without having to use the 
keyword `import`, see [import](#import-a-timetable-import).

### Transfer to another computer

Copy the application file with the corresponding `duke.txt` data file and `data` folder. A new file along with the respective folder will be created if either is missing.

### Transfer to NUSMODs (for NUS students)

(Recommended)
Enter the command `export` and the current timetable will be exported as a sharable NUSMod Link. 
Or you can close the programme by inputting `bye` and the links for all semesters will be provided.

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: YAMOM is designed with portability in mind. Simply copy the JAR file and the data folder over to your other
computer, and you are good to go.
Alternatively, you can export the timetable, copy the link generated, and import on the other computer.

## Command summary

| Action                           | Format                                                                                                            | Example                                                               |
|----------------------------------|-------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|
| Add a module                     | `add [ MODULE_CODE ]`                                                                                             | `add CS2101`                                                          |
| Remove a module                  | `remove [ MODULE_CODE ]`                                                                                          | `remove CS2102`                                                       |
| Exit application                 | `bye`                                                                                                             | `bye`                                                                 |
| Read more details about a module | `info [ MODULE_CODE ]`                                                                                            | `info CS2103`                                                         |
| Find module by keyword           | <code>search [ /code PARTIAL_MODULE_CODE &#124; /title KEYWORD ] < /level MODULE_LEVEL > < /sem SEMESTER ></code> | `search /code cs /level 2 /sem 1`                                     |
| Seek help                        | `help`                                                                                                            | `help`                                                                |
| Import modules from NUSMods URL  | `import [ NUSMODS_LINK ]`                                                                                         | `import https://nusmods.com/timetable/sem-1/share?CS2113=LEC:1,TUT:4` |
| Export modules to NUSMods URL    | `export`                                                                                                          | `export`                                                              |
| Change semester                  | `semester [ SEMESTER ]`                                                                                           | `semester 2`                                                          |
| View timetable                   | <code>timetable < /fancy &#124; /simple ></code>                                                                  | `timetable`                                                           |
| List selected modules            | `list`                                                                                                            | `list`                                                                |
| Add module timetable slot        | `select [ /module MODULE_CODE ] [ /type LESSON_TYPE ] [ /code CLASS_NO ]`                                         | `select /module CS1010 /type tutorial /code 1`                        |


## Credits

Credits to Owen Leong (owenl131), Hao Yi (CheahHaoYi), Koh Ngiap Hin (konnh) for snippets of code from their Individual
Projects to be integrated into YAMOM
