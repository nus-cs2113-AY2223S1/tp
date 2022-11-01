# Koh Ngiap Hin - Project Portfolio Page

## Overview
**Yet Another Module Organiser / Manager** is an all-in-one tool to find information about modules offered in NUS and plan your timetable. It is optimized for use via a Command Line Interface (CLI).

## Summary of Contributions

### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=kohnh&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Features Implemented

- **Parsing of User Input** 
  - What it does: Allows user's commands to be understood and know the corresponding commands to return.
  - Justification: This feature serves as one of the core pillars of our application to be able to carry out different tasks based on the input.
  - Highlights: The implementation of this feature makes it easier to add new commands in the future.
  - Credits: The idea of a separate parser class came from the Individual Project.

- **Saving and Reading from Hard Disk** 
  - What it does: Saves the application state periodically
  - Justification: This ensures that there will e no data loss if the programme suddenly crashes. We also want the users to not have to input their timetable everytime the application is loaded.
  - Highlights: Initially the format of saving the file was not specified and the storage class was implemented in a simplistic way. However there was a change in idea to be able to export and import as a NUSMods link which meant that the whole functionality had to be reworked. Parts of the function was refactored into a new link class to isolate the function of each class.

- **Parsing of NUSMods link**
  - What it does: Handles both converting the current application state into a NUSMods link and vice-versa from a NUSMods link to update the current state.
  - Justification: Data validation needs to be carried out to verify that the link contains the necessary information. As such, users will be able to easily transition from NUSMods to our application easily without the loss of data.
  - Highlights: The link contains many segments which each has to be validated separately. Many rounds of iterations were made to ensure that if any part of the link contains corrupted data (i.e. invalid parameters), the rest of the information in the link will still be parsed.

- **View Timetable**
  - What it does: Users will be able to view their selected modules and lessons in a timetable format.
  - Justification: A visual representation helps the user visualize their selected modules to see if there are any clashes or if the timeslots fit their schedule.
  - Highlights: How the timetable was being displayed and what it required was done by Owen Leong. He also handled how the data about the modules were stored. The implementation of this was challenging as there were alot of information that have to be understood first before knowing how it should be implemented.

### Enhancements to existing features
- **Command Architecture** Formulated and deliberated on the command architecture so ensure that our group can function effectively and efficiently.
- **Testing** To ensure that the parts implemented are working as intended.

### Contributions to the UG

- Added command descriptions for the `import` command and `timetable` command.
- Added more information of how the application data is stored
- Proofreading for the whole document.

### Contributions to the DG

- Standardize formatting of DG.
- Add the overall architecture diagram.
- Add class diagrams for `Parser`,`Link`,`Storage` and `TimetableCommand` components.
- Added documentation for the `Parser`,`Link`,`Storage` and `TimetableCommand` component that elaborates how it works.
- Ensured that all the features of the application is represented in the DG.

### Contributions to the Project Team

- PR reviewed (with non trivial comments): [#69](https://github.com/AY2223S1-CS2113-F11-3/tp/pull/69), [#101](https://github.com/AY2223S1-CS2113-F11-3/tp/pull/101), [#113](https://github.com/AY2223S1-CS2113-F11-3/tp/pull/113)
- Assisted in the implementation of `Import` command since it relies on the `Link` class which I implemented.
- Assisted in bug fixing. 
- Resolved the issue of whitespaces causing testing to fail.