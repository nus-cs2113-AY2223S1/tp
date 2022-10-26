# User Guide

- [Introduction](#introduction)
- [About This Guide](#about-this-guide)
    * [What is in Moolah Manager](#what-is-in-moolah-manager)
    * [Command Format](#command-format)
- [Getting Started](#getting-started)
    * [Setting Up](#setting-up)
    * [Viewing Help: `help`](#viewing-help---help-)
- [Managing Transactions](#managing-transactions)
    * [Adding a Transaction: `add`](#adding-a-transaction---add-)
    * [Editing a Transaction: `edit`](#editing-a-transaction---edit-)
    * [Listing the Transactions: `list`](#listing-the-transactions---list-)
    * [Searching for Transactions: `find`](#searching-for-transactions---find-)
    * [Deleting a Transaction: `delete`](#deleting-a-transaction---delete-)
    * [Purging all Transactions: `purge`](#purging-all-transactions---purge-)
- [Budgeting and Financial Insights](#budgeting-and-financial-insights)
    * [Viewing the Statistics: `stats`](#viewing-the-statistics---stats-)
    * [Managing the Budget: `budget`](#managing-the-budget---budget-)
- [General](#general)
    * [Persistent Data](#persistent-data)
    * [Exiting the Program: `exit`](#exiting-the-program---exit-)
- [Command Summary](#command-summary)
- [FAQ](#faq)

## Introduction

_Written by: Chua Han Yong Darren_

## About This Guide 

### What is in Moolah Manager

_Written by: Paul Low_

### Command Format 

_Written by: Chia Thin Hong_

## Getting Started

### Setting Up

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

_Written by: Chua Han Yong Darren_

### Viewing Help: `help`

_Written by: Chia Thin Hong_

## Managing Transactions

### Adding a Transaction: `add`

Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

_Written by: Yong Chin Han_

### Editing a Transaction: `edit`

_Written by: Brian Wong Yun Long_

### Listing the Transactions: `list`

_Written by: Chua Han Yong Darren_

### Searching for Transactions: `find`

_Written by: Chua Han Yong Darren_

### Deleting a Transaction: `delete`

_Written by: Brian Wong Yun Long_

### Purging all Transactions: `purge`

_Written by: Brian Wong Yun Long_

## Budgeting and Financial Insights

### Viewing the Statistics: `stats`

_Written by: Paul Low_

### Managing the Budget: `budget`

_Written by: Chia Thin Hong_

## Others

### Persistent Data

_Written by: Yong Chin Han_

### Exiting the Program: `exit`

_Written by: Brian Wong Yun Long_

## Command Summary


| Command                                                                  | Command Syntax                                                                                   | Example                                                                                                              |
|--------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| help                                                                     | help                                                                                             | help                                                                                                                 |
| help (detailed)                                                          | help o/detailed                                                                                  | help o/detailed                                                                                                      |
| add                                                                      | add t/TRANSACTION_TYPE c/CATEGORY_TYPE a/TRANSACTION_AMOUNT d/TRANSACTION_DATE i/ADDITIONAL_INFO | add t/expense c/transport a/1 d/02102022 i/bus_fare <br> add t/income c/bonus a/10000000 d/03102022 i/thank_you_boss |
| list                                                                     | list                                                                                             | list                                                                                                                 |
| list ( with filters )                                                    | list c/OPTIONAL_CATEGORY d/OPTIONAL_DATE t/OPTIONAL_TRANSACTION_TYPE                             | list c/food d/13092022 <br> list t/income d/30092022                                                                 |
| statistics for categorical savings                                       | stats s/categorical_savings                                                                      | stats s/categorical_savings                                                                                          |
| statistics for monthly expenditure                                       | stats s/monthly_expenditure                                                                      | stats s/monthly_expenditure                                                                                          |
| statistics for time insight of a specific year OR month of specific year | stats s/time_insights y/YEAR_NUMBER m/OPTIONAL_MONTH                                             | stats s/time_insights y/2022 <br/> stats s/time_insights y/2002 m/10                                                 |
| statistics for time insight for the PAST periods from current date       | stats s/time_insights p/PERIODS n/NUMBER_OF_PERIODS                                              | stats s/time_insights p/weeks n/3 <br/> stats s/time_insights p/months n/4                                           |
| budget                                                                   | budget b/MONTHLY_BUDGET                                                                          | budget b/9999999999999 <br/> budget b/1                                                                              |
| delete                                                                   | delete e/TASK_NUMBER                                                                             | delete e/3                                                                                                           |
| purge                                                                    | purge                                                                                            | purge                                                                                                                |
| find                                                                     | find KEYWORD(s)                                                                                  | find bus_fare <br> find transport <br> find Sep 13                                                                   |
| bye                                                                      | bye                                                                                              | bye                                                                                                                  |

Mandatory Tags

* The `TRANSACTION_TYPE` is either `"expense"` or `"income"`. 
* The `CATEGORY_TYPE` is a one-word parameter flexibly defined by the user. [ No numerals, symbols or spacings are allowed ]
* The `TRANSACTION_AMOUNT` is a positive numeral that is above 0 and below 100000001. [ No alphabets, symbols or spacings allowed ]
* The `TRANSACTION_DATE` MUST be in ddMMyyyy format.
* The `ADDITIONAL_INFO` is a single limitless parameter defined by the user. [ Spacings are not allowed ]
* The `TASK_NUMBER` is the entry value which is a positive numeral that is above 0 and below 100000001. [ No alphabets, symbols or spacings allowed ]
* The `KEYWORD` are parameter values within Search-fields that would be searched.  [ Available Search-fields: date, transaction type, category , amount, information. Cross-search across different Search-fields NOT supported ]
* The `MONTHLY_BUDGET` is a positive numeral that is above 0 and below a Trillion (1000000000000) . [ No alphabets, symbols or spacings allowed ]
* The `YEAR_NUMBER` is the year in yyyy format.
* The `PERIODS` is the selection of either periods in `"weeks"` or `"months"`. 
* The `NUMBER_OF_PERIODS` is the number of periods to view.  


Optional tags
* The `OPTIONAL_MONTH` is the month value in numerical form where January is represented by '1'. [ from 1 - 12 ]
* The `OPTIONAL_CATEGORY` is the category label for the transactions under the same transaction category
* The `OPTIONAL_DATE` is the date in ddMMyyyy format.
* The `OPTIONAL_TRANSACTION_TYPE` is either `"expense"` or `"income"`.

_Written by: Yong Chin Han_

## FAQ 

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

_Written by: Paul Low_