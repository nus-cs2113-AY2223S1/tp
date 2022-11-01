# Dhanish's Project Portfolio Page

## Project: OneDoc

OneDoc is a desktop CLI application that is intended for doctors and other medical 
professionals to efficiently keep track of patients, their visits and prescriptions.
It is written in Java and has about 3 kLoC currently.

### Summary of Contributions
* __New Feature__: Methods that allow manipulation of patients were written in the `PatientList` class by me. These include, adding a patient, modifying patient details, retrieving patient records and listing all patients.


* __New Feature__: Added functionality that saves the data of patients, visits and prescriptions when user exits the program and reloads them when user reopens the program.
  * Credits: Most of the code was heavily adapted from my own IP on Duke


* __Code contributed__: [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=dhanish265&tabRepo=AY2223S1-CS2113-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* __Documentation__: 
  * User Guide:
    * Added documentation for `Patient` related features
  * Developer Guide:
    * Added documentation for `Patient` component as well as the following related sequence diagram.

![](images/PatientListInitialization.png)

* __Enhancements__: 
  * Added some useful helper functions, such as `printErrorMessage` and `printLine`.
  * Fixed some major bugs: see [#27](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/27)


* __Testing__: Added relevant JUnit test cases to ensure `Patient` functions are working constantly after every PR.


