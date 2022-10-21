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

Syntax: `Add-Expense -n NAME -a AMOUNT [-d DATE] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY]`

* `NAME`, `DESCRIPTION`, `CATEGORY` and `REMARKS` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `CURRENCY` is a text string. By default, it will be SGD. 
* `AMOUNT` is a decimal value.
* `DATE` is a text string in the format `"dd/MM/yyyy HHmm"`. If this value is not provided, MoneyGoWhere will save the current date and time for you.

Example of usage: 

* `Add-Expense -n Subscription -a 13.37`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359"`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment"`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses"`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here"`
* `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD`

### Viewing expense(s): `View-Expense`
Displays the past expenses you have added.

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

Syntax: `Edit-Expense -e EXPENSE_NUMBER [-n NAME] [-a AMOUNT] [-d DATE] [-t DESCRIPTION] [-c CATEGORY] [-r REMARKS] [-x CURRENCY]`

* `EXPENSE_NUMBER` is an integer value.
* `NAME`, `DESCRIPTION`, `CATEGORY` and `REMARKS` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `CURRENCY` is a text string. 
* `AMOUNT` is a decimal value.
* `DATE` is a text string in the format `"dd/MM/yyyy HHmm"`.

Example of usage:

* `Edit-Expense -e 1 -n Subscription -a 13.37`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359"`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment"`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses"`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here"`
* `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD`

### Sorting expenses: `Sort-Expense`
Sorts the list of expenses according to an alphabetical, amount, date or currency order. It can be sorted in both ascending and
descending order. 

Syntax: `Sort-Expense -t TYPE -o ORDER`

* `TYPE` is a text string. It can be either `alphabetical`, `amount`, `date` or `currency`.
* `ORDER` is a text string. It can be either `ascending` or `descending`.

Example of usage:
* `Sort-Expense -t date -o ascending`
* `Sort-Expense -t amount -o descending`
* `Sort-Expense -t alphabetical -o ascending`
* `Sort-Expense -t currency -o descending`

### Converting currency of an expense: `Convert-Currency`
Converts the currency of an expense from the list of expenses.

Syntax: `Convert-Currency -e EXPENSE_NUMBER -x CURRENCY`

* `EXPENSE_NUMBER` is an integer value.
* `CURRENCY` is a text string.

Example of usage:

* `Convert-Currency -e 1 -x USD`

### Adding recurring payments: `Add-RecurringPayment`
Adds a recurring payment to the list of recurring payments

Syntax: `Add-RecurringPayment -n NAME -i INTERVAL -a AMOUNT [-t DESCRIPTION]`

* `NAME` and `DESCRIPTION` are text strings. You may use spaces within the text if you wrap the text with double quotes.
* `INTERVAL` is an integer value. Set this value to the estimated number of days between your recurring payments.
* `AMOUNT` is a decimal value.

Example of usage:
* `Add-RecurringPayment -n "Mobile Plan" -i 30 -a 20.00`
* `Add-RecurringPayment -n "Mobile Plan" -i 30 -a 20.00 -t "Monthly payment for my mobile plan"`

### Viewing recurring payments: `View-RecurringPayment`
Displays the past recurring payments you have added.

Syntax: `View-RecurringPayment [-r RECURRING_PAYMENT_INDEX]`

* `RECURRING_PAYMENT_INDEX` is an integer value. If this argument is provided, MoneyGoWhere will only display the specified recurring payment.

Example of usage:

* `View-RecurringPayment`
* `View-RecurringPayment -r 1`

### Deleting recurring payments: `Delete-RecurringPayment`
Deletes a recurring payment from the list of recurring payments.

Syntax: `Delete-RecurringPayment -r RECURRING_PAYMENT_INDEX`

* `RECURRING_PAYMENT_INDEX` is an integer value.

Example of usage:

* `Delete-RecurringPayment -r 1`

### Merge file: `Merge-File`
Merge save file from an external source given path to the save file (.xml)

Syntax: `Merge-File [-p PATH_STRING]`

* `RECURRING_PAYMENT_INDEX` is an integer value. If this argument is provided, MoneyGoWhere will only display the specified recurring payment.

Example of usage:

* `merge-file -p "C:\Users\the_d\Downloads\expenses.xml"`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: There will be a 'Memory' folder created in the same directory as your .jar file. 
To transfer your data, simply copy the whole folder over to the same directory as the .jar 
file in your other devices. Date will be saved automatically.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add expense: `Add-Expense -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD`
* View expense: `View-Expense`
* Delete expense: `Delete-Expense -e 1`
* Edit expense: `Edit-Expense -e 1 -n "Cloud subscription" -a 13.37 -d "01/01/2022 2359" -t "Monthly payment" -c "Work expenses" -r "Remarks here" -x USD`
* Sort expense: `Sort-Expense -t alphabetical -o ascending"`
* Convert currency: `Convert-Currency -e 1 -x USD`
