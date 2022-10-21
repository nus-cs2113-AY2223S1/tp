# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### PrescriptionList component
**API**: `PrescriptionList.java`

![](../diagrams/PrescriptionListClassDiagram.png)

The `PrescriptionList` component,
* stores the list of prescriptions
* can add new prescription to the list
* can edit existing prescriptions inside the list
* can change the status of prescription inside the list
* can view all prescriptions, view a patient's prescription or view a patient's active prescription 
* depends on `UI` class (because the `PrescriptionList` component needs to interact with user through the `UI` 
component)

**Methods in `PrescriptionList` class:**

* **`add`** - This method allow user to add prescription into the list by specifying `patientId`, `medicine`, `dosage` and 
`timeInterval`.
* **`viewAll`** - This methods iterates through the list of all prescriptions and print the details of prescriptions from
all patients.
* **`viewPatientPrescription`** - This method iterates through the list of prescriptions and print the details of 
prescriptions from the specified `patientId`.
* **`viewActivePatientPrescription`** - This method iterates through the list of prescriptions and print the details of
all active prescriptions with the specified `patientId`
* **`edit`** - This method allows user to edit the `medicine`, `dosage` or `timeInterval` of the prescription of the 
specified index
* **`activatePrescription`** - This method allows user to set the prescription of specified index as active.
* **`deactivatePrescription`** - This method allows user to set the prescription of specified index as inactive.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
