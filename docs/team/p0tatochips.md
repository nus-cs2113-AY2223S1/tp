# Heinrich Koh Kaiyang - Project Portfolio Page

## Overview

Computer Component Chooser (CCC) is a desktop CLI application for computer build management. 
CCC helps PC enthusiasts and commercial custom PC builders to track their computer builds and components and 
check the compatibility of the parts.

### Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=p0tatochips&breakdown=true)
* **Enhancements implemented**:
  * Refactored the parse method in the Parser Class to follow the OOP principles. (Pull request: #43)
    * Justification: The refactoring was done to improve the code quality and readability of the code.
    * Relatively simple to implement, used the IDE.
  * Modified/Fixed the find command to allow users to search for builds by their name. (Pull request: #51)
    * What it does: Allows users to search for builds by their name and prints all builds that contains the search term
    the user has provided.
    * Justification: This enhancement improves the product because it allows users to search for builds
    by their name, which is a very important feature. More convenient than having to scroll and read the whole list of 
    builds.
    * Highlights: The find command was not working as intended and not connected to other parts of the code, 
    so I fixed it and adapted other parts of the code, such as in the Parser class, to allow the find command to work.
  * Added a filter feature to filter the list of builds by their price, power or compatibility. (Pull request: #51) 
    * What it does: It filters the list of builds based on either the price, power or compatibility of the builds and 
    prints out the filtered list. 
    * Justification: It allows users to filter the list of builds by their price, power or compatibility.
    It provides a convenient way to see builds that meet certain requirements provided by the user.
    * Highlights: It is a new feature. This feature was done with various types of filters in mind and as one of the
    type of filters (compatibility) work differently from the other two (price and power) and was implemented 
    considering and handling possible bugs and exceptions, it was a challenging feature to implement.
  * Added Exceptions: BlankStringException, NegativeNumberException, UnlistedBuildException, UnlistedComponentException.
  * Fixed numerous bugs and typos.
  * Added some testing to the ParserTest and EditParserTest and ComponentTest classes. 

### Summary of Contributions to team-based tasks
* I created the 2.1 milestone.
* I released versions v1.0 and v2.0.

### Summary of Contributions in Documentation
* **JavaDoc**:
  * Added JavaDoc comments for most exceptions classes and the BuildManager class
* **User Guide**:
  * Changed Introduction to include the target audience and hyperlinks for features. (Pull request: #134)
  * Added Editing a build, Deleting a build, Viewing a build, Listing all builds, Finding builds, Filtering builds,
  Exporting builds, Exporting builds as CSV, Build Editing mode, Adding a component, Deleting a component, Viewing a 
  component, Listing all components, Checking compatibility, Information about a build, Exporting a build, Exiting Build
  Editing mode parts to the User Guide.
  * Helped with minor edits/fixes to the User Guide.
* **Developer Guide**:
  * Added to User Stories section to Developer Guide. (Pull requests: #39, #76)
  * Added a glossary for PC parts to the Developer Guide to explain terms for non-technical readers. (Pull request: #39)
  * Contributed the BuildManager, Adding a build, Listing all builds, Finding builds, Filtering builds of the Developer 
  Guide. (Pull requests: #138)
  * Added to the Manual Testing section of the Developer Guide (Build Editing mode and Exiting the Program). 
  (Pull request: #151)
  * Created diagrams for the Developer Guide:
    * BuildManager (Pull request: #76, 79)
    * BuildManagerAddBuildSequence (Pull requests: #138)
    * BuildManagerFilterSequence (Pull request: #138)
    * BuildManagerFindSequence (Pull request: #138)
    * BuildManagerListSequence (Pull request: #138)

### Contributions beyond the project team
* I helped to find bugs during the PE-demo.
* I helped review some PRs. (Pull request: #132)