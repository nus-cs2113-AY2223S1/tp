# joshuan98's Project Portfolio Page

## Product Overview

easySEP is a CLI application created to assist NUS Computer Engineering (CEG) undergraduates intending to embark on a Student Exchange Programme (SEP) in their planning for student exchange.
In particular, it is a useful utility for exploring potential module mappings for various partner universities, creating and maintaining lists for them and also favouriting selected ones for easier reference. As an added feature, users can also curate their timetables to prevent potential clashes in lessons.

Given below are my contributions to the project:

- New Feature: Added the ability to store module mapping information in memory.
  - What it does: Reads in data from a `csv` file, verify its authenticity, and writes the data into memory for use by other parts of the program.
  - Justification: This feature is the backdone to keep track of the approved CEG SEP moduble mappings
  - Highlights: Cleaned up the given data file, filter the relevant data from the `csv` for our application, parse the data into a usable form for other parts of the program, provide easy retrieval by filtering data based on name and module code for both NUS and partner university for ease of searching data in the database
- New Feature: Added the ability for users to list information from the database
  - What it does: 4 sub-capabilities, displays the list of all modules in the database, displays the list of all universities in the database, filters module mappings based on NUS module code, filters module mappings based on partner university name.
  - Justification: The user may not know what module mappings or universities are available to be added into their planning. This feature allows them to search and filter modules mappings that they are interested in.
  - Highlights: Check the validity of the list command query, execute the list command according to the 4 discrete types, throws errors when invalid inputs are provided, informs user when there are no valid module mappings that match their query.
- Code contributed: [RepoSense link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=joshuan98&breakdown=true)
- Documentation:
  - User Guide:
    - Added documentation for list command
  - Developer Guide:
    - Added documentation for database implementation, with relevant class and sequence diagrams for illustration
    - Added documentation for list command implementation, with relevant class and sequence diagrams for illustration
- Project Management:
  - Create GitHub labels for issues based on recommended labels from [CS2113 website](https://nus-cs2113-ay2223s1.github.io/website/admin/appendixE-gitHub.html#tp-issue-tracker-setup).
  - Populate GitHub issue tracker with user stories to tackle.
  - Maintain GitHub issue tracker by updating labels for issues.
  - Reviewed PRs from all team members, verifying that bugs were fixed, and no new bugs were introduced.
- Community:
  - Attempted to provide solutions to problems faced by others in CS2113 forum ([Forum Post #3](https://github.com/nus-cs2113-AY2223S1/forum/issues/3), [Forum Post #8](https://github.com/nus-cs2113-AY2223S1/forum/issues/8), [Forum Post #9](https://github.com/nus-cs2113-AY2223S1/forum/issues/9))
  - Clarified doubts that may be applicable to others in CS2113 forum ([Forum Post #12](https://github.com/nus-cs2113-AY2223S1/forum/issues/12), [Forum Post #24](https://github.com/nus-cs2113-AY2223S1/forum/issues/24))
  - Consistently reported bugs faced by other teams during tutorial exercises ([Developer Guide Review #1](https://github.com/nus-cs2113-AY2223S1/tp/pull/4), [Developer Guide Review #2](https://github.com/nus-cs2113-AY2223S1/tp/pull/1))
  - Reported 24 bugs faced by other teams during the CS2113 Practical Examination Dry-Run ([Practical Examination Dry-Run](https://github.com/joshuan98/ped/issues))

## Contributions to the Developer Guide (Extracts)

## Contributions to the User Guide (Extracts)
