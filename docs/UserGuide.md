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

Welcome to Upcycle - a perfect desktop app dedicate to managing rental businesses. The nature of these businesses
requires dealing with tons of data; therefore, noting down all information with notes and pen is not ideal for managers.
It is developed for rental business managers, who can type fast to efficiently keep track of all of their customers, items,
and transactions via a Command Line Interface.

This UserGuide introduces you a brief overview of our features with example of usage and expected outcome. Let's hop into the section [2. Quick Start](#2-quick-start) to start using Upcycle quickly.

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

## 3. Features 

This section allows users to understand all the features that we offer, including the format, and the constraint. We also demonstrate some examples of usage and the expected outcome.

**PLEASE TAKE NOTE:**
>
>1. Upcycle commands (except ```find-item``` and ```find-user```) are case-sensitive and space-insensitive. For example, ```upcycle``` and ```Upcycle``` are different words, ```Upcycle``` and ```Upcycle ``` are the same words.
>2. Parameters can be shuffled. For example, ```update-item /i [ITEM_ID] /p [PRICE]``` and ```update-item /p [PRICE] /i [ITEM_ID]``` are the same.
>3. Phrases in ```[CAPITAL_WORDS]``` are the parameters for you to input.
>4. Argument value cannot contain ```/``` or ```|```.
>5. You must put a space between delimiter and value. For example, ```/nbuiducthanh``` is an error, but ```/n buiducthanh``` is correct
>6. Note that price inputs can only have at most 2 decimal places:
>       1. 0.5 → $0.50 (GOOD)
>       2. 1 → $1.00 (GOOD)
>       3. 1.01 → $1.01 (GOOD)
>       4. 0.9666 → error (BAD)


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
View a user's items: view-user-items /u <userName>
View a item: view-item /i <itemId>
View a transaction: view-tx /t <transactionId>
View list of user's borrow transaction: view-borrow-tx-by-user /u <userName>
View list of user's lend transaction: view-lend-tx-by-user /u <userName>
View the amount of money loss of a user: view-user-loss /u <userName>
View the amount of money gain of a user: view-user-gain /u <userName>

UPDATE-RELATED-COMMANDS: 
--------------------
Update price of an item: update-item /i <itemId> /p <price>
Update duration of a transaction: update-tx /t <transactionId> /d <duration>

FIND-RELATED-COMMANDS: 
--------------------
Find all finished transactions: find-tx /s finished
Find all unfinished transactions: find-tx /s unfinished
Find user by keywords: find-user /k <keyword>
Find item by keywords: find-item /k <keyword>
Sort all items in a range: sort-items /mode <mode: hl or lh> /min <min> /max <max> /cat <categoryIndex>

ADDITIONAL-DETAILS: 
--------------------
Please take note to add a space before and after delimiters! e.g ' /c '
Please note that except for 'find-item' and 'find-user', all other commands are case-sensitive!
Refrain from using '|' and '/' as arguments!
____________________________________________________________
```

### 3.2. User-related features

#### 3.2.1. ```add-user``` - Add a new user
>Add a new user to the list

Format: ```add-user /n [USERNAME] /a [AGE] /c [CONTACT_NUMBER]```

Note:
1. Username is unique, length must be no more than 20 chars
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
2. All the items of this user will also be deleted, but not transactions.

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
>View the details of all users in the list

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
>View the details of a user in the list including the user's items and his/her gain and loss

Format: ```view-user /u [USERNAME]```

Note:
1. Username must be present in the user list

Example of usage: ```view-user /u bui```

Expected outcome:
```
____________________________________________________________
Here is the user you have requested to view: 
Username: bui Age: 20 Contact: 12345678 
The user's loss is $0.00
The user's gain is $0.00
Here are 2 item(s) in the list:
1. [Available] ItemId: 69b69ff2
   Item name: charger
   Category: ELECTRICAL_APPLIANCES
   Owner: bui
   PricePerDay: $1.00
2. [Available] ItemId: 63583a01
   Item name: battery
   Category: ELECTRICAL_APPLIANCES
   Owner: bui
   PricePerDay: $20.00
____________________________________________________________
```

#### 3.2.5. ```view-user-items``` - View user's items
>An extension to view-user command which shows a user's items only

Format: `view-user-items /u [USERNAME]`

Note:
1. Username must be present in the user list

Example of usage: `view-user-items /u bui`

Expected outcome:
```
____________________________________________________________
Here are 2 item(s) in the list:
1. [Available] ItemId: 69b69ff2
   Item name: charger
   Category: ELECTRICAL_APPLIANCES
   Owner: bui
   PricePerDay: $1.00
