
# Kyrene Chua's Project Portfolio Page
## Project: parKING
parKING is a desktop app that helps drivers choose the best place to park via the Command Line Interface (CLI).
parKING allows users to search, save and look at car park availability information at a glance, while interfacing with
LTA's real-time API. 

Given below are my contributions to the project:

### Code Contributed

[Reposense Report](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=kyrenechua&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=kyrenechua&tabRepo=AY2223S1-CS2113-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancement Implemented/Contributed
* New feature: Added the `Command`, `CommandResult`, `Parser` classes to parse the user input and return the command and
command result.
  * Implemented the class that interprets the user input into the desired command and returns the desired command's result.
  * The `Parser` class takes in a String user input as parses it into a command.
  * The command then undergoes its `execute()` method to return a `CommandResult`, which will be printed in the terminal.
  * The `Parser` class does the above in a do-while loop until the user calls for the `ExitCommand`.
* New feature: Added the `update` command to update the data from the api.
* New feature: Added the `auth` command to authenticate the user's secret api key.

* Enhancement to code:
  * Refactored the code to improve readability, and meet OOP standards.
  * Wrote the `Parking` class, which is our `main` class.

### Contributions to the User Guide:
* Formulated the documentation on the `Filter`, `Exit`, `Help`, `List`, `Favourite`, `Unfavourite`, and `Find` commands.
* Wrote the introduction and disclaimer sections of the User Guide.

### Contributions to the Developer Guide:
* Wrote the User Stories section
* Wrote the Product Scope section
* Wrote the documentation for the Architecture Level Design component, which includes the class diagrams and sequence 
diagrams to explain the high level design of the app, and to provide an overview of the main components and how they 
interact with one another.
* Wrote the documentation for the Logic Level Design component, which includes the class diagrams of the `Parser`, 
`Command`, and `runUntilExitCommandLoop` classes, and explains how the aforementioned classes work together to parse 
user input into commands and return the command result.
* Drew the class diagram of the Favourite/Unfavourite feature (Section 3.1) to explain how the `Favourite`, 
`FavouriteCommand`, `UnfavouriteCommand`, `Command`, and `FileStorage` classes work together to implement the favourite 
and unfavourite features.
* Drew the class diagram for the Common Component (Section 2.7)


### Contributions to team-based tasks
* Setup the Google Documents for product ideation.

### Contributions beyond the project team:
- Reported more bugs than necessary in the PE dry run.