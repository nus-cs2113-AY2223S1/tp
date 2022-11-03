# Malcolm Ang's Project Portfolio Page

## Project: parKING

parKING is a desktop app that helps drivers choose the best place to park via the Command Line Interface (CLI).
parKING allows users to search, save and look at car park availability information at a glance, while interfacing with
LTA's real-time API.

A summary of my contributions below:

### Code Contributed
[Reposense Report](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=malcolmang&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=malcolmang&tabRepo=AY2223S1-CS2113-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
### Enhancements Implemented
- **`CarparkList` and `Carpark` classes**: Implemented the classes that are central to the program: The `CarparkList` class contains all the `Carpark` objects and represents the data repository of the program while it is running. Most of the commands interact and modify the `CarparkList` class in some way - apart from designing the structure of these classes, I wrote all the internal methods to make this possible, including methods for `find` and `filter`.
- **`FileReader` class**: Implemented the class to read and parse .json format files into a usable format (the `Carpark` class). The `jackson` API was used and took much time to implement and work with. Extensive error handling was done such that .json files formatted improperly would be ignored and, in the worst case, regenerated from an internal .jar. Crucial fields are checked closely to make sure they adhere to the right format (using Regular Expressions). Additionally, methods to read and write to a `carpark.txt` file to save Carpark information that can be updated through files fetched from the API were written by me.  
- **`filter` function**: The `filter` function is one of the core functionalities of the program, allowing users to filter carparks, searching for keywords. Making it flexible (matching multiple keywords), and including features like colourful highlighting of matched words, took creating whole new classes (`Sentence` and `Word`) to implement properly. The methods for both matching Carpark ID and searching based on an address were written (and extensively tested) by me.
### Contributions to the UG: 
- Helped to create `filter` user guides and documentation
- Information on formatting with save files and what is/isn't accepted input 
### Contributions to the DG: Which sections did you contribute to the DG? 
- Documentation for `CarparkList`, `Carpark`, `FileReader` etc.
### Contributions to team-based tasks
### Review/mentoring contributions: Links to PRs reviewed, instances of helping team members in other ways.
### Contributions beyond the project team:
