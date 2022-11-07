# User Guide

## Introduction

TracknFit is a fitness tracking application for fitness enthusiasts. It allows users to track their
exercise routines, nutritional habits and biometrics, to help the user better understand and progress
towards their fitness journeys.

- Quick Start
- Features
- FAQ
- Command summary

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY2223S1-CS2113-W12-3/tp).

## Features

TracknFit offers 3 main components: **Biometrics, Exercises, and Food**  
For each component, you can **add, remove and view** records. Additionally, **find** command
is available for **Exercises and Food** components.

- Records are displayed in descending order of date by default

TracknFit can also summarise your caloric balance for each day.  
Please follow these guidelines when entering commands into the terminal:

- Words in curly brackets are parameters to be supplied, separated by backslashes `/`.  
  e.g. in `set biometrics /{age} /{gender} /{height} /{activity level}`, age, gender, height and activity level
  are parameters separated by `/` to be provided when using the `set biometrics` command.
    - **Parameters must be entered in the same order as described.**

- Parameters in square brackets `[]` are optional.  
  e.g. in `add strength /{name} /{weight} /{sets} /{reps} [/{date}]`, the `add strength` command can be used with or
  without a date parameter.
    - **If no date is entered for a new record, the current local date on your computer will be used.**
- All dates should be entered in the format `DD-MM-YYYY`. For example, `31-12-2020`.
- Parameters must not contain `/` within them. For example, `cheese/burger` is an invalid name for food

Records are automatically loaded when starting TracknFit, and saved when exiting TracknFit. Note that you must exit
TracknFit using the `exit` command (and not by closing the terminal) in order to save your changes.

### General

#### Display help message: `help`

Display the help message  

Format: `help`  

Example of usage: `help`  

Expected outcome: help message is displayed

```
Help will always be given at Hogwarts to those who ask for it
Available commands:
help
exit
set biometrics /{age} /{gender} /{height} /{activity level}
view {biometrics/food/exercise/strength/cardio/weight/bmi/maintenance/calories/all}
view {exercise/strength/cardio} /{done}
add strength /{description} /{weight} /{sets} /{repetitions} [/{date}]
add cardio /{description} /{distance} /{repetitions} [/{date}]
add food /{description} /{calories} [/{date}]
add weight /{weight} /{fat percentage} [/{date}]
remove {food/exercise/weight} /{record index}
mark {done} /{exercise index} /{time} /{metabolic equivalent}
mark {undone} /{exercise index}
find {strength/cardio/food} /{description}
find {calories} /{date}
Please read the user guide for more detailed explanations
```

#### Viewing All Daily Records: `view all`

View user's daily records over time in TracknFit

Format: `view all`

Example of usage:
`view all`

Expected outcome:
All daily historical records of the user are displayed.

```
All Records: 8
-----------------------------------------------------------------------------------------------------------
           | Weight&Fat              | Food                                                      | Exercise
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Date       | Weight | Fat Percentage | Description                                    | Calories | Exercise   | Weights    | Sets | Reps | Dist | Calories Burnt  | Status
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
06-11-2022 | 70     | 10             | 
06-11-2022 |                         | chicken rice                                   | 200      | 
06-11-2022 |                         | very very very very long food name for testing | 1000     | 
06-11-2022 |                                                                                     | weights    | 30         | 10   | 2    | -    | -               | [ ]
05-11-2022 |                         | noodles                                        | 300      | 
05-11-2022 |                                                                                     | running    | -          | 1    | 1    | 1.0  | -               | [ ]
04-11-2022 |                         | laksa                                          | 400      | 
03-11-2022 | 60     | 20             | 

--------------------------------------------------------------------------------
```

#### View Calories : `view calories`

Allows the user to check his/her total calorie consumption/burn and net surplus/deficit by date

Format : view calories

Example of usage: `view calories`

Expected outcome: The total calorie consumption/burn and net surplus/deficit of the user will be displayed by date

```
Calories Records: 2
--------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
Date       | Calories Consumed | Calories Burnt | Net Calories | Status                             | 
------------------------------------------------------------------------------------------------------
31-10-2022 | 400               | 1071           | -671         | Your calorie deficit is too high!  | 
30-10-2022 | 600               | 96             | 504          | Your calorie surplus is too much!  | 
--------------------------------------------------------------------------------
```

#### Find Calories : `find calories`

Allows the user to check his/her total calorie consumption/burn and net surplus/deficit on that date

Format: `find calories /date`

Example of usage: `find calories /30-11-2022`

