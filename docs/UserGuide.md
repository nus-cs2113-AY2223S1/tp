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

| Command                  | Description                                                                                 |
|--------------------------|---------------------------------------------------------------------------------------------| 
| `auth API_KEY`           | [Authenticate API using user's API key](#authenticate-user-api)                             |
| `auth default`           | [Authenticate API using default key](#authenticate-default)                                 |
| `auth status`            | [Authentication status](#authenticate-status-auth)                                          |
| `find CARPARK_ID`        | [Find number of lots available by carpark ID](#find-number-of-lots-available-by-carpark-id) | 
| `filter QUERY`           | [Filter carparks based on address](#filter-carparks-based-on-address)                       | 
| `list`                   | [Get a list of available carparks on the app](#get-a-list-of-available-carparks-on-the-app) |
| `update`                 | [Update data from API](#update-data-from-api)                                               |
| `favourite CARPARK_ID`   | [Favourite carparks by carpark ID](#favourite-carparks-by-carpark-id)                       |
| `unfavourite CARPARK_ID` | [Unfavourite carparks by carpark ID](#unfavourite-carparks-by-carpark-id)                   |
| `favourite list`         | [List all IDs of favourite carparks](#list-all-ids-of-favourite-carparks)                   |
| `exit`                   | [Exiting the program](#exiting-the-program)                                                 |

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
- `find` is case-insensitive and will find the correct carpark code even if lowercase is used.

Example of usage:

**Input:**

```find a9```

**Output:**
```
===========================================
BLK 202/203 ANG MO KIO STREET 22
===========================================
Carpark code: A9
Favourited: Yes
Number of available lots (total): 132
  Cars: 66 lots
  Motorcycles: 0 lots
  Heavy Vehicles: 0 lots
===========================================
```

### Filter carparks based on address

**Shows a list of carparks that match a given query.**
> Format: `filter QUERY`

- The filter command allows the user to search a carpark based on the name of the carpark. For example,
a carpark may have the name of `BLK 208 CLEMENTI AVE 6` - a query of `clementi` or `208` will return this
carpark as one of its results.
- Multiple words can be input to narrow the filtered results further. Given the previous example, `clementi ave` will
also give the above carpark as one of its results.
- For convenience, the `filter` command also matches substrings at the _beginning_ of a word. Following the previous 
example, `clem` will also match the above carpark.
- In carpark names, matched words will be wrapped with ` to easily see at a glance what was matched. 

Example of usage

**Input:**

```filter clem ave 2```

**Output:**
```    
CarparkID C5 at BLK 358-360,36`2`-363,366-367 `CLEMENTI` `AVE` `2`
    806 available lots total
CarparkID C8 at BLK 335/338 `CLEMENTI` `AVE` `2`
    356 available lots total
CarparkID C28M at BLK `208` `CLEMENTI` `AVE` 6
    370 available lots total
CarparkID C6 at BLK 3`2`8-334 `CLEMENTI` `AVE` `2`
    226 available lots total
CarparkID C7 at BLK 349-355 `CLEMENTI` `AVE` `2`
    304 available lots total
```

### Get a list of available carparks on the app

**Returns a list of the available carparks from the JSON file.**
> Format: `list`

- Using data from the API, after the user has inputted the command, the function will return a list of carparks from the API.

Example of usage:

**Input:**

```list```

**Output:**

```
CarparkID T0129 at TANJONG KATONG COMPLEX
   182 available lots total
CarparkID KU1 at BLK 301/305-306/311-318 UBI AVENUE 1
   376 available lots total
CarparkID SAM2 at BLK 61 STRATHMORE AVENUE
   302 available lots total
CarparkID HG80 at BLK 941A HOUGANG STREET 92
   730 available lots total
```

### Update data from API

**Fetches the most updated availability data and stores it locally.**
> Format: `update`

- The command requires a valid API access token to function.
- This overwrites any previous availability data for any carparks fetched by the API.

### Favourite carparks by carpark ID

**Saves the carpark to a list of favourited carparks for easy subsequent access.**
> Format: `favourite CARPARK_ID`

- The command requires user to input a valid and existing carpark ID that is not already favourited.

Example of usage:

**Input (assuming carpark ID 1 is not favourited):**

```favourite 1```

**Output:**

```
Added Carpark 1 to favourites!
```

**Input (assuming carpark ID J8 is already favourited):**

```favourite J8```

**Output:**

```
Carpark already in list.
```

**Input:**

```favourite not_a_carpark_ID```

**Output:**

```
No carpark was found.
```

### Unfavourite carparks by carpark ID 

**Removes the carpark from a list of favourited carparks.**
> Format: `unfavourite CARPARK_ID`

- The command requires user to input a valid and existing carpark ID that is already favourited.

Example of usage:

**Input (assuming carpark ID 1 is favourited):**

```unfavourite 1```

**Output:**

```
Removed Carpark 1 to favourites!
```

**Input (assuming carpark ID M32 is not favourited):**

```unfavourite M32```

**Output:**

```
No carpark was found.
```

### List lot availability of all favourite carparks

**Displays the name, ID and total lot availability of all favourite carparks.**
> Format: `favourite list`

Example of usage:

**Input:**

```favourite list```

**Output:**

```
CarparkID D0006 at DUXTON HILL OFF STREET: 104 lots available
===========================================
CarparkID CKM6 at BLK 462A CHOA CHU KANG AVE 4: 1606 lots available
===========================================
CarparkID A9 at BLK 202/203 ANG MO KIO STREET 22: 102 lots available
===========================================
CarparkID J8 at BLK 232/240 JURONG EAST ST 21: 318 lots available
===========================================
```

### Exiting the program

**Terminates the program.**

> Format: `exit`


`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How is the data for the carparks stored? Can I change or edit this data?

**A**: The carpark data is first fetched in a `.json` format, under 
`.\resources\api\ltaResponse.json`. Then, this carpark data is written
to a `.txt` file at `.\resources\carparkList.txt`. If you wish to manually edit any 
carpark data, you can do so as long as the format is maintained. These changes will be reflected
when you restart the program.


## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
