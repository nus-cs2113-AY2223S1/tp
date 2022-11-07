# Developer Guide

<p align="center"><img alt="icon" width="256" src="https://raw.githubusercontent.com/AY2223S1-CS2113T-W11-1/tp/master/docs/images/icon.png"></p>

## Contents
* [Introduction](#introduction)
* [Acknowledgements](#acknowledgements)
* [Getting Started](#getting-started)
* [Design](#design)
  * [Software Architecture](#software-architecture)
  * [Core Components](#core-components)
  * [Component Interactions](#component-interactions)
* [Implementation](#implementation)
* [Product Scope](#product-scope)
    * [Target User Profile](#target-user-profile)
    * [Value Proposition](#value-proposition)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)
* [Manual Testing](#manual-testing)

<br>
<br>

<div style="page-break-after: always;"></div>

## Introduction

MoneyGoWhere is a financial tracker designed to help computing professionals manage their finances.
This developer guide explains the design considerations and implementation details required for future developers to work on MoneyGoWhere.

<br>

[Back to Contents](#contents)

<br>
<br>

## Acknowledgements

We would like to acknowledge the following sources from which our team has referenced during the development of MoneyGoWhere:
* [AddressBook-Level3's User Guide and Developer](https://se-education.org/addressbook-level3/)
* [Apache Commons CLI Library](https://commons.apache.org/proper/commons-cli/)
* [Apache Commons Text Library](https://commons.apache.org/proper/commons-text/)

<br>

[Back to Contents](#contents)

<br>
<br>

<div style="page-break-after: always;"></div>

## Getting Started

Our team developed MoneyGoWhere using Java JDK 11 and Intellij IDEA.
The following steps will guide you through the setup process to get your development environment up and running.
1. Install **JDK 11**.
2. Install **Intellij IDEA**.
3. Fork the [MoneyGoWhere](https://github.com/AY2223S1-CS2113T-W11-1/tp) repository and clone it to your system.
4. Launch Intellij IDEA and open the repository you have cloned.
5. Navigate to `File > Project Structure > Project Settings > Project` and set `SDK` to `11`.
6. Verify the setup process by running `MoneyGoWhere#main()`.
   You should see the following greeting message if the project setup is successful:

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

[Back to Contents](#contents)

<br>
<br>

<div style="page-break-after: always;"></div>

## Design

## Sub-Contents
* [Software Architecture](#software-architecture)
* [Core Components](#core-components)
  * [Component Interactions](#component-interactions)
    * [General Interaction of Components](#general-interaction-of-components)
    * [Component Interaction Reference Diagrams](#component-interaction-reference-diagrams)
  * [Common Component](#common-component)
  * [Exceptions Component](#exceptions-component)
  * [UserInterface Component](#userinterface-component)
  * [Parser Component](#parser-component)
  * [Commands Component](#commands-component)
  * [Data Component](#data-component)
  * [Storage Component](#storage-component)
  * [API Component](#api-component)
  * [Logger Component](#logger-component)
* [Back to Contents](#contents)

<br>
<br>

<div style="page-break-after: always;"></div>

### Software Architecture
The software architecture diagram below describes the program's design and the interaction between components.

<br>

![Software-Architecture](images/SoftwareArchitecture.png)

<br>

[Back to Sub-Contents](#design)

<br>
<br>

<div style="page-break-after: always;"></div>

### Core Components

| Component     | Function                                                                 |
|---------------|--------------------------------------------------------------------------|
| MoneyGoWhere  | Defines the main entrypoint of the program.                              |
| Common        | Defines various parameters used by the program.                          |
| Exceptions    | Defines the exceptions thrown by the program.                            |
| UserInterface | Interface with the user via standard I/O and handle commands.            |
| Parser        | Provides functions to parse inputs read from standard input.             |
| Command       | Defines the commands accepted by the program along with its arguments.   |
| Data          | Defines classes to store data and provides functions to operate on data. |
| Storage       | Provides functions to save and load data.                                |
| API           | Provides functions to interact with external APIs.                       |
| Logger        | Provides functions to log user actions and the program's behaviour.      |

<br>

[Back to Sub-Contents](#design)

<br>
<br>

<div style="page-break-after: always;"></div>

### Component Interactions

### Sub-Contents

* [General Interaction of Components](#general-interaction-of-components)
* [Component Interaction Reference Diagrams](#component-interaction-reference-diagrams)
  * [Loading data](#loading-data)
  * [Getting user commands](#getting-user-commands)
  * [Printing the output from commands](#printing-the-output-from-a-command)
  * [Saving data](#saving-data)
* [Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### General Interaction of Components
To illustrate how the components interact with each other as a whole, we will use the following example where:

The user launches the program and enters the command `Add-Expense -n Expense -a 7.80` to add an expense with the name `Expense` and the amount `7.80`.

<br>

The sequence diagrams referenced by the component interaction diagram [below](#component-interaction-reference-diagrams).

<br>

![ComponentInteractionsOnCommandEntered](images/ComponentInteractionsOnCommandEntered.png)

*A sequence diagram showing the interaction between core components of MoneyGoWhere when Add-Expense is executed.*

<br>

1. When the user launches the program, `MoneyGoWhere` creates an instance of `UserInterface`.
2. `UserInterface` creates instances of `Logger` and `Data`.
3. `MoneyGoWhere` calls `UserInterface#run()` to start the interface between the program and the user.
4. `UserInterface#run()` calls `UserInterface#runLocalStorageLoadFromFile()` to load data from local storage.
5. `UserInterface#run()` will execute continuously in a loop until the user enters the command `Bye`.
6. `UserInterface#run()` calls `UserInterface#getConsoleCommand()` to read and parse the user's input.
7. `UserInterface#run()` will then call the corresponding command handler function based on the user's input.

<br>

In the example above, `consoleCommand` is an instance of `ConsoleCommandAddExpense` and hence,
  `UserInterface#runCommandAddExpense()` is called.
* When the command handler function is called, it calls `Data` functions to perform operations on data.
  In the example above, `UserInterface#runCommandAddExpense()` calls `Data#addExpense()` to add an expense to the program.
* After the operations are performed, command handler functions calls `UserInterface#runLocalStorageSaveToFile()` functions to save data to local storage.

<br>

[Back to Sub-Contents](#component-interactions)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Component Interaction Reference Diagrams

<br>

##### Loading data

![ComponentInteractionsSDLoadDataFromFile](images/ComponentInteractionsSDLoadDataFromFile.png)

*A reference diagram showing the interactions between the UI, Data and Storage components when data is loaded into MoneyGoWhere.*

<br>

1. `UserInterface#runLocalStorageLoadFromFile()` calls `Storage#loadFromFile()` to read any existing data from local storage.
2. `UserInterface#runLocalStorageLoadFromFile()` calls the corresponding set functions to load the data into the program.

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

##### Getting user commands

![ComponentInteractionsSDGetUserCommand](images/ComponentInteractionsSDGetUserCommand.png)

*A reference diagram showing the interactions between the UI, Parser and Logger components when the user's input is retrieved.*


1. `UserInterface#getConsoleCommand()` calls `UserInterface#getConsoleInput()` to read the user's input as a string.
2. `UserInterface#getConsoleCommand()` calls `Logger#logCommand()` to log the command entered by the user into a log file.
3. `UserInterface#getConsoleCommand()` calls `Parser#parse()` to parse the input string into the corresponding console command object.

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

##### Printing the output from a command

![ComponentInteractionsSDPrintingAnExpense](images/ComponentInteractionsSDPrintingAnExpense.png)

*A reference diagram showing the interactions between the UI, Parser and Logger components when the output of Add-Expense is printed.*

<br>

1. `UserInterface` calls the corresponding convert object function based on the data object's class.\
   In the example above, `UserInterface` calls `UserInterface#convertExpenseToConsoleString()` to convert the expense object into a formatted string.
2. `UserInterface` calls `UserInterface#printInformationalMessage()` to print the formatted string.
3. `UserInterface#printInformationalMessage()` calls `System.out.println()` to print the formatted string to a standard out.
4. `UserInterface#printInformationalMessage()` calls `Logger#logInformationalMessage()` to log the formatted string to a log file.

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

##### Saving data

![ComponentInteractionsSDSaveDataToFile](images/ComponentInteractionsSDSaveDataToFile.png)

*A reference diagram showing the interactions between the UI, Data and Storage components when data is saved in MoneyGoWhere.*

<br>

* `UserInterface#runLocalStorageSaveToFile()` calls the get functions to retrieve the data from the program.
* `UserInterface#runLocalStorageSaveToFile()` calls `Storage#saveToFile()` to write the data to local storage.

<br>

[Back to Sub-Contents](#component-interactions)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Common Component

The Common component consists of the classes `Messages` and `Configurations`.\
\
`Messages` defines the messages used by the program during execution. It includes the informational, warning and error messages that are displayed to the user.

`Configurations` defines the configuration parameters used by the program. It stores parameters such as data formatting information, directory and file paths, and the URLs of different APIs.

<br>

[Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Exceptions Component

The Exceptions component consists of various exception classes which inherits from `MoneyGoWhereException`.

<br>

![Component-Exceptions](images/ComponentExceptions.png)

*A reference diagram showing exception classes inherited from MoneyGoWhereException.*

<br>

The exceptions are thrown and handled by the program depending on the conditions outlined in their Javadoc comments.\
For example, `ConsoleParserCommandAddExpenseInvalidException` is thrown when an error is encountered while parsing the command.
Do refer to the [exceptions](https://github.com/AY2223S1-CS2113T-W11-1/tp/tree/master/src/main/java/seedu/moneygowhere/exceptions) package to view the full list of exceptions.

<br>

[Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### UserInterface Component

The UserInterface component consists of the class `ConsoleInterface` which runs the command line interface that the user interacts with.

<br>

![Component-UserInterface](images/ComponentUserInterface.png)

*A reference diagram showing the interactions between ConsoleInterface and other components of MoneyGoWhere.*


<br>

`ConsoleInterface` defines various command handlers which are called based on the command entered by the user.

For example, entering the command string `Add-Expense -n Expense -a 7.80` will result in the execution of the `ConsoleInterface#runCommandAddExpense()` command handler to add an expense to the program.

<br>

*To view the full list of command handler functions, do refer to [ConsoleInterface.java](https://github.com/AY2223S1-CS2113T-W11-1/tp/blob/master/src/main/java/seedu/moneygowhere/userinterface/ConsoleInterface.java).*

<br>

[Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Parser Component

The Parser component consists of the classes `ConsoleParserConfigurations` and `ConsoleParser`.

<br>

`ConsoleParserConfigurations` defines the syntax and arguments used by `ConsoleParser` when parsing the commands entered by the user.

`ConsoleParser` defines the `ConsoleParser#parse()` to parse commands and arguments into their corresponding console command subclasses.

<br>

When `ConsoleParser#parse()` is invoked to parse a command entered by the user:
* `ConsoleParser#parse()` calls the corresponding command parser function to parse the command arguments.
* The command parser function validates, parses and normalizes the command arguments before returning the corresponding console command subclass.

<br>

[Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Commands Component

The Commands component consists of various console command classes which inherits from the abstract class `ConsoleCommand`.

<br>

![Component-Commands](images/ComponentCommands.png)

*A reference diagram showing console command classes inherited from ConsoleCommand.*

<br>

The corresponding console command subclass is returned by `ConsoleParser#parse()` depending on the command supplied in the function's parameter.

For example, supplying the command string `Add-Expense -n Expense -a 7.80` to `ConsoleParser#parse()` will return a `ConsoleCommandAddExpense` object.

<br>

*To view the full list of console command subclasses, do refer to the [commands package](https://github.com/AY2223S1-CS2113T-W11-1/tp/tree/master/src/main/java/seedu/moneygowhere/commands).*

<br>

[Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Data Component

The Data component consists of the classes `Expense`, `Income`, `RecurringPayment`, `Target` along with their corresponding manager classes.

<br>

![Component-Data](images/ComponentData.png)

*A reference diagram showing the interaction between each class and their respective manager class.*

<br>

`Expense`, `Income`, `RecurringPayment` and `Target` define the attributes and their respective data types.

`ExpenseManager`, `IncomeManager`, `RecurringPaymentManager` and `TargetManager` define functions to store and manage the respective data objects.

`CurrencyManager` defines functions to add, retrieve, update and delete currencies and manages a HashMap of different currency codes and their corresponding exchange rates.

<br>

[Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Storage Component

The Storage component consists of the classes `LocalStorageConfigurations`, `LocalStorageNullErrorHandler` and `LocalStorage`.

<br>

`LocalStorageConfigurations` defines the syntax and arguments used by `LocalStorage` when reading data from and writing data to a xml file.

`LocalStorageNullErrorHandler` overwrites several default error handlers in the xml parser to allow for custom error handling.

`LocalStorage` defines functions that loads and saves data.

<br>

[Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### API Component

The API component consists of the classes `CurrencyApiManager` and `CurrencyApi`.

<br>

The `CurrencyApiManager` class calls functions in `CurrencyApi`.

The `CurrencyApi` class contains functions that fetches data from the API and loads that data into a HashMap of exchange rates.\

<br>

When `CurrencyApiManager#getCurrencyApi()` is invoked to retrieve the list of currencies and their corresponding conversion rates:
* `CurrencyApiManager#getCurrencyApi()` calls `CurrencyApi#getJson()` to fetch data from the API in the form of a JSON file.
    * The data contains the currency codes and its corresponding exchange rates with respect to the Singapore Dollar.
    * The data is written into a text file called `exchangeRates.txt`.
* `CurrencyApiManager#getCurrencyApi()` calls `CurrencyApi#loadFromFile()` to load the data from `exchangeRates.txt` into a HashMap of exchange rates.

<br>

[Back to Sub-Contents](#design)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Logger Component

The Logger component consists of the classes `LocalLoggerFormatter` and `LocalLogger`.

<br>

`LocalLoggerFormatter` formats the log record before it is written to a log file.

`LocalLogger` defines functions to log different types of messages to a log file.

<br>

[Back to Sub-Contents](#design)

<br>
<br>

<div style="page-break-after: always;"></div>

## Implementation

## Sub-Contents
* [Handling data](#handling-data)
* [Expense commands](#expense-commands)
* [Recurring payment commands](#recurring-payment-commands)
* [Income commands](#income-commands)
* [Target commands](#target-commands)
* [Back to Contents](#contents)

<br>
<hr>
<br>

### Handling data

### Sub-Contents
* [Loading data from file](#loading-data-from-file)
* [Saving data to file](#saving-data-to-file)
* [Merging data files](#merging-data-files-merge-file)
* [Back to Sub-Contents](#implementation)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Loading data from file

The following sequence diagram illustrates the process of loading data from local storage.

<br>

![Implementation-SD-Load-Data](images/ImplementationSDLoadData.png)

*A sequence diagram showing the interactions between ConsoleInterface and the manager classes when data is loaded into MoneyGoWhere.*

<br>

[Back to Sub-Contents](#handling-data)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Saving data to file

The following sequence diagram illustrates the process of saving data to local storage.

<br>

![Implementation-SD-Save-Data](images/ImplementationSDSaveData.png)

*A sequence diagram showing the interactions between ConsoleInterface and manager classes when data is saved in MoneyGoWhere.*

<br>

[Back to Sub-Contents](#handling-data)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Merging data files: `Merge-File`

The `Merge-File` command merges data from an external data file with the existing data in the current data file.

<br>

![Implementation-Merge-File](images/ImplementationMergeFile.png)

*A sequence diagram showing the interactions between ConsoleInterface and manager classes when data from an external file is merged into the current data file.*

<br>

[Back to Sub-Contents](#handling-data)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Expense commands

### Sub-Contents
* [Printing an expense](#printing-an-expense)
* [Adding an expense: `Add-Expense`](#adding-an-expense-add-expense)
* [Viewing an expense: `View-Expense`](#viewing-an-expense-view-expense)
* [Deleting an expense: `Delete-Expense`](#deleting-an-expense-delete-expense)
* [Editing an expense: `Edit-Expense`](#editing-an-expense-edit-expense)
* [Sorting expenses: `Sort-Expense`](#sorting-expenses-sort-expense)
* [Converting currencies: `Convert-Currency`](#converting-currencies-convert-currency)
* [Back to Sub-Contents](#implementation)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Printing an expense

The following sequence diagram illustrates the process of printing and logging an `Expense` object.

<br>

![Implementation-SD-Print-Expense](images/ImplementationSDPrintExpense.png)

*A sequence diagram showing the interactions between ConsoleInterface and Logger when an expense is printed.*

<br>

[Back to Sub-Contents](#expense-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Adding an expense: `Add-Expense`

The `Add-Expense` command adds a new expense to the program.

<br>

![Implementation-Add-Expense](images/ImplementationAddExpense.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when an expense is added.*

<br>

[Back to Sub-Contents](#expense-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Viewing an expense: `View-Expense`

The `View-Expense` command displays existing expenses in the program.

<br>

![Implementation-View-Expense](images/ImplementationViewExpense.png)

*A sequence diagram showing the interactions between ConsoleInterface and ExpenseManager when expenses are printed as a list.*

<br>

[Back to Sub-Contents](#expense-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Deleting an expense: `Delete-Expense`

The `Delete-Expense` command removes an existing expense from the program.

<br>

![Implementation-Delete-Expense](images/ImplementationDeleteExpense.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when an expense is deleted.*

<br>

[Back to Sub-Contents](#expense-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Editing an expense: `Edit-Expense`

The `Edit-Expense` command changes the attributes of an existing expense in the program.

<br>

![Implementation-Edit-Expense](images/ImplementationEditExpense.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when an expense is edited.*

<br>

[Back to Sub-Contents](#expense-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Sorting expenses: `Sort-Expense`

The `Sort-Expense` command sorts the existing expenses in the program.

<br>

![Implementation-Sort-Expense](images/ImplementationSortExpense.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when expenses are sorted.*

<br>

[Back to Sub-Contents](#expense-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Converting currencies: `Convert-Currency`

The `Convert-Currency` command converts the currency of an existing expense in the program.

<br>

![Implementation-Convert-Currency](images/ImplementationConvertCurrency.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when the currency of an expense is converted.*

<br>

[Back to Sub-Contents](#expense-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Recurring payment commands

### Sub-Contents
* [Printing a recurring payment](#printing-a-recurring-payment)
* [Adding a recurring payment: `Add-RecurringPayment`](#adding-a-recurring-payment-add-recurringpayment)
* [Viewing recurring payment(s): `View-RecurringPayment`](#viewing-a-recurring-payment-view-recurringpayment)
* [Deleting a recurring payment: `Delete-RecurringPayment`](#deleting-a-recurring-payment-delete-recurringpayment)
* [Editing a recurring payment: `Edit-RecurringPayment`](#editing-a-recurring-payment-edit-recurringpayment)
* [Adding an expense from a recurring payment: `Pay-RecurringPayment`](#adding-an-expense-from-a-recurring-payment-pay-recurringpayment)
* [Back to Sub-Contents](#implementation)

<br>
<hr>
<br>

#### Printing a recurring payment

The following sequence diagram illustrates the process of printing and logging a `RecurringPayment` object.

![Implementation-SD-Print-RecurringPayment](images/ImplementationSDPrintRecurringPayment.png)

*A sequence diagram showing the interactions between ConsoleInterface and Logger when a recurring payment is printed.*

<br>

[Back to Sub-Contents](#recurring-payment-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Adding a recurring payment: `Add-RecurringPayment`

The `Add-RecurringPayment` command adds a new recurring payment to the program.

<br>

![Implementation-Add-RecurringPayment](images/ImplementationAddRecurringPayment.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when a recurring payment is added.*

<br>

[Back to Sub-Contents](#recurring-payment-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Viewing a recurring payment: `View-RecurringPayment`

The `View-RecurringPayment` command displays existing recurring payments in the program.

<br>

![Implementation-View-RecurringPayment](images/ImplementationViewRecurringPayment.png)

*A sequence diagram showing the interactions between ConsoleInterface and RecurringPaymentManager when recurring payments are printed as a list.*

<br>

[Back to Sub-Contents](#recurring-payment-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Deleting a recurring payment: `Delete-RecurringPayment`

The `Delete-RecurringPayment` command removes an existing recurring payment from the program.

<br>

![Implementation-Delete-RecurringPayment](images/ImplementationDeleteRecurringPayment.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when a recurring payment is deleted.*

<br>

[Back to Sub-Contents](#recurring-payment-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Editing a recurring payment: `Edit-RecurringPayment`

The `Edit-RecurringPayment` command changes the attributes of an existing recurring payment in the program.

<br>

![Implementation-Edit-RecurringPayment](images/ImplementationEditRecurringPayment.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when a recurring payment is edited.*

<br>

[Back to Sub-Contents](#recurring-payment-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Adding an expense from a recurring payment: `Pay-RecurringPayment`

<br>

The `Pay-RecurringPayment` command adds a new expense based on an existing recurring payment.
This command helps the user to track when recurring payments are paid.

![Implementation-Edit-RecurringPayment](images/ImplementationPayRecurringPayment.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when a recurring payment is paid.*

<br>

[Back to Sub-Contents](#recurring-payment-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Income commands

### Sub-Contents
* [Printing an income](#printing-an-income)
* [Adding an income: `Add-Income`](#adding-an-income-add-income)
* [Viewing incomes: `View-Income`](#viewing-an-income-view-income)
* [Deleting an income: `Delete-Income`](#deleting-an-income-delete-income)
* [Editing an income: `Edit-Income`](#editing-an-income-edit-income)
* [Back to Sub-Contents](#implementation)

<br>
<hr>
<br>

#### Printing an income

The following sequence diagram illustrates the process of printing and logging an `Income` object.

<br>

![Implementation-SD-Print-Income](images/ImplementationSDPrintIncome.png)

*A sequence diagram showing the interactions between ConsoleInterface and Logger when an income is printed.*

<br>

[Back to Sub-Contents](#income-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Adding an income: `Add-Income`

The `Add-Income` command adds a new income to the program.

<br>

![Implementation-Add-Income](images/ImplementationAddIncome.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when an income is added.*

<br>

[Back to Sub-Contents](#income-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Viewing an income: `View-Income`

The `View-Income` command displays existing incomes in the program.

<br>

![Implementation-View-Income](images/ImplementationViewIncome.png)

*A sequence diagram showing the interactions between ConsoleInterface and IncomeManager when incomes are printed as a list.*

<br>

[Back to Sub-Contents](#income-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Deleting an income: `Delete-Income`

The `Delete-Income` command removes an existing income from the program.

<br>

![Implementation-Delete-Income](images/ImplementationDeleteIncome.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when an income is deleted.*

<br>

[Back to Sub-Contents](#income-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Editing an income: `Edit-Income`

The `Edit-Income` command changes the attributes of an existing income in the program.

<br>

![Implementation-edit-income](images/ImplementationEditIncome.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when an income is edited.*

<br>

[Back to Sub-Contents](#income-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

### Target commands

### Sub-Contents
* [Printing a target](#printing-a-target)
* [Adding a target: `Add-Target`](#adding-a-target-add-target)
* [Viewing target(s): `View-Target`](#viewing-a-target-view-target)
* [Deleting a target: `Delete-Target`](#deleting-a-target-delete-target)
* [Editing a target: `Edit-Target`](#editing-a-target-edit-target)
* [Back to Sub-Contents](#implementation)

<br>
<hr>
<br>

#### Printing a target

The following sequence diagram illustrates the process of printing and logging a `Target` object.

<br>

![Implementation-SD-Print-Target](images/ImplementationSDPrintTarget.png)

*A sequence diagram showing the interactions between ConsoleInterface and Logger when a target is printed.*


<br>

[Back to Sub-Contents](#target-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Adding a target: `Add-Target`

The `Add-Target` command adds a new target to the program.

<br>

![Implementation-add-target](images/ImplementationAddTarget.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when a target is added.*

<br>

[Back to Sub-Contents](#target-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Viewing a target: `View-Target`

The `View-Target` command displays existing targets in the program.

<br>

![Implementation-View-Target](images/ImplementationViewTarget.png)

*A sequence diagram showing the interactions between ConsoleInterface and TargetManager when targets are printed as a list.*

<br>

[Back to Sub-Contents](#target-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Deleting a target: `Delete-Target`

The `Delete-Target` command removes an existing target from the program.

<br>

![Implementation-Delete-Target](images/ImplementationDeleteTarget.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when a target is deleted.*

<br>

[Back to Sub-Contents](#target-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

#### Editing a target: `Edit-Target`

<br>

The `Edit-Target` command changes the attributes of an existing target in the program.

![Implementation-Edit-Target](images/ImplementationEditTarget.png)

*A sequence diagram showing the interactions between ConsoleInterface and other classes when a target is edited.*

<br>

[Back to Sub-Contents](#target-commands)

<br>
<hr>
<br>

<div style="page-break-after: always;"></div>

## Product Scope

## Sub-Contents
* [Target User Profile](#target-user-profile)
* [Value Proposition](#value-proposition)
* [Back to Contents](#contents)

<br>
<hr>
<br>

### Target User Profile

MoneyGoWhere is targeted at users who
* Prefer command line interfaces to graphical interfaces
* Use multiple operating systems concurrently
* Frequently type quickly

<br>

[Back to Sub-Contents](#product-scope)

<br>
<hr>
<br>

### Value Proposition

MoneyGoWhere is a financial tracker designed specifically to help computing professionals manage their finances. The program
* Runs on mainstream desktop operating system
* Works over remote command shells such as `ssh`, allowing easy access over the internet
* Stores data using the Extensible Markup Language, simplifying the transfer and backup of data using tools such as `scp` or `rsync`

<br>

[Back to Sub-Contents](#product-scope)

<br>
<br>
<div style="page-break-after: always;"></div>

## User Stories

| User story was implemented in... | As a ... | I want to ...                                     | So that I can ...                                 |
|:--------------------------------:|:--------:|---------------------------------------------------|---------------------------------------------------|
|               v1.0               |   user   | add expenses                                      | keep track of my finances                         |
|               v1.0               |   user   | view expenses                                     | keep track of my finances                         |
|               v1.0               |   user   | view expenses by name                             | search for past expenses easily                   |
|               v1.0               |   user   | view expenses by category                         | keep track of my spending across different areas  |
|               v1.0               |   user   | delete expenses                                   | keep track of my finances                         |
|               v1.0               |   user   | edit expenses                                     | keep track of my finances                         |
|               v1.0               |   user   | categorise expenses                               | keep track of my spending across different areas  |
|               v1.0               |   user   | sort expenses by alphabetical order               | organise my spending by their names               |
|               v1.0               |   user   | sort expenses by amount                           | keep track of the extent of my spending           |
|               v1.0               |   user   | sort expenses by date                             | keep track of my spending over time               |
|               v1.0               |   user   | save my data to a file                            | store my data easily                              |
|               v1.0               |   user   | load my data from a file                          | retrieve my data easily                           |
|               v1.0               |   user   | add my income                                     | keep track if my spending exceeds my income       |
|               v1.0               |   user   | add expense targets                               | keep track of my financial goals                  |
|               v2.0               |   user   | convert between different currencies              | keep track of expenses across multiple currencies |
|               v2.0               |   user   | update currency exchange rates                    | convert between currencies using the latest rates |
|               v2.0               |   user   | view my income                                    | keep track if my spending exceeds my income       |
|               v2.0               |   user   | delete my income                                  | keep track if my spending exceeds my income       |
|               v2.0               |   user   | edit my income                                    | keep track if my spending exceeds my income       |
|               v2.0               |   user   | view expense targets                              | keep track of my financial goals                  |
|               v2.0               |   user   | delete expense targets                            | keep track of my financial goals                  |
|               v2.0               |   user   | edit expense targets                              | keep track of my financial goals                  |
|               v2.0               |   user   | add recurring payments                            | keep track of my recurring payments               |
|               v2.0               |   user   | view recurring payments                           | keep track of my recurring payments               |
|               v2.0               |   user   | delete recurring payments                         | keep track of my recurring payments               |
|               v2.0               |   user   | edit recurring payments                           | keep track of my recurring payments               |
|               v2.0               |   user   | add an expense from an existing recurring payment | keep track of when recurring payments were paid   |
|               v2.0               |   user   | merge several data files together                 | consolidate my expenses easily                    |

<br>

[Back to Contents](#contents)

<br>
<br>
<div style="page-break-after: always;"></div>

## Non-Functional Requirements

1. The program should provide the same user experience regardless of the platform it is running on.
2. The program should be able to run without an internet connection.
3. The program should be responsive.

<br>

[Back to Contents](#contents)

<br>
<br>

## Glossary

| Term                                     | Definition                                                  |
|------------------------------------------|-------------------------------------------------------------|
| Mainstream Desktop Operating System (OS) | Windows, Mac OS, Linux                                      |
| Secure Shell (ssh)                       | Protocol that enables secure access to a remote system      |
| Secure Copy (scp)                        | Protocol enables secure data transfer with a remote system  |
| Remote Sync (rsync)                      | Tool that synchronises a file between a client and a server |

<br>

[Back to Contents](#contents)

<br>
<br>

## Manual Testing

## Sub-Contents
* [Launching MoneyGoWhere](#launching-moneygowhere)
* [Running Commands](#running-commands)
* [Terminating MoneyGoWhere](#terminating-moneygowhere)
* [Back to Contents](#contents)

<br>
<hr>
<br>

### Launching MoneyGoWhere
1. Set up the project according to the steps in [Getting Started](#getting-started).
2. Launch MoneyGoWhere by running `main()` in `MoneyGoWhere.java`.

<br>

[Back to Sub-Contents](#manual-testing)

<br>
<hr>
<br>

### Running Commands
1. Refer to the [User Guide](https://ay2223s1-cs2113t-w11-1.github.io/tp/UserGuide.html) for the list of commands and its corresponding arguments.
   It details the type of values accepted by each argument and which arguments are mandatory and optional.
2. Enter a command into the program.

<br>

[Back to Sub-Contents](#manual-testing)

<br>
<hr>
<br>

### Terminating MoneyGoWhere
1. Enter `Bye` into the console.

<br>

[Back to Sub-Contents](#manual-testing)

<br>
<br>
