# Chia Thin Hong's Project Portfolio Page

## Project: Moolah Manager

Moolah Manager is a financial bookkeeping epplications used for management of the monetary transactions. 
The user is encouraged to take ownership of managing their finances via a time-saving and efficient command-line interface. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=wcwy&breakdown=true)


* **Feature Implemented**: Added the budget feature for user to set monthly budget.
    * What it does: allow users to set their monthly budget. 
      Budget reminders, tips and advices are displayed to the users whenever appropriate.
    * Justification: This feature completes the money management component of the application by informing the users 
      whether their spending is kept within the budget.
    * Highlights: This enhancement complements the interaction between the application and the users.
      Budget related messages are now displayed in many scenarios such as program start up, modification of transaction 
      list and viewing of monthly statistics.


* **Feature Implemented**: Added the ability to display help messages to users to aid user's application usage.
    * What it does: allow users to list the help messages in either basic or detailed version. 
    * Justification: This feature improves the usability of the application especially for the new users 
      as it conveniently shows the usages and explaination of all available commands to the users.
    * Highlights: This enhancement affects all the existing and commands to be added in the future. 
      Every command is required to implements the help methods to display the command usage and explanation. 


* **Enhancements to existing features**:
    * Implementation of inheritance on transaction class 
      [\#16](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/16) 
    * Adding of transactions into transaction list 
      [\#18](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/18)
    * Creation of MoolahException class
      [\#18](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/18)
    * Parser logic for add command's date parameter 
      [\#52](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/52)
    * Refactoring of ParameterParser from Command classes on the concept of separation of concern 
      [\#81](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/81)
    * Setting maximum transaction list size and relevant error message 
      [\#114](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/114)


* **Project management**:
    * Set up GitHub team repository 
    * Protected master branch of team repository
    * Managed release `v2.0` on GitHub
    * Merged team members' pull requests into main repository frequently
    * Created [forking workflow guide](https://docs.google.com/document/d/1d-yTa5fhnyMyF6ABKGbYfh4Zi_q82gQgbxMp86cPO44/edit?usp=sharing) when project was first started
    * Tested the program in every milestone, with the following bugs created as an issue
      [\#130](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/130)
      [\#133](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/133)
      [\#239](https://github.com/AY2223S1-CS2113-W12-2/tp/issues/239)
    * Assisted in writing meeting minutes
      [1](https://docs.google.com/document/d/18zvFOPEew3ibDTlziH2LDhJD3XdkgpoRG-NV6DUVzgk/edit?usp=sharing),
      [2](https://docs.google.com/document/d/1BTy0oOmE4DH9Rnh0785crjeH-Cb6xhZu-WzwdBFXYZg/edit?usp=sharing),
      [3](https://docs.google.com/document/d/1YD_K-qJWD71bubg9NmO2Fk8da-m2jHnCxVfmOH-D1-o/edit?usp=sharing),
      [4](https://docs.google.com/document/d/16B4PsU8XksoOgPL4_LH8KSqvOimmlXA7SmXdmdsaLXs/edit?usp=sharing),
      [5](https://docs.google.com/document/d/1AJXFNDYvx4OPPSjxEO8yk8ahnYf_ddSTyPUaruKqVa4/edit?usp=sharing)


* **Testing**:
    * Created test cases for regression testing consistently
    * Wrote unit and integration testing using JUnit tests:
      [\#69](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/69),
      [\#121](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/121),


* **Documentation**:
    * User Guide:
        * Added documentation for the features `help` and `budget`
        * Added documentation to describe the command format used in the guide.
    * Developer Guide:
        * Added implementation details of the architecture of the application.
        * Added implementation details of the Data component of application.
        * Added implementation details of the `help` and `budget` feature.
        * Created architecture diagram, sequence diagram for `budget` and the class and sequence diagrams for `help`.


* **Community**:
    * PRs reviewed (with non-trivial review comments): 
      [\#75](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/75), 
      [\#77](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/77), 
      [\#84](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/84),
      [\#95](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/95), 
      [\#110](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/110), 
      [\#126](https://github.com/AY2223S1-CS2113-W12-2/tp/pull/126), 
      
    * Contributed to forum discussions (examples: 
      [1](https://github.com/nus-cs2113-AY2223S1/forum/issues/8#issuecomment-1236301126), 
      [2](https://github.com/nus-cs2113-AY2223S1/forum/issues/8#issuecomment-1236323125))
  
    * Reported bugs and suggestions for other teams in the class (severe bugs reported:
      [1](https://github.com/wcwy/ped/issues/1), [2](https://github.com/wcwy/ped/issues/2), 
      [3](https://github.com/wcwy/ped/issues/3), [4](https://github.com/wcwy/ped/issues/5), 
      [5](https://github.com/wcwy/ped/issues/6), [6](https://github.com/wcwy/ped/issues/7))