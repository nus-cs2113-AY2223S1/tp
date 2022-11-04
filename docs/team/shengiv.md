# Karthikeyan Vigneshram's Project Portfolio Page

## Project: SkyControl

SkyControl - SkyControl is a program which optimizes the use of the Command Line Interface (CLI)
to manage flights and passenger details in an airport terminal for the present operation day.

+ ### Code Contributed:
  + Click here for [Reposense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=shengiv&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=shengiv&tabRepo=AY2223S1-CS2113-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

+ ### Enhancements implemented:
  + #### Add Passenger:
    + Added a feature to add passenger details to the passenger list. 
    + Created passenger class and methods to access and set passenger attributes.
    + Added methods to retrieve Passenger attributes from user input and ensure valid input.
    + Initially passenger add would require user to input details present in flight list
such as flight number and departure time.
    + However, to allow the user to enter details faster and to prevent inconsistencies in logic, I integrated 
passenger details with flight details such that the flight number and departure time of the passenger 
will be retrieved from the pre-existing flight details.
    + Made code more defensive to prevent bugs and ease of usage of the program.

  + #### Modify flight number:
    + Added a feature to modify flight number of a flight in the flight list.
    + The changes to flight number will be reflected in each of the passenger details for
passengers in that particular flight.
    + Made code more defensive to prevent bugs and ease of usage of the program.

  + #### Modify gate number:
    + Similar to the feature above,this feature will modify gate number in both flight list
and passenger list according to flight number of the flight and passenger.

+ ### Contributions to the UG:
  + Added format, explanations and examples for the features that I added.
  + Contributed to the FAQ section and command summary section to further provide clarification for users.

+ ### Contributions to the DG:
  + Added sequence diagrams for the features that I added.
  + Added explanation to further go through sequence diagrams and how the features work internally.
  + Assisted with class diagram for Architecture.

+ ### Contributions to team-based tasks:
  + Handled release of `v1.0` of SkyControl.
  + Updating of issue tracker to add issues as required and closing milestones, moving issues to the next milestone.
  + Helped to reformat the code and improve code quality to reduce tight coupling and improve readability.
    + E.g: Removing the inheritance of the Command class from the Parser class by moving relevant attributes and 
methods to appropriate classes.

+ ### Review contributions:
  + PRs reviewed with non-trivial comments: [#14](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/14)
  + Helped team members by reviewing code and suggesting possible algorithms to be used.

