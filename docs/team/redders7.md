# Ong Shao Yong - Project Portfolio Page

## Product Overview
myReviews is a desktop application allowing users to save and rate movies and TV shows that they
have watched. myReviews is fully written in Java, and users can interact with the application using
Command Line Interface (CLI).

Given below are my contributions to the project.

## Summary of Contributions

**New Feature**: Media, Movies and TV Show classes
  * What it does: Created the Media base class from which the Movies and TV Shows inherits from. These classes included
the attributes of each review and various functions that are the foundation for the application.
  * Justification: The Movie and TV Show classes are the basis of the project, allowing other commands to be created.
Creating a Media parent class allows implementation of the `add` and `list` commands, where Movies and TV shows are 
added/listed separately. Attributes such as genre, rating, date watched and site are key to reviews.
  * Highlights: The key challenge was designing the attributes and functions in a way that can be easily implemented in
the various commands. 


**New Feature**: `Add` command
  * What it does: Allows the user to add movies or TV show reviews, differentiated by the format of the command given.
(/movie or /tv)
  * Justification: This feature is one of the most fundamental, allowing users to add movies/TV shows to their saved 
list. 
  * Highlights: This is a vital feature as it is the most basic ability on the application, allowing users to add to their
list. The key challenges was handling errant commands, including those with illegal characters and words that interfered
with the storage function and preventing users from properly saving their lists.
  * Credits: [Shao Yong's iP](https://redders7.github.io/ip/)


**New Feature**: Parsing of user input
  * What it does: Parses the user input and determines if a valid command was issued. The rest of the input is then parsed
based on the command issued.
  * Justification: This feature enables the application to determine the type of command issued, and throw a 
InvalidCommandException should an invalid command be issued. Based on the command issue, the Parser class then further
splits the input to extract the relevant fields required for the specific command.
  * Highlights: This is a key feature as it converts raw user input to executable commands. The key challenge was 
handling invalid inputs and dealing with edge cases where specific user inputs could cause the entire application to crash.


**Code contributed**: [RepoSense](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=redders7&tabRepo=AY2223S1-CS2113-T18-1b%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Contributions to team-based tasks**:
  * Transferred code to new repository due to issue with old repository (PR [\#1](https://github.com/AY2223S1-CS2113-T18-1b/tp/pull/1))
  * Maintained issue tracker, closing completed issues.
  * Added assignees, labels and milestones to issues.
  * Set up the team's User Guide and Developer Guide.
  * Reviewed and merged teammates' PRs.

**Enhancements to existing features**:
  * Repackaged commands and exception classes (PR [\#131](https://github.com/AY2223S1-CS2113-T18-1b/tp/pull/131))
  * Created new exception classes to better reflect the specific error encountered. (PR [\#131](https://github.com/AY2223S1-CS2113-T18-1b/tp/pull/131))
  * Wrote JUnit test cases for `add`, Parser, Media, Movie, TvShow and Ui classes. (PRs [\#66](https://github.com/AY2223S1-CS2113-T18-1b/tp/pull/66), [\#132](https://github.com/AY2223S1-CS2113-T18-1b/tp/pull/132))
  * Fixed various bugs and issues found throughout the project.

**Documentation**:
  * Contributions to User Guide:
    * Added documentation for the Quick Start guide.
    * Added documentation for the `add`, `find` and `delete` commands.
    * Fixed minor bugs found in PE-D.
  * Contributions to Developer Guide:
    * Added implementation details for the `add` and `find` features.
    * Drew UML sequence diagrams for `add` and `find` commands using PlantUML.
    * Drew UML class diagrams for Media and Ui classes using PlantUML.
    * Added user stories for v1.0 and v2.0.


* **Community**:
  * Reviewed PRs and provided constructive feedback for other students: 
    * tP [\#1](https://github.com/nus-cs2113-AY2223S1/tp/pull/1), iP [\#15](https://github.com/nus-cs2113-AY2223S1/ip/pull/15), iP [\#32](https://github.com/nus-cs2113-AY2223S1/ip/pull/32)
  * Reported [11 program bugs](https://github.com/redders7/ped/issues) during PE-D.

