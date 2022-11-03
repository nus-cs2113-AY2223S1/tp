# Ian Ng - Project Portfolio Page (PPP)
<img src="https://media-exp1.licdn.com/dms/image/C4E03AQE7jtNsKd_Xfw/profile-displayphoto-shrink_400_400/0/1624762372701?e=1669852800&v=beta&t=hG6aMZLrJhR26UK6dG2t-ny3KwweUpADecBZ2PCmVaI"/>

## Table of Contents

* [Product Overview](#product-overview)
* [Summary of contributions](#summary-of-contributions)
    * [Code Contributed](#code-contributed)
    * [Enhancements Implemented](#enhancements-implemented)
        * [Ui](#ui)
        * [Timetable](#timetable)
        * [Favourite Command](#favourite-command)
        * [Unit Testing & Assertions](#unit-testing--assertions)
        * [PED Bugs](#ped-bugs)
    * [Contributions to the User Guide UG](#contributions-to-the-user-guide-ug)
    * [Contributions to the Developer Guide DG](#contributions-to-the-developer-guide-dg)
    * [Contributions to Team-based Tasks](#contributions-to-team-based-tasks)
    * [Contributions Beyond The Project Team](#contributions-beyond-the-project-team)
        * [Forum Contributions](#forum-contributions)
        * [Review/mentoring contributions](#reviewmentoring-contributions)
        * [Other Contributions](#other-contributions)

## Product Overview

easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

The design philosophy of [easySEP](https://ay2223s1-cs2113-w13-2.github.io/tp/) is to incorporate [KISS](https://nus-cs2113-ay2223s1.github.io/website/se-book-adapted/chapters/codeQuality.html#practice-kissing)
and [SLAP](https://nus-cs2113-ay2223s1.github.io/website/se-book-adapted/chapters/codeQuality.html#slap-hard) principles. This facilitates a cleaner and more effective handover-takeover process, should the project
ever be passed down to a subsequent team. The application is also specifically tailored to fast typists.
## Summary of contributions

### Code Contributed

This [link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=nshian&breakdown=true) indicates the breakdown of code contributed, in terms of documentation, functional code, and test code.

### Enhancements Implemented

#### Ui
The Ui class is the cornerstone of the program to facilitate interaction with the user.
1. Created methods for scanning and collecting user input.
2. Print error messages to the user upon receipt of invalid input commands.
3. Display acknowledgement messages for successfully completed commands.
4. Print required information based on user commands.
   
* **Some notable PRs**: [#20](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/20), [#39](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/39), [#71](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/71),
   [#72](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/72), [#96](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/96).
#### Timetable
The timetabling feature is useful for users to create various timetables for different partner universities.
1. Factored feature into Lesson, Timetable and TimetableManager classes for higher cohesion and lower coupling.
2. Verify that information for lessons e.g. time are valid.
3. Verify that lessons being added into the timetable do not clash.

* **Some notable PRs**: [#80](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/80), [#81](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/81), [#82](https://github.com/AY2223S1-CS2113-W13-2/tp/pull/82)
#### Favourite Command
The favourite command is useful for users to favourite their lists of bookmarked module mappings for easier reference.
1. Enables bookmarking of selected lists of module mappings for easier reference.
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
* Set up About Us page.
* Modified GitHub workflow to remove unnecessary checks.
* Most active in reviewing and approving pull requests (40 PRs caa 3 Nov 2022).
* Conducted own testing to identify undetected bugs.
* Maintained issue tracker by tagging and labelling various pull requests for effective reference.

### Contributions beyond the project team

#### Forum Contributions
Clarified doubts about GitHub workflows that may be applicable to others in the CS2113 GitHub forum.

[Forum Post #34](https://github.com/nus-cs2113-AY2223S1/forum/issues/34)

#### Review/mentoring contributions
Gave suggestions to other students taking the modules for their individual/team projects: 
[#35](https://github.com/nus-cs2113-AY2223S1/ip/pull/35), [#43](https://github.com/nus-cs2113-AY2223S1/ip/pull/43), [#50](https://github.com/nus-cs2113-AY2223S1/ip/pull/50), [#11](https://github.com/nus-cs2113-AY2223S1/tp/pull/11/files).

#### Other Contributions

I reported a total of [14 program bugs](https://github.com/nshian/ped/issues) for another team during the module's PED.

Some notable reports:

* [Functionality bug: Incorrect handling of DESCRIPTION parameter](https://github.com/nshian/ped/issues/14)
* [Functionality bug: Incorrect handling of NAME parameter](https://github.com/nshian/ped/issues/10)
* [Functionality bug: No error-handling for empty list of expenses](https://github.com/nshian/ped/issues/1)
* [Functionality bug: Program unable to exit](https://github.com/nshian/ped/issues/4)
* [Functionality bug: Lack error checking for negative index](https://github.com/nshian/ped/issues/7)
