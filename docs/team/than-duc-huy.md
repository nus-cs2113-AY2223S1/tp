# Than Duc Huy - Project Portfolio Page

## Overview

I contributed to this RecipEditor: recipe manager
- Add recipe functionality (GUI)
- GUI functionality (proposed, got approved and solely in charged of GUI)
- Functionality related to GUI
  - Interaction with the storage files
  - Parsing text files to recipes
- Major contributor and reviewer of Developer Guide
## Summary of Contributions

### Features

#### Add Recipe

- **What**:

Add new recipe functionality that utilizes the GUI functionality

- **Justification**

I initially made the CLI add functionality. I proposed the GUI Add functionality because it will more user-friendly and
a faster way to add recipe for fast typer as the amount of specific flags worsen the user experience

#### GUI Functionality (Add, Edit GUI)

- **What**: Add the component that call the GUI and the interaction between GUI, CLI


- **Justification**:

To make the transition between the GUI and CLI workflow seamless. Since the functionality of edit and add in GUI mode
are the similar, the GUI component is also used for the Edit Command (whose CLI functionality was handled
by [William](snuckerzlol.md))

- **Highlight**:

Added other useful sub-features to make the GUI experience useful

- Template file: to give a user guide on how to use add
- Temporary file: remember the state of the invalid version of the file that the user input, allowing the user to revise
  quickly without re-write the whole thing again
- Save and Exit option: Allow the user to abort the editing without saving
- Option to fix invalid file without exiting the `/add` command: When the user text input is cannot be parsed, it will
  be saved in the temporary file and a question will be asked to the user on whether they want to change without having
  to type `/add` or `/edit` to access the command again

#### Text to Recipe Parsing

- **What**:

A parser that parse text file generated from the GUI into a valid Recipe that can be stored in the Recipe Object
by the program

- **Justification**:

Because the user can type virtually anything in a text file, a robust parser to a valid Recipe Object that the program
can understand is important

- **Highlight**:

The parser is strict but allow the user some freedom to key in the data

- Strict: only 4 headings as stated in the template is allowed
- Freedom:
    - The headings can be in any order (preferably following the template)
    - (Excessive) Whitespace will not affect the parsing
    - Whitespace in the DESCRIPTION will not be deleted, allowing the user to be creative in writing Recipe description.

#### Storage: GUI interaction with the data files, prevent tampering

- **What**:

Store Recipe data from the Model functionality. Create logic to make the program recover gracefully from user tampering
with the data

- **Justification**:

The bulk of the code for Storage is made by [Qian Hui](qianz-z.md). However, due to my GUI proposal, the complexity of
storing the data evolved over time and I helped to code various helper functions to ensure that the interaction between
the data files and the program is seamless

- **Highlight**:

Allow the user to tamper with the data in various way without crashing the program
### Code Contribution [Reposense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Than-Duc-Huy&tabRepo=AY2223S1-CS2113-T18-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project Management

- Propose changes direction from pure CLI to hybrid CLI-GUI to suit the use case
- Communicate the change in direction with TAs and Professor for approval
- Represent the group to various questions in the forum during the development
- Contributed frequently for various milestones

### Documentation

- **User Guide**
    - Major revision of the content after PED
    - Give suggestion of for the parsing of other functionality
    - Command Summary
- **Developer Guide**
    - In charge of the following sections
      - GUI Component
      - Data on Startup and Exit
      - Add Recipe
      - GUI Workflow
      - Parse Text to Recipe
      - Find REcipe
      - Instruction for manual testing
    - In charge of final revamp of the DG 
      - Minor changes to what others have done to make the DG coherent
    - In charge of reviewing and fixing of UML Diagrams