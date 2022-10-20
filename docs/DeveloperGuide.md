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

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
___
## Design
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Architecture
### UI Component
### Client Component
### Property Component
### Model Component
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



___
## Documentation, logging, testing, configuration and dev-ops
___
## Appendix: Requirements
___
## Appendix: Instruction for Manual Testing

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing