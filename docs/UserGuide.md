# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### 3. Prescription

#### Adding New Prescription: `add`
Add a new prescription to the list of prescriptions.

Format: `add i/patientID n/medicine_name d/medicine_dosage t/time_interval`

* The `medicine_name` should be either one or two words
* The `medicine_dosage` should be formatted as a number followed by a unit
* The `time_interval` can be in a natural language format

Example of usage:

`add i/add i/S7093944G n/Problaxan d/10 mg t/take 15 minutes after every meal, 3x a day`

Expected Output:

```

```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
