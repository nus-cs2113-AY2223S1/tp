# Developer Guide (Pre-Release)

## Introduction

MoneyGoWhere is a financial tracker designed to help computing professionals manage their finances.
This developer guide explains the design considerations and implementation details required for future developers to work on MoneyGoWhere.

`IMPORTANT:` In this pre-release version, the diagrams in this user guide are rendered on-the-fly as the webpage is loaded. 
**If your internet connection is unstable, the diagrams may appear as broken links.** 
Please refresh the webpage if the diagrams are not loaded in time.

## Acknowledgements

We would like to acknowledge the following sources which our team has referenced during the development of MoneyGoWhere:
* AddressBook-Level3's User Guide and Developer Guide ([Website](https://se-education.org/addressbook-level3/))
* Apache Commons CLI Library ([Website](https://commons.apache.org/proper/commons-cli/))
* Apache Commons Text Library ([Website](https://commons.apache.org/proper/commons-text/))

## Getting Started

This program was developed using the Java JDK 11 and Intellij IDEA.
The following steps will guide you through the setup process to get your development environment up and running.
1. Install JDK 11.
2. Install Intellij IDEA.
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

## Design
### Software Architecture:
The software architecture diagram below describes the program's design and the interaction between components.

![Software-Architecture](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/SoftwareArchitecture.puml)

### Core Components:

| Component     | Function                                                                            |
|---------------|-------------------------------------------------------------------------------------|
| MoneyGoWhere  | Main entrypoint of the program.                                                     |
| Common        | Defines various parameters used by the program.                                     |
| Exceptions    | Defines exceptions thrown by the program.                                           |
| UserInterface | Provides functions to interface with the user via standard I/O and handle commands. |
| Command       | Defines the commands accepted by the program along with its arguments.              |
| Parser        | Provides functions to parse inputs read from standard input.                        |
| Data          | Stores data and provides functions to operate on data.                              |
| Storage       | Defines functions to save and load data.                                            |
| Logger        | Defines functions to log the user's actions and the program's behaviour.            |

### Component Interactions:
The sequence diagram below describes the interaction between the various core components when a command is entered.
In this example, 
the user launches the program and enters the command `Add-Expense -n Expense -a 7.80` to add an expense with the name `Expense` and the amount `7.80`.
The sequence diagrams referenced by the component interaction diagram can be seen [below](#component-interaction-reference-diagrams).

![Component-Interaction-On-Command-Entered](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentInteractionsOnCommandEntered.puml)

* When the user launches the program, `MoneyGoWhere` creates an instance of `UserInterface`.
* `MoneyGoWhere` calls `UserInterface#run()` to start the interface between the program and the user.
* `UserInterface#run()` will execute continuously in a loop until the user enters the command `Bye`.
* `UserInterface#run()` calls `UserInterface#getConsoleCommand()` to read and parse the user's input.
* `UserInterface#run()` will then call the corresponding command handler function based on the user's input.\
In the example above, `ConsoleCommand` is an instance of `ConsoleCommandAddExpense` and hence,
`UserInterface#runCommandAddExpense()` is called.
* When the command handler function is called, it calls `Data` functions to perform operations on data.\
In the example above, `UserInterface#runCommandAddExpense()` calls `Data#addExpense()` to add an expense to the program.
* After the operations are performed, command handler functions calls `Storage` functions to save data.\
In the example above, `UserInterface#runCommandAddExpense()` calls `Storage#saveToFile()` to save the newly added expense to a file.

#### Component Interaction Reference Diagrams:

![Component-Interaction-SD-Save-Expense-To-File](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentInteractionsSDLoadExpensesFromFile.puml)

* `UserInterface()` calls `Data#load()` to load any existing data stored in a file.
* `Data#load()` calls `Storage#loadFromFile()` to load the data from a file.

![Component-Interaction-SD-Get-User-Command](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentInteractionsSDGetUserCommand.puml)

* `UserInterface#getConsoleCommand()` calls `UserInterface#getConsoleInput()` to read the user's input as a string.
* `UserInterface#getConsoleCommand()` then calls `Parser#parse()` to parse the input string into the corresponding console command object.

![Component-Interaction-SD-Print-Expense](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentInteractionsSDPrintingAnExpense.puml)

* `UserInterface#run()` will call the corresponding convert object function based on the data object's class.
In the example above, `UserInterface#run()` calls `UserInterface#convertExpenseToConsoleString()` to convert the expense object into a formatted string.
* `UserInterface#run()` will then call `UserInterface#printInformationalMessage()` to print the converted object.

![Component-Interaction-SD-Save-Expense-To-File](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentInteractionsSDSaveExpensesToFile.puml)

* `UserInterface#run()` calls `Data#save()` to save the data managed by the data manager class.
* `Data#save()` calls `Storage#saveToFile()` to write the data to a file.

### Common Component

The Common component consists of the classes `Messages` and `Configurations`.

![Component-Common](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentCommon.puml)

The `Messages` class defines the messages used by the program during execution. It includes the informational, warning and error messages that are displayed to the user.
The `Configurations` class defines the configuration parameters used by the program. It stores parameters such as formatting information, directory and file paths, and the URLs of different APIs.

### Exceptions Component

The Exceptions component consists of various exception classes which inherits from `MoneyGoWhereException`.

![Component-Exceptions](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentExceptions.puml)

The exceptions are thrown and handled by the program depending on the conditions outlined in their Javadoc comments.
For example, `ConsoleParserCommandAddExpenseInvalidException` is thrown when an error is encountered while parsing the command.
Do refer to the [exceptions](https://github.com/AY2223S1-CS2113T-W11-1/tp/tree/master/src/main/java/seedu/moneygowhere/exceptions) package to view the full list of exceptions. 

### UserInterface Component

The UserInterface component consists of the class `ConsoleInterface` which runs the command line interface that the user interacts with.

![Component-UserInterface](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentUserInterface.puml)

The `ConsoleInterface` class defines various command handlers which are called based on the command entered by the user.
For example, entering the command string `Add-Expense -n Lunch -a 7.80` will result in the execution of the `ConsoleInterface#runCommandAddExpense()` command handler to add an expense to the program.
Do refer to the [ConsoleInterface.java](https://github.com/AY2223S1-CS2113T-W11-1/tp/blob/master/src/main/java/seedu/moneygowhere/userinterface/ConsoleInterface.java) class to view the full list of command handler functions.

### Commands Component

The Commands component consists of various console command classes which inherits from the abstract class `ConsoleCommand`.

![Component-Commands](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ComponentCommands.puml)

The corresponding console command subclass is returned by `ConsoleParser#parse()` depending on the command supplied in the function's parameter.
For example, supplying the command string `Add-Expense -n Lunch -a 7.80` to `ConsoleParser#parse()` will return a `ConsoleCommandAddExpense` object.
Do refer to the [commands](https://github.com/AY2223S1-CS2113T-W11-1/tp/tree/master/src/main/java/seedu/moneygowhere/commands) package to view the full list of console command subclasses.

## Implementation
### Printing an expense

![Implementation-SD-Print-Expense](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationSDPrintExpense.puml)

### Adding an expense: `Add-Expense`

![Implementation-Add-Expense](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationAddExpense.puml)

### Viewing an expense: `View-Expense`

![Implementation-View-Expense](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationViewExpense.puml)

### Deleting an expense: `Delete-Expense`

![Implementation-Delete-Expense](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationDeleteExpense.puml)

### Editing an expense: `Edit-Expense`

![Implementation-Edit-Expense](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationEditExpense.puml)

### Printing a recurring payment

![Implementation-SD-Print-RecurringPayment](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationSDPrintRecurringPayment.puml)

### Adding a recurring payment: `Add-RecurringPayment`

![Implementation-Add-RecurringPayment](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationAddRecurringPayment.puml)

### Viewing a recurring payment: `View-RecurringPayment`

![Implementation-View-RecurringPayment](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationViewRecurringPayment.puml)

### Deleting a recurring payment: `Delete-RecurringPayment`

![Implementation-Delete-RecurringPayment](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationDeleteRecurringPayment.puml)

### Editing a recurring payment: `Edit-RecurringPayment`

![Implementation-Edit-RecurringPayment](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationEditRecurringPayment.puml)

### Adding an expense from a recurring payment: `Pay-RecurringPayment`

![Implementation-Edit-RecurringPayment](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-Webpage/docs/diagrams/ImplementationPayRecurringPayment.puml)

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...                                     | So that I can ...                                 |
|---------|----------|---------------------------------------------------|---------------------------------------------------|
| v1.0    | user     | add expenses                                      | keep track of my finances                         |
| v1.0    | user     | view expenses                                     | keep track of my finances                         |
| v1.0    | user     | view expenses by name                             | search for past expenses easily                   |
| v1.0    | user     | view expenses by category                         | keep track of my spending across different areas  |
| v1.0    | user     | delete expenses                                   | keep track of my finances                         |
| v1.0    | user     | edit expenses                                     | keep track of my finances                         |
| v1.0    | user     | categorise expenses                               | keep track of my spending across different areas  |
| v1.0    | user     | sort expenses by alphabetical order               | organise my spending by their names               |
| v1.0    | user     | sort expenses by amount                           | keep track of the extent of my spending           |
| v1.0    | user     | sort expenses by date                             | keep track of my spending over time               |
| v1.0    | user     | save my data to a file                            | store my data easily                              |
| v1.0    | user     | load my data from a file                          | retrieve my data easily                           |
| v1.0    | user     | add my income                                     | keep track if my spending exceeds my income       |
| v1.0    | user     | add expense targets                               | keep track of my financial goals                  |
| v2.0    | user     | convert between different currencies              | keep track of expenses across multiple currencies |
| v2.0    | user     | update currency exchange rates                    | convert between currencies using the latest rates |
| v2.0    | user     | view my income                                    | keep track if my spending exceeds my income       |
| v2.0    | user     | delete my income                                  | keep track if my spending exceeds my income       |
| v2.0    | user     | edit my income                                    | keep track if my spending exceeds my income       |
| v2.0    | user     | view expense targets                              | keep track of my financial goals                  |
| v2.0    | user     | delete expense targets                            | keep track of my financial goals                  |
| v2.0    | user     | edit expense targets                              | keep track of my financial goals                  |
| v2.0    | user     | add recurring payments                            | keep track of my recurring payments               |
| v2.0    | user     | view recurring payments                           | keep track of my recurring payments               |
| v2.0    | user     | delete recurring payments                         | keep track of my recurring payments               |
| v2.0    | user     | edit recurring payments                           | keep track of my recurring payments               |
| v2.0    | user     | add an expense from an existing recurring payment | keep track of when recurring payments were paid   |
| v2.0    | user     | merge several data files together                 | consolidate my expenses easily                    |

## Non-Functional Requirements

1. Works on any mainstream desktop OS supported by `Java 11`

## Glossary

| Term                  | Definition             |
|-----------------------|------------------------|
| Mainstream Desktop OS | Windows, Mac OS, Linux |

## Instructions for manual testing

### Launching MoneyGoWhere
1. Set up the project according to the steps in [Getting Started](#getting-started)
2. Launch MoneyGoWhere by running `main()` in `MoneyGoWhere.java`

### Terminating MoneyGoWhere
1. Enter `Bye` into the console
