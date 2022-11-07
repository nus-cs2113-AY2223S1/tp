# Ria Vora Portfolio Page

## Project: OneDoc

OneDoc is a desktop CLI application that is intended for doctors and other medical
professionals to efficiently keep track of patients, their visits and prescriptions.
It is written in Java and has about 3 kLoC.

### Summary of Contributions

* **New Feature**: Parsed commands for Patient Submenu
  * *What it does*: Allows the user to add, edit, retrieve, or view all patients through typing a command and a few parameters
  * *Highlight*: Utilized regular expressions to check for only one or two words for the name,
  a specific date format for the date of birth, and a single letter for gender.
* **New Feature**: Parsed commands for Visit Submenu
  * *What it does*: Allows the user to add, edit, delete a reason, view a patient's visits, or view all visits through typing
  * *Highlight*: Utilized regular expressions to add an optional reason of unlimited words,
    a specific date format for the date of birth, and a specific time format for time.
* **New Feature**: Parsed commands for Prescription Submenu
  * *What it does*: Allows the user to add, edit, view a patient's prescriptions and active prescriptions, activate
  / deactivate a prescription, and view all prescriptions 
  * *Highlight*: Utilized regular expressions to allow for certain Prescription names with a -, dosage with a number 
  and amount (accounting for floats as well), and a time instruction to explain how it should be taken.
* **Regular Expressions**: For each command, implemented strict regex to ensure that there wasn't any incorrect input
  * *What it does*: Utilizing regular expressions allowed me to group the input, and easily separate it from unnecessary
  spacing and extra characters, as well as check that the input is correctly formatted. For example, using the /d
  allows me to check for any digit between 0 and 9 without writing a separate method for it.
  * *Highlight*: I added //s* around every command, which allows for optional spaces that a quick typer may write by
  accident. If there are any accidental spaces, the groupings will ignore them, ensuring the input is correctly parsed
  and displayed to the user. This means if a user accidentally adds spaces, they won't have to redo the command or worry
  about it messing with their input!
* **Error Handling**: Implemented specific error messaging based on the command issue
  * *What it does*: If a command has incorrect parameters but the correct initial word (i.e. add with incorrect parameters)
  then it will fail the Matcher's find method (as the parameters are incorrectly formatted). To help with usability, I
  implemented a special error method to ensure that even if the Matcher fails, the type of command (i.e. add) can
  be identified, and the error message will specifically return how to format an add command.
  * *Highlight*: I also added a few helpful touches, such as checking everything in lowercase (an accidental uppercase 
  won't prevent the user from seeing a helpful error message) and checking similar words such as "delete" and deleteReason
  at the same time.
* **Demo Video**: Wrote the demo video script and executed the demo video screen recording
    * *What it does*: Explains how OneDoc should be used from a doctor's perspective, with fake data and a walk through
  of all the commands
    * *Highlight*: I also explained the exact use case of a doctor looking to quickly retrieve a patient record, as well
  as how the error handling can help with incorrect commands.
  
[Code Contributed](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=riavora&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code)

**Enhancements to existing features**:
* *Unique Ids*: Only allowed patients to be created with unique IDs, and ensured the patient exists for each command (
    [#139](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/139))
* *Exit*: Set the option for users to exit from anywhere in the program using `bye` (
  [#139](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/51))

**Project management**:
* *Idea Development*: Whiteboarded the class structure with my team during tutorial
* *Issue Creation*: During most meetings, I shared my screen and created issues for each item being discussed, as well
as assigned the issues and put the corresponding labels and milestone
* *Idea Development*: Whiteboarded the class structure with my team during tutorial

**Documentation**:
* User Guide:
  * Added documentation about all main menu commands, including `main` and `1`, `2`, and `3`
  * Created the introduction and instructions
  * Updated the description of the commands as the regular expressions were developed
* Developer Guide
  * Added class details for the `Parser` component, including specific error handling
  * Added step-by-step implementation details for `patientParser` method and how it analyzes each type of command

* **Community**:
    * PRs reviewed (within team): [#199](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/199),
      [#194](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/194),
      [#143](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/143),
      [#134](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/134),
      [#103](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/103),
      [#102](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/102),
      [#93](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/93),
      [#91](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/91),
      [#74](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/74),
      [#64](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/64),
      [#49](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/49),
      [#43](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/43)