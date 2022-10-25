# User Guide

## Introduction

easySEP is a CLI application created to assist NUS Computer Engineering undergraduates intending to embark on a Student Exchange Programme in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones
for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

{Give detailed description of each feature}

### Adding a todo: `todo`

Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

- The `DEADLINE` can be in a natural language format.
- The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### List Command: `/list`

Performs a total of 4 functionalities: displays all universities, displays all module mappings, filter module mappings based on NUS module code, or filter module mappings based on Partner University name.

#### List Command for all universities: `/list UNIVERSITIES`

Displays all universities with module mappings available in database

Example of usage:

`/list UNIVERSITIES`

#### List Command for all modules : `/list MODULES`

Displays all existing university modules mappings that are approved in the following format:

`[Partner University Module Code] [Partner University Module Title] [Partner University Module Credits] | [NUS Module Code] [NUS Module Title] [NUS Module Credits] in NUS`

Example of usage:

`/list MODULES`

#### Filtered List Command by module: `/list m/MODULECODE`

List all module mappings for NUS MODULECODE in database

Format: `/list m/MODULECODE`

Example of usage:

`/list m/CS2113`

#### Filtered List Command by university name: `/list u/UNIVERSITY_NAME_IN_UNDERSCORES`

List all module mappings offered by UNIVERSITY in database

Format: `/list u/UNIVERSITY_NAME_IN_UNDERSCORES`

Example of usage:

`/list u/Aalto_University`

### Favourite Command: `/favourite`

Performs a total of 3 functionalities: view all lists that are marked as favourites, add a new list to favourites and delete a list from favourites.

#### Favourite Command for viewing all favourited lists: `/favourite view/`

Displays all lists that are marked as favourites with their associated module mappings.

Example of usage:

`/favourite view/`

#### Favourite Command for adding a list to favourites : `/favourite add/UNIVERSITY_NAME_IN_UNDERSCORES`

Adds a list of module mappings for a particular partner university into favourites. This list of module mappings for the partner university
must already be created beforehand in order to be added into favourites.

Example of usage:

`/favourite add/Boston_University`

#### Favourite Command for deleting a list from favourites : `/favourite del/UNIVERSITY_NAME_IN_UNDERSCORES`

Deletes a list of module mappings for a particular partner university from favourites. This list of module mappings for the partner university
must already be marked as favourite beforehand in order to be deleted from favourites.

Example of usage:

`/favourite del/Boston_University`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

- Add todo `todo n/TODO_NAME d/DEADLINE`
