# Marvin Pranajaya's Project Portfolio Page

## Project: parKING

parKING is a desktop app that helps drivers choose the best place to park via the Command Line Interface (CLI).
parKING allows users to search, save and look at car park availability information at a glance, while interfacing with
LTA's real-time API.

Below are my contribution to the project:

### Code contributed

[Reposense Report](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=pipipipi2002&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=pipipipi2002&tabRepo=AY2223S1-CS2113-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancement implemented/contributed

- `Api` class: implemented the class that communicates with the LTA data mall API services, to receive the most updated 
carpark availability slots whenever we need it. The `Api` class supports asynchronous requests to prevent speed 
bottlenecks in the user side and also supports pagination fetch (where we can only receive at most 500 data set at once)
. The class is also responsible for ensuring any bad responses from the external API is handled (for example: no internet,
API service unavailable, wrong credentials). Upon successful retrieval of the datas, the class is responsible for 
concatenating the different datas from different fetches, and package it into a readable format for the parser to read
through the local data file.
- Provide functionality support for `update` and `auth` commands from the parser. Since we are working with an API, we
allow user to use their own API key to use our services. However, for the purposes of CS2113, we also provide a default
key for testing purposes. This API key is stored inside the source code (assuming a normal user with no access to our
source code) and can be invoked by calling `auth default`. The `update` command does the entire fetching sequence and
rewrite the entire local data file.
- Providing colour support to the terminal output using ANSI. I explored the option of using ANSI to make our UI more 
user friendly and readable. In the end, I used the library called `jansi` to enable this behaviour where we can show 
red colour output for warnings and errors, green colour for successful messages and many more to highlight differences.

### Contributions to the UG:

- Helped to format the overall UG document and did standardisation on the guides, including table of contents, links,
quickstart, example of usage.
- Formulated the documentation on `auth` commands and `update` command.

### Contributions to the DG:

- Documentation for `Api`, `update` which includes class diagram and sequence diagram to explain
how the api is loaded and how the data fetching process is implemented. 
- Contributed to the design considerations for the update command and the limitations of LTA's API.

### Contributions to team-based tasks
- Setup the Notion Page for easy task allocation.

### Review/mentoring contributions: Links to PRs reviewed, instances of helping team members in other ways.
- Helping other members to use the Jansi library to enhance the user experience.

### Contributions beyond the project team:
- Reported more bugs than necessary in the PE dry run.

### Tools

- Integrated third party library Jansi to the project ([#181](https://github.com/AY2223S1-CS2113-T17-4/tp/pull/181)).