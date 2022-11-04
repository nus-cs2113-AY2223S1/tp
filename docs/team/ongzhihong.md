# ongzhihong's Project Portfolio Page

## Product Overview
easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange. In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Code Contributed
This [link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=ongzhihong&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) indicates the breakdown of code contributed, in terms of documentation, functional code, and test code.

### Summary of Contributions

### Command Package

Created a Command package to account for every command that the user can input.
* Command (Abstract class)
* CommandType (Enumeration)
* AddCommand
* DeleteCommand
* ExitCommand
* HelpCommand
* ViewCommand
* ListCommand
* CreateCommand

### Command Parser

Created a CommandParser class to make sense of user input parameters and create the appropriate Command class.

### Duke

Managed the logic in Duke class to integrate different classes created by other team members (UserUniversityListManager, Ui, Database, etc) along with CommandParser to ensure the program flows logically.

### PED Bugs

Patched some bugs that were found in the module's Practical Exam Dry Run (PED).

### Contribution to the User Guide UG

Added basic documentation with formats for Commands in the Command Package to inform user of the correct format to use.

### Contribution to the Developer Guide DG

Added class diagram for Command package. Added sequence diagrams for the creation and execution of different commands, showing the interaction between Duke, CommandParser and other classes that are needed to execute the commands (Ui, Database, UserUniversityListManager, etc).

### Contributions beyond the project team
* Review/mentoring contributions: Provided comments during code reviews of other students code for their individual and team projects.
* PED contributions: Reported a total of 19 program bugs for other teams during PED.