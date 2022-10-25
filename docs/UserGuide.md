# Property Rental Manager - User Guide

Property Rental Manager (PRM) is a desktop application that helps property agents manage, filter and monitor single 
owner rental units for appropriate tenants. This application uses Command Line Interface (CLI) and is able to display 
information quickly with minimal latency.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Add Property: `add -property`](#add-property-add--property)
    * [Delete Property: `delete -property`](#delete-property-delete--property)
    * [List Properties: `list -property`](#list-properties-list--property)
    * [List Properties with tags: `list -property TAG`](#list-properties-with-tags-list--property-tag)
    * [Check Property: `check -property`](#check-property-check--property)
    * [Add Client: `add -client`](#add-client-add--client)
    * [Delete Client: `delete -client`](#delete-client-delete--client)
    * [List Clients: `list -client`](#list-clients-list--client)
    * [List Clients With Tags: `list -client TAG`](#list-clients-with-tags-list--client-tag)
    * [Check Client: `check -client`](#check-client-check--client)
    * [Pair Client and Property: `pair`](#pair-client-and-property-pair)
    * [Unpair Client and Property: `unpair`](#unpair-client-and-property-unpair)
    * [List Everything `list -everything`](#list-everything-list--everything)
    * [Exit: `quit`](#exit-quit)
    * [Saving data](#saving-data)
    * [Loading data](#loading-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)


## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Download the latest version of `Property Rental Manager` from [here](http://link.to/duke).

## Features 

Note:
* Parameters appear in the form of a/PARAMETER.
* `-property` and `-client` indicates whether the command is for property or client.
* Words in UPPER_CASE are parameters to be specified by the user.


### Add Property: `add -property`

### Delete Property: `delete -property`


### List Properties: `list -property`
Lists all properties present in the list
### List Properties With Tags: `list -property TAG` 
Lists only selected details of all the properties, depending on the TAG. The commands for these are -
* `list -property a/` This is for address
* `list -property n/` This is for name
* `list -property p/` This is for price
* `list -property t/` This is for unit type
* `list -property -short` This is for the shorthand version(displays address, price and unit type)


### Check Property: `check -property`
Displays the information of the specified property, along with the clients the property is being rented by, if any.

<u>Format</u>: `check ip/PROPERTY_INDEX`

<u>Example</u>: `check ip/2`

### Add Client: `add -client`

### Delete Client: `delete -client`

### List Clients: `list -client`
Lists all the clients present in the list
### List Clients With Tags `list -client TAG` 
List only selected details of all the clients, depending on TAG. The commands for these are-
* `list -client c/` This is for contact number
* `list -client b/` This is for budget
* `list -client n/` This is for name
* `list -client e/` This is for e-mail
* `list -client -short` This is for the shorthand version(displays just name and budget)

### Check Client: `check -client`
Displays the information of the specified client, along with the property the client is renting, if any.

<u>Format</u>: `check ic/CLIENT_INDEX`

<u>Example</u>: `check ic/5`

### Pair Client and Property: `pair`
Pairs the client to the specified property, to record that the client is renting the property.

<u>Format</u>: `pair ip/PROPERTY_INDEX ic/CLIENT_INDEX`
* `PROPERTY_INDEX` and `CLIENT_INDEX` must be indexes present in the client list and property list respectively.
* The client must not currently be in a pairing.
* The property can be paired with multiple clients.

<u>Example</u>: `pair ip/1 ic/5`

### Unpair Client and Property: `unpair`
Unpairs the client from the specified property, to record that the client is no longer renting the property.

<u>Format</u>: `unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX`
* `PROPERTY_INDEX` and `CLIENT_INDEX` must be indexes present in the client list and property list respectively.
* The client and property must currently be in the same pairing.

<u>Example</u>: `unpair ip/1 ic/5`

### List everything `list -everything`
Lists all information about every client and every property in the list

### Exit: `quit`

### Loading data

### Saving data

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Add Property: `add -property n/NAME a/ADDRESS p/PRICE t/TYPE`
* Delete Property: `delete -property ip/PROPERTY_INDEX`
* List Properties: `list -property`
* List Property Addresses: `list -property a/`
* List Property Owner Names : `list -property n/`
* List Property Budgets: `list -property b/`
* List Property Unit Type: `list -property t/`
* List Property Short: `list -property -short`
* Check Property: `check -property ip/PROPERTY_INDEX`


* Add Client: `add -client n/NAME c/CONTACT_NUMBER e/EMAIL b/BUDGET_MONTH`
* Delete Client: `delete -client ic/CLIENT_INDEX`
* List Clients: `list -client`
* List Client Names: `list -client n/`
* List Client Contact Numbers: `list -client c/`
* List Client Budgets: `list -client b/`
* List Client Emails: `list -client e/`
* List Client Short: `list -client -short`
* Check Client: `check -client ic/CLIENT_INDEX`


* List Everything: `list -everything`


* Pair: `pair ip/PROPERTY_INDEX ic/CLIENT_INDEX`
* Unpair: `unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX`



