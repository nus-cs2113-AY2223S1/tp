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

### Find Client and Property: `find`
This feature allows for the queried text to be filtered. The queried text is not constrained within any field.
This means that if the queried text matches the name of the first list and the address of second list, both entries
will be counted as a match.

<u>Format:</u> 
`find -CLIENT/PROPERTY f/QUERY_TEXT`

3 fields are required to be entered for successful operation:
- Command: In the context of this feature, the command will be `find`
- Client/Property: This section requires the selection of either client or property which is inputted after `-`.
- Query Text: After the tag `f/`, enter the text that will be searched through.

<u>Example:</u>

1. `find -property f/Bob The Builder`
2. `find -client f/Amos`

The 1st command above search through the property list for any matches with `Bob the Builder` while the 2nd command 
search through the client list for any matches with `Amos`.
### Exit: `quit`

---
### Loading data
The loading of the data occurs during the start of the program, before any command is entered.
There will be 3 different loading operations:
- Client
- Property
- Pairing

Clients will be added to a list containing the clients if the data stored in `client.txt` conforms to the format. 
Before appending into the list, it will also be passed through a check to ensure each of the entity is in the right 
format. Should the data fail to be in the correct format, it won't be stored into list. 

Similar operation can be applied to the `property list` where the data is stored in `property.txt`. The entries will 
also be checked that it was stored in the right format and each of the entries conforms to the requirement before it's 
added into the list containing all the property.

The `pairing list` will be the last to load. This is done so to verify that the client and property is within the 
Client's list and Property's list respectively. In the scenario where either of the entries is not in, the **pair 
would not be loaded and will be considered as incorrect data inputted**.

### Saving data

Saving of data occurs in 3 instances of operation:

- Client is added to Client's List
- Property is added to Property's List
- Pair is added to Pairing's List

When client, property and pairing is added, text will be appended to the text file as shown below:

- `Client`: Appends `CLIENT_NAME | CLIENT_CONTACT_NUMBER | CLIENT_EMAIL | CLIENT_BUDGET` to `client.txt`.
- `Property`: Appends `LANDLORD_NAME | PROPERTY_ADDRESS | PROPERTY_RENTAL_RATE | PROPERTY_UNIT_TYPE` to `property.txt`.
- `Pair`: Appends `[CLIENT_NAME | CLIENT_CONTACT_NUMBER | CLIENT_EMAIL | CLIENT_BUDGET] : [LANDLORD_NAME | PROPERTY_ADDRESS |
  PROPERTY_RENTAL_RATE | PROPERTY_UNIT_TYPE]` to `pair.txt`.

### Updating data

Updating of data occurs in 6 instances of operation:

- Client is deleted from Client List - `delete -client` command invoked
- Property is deleted from Property List - `delete -property` command invoked
- Pair is removed from Pairing List - `unpair` command invoked
- Incorrect data identified when loading of Client from `client.txt`
- Incorrect data identified when loading of Property from `property.txt`
- Missing Client or Property when loading Pairs from `pair.txt`

When the updating operation occurs, it will iterate through the list, depending on which list has been updated.
While iterating through the list, the program will **convert each entry into the correct format** and **store in their 
respective text files.**

### Manual input of data

Since the data is stored in a text file, it **allows for user to manually key in the entries** and will be loaded
when the program is re-ran.

This is how the entries should be stored :

Client: `CLIENT_NAME | CLIENT_CONTACT_NUMBER | CLIENT_EMAIL | CLIENT_BUDGET`

Property: `LANDLORD_NAME | PROPERTY_ADDRESS | PROPERTY_RENTAL_RATE | PROPERTY_UNIT_TYPE`

Pairing: `[CLIENT_NAME | CLIENT_CONTACT_NUMBER | CLIENT_EMAIL | CLIENT_BUDGET] : [LANDLORD_NAME | PROPERTY_ADDRESS |
PROPERTY_RENTAL_RATE | PROPERTY_UNIT_TYPE]`

Note that if the entries are stored incorrectly, the program will automatically remove the entries from the text file
during the next run of the program. This is done so to **prevent overcrowding** of junk data.

--- 
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Add Property: `add -property n/NAME a/ADDRESS p/PRICE t/TYPE`
* Delete Property: `delete -property ip/PROPERTY_INDEX`
* List Properties: `list -property`
* Check Property: `check -property ip/PROPERTY_INDEX`
* Find Property: `find -property f/QUERY_TEXT`

* Add Client: `add -client n/NAME c/CONTACT_NUMBER e/EMAIL b/BUDGET_MONTH`
* Delete Client: `delete -client ic/CLIENT_INDEX`
* List Clients: `list -client`
* Check Client: `check -client ic/CLIENT_INDEX`
* Find Client: `find -client f/QUERY_TEXT`

* Pair: `pair ip/PROPERTY_INDEX ic/CLIENT_INDEX`
* Unpair: `unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX`


## Common Error Messages
| Error Message                | Reason               | Remedy                |
|------------------------------|----------------------|-----------------------|
| *INSERT ERROR MESSAGE HERE*  | *INSERT REASON HERE* | *INSERT REMEDY HERE*  |
| *INSERT ERROR MESSAGE HERE*  | *INSERT REASON HERE* | *INSERT REMEDY HERE*  |
