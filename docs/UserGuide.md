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

[Authenticate user API: `auth`](#authenticate-user-api-auth)

[Authenticate default: `auth default`](#authenticate-default-auth-default)

[Authenticate status: `auth status`](#authenticate-status-auth-status)

[Find number of lots available by carpark ID: `find`](#find-number-of-lots-available-by-carpark-id-find)

[Get a list of available carparks on the app: `list`](#get-a-list-of-available-carparks-on-the-app-list)

[Update data from API: `update`](#update-data-from-api-update)

[Favourite carparks by carpark ID: `favourite`](#favourite-carparks-by-carpark-id-favourite)

[Unfavourite carparks by carpark ID: `unfavourite`](#unfavourite-carparks-by-carpark-id-unfavourite)

[List all IDs of favourite carparks: `favourite list`](#list-all-ids-of-favourite-carparks-favourite-list)

[Exiting the program: `exit`](#exiting-the-program-exit)

### Authenticate user API: `auth`

**Authenticate and subscribe to the API Service (LTA Data Mall) using the user’s API key.**

- LTA Data Mall API information (click [here](https://datamall.lta.gov.sg/content/datamall/en/dynamic-data.html)).
- API Registration (click [here](https://datamall.lta.gov.sg/content/datamall/en/request-for-api.html)). API key will be emailed to you.

> Format : `auth API_KEY`

- `API_KEY` is case sensitive and unique.
- Upon successful authentication, the key will be stored locally and future authentication is not required.
- User can use `auth` to change the current key. Only one and the most recent key will be stored.
- If API key is invalid, the new key will not be stored and the previous key will be retained. If there are no API key beforehand, it will stay empty.

### Authenticate default: `auth default`

**Authenticate using the default API key.**

- For user with no API key, they can use the provided api key to access the services.
- User will not be able to know the api key.

> Format: `auth default`

### Authenticate status: `auth status`

**Get the status of the api key authentication status.**

- It will inform the user whether the user api key is entered and valid or whether the default API key is used.
- If user has entered and validated their personal key before, they will be able to view their own API key. Otherwise, the default API key will not be revealed to the user.

> Format: `auth status`

### Find number of lots available by carpark ID: `find`

**Returns the number of lots available in the carpark that the user has chosen to find.**

- Using data from the API, after the user has inputted the ID of a certain carpark, the function will return the number of lots available in aforementioned carpark.
- If the user inputs a non-existing carpark ID or inputs the wrong format, the program will prompt the user to re-enter the correct and existing carpark ID.

> Format: `find CARPARK_ID`

### Get a list of available carparks on the app: `list`

**Returns a list of the available carparks from the JSON file.**

- Using data from the API, after the user has inputted the command, the function will return a list of carparks from the API.

> Format: `list`

### Update data from API: `update`

**Fetches the most updated availability data and stores it locally.**

- The command requires a valid API access token to function.
- This overwrites any previous data stored to the file.

> Format: `update`

### Favourite carparks by carpark ID: `favourite`

**Saves the carpark to a list of favourited carparks for easy subsequent access.**

- The command requires user to input a valid and existing carpark ID.

> Format: `favourite CARPARK_ID`

### Unfavourite carparks by carpark ID: `unfavourite`

**Removes the carpark from a list of favourited carparks.**

- The command requires user to input a valid and existing carpark ID that is already favourited.

> Format: `unfavourite CARPARK_ID`

### List all IDs of favourite carparks: `favourite list`

**Displays the IDs of all favourited carparks.**

> Format: `favourite list`

### Exiting the program: `exit`

**Terminates the program.**

> Format: `exit`

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
