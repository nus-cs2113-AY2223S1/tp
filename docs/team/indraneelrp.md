# Indraneel Paranjape - Project Portfolio Page

## Overview
myReviews is a desktop application allowing users to easily save, rate and track movies or TV shows that they
have watched. myReviews is fully written in Java, and users interact with the application via Command Line Interface (CLI). 
Given below are my contributions to the project:

### Summary of Contributions

**New Feature**: Driver code
  * What it does: This feature handles the overall run of duke. 
  * Justification: The overall executor needs to handle the set-up actions such as the creating of a file and
  reading in saved data, and then executing the main funtions of the program.
  * Highlights: The OOP model has been well-followed to make the structure of the program simple and intuitive. The driver code was written such that the instantiation of the Duke object instantiates the various helper classes (Parser, storage, Ui, ReviewList), allowing their implementation to be abstracted away, resulting in clean, concise and readable code.

**New Feature**: Parser
  * What it does: This feature resolves the user input into a command to execute.
  * Justification: The parser class is vital to the program, as it is responsible for proessing the user's string input
  and triggering the appropriate function. By having a Parser as a dedicated feature, the resolution of user input can be compartmentalised into a specific class, keeping the main code clean.
  * Highlights: The parser first splits the user's input string into the various words. This resolves the first word of a command, triggering the specific execute function via a switch-case block. Respective functions, where needed, obtain the arguments for a particular command by further splitting user input. The final manipulation of the ReviewList object is handed off to the Command classes (Eg AddCommand), keeping a separation of concerns.

**New Feature**: Storage
  * What it does: This feature ensures the user's information can be saved into local storage and retrieved
  every time the program is run.
  * Justification: Storage is vital for the persistence of data, ensuring a user does not have to rely on memory.
  * Highlights: The Storage class includes validation while reading in the input from the file to make sure it is corectly 
  formatted. The storage class checks for the existence of a file at the specified path and makes the approriate file if it does not exist.

**New Feature**: Ui
  * What it does: This feature is responsible for the interaction with the user at the command line. It gets user input from the command line and also prints out the appropriate information on the command line.
  * Justification: The UI is a key component for interaction with the user. 
  * Highlights: Accounts for NoSuchElementException by using HasNextLine(). [StackOverflow resource](https://stackoverflow.com/questions/7209110/java-util-nosuchelementexception-no-line-found)

**Code contributed**: [Personal RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=indraneelrp&tabRepo=AY2223S1-CS2113-T18-1b%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Contributions to team-based tasks**:
  * Created issues for enhancements, user stories and bugs found across v1.0-v2.1.
  * Closely followed and updated issue tracker, closing completed issues.
  * Added assignees, labels and milestones to issues.
  * Reviewed and merged teammates' PRs.
  * Led discussion on how to structure the project in V1.0, especially for Parser. [Indraneel's iP](https://github.com/indraneelrp/ip)

**Enhancements to existing features**:
  * Wrote JUnit test cases for Storage, Parser, UI, and Movie. Brought the unit tests to 70% coverage for Parser for v2.0.
  * Researched and implemented how to direct logging information from the console to a log file. [Reference Youtube video](https://www.youtube.com/watch?v=W0_Man88Z3Q) [PR #115](https://github.com/AY2223S1-CS2113-T18-1b/tp/pull/115)
  * Enhanced the add feature to ensure that entries with duplicate names would be rejected if attempted. This reduces redundant information when sorting and listing.
  * Figured out a platform-independent solution for JUnit tests compare against CLI output (Generally, line separators cause issues). [Stackoverflow link referenced](https://stackoverflow.com/questions/41674408/java-test-system-output-including-new-lines-with-assertequals)  

**Documentation**:
  * User guide:
    * Created the format of the User guide. i.e Header, Format, Usage, Output.
    * Added information on which arguments were compulsory for each command. Added details for add, list and find.
  * Developer guide:
    * Drew UML class diagrams for `storage`, `ui`, classes.
    * Added implementation details for them.

**Community**:
  * PRs reviewed and bugs found: [tP #W12](https://github.com/AY2223S1-CS2113-W12-1/tp), [iP #39](https://github.com/nus-cs2113-AY2223S1/ip/pull/39/files), [iP #26](https://github.com/nus-cs2113-AY2223S1/ip/pull/26), [PED bugs](https://github.com/indraneelrp/ped/issues)