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

---

### Add Property: `add -property`
Adds a new property into property list, along with Singapore address and unit-type validations. Also, duplicate property entries of the same **address** will not be accepted.

<u>Full Format</u>: `add -property n/NAME a/ADDRESS p/PRICE t/TYPE`

<u>Full Example</u>: `add -property n/Bob Tan Bee Bee a/25 Lower Kent Ridge Rd, Singapore 119081 p/1000 t/HDB 3` 

The descriptions of `add -property` PARAMETERS are as follows:
- `NAME`: Name of property owner (Landlord)
- `ADDRESS`: Singapore Address (including postal code)
- `PRICE`: Rental Price per Month for property (SGD)
- `TYPE`: Type of property (e.g. HDB, Condominium, etc)

As there are validations involved, some PARAMETERS must adhere to specific formats as shown below:

For valid `ADDRESS`, a valid Singapore address must be provided with the following format and details:
```
--------------------------------------------------------------------------------
LANDED PROPERTY:
  Format:  [Unit Number]<space>[Street Name],<space>Singapore<space>[Postal Code]
  Example: 60 Aria Street, Singapore 602580
--------------------------------------------------------------------------------
BUILDINGS (e.g. HDBs, apartments, condominiums):
  Format (Without Building Name):
  [Block Number]<space>[Street Name]<space>#[Unit Level]-[Unit Number]{<space>[Building Name]},<space>Singapore<space>[Postal Code]
  Example: 101 Marlow Street #12-05, Singapore 059020
  Example (With Building Name): 101 Marlow Street #12-05 Clife Parkview, Singapore 059020
--------------------------------------------------------------------------------
Note: Format is <space> sensitive; [Detail] must be provided; {Detail} is optional
Any deviation from format will lead to invalid address.
```
For valid `TYPE`, one of the 15 valid Singapore-based unit type labels (System Pre-Defined) must be provided with the following format:
```
Format: t/<label>
--------------------------------------------------------------------------------
HDB Labels:
  <HDB 2> for HDB 2-Room Flexi
  <HDB 3> for HDB 3-Room
  <HDB 4> for HDB 4-Room
  <HDB 5> for HDB 5-Room
  <HDB 3Gen> for HDB Third-Generation
  <HDB ExFlat> for HDB Executive Flat
  <HDB DBSS> for HDB Design, Build and Sell Scheme (DBSS) Flat
  <HDB ExMsn> for HDB Executive Maisonette
  <HDB Jumbo> for HDB Jumbo Flat
  <HDB TH> for HDB Terrace House
--------------------------------------------------------------------------------
Condominium Label:
  <Condo> for Condominium
--------------------------------------------------------------------------------
Penthouse Label:
  <PH> for Penthouse
--------------------------------------------------------------------------------
Landed Property Labels
  <LP TH> for LP Terrace House
  <LP SDH> for LP Semi-Detached House
  <LP BGL> for LP Bungalow
--------------------------------------------------------------------------------
```
Lastly, for valid `PRICE`, a positive number excluding any letters/symbols/spaces must be provided.

---

### Delete Property: `delete -property`


### List Properties: `list -property`

### Check Property: `check -property`
Displays the information of the specified property, along with the clients the property is being rented by, if any.

<u>Format</u>: `check ip/PROPERTY_INDEX`

<u>Example</u>: `check ip/2`

---

### Add Client: `add -client`

Adds a new client into client list, along with Singapore contact number and basic email format validations. Also, duplicate client entries of the same **name**, **contact number** or **email** will not be accepted.

<u>Full Format</u>: `add -client n/NAME c/CONTACT_NUMBER e/EMAIL b/BUDGET_MONTH`

<u>Full Example</u>: `add -client n/Doja Cat c/93437878 e/doja88@example.com b/2000`

Note: Email is optional so excluding `e/EMAIL` or having `e/BLANK` is acceptable.

The descriptions of `add -client` PARAMETERS are as follows:
- `NAME`: Name of client (Person looking for a property to rent)
- `CONTACT_NUMBER`:  Contact number of client
- `EMAIL`: Email of client
- `BUDGET_MONTH`: Budget per month (SGD) of client

As there are validations involved, some PARAMETERS must adhere to specific formats as shown below:

For valid `CONTACT_NUMBER`, a Singapore contact number (no extension) must be provided. A Singapore contact number starts with a `6`,`8` or `9`, followed by 7 remaining digits.  

For valid `EMAIL`, it must adhere to the RFC 5322 Official Email Standard.

For valid `BUDGET_MONTH`, a positive number excluding any letters/symbols/spaces must be provided.

---

### Delete Client: `delete -client`

### List Clients: `list -client`

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
* Check Property: `check -property ip/PROPERTY_INDEX`

* Add Client: `add -client n/NAME c/CONTACT_NUMBER e/EMAIL b/BUDGET_MONTH`
* Delete Client: `delete -client ic/CLIENT_INDEX`
* List Clients: `list -client`
* Check Client: `check -client ic/CLIENT_INDEX`

* Pair: `pair ip/PROPERTY_INDEX ic/CLIENT_INDEX`
* Unpair: `unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX`



