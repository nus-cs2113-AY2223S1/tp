# Upcycle - User Guide

## Table of contents

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)\
    3.1 [Getting help](#31-getting-help)\
    3.2 [User-related features](#32-user-related-features)\
    3.3 [Item-related features](#33-item-related-features)\
    3.4 [Transaction-related features](#34-transaction-related-features)\
    3.5 [Exit program](#35-exit-program)
4. [Command Summary](#4-command-summary)
5. [Frequently Asked Questions](#5-frequently-asked-questions)

## 1. Introduction

Welcome to Upcycle - a perfect desktop app dedicate for managing rental businesses. The nature of these businesses 
requires to deal with tons of data; therefore, noting down all information by notes and pen is not ideal for managers. 
It is developed for rental business managers, who can type fast to efficiently keep track all of their customers, items, 
and transactions via Command Line Interface with a single line of command. 

This UserGuide introduces you a brief overview of our features with example of usage and expected outcome.

>**Note:**
> 
>1. Upcycle commands are case-sensitive and space-insensitive. For example, ```upcycle``` and ```Upcycle``` are different words, ```Upcycle``` and ```Upcycle ``` are the same words.
>2. Parameters can be shuffled. For example, ```update-item /i [ITEM_ID] /p [PRICE]``` and ```update-item /p [PRICE] /i [ITEM_ID]``` are the same.
>3. Phrases in ```[CAPITAL_WORDS]``` are the parameters for you to input.

Let's hop into the section [2. Quick Start](#2-quick-start) to start using Upcycle quickly.

## 2. Quick Start

1. Install Java 11 on your computer.
2. Download the latest release of ```Upcycle.jar``` [here](https://github.com/AY2223S1-CS2113-W12-1/tp/releases).
3. Move the file to the desired folder for Upcycle.
4. Open the Command Prompt (for Windows) or Terminal (for Unix) and redirect to home folder of Duke.
5. Launch the chatbot by using ```java -jar Upcycle.jar``` on Command Prompt for Windows or Terminal for Unix OS.

   You are supposed to see a greeting message like this if your setup is correct:
```
____________________________________________________________
Hello from
                             _      
 /\ /\ _ __   ___ _   _  ___| | ___ 
/ / \ \ '_ \ / __| | | |/ __| |/ _ \
\ \_/ / |_) | (__| |_| | (__| |  __/
 \___/| .__/ \___|\__, |\___|_|\___|
      |_|         |___/             
What would you like to do?
To get started, type "help" to see the list of available commands
____________________________________________________________
```
6. Type the valid command and press Enter to run the command. See [Feature](#3-features) or [Command Summary](#4-command-summary) for more information of commands.

><span style="color: #FF1A00;">**IMPORTANT**</span>: **DO NOT** edit any files in ```/data/``` folder. This action potentially cause unfixable bugs when execute the program, and you may have to edit again or in the worst case, delete all storage file and all of your data will be gone forever.

## 3. Features 

This section allows users to understand all the features we offered, including the format, the constraint. We also demonstrate some examples of usage and the expected outcome.

### 3.1. Getting help

>Get the list of all commands' format and their description

Format: ```help```

Expected outcome:
```
____________________________________________________________

ADD-RELATED-COMMANDS: 
--------------------
Add a user: add-user /n <userName> /a <age> /c <contactNumber>
Add a item: add-item /n <itemName> /c <categoryIndex> /p <price> /o <userName>
Add a transaction: add-tx /i <itemId> /b <borrowerName> /d <duration> /c <createdAt>

REMOVE-RELATED-COMMANDS: 
--------------------
Remove a user: remove-user /u <userName>
Remove a item: remove-item /i <itemId>
Remove a transaction: remove-tx /t <transactionId>

LIST-RELATED-COMMANDS: 
--------------------
List all commands: help
List all users: list-users
List all items: list-items
List all categories available: list-categories
List all transactions: list-tx

VIEW-RELATED-COMMANDS: 
--------------------
View a user: view-user /u <userName>
View a item: view-item /i <itemId>
View a transaction: view-tx /t <transactionId>

UPDATE-RELATED-COMMANDS: 
--------------------
Update price of an item: update-item /i <itemId> /p <price>
Update duration of a transaction: update-tx /t <transactionid> /d <duration>

FIND-RELATED-COMMANDS: 
--------------------
Find all finished transactions: find-tx /s finished
Find all unfinished transactions: find-tx /s unfinished
Find user by keywords: find-user /k <keyword>
Find item by keywords: find-item /k <keyword>
Sort all items in a range: sort-items /mo <mode: hl or  lh> /mi <min> /ma <max>
____________________________________________________________
```

### 3.2. User-related features

#### 3.2.1. ```add-user``` - Add a new user
>Add a new user to the list

Format: ```add-user /n [USERNAME] /a [AGE] /c [CONTACT_NUMBER]```

Note:
1. Username is unique
2. Age must be an integer in range from 10 to 100
3. Contact number must an integer of 8 digits

Example of usage: ```add-user /n bui /a 20 /c 82364873```

Expected outcome:
```
____________________________________________________________
Noted. Following user has been added: 
Username: bui Age: 20 Contact: 82364873 
Total user(s) in database: 6
____________________________________________________________
```

#### 3.2.2. ```remove-user``` - Remove a user
>Remove a user in the list

Format: ```remove-user /u [USERNAME]```

Note:
1. The user to be deleted should not borrow or lend any items at the moment
2. All the items of this user will also be deleted

Example of usage: ```remove-user /u thanh```

Expected outcome:
```
____________________________________________________________
Noted. Following user has been deleted: 
Username: thanh Age: 20 Contact: 73648263 
Total user(s) in database: 6
____________________________________________________________
```

#### 3.2.3. ```list-users``` - List all users
>View the details of all user in the list

Format: ```list-users```

Example of usage: ```list-users```

Expected outcome:
```
____________________________________________________________
Here are 2 user(s) in your list:
   1. Username: WinstonLimCherHong Age: 22 Contact: 91824633 
   2. Username: test2 Age: 21 Contact: 91234557 
____________________________________________________________
```

#### 3.2.4. ```view-user``` - View a user
>View the details of a user in the list

Format: ```view-user /u [USERNAME]```

Note:
1. Username must be present in the user list

Example of usage: ```view-user /u jingwei```

Expected outcome:
```
____________________________________________________________
Here is the user you have requested to view: 
Username: jingwei Age: 19 Contact: 22384729 
____________________________________________________________
```

**...To be updated(Find user)**

### 3.3. Item-related features
#### 3.3.1. ```add-item``` - Add a new item
>Add a new item to the list

Format: ```add-item /n [ITEM_NAME] /c [CATEGORY_INDEX] /p [PRICE] /o [USERNAME]```

Note:
1. Owner cannot have duplicate items (items have the same name)
2. To choose category, please use ```list-categories``` to list them out and use the index
3. Price must be a float, and in range from 0 to 10000

Example of usage: ```add-item /n weight /c 1 /p 0.5 /o bui```

Expected outcome:
```
____________________________________________________________
Noted. Following item has been added: 
Status: [Available] ItemId: 2cc4edf3 Item: weight Category: SPORTS_EQUIPMENT Owner: bui PricePerDay: $0.5
Total item(s) in database: 2
____________________________________________________________
```

#### 3.3.2. ```remove-item``` - Remove an item
>Remove an item in the list

Format: ```remove-item /i [ITEM_ID]```

Note:
1. The item to be deleted must be available (not in any transaction)

Example of usage: ```remove-item /i 2cc4edf3```

Expected outcome:
```
____________________________________________________________
OK! I will remove the following item:
Status: [Available] ItemId: 2cc4edf3 Item: weight Category: SPORTS_EQUIPMENT Owner: bui PricePerDay: $0.5
Total item(s) in database: 1
____________________________________________________________
```

#### 3.3.3. ```list-items``` - View all items
>View all items

Format: ```list-items```

Example of usage: ```list-items```

Expected outcome:
```
____________________________________________________________
Here are 1 item(s) in your list:
   1. Status: [On loan] ItemId: ea608c61 Item: toy Category: SPORTS_EQUIPMENT Owner: WinstonLimCherHong PricePerDay: $4.0
____________________________________________________________
```

#### 3.3.4. ```view-item``` - View a specific item
>View an item based on the itemId requested by the user

Format: ```view-item /i [ITEM_ID]```

Note:
1. The itemId must be available (Present in item list)

Example of usage: ```view-item /i 99995bb2```

Expected outcome:
```
____________________________________________________________
Here is the item you requested: 
Status: [Available] ItemId: 99995bb2 Item: scale Category: SPORTS_EQUIPMENT Owner: jingwei PricePerDay: $1.0
____________________________________________________________
```

#### 3.3.5. ```update-item``` - Updates properties of an item
> Currently only supports updating the price of an item

Format: ```update-item /i [ITEM_ID] /p [NEW_PRICE]```

Note:

1. `itemId` must exist i.e. item has been created and was not deleted 
2. A valid price must be given i.e. non-zero and non-negative
3. Price is in price per day, and in range from 0 to 10000
4. You may or may not use floating point numbers for price

Example of usage: ```update-item /i ea608c61 /p 4```

Expected outcome:
```
____________________________________________________________
Done! Here is the item you updated
Status: [On loan] ItemId: ea608c61 Item: toy Category: SPORTS_EQUIPMENT Owner: WinstonLimCherHong PricePerDay: $4.0
____________________________________________________________
```

#### 3.3.6. ```sort-items``` - Sort and filter list of items
>Sort and filter list of items based on the mode of sorting, price boundaries and category requested by the user

Format: ```sort-items /mode [MODE_OF_SORTING] /min [MIN_PRICE] /max [MAX_PRICE] /cat [CATEGORY_NUMBER]```

Note:

1. Mode of sorting must either be ```lh``` (low to high) or ``` hl``` (high to low)
2. Minimum price, maximum price and category filters are optional
3. Minimum and Maximum price must be more than 0
4. Minimum price must be less than maximum price, and both are in range form 0 to 10000
5. Category number must be an integer

Example of usage: ```sort-items /mode lh /min 1 /max 5```

Expected outcome:
```
____________________________________________________________
Here are 2 item(s) in your filtered list:
   1. Status: [Available] ItemId: e084cd0a Item: battery Category: ELECTRICAL_APPLIANCES Owner: jingwei PricePerDay: $1.5
   2. Status: [Available] ItemId: 4621bcf6 Item: charger Category: ELECTRICAL_APPLIANCES Owner: winston PricePerDay: $2.0
____________________________________________________________
```

#### 3.3.7. ```list-categories``` - List all categories 
>List all categories that can be assigned to items and their index

Format: ```list-categories```

Note:

1. The index of categories is also listed and this number is used in ```add-item``` command

Example of usage: ```list-categories```

Expected outcome:
```
____________________________________________________________
Here are available categories: 
1. SPORTS_EQUIPMENT
2. TEXTBOOKS_AND_NOTES
3. ELECTRICAL_APPLIANCES
4. FURNITURE
5. KITCHEN_ITEMS
6. VEHICLES
7. CLOTHING
8. OTHERS
____________________________________________________________
```
#### 3.3.8. ```find-item``` - Find an item using keyword
>List all items that are associated with a given keyword

Example of usage: ```find-item /k book```

Expected outcome: 
```
____________________________________________________________
Here are items that match your keyword: 
1. TEXTBOOK
3. CHINESE BOOK
7. MATH BOOK
8. OLD BOOK
____________________________________________________________
```

### 3.4. Transaction-related features
#### 3.4.1. ```add-tx``` - Add a new transaction
>Add a new transaction to the list

Format: ```add-tx /i [ITEM_ID] /b [BORROWER_NAME] /d [DURATION] /c [CREATED_DATE]```

Note:
1. Duration must be an integer, greater than 0
2. The format of create date is YYYY-MM-DD, and it must be before the input date

Example of usage: ```add-tx /i 2cc4edf3 /b thanh /d 5 /c 2022-10-20```

Expected outcome:
```
____________________________________________________________
OK! I will add the following transaction:
Status: [On loan] TransactionID: 80902c3b ItemName: weight ItemID: 2cc4edf3 BorrowerID: thanh ReturnDate: Tue, Oct 25 2022 (4 day(s) remaining)
Total transaction(s) in database: 3
____________________________________________________________
```

#### 3.4.2. ```remove-tx``` - Remove a transaction
>Remove a transaction in the list

Format: ```remove-tx /t [TRANSACTION_ID]```

Note:
1. You can use ```list-tx``` to get the transaction ID you want to delete

Example of usage: ```remove-tx /t 80902c3b```

Expected outcome:
```
____________________________________________________________
OK! I will remove the following item:
Status: [On loan] TransactionID: 80902c3b ItemName: weight ItemID: 2cc4edf3 BorrowerID: thanh ReturnDate: Tue, Oct 25 2022 (4 day(s) remaining)
Total transactions(s) in database: 2
____________________________________________________________
```

#### 3.4.3. ```list-tx``` - View all transactions
>View the details of all transactions in the list

Format: ```list-tx```

Example of usage: ```list-tx```

Expected outcome:
```
____________________________________________________________
Here are 1 transaction(s) in your list:
   1. Status: [On loan] TransactionID: 2c833e49 ItemName: toy ItemID: ea608c61 BorrowerID: test2 ReturnDate: Sat, Nov 05 2022 (11 day(s) remaining)
____________________________________________________________
```

#### 3.4.4. ```view-tx``` - View a transaction
>View the details of a transaction in the list

Format: ```view-tx /t [TRANSACTION_ID]```

Note:
1. You can use ```list-tx``` to get the transaction ID you want to view

Example of usage: ```remove-tx /t 4f7f7fe8```

Expected outcome:
```
____________________________________________________________
Here is the transaction you requested to view: 
Status: [Returned] TransactionID: 4f7f7fe8 ItemName: weight ItemID: 5b727b2e BorrowerID: jingwei ReturnedDate: Sat, Oct 08 2022
____________________________________________________________
```

#### 3.4.5. ```update-tx``` - Updates properties of a transaction
>Currently, supports only updating the duration of a transaction

Format: ```update-tx /t [TRANSACTION_ID] /d [NEW_DURATION]```

Note:
1. `transactionId` must exist i.e. Transaction was created and has not been removed
2. Duration must be a valid duration i.e. non-zero and non-negative
3. Duration is in days and therefore should be a whole number

Example of usage: ```update-tx /t 2c833e49 /d 11```

```
____________________________________________________________
Done! Here is the updated transaction:
Status: [On loan] TransactionID: 2c833e49 ItemName: toy ItemID: ea608c61 BorrowerID: test2 ReturnDate: Sat, Nov 05 2022 (11 day(s) remaining)
____________________________________________________________

```

**...To be updated(View Tx by status)**

### 3.5. Exit Program

>Exit the program

Format: ```bye```

Note:
1. After running this command, your data in user list, item list and transaction list are stored in 3 file 
```user.txt```, ```item.txt```, and ```transaction.txt``` in ```/data/``` folders, respectively

Example of usage: ```bye```

Expected outcome:
```
____________________________________________________________
Bye! See you again
____________________________________________________________
```


## 4. Command Summary

| **Features**                       | **Format**                                                                                          |
|------------------------------------|-----------------------------------------------------------------------------------------------------|
| __*User-related features*__        |                                                                                                     |
| Add a new user                     | add-user /n [USERNAME] /a [AGE] /c [CONTACT_NUMBER]                                                 |
| Remove a user                      | remove-user /u [USERNAME]                                                                           |
| View a user                        | view-user /u [USERNAME]                                                                             |
| List all users                     | list-users                                                                                          |
| __*Item-related features*__        |                                                                                                     |
| Add a new item                     | add-item /n [ITEM_NAME] /c [CATEGORY_INDEX] /p [PRICE] /o [USERNAME]                                |
| Remove an item                     | remove-item /i [ITEM_ID]                                                                            |
| List all items                     | list-items                                                                                          |
| View an item                       | view-item /i [ITEM_ID]                                                                              |
| List categories                    | list-categories                                                                                     |
| Update an item                     | update-item /i [ITEM_ID] /p [NEW_PRICE]                                                             |
| Sort and filter list of items      | sort-items /mode [MODE_OF_SORTING] /min [MINIMUM_PRICE] /max [MAXIMUM_PRICE] /cat [CATEGORY_NUMBER] |
| __*Transaction-related features*__ |                                                                                                     |
| Add a new transaction              | add-tx /i [ITEM_ID] /b [BORROWER_NAME] /d [DURATION] /c [CREATED_DATE]                              |
| List all users                     | list-tx                                                                                             |
| Remove a transaction               | remove-tx /t [TRANSACTION_ID]                                                                       |
| View a transaction                 | view-tx /t [TRANSACTION_ID]                                                                         |
| Update a transaction               | update-tx /t [TRANSACTION_ID] /d [NEW_DURATION]                                                     |
| __*Others*__                       |                                                                                                     |
| Get help                           | help                                                                                                |
| Exit program                       | bye                                                                                                 |

## 5. Frequently Asked Questions

How do I transfer my data to another computer?

>On your other computer, download your ```Upcycle.jar``` file following the instructions in [Quick Start](#2-quick-start). Then, you just copy the \data\ folder and paste into your folder for Upcycle on your new computer. That's it, now you can run our app with your previous data on another computer.


