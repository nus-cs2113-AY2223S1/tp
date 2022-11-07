# Karthikeyan Vigneshram's Project Portfolio Page

## Project: SkyControl

SkyControl - SkyControl is a program which optimizes the use of the Command Line Interface (CLI)
to manage flights and passenger details in an airport terminal for the present operation day.

Given below are my contributions to the project:

+ ### Code Contributed:
  + Click here for [Reposense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=shengiv&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=shengiv&tabRepo=AY2223S1-CS2113-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

+ ### Features implemented:
  + #### Add Passenger:
    + What it does: Allows the Airside Operations Manager(AOM) to add passenger info to the passenger list. 

    + Justification: This feature is a crucial feature for the application to function as the AOMs require a command to 
be able to add passenger details. This feature is paramount in making sure that the AOM is able to accomplish his
tasks for the day successfully.

    + Highlights:
      + The passenger can only be added if there is an existing flight in the flight list with the
same flight number. The AOM would not be able to add the passenger without a valid flight number. This is to allow
integration between passenger and flight lists to improve cohesiveness and reduce bugs in the program.

      + The passenger cannot be added if there is already another passenger occupying the same seat number 
on that particular flight. An error message will pop up if the AOM tries to do so. This will help to inform the 
AOM about any discrepancies and will allow him/her to quickly rectify the issue.

  + #### Modify flight number:
    + What it does: Allows the Airside Operations Manager(AOM) to modify the flight number of both 
flights and passengers.

    + Justification: This feature is essential in simplifying the process to modify flight and passenger details.
Previously, the AOM would have to delete and then add the flight object or passenger object separately, one by one
in order to modify the corresponding details. With this new feature, the AOM would be able to modify the flight number 
of a flight with just a short command. The changes in flight number will also get propagated to the passengers who are
on that flight.

    + Highlights: The AOM would not be able to modify the flight number to a flight that already exists. In our 
Program, to reduce confusion, no two flights can have the same flight number. Abiding by this rule, there will
be an error message when the AOM tries to change the flight number to a pre-existing flight number.

  + #### Modify gate number:
    + Similar to the feature above, this feature will allow the Airside Operations Manager(AOM) to
modify gate number in both flight list and passenger list according to flight number.

+ ### Enhancements to existing features.
  + Helped to reformat the code and improve code quality to reduce tight coupling and improve readability.
    + Removing the inheritance of the Command class from the Parser class by moving relevant attributes and
      methods to appropriate classes. (Pull Request [#93](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/93))
    + Removing inheritance of OperationList class from Parser class. (Pull Request [#101](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/101))
  + Integrated passenger details with flight details such that the departure time and gate number of the passenger 
will be retrieved from the pre-existing flight details instead of the user having to type it in when adding passenger.
(Pull Request [#86](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/86))
    + This allows the user to enter details faster and prevents inconsistencies in logic.
  + Made code defensive on several occasions. (Pull Requests [#89](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/89),
[#27](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/27), [#23](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/23), etc.)

+ ### Contributions to the UG:
  + Added format, explanations and examples for the features that I added.
(Pull Request [#57](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/57))
  + Contributed to the FAQ section and command summary section to further provide clarification for users.

+ ### Contributions to the DG:
  + Added sequence diagrams for Modify features and Add a passenger feature. 
(Pull Request [#110](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/110))
  + Added explanation to further go through sequence diagrams and how the features work internally.
  + Assisted with class diagram for Architecture.

+ ### Contributions to team-based tasks:
  + Handled release of `v1.0` of SkyControl.
  + Contributed to functionality in releases `v1.0`, `v2.0` and `v2.1` 
(3 [releases](https://github.com/AY2223S1-CS2113-T17-1/tp/releases)) on GitHub
  + Updating of issue tracker to add issues as required and closing milestones, moving issues to the next milestone.

+ ### Review contributions:
  + PRs reviewed with non-trivial comments: [#14](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/14)
  + Helped team members by reviewing code and suggesting possible algorithms to be used.

+ ### External contributions
  + Helped to spot 9 bugs for team `CS2113-T18-1` during PE Dry Run.

