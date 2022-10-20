# Developer Guide for Yet Another Module Organiser / Manager

## Table Of Contents

1. [Introduction](#1-introduction)
   - [1.1. Welcome!](#11-welcome)
2. [Setting up](#2-setting-up)
   - [2.1. Prerequisites](#21-prerequisites)
   - [2.2. Setting up the project locally](#22-setting-up-the-project-locally)
   - [2.3. Configure coding style](#23-configure-the-coding-style)
3. [Design](#3-design)
   - [3.1. Architecture](#31-architecture)
   - [3.2. Model Component](#32-model-component)
   - [3.3. Parser Component](#33-parser-component)
   - [3.4. Command Component](#34-command-component)
   - [3.5 Utils Component](#35-utils-component)
     - [3.5.1 Ui Component](#351-ui-component)
     - [3.5.2 Link Component](#352-link-component)
     - [3.5.3 Storage Component](#353-storage-component)
4. [Implementation](#4-implementation)
5. [Documentation](#5-documentation)
6. [Testing](#6-testing)
   - [6.1. Running tests](#61-running-tests)
   - [6.2. Instructions for running manual tests](#62-instructions-for-manual-testing)

[Appendix A: Product Scope](#appendix-a-product-scope) <br>
[Appendix B: User Stores](#appendix-b-user-stories) <br>
[Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements) <br>
[Appendix D: Glossary](#appendix-d-glossary) <br>
[Appendix E: Acknowledgements](#appendix-e-acknowledgements) <br>

## 1. Introduction

### 1.1. Welcome!

Welcome, and thank you for choosing to help contribute to Yet Another Module Organiser/ Manager!
Yet Another Module Organizer and Manager (YAMOM) is an all-in-one desktop app featuring
a full course catalogue, module search and timetable builder for the National University of Singapore, optimized for use via a Command Line Interface.

This document intends to onboard developers onto YAMOM. We hope to bring you in to fix bugs, or even adding
new features and dimensions to YAMOM!
It gives insights on how the project is set up, the architecture used,
and the code style one should adopt when contributing to the project.

## 2. Setting up

This section describes the development tools used in the creation of YAMOM.

### 2.1. Prerequisites

1. **JDK** 11
2. **IntelliJ** IDEA
3. **Gradle** 6.2

### 2.2. Setting up the project locally

1. **Fork** this repo, and **clone** the fork into your computer.
2. Open IntelliJ (if you are not in the welcome screen, click **`File`** > **`Close Project`** to close the existing project dialog first).
3. Set up the correct JDK version for Gradle  
   a.To set up the correct project structure **`Configure`** > **`Project Defaults`** > **`Project Structure`**  
   b. Under **`New...`** find the directory of the appropriate JDK version.
4. Click **`Import Project`**.
5. Find the **`build.gradle`** file and select it. Click **`OK`**.
6. Click **`Open as Project`**.
7. Click **`OK`** and accept the default settings.

### 2.3. Configure the coding style

In IntelliJ's IDEA we adopt [[se-edu/guides] IDEA: Configuring the code style](https://se-education.org/guides/tutorials/intellijCodeStyle.html)
to set up IDEA’s coding style to match ours.

> Optionally, you can follow the guide [[se-edu/guides] Using Checkstyle](https://se-education.org/guides/tutorials/checkstyle.html)
> to find how to use the CheckStyle within IDEA e.g., to report problems as you write code.

## 3. Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### 3.1 Architecture

**How the architecture components interact with each other**

Core program flow is managed by the Duke class. The Duke class delegates work to the Ui class to handle user input.

User input is passed to the Parser class to parse the input as a command.

Each command subclass handles its own execution.

<code>Storage</code>: Reads and writes data to the hard disk in a NUSMods export link format.

### 3.2 Model Component

### 3.3 Parser Component

The <code>Parser</code> component can:

- return the correct command type based on user input.

### 3.4 Command Component

### 3.5 Utils Component

#### 3.5.1 UI Component

#### 3.5.2 Link Component

#### 3.5.3 Storage Component

![Storage Class](..\docs\images\storageClass.png)

The <code>Storage</code> component can:

- read from the hard disk a single line which is supposed to be a NUSMods export link
- save to the hard disk

Different checks have been implemented to ensure that even
if the data file is modified in any way, it would not crash the programme.

##### Why it is implemented this way

To facilitate easy transfer of information from NUSMods to YAMOM.

##### Alternatives considered

Storing as <code>.json</code> file

- would not be readable by the user
- would have to implement another function for export/import function

## 4. Implementation

This section describes how key features of YAMOM are implemented in the latest release V1.0

### Storage feature

!["Opening saved state"](..\docs\images\storageOpenPreviousState.png)  
When the application starts up, the storage openPreviousState function will be called
to load previous state

### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## 5. Documentation

The following section describes how documentation for the project should be written. Note: documentation is all
written in [GitHub-Flavoured Markdown](https://github.github.com/gfm/).

## 6. Testing

The following section describes the testing methodologies followed in this project to ensure high-quality, bug-free
code as far as possible.

## 6.1. Running tests

## 6.2 Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

## Appendix A: Product scope

## Appendix B: User Stories

| Version | As a ... | I want to ...                                      | So that I can ...                               |
| ------- | -------- | -------------------------------------------------- | ----------------------------------------------- |
| v1.0    | student  | search for modules by module code, name or faculty | quickly add them to my planner                  |
| v1.0    | new user | view my timetable                                  | visualise my school schedule                    |
| v1.0    | new user | add and remove modules to my planner               | customise and organise my modules this semester |
| v1.0    | new user | view a short description of each module            | plan what modules to take                       |
| v1.0    | student  | select timetable slots                             | plan my schedule                                |

## Appendix C: Non-Functional Requirements

<br>

## Appendix D: Glossary

- _glossary item_ - Definition

## Appendix E: Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

### Third-party libraries

- Jackson Databind 2.14.0-rc1 [maven](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- Apache Common Langs 3.12.0 [maven](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3)
