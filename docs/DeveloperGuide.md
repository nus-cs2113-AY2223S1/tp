# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

# Feature: List all transactions of the same status (finished/unfinished)
The viewTransactionsByStatus feature is facilitated by the TransactionList class. It extends the command class to add an additional command for users to view the history of finished or ongoing transactions for recording purposes. The feature implements the following commands:
* `find-tx /s finished`: Lists down all the transactions that have been completed.
* `find-tx /s unfinished`: Lists down all the transactions that are currently still ongoing.

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the ViewTransactionsByStatus command to be processed.

Step 2: The ViewTransactionsByStatus command checks if the delimiter ('s') is present in the user input through the getArgs() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is 'finished' or 'unfinish' through the isValidArgument() method. An exception will also be thrown if the final argument does not match the required words.

Step 3: Assuming the final argument is 'finish', the entire transactionList will be iterated through, and an array will store the transaction if the transaction.isFinished() status is true. 

Step 4: Once the transactionList has been completely iterated through, a message will be displayed to the user via Ui.viewcompletedTransactionsMessage() which lists down all the transactions that have their isFinished() status set to true. 

The following sequence diagram shows how the viewTransactionsByStatus operation works:

![viewTransactionsByStatus](https://user-images.githubusercontent.com/47519031/196048142-2f99254e-143c-484b-888e-fde270330368.png)

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

## Implementation

Feature: Find Item/ User by keyword.

The FindByKeyword feature is mainly facilitated by the classes ItemList and UserList. With this feature, users can search for and easily view all Users/Items that match the keyword they enter. The exact commands to be entered by the user are as follows:

find-item /k [keyword] - prints a list of items which match or contain the given keyword
find-user /k [keyword] - prints a list of users which match or contain the given keyword

The operations and functions implemented are as follows:

FindItemCommand(parts, itemList)

FindUserCommand(parts, userList)

ItemList.getItemsByKeyword(keyword, itemList)

UserList.getUsersByKeyword(keyword,userList)

Given below is an example usage scenario and how the FindItem mechanism works at each step. There are 5 Items in the ItemList, as shown in the object diagram below.

![alt text](http://url/to/img.png)

Figure: ItemList Object Diagram

The user enters the following command: “find-item /k Book”. In this case, the keyword is book. The entire ItemList is iterated through, and an ItemList containing all of the Items which contain the keyword is returned. 3 Items contain the keyword “Book”, hence these 3 items are returned by the function ItemList.getItemsByKeyword. This ItemList is then converted to a String via the method ItemList.toString, and printed by Ui.printResponse so that the user is able to see all the matching Items. The control flow is shown below.

Figure: Class Level Diagram


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
