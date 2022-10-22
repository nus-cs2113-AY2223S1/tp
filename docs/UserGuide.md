# User Guide

## Introduction

ComputerComponentChooser(CCC) is a Command Line Interface(CLI) 
program that simplifies the tracking of computer builds and 
help users ensure that the parts they pick are 
compatible with one another.

## Quick Start

1. Ensure that you have `Java 11` installed.
2. Download the latest version of `CCC` from 
[here](https://github.com/AY2223S1-CS2113T-W11-2/tp/releases).
3. Copy the file to an empty folder you want to use as the _home folder_
4. Open a command window in that folder
5. Run the command `java -jar CCC.jar` to start the app
6. Refer to the [Features](#features) below to try out some commands!

## Features 

Upon starting the Program, the user will be situated in the main mode.

### Main Mode

Main mode handles the management of the user's builds. 

#### Adding a build: `add`

Adds a build to the list of builds currently managed by the program. The user only needs to specify the name of the
build.

Format: `add/NAME`

Examples of usage: User enters `add/test build` to add a build named 'test build' to the list of builds.

Expected outcome: The program will add a build named 'test build' to the list of builds.
```
____________________________________________________________
add/test build
____________________________________________________________
You have added test build
____________________________________________________________
```

### Editing a build: `edit`

Enters edit mode for the specified build. The user only needs to specify the name of the
build.

Format: `edit/NAME`

Examples of usage: User enters `edit/test build` to edit the build named 'test build'.

Expected outcome: The user is now in edit mode for the build named 'test build'.

```
____________________________________________________________
edit/test build
____________________________________________________________
You are now editing test build
____________________________________________________________
```

#### Deleting a build: `delete`

Deletes a build in the list of builds current managed by the program. The user only needs to specify the name of the
build.

Format: `delete/NAME`

Examples of usage: User enters `delete/test build` to delete a build named 'test build' stored in the list of builds.

Expected outcome: The program will delete the build named 'test build' from the list of builds.
```
____________________________________________________________
delete/test build
Deleted the file: test build.txt
____________________________________________________________
You have removed test build
____________________________________________________________
```

### Viewing a build: `view`

Views a build in the list of builds current managed by the program. The user only needs to specify the name of the
build.

Format: `view/NAME`

Examples of usage: User enters `view/test build` to view a build named 'test build' stored in the list of builds.

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

### Listing all builds: `list`

Lists all builds in the list of builds currently managed by the program.

Format: `list`

Examples of usage: User enters `list` to list all builds stored in the list of builds.

Expected outcome: The program will display all builds stored in the list of builds.
```
____________________________________________________________
list
____________________________________________________________
Your current builds:
1. testbuild
____________________________________________________________
```

### Finding build(s): `find`
    
List all builds that contain the keyword in their name.

Format: `find/KEYWORD`
    
Examples of usage: User enters `find/test` to find all builds that contain the keyword 'test'.

Expected outcome: The program will display all builds that contain the keyword 'test'.
``` 
____________________________________________________________
Found Builds:
testbuild
____________________________________________________________
```

### Filtering build(s): `filter`

List all builds that fit the specified requirements.
Filter can filter builds that are compatible or has total cost/power within a specified range.
Range is optional for filtering compatibility.

Format: `filter/TYPE/RANGESTART/RANGEEND`

Examples of usage: User enters `filter/compatibility` to find all builds that are compatible.

Expected outcome: The program will display "No builds that meet specifications found." as there no components in 
test build yet.
```
____________________________________________________________
filter/compatibility
____________________________________________________________
No builds that meet specifications found.
____________________________________________________________
```

### Exporting build(s): `export`

Exports all builds to a text file.

Format: `export`

Examples of usage: User enters `export` to export all builds.

Expected outcome: The program will export all builds to a text file.
```
____________________________________________________________
export
____________________________________________________________
Exporting builds...
____________________________________________________________
```

### Exporting build(s) as a CSV : `exportCSV`

Exports all builds to a CSV file.

Format: `exportCSV`

Examples of usage: User enters `exportCSV` to export all builds.

Expected outcome: The program will export all builds to a CSV file.
```
____________________________________________________________
exportCSV
____________________________________________________________
Exporting builds...
____________________________________________________________
```

### Edit Mode

Edit mode handles the management of the computer components of a build.

### Adding a component: `add`

Adds a component to the list of components of a build.

Format: `add/TYPE/NAME/PRICE/POWER/PARAM/PARAM/...`

Examples of usage: User enters `add/cpu/intel 10990x/1200/80/lga1511/3.8` to add a cpu named 'intel 10990x' to the
list of components. This CPU has a price of $1200, a power consumption of 80 W, a socket of lga1511 and a clock speed
of 3.8 GHz.

Expected outcome: The program will add a cpu named 'intel 10990x' to the list of components.
```
____________________________________________________________
add/cpu/intel 10990x/1200/80/lga1511/3.8
____________________________________________________________
You have added intel 10990x
____________________________________________________________
```

### Deleting a component: `delete`

Deletes a component from the list of components of a build.

Format: `delete/TYPE/NAME`

Examples of usage: User enters `delete/cpu/intel 10990x` to delete a cpu named 'intel 10990x' from the list of 
components.

Expected outcome: The program will delete a cpu named 'intel 10990x' from the list of components.
```
____________________________________________________________
delete/cpu/intel 10990x
____________________________________________________________
You have removed intel 10990x
____________________________________________________________
```

### Viewing a component: `view`

Views a component from the list of components of a build.

Format: `view/TYPE/NAME`

Examples of usage: User enters `view/cpu/intel 10990x` to view a cpu named 'intel 10990x' from the list of
components.

Expected outcome: The program will display the details of a cpu named 'intel 10990x' from the list of components.
```
____________________________________________________________
view/cpu/intel 10990x
____________________________________________________________
Name: intel 10990x
Price: $1200
Power: 80 W
Socket: lga1511
Clock: 3.8 GHz
____________________________________________________________
```

### Listing all components: `list`

Lists all components from the list of components of a build.

Format: `list`

Examples of usage: User enters `list` to list all components from the list of components.

Expected outcome: The program will display all components from the list of components.
```
____________________________________________________________
Computer parts for testbuild:
1. intel 10990x [$1200] [80 W] [lga1511] [3.8 GHz]
____________________________________________________________
```

### Checking compatibility: `check`

Checks if a build is compatible.

Format: `check`

Examples of usage: User enters `check` to check if the build is compatible.

Expected outcome: The program will display a list of compatibilities based on the components of a build.
```
____________________________________________________________
check
____________________________________________________________
Compatibility Info:
Power supply: false
Socket: true
GPU slot: false
Memory slot: false
Expansion slots: false
Form factor: false
____________________________________________________________
```

### Information about a build: `info`

Displays information about a build.

Format: `info`

Examples of usage: User enters `info` to display information about a build.

Expected outcome: The program will display information about a build.
```
____________________________________________________________
info
____________________________________________________________
Build Info:
Build name: testbuild
Total cost: 1200.0
Total power: 80
Compatibility: Not compatible
____________________________________________________________
```

### Exporting a build: `export`

Exports a build to a text file.

Format: `export`

Examples of usage: User enters `export` to export a build.

Expected outcome: The program will export a build to a text file.
```
____________________________________________________________
export
____________________________________________________________
Exporting build to text file...
____________________________________________________________
```

### Exiting edit mode: `back`

Exits edit mode.

Format: `back`

Examples of usage: User enters `back` to exit edit mode.

Expected outcome: The program will exit edit mode.
```
____________________________________________________________
back
____________________________________________________________
Back to main mode.
____________________________________________________________
```

### Storage

The program stores all builds in a text file and components of each build in separate text files. The text file is 
stored in the data folder. 

Loading and saving is done automatically and the user does not need to worry about manually saving or loading data.

### Export

The program exports all builds to text and CSV files. The text and CSV files are stored in the data folder.

It makes it easier for the user to view the builds and components in a more readable format outside the program.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Upon exit of the program, your data is stored in the data folder. Simply copy the contents of the data folder 
to the data folder of the new program on your new computer to carry over your data.

## Command Summary
### Main Mode

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

### Edit Mode

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

