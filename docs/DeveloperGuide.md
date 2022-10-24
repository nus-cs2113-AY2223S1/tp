# Developer Guide

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
