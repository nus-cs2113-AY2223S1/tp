# Property Rental Manager - Developer Guide
* [Acknowledgements](#acknowledgements)
* [Setting Up and Getting Started](#setting-up-and-getting-started)
* [Design](#design)
  * [List Component](#list-component)
  * [Pairing Component](#pairing-component)
  * [Storage Component](#storage-component)
* [Implementation](#implementation)
  * [Add Feature](#add-feature)
  * [Delete Client/Property Feature](#delete-clientproperty-feature)
  * [Pairing and Unpairing Features](#pairing-and-unpairing-features)
  * [Storage Feature](#storage-feature)
  * [List Feature](#list-feature)
* [Appendix A: Product Scope](#appendix-a-product-scope)
* [Appendix B: User Stories](#appendix-b-user-stories)
* [Appendix C: Non Functional Requirement (NFR)](#appendix-c-non-functional-requirements)
* [Appendix D: Glossary](#appendix-d-glossary)
* [Appendix E: Instructions for Manual Testing](#appendix-e-instructions-for-manual-testing)

___
## Acknowledgements
* [AddressBook Level-3](https://github.com/se-edu/addressbook-level3) 

___
## Setting Up and Getting Started
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `PropertyRentalManager.jar` from [here](https://github.com/AY2223S1-CS2113-F11-1/tp/releases).
3. Put the JAR file into an empty folder.
4. Open a command window and change the current working directory to the directory that the JAR file is located in using
   following command:
```
cd [PATH_TO_JAR_DIRECTORY]
```
5. Run Property Rental Manager
```
java -jar PropertyRentalManager.jar
```
___
## Design

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
* `list -pair` This lists all clients and properties that have been paired, in no particular order.
* `list -pair -short` This lists the -short version of all clients and properties that have been paired, in 
no particular order

There are 7 different classes, which each inherit from the Command class, and work in similar ways - 
* They are executed when an object is created in the corresponding Parse class.
* On execution, they read information about a single client or property and send it to the corresponding 
display function in Ui
* The Ui function then displays the necessary information.
* Finally, it states the number of items present, and the Command object is no longer used.
![ListClassDiagram](diagrams/ListClassDiagramIncludePairsFinal.png)
Note: The C symbols are a result of the PlantUml layout.

---  

### Pairing Component
API: `pairingList.java`

* `PairingList` is responsible for recording which clients are renting which property.
* `PairingList` does not inherit from other classes. It stores references to `Client` and `Property` objects.

Here is how classes involved in the pairing/unpairing actions interact with each other:

![Pairing List Class Diagram](diagrams/PairingListCD.png)
1. `CommandPairParser` and `CommandUnpairParser` inherit from a general `CommandPairUnpairParser`, which contains 
    parsing methods that are common to its subclasses.
2. `CommandPairParser` and `CommandUnpairParser` are responsible for checking input format. After (successful) checking, 
    they create `CommandPair` and `CommandUnpair` objects respectively.
3. `CommandPair` and `CommandUnpair` contain references to `ClientList` and `PropertyList` because the command classes
    need to validate user input against the data in `ClientList` and `PropertyList`.
4. After input is validated, `PairingList` is updated with the new pairings. `Storage` records these changes and `Ui` 
    prints the confirmation message for the user action.


Here is the underlying data structure of `PairingList`:

![Pairing List Data Class Diagram](diagrams/PairingListAPICD.png)
* `PairingList` is essentially a "wrapper" to the underlying `java.util.HashMap`. The key-value pairs in the hashmap have 
    `Client` as the key and `Property` as the value.
* `PairingList` provides methods to add or delete these key-value pairs to represent the pairing and unpairing of real-life
    clients and properties.
* The `Client` and `Property` references contained in `PairingList` must be references to valid `Client` and `Property` 
    objects in `ClientList` and `PropertyList` as well, since `PairingList` is an implementation of an adjacency list. 

---
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

___
## Implementation

This section describes the implementation details of the features within Property Rental Manager.

---
### Add Feature
The add feature adds an entity to its corresponding list. For Property Rental Manager, there are two variations to the add feature, namely `add -client` and `add -property`.

- `add -client`: Add a new client to the client list.
- `add -property`: Add a new property to the property list.

The implementation of add feature can be simplified into two major sections. The first section involves the parsing and validation of relevant information provided by the user while the second section comprises the actual addition of client/property to the client list/property list.

<br/>

**Section 1: Parse and Validation of Information**

The first section is facilitated by the following classes:

- `CommandAddParser`: Contains common methods used by `CommandAddClientParser` and `CommandAddPropertyParser`.
- `CommandAddClientParser`: Extracts and validates client information from `commandDescription`(User Input).
- `CommandAddPropertyParser`: Extracts and validates property information from `commandDescription`(User Input).

The following is a simple class diagram of the three classes:
<p align="center">

![](diagrams/CommandAddParserRelatedClassesDiagram.png)

</p>

<p align="center">
Command Add Parser Related Classes Diagram
</p>

As shown above, both `CommandAddClientParser` and `CommandAddPropertyParser` classes have a similar core method called `parseCommand()` which is responsible for client or property detail extraction and validation. The rest of the methods in both classes are sub-methods of the `parseCommand()` method.

Also, most of the sub-methods are used to perform validations on the extracted details. Many of them are implemented via regex pattern checker.

- Client:
    - `checkForValidSingaporeContactNumber(String)`
    - `checkForValidEmail(String)`
    - `checkForBudgetNumberFormat(String)`
    - `checkForDuplicateClient(ClientList, ArrayList<String>)`
- Property:
    - `checkForValidSingaporeAddress(String)`
    - `checkForValidSingaporeLandedPropertyAddress(String)`
    - `checkForValidSingaporeBuildingAddress(String)`
    - `checkForPriceNumberFormat(String)`
    - `checkForValidUnitType(String)`
    - `checkForValidAddressFormatUnitTypeMatching(String, String)`
    - `checkForDuplicateProperty(PropertyList, String)`
- Common:
    - `checkForEmptyDetails(String)`: Checks for any missing essential details, non-essential detail such as optional email can be empty.

**Note**: Since the target user is a property manager working in Singapore, some validations are tailored to Singapore context.

<br/>

**Section 2: Addition of client or property to client list or property list**

The second section is facilitated by the following classes: 
- `CommandAdd`: Abstract superclass of `CommandAddClient` and `CommandAddProperty` classes.
- `CommandAddClient`: Creates a `Client` object and add it to the `clientList`.
- `CommandAddProerty`: Creates a `Property` object and add it to the `propertyList`.

The following is a simple class diagram of the three classes:

<p align="center">

![Command Add Related Classes Diagram](diagrams/CommandAddRelatedClassesDiagram.png)

</p>

<p align="center">
Command Add Related Classes Diagram
</p>


As shown above, both `CommandAddClient` and `CommandAddProperty` classes have a similar core method called `execute(...)` which is responsible for the new client or property addition into their respective lists.

<br/>

**Example Scenario**

Given below is an example scenario on how add client/property behaves at each step.


- **Step 1**: The user executes ```add -client n/NAME c/CONTACT_NUMBER e/EMAIL b/BUDGET_MONTH``` or ```add -property n/NAME a/ADDRESS p/PRICE t/TYPE```. Depending on `add -client` or `add -property` specified, a `Parser` object of type `CommandAddClientParser` or `CommandAddPropertyParser` is created.


- **Step 2**: The `Parser` object will then call method `CommandAddClientParser#parseCommand()` or `CommandAddPropertyParser#parseCommand()` which will check for any incorrect formatting before the extraction and validation of client/property details.


- **Step 3**: If there is no error, a `Command` object of type `CommandAddClient` or `CommandAddProperty` is created.


- **Step 4**: Next, the `Command` object will then call method `CommandAddClient#execute(...)` or `CommandAddProperty#execute(...)` which will add a new `Client` or `Property` type object created into their respective `clientList`/`propertyList`.


- **Step 5**: Lastly, method `Ui#showClientAddedConfirmationMessage()` or `Ui#showPropertyAddedConfirmationMessage()` is called to notify user about the successful addition of new client or property. Also, method `Storage#addToClientFile` or `Storage#addToPropertyFile` is called to update their respective storage files.

The following are simplified sequence diagrams of add feature for client and property:
![Add Client Sequence Diagram](diagrams/AddClientSequenceDiagram.png)
<p align="center">
Add Client Sequence Diagram
</p>

![Add Property Sequence Diagram](diagrams/AddPropertySequenceDiagram.png)
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
```CommandDeleteClient#execute()``` method.

![Delete Client Sequence Diagram](diagrams/DeleteClientSD.png)

The following *sequence diagram* shows how the **delete property** operation works, showcasing the
```CommandDeleteProperty#execute()``` method.

![Delete Property Sequence Diagram](diagrams/DeletePropertySD.png)

---

### Pairing and Unpairing Features

`PairingList` facilitates pair and unpair commands by storing client-property pairs.

When a client rents a property, the client and property form a pair.

* `PairingList` uses a hash map to represent these client-property pairs, where the key is a `Client`
  and the value is `Property`.
* A hash map is chosen due to its constant time lookup performance, making it efficient at querying the property that a
  client is renting.
* Also, the `java.util.HashMap` prevents duplicate keys, which dovetails nicely with the fact that real-life tenants (clients) only have
  one place of residence at any time.

#### Pair Feature

The partial sequence diagram for the pair command, when called from `Duke.java`, is shown below:

![PairingList Pair Sequence Diagram](diagrams/PairingListPairSD.png)

**NOTE**: Self-invocations have been omitted to emphasise inter-object method calls.

The pair command takes in user input of the format:
```
pair ip/PROPERTY_INDEX ic/CLIENT_INDEX
```
where `PROPERTY_INDEX` and `CLIENT_INDEX` must be positive integers which are indexes present in `ClientList`
and `PropertyList`, if their arrays were 1-indexed.

How the pair command works:
1. The user input is first parsed by `Parser` (specifically, `CommandPairParser`).
2. `CommandPairParser` checks the user input for formatting mistakes such as missing flags, wrong flag order and non-integer inputs.
3. After a successful check, a `CommandPair` object is created.
4. When `CommandPair` is executed, there are more checks to validate the parsed input against data from `PropertyList` and
    `ClientList`. These checks throw exceptions when the user inputs contains indexes which are not within the internal arrays of 
    `PropertyList` or `ClientList`.
5. After passing all these checks, the program fetches the desired `Property` and `Client` objects from
   `PropertyList` and `ClientList`.
6. A third layer of checks throws exceptions if the `Client` and `Property` objects already match an existing pair, the
    `Client` is already paired with some other `Property`, or when the user pairs a client whose budget is lower than the property's rental price.
7. The `Client` and `Property` objects are inserted as a pair into the hashmap of `PairingList`.
8. The change is saved in `Storage` and a confirmation message is shown to the user.
 
#### Unpair Feature

The unpair command takes in user input of the format:
```
unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX
```
where `PROPERTY_INDEX` and `CLIENT_INDEX` must be positive integers which are indexes present in `ClientList`
and `PropertyList`, if their private arrays were 1-indexed.

(The sequence diagram for unpair is not provided as the mechanism is similar to that of [Pair](#pair-feature))

How the unpair command works :
1. The user input is first parsed by `Parser` (specifically, `CommandUnpairParser`).
2. `CommandUnpairParser` checks the user input for formatting mistakes such as missing flags, wrong flag order and non-integer inputs.
3. After a successful check, a `CommandUnpair` object is created.
4. When `CommandUnpair` is executed, there are more checks to validate the parsed input against data from `PropertyList` and
   `ClientList`. These checks throw exceptions when the user inputs list indexes which are not within the internal arrays of
    `PropertyList` or `ClientList`.
5. After passing all these checks, the program fetches the desired `Property` and `Client` objects from
   `PropertyList` and `ClientList`.
6. A third layer of checks throws an exception if the `Client` and `Property` objects are not in an existing pair.
7. The `Client`-`Property` pair is deleted from the hashmap of `PairingList`.
8. The change is saved in `Storage` and a confirmation message is shown to the user.

---
### Find Feature

The find command takes in an input from the user in the following format:
```
find -client f/<QUERY_TEXT>
find -property f/<QUERY_TEXT>
```
where `QUERY_TEXT` refers to the text that will be searched through the Client List when the user indicates `-client` and Property List when the user indicates `-property`.

The sequence diagram for both find client and find property is as shown below:
![Find Client Sequence Diagram](diagrams/FindClientSequenceDiagram.png)
![Find Property Sequence Diagram](diagrams/FindPropertySequenceDiagram.png)

The program will iterate through all the entities to search for any matches with the query text. 

For clients, it will identify if the query text matches:
- Name
- Contact Number
- Email
- Budget

For properties, it will identify if the query text matches:
- Landlord Name
- Address
- Rental Price
- Unit Type

For example, if a query text is "Ken" and the address is "Kent Ridge", it will be identified as a match since the word is contained within part of the address.

Upon identifying a match, the program will print out the message to the console providing the full details, inclusive of their respective index number. This is to help facilitate other commands such as pairing or checking.

---

### Storage Feature

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

![Add Client to Storage Diagram](diagrams/StorageAddClientSequenceDiagram.png)
![Add Property to Storage Diagram](diagrams/StorageAddPropertySequenceDiagram.png)
![Add Pair to Storage Diagram](diagrams/StorageAddPairSequenceDiagram.png)

The three sequence diagram above shows the sequence of which the append operation is being invoked. All three
operations are similar in operations but are invoked with different `parameter` and `path`.

### Update To File
The update operation happens when entries in ClientList and PropertyList is being deleted and entries the hash map of 
PairingList is being removed.

The sequence diagram of `updateClient`, `updateProperty` and `updatePair` can be seen below:
![Update Client Sequence Diagram](diagrams/StorageUpdateClientSequenceDiagram.png)
![Update Property Sequence Diagram](diagrams/StorageUpdatePropertySequenceDiagram.png)
![Update Pairing Sequence Diagram](diagrams/StorageUpdatePairSequenceDiagram.png)


Note that when delete operation is being invoked on client and property, the `updatePair` method will also be invoked to
prevent entries retaining within pairingList after it has been deleted from clientList or propertyList.

---

### List feature

There are 3 main steps whenever a list command needs to be executed
* When the user enters any command, it first needs be understood. That is handled by the ParseManager class.
   Next, when the first word entered by the user is determined to be `list`, ParseManager itself then determines
   the type of list command entered, including the tags. ParseListClient, ParseListProperty, ParseListPair and
ParseListEverything are checkers to ensure that a valid command has been entered. ParseListClient and ParseListProperty
also determine if tags have been entered, and if those tags are valid. ParseListPair also checks whether -short 
has been added or not.
  ```
      private Parser parseListCommand(String commandDetail) throws UndefinedSubCommandTypeException {
        ArrayList<String> listCommandTypeAndFlags = getListCommandType(commandDetail);
        boolean isListProperty = listCommandTypeAndFlags.get(SUB_COMMAND_INDEX).trim().equals(PROPERTY_FLAG);
        boolean isListClient = listCommandTypeAndFlags.get(SUB_COMMAND_INDEX).equals(CLIENT_FLAG);
        boolean isListEverything = listCommandTypeAndFlags.get(SUB_COMMAND_INDEX).equals(EVERYTHING_FLAG);
        boolean isListPairs = listCommandTypeAndFlags.get(SUB_COMMAND_INDEX).equals(PAIR_FLAG);
        if (isListProperty) {
            return new ParseListProperty(listCommandTypeAndFlags.get(COMMAND_FLAG_INDEX));
        } else if (isListClient) {
            return new ParseListClient(listCommandTypeAndFlags.get(COMMAND_FLAG_INDEX));
        } else if (isListEverything && listCommandTypeAndFlags.get(COMMAND_FLAG_INDEX).isEmpty()) {
            return new ParseListEverything();
        } else if (isListPairs) {
            return new ParseListPair(listCommandTypeAndFlags.get(COMMAND_FLAG_INDEX));
        } else {
            throw new UndefinedSubCommandTypeException(MESSAGE_INCORRECT_LIST_DETAILS);
        }
    }
  ```
This block of code is part of ParseManager. It determines the type of list operation(-client, 
-property, -pair, or -everything) and returns the corresponding object.  
Both ParseListClient and ParseListObject then determine if tags are present, and if they are valid, 
throwing exceptions if any errors are encountered. They then return the corresponding Command type necessary.

* There are seven different classes which handle each of the features described above. Each class inherits from the
   abstract Command class, and reads information present in either the PropertyList object, ClientList object or both.

* The execute function works in slightly different ways -  
To list with tags(`ListClientsWithTags` and `ListPropertiesWithTags`), it calls the corresponding function. 
That function then loops through all the information about clients and properties, and sends a Client or Property 
object to the Ui class for printing
* For all other cases, the execute function itself runs the loop, which reads every single client, property or pair,
and sends individual objects to the Ui class for display.

* Each of these commands then uses a method present in the Ui class, to print an individual client, or property.
   The loop for printing every single client or property is present in the Command itself.
  ![CommandListClientsClass](diagrams/CommandListClientsClassUpdated.png)
The above is an example for CommandListClients. It reads from ClientList. Then, it displays each line
using the displayOneClient function in Ui. Note that the C tags for class are a result of the PlantUml 
display.  
The sequence diagram of the operation is as follows - 
![ListSequence](diagrams/ListSequenceUpdated.png)
___

## Appendix A: Product Scope

### Target user profile
Property agents who: 
* are managing single owner rental units
* has a need to keep track of information of properties that are being put out for rental.
* has a need to keep track of information of clients' (prospective tenants) information.
* is a fast typist
* favors a command-line interface over a Graphic User Interface.

### Value proposition
Aids property agents in tracking information related to the properties and clients (prospective tenants) they manage. The 
app enables them to easily:
- Record down information of properties (landlord, address, rental price, unit type).
- Record down information of clients (name, contact number, budget), who are looking to rent properties.
- Record down instances where a client decides to rent a property/stop renting a property.
- View client and property information quickly.

___


## Appendix B: User Stories

| Version | As a ... | I want to ...                                           | So that I can ...                                                    |
|---------|----------|---------------------------------------------------------|----------------------------------------------------------------------|
| v1.0    | user     | add properties                                          | keep track of properties                                             |
| v1.0    | user     | add clients                                             | keep track of clients                                                |
| v1.0    | user     | delete properties                                       | prevent properties I am no longer tracking from cluttering my data   |
| v1.0    | user     | delete clients                                          | prevent clients I am no longer tracking from cluttering my data      |
| v1.0    | user     | view a list of properties                               | find out what and how many properties I manage                       |
| v1.0    | user     | view a list of clients                                  | find out what and how many clients I manage                          |
| v1.0    | user     | check the details of a property                         | view the property's information                                      |
| v1.0    | user     | pair a client to a property                             | record down which client is renting which property                   |
| v1.0    | user     | unpair a client to a property                           | update my rental records when a client is no longer renting property |
| v1.0    | user     | save my data                                            | used the data created from a previous use of the app                 |
| v1.0    | user     | quit the app                                            | free up memory for other applications                                |
| v2.0    | user     | check the details of a client                           | view the client's information                                        |
| v2.0    | user     | search clients using their details                      | easily find specific clients                                         |
| v2.0    | user     | search properties using their details                   | easily find specific properties                                      |
| v2.0    | user     | list only specific details about clients and properties | Display the information I need without cluttering the screen         |
| v2.1    | user     | view a list of pairings completed                       | Keep track of all pairings I have already made                       |

---

## Appendix C: Non-Functional Requirements
1. Should work on any Windows, Linux and MacOS that has Java `11` or above installed.
2. The system should respond to the user input within 2 seconds.
3. This program should support loading and storing operation on any mainstream operating system.
4. The program does not require users to have prior programming experience to use.

---
## Appendix D: Glossary

* *client* - Person who is seeking for property to rent

---
## Appendix E: Instructions for Manual Testing

### Launch
1. Download the JAR file and move it into an empty folder.
2. On a command line application, change the current working directory to the same folder as the JAR file and run the app using:
```
java -jar PropertyRentalManager.jar
```
3. Expected: The app's welcome message is printed onto the terminal. 
    
### Add
**Add client**
1. Successful addition of client (With Email)
   1. Prerequisites:
      * Ensure that a valid Singapore contact number is provided. Singapore contact number starts with a `6`, `8` or `9` followed by 7 digits.
      * Ensure that email provided adhere to RFC 5322 Official Email Standard.
      * Ensure that positive integer is provided for budget.
   2. Test case: `add -client n/Gary Oaks c/90876543 e/garyoaks@example.com b/1550`  
      Expected: New client is added. Terminal shows successful add client message along with client details (name, contact number, email, budget per month).


2. Successful addition of client (Without Email)
    1. Prerequisites:
        * Ensure that a valid Singapore contact number is provided. Singapore contact number starts with a `6`, `8` or `9` followed by 7 digits.
        * Ensure that no email is provided.
        * Ensure that positive integer is provided for budget.
    2. Test case: `add -client n/Gary Oaks c/90876543 b/1550`  
       Expected: New client is added. Terminal shows successful add client message along with client details (name, contact number, budget per month).


3. Unsuccessful addition of client (Invalid Contact Number)
    1. Prerequisites:
        * Ensure that an invalid Singapore contact number is provided. Singapore contact number starts with a `6`, `8` or `9` followed by 7 digits.
    2. Test case: `add -client n/Gary Oaks c/10876543 e/garyoaks@example.com b/1550`  
       Expected: Terminal shows invalid Singapore contact number message.

       
4. Unsuccessful addition of client (Invalid Email)
    1. Prerequisites:
        * Ensure that an invalid email is provided. An easy way to replicate an invalid email is to exclude the `@` symbol.
    2. Test case: `add -client n/Gary Oaks c/90876543 e/garyoaksexample.com b/1550`  
       Expected: Terminal shows invalid email message.


5. Unsuccessful addition of client (Invalid Budget)
    1. Prerequisites:
        * Ensure that a non-positive integer input is provided for budget.
    2. Test case: `add -client n/Gary Oaks c/90876543 e/garyoaks@example.com b/0`  
       Expected: Terminal shows invalid budget message.


6. Unsuccessful addition of client (Duplication)
    1. Prerequisites:
        * Successful add a client.
        * Ensure that second client entry has either the same name, contact number. email or any combinations.
    2. Test cases:
       
       `add -client n/Gary Oaks c/90876543 e/garyoaks@example.com b/1550`
        
       `add -client n/Gary Oaks c/60876543 e/garyoaks@example.com b/1550`  
       Expected: Terminal shows duplicating client error message along with the details of existing client.


7. Unsuccessful addition of client (Empty Client Detail)
    1. Prerequisites:
        * Ensure that no client detail is provided.
    2. Test case: `add -client`  
       Expected: Terminal shows add client format error message. Message will include the required input format to add client, as well as an example to help user visualize actual input.


8. Unsuccessful addition of client (Missing Client Detail)
    1. Prerequisites:
        * Ensure that all client flags (except `e/`) are included in the right order.
        * Ensure that at least one of the client details (except email) after any client flags is blank.
    2. Test case: `add -client n/ c/90876543 e/garyoaks@example.com b/`  
       Expected: Terminal shows add client format error message. Message will include the required input format to add client, as well as an example to help user visualize actual input.


9. Unsuccessful addition of client (Missing Client Flag)
    1. Prerequisites:
        * Ensure that at least one of the client flags (except `e/`) is missing.
    2. Test case: `add -client n/Gary Oaks c/90876543 e/garyoaks@example.com 1550`  
       Expected: Terminal shows add client format error message. Message will include the required input format to add client, as well as an example to help user visualize actual input.


10. Unsuccessful addition of client (Wrong Ordering of Client Flags)
    1. Prerequisites:
       * Ensure that all client flags (except `e/`) are present.
       * Ensure that client flags are not in the right order.
    2. Test case: `add -client b/Gary Oaks c/90876543 e/garyoaks@example.com n/1550`  
       Expected: Terminal shows add client format error message. Message will include the required input format to add client, as well as an example to help user visualize actual input.


**Add property**
1. Successful addition of property (Landed Property)
    1. Prerequisites:
        * Ensure that a valid Singapore landed property address (app-specified format) is provided. Landed property address will not have unit level.
        * Ensure that positive integer is provided for rental price.
        * Ensure that unit type label provided contains `LP`. `LP` implies landed property unit type.
    2. Test case: `add -property n/Ash Ketchun a/25A Pallet Town, S121111 p/1600 t/LP BGL`  
       Expected: New property is added. Terminal shows successful add property message along with property details (landlord name, address, renting price, unit type).


2. Successful addition of property (Non-Landed Property)
    1. Prerequisites:
        * Ensure that a valid Singapore building address (app-specified format) is provided. Building address will have unit level and number.
        * Ensure that positive integer is provided for rental price.
        * Ensure that unit type label provided do not contain `LP`. `LP` implies landed property unit type.
    2. Test case: `add -property n/Ash Ketchun a/101 Marlow Street #12-05, S059020 p/1600 t/HDB 3`  
       Expected: New property is added. Terminal shows successful add property message along with property details (landlord name, address, renting price, unit type).


3. Unsuccessful addition of property (Invalid address format)
    1. Prerequisites:
        * Ensure that an invalid Singapore address (different from required format) is provided. 
        * Ensure that positive integer is provided for rental price.
        * Ensure that a valid unit type label is provided.
    2. Test case: `add -property n/Ash Ketchun a/idk whats my address p/1600 t/LP BGL`  
       Expected: Terminal shows invalid address message. Message consists of required address formats and examples to aid user.


4. Unsuccessful addition of property (Invalid Rental Price)
    1. Prerequisites:
        * Ensure that a valid Singapore building address (app-specified format) is provided. Building address will have unit level and number.
        * Ensure that non-positive integer is provided for rental price.
        * Ensure that unit type label provided do not contain `LP`. `LP` implies landed property unit type.
    2. Test case: `add -property n/Ash Ketchun a/101 Marlow Street #12-05, S059020 p/00 t/HDB 3`  
       Expected: Terminal shows invalid rental price message.


5. Unsuccessful addition of property (Invalid Unit Type)
    1. Prerequisites:
        * Ensure that a valid Singapore building address (app-specified format) is provided. Building address will have unit level and number.
        * Ensure that positive integer is provided for rental price.
        * Ensure that none of the 15 app-defined unit type labels is provided. However, unit type must not be empty.
    2. Test case: `add -property n/Ash Ketchun a/101 Marlow Street #12-05, S059020 p/1600 t/hi`  
       Expected: Terminal shows invalid unit type message along with the list of unit type labels pre-defined by the app.


6. Unsuccessful addition of property (Mismatch Address Format and Unit Type)
    1. Prerequisites:
        * Ensure that positive integer is provided for rental price.
        * To simulate mismatch, ensure address format and unit type of property do not belong to the same category (landed property or not).
    2. Test case: `add -property n/Ash Ketchun a/101 Marlow Street, S059020 p/1600 t/HDB 3`  
       Expected: Terminal shows address format and unit type mismatch message along with required formats and examples.


7. Unsuccessful addition of property (Duplication)
    1. Prerequisites:
        * Successful add a property.
        * Ensure that second property entry's address is identical to the first. Its address can have differing letter cases.
    2. Test cases:
       
       `add -property n/Ash Ketchun a/25A Pallet Town, S121111 p/1600 t/LP BGL`  
        
       `add -property n/Joe a/25A pAlLeT ToWn, S121111 p/1600 t/LP BGL`   
       Expected: Terminal shows duplicating property error message along with details of existing property.


8. Unsuccessful addition of property (Empty Property Detail)
    1. Prerequisites:
        * Ensure that no property detail is provided.
    2. Test case: `add -property`  
       Expected: Terminal shows add property format error message. Message will include the required input format to add property, as well as an example to help user visualize actual input.


9. Unsuccessful addition of property (Missing Property Detail)
    1. Prerequisites:
        * Ensure that all property flags are included in the right order.
        * Ensure that at least one of the property details after any property flags is blank.
    2. Test case: `add -property n/Ash Ketchun a/25A Pallet Town, S121111 p/ t/LP BGL`  
       Expected: Terminal shows add property format error message. Message will include the required input format to add property, as well as an example to help user visualize actual input.


10. Unsuccessful addition of property (Missing Property Flag)
    1. Prerequisites:
        * Ensure that at least one of the property flags is missing.
    2. Test case: `add -property Ash Ketchun a/25A Pallet Town, S121111 p/1600 t/LP BGL`  
       Expected: Terminal shows add property format error message. Message will include the required input format to add property, as well as an example to help user visualize actual input.


11. Unsuccessful addition of property (Wrong Ordering of Property Flags)
    1. Prerequisites:
        * Ensure that all property flags are present.
        * Ensure that property flags are not in the right order.
    2. Test case: `add -property a/Ash Ketchun n/25A Pallet Town, S121111 p/1600 t/LP BGL`  
       Expected: Terminal shows add property format error message. Message will include the required input format to add property, as well as an example to help user visualize actual input.


### Delete

### List
1. List clients with or without tags
   1. Test case `list -client`  
      Expected: All clients all listed with all their details. The format of details of each client is given below -  
      ```
      1. Client Name: Doja Cat
         Client Contact Number: 93437878
         Client Email: doja88@example.com
         Client Budget: 2000
      ```
      It also lists the number of clients present at the time, after listing them all. The format for that is as follows - 
      ```There are 2 clients in this list```
   2. Test case: list -client TAG. We will use `list -client n/` to demonstrate  
    Expected: Lists only the names of the clients. The format for each client is as follows -  
      ```1.Doja Cat```  
    It also lists the number of clients present in the list at the end
   3. Test case: `list -client -short`  
    Expected: Lists only the name and budget of all the clients present. The format is as follows -  
    ```
    1. Client Name: Doja Cat
       Client Budget: 2000
    ```
    It also lists the number of clients present in the list in the end.  
    All of these display the following when no clients are present in the list -  
    ```There are 0 clients in this list```
2. List properties with or without tags
    1. Test case: `list -property`  
       Expected: Lists all the properties with all their details  
       The format for each property listed is as follows -  
       ```
       1. Landlord Name: Bob Tan Bee Bee
          Property Address: 25 Lower Kent Ridge Rd, S119081
          Property Rental Price: 1000
          Unit Type: LP Bungalow
       ```
       This also lists the number of properties present in the list at the end. The format for 
       that is as follows -  
       ```There is 1 property in this list```  
   2. Test case: list -property TAG - `We will use list -property a/` to demonstrate  
      Expected: Lists the address of every property present in the list. The format is - 
        `1.	25 Lower Kent Ridge Rd, S119081`
    It also lists the total number of properties present in the list
   3. Test case: `list -property -short`  
    Expected: Lists the address, unit type, and Rental Price of each property in the list. The format
    is as follows - 
      ```
      1. Property Address: 25 Lower Kent Ridge Rd, S119081
         Unit Type: LP Bungalow
         Property Rental Price: 1000
      ```
      All of these display the following when no properties are present in the list -  
    `There are 0 properties in this list`
3. List pairs
    1. Test case `list -pair`  
     Expected: Lists all the pairs present in the list, in no particular order. It shows all the information
     about the clients and properties present in each pair.
       ```
       Client:
           Client Name: Doja Cat
           Client Contact Number: 93437878
           Client Email: doja88@example.com
           Client Budget: 2000
       Property:
           Landlord Name: Bob Tan Bee Bee
           Property Address: 25 Lower Kent Ridge Rd, S119081
           Property Rental Price: 1000
           Unit Type: LP Bungalow
       ```
       It also lists the number of pairs present in the list. The format is as follows -  
       ```There is 1 pair in this list```
   2. Test case `list -pair -short`
    Expected: Lists the short details of both clients and properties described earlier, for every 
    pair in the list. The format is as follows -  
       ```
       Client:
          Client Name: Doja Cat
          Client Budget: 2000
       Property:
          Property Address: 25 Lower Kent Ridge Rd, S119081
          Unit Type: LP Bungalow
          Property Rental Price: 1000
       ```
      It also lists the total number of pairs present in the list.
4. List everything
    1. Test case `list -everything`  
    Expected behaviour: Lists all information including and all details present in the list about clients, properties, and pairs.  
    In this case, a single client and property are present in the list, and they are paired. This is the expected output -  
       ```
       Clients:
       1.  Client Name: Doja Cat
           Client Contact Number: 93437878
           Client Email: doja88@example.com
           Client Budget: 2000
       --------------------------------------------------------------------------------
       There is 1 client in this list

       --------------------------------------------------------------------------------
       Properties:
       1.  Landlord Name: Bob Tan Bee Bee
           Property Address: 25 Lower Kent Ridge Rd, S119081
           Property Rental Price: 1000
           Unit Type: LP Bungalow
       --------------------------------------------------------------------------------
       There is 1 property in this list

       --------------------------------------------------------------------------------
       Pairs:
       Client:
          Client Name: Doja Cat
          Client Contact Number: 93437878
          Client Email: doja88@example.com
          Client Budget: 2000
       Property:
          Landlord Name: Bob Tan Bee Bee
          Property Address: 25 Lower Kent Ridge Rd, S119081
          Property Rental Price: 1000
          Unit Type: LP Bungalow
       --------------------------------------------------------------------------------
       There is 1 pair in this list
       ```

### Pair
1. Successful Pairing
    1. Prerequisites: 
       * Have at least 1 client and 1 property added to the app. 
       * Ensure that the client's budget is higher than or equal to the property's rental price.
       * Ensure that the client and property have **NOT** been paired.
    2. Test case: `pair ip/1 ic/1`

        Expected: Pairing is added. Terminal shows successful pairing message, the name of the paired client, 
        and the address of the paired property.


2. Failed Pairing (budget exceeded)
   1. Prerequisites:
       * Have at least 1 client and 1 property added to the app.
       * Ensure that the client's budget is lower than the property's rental price.
   2. Test case: `pair ip/1 ic/1`
   
       Expected: Terminal shows unsuccessful pairing message, name and budget of client, and the address and rental 
       price of the property.


3. Failed Pairing (client already paired to another property)
   1. Prerequisites:
       * Have at least 1 client and 2 properties added to the app.
       * Ensure that the client's budgets are higher than or equal to the rental prices of both properties.
       * Have paired the client and a property e.g `pair ip/1 ic/1`
   2. Test case: `pair ip/2 ic/1` (pair a different property to the same client)
   
      Expected: Terminal shows unsuccessful pairing message.


4. Failed Pairing (client and property form an existing pairing)
    1. Prerequisites:
        * Have at least 1 client and 1 property added to the app.
        * Ensure that the client's budgets is higher than or equal to the property's rental price.
        * Have paired the client and property e.g `pair ip/1 ic/1`
    2. Test case: `pair ip/1 ic/1` (re-pair using the same indexes)
   
        Expected: Terminal shows unsuccessful pairing message.


### Unpair
1. Successful unpairing:
   1. Prerequisites:
      * Have at least 1 client and 1 property added to the app.
      * Have paired the client and property e.g. `pair ip/1 ic/1`
   2. Test case: `unpair ip/1 ic/1` (unpair using the same indexes as the pair command)
      
       Expected: Pairing is deleted. Terminal shows successful unpairing message with the client's name and the 
       property address.


2. Failed unpairing (unpairing a property and a client that have not been paired):
   1. Prerequisites:
      * Have at least 1 client and 1 property added to the app.
      * Have **NOT** paired the client and property.
   2. Test case: `unpair ip/1 ic/1`
       
       Expected: Terminal shows unsuccessful unpairing message.


### Check

#### Check Property
1. Successful check property
   1. Prerequisites:
      * Have at least 2 clients and 2 properties added to the app.
      * Ensure that all clients have budgets equal to or greater than that of the properties.
      * Pair one of the properties with 2 clients: e.g. input `pair ip/1 ic/1` and `pair ip/1 ic/2`
   2. Test case: `check -property i/1`
      
      Expected: Terminal shows details of the property and information of the clients renting the property. Number of list results is greater than 0.
   3. Test case: `check -property i/2`
      
       Expected: Terminal shows details of the property, number of list results is 0. 
   

2. Failed check property
   1. Test case: `check -property i/0`
   
        Expected: Terminal shows error message.
   
   2. Test case: `check -property i/[INDEX]`, where INDEX is an index that is not in the property list (1-indexed).
   
        Expected: Terminal shows error message.

   3. Test case: `check -property i/1r2342`
   
        Expected: Terminal shows error message.

### Find

1. Querying for Client/Property stored in the Client/Property List:
  
    1. Prerequisites: List the clients using `list -client` command to verify that there is at least 1 client  in the database.
    2. Test case: `find -client f/<QUERY_TEXT>`
        - Enter the name (or part of the name) of the client that is shown in the list for the `<QUERY_TEXT>` portion. Omit the directional brackets when entering query text.
        - Expected: All the clients that matches the entered name is displayed with their respective index shown.
    3. Test case: `find -client f/<QUERY_TEXT>`
        - Enter a random text that does not match with any of the client. Omit the direction brackets when entering query text.
        - Expected: An error message will be displayed stating that there is no client that matches with the queried text.
    4. Test case: `find -client n/ f/`
        - Expected: An error message will be displayed indicating that there should only be 1 flag and the flag should be `f/`
    5. Test case: `find -client`
        - Expected: An error message will be displayed indicating that the tag is missing.
    6. Test case: `find -property f/<QUERY_TEXT>`
        - Enter the address (or part of an address) of the property that is in the list for the `<QUERY_TEXT>` portion. Omit the directional brackets when entering the query text.
        - Expected: All the property that matches the address will be displayed along with their respective index.
    7. Test case: `find -property f/<NON_EXISTENT_QUERY_TEXT>`
        - Enter a random text, that is not in the property list. Omit the directional bracket when entering the text.
        - Expected: A message stating that no property matches the text will be displayed.
    8. Test case: `find -property t/3-Room f/`
        - Expected: An error message will be displayed showing that there should only be one tag and the tag should be `f/`.
    9. Test case: `find -property`
        - Expected: An error stating that the tag is missing will be shown.
    10. Any other tags displayed after the `f/` tag will not be flagged as an error since it's possible that names contains a forward slash (/).
    
### Storage


### Finding for Client/Property:
1. Querying for Client/Property stored in the Client/Property List:
  
    1. Prerequisites: List the clients using `list -client` command to verify that there is at least 1 client  in the database.
    2. Test case: `find -client f/<QUERY_TEXT>`
        - Enter the name (or part of the name) of the client that is shown in the list for the `<QUERY_TEXT>` portion. Omit the directional brackets when entering query text.
        - Expected: All the clients that matches the entered name is displayed with their respective index shown.
    3. Test case: `find -client f/<QUERY_TEXT>`
        - Enter a random text that does not match with any of the client. Omit the direction brackets when entering query text.
        - Expected: An error message will be displayed stating that there is no client that matches with the queried text.
    4. Test case: `find -client n/ f/`
        - Expected: An error message will be displayed indicating that there should only be 1 flag and the flag should be `f/`
    5. Test case: `find -client`
        - Expected: An error message will be displayed indicating that the tag is missing.
    6. Test case: `find -property f/<QUERY_TEXT>`
        - Enter the address (or part of an address) of the property that is in the list for the `<QUERY_TEXT>` portion. Omit the directional brackets when entering the query text.
        - Expected: All the property that matches the address will be displayed along with their respective index.
    7. Test case: `find -property f/<NON_EXISTENT_QUERY_TEXT>`
        - Enter a random text, that is not in the property list. Omit the directional bracket when entering the text.
        - Expected: A message stating that no property matches the text will be displayed.
    8. Test case: `find -property t/3-Room f/`
        - Expected: An error message will be displayed showing that there should only be one tag and the tag should be `f/`.
    9. Test case: `find -property`
        - Expected: An error stating that the tag is missing will be shown.
    10. Any other tags displayed after the `f/` tag will not be flagged as an error since it's possible that names contains a forward slash (/).


