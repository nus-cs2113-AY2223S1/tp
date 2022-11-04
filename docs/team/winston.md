# Winston Lim Cher Hong - Project Portfolio Page

## Overview

Upcycle is a perfect desktop app dedicate to managing rental businesses. The nature of these businesses
requires dealing with tons of data; therefore, noting down all information with notes and pen is not ideal for managers.
It is developed for rental business managers, who can type fast to efficiently keep track of all of their customers, items,
and transactions via a Command Line Interface.

### Summary of Contributions

- **Code contributed:**

  - Codes written can be seen [here](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=winston&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=zoom&zFR=false&zA=winston-lim&zR=AY2223S1-CS2113-W12-1%2Ftp%5Bmaster%5D&zACS=228.5&zS=2022-09-16&zFS=winston&zU=2022-11-01&zMG=false&zFTF=commit&zFGS=groupByAuthors)
  - List of PRs can be seen [here](https://github.com/AY2223S1-CS2113-W12-1/tp/pulls?q=is%3Aclose+is%3Apr+author%3Awinston-lim)

- **Features and lasses implementations:**
  - Command class
    - Designed and implemented the way commands are handled throughout the application lifecycle
    - Wrote the base Command class for which all Commands extend
  - CommandParser class:
    - Designed and implemented the way user inputs are parsed into (possibly) commands
  - User-related features:
    - Wrote the initial versions for AddUserCommand, ListUsersCommand, ViewUserCommand and RemoveUserCommand in version 1.0.
  - Item-related features:
    - Wrote the initial versions for AddItemCommand, ListItemsCommand, ViewItemCommand and RemoveItemCommand in version 1.0.
    - Wrote the final version for UpdateItemCommand
  - Transaction-related features:
    - Wrote the initial versions for AddTransactionCommand, ListTransactionsCommand, ViewTransactionCommand, and RemoveTransactionCommand in version 1.0
    - Wrote the final version for UpdateTransactionCommand
  - HelpCommand
    - Wrote the command for displaying all commands available
  - Testing: Wrote tests related to Command.
  - DukeException: Contributed to the design of exception handling in our application
  - Ui: Added a CTA to UI and contributed to the design of the Ui class
- **User Guide contributions:**
  - Added guide for:
    - Help command (section 3.1)
    - List-related commands (sections 3.2.3 3.3.3, 3.4.6)
    - Update-related commands (section 3.3.5, 3.4.6)
- **Developer Guide contributions:**
  - Design component:
    - Wrote overall architecture(section 3)
    - Wrote Command design (section 3.2)
    - Wrote Ui design (section 3.6)
  - Implementation component:
    - Wrote implementation of list-related commands `list-users` (section 4.1.3, 4.2.3, 4.3.3)
    - Wrote implementation of updated-related commands (section 4.2.5, 4.3.6)
- **Team tasks contributions:**
  - Setup of initial PM tools with jira
  - Setup of documentation tools with confluence and notion
  - Multiple refactors for fixing checkstyle errors and added javadocs throughout code base such as [here](https://github.com/AY2223S1-CS2113-W12-1/tp/pull/221)
  - Wrote guide for our workflow [here](https://github.com/AY2223S1-CS2113-W12-1/tp/issues/50#issuecomment-1279933711)
- **Community contributions:**
  - Suggested ideas for the overall architecture and shape of components
  - Independently tested and fix bugs and style errors such as [here](https://github.com/AY2223S1-CS2113-W12-1/tp/pull/114)
