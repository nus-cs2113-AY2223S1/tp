# User Guide

## Introduction

Duke is a movie review allowing you to keep track of movies you have watched! Duke has several commands and also saves your information for future retrieval.

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

**Format**: `add /movie <title> /review <rating> /date <dateWatched>`

Usage: 
`add /movie inception /rating 99 /date 10-01-2020 /genre thriller`

Output:
```
Got it. I've added the following item to the list:
        [Movie]  up  Rating:99.0 Genre: animated Date watched:10-01-2020

        Now you have 5 reviews in the list.
```

### Display reviews: `list`
Displays all movies and television shows you have reviewed. Output is separated by media category. 

**Format**: `list`

Output:
```
---Here are the reviews in your list---

Movies:
1. [Movie]  inception  Rating:99.0 Genre: thriller Date watched:10-01-2020
2. [Movie]  titanic  Rating:98.0 Genre: romance Date watched:01-02-2019
3. [Movie]  hey  Rating:50.0 Genre: action Date watched:29-01-2000

TV Shows:
1. [TV Show]  titanic  Rating:98.0 Genre: romance  Date watched:01-02-2019 Site:  netflix

```

### Delete a review: `delete`
Removes a movie or television review from your list. References the output from the list command to determine which index to delete.

**Format**: `delete <type> <index>`

Usage:
`delete movie 1`
`delete tv 1`

### Clear all reviews: 'clear'
Deletes all reviews in your list.

**Format**: `clear`

### Sort reviews: 'sort'
Sorts reviews in your list. Sorting is possible by the following fields:
rating, title, genre and date


**Format**: `sort [field]`

Usage:
sort rating
sort title
sort genre
sort date

Output:
```
Your list has been sorted by rating
---Here are the reviews in your list---

Movies:
1. [Movie]  interstellar  Rating:50.0 Genre: action Date watched:29-01-2000
2. [Movie]  titanic  Rating:98.0 Genre: romance Date watched:01-02-2019
3. [Movie]  inception  Rating:99.0 Genre: thriller Date watched:10-01-2020

TV Shows:
1. [TV Show]  friends  Rating:98.0 Genre: romance  Date watched:01-02-2019 Site:  netflix
```

### Find reviews: 'find'
Allows the searching of a particular review.

**Format**: `find [string]`

Usage: find inc

Output:
```
---Here are the reviews that match the keyword---

Movies:
1. [Movie]  inception  Rating:99.0 Genre: thriller Date watched:10-01-2020

TV Shows:
```

### Favourite reviews: 'favourite'
Lets you mark items you find particularly notable or want to watch again for easier reference.

**Format**: `favourite [list position]`

Usage: favourite 1

Output:
```
The following task has been starred:
[Movie]  inception  Rating:99.0 Genre: thriller Date watched:10-01-2020
```

### List favourite reviews: 'favourite list'
Lets you list your favourited items.

**Format and usage**: `favourite list`

Output:
```
Your favourites are:
[Movie]  inception  Rating:99.0 Genre: thriller Date watched:10-01-2020
[Movie]  titanic  Rating:98.0 Genre: romance Date watched:01-02-2019
[TV Show]  titanic  Rating:98.0 Genre: romance  Date watched:01-02-2019 Site:  netflix
[Movie]  hey  Rating:50.0 Genre: action Date watched:29-01-2000
```

[//]: # (## Command Summary)

[//]: # ()
[//]: # ({Give a 'cheat sheet' of commands here})
