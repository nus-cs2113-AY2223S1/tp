# Amit - Project Portfolio Page

## Overview

**Yet Another Module Organiser / Manager** is an all-in-one tool to find information about modules offered in NUS and
plan your timetable. It is optimized for use via a Command Line Interface (CLI).

## Summary of Contributions

### Code Contributed

[RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=amitrahman1026&tabRepo=AY2223S1-CS2113-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Features Implemented

- **Addition & Removal commands**
    - What it does: Gives the application its primary functionality of being able to select, add and remove academic
      modules to allow users to plan.
    - Justification: These are core functionality features required for any planning application
    - Highlights: The implementation of this feature required deeper understanding of the applications Model components,
      in particular the Module class.
    - Credits: These commands originally came from the Individual Project and was adapted to fit YAMOM's model.

- **Timetable slot and semester details selection**
    - What it does: Allows users to select their own details for each module for e.g. lab group, tutorial session etc.
    - Justification: To make a planner application more robust, providing fine grain detail selection to users is very
      important.
    - Highlights: There were some data validation bugs squashed stemming from other classes in the model during
      development
    - Credits: Owen Leong's handling of parsing of raw data pulled from NUSMods provided strong foundations to build
      upon.

- **Module list view & help commands**
    - What it does: The help and list commands are auxiliary commands to help orientate users of the program.
    - Justification: Help command serves as an in-application guide, whereas the list command gives user visual
      confirmation on their selected modules.
    - Highlights: Again due to how intimately these commands rely on the execution results of other commands, various
      edge cases and bugs were exposed during the development (i.e. invalid parameters)

### Enhancements to existing features

- **Model Data validation** Caught and handled various NullPointerExceptions thrown due to corner cases in data
  validation.
- **Testing** To ensure that the parts implemented are working as intended. Increased branch coverage for all features
  added and beyond.

### Contributions to the UG

- Added command descriptions for the `add` command, `remove`, `select`, `semester`,`list` and `help` commands,
- Added more information of commands I implemented work, as well ask giving examples for users to learn from.

### Contributions to the DG

- Initial skeleton and sectioning of DG
- Introduction, prerequisites and set up, documentation guide, testing guide, appendix
- Added documentation for the `AddModuleCommand`,`RemoveModuleCommand`,`HelpCommand` components
  and elaborates how they work.

### Contributions to the Project Team

- PR reviewed (with non-trivial comments): [#82](https://github.com/AY2223S1-CS2113-F11-3/tp/pull/82), []
- Assisted in unit testing
- Assisted in product testing and bug fixing especially with data validation for various classes.