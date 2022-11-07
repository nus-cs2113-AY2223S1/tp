# Cheah Hao Yi - Project Portfolio Page

## Overview

**Yet Another Module Organiser / Manager** is an all-in-one tool to find information about modules offered in NUS and plan your timetable. It is optimized for use via a Command Line Interface (CLI).

### Summary of Contributions

[RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=CheahHaoYi&tabRepo=AY2223S1-CS2113-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Features Implemented

- **User Interface** 
  - What it does: Allow a standardized input and output mechanism. 
  - Justification: Allow the team to adhere to the Model-View-Controller (MVC) architecture, to minimize the complexity and coupling in the code repository
  - Highlights: NIL
  - Credits: the base code for the UI class was taken from Hao Yi's iP, with further refinement from the team


- **Exceptions**
  - What it does: Implemented a general exception class for the project 
  - Justification: Standardized the convention and method of which exceptions are thrown in the project to handle unexpected user behaviour while ensuring the programme can still function smoothly
  - Highlights: At the application level, we are able to distinguish exceptions that are caused by internal logics, versus those that are from other sources such as IOExceptions
  - Credits: The discussion and consensus from the team to led to such implementation of exception mechanism


- **Import and Export to NUSMOD**
  - What it does: Implemented the functionality for users to transfer timetable information between YAMOM and NUSMODs
  - Justification: Add robustness to the application and allow users (NUS students) to integrate with a familiar tool, 
    especially for those who prefer CLI interface
  - Highlights: NIL
  - Credits: The `Link` class implemented by Ngiap Hin to simplify the processing of NUSMod links.

### Enhancements to existing features

- Refactoring of Exception throwing mechanism.
- Trimming of un-used methods, functions and variables.
- Refactoring of `HelpCommand` to display intuitive help information to the users.

### Contribution to the UG

- Contributed the initial structure of the user guide and standardized guide format.
- Added documentation for `import` and `export` command
- Rewrite some portion of the UG to make it more intuitive for users

### Contribution to the DG

- Added documentation for the `UI` class 
- Added documentation for the `Command` abstract class
- Rephrasing of various section of the DG to make it more intuitive for the reader.

## Contribution to the Project Team

- Initiate, organize and facilitate weekly team meeting.
- Review team PR and ensure documentation comply with module standards: [Github Link](https://github.com/AY2223S1-CS2113-F11-3/tp/pull/226)
- Clarification with course Teaching Assistant with regard to the requirement of the project.
- Clarification with course Professor on [forum](https://github.com/nus-cs2113-AY2223S1/forum/issues/30) with regard to a possible use of library (even-though the team did not use it at the end).