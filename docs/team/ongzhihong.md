# ongzhihong's Project Portfolio Page

## Product Overview
easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange. In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Code Contributed
This [link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=ongzhihong&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) indicates the breakdown of code contributed, in terms of documentation, functional code, and test code.

## Summary of Contributions

### Command Package

Created a Command package to account for every command that the user can input. Package has a Command (Abstract Class) and a CommandType (Enumeration) along with other Command types.

* **Some notable PRs**: [#40](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/40), [#235](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/235)

### Command Parser

Created a CommandParser class to make sense of user input parameters and create the appropriate Command class.

* **Some notable PRs**: [#41](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/41), [#42](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/42), [#86](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/86), [#94](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/94), [#100](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/100)

### Duke

Managed the logic in Duke class to integrate different classes created by other team members (UserUniversityListManager, Ui, Database, etc) along with CommandParser to ensure the program flows logically.

* **Some notable PRs**: [#53](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/53), [#101](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/101), [#119](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/119)

### PED Bugs

Patched some bugs that were found in the module's Practical Exam Dry Run (PED).

* **Some notable PRs**: [#184](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/184)

### Contribution to the User Guide UG

Added basic documentation with formats for Commands in the Command Package to inform user of the correct format to use.

* **Some notable PRs**: [#107](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/107)

### Contribution to the Developer Guide DG

Added class diagram for Command package. Added sequence diagrams for the creation and execution of different commands, showing the interaction between Duke, CommandParser and other classes that are needed to execute the commands (Ui, Database, UserUniversityListManager, etc).

* **Some notable PRs**: [#85](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/85), [#104](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/104)

### Contributions to Team-Based Tasks

* Maintained issue tracker, labelled and tagged various pull requests.
* Responsible for cleaning up before releases, which include integrating everybody's work and identifying bugs.
* Helped out peers in debugging.

### Contributions beyond the project team
* Review/mentoring contributions: Provided comments during code reviews of other students code for their individual and team projects. ([IP jorellesee](https://github.com/nus-cs2113-AY2223S1/ip/pull/25/files/57329172ca6f3eb3861e80a992e2a8919145441c), [IP deveshl](https://github.com/nus-cs2113-AY2223S1/ip/pull/63/files/c5cc698efd80722355ac09b3b3c64c17faf28986), [TP TracknFit](https://github.com/nus-cs2113-AY2223S1/tp/pull/4/files/82775595be55ebf1e2bc0ae149992e7fdaf86e79))
* Helped out peers in debugging git control issues.
* PED contributions: Reported a total of 19 program bugs for other teams during PED. ([Link](https://github.com/ongzhihong/ped/issues))