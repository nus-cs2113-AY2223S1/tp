# Developer Guide
The aim of this guide is to help readers understand how the system and components of RecipEditor 
is designed, implemented and tested. This developer guide also serves to help 
developers to understand the architecture of RecipEditor and some design considerations. 
Click to view the latest release of [RecipEditor]((https://github.com/AY2223S1-CS2113-T18-2/tp/releases)).

## Content page
[Acknowledgements](#acknowledgements)

[Design](#design)
- [Architecture](#architecture)
- [Ui Component](#ui-component)
- [Logic Component](#logic-component)
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

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