Expected outcome:  The total calorie consumption/burn and net surplus/deficit of the user on that date will be
displayed.

```
--------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
Date       | Calories Consumed | Calories Burnt | Net Calories | Status                             |
------------------------------------------------------------------------------------------------------
30-11-2022 | 600               | 96             | 504          | Your calorie surplus is too much!  |
--------------------------------------------------------------------------------
```

### Biometrics

#### Setting Biometrics: `set biometrics`

Sets user biometrics in TracknFit

Format: `set biometrics /{age} /{gender} /{height} /{activity level}`

* age, height and activity level should be integer values
* age must be less than 120
* gender can only be female, male or others
* height should be in units of cm and cannot exceed 300cm
* activity level should be between 1 and 5

Example of usage: `set biometrics /15 /male /146 /2`

Expected outcome: biometrics is set successfully

```
Biometrics set:
Age: 15
Gender: male
Height: 146cm
Activity Level: 2
```

#### Adding weight and fat record: `add weight`

Adds a record of weight and fat to TracknFit

Format: `add weight /{weight} /{fat percentage} [/{date}]`

* weight and fat percentage should be integer values
* weight should be in units of kg and cannot exceed 400kg
* fat percentage should be between 1% and 99%
* date, if provided, cannot be in the future

Example of usage: `add weight /133 /42`

Expected outcome: weight and fat record is added successfully

```
Weight: 133kg
Fat percentage: 42%
Date: 07-11-2022
 Weight and fat percentage are recorded successfully
```

#### Viewing weight and fat records: `view weight`

Views all records of weight and fat stored in TracknFit

Format: `view weight`

Example of usage: `view weight`

Expected outcome: all weight and fat records displayed in descending order of date

```
3 records of weight and fat percentage:
-----------------------------------------------
Index | Weight | Fat Percentage | Date       | 
-----------------------------------------------
1     | 133    | 42             | 07-11-2022 | 
2     | 122    | 22             | 12-10-2022 | 
3     | 118    | 33             | 08-04-2022 | 

```

#### Removing weight and fat record: `remove weight`

Removes a record of weight and fat from TracknFit

Format: `remove weight /{record index}`

* record index corresponds with the index when viewing weight records

Example of usage: `remove weight /2`

Expected outcome: weight and fat record is removed successfully

```
This weight and fat record has been deleted successfully!
Weight: 122kg
Fat percentage: 22%
Date: 12-10-2022
```

#### View BMI : `view bmi`

Allows the user to check their BMI

Format : view bmi

Example of usage: `view bmi`

Expected outcome: bmi of user will be displayed

```
Your current BMI is : 25.2
You are currently in the overweight range
```

#### View Maintenance Calories : `view maintenance`

Allows the user to check his/her maintenance calories based on her biometrics

Format : view maintenance

Example of usage: `view maintenance`

Expected outcome: Suggested maintenance calories of user will be displayed

```
You have a moderately active lifestyle!
Thus your maintenance calories is 2350
```

#### View Biometrics  : `view biometrics`

Allows the user to check his/her biometrics

Format : view biometrics

Example of usage: `view biometrics`

Expected outcome: Current biometrics of user will be displayed

```
Biometrics:
Age: 15
Gender: male
Height: 176cm
Weight: 83kg
Fat percentage: 23%
Activity Level: 2
```

### Exercises

#### Adding strength exercise: `add strength`

Add strength exercise into the exercise list

Format: `add strength /{exercise name} /{weight} /{sets} /{reps} [/{date}]`

- exercise name must be within 50 characters
- weight must be positive int and less or equal to 1000.
- sets must be integer ranges from 1 to 100 inclusive
- reps must be integer ranges from 1 to 100 inclusive.
- date must be in the format of `dd-MM-yyyy` and cannot be more than a month after.
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

#### Viewing strength exercise: `view strength`

View user's strength exercises record in TracknFit

Format: `view strength [/{done}]`

- strength exercises yet to be completed will be displayed if `/{done}` is omitted
- strength exercises completed will be displayed if `/done` is included.

Example of usage: `view strength /done`

Expected outcome: Uncompleted strength exercises will be displayed

```
-------------------------------------------------------------------------------

Strength exercises completed: 1
-------------------------------------------------------------------------------
Index | Exercise | Weight | Sets | Reps | Dist | Calories | Date       | Status
-------------------------------------------------------------------------------
1     | press    | 1      | 2    | 3    | -    | 49       | 06-11-2022 | [X]

-------------------------------------------------------------------------------

```

