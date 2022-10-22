# User Guide

## Introduction

ComputerComponentChooser(CCC) is a Command Line Interface(CLI) 
program that simplifies the tracking of computer builds and 
help users ensure that the parts they pick are 
compatible with one another.

## Quick Start

1. Ensure that you have `Java 11` installed.
2. Download the latest version of `CCC` from 
[here](https://github.com/AY2223S1-CS2113T-W11-2/tp/releases).
3. Copy the file to an empty folder you want to use as the _home folder_
4. Open a command window in that folder
5. Run the command `java -jar CCC.jar` to start the app
6. Refer to the [Features](#features) below to try out some commands!

## Features 

Upon starting the Program, the user will be situated in the main mode.

### Main Mode

Main mode handles the management of the user's builds. 

#### Adding a build: `add`

Adds a build to the list of builds currently managed by the program. The user only needs to specify the name of the
build.

Format: `add/NAME`

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Upon exit of the program, your data is stored in the data folder. Simply copy the contents of the data folder 
to the data folder of the new program on your new computer to carry over your data.

## Command Summary
### Main Mode

| Action         | Format, Examples                                                                               |
|----------------|------------------------------------------------------------------------------------------------|
| Add            | `add/NAME` <br> E.g. `add/build1`                                                              |
| Edit           | `edit/NAME` <br> E.g. `edit/build1`                                                            |
| Delete         | `delete/NAME` <br> E.g. `delete/build1`                                                        |  
| View           | `view/NAME` <br> E.g.  `view/build1`                                                           |
| List           | `list`                                                                                         |
| Find           | `find/KEYWORD` <br> E.g.  `find/build`                                                         |
| Filter         | `filter/TYPE/RANGESTART/RANGEEND` E.g. <br>  `filter/compatibility` <br>  `filter/price/1000/3000` |
| Export         | `export`                                                                                       |
| ExportCSV      | `exportCSV`                                                                                    |
| Bye            | `bye`                                                                                          |

### Edit Mode

| Action    | Format, Examples                                                                           |
|-----------|--------------------------------------------------------------------------------------------|
| Add       | `add/TYPE/NAME/PRICE/POWER/PARAM/PARAM/...` <br> E.g. `add/powersupply/Corsair PSU/200/860` |
| Delete    | `delete/TYPE/NAME` <br> E.g. `delete/powersupply/Corsair PSU`                              |  
| View      | `view/TYPE/NAME` <br> E.g. `view/powersupply/Corsair PSU`                                      |
| List      | `list`                                                                                     |
| Check     | `check`                                                                                    |
| Info      | `info`                                                                                     |
| Export    | `export`                                                                                   |
| Back      | `back`                                                                                     |

