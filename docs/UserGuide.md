# MoneyGoWhere

## Introduction

MoneyGoWhere is a financial planner to help you manage your finances.

## Quick Start

1. Ensure that ```Java 11``` is installed on your system.
    1. Execute the command ```java --version``` in your terminal window.
    2. Verify that the version of Java installed is ```Java 11```.
2. Ensure that you have write permissions for the directory in which you are executing the program.

## Features 

### Adding an expense: `Add-Expense`
Adds a new expense to the list of expenses.

Syntax: `Add-Expense -n NAME -a AMOUNT [-d DATE] [-t DESCRIPTION] [-c CATEGORY]`

* `NAME`, `DESCRIPTION` and `CATEGORY` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `AMOUNT` is a decimal value.
* `DATE` is a text string in the format `"dd/MM/yyyy HHmm"`. If this value is not provided, MoneyGoWhere will save the current date and time for you.

Example of usage: 

* `Add-Expense -n Subscription -a 13.37`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359"`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment"`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses"`

### Viewing expense(s): `View-Expense`
Display the past expenses you have added.

Syntax: `View-Expense [-e EXPENSE_NUMBER]`

* `EXPENSE_NUMBER` is an integer value. If this argument is provided, MoneyGoWhere will only display the specified expense.

Example of usage:

* `View-Expense`
* `View-Expense -e 1`

### Deleting an expense: `Delete-Expense`
Deletes an expense from the list of expenses.

Syntax: `Delete-Expense -e EXPENSE_NUMBER`

* `EXPENSE_NUMBER` is an integer value.

Example of usage:

* `Delete-Expense -e 1`

### Editing an expense: `Edit-Expense`
Edits an existing expense in the list of expenses.

Syntax: `Edit-Expense -e EXPENSE_NUMBER [-n NAME] [-a AMOUNT] [-d DATE] [-t DESCRIPTION] [-c CATEGORY]`

* `EXPENSE_NUMBER` is an integer value.
* `NAME`, `DESCRIPTION` and `CATEGORY` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `AMOUNT` is a decimal value.
* `DATE` is a text string in the format `"dd/MM/yyyy HHmm"`.

Example of usage:

* `Edit-Expense -e 1 -n Subscription -a 13.37`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359"`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment"`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses"`

### Sorting expenses: `Sort-Expense`
Sorts the list of expenses according to an alphabetical, amount or date order. It can be sorted in both ascending and
descending order.

Syntax: `Sort-Expense -t TYPE -o ORDER`

* `TYPE` is a text string. It can be either `alphabetical`, `amount` or `date`.
* `ORDER` is a text string. It can be either `ascending` or `descending`.

Example of usage:
* `Sort-Expense -t date -o ascending`
* `Sort-Expense -t amount -o descending`
* `Sort-Expense -t alphabetical -o ascending`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: There will be a 'Memory' folder created in the same directory as your .jar file. 
To transfer your data, simply copy the whole folder over to the same directory as the .jar 
file in your other devices. Date will be saved automatically.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add expense: `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses"`
* View expense: `View-Expense`
* Delete expense: `Delete-Expense -e 1`
* Edit expense: `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses"`
* Sort expense: `Sort-Expense -t alphabetical -o ascending"`
