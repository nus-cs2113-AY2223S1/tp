# MoneyGoWhere
<p align="center"><img alt="icon" src="https://raw.githubusercontent.com/penguin-s/tp/branch-User-Guide/docs/images/icon.png"></p>

## Contents
* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Understanding the Command Syntax](#understanding-the-command-syntax)
* [Features](#features)
   * [Managing Your Expenses](#managing-your-expenses)
   * [Managing Your Recurring Payments](#managing-your-recurring-payments)
   * [Managing Your Incomes](#managing-your-incomes)
   * [Managing Your Targets](#managing-your-targets)
   * [Handling Your Data](#handling-your-data)
   * [Listing all available commands: `help`](#listing-all-available-commands-help)
   * [Exiting the application: `bye`](#exiting-the-application-bye)
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

## Understanding the Command Syntax

* Arguments given without square brackets denote **mandatory** arguments.
* Arguments given with square brackets denote **optional** arguments.
* Argument values with spaces should be enclosed with double quotes.
* Argument values with a double quote can be escaped by prepending an additional double quote.
* Argument values cannot start with a hyphen.

### Example: `Add-Expense`

Syntax: `Add-Expense -n NAME -a AMOUNT [-d DATE_TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]`

Examples of valid command syntax:
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`
* `Add-Expense -n Subscription -a 13.37`

Examples of invalid command syntax:
* `Add-Expense -n Subscription`
* `Add-Expense -a 13.37`

## Features

### Listing all available commands: `help`
Lists all valid commands for MoneyGoWhere.

Syntax: `help`

Example of usage:
* `help`

Expected output: 
```
---- EXPENSE-RELATED-COMMANDS ----
Add an expense: Add-Expense -n NAME -a AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]
View your expenses: View-Expense [-e EXPENSE-INDEX] [-c EXPENSE-CATEGORY] [-n EXPENSE-NAME]
Delete an expense: Delete-Expense -e EXPENSE-INDEX
Edit an expense: Edit-Expense -e EXPENSE-INDEX [-n NAME] [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-a AMOUNT] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]
Sort your expenses: Sort-Expense -t Alphabetical/Amount/Date/Currency -o Ascending/Descending
Convert currency of an expense: Convert-Currency -e EXPENSE-INDEX -x CURRENCY [-r RATE]

---- RECURRING-PAYMENT-RELATED-COMMANDS ----
Add a recurring payment: Add-RecurringPayment -n NAME -i INTERVAL -a AMOUNT [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY]
View your recurring payments: View-RecurringPayment [-r RECURRINGPAYMENT-INDEX]
Delete a recurring payment: Delete-RecurringPayment -r RECURRINGPAYMENT-INDEX
Edit a recurring payment: Edit-RecurringPayment -r RECURRINGPAYMENT-INDEX [-n NAME] [-i INTERVAL] [-a AMOUNT] [-t DESCRIPTION] [-c CATEGORY] [-x CURRENCY]
Pay a recurring payment: Pay-RecurringPayment -r RECURRINGPAYMENT-INDEX

---- INCOME-RELATED-COMMANDS ----
Add an income: Add-Income -n NAME -a AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION]
View your incomes: View-Income [-e INCOME-INDEX]
Delete an income: Delete-Income -e INCOME-INDEX
Edit an income: Edit-Income -e INCOME-INDEX [-n NAME] [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-a AMOUNT]

---- TARGET-RELATED-COMMANDS ----
Add an target: Add-Target -n NAME -a AMOUNT -c CURRENT-AMOUNT [-d dd/MM/yyyy HHmm] [-t DESCRIPTION]
View your targets: View-Target [-e TARGET-INDEX]
Delete a target: Delete-Target -e TARGET-INDEX
Edit a target: Edit-Target -e TARGET-INDEX [-n NAME] [-d dd/MM/yyyy HHmm] [-t DESCRIPTION] [-a AMOUNT] [-c CURRENT-AMOUNT]
```

## Managing your expenses
### Adding an expense: `Add-Expense`
Adds a new expense to the list of expenses.

Syntax: `Add-Expense -n NAME -a AMOUNT [-d DATE_TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]`

> ⚠️️️️ Syntax Notes
> * `NAME`, `DESCRIPTION`, `CATEGORY`, `REMARKS` and `MODE OF PAYMENT` are text strings. You may use spaces within the text if you wrap the text with double quotes.
> * `CURRENCY` is a text string. It must be a valid currency code.
> * `AMOUNT` is a decimal value. The value should be greater than 0.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.


Examples of usage: 
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`
* `Add-Expense -n Subscription -a 13.37`

<hr>

### Viewing expense(s): `View-Expense`
Displays past expenses you have added.

Syntax: `View-Expense [-e EXPENSE_NUMBER]`

> ⚠️️️️ Syntax Notes
> * `EXPENSE_NUMBER` is an integer value. This value should be equal to or greater than 0.
> * If this argument is provided, MoneyGoWhere will only display the specified expense.

Example of usage:
* `View-Expense`
* `View-Expense -e 1`

<hr>

### Deleting an expense: `Delete-Expense`
Deletes an expense from the list of expenses.

Syntax: `Delete-Expense -e EXPENSE_NUMBER`

> ⚠️️️️ Syntax Notes
> * `EXPENSE_NUMBER` is an integer value. This value should be equal to or greater than 0.

Example of usage:
* `Delete-Expense -e 1`

<hr>

### Editing an expense: `Edit-Expense`
Edits an existing expense in the list of expenses.

Syntax: `Edit-Expense -e EXPENSE_NUMBER [-n NAME] [-a AMOUNT] [-d DATE_TIME] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY] [-p MODE OF PAYMENT]`

> ⚠️️️️ Syntax Notes
> * `EXPENSE_NUMBER` is an integer value. This value should be equal to or greater than 0.
> * `NAME`, `DESCRIPTION`, `CATEGORY`, `REMARKS` and `MODE OF PAYMENT` are text strings. You may use spaces within the text if you wrap the text with double quotes.
> * `CURRENCY` is a text string. It must be a valid currency code.
> * `AMOUNT` is a decimal value. The value should be greater than 0.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

Example of usage:
* `Edit-Expense -e 1 -n Subscription -a 13.37`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD -p PayLah`

<hr>

### Sorting expenses: `Sort-Expense`
Sorts the list of expenses according to an alphabetical, amount, date or currency order. It can be sorted in both ascending and
descending order. 

Note:
1. By default, expenses are sorted in alphabetical order, from A to Z. Expenses are sorted **automatically** whenever a new expense is added, or if an existing expense is edited. 
2. The nature of this feature could cause the indexes of the expenses to change while running MoneyGoWhere. Please use `View-Expense` to get the most updated indexes of the expenses.

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
> * `EXPENSE_NUMBER` is an integer value. This value should be equal to or greater than 0.
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
> * `AMOUNT` is a decimal value. The value should be greater than 0.
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
> * `AMOUNT` is a decimal value. The value should be greater than 0.

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
> * `AMOUNT` is a decimal value. The value should be greater than 0.
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
> * `AMOUNT` is a decimal value. The value should be greater than 0.
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
> * `AMOUNT` and `CURRENT_AMOUNT` are decimal value. The values should be greater than 0.
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

Syntax: `Edit-Target -e TARGET_NUMBER [-n NAME] [-a AMOUNT] [-c CURRENT_AMOUNT] [-d DATE_TIME] [-t DESCRIPTION]`

> ⚠️️️️ Syntax Notes
> * `TARGET_NUMBER` is an integer value.
> * `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.
> * `AMOUNT` and `CURRENT_AMOUNT` is a decimal value. The values should be greater than 0.
> * `DATE_TIME` is a text string in the format `"dd/MM/yyyy HHmm"`.

Example of usage:
* `Edit-Target -e 1 -n "October Target" -a 100.00`
* `Edit-Target -e 1 -n "Monthly Target" -a 3000 -c 1200 -d "01/01/2022 2359" -t "Monthly payment"`

<hr>

## Handling your data
### Merging a data file: `Merge-File`
Merges save file from an external source given path to the .xml save file

Syntax: `Merge-File -p PATH_STRING` 

> ⚠️️️️ Syntax Notes
> * `PATH_STRING` is text string. If your path contains spaces, you would need to wrap the text with double quotes.


Example of usage:
* `Merge-File -p "C:\Users\the_d\Downloads\expenses.xml"`

<hr>

### Exiting the application: `bye`
Exits MoneyGoWhere.

Syntax: `bye`

Example of usage:
* `bye`

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

**A**:
As you enter data into MoneyGoWhere, it creates a **Memory** folder in the same directory as the *.jar* file.
You may use any of your preferred tool to transfer the **Memory/MoneyGoWhereData.xml** data file to another computer and run the [`Merge-File`](#merging-a-data-file-merge-file) command to merge the copied file.

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
* Add income: `Add-Income -n "Stocks" -a 500.00 -d "01/02/2022 2359" -t "Investment payouts"`
* View income: `View-Income -e 1`
* Delete income: `Delete-Income -e 1`
* Edit income: `Edit-Income -e 1 -n "Monthly Salary" -a 3000 -d "01/01/2022 2359" -t "Monthly payment"`
* Add target: `Add-Target -n "Food target" -a 1000.00 -c 1500.00 -d "01/02/2022 2359" -t "Money spent on food"`
* View target: `View-Target -e 1`
* Delete target: `Delete-Target -e 1`
* Edit target: `Edit-Target -e 1 -n "October Target" -a 100.00`
* Merge file: `Merge-File -p "C:\Users\the_d\Downloads\expenses.xml"`
* Help: `help`
* Exit: `bye`

