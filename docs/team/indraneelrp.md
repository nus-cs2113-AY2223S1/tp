# Indraneel Paranjape - Project Portfolio Page

## Overview
myReviews is a desktop application allowing users to save, rate and track movies or TV shows that they
have watched easily. myReviews is fully written in Java, and users can interact with the application using
Command Line Interface (CLI). Given below are my contributions to the project:

### Summary of Contributions

* **New Feature**: Driver code
  * What it does: This feature handles the overall run of duke. 
  * Justification: The overall executor needs to handle the set-up actions such as the creating of a file or
  reading in saved data, and then executing the main funtions of the program.
  * Highlights: The OOP model has been well-followed to make the structure of the program simple and intuitive. The
  driver code was written such that the instantiation of the Duke object instantiates the various helper classes (Parser, storage, Ui, ReviewList), allowing the smaller details to be abstracted away, resulting in clean concise and very readable
  driver code.

* **New Feature**: Parser
  * What it does: This feature resolves the user input into a command to execute.
  * Justification: The parser class is vital to the program, as it is responsible for proessing the user's string input
  and triggering the appropriate function. By having a Parser as a dedicated feature, the resolution of user input can be compartmentalised into a specific class, keeping the main code clean.
  * Highlights: The parser splits the user's input string using space " " as a delimiter. This resolves the first word of the command, triggering the specific execute function via the switch-case block. Then, the respective functions, where needed, obtain the arguments for the particular command by splitting the user input using "/". The presence of exact arguments are 
  also determined by checking for the presence of specific keywords. Within the execute functions, the manipulation of the ReviewList object is handed off to the Command classes (Eg AddCommand), again to keep a separation of concerns.

  * **New Feature**: Storage
  * What it does: This feature ensures the user's information can be saved into local storage and retrieved
  every time the program is run.
  * Justification: Storage is vital for the persistence of data. This ensures the user does not have to remember the information.
  * Highlights: The Storage class includes validation while reading in the input to make sure it is corectly formatted in file.
  The storage class checks for the existence of a file or folder at the specified path and then makes the approriate file if it does not exist.

* **New Feature**: Ui
  * What it does: This feature is responsible for the interaction with the user at the command line. It gets user input from the command line and also prints out the appropriate information on the command line.
  * Justification: The UI is a key component for interaction with the user. 
  * Highlights: Keeps getting input from the user. Prevents NoSuchElementException by using HasNextLine(). StackOverflow 
  resource used: https://stackoverflow.com/questions/7209110/java-util-nosuchelementexception-no-line-found.

* **Code contributed**: [Personal RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=indraneelrp&tabRepo=AY2223S1-CS2113-T18-1b%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Contributions to team-based tasks**:
  * Created issues for enhancements, user stories and bugs found across v1.0-v2.1.
  * Closely followed and updated issue tracker, closing completed issues.
  * Added assignees, labels and milestones to issues.
  * Reviewed and merged teammates' PRs.
  * Led discussion on how to structure the project in V1.0.

* **Enhancements to existing features**:
  * Wrote JUnit test cases for Storage, Parser, UI, and media. For the largest feature, Parser,
  I brought the unit tests to 70% coverage as of v2.0.
  * Researched and directed logging information from the console to a log file. Youtube video used as reference: 
  https://www.youtube.com/watch?v=W0_Man88Z3Q
  * Enhanced the add feature to ensure that entries with duplicate names would be rejected while trying to be added.

* **Documentation**:
  * User guide:
    * Created the format of the User guide. i.e Header, Format, Usage, Output.
    * Added information on which arguments were compulsory for each command.
    * Added details for add, list and find.
  * Developer guide:
    * Drew UML class diagrams for `storage`, `ui`, classes.
    * Added implementation detail for them.

* **Community**:
  * PRs reviewed:
    * [tP #W12](https://github.com/AY2223S1-CS2113-W12-1/tp)
    * [iP #39](https://github.com/nus-cs2113-AY2223S1/ip/pull/39/files)
    * [iP #26](https://github.com/nus-cs2113-AY2223S1/ip/pull/26)