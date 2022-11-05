# User Guide

## Table of Contents
* [Introduction](#introduction)
* [Quick Start](#quick-start)
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
        * [Exporting builds as CSV](#exporting-builds-as-a-csv--exportcsv)
        * [Bye](#bye)
    * [Build Editing Mode](#build-editing-mode)
        * [Adding a component](#adding-a-component-add)
        * [Deleting a component](#deleting-a-component-delete)
        * [Viewing a component](#viewing-a-component-view)
        * [Listing all components](#listing-all-components-list)
        * [Checking compatibility](#checking-compatibility-check)
        * [Information about a build](#information-about-a-build-info)
        * [Exporting a build](#exporting-a-build-export)
        * [Exit edit mode](#exiting-edit-mode-back)
    * [Storage](#storage)
    * [Export](#export)
* [FAQ](#faq)
* [Command Summary](#command-summary)
    * [Main Mode Summary](#main-mode-summary)
    * [Edit Mode Summary](#edit-mode-summary)
* [Glossary](#glossary)
    * [PC Component Parameters Glossary](#pc-component-parameters-summary)

## Introduction

Welcome to Computer Component Chooser!
ComputerComponentChooser(CCC) is a Command Line Interface(CLI) 
program targeting PC enthusiasts and commercial PC builders who want to:
- simplify the [tracking of computer builds](#features) 
- ensure that the parts they pick are [compatible](#checking-compatibility-check) with one another.

## Quick Start

1. Ensure that you have `Java 11` installed.
2. Download the latest version of `CCC` from 
[here](https://github.com/AY2223S1-CS2113T-W11-2/tp/releases).
3. Copy the file to an empty folder you want to use as the _home folder_
4. Open a command window in that folder
5. Run the command `java -jar tp.jar` to start the app
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

### Build Management Mode

Main mode handles the management of your builds. 

#### Adding a build: `add`

Adds a build to the list of builds currently managed by the program. You only need to specify the name of the
build.

Note: If you wish to edit your build after adding it, you can do so by following this command [Editing a build](#editing-a-build-edit).

Format: `add/NAME`

Example of usage: Enter `add/test build` to add a build named 'test build' to the list of builds, a build 
named 'test build' is added to the list of builds managed by the program.

Expected outcome: The program will add a build named 'test build' to the list of builds.
```
____________________________________________________________
add/test build
____________________________________________________________
You have added test build
____________________________________________________________
```

#### Editing a build: `edit`

Enters [Build Editing Mode](#build-editing-mode) for the specified build. You only need to specify the name of the
build.

Format: `edit/NAME`

Note: If you wish to go back to the main mode, you can do so by following this command [Exit edit mode](#exiting-edit-mode-back).

Example of usage: Enter `edit/test build` to edit the build named 'test build'.

Expected outcome: You are now in edit mode for the build named 'test build'.

```
____________________________________________________________
edit/test build
<--------------------------------------------------------->
You are now editing test build
<--------------------------------------------------------->
```

#### Deleting a build: `delete`

Deletes a build in the list of builds current managed by the program. You only need to specify the name of the
build.

Format: `delete/NAME`

Example of usage: Enter `delete/test build` to delete a build named 'test build' stored in the list of builds.

Expected outcome: The program will delete the build named 'test build' from the list of builds.
```
____________________________________________________________
delete/test build
Deleted the file: test build.txt
____________________________________________________________
You have removed test build
____________________________________________________________
```

#### Viewing a build: `view`

Views a build in the list of builds current managed by the program. You only need to specify the name of the
build.

Format: `view/NAME`

Example of usage: Enter `view/test build` to view a build named 'test build' stored in the list of builds.

Expected outcome: The program will display the components of the build named 'test build' from the list of builds.
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

Example of usage: Enter `list` to list all builds stored in the list of builds.

Expected outcome: The program will display all builds stored in the list of builds.
```
____________________________________________________________
list
____________________________________________________________
Your current builds:
1. testbuild
____________________________________________________________
```

#### Finding build(s): `find`
    
List all builds that contain the keyword in their name.

Format: `find/KEYWORD`
    
Example of usage: Enter `find/test` to find all builds that contain the keyword 'test'.

Expected outcome: The program will display all builds that contain the keyword 'test'.
``` 
____________________________________________________________
find/test
____________________________________________________________
Found Builds:
test build
____________________________________________________________
```

#### Filtering build(s): `filter`

List all builds that fit the specified requirements.
`Filter` can filter builds that are compatible or has total cost/power within a specified range.
Range is optional for filtering compatibility.

Acceptable Formats:
1. `filter/price/RANGESTART/RANGEEND`
2. `filter/power/RANGESTART/RANGEEND`
3. `filter/compatibility`
- `RANGESTART` must be integer or float number
- `RANGEEND` must be integer or float number

Examples of usage: 
1. Enter `filter/compatibility` to find all builds that are compatible.
2. Enter `filter/price/3000/4000` to find all builds that have a total cost between $3000 and $4000.

Expected outcomes: 
1. The program will display "No builds that meet specifications found." as there no components in 
test build yet.
2. The program will display all builds that have a total cost between $3000 and $4000.
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

#### Exporting build(s): `export`

Exports all builds to a text file.

Format: `export`

Example of usage: Enter `export` to export all builds.

Expected outcome: The program will export all builds to a text file.
```
____________________________________________________________
export
____________________________________________________________
Exporting builds...
____________________________________________________________
```

#### Exporting build(s) as a CSV : `exportCSV`

Exports all builds to a CSV file.

Format: `exportCSV`

Example of usage: Enter `exportCSV` to export all builds.

Expected outcome: The program will export all builds to a CSV file.
```
____________________________________________________________
exportCSV
____________________________________________________________
Exporting builds...
____________________________________________________________
```
#### Bye: `bye`

Exits the program.

Format: `bye`

Example of usage: Enter `bye` to exit the program.

Expected outcome: The program will exit.
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
### Build Editing Mode

Edit mode handles the management of the computer components of a build. If you want to go back to the main mode,
you can do so by following this command [Exit edit mode](#exiting-edit-mode-back).

#### Adding a component: `add`

Adds a component to the list of components of a build.

Note: 
- If you want to see the components that have been added in the Edit Mode, you can do so by following this command [Listing all components](#listing-all-components-list).
- If you want to see the information of the component you have added, you can do so by following this command [Viewing a component](#viewing-a-component-view).

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

Note: If you need a reference for each parameter, refer to the [PC Component Parameters Glossary](#pc-component-parameters-summary).

Example of usage: 
Enter `add/cpu/intel 10990x/1200/80/lga1511/3.8` to add a cpu named 'intel 10990x' to the
list of components. This CPU has a price of $1200, a power consumption of 80 W, a socket of lga1511 and a clock speed
of 3.8 GHz.

Expected outcome: The program will add a cpu named 'intel 10990x' to the list of components.
```
<--------------------------------------------------------->
add/cpu/intel 10990x/1200/80/lga1511/3.8
<--------------------------------------------------------->
You have added intel 10990x
<--------------------------------------------------------->
```

#### Deleting a component: `delete`

Deletes a component from the list of components of a build.

Format: `delete/TYPE/NAME`

Example of usage: Enter `delete/cpu/intel 10990x` to delete a cpu named 'intel 10990x' from the list of 
components.

Expected outcome: The program will delete a cpu named 'intel 10990x' from the list of components.
```
<--------------------------------------------------------->
delete/cpu/intel 10990x
<--------------------------------------------------------->
You have removed intel 10990x
<--------------------------------------------------------->
```

#### Viewing a component: `view`

Views a component from the list of components of a build.

Format: `view/TYPE/NAME`

Example of usage: Enter `view/cpu/intel 10990x` to view a cpu named 'intel 10990x' from the list of
components.

Expected outcome: The program will display the details of a cpu named 'intel 10990x' from the list of components.
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

Lists all components from the list of components of a build.

Format: `list`

Example of usage: Enter `list` to list all components from the list of components.

Expected outcome: The program will display all components from the list of components.
```
<--------------------------------------------------------->
Computer parts for test build:
1. intel 10990x [$1200] [80 W] [lga1511] [3.8 GHz]
<--------------------------------------------------------->
```

#### Checking compatibility: `check`

Checks if a build is compatible.

Format: `check`

Example of usage: Enter `check` to check if the build is compatible.

Expected outcome: The program will display a list of compatibilities based on the components of a build.
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

Displays information about a build.

Format: `info`

Example of usage: Enter `info` to display information about a build.

Expected outcome: The program will display information about a build.
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

Exports a build to a text file.

Format: `export`

Example of usage: Enter `export` to export a build.

Expected outcome: The program will export a build to a text file.
```
<--------------------------------------------------------->
export
<--------------------------------------------------------->
Exporting build to text file...
<--------------------------------------------------------->
```

#### Exiting edit mode: `back`

Exits edit mode.

Format: `back`

Example of usage: Enter `back` to exit edit mode.

Expected outcome: The program will exit edit mode.
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

The program stores all builds in a `text` file and components of each build in separate `text` files. The text files are 
stored in the `data` folder. 

Loading and saving are done automatically, so you do not need to worry about saving or loading data manually.

### Export

The program exports all builds to text and CSV files. The text and CSV files are stored in the data folder.

It makes it easier for you to view the builds and components in a more readable format outside the program.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Upon exit of the program, your data is stored in the data folder. Simply copy the contents of the data folder 
to the data folder of the new program on your new computer to carry over your data.

## Command Summary
### Main Mode Summary

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

### Edit Mode Summary

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
| Parameter      | Examples                                                        |
|----------------|-----------------------------------------------------------------|
| Name           | Name of the component <br/> E.g. `Intel 10990x`                 |
| Price          | Price of the component <br/> E.g. `1000`                        |
| Power          | Power consumption of the component <br/> E.g. `80`              |
| Socket         | Socket type of the component <br/> E.g. `lga1511`, `lga1200`    |
| ClockSpeed     | Clock speed in GHz <br/> E.g. `3.8`                             |
| ExpansionSlots | Number of Expansion slots <br/> E.g.`3`                         |
| GpuSlots       | Number of Gpu slots <br/> E.g. `2`                              |
| MemorySlots    | Number of Memory slots <br/> E.g. `4`                           |
| FormFactor     | Formfactor of case/motherboard <br/> E.g. `ATX`, `Mini-ITX` etc |
| MemorySize     | Memory size in GB <br/> E.g. `16`                               |
| StorageSize    | Storage size in GB <br/> E.g. `1000`                            |
| StorageType    | Storage type <br/> E.g. `HDD` , `SDD`                           |
 | FanSpeed       | Fan speed in RPM <br/> E.g. `2000`                              |
| NoiseLevel     | Noise level in dB <br/> E.g. `50`                               |
| RefreshRate    | Refresh Rate in Hz <br/> E.g. `144`                             |
| ResponseTime   | Response Time in ms <br/> E.g. `1`                              |
| Resolution     | Resolution in pixels <br/> E.g. `4000`                          |



