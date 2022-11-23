---
title: User Guide
layout: default
nav_order: 2
description: "A guide specially made for you so that your money's going into the right places!"
has_toc: false
---
# User Guide

<br>
<p align="center"><img alt="icon" src="https://raw.githubusercontent.com/AY2223S1-CS2113T-W11-1/tp/master/docs/images/icon.png"></p>
<br>

<h2 align="center">The financial tracker built for the computing professional.</h2>

<br>
<br>
<br>

<div style="page-break-after: always;"></div>

## Introduction

<br>

MoneyGoWhere is a financial tracking application to help you manage your finances.

Based on a Command Line Interface (CLI), you can add your `expenses`, `income` sources, spending `targets` and `recurring payments` to a trackable list that can be saved and accessed **anywhere, anytime**.

Although this application is mainly targeted at computing professionals who are familiar with CLI, **you don't have to worry if you're not an expert** -- with the help of this User Guide, MoneyGoWhere can be **easy to pick up** and help you start organising your finances in a matter of **moments**.

Head down to [How to use the User Guide](#how-to-use-the-user-guide) to get MoneyGoWhere up and running!

<br>
<br>

<div style="page-break-after: always;"></div>

## Contents
* [Introduction](#introduction)
* [How to use the User Guide](#how-to-use-the-user-guide)
    * [Understanding the symbols used in the user guide](#understanding-the-symbols-used-in-the-user-guide)
    * [Understanding the command syntax](#understanding-the-command-syntax)
* [Quick start](#quick-start)
    * [Basic commands](#basic-commands)
* [Features](#features)
    * [Displaying the help menu](#displaying-the-help-menu-help)
    * [Managing your expenses](#managing-your-expenses)
    * [Managing your recurring payments](#managing-your-recurring-payments)
    * [Managing your incomes](#managing-your-incomes)
    * [Managing your targets](#managing-your-targets)
    * [Managing your data](#managing-your-data)
    * [Exiting the application](#exiting-the-application-bye)
* [FAQ](#faq)
* [Command summary](#command-summary)

<br>
<br>

<div style="page-break-after: always;"></div>

## How to use the User Guide

<br>

When using the user guide, you should first refer to [Contents](#contents) to quickly navigate between sections.

For certain sections *(e.g. [Managing your expenses](#managing-your-expenses))* in [Contents](#contents), there will be sub-contents that will let you navigate between subsections *(e.g. [Adding an expense](#adding-an-expense-add-expense))* within that section.

At the bottom of every section and subsection, there will be hyperlinks that will take you to back to [Contents](#contents) and the sub-contents of that section respectively.

Each of the sub-contents will also have a hyperlink to take you back to [Contents](#contents) to make navigation easier.

<br>

**Otherwise, the [Quick Start](#quick-start) can help you get into MoneyGoWhere in a snap.**

<br>

### Sub-Contents
* [Understanding the symbols used in the User Guide](#understanding-the-symbols-used-in-the-user-guide)
* [Understanding the Command Syntax](#understanding-the-command-syntax)
    * [Basic Syntax](#basic-syntax)
    * [Additional Syntax](#additional-syntax)
        * [Adding spaces](#adding-spaces)
        * [Adding double quotes](#adding-double-quotes)
        * [Limitation on adding double quotes](#limitation-on-adding-double-quotes)
        * [Limitation on adding hyphens](#limitation-on-adding-hyphens)
* [Back to Contents](#contents)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Understanding the symbols used in the User Guide

<br>

{: .invalid }
> Cross symbols (❌) indicate **invalid** inputs into MoneyGoWhere.

{: .valid }
> Tick symbols (✔️) indicate **valid** inputs into MoneyGoWhere.

{: .tips }
> Information symbols (ℹ️) indicate **tips** or **notes** that contain **extra** information to clarify the usage of functions.

{: .notes }
> Light-bulb symbols (💡) indicate **important information** that you should know when using the function.

{: .question }
> Question symbols (❓) indicate common **enquiries** that users ask about MoneyGoWhere.

{: .answer }
> Answer symbols (🔎) indicate **solutions** to user enquiries stated beforehand.

<br>

[Back to Sub-Contents](#how-to-use-the-user-guide)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Understanding the Command Syntax

<br>

#### Basic Syntax

<br>

When inputting arguments:
* The absence of square brackets denotes **mandatory** arguments.
* The square brackets denote **optional** arguments.
* The presence of invalid or extra arguments will be ignored by MoneyGoWhere.

<br>

Example: `Add-Expense -n NAME -a AMOUNT [-d DATE-TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE-OF-PAYMENT]`

{: .invalid }
> `Add-Expense -n Lunch`
>
> `Add-Expense -a 13.37`

{: .valid }
> `Add-Expense -n Lunch -a 13.37`
>
> `Add-Expense -n Lunch -a 13.37 -x SGD -p Card`

<br>

[Back to Sub-Contents](#how-to-use-the-user-guide)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Additional Syntax

<br>

##### Adding spaces
Argument values with spaces should be enclosed with double quotes.

<br>

Example:
```
Add-Expense -n "Lunch at Marina Bay Sands" -a 1333.37

Name            : Lunch at Marina Bay Sands
Date and Time   : 07 Nov 2022 12:15
Amount          : 1333.37
Currency        : SGD

The expense was added successfully.
```

<br>

[Back to Sub-Contents](#how-to-use-the-user-guide)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

##### Adding double quotes
Argument values with double quotes should be escaped by prepending an additional double quote.

<br>

Example:
```
Add-Expense -n "Lunch at ""Marina Bay Sands""" -a 1333.37

Name            : Lunch at "Marina Bay Sands"
Date and Time   : 07 Nov 2022 12:15
Amount          : 1333.37
Currency        : SGD

The expense was added successfully.
```

<br>

[Back to Sub-Contents](#how-to-use-the-user-guide)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

##### Limitation on adding double quotes
Argument values cannot start and end with escaped double quotes.

<br>

Example:
```
Add-Expense -n """Lunch""" -a 13.37

Name            : Lunch
Date and Time   : 07 Nov 2022 18:58
Amount          : 13.37
Currency        : SGD

The expense was added successfully.
``` 

<br>

[Back to Sub-Contents](#how-to-use-the-user-guide)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

##### Limitation on adding hyphens
Argument values cannot be the same as the command's arguments.

<br>

Example:
```
Add-Expense -n "-n" -a 13.37

ERROR: The arguments entered are invalid.
SYNTAX: Add-Expense -n NAME -a AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-c CATEGORY]
[-r REMARKS] [-x CURRENCY] [-p MODE-OF-PAYMENT]
```
<br>

[Back to Sub-Contents](#how-to-use-the-user-guide)

<br>
<br>

<div style="page-break-after: always;"></div>

## Quick Start

### Sub-Contents
* [Getting started with MoneyGoWhere](#getting-started-with-moneygowhere)
* [Basic commands](#basic-commands)
* [Back to Contents](#contents)

<br>
<hr>
<br>

### Getting started with MoneyGoWhere

<br>

#### On Windows OS

1. Navigate to [our repository](https://github.com/AY2223S1-CS2113T-W11-1/tp/releases) to download the latest release of MoneyGoWhere.
2. If you are running **Windows 10** or earlier, select **Windows Powershell** from the start menu to launch the Windows Powershell Terminal.
3. If you are running **Windows 11** or later, select **Terminal** from the start menu to launch the Windows Terminal.
4. Execute the command `java --version` in your terminal window to verify that **Java 11** is installed on your system.
   ```
   java 11.0.16 2022-07-19 LTS
   ``` 
5. Execute the command  `java -jar MoneyGoWhere.jar` to launch MoneyGoWhere
6. When successfully run, MoneyGoWhere should display the following message:

<div style="page-break-after: always;"></div>

```
  __  __                         _____   __          ___                   
 |  \/  |                       / ____|  \ \        / / |                  
 | \  / | ___  _ __   ___ _   _| |  __  __\ \  /\  / /| |__   ___ _ __ ___ 
 | |\/| |/ _ \| '_ \ / _ \ | | | | |_ |/ _ \ \/  \/ / | '_ \ / _ \ '__/ _ \
 | |  | | (_) | | | |  __/ |_| | |__| | (_) \  /\  /  | | | |  __/ | |  __/
 |_|  |_|\___/|_| |_|\___|\__, |\_____|\___/ \/  \/   |_| |_|\___|_|  \___|
                           __/ |                                           
                          |___/                                            

Your MoneyGoWhere? Let me help you track it.
```
<br>

[Back to Sub-Contents](#quick-start)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### On Mac OS

1. Navigate to [our repository](https://github.com/AY2223S1-CS2113T-W11-1/tp/releases) to download the latest release of MoneyGoWhere.
2. Open the **Applications** folder and open **Utilities**. Select **Terminal** to launch the Mac OS Terminal.
3. Execute the command `java --version` in your terminal window to verify that **Java 11** is installed on your system.
   ```
   java 11.0.16 2022-07-19 LTS
   ``` 
4. Execute the command  `java -jar MoneyGoWhere.jar`
5. When successfully run, MoneyGoWhere should display the following message:

```
  __  __                         _____   __          ___                   
 |  \/  |                       / ____|  \ \        / / |                  
 | \  / | ___  _ __   ___ _   _| |  __  __\ \  /\  / /| |__   ___ _ __ ___ 
 | |\/| |/ _ \| '_ \ / _ \ | | | | |_ |/ _ \ \/  \/ / | '_ \ / _ \ '__/ _ \
 | |  | | (_) | | | |  __/ |_| | |__| | (_) \  /\  /  | | | |  __/ | |  __/
 |_|  |_|\___/|_| |_|\___|\__, |\_____|\___/ \/  \/   |_| |_|\___|_|  \___|
                           __/ |                                           
                          |___/                                            

Your MoneyGoWhere? Let me help you track it.
```

<br>

[Back to Sub-Contents](#quick-start)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### On Linux OS

1. Navigate to [our repository](https://github.com/AY2223S1-CS2113T-W11-1/tp/releases) to download the latest release of MoneyGoWhere.
2. Open the **Launcher** and select **Terminal** to launch the Linux Terminal.
3. Execute the command `java --version` in your terminal window to verify that **Java 11** is installed on your system.
   ```
   java 11.0.16 2022-07-19 LTS
   ``` 
4. Execute the command  `java -jar MoneyGoWhere.jar`
5. When successfully run, MoneyGoWhere should display the following message:

```
  __  __                         _____   __          ___                   
 |  \/  |                       / ____|  \ \        / / |                  
 | \  / | ___  _ __   ___ _   _| |  __  __\ \  /\  / /| |__   ___ _ __ ___ 
 | |\/| |/ _ \| '_ \ / _ \ | | | | |_ |/ _ \ \/  \/ / | '_ \ / _ \ '__/ _ \
 | |  | | (_) | | | |  __/ |_| | |__| | (_) \  /\  /  | | | |  __/ | |  __/
 |_|  |_|\___/|_| |_|\___|\__, |\_____|\___/ \/  \/   |_| |_|\___|_|  \___|
                           __/ |                                           
                          |___/                                            

Your MoneyGoWhere? Let me help you track it.
```

<br>

[Back to Sub-Contents](#quick-start)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Basic commands

| Command                   | Syntax                                                                                                                              |
|:--------------------------|:------------------------------------------------------------------------------------------------------------------------------------|
| Add expense               | `Add-Expense -n NAME -a AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE-OF-PAYMENT]` |
| View expense(s)           | `View-Expense [-e EXPENSE-INDEX] [-c CATEGORY] [-n NAME]`                                                                           |
| Add recurring payment     | `Add-RecurringPayment -n NAME -i INTERVAL -a AMOUNT [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY] [-p MODE-OF-PAYMENT]`              |
| View recurring payment(s) | `View-RecurringPayment [-r RECURRINGPAYMENT-INDEX]`                                                                                 |
| Add income                | `Add-Income -n NAME -a AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION]`                                                                |
| View income(s)            | `View-Income [-e INCOME-INDEX]`                                                                                                     |
| Add target                | `Add-Target -n NAME -a AMOUNT -c CURRENT-AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION]`                                              |
| View target(s)            | `View-Target [-e TARGET-INDEX]`                                                                                                     |
| View command list         | `Help`                                                                                                                              |
| Exit                      | `Bye`                                                                                                                               |

<br>

[Back to Sub-Contents](#quick-start)

<br>
<br>

<div style="page-break-after: always;"></div>

## Features

### Displaying the help menu: `Help`
Lists all valid commands for MoneyGoWhere.

Syntax: `Help`

Example of usage:

`Help`

_If you would like a user-friendly overview of all available commands, please refer to the [Command Summary](#command-summary) at the end of the guide._

<br>

[Back to Contents](#contents)

<br>
<br>

## Managing your expenses

### Sub-Contents
* [Adding an expense](#adding-an-expense-add-expense): `Add-Expense`
* [Viewing expense(s)](#viewing-expenses-view-expense): `View-Expense`
* [Deleting an expense](#deleting-an-expense-delete-expense): `Delete-Expense`
* [Editing an expense](#editing-an-expense-edit-expense): `Edit-Expense`
* [Sorting expenses](#sorting-expenses-sort-expense): `Sort-Expense`
* [Converting the currency of an expense](#converting-the-currency-of-an-expense-convert-currency): `Convert-Currency`
* [Back to Contents](#contents)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Adding an expense: `Add-Expense`

Adds a new expense to the list of expenses.

<br>

Syntax: Syntax: `Add-Expense -n NAME -a AMOUNT [-d DATE-TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE-OF-PAYMENT]`

{: .notes }
> * `NAME`, `DESCRIPTION`, `CATEGORY`, `REMARKS` and `MODE-OF-PAYMENT` are text strings.
> * `DATE-TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.
> * `CURRENCY` is a text string. It must be a valid currency code.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).
> * You may exclude the `DATE-TIME` argument from the command and MoneyGoWhere will save the current date and time for you.

<br>

<div style="page-break-after: always;"></div>

Examples of usage:

#### Adding an expense using mandatory arguments
```
Add-Expense -n Laptop -a 2999.90

Name            : Laptop
Date and Time   : 07 Nov 2022 16:47
Amount          : 2999.90
Currency        : SGD

The expense was added successfully.
```

<br>

#### Adding an expense using mandatory and optional arguments
```
Add-Expense -n "Work Laptop" -a 2999.90 -d "01/01/2022 1200" -t "Development Laptop"
-c "Work Expenses" -r "Submit invoice to finance" -x SGD -p Card

Name            : Work Laptop
Date and Time   : 01 Jan 2022 12:00
Description     : Development Laptop
Amount          : 2999.90
Category        : Work Expenses
Remarks         : Submit invoice to finance
Currency        : SGD
Mode of Payment : Card

The expense was added successfully.
```

<br>

[Back to Sub-Contents](#managing-your-expenses)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Viewing expense(s): `View-Expense`
Displays past expenses you have added.

<br>

Syntax: `View-Expense [-e EXPENSE-INDEX] [-c CATEGORY] [-n NAME]`

{: .notes }
> * `EXPENSE-INDEX` is an integer value. This value should be equal to or greater than 0.
> * `CATEGORY` and `NAME` are text strings.
> * `CATEGORY` should be entered using its full name.
> * The precedence of the optional arguments are as follows: `EXPENSE-INDEX`, `CATEGORY`, `NAME`. If any combination of these arguments is entered, MoneyGoWhere will display an output according to their precedence.

<br>

<div style="page-break-after: always;"></div>

Examples of usage:

#### Viewing all expenses
```
View-Expense

---- EXPENSE INDEX 0 ----
Name            : Work Laptop
Date and Time   : 01 Jan 2022 12:00
Description     : Development Laptop
Amount          : 2999.90
Remarks         : Submit invoice to finance
Currency        : SGD
Mode of Payment : Card
---- EXPENSE INDEX 1 ----
Name            : Flash Drive
Date and Time   : 07 Nov 2022 17:10
Amount          : 28.00
Category        : Work Expenses
Currency        : USD
Mode of Payment : PayLah
Mode of Payment : Card
```

<br>

#### Viewing an expense using an expense index
```
View-Expense -e 1

---- EXPENSE INDEX 1 ----
Name            : Flash Drive
Date and Time   : 07 Nov 2022 17:10
Amount          : 28.00
Category        : Work Expenses
Currency        : USD
Mode of Payment : Card
```

<br>

<div style="page-break-after: always;"></div>

#### Viewing expenses by category
```
View-Expense -c "Work Expenses"

---- EXPENSE INDEX 0 ----
Name            : Flash Drive
Date and Time   : 07 Nov 2022 17:10
Amount          : 28.00
Category        : Work Expenses
Currency        : USD
Mode of Payment : Card
```

<br>

#### Viewing expenses containing a specific keyword within its name
```
View-Expense -n "Work"

---- EXPENSE INDEX 0 ----
Name            : Work Laptop
Date and Time   : 01 Jan 2022 12:00
Description     : Development Laptop
Amount          : 2999.90
Remarks         : Submit invoice to finance
Currency        : SGD
Mode of Payment : Card
```

<br>

[Back to Sub-Contents](#managing-your-expenses)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Deleting an expense: `Delete-Expense`
Deletes an expense from the list of expenses.

<br>

Syntax: `Delete-Expense -e EXPENSE-INDEX`

{: .notes }
> * `EXPENSE-INDEX` is an integer value. This value should be equal to or greater than 0.

<br>

Example of usage:

#### Deleting an expense using an expense index
```
Delete-Expense -e 1

The expense was deleted successfully.
```

<br>

[Back to Sub-Contents](#managing-your-expenses)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Editing an expense: `Edit-Expense`
Edits an existing expense in the list of expenses.

<br>

Syntax: `Edit-Expense -e EXPENSE-INDEX [-n NAME] [-d DATE-TIME] [-t DESCRIPTION] [-a AMOUNT] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE-OF-PAYMENT]`

{: .notes }
> * `EXPENSE-INDEX` is an integer value. This value should be equal to or greater than 0.
> * `NAME`, `DESCRIPTION`, `CATEGORY`, `REMARKS` and `MODE-OF-PAYMENT` are text strings.
> * `DATE-TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.
> * `AMOUNT` is a decimal value. The value should be greater than 0.
> * `CURRENCY` is a text string. It must be a valid currency code.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).

<br>

<div style="page-break-after: always;"></div>

Examples of usage:

#### Editing the amount attribute of an expense
```
Edit-Expense -e 0 -a 2799.90

---- EXPENSE INDEX 0 ----
Name            : Work Laptop
Date and Time   : 01 Jan 2022 12:00
Description     : Development Laptop
Amount          : 2799.90
Remarks         : Submit invoice to finance
Currency        : SGD
Mode of Payment : Card

The expense was edited sucessfully.
```

<br>

<div style="page-break-after: always;"></div>

#### Editing all attributes of an expense
```
Edit-Expense -e 1 -n "Flash Drive" -d "07/11/2022 1510" -t "Development Flash Drive"
-a 26.00 -c "Work Expenses" -r "Submit invoice to finance" -x USD -p Card

---- EXPENSE INDEX 1 ----
Name            : Flash Drive
Date and Time   : 07 Nov 2022 15:10
Description     : Development Flash Drive
Amount          : 26.00
Category        : Work Expenses
Remarks         : Submit invoice to finance
Currency        : USD
Mode of Payment : Card

The expense was edited successfully.
```

<br>

[Back to Sub-Contents](#managing-your-expenses)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Sorting expenses: `Sort-Expense`
Sorts the list of expenses according to an alphabetical, amount, date or currency order. It can be sorted in both ascending and
descending order.

{: .tips }
> 1. By default, expenses are sorted in alphabetical order, from A to Z. Expenses are sorted **automatically** whenever a new expense is added, or if an existing expense is edited.
> 2. The nature of this feature could cause the indexes of the expenses to change while running MoneyGoWhere. Please use `View-Expense` to get the most updated indexes of the expenses.

<br>

Syntax: `Sort-Expense -t TYPE -o ORDER`

{: .notes }
> * `TYPE` is a text string. It can be either `alphabetical`, `amount`, `date` or `currency`.
> * `ORDER` is a text string. It can be either `ascending` or `descending`.

<br>

Examples of usage:

#### Sorting expenses by date in ascending order
```
Sort-Expense -t date -o ascending

Your expenses have been sorted successfully.
```

<br>

#### Sorting expenses by amount in descending order
```
Sort-Expense -t amount -o descending

Your expenses have been sorted successfully.
```

<br>

<div style="page-break-after: always;"></div>

#### Sorting expenses alphabetically in ascending order
```
Sort-Expense -t alphabetical -o ascending

Your expenses have been sorted successfully.
```

<br>

#### Sorting expenses by currency in descending order
```
Sort-Expense -t currency -o descending

Your expenses have been sorted successfully.
```

<br>

[Back to Sub-Contents](#managing-your-expenses)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Converting the currency of an expense: `Convert-Currency`
Converts the currency of an expense from the list of expenses.

<br>

Syntax: `Convert-Currency -e EXPENSE-INDEX -x CURRENCY [-r RATE]`

{: .notes }
> * `EXPENSE-INDEX` is an integer value. This value should be equal to or greater than 0.
> * `CURRENCY` is a text string. It must be a valid currency code.
> * `RATE` is a decimal value.
    >   * This value should be greater than 0.
>   * This rate should be the rate to convert the expense amount from the old currency to the new currency.

<br>

<div style="page-break-after: always;"></div>

Example of usage:

#### Converting the currency of an expense to the Malaysian Ringgit using a custom exchange rate
```
Convert-Currency -e 1 -x MYR -r 1.35

Name            : Flash Drive
Date and Time   : 07 Nov 2022 15:10
Description     : Development Flash Drive
Amount          : 35.10
Category        : Work Expenses
Remarks         : Submit invoice to finance
Currency        : MYR
Mode of Payment : Card

The expense's currency was changed successfully.
```

<br>

[Back to Sub-Contents](#managing-your-expenses)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

## Managing your recurring payments

### Sub-Contents
* [Adding a recurring payment](#adding-a-recurring-payment-add-recurringpayment): `Add-RecurringPayment`
* [Viewing recurring payment(s)](#viewing-recurring-payments-view-recurringpayment): `View-RecurringPayment`
* [Deleting a recurring payment](#deleting-a-recurring-payment-delete-recurringpayment): `Delete-RecurringPayment`
* [Editing a recurring payment](#editing-a-recurring-payment-edit-recurringpayment): `Edit-RecurringPayment`
* [Paying a recurring payment](#paying-a-recurring-payment-pay-recurringpayment): `Pay-RecurringPayment`
* [Back to Contents](#contents)

<br>
<hr>
<br>

### Adding a recurring payment: `Add-RecurringPayment`
Adds a recurring payment to the list of recurring payments.

<br>

Syntax: `Add-RecurringPayment -n NAME -i INTERVAL -a AMOUNT [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY] [-p MODE-OF-PAYMENT]`

{: .notes }
> * `NAME`, `DESCRIPTION`, `CATEGORY` and `MODE_OF_PAYMENT` are text strings.
> * `INTERVAL` is an integer value. Set this value to the estimated number of days between your recurring payments.
> * `AMOUNT` is a decimal value. The value should be greater than 0.
> * `CURRENCY` is a text string. It must be a valid currency code.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).

<br>

<div style="page-break-after: always;"></div>

Examples of usage:

#### Adding a recurring payment using mandatory arguments
```
Add-RecurringPayment -n "Mobile Plan 1" -i 30 -a 20.00

Name            : Mobile Plan 1
Interval (Days) : 30
Amount          : 20.00
Currency        : SGD

The recurring payment was added successfully.
```

<br>

#### Adding a recurring payment using mandatory and optional arguments
```
Add-RecurringPayment -n "Mobile Plan 2" -i 30 -a 20.00
-t "Monthly payment for my 2nd mobile plan" -c Bills -x SGD -p Card

Name            : Mobile Plan 2
Interval (Days) : 30
Description     : Monthly payment for my 2nd mobile plan
Amount          : 20.00
Category        : Bills
Currency        : SGD
Mode of Payment : Card

The recurring payment was added successfully.
```

<br>

[Back to Sub-Contents](#managing-your-recurring-payments)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Viewing recurring payment(s): `View-RecurringPayment`
Displays the past recurring payments you have added.

<br>

Syntax: `View-RecurringPayment [-r RECURRINGPAYMENT-INDEX]`

{: .notes }
> * `RECURRINGPAYMENT-INDEX` is an integer value.
    >   * This value should be equal to or greater than 0.
>   * If this argument is provided, MoneyGoWhere will only display the specified recurring payment.

<br>

Examples of usage:

#### Viewing all recurring payments
```
View-RecurringPayment

---- RECURRING PAYMENT INDEX 0 ----
Name            : Mobile Plan 1
Interval (Days) : 30
Amount          : 20.00
Currency        : SGD

---- RECURRING PAYMENT INDEX 1 ----
Name            : Mobile Plan 2
Interval (Days) : 30
Description     : Monthly payment for my 2nd mobile plan
Amount          : 20.00
Category        : Bills
Currency        : SGD
Mode of Payment : Card
```

<br>

<div style="page-break-after: always;"></div>

#### Viewing a recurring payment using a recurring payment index
```
View-RecurringPayment -r 1

---- RECURRING PAYMENT INDEX 1 ----
Name            : Mobile Plan 2
Interval (Days) : 30
Description     : Monthly payment for my 2nd mobile plan
Amount          : 20.00
Category        : Bills
Currency        : SGD
Mode of Payment : Card
````

<br>

[Back to Sub-Contents](#managing-your-recurring-payments)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Deleting a recurring payment: `Delete-RecurringPayment`
Deletes a recurring payment from the list of recurring payments.

<br>

Syntax: `Delete-RecurringPayment -r RECURRINGPAYMENT-INDEX`

{: .notes }
> * `RECURRINGPAYMENT-INDEX` is an integer value. This value should be equal to or greater than 0.

<br>

Example of usage:

#### Deleting a recurring payment using a recurring payment index
```
Delete-RecurringPayment -r 1

The recurring payment was deleted successfully.
```

<br>

[Back to Sub-Contents](#managing-your-recurring-payments)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Editing a recurring payment: `Edit-RecurringPayment`
Edits a recurring payment in the list of recurring payments.

<br>

Syntax: `Edit-RecurringPayment -r RECURRINGPAYMENT-INDEX [-n NAME] [-i INTERVAL] [-a AMOUNT] [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY] [-p MODE-OF-PAYMENT]`

{: .notes }
> * `RECURRINGPAYMENT-INDEX` is an integer value. This value should be equal to or greater than 0.
> * `NAME`, `DESCRIPTION`, `CATEGORY` and `MODE-OF-PAYMENT` are text strings.
> * `INTERVAL` is an integer value. Set this value to the estimated number of days between your recurring payments.
> * `AMOUNT` is a decimal value. The value should be greater than 0.
> * `CURRENCY` is a text string. It must be a valid currency code.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).

<br>

Examples of usage:

#### Editing the amount attribute of a recurring payment
```
Edit-RecurringPayment -r 0 -a 18.00

---- RECURRING PAYMENT INDEX 0 ----
Name            : Mobile Plan 1
Interval (Days) : 30
Amount          : 18.00
Currency        : SGD

The recurring payment was edited successfully.
```

<br>

<div style="page-break-after: always;"></div>

#### Editing all attributes of a recurring payment
```
Edit-RecurringPayment -r 0 -n "Mobile Plan 2" -i 30
-t "Monthly payment for my 2nd mobile phone" -a 18.00 -c "Bills" -x SGD -p Card

---- RECURRING PAYMENT INDEX 0 ----
Name            : Mobile Plan 2
Interval (Days) : 30
Description     : Monthly payment for my 2nd mobile phone
Amount          : 18.00
Category        : Bills
Currency        : SGD
Mode of Payment : Card

The recurring payment was edited successfully.
```

<br>

[Back to Sub-Contents](#managing-your-recurring-payments)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Paying a recurring payment: `Pay-RecurringPayment`
Adds an expense based on an existing recurring payment.

<br>

Syntax: `Pay-RecurringPayment -r RECURRING_PAYMENT_INDEX [-d DATE-TIME]`

{: .notes }
> * `RECURRING_PAYMENT_INDEX` is an integer value. This value should be equal to or greater than 0.
> * `DATE-TIME` is a text string in the format `"dd/MM/yyyy HHmm"`. If this value is not provided, MoneyGoWhere will save the current date and time for you.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).
> * You may exclude the `DATE-TIME` argument from the command and MoneyGoWhere will save the current date and time for you.

<br>

<div style="page-break-after: always;"></div>

Example of usage:

#### Adding an expense based on a recurring payment
```
Pay-RecurringPayment -r 1

Name            : Mobile Plan 2
Date and Time   : 07 Nov 2022 17:50
Description     : Monthly payment for my 2nd mobile plan
Amount          : 20.00
Category        : Bills
Currency        : SGD
Mode of Payment : Card

The recurring payment was added as an expense successfully.
```

<br>

[Back to Sub-Contents](#managing-your-recurring-payments)

<br>
<br>

<div style="page-break-after: always;"></div>

## Managing your incomes

### Sub-Contents
* [Adding an income](#adding-an-income-add-income): `Add-Income`
* [Viewing income(s)](#viewing-incomes-view-income): `View-Income`
* [Deleting an income](#deleting-an-income-delete-income): `Delete-Income`
* [Editing an income](#editing-an-income-edit-income): `Edit-Income`
* [Back to Contents](#contents)

<br>
<hr>
<br>

### Adding an income: `Add-Income`
Adds a new income to the list of incomes.

<br>

Syntax: `Add-Income -n NAME -a AMOUNT [-d DATE_TIME] [-t DESCRIPTION]`

{: .notes }
> * `NAME` and `DESCRIPTION` are text strings.
> * `AMOUNT` is a decimal value. The value should be greater than 0.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).

<br>

<div style="page-break-after: always;"></div>

Examples of usage:

#### Adding an income using mandatory arguments
```
Add-Income -n "Salary" -a 3000.00

Name          : Salary
Date and Time : 02 Nov 2022 14:19
Amount        : 3000.00

The income was added successfully.
```

<br>

#### Adding an income using mandatory and optional arguments
```
Add-Income -n "Stocks" -a 500.00 -d "01/02/2022 2359" -t "Investment payouts"
        
Name          : Stocks
Date and Time : 01 Feb 2022 23:59
Description   : Investment payouts
Amount        : 500.00

The income was added successfully.
```

<br>

[Back to Sub-Contents](#managing-your-incomes)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Viewing income(s): `View-Income`
Displays past incomes you have added.

<br>

Syntax: `View-Income [-e INCOME-INDEX]`

{: .notes }
> * `INCOME-INDEX` is an integer value.
    >   * This value should be equal to or greater than 0.
>   * If this argument is provided, MoneyGoWhere will only display the specified income.

<br>

Examples of usage:

#### Viewing all incomes
```
View-Income

---- INCOME INDEX 0 ----
Name          : Salary
Date and Time : 03 Nov 2022 13:49
Amount        : 3000.00
---- INCOME INDEX 1 ----
Name          : Stocks
Date and Time : 01 Feb 2022 23:59
Description   : Investment payouts
Amount        : 500.00
```

<br>

<div style="page-break-after: always;"></div>

#### Viewing an income using an income index
```
View-Income -e 1

---- INCOME INDEX 1 ----
Name          : Stocks
Date and Time : 01 Feb 2022 23:59
Description   : Investment payouts
Amount        : 500.00
```

<br>

[Back to Sub-Contents](#managing-your-incomes)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Deleting an income: `Delete-Income`
Deletes an income from the list of incomes.

<br>

Syntax: `Delete-Income -e INCOME-INDEX`

{: .notes }
> * `INCOME-INDEX` is an integer value. This value should be equal to or greater than 0.

<br>

Example of usage:

#### Deleting an income using an income index
```
Delete-Income -e 1

The income was deleted successfully.
```

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Editing an income: `Edit-Income`
Edits an existing income in the list of incomes.

<br>

Syntax: `Edit-Income -e INCOME-INDEX [-n NAME] [-a AMOUNT] [-d DATE_TIME] [-t DESCRIPTION]`

{: .notes }
> * `INCOME-INDEX` is an integer value. This value should be equal to or greater than 0.
> * `NAME` and `DESCRIPTION` are text strings.
> * `AMOUNT` is a decimal value. The value should be greater than 0.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).

<br>

Examples of usage:

#### Editing the amount attribute of an income
```
Edit-Income -e 1 -a 100.00

---- INCOME INDEX 1 ----
Name          : Stocks
Date and Time : 01 Feb 2022 23:59
Description   : Investment payouts
Amount        : 100.00

The income was edited successfully.
```

<br>

<div style="page-break-after: always;"></div>

#### Editing all attributes of an income
```
Edit-Income -e 1 -n "Monthly Salary" -a 3000 -d "01/01/2022 2359" -t "Monthly payment"

---- INCOME INDEX 1 ----
Name          : Monthly Salary
Date and Time : 01 Jan 2022 23:59
Description   : Monthly payment
Amount        : 3000

The income was edited successfully.
```

<br>

[Back to Sub-Contents](#managing-your-incomes)

<br>
<br>

<div style="page-break-after: always;"></div>

## Managing your targets

### Sub-Contents
* [Adding a target](#adding-a-target-add-target): `Add-Target`
* [Viewing target(s)](#viewing-targets-view-target): `View-Target`
* [Deleting a target](#deleting-a-target-delete-target): `Delete-Target`
* [Editing a target](#editing-a-target-edit-target): `Edit-Target`
* [Back to Contents](#contents)

<br>
<hr>
<br>

### Adding a target: `Add-Target`
Adds a new target to the list of targets.

<br>

Syntax: `Add-Target -n NAME -a AMOUNT -c CURRENT_AMOUNT [-d DATE_TIME] [-t DESCRIPTION]`

{: .notes }
> * `NAME` and `DESCRIPTION` are text strings.
> * `AMOUNT` and `CURRENT_AMOUNT` are decimal value. The values should be greater than 0.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).

<br>

<div style="page-break-after: always;"></div>

Examples of usage:

#### Adding a target using mandatory arguments
```
Add-Target -n "Salary" -a 3000.00 -c 1500.00

Name          : Salary
Date and Time : 01 Jan 2022 23:59
Amount        : 3000.00
Current Amount: 1500.00

The target was added successfully.
```

<br>

#### Adding a target using mandatory and optional arguments
```
Add-Target -n "Food target" -a 1000.00 -c 1500.00 -d "01/02/2022 2359"
-t "Money spent on food"

Name          : Food target
Date and Time : 01 Feb 2022 23:59
Description   : Money spent on food
Amount        : 1000.00
Current Amount: 1500.00

The target was added successfully.
```

<br>

[Back to Sub-Contents](#managing-your-targets)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Viewing target(s): `View-Target`
Displays past targets you have added.

<br>

Syntax: `View-Target [-e TARGET-INDEX]`

{: .notes }
> * `TARGET-INDEX` is an integer value.
    >  * This value should be equal to or greater than 0.
>  * If this argument is provided, MoneyGoWhere will only display the specified target.

<br>

Examples of usage:

#### Viewing all targets
```
View-Target

---- TARGET INDEX 0 ----
Name          : Salary
Date and Time : 03 Nov 2022 13:58
Amount        : 3000.00
Current Amount: 1500.00
---- TARGET INDEX 1 ----
Name          : Food target
Date and Time : 01 Feb 2022 23:59
Description   : Money spent on food
Amount        : 1000.00
Current Amount: 1500.00
```

<br>

<div style="page-break-after: always;"></div>

#### Viewing a target using a target index

```
View-Target -e 1

---- TARGET INDEX 1 ----
Name          : Food target
Date and Time : 01 Feb 2022 23:59
Description   : Money spent on food
Amount        : 1000.00
Current Amount: 1500.00
```

<br>

[Back to Sub-Contents](#managing-your-targets)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Deleting a target: `Delete-Target`
Deletes a target from the list of targets.

<br>

Syntax: `Delete-Target -e TARGET-INDEX`

{: .notes }
> * `TARGET-INDEX` is an integer value. This value should be equal to or greater than 0.

<br>

Example of usage:

#### Deleting a target using a target index
```
Delete-Target -e 1

The target was deleted successfully.
```

<br>

[Back to Sub-Contents](#managing-your-targets)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Editing a target: `Edit-Target`
Edits an existing target in the list of targets.

<br>

Syntax: `Edit-Target -e TARGET-INDEX [-n NAME] [-a AMOUNT] [-c CURRENT_AMOUNT] [-d DATE_TIME] [-t DESCRIPTION]`

{: .notes }
> * `TARGET-INDEX` is an integer value. This value should be equal to or greater than 0.
> * `NAME` and `DESCRIPTION` are text strings.
> * `AMOUNT` and `CURRENT_AMOUNT` is a decimal value. The values should be greater than 0.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

{: .tips }
> * You may use spaces within your argument values if you wrap the value with [double quotes](#adding-double-quotes).

<br>

Examples of usage:

#### Editing the amount attribute of a target
```
Edit-Target -e 1 -a 100.00

---- TARGET INDEX 1 ----
Name          : Food target
Date and Time : 01 Feb 2022 23:59
Description   : Money spent on food
Amount        : 100.00
Current Amount: 1500.00

The target was edited successfully.
```

<br>

<div style="page-break-after: always;"></div>

#### Editing all attributes of a target
```
Edit-Target -e 1 -n "Monthly Target" -a 3000 -c 1200 -d "01/01/2022 2359"
-t "Monthly payment"

---- TARGET INDEX 1 ----
Name          : Monthly Target
Date and Time : 01 Jan 2022 23:59
Description   : Monthly payment
Amount        : 3000
Current Amount: 1200

The target was edited successfully.
```

<br>

[Back to Sub-Contents](#managing-your-targets)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

## Managing your data
By default, all lists are saved in the `MoneyGoWhereData.xml` file in the **Memory** folder created in the same directory as `MoneyGoWhere.jar`.
Rest assured that your data will be saved automatically and will not be lost when exiting the MoneyGoWhere.

<br>
<hr>
<br>

### Merging a data file: `Merge-File`
Merges the data from an external data file with the data from the current data file.

<br>

Syntax: `Merge-File -p PATH_STRING`

{: .notes }
> * `PATH_STRING` is text string.

{: .tips }
> * If the path to your external data file happens to contain spaces, please wrap the path with [double quotes](#adding-double-quotes).

<br>

Example of usage:

#### Merging an external data file
```
Merge-File -p "C:\Users\the_d\Downloads\expenses.xml"
```

<br>

[Back to Contents](#contents)

<br>
<br>

## Exiting the application: `Bye`

Exits MoneyGoWhere.

{: .tips }
> Worried about losing your data? No worries! MoneyGoWhere saves your data automatically.

Syntax: `Bye`

Example of usage:

`Bye`

<br>

[Back to Contents](#contents)

<br>
<br>

<div style="page-break-after: always;"></div>

## FAQ

A compilation of frequently asked questions that can help you clarify common misconceptions in using MoneyGoWhere.

<br>

{: .question }
> How do I transfer my data to another computer?
> <div markdown="block">
> {: .answer }
> As you enter data into MoneyGoWhere, it creates a **Memory** folder in the same directory as the `MoneyGoWhere.jar` file. To merge the copied file, open the **Memory** folder and transfer `MoneyGoWhereData.xml`** to another computer. Then, run the [`Merge-File`](#merging-a-data-file-merge-file) command to merge the copied file.
> </div>

<br>

{: .question }
> Does this application work over a remote terminal shell?
> <div markdown="block">
> {: .answer }
> Yes, MoneyGoWhere does work over a remote terminal shell such as the Secure Shell (SSH). 
> </div>

<br>

[Back to Contents](#contents)

<br>
<br>

<div style="page-break-after: always;"></div>

## Command Summary

An overview of all valid commands that you can use in MoneyGoWhere.


### Expenses

| Command          | Syntax                                                                                                                                                    |
|:-----------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add expense      | `Add-Expense -n NAME -a AMOUNT [-d DATE_TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]`                             |
| View expense(s)  | `View-Expense [-e EXPENSE-INDEX] [-c EXPENSE-CATEGORY] [-n EXPENSE-NAME]`                                                                                 |
| Delete expense   | `Delete-Expense -e EXPENSE-INDEX`                                                                                                                         |
| Edit expense     | `Edit-Expense -e EXPENSE-INDEX [-n NAME] [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-a AMOUNT] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]` |
| Sort expense     | `Sort-Expense -t Alphabetical/Amount/Date/Currency -o Ascending/Descending`                                                                               |
| Convert currency | `Convert-Currency -e EXPENSE-INDEX -x CURRENCY [-r RATE]`                                                                                                 |

<br>

<div style="page-break-after: always;"></div>

### Recurring Payments

| Command                   | Syntax                                                                                                                                                  |
|:--------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add recurring payment     | `Add-RecurringPayment -n NAME -i INTERVAL -a AMOUNT [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY] [-p MODE-OF-PAYMENT]`                                  |
| View recurring payment(s) | `View-RecurringPayment [-r RECURRINGPAYMENT-INDEX]`                                                                                                     |
| Delete recurring payment  | `Delete-RecurringPayment -r RECURRINGPAYMENT-INDEX`                                                                                                     |
| Edit recurring payment    | `Edit-RecurringPayment -r RECURRINGPAYMENT-INDEX [-n NAME] [-i INTERVAL] [-a AMOUNT] [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY] [-p MODE-OF-PAYMENT]` |
| Pay recurring payment     | `Pay-RecurringPayment -r RECURRINGPAYMENT-INDEX [-d dd/MM/yyyy HHmm]`                                                                                   |

<br>

<div style="page-break-after: always;"></div>

### Incomes

| Command        | Syntax                                                                                    |
|:---------------|:------------------------------------------------------------------------------------------|
| Add income     | `Add-Income -n NAME -a AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION]`                      |
| View income(s) | `View-Income [-e INCOME-INDEX]`                                                           |
| Delete income  | `Delete-Income -e INCOME-INDEX`                                                           |
| Edit income    | `Edit-Income -e INCOME-INDEX [-n NAME] [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-a AMOUNT]` |

<br>

### Targets

| Command        | Syntax                                                                                                        |
|:---------------|:--------------------------------------------------------------------------------------------------------------|
| Add target     | `Add-Target -n NAME -a AMOUNT -c CURRENT-AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION]`                        |
| View target(s) | `View-Target [-e TARGET-INDEX]`                                                                               |
| Delete target  | `Delete-Target -e TARGET-INDEX`                                                                               |
| Edit target    | `Edit-Target -e TARGET-INDEX [-n NAME] [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-a AMOUNT] [-c CURRENT-AMOUNT]` |

<br>

### Miscellaneous

| Command           | Syntax                      |
|:------------------|:----------------------------|
| Merge file        | `Merge-File -p PATH-STRING` |
| View command list | `Help`                      |
| Exit              | `Bye`                       |

<br>

[Back to Contents](#contents)

<br>
<br>
