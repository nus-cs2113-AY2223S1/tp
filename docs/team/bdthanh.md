# Bui Duc Thanh - Project Portfolio Page

## Overview

Upcycle is a perfect desktop app dedicate to managing rental businesses. The nature of these businesses
requires dealing with tons of data; therefore, noting down all information with notes and pen is not ideal for managers.
It is developed for rental business managers, who can type fast to efficiently keep track of all of their customers, items,
and transactions via a Command Line Interface.

### Summary of Contributions
+ **Code contributed:** 
    + Codes written can be seen [here](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=bdthanh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=bdthanh&tabRepo=AY2223S1-CS2113-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=other~docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
    + List of PRs can be seen [here](https://github.com/AY2223S1-CS2113-W12-1/tp/pulls?q=is%3Aclose+is%3Apr+author%3Abdthanh+)

+ **Enhancements implemented:**
    + User-related features (Wrote final version base on first version of Winston): 
        + Add a user `add-user`
        + Remove a user `remove-user`
    + Item-related features:
        + Add an item `add-item` 
        + Remove an item `remove-item`
        + List all categories `list-categories`
    + Transaction-related features (Wrote final version base on first version of Winston): 
        + Add a transaction `add-tx`
        + Remove a transaction `remove-tx`
    + Exit command `bye`
    + Transaction: Wrote base class for transaction (`Transaction` and `TransactionList`) 
    + Storage: Wrote storage-related classes to store users, items and transactions to files.
    + Duke (main class): Integrated main activity and storage
    + DateParser: Wrote DateParser class to parse and format the date of transaction.
    + Testing: Wrote transaction-related and storage-related tests.
    + CommandParser: Enhanced this class based on the original class by Winston.
    + DukeException: Contributed to some exception classes and ExceptionMessages.
+ **User Guide contributions:**
    + Added `Table of contents`
    + Added `1. Introduction` and `2. Quick Start`
    + Wrote guide for add-related and remove-related command (`add-user`, `add-item`, `add-tx`, `remove-tx`, `remove-item` and `remove-tx`)
    + Contributed to Command Summary
    + Contributed to Frequently Asked Questions
+ **Developer Guide contributions:**
    + Added `Table of contents`, `Acknowledgement` and `2. Setup the environment`
    + Wrote the transaction component section `3.5. Transaction component`
    + Wrote the storage component section `3.7. Storage component`
    + Contributed to the Implementation subsection:
      + Wrote add a user (4.1.1) 
      + Wrote remove a user (4.1.2)
      + Wrote add a item (4.2.1)
      + Wrote remove a item (4.2.2)
      + Wrote add a transaction (4.3.1)
      + Wrote remove a transaction (4.3.2)
      + Wrote list all categories (4.2.7)
    + Made Class diagrams in section 3.5 and 3.7 
    + Made Sequence diagrams in section 3.1, 4.1.1, 4.1.2, 4.2.1, 4.2.2, 4.3.1 and 4.3.2
    + Wrote the non-functional requirements `7. Non-functional Requirements`
+ **Team-based tasks contributions:**
    + Maintained the issues tracker on GitHub.
    + Ensure that weekly and milestone tasks/features are completed in time.
+ **Community contributions:**
    + Reported 21 bugs/flaws in peers' tp. Issues can be checked [here](https://github.com/bdthanh/ped/issues)
    + Reviewed peers' Developer Guide during week 11 tutorial session: 
        + [CS2113-T17-3](https://github.com/nus-cs2113-AY2223S1/tp/pull/10/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
        + [CS2113-W11-2](https://github.com/nus-cs2113-AY2223S1/tp/pull/2/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
        + [CS2113-F11-4](https://github.com/nus-cs2113-AY2223S1/tp/pull/18/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
    + [Reviewed teammates' PRs](https://github.com/AY2223S1-CS2113-W12-1/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed+sort%3Acomments-desc)