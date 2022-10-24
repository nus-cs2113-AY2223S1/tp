# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Database

The database reads in SEP module data from data.csv and stores the useful data into an ArrayList of module mappings and universities.

Upon starting easySEP, the DatabaseStorage will load each line from data.csv, parse the line using DatabaseParser, and store the data into the Database.

Relevant exceptions are thrown when there are unexpected scenarios. For instance, if data.csv cannot be found at the given file path, a FileNotFoundException is thrown.

The following diagram illustrates the relationships between the three main database classes - DatabaseStorage, DatabaseParser, and Database.

![Database Class Diagram](./images/Database_Class.png)

The following diagram illustrates the flow of the program, from the initial loadDatabase call to the eventual completion of updating the entire database.

![Database Sequence Diagram](./images/Database_Sequence.png)

### User Storage

User Storage stores user's information, which is a list of interested universities for SEP. Each university has a list of
modules that the user is interested in. This will be stored in a text file called myinfo.txt.

Upon starting easySEP, the UserStorage class will take in information from the text file and convert it into a String.
UserStorageParser will create a new UserUniversityListManager, by converting the String into a HashMap<String, UserUniversityList>
which serves as myManager for the UserUniversityListManager class.

Relevant exceptions are thrown when there are unexpected scenarios. For example, if the data in the text file is stored in an invalid format,
an InvalidUserStorageFileException will be thrown.

During the duration of the program, whenever the user decides to alter the data in the text file
(ie. add / delete universities or modules), UserStorageParser class will update the text file accordingly.
This is achieved by converting myManager into a String format, before saving it in the text file.

The following diagram illustrates the relationships between the two main user storage classes - UserStorage and UserStorageParser.

![User Storage Class Diagram](./images/UserStorage_Class.png)

The following diagram illustrates the flow of the program, from initialisation to updating of the text file.

![User Storage Sequence Diagram](./images/UserStorage_Sequence.png)

### Timetable

Upon starting easySEP, a TimetableManager is created in preparation for users to begin adding Timetables. The TimetableManager is used to create new Timetables, manage existing Timetables and delete old Timetables.
These Timetables are stored in a HashMap and indexed by university name to facilitate easy reference.

For existing Timetables, they can be used to add and delete Lessons. Each Timetable is made up of a HashMap of ArrayLists(type: Lesson) indexed by weekday e.g. Monday for easy compartmentalisation.
Within Timetables, Lessons will compromise details like the day, start time and end time. The ArrayLists(type: Lesson) are sorted in non-descending order by the start time of the Lessons.

Relevant exceptions are thrown when unanticipated scenarios occur. For instance, if the user attempts to delete a non-existent Timetable, a TimetableNotFoundException is thrown.
Another example is the user attempting to add a lesson that is conflicting with an existing lesson in his/her timetable. In this case, a TimetableClashException is thrown.

The following diagram illustrates the relationships between the three main timetable classes - TimetableManager, Timetable and Lesson.

![Timetable Class Diagram](./images/Timetable_Class.png)

### Ui

The Ui class is the cornerstone of the Duke program to facilitate interaction with the user. It is used to scan and collect user input, print error messages to the user upon invalid input commands, 
and display the appropriate acknowledgements or required information based on the user's command.

The following diagram illustrates the methods within the Ui class that can be invoked by the other classes in Duke for the purpose of user interaction.

![Ui Class Diagram](./images/Ui_Class.png)

### Commands

To interact with easySEP, users have to input commands specified with parameters to perform operations which will be passed to the CommandParser to generate a corresponding Command according to their input.
The Command will then be executed to perform the operation on the other classes managing the timetables and databases. Error checking is handled to throw InvalidUserCommandException if the user's input does not match
the specified Command format. To deal with parsing parameters, spaces in University names and Module codes are to be replaced with underscores.

[comment]: <> (The following diagram illustrates the relationships between the CommandParser class and the Command subclasses. ![CommandParser Sequence Diagram](./images/CommandParser_Sequence.png)

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

## Product scope

### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...             | So that I can ...                                           |
| ------- | -------- | ------------------------- | ----------------------------------------------------------- |
| v1.0    | new user | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user     | find a to-do item by name | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

- _glossary item_ - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
