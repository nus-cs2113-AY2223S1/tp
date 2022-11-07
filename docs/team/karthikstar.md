# Karthikeyan's Project Portfolio Page

## Project: OneDoc
OneDoc is a CLI application to be used by doctors and other healthcare professionals
to manage patient records, their visit records and prescription records as well. It is
written in Java, and has about 3kLoC.

### Summary of Contributions
* __New Feature__: Added functionalities to manipulate Visits
    * Entirety of the methods in `VisitList` and `Visit` classes was written by me.
    * These include, adding a visit, editing a visit's reason, deleting a visit's reason, viewing all the visits of a specific patient, viewing all visits and viewing a specific visit.
    * Additional methods were implemented to handle duplicate visit records, and manipulation of reason for visits.


* __Code contributed__: [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=karthikstar&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=karthikstar&tabRepo=AY2223S1-CS2113-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* __Documentation__:
    * User Guide:
        * Added documentation for `Visit` related features
        * Added minor tweaks to rectify flaws in User Guide
    * Developer Guide:
        * Added documentation for `Visit` component as well as related class and sequence diagrams for it
        * Added Quick Start section to guide developers on how to run our application


* __Enhancements__:
    * Added some useful helper functions, such as `printErrorMessage` and `printLine`.
    * Fixed some major issues: see [#111](https://github.com/AY2223S1-CS2113-F11-4/tp/issues/111), [#161](https://github.com/AY2223S1-CS2113-F11-4/tp/issues/161), [#167](https://github.com/AY2223S1-CS2113-F11-4/tp/issues/167)
    * Added duplicate checks in `VisitList` class to avoid creation of exact duplicate visit records (Pull Request
            [#199](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/199))
    * Fixed formatting issues


* __Testing__: Added relevant JUnit test cases to thoroughly ensure `VisitList` methods are working as intended, after each PR.

* **Community**:
    * PRs reviewed (within team): 
[#90](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/90),
[#94](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/94),
[#97](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/97),
[#122](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/122),
[#198](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/198),
[#210](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/210),
[#234](https://github.com/AY2223S1-CS2113-F11-4/tp/pull/234)


