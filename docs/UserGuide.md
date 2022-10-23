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


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Display help message `help`
* Exit TracknFit `exit`
* Set biometrics `set biometrics /{age} /{gender} /{height} /{weight} /{fat percentage}`
* Add weight and fat percentage `add weight /{weight} /{fat percentage}`
* View weight and fat percentage records `view weight`
