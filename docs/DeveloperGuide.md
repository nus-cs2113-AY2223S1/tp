# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Favourite / Unfavourite feature

#### Implementation

The favourite / unfavourite feature is implemented with the Favourite class.

It uses the FileReader and FileStorage classes to read and write carpark IDs to a .txt file so that user favourited 
carparks can be saved locally and retrieved even after the user exits the application.

It implements the following operations:

- setFavourite() - Inserts a carpark into the favourite list.
- setUnfavourite() - Remove a carpark from the favourite list.
- updateFavouriteList() - Reads in data from favourite.txt file and saves it to this object.
- writeFavouriteList() - Writes all favourite carpark IDs to favourite.txt file.
- getCarparkId() - Returns the carpark ID requested.
- showList() - Returns the carpark IDs of all favourited carparks in a string.

Given below is an example of how the Favourite class is used to perform favourite / unfavourite operations:

Step 1. On startup, the Favourite is initialised with a directory and file path to the file that contains data, and a favouriteList attribute. 
The Favourite class calls `updateFavouriteList()` shortly after to populate its ArrayList with the carpark IDs in the file.

Step 2. The user executes `favourite 1` to favourite the carpark with carpark ID `1`. The command is passed to the Parser class which returns a Command with value `FAVOURITE`. 
The main program first checks if the second argument in the command is equal to `list`. If so, the main program calls `showList()` and passes it output to the Ui class for printing. 
Else, the main program checks if there exists a carpark with carpark ID `1` by calling `findCarpark()` from the CarparkList class, which throws `NoCarparkFoundException` if no carpark was found. 
`setFavourite()` is then called to add the valid carpark ID into `favouriteList`, which searches `favouriteList` for any identical carpark IDs and throws `DuplicateCarparkException` if found, to prevent addition of duplicates. 
`1` is then added into `favouriteList` and `setFavourite()` calls `writeFavouriteList()` which overwrites data from `favouriteList` to the favourites file.

> Note: If any exception is thrown, `1` will not be added into `favouriteList`, and `writeFavouriteList()` will not be called, hence preserving the validity of the carpark IDs.

Step 3. The user executes `favourite 2` to favourite the carpark with carpark ID `2`. Same as Step 2.

Step 4. The user realises he/she made a mistake and wants to unfavourite the carpark with carpark ID `2`, and executes `unfavourite 2`.
The command is passed to the Parser class which returns a Command with value `UNFAVOURITE`. The main program calls `setUnfavourite()`, 
which first checks if `favouriteList` contains an entry that matches `2`, and throws `NoCarparkFoundException` if none is found. 
Next, `2` is removed from `favouriteList` and `setUnfavourite()` calls `writeFavouriteList()` which overwrites data from `favouriteList` to the favourites file.

Step 5. The user wants to view all favourited carparks and executes `favourite list`. The command is passed to the Parser class which returns a Command with value `FAVOURITE`.
The main program calls `showList()` this time as the second argument in the command is `list`. The `showList()` method 
uses StringBuilder to format all the contents of `favouriteList` into a user-friendly string, and returns it for printing.

#### Design considerations

**Aspect: Format of input after `favourite` or `unfavourite` command**
- **Alternative 1 (current choice):** Carpark ID
  - Pros: More direct, do not need to search before favouriting
  - Cons: User has to either memorise the carpark ID they want to favourite, or search and type in the whole carpark ID
- **Alternative 2:** Index of search result
  - Pros: Less time needed to favourite after a search result, do not need to key in entire carpark ID again
  - Cons: Need to search before favouriting, even if user already knows the exact carpark ID


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