2. [Available] ItemId: 63583a01
   Item name: battery
   Category: ELECTRICAL_APPLIANCES
   Owner: bui
   PricePerDay: $20.00
____________________________________________________________
```

#### 3.2.6. ```find-user``` - Find users using keyword
>List all users that are associated with a given keyword

Format: ```find-user /k [KEYWORD]```

Note:
1. This command is case-insensitive

Example of usage: ```find-user /k ng```

Expected outcome:
```
____________________________________________________________
Here are 2 user(s) in your list:
1. Username: jingwei Age: 21 Contact: 22384729 
2. Username: yixiang Age: 21 Contact: 69324729 
____________________________________________________________
```

#### 3.2.7. ```view-user-loss``` - Find user's loss
>View user's gain or loss based on transactions

Format: ```view-user-loss /u [USERNAME]```

Example of usage: ```view-user-loss /u jingwei```

Expected outcome:
```
____________________________________________________________
The amount of money loss of jingwei is: $2.50
____________________________________________________________
```

#### 3.2.8. ```view-user-gain``` - Find user's gain
>View user's gain or loss based on transactions

Format: ```view-user-gain /u [USERNAME]```

Example of usage: ```view-user-gain /u bui```

Expected outcome:
```
____________________________________________________________
The amount of money earned of bui is: $2.50
____________________________________________________________
```

#### 3.2.9. ```view-borrow-tx-by-user``` - View borrower's transactions
>View transactions in which given user is a borrower

Format: ```view-borrow-tx-by-user /u [USERNAME]```

Example of usage: ```view-borrow-tx-by-user /u jingwei```

Expected outcome:
```
____________________________________________________________
Here are 2 transaction(s) you want to view:
1. [Finished] TxID: 88de8884
   ItemName: weight ItemID: fc3f71ae
   Lender: bui
   Borrower: jingwei
   Duration: 5
   ReturnedDate: Tue, Oct 25 2022
   MoneyTransacted: $2.50 
2. [Finished] TxID: 6a99ef95
   ItemName: book ItemID: 8362c71a
   Lender: bui
   Borrower: jingwei
   Duration: 5
   ReturnedDate: Tue, Oct 25 2022
   MoneyTransacted: $2.50 
____________________________________________________________
```

#### 3.2.10. ```view-lend-tx-by-user``` - View lender's transactions
>View transactions in which given user is a lender

Format: ```view-lend-tx-by-user /u [USERNAME]```

Example of usage: ```view-lend-tx-by-user /u bui```

Expected outcome:
```
____________________________________________________________
Here are 2 transaction(s) you want to view:
1. [Finished] TxID: 88de8884
   ItemName: weight ItemID: fc3f71ae
   Lender: bui
   Borrower: jingwei
   Duration: 5
   ReturnedDate: Tue, Oct 25 2022
   MoneyTransacted: $2.50 
2. [Finished] TxID: 6a99ef95
   ItemName: book ItemID: 8362c71a
   Lender: bui
   Borrower: jingwei
   Duration: 5
   ReturnedDate: Tue, Oct 25 2022
   MoneyTransacted: $2.50 
____________________________________________________________
```
### 3.3. Item-related features
#### 3.3.1. ```add-item``` - Add a new item
>Add a new item to the list

Format: ```add-item /n [ITEM_NAME] /c [CATEGORY_INDEX] /p [PRICE] /o [USERNAME]```

Note:
1. Item name length must be less than 20 chars
2. To choose category, please use ```list-categories``` to list them out and use the index
3. Price must be a float, and in range from 0 to 10000
4. Note that price must have at most 2 decimal places

Example of usage: ```add-item /n weight /c 1 /p 0.5 /o bui```

Expected outcome:
```
____________________________________________________________
Noted. Following item has been added: 
Status: [Available] ItemId: 2cc4edf3 
   Item: weight 
   Category: SPORTS_EQUIPMENT 
   Owner: bui 
   PricePerDay: $0.50
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
Status: [Available] ItemId: 2cc4edf3 
   Item: weight 
   Category: SPORTS_EQUIPMENT 
   Owner: bui 
   PricePerDay: $0.50
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
Here are 1 item(s) in the list:
1. Status: [On loan] ItemId: ea608c61 
   Item: toy 
   Category: SPORTS_EQUIPMENT 
   Owner: WinstonLimCherHong 
   PricePerDay: $4.00
