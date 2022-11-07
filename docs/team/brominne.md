# Bian Rui's Project Portfolio Page 

### Project Contribution
- Construct the overall OOP structure for normal commands and edit mode commands.
All relavent subtypes of commands extends from `Command` or `EditModeCommand`
- Implement `Command`, including `DeleteCommand`, `ViewCommand`, `HelpCommand`,
`InvalidCommand`, `ExitCommand`.
- Implement `EditModeCommand`, including `Add`, `Delete`, `Invalid`.
- Structure the flow of `Command` execution and `EditModeCommand` execution.
- Update Developer Guide and User Guide of respective sections.


## Overview
RecipEditor is a hybrid CLI-GUI application that allows you to manage your recipes with the speed
and convenience of command-line based tools. It helps users to store recipes, edit saved recipes,
delete unused or outdated recipes, find recipes with relevant keywords, view and list recipes.

## Summary of Contributions
The following sections summarise what I have contributed to the project.

#### **New Feature**: Command and EditModeCommand
- What it does:

  Command is a wrapper for all command user specified. EditModeCommand is a wrapper for all modes of edit 
  command.

- Justification:

  Normal commands are similar as they shared many attributes and methods. They are constructed and called 
  similarly. Creating wrapper for all the commands makes adding new commands easier and standardized without
  affecting other components depending on the wrapper. So as for various edit commands.

- Highlights:

  - All `Command` and `EditModeCommand` feature `execute()` method, which returns `CommandResult`. This allows
  integrating execution of all commands in a single shared code block.
  - `Command` and `EditModeCommand` contains information on the usage of each command. This makes documentation
  of commands in Recipeditor systematic.

- Pull requests: [#3](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/3),
  [#67](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/67)

#### **New Feature**: Parser and FlagParser
- What it does:

  It parses command and flags in command. After parsing, relevant commands pertaining command information
  will be generated. It reports respective issues if command input does not follow existing rules or contains
  unrecognised flags or arguments.

- Justification:

  Due to similarity and differences in many command types, it is important to interpret commands as clear as
  possible so the software will run as expected. This also helps identify issues in commands more accurately
  for users to rectify faulty command input.

- Highlights:

  - All parsing functions on commands soly rely on Parser or FlagParser. This makes managing parsers much easier.
  - Indentifying exact issues is achieved with extensive use of different types of exceptions that can only be 
  produced in certain section of the code. Referring to the different types of exceptions, it makes identifying
  source of error much easier.

- Pull requests: [#31](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/31),
[#67](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/67),
[#195](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/195)

#### **New Feature**: Help, List and Add Command
- What it does:

  `HelpCommand` gives usage of all available commands. `ListCommand` list all recipes available in Recipeditor.
  `AddCommand` adds new recipe to the Recipeditor.

- Justification:

  These are essential features for users to get to know and perform basic operations in Recipeditor. With these
  features, users can add and view all the recipes they store, and access rules for using the commands in software.

- Highlights:

  - `ListCommand` shows all recipe titles with index in front.
  - `HelpCommand` provides information on all available commands and their syntax. It relies on `Command` wrapper
  for syntax information.

- Pull requests: [#3](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/3),
  [#19](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/19),
  [#154](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/154)

#### **Enhancement to Existing Feature**: View and Delete Recipes
- What it does:

  It helps user to view or delete recipes from the list of all stored recipe, and keep the changes
  to the local machine. 

- Enhancements:

  - Give users options of using index or title of recipe to view or delete recipes, users can choose 
  the more comfortable way to use Recipeditor more efficiently.

- Pull requests: [#168](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/168)

#### **Code Contribution**: 
[RepoSence link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=brominne&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Brominne&tabRepo=AY2223S1-CS2113-T18-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### **Project Management**:
- Manage issue trackers in `v1.0`
- Release `v2.0`
- Create, review and merge PRs, and resolve conflicts across branches.
- Maintain JUnit test for newly added code from me in CI/CD

#### **Documentation**:
- Developer Guide:
  - Command component in design [#63](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/63)
  - Parser in implementation [#169](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/169)

#### **Community**:
- Report 13 issues in [PE dry run](https://github.com/Brominne/ped/issues)
- Review iP for [Hang Tian](https://github.com/nus-cs2113-AY2223S1/ip/pull/49) and
[Kwua Chun Ren](https://github.com/nus-cs2113-AY2223S1/ip/pulls/JordanKwua)