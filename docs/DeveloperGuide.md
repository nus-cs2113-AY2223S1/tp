# Developer Guide

## Introduction

{add introduction here}

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Getting Started
This program was developed using the Java JDK 11 and the IDE Intellij IDEA.
The following steps will guide you through the setup process to get your development environment up and running.
1. Install Java Development Kit (JDK) 11.
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

## Design & Implementation
### Software Architecture:
The software architecture diagram below describes the application's design and the interaction between components.

![Software-Architecture](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-MoneyGoWhere-Webpage/docs/diagrams/SoftwareArchitecture.puml)

### Core Components:
* `MoneyGoWhere`: Main entrypoint of the application.
* `Common`: Defines various parameters used by the application.
* `UserInterface`: Provides functions to interface with the user via standard I/O and handle commands.
* `Command`: Defines the commands accepted by the program along with its arguments.
* `Parser`: Provides functions to parse inputs read from standard input.
* `Data`: Stores data and provides functions to operate on data.
* `Storage`: Defines functions to save and load data.
* `Logger`: Defines functions to log the user's actions and the application's behaviour.

### Component Interactions:
The sequence diagram below describes the interaction between the various core components when a command is entered.
In this example, 
the user enters the command `Add-Expense -n Expense -a 7.80` to add an expense with the name `Expense` and the amount `7.80`.

![Component-Interaction-On-Command-Entered](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-MoneyGoWhere-Webpage/docs/diagrams/ComponentInteractionsOnCommandEntered.puml)

* The `UserInterface` runs continuously in a loop.
When the program is ready to receive the user's input, 
it calls `UserInterface#getConsoleCommand()` which reads the input from standard in.
* `UserInterface#getConsoleCommand()` calls `Parser#parse()` to parse the input string into a `ConsoleCommand` object.
* Depending on the instance of `ConsoleCommand`, the corresponding command handler function will be called.\
In the example above, `ConsoleCommand` is an instance of `ConsoleCommandAddExpense` and hence, 
`UserInterface#runCommandAddExpense()` is called.
* The `UserInterface` command handler functions calls various `Data` functions to perform operations on data.\
In the example above, `UserInterface#runCommandAddExpense()` calls `Data#addExpense()` to add an expense to the program.
* After the operations is performed, command handler functions calls `Storage` functions to save data.\
In the example above, `UserInterface#runCommandAddExpense()` calls `Storage#saveToFile()` to save the newly added expense to a file.

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
| v2.0    | user     | convert between different currencies              | keep track of expenses across multiple currencies |
| v2.0    | user     | update currency exchange rates                    | convert between currencies using the latest rates |
| v1.0    | user     | add my income                                     | keep track if my spending exceeds my income       |
| v2.0    | user     | view my income                                    | keep track if my spending exceeds my income       |
| v2.0    | user     | delete my income                                  | keep track if my spending exceeds my income       |
| v2.0    | user     | edit my income                                    | keep track if my spending exceeds my income       |
| v1.0    | user     | add expense targets                               | keep track of my financial goals                  |
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

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
