# Wilson Ng Jing An - Project Portfolio Page

## Property Rental Manager - Overview
Property Rental Manager (PRM) is a desktop application that helps property agents manage, filter and monitor single owner rental units for appropriate tenants. This application uses Command Line Interface (CLI) and has approximately 7500 lines of code.

### Summary of Contribution
- Code Contribution - [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=wilsonngja&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
- Enhancement: Implementing 'Find' feature

  - This feature allows the user to query through the client and property list to find any matching clients and property. 
  - The reason for this implementation was to allow users to filter their search
  - Specific error message was also implemented to help improve user experience. For example, if the user entered a wrong tag, the user will be shown example to correct the format.

- Enhancement: Refactoring Parser Class

  - The enhancement aims to reduce the coupling within Parser class. Previously, a parser class would conduct checks for all the commands and return a Command class if the format is correct. 
  - However, the enhancement split different commands into different parser class to conduct checks before returning a command class.

- Enhancement: Storage updates

  - The text file used for storage has also been updated due to the bugs noticed.
  - Pairing file now stores the full pairing data (Full client and property information).
  - Change the order of loading for pairing to load last.

    - This is to allow loading of client and property. Before pairing, it will check if the client and property both exists in the list. 
    - If either does not exist in the list, the pairing will not be loaded.

- Documentation:

  - User Guide:

    - Added 'Find Client and Property' section
    - Added 'Loading Data' and 'Saving Data' section
    - Added 'Common Error Messages' table
  - Developer Guide:

    - Added Content Page with clickable link
    - Added the Storage Component of Design in the Developer Guide
    - Added the Implementation details for Storage.
    - Contributed by drawing Sequence Diagram for Storage and Find features.
    - Added a flowchart to explain the operations of loading data.

  - Team-Based Task:
    
    - Created team repository
    - Added the outline for DeveloperGuide
    - Initiates weekly team meeting
    - Release of JAR file for v1.0 

  - Review Contributions: [#93](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/93), [#97](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/97), [#108](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/108), [#185](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/185), [#192](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/192)

  - Contributions beyond project team:

    - Reporting of bugs for CurrencyManager during PE-D: [#114](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/114), [#115](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/115), [#119](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/119), [#120](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/120), [#121](https://github.com/AY2223S1-CS2113-W13-1/tp/issues/121) 

  