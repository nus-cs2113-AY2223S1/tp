# Wilson Ng Jing An - Project Portfolio Page

## Property Rental Manager - Overview
Property Rental Manager (PRM) is a desktop application that helps property agents manage, filter and monitor single owner rental units for appropriate tenants. This application uses Command Line Interface (CLI) and has approximately 7500 lines of code.

Given below are my contributions to the project.
- **New Features:** Added the ability to dynamically load and update Client, Property and Pairing data.

  - What it does: Stores the Client, Property and Pairing data into a text file whenever a new entries is added or when pairings is made between a Client and Property. When the program is ran, the stored information will be loaded before the user can use.
  - Justification: This feature allows the data to be retained despite terminating the program while also allowing the user to update the data offline (when the program is not running) if they are familiar with the formatting.
  - Highlights: This feature requires management of 3 different text files. The complexities is shown with the scope of potential bugs where files can be corrupted through the insertion of text in the wrong format. <br/><br/> 

- **New Features:** Added the ability to search for matching Client or Property.

  - What it does: Search through the Client List or Property List, depending on what the user entry, for matching entity with the string inputted by the client.
  - Justification: This feature allows filtering of entities. For example, if the user wants to look for all the properties in the list that is a 3 Room Flat, he/she could query for "HDB 3-Room", and all the 3 room flats will be displayed.
  - Highlights: This feature requires the placement of tags (f/) to segment the query text. Since it only accepts a single tag, there is a need to identify incorrect number of tag or incorrect tag itself.<br/><br/>

- **Code Contribution:** [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=wilsonngja&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


- **Project Management**:

  - Set up team repository
  - Set up milestones for the team
  - Release of v1.0
  - Closing of milesontes
  - Enabled assertion for the team project
  - Initiation of weekly meeting


- **Enhancement to existing features:**
   - Restructured Parser Class to multiple parser class that is specific to the commands. (Pull Request: [#87](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/87))
   - Changing the PairingList to take in Object as a key instead of a String (Pull Request: [#69](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/69))
   - Improve the consistency such that when a Client has been removed, the pairing will not be loaded. (Pull Request: [#58](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/58))
   - Increase the branch coverage for Find Client and Property to 61% (Pull Request: [#193](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/193))

- **Documentation:**

  - User Guide:
      - Added documentation for the features `Storage` and `find` [#135](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/135)
      - Added a table for common error messages [#135](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/135)
  - Developer Guide:
      - Added Storage into the Design and Implementation section [#99](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/99)
      - Added content page with navigatable link [#80](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/80)
      - Added Setting Up & Getting Started, Target User Profile and Value Proposition  [#80](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/80)
      - Included Baseline point for the team to start working on their component. [#80](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/80)


- **Community:**
  
  - PRs reviewed (with non-trivial review comments): [#93](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/93), [#97](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/97), [#108](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/108), [#185](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/185), [#192](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/192)
  - Contributed to forum discussion: (examples: [#8](https://github.com/nus-cs2113-AY2223S1/forum/issues/8), [#21](https://github.com/nus-cs2113-AY2223S1/forum/issues/21))
  - Reporting of bugs for CurrencyManager during PE-D: [#114](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/114), [#115](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/115), [#119](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/119), [#120](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/120), [#121](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/121) 

  