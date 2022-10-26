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

![avatar](/property/newcurrency1.jpg)

Step 1: The user calls the command new_currency and is then prompted in the CLI with the following commands: 
Please enter your new currency's abbreviation: `btc`
Please enter your new currency's full name: `bitcoin`
Please enter your new currency's symbol: `bc`
Please enter your new currency's rate: `0.000049`

Based on that a new currency is created (in this example we have created a new currency of bitcoin).

![avatar](/property/newcurrency2.jpg)

Step 2: In order for the new currency to be added to the currencies file, firstly the equivalent string value of it is needed to write it. For that the `getPersonalCurrencyFileLine` method of the `PersonalCurrencyList` class is called. It returns a string in the format of `“!btc,bitcoin,bc,4.9E-6”`. You can notice that there is `‘!’` in front of the string, that indicates that it is a personal currency. Then the `writeToCurrencies` method of `NewCurrencyFileWorkings` class is called with the previously returned string value as parameter to write that line to the currencies file.


Step 3: Then once it is successfully written to the `currencies.txt` file, then the previously created `Currency` is added both to the `currencyList` and to the `personalCurrencyList`. We need to keep track of both so it’s possible to remove that same currency from that `currencies.txt` file later.

Step 4: Let’s say the user now wants to remove the previously created personal currency from the list of available currencies. It can type in the command `remove` and get prompted with this : 
OK, which currency would you like to remove? Enter the abbreviation of that currency: `btc`

Step 5: Now we have the abbreviation of the currency and we will add `‘!’` in front of it and create a new temporary currency object based on that with the `findCurrencyByAbbrName` method of the `CurrencyList` class and then check if the currencyList contains that currency. If so, then the `deleteFromCurrencies` method of the `NewCurrencyFileWorkings` class is called, which removes that currency from both of the currency lists and rewrites the `currencies.txt` file without that personal currency.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
