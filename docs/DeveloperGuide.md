# Pet Clinic Manager - Developer Guide
* [Setting Up and Getting Started](#setting-up-and-getting-started)
* [Design and Implementation](#Design-and-Implementation)
  * [Appointment Task-Employee Allocation Feature](#Appointment-Task-Employee-Allocation-Feature)
  * [Appointment Manage Feature](#Appointment-Manage-Feature)
  * [Appointment Status Feature](#Appointment-Status-Feature)
  * [Appointment Date Feature](#Appointment-Date-Feature)
  * [Employee Management Feature](#Employee-Management-Feature)
  * [Pet Management Feature](#Pet-Management-Feature)
  * [Service Management Feature](#Service-Management-Feature)
* [Appendix A: Product Scope](#Appendix-A-Product-Scope)
  * [Target User Profile](#target-user-profile)
  * [Value Proposition](#value-proposition)
* [Appendix B: User Stories](#Appendix-B-User-Stories)
* [Appendix C: Non-Functional Requirements](#Appendix-C-Non-Functional-Requirements)

## Setting Up and Getting Started

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `tp.main.jar` from [here](https://github.com/AY2223S1-CS2113-F11-2/tp/releases).
3. Put the JAR file into a folder you would like to use.
4. Open the command line in that folder and run the command `java -jar [filename].jar`.

## Design and Implementation

### Appointment Task-Employee Allocation Feature

### Implementation

The proposed task allocation mechanism is facilitated by `Appointment` , `TaskList` and `Employee`. It implements the following operations:

- `TaskList#addTask()` —  Creates task to be performed for an existing appointment and allocates to an employee.
- `TaskList#reassignTask()` — Reassigns a task from one employee to another.
- `Appointment#addTaskToAppointment()` —  Attach a task to an appointment when task is created.
- `Employee#addTaskToEmployee()` — Add to the taskList of individual employees when task is assigned to him or her.
- `Employee#viewEmployeeTasks()` — Prints the list of tasks assigned to a particular employee


Given below is an example usage scenario and how the Task-Employee mechanism behaves at each step.

Step 1. The user launches the application. The user executes `task add i/3001 e/1001 d/Wash Equipment` command which calls `TaskList#addTask()` and creates a task to "wash equipment" for appointment id = 3001, to be done by employee id = 1001. The `task add` command also calls `Appointment#addTaskToAppointment()` to allocate the task to Appointment 3001, and calls 'Employee#addTaskToEmployee()' to allocate the task to Employee 1001.

Step 2. The user executes `task view` command to view all the current tasks for the clinic. The command calls `TaskList#listTasks()` to display all existing tasks and the employee allocation.

Step 3. The user then wishes to reassign task id = 4001 to another employee and executes `task reassign i/4001 e/1002` . The `task reassign` command calls `TaskList#reassignTask()` and the task is reassigned from Employee 1001 - ‘Sally’ to Employee 1002 - ‘John’.

> The following sequence diagram shows how the task allocation/reallocation operation works:

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/NewTaskAllocationSequence.png'>

Step 4. The user then executes the command `employee task i/1002` to view the tasks of employee 1002, which is ‘John’ in this case. The `employee task` command calls `EmployeeList#viewEmployeeTasks()`. Due to the reassignment done in step 3, task 4001 now belongs to Employee 1002, and task 4001 will not show up in Employee 1001’s tasks.

### Design considerations:

**Aspect: How reassignTask executes:**

- Alternative 1 (current choice):

   Updates the Employee attribute of Task to another employee’s id, deletes from the original employee's tasks then added to the new employee's tasks.

  - Pros: Simple, not complex
  - Cons: Need validate the new employee id to be assigned to, so the task will not be removed from the original employee and then fail to be added to the new employee.

------

### Appointment Manage Feature

### Implementation

The proposed appointment feature is facilitated by the `appointment`, `service`, `pet` package. It implements the following operations:  
- `AppointmentList#addAppointment()`— Adds an appointment to the appointment list.
- `AppointmentList#listAppointment()`— Views all appointments in the appointment list.
- `AppointmentList#removeAppointment()`— Removes an appointment to the appointment list.
- `AppointmentList#updateAppointmentStatus()`— Updates the status of an appointment in the appointment list.

Given below is an example usage scenario and how the appointment management behaves at each step.

Step 1. The user launches the application. The user executes `appointment add s/bath p/2001 d/2022-12-12` command which calls `AppointmentList#addAppointment()`. 
To create an appointment with bath service for pet with id = 2001, scheduled on date 2022-12-12, after all checking, creates the corresponding appointment and adds it into appointment list.

Step 2. The user executes `appointment view` command to view all the current appointments for the clinic. The command calls `AppointmentList#listAppointment()` to display all existing appointments details.

Step 3. The user then wishes to remove an existing appointment id = 3001 and executes `appointment remove i/3001` . The `appointment remove` command calls `AppointmentList#removeAppointment()` and then,  
- First the appointment is removed from the appointment list.
- Secondly all tasks related to this appointment are also removed from task list.

The following sequence diagram shows how the appointment management operation works:

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/AppointmentAdd.png'>

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/RemoveAppointment.png'>


### Design Considerations
**Aspect: How addAppointment executes:**

Alternative 1 (current choice):
To relate appointment with existing service, pet and valid date, check all three attributes before adding an appointment to the appointment list:
- First calls `Appointment#checkFormattedDate()` to check if the given date is valid.
- Secondly calls `ServiceList#findService()` to check if the corresponding service with given description exists.
- Thirdly calls `PetList#findPet()` to check if the corresponding pet with given id exists.

  - Pros: Easy to understand.
  - Cons: An invalid appointment may not be added to the appointment list but will consume an appointment id, which causes the valid id not contiguous.

**Aspect: How to reschedule appointments:**

Alternative 1 (current choice):  
Delete the old appointment and then creating a new one.
- Pros: Easy to implement, no extra function needs to be added.
- Cons: Have to destruct and construct even if only one attribute needs to be changed.

Alternative 2:  
Add a new feature called `rescheduleAppointment`.
- Pros: Less overhead when rescheduling the appointment.
- Cons: Harder to implement.


### Appointment Status Feature

### Implementation

The proposed appointment feature is facilitated by the `appointment` package. The following classes in the package works together to allow users to manage their appointments:

#### 1. AppointmentStatus  
An enumeration class, which has the following 3 status:  
- `AppointmentStatus::PENDING` — When an appointment is first created.
- `AppointmentStatus::PROCESSING` — When at least one of the tasks is derived from the appointment, while not all tasks are marked as done.
- `AppointmentStatus::PROCESSED` — When all tasks from the appointment are marked as done.

#### 2. Appointment
- `Appointment#updateAppointmentStatus()` — It helps to update the appointment status under following circumstances respectively:
  - After one task is created from this appointment, update it to `PROCESSING`.
  - After one task related to this appointment is finished, check all the tasks in the taskList of this appointment. 
  If every task is finished already, update it to `PROCESSED`.
- `Appointment#getAppointmentStatus()` — Return the status of one appointment in string format.

### Design Considerations:

**Aspect: How status feature represents:**

- Alternative 1 (current choice):   
use enumeration class.
  - Pros: Give every status a human-readable name. And it's easy to extend for more status.
  - Cons: Add more overhead to create another class.

- Alternative 2:  
  use hard-code integer or string.
  - Pros: Low cost of constructing a status.
  - Cons: Hard to read. And have to recode if status changes much.

### Appointment Date Feature

### Implementation

The proposed appointment feature is facilitated by the `appointment` class and the formatter from `SimpleDateFormat`. The following methods in the package works together to filter appointment date input:

- `Appointment#formatter` — A simple date formatter which allows tailored date format `yyyy-MM-dd`.
- `Appointment#checkFormattedDate()` — Check if the given date is valid for an appointment, which:
  - First, use the `Parser` from `SimpleDateFormat` to examine the format with valid year, month and day value.
  - Secondly, check if the formatted date is too old , i.e. before the using day, since a new appointment should be scheduled in the future.

### Design Considerations:

**Aspect: How status feature represents:**

- Alternative 1 (current choice):   
  use `Date` type.
  - Pros: Give every status a human-readable name. And it's easy to extend for more status.
  - Cons: Add more overhead to create another class.

- Alternative 2:  
  use `String` type.
  - Pros: Low cost of constructing a status.
  - Cons: Hard to read. And have to recode if status changes much.
---


### Employee management Feature

### Implementation

The proposed employee management mechanism is facilitated by `Employee` , `EmployeeList`. It implements the following operations:

- `EmployeeList#addEmployee()` — Adds an employee to the employee list.
- `EmployeeList#listEmployee()` — Views all the employees in the employee list.
- `EmployeeList#removeEmployee()` — Removes an employee in the employee list.

Given below is an example usage scenario and how the employee management mechanism behaves at each step.

Step 1. The user launches the application.

Step 2. The user executes `employee add n/Mozart` command to add an employee named Mozart. This updates `EmployeeList` with the employee.

Step 3. The user executes `employee view` command to view the current employees. 

Step 4. The user executes `employee remove i/1` command to remove the employee with index 1 from the employee list.

The following sequence diagram shows how the employee management works:

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/EmployeeView.png'>

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/EmployeeAdd.png'>

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/RemoveEmployee.png'>

### Design Considerations:

**Aspect: How to number the employee:**

- Alternative 1 (current choice):

  Uses permanent indexes. When an employee is removed, the indexes of other employees are not changed.

  - Pros: Each employee corresponds to only one index, which does not cause ambiguity.
  - Cons: More complex, and the indexes easily get very large.

- Alternative 2:

  Uses dynamic indexes. When an employee is removed, the indexes of other employees are changed.

  - Pros: More easy, and the indexes are always continuous.	
  - Cons: The index of an employee may change frequently.
---


### Pet Management Feature

### Implementation

The proposed pet feature is facilitated by the `pet` package. It implements the following methods to help the head nurse manage pets.

- `AddPetCommand#execute()` —  Register a new pet into the pet management system.
- `RemovePetStatus#execute()` —  Remove a pet from the pet management system.
- `ViewPetCommand#execute()` —  Display all registered pets in a stipulated format.

Given below is an example usage scenario and how the Pet management mechanism behaves at each step.

Step 1. The user launches the application.

Step 2. The user executes `pet add n/Taro s/cat h/1` command to register a cat named ‘Taro’, with a healthy status.

Step 3. The user executes `pet remove i/2` command to remove the second pet in the system.

Step 4. The user executes `view pet` to display all pets in the pet manage system.


The following sequence diagram shows how the add pet operation works:

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/AddPetCommand.png'>

The following sequence diagram shows how the remove pet operation works:

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/RemovePetCommand.png'>

The following sequence diagram shows how the view pet operation works:

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/ViewPetCommand.png'>

### Design Considerations:

**Aspect: How to represent the status of pets:**

- Alternative 1 (current choice):

  Uses java enum to make the pet status more readable and formatted.

  - Pros: easy to regulate the status of each pet and easier to change the pet status.
  - Cons: harder to extend more status.

- Alternative 2:

  Uses hard code string for status.

  - Pros: More easy, and pet status can have a wide range.
  - Cons: hard to read and maintain, not stable, may cause misunderstanding due to typo.
---

### Service Management Feature

### Implementation

The proposed service management mechanism is facilitated by`ServiceList`, `Service`. It implements the following operations:

- `ServiceList#addService()` — Adds a service to the service list.
- `ServiceList#listService()` — Views all the services in the service list.
- `ServiceList#removeService()` — Removes a service in the service list.

Given below is an example usage scenario and how the service management mechanism behaves at each step.

Step 1. The user launches the application.

Step 2. The user executes `service add d/Haircut` command to add a service with description `Haircut`. This inserts a new service object into `ServiceList`.

Step 3. The user executes `service view` command to view the current services.

Step 4. The user executes `service remove i/1` command to remove the service with index 1 from the service list.

The following sequence diagram shows how the service management works

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/ServiceView.png'>

<img decoding = 'async' src = 'https://github.com/AY2223S1-CS2113-F11-2/tp/blob/master/docs/uml/ServiceAdd.png'>

### Design Considerations:

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
---

## Appendix A: Product scope

### Target User Profile

This application is for Head Nurses of Pet Clinics to manage the appointments of the clinic, manage employees and allocate tasks to be done by each employee.

### Value Proposition

The head nurse can now:
- have an upcoming schedule of all appointments of the clinic in one place.
- have an overview of all employees in the clinic and the ability to manage them.
- create and assign tasks to be performed.

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...                                         |
|--------|----------|---------------|-----------------------------------------------------------|
|v1.0|Head Nurse|add appointments| schedule a customer’s appointment                         |
|v1.0|Head Nurse|remove appointments| cancel a customer’s appointment                           |
|v1.0|Head Nurse|view all the upcoming appointments| keep track of the clinic’s schedule                       |
|v1.0|Head Nurse|set the appointment status| update appointment status after some operations           |
|v1.0|Head Nurse|add new employees||
|v1.0|Head Nurse|remove employee||
|v1.0|Head Nurse|view employee||
|v1.0|Head Nurse|register a new pet| keep track of new pets                                    |
|v1.0|Head Nurse|remove a pet||
|v1.0|Head Nurse|view all of the pets| check all of the pets                                     |
|v1.0|Head Nurse|change the status of pets| know whether the pets are recovered                       |
|v1.0|Head Nurse|add to the list of services this clinic can provide| select corresponding service when entering an appointment |
|v1.0|Head Nurse|remove a service from the list of services the clinic can provide||
|v1.0|Head Nurse|view the list of services this clinic can provide||
|v2.0|Head Nurse|add tasks| create tasks to be done for the clinic                    |
|v2.0|Head Nurse|remove tasks| remove tasks to be done for the clinic                    |
|v2.0|Head Nurse|set tasks as completed| mark tasks as done when they are fulfilled                |
|v2.0|Head Nurse|view tasks| view the list of tasks that needs to be done              |
|v2.0|Head Nurse|reassign tasks| reassign a task from an employee to another               |

## Appendix C: Non-Functional Requirements

1. Should work on any Windows, Linux and macOS that has Java `11` or above installed.
2. Should not require users to have prior programming experience.
