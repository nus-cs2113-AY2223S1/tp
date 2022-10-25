# User Guide

## Introduction

easySEP is a Command Line Interface application that allows you to search for SEP module mappings for CS modules from universities all around the world.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `easySEP` from [here](http://link.to/duke).
3. Open your command prompt to the folder where you saved the .jar file
4. Run java -jar easySEP.jar

## Features
### Create Command: `/create u/{UNIVERSITY_NAME}`

Creates a university list and the corresponding timetable for user's specified university.


### Exit Command: `/exit`

Exits easySEP


### Help Command: `/help`

Prints out all the commands and command format required for easySEP


### Add Command: `/add`

Has a total of 2 functionalities: adding a lesson to the partner university timetable or adding a module mapping to the university list.

#### Add Command for Lessons: `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE} d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}`

Adds a lesson for the specified module code to the timetable for the specified university with given start time and end time on the specified day of the week.

#### Add Command for Module Mapping: `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE}`

Adds module to university list.


### Delete Command: `/delete`

Has a total of 3 functionalities: deleting a lesson from timetable, deleting a module from a university list or deleting the entire university list.

#### Delete Command for Lessons: `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE} d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}`

Deletes the specified lesson from the university's timetable

#### Delete Command for Modules: `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE}`

Deletes the specified module from the university's list

#### Delete Command for Universities: `/delete u/{UNIVERSITY_NAME}`

Deletes the university list for the specified university


### View Command: `/view`

Performs a total of 4 functionalities: displays all user created university lists, displays user's delete history, displays user's selected university list and timetable and displays all user's timetables.

#### View Command for all user university lists: `/view LISTS`

Displays all universities lists created by user.

#### View Command for user's delete history: `/view DELETE_HISTORY`

Displays delete history of the user.

#### View Command for user's selected university: `/view u/{UNIVERSITY_NAME}`

Displays the created list and timetable for the specified university.

#### View Command for all timetables created by user: `/view TIMETABLES`

Displays all timetables for universities lists created by user.


### List Command: `/list`

Performs a total of 4 functionalities: displays all universities, displays all module mappings, filter module mappings based on NUS module code, or filter module mappings based on Partner University name.

#### List Command for all universities: `/list UNIVERSITIES`

Displays all universities with module mappings available in database

Example of usage:

`/list UNIVERSITIES`

#### List Command for all modules : `/list MODULES`

Displays all existing university modules mappings that are approved in the following format:

`[Partner University Module Code] [Partner University Module Title] [Partner University Module Credits] | [NUS Module Code] [NUS Module Title] [NUS Module Credits] in NUS`

Example of usage:

`/list MODULES`

#### Filtered List Command by module: `/list m/MODULECODE`

List all module mappings for NUS MODULECODE in database

Format: `/list m/MODULECODE`

Example of usage:

`/list m/CS2113`

#### Filtered List Command by university name: `/list u/UNIVERSITY_NAME_IN_UNDERSCORES`

List all module mappings offered by UNIVERSITY in database

Format: `/list u/UNIVERSITY_NAME_IN_UNDERSCORES`

Example of usage:

`/list u/Aalto_University`

### Favourite Command: `/favourite`

Performs a total of 3 functionalities: adding a university list to user's favourites, removing a university list from a user's favourites and viewing all the user's favourite university lists

#### Favourite Command to add a university to favourites: `/favourite add/{UNIVERSITY_NAME}`

Adds the specified university list to user's favourites.

#### Favourite Command to remove a university to favourites: `/favourite del/{UNIVERSITY_NAME}`

Removes the specified university list from user's favourites.

#### Favourite Command to view user's favourites university lists: `/favourite VIEW`

Displays all the user's favourite university lists.

## FAQ

## Command Summary

{Give a 'cheat sheet' of commands here}