# Koh Chee Heng's Personal Portfolio Page

### Project: Timetabler

Timetabler is a desktop timetable planning application used by students of NUS to plan their NUS modules for either Semester 1 or 2. The user interacts with it using CLI. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability add modules to the current session timetable.
  * What it does: allows the user to add a specific module by entering a valid NUS module code. This operation is performed through a call to the NUSMods API.
  * Justification: This is one of the most crucial feature for planning a timetable since it allows students to list out and get an overview of only modules that concerns them.
  * Highlights: On top of the API call and the parsing of return information in the form of JsonNodes, the information is further segregated and manipulated to fit the usage of the application.
  * Credits:
    - [NUSMods API](https://api.nusmods.com/v2/) - To extract information about modules in NUS
    - [FasterXML](https://github.com/FasterXML/jackson) - Helps parse json files from the NUSMods API

---

* **New Feature**: Added the ability to set module lessons.
  * What it does: allows the user set which lessons he or she is taking for a specific module.
  * Justification: This is another crucial feature that allows the student to only see the lessons that concerns him or her in the timetable.
  * Highlights: User experience was prioritised by making setting feature a step by step CLI input process, instead of typing one long command which is prone to typos.

---
* **Application Contribution**: Provided the backbone of the application structure for the team to work on.
  * Designed the original architecture of the application and the data structures to be used.
  * Credits: @jjtoh (https://github.com/jjtoh) for revising and improving the data structures.

---

* **Application Contribution**: Redesign data saving feature
  * Changed the data management from one that hard coded data to one which leveraged API calls for simplicity.
  * Credits: @AaaaaronC (https://github.com/AaaaaronC) for the original code for data management and snippets of code that was reused by myself.

---
* **Application Contribution**: Provided original design of print feature
  * Wrote the first iteration of the print timetable feature, which was used as a basis for the final iteration.
  * Credits: @HTT (https://github.com/HT-T) for improving on the design and writing the final iteration.

---
* **Testing**:
  * Wrote JUnit tests for important methods used in features I implemented.
  * Ran and updated Text-UI-Tests before commiting changes to Team Repo's master branch.

---

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=cheehengk&tabRepo=AY2223S1-CS2113-T17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

---

* **Project management**:
  * Added issues for myself and the team, and assigned the issues and workload.
  * Managed release `v2.0` on GitHub.
  * Set-up milestones and labels used for the project.

---

* **Documentation**:

  * User Guide
    - Most major features and structure of the document.
  * Developer Guide
    - System architecture
    - Add module feature
    - List module feature
    - Storage
    - Other formatting and documentation
---
* **Community**:
  * PR(s) reviewed (with non-trivial review comments): example - [\#24](https://github.com/AY2223S1-CS2113-T17-3/tp/pull/24)
  * Reported bugs and suggestions for other teams in the class (examples: [\#16](https://github.com/nus-cs2113-AY2223S1/tp/pull/16), [\#32](https://github.com/nus-cs2113-AY2223S1/tp/pull/32))
