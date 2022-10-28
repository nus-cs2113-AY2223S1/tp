# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary


| Action                                          | Format, Examples                                                                                                                                                                                                  |
|-------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Go to the main menu and choose accordingly      | Format: ‘main’, then the user  will to choose between three options:<br/>Patient(1), Visit(2), and Prescription(3)                                                                                                |
| Add patient                                     | Format: add i/ID d/date_of_visit t/time_of_visit [r/reason] (in Patient i.e. input ‘1’ in the main menu) <br/>Example: add i/S7093944G n/John Smith d/30/2/2022 t/16:00:00 r/Having Flu                           |
| View all Patients                               | Format: viewAll (in Patient i.e. input ‘1’ in the main menu)                                                                                                                                                      |
| Retrieve information about a patient            | Format: retrieve i/ID  (in Patient i.e. input ‘1’ in the main menu)                                                                                                                                               |  
| Modifying a patient’s record                    | Format: edit i/ID [n/name] [g/M/F] [d/date_of_birth] (in Patient i.e. input ‘1’ in the main menu)                                                                                                                 |
| Add a patient visit                             | Format: add i/ID d/date_of_visit t/time_of_visit [r/reason] (in Visit i.e. input ‘2’ in the main menu)<br/>Example: add i/S7093944G n/John Smith d/30/2/2022 t/16:00:00 r/Having Flu                              |  
| Add/Edit a reason for a patient visit           | Format: reason x/index [r/reason] (in Visit i.e. input ‘2’ in the main menu)<br/>Example: reason x/4 r/Having Flu and Fever                                                                                       |
| Delete a Reason for patient visit               | Format: deleteReason x/index (in Visit i.e. input ‘2’ in the main menu)<br/>Example: reason x/4                                                                                                                   |  
| View a list of all patients visits              | Format: viewAll (in Visit i.e. input ‘2’ in the main menu)                                                                                                                                                        |
| View a list of all visits for one patient       | Format: viewPatient i/ID (in Visit i.e. input ‘2’ in the main menu)                                                                                                                                               |  
| Viewing information for patient’ specific visit | Format: viewVisit x/index (in Visit i.e. input ‘2’ in the main menu)<br/>Example: viewVisit x/5                                                                                                                   |
| Add a new Prescription Record                   | Format: add i/ID n/medicine_name d/medicine_dosage t/time_interval(in Prescription i.e. input ‘3’ in the main menu)<br/>Example: add i/S7093944G n/Problaxan d/10 mg t/take 15 minutes after every meal, 3x a day |  
| Modifying a patient’s prescription              | Format: edit x/[index] ([n/medicine_name] or [d/medicine_dosage] or [t/time_interval]) (in Prescription i.e. input ‘3’ in the main menu)<br/>Example: edit i/4 d/20 mg                                            |
| View a list of existing prescriptions           | Format: viewAll (in Prescription i.e. input ‘3’ in the main menu)                                                                                                                                                 |  
| View all patient’s prescription                 | Format: viewPatientPres i/ID (in Prescription i.e. input ‘3’ in the main menu)                                                                                                                                    |
| View all patient’s active prescription          | Format: viewActPatientPres i/ID (in Prescription i.e. input ‘3’ in the main menu)                                                                                                                                 |  
| Change prescription status to active            | Format: activate x/[index] (in Prescription i.e. input ‘3’ in the main menu)                                                                                                                                      |
| Change prescription status to inactive          | Format: deactivate x/[index] (in Prescription i.e. input ‘3’ in the main menu)                                                                                                                                    |  
| Exiting the program                             | Format: bye                                                                                                                                                                                                       |





* Add todo `todo n/TODO_NAME d/DEADLINE`
