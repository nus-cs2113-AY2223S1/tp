# User Guide

## Contents

- [Contents](#contents)
- [Introduction](#introduction)
- [Quick Start](#quick-start)
  - [Disclaimer](#disclaimer-regarding-use-of-api-key-for-the-purposes-of-nus-cs2113)
- [Features](#features)
  - [Authenticate user API](#authenticate-user-api)
  - [Authenticate default](#authenticate-default)
  - [Authentication status](#authentication-status)
  - [Find number of lots by carpark ID](#find-number-of-lots-by-carpark-id)
  - [Filter carparks based on address](#filter-carparks-based-on-address)
  - [Get a list of carparks on the app](#get-a-list-of-carparks-on-the-app)
  - [Update data from API](#update-data-from-api)
  - [Favourite carparks by carpark ID](#favourite-carparks-by-carpark-id)
  - [Unfavourite carparks by carpark ID](#unfavourite-carparks-by-carpark-id)
  - [List lot availability of favourite carparks](#list-lot-availability-of-favourite-carparks)
  - [Help](#help)
  - [Exiting the program](#exiting-the-program)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction

parKING is a desktop app that helps drivers choose the best place to park via the Command Line Interface (CLI).
parKING allows users to search, save and look at car park availability information at a glance, while interfacing with 
LTA's real-time API. Whenever you launch parKING, it automatically get the latest car park availability and updates the
local data immediately. The best part about parKING is that it can be used offline (based on the data you have saved the 
last time you are connected to the internet).

With parKING, you can plan your parking destination in advance or simply check lot availability before you leave. We 
strive to become your driving companion, keeping you at ease while ensuring that your vehicle will always get a parking 
lot when you arrive at your destination. We got you and your loved ones parked!

### Disclaimer regarding use of API key for the purposes of NUS CS2113
We understand that an api key is something personal and should not be shared with everyone. However, for the purpose of
NUS CS2113, we have provided a default api key for testing purposes. **This default api key will be removed at the end of
module in Q4 2022**.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of parKING from [here](https://github.com/AY2223S1-CS2113-T17-4/tp/releases/download/v2.0/parKING.jar).
3. [__Recommended__] For continued usage of the application, do sign up for your personal API key through [LTA](https://datamall.lta.gov.sg/content/datamall/en/request-for-api.html).
4. Use the command `java -jar parKING.jar` to start the program!
5. Note that the programme will automatically create a directory called `Resources` to store local data files.
6. Here are some simple commands to get you started. 
   1. To authenticate using your personal api key, use the command [`auth API_KEY`](#authenticate-user-api).
   2. To get a detailed information on a carpark using its ID, use the command [`find CARPARK_ID`](#find-number-of-lots-by-carpark-id).
   3. To get the list of carpark by address keyword, use the command [`filter QUERY`](#filter-carparks-based-on-address).
   4. Use [`favourite CARPARK_ID`](#favourite-carparks-by-carpark-id)/[`unfavourite CARPARK_ID`](#unfavourite-carparks-by-carpark-id)
   to add / remove certain carparks into your favourite list.
      
Read in more detail at the [Features Section](#features) section below.

## Features

> Notes about the command format:
>  - Words in `UPPER_CASE` are the parameters supplied by the user.

| Command                  | Shortcuts         | Description                                                                                 |
|--------------------------|-------------------|---------------------------------------------------------------------------------------------| 
| `auth API_KEY`           | `a API_KEY`       | [Authenticate API using user's API key](#authenticate-user-api)                             |
| `auth default`           | `a default`       | [Authenticate API using default key](#authenticate-default)                                 |
| `auth status`            | `a status`        | [Authentication status](#authentication-status)                                             |
| `find CARPARK_ID`        | `fin CARPARK_ID`  | [Find number of lots available by carpark ID](#find-number-of-lots-by-carpark-id)           | 
| `filter QUERY`           | `fil QUERY`       | [Filter carparks based on address](#filter-carparks-based-on-address)                       | 
| `list`                   | `l`               | [Get a list of available carparks on the app](#get-a-list-of-carparks-on-the-app)           |
| `update`                 | `u`               | [Update data from API](#update-data-from-api)                                               |
| `favourite CARPARK_ID`   | `fav CARPARK_ID`  | [Favourite carparks by carpark ID](#favourite-carparks-by-carpark-id)                       |
| `unfavourite CARPARK_ID` | `ufav CARPARK_ID` | [Unfavourite carparks by carpark ID](#unfavourite-carparks-by-carpark-id)                   |
| `favourite list`         | `fav list`        | [List lot availability of favourite carparks](#list-lot-availability-of-favourite-carparks) |
| `help`                   | `h`               | [Lists all possible commands](#help)                                                        |   
| `exit`                   | `e`               | [Exiting the program](#exiting-the-program)                                                 |

### Authenticate user API

**Authenticate and subscribe to the API Service (LTA Data Mall) using the user’s API key.**
> Format : `auth API_KEY` or `a API_KEY`

- LTA Data Mall API information (click [here](https://datamall.lta.gov.sg/content/datamall/en/dynamic-data.html)).
- API Registration (click [here](https://datamall.lta.gov.sg/content/datamall/en/request-for-api.html)).
  API key will be emailed to you.
- `API_KEY` is case sensitive and unique.
- Upon successful authentication, the key will be stored locally and future authentication is not required.
- User can use `auth` to change the current key. Only one and the most recent key will be stored.
- If API key is invalid, the new key will not be stored and the previous key will be retained.
  If there are no API key beforehand, it will stay empty.
- Upon successful authentication, a fetching sequence will be initiated from the LTA API.

Example of usage:

**Input (assuming 12345678 is a valid API key)**

```auth 12345678```

**Output**
```
2312 Parking Lot data received from LTA!
Authenticated successfully.
```

### Authenticate default

**Authenticate using the default API key.**
> Format: `auth default` or `a default`

- For user with no API key, they can use the provided api key to access the services.
- User will not be able to see the api key.
- A fetching sequence will be initiated also from LTA API.

Example of usage:

**Input**

```auth default```

**Output**

```
2312 Parking Lot data received from LTA!
Authenticated successfully using default API Key.
```

### Authentication status

**Get the status of the api key authentication status.**
> Format: `auth status` or `a status`

- It will inform the user whether the user api key is entered and valid or whether the default API key is used.
- If user has entered and validated their personal key before, they will be able to view their own API key.
  Otherwise, the default API key will not be revealed to the user.

Example of usage:

**Input**

```auth status```

**Output**
1. If user has inputted his personal API key and is authenticated

```
You have authenticated your API key successfully. API key stored in the local file is ********
```

2. If user has not inputted his personal API key and/or not authenticated

```
You have not authenticated your personal API key. Currently you have access to the API but you are using our default key!
```

### Find number of lots by carpark ID

**Returns detailed information about the carpark that the user has chosen to find.**
> Format: `find CARPARK_ID` or `fin CARPARK_ID`

- Using data from the API, after the user has inputted the ID of a certain carpark,
  the function will return detailed information regarding the aforementioned carpark (this includes the number 
  of available carparks of different types).
- The command can only take a complete carpark ID. If the user inputs a non-existing carpark ID or inputs the wrong format,
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

**Shows a summarised list of carparks that match a given query.**
> Format: `filter QUERY` or `fil QUERY`

- The filter command allows the user to search a carpark based on the name of the carpark. For example,
  a carpark may have the name of `BLK 208 CLEMENTI AVE 6` - a query of `clementi` or `208` will return this
  carpark as one of its results.
- Multiple words can be input to narrow the filtered results further. Given the previous example, `clementi ave` will
  also give the above carpark as one of its results.
- Only a summarised list of carparks with minimal information will be shown.
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

### Get a list of carparks on the app

**Returns a list of the available carparks from the JSON file.**
> Format: `list` or `l`

- Using data from the API, after the user has inputted the command, the function will return a list of carparks from the API.

Example of usage:

**Input:**

 `list`

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

> Format: `update` or `u`

- The command requires a valid API access token to function.
- This overwrites any previous availability data for any carparks fetched by the API.
- This command is useful for when the program is left opened for a very long time, and the data that has been fetched by
the API at the start of the program becomes outdated. The user can then use this command to update the data fetched by
the API without having to exit and terminate the program.

Example of usage:

**Input**

 `update`

**Output**

```
2312 Parking Lot data received from LTA!
Update Successful.
```

### Favourite carparks by carpark ID

**Saves the carpark to a list of favourited carparks for easy subsequent access.**
> Format: `favourite CARPARK_ID` or `fav`

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
> Format: `unfavourite CARPARK_ID` or `ufav CARPARK_ID`

- The command requires user to input a valid and existing carpark ID that is already favourited.

Example of usage:

**Input (assuming carpark ID 1 is favourited):**

```unfavourite 1```

**Output:**

```
Removed Carpark 1 from favourites!
```

**Input (assuming carpark ID M32 is not favourited):**

```unfavourite M32```

**Output:**

```
Carpark not found in favourite list!
```

### List lot availability of favourite carparks

**Displays the name, ID and total lot availability of all favourite carparks.**
> Format: `favourite list` or `fav list`

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

### Help

**Returns a list of all the available command of the program.**
> Format : `help` or `h`

- This function will return a list of all the available commands to use and a description of their purpose.

Example of usage:

**Input:**

`help`

**Output**
```
Here are the list of available commands to use! 
Refer to the user guide at https://ay2223s1-cs2113-t17-4.github.io/tp/UserGuide.html for more information.
`help` 	: To display all possible commands.
`exit` 	: To quit parKING.
`list` 	: List the carparks and its details.
`auth API_KEY` 	: to authenticate your personal API key.
`auth default` 	: to authenticate using the default key provided by parKING.
`auth status` 	: to get the authentication status.
`update` 	: To fetch the latest data from LTA.
`filter KEYWORD` 	: Find carpark based on its address.
`find CARPARK_ID` 	: Display information about the queried carpark.
`favourite CARPARK_ID` 	: favourite carpark by its ID
`favourite list` 	: to get the list of favourited carparks.
`unfavourite CARPARK_ID` 	: unfavourite carpark by its ID.
```

### Exiting the program

**Terminates the program.**

> Format: `exit` or `e`

## FAQ

**Q**: How is the data for the carparks stored? Can I change or edit this data?

**A**: The carpark data is first fetched in a `.json` format, under
`.\resources\api\ltaResponse.json`. Then, this carpark data is written
to a `.txt` file at `.\resources\carparkList.txt`. If you wish to manually edit any
carpark data, you can do so as long as the format is maintained. These changes will be reflected
when you restart the program.

## Command Summary

| Command                  | Shortcuts         | Description                                                                                 |
|--------------------------|-------------------|---------------------------------------------------------------------------------------------| 
| `auth API_KEY`           | `a API_KEY`       | [Authenticate API using user's API key](#authenticate-user-api)                             |
| `auth default`           | `a default`       | [Authenticate API using default key](#authenticate-default)                                 |
| `auth status`            | `a status`        | [Authentication status](#authentication-status)                                             |
| `find CARPARK_ID`        | `fin CARPARK_ID`  | [Find number of lots available by carpark ID](#find-number-of-lots-by-carpark-id)           | 
| `filter QUERY`           | `fil QUERY`       | [Filter carparks based on address](#filter-carparks-based-on-address)                       | 
| `list`                   | `l`               | [Get a list of available carparks on the app](#get-a-list-of-carparks-on-the-app)           |
| `update`                 | `u`               | [Update data from API](#update-data-from-api)                                               |
| `favourite CARPARK_ID`   | `fav CARPARK_ID`  | [Favourite carparks by carpark ID](#favourite-carparks-by-carpark-id)                       |
| `unfavourite CARPARK_ID` | `ufav CARPARK_ID` | [Unfavourite carparks by carpark ID](#unfavourite-carparks-by-carpark-id)                   |
| `favourite list`         | `fav list`        | [List lot availability of favourite carparks](#list-lot-availability-of-favourite-carparks) |
| `help`                   | `h`               | [Lists all possible commands](#help)                                                        |   
| `exit`                   | `e`               | [Exiting the program](#exiting-the-program)                                                 |

