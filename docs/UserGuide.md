# User Guide

## Introduction

OneDoc is a desktop application for doctors to manage patients’ profile, visit records, prescriptions records and
medicine information via Command Line Interface.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `OneDoc` from [here](http://link.to/duke).

## Features 

### Main Menu
The main menu will allow the user to choose between three options:
<br> (1) Patient, (2) Visit, and (3) Prescription 
#### Going to main menu: `main`
Allow the user to view and go to main menu at any point of the program
<br>Format / Example: `main`

Expected Output:
<br>Hello welcome to
<br>ıllıllı OneDoc ıllıllı
<br>Please choose one of the following options:
<br>1 - Patients
<br>2 - Visits
<br>3 - Prescription
<br>bye - Quit OneDoc

#### Choosing an option: `index`
Chooses an option for the user to continue with. The application will direct the user to 3 different pages accordingly.

Format: `index`

* The `index` can be 1, 2, or 3 to indicate a sub-menu, or bye to exit the program


Example of usage:
<br>`1`
<br>`bye`


### 3. Prescription

#### Adding New Prescription: `add`
Add a new prescription to the list of prescriptions.

Format: `add i/patientID n/medicine_name d/medicine_dosage t/time_interval`

* The `medicine_name` should be either one or two words
* The `medicine_dosage` should be formatted as a number followed by a unit
* The `time_interval` can be in a natural language format

Example of usage:

`add i/add i/S7093944G n/Problaxan d/10 mg t/take 15 minutes after every meal, 3x a day`

Expected Output:

```

```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: The data is stored in the /data folder of the computer where you ran the program, under the files `patient.txt`, 
`prescription.txt`, and `visit.txt`. In order to transfer the data to another computer, move the /data folder to a
new directory on your new computer along with the OneDoc JAR file, and the JAR file in that directory.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
