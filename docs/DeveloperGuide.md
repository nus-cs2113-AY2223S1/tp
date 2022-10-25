# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
### Software Architecture:
The software architecture diagram below describes the application's design and the interaction between components.

![Software-Architecture](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-MoneyGoWhere-Webpage/docs/diagrams/SoftwareArchitecture.puml)

### Core Components:
* `MoneyGoWhere`: Main entrypoint of the application.
* `Common`: Defines various parameters used by the application.
* `UserInterface`: Provide functions to interface with the user via standard I/O and handle commands.
* `Parser`: Provide functions to parse inputs read from standard input.
* `Data`: Stores data and provides functions to operate on data.
* `Storage`: Defines functions to save and load data.
* `Logger`: Defines functions to log the user's actions and the application's behaviour.

### Component Interactions:
The sequence diagram below describes the interaction between the various core components when a command is entered.
In this example, 
the user enters the command `Add-Expense -n Expense -a 7.80` to add an expense with the name `Expense` and the amount `7.80`.

![Component-Interaction-On-Command-Entered](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/xzynos/tp/branch-MoneyGoWhere-Webpage/docs/diagrams/ComponentInteractionsOnCommandEntered.puml)

* The `UserInterface` runs cotinuously in a loop.
When the program is ready to receive the user's input, 
it calls `UserInterface#getConsoleCommand()` which reads the input from standard in.
* `UserInterface#getConsoleCommand()` calls `Parser#parse()` to parse the input string into a `ConsoleCommand` object.
* Depending on the instance of `ConsoleCommand`, the corresponding command handler function will be called.
In the example above, `ConsoleCommand` is an instance of `ConsoleCommandAddExpense` and hence, 
`UserInterface#runCommandAddExpense()` is called.
* The `UserInterface` command handler functions calls various `Data` functions to perform operations on data.
In the example above, `UserInterface#runCommandAddExpense()` calls `Data#addExpense()` to add an expense to the program.
* After the operations is performed, command handler functions calls `Storage` functions to save data.
In the example above, `UserInterface#runCommandAddExpense()` calls `Storage#saveToFile()` to save the newly added expense to a file.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...                                 |
|--------|----------|---------------|---------------------------------------------------|
| v1.0    | user     | add expenses                         | keep track of my finances                         |
| v1.0    | user     | view expenses                        | keep track of my finances                         |
| v1.0    | user     | view expenses by name                | search for past expenses easily                   |
| v1.0    | user     | view expenses by category            | keep track of my spending across different areas  |
| v1.0    | user     | delete expenses                      | keep track of my finances                         |
| v1.0    | user     | edit expenses                        | keep track of my finances                         |
| v1.0    | user     | categorise expenses                  | keep track of my spending across different areas  |
| v1.0    | user     | save my data to a file               | store my data easily                              |
| v1.0    | user     | load my data from a file             | retrieve my data easily                           |
| v1.0    | user     | add my income                        | keep track if my spending exceeds my income       |
| v2.0    | user     | view my income                       | keep track if my spending exceeds my income       |
| v2.0    | user     | delete my income                     | keep track if my spending exceeds my income       |
| v2.0    | user     | edit my income                       | keep track if my spending exceeds my income       |
| v1.0    | user     | add expense targets                  | keep track of my financial goals                  |
| v2.0    | user     | view expense targets                 | keep track of my financial goals                  |
| v2.0    | user     | delete expense targets               | keep track of my financial goals                  |
| v2.0    | user     | edit expense targets                 | keep track of my financial goals                  |
| v1.0    | user     | sort expenses by alphabetical order  | organise my spending by their names               |
| v1.0    | user     | sort expenses by amount              | keep track of the extent of my spending           |
| v1.0    | user     | sort expenses by date                | keep track of my spending over time               |
| v2.0    | user     | add recurring payments               | keep track of my recurring payments               |
| v2.0    | user     | view recurring payments              | keep track of my recurring payments               |
| v2.0    | user     | delete recurring payments            | keep track of my recurring payments               |
| v2.0    | user     | edit recurring payments              | keep track of my recurring payments               |
| v2.0    | user     | merge several data files together    | consolidate my expenses easily                    |
| v2.0    | user     | convert between different currencies | keep track of expenses across multiple currencies |
| v2.0    | user     | update currency exchange rates       | convert between currencies using the latest rates |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
