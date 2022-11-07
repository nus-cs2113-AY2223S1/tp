# User Guide

## <span style="color:orange ">Introduction</span>

Timetabler is a **desktop app** that allows **NUS students** taking official NUS modules to **view their classes** and **plan their timetable efficiently** in AY22/23. The desktop app is optimmized for use via a Command Line Interface (CLI).


## <span style="color:orange ">Quick Start</span>

1. Ensure that you have Java `11` or above installed.
2. Down the latest version of `tp` from [here](https://github.com/AY2223S1-CS2113-T17-3/tp/releases/).
3. Copy the jar file into an empty folder.
4. Open a command window or terminal for mac in that folder.
5. Run the command `java -jar tp.jar` in the same folder.
6. You will be prompted to enter the `semester` (1 or 2) you are planning for when the program first starts.
7. Note that you should not modify the files created by the program unless you are certain of what you are doing. In
   the event that the files have been incorrectly modified, see the Appendix below for help.


##  <span style="color:orange ">Features</span>


* [Add module](#adding-a-module-add) `add`
* [List all modules](#listing-modules-list) `list`
* [Find info on a module](#finding-information-on-a-module-info) `info`
* [Set lesson for a module](#setting-a-modules-lessons-set) `set`
* [Delete a module](#deleting-a-module-add) `delete`
* [Auto-allocate lessons](#auto-allocating-all-modules-allocate) `allocate`
* [Print timetable](#printing-the-timetable-print) `print`
* [Quit program](#quitting-the-program-quit) `quit`


###  <span style="color:orange ">Adding a module: `add`</span>
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
###  <span style="color:orange ">Listing modules: `list`</span>
Command used to list out all modules added to user's timetable.

* Modules with duplicate lesson types means that students are expected to go for at least that number of classes of that lesson type.

* For example: 2 Lecture slots means that students are expected to attend 2 different lecture slots.

* Note that certain lessons are fixed and will be displayed without the need to `set` or `allocate` them.

**Example of Usage:**
input: `list`

```
Here are your modules:
1. CS2040C: Data Structures and Algorithms
     [Lecture 1] Wednesday   10:00 - 12:00   Weeks: 1,2,3,4,5,6,7,8,9,10,11,12,13
     [Lecture 2] Thursday   17:00 - 18:00   Weeks: 1,2,3,4,5,6,7,8,9,10,11,12,13
     [Laboratory] Undetermined Day   Undetermined Time - Undetermined Time   Weeks: NA
```
---
###  <span style="color:orange ">Finding information on a module: `info`</span>
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
###  <span style="color:orange ">Setting a module's lessons: `set`</span>
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
1. Laboratory  
```
input: `1`
```
Which is your preferred timeslot? Enter corresponding valid index
1:
Monday   10:00 - 12:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13
2:
Monday   16:00 - 18:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13
3:
Tuesday   10:00 - 12:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13
4:
Tuesday   12:00 - 14:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13
5:
Tuesday   16:00 - 18:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13
6:
Tuesday   14:00 - 16:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13
7:
Monday   12:00 - 14:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13
8:
Monday   14:00 - 16:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13
```
input: `1`
```
Successfully set your lesson!
```
---
###  <span style="color:orange ">Deleting a module: `delete`</span>
Command used to initiate the process of deleting a new module.

* You will be prompted to choose the module to delete after this command.

**Example of Usage:**
input: `delete`

```
Which module you would like to delete? Please enter the index of that module. 
Here are your modules:
1. CS2040C: Data Structures and Algorithms
     [Lecture 1] Wednesday   10:00 - 12:00   Weeks: 1,2,3,4,5,6,7,8,9,10,11,12,13
     [Lecture 2] Thursday   17:00 - 18:00   Weeks: 1,2,3,4,5,6,7,8,9,10,11,12,13
     [Laboratory] Monday   16:00 - 18:00   Weeks: 3,4,5,6,7,8,9,10,11,12,13

```
input: `1`
```
Successfully deleted module!
```
---
###  <span style="color:orange ">Auto-allocating all modules: `allocate`</span>
Command used to initiate the process of automatically allocating modules.

* Modules that have a large number of possible lesson permutations are not supported and the `allocate` command will return
a string telling the user to manually set those lessons before running the command again.
* In the case where it is impossible to have a permutation of lessons such that there is no clash, the user will be notified and the lessons will not be allocated.
* Users can still choose to manually set the modules to clash with the command `set`.
* Lessons which have already been set by the user or automatically set due to it being a fixed lesson will not be altered. Hence, if a user manually sets a clash,
the `allocation` command will not resolve such clashes.
* As the number of permutations that the program has to consider can be very large, some time may be needed for the program to run.
If no error messages are shown, the program is still running as expected.

**Example of Usage:** input: `allocate`
* Successful allocation with no clashes:
```
All your mods have been successfully allocated!
```

* Allocation with some clashes:
```
Sorry we were unable to allocate timings for these modules due to timetable clashes:
CDE2000 (Tutorial)
Please rearrange some of your modules and try again!
```

* Modules has too many possible lessons:
```
Sorry these modules have too many lessons for us to check, please manually allocate them before trying again:
MA2001 (Linear Algebra I)
```

---
###  <span style="color:orange ">Printing the timetable: `print`</span>
Command used to produce a timetable for a straightforward view.

* Legend and other things to take note of will be printed below the timetable.
* Only lessons which are correctly set will be reflected in the timetable.
* Clashes are indicated with `XXXXXX` in place of the module code. At the end of the timetable, clashed modules will be listed out, if any.
  Alternatively, you may use `list` command to find the clash and deconflict with `set` feature.

**Example of Usage:**
input: `print`

```              :              :              :              :              :               
              : MON          : TUE          : WED          : THU          : FRI           
==============:==============:==============:==============:==============:===============
   0800       :              :              :XXXXXXXXXXXXXX:              :               
   0830       :              :              :XXXXXXXXXXXXXX:              :               
   0900       :              :              :XXXXXXXXXXXXXX:              :               
   0930       :              :              :XXXXXXXXXXXXXX:              :               
   1000       :              :              :XXXXXXXXXXXXXX:              :               
   1030       :              :              :              :              :               
   1100       :              :              :              :              :               
   1130       :              :              :              :              :               
   1200       :              :              :              :--------------:               
   1230       :              :              :              :CS1231        :               
   1300       :              :              :              : SEC          :               
   1330       :              :              :              :              :               
   1400       :              :              :              :--------------:               
   1430       :              :              :              :              :               
   1500       :              :              :              :              :               
   1530       :              :              :              :              :               
   1600       :              :              :              :              :-------------- 
   1630       :              :              :              :              :CS2113         
   1700       :              :              :--------------:              : LEC           
   1730       :              :              :CS2113 TUT    :              :               
   1800       :              :              :--------------:              :-------------- 
   1830       :              :              :              :              :               
   1900       :              :              :              :              :               
   1930       :              :              :              :              :               
   2000       :              :              :              :              :               
   2030       :              :              :              :              :               
   2100       :              :              :              :              :               
   2130       :              :              :              :              :               
   2200       :              :              :              :              :               

These are the clashed modules : 
CS1231
ES2631

 * Note that timings indicated refers to the start of the corresponding 30 minutes timeslot.
 * Slots with XXXXXX indicates that there is a clash between two or more lessons.
 * Modules, if any, that start before 8am or ends after 10pm timings are excluded.
 * Timings are approximated to 30 minutes block with valid assumption that NUS mods are typically designed in such blocks.
```
---
###  <span style="color:orange ">Quitting the program: `quit`</span>

Command used to exit the program and stop its execution.

**Example of usage**
input: `quit`

``` 
 _____            __   __             ___              _       _ 
/  ___|           \ \ / /            / _ \            (_)     | |
\ `--.  ___  ___   \ V /___  _   _  / /_\ \ __ _  __ _ _ _ __ | |
 `--. \/ _ \/ _ \   \ /  _ \| | | | |  _  |/ _` |/ _` | | '_ \| |
/\__/ /  __/  __/   | | (_) | |_| | | | | | (_| | (_| | | | | |_|
\____/ \___|\___|   \_/\___/ \__,_| \_| |_/\__, |\__,_|_|_| |_(_)
                                            __/ |                
                                           |___/                 
```

##  <span style="color:orange ">FAQ</span>

**Q**: How do I transfer my data to another computer?

**A**: Copy all data files `Sem1Data` or / and `Sem2Data` together with the jar file to the new computer, in the same directory.

##  <span style="color:orange ">Feature Summary</span>
| Feature                            |   Input    |
|------------------------------------|:----------:|
| adding a module                    |   `add`    |
| listing all added modules          |   `list`   | 
| finding information about a module |   `info`   |
| setting the lesson for a module    |   `set`    |
| deleting a module                  |  `delete`  |
| auto allocation of lessons         | `allocate` |
| printing the timetable             |  `print`   |
| exiting the program                |   `quit`   |


## <span style="color:orange ">Appendix</span>

###  <span style="color:orange ">Dealing with corrupted files / loss of internet connection on startup</span>

In the event that either of these situations occur, a prompt will be displayed when the program is launched. This gives
you the option to try to rerun the program, or delete the files that are currently saved in your system.

This is how the prompt will look like:
```
Oops! Something went wrong. Either a file is corrupted or you are not connected to the internet.
Enter 0 if you are sure you have internet connection - files will be deleted and recreated before continuing.
Enter 1 if you have no internet connection - program will quit without losing data.
```


Entering `0` will cause the program to delete all the files saved in your system and allows you to start the
program in a clean state.


Entering `1` will exit the program without deleting the saved files, allowing you try to run the program again.

**Note**: Although a data corruption protocol exists, it is beyond the scope of the product for users to illegally tamper with the files.