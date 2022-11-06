# Ang Jia Le Marcus - Project Portfolio Page

## Property Rental Manager - Overview
Property Rental Manager (PRM) is a desktop application which operates via Command Line Interface (CLI) that helps property agents manage, filter and monitor single owner rental units for appropriate tenants. This application targets specifically property agents who are working in Singapore and dealing with local properties. Thus, some features are tailored to Singapore context. 

Given below are my contributions to the project.

- **New feature**: Added the ability to add new property, along with various property detail validations.
    - What it does: 
      1. Checks whether address provided is a valid Singapore address. While Singapore address format has many variations, the application can determine whether address provided adheres to the current (Nov 2022) variations of Singapore address format.
      2. Checks for valid unit type of property (e.g. HDB, Condo, etc).
      3. Checks whether unit type of property matches address format.
      4. If all checks pass, a new property is added to property list.
    - Justification: Under the assumption that all properties are local to Singapore, the implemented validation will help relieve some burdens experienced by Singapore property agents dealing with large number of properties.
      1. Manual checking for correct Singapore address format can be avoided with the aid of the application.
      2. Application has condensed the unit type of Singapore properties to 15 variations. User will only need to select from one of them.
      3. Removes the need to check for mismatch address and unit type of property (e.g. Unit type is HDB flats but address does not have unit level/number).
    - Challenges: Extensive research was conducted on all possible Singapore address format as well as existing types of housing (unit type of property) approved by the Singapore government for rental. It was challenging to convert the large amount of research information into working algorithms in the application. This is especially true when considering all possible variations of Singapore address format. 


-  **New feature**: Added the ability to add new client (Person who wants to rent property)
    - What it does: Adds a new client to client list along with Singapore contact number and basic email validation.
    - Justification: The contact number provided by the client should be Singapore-based. This is to ensure that clients, or future tenants, remain contactable via local contact number. Also, email can serve as a backup to contact clients.


- **Code Contribution**: [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=OVReader&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=OVReader&tabRepo=AY2223S1-CS2113-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


- **Project Management**:
    - Preparation for weekly team meeting (Pre v1.0 Phase)
    - Formulation of initial PRM proposal
    - Pull-Request review and merging
    - Adhere to proper workflow and internal deadlines


- **Enhancement to existing features**:
    - Added testing for add features


- **Documentation**:
    - User Guide:
      * Added comprehensive guide for features `add -client` and `add -property` [#134](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/134), [#258](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/258)

    - Developer Guide:
        * Added comprehensive explanation for the implementation of add features [#107](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/107)
        * Added class and sequence diagrams for add features [#222](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/222)
        * Added manual testing instructions for add client and property [#268](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/268)
        * Added glossary [#270](https://github.com/AY2223S1-CS2113-F11-1/tp/pull/270)


- **Community**:
    - Contribute to forum discussion: [#11](https://github.com/nus-cs2113-AY2223S1/forum/issues/11)
    - Suggestions/Bug reporting for Moolah Manager during PE-D: [#171](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/171), [#178](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/178), [#186](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/186), [#198](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/198), [#199](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/199)
