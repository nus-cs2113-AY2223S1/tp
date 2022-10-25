# User Guide

## Introduction

TracknFit is a fitness tracking application for fitness enthusiasts. It allows users to track their
exercise routines, nutritional habits and biometrics, to help the user better understand and progress
towards their fitness journeys.

- Quick Start
- Features
    - Viewing help: `help`
    - Setting your biometrics within the app: `set biometricss`
    - Based on your fitness goals and biometrics, TracknFit can
      calculate recommended daily caloric
      balance [coming soon]
    - Add a record of weight and fat percentage: `add weight`
    - View records of weight and fat percentage: `view weight`
    - Adding food consumption: `add food`
    - Viewing food consumptions: `view food`
    - Remove food: `remove food`
    - Find food consumptions: `find food`
    - Adding a strength exercise: `add strength`
    - Viewing strength exercise: `view strength`
    - Finding strength exercise: `find strength`
    - Viewing exercise: `view exercise`
    - Marking exercise as done or undone: `mark`
- FAQ
- Command summary

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY2223S1-CS2113-W12-3/tp).

## Features

- **Records are displayed in order of date by default**
- **Records are automatically saved when exiting TracknFit, and loaded when starting TracknFit**
- **Words in curly brackets are parameters to be supplied by the user which are separated by forward slash.  
  e.g. in `set biometrics /{age} / {gender} /{height}`, age, gender and height are parameters separated by `/` which can
  be
  used as part of set biometrics command.**
- **Items in square brackets are optional.  
  e.g. `add strength /{name} /{weight} /{sets} /{reps} [/{date}]`, date can be omitted from the add exercise command and
  the
  date for the current day will be used.**
- **Parameters must follow the order as instructed**

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

### Adding strength exercise: `add strength`

Add strength exercise into the exercise list

Format: `add strength /{exercise name} /{weight} /{sets} /{reps} [/{date}]`

- exercise name must be within 50 characters
- weight must be positive int and less or equal to 1000.
- sets must be positive int and less or equal to 500
- reps must be positive int and less or equal to 500
- date must be in the format of `dd-MM-yyyy` and cannot be before the current date.
- the current date will be set if date is omitted.

Example of usage: `add strength /squat /100 /5 /5 /28-10-2022`

Expected outcome: Strength exercises added to the exercise list.

```
-------------------------------------------------------------------------------
Strength Exercise: squat
Weight: 100kg
Sets: 5
Repetitions: 5
Date: 28-10-2022
Status: [ ]
This strength exercise is added to the exercise list successfully
-------------------------------------------------------------------------------
```

### Viewing strength exercise: `view strength`

View user's strength exercises record in TracknFit

Format: `view strength [/{done}]`

- strength exercises yet to be completed will be displayed if `/{done}` is omitted
- strength exercises completed will be displayed if `/done` is included.

Example of usage: `view strength /done`

Expected outcome: Uncompleted strength exercises will be displayed

```
-------------------------------------------------------------------------------
Index | Exercise | Weight | Sets | Reps | Dist | Calories | Date       | Status
-------------------------------------------------------------------------------
1     | press    | 120    | 5    | 5    | -    | -        | 27-10-2022 | [X]
2     | squat    | 100    | 5    | 5    | -    | -        | 28-10-2022 | [X]

-------------------------------------------------------------------------------
```

### Finding strength exercise: `find strength`

View user's progress for strength exercise overtime

Format: find strength /{exercise name}

- exercise name must be within 50 characters

Example of usage: `find strength /squat`

Expected outcome: Strength exercises whose name contain the search name will be displayed.

```
Here are the matching strength exercises in your list:
-------------------------------------------------------------------------------
Index | Exercise | Weight | Sets | Reps | Dist | Calories | Date       | Status
-------------------------------------------------------------------------------
1     | squat    | 125    | 5    | 5    | -    | -        | 20-10-2022 | [X]
2     | squat    | 100    | 5    | 5    | -    | -        | 28-10-2022 | [ ]

-------------------------------------------------------------------------------
```

### Viewing exercise: `view exercise`

View all exercises in the exercise list

Format: view exercise [/{done}]

- exercises yet to be completed will be displayed if `/{done}` is omitted
- exercises completed will be displayed if `/done` is included.

Example of usage: `view exercise /done`

Expected outcome: All completed exercises will be displayed.

```
-------------------------------------------------------------------------------
Exercises completed: 2
-------------------------------------------------------------------------------
Index | Exercise | Weight | Sets | Reps | Dist | Calories | Date       | Status
-------------------------------------------------------------------------------
1     | easy jog | -      | 1    | 1    | 2.4  | 113      | 27-02-2022 | [X]
2     | press    | 120    | 5    | 5    | -    | 47       | 27-08-2022 | [X]

-------------------------------------------------------------------------------

-------------------------------------------------------------------------------
```

### Marking exercise as done or undone: `mark`

Mark undone exercise displayed by `view exercise` as done.  
Mark completed exercise displayed by `view exercise /done` as undone

Format:

1. `mark done /{index} /{time} /{met}`
2. `mark undone /{index}`

- index must be within the limit of the list displayed.
- time can be a double, must be positive and smaller than 1440 mins
- Metabolic equivalent (met) a double, must be positive and smaller than 50.
- met can be used to measure the intensity of the activity and is used to calculate calories burnt for the exercise. Use
  the reference below as a guide to estimate the intensity of the exercise.
    - e.g.
        - Walking: 3.0
        - Light calisthenics (e.g. push ups, jumping jacks): 4
        - WeightLifting: 6.0
        - circuit training: 8.0
        - Running(7min/km): 8.0
        - Running(5min/km): 12

Example of usage: `mark undone /1`

Expected outcome: Selected exercise mark as undone.

```
-------------------------------------------------------------------------------
squat is marked as undone successfully
-------------------------------------------------------------------------------
```

Example of usage: `mark done /3 /10 /5.6`

Expected outcome: Selected exercise mark as done.

```
mark done /3 /10 /5.5
-------------------------------------------------------------------------------
squat is marked as done successfully
calories:62
-------------------------------------------------------------------------------
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Transfer the data.txt at the project root directory to another computer.

## Command Summary

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
