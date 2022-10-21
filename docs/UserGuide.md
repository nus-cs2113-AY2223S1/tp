# Upcycle - User Guide

## Table of contents

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)

    3.1 [Getting help](#31-getting-help)

    3.2 [User-related features](#32-user-related-features)

    3.3 [Item-related features](#33-item-related-features)

    3.4 [Transaction-related features](#34-transaction-related-features)

    3.5 [Exit program](#35-exit-program)
4. [Command Summary](#4-command-summary)
5. [Frequently Asked Questions](#5-frequently-asked-questions)

## 1. Introduction

...To be updated

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
____________________________________________________________
```
6. Type the valid command and press Enter to run the command. See [Feature](#3-features) or [Command Summary](#4-command-summary) for more information of commands.

><span style="color: #FF1A00;">**IMPORTANT**</span>: **DO NOT** edit any files in ```/data/``` folders. This action potentially cause massive bugs when execute the program, and you may have to edit again or in the worst case, delete all storage file and all of your data will be gone forever
## 3. Features 

This section allows users to understand all the features we offered, including the format, the constraint. We also demonstrate some examples of usage and the expected outcome.

### 3.1. Getting help

...To be updated

### 3.2. User-related features

#### 3.2.1. ```add-user``` - Add a new user
>Adds a new user to the list

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
<br>

#### 3.2.2. ```remove-user``` - Remove a user
>Removes a user in the list

Format: ```remove-user /u [USERNAME]```

Note:
1. The user to be deleted should not borrow or lend any items at the moment
2. All the items of this user will also be deleted

Example of usage: ```remove-user /u [USERNAME]```

Expected outcome:
```
____________________________________________________________
Noted. Following user has been deleted: 
Username: thanh Age: 20 Contact: 73648263 
Total user(s) in database: 6
____________________________________________________________

```

### 3.3. Item-related features
#### 3.3.1. ```add-item``` - Add a new item
>Adds a new item to the list

Format: ```add-item /n [ITEM_NAME] /c [CATEGORY_INDEX] /p [PRICE] /o [USERNAME]```

Note:
1. Owner cannot have duplicate items (items have the same name)
2. To choose category, please use ```list-categories``` to list them out and use the index
3. Price must be a float

Example of usage: ```add-item /n weight /c 1 /p 0.5 /o bui```

Expected outcome:
```
____________________________________________________________
Noted. Following item has been added: 
Status: [Available] ItemId: 2cc4edf3 Item: weight Category: SPORTS_EQUIPMENT Owner: bui PricePerDay: $0.5
Total item(s) in database: 2
____________________________________________________________
```
<br>

#### 3.3.2. ```remove-item``` - Remove an item
>Removes an item in the list

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

<br>

### 3.4. Transaction-related features
#### 3.4.1. ```add-tx``` - Add a new transaction
>Adds a new transaction to the list

Format: ```add-tx /i [ITEM_ID] /b [BORROWER_NAME] /d [DURATION] /c [CREATED_DATE]```

Note:
1. Duration must be a integer, greater than 0
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
<br>

#### 3.4.2. ```remove-tx``` - Remove a transaction
>Removes a transaction in the list

Format: ```remove-tx /t [TRANSACTION_ID]```

Note:
1. You can use ```list-tx``` to get the transaction ID you want to delete

Example of usage: ```remove-tx /t [TRANSACTION_ID]```

Expected outcome:
```
____________________________________________________________
OK! I will remove the following item:
Status: [On loan] TransactionID: 80902c3b ItemName: weight ItemID: 2cc4edf3 BorrowerID: thanh ReturnDate: Tue, Oct 25 2022 (4 day(s) remaining)
Total transactions(s) in database: 2
____________________________________________________________

```

<br>

### 3.5. Exit Program

...To be updated


## 4. Command Summary

| **Features**                       | **Format**                                                             |
|------------------------------------|------------------------------------------------------------------------|
| __*User-related features*__        |                                                                        |
| Add a new user                     | add-user /n [USERNAME] /a [AGE] /c [CONTACT_NUMBER]                    |
| Remove a user                      | remove-user /u [USERNAME]                                              |
| __*Item-related features*__        |                                                                        |
| Add a new item                     | add-item /n [ITEM_NAME] /c [CATEGORY_INDEX] /p [PRICE] /o [USERNAME]   |
| Remove an item                     | remove-item /i [ITEM_ID]                                               |
| __*Transaction-related features*__ |                                                                        |
| Add a new transaction              | add-tx /i [ITEM_ID] /b [BORROWER_NAME] /d [DURATION] /c [CREATED_DATE] |
| Remove a transaction               | remove-tx /t [TRANSACTION_ID]                                          |
| __*Others*__                       |                                                                        |
| Get help                           | help                                                                   |
| Exit program                       | bye                                                                    |

## 5. Frequently Asked Questions

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}