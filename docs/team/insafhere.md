# Muhamed Insaf - Project Portfolio Page

### Project: PlanIt

PlanIt is a Command Line Interface (CLI) based application to help School of Computing students to plan their modules and credits every semester.
They can also check if they are eligible for NOC or SEP using this application.

### Summary of Contributions

Given below are my contributions to the project:

* **New Feature**: Added the ability to Clear modules in the Student Plan
    * What it does: Allows the user to Clear modules for one Semester or all Semesters in with one command
    * Justification: This feature enables users to simply remove modules in their Plan
    * Highlights: This enhancement is very usefully for users when they want to restart or modify their Academic Plan
    * Credits: NIL
    * Pull Request(s): [#151](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/151), [#158](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/158)

* **New Feature**: Added the ability to Overview Student Profile
    * What it does: Allows the User to view their total MCs taken, CAP and Eligibility for SEP/NOC in one command
    * Justification: This feature is useful to have a quick glance of their Academic Plan
    * Highlights: This enhancement was challenging to implement as it requires implementation of other features such as `Check` and `View` features
    * Credits: NIL
    * Pull Request(s) : Pull Request(s): [#154](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/154), [#157](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/157), [#158](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/158)

* **New Feature**: Added the ability for users to view all format input issues for commands
    * What it does: Allows the user to view all input format issue(s) in when using the `Add`, `Delete`, `View`, `Clear`, `MCs` and `View` command
    * Justification: This feature is useful for users if they made multiple errors when using a command, so they can rectify it accordingly
    * Highlights: This enhancement was tedious to implement due to the non-existence of getMessage() method for each exception implemented previous, hence had to restructure the exceptions for best implementation of this feature
    * Credits: NIL
    * Pull Request(s): [#140](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/140), [#145](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/145), [#146](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/146), [#147](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/147)

* **New Feature**: Prints 'PlanIt!' as an ASCII text in the Welcome Message in console
    * What it does: Allows the user to have a pleasant welcoming experience
    * Justification: This feature improves the Users' experience when using the app
    * Highlights: This enhancement required the use of external code reference
    * Credits: This code is licensed under - CC Attribution CC BY 4.0. [#Link](https://www.quickprogrammingtips.com/java/ascii-art-generator-library-in-java.html)
    * Pull Request(s): [#149](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/149)

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=insafhere&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code)

* **Project management**:
    * Took full responsibility of managing the User Guide from scratch (Pull Request(s): [#45](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/45), [#159](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/159))
    * Implementation of JUnit Test (Pull Request(s): [#7](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/7))
    * Fixed and closed issues (Issue(s) : [#52](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/52), [#92](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/92), [#108](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/108), [#109](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/109), [#138](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/138), [#139](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/139), [#143](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/143), [#144](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/144), [#150](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/150), [#155](https://github.com/AY2223S1-CS2113-T17-2/tp/issues/155))

* **Enhancements to existing features**:
  * Modified Welcome message to introduce 'Help' feature (Pull Request(s): [#115](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/115))
  * Modified 'Help' command summary Message (Pull Request(s): [#116](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/116), [#148](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/148))
  * Modified 'Find' Feature to be able to find any keywords that match (Pull Request(s): [#118](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/118))
  * Modified 'Check' Feature to include reasons for NOC/SEP ineligibility (Pull Request(s): [#114](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/114))
  * Modified 'Check' Feature to include check for semester requirements for NOC/SEP (Pull Request(s): [#112](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/112), [#142](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/142), [#157](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/157))
  * Added Javadocs for methods in project (Pull Request(s): [#163](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/163), [#164](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/164), [#166](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/166))

* **Documentation**:
    * User Guide:
        * Full responsibility of managing the User Guide from scratch for commands (Pull Request(s): [#45](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/45), [#159](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/159))
      * Developer Guide:
        * Addition of 'View' Feature Sequence Diagram and write up (Pull Request(s): [#33](https://github.com/AY2223S1-CS2113-T17-2/tp/pull/33))
        * Review of sequence diagrams in DG

* **Community**:
  * Reported bugs and suggestions for other teams [bugs found in PED](https://github.com/insafhere/ped/issues)
  * PRs reviewed [Review on DG - MoneyGoWhere](https://github.com/nus-cs2113-AY2223S1/tp/pull/1)