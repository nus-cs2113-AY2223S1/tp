# Property Rental Manager - User Guide

Property Rental Manager (PRM) is a desktop application that helps property agents manage, filter and monitor single 
owner rental units for appropriate tenants. This application uses Command Line Interface (CLI) and is able to display 
information quickly with minimal latency.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Add Property: `add -property`](#add-property-add--property)
    * [Delete Property: `delete -property`](#delete-property-delete--property)
    * [List Properties: `list -property`](#list-properties-list--property)
    * [Check Property: `check -property`](#check-property-check--property)
    * [Add Client: `add -client`](#add-client-add--client)
    * [Delete Client: `delete -client`](#delete-client-delete--client)
    * [List Clients: `list -client`](#list-clients-list--client)
    * [Check Client: `check -client`](#check-client-check--client)
    * [Pair Client and Property: `pair`](#pair-client-and-property-pair)
    * [Unpair Client and Property: `unpair`](#unpair-client-and-property-unpair)
    * [Exit: `quit`](#exit-quit)
    * [Saving and Loading Entries dynamically](#saving-and-loading-entries-dynamically)
* [FAQ](#faq)
* [Command Summary](#command-summary)


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

Note:
* Parameters appear in the form of a/PARAMETER 
* `-property` and `-client` indicates whether the command is for property or client 
* Words in UPPER_CASE are parameters to be specified by the user


### Add Property: `add -property`

### Delete Property: `delete -property`


### List Properties: `list -property`

### Check Property: `check -property`
Displays the information of the specified property, along with the clients the property is being rented by, if any.

<u>Format</u>: `check ip/PROPERTY_INDEX`

<u>Example</u>: `check ip/2`

### Add Client: `add -client`

### Delete Client: `delete -client`

### List Clients: `list -client`

### Check Client: `check -client`
Displays the information of the specified client, along with the property the client is renting, if any.

<u>Format</u>: `check ic/CLIENT_INDEX`

<u>Example</u>: `check ic/5`

### Pair Client and Property: `pair`
Pairs the client to the specified property, to record that the client is renting the property.

<u>Format</u>: `pair ip/PROPERTY_INDEX ic/CLIENT_INDEX`
* `PROPETY_INDEX` and `CLIENT_INDEX` must be indexes present in the client list and property list respectively.
* The client must not currently be in a pairing.
* The property can be paired with multiple clients.

<u>Example</u>: `pair ip/1 ic/5`

### Unpair Client and Property: `unpair`
Unpairs the client from the specified property, to record that the client is no longer renting the property.

<u>Format</u>: `unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX`
* `PROPETY_INDEX` and `CLIENT_INDEX` must be indexes present in the client list and property list respectively.
* The client and property must currently be in the same pairing.

<u>Example</u>: `unpair ip/1 ic/5`

### Exit: `quit`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Add Property: `add -property n/NAME a/ADDRESS p/PRICE t/TYPE`
* Delete Property: `delete -property ip/PROPERTY_INDEX`
* List Properties: `list -property`
* Check Property: `check -property ip/PROPERTY_INDEX`

* Add Client: `add -client n/NAME c/CONTACT_NUMBER e/EMAIL b/BUDGET_MONTH`
* Delete Client: `delete -client ic/CLIENT_INDEX`
* List Clients: `list -client`
* Check Client: `check -client ic/CLIENT_INDEX`

* Pair: `pair ip/PROPERTY_INDEX ic/CLIENT_INDEX`
* Unpair: `unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX`



