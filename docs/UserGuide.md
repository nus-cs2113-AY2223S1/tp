# User Guide

## Introduction

easySEP is a CLI application created to assist NUS Computer Engineering undergraduates intending to embark on a Student Exchange Programme in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones
for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `easySEP` from [here](https://github.com/AY2223S1-CS2113-W13-2/tp/releases).
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

Performs a total of 3 functionalities: view all lists that are marked as favourites, add a new list to favourites and delete a list from favourites.

#### Favourite Command for viewing all favourited lists: `/favourite VIEW`

Displays all lists that are marked as favourites with their associated module mappings.

Example of usage:

`/favourite VIEW`

#### Favourite Command for adding a list to favourites : `/favourite add/UNIVERSITY_NAME_IN_UNDERSCORES`

Adds a list of module mappings for a particular partner university into favourites. This list of module mappings for the partner university
must already be created beforehand in order to be added into favourites.

Example of usage:

`/favourite add/Boston_University`

#### Favourite Command for deleting a list from favourites : `/favourite del/UNIVERSITY_NAME_IN_UNDERSCORES`

Deletes a list of module mappings for a particular partner university from favourites. This list of module mappings for the partner university
must already be marked as favourite beforehand in order to be deleted from favourites.

Example of usage:

`/favourite del/Boston_University`

## FAQ

## Command Summary

| Command   | Format                                                | Purpose                                                                                                                                                                                                                                                     |
| --------- | ----------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| help      | /help                                                 | Displays eligible user commands for the program                                                                                                                                                                                                             |
| create    | /create u/UNIVERSITY_NAME_IN_UNDERSCORES              | Creates an empty module list for the input university                                                                                                                                                                                                       |
| favourite | /favourite add/UNIVERSITY_NAME_IN_UNDERSCORES         | Adds a university list to the user's favourites                                                                                                                                                                                                             |
| favourite | /favourite del/UNIVERSITY_NAME_IN_UNDERSCORES         | Deletes a university list from the user's favourites                                                                                                                                                                                                        |
| favourite | /favourite view/                                      | View the user's favourite university lists                                                                                                                                                                                                                  |
| exit      | /exit                                                 | Terminate the program                                                                                                                                                                                                                                       |
| view      | /view LISTS                                           | Displays all existing university lists that have been created by the user                                                                                                                                                                                   |
| view      | /view u/UNIVERSITY_NAME_IN_UNDERSCORES                | Displays all the modules that have been added to the user's input university's list in the format: [Home University Module Code] [Home University Module Title] [Partner University Module Code] [Partner University Module Title] [Equivalent NUS Credits] |
| view      | /view DELETE_HISTORY                                  | Displays up to 5 most recent modules that the user has deleted                                                                                                                                                                                              |
| list      | /list MODULES                                         | Displays all existing university modules mappings that are approved in the format: [Partner University Module Code] [Partner University Module Title] [Partner University Module Credits] [NUS Module Code] [NUS Module Title] [NUS Module Credits] in NUS  |
| list      | /list UNIVERSITIES                                    | Displays all universities with module mappings available in database                                                                                                                                                                                        |
| list      | /list m/MODULECODE                                    | List all module mappings for NUS MODULECODE in database                                                                                                                                                                                                     |
| list      | /list u/UNIVERSITY_NAME_IN_UNDERSCORES                | List all module mappings offered by UNIVERSITY in database                                                                                                                                                                                                  |
| add       | /add u/UNIVERSITY_NAME_IN_UNDERSCORES m/MODULECODE    | Add input Partner University module code to input university list                                                                                                                                                                                           |
| delete    | /delete u/UNIVERSITY_NAME_IN_UNDERSCORES m/MODULECODE | Remove input Partner University module code from input university list                                                                                                                                                                                      |
| delete    | /delete u/UNIVERSITY_NAME_IN_UNDERSCORES              | Delete input university list                                                                                                                                                                                                                                |

Note: Words in UPPER_CASE are parameters that you should input as a user
Note: There should not be spaces in parameters, replace with underscore instead
