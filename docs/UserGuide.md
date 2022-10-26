# MoneyGoWhere
<p align="center"><img alt="icon" src="https://raw.githubusercontent.com/ay2223s1-cs2113t-w11-1/tp/master/docs/images/icon.png"></p>

<hr>

## Contents
* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Legend](#legend)
* [Features](#features)
   * [Managing Your Expenses](#managing-your-expenses)
   * [Managing Your Recurring Payments](#managing-your-recurring-payments)
   * [Handling Your Data](#handling-your-data)
   * [Listing all available commands: `help`](#listing-all-available-commands-help)
   * [Exiting the application: `exit`](#exiting-the-application-exit)
* [FAQ](#faq)
* [Command Summary](#command-summary)

<hr>

## Introduction

MoneyGoWhere is a financial planner to help you manage your finances.

<hr>

## Quick Start

1. Ensure that ```Java 11``` is installed on your system.
    1. Execute the command ```java --version``` in your terminal window.
    2. Verify that the version of Java installed is ```Java 11```.
2. Ensure that you have write permissions for the directory in which you are executing the program.

<hr>

## Legend
> ⚠️ **Warnings**

> 💡 **Tips**


<hr>

## Features

## Managing your expenses
### Adding an expense: `Add-Expense`
Adds a new expense to the list of expenses.

Syntax: `Add-Expense -n NAME -a AMOUNT [-d DATE_TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `NAME`, `DESCRIPTION`, `CATEGORY`, `REMARKS` and `MODE OF PAYMENT` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `CURRENCY` is a text string. It must be a valid currency code.
* `AMOUNT` is a decimal value.
* `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.
</details>


Examples of usage: 
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`
* `Add-Expense -n Subscription -a 13.37`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`

<hr>

### Viewing expense(s): `View-Expense`
Displays past expenses you have added.

Syntax: `View-Expense [-e EXPENSE_NUMBER]`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

`EXPENSE_NUMBER` is an integer value.
If this argument is provided, MoneyGoWhere will only display the specified expense.
</details>

Example of usage:

* `View-Expense`
* `View-Expense -e 1`

<hr>

### Deleting an expense: `Delete-Expense`
Deletes an expense from the list of expenses.

Syntax: `Delete-Expense -e EXPENSE_NUMBER`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

`EXPENSE_NUMBER` is an integer value.
</details>

Example of usage:

* `Delete-Expense -e 1`

<hr>

### Editing an expense: `Edit-Expense`
Edits an existing expense in the list of expenses.

Syntax: `Edit-Expense -e EXPENSE_NUMBER [-n NAME] [-a AMOUNT] [-d DATE_TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `EXPENSE_NUMBER` is an integer value.
* `NAME`, `DESCRIPTION`, `CATEGORY`, `REMARKS` and `MODE OF PAYMENT` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `CURRENCY` is a text string. It must be a valid currency code.
* `AMOUNT` is a decimal value.
* `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.
</details>


Example of usage:

* `Edit-Expense -e 1 -n Subscription -a 13.37`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`

<hr>

### Sorting expenses: `Sort-Expense`
Sorts the list of expenses according to an alphabetical, amount, date or currency order. It can be sorted in both ascending and
descending order. 

Syntax: `Sort-Expense -t TYPE -o ORDER`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `TYPE` is a text string. It can be either `alphabetical`, `amount`, `date` or `currency`.
* `ORDER` is a text string. It can be either `ascending` or `descending`.
</details>


Example of usage:
* `Sort-Expense -t date -o ascending`
* `Sort-Expense -t amount -o descending`
* `Sort-Expense -t alphabetical -o ascending`
* `Sort-Expense -t currency -o descending`

<hr>

### Converting currency of an expense: `Convert-Currency`
Converts the currency of an expense from the list of expenses.

Syntax: `Convert-Currency -e EXPENSE_NUMBER -x CURRENCY [-r RATE]`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `EXPENSE_NUMBER` is an integer value.
* `CURRENCY` is a text string. It must be a valid currency code.
* `RATE` is a decimal value. This rate should be the rate to convert the expense amount from the old currency to the new currency.
</details>

Example of usage:

* `Convert-Currency -e 1 -x USD -r 1.35`

<hr>

## Managing your recurring payments
### Adding recurring payments: `Add-RecurringPayment`
Adds a recurring payment to the list of recurring payments

Syntax: `Add-RecurringPayment -n NAME -i INTERVAL -a AMOUNT [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY] [-p MODE_OF_PAYMENT]`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `INTERVAL` is an integer value. Set this value to the estimated number of days between your recurring payments.
* `AMOUNT` is a decimal value.
* `MODE_OF_PAYMENT` is a text string.
</details>

Example of usage:
* `Add-RecurringPayment -n "Mobile Plan" -i 30 -a 20.00`
* `Add-RecurringPayment -n "Mobile Plan" -i 30 -a 20.00 -t "Monthly payment for my mobile plan" -c Telecom -x SGD -p Card`

<hr>

### Viewing recurring payments: `View-RecurringPayment`
Displays the past recurring payments you have added.

Syntax: `View-RecurringPayment [-r RECURRING_PAYMENT_INDEX]`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `RECURRING_PAYMENT_INDEX` is an integer value. If this argument is provided, MoneyGoWhere will only display the specified recurring payment.
</details>

Example of usage:

* `View-RecurringPayment`
* `View-RecurringPayment -r 1`

<hr>

### Deleting recurring payments: `Delete-RecurringPayment`
Deletes a recurring payment from the list of recurring payments.

Syntax: `Delete-RecurringPayment -r RECURRING_PAYMENT_INDEX`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `RECURRING_PAYMENT_INDEX` is an integer value.
</details>

Example of usage:

* `Delete-RecurringPayment -r 1`

<hr>

### Editing recurring payments: `Edit-RecurringPayment`
Edits a recurring payment in the list of recurring payments

Syntax: `Edit-RecurringPayment -r RECURRING_PAYMENT_INDEX [-n NAME] [-i INTERVAL] [-a AMOUNT] [-t DESCRIPTION]`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `RECURRING_PAYMENT_INDEX` is an integer value.
* `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `INTERVAL` is an integer value. Set this value to the estimated number of days between your recurring payments.
* `AMOUNT` is a decimal value.
</details>

Example of usage:

* `Edit-RecurringPayment -r 1 -a 20.00`
* `Edit-RecurringPayment -r 1 -n "Mobile Plan" -i 30 -a 20.00 -t "Monthly payment for my mobile plan" -c Telecom -x SGD -p Card`

<hr>

### Paying recurring payments: `Pay-RecurringPayment`
Pays a recurring payment from the list of recurring payments.

Syntax: `Pay-RecurringPayment -r RECURRING_PAYMENT_INDEX [-d DATE_TIME]`

* `RECURRING_PAYMENT_INDEX` is an integer value.
* `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`. If this value is not provided, MoneyGoWhere will save the current date and time for you.

Example of usage:

* `Pay-RecurringPayment -r 1`

<hr>

## Handling your data
### Merging a data file: `Merge-File`
Merges save file from an external source given path to the save file (.xml)

Syntax: `Merge-File [-p PATH_STRING]`

<details>
<summary>⚠️️️️ Syntax Notes</summary>

* `RECURRING_PAYMENT_INDEX` is an integer value. If this argument is provided, MoneyGoWhere will only display the specified recurring payment.
</details>

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

<hr>

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: There will be a 'Memory' folder created in the same directory as your .jar file. 
To transfer your data, simply copy the whole folder over to the same directory as the .jar 
file in your other devices. Data will be saved automatically.

<hr>

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

<hr>
<br>