# Pet Clinic Management System - User Guide

## Introduction
Pet Clinic Management System (PCMS) is a desktop application that helps a clinic reception to record 
appointments, assign tasks, and manage data of pets/services/tasks/employees. This application uses 
Command Line Interface (CLI) and is able to display information quickly with minimal latency.

## Quick Start
### software requirement
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Pet Clinic Management System` from [here](https://github.com/AY2223S1-CS2113-F11-2/tp).

### Sample workflow
1. pet add n/Eliza s/cat h/0
2. pet view
3. service add d/haircut
4. service view
5. employee add n/chris
6. appointment add s/haircut p/2001 d/2022-12-29
7. appointment view
8. task add i/3001 e/1001 d/prepare hot water
9. task add i/3001 e/1001 d/wash hair
10. task view
11. employee add n/sally
12. task reassign i/4001 e/1002
13. task finish i/4001
14. task finish i/4002
15. task view
16. appointment view
17. pet view
18. appointment remove i/3001
19. pet remove i/2001
20. bye


## Features 
Note:

* Parameters appear in the form of a/PARAMETER
* Words in UPPER_CASE are parameters to be specified by the user.
* Indexes are absolute index that will not change even after some items are deleted.
* For employee, pet, appointment, task, and service, a maximum of 9999 items can be stored in each list.
Index list
* 1XXX: employee
* 2XXX: pet 
* 3XXX: appointment
* 4XXX: task
* 5XXX: service

### Appointment Management

#### Add an appointment: `appointment add`
Adds a new appointment given service, pet, and date involved.

Format: `appointment add s/SERVICE_ID p/PET_ID d/DATE`
* The service and pet that `SERVICE_ID` and `PET_ID` refer to must already exist in service list and pet list.
* `SERVICE_ID` can be found by `service view`
* `PET_ID` can be found by `pet view`
* `Date` should be in the format of `YYYY-MM-DD`
* `Date` has to be later than current date

Example of usage:

`appointment add s/5001 p/2001 d/2022-12-01`

`appointment add s/5002 p/2002 d/2022-12-02`

#### Remove an appointment: `appointment remove`
Remove a particular appointment by INDEX. All tasks belong to this appointment will also be deleted.

Format: `appointment remove i/APPOINTMENT_ID`
* The `APPOINTMENT_ID` must be a positive integer 1, 2, 3, …
* The `APPOINTMENT_ID` can be found by `appointment view`

Example of usage: 

`appointment remove i/3001`

#### View all appointments: `appointment view`
View list of all appointments.

Format: `appointment view`

---
### Task Management

#### Add a task: `task add`
- add a task to the list of tasks for the clinic
- link this task to a specific appointment
- assign the employee who is going to complete this task

Format: `task add i/APPOINTMENT_ID e/EMPLOYEE_ID d/DESCRIPTION`
* The `APPOINTMENT_ID` and `EMPLOYEE_ID` must be positive integers 1, 2, 3, …
* The `APPOINTMENT_ID` can be found by `appointment view`
* and The `EMPLOYEE_ID` can be found by `employee view`
* The `Description` is the description of the task

Example of usage:

`task add i/3001 e/1001 d/prepare hot water`

#### Remove a task: `task remove`
Remove a particular task by TASK_ID

Format: `task remove i/TASK_ID`
* The `TASK_ID` must be a positive integer 1, 2, 3, …
* The `TASK_ID` can be found by `task view`

Example of usage:
`task remove i/4001`

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
`task reassign i/4001 e/1001`


#### Finish a task: `task finish`
Finish a task in the task list

After the first task is added to the appointment, the status of the appointment will
automatically update to processing.

After all tasks of an appointment are finished, the status of an appointment
will automatically update to processed.

Format: `task finish i/TASK_ID`
* The `TASK_ID` and `EMPLOYEE_ID` must be positive integers 1, 2, 3, …
* The `TASK_ID` can be found by `task view`

Example of usage:
`task finish i/4001`

### Employee Management

#### Add an employee: `employee add`
Add an employee to the list of employee in the clinic

Format: `employee add n/NAME`
* The `Name` is the name of the employee
* Duplicate employee names are allowed

Example of usage:
`employee add n/chris`

#### Remove an employee: `employee remove`
Remove a particular employee by EMPLOYEE_ID

Format: `employee remove i/EMPLOYEE_ID`
* The `EMPLOYEE_ID` must be a positive integer 1, 2, 3, …
* The `EMPLOYEE_ID` can be found by `employee view`

Example of usage:
`employee remove i/1001`


#### View all employee: `employee view`
View all employees in the clinic
Format: `employee view`

#### View an employee's assigned tasks: `employee task`
View all tasks assigned to an employee

Format: `employee task i/EMPLOYEE_ID`
* The `EMPLOYEE_ID` must be a positive integer 1, 2, 3, …
* The `EMPLOYEE_ID` can be found by `employee view`

Example of usage:
`employee task i/1001`

---
### Pet Management
#### Add a pet: `pet add`
Add a pet to the list of pets in the clinic. 
Base on the value of health, 1 out of 10 different status of pets will be generated and assigned to pet. 

* `HEALTH = 1`: happy / boring / sleepy / energetic / fantastic
* `HEALTH = 0`: exhausted / painful / injured / bleeding / dying
* The random `HEALTH` status assigned is for fun. Whether is the pet happy or sleepy is not important. Whether the
* health state is 1 or 0 is important.

Format: `pet add n/NAME s/SPECIES h/HEALTH`
* The `NAME` is the name of the pet
* Duplicate pet names are allowed
* The `SPECIES` is the species of the pet
* The `HEALTH` must be either 0(unhealthy) or 1(healthy)

Example of usage:
`pet add n/Eliza s/cat h/0`
`pet add n/Tim s/dog h/1`

#### Remove a pet: `pet remove`
Remove a particular pet by PET_ID

Format: `pet remove i/PET_ID`
* The `PET_ID` must be a positive integer 1, 2, 3, …
* The `PET_ID` can be found by `pet view`

Example of usage:
`pet remove i/2001`

#### View all pets: `pet view`
View all pets registered

Format: `pet view`

---
### Service Management

#### Add a service: `service add`
Add a service to the list of services provided in the clinic

Format: `service add d/DESCRIPTION`
* The `Description` is the description of the service
* Service cannot be added when a service with same name already exist in the service list.

Example of usage:
`service add d/haircut`

#### Remove a service: `service remove`
Remove a specific service by SERVICE_ID

Format: `service remove i/SERVICE_ID`
* The `SERVICE_ID` must be a positive integer 1, 2, 3, …
* The `SERVICE_ID` can be found by `service view`

Example of usage:
`service remove i/5001`

#### View all services: `service view`
View all services provided in the clinic
Format: `service view`

---
### Exit: `bye`

---
## Command Summary

* Add appointment `appointment add s/SERVICE_ID p/PET_ID d/DATE`
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