____________________________________________________________
```

#### 3.3.4. ```view-item``` - View a specific item
>View an item based on the itemId requested by the user

Format: ```view-item /i [ITEM_ID]```

Note:
1. The itemId must be available (Present in item list)
2. To get item ID, use ```list-items```

Example of usage: ```view-item /i 99995bb2```

Expected outcome:
```
____________________________________________________________
Here is the item you requested: 
Status: [Available] ItemId: 99995bb2 
   Item: scale 
   Category: SPORTS_EQUIPMENT 
   Owner: jingwei 
   PricePerDay: $1.00
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
5. Note that price must have at most 2 decimal places

Example of usage: ```update-item /i ea608c61 /p 4```

Expected outcome:
```
____________________________________________________________
Done! Here is the item you updated
Status: [On loan] ItemId: ea608c61 
   Item: toy 
   Category: SPORTS_EQUIPMENT 
   Owner: WinstonLimCherHong 
   PricePerDay: $4.00
____________________________________________________________
```

#### 3.3.6. ```sort-items``` - Sort and filter list of items
>Sort and filter list of items based on the mode of sorting, price boundaries and category requested by the user

Format: ```sort-items /mode [MODE_OF_SORTING] /min [MIN_PRICE] /max [MAX_PRICE] /cat [CATEGORY_NUMBER]```

Note:

1. Mode of sorting must either be ```lh``` (low to high) or ``` hl``` (high to low) (default: ```lh```)
2. Mode, minimum price, maximum price and category filters are **optional**. If you do not use, please exclude the whole part (for example: exclude ```/min [MIN_PRICE]```)
3. Minimum and Maximum price must be no less than 0 and no more than 10000
4. Minimum price must be less than maximum price
5. Note that price can only have at most 2 decimal places
6. Category number must be an integer from 1 to 8, (default: 0, which means all categories)

Example of usage: ```sort-items /mode lh /min 1 /max 5 /cat 3```

Expected outcome:
```
____________________________________________________________
Here are 2 item(s) in your filtered list:
1. Status: [Available] ItemId: e084cd0a 
   Item: battery 
   Category: ELECTRICAL_APPLIANCES 
   Owner: jingwei 
   PricePerDay: $1.50
2. Status: [Available] ItemId: 4621bcf6 
   Item: charger 
   Category: ELECTRICAL_APPLIANCES 
   Owner: winston 
   PricePerDay: $2.00
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
#### 3.3.8. ```find-item``` - Find items using keyword
>List all items that are associated with a given keyword

Format: ```find-item /k [KEYWORD]```

Note:
1. This command is case-insensitive

Example of usage: ```find-item /k book```

Expected outcome: 
```
____________________________________________________________
Here are 3 item(s) in the list:
1. Status: [Available] ItemId: 18c90077 
   Item: book 
   Category: TEXTBOOKS_AND_NOTES
   Owner: jingwei 
   PricePerDay: $99.00
2. Status: [Available] ItemId: 4e6f4b89 
   Item: books1 
   Category: TEXTBOOKS_AND_NOTES
   Owner: jingwei 
   PricePerDay: $99.00
3. Status: [Available] ItemId: 75d7d384 
   Item: textbook 
   Category: TEXTBOOKS_AND_NOTES
   Owner: jingwei 
   PricePerDay: $99.00
____________________________________________________________
```

### 3.4. Transaction-related features
Transactions have 2 statuses: ```finished``` or ```unfinished```. This is to indicate if the item is currently being loaned or is available for loan
#### 3.4.1. ```add-tx``` - Add a new transaction
>Add a new transaction to the list

Format: ```add-tx /i [ITEM_ID] /b [BORROWER_NAME] /d [DURATION] /c [CREATED_DATE]```

Note:
1. The unit of duration is days
2. Duration must be an integer, in range from 0 to 1461 days (4 years)
3. The format of create date is YYYY-MM-DD, and it must be before the input date
4. The item must be available during the period of new transaction.
5. The moneyTransacted of transaction will only use the pricePerDay of the items at the moment of input. If the item's price is updated, it will not affect the moneyTransacted.
6. First parameter of the transaction message shows the status of the transaction (Finished/Unfinished).

Example of usage: ```add-tx /i 3ff10798 /b bui /d 5 /c 2022-10-20```

Expected outcome:
```
____________________________________________________________
OK! I will add the following transaction:
[Finished] TxID: 7ddc865f 
   ItemName: speaker ItemID: 3ff10798
   Lender: thanh 
   Borrower: bui 
   Duration: 5 
   ReturnedDate: Tue, Oct 25 2022 
   MoneyTransacted: $10.00
