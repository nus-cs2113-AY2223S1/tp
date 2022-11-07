# Malcolm Ang's Project Portfolio Page

## Project: parKING

parKING is a desktop app that helps drivers choose the best place to park via the Command Line Interface (CLI).
parKING allows users to search, save and look at car park availability information at a glance, while interfacing with
LTA's real-time API.

A summary of my contributions below:

### Code Contributed
[Reposense Report](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=malcolmang&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=malcolmang&tabRepo=AY2223S1-CS2113-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
### Enhancements Implemented

- **`CarparkList` and `Carpark` classes**: Implemented the classes that are central to the program: The `CarparkList` class contains all the `Carpark` objects and represents the data repository of the program while it is running. Most of the commands interact and modify the `CarparkList` class in some way - apart from designing the structure of these classes, I wrote all the internal methods to make this possible, including methods for `find` and `filter`. ([#12](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/12))
- **`FileReader` class**: Implemented the class to read and parse .json format files into a usable format (the `Carpark` class). The `jackson` API was used and took much time to implement and work with. Extensive error handling was done such that .json files formatted improperly would be ignored and, in the worst case, regenerated from an internal .jar. Crucial fields are checked closely to make sure they adhere to the right format (using Regular Expressions). Additionally, methods to read and write to a `carpark.txt` file to save Carpark information that can be updated through files fetched from the API were written by me. ([#12](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/12), [#88](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/88))
- **`filter` function**: The `filter` function is one of the core functionalities of the program, allowing users to filter carparks, searching for keywords. Making it flexible (matching multiple keywords), and including features like colourful highlighting of matched words, took creating whole new classes (`Sentence` and `Word`) to implement properly. The methods for both matching Carpark ID and searching based on an address were written (and extensively tested) by me. ([#75](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/75))
- **Exception handling**: robust exception handling was designed and enforced by me to make sure that the program won't crash under any (normal) circumstances. Strict error handling and input validation was introduced.

### Contributions to the UG: 
- Helped to create `filter` User Guide
- Added a whole section on editing and formatting save files, and what valid input is allowed for each field. Added many disclaimers about what exactly is supported and unsupported behaviour when it comes to file editing.
- Helped to correct errors and find typos in the User Guide

### Contributions to the DG: Which sections did you contribute to the DG? 

- Class diagrams for `Model` component and associated documentation 
- Class diagrams for `Common` component and associated documentation
- Sequence diagrams and explanation for the implementation for loading and parsing files from the .json files

### Contributions to team-based tasks

- Helped keep team on track in terms of Github issues
- Created the GitHub organization and forked the tp repo

### Review/mentoring contributions: Links to PRs reviewed, instances of helping team members in other ways.

- Helped various members with debugging and issues with Gradle and git. 
- Assisted in heavy bug testing of code
- Assisted other members with writing methods for their portions (for example, writing the `Argument` class to keep track of dashed arguments for the `Parser` class)
- Kept team on track with milestones and keeping code clean (coming up with a list of things to check on that won't be picked up by checkstyle)

### Contributions beyond the project team:

- Reported more bugs than necessary for PE-D
- Assisted a student outside my group in solving an issue with CI/CD on GitHub actions

