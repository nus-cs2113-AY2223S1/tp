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

Example of usage:

`appointment add s/bath p/Muse d/01/02/2022`

`appointment add s/haircut p/Donald d/30/02/2022`

#### Remove an appointment: `appointment remove`
- remove a particular appointment by INDEX

Format: `appointment remove i/APPOINTMENT_ID`
* The `APPOINTMENT_ID` has to be an int within the range of 1 to number of appointments in the list.
* The `APPOINTMENT_ID` can be found by `appointment view`

Example of usage: 

`appointment remove i/1`

#### View all appointments: `appointment view`
View list of all appointments

Format: `appointment view`

### Task Management

#### Add a task: `task add`
- add a task to the list of tasks for the clinic
- link this task to a specific appointment
- assign the employee who is going to complete this task

Format: `task add i/APPOINTMENT_ID e/EMPLOYEE_ID d/DESCRIPTION`
* The `APPOINTMENT_ID` and `EMPLOYEE_ID` must be positive integers 1, 2, 3, …
* The `APPOINTMENT_ID` can be found by `appointment view`
* and The `EMPLOYEE_ID` can be found by `employee view`

Example of usage:

`task add i/1 e/2 d/prepare hot water`

#### Remove a task: `task remove`
- remove a particular task by TASK_ID

Format: `task remove i/TASK_ID`
* The `TASK_ID` must be a positive integer 1, 2, 3, …
* The `TASK_ID` can be found by `task view`

Example of usage:
`task remove i/1`

#### View all tasks: `task view`
View list of all tasks

Format: `task view`

#### Reassign task to employee: `task reassign`
- assign a task to an employee

Format: `task reassign i/TASK_ID e/EMPLOYEE_ID`
* The `TASK_ID` and `EMPLOYEE_ID` must be positive integers 1, 2, 3, …
* The `TASK_ID` can be found by `task view`
* and The `EMPLOYEE_ID` can be found by `employee view`

Example of usage:
`task reassign i/1 e/1`

#### Finish a task: `task finish`

- Finish a task in the task list

Format: `task finish i/TASK_ID`

* The `TASK_ID` and `EMPLOYEE_ID` must be positive integers 1, 2, 3, …
* The `TASK_ID` can be found by `task view`
* When all the tasks of an appointment is marked as done, the status of an appointment is automatically marked as processed.

Example of usage:
`task finish i/1`


### Employee Management

#### Add an employee: `employee add`
- add an employee to the list of staff in the clinic

Format: `employee add n/NAME`

Example of usage:
`employee add n/chris`

#### Remove an employee: `employee remove`
- remove a particular employee by EMPLOYEE_ID

Format: `employee remove i/EMPLOYEE_ID`
* The `EMPLOYEE_ID` must be a positive integer 1, 2, 3, …
* The `EMPLOYEE_ID` can be found by `employee view`

Example of usage:
`employee remove i/1`


#### View all employee: `employee view`
view all employees in the clinic
Format: `employee view`

#### View an employee's assigned tasks: `employee task`
- view all tasks assigned to an employee

Format: `employee task i/EMPLOYEE_ID`
* The `EMPLOYEE_ID` must be a positive integer 1, 2, 3, …
* The `EMPLOYEE_ID` can be found by `employee view`

Example of usage:
`employee task i/1`

### Pet Management
#### Add a pet: `pet add`
- add a pet to the list of pets in the clinic

Format: `pet add n/NAME s/SPECIES h/HEALTH`
* The `HEALTH` must be either 0(unhealthy) or 1(healthy)

Example of usage:
`pet add n/Eliza s/cat h/0`
`pet add n/Tim s/dog h/1`

#### Remove a pet: `pet remove`
- remove a particular pet by PET_ID

Format: `pet remove i/PET_ID`
* The `PET_ID` must be a positive integer 1, 2, 3, …
* The `PET_ID` can be found by `pet view`

Example of usage:
`pet remove i/1`

#### View all pets: `pet view`
view all pets registered

Format: `pet view`


### Service Management

#### Add a service: `service add`
- add a service to the list of services provided in the clinic

Format: `service add d/DESCRIPTION`

Example of usage:
`service add d/haircut`

#### Remove a service: `service remove`
- add a service to the list of services provided in the clinic

Format: `service remove i/SERVICE_ID`
* The `SERVICE_ID` must be a positive integer 1, 2, 3, …
* The `SERVICE_ID` can be found by `service view`

Example of usage:
`service remove i/1`

#### View all services: `service view`
view all services provided in the clinic
Format: `service view`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## command.command Summary

* Add appointment `appointment add s/SERVICE p/PET d/DATE`
* Remove appointment `appointment remove i/APPOINTMENT_ID`
* View appointments `appointment view`

* Add task `task add i/APPOINTMENT_ID e/EMPLOYEE_ID d/DESCRIPTION`
* Remove task `task remove i/TASK_ID`
* View tasks `task view`
* Reassign task `task reassign i/TASK_ID e/EMPLOYEE_ID`

* Add employee `employee add n/NAME`
* Remove employee `employee remove i/EMPLOYEE_ID`
* View employees `employee view`
* View employee's tasks `employee task i/EMPLOYEE_ID`

* Add pet `pet add n/NAME s/SPECIES h/HEALTH`
* Remove pet `pet remove i/PET_ID`
* View pets `pet view`

* Add service `service add d/DESCRIPTION`
* Remove service `service remove i/SERVICE_ID`
* View service `service view`