# Deen - Project Portfolio Page

## Overview
**Yet Another Module Organiser / Manager** is an all-in-one tool to find information about modules offered in NUS and plan your timetable. It is optimized for use via a Command Line Interface (CLI).

## Summary of Contributions

### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=deenliong&tabRepo=AY2223S1-CS2113-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Features implemented
- **Search module command**
    - What it does: Allows user's to search for modules based on the module code or module name.
    - Justification: This feature improves the product significantly because a user can find modules that they are interested in taking. 
    - Highlights: Users can refine their search by specifying the semester that the module exists in and the level of the module in addition to the module code or module name.
    - Credits: the processParams() method in the SearchModuleCommand class was adapted from Owen's individual project.

- **Info Command**
    - What it does: Allows user to view the all information of a module.
    - Justification: This feature improves the product significantly because a user can view all the information of a module, allowing them to extract crucial details such as the module offering in different semesters and the lesson timings offered. This will allow user to better plan their timetable.
    - Highlights: Nil.
    - Credits: Displaying the timetable has been adapted from the display timetable feature implemented by Owen.


### Enhancements to existing features
- Testing for SearchModuleCommand, InfoCommand, AddModuleCommand, SelectSlotCommand.
- Refactored the code for the SearchModuleCommand and InfoCommand class to improve readability and maintainability.
- Validating data for the SelectSlotCommand.

### Contributions to the UG
- Added usage instructions for `search` and `info` commands.
- Added class diagrams for `search` and `info` commands.


### Contributions to the DG
- Created the overall structure of the developer guide and standardized the initial guide format.
- Added class diagrams for SearchModuleCommand and InfoCommand.
- Added sequence diagrams for AddModuleCommand.

### Contributions to the Project Team
- Assisted in small bug fixing and data validation.
- Assisted and implemented several unit testing for features not implemented by me such as AddModuleCommand.
- Reviewed PRs and provided feedback.
 
