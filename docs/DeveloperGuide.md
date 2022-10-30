# Pet Clinic Manager - Developer Guide
* [Acknowledgements](#acknowledgements)
* [Setting Up and Getting Started](#setting-up-and-getting-started)
* [Product Scope](#product-scope)
* [Target User Profile](#target-user-profile)
* [Value Proposition](#value-proposition)
* [User Stories](#user-stories)
* [Design](#design)
  * [Architecture](#architecture)
  * [Common Classes](#storage-component)
* [Implementation](#implementation)


## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Product scope

### Target user profile

This application is for Head Nurses of Pet Clinics to manage the appointments of the clinic, manage employees and allocate tasks to be done by each employee.

### Value proposition

The head nurse can now:
- have an upcoming schedule of all appointments of the clinic in one place.
- have an overview of all employees in the clinic and the ability to manage them
- create and assign tasks to be performed


## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Head Nurse|add appointments|schedule a customer’s appointment|
|v1.0|Head Nurse|remove appointments|cancel a customer’s appointment|
|v1.0|Head Nurse|view all the upcoming appointments|keep track of the clinic’s schedule|
|v1.0|Head Nurse|set the appointment status||
|v1.0|Head Nurse|add new employees||
|v1.0|Head Nurse|remove employee||
|v1.0|Head Nurse|view employee||
|v1.0|Head Nurse|register a new pet|keep track of new pets|
|v1.0|Head Nurse|remove a pet||
|v1.0|Head Nurse|view all of the pets|check all of the pets|
|v1.0|Head Nurse|change the status of pets|know whether the pets are recovered|
|v1.0|Head Nurse|add to the list of services this clinic can provide|select corresponding service when entering an appointment|
|v1.0|Head Nurse|remove a service from the list of services the clinic can provide||
|v1.0|Head Nurse|view the list of services this clinic can provide||
|v2.0|Head Nurse|add tasks|create tasks to be done for the clinic|
|v2.0|Head Nurse|remove tasks|remove tasks to be done for the clinic|
|v2.0|Head Nurse|set tasks as completed|mark tasks as done when they are fulfilled|
|v2.0|Head Nurse|view tasks|view the list of tasks that needs to be done|
|v2.0|Head Nurse|reassign tasks|reassign a task from an employee to another|

## Implementation

### Appointment Task-Employee Allocation feature

### Implementation

The proposed task allocation mechanism is facilitated by `Appointment` , `TaskList` and `Employee`. It implements the following operations:

- `TaskList#addTask()` —  Creates task to be performed for an existing appointment and allocates to an employee.
- `TaskList#reassignTask()` — Reassigns a task from one employee to another.
- `Appointment#addTaskToAppointment()` —  Attach a task to an appointment when task is created.
- `Employee#addTaskToEmployee()` — Add to the tasklist of individual employees when task is assigned to him or her.
- `Employee#viewEmployeeTasks()` — Prints the list of tasks assigned to a particular employee


Given below is an example usage scenario and how the Task-Employee mechanism behaves at each step.

Step 1. The user launches the application for the first time. The user executes `task add i/1 e/1 d/Wash Equipment` command which calls `TaskList#addTask()` and creates a task to "wash equipment" for appointment id = 1, to be done by employee id = 1. The `task add` command also calls `AppointmentList#addTaskToAppointment()` to allocate the task to Appointment 1. The user is prompted with a list of employees to allocate this task, and the user executes `1` to allocate the task to ‘Sally’.

Step 2. The user executes `task view` command to view all the current tasks for the clinic. The command calls `TaskList#listTasks()` to display all existing tasks and the employee allocation.

Step 3. The user then wishes to reassign task id = 1 to another employee and executes `task reassign i/1 e/2` . The `task reassign` command calls `TaskList#reassignTask()` and the task is reassigned from Employee 1 - ‘Sally’ to Employee 2 - ‘John’.

> The following sequence diagram shows how the task allocation/reallocation operation works:

![https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/TaskAllocationSequence.png](https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/TaskAllocationSequence.png)

Step 4. The user then executes the command `employee task i/2` to view the tasks of employee 2, which is ‘John’ in this case. The `employee task` command calls `EmployeeList#viewEmployeeTasks()`. Due to the reassignment done in step 3, the task id = 1 now belongs to Employee id = 2, and task 1 will not show up in Employee id = 1’s tasks.

### Design considerations:

**Aspect: How reassignTask executes:**

- Alternative 1 (current choice):

   Updates the Employee attribute of Task to another employee’s id, deletes from the original employee's tasks then added to the new employee's tasks.

  - Pros: Simple, not complex
  - Cons: Need validate the new employee id to be assigned to, so the task will not be removed from the original employee and then fail to be added to the new employee.

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

> The following activity diagram summarizes what happens when a user executes a new command:

### Design considerations:

**Aspect: How status feature executes:**

- Alternative 1 (current choice):


- Alternative 2:


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

![https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/EmployeeManagement.png](https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/EmployeeManagement.png)

![https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/EmployeeView.png](https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/EmployeeView.png)


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

### [Proposed] Pet management feature

### Proposed Implementation

The proposed appointment feature is facilitated by the pet package. It implement the following methods to help the head nurse manage pets.

- `AddPetCommand#execute()` —  Register a new pet into the pet management system.
- `RemovePetStatus#execute()` —  Remove a pet from the pet management system.
- `ViewPetCommand#execute()` —  Display all registered pets in a stipulated format.

Given below is an example usage scenario and how the Pet management mechanism behaves at each step.

Step 1. The user launches the application and there are already pre-existing pet registered.

Step 2. The user executes `pet add n/Taro s/cat h/1` command to register a cat named ‘Taro’, with a healthy status.

Step 3. The user executes `pet remove 2/index` command to remove the second pet in the system.

Step 4. The user executes `view pet` to display all pets in the pet manage system.

The following sequence diagram shows how the status operation works:

### [Proposed] Service management feature

### Proposed Implementation

The proposed service management mechanism is facilitated by`ServiceList`, `Service`. It implements the following operations:

- `ServiceList#addService()` — Adds a service to the service list.
- `ServiceList#listService()` — Views all the services in the service list.
- `ServiceList#removeService()` — Removes a service in the service list.
- `Service#loadService()` —  Storage stores the list of current services into a file and this function loads it when application starts.

Given below is an example usage scenario and how the service management mechanism behaves at each step.

Step 1. The user launches the application and there are already pre-existing services loaded. `ServiceList` initializes and loads from `Storage` the existing services.

Step 2. The user executes `service add d/Haircut` command to add a service with description 'd\Haircut'. This inserts a new service object into `ServiceList`.

Step 3. The user executes `service view` command to view the current services.

Step 4. The user executes `service remove i/1` command to remove the service with index 1 from the service list.

The following sequence diagram shows how the service management works

![https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/ServiceManagement.png](https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/ServiceManagement.png)

![https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/ServiceView.png](https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/ServiceView.png)

### Design considerations:

**Aspect: How to represent the Service:**

- Alternative 1 (current choice):
  
  Uses a class to represent the `Service`, with a unique id and a description.

  - Pros: 
  1. Each service corresponds to only one index, which does not cause ambiguity.
  2. If more features are to be added in Service, 
  it could easily be done via adding more attributes and functions in it.
  3. The design is more OOP.
  
  - Cons: 
  1. Each Service causes more overhead. 
  2. Index is not reused, so new index could become quite large. 

- Alternative 2:

  Uses a global `ServiceList` to store all services. 
  In Service class, create a static Arraylist to store all service strings.
  - Pros: 
  1. Take less memory and constructing or destructing cost.
  2. The indexes are always continuous.
  - Cons: 
  1. The service only has one attribute which is the description. 
  So can not attach more attributes to it.
  2. Less oop.

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
