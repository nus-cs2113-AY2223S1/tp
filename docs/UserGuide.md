# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

> Notes about the command format:
>  - Words in `UPPER_CASE` are the parameters supplied by the user.
>  - Items in square bracket are optional.

| Command                   | Description                                                                                  |
|---------------------------|----------------------------------------------------------------------------------------------| 
| `auth API_KEY`            | [Authenticate API using user's API key](#authenticate-user-api)                              |
| `auth default`            | [Authenticate API using default key](#authenticate-default)                                  |
| `auth status`             | [Authentication status](#authenticate-status-auth)                                           |
| `find CARPARK_ID`         | [Find number of lots available by carpark ID](#find-number-of-lots-available-by-carpark-id)  | 
| `list`                    | [Get a list of available carparks on the app](#get-a-list-of-available-carparks-on-the-app)  |
| `update`                  | [Update data from API](#update-data-from-api)                                                |
| `favourite CARPARK_ID`    | [Favourite carparks by carpark ID](#favourite-carparks-by-carpark-id)                        |
| `unfavourite CARPARK_ID`  | [Unfavourite carparks by carpark ID](#unfavourite-carparks-by-carpark-id)                    |
| `favourite list`          | [List all IDs of favourite carparks](#list-all-ids-of-favourite-carparks)                    |
| `exit`                    | [Exiting the program](#exiting-the-program)                                                  |

### Authenticate user API

**Authenticate and subscribe to the API Service (LTA Data Mall) using the user’s API key.**
> Format : `auth API_KEY`

- LTA Data Mall API information (click [here](https://datamall.lta.gov.sg/content/datamall/en/dynamic-data.html)).
- API Registration (click [here](https://datamall.lta.gov.sg/content/datamall/en/request-for-api.html)).
API key will be emailed to you.
- `API_KEY` is case sensitive and unique.
- Upon successful authentication, the key will be stored locally and future authentication is not required.
- User can use `auth` to change the current key. Only one and the most recent key will be stored.
- If API key is invalid, the new key will not be stored and the previous key will be retained. 
If there are no API key beforehand, it will stay empty.

Example of usage:

### Authenticate default

**Authenticate using the default API key.**
> Format: `auth default`

- For user with no API key, they can use the provided api key to access the services.
- User will not be able to see the api key.

Example of usage:

### Authenticate status

**Get the status of the api key authentication status.**
> Format: `auth status`

- It will inform the user whether the user api key is entered and valid or whether the default API key is used.
- If user has entered and validated their personal key before, they will be able to view their own API key. 
Otherwise, the default API key will not be revealed to the user.

Example of usage:

### Find number of lots available by carpark ID

**Returns the number of lots available in the carpark that the user has chosen to find.**
> Format: `find CARPARK_ID`

- Using data from the API, after the user has inputted the ID of a certain carpark, 
the function will return the number of lots available in aforementioned carpark.
- If the user inputs a non-existing carpark ID or inputs the wrong format, 
the program will prompt the user to re-enter the correct and existing carpark ID.

Example of usage:

### Get a list of available carparks on the app

**Returns a list of the available carparks from the JSON file.**
> Format: `list`

- Using data from the API, after the user has inputted the command, the function will return a list of carparks from the API.

Example of usage:

### Update data from API

**Fetches the most updated availability data and stores it locally.**
> Format: `update`

- The command requires a valid API access token to function.
- This overwrites any previous data stored to the file.

### Favourite carparks by carpark ID

**Saves the carpark to a list of favourited carparks for easy subsequent access.**
> Format: `favourite CARPARK_ID`

- The command requires user to input a valid and existing carpark ID.

### Unfavourite carparks by carpark ID 

**Removes the carpark from a list of favourited carparks.**
> Format: `unfavourite CARPARK_ID`

- The command requires user to input a valid and existing carpark ID that is already favourited.

### List all IDs of favourite carparks

**Displays the IDs of all favourited carparks.**
> Format: `favourite list`

### Exiting the program

**Terminates the program.**

> Format: `exit`


`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
