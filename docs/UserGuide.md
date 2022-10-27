# User Guide

## Introduction

OneDoc is a desktop application for doctors to manage patients’ profile, visit records, prescriptions records and
medicine information via Command Line Interface.

- [User Guide](#user-guide)
    * [Introduction](#introduction)
    * [Quick Start](#quick-start)
    * [Features](#features)
        + [Main Menu](#main-menu)
            - [Going to main menu: `main`](#going-to-main-menu-main)
            - [Choosing an option: `index`](#choosing-an-option-index)
        + [3. Prescription](#3-prescription)
            - [Adding New Prescription: `add`](#adding-new-prescription-add)
            - [Modifying a patient’s prescription: `edit`](#modifying-a-patient-s-prescription-edit)
            - [Viewing List of All Existing Prescriptions: `viewAll`](#viewing-list-of-all-existing-prescriptions-viewall)
            - [Viewing all patient’s prescription: `viewPatientPres`](#viewing-all-patient-s-prescription-viewpatientpres)
            - [Viewing all patient’s active prescription: `viewActPatientPres`](#viewing-all-patient-s-active-prescription-viewactpatientpres)
            - [Changing prescription status to active: `activate`](#changing-prescription-status-to-active-activate)
            - [Changing prescription status to inactive: `deactivate`](#changing-prescription-status-to-inactive-deactivate)
    * [FAQ](#faq)
    * [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `OneDoc` from [here](http://link.to/duke).

## Features 

### Main Menu
The main menu will allow the user to choose between three options: (1) Patient, (2) Visit, and (3) Prescription 

#### Going to main menu: `main`
Allow the user to view and go to main menu at any point of the program

Format / Example: `main`

Expected Output:

```
Hello welcome to
ıllıllı OneDoc ıllıllı
Please choose one of the following options:
1 - Patients
2 - Visits
3 - Prescription
bye - Quit OneDoc
```

#### Choosing an option: `index`
Chooses an option for the user to continue with. The application will direct the user to 3 different pages accordingly.

Format: `index`

* The `index` can be 1, 2, or 3 to indicate a sub-menu, or bye to exit the program

Example of usage:
* `1`
* `bye`


### 3. Prescription

#### Adding New Prescription: `add`
Add a new prescription to the list of prescriptions.

Format: `add i/patientID n/medicine_name d/medicine_dosage t/time_interval`

* The `patientID` should be the ID of a patient that is already in the patient list
* The `patientID` is case-insensitive
* The `medicine_name` should be either one or two words
* The `medicine_dosage` should be formatted as a number followed by a unit
* The `time_interval` can be in any alphabets, numbers and spaces but not other characters

Example of usage:

`add i/T1 n/Problaxan d/10 mg t/take 15 minutes after every meal`

Expected Output:

```
You have added a prescription!
    ____________________________________________________________
Prescription #3
    ID: T1
    Medicine: Problaxan
    Dosage: 10 mg
    Time Interval: take 15 minutes after every meal
    Status: Active
    ____________________________________________________________
```

#### Modifying a patient’s prescription: `edit`
Modifies one part of a prescription’s record

Format: `edit x/index n/medicine_name` or `edit x/index d/medicine_dosage` or `edit x/index t/time_interval`

* The `index` must be an integer within the range from 1 to the total number of prescriptions in the list
* The `medicine_name` should be either one or two words
* The `medicine_dosage` should be formatted as a number followed by a unit
* The `time_interval` can be in any alphabets, numbers and spaces but not other characters

Example of usage:

`edit x/3 d/20 mg`

Expected output:

```
You have edited the prescription!
    ____________________________________________________________
Prescription #3
    ID: T1
    Medicine: Problaxan
    Dosage: 20 mg
    Time Interval: take 15 minutes after every meal
    Status: Active
    ____________________________________________________________
```

#### Viewing List of All Existing Prescriptions: `viewAll`
Display the list of all prescription records for all patients

Format: `viewAll`

Expected output: 

* Assume there are currently 2 prescription records in the system. 

```
Here are all the prescriptions:
    ____________________________________________________________
Prescription #1
    ID: T1
    Medicine: cough syrup
    Dosage: 10 mL
    Time Interval: every 3 hours
    Status: Inactive
    ____________________________________________________________
Prescription #2
    ID: T2
    Medicine: penicillin
    Dosage: 1 tablet
    Time Interval: every 3 days
    Status: Active
    ____________________________________________________________
```

#### Viewing all patient’s prescription: `viewPatientPres`
Views all prescription records for a specific patient

Format: `viewPatientPres i/pateintID`

* The `patientID` should be the ID of a patient that is already in the patient list
* The `patientID` is case-insensitive

Example of usage:

`viewPatientPres i/T1`

Expected output:

```
Here are all the prescriptions:
    ____________________________________________________________
Prescription #1
    ID: T1
    Medicine: cough syrup
    Dosage: 10 mL
    Time Interval: every 3 hours
    Status: Inactive
    ____________________________________________________________
```

#### Viewing all patient’s active prescription: `viewActPatientPres`
Views all active prescription records for a patient

Format: `viewActPatientPres i/patientID`

* The `patientID` should be the ID of a patient that is already in the patient list
* The `patientID` is case-insensitive

Example of usage:

`viewActPatientPres i/T1`

Expected output:

* If the patient with ID `T1` has no active prescription and `viewActPatientPres i/T1` is executed.

```
There are currently no active prescriptions from this patient.
```

* If the patient with ID `T2` has active prescription and `viewActPatientPres i/T2` is executed, only his active
prescriptions would be shown.

```
Here are all the prescriptions:
    ____________________________________________________________
Prescription #2
    ID: T2
    Medicine: penicillin
    Dosage: 1 tablet
    Time Interval: every 3 days
    Status: Active
    ____________________________________________________________
```

#### Changing prescription status to active: `activate`
Changes a specific prescription record to be active

Format: `activate x/index`

* The `index` must be an integer within the range from 1 to the total number of prescriptions in the list

Example of usage:

`activate x/3`

Expected output:

```
Ok, I've activated the prescription below:
    ____________________________________________________________
Prescription #3
    ID: T1
    Medicine: Problaxan
    Dosage: 20 mg
    Time Interval: take 15 minutes after every meal
    Status: Active
    ____________________________________________________________
```

#### Changing prescription status to inactive: `deactivate`
Changes a specific prescription record to be inactive

Format: `deactivate x/index`

* The `index` must be an integer within the range from 1 to the total number of prescriptions in the list

Example of usage:

`deactivate x/3`

Expected output:

```
Ok, I've deactivated the prescription below:
    ____________________________________________________________
Prescription #3
    ID: T1
    Medicine: Problaxan
    Dosage: 20 mg
    Time Interval: take 15 minutes after every meal
    Status: Inactive
    ____________________________________________________________
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: The data is stored in the /data folder of the computer where you ran the program, under the files `patient.txt`, 
`prescription.txt`, and `visit.txt`. In order to transfer the data to another computer, move the /data folder to a
new directory on your new computer along with the OneDoc JAR file, and the JAR file in that directory.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
