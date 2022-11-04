# Ian Ng - Project Portfolio Page (PPP)

## Product Overview

easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, 
creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

### Code Contributed

This [link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=nshian&breakdown=true) indicates the breakdown of code contributed, in terms of documentation, functional code, and test code.

### Enhancements Implemented

#### Ui
The Ui class is the cornerstone of the program to facilitate interaction with the user. It is used for scanning and collecting user input, printing error messages to the user upon receipt of invalid input commands.
Additionally, it displays acknowledgement messages for successfully completed commands and prints the required information.
   
* **Some notable PRs**: [#20](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/20), [#39](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/39), [#71](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/71),
   [#72](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/72), [#96](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/96).

#### Timetable
The timetabling feature is useful for users to create various timetables for different partner universities. It is factored into Lesson, Timetable and TimetableManager classes for higher cohesion and lower coupling. 
It is also used to verify that information for lessons e.g. time are valid and that lessons being added into the timetable do not clash.

* **Some notable PRs**: [#80](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/80), [#81](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/81), [#82](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/82)

#### Favourite Command
The favourite command is useful for users to favourite their lists of bookmarked module mappings for easier reference.
* **Related PR**: [#73](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/73)

#### Unit Testing & Assertions
Added JUnit tests and assertions to make code more defensive and facilitate effective and quick regression testing.
* **Some notable PRs**: [#39](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/39), [#50](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/50), [#74](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/74),
  [#93](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/93), [#190](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/190).

#### PED Bugs
Got rid of bugs found in the module's Practical Exam Dry Run (PED).
* **Some notable PRs**: [#116](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/116), [#173](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/173), [#174](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/174),
[#175](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/175), [#193](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/193).

### Contributions to the User Guide UG
Added documentation for favourite command and rearranged components for easier reader comprehension. Definitions for abbreviations and terminologies used were also included.
* **Some notable PRs**: [#103](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/103), [#173](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/173), [#174](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/174).

### Contributions to the Developer Guide DG
Added class and sequence diagrams for the implementation of Ui, Timetable and Favourite Command using PlantUML. 
Updated class diagrams to follow standard notation.
* **Some notable PRs**: [#83](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/83), [#97](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/97), [#188](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/188).

### Contributions to Team-Based Tasks
I helped to set up the About Us page and modified GitHub workflows to remove unnecessary checks.
I was also the most active in reviewing and approving pull requests (44 PRs caa 4 Nov 2022).
In my own time, I also conducted testing to identify undetected bugs ([#56](https://github.com/AY2223S1-CS2113-W13-2/tp/issues/56)).
I maintained the issue tracker by tagging and labelling various pull requests for effective reference.

### Contributions beyond the project team
* Forum Contributions: Clarified doubts about GitHub workflows that may be applicable to others in the CS2113 GitHub forum ([Forum Post #34](https://github.com/nus-cs2113-AY2223S1/forum/issues/34))
* Review/mentoring contributions: Gave suggestions to other students taking the modules for their individual/team projects: 
[iP #35](https://github.com/nus-cs2113-AY2223S1/ip/pull/35), [iP #43](https://github.com/nus-cs2113-AY2223S1/ip/pull/43), [iP #50](https://github.com/nus-cs2113-AY2223S1/ip/pull/50), 
[tP #11](https://github.com/nus-cs2113-AY2223S1/tp/pull/11/files).
* Other Contributions: I reported a total of [14 program bugs](https://github.com/nshian/ped/issues) for another team during the module's PED.
