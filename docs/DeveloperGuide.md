# Developer Guide

The aim of this guide is to help readers understand how the system and components of RecipEditor
is designed, implemented and tested. This developer guide also serves to help
developers to understand the architecture of RecipEditor and some design considerations.
Click to view the latest release of [RecipEditor]((https://github.com/AY2223S1-CS2113-T18-2/tp/releases)).

## Content page

[Design](#design)

- [Architecture](#architecture)
- [Logic Component](#logic-component)
- [Ui Component](#ui-component)
- [Storage Component](#storage-component)
- [Command Component](#command-component)
- [Recipe Module](#recipe-module)
- [Edit Component](#edit-component)
- [GUI Component](#gui-component)
- [Implementation](#implementation)
    - [Loading Of Data On Startup](#loading-of-data-on-startup)
    - [Parsing of Commands](#parsing-of-commands)
    - [Add a New Recipe](#add-recipe)
    - [Edit an Existing Recipe](#add-an-existing-recipe)
    - [Find Recipe Based on Recipe Name and Ingredient](#find-recipe-based-on-recipe-name-and-ingredient)
- [Product Scope](#product-scope)
    - [Target User Profile](#target-user-profile)
    - [Value Proposition](#value-proposition)
- [User Stories](#user-stories)
- [Instructions for Manual Testing](#instructions-for-manual-testing)
- [Non-functional Requirements](#non-functional-requirements)
- [Acknowledgements](#acknowledgements)


## Design

### Architecture

<p align="center" width="50%">
  <img width="40%" src="images/Architecture.png" alt="Storage Class Diagram"/>
</p>

`Recipeditor` calls to various class a perform all the tasks assigned by the user.

- `Ui`: handles interactions with users, including printing messages and reading of inputs
- `Storage`: manages the storage of the list of recipes by reading and writing data
- `Command`: command executor to instruct what task to perform
- `Parser`: interprets the user input into different commands
- `Recipe`: main entrypoint of the program.
- `Exception`: exceptions thrown by the program.

#### Software running flow:

Upon start, Recipeditor will check load or create saves.

During software run, it will repeat iterations of reading and executing commands.
In each iteration,raw user inputs are read from CLI. They will be
interpreted into commands for the software to execute.
Each execution will either write or read the list of recipes
depending on the command. Finally, the result of current iteration
is reflected to the user.

Termination of software purges all temporary data, while saved changes
can be loaded from saves upon next software launch.

### Ui Component

The UI component is responsible for all user interfaces of the application.

<p align="center" width="100%">
  <img width="80%" src="images/ClassDiagrams/UiClassDiagram.png" alt="Ui Class Diagram"/>
</p>

**API:** `Ui.java`

1. `Ui` takes `CommandResult` as a parameter to show the output message after a command is completed.
2. `AddMode` calls `Recipe` to add new recipe into the list.
3. `AddMode` calls `Ingredient` to parse ingredients according to its name, amount and unit.
4. `Editor` takes `Storage` as a parameter to access the temporary file path, where the recipe will be 
temporarily stored at.

### Storage Component

The storage component allows data to be read from and saved to a storage file.

<p align="center" width="100%">
  <img width="80%" src="images/ClassDiagrams/StorageClassDiagram.png" alt="Storage Class Diagram"/>
</p>

**API:** `Storage.java`

1. `Storage` calls `Recipe` when saving data from `RecipeList` to an external storage file.
2. `Storage` calls `RecipeList` when loading recipe data from external storage file to.
3. `Storage` calls `Ui` to show relevant messages to the user.
4. `Storage` calls `ParserFileException` when there is an error in parsing recipe file content.
5. `Storage` uses a method in `RecipeFileParser` to parse the content in the individual recipe text file
into recipe.
6. `Storage` uses a method in `TitleFileParser` to parse the title of the text file into recipe title.

The external storage file contains:
1. Individual Recipe Text File
   - Recipe Name
   - Recipe Description
   - Recipe Ingredients (name, amount, unit)
   - Recipe Steps
   
2. All Recipe Text File - that contains the recipe title of all the recipes in the list.

### Command Component

The command component features a list of commands falls under `Command`,
identified from user input for the software to carry out certain tasks.
A `CommandResult` is returned from `execute()` method call of each `Command`.
The `CommandResult` consists of a single error message in `String`.

<p align="center" width="100%">
  <img width="80%" src="images/ClassDiagrams/CommandClassDiagram.png" alt="Storage Class Diagram"/>
</p>

Each subclass of `Command` has their own attributes and `CommandResult`
from `Execute` method, allowing them to perform respective tasks.

All types of`Command`and their functionalities are explained below:

`AddCommand`: Add a valid `Recipe` to `RecipeList`, otherwise shows error message
for `invalid Recipe`

`DeleteCommand`: Remove an existing `Recipe` at a valid index from `RecipeList`,
otherwise show error message on `index out of bound`

`ExitCommand`: Deliver a `CommandResult` to terminate software run.

`InvalidCommand`: Deliver a `CommandResult` of invalid command

`ListCommand`: Print all formatted `Recipe` in `RecipeList` to screen

`ViewCommand`: View an existing `Recipe` at a valid index from `RecipeList`,
otherwise show error message on `index out of bound`

### Recipe Module

The recipe module encapsulates the array, recipe and ingredient objects.

<p align="center" width="100%">
  <img width="80%" src="images/ClassDiagrams/RecipeClassDiagram.png" alt="Recipe Module Diagram"/>
</p>

**API:** `RecipeList.java`

1. `RecipeList` calls `Recipe` to add, edit or delete recipes

**API:** `Recipe.java`

1. `Recipe` calls `Ingredient` to add, edit or delete ingredients

### Edit Component

<p align="center" width="100%">
  <img width="80%" src="images/ClassDiagrams/EditClassDiagram.png" alt="Edit Module Diagram"/>
</p>

The edit component consists of three parts:

- Parser
    - Parses the user input, instantiates the EditCommand class
- EditModeCommand
    - Handles the edit functions (Add, Swap, Change, Delete, Invalid)
- EditCommand
    - Instantiated by parser whenever /edit is called, instantiates the flag parser, switches the flow between GUI and
      CLI,
      handles saving the edited recipe

#### Parser
The FlagParser contains several functions to extract flags from the user input in the FlagType format. It is used to
instantiate the necessary EditModeCommand.
GuiWorkFlow bypasses this parsing step since there is nothing to be parsed (given that only the index is provided).

#### EditModeCommand
An abstract class instantiated by EditCommand in CLI mode. It takes in the old recipe and, once executed, 
returns a new recipe which will be saved to Storage.

#### EditCommand
Handles the branching of commands, once executed it will save the new recipe to Storage or returns an error.

The following illustrates the work sequence to edit a recipe.

<p align="center" width="100%">
  <img width="80%" src="images/SequenceDiagram/Edit.png" alt="Edit Sequence Diagram"/>
</p>
The user first call the edit command from the Main class which will then be passed to the Parser class. It decides
whether the GUI or CLI should be called through the number of arguments passed by the user.

#### GUI Edit
- GUI window is called by GuiWorkFlow
- After the GUI edit has finished, EditCommand is instantiated and the new recipe is saved to Storage through the
RecipeList class

#### CLI Edit
- EditCommand is instantiated with the corresponding flags parsed from the arguments provided by the user
- Depending on the flags passed, it instantiates the abstract class EditModeCommand using different constructors
  (Add, Delete, Swap, Change)
- Once it has been executed, it returns the new edited recipe, which will be saved to Storage through the RecipeList
class

### GUI Component

When the user type

## Implementation

### Add new recipe

The following sequence diagram shows the usage of relevant classes when trying
to add a new recipe to storage.

<p align="center" width="100%">
  <img width="80%" src="images/SequenceDiagram/AddEditor.png" alt="Recipe Module Diagram"/>
</p>

Step 1: User will first input a customer `AddCommand`. The user input
is read by `Main` and is parsed by the static method `Parser.parseCommand()`.

Step 2: If `AddCommand` of correct format is parsed, `Parser` will create a new
instance of `GuiWorkFlow`.

Step 3: When the new instance of `GuiWorkFlow` is constructed, it creates a new
instance of `Editor`, and it calls `enterEditor`. This opens the GUI editor for
user input of `recipe`.

Step 4: `GuiWorkFlow` will load a template `recipe` to Editor for user to edit on it.

Step 5: `GuiWorkFlow` will keep listening to changes made in the editor and save them to
`Template.txt` in `Storage`. This process loops until user exits the editor manually.

Step 6: `GuiWorkFlow` will create an instance of `TextFileParser` and calls `parseTextToRecipe`
to store the newly added `recipe` in itself.

Step 7: `GuiWorkFlow` checks if the user input of recipe has a valid title, which is not
the same as titles of any existing `recipe` in `Storage`.

Step 8: The content and validity of `recipe` are used to create an instance of `AddCommand`,
which is returned to `Main` for execution.

Step 9: Upon execution of `AddCommand`, its validity is checked. If the `AddCommand` is valid,
the `recipe` in it will be written to `RecipeList` and `Storage` successfully. Otherwise, a message
of invalid `AddCommand` will be returned backed to `Main`.

## Product scope

### Target user profile

Avid cook who wants to organize their recipe list for ease of reference and search.

### Value proposition

Helps people who cook often to keep track of the many recipes that they have so that they don't have to go about
memorising all the recipe details. RecipEditor helps to manage all these recipes where users can add, edit and
delete recipes of their choice. Furthermore, they can find relevant recipes quickly using RecipEditor. For example,
if the user wants to make a dish with tomato, he/she can use RecipEditor to find recipes that uses tomato as ingredient
quickly.

## User Stories

| Version | As a ... | I want to ...                                 | So that I can ...                                                                                           |
|---------|----------|-----------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| v1.0    | new user | see usage instructions                        | refer to them when I forget how to use the application                                                      |
| v1.0    | user     | add new recipes                               | store recipes that I need without having to remember everything                                             |
| v1.0    | user     | delete existing recipes                       | remove recipes that I no longer use so that the list will not be cluttered                                  |
| v1.0    | user     | show all recipes in my list                   | see the overview of what recipes I have added beforehand                                                    |
| v1.0    | user     | exit the application                          | close the entire application                                                                                |
| v2.0    | user     | edit a previously saved recipe                | update the recipe without having go through the trouble to delete and add the updated version of the recipe |
| v2.0    | user     | find recipe by recipe name or ingredient name | locate a recipe without having to go through the entire list                                                |
| v2.0    | user     | show detailed recipe that I specified         | view detailed recipe (name, description, ingredients and steps) of the one that I am interested             |
| v2.0    | new user | view the list of available commands           | use the appropriate command according to my needs                                                           |


## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Parsing  from Text to Recipe using Add or Edit GUI

- For the ease of testing, use `/add` command and edit directly on the template
- When the parser throw an error and you were asked `Do you want to FIX the recipe? (Y/N)`, type `y`

#### General errors

- **Whitespace at the start of the line**
    - Expected: Will be trimmed (except for [Description](#description-errors))
- **Blank whitespace**
    - Different types
        - Blank space at the start
        - Blank space between the '# HEADING' and the content
        - Blank space between the ingredients and step
    - Expected outcome: allowed and will not affect parsing (except for [Description](#description-errors))
- **Missing, duplicate Heading**
    - Expected: `Incorrect number of HEADINGS! Please follow the template!`
- **Heading in different order**
    - Expected: able to parse
- **Duplicate Title in `/add`**
    - Expected outcome: `This Recipe Title already existed!`

#### Title errors

- **Multi-line title**: Title contains multiple lines
    - Expected outcome: `TITLE should be a single line and less than 255 characters`
- **Title that is not alphanumeric**: To prevent characters that are an invalid file name
    - Expected outcome:
- **Title with >255 characters**: Cannot be saved as a file in the Operating System
    - Here is a 255 characters
      string: `aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa`
    - Expected outcome: `TITLE is too long! TITLE should be less than 255 characters!`

#### Description errors

- Description will record down all type of characters under the DESCRIPTION Heading as a string because we want to give
  the user freedom
- **Blank lines and whitespace at the start of a line**
    - Expected outcome: will be recorded
    - Save the Recipe and use `/view` command to check the description content

#### Ingredients errors

- **Characters in front of the index**
    - Expected outcome: `INGREDIENT format is incorrect!` and further instructions
- **Negative index**
    - Expected outcome: `INGREDIENT index must be a positive integer!`
- **Index increment is wrong**:
    - Expected outcome: `INGREDIENT index increment is incorrect! Index starts from 1`
- **More `/` than the format**
    - Expected outcome: `INGREDIENT format is incorrect!` and further instructions
- **`.` in ingredient name**
    - Expected outcome: allowed
- **Amount not a valid positive double**:
    - Expected outcome: `INGREDIENT amount should be a positive rational number!` and further instructions

#### Steps errors

- **Characters in front of the index**
    - Expected outcome: `STEP format is incorrect!` and further instructions
- **Negative index**
    - Expected outcome: `STEP index must be a positive integer!`
- **Index increment is wrong**:
    - Expected outcome: `STEP index increment is incorrect! Index starts from 1`
- **`.` in step description**:
    - Expected outcome: allowed because a step can have multiple sentences

### Storage: Tampering the data

- Tamper the data to test if the program can recover gracefully

#### During the running of the program

- **Delete or tamper the `AllRecipes.txt` file**
    - No effect as the program does not use `AllRecipes.txt` while running
    - If the program is stopped not using `/exit`. The effect will be reflected in the next run
    - However, after `/add`, `/edit`,`/exit`, a correct `AllRecipes.txt` will be generated

- **Delete recipe files then `/edit`**
    - Deleting the data files can only be done if the files are newly created during the same run of the program.
    - If the file is loaded by the program, you cannot delete because JDK is using them.<p align="center" width="100%">
      <img width="80%" src="images/DeveloperGuide/JDKUsing.png" alt="Recipe Module Diagram"/></p>
    - Expected outcome:
      ```
      Please edit in the GUI editor!
      Recipe File is missing! Regenerate Recipe File! Please try again!
      >>>
      ```
- **Tamper the recipe files**
    - The change in recipe files will not be reflected in the Model
- **Tamper the recipe file then `/edit`**
    - The change will be loaded into the Editor GUI
    - The validity of the change will be parsed when exit the GUI and will be reflected in the Model
- **Tamper the recipe file then `/exit`**
    - The program will regenerate all the recipe files and `AllRecipes.txt` based on the Model

#### Before the running of the program

- **Delete the `AllRecipes.txt`**
    - The program cannot recognize any title start anew, despite having recipe files
- **Tamper the `AllRecipes.txt`**
    - The program will match the recipe titles in `AllRecipes.txt` with the stored recipe files
    - The program will parse the recipe files
    - If valid, the program will load the recipe into the Model
    - If the title does not match the stored recipe or the stored recipe cannot be parsed, the program will not
      recognize the recipe

- **Delete the recipe file**
    - The program cannot find the recipe file from the title in `AllRecipes.txt`
    - The program will skip this
- **Tamper the recipe file (parseable recipe)**
    - The program will load the recipe with the tampered content
- **Tamper the recipe file (unparseable recipe)**
<<<<<<< HEAD
  - The program will not load the recipe 

## Non-Functional Requirements

1. Should work on any OS as long as it has Java 11 or above installed on their PC.
2. Should be able to hold up to 1000 recipes without a slowdown of performance.
3. Any user that is comfortable with typing of speeds >55 words per minute would be able to accomplish these tasks faster than if they used a mouse to navigate.


## Acknowledgements

### External Libraries

- org.apache.commons:commons-lang3:3.0 [link](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.0)
- org.apiguardian:apiguardian-api:1.1.0 [link](https://mvnrepository.com/artifact/org.apiguardian/apiguardian-api)
=======
    - The program will skip this

#### Other files

- **Delete `Template.txt` then `/add`**
    - Expected outcome: `Template file is missing! Regenerate Template File! Please try again`
- **Tamper `Template.txt` then `/add`**
    - The tampered file will be loaded
    - Everything will continue to work but without a proper template, it is hard for the user
- **Delete `TemporaryFile.txt` then `/add`**
    - No effect because it will be constantly overwritten based on content in the Editor when closed
>>>>>>> aee7e29689b0b76d3dcf391b0330ce27b1810296
