# Developer Guide

- [Preface](#preface)
- [Acknowledgements](#acknowledgements)
- [Setting Up the Project](#setting-up-the-project)
- [Design](#design)
    * [Architecture](#architecture)
    * [Command Component](#command-component)
    * [Data Component](#data-component)
    * [Storage Component](#storage-component)
    * [Parser Component](#parser-component)
    * [UI Component](#ui-component)
    * [Common Component](#common-component)
- [Implementation](#implementation)
    * [Overview for Transaction](#overview-for-transaction)
    * [Implementation for Transaction](#implementation-for-transaction)
    * [Help Command](#help-command)
    * [Add Command](#add-command)
    * [Edit Command](#edit-command)
    * [List Command](#list-command)
    * [Find Command](#find-command)
    * [Stats Command](#stats-command)
    * [Delete Command](#delete-command)
    * [Purge Command](#purge-command)
    * [Storage Operations](#storage-operations)
        + [Reading From a File](#reading-from-a-file)
        + [Writing To a File](#writing-to-a-file)
    * [Logging Operations](#logging-operations)
- [Appendix A: Product scope](#appendix-a--product-scope)
    * [Target user profile](#target-user-profile)
    * [Value proposition](#value-proposition)
- [Appendix B: User Stories](#appendix-b--user-stories)
- [Appendix C: Non-Functional Requirements](#appendix-c--non-functional-requirements)
- [Appendix D: Glossary](#appendix-d--glossary)
- [Appendix E:  Instructions for manual testing](#appendix-e---instructions-for-manual-testing)

## Preface

{Provide brief details of the Moolah Manager application and the purpose of the Developer Guide}

_Written by: Author name_

## Acknowledgements

{List here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

_Written by: Author name_

## Setting Up the Project

{Detail how to set up the project on one's computer, assuming the software is Intellij IDEA}
Before setting up the project on your computer, kindly check that you have installed:
* Java JDK 11
* Intellij IDEA - highly recommended

Firstly, you should fork this repo, before cloning the fork to your computer.

Next,

1. **Ensure that Intellij JDK 11 is defined as an SDK**, as described in this [[Set up JDK guide]](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) -- this step is not needed if you have used JDK 11 in a previous Intellij project.
   * You _might need to set the Project language level_ section to the SDK default option.
2. **Import the project _as a Gradle project_**, as described in [[se-edu's Import Gradle Project guide]](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
3. **Running the project**: After finishing the import, locate the `src/main/java/seedu.duke/Duke.java` file in this project, right-click it, and choose `Run Duke.main()`. 

_Written by: Paul Low_

## Design

{Describe the design of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture 

_Written by: Author name_

### Command Component 

_Written by: Author name_

### Data Component 

_Written by: Author name_

### Storage Component 

_Written by: Author name_

### Parser Component 

_Written by: Author name_

### UI Component 

_Written by: Author name_

### Common Component 

_Written by: Author name_

## Implementation

### Overview for Transaction

{Give a brief overview of the Transaction feature in Moolah Manager application.}

_Written by: Author name_

### Implementation for Transaction

{Provide the class diagram for Transaction} 

_Written by: Author name_

### Help Command

{Describe the implementation for the Help Command}

_Written by: Author name_

### Add Command

{Describe the implementation for the Add Command} 

_Written by: Author name_

### Edit Command 

{Describe the implementation for the Edit Command}

_Written by: Author name_

### List Command 

{Describe the implementation for the List Command}

The full command for list is `list [t/TYPE] [c/CATEGORY] [d/DATE]`
For example, if `list' is called, all transactions that are present in Moolah Manager will be listed out
Adding tags such as type, category and date will list all transactions to that category

In a command like `list c/food`
1. The `main()` method in Duke calls `run()` in Duke. The `ui` reads the command and parses it
 through `CommandParser.parse()`. 
2. Within `CommandParser.parse()`, `getCommand()` is called to obtain the command, before `ParameterParser.parse()`
is called
3. Various checks are done through functions within `parameter.parse()`
4. The list command is undergoing execution in `command.execute()` which will call `listTransactions()` in ListCommand
5. `ui.showTransactionsList()` is then executed since parameters are present

_Written by: Paul Low_

### Find Command

{Describe the implementation for the Find Command}

_Written by: Author name_

### Stats Command

{Describe the implementation for the Stats Command}

_Written by: Author name_

### Delete Command

{Describe the implementation for the Delete Command}

_Written by: Author name_

### Purge Command

{Describe the implementation for the Purge Command}

_Written by: Author name_

### Storage Operations 

#### Reading From a File

#### Writing To a File 

_Written by: Author name_

### Logging Operations

{Describe how logging is performed in the developer code}

Our team used `java.util.logging` package for the purposes of logging. We instantiated various objects
for different classes such as `parserLogger` and `addLogger` to set the log messages.

**Logging Levels**:
* `WARNING`: An exception has been caught by the app
* `INFO`: Information details what the app has done

_Written by: Paul Low_
## Appendix A: Product scope

### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Appendix C: Non-Functional Requirements

{Give non-functional requirements}

## Appendix D: Glossary

* *glossary item* - Definition

## Appendix E:  Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
