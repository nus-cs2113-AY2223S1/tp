# Yong Chin Han - Project Portfolio Page

## Project: Moolah Manager

Moolah Manager is a financial bookkeeping application used for management of the monetary transactions.
The user is encouraged to take ownership of managing their finances via a time-saving and efficient command-line
interface.
It is written in Java, and has about 10 kLoC.

### Summary of Contributions

* **Code
  Contributed**: [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=chinhan99&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)  


* **Implementations**: Added and tested parsing capabilities to check for erroneous user inputs.
  * What it does: The parser is required for all commands, it checks for the necessary tags and parameters required in the particular command. If the user input contains errors, the program will output an error message. 
  If the user input is a valid input after it has been parsed, the input values will be further parsed by the command's respective methods. 
  * Justification: Essential in preventing incorrect data from being accepted into the program and informs users regarding error tags or parameters.
  * Highlights: Parsing methods are used by all features within the program. It serves as the first line of checks on the user's inputs.


* **Implementations**: Added storage capabilities for the program into external file duke.txt
  * What it does: Each time a transaction/budget is edited or added to the program, it's values will be updated into external file duke.txt.
  When users want to reuse these data after shutting down Moolah Manager, these values can be reused.
  * Justification: As users would want to continuously use the program at different points of time, storage is essential to ensure that data gets stored correctly. 
  * Highlights: During the storage of file values when Moolah Manager is started up, the parsers would check for erroneous data in the file.
  Incorrect data parameters would trigger an error message to inform users to edit the incorrect data (to prevent overwriting the file ). 



* **Contributions to the UG**:
  - Described the Add transaction command.
  - Described the automated storage feature. 
  - Added the Command cheatsheet, detailing the syntax users need to use to correctly use the program.


* **Contributions to the DG**:
Contributed to the followings DG segments with their attached diagrams and descriptions:
  - Storage Component.
  - Storage Operation.
  - Add Command.


* **Contributions to team based tasks** (Highlighted **a few examples for conciseness**):


  1) Conducted extensive testing of program beyond personal scope and published bug findings regularly.
  - [Issue 1](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/76)
  - [Issue 2](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/128)
  - [Issue 3](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/145)
  
  2) Assisted team to fix bugs beyond personal assigned tasking.
  - [Bug 1](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/80)
  - [Bug 2](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/157)
  - [Bug 3](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/205)
  
  3) Reviewed and Commented on team's PR code with improvement suggestions. 
  - [PR 1](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/77)
  - [PR 2](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/126)
  - [PR 3](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/149)
  - [PR 4](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/154)


* **Contributions beyond the project team**: Contribution of Issues to other projects.
  - [Issue 1](https://github.com/chinhan99/ped/issues/1)
  - [Issue 2](https://github.com/chinhan99/ped/issues/2)
  - [Issue 3](https://github.com/chinhan99/ped/issues/3)
  - [Issue 4](https://github.com/chinhan99/ped/issues/5)