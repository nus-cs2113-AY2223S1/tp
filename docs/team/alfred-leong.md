# alfred-leong's Project Portfolio Page (PPP)

## Product Overview

easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Summary of contributions

### Code Contributed

This [link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=alfred-leong&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) indicates the breakdown of code contributed, in terms of documentation, functional code, and test code.

### Enhancements Implemented

#### User Storage
User storage refers to saving and loading user information into text files. Initially, user information was stored in two text files, one for all saved modules and another for all timetables.
In the most recent version, user information is stored in several text files, where each text file stores the relevant information for the respective partner university. When an invalid file format is detected, the corresponding text file will be deleted. Much effort was put into detecting invalid files and recovering valid files.

* **Some notable PRs**: [#33](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/33), [#48](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/48), [#98](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/98), [#206](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/206)

#### Delete History
To help users recall the modules that they had recently deleted, the delete history feature allows them to view up to 5 most recently deleted module mappings.
This helps users to add previous module mappings without having to search for the specific module code again.

* **Some notable PRs**: [#60](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/60)

#### Help Command
To allow users to view the list of valid commands available in this program.

* **Some notable PRs**: [#57](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/57), [#172](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/172)

#### Unit Testing & Assertions
Added JUnit tests and assertions to make code more defensive and facilitate effective and quick regression testing.
* **Some notable PRs**: [#48](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/48), [#55](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/55), [#87](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/87), [#99](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/99), [#110](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/110)

#### PED Bugs
Got rid of bugs found in the module's Practical Exam Dry Run (PED).
* **Some notable PRs**: [#171](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/171), [#172](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/172)

### Contributions to the User Guide UG
Added documentation for help command and view delete history command. Rearranged components in alphabetical order for better flow.
Also fixed minor bugs pointed out during PED, such as updating abbreviations, incorrect examples and general standardisation of formats. 
* **Some notable PRs**: [#172](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/172)

### Contributions to the Developer Guide DG
Added class and sequence diagrams for the implementation of User Storage and Delete History using PlantUML.
Updated class diagrams to follow standard notation.
* **Some notable PRs**: [#84](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/84), [#102](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/102), [#179](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/179), [#208](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/208)

### Contributions to Team-Based Tasks
* Performed extensive testing to find and fix bugs for all features of the app
* Maintained issue tracker by tagging and labelling various pull requests for effective reference
* Exhibited strong project management skills, reminding team members about uncompleted tasks and voluntarily do submissions (eg. release v1.0)

### Contributions beyond the project team
#### Review/mentoring contributions
Gave suggestions to other students taking the modules for their individual/team projects:
  [iP #40](https://github.com/nus-cs2113-AY2223S1/ip/pull/40), [iP #63](https://github.com/nus-cs2113-AY2223S1/ip/pull/63),
  [tP #1](https://github.com/nus-cs2113-AY2223S1/tp/pull/1)
#### Other Contributions
Reported a total of [14 program bugs](https://github.com/alfred-leong/ped/issues) for another team during the module's PED.
