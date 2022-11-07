# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
- [Commands](#commands)
  - [Adding a movie or TV show: `add`](#adding-a-movie-or-tv-show-add)
  - [Displaying reviews: `list`](#displaying-reviews-list)
  - [Deleting a review: `delete`](#deleting-a-review-delete)
  - [Clearing all reviews: `clear`](#clearing-all-reviews-clear)
  - [Sorting reviews: `sort`](#sorting-reviews-sort)
  - [Finding reviews: `find`](#finding-reviews-find)
  - [Mark favourite reviews: `favourite`](#favouriting-reviews-favourite)
  - [Listing favourite reviews: `favourite list`](#listing-favourite-reviews-favourite-list)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
- [Command Summary](#command-summary)

## Introduction

myReviews is a movie review application allowing you to keep track of movies you have watched. With various commands at your 
disposal and the ability to store your review list, it has never been easier to keep track of all the movies you have 
watched!

The main features of myReviews include:
- Tracking watched movies and TV shows with ratings attached
- Gain insight into your movie preferences by sorting according to rating, category and more!
- Mark the movies and TV shows that you consider your favourites!

myReviews is optimized for use via a Command Line Interface (CLI). If you can type fast, look no further for your perfect, 
one-stop movie review companion.

## Quick Start

1. Ensure that you have Java 11 or above installed on your machine.
2. Download the latest version of `myReviews` from [here](https://github.com/AY2223S1-CS2113-T18-1b/tp/releases).
3. Copy the jar file into the folder you want to store your reviews.
4. Open terminal application and change directory to the above folder.
5. Run the jar file by entering `java -jar myReviews.jar` into the terminal.
6. myReviews will display a greeting message. Enter a command and press Enter to execute it.

## Features 

The following features are supported by myReviews.

1. User can add reviews on watched movies and tv shows.
2. User can save their ratings, date watched, genre, location watched (only for tv) to a particular tv show/movie.
3. User can delete unwanted reviews.
4. User can view all added reviews.
5. User can clear all reviews.
6. User can sort reviews by genre, title, date and rating.
7. User can find reviews by keyword.
8. User can set specific reviews as a favourite.
9. User can list all favourite reviews.
10. User input is automatically saved and loaded every session.

> Notes for saved list:
> - Your saved review list will be saved in a file called `stored.txt`.
> - Removing parameters from this file might result in a corrupted file, and your list will be deleted should there be
> missing parameters when the app is launched.

## Commands
### Adding a movie or TV show: `add`
Adds a new movie or TV show review to your list.

> Note:
> 
>Title, genre, and site must not include "/" delimiter in input so that command can be correctly parsed.
Entries with duplicate title names (case sensitive) are not allowed within the same media type. 
>
> Example: If your current list contains the Movie "avengers", attempting to add new Movie "avengers" will not be allowed.
> However, adding a Movie titled "Avengers" (with capitalised 'A') is taken as a different Movie and will be accepted.

**Format**: 

`add /movie <title> /rating <rating> /date <dateWatched> /genre <genre>`

`add /tv <title> /rating <rating>  /date <dateWatched> /genre <genre> /site <location>`

- `<title>`, `<genre>` and `<location>` must be strings.
- `<rating>` must be an integer value from 0 to 100.
- `<dateWatched>` must have passed and be given in the following format: dd-MM-YYYY.
- All arguments are compulsory.

Usage: 

`add /movie inception /rating 90 /date 10-01-2020 /genre thriller`

`add /tv game of thrones /rating 5 /date 02-02-2022 /genre fantasy /site hbo`

Output:
```
Got it. I've added the following item to the list:
        [Movie] inception  Rating: 90.0  Genre: thriller  Date watched: 10-01-2020

        Now you have 1 reviews in the list.
```
```
Got it. I've added the following item to the list:
	[TV Show]  game of thrones  Rating: 5.0  Genre: fantasy  Date watched:  02-02-2022   Site: hbo

	Now you have 2 reviews in the list.
```

### Displaying reviews: `list`
Displays all movies and television shows you have reviewed. Output is separated by media category. 

**Format and usage**: `list`

Output:
```
---Here are the reviews in your list---

Movies:
1. [Movie]  inception  Rating: 95.0  Genre: thriller  Date watched: 10-01-2020
2. [Movie]  avengers  Rating: 99.0  Genre: action  Date watched: 30-01-2020
3. [Movie]  cars  Rating: 92.0  Genre: animated  Date watched: 20-01-2020

TV Shows:
1. [TV Show]  game of thrones  Rating: 97.0  Genre: fantasy  Date watched:  10-01-2020   Site: hbo
2. [TV Show]  rick and morty  Rating: 98.0  Genre: animated  Date watched:  30-01-2020   Site: amazon
3. [TV Show]  the office  Rating: 99.0  Genre: comedy  Date watched:  20-01-2020   Site: disney
```

### Deleting a review: `delete`
Removes a movie or television review from your list. References the output from the list command to determine which index to delete.

**Format**: `delete <type> <index>`

- `<type>` must be either 'movie' or 'tv', for movies and tv shows respectively in the review list.
- `<index>` refers to the index number shown by category in the displayed review list.
- `<index>` must be a positive integer within the range of indices in the review list.
- All arguments are compulsory for successful execution of command.

Usage:

`delete movie 1`

`delete tv 2`

Output:

```
Noted. I've deleted the following media:
	[Movie]  Cars  Rating: 92.0  Genre: animated  Date watched: 20-01-2020
Now you have 8 reviews in the list.
```

### Clearing all reviews: 'clear'
Deletes all reviews in your list.

**Format and usage**: `clear`

Output: 

```
Your list is now cleared.
```
### Sorting reviews: 'sort'
Sorts your review list according to a given field.

**Format**: `sort <field>`

- `<field>` must be one of the following strings: 'rating', 'title', 'date' or 'genre'.
- Sorting the review list by 'rating' or 'date' returns the reviews in descending order i.e. from the highest rating to 
the lowest rating and from the most recent to the least recent.
- Sorting by 'title' or 'genre' returns the reviews in case-sensitive ascending order i.e. from 'A' to 'Z'.
- All arguments are compulsory for successful execution of command.

Usage:

`sort rating`

`sort title`

`sort genre`

`sort date`

Output:
```
Your list has been sorted by rating
---Here are the reviews---

Movies:
1. [Movie]  avengers  Rating: 99.0  Genre: action  Date watched: 30-01-2020
2. [Movie]  inception  Rating: 95.0  Genre: thriller  Date watched: 10-01-2020

TV Shows:
1. [TV Show]  the office  Rating: 99.0  Genre: comedy  Date watched: 20-01-2020  Site: disney
2. [TV Show]  rick and morty  Rating: 98.0  Genre: animated  Date watched: 30-01-2020  Site: amazon
```

### Finding reviews: 'find'
Searches your review list to find movies whose **titles** contain the given keyword.

**Format**: `find <keyword>`

- `<keyword>` must be a string.
- The search is case-sensitive e.g. `Inception` will not match `inception`
- All arguments are compulsory for successful execution of command.
- You may find with one or multiple words.

Usage (1): `find inc`

Output:
```
---Here are the reviews---

Movies:
1. [Movie]  inception  Rating: 95.0  Genre: thriller  Date watched: 10-01-2020

TV Shows:
```

Usage (2): `find game of`

Output:
```
---Here are the reviews---

Movies:

TV Shows:
1. [TV Show]  game of thrones  Rating: 5.0  Genre: fantasy  Date watched:  02-02-2022   Site: hbo
```

### Mark favourite reviews: 'favourite'
Lets you mark items you find particularly notable or want to watch again for easier reference.

**Format**: `favourite <type> <index>`

- `<type>` must be either 'movie' or 'tv', for movies and tv shows respectively in the review list.
- `<index>` refers to the index number shown by category in the displayed review list.
- `<index>` must be a positive integer within the range of indices in the review list.
- All arguments are compulsory for successful execution of command.

Usage: 

`favourite movie 1`

`favourite tv 3`

Output:
```
The following review has been starred:
[Movie]  inception  Rating: 95.0  Genre: thriller  Date watched: 10-01-2020
```

### Listing favourite reviews: 'favourite list'
Displays the items in your review list that have been marked as favourite.

**Format and usage**: `favourite list`

Output:
```
You have 5 favourites in total.
---Here are the reviews---

Movies:
1. [Movie]  avengers  Rating: 99.0  Genre: action  Date watched: 30-01-2020
2. [Movie]  inception  Rating: 95.0  Genre: thriller  Date watched: 10-01-2020

TV Shows:
1. [TV Show]  the office  Rating: 99.0  Genre: comedy  Date watched: 20-01-2020  Site: disney
2. [TV Show]  rick and morty  Rating: 98.0  Genre: animated  Date watched: 30-01-2020  Site: amazon
3. [TV Show]  game of thrones  Rating: 97.0  Genre: fantasy  Date watched: 10-01-2020  Site: hbo
```

### Exiting the program: 'bye'
Saves your review list to an external file and exits the program.

**Format and usage**: `bye`

Output:
```
---------------
See you again!
---------------
```

## Command Summary

| Command        | Format                                                                                  | Example                                                                       |
|----------------|-----------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| add (movie)    | `add /movie <title> /rating <rating> /date <dateWatched> /genre <genre>`                | `add /movie inception /rating 90 /date 10-01-2020 /genre thriller`            |
| add (TV show)  | `add /tv <title> /rating <rating>  /date <dateWatched> /genre <genre> /site <location>` | `add /tv game of thrones /rating 5 /date 02-02-2022 /genre fantasy /site hbo` |
| list           | `list`                                                                                  | `list`                                                                        |
| delete         | `delete <type> <index>`                                                                 | `delete movie 1` `delete tv 2`                                                |
| clear          | `clear`                                                                                 | `clear`                                                                       |
| find           | `find <keyword>`                                                                        | `find inc` `find avengers`                                                    |
| favourite      | `favourite <type> <index>`                                                              | `favourite movie 1` `favourite tv 3`                                          |
| favourite list | `favourite list`                                                                        | `favourite list`                                                              |
| sort           | `sort <field>`                                                                          | `sort rating` `sort title` `sort genre` `sort date`                           |
| bye            | `bye`                                                                                   | `bye`                                                                         |

