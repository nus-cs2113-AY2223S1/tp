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

Format: `add food /{description} /{calories}`

* description needs to be a string
* calories should be positive integer inputs in the units of kcal

Example of usage:

`add food /ice cream /350`

Expected outcome:
Food is added to the dietary consumption list.

```
-------------------------------------------------------------
Food Description: ice cream
calories: 250
This food is added to the food list successfully
-------------------------------------------------------------
```


### Viewing Food Consumptions: `view food`
View user's food consumption over time in TracknFit

Format: `view food`

Example of usage:
`view food`

Expected outcome:
All historical records of the food consumed are displayed.
```
-------------------------------------------------------------
----------------------------------
Index | Description  | Calories | 
----------------------------------
1     | cola         | 100      | 
2     | chicken rice | 200      | 
3     | laksa        | 400      | 
4     | ice cream    | 250      | 

-------------------------------------------------------------
```

### Remove Food Consumptions: `remove food`
Remove a specified record from food list in TracknFit

Format: `remove food /{index}`

Example of usage:
`remove food /1`

Expected outcome:
The first record in the food list will be deleted.
```
-------------------------------------------------------------
 This food has been deleted from the food list successfully
Food Description: cola
calories: 100
-------------------------------------------------------------
```

### Find Food Consumptions: `find food`
View user's food consumption over time in TracknFit

Format: `find food /{description}`

Example of usage:
`find food /laksa`

Expected outcome:
All relevant records from the history would be printed out for the user to see how much of a specific food they 
have been consuming.
```
-------------------------------------------------------------
Here are the matching food in your food list:
----------------------------------
Index | Description  | Calories | 
----------------------------------
1     | laksa        | 400      | 

-------------------------------------------------------------
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
