# Moolah Manager - User Guide

<p align="center">
    <img src="images/logo.png" width="30%">
</p>

- [1. Introduction](#1-introduction)
- [2. About This Guide](#2-about-this-guide)
  * [2.1. What is in Moolah Manager](#21-what-is-in-moolah-manager)
  * [2.2. Command Format](#22-command-format)
- [3. Getting Started](#3-getting-started)
  * [3.1. Setting Up](#31-setting-up)
  * [3.2. Viewing Help](#32-viewing-help)
- [4. Managing Transactions](#4-managing-transactions)
  * [4.1. Adding a Transaction](#41-adding-a-transaction)
  * [4.2. Editing a Transaction](#42-editing-a-transaction)
  * [4.3. Listing the Transactions](#43-listing-the-transactions)
  * [4.4. Searching for Transactions](#44-searching-for-transactions)
  * [4.5. Deleting a Transaction](#45-deleting-a-transaction)
  * [4.6. Purging all Transactions](#46-purging-all-transactions)
- [5. Budgeting and Financial Insights](#5-budgeting-and-financial-insights)
  * [5.1. Viewing the Categorical Savings](#51-viewing-the-categorical-savings)
  * [5.2. Viewing the Monthly Expenditure](#52-viewing-the-monthly-expenditure)
  * [5.3. Viewing the Financial Insights for Specific Time Periods](#53-viewing-the-financial-insights-for-specific-time-periods)
  * [5.4. Managing the Budget](#54-managing-the-budget)
- [6. Others](#6-others)
  * [6.1. Persistent Data](#61-persistent-data)
  * [6.2. Exiting the Application](#62-exiting-the-application)
- [7. Command Summary](#7-command-summary)
  * [7.1. Command Syntax](#71-command-syntax)
  * [7.2. Information for Parameters](#72-information-for-parameters)
- [8. FAQ](#8-faq)

## 1. Introduction

Financial bookkeeping via traditional mobile applications has always been a hassle due to the repetitive clicks needed to manage monetary transactions. 
With Moolah Manager, you will be encouraged to take ownership of managing your finances via a time-saving and efficient command-line (CLI) interface.

The main features of Moolah Manager include:

- Managing records of monetary transactions
- Gathering financial insights such as categorical savings and periodic expenditure
- General budgeting and reminders about spending

The application is optimised for use with a keyboard and all you need is to just type in your commands into a terminal.
Moreover, if you are a fast-typist, the recording and querying of transactions can be performed efficiently.

_Written by: Chua Han Yong Darren_

## 2. About This Guide

### 2.1. What is in Moolah Manager

With an intuitive CLI platform, you can make full use of a plethora of features provided by Moolah Manager! 
Moreover, Moolah Manager saves the monetary transactions list everytime it is updated, so that you don’t have to.
If you are a first-time user, don't forget to check out the [Getting Started](#3-getting-started) section too.

#### Managing Records of Monetary Transactions

You can add, delete, and edit monetary transaction entries to keep their records up-to-date. 
Moolah Manager also features the ability to modify dates of transactions and add categories to better classify the expenses.

Related Commands: [`add`](#41-adding-a-transaction),[`edit`](#42-editing-a-transaction), [`list`](#43-listing-the-transactions), [`find`](#44-searching-for-transactions), [`delete`](#45-deleting-a-transaction), [`purge`](#46-purging-all-transactions)

#### Gathering Financial Insights

As for statistics, Moolah Manager supports the viewing of summarised expenses in daily, weekly and monthly formats. 
This enables you to better understand your expenses, and savings across various time periods. 
In addition, Moolah Manager also shows which category of expenses users spend more on so that they can better manage spending. 

Related Commands: [`stats s/categorical_savings`](#51-viewing-the-categorical-savings), [`stats s/monthly_expenditure`](#52-viewing-the-monthly-expenditure), [`stats s/time_insights`](#53-viewing-the-financial-insights-for-specific-time-periods)

#### General Budgeting and Moolah Reminders

Beyond just tracking expenses, Moolah Manager analyzes your monthly spending habits and give you reminders on your savings.
You will also be able to allocate a monthly budget to better stick to your plans. 

Related Commands: [`budget`](#54-managing-the-budget)

_Written by: Paul Low_

### 2.2. Command Format

General illustrations about the text format used for the commands in this guide can be found below. 

1. The **first word** in the command refers to the command word to be supplied by the user. The command words are case-insensitive. 
   1. e.g. `bye`, `BYE` and `bYe` will all be interpreted as the same.

2. Words in `UPPER CASE` refer to the parameters in each command. Each parameter must be prepended with a tag.
   1. e.g. in `delete e/ENTRY`, `delete` is the command word, while `ENTRY` is a parameter expected after the tag `e/`.

3. Parameters in square brackets are optional.
   1. e.g. `help [o/detailed]` can be used as `help o/detailed` or `help`.
   2. Note that the parameter `detailed` is written in lower case here, which means, only the exact wording is accepted.

4. Each parameter is separated by a space. Users are not allowed to use spaces in their parameter.

5. Each parameter can only be used at most once. 

_Written by: Chia Thin Hong_

## 3. Getting Started

### 3.1. Setting Up

1. Ensure that you have Java 11 or above installed. If not, kindly install Java's [latest version](https://www.oracle.com/java/technologies/downloads/).
2. Download the latest version of `Moolah Manager` from [here](https://github.com/AY2223S1-CS2113-W12-2/tp/releases).
   As shown in Figure 1 below, click on the `duke.jar` file from the latest version available.

    <p align="center">
    <img src="images/MoolahManagerDownload.png" width="600">
    <br />
    <i>Figure 1: How to Download Moolah Manager</i>
    </p>

3. Copy the file to the folder that you wish to use as a home folder for Moolah Manager. The data saved during the application will reside in your `<home_folder>/data` folder.
4. Launch a command prompt or terminal and run the command `java -jar duke.jar` to start the application.
5. Moolah Manager will display a greeting message and a remaining budget for the current month. 
6. If you encounter any issues when setting up and hereon, do check out the [FAQ](#8-faq) section.

_Written by: Chua Han Yong Darren_

### 3.2. Viewing Help

Display basic or detailed help information explaining the commands available in the application. 

**Format:** `help [o/detailed] [q/COMMAND]`

| Parameter  | Description                                                                                                                                       |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| `detailed` | A detailed version of the guide.                                                                                                                  |
| `COMMAND`  | A case-insensitive command word to search for. It should either be `help`, `add`, `edit`, `list`, `find`, `delete`, `purge`, `stats` or `budget`. |

**Important Information:**

- To view detailed help, the exact `o/detailed` must be added in the command.

**Examples:**

- `help`
- `help o/detailed`
- `help o/detailed q/bye`

**Expected Output:**

Viewing Basic Help

```
help
____________________________________________________________
Gotcha! Here are the commands that you may use:
Command Word: HELP
Display basic or detailed help information explaining the commands available in the application.
Usage: help [o/detailed] [q/COMMAND]

Command Word: BUDGET
Set the amount for monthly budget, with a value from 1 to 10^13 (Ten Trillion).
Usage: budget b/BUDGET

Command Word: ADD
Add a new transaction entry, which could be either an "expense" or "income" into the transactions list.
Usage: add t/TYPE c/CATEGORY a/AMOUNT d/DATE i/DESCRIPTION
... (Similar output for other commands truncated)
____________________________________________________________
```

Viewing Detailed Help for Delete Command 

```
help o/detailed q/delete
____________________________________________________________
Gotcha! Here are the commands that you may use:
Command Word: DELETE
Delete a transaction entry from the unfiltered list of transactions.
Usage: delete e/ENTRY
Parameters information:
- ENTRY: A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000.

____________________________________________________________
```

_Written by: Chia Thin Hong_

## 4 Managing Transactions

### 4.1. Adding a Transaction

Add a new transaction entry, which could be either an expense or income into the transactions list.

Format: `add t/TYPE c/CATEGORY a/AMOUNT d/DATE i/DESCRIPTION`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `TYPE`        | The type of transaction. It should either be `expense` or `income`.                                    |
| `CATEGORY`    | A category for the transaction. It can be any word without numeral, symbol or spacing.                 |
| `AMOUNT`      | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million). | 
| `DATE`        | The date when the transaction took place on. It must be in ddMMyyyy format, e.g. 29102022.             |                                                                                                 
| `DESCRIPTION` | More information regarding the transaction. It can be any word without any spacing.                    | 

**Important Information:**

- All tags must be present in this command.
- All parameters must not be empty.

**Examples:**

- `add t/expense c/transport a/1 d/02102022 i/bus_fare` <br> 
- `add t/income c/bonus a/10000000 d/03102022 i/thank_you_boss`

**Expected Output:**

Adding an Expense Transaction

```
add t/expense c/transport a/1 d/02102022 i/bus_fare
____________________________________________________________
I have added the following Expense transaction:
[-][transport] $1 at Oct 02 2022 | Description: bus_fare
Remaining budget for Oct 2022: $999. Keep it up!
____________________________________________________________
```

Adding an Income Transaction

```
add t/income c/bonus a/10000000 d/03102022 i/thank_you_boss
____________________________________________________________
I have added the following Income transaction:
[+][bonus] $10000000 at Oct 03 2022 | Description: thank_you_boss
Remaining budget for Oct 2022: $1000. Keep it up!
____________________________________________________________
```

_Written by: Yong Chin Han_

### 4.2. Editing a Transaction

Edit a transaction entry from the list of transactions.

**Format:** `edit e/ENTRY [t/TYPE] [c/CATEGORY] [a/AMOUNT] [d/DATE] [i/DESCRIPTION]`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `ENTRY`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000.       |
| `TYPE`        | The type of transaction. It should either be `expense` or `income`.                                    |
| `CATEGORY`    | A category for the transaction. It can be any word without numeral, symbol or spacing.                 |
| `AMOUNT`      | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million). | 
| `DATE`        | The date when the transaction took place on. It must be in ddMMyyyy format, e.g. 29102022.             |                                                                                                 
| `DESCRIPTION` | More information regarding the transaction. It can be any word without any spacing.                    |

**Important Information:**

- There must be at least one optional tag present in this command.

**Examples:**

- `edit e/1 t/expense c/food`
- `edit e/2 a/10 d/10032022 i/games`

**Expected Output:**

Editing a Transaction 

```
edit e/1 t/expense c/food
____________________________________________________________
I have edited the following Expense transaction:
[-][food] $20 at Jan 30 2022 | Description: banana_pudding
Remaining budget for Jan 2022: $980. Keep it up!
____________________________________________________________
```

_Written by: Brian Wong Yun Long_

### 4.3. Listing the Transactions

List all or some transactions based on selection by the ascending order of transaction date. 
If tag filters are used, the transactions retrieved from the records must match all the filter tags that have been specified in order to be recognized as a valid record.

**Formats:** 
```
list [t/TYPE] [c/CATEGORY] [d/DATE]
list [t/TYPE] [c/CATEGORY] [d/DATE] [m/MONTH] y/YEAR
list [t/TYPE] [c/CATEGORY] [d/DATE] p/PERIOD n/NUMBER
```

| Parameter  | Description                                                                                                                                                                                      |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `TYPE`     | The type of transaction. It should either be `expense` or `income`.                                                                                                                              |
| `CATEGORY` | A category for the transaction. It can be any word without numeral, symbol or spacing.                                                                                                           |
| `DATE`     | The date when the transaction took place on. It must be in ddMMyyyy format, e.g. 29102022.                                                                                                       |
| `MONTH`    | The month which the transaction falls on. It is a positive whole number ranging from 1 to 12, where 1 represents January. <br><!> MONTH parameter must be used together with the YEAR parameter. |
| `YEAR`     | The year which the transaction falls on. It is a positive whole number ranging from 0 to 9999, where 0 represents Year 0.                                                                        |
| `PERIOD`   | The period which the transaction falls on. It should either be `days`, `weeks` or `months`. <br> <!> PERIOD parameter must be used together with the NUMBER parameter.                           |
| `NUMBER`   | The last N number of days, weeks or months. It is a positive whole number ranging from 1 to 100. <br> <!> NUMBER parameter must be used together with the PERIOD parameter.                      |                                                                     

**Important Information:**

- The `m/MONTH` and `y/YEAR` tags should not be used together with `p/PERIOD` and `n/NUMBER` tags.

**Examples:**

- `list`
- `list y/2022`
- `list y/2022 m/11`
- `list n/4 p/weeks`
- `list d/20102022 n/1 p/months`
- `list t/income c/transport d/27102022`
- `list t/expense c/leisure n/7 p/days`

**Expected Output:**

Listing All Transactions in November 2022 

```
list y/2022 m/11
____________________________________________________________
Here are your transaction records:
Indexes are not shown as your transactions have been consolidated for specific time periods.
If you need to know an entry's index, use the Find command to search by description.
[-][food] $80 on Nov 13 2022 | Description: toilet_cake 
[+][education] $20 on Nov 30 2022 | Description: sold_my_pe_bugs
____________________________________________________________
```

Listing All Transactions in the Last 4 Weeks

> If today's date is 4 November 2022, the range will be 10 October to 30 October 2022.

```
list n/4 p/weeks
____________________________________________________________
Here are your transaction records:
Indexes are not shown as your transactions have been consolidated for specific time periods.
If you need to know an entry's index, use the Find command to search by description.
[-][beauty] $40 on Oct 11 2022 | Description: bought_a_facial_wash
[+][salary] $2000 on Oct 20 2022 | Description: worked_at_the_cinema
[-][food] $120 on Oct 25 2022 | Description: enjoyed_my_steamboat
____________________________________________________________
```

Listing All Transactions in the Last 1 Month with a Date as 20 October 2022

> If today's date is 4 November 2022, the range will be 1 October to 31 October 2022. However, only records on 20 October 2022 will be shown as each entry must match all the filter tags specified.

```
list d/20102022 n/1 p/months
____________________________________________________________
Here are your transaction records:
Indexes are not shown as your transactions have been consolidated for specific time periods.
If you need to know an entry's index, use the Find command to search by description.
[+][salary] $2000 on Oct 20 2022 | Description: worked_at_the_cinema
____________________________________________________________
```

Listing All Transactions in the Last 7 Days (or Past 1 Week) for Expenses on Leisure

> If today's date is 4 November 2022, the range will be 28 October to 3 November 2022.

```
list t/expense c/leisure n/7 p/days
____________________________________________________________
Here are your transaction records:
Indexes are not shown as your transactions have been consolidated for specific time periods.
If you need to know an entry's index, use the Find command to search by description.
[-][leisure] $40 on Nov 2 2022 | Description: went_for_salon_treatment
[-][leisure] $120 on Nov 3 2022 | Description: spa_at_sentosa
____________________________________________________________
```

_Written by: Chua Han Yong Darren_

### 4.4. Searching for Transactions

Find a specific or multiple transactions based on any keyword that have been specified.

**Format:** `find k/KEYWORD`

| Parameter | Description                                                                                                                          |
|-----------|--------------------------------------------------------------------------------------------------------------------------------------|
| `KEYWORD` | A case-insensitive word that matches the partial or full description of a transaction entry. It can be any word without any spacing. |

**Example:**

- `find k/worked_at`

**Expected Output:**

```
find k/worked_at
____________________________________________________________
Here are the transaction records that match your search expression:
1: [+][salary] $2000 on Oct 20 2022 | Description: worked_at_the_cinema
____________________________________________________________
```

_Written by: Chua Han Yong Darren_

### 4.5. Deleting a Transaction

Delete a transaction entry from the list of transactions.

**Format:** `delete e/ENTRY`

| Parameter | Description                                                                                      |
|-----------|--------------------------------------------------------------------------------------------------|
| `ENTRY`   | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000. |

**Examples:**

- `delete e/1`
- `delete e/2`

**Expected Output:**

```
delete e/1
____________________________________________________________
I have deleted the following transaction:
[+][food] $20 at Jan 30 2022 | Description: banana_pudding
Remaining budget for Jan 2022: $1000. Keep it up!
____________________________________________________________
```

_Written by: Brian Wong Yun Long_

### 4.6. Purging all Transactions

Delete all transaction entries from the list of transactions. User must enter 'Y' to confirm the purge.

**Format:** `purge`

**Expected Output:**

```
purge
____________________________________________________________
Are you sure you want to proceed with this command? Please enter 'Y' to confirm.
____________________________________________________________
____________________________________________________________
Y
____________________________________________________________
____________________________________________________________
All your transactions have been purged.
____________________________________________________________

```

_Written by: Brian Wong Yun Long_

## 5. Budgeting and Financial Insights

### 5.1. Viewing the Categorical Savings 

View the total savings of all transactions in each category.

**Format:** `stats s/categorical_savings`

**Expected Output:**

```
stats s/categorical_savings
____________________________________________________________
Here are your net categorical savings:
[bonus] $540
[transport] -$10
[salary] $2000
[food] -$80

____________________________________________________________
```

### 5.2. Viewing the Monthly Expenditure

View the total income, expense and savings values of all transactions in each month.

**Format:** `stats s/monthly_expenditure`

**Expected Output:**

```
stats s/monthly_expenditure
____________________________________________________________
Here is a summary of your monthly expenditure:
[Sep 2022]
Income: $2000
Expense: $20
Savings: $1980
Budget: $10000
Spending Habit: Wow, keep up the good work. You saved at least two-third of your income.
In terms of monthly budget, you have kept yourself well within the budget!

[Oct 2022]
Income: $0
Expense: $10001
Savings: -$10001
Budget: $10000
Spending Habit: You spent way more than what you have earned for the current month. Please spend wisely based on your income.
In terms of monthly budget, you have spent more than your budget planned!

____________________________________________________________
```

### 5.3. Viewing the Financial Insights for Specific Time Periods

View monthly expenditure, total income, expense and savings values of all transactions in the specified time period.

**Formats:** 
```
stats s/time_insights y/YEAR [m/MONTH]
stats s/time_insights p/PERIOD n/NUMBER
```

| Field      | Description                                                                                                                                                                                      |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `MONTH`    | The month which the transaction falls on. It is a positive whole number ranging from 1 to 12, where 1 represents January. <br><!> MONTH parameter must be used together with the YEAR parameter. |
| `YEAR`     | The year which the transaction falls on. It is a positive whole number ranging from 0 to 9999, where 0 represents Year 0.                                                                        |
| `PERIOD`   | The period which the transaction falls on. It should either be `days`, `weeks` or `months`. <br> <!> PERIOD parameter must be used together with the NUMBER parameter.                           |
| `NUMBER`   | The last N number of days, weeks or months. It is a positive whole number that is from 1 to 100. <br> <!> NUMBER parameter must be used together with the PERIOD parameter.                      |                                                                     

**Important Information:**

- The `m/MONTH` and `y/YEAR` tags should not be used together with `p/PERIOD` and `n/NUMBER` tags.

**Examples:**

- `stats s/time_insights y/2022`
- `stats s/time_insights y/2022 m/11`
- `stats s/time_insights p/weeks n/3`
- `stats s/time_insights p/months n/12`

**Expected Output:**

Viewing the Financial Insights for November 2022

```
stats s/time_insights y/2022
____________________________________________________________
Here are the categorical savings and expenditure summary for
Year: 2022, Month: 11

-----Categorical Savings-----
[salary] $3040
[food] -$100

-----Expenditure Summary-----
Income: $3040
Expense: $100
Savings: $2940
Budget: $1000
Spending Habit: Wow, keep up the good work. You saved at least two-third of your income.
In terms of monthly budget, you have kept yourself well within the budget!
____________________________________________________________

```

Viewing the Financial Insights for the Last 3 Weeks

> If today's date is 4 November 2022, the range will be 10 October to 30 October 2022.

```
stats s/time_insights p/weeks n/3
____________________________________________________________
Here are the categorical savings and expenditure summary for
The last 3 weeks:

-----Categorical Savings-----
[bonus] $540

-----Expenditure Summary-----
Income: $540
Expense: $0
Savings: $540
____________________________________________________________
```

_Written by: Paul Low_

### 5.4. Managing the Budget

Set the amount for monthly budget, with a value from 1 to 10<sup>13</sup> (Ten Trillion).

Format: `budget b/BUDGET`

| Field    | Description                                                                                                   |
|----------|---------------------------------------------------------------------------------------------------------------|
| `BUDGET` | An estimate of expense for every month. It is a positive whole number ranging from 1 to 10^13 (Ten Trillion). |

**Important Information:**

- The monthly budget is set to $1000 by default if the user has never run this command.

**Examples:**

- `budget b/1000`
- `budget b/52013149999`

**Expected Output:** 

```
budget b/52013149999
____________________________________________________________
You have successfully updated the budget.
Monthly budget set as: $52013149999
Remaining budget for current month: $52013140000
REMINDER: Continue to stay within your budget for this month! Good fortune!
____________________________________________________________
```

_Written by: Chia Thin Hong_

## 6. Others

### 6.1. Persistent Data

Our program comes with a storage feature to ensure user data gets saved each time Moolah Manager is stopped and restarted.
If you are accessing Moolah Manager via our JAR file release, the storage file, **duke.txt** would be newly created in a newly created **data** folder in your current directory.

In duke.txt, the monthly budget value would be stored on the 1st line, with the different transaction entries on subsequent lines.
When Moolah Manager is started, the program will attempt to store the budget and transaction values locally into the program.

If the duke.txt  data is in **incorrectly formatted** when Moolah Manager is started, none of the data would be stored into the program. 
An error message would be shown to inform users of the corrupted data and recommend editing the entries correctly. 
Suppose users want to continue with the program in the current state without updating the corrupted duke.txt data, 
they can enter any command which would add or alter the budget / transaction values which promptly **overwrites** the duke.txt data.

We **DO NOT** recommend editing duke.txt unless you are familiar with the storage syntax of the program to **prevent lose of data**.  

_Written by: Yong Chin Han_

### 6.2. Exiting the Application

Exit the application.

**Format:** `bye`

**Expected Output:**

```
bye
____________________________________________________________
Goodbye and see you soon.
____________________________________________________________
```

_Written by: Brian Wong Yun Long_

## 7. Command Summary

### 7.1. Command Syntax

**OPTIONAL** tags are enclosed with SQUARE BRACKETS "[" and "]" in the **command syntax in the table** below.

| Action                                                              | Command Syntax                                                           | Examples                                                                                                                 |
|---------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Viewing Help                                                        | `help`                                                                   | `help`                                                                                                                   |
| Viewing Help (Detailed)                                             | `help o/detailed [q/QUERY]`                                              | `help o/detailed` <br/> `help o/detailed q/budget`                                                                       |
| Adding a Transaction                                                | `add t/TYPE c/CATEGORY a/AMOUNT d/DATE i/DESCRIPTION`                    | `add t/expense c/transport a/1 d/02102022 i/bus_fare` <br> `add t/income c/bonus a/10000000 d/03102022 i/thank_you_boss` |
| Editing a Transaction                                               | `edit e/ENTRY [t/TYPE] [c/CATEGORY] [a/AMOUNT] [d/DATE] [i/DESCRIPTION]` | `edit e/1 t/expense c/food <br/> edit e/2 a/10 d/10202022 i/games`                                                       |
| Listing the Transactions                                            | `list  [t/TYPE] [c/CATEGORY] [d/DATE]`                                   | `list` <br/> `list c/food d/13092022`                                                                                    |
| Listing the Transactions for a Specific Year or Month               | `list [t/TYPE] [c/CATEGORY] [d/DATE] y/YEAR [m/MONTH]`                   | `list t/income d/30092022 y/2022` <br/> `list t/income d/30092022 y/2022 m/9`                                            |
| Listing the Transactions for the Last N Days, Weeks or Months       | `list [t/TYPE] [c/CATEGORY] [d/DATE] p/PERIOD n/NUMBER`                  | `list p/days n/7` <br/> `list p/weeks n/4` <br/> `list p/months n/1`                                                     |
| Searching for Transactions                                          | `find k/KEYWORD`                                                         | `find k/bus_fare` <br/> `find k/transport`                                                                               |
| Viewing the Categorical Savings                                     | `stats s/categorical_savings`                                            | `stats s/categorical_savings`                                                                                            |
| Viewing the Monthly Expenditure                                     | `stats s/monthly_expenditure`                                            | `stats s/monthly_expenditure`                                                                                            |
| Viewing the Financial Insights for a Specific Year or Month         | `stats s/time_insights y/YEAR [m/MONTH]`                                 | `stats s/time_insights y/2022` <br/> `stats s/time_insights y/2002 m/10`                                                 |
| Viewing the Financial Insights for the Last N Days, Weeks or Months | `stats s/time_insights p/PERIOD n/NUMBER`                                | `stats s/time_insights p/weeks n/3` <br/> `stats s/time_insights p/months n/12`                                          |
| Managing the Budget                                                 | `budget b/BUDGET`                                                        | `budget b/9999999999999` <br/> `budget b/1`                                                                              |
| Deleting a Transaction                                              | `delete e/ENTRY`                                                         | `delete e/3`                                                                                                             |
| Purging all Transactions                                            | `purge`                                                                  | `purge`                                                                                                                  |
| Exiting the Application                                             | `bye`                                                                    | `bye`                                                                                                                    |

### 7.2. Information for Parameters 

| Parameter     | Description                                                                                  | Restrictions                                                                                                                                     |
|---------------|----------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| `COMMAND`     | A case-insensitive command word to search for.                                               | `help`, `add`, `edit`, `list`, `find`, `delete`, `purge`, `stats` or `budget`.                                                                   |
| `TYPE`        | The type of transaction.                                                                     | `expense` or `income`.                                                                                                                           |
| `CATEGORY`    | A category for the transaction.                                                              | Any word without numeral, symbol or spacing.                                                                                                     |
| `AMOUNT`      | The amount of the transaction.                                                               | A positive whole number ranging from 1 to 10000000 (Ten Million).                                                                                |
| `DATE`        | The date when the transaction took place on.                                                 | ddMMyyyy format, e.g. 29102022.                                                                                                                  |
| `DESCRIPTION` | More information regarding the transaction.                                                  | Any word without any spacing.                                                                                                                    |
| `ENTRY`       | A list entry value for the transaction.                                                      | A positive whole number ranging from 1 to 1000000.                                                                                               |
| `MONTH`       | The month which the transaction falls on.                                                    | A positive whole number ranging from 1 to 12, where 1 represents January. <br><!> MONTH parameter must be used together with the YEAR parameter. |
| `YEAR`        | The year which the transaction falls on.                                                     | A positive whole number ranging from 0 to 9999, where 0 represents Year 0.                                                                       |
| `PERIOD`      | The period which the transaction falls on.                                                   | `days`, `weeks` or `months`. <br> <!> PERIOD parameter must be used together with the NUMBER parameter.                                          |
| `NUMBER`      | The last N number of days, weeks or months.                                                  | A positive whole number ranging from 1 to 100. <br> <!> NUMBER parameter must be used together with the PERIOD parameter.                        |         
| `KEYWORD`     | A case-insensitive word that matches the partial or full description of a transaction entry. | Any word without any spacing.                                                                                                                    |
| `BUDGET`      | An estimate of expense for every month.                                                      | A positive whole number ranging from 1 to 10^13 (Ten Trillion).                                                                                  |

_Written by: Yong Chin Han_

## 8. FAQ

**Q**: Why does running the command `java -jar duke.jar` in the command prompt or terminal not open up the application?
* **A**: It could be that you are not in the home directory of that application. Type `cd [PATH TO DIRECTORY CONTAINING .JAR FILE]`.

**Q**: How do I view commands while the app is running? 
* **A**: Type `help` command to view all the commands!

**Q**: Can I directly input values into the duke.txt file? 
* **A**: It is strongly advisable not to edit the duke.txt file as that could disrupt Moolah Manager's retrieval of the data.

**Q**: What should I do if most of my transactions are incorrect?
* **A**: You can consider using the edit or delete function to modify individual transactions at a time. However, if
 there are too many transactions to change, you can consider using the purge function to delete all values. 
 
**Q**: What is the purpose of using the different statistics commands?
* **A**: They provide you with great flexibility in viewing the current transactions from different perspectives. To analyze 
all the stored transactions, use `stats s/categorical_savings` or `stats s/monthly_expenditure`. Otherwise, use `stats s/time_insights`
to analyze transactions for a specified time period.

_Written by: Paul Low_
