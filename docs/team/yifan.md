# Bao Yifan's Project Portfolio Page

CurrencyManager - CurrencyManager is an easy wallet managing tool 
that helps you keep track of all your deposits and withdrawals, with
the possibility
of keeping different currencies and wallets, between which
you can exchange and transfer.

Given below are my contributions to the project :

### New Feature: Online Exchange Rate List Update
 - What it does: When the program initializes, it automatically checks and loads
the local record user have, and makes a URL request to online API to update the rates
if needed.
 - Justification: The user needs to have the latest version of exchange rate
list to decide their operations of saving and withdrawal.
 - Highlights: There is a three-step process that makes sure the user 
can have the latest available version of exchange rate list. First, when the program initializes, 
it checks the local record and compare the time with the system time. 
Next, if the local record does not exist, or is broken, or is out of date, 
an online URL request will be made to get the latest version of exchange rates and
update the local record. Last, If the URL request fails due to bad Internet connection,
program will use the latest local record to work. If the URL request fails and 
no local record can be used, the program will fall back to the hardcoded list 
in the source code. The program will tell the users the current version of 
exchange rate list and ask them to check the Internet connection if update is failed. 
This three-step process guarantees user can work with the latest version of 
exchange rate list we can get. All the three steps are automatically done and users are not required to update the 
exchange list manually.
 - Locations: CurrencyUpdater.java

### New Feature: Authentication Operation
  - What it does: The authentication consists of register and login 
function. It allows the user to create new wallet and login to it.
  - Highlights: the register manager checks whether the username is duplicated
and whether the password is too short. If true, it will ask the user to 
change their username and password.
  - Locations: Authentication.java, LoginCommand.java, RegisterCommand.java

### New feature: Improved Wallet and Wallet File Operation
  - What it does: It defines the wallet structure and the corresponding wallet file structure
that save the wallet info to the hard disk. When users login to a certain account,
it checks whether the info provided by users is correct, and then restore 
the info on the disk and assemble a new Wallet object for the account operations. 
When users update the wallet, the wallet automatically saves it to the file.
  - Justification: Wallet and Wallet file operations are at the core of our program.
 The authentication relies on them.
  - Highlights: The Wallet structure includes all the info needed for the 
account operation, from the username, password, to the deposit list and the balance.
  - Locations: Wallet.java, WalletFile.java

### New feature: Account Operations
  - What it does: The account operations I added include saving money, withdrawing money,
converting some amount of money to a certain currency, converting all the money to a 
certain currency, showing total balance, showing details of the wallet, deleting account, and setting the
default currency of the wallet.
  - Highlights: I implement a major part of the codes of the account operations, these functions
are the most common commands user will use. In the deletion function, the 
program asks twice before the user data is deleted. In the saving function, the 
wallet will check whether there is existing deposit in the given type of currency,
and only create a new one when no previous deposit is found, thus avoid duplication 
in the deposit list.
  - Locations: Account.java, DeleteCommand.java, MoneyCommand.java

### New feature: Exception Handling
  - What it does: The FinanceException class handles all the exceptions 
possible in the program.
  - Justification: When the exception happens, the proper exception handling is 
needed before new commands can be processed.
  - Highlights: The exception class provides a list of all the exceptions 
possible, and when encountering certain exceptions, just need to throw the 
corresponding name in the list. The exception class will automatically assemble 
the proper output.
  - Locations: FinanceException.java

### New feature: Currency List
  - What it does: The currency list stores all the currency and related information 
such as rate, abbreviation name and full name.
  - Justification: All the operations related to currency need to consult 
the currency list to get the information needed. 
  - Highlights: The currency list is initialized only once at the beginning 
of the program, and all the currency-related functions can get the info needed by 
the consult function provided in the currency list.
  - Locations: CurrencyList.java

### New feature: Currency Structure
  - What it does: The currency structure stores the information related to a currency. 
  - Highlights: The currency structure is important for 
  - Locations: CurrencyStructure.java

### New feature: UI work
  - What it does: The Ui class handle almost all the output and assemble them 
into good appearance.
  - Justification: Ui is important to the users' using experience. 
  - Highlights: The Ui class can assemble standard output, single line output, 
and multiple lines output, so it's easy to use. 
  - Locations: BasicUi.java, AuthenticationUi.java, AccountUi.java

### New Feature: Input Manager
  - What it does: The input manager handles almost all the input from the 
keyboard.
  - Justification: To provide a uniform interface to all functions that need
user input from the keyboard.
  - Highlights: It provides preliminary check on the input and filters the 
empty input.
  - Locations: InputManager.java


### Code contributed [RepoSense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=zoom&zA=AmethystQ&zR=AY2223S1-CS2113-W13-1%2Ftp%5Bmaster%5D&zACS=185.5609756097561&zS=2022-09-16&zFS=&zU=2022-11-07&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

### Enhancements to existing features
  -  Separate the code and arrange some of them into packages
  - Examples : 
      1) [Bug Fix And Code Separation](https://github.com/nus-cs2113-AY2223S1/tp/commit/d370b3f7230a2f223c00014f12a5db72ba5ef821) 
      2) [Bug Fix And Improve the Record](https://github.com/AY2223S1-CS2113-W13-1/tp/commit/eb057e78491c6f4ca5601605021641d6d7616116)
  -  

### Documentation
  - User Guide
    - Added the functions like convert, convert all, set default currency 
    functions
  - Developer Guide
    - Added the Save and Deposit features to the developer guide, and explain the 
  sture Wallet Structure 


### Community tasks (add examples of team tasks, listed on the page)
  - Separate the code when they are too long.
  - arrange the closely-related codes(close in functionality) into packages.
  - Improve other team members' UI work and arrange it in standard output.
  - Fix some bugs.
