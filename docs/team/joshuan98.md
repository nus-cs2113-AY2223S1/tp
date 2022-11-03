# Project Portfolio Page (PPP)

## Product Overview

easySEP is a CLI application created to assist NUS Computer Engineering undergraduates intending to embark on a Student Exchange Programme in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

## Summary of Contributions

### Code Contributed

This [link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=joshuan98&breakdown=true) indicates the breakdown of code contributed, in terms of documentation, functional code, and test code.

### Enhancements Implemented

#### Database Storage

1. Cleaned up the data file that used as sample database.
1. Read the data line-by-line and verify that the data is valid.
1. Write the data into memory for use by other parts of the program.

#### Database Parser

1. Verify that each line of data is valid.
1. Filter the relevant data to be used in the program.
1. Parse the data into a easily usable form.

#### Database

1. Store the data and provide ease of retrieval by other parts of the program.
1. Filters the data based on name and module code for both NUS and partner university for ease of searching data in the database.

#### /list Command

1. Check validity of list command
1. Create new list command
1. Execute list command accordingly

#### Testing

1. Add JUnit tests for all database and list command components
1. Ensure that valid inputs returns the correct outputs.
1. Ensure that invalid inputs throws exceptions accordingly.

### Contributions to the User Guide UG

1. Update UG for list command, providing brief description, usage format, example usage, and example output.

### Contributions to the Developer Guide DG

1. Update DG for database implementation with a brief description.
1. Construct class diagram for database implementation to illustrate relationships between the various database classes such as Database, DatabaseParser, and DatabaseStorage.
1. Construct sequence diagram for database implementation to illustrate how data is read and stored in memory.
1. Update DG for list command implementation with a brief description.
1. Construct class diagram for list command implementation to illustrate relationships between Duke, CommandParser, Ui, List Command, and Database.
1. Construct sequence diagram for list command implementation to illustrate how the user input leads to a list command being instantiated and executed.

### Contributions to Team-Based Tasks

1. Set up GitHub team organisation and repository.
1. Create GitHub labels for issues based on recommended labels from [CS2113 website](https://nus-cs2113-ay2223s1.github.io/website/admin/appendixE-gitHub.html#tp-issue-tracker-setup).
1. Populate GitHub issue tracker with user stories to tackle.
1. Maintain GitHub issue tracker by updating user stories with new descriptions as necessary
1. Maintain GitHub issue tracker by updating labels for issues.
1. Closed v2.0 milestone

### Review / mentoring contributions

1. Reviewed PRs from all team members.
1. Verified that bugs were fixed.
1. Verified that new bugs were not introduced.
1. Conducted checkstyle tests to ensure that there were no formatting errors.
1. Conducted JUnit tests for integration testing.

### Contributions beyond the project team

1. Attempted to provide solutions to problems faced by others in CS2113 forum

- [Forum Post #3](https://github.com/nus-cs2113-AY2223S1/forum/issues/3)
- [Forum Post #8](https://github.com/nus-cs2113-AY2223S1/forum/issues/8)
- [Forum Post #9](https://github.com/nus-cs2113-AY2223S1/forum/issues/9)

2. Clarified doubts that may be applicable to others in CS2113 forum

- [Forum Post #12](https://github.com/nus-cs2113-AY2223S1/forum/issues/12)
- [Forum Post #24](https://github.com/nus-cs2113-AY2223S1/forum/issues/24)

3. Consistently reported bugs faced by other teams during tutorial exercises.

- [Developer Guide Review #1](https://github.com/nus-cs2113-AY2223S1/tp/pull/4)
- [Developer Guide Review #2](https://github.com/nus-cs2113-AY2223S1/tp/pull/1)

4. Reported bugs faced by other teams during the CS2113 Practical Examination Dry-Run

- [Practical Examination Dry-Run](https://github.com/joshuan98/ped/issues)

## Contributions to the Developer Guide (Extracts)

## Contributions to the User Guide (Extracts)
