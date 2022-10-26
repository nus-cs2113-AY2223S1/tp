# User Guide

## Introduction

Timetabler is an app that allows NUS students taking official NUS modules to view their classes and plan their timetable efficiently in AY22/23.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `tp` from [here](http://link.to/duke).
3. Copy the jar file into an empty folder.
4. Open a command window or terminal for mac in that folder.
5. Run the command `java -jar tp.jar` in the same folder.
6. You will be prompted to enter the `semester` (1 or 2) you are planning for when the program first starts.


## Features

### Adding a module: `add`
Command used to initiate the process of adding a new module.

* You will be prompted to enter the module code after this command.
* The module code is not case sensitive but must be of an official NUS module.

**Example of Usage:**
input: `add`

```
Please enter module code
```
input: `cs2040c`
```
Successfully added new module: CS2040C : Data Structures and Algorithms
```
---
### Listing modules: `list`
Command used to list out all modules added to user's timetable.

* Modules with duplicate lesson types means that students are expected to go for at least that number of classes of that lesson type.

*For example: 2 Lecture slots means that students are expected to attend 2 different lecture slots.*

* The app does not restrict users from leaving empty lesson slots or other unoffical lesson combinations. This gives users freedom and caters to their unique situations.

**Example of Usage:**
input: `list`

```
Here are your modules:
1. CS2040C: Data Structures and Algorithms
     [Lecture] Undetermined Day   Undetermined Time - Undetermined Time
     [Lecture] Undetermined Day   Undetermined Time - Undetermined Time
     [Laboratory] Undetermined Day   Undetermined Time - Undetermined Time
```
---
### Finding information on a module: `info`
Command used to see information on a module.

* Only module name, module code and description will be shown.

**Example of Usage:**
input: `info`

```
Please enter module code
```
input: `cs1010`
```
Here are some information about the module:
Module Code: CS1010
Module Name: Programming Methodology
Module Description: This module introduces the fundamental concepts of problem solving by computing and programming using an imperative programming language. It is the first and foremost introductory course to computing.  Topics covered include computational thinking and computational problem solving, designing and specifying an algorithm, basic problem formulation and problem solving approaches, program development, coding, testing and debugging, fundamental programming constructs (variables, types, expressions, assignments, functions, control structures, etc.), fundamental data structures (arrays, strings, composite data types), basic sorting, and recursion.
```
---
### Setting a module's lessons: `set`
Command used to initiate the process of setting lesson for a module.

* Only lessons of modules which are already added can be set.
* Duplicates can be set and lesson slots can be left empty to cater to students' unique situations.
* Only one lesson can be set at a time.
* Follow the prompts at each stage of the setting process carefully and only enter valid inputs.

**Example of Usage:**
input: `set`

```
Which module would you like to set lessons for? Enter corresponding valid index
1. CS2040C : Data Structures and Algorithms
```
input: `1`
```
Which lesson type do you want to set? Enter corresponding valid index
1. Lecture     2. Lecture     3. Laboratory     
```
input: `2`
```
Which is your preferred timeslot? Enter corresponding valid index
1. [Lecture] Wednesday   10:00 - 12:00
2. [Lecture] Thursday   17:00 - 18:00
```
input: `1`
```
Successfully set your lesson!
```
---
### Deleting a module: `add`
Command used to initiate the process of deleting a new module.

* You will be prompted to choose the module to delete after this command.

**Example of Usage:**
input: `delete`

```
Which module you would like to delete? Please enter the index of that module. 
Here are your modules:
1. CS2040C: Data Structures and Algorithms
     [Lecture] Undetermined Day   Undetermined Time - Undetermined Time
     [Lecture] Wednesday   10:00 - 12:00
     [Laboratory] Undetermined Day   Undetermined Time - Undetermined Time

```
input: `1`
```
Successfully deleted module!
```
---
### Auto-allocating all modules: `allocate`
Command used to initiate the process of automatically allocating modules.

* Modules are allocated in the order they were added / listed as per the command `list`.
* In the case where it is impossible to have a permutation of lessons such that there is no clash, the user will be notified and the lessons will not be allocated.
* Users can still choose to manually set the modules to clash with the command `set`.

**Example of Usage:**
input: `allocate`

```
All your mods have been successfully allocated!
```
---
### Printing the timetable: `print`
Command used to initiate the process of deleting a new module.

* Legend and other things to take note of will be printed below the timetable.
* Only lessons which are correctly set will be reflected in the timetable.
* Clashes are indicated with `XXXXXX` in place of the module code. Use `list` command to find the clash and deconflict with `set` feature.

**Example of Usage:**
input: `print`

```
    | 0800 | 0830 | 0900 | 0930 | 1000 | 1030 | 1100 | 1130 | 1200 | 1230 | 1300 | 1330 | 1400 | 1430 | 1500 | 1530 | 1600 | 1630 | 1700 | 1730 | 1800 | 1830 | 1900 | 1930 |
MON |______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|
TUE |______|______|______|______|CS2040|CS2040|CS2040|CS2040|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|
WED |______|______|______|______|CS2040|CS2040|CS2040|CS2040|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|
THU |______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|XXXXXX|XXXXXX|______|______|______|______|
FRI |______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|CS2113|CS2113|CS2113|CS2113|______|______|______|______|

 * Note that timings indicated refers to the start of the corresponding 30 minutes timeslot.
 * Slots with XXXXXX indicates that there is a clash between two or more lessons.
 * Modules, if any, that start or end beyond the 8am to 8pm timings are excluded.
 * Timings are approximated to 30 minutes block with valid assumption that NUS mods are typically designed in such blocks.
```
---

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy all data files `Sem1DataDirectory` or / and `Sem2DataDirectory` together with the jar file to the new computer, in the same directory.

## Command Summary

* Add module `add`
* List all modules `list`
* Find info on a module `info`
* Set lesson for a module `set`
* Delete a module `delete`
* Auto-allocate lessons `allocate`
* Print timetable `print`
* Quit program `quit`