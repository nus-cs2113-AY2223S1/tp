# Pet Clinic Manager - Developer Guide


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
