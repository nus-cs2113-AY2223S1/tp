# Shawn Tan Jinhui - Project Portfolio Page

## Computer Component Chooser - Overview
ComputerComponentChooser(CCC) is a Command Line Interface(CLI)
program targeting PC enthusiasts and commercial PC builders who want to simplify the tracking of computer builds
and ensure that the components they pick are compatible with one another.


### Summary of Contributions
- **New Feature:** Added compatibility checking for builds. (PR: [#34](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/34),
[#47](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/47), [#72](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/72))
    - What it does: Checks if the components in the build are compatible with one another. There are
  six checks namely: power, form factor, socket, gpu slots, ram slots and expansion slots.
    - Justification: This feature improves the product significantly because it ensures that the
    components in the build are compatible with one another. This was one of primary goals of the product
  in terms of what value we bring to our target audience.
    -  Highlights: This feature required an in-depth analysis of how the different components fit together.
- **New Feature** Added export to text and csv functionality. (PR: [#44](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/44),
 [#49](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/49))
    - What it does: Allows the user to export the build to a text file or a csv file. The text file contains the list of 
  components along with other useful aggregate information such as the total price, power and compatibility of the build.
  The CSV file contains the list of components in a table format.
    - Justification: This feature improves the product significantly because it allows the user to
    export the builds to more useful formats. The text file can be printed out and used as a shopping list while
    the csv file can be used to import the build into a spreadsheet program for further analysis.
    - Highlights: This feature required new to string methods for each component and careful formatting to ensure 
  that the output were suitable for each format.
- **Code Contribution:** [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=gitpancaked&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
- **Project Management:**
    - Initiated weekly meetings with the team.
    - Pushed the team to complete the project on time.
    - Created a discord server and telegram chat for the team to communicate and engage in discussions.
    - Set up project milestones and deadlines.
    - Checked up on the team's progress and provided feedback to ensure we remain coordinated.
    - Created issues and assigned them to team members.

- **Enhancement to existing features:**
    - Added LinkedHashMap2D class to store components in each build in a 2D linked hash map data structure. This allowed
  for more convenient storage and retrieval of components in the build. (PR: [#29](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/29))
    - Refactored EditParser to multiple methods to improve readability and maintainability. (PR: [#42](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/42))
    - Added more tests to increase code coverage for component classes. (PR: [#131](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/131), 
  [#132](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/132))
    - Created the Ui class along with the product logo. (PR: [#9](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/9))
    - General bug fixes. (PR: [#17](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/17), [#38](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/18), 
  [#41](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/41), [#133](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/133))
- **Documentation:**
    - JavaDocs for Ui, Parser, Build, LinkedHashMap2D and Export classes. (PR: [#65](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/65))
    - User Guide:
        - Added TOC, introduction, quick start, FAQ and summary of commands. (PR: [#60](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/60), 
        [#127](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/127))
    - Developer Guide:
      - Added documentation and diagrams for build and export classes. (PR: [#78](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/78), [#144](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/144))
      - Added table of contents. (PR: [#75](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/75/files))        
      - Diagram for the overall architecture of the program. (PR: [#147](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/147))
      - Added documentation for testing in main mode. (PR: [#150](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/150))
- **Community:**
    - PRs reviewed (with non-trivial review comments): (PR: [#151](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/151), 
  [#124](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/124), [#28](https://github.com/AY2223S1-CS2113T-W11-2/tp/pull/28))