#### Finding strength exercise: `find strength`

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

Example of usage: `find strength /x`

Expected outcome: No matching found message displayed

```
-------------------------------------------------------------------------------
No matching strength exercise found
-------------------------------------------------------------------------------
```

#### Adding cardio exercise: `add cardio`

Add cardio exercise into the exercise list

Format: `add cardio /{description} /{distance} /{repetitions} [/{date}]`

- exercise name must be within 50 characters
- distance must be positive int and less or equal to 100.
- repetitions must be positive int and less or equal to 50
- date must be in the format of `dd-MM-yyyy` and cannot be more than a month after.
- the current date will be set if date is omitted.

Example of usage: `add cardio /sprints /1.2 /1 /28-10-2022`

Expected outcome: Cardio exercises added to the exercise list.

```
-------------------------------------------------------------------------------
Cardio Exercise: sprints
Distance: 1.2km
Repetitions: 1
Date: 28-10-2022
Status: [ ]
 This cardio exercise is added to the exercise list successfully
-------------------------------------------------------------------------------
```

#### Viewing cardio exercise: `view cardio`

View user's cardio exercises record in TracknFit

Format: `view cardio [/{done}]`

- cardio exercises yet to be completed will be displayed if `/{done}` is omitted
- cardio exercises completed will be displayed if `/done` is included.

Example of usage: `view cardio /done`

Expected outcome: Completed cardio exercises will be displayed

```
Index | Exercise | Weight | Sets | Reps | Dist | Calories | Date       | Status
-------------------------------------------------------------------------------
1     | sprints  | -      | 1    | 1    | 1.2  | -        | 25-10-2022 | [X]
2     | sprints  | -      | 3    | 2    | 3.2  | -        | 28-10-2022 | [X]

-------------------------------------------------------------------------------
```

#### Finding cardio exercise: `find cardio`

View user's progress for cardio exercise overtime

Format: find cardio /{exercise name}

- exercise name must be within 50 characters

Example of usage: `find cardio /sprints`

Expected outcome: Cardio exercises whose name contain the search name will be displayed.

```
Here are the matching cardio exercises in your list:
-------------------------------------------------------------------------------
Index | Exercise | Weight | Sets | Reps | Dist | Calories | Date       | Status
-------------------------------------------------------------------------------
1     | sprints  | -      | 1    | 1    | 1.2  | -        | 25-10-2022 | [ ]
2     | sprints  | -      | 3    | 2    | 3.2  | -        | 28-10-2022 | [ ]

-------------------------------------------------------------------------------
```

#### Viewing exercise: `view exercise`

View all exercises in the exercise list

Format: view exercise [/{done}]

- exercises yet to be completed will be displayed if `/{done}` is omitted
- exercises completed will be displayed if `/done` is included.

Example of usage: `view exercise /done`

Expected outcome: All completed exercises will be displayed.

```
Exercises completed: 2
-------------------------------------------------------------------------------
Index | Exercise | Weight | Sets | Reps | Dist | Calories | Date       | Status
-------------------------------------------------------------------------------
1     | easy jog | -      | 1    | 1    | 2.4  | 113      | 27-02-2022 | [X]
2     | press    | 120    | 5    | 5    | -    | 47       | 27-08-2022 | [X]

-------------------------------------------------------------------------------

```

#### Remove exercise: `remove exercise`

Remove a specified record from exercise list in TracknFit

Format: `remove exercise /{index}`

Example of usage:
`remove exercise /2`

Expected outcome:
The record of the corresponding index in the exercise list will be deleted.

```
-------------------------------------------------------------------------------
remove exercise /2
-------------------------------------------------------------------------------
This exercise has been deleted from the exercise list successfully!
Strength Exercise: squats
Weight: 100kg
Sets: 5
Repetitions: 5
Date: 05-11-2022
Status: [ ]
-------------------------------------------------------------------------------

```

#### Marking exercise as done or undone: `mark`

Mark undone exercise displayed by `view exercise` as done.  
Mark completed exercise displayed by `view exercise /done` as undone

Format:

1. `mark done /{index} /{time} /{met}`
2. `mark undone /{index}`

- index must be within the limit of the list displayed.
- time can be a double with 1 d.p, must be positive and smaller than 1440 mins
- Metabolic equivalent (met) a double with 1 d.p, must be positive and smaller than 50.
- met can be used to measure the intensity of the activity and is used to calculate calories burnt for the exercise. Use
  the reference below as a guide to estimate the intensity of the exercise.
    - e.g.
        - Walking: 3.0
        - Light calisthenics (e.g. push ups, jumping jacks): 4.0
        - WeightLifting: 6.0
        - circuit training: 8.0
        - Running(7min/km): 8.0
        - Running(5min/km): 12.0

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

