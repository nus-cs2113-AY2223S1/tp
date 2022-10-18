# Developer Guide
The aim of this guide is to help readers understand how the system and components of RecipEditor 
is designed, implemented and tested. This developer guide also serves to help 
developers to understand the architecture of RecipEditor and some design considerations. 
Click to view the latest release of [RecipEditor]((https://github.com/AY2223S1-CS2113-T18-2/tp/releases)).

## Content page
[Acknowledgements](#acknowledgements)

[Design](#design)
- [Architecture](#architecture)
- [Logic Component](#logic-component)
- [Ui Component](#ui-component)
- [Storage Component](#storage-component)
- [Implementation](#implementation)
    - [Loading Of Data On Startup](#loading-of-data-on-startup)
    - [Parsing of Commands](#parsing-of-commands)
    - [Add Recipe](#add-recipe)
    - [Edit an Existing Recipe](#add-an-existing-recipe)
    - [Find Recipe Based on Recipe Name and Ingredient](#find-recipe-based-on-recipe-name-and-ingredient)
- [Product Scope](#product-scope)
    - [Target User Profile](#target-user-profile)
    - [Value Proposition](#value-proposition)
- [User Stories](#user-stories)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design
### Architecture
{architecture-level description}

### Ui Component
The UI component is responsible for all user interfaces of the application.

<p align="center" width="100%">
  <img width="80%" src="images/UiClassDiagram.png" alt="Ui Class Diagram"/>
</p>

**API:** `Ui.java`
1. `Ui` calls `CommandResult` to show the output message after a command is completed.
2. `AddMode` calls `Recipe` to add new recipe into the list.
3. `AddMode` calls `Ingredient` to parse ingredients according to its name, amount and unit.


### Recipe Module
The recipe module encapsulates the array, recipe and ingredient objects.

<p align="center" width="100%">
  <img width="80%" src="images/RecipeModuleDiagram.png" alt="Recipe Module Diagram"/>
</p>

**API:** `RecipeList.java`
1. `RecipeList` calls `Recipe` to add new recipe to the list
2. `RecipeList` calls `Recipe` to edit existing recipes
3. `RecipeList` calls `Recipe` to delete a recipe

**API:** `Recipe.java`
1. `Recipe` calls `Ingredient` to add, edit or delete ingredients

### Storage Component
The storage component allows data to be read from and saved to a storage file.

<p align="center" width="100%">
  <img width="80%" src="images/StorageClassDiagram.png" alt="Storage Class Diagram"/>
</p>

**API:** `Storage.java`
1. `Storage` calls `Recipe` when saving data from `RecipeList` to an external storage file.
2. `Storage` calls `RecipeList` when loading recipe data from external storage file to.
3. `Storage` calls `Ui` to show relevant messages to the user.

The external storage file contains:
- Recipe Name
- Recipe Description
- Recipe Ingredients (name, amount, unit)
- Recipe Steps


## Product scope
### Target user profile

{Describe the target user profile}
### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ...                                 | So that I can ...                                                                               |
|--------|---------|-----------------------------------------------|-------------------------------------------------------------------------------------------------|
|v1.0|new user| see usage instructions                        | refer to them when I forget how to use the application                                          |
|v1.0|user| add new recipes                               | store recipes that I need without having to remember everything                                 |
|v1.0|user| delete existing recipes                       | remove recipes that I no longer use so that the list will not be cluttered                      |
|v1.0|user| show all recipes in my list                   | view an overview of what recipes I have added beforehand                                        |
|v1.0|user| show detailed recipe that I specified         | view detailed recipe (name, description, ingredients and steps) of the one that I am interested |
|v1.0|user| exit the application                          | close the entire application                                                                    |
|v2.0|user| find recipe by recipe name or ingredient name | locate a recipe without having to go through the entire list    g                               |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
