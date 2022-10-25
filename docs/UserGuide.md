# User Guide

## Introduction

TracknFit is a fitness tracking application for fitness enthusiasts. It allows users to track their 
exercise routines, nutritional habits and biometrics, to help the user better understand and progress
towards their fitness journeys.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY2223S1-CS2113-W12-3/tp).

## Features 

{Give detailed description of each feature}  
Set your biometrics within the app  
Based on your fitness goals and biometrics, TracknFit can calculate recommended daily caloric balance [coming soon]
Add a record of weight and fat percentage  
View records of weight and fat percentage  

**Records are displayed in order of date by default**  
**Records are automatically saved when exiting TracknFit, and loaded when starting TracknFit**  

### Setting Biometrics: `set biometrics`
Sets user biometrics in TracknFit

Format: `set biometrics /{age} /{gender} /{height} /{weight} /{fat percentage}`

* age, height, weight and fat percentage should be integer values
* gander can only be female, male or others
* height should be in units of cm and cannot exceed 300cm
* weight should be in units of kg and cannot exceed 400kg
* fat percentage should be between 0% and 100%

Example of usage:

`set biometrics /15 /male /146 /98 /55`

### Adding Food Consumptions: `add food`
Add user's food consumption in TracknFit

Format: `add food /{description} /{calories} /{date}`

* description needs to be a string
* calories should be positive integer inputs in the units of kcal
* date is an optional parameter. If user does not input a specific date,
the program will automatically fill it with today's date

Example of usage:

Adding without date:

`add food /dumplings /300`

Expected outcome:
Food is added to the dietary consumption list.

```
add food /dumplings /300
-------------------------------------------------------------------------------
Food Description: dumplings
calories: 300
Recorded on: 25-10-2022
 This food is added to the food list successfully
-------------------------------------------------------------------------------
```
Adding with date:

`add food /noodles /300 /24-10-2022`

Expected outcome:
Food is added to the dietary consumption list.

```
add food /noodles /300 /24-10-2022
-------------------------------------------------------------------------------
Food Description: noodles
calories: 300
Recorded on: 24-10-2022
 This food is added to the food list successfully
-------------------------------------------------------------------------------
```


### Viewing Food Consumptions: `view food`
View user's food consumption over time in TracknFit

Format: `view food`

Example of usage:
`view food`

Expected outcome:
All historical records of the food consumed are displayed.
```
-------------------------------------------------------------------------------
--------------------------------------------------------------------------------------
Index | Description                                         | Calories | Date       | 
--------------------------------------------------------------------------------------
1     | noodles                                             | 300      | 24-10-2022 | 
2     | chicken rice                                        | 200      | 25-10-2022 | 
3     | laksa                                               | 400      | 25-10-2022 | 
4     | cola                                                | 1000     | 25-10-2022 | 
5     | this is just for testing                            | 100      | 25-10-2022 | 
6     | ice cream                                           | 200      | 25-10-2022 | 
7     | very very very very long food name for testing view | 300      | 25-10-2022 | 
8     | dumplings                                           | 300      | 25-10-2022 | 

-------------------------------------------------------------------------------

```

### Remove Food Consumptions: `remove food`
Remove a specified record from food list in TracknFit

Format: `remove food /{index}`

Example of usage:
`remove food /1`

Expected outcome:
The first record in the food list will be deleted.
```
-------------------------------------------------------------------------------
remove food /1
-------------------------------------------------------------------------------
 This food has been deleted from the food list successfully
Food Description: noodles
calories: 300
Recorded on: 24-10-2022
-------------------------------------------------------------------------------

```

### Find Food Consumptions Based On Description: `find food`
Find specific food records from user's food consumption over time using keyword in TracknFit

Format: `find food /{description}`

Example of usage:
`find food /laksa`

Expected outcome:
All relevant records from the history would be printed out for the user to see how much of a specific food they 
have been consuming.
```
-------------------------------------------------------------------------------
Here are the matching food in your food list:
-----------------------------------------------
Index | Description  | Calories | Date       | 
-----------------------------------------------
1     | laksa        | 400      | 25-10-2022 | 

-------------------------------------------------------------------------------
```

### Find Food Consumptions Based On Date: `find date_f`
Find specific food records from user's food consumption over time using keyword in TracknFit

Format: `find date_f /{date}`

Example of usage:
`find date_f /25-10-2022`

Expected outcome:
All relevant records from the history would be printed out for the user to see how much of a specific food they
have been consuming.
```
-------------------------------------------------------------------------------

Here are the food records in your list matching this date:
--------------------------------------------------------------------------------------
Index | Description                                         | Calories | Date       | 
--------------------------------------------------------------------------------------
1     | chicken rice                                        | 200      | 25-10-2022 | 
2     | laksa                                               | 400      | 25-10-2022 | 
3     | cola                                                | 1000     | 25-10-2022 | 
4     | this is just for testing                            | 100      | 25-10-2022 | 
5     | ice cream                                           | 200      | 25-10-2022 | 
6     | very very very very long food name for testing view | 300      | 25-10-2022 | 
7     | dumplings                                           | 300      | 25-10-2022 | 

-------------------------------------------------------------------------------

```

### Viewing All Daily Records: `view all`
View user's daily records over time in TracknFit

Format: `view all`

Example of usage:
`view all`

Expected outcome:
All daily historical records of the user are displayed.
```
-------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------
           | Weight&Fat              | Food                                                           | Exercise
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Date       | Weight | Fat Percentage | Description                                         | Calories | Exercise   | Weights    | Sets | Reps | Dist | Calories Burnt  | Status
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
23-10-2022 | 70     | 20             | 
25-10-2022                           | chicken rice                                        | 200      | 
25-10-2022                           | laksa                                               | 400      | 
25-10-2022                           | cola                                                | 1000     | 
25-10-2022                           | this is just for testing                            | 100      | 
25-10-2022                           | ice cream                                           | 200      | 
25-10-2022                           | very very very very long food name for testing view | 300      | 
25-10-2022                           | dumplings                                           | 300      | 
25-10-2022                                                                                            | strength   | 20         | 2    | 2    | -    | -               | [ ]

-------------------------------------------------------------------------------

```


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Display help message `help`
* Exit TracknFit `exit`
* Set biometrics `set biometrics /{age} /{gender} /{height} /{weight} /{fat percentage}`
* Add weight and fat percentage `add weight /{weight} /{fat percentage}`
* Add food consumed `add food /{description} /{calories}`
* View weight and fat percentage records `view weight`
* View food records `view food`
* Delete a food record `remove food /{index}`
* Find food records using a keyword `find food /{description}`
* 
