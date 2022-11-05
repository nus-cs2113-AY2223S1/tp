# Ivan Theng's Project Portfolio Page

## Project: SkyControl

SkyControl - SkyControl is a program which optimizes the use of the Command Line Interface (CLI)
to manage flights and passengers in an airport terminal for the present operation day.

+ ### The link below is a summary of my code contribution:  
  + **Link:** [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=ivanthengwr&breakdown=true)

Here are my contributions to the project:

+ ### New Feature: Added a feature to delete passenger details command.
  + What it does: Allows the manager of the bot to delete a passenger detail from the passenger list.  
  + Justification: This feature is one of the fundamental feature of the bot which allows the manager to remove a passenger 
  detail that he/she may have keyed in wrongly or would like to omit from the list of passengers.
  + Highlights: This command is unique because it specifically pinpoints the selected passenger to be removed based on
  certain parameters. These parameters that are required to be entered by the manager ensures that the correct passenger is 
  selected to be deleted.

+ ### New Feature: Added a feature to list all passenger details command.
  + What it does: Allows the manager to view all passengers details in a single view via a content of table. This reflects
  **all** passengers that have been added into the list. 
  + Justification: This feature improves the usability of the bot, allowing the manager to see all passenger's details at once.
  + Highlights: This feature helps to aid new features like searching for a particular passenger's detail.  

+ ### Project management:
  + Managed releases `v1.0`, `v2.0` and `v2.1` (3 [releases](https://github.com/AY2223S1-CS2113-T17-1/tp/releases)) on GitHub  

<div style="page-break-after: always;"></div>

+ ### Enhancement to existing features:
  + Updated order of input workflow between `add flight` and `add passenger` command. Order of priority changed to
  `add flight` command first were the manager has to add in an existing flight number before he/she is able to add in the
  passengers details of the same flight number. Rational is that the flight has to exist first before the passengers can
  be assigned to it, which is for syncing purposes. (Pull request [#46](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/46))
  + Optimise `passenger add` command to omit `BOARDING TIME` and automate it to always set as an earlier time than before `DEPARTURE TIME`.
  This would prevent bugs from occurring. i.e. the manager may mistakenly input a `BOARDING TIME` that is later than the `DEPARTURE TIME`.
    (Pull request [#87](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/87))
  + Improved `modify` command to auto update the `BOARDING TIME` for all passengers who had their flights delayed (Pull request [#87](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/87)) 
  + Wrote additional test and more catch exceptions to make code more defensive.
  (Pull requests [#17](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/17),
  [#28](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/28), [#42](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/42))
  + Optimise overall code integration to make it more OOP. (Pull requests [#14](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/14/files),
  [#94](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/94))
  + Solved bugs for a few features within SkyControl (Pull request [#61](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/61),
  [#16](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/16))  

+ ### Documentation:
    + Added overall format and standardisation write up for UG and DG [#42](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/42/commits)
    [#56](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/56/files)
    + User Guide:
      + Added in documentation for the features `passenger list` and `passenger delete` [#51](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/51)
      + Wrote summary documentation and most of the FAQ questions [#51](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/51)
      + Did cosmetic tweaks to table of contents [#51](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/51)
    + Developer Guide:
      + Added implementation details of the `passenger delete` and `passenger list` feature. [#42](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/42/commits)
      + Added in product scope in UG.

+ ### Community:
  + PRs reviewed (with non-trivial review comments): [#12](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/12),
  [#21](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/21), [27](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/27),
  [#35](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/35)
  + Helped coordinated the update of RepoSense ownership distribution [#40](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/40)
