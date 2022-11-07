# User Guide

## Contents
* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Features](#features)
  * [Lock Screen Commands](#lock-screen-commands)
    * [Registering a New Account](#registering-a-new-wallet-register)
    * [Log In to an Existing Account](#logging-into-an-existing-wallet-login)
    * [Currency Manager](#currencies-section-currencies)
    * [Add a new Personal Currency](#add-a-new-personal-currency-to-the-list-of-currencies-new_currency)
    * [Remove a Personal Currency](#removing-your-personal-currency-remove)
    * [Exit the program](#exit-program-at-login-screen-bye)
  * [Currency Manager](#currencies-section-currencies)
    * [Information about a currency](#currency-information-info)
    * [Conversion between currencies](#conversion-between-two-currencies-conversion)
    * [List information about all available currencies](#listing-currencies-list)
    * [Exit Currency Manager](#exit-currency-manger-exit)
  * [Account Commands](#account-commands)
    * [Get account balance](#getting-balance-balance)
    * [Get account details](#getting-details-details)
    * [Deposit money into account](#depositing-money-to-your-account-deposit)
    * [Withdraw money from account](#withdrawing-money-from-your-account-withdraw)
    * [Convert Money from one currency to another](#convert-money-convert)
    * [Convert all the money into one currency](#convert-all-money-convert-all)
    * [Transfer money into another account](#transferring-currencies-transfer)
    * [Listing the names of the available commands](#listing-commands-list)
    * [Get information about the commands entered in the current session](#current-session-account-history-account-history)
    * [Enter the Help Center](#help-center-help)
    * [Logout](#log-out-logout-log-out)
  * [Help Center](#help-center-help)
    * [Change default currency](#change-accounts-default-currency-change-default-currency)
    * [Change account username](#change-account-username-change-username)
    * [Change account password](#change-account-password-change-password)
    * [Delete Account](#delete-account-delete-account)
    * [Exit Help Center](#exit-help-center-exit)


## Introduction

Stores all the forex rates in one place and allows the user to store different types of currency within one “bank” account/wallet.

## Quick Start


1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Duke` from [here](https://github.com/AY2223S1-CS2113-W13-1/tp/releases).

## Features 

### Lock Screen Commands

### Registering a new wallet: `register`
Registers a new wallet for you

Format: 
  1. `register`
  2. `Please enter your username: <username>`
  3. `Please enter your password: <password>`

* The username has to be a unique username.
* The password has to be at least 8 characters long.  

Example of usage: 

`Please enter your username: testing`

`Please enter your password: password123`
  
  

### Logging into an existing wallet: `login`
Logs you into your wallet, so you can do your actions there

Format: 
  1. `login`
  2. `Please enter your username: <username>`
  3. `Please enter your password: <password>`
 

Example of usage: 

`Please enter your username: testing`

`Please enter your password: password123`
  
  

### Add a New Personal Currency to the list of currencies: `new_currency`
Adds your currency to the list of currencies so that you could track that one as well

Format: 
  1. `new_currency`
  2. `Please enter your new currency's abbreviation: <abbreviation>`
  3. `Please enter your new currency's full name: <full name>`
  4. `Please enter your new currency's symbol: <symbol>`
  5. `Please enter your new currency's rate: <rate>`
  
#The rate has to be against usd, e.g 1 usd - 0.000049, you would enter 0.000049

Example of usage: 

`Please enter your new currency's abbreviation: btc`

`Please enter your new currency's full name: bitcoin`
  
`Please enter your new currency's symbol: bc`
  
`Please enter your new currency's rate: 0.000049`

  
### Removing your personal currency: `remove`
Removes your personal currency from the list of currencies

Format: 
  1. `remove`
  2. `OK, which currency would you like to remove? Enter the abbreviation of that currency: <abbreviation>`
  
#The currency has to be the one that is added by yourself, you can't remove other preset currencies

Example of usage: 

`OK, which currency would you like to remove? Enter the abbreviation of that currency: btc`

### Exit Program at Login Screen: `bye`
Exit from the Curency Manager Interface

Format:
1. `bye`

Example of usage:
`bye`

### Currencies section: `currencies`
Get to the currencies section

#### Can be called inside or outside your wallet

Format:
1. `currencies`


Example of usage:

`currencies`


### Currency information: `info`
Get information about a certain currency

Format:
1. `info <abbreviation>`


Example of usage:
`info sgd`


### Conversion between two Currencies: `conversion`
Get information about a certain currency

Use the first example when inside your account to get the conversion from the given currency to your default currency

Format:
1. `conversion <abbreviation>`
2. `conversion <abbreviation> <abbreviation>`

Example of usage:
`conversion sgd`
`conversion sgd usd`

### Listing currencies: `list`
Lists a detailed view of all the currencies that are available to use

Format:
1. `list`


Example of usage:

`list`

### Exit Currency Manger: `exit`
Exit from the currency center

Format:
1. `exit`

Example of usage:
`exit`

### Account Commands
  
### Getting balance: `balance`
Gets you your current wallet balance

Format: 
  1. `balance`
  
#You need to be logged into your wallet

Example of usage: 

`balance`
  
### Getting details: `details`
Getting a detailed view of your wallet

Format: 
  1. `details`
  
#You need to be logged into your wallet

Example of usage: 

`details`
  
  
### Depositing money to your account: `deposit`
Deposit money to your wallet

Format: 
  1. `deposit <currency> <amount>`
2. `deposit <amount>`
  
#You need to be logged into your wallet

Example of usage: 

`deposit sgd 100`
`deposit 100`
  
  
### Withdrawing money from your account: `withdraw`
Withdrawing money from your account

Format: 
  1. `withdraw <currency> <amount>`
  2. `withdraw <amount>`
  
#You need to be logged into your wallet

Example of usage: 

`withdraw sgd 100`
`withdraw 100`

### Convert money: `convert`
Convert some money in a certain currency to another one in your wallet

Format: 
  1. `convert <oldCurrency> <oldAmount> <newCurrency>`
  
#You need to be logged into your wallet

Example of usage: 

`convert sgd 100 usd`

In the example above, the command means converting 10 units of SGD to USD

### Convert all money: `convert all`
Convert all money in your wallet to a certain currency

Format: 
  1. `convert all <currency>`
  
#You need to be logged into your wallet

Example of usage: 

`convert all sgd`

In the example above, the command means converting all the money in the wallet to SGD

### Listing commands: `list`
Lists a detailed view of all the commands that are available to use

Format: 
  1. `list`
  
#You need to be in the currencies section

Example of usage: 

`list`
  
 
### Transferring currencies: `transfer`
Transfer an amount of money to another wallet

Format: 
  1. `transfer <recipient> <amount> <currency>`
  
#You need to be logged into your account

Example of Usage: 

`transfer testing2 100 sgd`

### Current session account history: `account history`
Print out the list of commands you have entered within this current instance of accessing your account

Format: `account history`

Example of Usage: `account history`

#### testing logged in at 2022-11-07 13:58:28

#### balance




### Log Out: `logout/ log out`
Log out of your account

Format:
1. `logout`
2. `log out`

Log out of your account and get back to the home screen


### Help center: `help`
Helps you deal with account settings, such as changing your password, username, or default currency or deleting your account.
#### For all of the commands within the help center you can cancel changing by typing exit whenever prompted
Format:
1. `help`

Example of usage:

1. `help`

### Change accounts default currency: `change default currency`
Request the help center to assist with changing your default currency. Enter the new currency you want to change to as it's abbreviation.

Format:
1. `change default currency`
2. `Please re-enter your password to make this change`
3. `Password: <password>`
4. `Enter the currency you would like to change your default to: <currency>`
5. `Your default currency has been set from <currency> to <currency>`

Example of Usage:
1. `change default currency`
2. `Please re-enter your password to make this change`
3. `Password: password123`
4. `Enter the currency you would like to change your default to: euro`
5. `Your default currency has been set from usd to euro`

### Change account username: `change username`
Request the help center to assist with changing your username

Format:
1. `change username`
2. `Please re-enter your password to make this change`
3. `Password: <password>`
4. `New username: <username>`
5. `Your username has been successfully changed`

Example of Usage:
1. `change username`
2. `Please re-enter your password to make this change`
3. `Password: password123`
4. `New username: testing1`
5. `Your username has been successfully changed`

### Change account password: `change password`
Request the help center to assist with changing your default currency

Format:
1. `change password`
2. `Please re-enter your password to make this change`
3. `Password: <password>`
4. `New Password: <password>`
5. `Your passwrod has been successfully changed`

Example of Usage:
1. `change password`
2. `Please re-enter your password to make this change`
3. `Password: password123`
4. `New Password: 12345678`
5. `Your passwrod has been successfully changed`

### Delete account: 'delete account'
Request to delete your account

Format:
1. `delete account`
2. `Please re-enter your password to make this change`
3. `Password: <password>`
4. `Your wallet username: <username>`
5. `Are you sure to delete your wallet? Your wallet can't be retrieved and you will automatically logout.`
6. `Please enter Y or yes to proceed. Any other input will cancel the deletion operation.`
7. `Y/yes`

Example of Usage:
1. `delete account`
2. `Please re-enter your password to make this change`
3. `Password: 12345678`
4. `Your wallet username: testing1`
5. `Are you sure to delete your wallet? Your wallet can't be retrieved and you will automatically logout.`
6. `Please enter Y or yes to proceed. Any other input will cancel the deletion operation.`
7. `Y`

### Exit help center: `exit`
Exit the help center
Format: `exit`

Example of Usage: `exit`
  
## FAQ
**Q**: How do I transfer my data to another computer? 

**A**: You can just create a zip file of your jar file with your data folders that contain all the information about your wallet(s) so once you copy those over you can automatically keep working there

## Command Summary
 
 ### 1) Login / Register
 
 - `login`
 - `register`
 - `Please enter your username: <username>`
 - `Please enter your password: <password>`
  
 ### 2) New Currency
 
 - `new_currency`
 - `Please enter your new currency's abbreviation: <abbreviation>`
 - `Please enter your new currency's full name: <full name>`
 - `Please enter your new currency's symbol: <symbol>`
 - `Please enter your new currency's rate: <rate>`
  
### 3) Remove currency
 
 - `remove
 - `OK, which currency would you like to remove? Enter the abbreviation of that currency: <abbreviation>`
 - `change default currency`
  
 
 ### 4) Adding password during register
 
 
 - `password: password123`
 - `Enter the currency you would like to change your default to: eur`
  
### 5) Changing password
 
 
 - `change password`
 - `password: password123`
 - `Enter the currency you would like to change your default to: eur`
  
 ### 5) Changing username
 
 - `change username`
 - `password: password123`
 - `New username: testing1`
  
 ### 6) Deleting account
 
 - `delete account`
 - `y`
  
 ### 7) Getting balance
 
 - `balance`
  
 ### 8) Getting detailed view of your wallet
 
 - `detail`
  
 ### 9) Depositing money to your account
 
 - `deposit <currency> <amount>`
 - `deposit <amount>`
  
 ### 10) Withdrawing money from your account
 
 - `withdraw <currency> <amount>`
 - `withdraw <amount>`

  ### 11) Converting all your money to one currency
 
 - `convert`
 - `convertAll`
 - `setDefault`

  ### 12) Getting a list view of all the currencies
 
 - `list`
  
  ### 13) Transfer money to another wallet
 
 - `transfer <recipient> <amount> <currency>`
  
  ### 14) Getting info about a currency
 
 - `info <currency>`

 ### 15) Converting from one currency to another
 
 - `conversion <abbreviation>`
 - `conversion <abbreviation> <abbreviation>`
