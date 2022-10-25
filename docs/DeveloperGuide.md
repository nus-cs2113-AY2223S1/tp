# Property Rental Manager - Developer Guide
* [Acknowledgements](#acknowledgements)
* [Setting Up and Getting Started](#setting-up-and-getting-started)
* [Product Scope](#product-scope)
* [Target User Profile](#target-user-profile)
* [Value Proposition](#value-proposition)
* [User Stories](#user-stories)
* [Design](#design)
  * [Architecture](#architecture)
  * [UI Component](#ui-component)
  * [Client Component](#client-component)
  * [Property Component](#property-component)
  * [Model Component](#model-component)
  * [List Component](#list-component)
  * [Storage Component](#storage-component)
  * [Common Classes](#storage-component)
* [Implementation](#implementation)
* [Documentation, logging, testing, configuration and dev-ops](#documentation-logging-testing-configuration-and-dev-ops)
* [Appendix: Requirements](#appendix-requirements)
* [Appendix: Instruction for manual testing](#appendix-instruction-for-manual-testing)
* [Non Functional Requirement (NFR)](#non-functional-requirements)
* [Glossary](#glossary)
___
## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}
___
## Setting Up and Getting Started
___
## Product scope
___
## Target user profile
This application is for property agent who is managing single owner rental units and is looking to reduce the overhead to filter appropriate tenants. The property agent would also want to monitor the expenses such as damages, utility bills and payment dates for rent.
___
## Value proposition
Aids property agent in tracking information related to their property which includes:
- Filtering appropriate tenants (Based on tags e.g.: gender, occupations and age)
- Show expenses from the rented unit
- Monitor payment dates 

Some of the constraint includes:
- Single owner unit (Shared ownership will be registered under one owner's name)
- Unable to calculate tax payment
___
## User Stories
| Version | As a ...         | I want to ...                         | So that I can ...                                                    |
|---------|------------------|---------------------------------------|----------------------------------------------------------------------|
| v1.0    | Property Manager | add properties                        | keep track of properties                                             |
| v1.0    | Property Manager | add clients                           | keep track of clients                                                |
| v1.0    | Property Manager | delete properties                     | prevent properties I am no longer tracking from cluttering my data   |
| v1.0    | Property Manager | delete clients                        | prevent clients I am no longer tracking from cluttering my data      |
| v1.0    | Property Manager | view a list of properties             | find out what and how many properties I manage                       |
| v1.0    | Property Manager | view a list of clients                | find out what and how many clients I manage                          |
| v1.0    | Property Manager | check the details of a property       | view the property's information                                      |
| v1.0    | Property Manager | pair a client to a property           | record down which client is renting which property                   |
| v1.0    | Property Manager | unpair a client to a property         | update my rental records when a client is no longer renting property |
| v1.0    | Property Manager | save my data                          | used the data created from a previous use of the app                 |
| v1.0    | Property Manager | quit the app                          | -                                                                    |
| v2.0    | Property Manager | check the details of a client         | view the client's information                                        |
| v2.0    | Property Manager | search clients using their details    | easily find specific clients                                         |
| v2.0    | Property Manager | search properties using their details | easily find specific properties                                      |

___
## Design
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Architecture
![Software Architecture Diagram](diagrams/ArchitectureDiagram.png)
### UI Component
### Parser Component
### Client Component
### Property Component
### List Component
The list feature has the following commands in it -
* `list -client` This lists every client, along with all their information
* `list -property` This lists every property, along with all its information
* `list -everything` This lists everything about both clients and properties
* `list -client TAG` This lists only the information present in the TAG for every client. The types of
  TAGs are -
    * `c/` This is for contact number
    * `b/` This is for budget
    * `n/` This is for name
    * `e/` This is for e-mail
    * `-short` This is for the shorthand version(displays just name and budget)
* `list -property TAG` This lists only the information present in the TAG for every property. The types
  of TAG are -
    * `a/` This is for address
    * `n/` This is for name
    * `p/` This is for price
    * `t/` This is for unit type
    * `-short` This is for the shorthand version(displays address, price and unit type)

There are 5 different classes, that each inherit from the abstract Command class. The commands read information from 
the PropertyList and ClientList classes respectively, and display using the Ui class, making use of the objects of 
these classes. The Commands which display all the information - i.e. CommandListClients, CommandListProperties, and
CommandListEverything read and display using loops inside the overriden execute() method itself. The Commands which 
display selected information - i.e. CommandListClientsWithTags and CommandListPropertiesWithTags use their private 
methods to display their information, using methods present in Ui. The class structure is as follows - 
![ListClassDiagram](diagrams/ListClassDiagram.png)

  

### Pairing Component
API: [```pairingList.java```](../src/main/java/seedu/duke/PairingList.java)

```PairingList``` is responsible for recording which clients renting which property.

```PairingList``` does not inherit from other classes. It stores references to Client and Property objects.

This a partial class diagram of the ```PairingList``` class:

![Pairing List Design Diagram](diagrams/PairingListDesignDiagram.png)
```ParsePair``` and ```ParseUnpair``` contain references to data classes ```PairingList```, ```ClientList``` and 
```PropertyList``` because the data classes provide the required information to validate user input.

### Storage Component
For `Storage` feature:

![Storage Design Diagram](diagrams/StorageDesignDiagram.jpg)

The Storage class is a superclass itself that is not inherit from other class. This class is responsible for managing 
three different text file:
- `client.txt` - Stores the client that is in the Client ArrayList.
- `property.txt` - Stores the property that is in the Property ArrayList.
- `pairing.txt` - Stores the relationship between a client and property which is stored in the Pairing hashmap.


It has an association with other class which includes:
- CommandAddProperty
- CommandAddClient
- CommandDeleteProperty
- CommandDeleteClient
- CommandPair
- CommandUnpair

Since the arraylist changes by **adding** and **deleting** operations while hashmap changes by **pair** and **unpair** 
operations, the text files will be updated when `add`, `delete`, `pair` or `unpair` is invoked.

### Common Classes
___
## Implementation

This section describes the implementation details of the features within Property Rental Manager.

---
### Add Feature
The add feature simply adds an entity to its corresponding list. For Property Rental Manager, there are two variations to the add feature, namely `add -client` and `add -property`.

- `add -client`: Add a new client to the client list.
- `add -property`: Add a new property to the property list.

The implementation of add feature can be simplified into two major sections. The first section involves the parsing and validation of relevant information provided by the user while the second section comprises the actual addition of client/property to the client list/property list.

<br/>

**Section 1: Parse and Validation of Information**

The first section is facilitated by the following classes:

- `ParseAdd`: Contains common methods used by `ParseAddClient` and `ParseAddProperty`.
- `ParseAddClient`: Extracts and validates client information from `commandDescription`(User Input).
- `ParseAddProperty`: Extracts and validates property information from `commandDescription`(User Input).

The following is a simple class diagram of the three classes:
<p align="center">

![](diagrams/ParseAddRelatedClassesDiagram.jpg)

</p>

<p align="center">
Parse Add Related Classes Diagram
</p>

As shown above, both `ParseAddClient` and `ParseAddProperty` classes have a similar core method called `parseCommand()` which is responsible for client or property detail extraction and validation. The rest of the methods in both classes are sub-methods of the `parseCommand()` method.

Also, most of the sub-methods are used to perform validations on the extracted details. These methods are implemented via regex pattern checker.

- Client:
    - `checkForValidSingaporeContactNumber(String)`
    - `checkForValidEmail(String)`
    - `checkForBudgetNumberFormat(String)`
- Property:
    - `checkForValidSingaporeAddress(String)`
    - `checkForValidSingaporeLandedPropertyAddress(String)`
    - `checkForValidSingaporeBuildingAddress(String)`
    - `checkForPriceNumberFormat(String)`
- Common:
    - `checkForEmptyDetails(String)`: Checks for any missing essential details, non-essential detail such as optional email can be empty.

Note: Since the target user is a property manager working in Singapore, some validations are tailored to Singapore context.

<br/>

**Section 2: Addition of client or property to client list or property list**

The second section is facilitated by the following classes: 
- `CommandAdd`: Abstract superclass of `CommandAddClient` and `CommandAddProperty` classes.
- `CommandAddClient`: Creates a `Client` object and add it to the `clientList`.
- `CommandAddProerty`: Creates a `Property` object and add it to the `propertyList`.

The following is a simple class diagram of the three classes:

<p align="center">

![Command Add Related Classes Diagram](diagrams/CommandAddRelatedClassesDiagram.jpg)

</p>

<p align="center">
Command Add Related Classes Diagram
</p>


As shown above, both `CommandAddClient` and `CommandAddProperty` classes have a similar core method called `execute(...)` which is responsible for the new client or property addition into their respective lists.

<br/>

**Example Scenario**

Given below is an example scenario on how add client/property behaves at each step.


- **Step 1**: The user executes ```add -client n/NAME c/CONTACT_NUMBER e/EMAIL b/BUDGET_MONTH``` or ```add -property n/NAME a/ADDRESS p/PRICE t/TYPE```. Depending on `add -client` or `add -property` specified, a `Parser` object of type `ParseAddClient` or `ParseAddProperty` is created.


- **Step 2**: The `Parser` object will then call method `ParseAddClient#parseCommand()` or `ParseAddProperty#parseCommand()` which will check for any incorrect formatting before the extraction and validation of client/property details.


- **Step 3**: If there is no error, a `Command` object of type `CommandAddClient` or `CommandAddProperty` is created.


- **Step 4**: Next, the `Command` object will then call method `CommandAddClient#execute(...)` or `CommandAddProperty#execute(...)` which will add a new `Client` or `Property` type object created into their respective `clientList`/`propertyList`.


- **Step 5**: Lastly, method `Ui#showClientAddedConfirmationMessage()` or `Ui#showPropertyAddedConfirmationMessage()` is called to notify user about the successful addition of new client or property. Also, method `Storage#addToClientFile` or `Storage#addToPropertyFile` is called to update their respective storage files.

The following are simplified sequence diagrams of add feature for client and property:
![Add Client Sequence Diagram](diagrams/AddClientSequenceDiagram.JPG)
<p align="center">
Add Client Sequence Diagram
</p>

![Add Property Sequence Diagram](diagrams/AddPropertySequenceDiagram.JPG)
<p align="center">
Add Property Sequence Diagram
</p>

---

### Delete Client/Property feature
The **delete client/property** mechanism involves the following classes: ```ParseDeleteClient```,
```ParseDeleteProperty```, ```CommandDeleteClient```, ```CommandDeleteProperty```,
```ClientList```, ```PropertyList``` and ```PairingList```.

Given below is an example usage scenario and how the delete client/property behaves at each step.

**Step 1:** The user executes ```delete -client ic/INDEX``` or ```delete -property ip/INDEX```.
The ```ParseDeleteClient``` or ```ParseDeleteProperty``` class is called respectively and the format of the user
input is checked for any incorrect formatting.

**Step 2:** If there are no errors, ```CommandDeleteClient``` or ```CommandDeleteProperty``` is called respectively.
The ```CommandDeleteClient#execute()``` or ```CommandDeleteProperty#execute()``` method is then called.

**Step 3:** The ```ClientList#deleteClient()``` or ```PropertyList#deleteProperty()``` method is called which 
removes the Client or Property with that specific index from their respective ArrayList.

**Step 4:** Any pairings involving that specific Client or Property is also deleted using the
```pairingList#deletePairing()``` method. A message showing all the deleted pairs is shown to the user.

**Step 5:** The corresponding line(s) in the respective files are deleted. The method is shown in the Storage
Implementation section.

The following *class diagram* shows all the classes involved in the **delete client/property** operation
and their relationships.

![Delete Client/Property Class Diagram](diagrams/DeleteClientPropertyCD.png)

The following *sequence diagram* shows how the **delete client** operation works, showcasing the
```ClientList#deleteClient()``` method.

![Delete Client Sequence Diagram](diagrams/DeleteClientSD.png)

The following *sequence diagram* shows how the **delete property** operation works, showcasing the
```PropertyList#deleteProperty()``` method.

![Delete Property Sequence Diagram](diagrams/DeletePropertySD.png)

---

### PairingList

```PairingList``` facilities that pair and unpair commands by storing client-property pairs.

When client rents a property, the client and property form a pair.

*  ```PairingList``` uses a hash map to represent these client-property pairs, where the key is a ```Client``` object
  and the value is a ```Property``` object.
* A hash map is chosen due to its constant time lookup performance, making it efficient at querying the property that a
  client is renting.
* Also, the Java HashMap prevents duplicate keys, which dovetails nicely with the fact that real-life tenants only have
  one place of residence at any time.

#### Pair

The sequence diagram for the pair command is called is shown below:

![PairingList Add Pair Sequence Diagram](diagrams/PairingListAddPairSD.png)

**NOTE**: Some self-invocated calls have been omitted because this diagram emphasises cross-class method calls.

The pair command takes in user input of the format:
```
pair ip/PROPERTY_INDEX ic/CLIENT_INDEX
```
where ```PROERTY_INDEX``` and ```CLIENT_INDEX``` must be positive integers which are indexes present in ```ClientList```
and ```PropertyList``` if their private arrays were 1-indexed.

How the pair command works:
1. The user input for a pair command is first parsed by ```Parser``` (specifically, ```ParsePair```).
2. ```ParsePair``` checks the user input for formatting mistakes such as missing flags and wrong flag orders.
3. ```ParsePair``` also calls helper methods in ```PairingList``` to check that the pairing client and property indexes
    exists. Also, the client and property must not be already paired. The client must not be renting any property
    presently as well.
4. After passing all these checks, the program fetches the desired```Property``` and ```Client``` from
   ```PropertyList``` and ```ClientList```.
5. The ```Property``` and ```Client``` objects are inserted as a pair into the hashmap of ```PairingList```.
 
#### Unpair

The unpair command takes in user input of the format:
```
unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX
```
where ```PROERTY_INDEX``` and ```CLIENT_INDEX``` must be positive integers which are indexes present in ```ClientList```
and ```PropertyList``` if their private arrays were 1-indexed.


How the unpair command works:
1. The user input for a pair command is first parsed by ```Parser``` (specifically, ```ParseUnpair```).
2. ```ParseUnpair``` checks the user input for formatting mistakes such as missing flags and wrong flag orders.
3. ```ParseUnpair``` also calls helper methods in ```PairingList``` to check that the pairing client and property
   indexes exist, and that the client-property pair exist in ```PairingList```.
4. After passing all these checks, the ```PairingList``` deletes the hashmap entry in ```clientPropertyPairings```
   which contains the client-property pair.

---

### Storage
The implementation of Storage class requires consists of different level of operations:

- Load Files
- Append to File
- Update to File

#### Load Files:
![Load File Flowchart](diagrams/LoadFileFlowChart.jpg)
At the file loading level, it comprises checks to verify the directory is created. This is done by invoking a method: 
`loadFiles(hasDirectory, hasPropertyFile, hasClientFile, hasPairingFile, clientList, propertyList, pairingList)`.
This method would conduct the following operations:
- Create a `data` directory if not already exist. (`hasDirectory` is `false`)
- Load Clients into Client ArrayList if `hasClientFile` is `true`.
- Load Properties into Property ArrayList if `hasPropertyFile` is `true`.
- Load Pairings into Pairing HashMap if `hasPairingFile` is `true`.

An empty file would not be loaded into the ArrayList and PropertyList as the code is designed to read for `next()`.
An empty file would invoke a `false` in `hasNext()`, thus adding operation would not continue. The overall operation can
be visualised in the flowchart above.

#### Append To File
When file is appended into the text file, it's being stored in different formats as shown below:

- Client:  `NAME | CONTACT_NUMBER | EMAIL <optional> | BUDGET` 
- Property: `NAME | ADDRESS | RENTAL_PRICE | UNIT_TYPE` 
- Pairing: `[CLIENT_FORMAT] : [PROPERTY_FORMAT]` 

The text file of which Client, Property and Pairing is being stored is `client.txt`, `property.txt` and `pairing.txt` 
respectively.

![Add Client to Storage Diagram](diagrams/StorageAddClientSequenceDiagram.jpg)
![Add Property to Storage Diagram](diagrams/StorageAddPropertySequenceDiagram.jpg)
![Add Pair to Storage Diagram](diagrams/StorageAddPairSequenceDiagram.jpg)

The three sequence diagram above shows the sequence of which the append operation is being invoked. All three
operations are similar in operations but are invoked with different `parameter` and `path`.

### Update To File
The update operation happens when entries in ClientList and PropertyList is being deleted and entries the hash map of 
PairingList is being removed.

The sequence diagram of `updateClient`, `updateProperty` and `updatePair` can be seen below:
![Update Client Sequence Diagram](diagrams/StorageUpdateClientSD.jpg)
![Update Property Sequence Diagram](diagrams/StorageUpdatePropertySD.jpg)
![Update Pairing Sequence Diagram](diagrams/StorageUpdatePairSD.jpg)

Note that when delete operation is being invoked on client and property, the `updatePair` method will also be invoked to
prevent entries retaining within pairingList after it has been deleted from clientList or propertyList.


### List feature

There are 3 main steps whenever a list command needs to be executed
* When the user enters any command, it first needs be understood. That is handled by the ParseManager class.
   Next, when the first word entered by the user is determined to be `list`, ParseManager itself then determines
   the type of list command entered, including the tags. ParseListClient, ParseListProperty and ParseListEverything
   are checkers to ensure that a valid command has been entered. ParseListClient and ParseListProperty also determine
   if tags have been entered, and if those tags are valid.
  ```
        case COMMAND_LIST:
            ArrayList<String> listCommandTypeAndFlags = getListCommandType(commandDetail);
            boolean isListProperty = listCommandTypeAndFlags.get(0).trim().equals(PROPERTY_FLAG);
            boolean isListClient = listCommandTypeAndFlags.get(0).equals(CLIENT_FLAG);
            boolean isListEverything = listCommandTypeAndFlags.get(0).equals(EVERYTHING_FLAG);
            if (isListProperty) {
                return new ParseListProperty(listCommandTypeAndFlags.get(1));
            } else if (isListClient) {
                return new ParseListClient(listCommandTypeAndFlags.get(1));
            } else if (isListEverything && listCommandTypeAndFlags.get(1).isEmpty()) {
                return new ParseListEverything();
            } else {
                throw new UndefinedSubCommandTypeException(MESSAGE_INCORRECT_LIST_DETAILS);
            }
  ```
This block of code is part of ParseManager. It determines the type of list operation(-client, 
-property or -everything) and returns the corresponding object.  
Both ParseListClient and ParseListObject then determine if tags are present, and if they are valid, 
throwing exceptions if any errors are encountered. They then return the corresponding Command type necessary.

* There are five different classes which handle each of the features described above. Each class inherits from the
   abstract Command class, and reads information present in either the PropertyList or ClientList objects respectively.

The execute function retrieves the propertyList holding all the current properties. It then loops 
through every property present. For every property, it passes it to the corresponding display function
in Ui.

* Each of these commands then uses a method present in the Ui class, to print an individual client, or property.
   The loop for printing every single client or property is present in the Command itself.
  ![CommandListClientsClass](diagrams/CommandListClientsClass.png)
The above is an example for CommandListClients. It reads from ClientList. Then, it displays each line
using the displayOneClient function in Ui.  
The sequence diagram of the operation is as follows - 
![ListSequence](diagrams/ListSequence.png)


---

## Documentation, logging, testing, configuration and dev-ops
___
## Appendix: Requirements
___
## Appendix: Instruction for Manual Testing

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *client* - Person who is seeking for property to rent


## Instructions for manual testing