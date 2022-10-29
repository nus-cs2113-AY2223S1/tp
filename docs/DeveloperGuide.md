# Developer Guide

## Acknowledgements
{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

Ideas for this project partly comes from NUSmods at https://nusmods.com/

API and library used includes the following:
- [NUSMods API](https://api.nusmods.com/v2/) - To extract information about modules in NUS
- [FasterXML](https://github.com/FasterXML/jackson) - Helps parse json files from the NUSMods API

## Design & implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}



#### Architecture 
<img src="images/architectureDiagram.png" width="280" />


The ***Architecture Diagram*** given above presents the high-level design of the product. The main components included are as follows:
1. `Duke`: Initialised when launch and acts as the main logic component of the program.
2. `UI`: Handles all user interaction, including printing responses and receiving user inputs.
3. `Parser`: Parses main user commands and calls for appropriate functions.
4. `commands`: Exists as a package which includes all command functions.
5. `Timetable`: Contains all the data in the current session. (Data can only be accessed and manipulated by commands)
6. `data`: Manages the saving and loading of the current session data.
7. `Nusmods`: Handles all communication to the NUSMods API and extracts useful information that is used for the rest of 
the program.

<p style='text-align: justify;'>The design of the product focuses on low coupling and high cohesion. Following as closely to the Single Responsibility Principle (SRP) as possible, each major component explained above has unique roles which is not overlapped by another component. For example, printing of all responses go through the UI class, which handles all interactions with user, including the display of program responses.</p>

#### Key Features
##### Adding modules to current timetable
<img src="images/addModule.png" width="580" />

<p style='text-align: justify;'>The ***Sequence Diagram*** above is a simplified depiction of how new modules are added to the user current session timetable. The feature is rather complex due to the need to query, extract, and check the data before adding it to the timetable. Thus, a few design decisions were made:</p>

* The class CommandAddModule manages the key actions (as explained above) from a higher level.
* The class Nusmods manages all interactions with the NUSmods API and deals with JsonNode objects.
* Any particular module is only added to the current session timetable through the Timetable class after all relevant data has been extracted, packaged and checked.
* Module object consists of all relevant data for the module, including code, name, details, all available lessons, and lessons that is to be attented by the user.

<p style='text-align: justify;'>The class CommandAddModule provides control abstraction to the comlpex operation as it abstracts away lower level data items such as the day and time of a specific tutorial class for a particular module.</p>

<p style='text-align: justify;'>By having a separate class Nusmods deal with all API calls and JsonNode objects, it allows the other classes/components to only have to work with simpler data types and native objects (such as Module and Lesson objects).</p>

<p style='text-align: justify;'>Other alternatives considered includes having the CommandAddModule class handle the API call but it would have resulted in the code being even more convoluted and less readable.</p>

<p style='text-align: justify;'>In the current design, we keep all data within the Timetable class 'clean', in other words, no program failure should occur when working with the data. Thus all checks, such as if the module exists for the semester indicated, are done before adding the module to the timetable. This gives the assurance that other functions executed by the program will not fail due to invalid data.</p>

<p style='text-align: justify;'>Lastly, the objects in the program is designed in such a way where it is possible to pass a single larger object rather than many smaller objects each containing different data. Thus, by adding an entire Module object into an ArrayList in the Timetable class, other operations can access the required data more easily without uneccessary coupling.</p>

## Product scope
### Target user profile

The target user profile is as follows:
* Comfortable with using Desktop CLI apps
* Prefers typing over mouse interactions
* A student of National University of Singapore (NUS)
* Takes official valid NUS modules
* Has a need to manage and plan his/her timetable

### Value proposition

The value proposition of the product lies in its ability to aid the management and planning of a university timetable containing NUS modules.

## User Stories

| Version | As a ...     | I want to ...                                                  | So that I can ...                                             |
|---------|--------------|----------------------------------------------------------------|---------------------------------------------------------------|
| v1.0    | NUS Student  | See all the timings of the tutorials/lectures I am involved in | Plan my work schedule conveniently                            |
| v1.0    | NUS Student  | View the details of any module                                 | Get an overview of what the module is about                   |
| v1.0    | NUS Student  | Keep track of the modules I want to take                       | Better plan out my schedule                                   |
| v1.0    | NUS Student  | Remove the modules I do not wish to take                       | Keep only the modules that I am interested in                 |
| v1.0    | NUS Student  | Plan my schedule for the semester                              | Better prepare myself for the semester                        |
| v1.0    | NUS Student  | Preserve my timetable information across program runs          | Plan my timetable once and never have to worry about it again |
| v2.0    | user         | find a to-do item by name                                      | locate a to-do without having to go through the entire list   |

## Non-Functional Requirements

1. Program should run on any mainstream OS that runs Java 11 and have a stable internet 
connection
2. Users should be able to easily use all functionalities if they have a general idea of module codes
in NUS.

## Data Storage Component
The `Data` component:
* saves module and lesson information that the user has selected into a text file
* loads the `Timetable` component upon starting the program with module and lesson information from previous runs
* Has 3 static classes that manage data:
  * `LessonManager`: Handles information regarding the lessons corresponding to the user's selected modules. Information is stored in `LessonData.txt`
  * `ModuleManager`: Handles information regarding the modules the user has selected. Information is stored in `ModuleData.txt`
  * `AttendingManager`: Handles information regarding the lessons the user is attending. Information is stored in `AttendingData.txt`
* Has a static class `DataManager`, which is used when an action requires the use of all 3 of the above classes

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