Total transaction(s) in database: 3
____________________________________________________________
```

#### 3.4.2. ```remove-tx``` - Remove a transaction
>Remove a transaction in the list

Format: ```remove-tx /t [TRANSACTION_ID]```

Note:
1. You can use ```list-tx``` to get the transaction ID you want to delete

Example of usage: ```remove-tx /t 7ddc865f```

Expected outcome:
```
____________________________________________________________
OK! I will remove the following item:
[Finished] TxID: 7ddc865f 
   ItemName: speaker ItemID: 3ff10798 
   Lender: thanh 
   Borrower: bui 
   Duration: 5 
   ReturnedDate: Tue, Oct 25 2022 
   MoneyTransacted: $10.00
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
Here are 2 transaction(s) you want to view:
1. [Unfinished] TxID: 55e36921 
   ItemName: scale ItemID: f15dff20 
   Lender: thanh 
   Borrower: jorelle 
   Duration: 5 
   ReturnDate: Fri, Oct 28 2022 (0 day(s) left) 
   MoneyTransacted: $5.00
2. [Unfinished] TxID: 9e27c530 
   ItemName: laptop ItemID: bd4961ed 
   Lender: thanh 
   Borrower: winston 
   Duration: 100 
   ReturnDate: Sat, Jan 21 2023 (85 day(s) left) 
   MoneyTransacted: $900.0 
____________________________________________________________
```

#### 3.4.4. ```view-tx``` - View a transaction
>View the details of a transaction in the list

Format: ```view-tx /t [TRANSACTION_ID]```

Note:
1. You can use ```view-tx``` to get the transaction ID you want to view

Example of usage: ```view-tx /t 9e27c530```

Expected outcome:
```
____________________________________________________________
Here is the transaction you requested to view: 
[Unfinished] TxID: 9e27c530 
   ItemName: laptop ItemID: bd4961ed 
   Lender: thanh 
   Borrower: winston 
   Duration: 100 
   ReturnDate: Sat, Jan 21 2023 (85 day(s) left) 
   MoneyTransacted: $900.00
____________________________________________________________
```

#### 3.4.5. ```find-tx``` - Find a transaction by status
>Find all the transactions that have finished or are still ongoing

Format: ```find-tx /s finished``` OR ```find-tx /s unfinished```

Example of usage: ```find-tx /s unfinished```

Expected outcome:
```
____________________________________________________________
Here are the uncompleted transactions: 
[Unfinished] TxID: 55e36921 
   ItemName: scale ItemID: f15dff20
   Lender: thanh  
   Borrower: jorelle 
   Duration: 5 
   ReturnDate: Fri, Oct 28 2022 (0 day(s) left) 
   MoneyTransacted: $5.00
[Unfinished] TxID: 9e27c530 
   ItemName: laptop ItemID: bd4961ed 
   Lender: thanh 
   Borrower: winston 
   Duration: 100 
   ReturnDate: Sat, Jan 21 2023 (85 day(s) left) 
   MoneyTransacted: $900.00
____________________________________________________________
```

#### 3.4.6. ```update-tx``` - Updates properties of a transaction
>Currently, supports only updating the duration of a transaction

Format: ```update-tx /t [TRANSACTION_ID] /d [NEW_DURATION]```

Note:
1. `transactionId` must exist i.e. Transaction was created and has not been removed
2. Duration must be a valid duration i.e. non-zero and non-negative
3. Duration is in days and therefore should be a whole number

Example of usage: ```update-tx /t 55e36921 /d 10```

```
____________________________________________________________
Done! Here is the updated transaction:
[Unfinished] TxID: 55e36921 
   ItemName: scale ItemID: f15dff20 
   Lender: thanh 
   Borrower: jorelle 
   Duration: 10 
   ReturnDate: Wed, Nov 02 2022 (5 day(s) left) 
   MoneyTransacted: $10.00
