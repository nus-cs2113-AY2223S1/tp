# Developer Guide

## Design & Implementation

### Ui
The Ui Class handles user input

![img.png](imgs/UiClass.png)

### Storage Class
![img.png](imgs/StorageClass.png)

### Media Classes
![img.png](imgs/MovieClass.png)

### Commands
![img.png](imgs/CommandsClass.png)


#### Add
![img_1.png](imgs/AddCommandSequence.png)

#### Find
![img_1.png](imgs/FindCommandSequence.png)

#### List
![img_2.png](imgs/ListCommandSequence.png)

#### Sort
![img.png](imgs/sortSequence.png)

#### Remove
When the user wants to delete a specific review from the review list, they will use the following command: "delete 
[mediaType] [index]" which triggers the executeDelete() method in Parser. For example, "delete movie 2" will delete the 
review at index 2 of movie section of the review list.

Step 1: To check that the command given by the user is valid, the program checks for the condition that the user input 
array contains exactly three String objects, failing which a DukeException will be thrown.

Step 2: The program then checks if a valid media type is given i.e. "movie" or "tv", failing which a DukeException will 
also be thrown. 

Step 3: If the previous conditions are successfully met, a RemoveCommand object is created and its execute() method is 
called. This method loops through the review list, first checking whether the review is of the given media type, then 
whether the review is at the given index.

Step 4: If both conditions are met, the review is successfully removed from the review list.

Step 5: A string confirming the successful removal of the review is generated and printed for the user.

![img.png](imgs/deleteSequence.png)

#### Clearing the review list

When the user wants to remove every review from the review list, they will use the following command: "clear", which 
triggers the executeClear() method in Parser.

Step 1: A ClearCommand object is created and its execute() method is called.

Step 2: The ArrayList method clear() is called on the review list, removing every Media object from the review list.

Step 3: A string confirming the successful clearing of the review list is generated and printed for the user.

![img.png](imgs/clearSequence.png)

### Marking favourite reviews or listing all favourites

The "favourite" command covers two user scenarios:
1) The user wants to mark or unmark a specific review as favourite.
2) The user wants to list all favourites.

**Scenario 1**:
To mark or unmark a review, the user will use the following command: "favourite [index]", which triggers the 
executeFavourite() method in Parser. For example, "favourite 3" mark the review at index 3 if it has not been marked as 
favourite, or unmark it if it has already been marked as favourite.

Step 1: A FavouriteCommand object is created and its execute() method is called.

Step 2: The program checks that an index is given in the user input.

Step 3: If the review at the given index has not been marked as favourite, the setFavourite(true) method of the Media 
class is called to mark the review as favourite. Conversely, if the review at the given index has already been marked 
as favourite, the setFavourite(false) method of the Media class is called to unmark the review as favourite.

Step 4: A string confirming the successful marking or unmarking of the review as favourite is generated and printed for 
the user.

**Scenario 2**:
To list all favourites in the review list, the user will use the following command: "favourite list", which triggers 
the executeFavourite() command in Parser.

Step 1: A FavouriteCommand object is created and its execute() method is called.

Step 2: The program checks that the user input contains the "list" keyword.

Step 3: The program loops through the review list and checks whether the review has been marked as favourite. If the 
review has been marked as favourite, the toString() method of the Media class is called on that review so that the 
stringified version of that review can be added to a String object, which will be returned to the Parser.

Step 4: The String object which was returned to the Parser and contains the list of reviews marked as favourite is 
printed for the user.

![img.png](imgs/favouriteSequence.png)



## Product scope
### Target user profile

{Describe the target user profile}
The target user profile is ideally individuals who are well-versed in the command line
and are avid movie watchers. Therefore, this app is customised to their preferences.

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ...                    | So that I can ...                            |
|--------|---------|----------------------------------|----------------------------------------------|
|v1.0|user| add movies to a list             | keep track of which movies I have watched.   |
|v1.0|user| add ratings out of 10 to a movie | remember how much I enjoyed the movie.       |
|v1.0|user| remove movies from my list       | delete erroneous entries.                    |
|v1.0|user| list the movies I have added       | view what movies I have watched              |
|v1.0|user| enter the date I watched the movie       | recall when I watched the movie.             |
|v2.0|user| arrange my review list by worst or best ratings        | see the shows that I like or hate the most.  |
|v2.0|user| star/favorite a movie        | mark shows that I would like to watch again. |
|v2.0|user| display a list that displays starred movies and shows        | view which are my favorites.|
|v2.0|user| be able to save my information after I exit the program   | offload it from my mind. |
|v2.0|user| list the movies I have watched                        | view what movies I have watched              |
|v2.0|user| list the tv shows I have watched                      | view what tv shows I have watched            |
|v2.0|user| search movies using a keyword                         | to find a specific movie I have watched      |
|v2.0|user| search tv show using a keyword                        | to find a specific tv show I have watched    |
|v2.0|user| clear movies that I have watched                      | to clear my watch history                    |
|v2.0|user| clear tv shows that I have watched                    | to clear my watch history                    |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
- Load Main 
- Add a movie or tv show 
  - Command:
    - add /movie test /rating 5  /date 02-02-2022 /genre horror)
    - add /tvshow test /rating 5  /date 02-02-2022 /genre horror /site e
- List movies and tv shows 
  - Command: list
- Remove a movie or tv show
  - Command: remove (index #)
- Clear history
  - Command: clear



