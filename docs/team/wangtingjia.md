# Wang Tingjia - Project Portfolio Page (PPP)

## Product Overview

easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities,
creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Summary of contributions

### Code Contributed

This [link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=wangtingjia&breakdown=true) indicates the contribution,
broken down into documentation, functional and test code.


### Features & Enhancements Implemented

#### UserUniversityListManager, UserUniversityList Classes
* The main classes for users to store their university and a corresponding list of modules 
* Users are able to create lists, add modules, add comments and delete them respectively.
* This forms the backbone of the functionalities corresponding to user inputs, timetables and storage.
* **Notable PRs**: [#9](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/9), [#23](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/23)

#### UserModuleMappingList, UserModuleMapping Classes
* This is the main classes that UserUniversityListManager builds upon in OOP style.
* Each UserModuleMapping stores the information needed for a successful module mapping and UserModuleMappingList manages them.
* **Notable PRs**: [#9](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/9), [#23](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/23)

#### Favourite function
* Users are able to favourite some top picks for their SEP spots. 
* This aids them in managing a large number of lists, especially given the UI of our CLI application
* **Notable PRs**: [#70](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/70)

#### Adding & Deleting of Comments function
* Users are able to add and delete comments in regard to a particular module he/she wants to map. This can include notes such as "only offered in sem 2", "need additional pre-requisites", etc.
* Integrated this functions with other classes including Duke.main(), Command Class and Ui class.
* **Notable PRs**: [#180](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/180), [#185](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/185)

#### Unit Testing & Assertions
* Added JUnit tests and assertions to make code more defensive and facilitate effective and quick regression testing.
* **Notable PRs**: [#38](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/38)

#### Setup Script
* Added a setup script to automatically download data and store in the right folder during execution
* This simplifies usage for users and addresses some feedback we received that our setup is troublesome
* **Notable PRs**: [#170](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/170)

#### Integrated TimetableManager Class into UserUniversityListManager class
* From PED and our own testing, we found that the TimetableManager and UserUniversityListManager class did not execute correctly in tandem as they were written by 2 different groupmates entirely
* Successfully refactored it into UserUniversityListManager class to remove possible bugs. 
* **Notable PRs**: [#176](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/176)

#### PED Bugs
* Got rid of bugs found in the module's Practical Exam Dry Run (PED).
* **Notable PRs**: [#178](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/178), [#191](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/191), [#192](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/192)

### Contributions to the User Guide UG
* Completed the bulk of the User Guide first draft, documenting all functions, valid inputs and expected outputs
* **Notable PRs**: [#115](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/115)

### Contributions to the Developer Guide DG
* Added relevant UML & Sequence diagrams for my classes
* **Some notable PRs**: [#95](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/95), [#78](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/78), [#113](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/113), [#108](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/108)

### Contributions to Team-Based Tasks
- to be added

### Review / Mentoring Contributions
- to be added

### Contributions beyond the project team
- to be added