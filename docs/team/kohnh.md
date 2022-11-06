# Koh Ngiap Hin - Project Portfolio Page

## Overview
**Yet Another Module Organiser / Manager** is an all-in-one tool to find information about modules offered in NUS and plan your timetable. It is optimized for use via a Command Line Interface (CLI).

## Summary of Contributions

### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=kohnh&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Features Implemented

- **Parsing of User Input** 
  - What it does: Allows the user's commands to be understood and know the corresponding commands to return.
  - Justification: This feature serves as one of the core pillars of our application to be able to carry out different tasks based on the input.
  - Highlights: This implementation allows developers to easily add new commands in the future.
  - Credits: The idea of a separate parser class came from the Individual Project.

- **Saving and Reading from Hard Disk** 
  - What it does: Saves the application state periodically
  - Justification: This ensures that there will be no data loss if the programme suddenly crashes. We also want the users to not have to input their timetable every time the application is loaded.
  - Highlights: Initially the format of saving the file was not specified and the storage class was implemented simplistically. However, there was a change in the idea to be able to export and import as a NUSMods link which meant that the whole functionality had to be reworked.

- **Parsing of NUSMods link**
  - What it does: Handles both converting the current application state into a NUSMods link and vice-versa from a NUSMods link to update the current state.
  - Justification: Data validation needs to be carried out to verify that the link contains the necessary information. As such, users will be able to transition from NUSMods to our application easily without the loss of data.
  - Highlights: The link contains many segments and each has to be validated separately. Many rounds of regression testing were conducted to ensure that if any part of the link contains corrupted data (i.e. invalid parameters), the rest of the information will still be parsed.

- **View Timetable**
  - What it does: Users will be able to view their selected modules and lessons in a timetable format.
  - Justification: A visual representation helps the user visualize their selected modules to see if there are any clashes or if the timeslots fit their schedule.
  - Highlights: How the timetable was being displayed and what it required was done by Owen Leong. He also handled how the data about the modules were stored. The implementation of this was challenging as there was a lot of information that have to be understood first before knowing how it should be implemented.

### Enhancements to existing features
- **Command Architecture** Formulated and deliberated on the command architecture to ensure that our group can function effectively and efficiently.
- **Testing** To ensure that the parts implemented are working as intended.

### Contributions to the UG

- Added command descriptions for the `import` command and `timetable` command.
- Added more information of how the application data is stored
- Proofreading the whole document.

### Contributions to the DG

- Standardize the formatting of DG.
- Add the overall architecture diagram.
- Add class diagrams for all `Command` classes.
- Add class diagrams and documentation for `Parser`, `Link`, `Storage` components.
- Ensured that all the features of the application is represented in the DG.

### Contributions to the Project Team

- PR reviewed (with non-trivial comments): [#69](https://github.com/AY2223S1-CS2113-F11-3/tp/pull/69), [#101](https://github.com/AY2223S1-CS2113-F11-3/tp/pull/101), [#113](https://github.com/AY2223S1-CS2113-F11-3/tp/pull/113)
- Assisted in the implementation of the `Import` command.
- Assisted in bug fixing. (E.g. Resolved the issue of whitespaces causing testing to fail.)