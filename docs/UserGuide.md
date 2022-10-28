# MoneyGoWhere
<p align="center"><img alt="icon" src="https://raw.githubusercontent.com/penguin-s/tp/branch-User-Guide/docs/images/icon.png"></p>

## Contents
* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Features](#features)
   * [Managing Your Expenses](#managing-your-expenses)
   * [Managing Your Recurring Payments](#managing-your-recurring-payments)
   * [Managing Your Incomes](#managing-your-incomes)
   * [Managing Your Targets](#managing-your-targets)
   * [Handling Your Data](#handling-your-data)
   * [Listing all available commands: `help`](#listing-all-available-commands-help)
   * [Exiting the application: `exit`](#exiting-the-application-exit)
* [Sample Outputs](#sample-outputs)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Introduction

MoneyGoWhere is a financial planner to help you manage your finances.

## Quick Start

1. Ensure that ```Java 11``` is installed on your system.
    1. Execute the command ```java --version``` in your terminal window.
    2. Verify that the version of Java installed is ```Java 11```.
2. Ensure that you have write permissions for the directory in which you are executing the program.

## Features

## Managing your expenses
### Adding an expense: `Add-Expense`
Adds a new expense to the list of expenses.

Syntax: `Add-Expense -n NAME -a AMOUNT [-d DATE_TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]`

> ⚠️️️️ Syntax Notes
> * `NAME`, `DESCRIPTION`, `CATEGORY`, `REMARKS` and `MODE OF PAYMENT` are text strings. You may use spaces within the text if you wrap the text with double quotes.</li>
> * `CURRENCY` is a text string. It must be a valid currency code.
> * `AMOUNT` is a decimal value.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.


Examples of usage: 
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`
* `Add-Expense -n Subscription -a 13.37`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`

<hr>

### Viewing expense(s): `View-Expense`
Displays past expenses you have added.

Syntax: `View-Expense [-e EXPENSE_NUMBER]`

> ⚠️️️️ Syntax Notes
> * `EXPENSE_NUMBER` is an integer value.
> * If this argument is provided, MoneyGoWhere will only display the specified expense.

Example of usage:
* `View-Expense`
* `View-Expense -e 1`

<hr>

### Deleting an expense: `Delete-Expense`
Deletes an expense from the list of expenses.

Syntax: `Delete-Expense -e EXPENSE_NUMBER`

> ⚠️️️️ Syntax Notes
> * `EXPENSE_NUMBER` is an integer value.

Example of usage:
* `Delete-Expense -e 1`

<hr>

### Editing an expense: `Edit-Expense`
Edits an existing expense in the list of expenses.

Syntax: `Edit-Expense -e EXPENSE_NUMBER [-n NAME] [-a AMOUNT] [-d DATE_TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]`

> ⚠️️️️ Syntax Notes
> * `EXPENSE_NUMBER` is an integer value.
> * `NAME`, `DESCRIPTION`, `CATEGORY`, `REMARKS` and `MODE OF PAYMENT` are text strings. You may use spaces within the text if you wrap the text with double quotes.
> * `CURRENCY` is a text string. It must be a valid currency code.
> * `AMOUNT` is a decimal value.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

Example of usage:
* `Edit-Expense -e 1 -n Subscription -a 13.37`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`

<hr>

### Sorting expenses: `Sort-Expense`
Sorts the list of expenses according to an alphabetical, amount, date or currency order. It can be sorted in both ascending and
descending order. 

Syntax: `Sort-Expense -t TYPE -o ORDER`

> ⚠️️️️ Syntax Notes
> * `TYPE` is a text string. It can be either `alphabetical`, `amount`, `date` or `currency`.
> * `ORDER` is a text string. It can be either `ascending` or `descending`.

Example of usage:
* `Sort-Expense -t date -o ascending`
* `Sort-Expense -t amount -o descending`
* `Sort-Expense -t alphabetical -o ascending`
* `Sort-Expense -t currency -o descending`

<hr>

### Converting currency of an expense: `Convert-Currency`
Converts the currency of an expense from the list of expenses.

Syntax: `Convert-Currency -e EXPENSE_NUMBER -x CURRENCY [-r RATE]`

> ⚠️️️️ Syntax Notes
> * `EXPENSE_NUMBER` is an integer value.
> * `CURRENCY` is a text string. It must be a valid currency code.
> * `RATE` is a decimal value. This rate should be the rate to convert the expense amount from the old currency to the new currency.

Example of usage:
* `Convert-Currency -e 1 -x USD -r 1.35`

## Managing your recurring payments
### Adding recurring payments: `Add-RecurringPayment`
Adds a recurring payment to the list of recurring payments

Syntax: `Add-RecurringPayment -n NAME -i INTERVAL -a AMOUNT [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY] [-p MODE_OF_PAYMENT]`

> ⚠️️️️ Syntax Notes
> * `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.
> * `INTERVAL` is an integer value. Set this value to the estimated number of days between your recurring payments.
> * `AMOUNT` is a decimal value.
> * `MODE_OF_PAYMENT` is a text string.

Example of usage:
* `Add-RecurringPayment -n "Mobile Plan" -i 30 -a 20.00`
* `Add-RecurringPayment -n "Mobile Plan" -i 30 -a 20.00 -t "Monthly payment for my mobile plan" -c Telecom -x SGD -p Card`

<hr>

### Viewing recurring payments: `View-RecurringPayment`
Displays the past recurring payments you have added.

Syntax: `View-RecurringPayment [-r RECURRING_PAYMENT_INDEX]`

> ⚠️️️️ Syntax Notes
> * `RECURRING_PAYMENT_INDEX` is an integer value. If this argument is provided, MoneyGoWhere will only display the specified recurring payment.

Example of usage:
* `View-RecurringPayment`
* `View-RecurringPayment -r 1`

<hr>

### Deleting recurring payments: `Delete-RecurringPayment`
Deletes a recurring payment from the list of recurring payments.

Syntax: `Delete-RecurringPayment -r RECURRING_PAYMENT_INDEX`

> ⚠️️️️ Syntax Notes
> * `RECURRING_PAYMENT_INDEX` is an integer value.

Example of usage:
* `Delete-RecurringPayment -r 1`

<hr>

### Editing recurring payments: `Edit-RecurringPayment`
Edits a recurring payment in the list of recurring payments

Syntax: `Edit-RecurringPayment -r RECURRING_PAYMENT_INDEX [-n NAME] [-i INTERVAL] [-a AMOUNT] [-t DESCRIPTION]`

> ⚠️️️️ Syntax Notes
> * `RECURRING_PAYMENT_INDEX` is an integer value.
> * `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.
> * `INTERVAL` is an integer value. Set this value to the estimated number of days between your recurring payments.
> * `AMOUNT` is a decimal value.

Example of usage:
* `Edit-RecurringPayment -r 1 -a 20.00`
* `Edit-RecurringPayment -r 1 -n "Mobile Plan" -i 30 -a 20.00 -t "Monthly payment for my mobile plan" -c Telecom -x SGD -p Card`

<hr>

### Paying recurring payments: `Pay-RecurringPayment`
Pays a recurring payment from the list of recurring payments.

Syntax: `Pay-RecurringPayment -r RECURRING_PAYMENT_INDEX [-d DATE_TIME]`

> ⚠️️️️ Syntax Notes
> * `RECURRING_PAYMENT_INDEX` is an integer value.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`. If this value is not provided, MoneyGoWhere will save the current date and time for you.

Example of usage:
* `Pay-RecurringPayment -r 1`

## Managing your incomes
### Adding an income: `Add-Income`
Adds a new income to the list of incomes.

Syntax: `Add-Income -n NAME -a AMOUNT [-d DATE_TIME] [-t DESCRIPTION]`

> ⚠️️️️ Syntax Notes
> * `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.</li>
> * `AMOUNT` is a decimal value.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.


Examples of usage:
* `Add-Income -n "Stocks" -a 500.00 -d "01/02/2022 2359" -t "Investment payouts"`
* `Add-Income -n "Salary" -a 3000.00`

<hr>

### Viewing income(s): `View-Income`
Displays past incomes you have added.

Syntax: `View-Income [-e INCOME_NUMBER]`

> ⚠️️️️ Syntax Notes
> * `INCOME_NUMBER` is an integer value.
> * If this argument is provided, MoneyGoWhere will only display the specified expense.

Example of usage:
* `View-Income`
* `View-Income -e 1`

<hr>

### Deleting an income: `Delete-Income`
Deletes an income from the list of incomes.

Syntax: `Delete-Income -e INCOME_NUMBER`

> ⚠️️️️ Syntax Notes
> * `INCOME_NUMBER` is an integer value.

Example of usage:
* `Delete-Income -e 1`

<hr>

### Editing an income: `Edit-Income`
Edits an existing income in the list of incomes.

Syntax: `Edit-Income -e INCOME_NUMBER [-n NAME] [-a AMOUNT] [-d DATE_TIME] [-t DESCRIPTION]`

> ⚠️️️️ Syntax Notes
> * `INCOME_NUMBER` is an integer value.
> * `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.
> * `AMOUNT` is a decimal value.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

Example of usage:
* `Edit-Income -e 1 -n Payout -a 100.00`
* `Edit-Income -e 1 -n "Monthly Salary" -a 3000 -d "01/01/2022 2359" -t "Monthly payment"`

<hr>

## Managing your targets
### Adding a target: `Add-Target`
Adds a new target to the list of targets.

Syntax: `Add-Target -n NAME -a AMOUNT -c CURRENT_AMOUNT [-d DATE_TIME] [-t DESCRIPTION]`

> ⚠️️️️ Syntax Notes
> * `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.</li>
> * `AMOUNT` and `CURRENT_AMOUNT` are decimal value.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.


Examples of usage:
* `Add-Target -n "Food target" -a 1000.00 -c 1500.00 -d "01/02/2022 2359" -t "Money spent on food"`
* `Add-Target -n "Salary" -a 3000.00 -c 1500.00`

<hr>

### Viewing target(s): `View-Target`
Displays past targets you have added.

Syntax: `View-Target [-e TARGET_NUMBER]`

> ⚠️️️️ Syntax Notes
> * `TARGET_NUMBER` is an integer value.
> * If this argument is provided, MoneyGoWhere will only display the specified expense.

Example of usage:
* `View-Target`
* `View-Target -e 1`

<hr>

### Deleting a target: `Delete-Target`
Deletes a target from the list of targets.

Syntax: `Delete-Target -e TARGET_NUMBER`

> ⚠️️️️ Syntax Notes
> * `TARGET_NUMBER` is an integer value.

Example of usage:
* `Delete-Target -e 1`

<hr>

### Editing a target: `Edit-Target`
Edits an existing target in the list of targets.

Syntax: `Edit-Target -e INCOME_NUMBER [-n NAME] [-a AMOUNT] [-c CURRENT_AMOUNT] [-d DATE_TIME] [-t DESCRIPTION]`

> ⚠️️️️ Syntax Notes
> * `INCOME_NUMBER` is an integer value.
> * `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.
> * `AMOUNT` and `CURRENT_AMOUNT` is a decimal value.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

Example of usage:
* `Edit-Target -e 1 -n "October Target" -a 100.00`
* `Edit-Income -e 1 -n "Monthly Target" -a 3000 -c 1200 -d "01/01/2022 2359" -t "Monthly payment"`

<hr>

## Handling your data
### Merging a data file: `Merge-File`
Merges save file from an external source given path to the save file (.xml)

Syntax: `Merge-File [-p PATH_STRING]`

Example of usage:
* `Merge-File -p "C:\Users\the_d\Downloads\expenses.xml"`

<hr>

### Listing all available commands: `help`
Lists all valid commands for MoneyGoWhere.

Syntax: `help`

Example of usage:
* `help`

<hr>

### Exiting the application: `exit`
Exits MoneyGoWhere.

Syntax: `exit`

Example of usage:
* `exit`

## Sample Outputs
Input: `Add-Expense -n Subscription -a 13.37`

Output:
```
Name            : Subscription 
Date and Time   : 27 Oct 2022 16:40
Amount          : 13.37
Currency        : SGD


The expense was added successfully.
```

<hr>

Input: `View-RecurringPayment`

Output:
```
---- RECURRING PAYMENT INDEX 0 ----
Name            : Mobile Plan
Interval (Days) : 30
Description     : Monthly payment for my mobile plan
Amount          : 20.00
Category        : Telecom
Currency        : SGD
Mode of Payment : Card
```

<hr>


Input: `Pay-RecurringPayment -r 0`

Output:
```
Name            : Mobile Plan
Date and Time   : 27 Oct 2022 16:49
Description     : Monthly payment for my mobile plan
Amount          : 10.00
Category        : Telecom
Currency        : SGD
Mode of Payment : Card

The recurring payment was added as an expense successfully.
```
## FAQ

**Q**: How do I transfer my data to another computer?

**A**: There will be a 'Memory' folder created in the same directory as your .jar file. 
To transfer your data, simply copy the whole folder over to the same directory as the .jar 
file in your other devices. Data will be saved automatically.

## Command Summary

A list of all valid commands.

* Add expense: `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD`
* View expense: `View-Expense`
* Delete expense: `Delete-Expense -e 1`
* Edit expense: `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD`
* Sort expense: `Sort-Expense -t alphabetical -o ascending"`
* Convert currency: `Convert-Currency -e 1 -x USD`
* Add recurring payment: `Add-RecurringPayment -n "Mobile Plan" -i 30 -a 20.00`
* View recurring payment: `View-RecurringPayment`
* Delete recurring payment: `Delete-RecurringPayment -r 1`
* Edit recurring payment: `Edit-RecurringPayment -r 1 -n "Mobile Plan" -i 30 -a 20.00 -t "Monthly payment for my mobile plan" -c Telecom -x SGD -p Card`
* Pay recurring payment: `Pay-RecurringPayment -r 1`
* Merge file: `Merge-File -p "C:\Users\the_d\Downloads\expenses.xml"`
* Help: `help`
* Exit: `exit`

