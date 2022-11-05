# User Guide

## Table of Contents

### 1. [Introduction](#introduction)

### 2. [Quick Start](#quick-start)

### 3. [Features](#features)

#### 3.1 [Add Command](#add-command-add)

3.11 [Add Command for Module Mapping](#1-add-command-for-module-mapping-add-uuniversity_name-mmodule_code)\
3.12 [Add Command for Lessons](#2-add-command-for-lesson-add-uuniversity_name-mmodule_code-dday_of_the_week-ststart_time-enend_time)

#### 3.2 [Create Command](#create-command-create-uuniversity_name)

#### 3.3 [Delete Command](#delete-command-delete)

3.31 [Delete Command for Universities](#1-delete-command-for-universities-delete-uuniversity_name)\
3.32 [Delete Command for Modules](#2-delete-command-for-modules-delete-uuniversity_name-mmodule_code)\
3.33 [Delete Command for Lessons](#3-delete-command-for-lessons-delete-uuniversity_name-mmodule_code-dday_of_the_week-ststart_time-enend_time)

#### 3.4 [Exit Command](#exit-command-exit)

#### 3.5 [Favourite Command](#favourite-command-favourite)

3.51 [Favourite Command to view favourite lists](#1-favourite-command-for-viewing-all-favourited-lists-favourite-view)\
3.52 [Favourite Command to add list to favourites](#2-favourite-command-for-adding-a-list-to-favourites--favourite-adduniversity_name)\
3.53 [Favourite Command to delete list from favourites](#3-favourite-command-for-deleting-a-list-from-favourites--favourite-deluniversity_name)

#### 3.6 [Help Command](#help-command-help)

#### 3.7 [List Command](#list-command-list)

3.71 [List Command for all Universities](#1-list-command-for-all-universities-list-universities)\
3.72 [List Command for all Modules](#2-list-command-for-all-modules--list-modules)\
3.73 [List Command filtered by module](#3-list-command-filtered-by-module-list-mmodulecode)\
3.74 [List Command filtered by university](#4-list-command-filtered-by-university-name-list-uuniversity_name)

#### 3.8 [View Command](#view-command-view)

3.81 [View Command for University List](#1-view-command-for-all-user-university-lists-view-lists)\
3.82 [View Command for input University](#2-view-command-for-users-input-university-view-uuniversity_name)\
3.83 [View Command for Timetables](#3-view-command-for-all-timetables-created-by-user-view-timetables)\
3.84 [View Command for Delete History](#4-view-command-for-delete-history-view-delete_history)

### 4. [Command Summary](#command-summary)

## Introduction

easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `easySEP` from [here](https://github.com/AY2223S1-CS2113-W13-2/tp/releases).
3. Open your command prompt to the folder where you saved the .jar file.
4. Maximise your command prompt window to full screen and set your screen to minimum resolution.
5. Run `java -jar easySEP.jar`.

Note: The word `databases` in this guide refers to the static copy of module mappings contained in `data.csv`.
Where applicable, the abbreviation `PU` stands for Partner University.

Expected output:

```
                        _____ ______ _____
                       / ____|  ____|  __ \
   ___  __ _ ___ _   _| (___ | |__  | |__) |
  / _ \/ _` / __| | | |\___ \|  __| |  ___/
 |  __/ (_| \__ \ |_| |____) | |____| |
  \___|\__,_|___/\__, |_____/|______|_|
                  __/ |
                 |___/
Hello! Welcome to easySEP, your personal companion for planning your student exchange :-)
How may I help you today?
Type /help if you need help with getting started for user commands
____________________________________________________________________________
```

## Features

### Add Command: `/add`

We have incorporated 3 types of add commands.

#### 1. Add Command for Module Mapping: `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE}`

Adds input Partner University module code to input university list.

> <span style="color: #FFA500;">**IMPORTANT**</span>
> - Can only add modules in database. Use `/list u/{UNIVERSITY_NAME}` to see modules available

Example input:

```
/add u/Western_University m/CS4487
```

Expected output:

```
____________________________________________________________________________
Success! You added:
NUS: CS4243 Comp Vision & Pattern Recogntn | Partner University: Western University CS4487 Algorithms for Image Analysis | Equivalent NUS Credits: 4 MCs
____________________________________________________________________________
```

#### 2. Update Add Command Note for Module Mapping: `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE} note/{{NOTE_WITH_SPACES}}`

Adds/Updates a (previous) note for the input Partner University module code in the input university list.

> <span style="color: #FFA500;">**IMPORTANT**</span>: 
>  - Can only add a note after previously adding the Partner University module code in the input university list.
>  - Only the latest comment will be stored. 

Example input:

```
/add u/Western_University m/CS4487 note/{Hello world!}
```

Expected output:

```
Module updated successfully

____________________________________________________________________________
Success! You updated:
NUS: CS4243 Comp Vision & Pattern Recogntn | Partner University: Western University CS4487 Algorithms for Image Analysis | Equivalent NUS Credits: 4 MCs
Note: Hello World!
____________________________________________________________________________

```

#### 3. Add command for Lesson: `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE} d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}`

Adds a lesson for the specified module code to the timetable for the specified university with given start time and end time on the specified day of the week.

> <span style="color: #FFA500;">**IMPORTANT**</span>
> - {START_TIME} & {END_TIME} in {hh:mm} format
> 
Example input:

```
/add u/Western_University m/CS3319 d/Thursday st/18:00 en/20:00
```

Expected output:

```
____________________________________________________________________________
Success! You have added a new lesson:
Western University Thursday 18:00hrs-20:00hrs: CS3319 Databases I
____________________________________________________________________________
```


### Create Command: `/create u/{UNIVERSITY_NAME}`

Creates a university list and the corresponding timetable for user's specified university.

Example Input:

```
/create u/Aalto_University
```

Expected Output:

```
____________________________________________________________________________
Success! You have created a new list for Aalto University
____________________________________________________________________________
____________________________________________________________________________
Success! You have created a new timetable for Aalto University
____________________________________________________________________________
```

### Delete Command: `/delete`

We have incorporated 4 types of delete commands.

#### 1. Delete Command for Universities: `/delete u/{UNIVERSITY_NAME}`

Deletes input university list.

Example Input:

```
/delete u/Western_University
```

Expected Output:

```
____________________________________________________________________________
Success! You deleted the list for Western University
____________________________________________________________________________
____________________________________________________________________________
Success! You deleted the timetable for Western University
____________________________________________________________________________
```

#### 2. Delete Command for Modules: `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE}`

Deletes the specified module from the university.

Example Input:

```
/delete u/Western_University m/CS4487
```

Expected Output:

```
____________________________________________________________________________
Success! You deleted:
NUS: CS4243 Comp Vision & Pattern Recogntn | Partner University: Western University CS4487 Algorithms for Image Analysis | Equivalent NUS Credits: 4 MCs
____________________________________________________________________________
Modules left for current school are:
No current modules saved
```

#### 3. Delete Command for Lessons: `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE} d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}`

Deletes the specified lesson from the university's timetable.

Example Input:

```
/delete u/Western_University m/CS3319 d/Thursday st/18:00 en/20:00
```

Expected Output:

```
____________________________________________________________________________
Success! You have deleted the lesson:
Western University Thursday 18:00hrs-20:00hrs: CS3319 Databases I
____________________________________________________________________________
```

#### 4. Delete Command for Comments: `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE} note/`

Deletes comment from the specified university & module code. 
Note that the university & module code & a non-empty comment must exist for a successful deletion.

Example Input: 

```
/delete u/Western_University m/DS3000 note/
```

Expected Output:

```
Successfully deleted comment
```

### Exit Command: `/exit`

Exits easySEP

Example Input:

```
/exit
```

Expected Output:

```
____________________________________________________________________________
 ___               _  _
/  _>  ___  ___  _| || |_  _ _  ___
| <_/\/ . \/ . \/ . || . \| | |/ ._>
`____/\___/\___/\___||___/`_. |\___.
                          <___'
Hope to see you again soon!
____________________________________________________________________________
```

### Favourite Command: `/favourite`

We have incorporated a total of 3 functionalities involving favourite:

#### 1. Favourite Command for viewing all favourited lists: `/favourite VIEW`

Displays all lists that are marked as favourites with their associated module mappings.

Example of usage:

```
/favourite VIEW
```

Expected Output:

```
____________________________________________________________________________
Your favourite lists are:
1. Boston University
No current modules saved
____________________________________________________________________________
```

#### 2. Favourite Command for adding a list to favourites : `/favourite add/UNIVERSITY_NAME`

Adds a list of module mappings for a particular partner university into favourites. This list of module mappings for the partner university
must already be created beforehand in order to be added into favourites.

Example of usage:

```
/favourite add/Boston_University
```

Expected Output:

```
____________________________________________________________________________
Success! You added:
Boston University to your favourite lists
____________________________________________________________________________
```

#### 3. Favourite Command for deleting a list from favourites : `/favourite del/UNIVERSITY_NAME`

Deletes a list of module mappings for a particular partner university from favourites. This list of module mappings for the partner university
must already be marked as favourite beforehand in order to be deleted from favourites.

Example of usage:

```
/favourite del/Boston_University
```

Expected Output:

```
____________________________________________________________________________
Success! You deleted:
Boston University from your favourite lists
____________________________________________________________________________
```

### Help Command: `/help`

Prints out all the commands and command format required for easySEP

Example Input:

```
/help
```

Expected Output:

```
____________________________________________________________________________
     COMMAND   FORMAT                                                PURPOSE
     --------  --------------------------------------                -------
     add       /add u/{UNIVERSITY_NAME} m/{MODULE_CODE}              Adds input Partner University module code to input university list
     add       /add u/{UNIVERSITY_NAME} m/{MODULE_CODE}              Adds a lesson for the input module code to the timetable for the input
               d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}
     add       /add u/{UNIVERSITY_NAME} m/{MODULE_CODE} note/{input} Adds input comment to the corresponding module code & university

     create    /create u/UNIVERSITY_NAME                             Creates an empty module list for the input university

     delete    /delete u/{UNIVERSITY_NAME}                           Deletes input university list
     delete    /delete u/{UNIVERSITY_NAME} m/{MODULE_CODE}           Deletes input module from the university list
     delete    /delete u/{UNIVERSITY_NAME} m/{MODULE_CODE}           Deletes input lesson from the university's timetable
               d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}
     delete    /delete u/{UNIVERSITY_NAME} m/{MODULE_CODE} note/     Deletes the comment from input university & module code if any

     exit      /exit                                                 Terminates the program

     favourite /favourite add/{UNIVERSITY_NAME}                      Adds a university list to the user's favourites
     favourite /favourite del/{UNIVERSITY_NAME}                      Deletes a university list from the user's favourites
     favourite /favourite VIEW                                       Displays the user's favourite university lists

     help      /help                                                 Displays eligible user commands for the program

     list      /list MODULES                                         Lists all existing university modules mappings that are approved in the format:
                                                                     [PU Module Code] [PU Module Title] [PU Module Credits] |
                                                                     [NUS Module Code] [NUS Module Title] [NUS Module Credits]
     list      /list UNIVERSITIES                                    Lists all universities with module mappings available in database
     list      /list m/{MODULE_CODE}                                 Lists all module mappings for input NUS module code in database
     list      /list u/{UNIVERSITY_NAME}                             Lists all module mappings offered by input university in database

     view      /view LISTS                                           Displays all existing university lists that have been created by the user
     view      /view u/{UNIVERSITY_NAME}                             Displays all modules added to user's input university list in the format:
                                                                     [Home University Module Code] [Home University Module Title] |
                                                                     [PU Module Code] [PU Module Title] | [Equivalent NUS Credits]
     view      /view TIMETABLES                                      Displays all timetables for list of universities created by user
     view      /view DELETE_HISTORY                                  Displays up to 5 most recent modules that the user has deleted

     Note: Words in curly brackets are parameters that you should input as a user
     Note: There should not be spaces in parameters, replace with underscores instead
____________________________________________________________________________
```

### List Command: `/list`

We have incorporated 4 types of list commands.

#### 1. List Command for all universities: `/list UNIVERSITIES`

Displays all universities with module mappings available in database.

Example of usage:

```
/list UNIVERSITIES
```

Expected Output:

```
____________________________________________________________________________
The universities available in the database are:
1. Aalto University
2. Aarhus University
3. Arizona State University
4. Beihang University
5. Bilkent University
6. Bogazici University Turkey
7. Boston College Massachusetts
8. Boston University
9. Budapest University of Technology and Economics
10. Carnegie Mellon University

...

172. Vilnius University
173. Waseda University
174. Western University
175. WHU - Otto Beisheim School of Management
176. Yonsei University
177. Zhejiang University
____________________________________________________________________________

```

#### 2. List Command for all modules : `/list MODULES`

Displays all existing university modules mappings that are approved in the following format:

`[Partner University Module Code] [Partner University Module Title] [Partner University Module Credits] | [NUS Module Code] [NUS Module Title] [NUS Module Credits] in NUS`

> <span style="color: #FFA500;">**WARNING**</span>: 
> - Expect many lines of output. To get fewer lines, use filters as shown in the next section
>

Example of usage:

```
/list MODULES
```

Expected Output:

```
____________________________________________________________________________
The eligible module mappings are:
1. C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS
2. C3150 Software Engineering 5MCs in Aalto University | CS2103 Software Engineering 4MCs in NUS
3. CS-C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS
4. CS--E4300 Network Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS
5. CS--E4320 Cryptography and Data Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS
6. ELEC-E7120 Wireless Systems 5MCs in Aalto University | CS4222 Wireless Networking 4MCs in NUS
...
2609. 21120510 Computer Graphics 2.5MCs in Zhejiang University | CS3241 Computer Graphics 4MCs in NUS
2610. 21121330 Operating System 2.5MCs in Zhejiang University | CS2106 Intro to Operating Systems 4MCs in NUS
2611. 21121340 Computer Networks 2.5MCs in Zhejiang University | CS2105 Intro to Computer Networks 4MCs in NUS
2612. 21190120 Algorithm Design and Analysis 2.5MCs in Zhejiang University | CS3230 Design & Analysis of Algorithm 4MCs in NUS
2613. 21191070 Computer Vision 2.5MCs in Zhejiang University | CS4243 Comp Vision & Pattern Recogntn 4MCs in NUS
2614. 21191840 Advanced Practices on Big Data Applications I 2.5MCs in Zhejiang University | CS4225 Big Data Systems for Data Scie 4MCs in NUS
2615. 21191920 Data-driven Security 2.5MCs in Zhejiang University | CS2107 Introduction to Information Se 4MCs in NUS
____________________________________________________________________________
```

#### 3. List Command filtered by module: `/list m/MODULECODE`

List all module mappings for NUS MODULECODE in database.

Example of usage:

```
/list m/CS2113
```

Expected Output:

```
____________________________________________________________________________
The eligible module mappings are:
1. COM162 Object Oriented Design and Programming with Java 15MCs in The University of Sheffield | CS2113 Software Engineering & Object- 4MCs in NUS
2. CS329E Elements of Software Engineering 3MCs in The University of Texas at Austin | CS2113 Software Engineering & Object- 4MCs in NUS
____________________________________________________________________________
```

#### 4. List Command filtered by university name: `/list u/UNIVERSITY_NAME`

List all module mappings offered by UNIVERSITY in database.

Example of usage:

```
/list u/Aalto_University
```

Expected Output:

```
____________________________________________________________________________
The eligible module mappings are:
1. C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS
2. C3150 Software Engineering 5MCs in Aalto University | CS2103 Software Engineering 4MCs in NUS
3. CS-C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS
4. CS--E4300 Network Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS
5. CS--E4320 Cryptography and Data Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS
6. ELEC-E7120 Wireless Systems 5MCs in Aalto University | CS4222 Wireless Networking 4MCs in NUS
____________________________________________________________________________
```

### View Command: `/view`

We have incorporated 4 types of view commands.

#### 1. View Command for all user university lists: `/view LISTS`

Displays all university lists created by user and the corresponding modules for each university.

Example Input:

```
/view LISTS
```

Expected Output:

```
Western University
____________________________________________________________________________
1. NUS: CS4243 Comp Vision & Pattern Recogntn | Partner University: Western University CS4487 Algorithms for Image Analysis | Equivalent NUS Credits: 4 MCs
2. NUS: CS2102 Database Systems | Partner University: Western University CS3319 Databases I | Equivalent NUS Credits: 4 MCs
____________________________________________________________________________
NUS
No current modules saved
```

#### 2. View Command for user's input university: `/view u/{UNIVERSITY_NAME}`

Displays all modules and timetable for the input university.

Example Input:

```
/view u/Western_University
```

Expected Output:

```
Western University
____________________________________________________________________________
1. NUS: CS4243 Comp Vision & Pattern Recogntn | Partner University: Western University CS4487 Algorithms for Image Analysis | Equivalent NUS Credits: 4 MCs
2. NUS: CS2102 Database Systems | Partner University: Western University CS3319 Databases I | Equivalent NUS Credits: 4 MCs
____________________________________________________________________________
Timetable for Western University:
____________________________________________________________________________
Monday:
Tuesday:
Wednesday:
Thursday:
1. 18:00hrs-20:00hrs: CS3319 Databases I
Friday:
1. 19:00hrs-21:00hrs: CS4487 Algorithms for Image Analysis
Saturday:
Sunday:
____________________________________________________________________________
```

#### 3. View Command for all timetables created by user: `/view TIMETABLES`

Displays all timetables for list of universities created by user.

Example Input:

```
/view TIMETABLES
```

Expected Output:

```
____________________________________________________________________________
Timetable for Western University:
____________________________________________________________________________
Monday:
Tuesday:
Wednesday:
Thursday:
1. 18:00hrs-20:00hrs: CS3319 Databases I
Friday:
1. 19:00hrs-21:00hrs: CS4487 Algorithms for Image Analysis
Saturday:
Sunday:
____________________________________________________________________________
Timetable for NUS:
____________________________________________________________________________
Monday:
Tuesday:
Wednesday:
Thursday:
Friday:
Saturday:
Sunday:
____________________________________________________________________________
```

#### 4. View Command for delete history: `/view DELETE_HISTORY`

Displays up to 5 module mappings that user recently deleted, ordered from most recently deleted
to least recently deleted.

Example Input:

```
/view DELETE_HISTORY
```

Expected Output:

```
____________________________________________________________________________
Your most recently deleted modules are:
1. NUS: CS4243 Comp Vision & Pattern Recogntn | Partner University: Western University CS4487 Algorithms for Image Analysis | Equivalent NUS Credits: 4 MCs
2. NUS: CS2102 Database Systems | Partner University: Western University CS3319 Databases I | Equivalent NUS Credits: 4 MCs
____________________________________________________________________________
```

## Command Summary

| Command   | Format                                                                                          | Purpose                                                                                                                                                                                                                                                     |
| --------- |-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| add       | `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE}`                                                      | Adds input Partner University module code to input university list                                                                                                                                                                                          |
| add       | `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE} d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}`    | Adds a lesson for the input module code to the timetable for the input university with input start time and end time on the input day of the week                                                                                                           |
| add       | `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE} note/{input}`                                         | Adds input comment to the corresponding module code & university                                                                                                                                                                                            |
| create    | `/create u/{UNIVERSITY_NAME}`                                                                   | Creates an empty module list for the input university                                                                                                                                                                                                       |
| delete    | `/delete u/{UNIVERSITY_NAME}`                                                                   | Deletes input university list                                                                                                                                                                                                                               |
| delete    | `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE}`                                                   | Deletes input module from the university list                                                                                                                                                                                                               |
| delete    | `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE} d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}` | Deletes input lesson from the university's timetable                                                                                                                                                                                                        |
| exit      | `/exit`                                                                                         | Terminates the program                                                                                                                                                                                                                                      |
| favourite | `/favourite add/{UNIVERSITY_NAME}`                                                              | Adds a university list to the user's favourites                                                                                                                                                                                                             |
| favourite | `/favourite del/{UNIVERSITY_NAME}`                                                              | Deletes a university list from the user's favourites                                                                                                                                                                                                        |
| favourite | `/favourite VIEW`                                                                               | Displays the user's favourite university lists                                                                                                                                                                                                              |
| help      | `/help`                                                                                         | Displays eligible user commands for the program                                                                                                                                                                                                             |
| list      | `/list MODULES`                                                                                 | Lists all existing university modules mappings that are approved in the format: [Partner University Module Code] [Partner University Module Title] [Partner University Module Credits] [NUS Module Code] [NUS Module Title] [NUS Module Credits] in NUS     |
| list      | `/list UNIVERSITIES`                                                                            | Lists all universities with module mappings available in database                                                                                                                                                                                           |
| list      | `/list m/{MODULE_CODE}`                                                                         | List all module mappings for input NUS module code in database                                                                                                                                                                                              |
| list      | `/list u/{UNIVERSITY_NAME}`                                                                     | List all module mappings offered by input university in database                                                                                                                                                                                            |
| view      | `/view LISTS`                                                                                   | Displays all existing university lists that have been created by the user                                                                                                                                                                                   |
| view      | `/view u/{UNIVERSITY_NAME}`                                                                     | Displays all the modules that have been added to the user's input university's list in the format: [Home University Module Code] [Home University Module Title] [Partner University Module Code] [Partner University Module Title] [Equivalent NUS Credits] |
| view      | `/view TIMETABLES`                                                                              | Displays all timetables for list of universities created by user                                                                                                                                                                                            |
| view      | `/view DELETE_HISTORY`                                                                          | Displays up to 5 modules that user recently deleted, ordered from most recently deleted to least recently deleted                                                                                                                                           |

Note: Words in curly brackets are parameters that you should input as a user\
Note: There should not be spaces in parameters, replace with underscore instead
