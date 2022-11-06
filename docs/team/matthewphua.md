# Matthew Phua - Project Portfolio Page

## Overview
myReviews is a desktop application allowing users to save and rate movies and TV shows that they
have watched. myReviews is fully written in Java, and users can interact with the application using
Command Line Interface (CLI).

### Summary of Contributions

**Code contributed:**
[Tp Dashboard contributions](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=matthewphua&tabRepo=AY2223S1-CS2113-T18-1b%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: Abstract Command Class Architecture
  * What it does: This feature serves as the foundation for all commands within our CLI product.
  * Justification: All commands need an overall similar structure to provide continuity within implementation.
  * Highlights: Had to make an overall structure for all command actions.
  Implemented the OOP model into our design. 
* **New Feature**: Add Command
  * What it does: Adds the review to storage.
  * Justification: This is the most essential behavior to our application. 
  It allows the user to add own personal reviews to storage.
  * Highlights: Had to address many edge cases in `executeAdd` in the `Parser` class given the variability in user 
  inputs. This included # of inputs, adding characters instead of integers for
  rating, or adding a "/" delimiter in user input. 
* **New Feature**: Remove Command
  * What it does: Allows users to remove a review for a given type.
  * Justification: This is an essential behavior to allow users to remove unwanted content.
  * Highlights: It was difficult to figure out how indexing would work. The list command separates out content based
  on category (so indexes are seperated per category). However, on the other hand, storage stores all reviews in one
  array. I had to create a search algorithm to convert between these discrepancies.
* **New Feature**: List Command
  * What it does: Displays added reviews to users. Program separates reviews based on category.
  * Justification: Allows user to see reviews added. Also provides index numbers for removing reviews.
* **New Feature** Find Command
  * What it does: Finds a review with a specific title
  * Justification: Allows user to find a particular review without having to sort through the long list of reviews.
  * Highlight: Leveraged list command behavior to prevent redundancy in code when printing out the find command output. 
* **New Feature** Review List
  * What it does: Stores reviews in current session in array list
  * Justification: We needed an object to store all the user's reviews and have the appropriate
  accessors and behavior methods to perform all necessary actions.
  * Highlights: Follows the OOP model by creating an object for storage.


**Enhancements to existing features**:
* Wrote JUnit tests for `Parser`, `RemoveCommand`, `UI`, `ClearCommand`, `AddCommand`, `ListCommand`
* Fixed various bugs (Parsing errors, incorrect input, handling different cases)
* Incorporated a library that helps test for system.out.print output
  [documentation](https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println)

**Contributions to the DG**
* Wrote Sequence Diagram/Step-by-step walk through for `SortCommand` and `ListCommand` using PlantUML.
* Created user stories for user story list, non-functional requirements, and step-by-step guide for manual testing

**Contributions to the UG**
* Helped create command guide by adding descriptions, 
example input and output. (`add`, `clear`, `delete`, etc.)
* Created the feature list in UG.

**Review/mentoring contributions**
* Helped architect overall structure of CLI
* Given that I am the most senior of my group and have had internship
experience, I provided them with resources and advice to obtain and be successful in interviews. 
  * Ex: I am an exchange student from berkeley, so I sent them a course I took 
  on [leetcode](https://algorithmicthinking.github.io/#/) that I thought was really helpful 
* Sent stack overflow links to help solve issues. 
  * Ex: Testing for functions that have print statements and verifying the print
  statements have proper output. 
  [documentation](https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println)

**Contributions to team-based tasks**
* Tagged issues and pull requests with proper tags, milestone, assignees 
* commented on PRs
* Helped with function header comments
* Created feature list in User Guide
* Created manual-testing instructions, non-functional requirements in Developer Guide 
* Fixed code style throughout codebase

**Contributions beyond the project team**
* Reviewed repo in PE-dry-run and reported [16 bugs](https://github.com/matthewphua/ped)
("16 bugs to be exact, putting you in the top 10%")
  * 4 High severity 
  * 4 Medium severity 
  * 8 Low severity 
* Reviewed other teams code:
  * example: [Organizer Manager](https://github.com/nus-cs2113-AY2223S1/tp/pull/32)
