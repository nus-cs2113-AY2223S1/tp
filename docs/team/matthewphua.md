# Matthew Phua - Project Portfolio Page

## Overview
myReviews is a desktop application allowing users to save and rate movies and TV shows that they
have watched. myReviews is fully written in Java, and users can interact with the application using
Command Line Interface (CLI).

### Summary of Contributions

**Code contributed:**
[Tp Dashboard contributions](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=matthewphua&tabRepo=AY2223S1-CS2113-T18-1b%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

For version 1 MVP:
1) Architected, designed, and created the initial structure for `Commands` Class 
2) Created subclasses that inherited from `Command` class (`AddCommand`, `RemoveCommand`, `ListCommand`). 
This includes writing the execute function to make the necessary changes to review list.
5) Created the `ReviewList` class to store local reviews

For version 2:
1) Updated and wrote executeCommand classes in `Parser` class
   (`executeClear`, `executeFind`, `executeDelete`)
2) Created subclasses that inherited from `Command` class
   (`FindCommand`,`ClearCommand`)
3) Created Tests 
   `RemoveCommandTest`, `ListCommandTest`, `UiTest`, `ClearCommandTest`
4) Updated `listCommand` to separate tvShows and movies
5) Updated `removeCommand` to allow the users to choose to remove 
either a movie or tv show.

For version 2.1:
1) Fixed the primary reported bug: Parsing errors related adding movie and tvShows.
Handled error catching and edge cases.
   1) non-number inputs for rating
   2) '/' character used aside from delimiter usage
   3) incorrect number of arguments
2) Fixed parsing errors with Remove command
   1) incorrect index
   2) bug with deleting an index when specifying category 
   3) non-number input for index
3) 

**Enhancements implemented**

**Contributions to the DG** 
1) Wrote Sequence Diagram for `SortCommand` and `ListCommand`
2) Created user stories for user story list
3) Came up with non-functional requirements 
4) Created step-by-step guide for manual testing

**Contributions to the UG** 
1) Helped create command guide by adding descriptions, 
example input and output.
2) Created the feature list in UG.

**Review/mentoring contributions**
1) Helped architect overall structure of CLI
2) Given that I am the most senior of my group and have had internship
experience, I provided them with resources and advice to obtain and be successful in interviews.
   1) Ex: I am an exchange student from berkeley, so I sent them a course I took 
on [leetcode](https://algorithmicthinking.github.io/#/) that I thought was really helpful
3) Sent stack overflow links to help solve issues.
   1) Ex: Testing for functions that have print statements and verifying the print
statements have proper output. 
[documentation](https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println)

**Contributions to team-based tasks**
1) Tagged issues and pull requests with proper tags, milestone, assignees
2) Helped with function header comments
3) Incorporated a library that helps test for system.out.print output
   [documentation](https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println)
4) Updated feature list in User Guide
5) Created manual-testing instructions in Developer Guide
6) Created non-functional requirements
7) Fixed code style throughout codebase

**Contributions beyond the project team**
1) Reviewed repo in PE-dry-run and reported 16 bugs 
("16 bugs to be exact, putting you in the top 10%")
   1) 4 High severity
   2) 4 Medium severity
   3) 8 Low severity
