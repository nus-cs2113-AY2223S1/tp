# Developer Guide

## Table of contents

* ### [General Overview](#general-overview-1)

* ### [Design & Implementation](#design--implementation-1)
  * [Database](#database)
  * [User Storage](#user-storage)
  * [Timetable](#timetable)
  * [Delete History](#delete-history)
  * [Ui](#ui)
  * [Commands](#commands)
  * [User Module Mapping](#user-module-mappings)
  * [User University List Manager](#user-university-list-manager)

* ### [Appendix: Requirements](#appendix-requirements-1)
  * [Appendix A: Product Scope](#appendix-a-product-scope)
    * [Target User Profile](#target-user-profile)
    * [Value Proposition](#value-proposition)
    * [Problem Addressed](#problem-addressed)
  * [Appendix B: User Stories](#appendix-b-user-stories)
  * [Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements)
  * [Appendix D: Glossary](#appendix-d-glossary)
  * [Appendix E: Instructions for Manual Testing](#appendix-e-instructions-for-manual-testing)


## General Overview

Below is an architecture diagram that provides a brief overview of how the program works.

![Architecture Diagram](./images/ArchitectureDiagram.png)

The program will first make use of the save file and database file to populate its internal memory. This is stored in the program's user storage and database storage respectively. When the user then interacts with the program's UI, the program logic will take over and make use of the information from database storage to update the user storage. The user storage then updates the save file accordingly. This process will continue until the user makes no further inputs and exits the program.

## Design & Implementation

### Database

The database reads SEP module data from `data.csv` and stores the useful data into an ArrayList of module mappings and universities.

Upon starting easySEP, the DatabaseStorage will load each line from `data.csv`, parse the line using DatabaseParser, and store the data in the Database.

Relevant exceptions are thrown when there are unexpected scenarios. For instance, if `data.csv` cannot be found at the given file path, a FileNotFoundException is thrown.

The DatabaseStorage will read the information from `data.csv`, using the DatabaseParser to make sense of the information, and then store it in the Database class. The Database class will then be used by other parts of the program to reference information. This is done to achieve the Separations of Concerns principle since the code related to loading the data, parsing the data, and referencing the data is all separated from each other.

The following diagram illustrates the relationships between the three main database classes - DatabaseStorage, DatabaseParser, and Database.

![Database Class Diagram](./images/Database_Class.png)

The following diagram illustrates the flow of the program, from the initial `loadDatabase` call to the eventual completion of updating the entire database.

![Database Sequence Diagram](./images/Database_Sequence.png)

### User Storage

User storage refers to saving and loading the necessary user information into text files. It mainly involves two classes, UserStorage and UserStorageParser.
UserStorage handles extracting and storing information from the various text files while UserStorageParser handles the exchange of information between String and UserUniversityListManager formats.
Saving user information into a text file occurs when the user inputs an add/delete/create command. Loading of user information from text files occurs at the start of the program.

Based on the user's saved university lists, user storage will save each university list's information in a separate text file.
Each text file is named after the partner university (ie. `Boston University.txt`) and will contain the user's favourites, saved modules, and timetable information.
An example of a text file will look like [this](https://github.com/AY2223S1-CS2113-W13-2/tp/blob/master/docs/Example%20University.txt).

Here is how the information is stored:

- First line contains 'T' indicated this list is favourited by the user, and 'F' otherwise.
- From second line to '#', each line contains a partner university's module code and a comment if it was added to the module, which is separated by ';'.
- From '#' to the end of the file, each line contains a partner university's module code, lesson day, start time and end time, which are separated by ';'.
- Every line is separated by '%'.

Only the partner university's module code is stored to prevent storing excessive information, avoiding potential tampering or corrupting of information.
To retrieve the other information (ie. partner university's module title, NUS module code, NUS module title etc.), UserStorageParser will call Database to get the relevant information.

Upon starting easySEP, UserStorage will first load its private `HashMap` called `filePaths`, which stores all mappings of the university name to the corresponding file path.
UserStorage will also extract all valid information from the text files into a `String` and convert them into a UserUniversityListManager to be used in the main Duke class.

InvalidUserStorageFileException is thrown when there are unexpected scenarios. Such instances include

- File name not found in database
- Module code not found in the corresponding university in database
- Incorrect number of fields of information
- Module code exists in the timetable portion but missing in the saved modules portion
- Invalid day or time for lessons

During these situations, the corresponding text file for the university will be deleted, and an error message will inform the user of the invalid file format and deletion.

During the duration of the program, whenever the user decides to alter the data corresponding to UserUniversityListManager
(ie. add / delete universities or modules, or create a new university list), UserStorageParser class will update the affected university's text file accordingly.
This is achieved by converting UserUniversityListManager into a `String`, before saving it in the text file.

Initially, user storage was designed to store all information into a single text file, including module codes, module titles and module credits.
After the implementation of [timetable](#timetable), information was stored in two text files, one for saved modules and the other for timetable information.
However, to recover as much information as possible in the event of file corruption, the current iteration stores every university's information in separate text files, and minimal information is stored.
For future versions, more can be done to detect corrupted information and recover uncorrupted information.

The following diagram illustrates the relationships between the two main user storage classes - UserStorage and UserStorageParser.

![User Storage Class Diagram](./images/UserStorage_Class.png)

The following diagram illustrates the flow of the program, from initialisation to updating of the text files.

![User Storage Sequence Diagram](./images/UserStorage_Sequence.png)

### Timetable

Upon starting easySEP, a TimetableManager is created in preparation for users to begin adding Timetables. The TimetableManager is used to create new Timetables, manage existing Timetables and delete old Timetables.
These Timetables are stored in a HashMap and indexed by university name to facilitate easy reference.

For existing Timetables, they can be used to add and delete Lessons. Each Timetable is made up of a `HashMap` of `ArrayLists(type: Lesson)` indexed by weekday e.g. Monday for easy compartmentalisation.
Within Timetables, Lessons will compromise details like the day, start time and end time. The `ArrayLists(type: Lesson)` are sorted in non-descending order by the start time of the Lessons.

Relevant exceptions are thrown when unanticipated scenarios occur. For instance, if the user attempts to delete a non-existent Timetable, a TimetableNotFoundException is thrown.
Another example is the user attempting to add a lesson that is conflicting with an existing lesson in his/her timetable. In this case, a TimetableClashException is thrown.

The following diagram illustrates the relationships between the three main timetable classes - TimetableManager, Timetable and Lesson.

![Timetable Class Diagram](./images/Timetable_Class.png)

In chronological order, the following diagrams illustrate the flow of the program for adding lessons, deleting lessons and displaying timetables to the user.

![Timetable Add Lesson Sequence Diagram](./images/Timetable_addLesson_Sequence.png)
![Timetable Delete Lesson Sequence Diagram](./images/Timetable_deleteLesson_Sequence.png)
![Timetable Print Timetable Sequence Diagram](./images/Timetable_printTimetable_Sequence.png)

### Delete History

To help users recall the modules that they had recently deleted, the Delete History feature allows them to view up to the 5 most
recently deleted module mappings. This can help them to add the module mappings back to the lists without having to search for the specific
module code again.

The UserDeletedModules class has an `ArrayDeque`, which stores the recently deleted module mappings.
When the user deletes a module mapping, it will be added to the `ArrayDeque`.
If the `ArrayDeque` already contains 5 module mappings, the last one (least recent) will be deleted, before the addition of a new module mapping.
Do note that delete history information is not stored upon exiting the app (ie. it is not stored in User Storage).

The following diagram illustrates the relationship between UserUniversityListManager and UserDeletedModules classes.

![User Deleted Modules Class Diagram](./images/UserDeletedModules_Class.png)

The following diagram illustrates the flow of the program, when a user deletes a module.

![User Deleted Modules Sequence Diagram](./images/UserDeletedModules_Sequence.png)

### Ui

The Ui class is the cornerstone of the Duke program to facilitate interaction with the user. It is used to scan and collect user input, print error messages to the user upon invalid input commands,
and display the appropriate acknowledgements or required information based on the user's command.

The following diagram illustrates the methods within the Ui class that can be invoked by the other classes in Duke for user interaction.

![Ui Class Diagram](./images/Ui_Class.png)

### Commands

To interact with easySEP, users have to input commands specified with parameters to perform operations which will be passed to the CommandParser to generate a corresponding Command according to their input.
The Command will then be executed to perform the operation on the other classes managing the timetables and databases. Error checking is handled to throw InvalidUserCommandException if the user's input does not match
the specified Command format. To deal with parsing parameters, spaces in University names and Module codes are to be replaced with underscores.

The following class diagram illustrates the relationship between Command class and its subclasses as well as other classes related to Commands.
![Command Class Diagram](./images/Command_Class.png)

#### Create Command

A Create command can be used to create a university list and its corresponding timetable.

The following sequence diagram illustrates the relationship between the respective classes involved in the creation and execution of a create command.

![Create Command Sequence Diagram](./images/CreateCommand_Sequence.png)

#### Exit Command

An Exit command can be used to exit the application.

The following sequence diagram illustrates the relationship between the respective classes involved in the creation and execution of an exit command.

![Exit Command Sequence Diagram](./images/ExitCommand_Sequence.png)

#### Help Command

A Help command can be used to exit the application.

The following sequence diagram illustrates the relationship between the respective classes involved in the creation and execution of a help command.

![Help Command Sequence Diagram](./images/HelpCommand_Sequence.png)

#### Add Command

An add command can be used to add a lesson to the timetable, add a module mapping to the user university list, or add a note for an existing module mapping in the user university list.

The following sequence diagram illustrates the relationship between the respective classes involved in the creation and execution of an add command.

![Add Command Sequence Diagram](./images/AddCommand_Sequence.png)

#### Delete Command

A delete command can be used to delete a lesson from the timetable, delete a module mapping from the user university list or delete an entire user-created university list.

The following sequence diagram illustrates the relationship between the respective classes involved in the creation and execution of a delete command.

![Delete Command Sequence Diagram](./images/DeleteCommand_Sequence.png)

#### View Command

A view command can be used to view all user-created university lists, view the user's delete history, view the user's selected university list or view all the user's created university lists' timetables.

The following sequence diagram illustrates the relationship between the respective classes involved in the creation and execution of a view command.

![View Command Sequence Diagram](./images/ViewCommand_Sequence.png)

#### List Command

A list command can be used to display all the module mappings in the database, all the universities in the database, or allow users to filter by NUS module code or partner university name.

The following class diagram illustrates the relationship between the respective classes involved in the creation and execution of a list command.

![List Command Class Diagram](./images/ListCommand_Class.png)

To differentiate between the various functions of the list command, a variable `listOption` is used. This variable signals Duke to execute the relevant commands accordingly.

The following sequence diagram illustrates the flow of the program to read in the user input, parse the user input, check if it is a valid list command, and execute the relevant list command based on the `listOption`.

![List Command Sequence Diagram](./images/ListCommand_Sequence.png)

#### Favourite Command

A favourite command can be used by the user to favourite or un-favourite lists of module mappings that they have curated. It can also be used to display all of the user's favourite lists and the module mappings they contain.

To differentiate between the various functions of the favourite command, a variable `favouriteOption` is used. This variable signals Duke to execute the relevant commands accordingly.

The following class diagram illustrates the relationship between the respective classes involved in the creation and execution of a favourite command.

![Favourite Command Class Diagram](./images/FavouriteCommand_Class.png)

The following sequence diagram illustrates the flow of the program to read in the user input, parse the user input, check if it is a valid favourite command, and execute the relevant favourite command.

![Favourite Command Sequence Diagram](./images/FavouriteCommand_Sequence.png)

### User Module Mappings

#### UserModuleMapping

The UserModuleMapping class aims to bridge 2 modules (one from NUS and one from a Partner University). It is initialized by a constructor
requiring the module code, title, and university name for both NUS and partner university. This aims to simulate a real-life example of users
mapping an NUS module to a foreign university's.

#### UserModuleMappingList

The UserModuleMappingList class consists of stores a list of UserModuleMapping in an ArrayList. Users can add new modules using the `addModule`
function, search for matching modules using `findModuleByCode` and delete modules using `deleteModule`. Additionally, users are also able to search
for modules using keywords to filter out the modules currently stored that are related using `findModuleByTitle`

The following class diagram illustrates the relationship between UserModuleMappingList and UserModuleMapping.

![UserModuleMappingList Diagram](./images/UserModuleMapping_Class.png)

The following sequence diagram helps explain the key steps behind adding and deleting modules in the UserModuleMappingList class

![UserModuleMappingList_Sequence](./images/UserModuleMapping_Sequence.png)

### User University List Manager

#### UserUniversityList

The UserUniversityList class stores 2 important things the `universityName` and a list of UserModuleMapping under `myModules`
Each list is identified using the universityName. Users can only create 1 list for each partner university and this is managed by the
UserUniversityListManager. A notable function in UserUniversityList is `setFavourite` which will be used in the favourite function
to help users manage multiple lists and note down their favourites

#### UserUniversityListManager

The UserUniversityListManager manages a hashmap of lists, with the `universityName` as the key. This prevents duplicates and unnecessary space
wastage. HashMap is also an efficient data structure to obtain the UserUniversityList as the value in constant time. The UserUniversityListManager
has the notable functions `addModule` and `deleteModule` which allows users to add and delete a module in a specific list. It also has the `addFavourite`
and `deleteFavourite` function which helps the users to organise their lists.

Additionally, the UserUniversityListManager also has a `timetableManager`, for users to add lessons and plan out their timetables at partner universities.
They can plan for any potential timetable clashes. Users are only able to add / delete lesson times from existing Partner University lists. 

The following class diagram illustrates the relationship between UserUniversityListManager and UserUniversityList as well as other relevant classes.

![UserUniversityListManager Diagram](./images/UserUniversityListManager_Class.png)

The following sequence diagram helps explain the key steps behind the main functions in UserUniversityListManager.

![UserUniversityListManager Sequence](./images/UserUniversityListManager_Sequence.png)

## Appendix: Requirements

### Appendix A: Product Scope

#### Target User Profile

NUS SoC undergraduates intending to embark on a Student Exchange Programme

#### Value Proposition

- SoC students are able to efficiently query their local database of past mappings for EE, CG, and CS-coded modules at one go and store their shortlisted modules locally, allowing for ease of reference and follow-up at a later time. This will improve on the shortcomings of EduRec.
- Users can view potential partner universities for SEP and the modules offered.
- Users can create university lists for their desired partner universities, add modules, delete modules and delete lists.
- Users can create timetables, add class timings and plan out their schedules at desired partner universities.
- Users can save their current university lists after exiting the app and load it again next time.
- Users can favourite their top university picks.

#### Problem Addressed

The module mapping support provided by the EduRec website is not helpful, allowing students to only search for past mappings either by Faculty or by University. This makes the module mapping process very time-consuming, with the slow response of the EduRec website adding fuel to the fire. Moreover, the website does not allow for the searching of modules to map to begin with, much less so for searching multiple modules at one go (which is exactly what most users would be looking for). EduRec does not give students the option of saving shortlisted modules either.

### Appendix B: User Stories

| Version | As a ...              | I want to ...                                                            | So that I can ...                                                         |
|---------|-----------------------|--------------------------------------------------------------------------|---------------------------------------------------------------------------|
| v1.0    | interested user       | use a program that provides feedback to my actions                       | feel my commands being acknowledged                                       |
| v1.0    | interested user       | have my commands understood by the program                               | use the easySEP to plan my SEP modules                                    |
| v1.0    | user                  | save my shortlisted modules and their associated partner universities    | continue my module mapping search at a later time                         |
| v1.0    | user                  | create a listing for my target university                                | track whatever modules I am interested in for my target university so far |
| v1.0    | user                  | add a module that I am interested in to a list                           | track what I have seen so far                                             |
| v1.0    | potential SEP student | plan my schedule based on the most up-to-date information                | ensure the currency of my plans                                           |
| v1.0    | user                  | be able to delete modules that I am no longer interested in from my list | ignore them in my planning                                                |
| v1.0    | user                  | edit my previous modules that I have added                               | make changes to my plans whenever needed                                  |
| v1.0    | user                  | be able to view my past modules that I have saved                        | refer back to my previous lists                                           |
| v2.0    | user                  | see the list of valid commands                                           | review the valid commands                                                 |
| v2.0    | potential SEP student | be able to view the full list of modules available                       | consider all my available options                                         |
| v2.0    | potential SEP student | be able to view the full list of partner universities available          | consider all my available options                                         |
| v2.0    | user                  | be able to view all module mappings for an NUS module code               | consider modules to map                                                   |
| v2.0    | user                  | be able to view all module mappings offered by a partner university      | consider modules to map                                                   |
| v2.0    | user                  | see my most 5 recent deleted modules                                     | check my history                                                          |
| v2.0    | user                  | be able to favourite my lists                                            | keep that of my priorities                                                |
| v2.0    | user                  | various search functions for my lists                                    | search for information faster                                             |
| v2.0    | user                  | be able to add class timings and create a timetable for SEP              | keep track of my timetable                                                |
| v2.1    | new user              | use a program that does not fail unexpectedly                            | do not have to deal with handling crashes                                 |

### Appendix C: Non-Functional Requirements

1. Java 11 or above installed
2. Program built to support only single user

### Appendix D: Glossary

- HU: home university
- PU: partner university
- u/ - university
- m/ - modules
- d/ - day of lesson
- st/ - start time of lesson
- en/ - end time of lesson

### Appendix E: Instructions for Manual Testing

1. Download the `.jar` file from [this link](https://github.com/AY2223S1-CS2113-W13-2/tp/releases).
2. Open the folder that the `.jar` file is in and run the program using `java -jar easySEP.jar`.
3. View all commands and the correct command format using `/help`. Below are a few examples to consider.
4. The list of valid universities and module mappings can be found using `/list UNIVERSITIES` and `/list MODULES` respectively.
5. The commands `/list m/CS2113` and `/list u/Aalto_University` can be used to list all module mappings for `CS2113` and `Aalto University` in the database.
6. Create a new university list using `/create u/Aalto_University`.
7. Add a new module using `/add u/Aalto_University m/C3130`.
8. Add a new lesson using `/add u/Aalto_University m/C3130 d/Monday st/08:00 en/10:00`.
9. Add a new comment using `/add u/Aalto_University m/C3130 note/{this is a note}`.
10. Favourite a university list using `/favourite add/Aalto_University`.
11. Use `/favourite VIEW` to see the list of favourite university lists.
12. Un-favourite a university list using `/favourite del/Aalto_University`.
13. View all university lists using `/view LISTS` and all timetables using `/view TIMETABLES`.
14. For a specific university list, use `/view u/Aalto_University` to see modules added.
15. Delete a comment using `/delete u/Aalto_University m/C3130 note/`.
16. Delete a lesson using `/delete u/Aalto_University m/C3130 d/Monday st/08:00 en/10:00`.
17. Delete a module using `/delete u/Aalto_University m/C3130`.
18. Delete university list using `/delete u/Aalto_University`.
19. Input `/view DELETE_HISTORY` to view recently deleted modules.
20. Use `/exit` to end the program.
