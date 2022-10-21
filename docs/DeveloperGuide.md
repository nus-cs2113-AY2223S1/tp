# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & Implementation

### [Proposed] Appointment Task-Employee Allocation feature

### Proposed Implementation

The proposed task allocation mechanism is facilitated by `AppointmentList` , `TaskList` and `EmployeeList`. It implements the following operations:

- `AppointmentList#addTaskToAppointment()` —  Allocates a task to an appointment when task is created
- `TaskList#createTask()` —  Creates task to be performed for an existing appointment, the task will be allocated to an employee during creation.
- `TaskList#reallocateTask()` — Reallocates the employee in charge of a particular task.
- `EmployeeList#viewIndividualTask()` — User can view the Tasks that a particular employee is in charge of

Given below is an example usage scenario and how the Task-Employee mechanism behaves at each step.

Step 1. The user launches the application for the first time. The user executes `task add i/1 d/Wash Equipment t/12pm` command which calls `TaskList#createTask()` and creates a task, to wash equipment at 12pm. The `task add` command also calls `AppointmentList#addTaskToAppointment()` to allocate the task to Appointment 1. The user is prompted with a list of employees to allocate this task, and the user executes `1` to allocate the task to ‘Sally’.

Step 2. The user executes `task view` command to view all the current tasks for the clinic. The command calls `TaskList#listTasks()` to display all existing tasks and the employee allocation.

Step 3. The user wishes to reallocate task 1 to another employee and executes `task reallocate i/1` . The `task reallocate` command also calls `TaskList#reallocateTask()`, and the user is prompted with a list of employees to reallocate this task, and the user executes `2` to reallocate the task from Employee 1 - ‘Sally’ to Employee 2 - ‘John’.

The following sequence diagram shows how the task allocation/reallocation operation works:

![https://se-education.org/addressbook-level3/images/UndoSequenceDiagram.png](https://se-education.org/addressbook-level3/images/UndoSequenceDiagram.png)

Step 4. The user then executes the command `employee task i/2` to view the tasks of employee 2, which is ‘John’ in this case. The `employee task` command calls `EmployeeList#viewIndividualTask()` . Due to the reallocation done in step 3, the task 1 is moved from Employee 2 - ‘John’ instead of Employee 1 - ‘Sally’, and task 1 will not show up in Employee 1’s individual tasks.

### Design considerations:

**Aspect: How reallocateTask executes:**

- Alternative 1 (current choice):

   Updates the Employee attribute of Task to another employee’s id.

  - Pros: Less complex, `EmployeeList#viewIndividualTask()` will find from a single `TaskList` to retrieve that Employee’s list of tasks.
  - Cons: If the list of tasks is too long, fetch speed may be slow.

- Alternative 2:

   Shift the task from one employee to another.

  - Pros: Instant load speed when `EmployeeList#viewIndividualTask()` is called.
  - Cons: Difficult to implement.
  - Cons: Complex to manage multiple TaskLists belonging to different employees

*{more aspects and alternatives to be added}*

------

### [Proposed] Appointment Status & History feature

### Proposed Implementation

The proposed appointment feature is facilitated by the appointment package. The following classes in the package works together to allow users to manage their appointments.

- `AppointmentList#setStatus()` — Sets the status of an appointment.
- `AppointmentHistory#viewHistory` — Lists the list of expired appointments for users to refer.
- `Storage#loadAppointment` —  Storage stores the list of current appointments into a file and this function loads it when application starts.

Given below is an example usage scenario and how the Appointment mechanism behaves at each step.

Step 1. The user launches the application and there are already pre-existing appointments loaded. `AppointmentList` and `AppointmentHistory` initialises and loads from `Storage` the existing appointments.

Step 2. The user executes `appointment add s/TRIM p/DOGGO d/20-10-2022` command to add an appointment for trimming service for ‘DOGGO’ on 20-10-2022. This updates `AppointmentList` with the appointment.

Step 3. The user executes `appointment status i/2 s/COMPLETED` command to update the status of the above appointment to completed. This removes the appointment from `AppointmentList` and adds the appointment to `AppointmentHistory`.

Step 4. The user executes `appointment history` command to view the list of expired appointment. This will display the list of appointments that has already been completed by the clinic. Then, the user ends with application with `bye` command, the current state of `AppointmentList` and `AppointmentHistory` will be stored in a file through `Storage` .

The following sequence diagram shows how the status operation works:

![https://se-education.org/addressbook-level3/images/UndoSequenceDiagram.png](https://se-education.org/addressbook-level3/images/UndoSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![https://se-education.org/addressbook-level3/images/CommitActivityDiagram.png](https://se-education.org/addressbook-level3/images/CommitActivityDiagram.png)

### Design considerations:

**Aspect: How undo & redo executes:**

- Alternative 1 (current choice):

   Saves the entire address book.

  - Pros: Easy to implement.
  - Cons: May have performance issues in terms of memory usage.

- Alternative 2:

   Individual command knows how to undo/redo by itself.

  - Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  - Cons: We must ensure that the implementation of each individual command are correct.

*{more aspects and alternatives to be added}*

### [Proposed] Employee management feature

### Proposed Implementation

The proposed employee management mechanism is facilitated by `Employee` , `EmployeeList`, `Storage`. It implements the following operations:

- `EmployeeList#addEmployee()` — Adds an employee to the employee list.
- `EmployeeList#listEmployee()` — Views all the employees in the employee list.
- `EmployeeList#removeEmployee()` — Removes an employee in the employee list.
- `Storage#loadEmployee()` —  Storage stores the list of current employees into a file and this function loads it when application starts.

Given below is an example usage scenario and how the employee management mechanism behaves at each step.

Step 1. The user launches the application and there are already pre-existing employees loaded. `EmployeeList` initializes and loads from `Storage` the existing employees.

Step 2. The user executes `employee add n/Mozart` command to add an employee named Mozart. This updates `EmployeeList` with the employee.

Step 3. The user executes `employee view` command to view the current employees. 

Step 4. The user executes `employee remove i/1` command to remove the employee with index 1 from the employee list.

The following sequence diagram shows how the employee management works:



### Design considerations:

**Aspect: How to number the employee:**

- Alternative 1 (current choice):

  Uses permanent indexes. When an employee is removed, the indexes of other employees are not changed.

  - Pros: Each employee corresponds to only one index, which does not cause ambiguity.
  - Cons: More complex, and the indexes easily get very large.

- Alternative 2:

  Uses dynamic indexes. When an employee is removed, the indexes of other employees are changed.

  - Pros: More easy, and the indexes are always continuous.	
  - Cons: The index of an employee may change frequently.




## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|add appointments|schedule a customer’s appointment|
| v1.0    | new user | remove appointments                                          | cancel a customer’s appointment                           |
|v1.0|new user|view all the upcoming appointments|keep track of the clinic’s schedule|
|v1.0|new user|set the appointment status||
|v1.0|new user|add new employees||
|v1.0|new user|remove employee||
|v1.0|new user|view employee||
|v1.0|new user|register a new pet|keep track of new pets|
|v1.0|new user|remove a pet||
|v1.0|new user|view all of the pets|check all of the pets|
|v1.0|new user|change the status of pets|know whether the pets are recovered|
|v1.0|new user|add to the list of services this clinic can provide|select corresponding service when entering an appointment|
|v1.0|new user|remove a service from the list of services the clinic can provide||
|v1.0|new user|view the list of services this clinic can provide||
|v2.0|new user| add tasks                                                    ||
|v2.0|new user|remove tasks||
|v2.0|new user|view tasks||

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
