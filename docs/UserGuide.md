# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

### Appointment Management

#### Add an appointment: `appointment add`
Adds a new appointment given service, pet, and date involved.

Format: `appointment add s/SERVICE p/PET d/DATE`

Example of usage: 还没写

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

#### Remove an appointment: `appointment remove`
- remove a particular appointment by INDEX

Format: `appointment remove i/INDEX`
* The `INDEX` has to be an int within the range of 1 to number of appointments in the list.

Example of usage: 

`appointment remove i/1`

#### View all appointments: `appointment view`
View list of all appointments

Format: `appointment view`

### Task Management

#### Adding a task: `task add`
- add a task to the list of tasks for the clinic
- link this task to a specific appointment
- assign the employee who is going to complete this task
- automatically generate a unique id of the task

Format: `task add i/APPOINTMENT_ID e/EMPLOYEE_ID d/DESCRIPTION`

Example of usage:

`task add i/1 e/2 d/prepare hot water`

#### Removing a task: `task remove`
- remove a particular task by TASK_ID

Format: `task remove i/TASK_ID`
* The `TASK_ID` must be a positive integer 1, 2, 3, …

Example of usage:
`task remove i/1`

#### View all tasks: `task view`
View list of all tasks

Format: `task view`

#### Reassign task to employee: `task reassign`
- assign a task to a employee

Format: `task reassign i/TASK_ID e/EMPLOYEE_ID`
* The `TASK_ID` and `EMPLOYEE_ID` must be a positive integer 1, 2, 3, …

Example of usage:
`task reassign i/1 e/1`


### Employee Management

#### Adding an employee: `employee add`
- add an employee to the list of staff in the clinic

Format: `employee add n/NAME`

Example of usage:
`employee add n/chris`

#### Remove an employee: `employee remove`
- remove a particular employee by EMPLOYEE_ID

Format: `employee remove i/EMPLOYEE_ID`
* The `EMPLOYEE_ID` must be a positive integer 1, 2, 3, …

Example of usage:
`employee remove i/1`


#### Viewing employee: `employee view`
view all employees in the clinic
Format: `employee view`

#### Viewing employee’s task: `employee task`
- view all tasks assigned to an employee

Format: `employee task i/EMPLOYEE_ID`
* The `EMPLOYEE_ID` must be a positive integer 1, 2, 3, …

Example of usage:
`employee task i/1`

### Pet Management
#### Add a pet: `pet add`
- add a pet to the list of pets in the clinic

Format: `pet add n/NAME s/SPECIES h/HEALTH`
* The `HEALTH` must be either 0(unhealthy) or 1(healthy)

Example of usage:
`pet add n/Eliza s/cat h/0`

#### Remove a pet: `pet remove`
- remove a particular pet by PET_ID

Format: `pet remove i/PET_ID`
* The `PET_ID` must be a positive integer 1, 2, 3, …

Example of usage:
`pet remove i/1`

### Service Management
#### Add a service: `service add`
- add a service to the list of services provided in the clinic

Format: `pet remove i/PET_ID`
* The `PET_ID` must be a positive integer 1, 2, 3, …

Example of usage:
`pet remove i/1`




## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## command.command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
