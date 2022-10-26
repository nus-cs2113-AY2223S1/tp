# Developer Guide

## Welcome!
Welcome and thanks for joining us in building our program created to handle all of your currencies and money problems. With us you can use such functions as transfer, withdraw, add with different currencies to handle all your daily money problems. You also have the opportunity to add your own currencies, such as cryptocurrencies and etc. 

Within this documentation you can find all the information needed to keep building it.

## Setting up

This section describes the development tools used in the creation of Currency Manager.

### 2.1 Prerequisites
  1. JDK 11
  2. Intellij IDEA
  3. Gradle 6.2

### 2.2 Setting up the project
  1. Fork this repo and clone the fork onto your computer
  2. Open Intellij(if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first).
  3. Set up the correct JDK version for Gradle
      a) That can be done under `Configure` > `Project Defaults` > `Project Structure`
  4. Click `Import Project`
  5. Find the `build.gradle` file and select it. Click `OK`.
  6. Click `Open as Project`
  7. Click `OK` and accept the default settings.

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Save / Withdraw feature

The save and withdraw feature is implemented based on the Wallet class data structure. 
The Wallet class has several components: the username, the password, the default currency, the total balance, 
and the deposit list containing different types of currency and their balances.

Given below is the UML diagram of the Wallet class.

![avatar](/property/WalletClass.jpg)

Here is an example usage scenario and how the save/withdraw feature behaves at each step.

Step 1: The user register a new account and a new `Wallet` object is initialized.
For now, the deposit list is empty.

![avatar](/property/ObjectStep1.jpg)

Step 2: The user login and executes `save sgd 100` to save 100 singapore dollars in the wallet. 
The `save` command first parses the command and verify its validity. 
After that, it calls the `saveMoney` method of the `Wallet` class, 
and the `saveMoney` method traverses the deposit list and finds that there's no existed SGD deposit. 
Therefore, it creates a new `Deposit` object with the currency of SGD and the balance of 100, 
and then adds it to the deposit list.
Also, the total balance is updated.

![avatar](/property/ObjectStep2.jpg)

Step 3: The user executes `withdraw sgd 20` to withdraw 20 singapore dollars in the wallet. 
The `withdraw` command parsed the command and verify its validity. 
Afterwards, it calls the `withdrawMoney` method of the `Wallet` class, 
and the `withdrawMoney` method traverses the deposit list and finds that there's existing SGD deposit. 
Therefore, it finds the corresponding `Deposit` object 
and calls the `withdraw` method within it to withdraw 20 singapore dollars.
Similarly, the total balance is updated.

![avatar](/property/ObjectStep3.jpg)

### Add/remove personal currency feature 

The personal currency feature allows users to add a new currency to the list of currencies that is stored on their computer. They also have the opportunity to remove that same currency from the list so that it can’t be used anymore, but they are not able to remove any other currencies, that have been preset when downloading the jar.

Given below are the UML diagrams of the classes that are needed for adding/removing a personal currency: 

![avatar](/property/newcurrency1.png)

Step 1: The user calls the command new_currency and is then prompted in the CLI with the following commands: 
Please enter your new currency's abbreviation: `btc`
Please enter your new currency's full name: `bitcoin`
Please enter your new currency's symbol: `bc`
Please enter your new currency's rate: `0.000049`

Based on that a new currency is created (in this example we have created a new currency of bitcoin).

![avatar](/property/newcurrency2.png)

Step 2: In order for the new currency to be added to the currencies file, firstly the equivalent string value of it is needed to write it. For that the `getPersonalCurrencyFileLine` method of the `PersonalCurrencyList` class is called. It returns a string in the format of `“!btc,bitcoin,bc,4.9E-6”`. You can notice that there is `‘!’` in front of the string, that indicates that it is a personal currency. Then the `writeToCurrencies` method of `NewCurrencyFileWorkings` class is called with the previously returned string value as parameter to write that line to the currencies file.


Step 3: Then once it is successfully written to the `currencies.txt` file, then the previously created `Currency` is added both to the `currencyList` and to the `personalCurrencyList`. We need to keep track of both so it’s possible to remove that same currency from that `currencies.txt` file later.

Step 4: Let’s say the user now wants to remove the previously created personal currency from the list of available currencies. It can type in the command `remove` and get prompted with this : 
OK, which currency would you like to remove? Enter the abbreviation of that currency: `btc`

Step 5: Now we have the abbreviation of the currency and we will add `‘!’` in front of it and create a new temporary currency object based on that with the `findCurrencyByAbbrName` method of the `CurrencyList` class and then check if the currencyList contains that currency. If so, then the `deleteFromCurrencies` method of the `NewCurrencyFileWorkings` class is called, which removes that currency from both of the currency lists and rewrites the `currencies.txt` file without that personal currency.

## Product scope
### Target user profile

 Someone who works with many types of currencies, is interested in the forex market, or is interested in having a completely online wallet for tracking the money in the account.

### Value proposition

User who has a wallet which can have multiple currencies and can change them from one currency to another, essentially a bank account manager, where user can also withdraw and add money to the account. If they are trying to withdraw more money than they have in one currency we give them the option of exchange some from another currency (e.g trying to withdraw 100 SGD, but only have 80 SGD, we would tell them they can convert 14.34 EUR to match the remaining 20 SGD). They can give out requests to convert either one, select amount of currencies or all of the currencies they have to one specific currency. 

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|New User|Create a wallet| can access my wallet and see its total value|
|v1.0|User|Add money to the wallet| to increase the balance in my account|
|v1.0|User|Withdraw money| I can indicate money has been taken from the wallet|
|v1.0|User|get the total balance in their account|get the total amount of money in the account in their default currency|
|v1.0|User|get the wallet details|know how much of each currency they currently have in the account|
|v1.0|User|Request my wallet to be deleted|So that all of the data on my wallet and everything will be deleted.|
|v1.0|User|ask for the current exchange rate between two currencies| know the rate|
|v1.0|User|Get details about a certain currency|Find all of its symbols and the main conversion rates between the major currencies|
|v1.0|New User|Set username and password|Sets username and password to account in order for more secure access|

|v2.0|User|Change password|I can change my password|
|v2.0|User|Change username|I can change my username|
|v2.0|User|get their account statement for the current cycle|get some sense of tracking within a single execution|
|v2.0|User|Transfer funds|Send some money to some other wallet|
|v2.0|User|Set default currency|So all withdrawals are made in that currency|
|v2.0|User|View all available currencies|I can see all the currencies that are available to exchange|
|v2.0|User|Convert all currencies to one currency|So that I can convert all of my currencies to only one currency|
|v2.0|User|Edit the wallet settings|To change the default currency|
|v2.0|User|Exchange currencies| I can get some of another currency|
|v2.0|User|add a new personal currency|use that with my other transactions and keep track of it|
|v2.0|User|remove my personal currency|I can remove once it's outdated or not needed anymore|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
