# Leong Song Zhu Owen - Project Portfolio Page

## Overview

**Yet Another Module Organiser / Manager** is an all-in-one tool to find information about modules offered in NUS and plan your timetable. It is optimized for use via a Command Line Interface (CLI). 

## Summary of Contributions

[Code Contributed](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=owenl131&tabRepo=AY2223S1-CS2113-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

- **Timetable Generation** Implemented the logic to generate a timetable from the modules and lesson slots selected by the user. This is one of the major selling points of our application, that a user can get a beautifully formatted timetable even from their command line.
- **Resource File Parsing** Implemented the logic to download and parse all required module data from the NUSMods API. The challenge here is that the API does not provide the data in a directly-usable format for our application and thus some pre-processing must first be run. The contribution here enables the application to run completely offline, another major selling point for our users. Furthermore the data files are stored in a compressed state to minimize the application file size for the user.
- **General Code Architecture** Formulated and deliberated on the code architecture so ensure that our group can function effectively and efficiently.

### Contributions to the UG

- Added command formats.
- Added command descriptions for the `select` command.
- Proofreading for the whole document.

### Contributions to the DG

- Added documentation for the `Model` package which contains our application business logic.
- Added documentation for the `Timetable` logic which explains how a timetable is generated and formatted.
- Formulated the bird's eye view sequence diagram to explain the high-level flow of the programme.

### Contributions to the Project Team

- Assisted in bug fixing. 