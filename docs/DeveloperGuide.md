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
### Common Classes
___
## Implementation

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
```PropertyList#deleteClient()``` method.

![Delete Property Sequence Diagram](diagrams/DeletePropertySD.png)

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