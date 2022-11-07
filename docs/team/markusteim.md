# Markus Teimann's Project Portfolio Page

CurrencyManager - CurrencyManager is a easy wallet managing tool that helps you keep track of all your deposits and withdrawals, with the possibility
of keeping different currencies and wallets, between which you can exchange and transfer.

Given below are my contributions to the project :

### New Feature Added the ability to transfer funds between wallets
 - What it does : allows user to transfer funds between different wallets
 - Justification : it improves the product as now it's possible to transfer funds inbetween different wallets whenever needed
 - Highlights : this feature was built into the customer's own wallet part, as the user needs to be logged in and hence had to work with other commands


### New Feature Adding a new personal currency to the list
  - What it does : allows user to add their custom currency to the list that they can track, e.g adding cryptocurrencies to the list of currencies
  - Justification : adds some more freedom and flexibiltiy to the program, as now the currencies are not only hardcoded but new ones can be added to the list. The user can also remove those currencies, but only the ones they have added themself.
  - Highlights : as this feature is tightly linked to our previous currencies, there was a lot of work required to make it work properly and make sure that it interacts with other functions such as deposit, withdraw, transfer etc. The deleting the currency required quite a lot of work as well, as it was a bit troublesome to ensure that only the currencies that have been manually added could be removed.

### New feature Initial registering of wallet with username and password
  - What it does : Creates the wallet and the files associated with it
  - Justification : It was one of the most necessary parts of the project, as it is crucial being able to register a new wallet


### New Feature Password encoding
  - What it does : instead of writing the password just as a string, it encodes it to base64, so not everyone could read it.
  - Justification : whenever the user creates a new wallet, their username and password is saved in a .txt file, to ensure the integrity of their password and avoid compromisation, it was necessary to add some encyrption.
 
  

### Code contributed [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=AY2223S1-CS2113-W13-1%2Ftp%5Bmaster%5D&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Markusteim&tabRepo=AY2223S1-CS2113-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements to existing features
  - Did some UI improvements and bug fixes through out the project
  - Examples : 
      1) [Bug Fix](https://github.com/AY2223S1-CS2113-W13-1/tp/commit/76911d91c6b5c41227a31ab6e3ea4b014dae3ed4) 
      2) [Bug Fix](https://github.com/AY2223S1-CS2113-W13-1/tp/commit/329539a4be46bc7ce6a6e7e58b2f40d26129336f)
  - Moving our currencies from a .txt file to a 2D array. Initially I had created a .txt file where all the currencies were read in from, but later using       a 2D array seemed more sufficient, so I moved it from using the .txt file to using a 2D array that required changing a lot of the code to ensure          that all the readings and writings to the currencies list was correct and that a txt file wouldn't be used anymore.

### Documentation
  - User Guide
    - Added our functions that we use to the User Guide and drafted the first version, that was based on the Google Docs, that we had discussed with the        team before.
  - Developer Guide
    - Added the transfer of funds function to the developer guide along with some UML models that help understand it. 


### Community tasks (add examples of team tasks, listed on the page)
  - Wrote the JUnit tests that gave us 75% class coverage and 65% method coverage.
  - Some minor bug fixes along when writing my functions and working on other branches.
  - Maintained the issue tracker, adding new issues and marking them done.
