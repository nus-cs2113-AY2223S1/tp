# Hang Tian - Project Portfolio Page

## Overview

Project: Timetabler
Timetabler is a desktop address book application used for teaching Software Engineering principles. 
The user interacts with it using a CLI. It is written in Java.

### Summary of Contributions

**New Feature:** 

- **Delete Modules**: Delete the modules in the timetable you have stored.

- **Print Timetable**: Print the timetable on terminal for users to have a straightforward view of their lessons each week. Students can also see the clashed slots and modules they have added on their timetable.
    
    - Justification: One of the most important features. Instead of a pile of jumbled up lessons in the shape of horizontal lines, this function provides a timetable with rows and columns that looks like a real timetable, truly allowing the application to live up its name of "Timetabler".
    - Highlights: 

        - Sorting of pairs of integers as starting and ending slots implemented;
        - Differenciating all sorts of lesson clashes with lessons that do not clash at all;
        - Clashed lessons are implemented in a way that maximum clash interval is marked on timetable;
        - Providing clashed module codes for users to know instantly which lessons are conflicting each other.
        - For example, ```[0900,1100],[0900,1100],[1130,1230],[1200,1300],[1400,1500]``` are merged into ```[0900,1100],[1130,1300]``` , where all all clashed lessons will be marked instead of just the clashing intervals, providing a more pragmatic solution for users. Lessons free of conflict are printed normally.
    
    - Credits: [@cheehengk](https://github.com/cheehengk) for implementing a basic version of print timetable for development. A few methods of the product are adapted from his version of timetable printing.

---
- **Testing:** 
    - Wrote some Text-UI testing in the starting phases of the project. Ran and updated them at each build. 
    - Wrote JUnit testing for print timetable functions.


---

* **Project management**:
    * Added issues for myself, and assigned the issues.
    * Set-up milestones and labels used for the project.

---
* **Documentation**:
    * Some additions and changes to the User Guide
    * Features I implemented in the Developer Guide

---
* **Community**:
    * Helped to look through and merge some PRs from teammates (e.g. [\#110](https://github.com/AY2223S1-CS2113-T17-3/tp/pull/110), [\#107](https://github.com/AY2223S1-CS2113-T17-3/tp/pull/107))
    * Reported bugs and suggestions for other teams in the class (examples: [\#8](https://github.com/nus-cs2113-AY2223S1/tp/pull/8))

---
- Code contributed: [RepoSence Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=HT-T&tabRepo=AY2223S1-CS2113-T17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

