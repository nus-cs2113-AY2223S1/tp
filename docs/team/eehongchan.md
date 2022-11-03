# Chan Ee Hong's Project Portfolio Page

## Project: parKING

parKING is a desktop app that helps drivers choose the best place to park via the Command Line Interface (CLI).
parKING allows users to search, save and look at car park availability information at a glance, while interfacing with
LTA's real-time API.

Given below are my contributions to the project.

- **New Feature**: Added the `Favourite` class
  - What it does: It allows the user to favourite specific carparks that they want, and unfavourite carparks that they have already favourited.
  - Justification: This feature improves the user experience as it saves the user time by allowing the user to view the 
number of available lots of all favourited carparks immediately with one command, instead of searching for each carpark individually, making it more convenient for the user
  - Highlights: Implementing this feature required good understanding of the implementation details of many other classes (e.g. `FileReader`, `FileStorage`, `CarparkList`) and how they interact with each other.
- **New Feature**: Added the 'Ui' class
  - What it does: It is responsible for taking in user input and printing output to communicate with the user.
  - Justification: This feature is important as it bridges the gap between the source code and the user and allows them to interact with each other.
- **Code Contributed**: [RepoSense link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=eehongchan&breakdown=true).
- **Enhancement To Existing Feature**:
  - Allow users to favourite and unfavourite multiple carparks at once, saving users a lot of time and effort as they only have to type in a single command instead of multiple commands (one command for each carpark they want to favourite/unfavourite).
  - Allow users to use shorthand commands (e.g. `fav` instead of `favourite`, `e` instead of `exit`) to save time.
- **Documentation**:
  - User Guide:
    - Added documentation for the `favourite` and `unfavourite` commands [#123](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/123).
    - Added example usage for the `list` command [#123](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/123).
  - Developer Guide:
    - Added documentation for the `favourite` and `unfavourite` commands [#87](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/87).
    - Added documentation for the `Ui` Component [#103](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/103).
    - Created class diagram in PlantUML for `favourite` command [#87](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/87).
    - Created sequence diagram in PlantUML for `favourite` command [#103](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/103).
