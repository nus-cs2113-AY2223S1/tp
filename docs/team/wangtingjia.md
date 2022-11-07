# Wang Tingjia - Project Portfolio Page (PPP)

## Product Overview
easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities,
creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

### Summary of contributions

[Code contributions](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=wangtingjia&breakdown=true)
broken down into documentation, functional and test code.

### Features

#### UserUniversityListManager, UserUniversityList Classes
* The main classes for users to store their university and a corresponding list of modules. Users are able to create lists, add modules, add comments and delete them respectively.
* **Notable PRs**: [#9](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/9), [#23](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/23)

#### UserModuleMappingList, UserModuleMapping Classes
* This is the main classes that UserUniversityListManager builds upon in OOP style. Each UserModuleMapping stores the information needed for a successful module mapping and UserModuleMappingList manages them.
* **Notable PRs**: [#9](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/9), [#23](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/23)

#### Favourite function
* Users are able to favourite some top picks for their SEP spots.
* **Notable PRs**: [#70](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/70)

#### Adding & Deleting of Comments function
* Users are able to add and delete comments in regard to a particular module he/she wants to map.
* **Notable PRs**: [#180](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/180), [#185](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/185)

#### Setup Script
* Added a setup script to automatically download data and store in the right folder during execution
* **Notable PRs**: [#170](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/170)

#### Integrated TimetableManager Class into UserUniversityListManager class
* Successfully refactored it into UserUniversityListManager class to remove possible bugs. 
* **Notable PRs**: [#176](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/176)

#### PED Bugs
* Got rid of bugs found in the module's Practical Exam Dry Run (PED).
* **Notable PRs**: [#178](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/178), [#191](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/191), [#192](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/192)

### Contributions to the User Guide UG
* Documented majority of the functions including `VIEW`, `ADD`, `FAVOURITE`, `CREATE`, `DELETE`.
* **Notable PRs**: [#115](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/115)

### Contributions to the Developer Guide DG
* Added relevant UML & Sequence diagrams for my classes under sections `2.7 User Module Mapping` and `2.8 User University List Manager`.
* **Some notable PRs**: [#95](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/95), [#78](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/78), [#113](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/113), [#108](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/108)

### Contributions to Team-Based Tasks
* Maintained GitHub issues on existing bugs and assigned team members.
* Reviewed PRs from all team members.

### Contributions beyond the project team
* Forum Contributions: Clarified doubts about CI checkstyle issue on CS2113 GitHub [Forum Post #35](https://github.com/nus-cs2113-AY2223S1/forum/issues/35)
* Reviewing / Mentoring Contributions: Provided feedback to other students taking the modules for their projects [iP #5](https://github.com/nus-cs2113-AY2223S1/ip/pull/5), [iP #42](https://github.com/nus-cs2113-AY2223S1/ip/pull/42), [tP #3](https://github.com/nus-cs2113-AY2223S1/tp/pull/3)
* Provided feedback during PED [Bugs Reported](https://github.com/wangtingjia/ped/issues)