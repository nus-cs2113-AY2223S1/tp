# Lim Chee Sng Roy - Project Portfolio Page

## Overview
Our product ComputerComponentChooser(CCC) is a Command Line Interface (CLI) based application that helps users to keep track of
their computer components when building a computer. It also helps the user to check for compatibility of the components
before purchasing them.

Note: The author "DESKTOP-8022BOQ\Admin" in Intellij IDE refers to me.
### Summary of Contributions
- **Code Contribution:** [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=lcsroy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=functional-code)
- **Enhancement implemented:** 
  - **Storage**
    - What it does: Allows the program to load any existing data from a specific folder when it starts up. It also saves
      all the data whenever the user adds or deletes a build or component.
    - Justification: This feature improves the product significantly because a user can store their data and retrieve it
      whenever they want to. This feature allows the user to have a more personalized experience.
    - Highlights: This feature required the naming of the text file to be the same as the name of the builds. This was
      done to ensure that the user can easily identify the text file that they want to load. 
  - **InvalidBuildException**
    - What it does: Handles the exception when the user uses an invalid build name. 
    - Justification: This feature helps to ensure that the user do not use a build name that has the same naming as the text
    file that is used to store all the builds. This is to prevent the user from accidentally overwriting the text file.
    - Highlights: This feature required the use of a boolean variable to check if the build name is the same as the text file
    name.
  - **Component:** Drive, GPU, PowerSupply Classes
    - What it does: Allows the user to add a drive, GPU or power supply to the build.
    - Justification: This feature allows the user to add more components to their build. It allows the user to
      have a more personalized experience.
    - Highlights: This feature required the use of inheritance to reduce the amount of code that is needed to be written.
      This was done to ensure that the code is more readable and easier to maintain.
  
### Summary of Documentation Contributions
- **Documentation Contribution:** [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=lcsroy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs)
- **JavaDoc:**
    - Added JavaDoc to all Component classes and Storage class.
- **User Guide:**
    - Added Glossary section for the different parameters that the user has to input for each component.
    - Contributed to the Filter feature for better user experience.
    - Contributed to the Add Component feature for better user experience.
- **Developer Guide:**
  - Added class diagram for Storage class to illustrate the methods and attributes used to load and save the data.
  - Added sequence diagram for Storage class to illustrate the flow of the program when loading and saving the data.
  - Added PC component glossary to explain the different parameters that the user has to input for each component.