### Food

#### Adding Food Consumptions: `add food`

Add user's food consumption in TracknFit

Format: `add food /{description} /{calories} [/{date}]`

<<<<<<< HEAD
* calories should be positive integer inputs in the units of kcal and must not exceed 10000kcal
* If user does not input a specific date, the program will automatically fill it with today's date
=======
* description needs to be a string
* calories should be positive integer inputs in the units of kcal
* date is an optional parameter. date must be in the format of `dd-MM-yyyy` and cannot be more than a month after. If user does not input a specific date,
  the program will automatically fill it with today's date
>>>>>>> newbranch

Example of usage:

Adding without date:

`add food /dumplings /300`

Expected outcome:
Food is added to the dietary consumption list.

```
--------------------------------------------------------------------------------
Food Description: dumplings
calories: 300
Recorded on: 06-11-2022
 This food is added to the food list successfully
--------------------------------------------------------------------------------
```

Adding with date:

`add food /laksa /400 /04-11-2022`

Expected outcome:
Food is added to the dietary consumption list.

```
-------------------------------------------------------------------------------
Food Description: laksa
calories: 400
Recorded on: 04-11-2022
 This food is added to the food list successfully
-------------------------------------------------------------------------------
```

#### Viewing Food Consumptions: `view food`

View user's food consumption over time in TracknFit

Format: `view food`

Example of usage:
`view food`

Expected outcome:
All historical records of the food consumed are displayed.

```
Food Records: 5
---------------------------------------------------------------------------------
Index | Description                                    | Calories | Date       | 
---------------------------------------------------------------------------------
1     | dumplings                                      | 300      | 06-11-2022 | 
2     | chicken rice                                   | 200      | 06-11-2022 | 
3     | very very very very long food name for testing | 1000     | 06-11-2022 | 
4     | noodles                                        | 300      | 05-11-2022 | 
5     | laksa                                          | 400      | 04-11-2022 | 

--------------------------------------------------------------------------------
```

#### Remove Food Consumptions: `remove food`

Remove a specified record from food list in TracknFit

Format: `remove food /{index}`

- index must be within the limit of the list displayed.

Example of usage:
`remove food /1`

Expected outcome:
The first record in the food list will be deleted.

```
--------------------------------------------------------------------------------
 This food has been deleted from the food list successfully
Food Description: dumplings
calories: 300
Recorded on: 06-11-2022
--------------------------------------------------------------------------------
```

#### Find Food Consumptions Based On Description: `find food`

Find specific food records from user's food consumption over time using keyword in TracknFit

Format: `find food /{description}`

Example of usage:
`find food /laksa`

Expected outcome:
All relevant records from the history would be printed out for the user to see how much of a specific food they
have been consuming.

```
--------------------------------------------------------------------------------
Here are the matching food in your food list:
-----------------------------------------------
Index | Description  | Calories | Date       | 
-----------------------------------------------
1     | laksa        | 400      | 04-11-2022 | 

--------------------------------------------------------------------------------
```

Example of usage: 
`find food /x`

Expected outcome: No matching found message displayed

```
-------------------------------------------------------------------------------
No matching food found
-------------------------------------------------------------------------------
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Transfer the data.txt at the project root directory to another computer.

## Command Summary

* Display help message `help`
* Exit TracknFit `exit`
* Set biometrics `set biometrics /{age} /{gender} /{height} /{activity level}`
* View records `view {biometrics/food/exercise/strength/cardio/weight/bmi/maintenance/calories/all}`
* View exercise records marked as done `view {exercise/strength/cardio} /{done}`
* Add strength record `add strength /{description} /{weight} /{sets} /{repetitions} [/{date}]`
* Add cardio record `add cardio /{description} /{distance} /{repetitions} [/{date}]`
* Add food record `add food /{description} /{calories} [/{date}]`
* Add weight and fat record `add weight /{weight} /{fat percentage} [/{date}]`
* Delete a record `remove {food/exercise/weight} /{record index}`
* Mark an exercise as done `mark {done} /{exercise index} /{time} /{metabolic equivalent}`
* Mark an exercise as not done `mark {undone} /{exercise index}`
* Find food records using a keyword `find food /{description}`

