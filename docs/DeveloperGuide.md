# Upcycle - Developer Guide

## Table of contents
[Acknowledgements](#acknowledgements)
1. [Introduction](#1-introduction)
2. [Setup the environment](#2-setup-the-environment)
3. [Design](#3-design)\
   3.1. [Duke](#31-duke)\
   3.2. [Command component](#32-command-component)\
   3.3. [User component](#33-user-component)\
   3.4. [Item component](#34-item-component)\
   3.5. [Transaction component](#35-transaction-component)\
   3.6. [Ui component](#36-ui-component)\
   3.7. [Storage component](#37-storage-component)
4. [Implementation](#4-implementation)\
   4.1. [User-related features](#41-user-related-features)\
   4.2. [Item-related features](#42-item-related-features)\
   4.3. [Transaction-related features](#43-transaction-related-features)\
   4.4. [Help command](#44-help-command)\
   4.5. [Exit command](#45-exit-command)
5. [Product Scope](#5-product-scope)\
   5.1. [Target user profile](#51-target-user-profile)\
   5.2. [Value proposition](#52-value-proposition)
6. [User stories](#6-user-stories)
7. [Non-functional requirements](#7-non-functional-requirements)
8. [Glossary](#8-glossary)
9. [Instructions for manual testing](#9-instructions-for-manual-testing)

## Acknowledgements

This project is based on the skeleton code and documents of [SE-EDU AddressBook Level-3](https://se-education.org/addressbook-level3/)

## 1. Introduction

...To be updated

## 2. Setup the environment

**Step 1**: JDK 11 and IntelliJ are recommended

**Step 2**: Fork [our repo](https://github.com/AY2223S1-CS2113-W12-1/tp) and clone the fork to your local computer.

**Step 3**: Set up the JDK:
1. From the main menu, select File > Project Structure > Project Settings > Project > Project SDK
2. If ```JDK 11``` is already defined in Intellij IDEA, select it and click ```Apply```
3. If ```JDK 11``` is not defined, select Add SDK > Download JDK, choose the necessary JDK (JDK 11) and click Download, then click ```Apply```

**Step 4**: Go to File > Open, locate and open the cloned repository.

**Step 5**: Verify the setup:
1. Run ```Duke.main()``` and try some commands.
2. Run the JUnit tests in ```src/test/java/seedu.duke```

## 3. Design

This sector describes the architecture design of Upcycle with its components, and UML diagrams are used to support.

### 3.1. Duke

![DukeSequence](images/DukeSequence.png)

... To be updated

### 3.2. Command component

From the above, it is quite clear that the Command component is important as it governs execution of behaviors depending on user input.

For our components, we try to be consistent by having an interface for classes that acts as a 'contract' to tell us what we can do with a class

We therefore have a `CommandInterface` that is implemented by an abstract class `Command`, which is the base class for all commands

Below, we show a simplified class diagram of how our Command classes are designed

![CommandDesign](images/CommandDesign.png)

A few things to take note are:

- `UserCommand`, `TransactionCommand` and `ItemCommand` are simplified here they comprise quite a number of classes
- In general, each related has a `Add`, `Remove`, `List`, `View`, `Update` command
- For example, the ItemCommand actually comprises `AddItemCommand`, `ListItemCommand`, `ViewItemCommand`, `UpdateItemCommand` and `DeleteItem` and will have other commands in the future.
- Since `Comamnd` is abstract, each subclass will have its own implementation of `executeCommand`
- `executeCommand` will either return a `Boolean` value or thrown an Exception
- The `Boolean` returned value represents if the executing command is the last executed command(such as if a user exits the program)
- `executeCommand` follows the delegation pattern. It receives `UserList`, `ItemList` and `TransactionList` which it delegates work to.

### 3.3. User component

The class diagram below shows how User-related classes interact with each other. `User` object contains `name`, `age`, `contactNumber` attributes. Users are stored in UserList, which will be loaded and written on the file by `UserStorage` (inherits from `Storage`) whenever Upcycle runs or exits.
All user-related commands operate mainly on a list of users (userList:UserList).

![UserClassDiagram](images/UserClassDiagram.png)

### 3.4. Item component

...To be updated

### 3.5. Transaction component

The Class diagram below show how Transaction-related classes interact with each other. `Transaction` object contains `transactionId`, `itemName`, `itemId`, `borrower`, `duration`, `createdAt`, `returnedAt` 
attributes. Among those, `transactionId` is created by `IdGenerator`'s static method and dates are parsed by `DateParser`, therefore, Transaction class depends on those two classes.
Transactions are stored in `TransactionList`, which will be loaded and written on the file by `TransactionStorage` (inherits from `Storage`) whenever Upcycle runs or exits. All transaction-related 
commands operate mainly on a list of transaction (transactionList:TransactionList)

![TransactionClassDiagram](images/TransactionClassDiagram.png)

### 3.6. Ui component

The UI component handles anything related to user interaction.
Primarily, it consists of two main operations: reading user inputs and printing responses to the user.
For reading inputs, there is simply `UI::readInput`
For printing responses, there are `UI::printResponse` and `UI::printErrorResponse`
Further methods for printing specific responses that correspond to a user's behavior are defined for convenience.

The design of the UI component is to simply act as a helper class i.e. one consists mainly of static methods that is called directly from the class.
This makes sense as the UI class should not to be instantiated and is not inherited by other classes.

Below, we detail the design of the UI class with a class diagram
![UiDesign](images/UiDesign.png)


### 3.7. Storage component

Upcycle has three separate Storage class, dedicated for three types of object: ```UserStorage```, ```ItemStorage```, and ```TransactionStorage```. All of these inherit from an abstract class called ```Storage```. 
The following  diagrams show more details about Storage classes: 

![StorageClassDiagram](images/StorageClassDiagram.png)

Upcycle stores the user's data, including the user list, item list, transaction list in three files ```user.txt```, ```item.txt```, ```transaction.txt```, respectively.
The data will be loaded when running the program, and ONLY be written to the files when exiting the program correctly, otherwise, the data during the execution will be gone.
These find can be found in ```data``` folder in the same directory with the folder containing project root.

## 4. Implementation

This sector describe how features are implemented, where readers can get insight into the mechanisms of them with step-by-step descriptions and UML diagrams.
> **Note:**
> 
> ```executeCommand()``` method of every command return a boolean. Only ```ExitCommand``` returns ```true``` since it informs Duke that this is the last command to exit.

### 4.1. User-related Features

#### 4.1.1. Add a user

> This feature allows user to add a new user to their list and upon successful adding, a confirmation response about the new user will be sent from Ui to user

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the AddUserCommand class to be processed.

Step 2: The AddUserCommand command checks if the delimiters ('n', 'a', 'c') is present in the user input through the getArgsAddUserCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid through `isValidName()`, `isValidAge()`, `isValidContactNumber()` methods. An exception will also be thrown if the final argument does not satisfy the requirements (duplicate name, wrong range or format age, wrong contact length ,...).

Step 3: If all arguments are valid, then it creates a new `User(arg[0], Integer.parseInt(args[1]), args[2])` with `args[0]` is username, `args[2]` is age, and `args[3]` is contact number

Step 4: After adding the new user, a message will be displayed to the user via `Ui.addUserMessage()` method which show the new user's details and the size of userList.

The following sequence diagram shows how the add user operation works:
![addUserSequence](images/AddUserSequence.png)

#### 4.1.2. Remove a user

> This feature allows user to remove a user to their list and upon successful removing, a confirmation response about the deleted user will be sent from Ui to user

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the RemoveUserCommand class to be processed.

Step 2: The RemoveUserCommand command checks if the delimiters ('u') is present in the user input through the getArgsRemoveUserCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid (user can be deleted or not) by checking whether that user is currently borrowing or lending any item via `canDeleteUser()`, `isBorrowing()` and `isLending()` methods. An exception will also be thrown if the final argument does not satisfy the requirements (user not found, user is borrowing/lending).

Step 3: If all arguments are valid, then it finds and deletes the user in the userList by his/her name, which is unique. And all of his/her items stored in itemList are also deleted:

`userList.deleteUser(username);`

`itemList.deleteAllItemOfAnUser(username, transactionList)`

Step 4: After deleting a user, a message will be displayed to the user via `Ui.deleteUserMessage()` method which show the deleted user's details and the size of userList.

The following sequence diagram shows how the remove user operation works:
![removeUserSequence](images/RemoveUserSequence.png)

#### 4.1.3. List users

> This feature allows the user to view details of all users in the user list

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the ListUsersCommand class to be processed.

Step 2: In `ListUserCommand::exeucteCommand` work is delegated `UserList::toString()` to obtain a string representation of all users in the user list

Step 3: The string representation is forwarded to `UI::printResponse` to show it to the user

Step 4: Lastly, `executeCommand` returns false to indicate that this is a non-terminating command

The following sequence diagram shows how the view user operation works:
![ListUserSequence](images/ListUserSequence.png)

#### 4.1.4. View a user

> This feature allows the user to view a specific user in the user list. Upon successfully sending the command, the Ui will display the details of the searched user

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the ViewUserCommand class to be processed.

Step 2: The ViewUserCommand command checks if the delimiters ('u') is present in the user input through the getArgsViewUserCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid (user can be found or not) by checking whether that user is currently in the user list via the `isValidUser` method. An exception will also be thrown if the final argument does not satisfy the requirements (user not found).

Step 3: If all arguments are valid, then it finds the user in the userList by his/her name, which is unique:

`userList.getUserById(userName);`

Step 4: After finding the user, a message will be displayed to the user via `Ui.viewUserMessage()` method which show the specified user's details.

The following sequence diagram shows how the view user operation works:
![viewUserSequence](images/ViewUserSequence.png)

**...To be updated(Find user)**

### 4.2. Item-related Features

#### 4.2.1. Add an item

> This feature allows user to add a new item to their list and upon successful adding, a confirmation response about the new item will be sent from Ui to user

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the AddItemCommand to be processed.

Step 2: The AddItemCommand command checks if the delimiters ('n', 'c', 'p', 'o') is present in the user input through the getArgsAddItemCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid through `isValidName()`, `isValidOwner()`, `isValidPrice()` and `isValidCatgoryNumber()` methods. An exception will also be thrown if the final argument does not satisfy the requirements (duplicate name of item of the same owner, owner not found, wrong range and format price,...).

Step 3: If all arguments are valid, then it creates a new `Item()` with `args[0]` is itemName, `args[2]` is categoryNumber, and `args[3]` is price and `args[4]` is owner's name

Step 4: After adding the new item, a message will be displayed to the user via `Ui.addItemMessage()` method which show the new item's details and the size of itemList.

The following sequence diagram shows how the add item operation works:
![addUserSequence](images/AddItemSequence.png)

#### 4.2.2 Remove an item

> This feature allows user to remove an item to their list and upon successful removing, a confirmation response about the deleted item will be sent from Ui to user

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the RemoveItemCommand to be processed.

Step 2: The RemoveItemCommand command checks if the delimiters ('i') is present in the user input through the getArgsRemoveItemCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid (item can be deleted or not) by checking whether that item is available (not in any transaction). An exception will also be thrown if the final argument does not satisfy the requirements (item not found, item is unavailable).

Step 3: If all arguments are valid, then it finds and deletes the item in the itemList by its ID, which is unique. However, finished transaction related to that items will not be deleted since if in the future we implement "get profit" feature then it still counts those finished transaction

`itemList.deleteItem(itemId, transactionList);`

Step 4: After deleting an item, a message will be displayed to the user via `Ui.deleteItemMessage()` method which show the deleted item's details and the size of itemList.

The following sequence diagram shows how the remove item operation works:
![removeItemSequence](images/RemoveItemSequence.png)

#### 4.2.3. List items

> This feature allows the user to view details of all items in the item list

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the ListItemsCommand class to be processed.

Step 2: In `ListItemsComamnd::exeucteCommand` work is delegated `ItemList::toString()` to obtain a string representation of all items in the item list

Step 3: The string representation is forwarded to `UI::printResponse` to show it to the user

Step 4: Lastly, `executeCommand` returns false to indicate that this is a non-terminating command

The following sequence diagram shows how the view user operation works:
![ListItemsSequence](images/ListItemSequence.png)

#### 4.2.4. View an item

> This feature allows the user to view a specific item in the item list. Upon successfully sending the command, the Ui will display the details of the searched item

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the ViewItemCommand class to be processed.

Step 2: The ViewItemCommand command checks if the delimiters ('i') is present in the user input through the getArgsViewItemCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid (item can be found or not) by checking whether that item is currently in the item list via the `isValidItem` method. An exception will also be thrown if the final argument does not satisfy the requirements (item not found).

Step 3: If all arguments are valid, then it finds the item in the itemList by itemId, which is unique:

`itemList.getItemById(itemId);`

Step 4: After finding the user, a message will be displayed to the user via `Ui.viewItemMessage()` method which show the specified user's details.

The following sequence diagram shows how the view item operation works:
![viewItemSequence](images/ViewItemSequence.png)

#### 4.2.5. Update an item

> This feature allows the user to update a specific item in the item list

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either throws an exception, or forwards the input to `UpdateItemCommand` class to be processed.

Step 2: `UpdateItemCommand::executeCommand` checks if the delimiters ('i', 'p') is present in the user input with the `getArgsViewItemCmd()` method. If any arguments are not present, an exception will be thrown.

Step 3: If all commands are present, it then checks if arguments are valid with `UpdateItemCommand::areValidArgs` . An exception will also be thrown if the final argument does not satisfy the requirements (item not found or price not valid).

Step 4: If all arguments are valid, it then delegates to `ItemList::updateItemPrice` which finds the item in the itemList by itemId, which is unique and calls `Item::updatePrice`, which returns an Item with updated price

Step 5: `ItemList` then updates itself with the latest item and returns

Step 6: Lastly, information about the updated item is printed to the user

The following sequence diagram models the operation:
![updateItemSequence](images/UpdateItemSequence.png)

#### 4.2.6. Sorting items

> This feature allows user to sort and filter items on their list, and if the input command is correct, a list that is sorted and filtered will be sent from Ui to user

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception or send the input to the SortItemCommand to be processed.

Step 2: The SortItemCommand checks if the delimiters ('mode' (optional), 'min' (optional), 'max' (optional), 'cat' (optional)) are present in the user input through the getArgsSortItemsCmd() method. The default sorting mode is low to high if `/mode` is empty. The command checks whether the input's final argument is valid through `isValidMode()`, `isValidPrice()`, `isValidPriceBoundaries` and `isValidCategoryNumber` methods. An exception will also be thrown if the final argument does not satisfy the requirements (incorrect sort format, format price, incorrect price boundaries...)

Step 3: If all arguments are valid, then `sortAndFilter()` will return a list based on the input arguments.

Step 4: This list of items will then be displayed to the user via `Ui.printResponse()`

The following sequence diagram shows how the sort items operation works:
![sortItemSequence](images/SortItemsSequence.png)

### 4.2.7. List categories

>This feature allow users to view categories that can be assigned to items and their index after executing the command ```list-categories```.

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command ```list-categories``` in the command line. The CommandParser class checks if the command is valid through the createCommand() method.

Step 2: Duke will receive the ```ListCategoriesCommand``` and execute it.

Step 3: Since there is no parameter to check for list categories command, it will execute directly by calling ```Ui.printResponse(Category.listCategories());``` (```Category.listCategories()``` returns a string represent the category list)

Step 4: The ```executeCommand()``` of ExitCommand returns false, so Duke will recognize this command is ```isLastCommand``` and exit the ```while``` loop and stop the program.

### 4.2.8. Find Items by Keyword

>This feature allow users to find items through the command ```find-item```.
> 
> Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command ```find-items /k [keyword]``` in the command line. The CommandParser class checks if the command is valid through the createCommand() method.

Step 2: Duke will receive the ```FindItemsCommand``` and execute it.

Step 3: FindItemsCommand will check for the delimiter "/k". If it is not present, an exception is thrown. Else the command is executed.

Step 4: The ItemList is iterated through to check for Items that match the provided keyword. Matched items are appended to a List which is returned and then printed by Ui.printResponse.

The following sequence diagram models the operation:
![FindItemByKeywordSequence](images/FindItemByKeywordSequence.png)

### 4.3. Transaction-related Features

#### 4.3.1. Add a Transaction

> This feature allows user to add a new transaction to their list and upon successful adding, a confirmation response about the new transaction will be sent from Ui to user

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the AddTransactionCommand to be processed.

Step 2: The AddTransactionCommand command checks if the delimiters ('i', 'b', 'd', 'c') is present in the user input through the getArgsAddItemCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid through `isValidItem()`, `isValidBorrower()`, `isValidDuration()` and `isValidCreatedDate()` methods. An exception will also be thrown if the final argument does not satisfy the requirements (item not found, user not found, duration wrong format, createdDate wrong format...).

Step 3: If all arguments are valid, then it creates a new `transaction()` with `args[0]` is itemId, `args[2]` is borrowerId, and `args[3]` is duration and `args[4]` is createdDate

Step 4: After adding the new transaction, a message will be displayed to the user via `Ui.addTransactionMessage()` method which show the new transaction's details and the size of transactionList.

The following sequence diagram shows how the add transaction operation works:
![addUserSequence](images/AddTransactionSequence.png)

#### 4.3.2. Remove a Transaction

> This feature allows user to remove a transaction to their list and upon successful removing, a confirmation response about the deleted transaction will be sent from Ui to user

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the RemoveTransactionCommand to be processed.

Step 2: The RemoveTransactionCommand command checks if the delimiters ('t') is present in the user input through the getArgsRemoveTransactionCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid (transaction can be found). An exception will also be thrown if the final argument does not satisfy the requirements (transaction not found).

Step 3: If all arguments are valid, then it finds and deletes the transaction in the transactionList by its ID, which is unique.

`transactionList.deleteTransaction(transactionId);`

Step 4: After deleting a transaction, a message will be displayed to the user via `Ui.deleteTransactionMessage()` method which show the deleted transaction's details and the size of transactionList.

The following sequence diagram shows how the remove transaction operation works:
![removeTransactionSequence](images/RemoveTransactionSequence.png)

#### 4.3.3. List transactions

> This feature allows the user to view details of all transactions

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the ListTransactionsCommand class to be processed.

Step 2: In `ListTransactionsCommand::exeucteCommand` work is delegated `TransactionList::toString()` to obtain a string representation of all transactions in the transaction list

Step 3: The string representation is forwarded to `UI::printResponse` to show it to the user

Step 4: Lastly, `executeCommand` returns false to indicate that this is a non-terminating command

The following sequence diagram shows how the view user operation works:
![ListTransactionSequence](images/ListTransactionSequence.png)

#### 4.3.4. View a transaction

> This feature allows the user to view a specific transaction in the transaction list. Upon successfully sending the command, the Ui will display the details of the searched transaction

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the ViewTransactionCommand class to be processed.

Step 2: The ViewTransactionCommand command checks if the delimiters ('u') is present in the user input through the getArgsViewTransactionCmd() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is valid (transaction can be found or not) by checking whether that transaction is currently in the transaction list via the `isValidTransaction` method. An exception will also be thrown if the final argument does not satisfy the requirements (transaction not found).

Step 3: If all arguments are valid, then it finds the transaction in the transactionList by transactionId, which is unique:

`transactionList.getTransactionById(transactionId)`

Step 4: After finding the transaction, a message will be displayed to the user via `Ui.viewTransactionMessage()` method which show the specified transaction's details.

The following sequence diagram shows how the view transaction operation works:
![viewTransactionSequence](images/ViewTransactionSequence.png)

#### 4.3.5. View Transactions By Status

> The viewTransactionsByStatus feature is facilitated by the TransactionList class. It extends the command class to add a command for users to view the history of finished or ongoing transactions for recording purposes. The feature implements the following commands:

- `find-tx /s finished`: Lists down all the transactions that have been completed.
- `find-tx /s unfinished`: Lists down all the transactions that are currently still ongoing.

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either sends an exception, or send the input to the ViewTransactionsByStatus command to be processed.

Step 2: The ViewTransactionsByStatus command checks if the delimiter ('s') is present in the user input through the getArgs() method. If not present, an exception will be thrown. The command also checks whether the input's final argument is 'finished' or 'unfinished' through the isValidArgument() method. An exception will also be thrown if the final argument does not match the required words.

Step 3: Assuming the final argument is 'finish', the entire transactionList will be iterated through, and an array will store the transaction if the transaction.isFinished() status is true.

Step 4: Once the transactionList has been completely iterated through, a message will be displayed to the user via Ui.viewcompletedTransactionsMessage() which lists down all the transactions that have their isFinished() status set to true.

The following sequence diagram shows how the viewTransactionsByStatus operation works:

![viewTransactionsByStatus](images/ViewTransactionByStatusSequence.png)

#### 4.3.6. Update an transaction

> This feature allows the user to update a specific item in the item list

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command in the command line. The CommandParser class checks if the command is valid through the createCommand() method, and either throws an exception, or forwards the input to `UpdateTransactionCommand` class to be processed.

Step 2: `UpdateTransactionCommand::executeCommand` checks if the delimiters ('t', 'd') is present in the user input with the `getArgsViewItemCmd()` method. If any arguments are not present, an exception will be thrown.

Step 3: If all commands are present, it then checks if arguments are valid with `UpdateTransactionCommand::areValidArgs` . An exception will also be thrown if the final argument does not satisfy the requirements (transaction not found or duration not valid).

Step 4: If all arguments are valid, it then delegates to `TransactionList::updateTransactionDuration` which finds the transaction in the list by `transactionId`, which is unique and calls `Transaction::updateDuration`, which returns a Transaction with updated duration

Step 5: `TransactionList` then updates itself with the latest item and returns

Step 6: Lastly, information about the updated transaction is printed to the user

The following sequence diagram models the operation:
![updateTransactionSequence](images/UpdateTransactionSequence.png)


**...To be updated(List, Update transaction)**

### 4.4. Help Command

### 4.5. Exit Command

>This feature allow users to exit the program, and they will receive goodbye message after executing the command ```help```.

Given below is an example usage scenario and how the command mechanism behaves at each step.

Step 1: The user types in the command ```help``` in the command line. The CommandParser class checks if the command is valid through the createCommand() method.

Step 2: Duke will receive the ExitCommand and execute it.

Step 3: Since there is no parameter to check for exit command, it will execute directly by calling ```Ui.printExitMessage()```

Step 4: The ```executeCommand()``` of ExitCommand returns false, so Duke will recognize this command is ```isLastCommand``` and exit the ```while``` loop and stop the program.


## 5. Product scope

### 5.1. Target user profile

- Student at NUS
- Reasonably comfortable using CLI
- Student staying at campus
- Has a passion to serve the student community
- Student who wants to host a rental business for his/her hall
- Student who wants to help others borrow or loan items

### 5.2. Value proposition

Upcycle is a Database Management System that aims to promote frugality and sustainability by providing service for students
staying in a particular community/hall to loan or borrow items they wish to share with their community.

## 6. User Stories

| Version | As a ... | I want to ...               | So that I can ...                                             |
|---------|----------|-----------------------------|---------------------------------------------------------------|
| v1.0    | new user | see usage instructions      | refer to them when I forget how to use the application        |
| v1.0    | manager  | add a user                  | he may borrow and loan items                                  |
| v1.0    | manager  | view a specific user        | know what items he is borrowing and loaning                   |
| v1.0    | manager  | view all users              | see who are available for loaning and borrowing               |
| v1.0    | manager  | delete a user               | remove the user if he violates community guidelines           |
| v1.0    | manager  | add an item                 | make the item available for loan                              |
| v1.0    | manager  | delete an item              | remove the item when a user decides not to loan it            |
| v1.0    | manager  | create a transaction        | keep a record of that transaction                             |
| v1.0    | manager  | view a transaction          | keep track of the status of the transaction                   |
| v1.0    | manager  | delete a transaction        | remove transaction when users change their mind               |
| v2.0    | manager  | find a item by keyword      | locate an item without going through the list                 |
| v2.0    | manager  | find a user by keyword      | locate a user without going through the list                  |
| v2.0    | manager  | view transactions by status | view the transactions that are finished or not finished       |
| v2.0    | manager  | sort items                  | view items based on its price                                 |
| v2.0    | manager  | update price                | change the price a user decides to loan his item for          |
| v2.0    | manager  | update transaction          | change the number of days a user decides to loan his item for |
| v2.0    | manager  | store my database           | maintain the database without losing my data                  |

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

![Figure: ItemList Object Diagram](https://raw.githubusercontent.com/AY2223S1-CS2113-W12-1/tp/master/docs/diagrams/ItemList.png)

The user enters the following command: “find-item /k Book”. In this case, the keyword is book. The entire ItemList is iterated through, and an ItemList containing all the Items which contain the keyword is returned. 3 Items contain the keyword “Book”, hence these 3 items are returned by the function ItemList.getItemsByKeyword. This ItemList is then converted to a String via the method ItemList.toString, and printed by Ui.printResponse so that the user is able to see all the matching Items. The sequence diagram is shown below.

![Figure: FindItem Sequence Diagram](https://raw.githubusercontent.com/AY2223S1-CS2113-W12-1/tp/master/docs/diagrams/FindItemSequence.png)

## 7. Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3. Should work for only one user.
4. Should work without Internet connection.

## 8. Glossary

...To be updated

## 9. Instructions for manual testing

...To be updated

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
