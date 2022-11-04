# joshuan98's Project Portfolio Page

## Table of Contents

- [Product Overview](#product-overview)
- [Project Contribution](#project-contribution)
  - [Database](#database)
  - [/list Command](#list-command)
  - [Testing](#testing)
  - [Documentation](#documentation)
  - [Contributions to Team-based Tasks](#contributions-to-team-based-tasks)
  - [Contributions Beyond The Project Team](#contributions-beyond-the-project-team)

## Product Overview

easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones for easier reference.
As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Project Contribution

### Code Contribution

- Code contributed: [RepoSense link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=joshuan98&breakdown=true)

### Database

- New Feature: Added the ability to store module mapping information in memory.
  - What it does: Reads in data from a `csv` file, verify its authenticity, and writes the data into memory for use by other parts of the program.
  - Justification: This feature is the backdone to keep track of the approved CEG SEP moduble mappings.
  - Highlights: Cleaned up the given data file, filter the relevant data from the `csv` for our application, parse the data into a usable form for other parts of the program, provide easy retrieval by filtering data based on name and module code for both NUS and partner university for ease of searching data in the database.
- **Relevant PRs**: [#7](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/7), [#49](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/49)

### `/list` command

- New Feature: Added the ability for users to list information from the database.
  - What it does: 4 sub-capabilities, displays the list of all modules in the database, displays the list of all universities in the database, filters module mappings based on NUS module code, filters module mappings based on partner university name.
  - Justification: The user may not know what module mappings or universities are available to be added into their planning. This feature allows them to search and filter modules mappings that they are interested in.
  - Highlights: Check the validity of the list command query, execute the list command according to the 4 discrete types, throws errors when invalid inputs are provided, informs user when there are no valid module mappings that match their query.
- **Relevant PRs**: [#61](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/61)

## Contributions to the Developer Guide (Extracts)

## Contributions to the User Guide (Extracts)
