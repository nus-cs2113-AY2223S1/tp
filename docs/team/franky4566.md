# Rachel Fong's Project Portfolio Page

## Project: SkyControl

SkyControl - SkyControl is a program which optimizes the use of the Command Line Interface (CLI)
to manage flights and passengers operations in an airport terminal day by day.

Given below are my contributions to the project.

+ ### New Feature: Added the ability to add a flight
  + What it does: Allows the manager to add flights and its respective details one at a time into the flight list.
  + Justification: This feature is essential for the product to function as users need a command to add flights to SkyControl.
  + Highlights: This command affects future commands as it is the base to gather information from the user. 
  Hence, a proper implementation is important to ensure all the essential flight details are recorded.
  For example, the flight departure time, boarding gate etc. 
  Thus, when future flights are added, we can check if there are any clashes using the flight details from this input command.

+ ### New Feature: Added the ability to list all saved flights
  + What it does: Allows the manager to list all saved flights in the flight list.
  + Justification: This feature allows the manager to recall flights he has already inputted or find a flight with its respective flight details.
  + Highlights: This feature helps potential features like a find feature that looks for specific keywords in the flight list.

+ ### New Feature: Added the ability to delay flights
  + What it does: Allows the manager to delay flight departure timings in the event of a flight needs to take off later than its scheduled time.
  + Justification: Flight delays often occur in airports due to unforeseen circumstances. 
  Hence, this feature allows the manager to delay a flight in 1 step without needing to delete 
  the existing flight just to add a new flight with the new departure time.
  + Highlights: This feature may affect existing features like list.
  Hence, it required a strong understanding of existing commands to make appropriate changes.
  
- ### Code Contributed:
  + [RespoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=franky4566&breakdown=true)

+ ### Project management:
  + Contributed to functionality in releases `v1.0`, `v2.0` and `v2.1` (3 [releases](https://github.com/AY2223S1-CS2113-T17-1/tp/releases)) on GitHub

+ ### Enhancement to Existing Features:
  + Created SkyControl Exception class to handle exceptions [#11](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/11)
  + Improved Code Defensiveness   

+ ### Documentation:
  + User Guide:
    + Added in documentation for the features `flight add`, `flight list` and `delay` [#54](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/54)
    + Cleaned up UG to sync formats [#85](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/85)
    + Contributed to the FAQ and Command Summary section [#85](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/85)
  + Developer Guide:
    + Added implementation details of the `flight add`, `flight list` and `delay` feature with their respective sequence diagrams. 
    [#45](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/45), [#]()
    + Populated Non-Functional Requirements, Glossary and Manual Testing Instructions section. [#]()

+ ### Community:
  + PRs reviewed (with non-trivial review comments): [#21](https://github.com/AY2223S1-CS2113-T17-1/tp/pull/21)
  + Some exceptions I handled were also adopted by other teammates e.g. checking valid gate number