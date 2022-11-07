# User Guide

## Contents
<!-- TOC -->
  * [Introduction](#introduction)
    * [Disclaimer regarding use of API key for the purposes of NUS CS2113](#disclaimer-regarding-use-of-api-key-for-the-purposes-of-nus-cs2113)
  * [Quick Start](#quick-start)
  * [Features](#features)
    * [Authenticate user API](#authenticate-user-api)
    * [Authenticate default](#authenticate-default)
    * [Authentication status](#authentication-status)
    * [Find number of lots by carpark ID](#find-number-of-lots-by-carpark-id)
    * [Filter carparks based on address](#filter-carparks-based-on-address)
    * [Filter carparks based on carpark ID](#filter-carparks-based-on-carpark-id)
    * [Get a list of carparks on the app](#get-a-list-of-carparks-on-the-app)
    * [Update data from API](#update-data-from-api)
    * [Favourite carparks by carpark ID](#favourite-carparks-by-carpark-id)
    * [Unfavourite carparks by carpark ID](#unfavourite-carparks-by-carpark-id)
    * [List lot availability of favourite carparks](#list-lot-availability-of-favourite-carparks)
    * [List all possible commands](#list-all-possible-commands)
    * [Exiting the program](#exiting-the-program)
  * [Editing Files](#editing-files)
    * [How files are generated](#how-files-are-generated)
    * [`favourites.txt`  Favourites list](#favouritestxt-favourites-list)
      * [How files are saved and loaded](#how-files-are-saved-and-loaded)
      * [Supported editing behaviour](#supported-editing-behaviour)
    * [`secret.txt` Access token file](#secrettxt-access-token-file)
      * [How files are saved and loaded](#how-files-are-saved-and-loaded)
      * [Supported editing behaviour](#supported-editing-behaviour)
    * [`carparkList.txt`  Carparks list](#carparklisttxt-carparks-list)
      * [How files are saved and loaded](#how-files-are-saved-and-loaded)
      * [Supported editing behaviour](#supported-editing-behaviour)
    * [`ltaResponse.json` and `ltaResponseSample.json` JSON files from the API](#ltaresponsejson-and-ltaresponsesamplejson-json-files-from-the-api)
  * [FAQ](#faq)
  * [Command Summary](#command-summary)
<!-- TOC -->

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
2. Download the latest version of parKING from [here](https://github.com/AY2223S1-CS2113-T17-4/tp/releases/download/v2.1/parKING.jar).
3. [__Recommended__] For continued usage of the application, do sign up for your personal API key through [LTA](https://datamall.lta.gov.sg/content/datamall/en/request-for-api.html).
4. Use the command `java -jar parKING.jar` to start the program!
5. Note that the program will automatically create a directory called `resources` to store local data files.
6. Here are some simple commands to get you started. 
   1. To authenticate using your personal api key, use the command [`auth API_KEY`](#authenticate-user-api).
   2. To get a detailed information on a carpark using its ID, use the command [`find CARPARK_ID`](#find-number-of-lots-by-carpark-id).
   3. To get the list of carpark by address keyword, use the command [`filter QUERY`](#filter-carparks-based-on-address).
   4. Use [`favourite CARPARK_ID`](#favourite-carparks-by-carpark-id)/[`unfavourite CARPARK_ID`](#unfavourite-carparks-by-carpark-id)
   to add / remove certain carparks into your favourite list.
      
See more detail in the [Features Section](#features) section below.

## Features

> Notes about the command format:
>  - Words in `UPPER_CASE` are the parameters supplied by the user.

| Command                  | Shortcuts         | Description                                                                                |
|--------------------------|-------------------|--------------------------------------------------------------------------------------------| 
| `auth API_KEY`           | `a API_KEY`       | [Authenticate API using user's API key](#authenticate-user-api)                            |
| `auth default`           | `a default`       | [Authenticate API using default key](#authenticate-default)                                |
| `auth status`            | `a status`        | [Authentication status](#authentication-status)                                            |
| `find CARPARK_ID`        | `fin CARPARK_ID`  | [Find number of lots available by carpark ID](#find-number-of-lots-by-carpark-id)          | 
| `filter QUERY`           | `fil QUERY`       | [Filter carparks based on address](#filter-carparks-based-on-address)                      | 
| `filter -id QUERY`       | `fil -id QUERY`   | [Filter carparks based on carpark ID](#filter-carparks-based-on-carpark-id)                | 
| `list`                   | `l`               | [Get a list of available carparks on the app](#get-a-list-of-carparks-on-the-app)          |
| `update`                 | `u`               | [Update data from API](#update-data-from-api)                                              |
| `favourite CARPARK_ID`   | `fav CARPARK_ID`  | [Favourite carparks by carpark ID](#favourite-carparks-by-carpark-id)                      |
| `unfavourite CARPARK_ID` | `ufav CARPARK_ID` | [Unfavourite carparks by carpark ID](#unfavourite-carparks-by-carpark-id)                  |
| `favourite list`         | `fav list`        | [List lot availability of favourite carparks](#list-lot-availability-of-favourite-carparks) |
| `help`                   | `h`               | [List all possible commands](#list-all-possible-commands)                                                        |   
| `exit`                   | `e`               | [Exiting the program](#exiting-the-program)                                                |

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
- Note that it will also show empty car park lots.
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
Favourited: No
Number of available lots (total): 52
  Cars: 52 lots
  Motorcycles: 0 lots
  Heavy Vehicles: 0 lots
Last Updated: 04-11-2022 19:28:10
===========================================
```

### Filter carparks based on address

**Shows a summarised list of carparks that match a given query based on its address.**
> Format: `filter QUERY`, `fil QUERY` (dashed argument `-address` optional)

- The filter command allows the user to search a carpark based on the name of the carpark. For example,
  a carpark may have the name of `BLK 208 CLEMENTI AVE 6` - a query of `clementi` or `208` will return this
  carpark as one of its results.
- The query is case-insensitive.
- Multiple words can be input to narrow the filtered results further. Given the previous example, `clementi ave` will
  also give the above carpark as one of its results. The carparks displayed will have matches for **all** the space-separated words in the query.
- For convenience, the `filter` command also matches substrings at the _beginning_ of a word. Following the previous
  example, `clem` will also match the above carpark, but `ti` will not.
- Only a summarised list of carparks with minimal information will be shown.
- Note that it will also show empty car park lots.
- If supported by your console and operating system, matched words will be highlighted for ease of viewing.

> **Note:**  The `filter` command allows for dashed arguments `-address` and `-id` to switch modes, but if none is provided it defaults to address mode above.

Related: [Filter carparks based on carpark ID](#filter-carparks-based-on-carpark-id)

Example of usage:

**Input:**

```filter clem ave 2```

**Output:**
```    
CarparkID C28M at BLK 208 CLEMENTI AVE 6
--> 233 available lots total
CarparkID C5 at BLK 358-360,362-363,366-367 CLEMENTI AVE 2
--> 173 available lots total
CarparkID C6 at BLK 328-334 CLEMENTI AVE 2
--> 129 available lots total
CarparkID C7 at BLK 349-355 CLEMENTI AVE 2
--> 223 available lots total
CarparkID C8 at BLK 335/338 CLEMENTI AVE 2
--> 240 available lots total
```

### Filter carparks based on carpark ID

**Shows a summarised list of carparks that match a given query.**
> Format: `filter -id QUERY` or `fil -id QUERY`

- The filter command allows the user to search a carpark based on the carpark ID. Different from the `find` command, the `filter -id` command displays a list of partial matches. For example, a carpark with ID `A35` can be matched by `fil -id A` or `fil -id 35`.
- The query is case-insensitive.
- Unlike the `filter` command in address mode, rather than matching **all** words in the query, a carpark only needs to match **one or more** words in the query.
- For convenience, the `filter -id` command also matches substrings _anywhere_ in the ID. For example, any of `A35`, `a`, `5` will match the above carpark.
- Only a summarised list of carparks with minimal information will be shown.
- Note that it will also show empty car park lots.

> **Note:**  To use the `filter` command in ID mode, a dashed argument `-id` is **required**.

Related: [Filter carparks based on address](#filter-carparks-based-on-address)

Example of usage:

**Input:**

```filter -id 7a 8a```

**Output:**
```    
CarparkID B7A at BLK 216/218 BEDOK NORTH ST 1
--> 0 available lots total
CarparkID C18A at BLK 426/427 CLEMENTI AVE 3
--> 25 available lots total
CarparkID CK8A at BLK 203A CHOA CHU KANG AVE 1/CENTRAL
--> 297 available lots total
CarparkID T47A at BLK 864B TAMPINES STREET 83
--> 123 available lots total
```

### Get a list of carparks on the app

**Returns a list of the available carparks from the JSON file.**
> Format: `list` or `l`

- Using data from the API, after the user has inputted the command, the function will return a list of carparks from the API.
> **Note:** This produces a very long list with Carpark ID in alphabetical order and result in a long output list in your terminal. Do not use if you need to refer 
to output already printed to the console before this.

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

- The command requires a valid API access token and internet access to function.
- This overwrites any previous carpark availability data for any carparks fetched by the API.
- This command is useful for when the program is left opened for a very long time, and the data that has been fetched by
the API at the start of the program becomes outdated. The user can then use this command to update the data fetched by
the API without having to exit and terminate the program.

>  **Note:** Tampering with the `.json` files generated can result in the data fetched from the `update` command to be skipped and not loaded in. For more information see [`ltaResponse.json` and `ltaResponseSample.json` JSON files from the API](#ltaresponsejson-and-ltaresponsesamplejson-json-files-from-the-api)

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
> Format: `favourite CARPARK_ID` or `fav CARPARK_ID`

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

> For advanced users:
>  - Favourite multiple carparks at once by entering more than one carpark ID after `favourite`.
>  - Example of usage:
>    - `favourite 1 2 J8`

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

> For advanced users:
>  - Unfavourite multiple carparks at once by entering more than one carpark ID after `unfavourite`.
>  - Example of usage:
>    - `unfavourite 3 4 5`

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

### List all possible commands

**Returns a list of all the available command of the program.**
> Format : `help` or `h`

- This function will return a list of all the available commands to use and a description of their purpose.

Example of usage:

**Input:**

`help`

**Output**
```
Here are the list of available commands to use! Refer to the user guide at https://ay2223s1-cs2113-t17-4.github.io/tp/UserGuide.html for more information.
`help` or `h` 	: To display all possible commands.
`exit` or `e` 	: To quit parKING.
`list` or `l` 	: List the carparks and its details.
`auth API_KEY` or `a API_KEY` 	: to authenticate your personal API key.
`auth default` or `a default` 	: to authenticate using the default key provided by parKING.
`auth status` or `a status` 	: to get the authentication status.
`update` or `u` 	: To fetch the latest data from LTA.
`filter QUERY` or `fil QUERY` or `fil -address QUERY` 	: Find carparks based on its address.
`filter -id QUERY` or `fil -id QUERY` 	: Find carparks based on its Carpark Id.
`find CARPARK_ID` or `fin CARPARK_ID` 	: Display information about the specific queried carpark.
`favourite list` or `fav list` 	: to get the list of favourited carparks.
`favourite CARPARK_ID` or `fav CARPARK_ID` 	: favourite carpark by its ID.
`unfavourite CARPARK_ID` or `ufav CARPARK_ID` 	: unfavourite carpark by its ID.
```

### Exiting the program

**Terminates the program.**

> Format: `exit` or `e`

## Editing Files

Advanced users may want to enter data more efficiently, or customise the data in ways currently not possible through the CLI. 
For example, you may want to enter favourites without the need to run the program in a command prompt, or edit the names of carparks 
for convenient finding. Below are the various files generated by parKING.

### How files are generated

If no files exist at the time of running the program, the necessary files and directories will be created within the directory that the .jar file was run from.

### `favourites.txt`  Favourites list
Location: `.\resources\api\favourite\favourite.txt`
#### How files are saved and loaded

- This file contains the carpark IDs of all favourite carparks.
- When the user first starts the application, contents from this file will be read and stored.
- Contents are loaded and validated before every `favourite` and `unfavourite` command.
- Contents are written to this file after every `favourite` and `unfavourite` command.

#### Supported editing behaviour

- Each row contains the carpark ID of a carpark that is favourited.
- Users can edit the contents of this file while the application is running, but changes to the file will only be 
reflected after a `favourite` or `unfavourite` command.
- If users enter an invalid carpark ID, it will be removed from this file after a `favourite` or `unfavourite` command.

### `secret.txt` Access token file
Location: `.\resources\api\secret.txt`

#### How files are saved and loaded

- This file contains the API key entered by the user (default key is not stored here).
- Only when the API key is verified, will then the new key be written to this file.
- If there exists an existing key in the file, and the user successfully authenticate using
a new API key through the program, the existing key will be overwritten with the new one. If
the authentication fails, the existing key will not be overwritten

#### Supported editing behaviour

- The file will only be read during program startup. Thus, user's direct edit on the file during
the run of the program will have no effect on the program.
- After editing the file, reload the program.
  - If the key entered manually is an invalid key, the program will detect it and load the default
    key automatically. However, the secrets.txt will not be cleared. 
  - The text file will only be cleared when there is a successful authentication in the future.

### `carparkList.txt`  Carparks list

Location: `.\resources\carparkList.txt`

#### How files are saved and loaded

- The `carparksList.txt` contains data for all the carparks found in the program. It reflects the data stored within the program that is used by the commands. 
- After data is fetched from the LTA API, the resulting carpark list generated will update this file, changing the number of available lots.
- The file is read and written to and saved on initialisation of the program and every time the `update` command is called.

#### Supported editing behaviour

Each row corresponds to one carpark, and fields can be edited and retained to be updated. For example, a
carpark with a development of `BLK 309 CLEMENTI AVE 1` can be renamed to `Mum's place`, and future updates to this file
will retain that information for easy tracking and use within the program.


> Any files with invalid format will be **erased and written over** with a valid format from within the program. Please make sure that the format is strictly adhered to or risk losing all your data.

>**Note:** Updating this file requires both the incoming carpark data and the existing data to have the same Carpark ID. Changing the Carpark ID is **not recommended**, and files with duplicate Carpark ID will be discarded and regenerated.

Format of one row in the `carparkList.txt` file is shown below:
```
1 || Marina || Suntec City || 1.29375 103.85718 || 1051 || 1051 0 0 || false || LTA || 04-11-2022 19:28:10 
```
- Each row must be separated by a linebreak.
- Each field of data is separated by the double pipes delimiter `||`.
- The fields in order and what values are valid is below:
  - **Carpark ID**: 
    - Must not be blank
    - Can be in the following configurations:
      - Any number of letters: `a`, `B`, `ABCDE`
      - Any number of numbers: `1`, `34`, `340`
      - Any number of letters followed by any number of numbers: `A5`, `p3`, `abc123`
      - Any number of numbers followed by any number of letters: `5A`, `3b`, `123abc`
      - Any number of letters followed by any number of numbers and then any numbers of letters: `A5R`, `p3NBC`, `B132A` 
    - Any other configuration or characters will result in an invalid format. E.g. `35A9`, `29+A_`
  - **Area** (unused by ParKING): 
    - Typically the region or area the carpark is in.
    - Can be blank
    - Any values not containing `||`
  - **Development**: 
    - Typically the address or name of the mall. 
    - Any values not containing `||`
  - **Location** (unused by ParKING):
    - Longitude and latitude of the carpark.
    - Can be blank
    - Any values not containing `||`
  - **Available Lots**:
    - The total number of available lots.
    - Must not be blank
    - Must be a non-negative integer: any floats or negative values will be defaulted to `0`
    - _Note: Typically unused. This value is updated based on the "All Available Lots" field below._
  - **All Available Lots**:
    - Space-separated breakdown of available lots for different lot types.
    - Must not be blank
    - Must have exactly **three** space-separated numbers, representing three different lot types in order: Car, Motorcycle, Heavy Vehicle
    - Each number must be a non-negative integer: any floats or negative values will be defaulted to `0`
  - **Favourited**:
    - If at the time of saving the list was favourited (true/false).
    - **Note:** This value is only for the convenience of the user when viewing the file and **will not update the program on change**. Edit `favourites.txt` to change the favourites list.
  - **Agency**  (unused by ParKING):
    - Represents the agency which provided the data.
    - Can be blank
  - **Last Updated Time**:
    - Represents the last time this carpark was updated.
    - Must be in `DD-MM-YY HH:MM:SS` format, and must be a valid date-time (`36:99:99` is an invalid time)
    - **Note:** May be overwritten in the program, if the carpark was updated upon initialisation or with the `update` command.
  
- Any extra delimiters `||` or not enough delimiters will result in an invalid format.
- Unused fields exist only for the convenience of the user to view the carpark list, and are free to be edited or even left blank. These fields do not affect the program in any way and are left over from the API.

### `ltaResponse.json` and `ltaResponseSample.json` JSON files from the API

> **WARNING:** These are raw files generated from data fetched from the API, and are not recommended to be edited. Any files with formatting errors will be deleted completely and **will not** be loaded in to the program. As such, **editing these files is unsupported**.

`ltaResponse.json` is a file with data generated by the API over the internet, fetched whenever `update` is used or on initialisation of the program. If the program fails to fetch data from the API or it is in an incorrect format, this file will be skipped and **not loaded in**.

`ltaResponseSample.json` is backup data generated from the program internally, meant as a "demo mode" to test and use parKING's features offline if there isn't an internet connection. If the program fails to load properly the data from `ltaResponse.json`, data from `ltaResponseSample.json` is loaded in instead. 

If both `.json` files produce errors, a valid `ltaResponseSample.json` file is regenerated from within the program and then loaded in.

>**Do not edit** these files unless you know what you are doing. Edit the `carpark.txt` file instead if you would like to change details about the carparks.

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
| `find CARPARK_ID`        | `fin CARPARK_ID`  | [Find number of lots by carpark ID](#find-number-of-lots-by-carpark-id)                     | 
| `filter QUERY`           | `fil QUERY`       | [Filter carparks based on address](#filter-carparks-based-on-address)                       | 
| `filter -id QUERY`       | `fil -id QUERY`   | [Filter carparks based on carpark ID](#filter-carparks-based-on-carpark-id)                 | 
| `list`                   | `l`               | [Get a list of available carparks on the app](#get-a-list-of-carparks-on-the-app)           |
| `update`                 | `u`               | [Update data from API](#update-data-from-api)                                               |
| `favourite CARPARK_ID`   | `fav CARPARK_ID`  | [Favourite carparks by carpark ID](#favourite-carparks-by-carpark-id)                       |
| `unfavourite CARPARK_ID` | `ufav CARPARK_ID` | [Unfavourite carparks by carpark ID](#unfavourite-carparks-by-carpark-id)                   |
| `favourite list`         | `fav list`        | [List lot availability of favourite carparks](#list-lot-availability-of-favourite-carparks) |
| `help`                   | `h`               | [Lists all possible commands](#list-all-possible-commands)                                                        |   
| `exit`                   | `e`               | [Exiting the program](#exiting-the-program)                                                 |

