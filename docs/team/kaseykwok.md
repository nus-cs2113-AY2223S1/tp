# Kwok Ying Kwan Kasey's Project Portfolio Page

## Project: OneDoc
OneDoc is a desktop application used for managing medical records and patient details. The user interacts with it using 
a CLI. It is written in Java, and has about 3kLoC.

Given below are my contributions to the project.

* **New Feature**: Added abilities to manipulate prescriptions.
  * What it does: Allows user to add, edit, view prescriptions in 3 different modes (all prescriptions, specific
  patient's prescription only and specific patient's active prescriptions only), activate and deactivate prescriptions.
* **New Feature**: Added the functionality to navigate between main menu and the 3 sub menus (Patient menu, Visit menu 
and Prescription menu)
  * What it does: Allows user to navigate to sub menus through the index number, return to main menu with the `main` 
  command, and quit the program at any point with `bye` command.
  * Highlight: This enhances the flexibility of the program to manipulate multiple entities in one run, which maintains
  the overall structure of the program.
* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=kaseykwok&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Project management**:
  * Managed releases `v1.0`, `v2.0` and `v2.1` on GitHub, including their corresponding milestones.
  * Protected the `master` branch of the team repo with mandatory status checks to pass before merging so that the CIs
  must pass for the branch.
  * Created issues and assigned issues to the team member.
* **Enhancements to existing features**:
  * Refactored most I/O interactions to the `UI` class and align printing format in `v1.0` (Pull Request 
  [#64](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/64))
  * Added duplicate checks in `PrescriptionList` class to avoid redundant prescription records (Pull Request 
  [#197](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/197))
  * Added the `help` command to avoid printing all commands every time an invalid input is received.
* **Documentation**:
  * User Guide:
    * Added documentation for all prescription manipulation commands.
    * Added documentation for the `help` and `exit` command.
    * Did cosmetic tweaks to structure the user guide and the table of content.
    * Added the TOC.
  * Developer Guide
    * Added design and implementation details for the architecture and the main component, including architecture, 
    object and sequence diagrams.
    * Added details for the `Prescription` component, including a class diagram. 
    * Added implementation details for 4 main commands in Prescription menu, with their respective sequence diagrams.
    * Added instruction for manual testing section's launch and shutdown, navigation and prescription menu tests.
    * Restructured the DG to have a similar structure with AB3-AddressBook.
    * Did final check for the DG.
    * Added the TOC.
* **Community**:
  * PRs reviewed (within team): [#34](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/34),
  [#35](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/35),
  [#37](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/37), 
  [#38](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/38),
  [#40](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/40),
  [#59](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/59),
  [#75](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/75),
  [#136](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/136),
  [#137](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/137),
  [#142](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/142),
  [#202](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/202),
  [#203](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/203),
  [#206](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/206),
  [#211](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/211),
  [#235](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/235),
  [#242](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/242)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2113-AY2223S1/forum/issues/20))
  * Assisted other team members to fix bugs they encountered