# User Guide

## Introduction

Duke is a movie review allowing you to keep track of movies you have watched!

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY2223S1-CS2113-T18-1/tp/releases/tag/v1.0).
3. Copy the jar file into the folder you want to store your reviews.
4. Open terminal application and change directory to the above folder
5. Run the jar file by entering java -jar tp.jar into the terminal.

## Features 

The following features are supported by Duke.

### Adding a movie: `add`
Adds a new movie review to your list.

Format: `add /movie <title> /review <rating> /date <dateWatched>`

Example of usage: 

`add /movie Harry Potter /review 5.0 /date 10 Oct 2022`

### Display reviews: `list`
Displays all movies and television shows you have reviewed. Output is seperated by media category. 

Format: `list`

### Delete a review: `delete`
Removes a movie or television review from your list. References the output from the list command to determine which index to delete.

Format: `delete <type> <index>`

Example of usage:

`delete movie 1`

`delete tv 1`

### Clear all reviews: 'clear'
Deletes all reviews in your list.

Format: `clear`

[//]: # (## Command Summary)

[//]: # ()
[//]: # ({Give a 'cheat sheet' of commands here})

[//]: # ()
[//]: # (* Add todo `todo n/TODO_NAME d/DEADLINE`)
