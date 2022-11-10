# User Guide

## Table of Contents
* [Introduction](#introduction)
* [Getting Started](#getting-started)
* [Features](#features)
    * [Build Management Mode](#build-management-mode)
        * [Adding a build](#adding-a-build-add)
        * [Editing a build](#editing-a-build-edit)
        * [Deleting a build](#deleting-a-build-delete)
        * [Viewing a build](#viewing-a-build-view)
        * [Listing all builds](#listing-all-builds-list)
        * [Finding builds](#finding-builds-find)
        * [Filtering builds](#filtering-builds-filter)
        * [Exporting builds](#exporting-builds-export)
        * [Exporting builds as CSV](#exporting-builds-as-csv-exportcsv)
        * [Exiting the program](#exiting-the-program-bye)
    * [Build Editing Mode](#build-editing-mode)
        * [Adding a component](#adding-a-component-add)
        * [Deleting a component](#deleting-a-component-delete)
        * [Viewing a component](#viewing-a-component-view)
        * [Listing all components](#listing-all-components-list)
        * [Checking compatibility](#checking-compatibility-check)
        * [Information about a build](#information-about-a-build-info)
        * [Exporting a build](#exporting-a-build-export)
        * [Exiting Build Editing Mode](#exiting-build-editing-mode-back)
    * [Storage](#storage)
    * [Export](#export)
* [FAQ](#faq)
* [Command Summary](#command-summary)
    * [Build Management Mode Summary](#build-management-mode-summary)
    * [Build Editing Mode Summary](#build-editing-mode-summary)
* [Glossary](#glossary)
    * [PC Component Parameters Glossary](#pc-component-parameters-glossary)

## Introduction

Welcome to Computer Component Chooser!
ComputerComponentChooser (CCC) is a command-line program targeting PC enthusiasts and commercial PC builders who want to:
- simplify the [tracking of computer builds](#features) 
- ensure that the parts they pick are [compatible](#checking-compatibility-check) with one another.

This user guide is meant to provide an overview of the various commands and features available in CCC. It is targeted at users who have some experience with command-line interfaces.
If you are unsure about any of the terminology used in this guide, you can refer to the [Glossary](#glossary) for a list of terms and their definitions, or consult a search engine.
Text formatted like `this` represents commands you can enter into the command line or output that you will see in the terminal.

## Getting Started

1. Install [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) for your operating system. If you are unsure about how to do this, you can refer to the [Java installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) or consult a search engine.
2. Download the latest version of `CCC` from 
[the releases page](https://github.com/AY2223S1-CS2113T-W11-2/tp/releases).
3. Copy the file to an empty folder you want to use as the _home folder_ This will be the folder in which CCC will store all of its data.
4. Open a terminal window (i.e. Command Prompt on Windows, Terminal on Mac OS, or a Linux terminal) and navigate to the home folder.
5. Run the command `java -jar CCC.jar` to start the app.
6. Refer to the [Features](#features) below to try out some commands!

Upon successfully starting the program, you will be greeted with the following:
```
Hello from
  _____                     __         
 / ___/__  __ _  ___  __ __/ /____ ____
/ /__/ _ \/  ' \/ _ \/ // / __/ -_) __/
\___/\___/_/_/_/ .__/\_,_/\__/\__/_/   
              /_/                      
  _____                                   __ 
 / ___/__  __ _  ___  ___  ___  ___ ___  / /_
/ /__/ _ \/  ' \/ _ \/ _ \/ _ \/ -_) _ \/ __/
\___/\___/_/_/_/ .__/\___/_//_/\__/_//_/\__/ 
              /_/                            
  _______                        
 / ___/ /  ___  ___  ___ ___ ____
/ /__/ _ \/ _ \/ _ \(_-</ -_) __/
\___/_//_/\___/\___/___/\__/_/   

____________________________________________________________
Hello! ComputerComponentChooser at your service!
What can I do for you today?
____________________________________________________________

```

## Features

Our program has two modes, the [Build Management Mode](#build-management-mode) and the
[Build Editing Mode](#build-editing-mode). 

Upon starting the Program, you will be situated in the [Build Management Mode](#build-management-mode). 
As you create builds, you will be able to switch to the [Build Editing Mode](#build-editing-mode) to edit the builds you have created.

### Build Management Mode

Build Management Mode, also known as Main Mode, handles the management of your builds. This is the default mode when you start the program.
You can add, delete, list, and filter through your builds in this mode.

#### Adding a build: `add`

Adds a new empty build to your list of builds. This command takes the name of the build its only parameter.

__Note__: If you wish to edit your build after adding it, you can do so by following this command [Editing a build](#editing-a-build-edit).

<span style="color:red">__Caution__</span>: The build name must be unique. If you try to add a build with a name that already exists, you will receive an error message. Build names cannot contain the following characters: `|`, `>`, `<`, `:`, `*`, `?`, `"`, `/`, `\`.

Format: `add/NAME`

Example of usage: Enter `add/test build` to add a build named 'test build' to the list of builds


```
____________________________________________________________
add/test build
____________________________________________________________
You have added test build
____________________________________________________________
```

#### Editing a build: `edit`

Enters [Build Editing Mode](#build-editing-mode) to edit the build with the given name. This command takes the name of the build as its only parameter.

Format: `edit/NAME`

__Note__: If you wish to go back to the main mode, you can do so by [entering the `back` command](#exiting-build-editing-mode-back).

Example of usage: Enter `edit/test build` to edit the build named 'test build'.


```
____________________________________________________________
edit/test build
<--------------------------------------------------------->
You are now editing test build
<--------------------------------------------------------->
```

#### Deleting a build: `delete`

Deletes a build from the list of your builds, **including the associated data files**. This command takes the name of the build as its only parameter.

Format: `delete/NAME`

Example of usage: Enter `delete/test build` to delete the build named 'test build'.


```
____________________________________________________________
delete/test build
Deleted the file: test build.txt
____________________________________________________________
You have removed test build
____________________________________________________________
```

#### Viewing a build: `view`

Lists all the components in the build with the given name, including their details. This command takes the name of the build as its only parameter.

Format: `view/NAME`

Example of usage: Enter `view/test build` to view the components of the build named 'test build'.


```
____________________________________________________________
view/testbuild
____________________________________________________________
1. ASUS ROG [$600] [130 W] [lga1511] [Full ATX] [4 RAM slots] [4 GPU slots]
2. Cooler Master [$400] [0 W] [full ATX] [2]
3. intel 10990x [$1200] [80 W] [lga1511] [3.8 GHz]
4. noctuna [$300] [60 W] [m4] [1000 RPM] [20 dB]
5. Samsung EVO [$500] [35 W] [500 GB] [ssd]
6. Samsung EVO 2 [$500] [35 W] [500 GB] [ssd]
7. Samsung EVO 3 [$500] [35 W] [500 GB] [ssd]
____________________________________________________________
```

#### Listing all builds: `list`

Lists all builds in the list of builds currently managed by the program.

Format: `list`

Example of usage: Enter `list` to list all builds.


```
____________________________________________________________
list
____________________________________________________________
Your current builds:
1. testbuild
____________________________________________________________
```

#### Finding builds: `find`
    
Finds and lists builds whose names contain any of the given keywords. This command takes the keywords as its only parameter.

Format: `find/KEYWORD`

KEYWORD can be any number of words separated by a space.
    
Example of usage: Enter `find/test` to find all builds that contain the keyword 'test'.


``` 
____________________________________________________________
find/test
____________________________________________________________
Found Builds:
test build
____________________________________________________________
```

#### Filtering builds: `filter`

List all builds that fit the specified requirements.
`filter` can display builds that pass compatibility checks, or display builds that fall within a specified price or power range.

Acceptable Formats:
1. `filter/price/RANGESTART/RANGEEND`
2. `filter/power/RANGESTART/RANGEEND`
3. `filter/compatibility`
- `RANGESTART` must be integer or float number
- `RANGEEND` must be integer or float number

__Note__: If you provide a `RANGESTART` that is greater than `RANGEEND`, the program will automatically swap the two values.

If there are no builds that fit the specified requirements, the program will display a message saying so.

Examples of usage: 
1. Enter `filter/compatibility` to find all builds that are compatible.
2. Enter `filter/price/3000/4000` to find all builds that have a total cost between $3000 and $4000.


```
____________________________________________________________
filter/compatibility
____________________________________________________________
No builds that meet specifications found.
____________________________________________________________
```

```
____________________________________________________________
filter/price/3000/4000
____________________________________________________________
Filtered Builds:
1. build1
2. build2
____________________________________________________________
```

#### Exporting builds: `export`

Exports the information of all builds being managed by the program to text files in the `data/export` folder. This command takes no parameters.

Format: `export`

Example of usage: Enter `export` to export all builds.



__Note__: For builds that have no components, a text file will still be created.

```
____________________________________________________________
export
____________________________________________________________
Exporting builds...
____________________________________________________________
```

#### Exporting builds as CSV: `exportCSV`

Exports the information of all builds to a CSV file in the `data/export` folder. This command takes no parameters.

Format: `exportCSV`

Example of usage: Enter `exportCSV` to export all builds.



__Note__: The CSV file will be empty if there are no components in all the builds.
```
____________________________________________________________
exportCSV
____________________________________________________________
Exporting builds...
____________________________________________________________
```
#### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example of usage: Enter `bye` to exit the program.


```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
### Build Editing Mode

In Build Editing Mode, you can add, delete, and view components in the build you are currently editing.

To get to this mode, you must first [enter the `edit` command](#editing-a-build-edit). You can exit this mode by [entering the `back` command](#exiting-build-editing-mode-back).

#### Adding a component: `add`

Adds a component to the list of components in the current build.

__Note__: 
- If you want to see the components that have been added in the Edit Mode, you can do so by [using the `list` command](#listing-all-components-list).
- If you want to see information about a component you have added, you can do so by [using the `view` command](#viewing-a-component-view).

Acceptable Formats: 
1. `add/case/Name/Price/Power/FormFactor/ExpansionSlots`
2. `add/cooler/Name/Price/Power/Socket/FanSpeed/NoiseLevel`
3. `add/cpu/Name/Price/Power/Socket/ClockSpeed`
4. `add/gpu/Name/Price/Power/Memory/ClockSpeed`
5. `add/drive/Name/Price/Power/StorageSize/StorageType`
6. `add/motherboard/Name/Price/Power/Socket/FormFactor/MemorySlots/GPUSlots`
7. `add/memory/Name/Price/Power/MemorySize/MemorySpeed`
8. `add/powersupply/Name/Price/Power`
9. `add/monitor/Name/Price/Power/RefreshRate/ResponseTime/Resolution`
10. `add/other/Name/Price/Power`

__Note__: If you need a reference for each parameter, refer to the [PC Component Parameters Glossary](#pc-component-parameters-glossary). 

__Note__: The price parameter must be a positive integer or float number. The power parameter must be a positive integer.

Example of usage: 
Enter `add/cpu/intel 10990x/1200/80/lga1511/3.8` to add a cpu named 'intel 10990x' to the
list of components. This CPU has a price of $1200, a power consumption of 80 W, a socket of lga1511 and a clock speed
of 3.8 GHz.


```
<--------------------------------------------------------->
add/cpu/intel 10990x/1200/80/lga1511/3.8
<--------------------------------------------------------->
You have added intel 10990x
<--------------------------------------------------------->
```

#### Deleting a component: `delete`

Deletes a component from the list of components in the current build..

Format: `delete/TYPE/NAME`

Example of usage: Enter `delete/cpu/intel 10990x` to delete a cpu named 'intel 10990x' from the list of 
components.


```
<--------------------------------------------------------->
delete/cpu/intel 10990x
<--------------------------------------------------------->
You have removed intel 10990x
<--------------------------------------------------------->
```

#### Viewing a component: `view`

Displays information about a component in the current build.

Format: `view/TYPE/NAME`

Example of usage: Enter `view/cpu/intel 10990x` to view a cpu named 'intel 10990x' from the list of
components.


```
<--------------------------------------------------------->
view/cpu/intel 10990x
<--------------------------------------------------------->
Name: intel 10990x
Price: $1200
Power: 80 W
Socket: lga1511
Clock: 3.8 GHz
<--------------------------------------------------------->
```

#### Listing all components: `list`

Lists all components in the current build.

Format: `list`

Example of usage: Enter `list` to list all components.


```
<--------------------------------------------------------->
Computer parts for test build:
1. intel 10990x [$1200] [80 W] [lga1511] [3.8 GHz]
<--------------------------------------------------------->
```

#### Checking compatibility: `check`

This command runs a number of compatibility checks on the build you are currently editing and outputs the results of each check.
A result of `true` means that the check has passed while a result of `false` means that the check has failed.
These are the compatibility checks that are run:
- Power supply: Checks if the power supply has enough power to power all the components in the build.
- Socket: Checks if the CPU socket is compatible with the motherboard socket.
- GPU slot: Checks if the motherboard has enough GPU slots to support all the GPUs in the build.
- Memory slot: Checks if the motherboard has enough memory slots to support all the memory modules in the build.
- Expansion slot: Checks if the case has enough expansion slots to support all the drives in the build.
- Form factor: Checks if the motherboard form factor is compatible with the case form factor.

Format: `check`

Example of usage: Enter `check` to run the compatibility checks.


```
<--------------------------------------------------------->
check
<--------------------------------------------------------->
Compatibility Info:
Power supply: false
Socket: false
GPU slot: false
Memory slot: false
Expansion slots: false
Form factor: false
<--------------------------------------------------------->
```

#### Information about a build: `info`

Displays information about the current build. This will list the name, total price, total power consumption, and whether the build passes all the compatibility checks.

Format: `info`

Example of usage: Enter `info` to display information about the build.


```
<--------------------------------------------------------->
info
<--------------------------------------------------------->
Build Info:
Build name: test build
Total cost: 1200.0
Total power: 80
Compatibility: Not compatible
<--------------------------------------------------------->
```

#### Exporting a build: `export`

Exports information about the current build to a text file in the `data/export` folder.

Format: `export`

Example of usage: Enter `export` to export a build.



__Note__: For builds with no components, a text file will still be created.
```
<--------------------------------------------------------->
export
<--------------------------------------------------------->
Exporting build to text file...
<--------------------------------------------------------->
```

#### Exiting Build Editing mode: `back`

Exits Build Editing mode.

Format: `back`

Example of usage: Enter `back` to exit Build Editing mode.


```
<--------------------------------------------------------->
back
<--------------------------------------------------------->
Back to main mode.
<--------------------------------------------------------->
____________________________________________________________
You are in Main Mode.
____________________________________________________________
```

### Storage

The program stores all data about builds and components in the `data` folder. The program will automatically create this folder if it does not exist.
The program will automatically load and save data as necessary, so you do not have to worry about manually saving and loading data.

<span style="color:red">**Warning:**</span> All files in the `data` folder (except for the `export` folder) are automatically generated by the program. Do not modify these files manually as doing so may cause unexpected behaviour or data loss. Manual modification of these files is not supported.

### Export

When the user runs any of the `export` commands, the program will create a text file in the `data/export` folder.
The text file will be named after the build name. These text files can be used to share information about a build with other users.

These text files may be accessed and modified at any time, but will be overwritten if the user runs the `export` command again.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: The program stores all data in the `data` folder. You can copy this folder to another computer along with the `CCC.jar` file to carry your data over.

## Command Summary
### Build Management Mode Summary

| Action    | Format, Examples                                                                                   |
|-----------|----------------------------------------------------------------------------------------------------|
| Add       | `add/NAME` <br> E.g. `add/build1`                                                                  |
| Edit      | `edit/NAME` <br> E.g. `edit/build1`                                                                |
| Delete    | `delete/NAME` <br> E.g. `delete/build1`                                                            |  
| View      | `view/NAME` <br> E.g.  `view/build1`                                                               |
| List      | `list`                                                                                             |
| Find      | `find/KEYWORD` <br> E.g.  `find/build`                                                             |
| Filter    | `filter/TYPE/RANGESTART/RANGEEND` E.g. <br>  `filter/compatibility` <br>  `filter/price/1000/3000` |
| Export    | `export`                                                                                           |
| ExportCSV | `exportCSV`                                                                                        |
| Bye       | `bye`                                                                                              |

### Build Editing Mode Summary

| Action | Format, Examples                                                                            |
|--------|---------------------------------------------------------------------------------------------|
| Add    | `add/TYPE/NAME/PRICE/POWER/PARAM/PARAM/...` <br> E.g. `add/powersupply/Corsair PSU/200/860` |
| Delete | `delete/TYPE/NAME` <br> E.g. `delete/powersupply/Corsair PSU`                               |  
| View   | `view/TYPE/NAME` <br> E.g. `view/powersupply/Corsair PSU`                                   |
| List   | `list`                                                                                      |
| Check  | `check`                                                                                     |
| Info   | `info`                                                                                      |
| Export | `export`                                                                                    |
| Back   | `back`                                                                                      |

## Glossary
### PC Component Parameters Glossary

| Parameter      | Examples                                                         |
|----------------|------------------------------------------------------------------|
| Name           | Name of the component <br/> E.g. `Intel 10990x`                  |
| Price          | Price of the component <br/> E.g. `1000`                         |
| Power          | Power consumption of the component <br/> E.g. `80`               |
| Socket         | Socket type of the component <br/> E.g. `lga1511`, `lga1200`     |
| ClockSpeed     | Clock speed in GHz <br/> E.g. `3.8`                              |
| ExpansionSlots | Number of Expansion slots <br/> E.g.`3`                          |
| GpuSlots       | Number of Gpu slots <br/> E.g. `2`                               |
| MemorySlots    | Number of Memory slots <br/> E.g. `4`                            |
| FormFactor     | Form factor of case/motherboard <br/> E.g. `ATX`, `Mini-ITX` etc |
| MemorySize     | Memory size in GB <br/> E.g. `16`                                |
| StorageSize    | Storage size in GB <br/> E.g. `1000`                             |
| StorageType    | Storage type <br/> E.g. `HDD` , `SDD`                            |
 | FanSpeed       | Fan speed in RPM <br/> E.g. `2000`                               |
| NoiseLevel     | Noise level in dB <br/> E.g. `50`                                |
| RefreshRate    | Refresh Rate in Hz <br/> E.g. `144`                              |
| ResponseTime   | Response Time in ms <br/> E.g. `1`                               |
| Resolution     | Resolution in pixels <br/> E.g. `1920x1080`                      |