____________________________________________________________
```

### 3.5. Exit Program

>Exit the program

Format: ```bye```

Example of usage: ```bye```

Expected outcome:
```
____________________________________________________________
Bye! See you again
____________________________________________________________
```


## 4. Command Summary

| **Features**                       | **Format**                                                                                                 |
|------------------------------------|------------------------------------------------------------------------------------------------------------|
| __*User-related features*__        |                                                                                                            |
| Add a new user                     | add-user /n [USERNAME] /a [AGE] /c [CONTACT_NUMBER]                                                        |
| Remove a user                      | remove-user /u [USERNAME]                                                                                  |
| View a user                        | view-user /u [USERNAME]                                                                                    |
| View a user's items                | view-user-items /u [USERNAME]                                                                              |
| View a user's borrow transactions  | view-borrow-tx-by-user /u [USERNAME]                                                                       |
| View a user's lend transactions    | view-lend-tx-by-user /u [USERNAME]                                                                         |
| View a user's gain                 | view-user-gain /u [USERNAME]                                                                               |
| View a user's loss                 | view-user-loss /u [USERNAME]                                                                               |
| List all users                     | list-users                                                                                                 |
| Find users by keyword              | find-user /k [KEYWORD]                                                                                     |
| __*Item-related features*__        |                                                                                                            |
| Add a new item                     | add-item /n [ITEM_NAME] /c [CATEGORY_INDEX] /p [PRICE] /o [USERNAME]                                       |
| Remove an item                     | remove-item /i [ITEM_ID]                                                                                   |
| List all items                     | list-items                                                                                                 |
| View an item                       | view-item /i [ITEM_ID]                                                                                     |
| List categories                    | list-categories                                                                                            |
| Update an item                     | update-item /i [ITEM_ID] /p [NEW_PRICE]                                                                    |
| Sort and filter list of items      | sort-items <u>/mode [MODE_OF_SORTING] /min [MINIMUM_PRICE] /max [MAXIMUM_PRICE] /cat [CATEGORY_NUMBER]</u> |
| Find items by keyword              | find-item /k [KEYWORD]                                                                                     |
| __*Transaction-related features*__ |                                                                                                            |
| Add a new transaction              | add-tx /i [ITEM_ID] /b [BORROWER_NAME] /d [DURATION] /c [CREATED_DATE]                                     |
| List all users                     | list-tx                                                                                                    |
| Remove a transaction               | remove-tx /t [TRANSACTION_ID]                                                                              |
| View a transaction                 | view-tx /t [TRANSACTION_ID]                                                                                |
| Update a transaction               | update-tx /t [TRANSACTION_ID] /d [NEW_DURATION]                                                            |
| Find transactions by status        | find-tx /s finished OR find-tx /s unfinished                                                               |
| __*Others*__                       |                                                                                                            |
| Get help                           | help                                                                                                       |
| Exit program                       | bye                                                                                                        |

>**Note:**
> 
> 1. If phrases are <u>underlined</u>, they are optional arguments for that commands

## 5. Frequently Asked Questions

How do I transfer my data to another computer?

>On your other computer, download your ```Upcycle.jar``` file following the instructions in [Quick Start](#2-quick-start). Then, you copy the `data` folder and paste into your folder for Upcycle on your new computer. That's it, now you can run our app with your previous data on another computer.

If Duke crashes, is my data saved?

>Yes, Duke will automatically save your data to files after each operation. Therefore, data will be saved no matter how you exit the program, even with ```Ctrl-C```.

What will happen if I illegally modify data in the files?

> Duke will detect if the data is modified or not. If yes, it would give you a chance to try to fix the data in the files, but they would not able
> to use the app until Duke detect your data is fixed. If you cannot fix it, then you can choose to delete entire ```data``` folder. However, this also means that your previous data cannot be recovered.
> Please edit your data files at your own risk, otherwise, you might have to input all over again.
> 
> Note: 
> - Duke is designed not to be able to run with corrupted data file, not Duke crashes
> - You are allowed to manipulate data by editing the files, but you do it at your own risk. It can potentially cause unexpected behaviours afterwards and loss of data.

How can I manually edit data files correctly?

>**User file**
> 
> Each line represents one user with the format `[USERNAME] | [AGE] | [CONTACT]`
> 
> If you edit this files, please take note: 
> - Age range is from 10 to 100
> - If name is edited, please also edit all items of that user in the item files
> - Contact number length is 8
> - If user is removed, please also remove all items of that user in the item files
> 
> **Item file**
> 
> Each line represents one item with the format:
> 
> `[ITEMNAME] | [CATEGORY] | [PRICE] | [OWNER] | [ITEMID]`
> 
> If you edit this files, please take note: 
> 
> - Price range from 0 to 10000
> - Currently, we support 8 categories form 1 to 8
> - Item's name length is no more than 20 characters
> - Avoid change the name of owner, it can cause error (you can change both in user and item files)
> - No item ID occurs twice
> - If user is currently borrowing/lending something, do not delete him/her, or you can delete that unfinished transaction.
> 
> **Transaction file**
> 
> Each line represents one transaction with the format:
> 
> `[ITEMID] | [BORROWER] | [DURATION] | [CREATED_DATE] | [LENDER] | [ITEMNAME] | [TOTALMONEY] | [TRANSACTIONID]`
> 
> If you edit this files, please take note: 
> 
> - If the itemId occurs in item list, make sure the item's name and owner(lender) are matched with that item with given ID
> - Duration range is from 0 to 1461
> - Created date range is from 2016-01-01 to today
> - No transaction ID occurs twice
> - If that transaction is UNFINISHED, make sure that the name of lender, borrower and item ID must occur in the list.
