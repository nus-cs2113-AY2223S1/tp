# User Guide

## Table of Contents

### 1. [Introduction](#introduction)
### 2. [Quick Start](#quick-start)
### 3. [Features](#features)
3.1 [Create Command](#create-command-create-uuniversity_name)\
3.2 [Exit Command](#exit-command-exit)\
3.3 [Help Command](#help-command-help)\
3.4 [Add Command](#add-command-add)\
3.5 [Add Command for Lessons](#add-command-for-lessons-add-uuniversity_name-mmodule_code-dday_of_the_week-ststart_time-enend_time)\
3.6 [Add Command for Module Mapping](#add-command-for-module-mapping-add-uuniversity_name-mmodule_code)\
3.7 [Delete Command](#delete-command-delete)\
3.8 [Delete Command for Lessons](#delete-command-for-lessons-delete-uuniversity_name-mmodule_code-dday_of_the_week-ststart_time-enend_time)\
3.9 [Delete Command for Modules](#delete-command-for-modules-delete-uuniversity_name-mmodule_code)\
3.10 [Delete Command for Universities](#delete-command-for-universities-delete-uuniversity_name)\
3.11 [View Command](#view-command-view)\
3.12 [View Command for University List](#view-command-for-all-user-university-lists-view-lists)\
3.13 [View Command for select University](#view-command-for-users-selected-university-view-uuniversity_name)\
3.14 [View Command for Timetables](#view-command-for-all-timetables-created-by-user-view-timetables)\
3.15 [List Command](#list-command-list)\
3.16 [List Command for all Universities](#list-command-for-all-universities-list-universities)\
3.17 [List Command for all Modules](#list-command-for-all-modules--list-modules)\
3.18 [Filtered List Command by module](#filtered-list-command-by-module-list-mmodulecode)\
3.19 [Filtered List Command by university](#filtered-list-command-by-university-name-list-uuniversity_name_in_underscores)\
3.20 [Favourite Command](#favourite-command-favourite)\
3.21 [Favourite Command to view favourite lists](#favourite-command-for-viewing-all-favourited-lists-favourite-view)\
3.22 [Favourite Command to add list to favourites](#favourite-command-for-adding-a-list-to-favourites--favourite-adduniversity_name_in_underscores)\
3.23 [Favourite Command to delete list from favourites](#favourite-command-for-deleting-a-list-from-favourites--favourite-deluniversity_name_in_underscores)
### 4. [Command Summary](#command-summary)

## Introduction

easySEP is a CLI application created to assist NUS Computer Engineering undergraduates intending to embark on a Student Exchange Programme in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones
for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `easySEP` from [here](https://github.com/AY2223S1-CS2113-W13-2/tp/releases).
3. Open your command prompt to the folder where you saved the .jar file
4. Run java -jar easySEP.jar

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
____________________________________________________________________________
____________________________________________________________________________
```

## Features

### Create Command: `/create u/{UNIVERSITY_NAME}`

Creates a university list and the corresponding timetable for user's specified university.

Example Input: 
```
/create u/UCLA
```

Expected Output:
```
____________________________________________________________________________
Success! You have created a new list for UCLA
____________________________________________________________________________
____________________________________________________________________________
Success! You have created a new timetable for UCLA
____________________________________________________________________________
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
Goodbye. Hope to see you again soon!
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
     help      /help                                                 Displays eligible user commands for the program
     create    /create u/UNIVERSITY_NAME_IN_UNDERSCORES              Creates an empty module list for the input university
     favourite /favourite add/UNIVERSITY_NAME_IN_UNDERSCORES         Adds a university list to the user's favourites
     favourite /favourite del/UNIVERSITY_NAME_IN_UNDERSCORES         Deletes a university list from the user's favourites
     favourite /favourite VIEW                                       View the user's favourite university lists
     exit      /exit                                                 Terminate the program
     view      /view LISTS                                           Displays all existing university lists that have been created by the user
     view      /view u/UNIVERSITY_NAME_IN_UNDERSCORES                Displays all the modules that have been added to the user's input university's list in the format:
                                                                     [Home University Module Code] [Home University Module Title] | [Partner University Module Code] [Partner University Module Title] | [Equivalent NUS Credits]
     view      /view DELETE_HISTORY                                  Displays up to 5 most recent modules that the user has deleted
     list      /list MODULES                                         Displays all existing university modules mappings that are approved in the format:
                                                                     [Partner University Module Code] [Partner University Module Title] [Partner University Module Credits] | [NUS Module Code] [NUS Module Title] [NUS Module Credits] in NUS
     list      /list UNIVERSITIES                                    Displays all universities with module mappings available in database
     list      /list m/MODULECODE                                    List all module mappings for NUS MODULECODE in database
     list      /list u/UNIVERSITY_NAME_IN_UNDERSCORES                List all module mappings offered by UNIVERSITY in database
     add       /add u/UNIVERSITY_NAME_IN_UNDERSCORES m/MODULECODE    Add input Partner University module code to input university list
     delete    /delete u/UNIVERSITY_NAME_IN_UNDERSCORES m/MODULECODE Remove input Partner University module code from input university list
     delete    /delete u/UNIVERSITY_NAME_IN_UNDERSCORES              Delete input university list

     Note: Words in UPPER_CASE are parameters that you should input as a user
     Note: There should not be spaces in parameters, replace with underscore instead
____________________________________________________________________________
```

### Add Command: `/add`

We have incorporated 2 types of add commands.

### 1. Adding a lesson to the partner university timetable 

#### Add Command for Lessons: `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE} d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}`

Adds a lesson for the specified module code to the timetable for the specified university with given start time and end time on the specified day of the week.

><span style="color: #FFA500;">**IMPORTANT**</span>: {START_TIME} & {END_TIME} in {hh:mm} format
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

### 2.Adding a module mapping to the university list.

#### Add Command for Module Mapping: `/add u/{UNIVERSITY_NAME} m/{MODULE_CODE}`

><span style="color: #FFA500;">**IMPORTANT**</span>: Can only add modules in database. Use  `/list u/{UNIVERSITY_NAME}` to see modules available

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





### Delete Command: `/delete`

We have incorporated 3 types of delete commands.

### 1. Deleting a lesson from timetable

#### Delete Command for Lessons: `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE} d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}`

Deletes the specified lesson from the university's timetable

### 2. Deleting a specific module from a university list

#### Delete Command for Modules: `/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE}`

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

### 3. Deleting the entire university list

#### Delete Command for Universities: `/delete u/{UNIVERSITY_NAME}`

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





### View Command: `/view`

There are a total of 3 types of view commands

### 1. Displays all universities lists created by user.

This command displays the user's selected university list and timetable and displays all user's timetables.

#### View Command for all user university lists: `/view LISTS`

Example Input: 
```
/view LISTS
```

Expected Output: 
```
No current modules saved
Western University
No current modules saved
NUS
No current modules saved
```

### 2. Displays the created list and timetable for the specified university.
#### View Command for user's selected university: `/view u/{UNIVERSITY_NAME}`

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
3. NUS: CS4218 Software Testing | Partner University: Western University CS4472 Software Specification Testing and Quality Assurance | Equivalent NUS Credits: 4 MCs
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
____________________________________________________________________________
```
#### 3. Displays all timetables for universities lists created by user.

#### View Command for all timetables created by user: `/view TIMETABLES`

Example Input:
```
/view TIMETABLESE
```

Expected Output:
```
_____________________________________________________________________________
Timetable for UCLA:
____________________________________________________________________________
Monday:
Tuesday:
Wednesday:
Thursday:
Friday:
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
____________________________________________________________________________
Timetable for NUS:
____________________________________________________________________________
Monday:
Tuesday:
Wednesday:
Thursday:
Friday:
____________________________________________________________________________
```



### List Command: `/list`

We have incorporated a total of 4 types of list functionalities: 

### 1. Displays all universities

#### List Command for all universities: `/list UNIVERSITIES`

Displays all universities with module mappings available in database

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



### 2. Displays all module mappings

#### List Command for all modules : `/list MODULES`

Displays all existing university modules mappings that are approved in the following format:

`[Partner University Module Code] [Partner University Module Title] [Partner University Module Credits] | [NUS Module Code] [NUS Module Title] [NUS Module Credits] in NUS`

><span style="color: #FFA500;">**WARNING**</span>: Expect many lines of output. To get fewer lines, use filters as shown in the next section
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


### 3. Filter module mappings based on NUS module code

#### Filtered List Command by module: `/list m/MODULECODE`

List all module mappings for NUS MODULECODE in database


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

### 4. Filter module mappings based on Partner University name.

#### Filtered List Command by university name: `/list u/UNIVERSITY_NAME_IN_UNDERSCORES`

List all module mappings offered by UNIVERSITY in database

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

### Favourite Command: `/favourite`


We have incorporated a total of 3 functionalities involving favourite:

### 1. View all lists that are marked as favourites,  and 

#### Favourite Command for viewing all favourited lists: `/favourite VIEW`

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

### 2. Add a new list to favourites

#### Favourite Command for adding a list to favourites : `/favourite add/UNIVERSITY_NAME_IN_UNDERSCORES`

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

### 3. Delete a list from favourites

#### Favourite Command for deleting a list from favourites : `/favourite del/UNIVERSITY_NAME_IN_UNDERSCORES`

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
