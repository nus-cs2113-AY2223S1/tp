# John Toh Jia Jun's Personal Portfolio Page

### Project: Timetabler


Given below are my contributions to the project
* **New Feature:** Added ability to search for information about a certain module.
    * What it does: allows the user to search for basic information about a specific module.
    * Justification: This feature allows the user to get a basic gist of what the module is about.
    * Highlights: This feature uses the NUSMods API, which is heavily utilised in the rest of the project.
    * Credits: [NUSMods API](https://api.nusmods.com/v2/) for all the information on modules in NUS.
      [FasterXML](https://github.com/FasterXML/jackson) for parsing of json responses from the API.

* **Code Contributed:** [RepoSense link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=jjtoh&tabRepo=AY2223S1-CS2113-T17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **Project Management:**
    * Managed release v1.0 on GitHub
* **Enhancement to existing features:**
  * Added data structures to better classify lessons within each module
  * Improved set function such that only eligible lessons can be selected
  * Improved add function such that certain lessons that are fixed are included by default
* **Documentation:**
  * User Guide:
    * Updated some features within the user guide
    * Added an appendix that covers issues regarding file corruption during usage of the program
  * Developer Guide
    * Added implementation details for `set` and `info` features.
    * Added user stories
* **Tools:**
    * Integrated third party API (NUSMods API) to the team project
    * Integrated a plugin (FasterXML) to help parse json responses from API calls