# User Guide for Yet Another Module Organizer/Manager (YAMOM) v1.0

## Introduction

YAMOM is a lightweight, simplified Module Organizer and Manager application that provides simple and intuitive interface for timetable organization. YAMOM is more oriented towards users who are proficient in Command Line Interface (CLI).
{Give a product intro}

## Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features)
    1. [Getting help: `help`](#getting-help-help)
    2. [Feature: `COMMAND`](#feature-x-command)
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
> - Words in `[BRACKETS]` are the parameters to be supplied by the user.
    e.g. in `event [NAME] /at [DATE]`, `[NAME]` and `[DATE]` are parameters which can be used in the form of `event Alibaba Shopping /at 2022-11-11`.
> - Extraneous parameters for commands that do not take in parameters will be ignored.

### Getting help: `help`

Displays the list of commands available by Duke.

Format: `help`

Example of usage:
`help`

Sample Output:

### Feature X: `COMMAND`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

Sample Output:

Possible Error:

## Application Data

### Data Storage

### Transfer to another computer

### Transfer to NUSMODs (for NUS students)

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: YAMOM is designed with portability in mind. Simply copy the JAR file and the data folder over to your other computer and you are good to go.

## Command summary

| Action                | Format                       | Example                                |
|-----------------------|------------------------------|----------------------------------------|
| Add a deadline        | `deadline [TASK] /by [DATE]` | `deadline team project /by tomorrow`   |
| Add an event          | `event [TASK] /at [DATE]`    | `event April Fool's Day /at 1st April` |
| Add a Todo            | `todo [TASK]`                | `todo Do Luminus Quiz`                 |
| Delete a task         | `delete [INDEX]`             | `delete 1`                             |
| Exit application      | `bye`                        | `bye`                                  |
| Find tasks by keyword | `find [KEYWORD]`             | `find assignment`                      |
| View help             | `help`                       | `help`                                 |
| List tasks            | `list`                       | `list`                                 |
| Mark a task as done   | `mark [INDEX]`               | `mark 1`                               |
| Mark a task as undone | `unmark [INDEX]`             | `unmark 1`                             |


## Credits
Credits to Owen Leong (owenl131), Hao Yi (CheahHaoYi)
