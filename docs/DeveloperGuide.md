# Developer Guide

## Table of Contents
* [Acknowledgements](#acknowledgements)
* [Design & Implementation](#design--implementation)
  * [Main Mode](#main-mode)
    * [Build Manager](#build-manager) 
  * [Edit Mode](#edit-mode)
    * [Build](#build)
    * [Components](#components)
  * [Storage](#storage)
  * [Export](#export)

* [Appendix](#appendix)
  * [Product Scope](#product-scope)
  * [Target User Profile](#target-user-profile)
  * [Value Proposition](#value-proposition)
  * [User Stories](#user-stories)
  * [Non-Functional Requirements](#non-functional-requirements)
  * [Glossary](#glossary)
* [Instructions for Manual Testing](#instructions-for-manual-testing)


## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Main Mode

This section describes the implementation of Main Mode features.

Once the `main()` method of ComputerComponentChooser is called, objects for the `BuildManager`, `Parser`, 
`editParser`, `Storage`, `Ui` are created.



#### BuildManager

![](/images/BuildManager.png)

The builds are managed by the BuildManager class. It contains a list of builds in a HashMap. The class also contains
methods to find and filter builds based on user requirements. The BuildManager class is a singleton class, meaning that
there is only one instance of the class in the program. This is to ensure that there is only one list of builds in the
program. 

In our application, BuildManager is a class object that contains a HashMap of builds. The HashMap is used to store the
builds in the program. The key of the HashMap is the name of the build, and the value is the build object.

The BuildManager class is responsible for the following operations:
- Add a build to the list of builds
- Delete a build from the list of builds
- Get a particular build from the list of builds
- Get the list of builds
- Find build that contains a search term from the list of builds
- Filter builds based on user requirements


### Edit Mode

#### Build 

![](/images/Build.png)

A build is a collection of components that are used to create a PC. A build can be saved and loaded from the storage. 
The user can also export the build to a text file via the export functionality. 

In our application build is a class object which contains a 2D linked hash map of components. LinkedHashMap2D is a class
representing a 2D linked hash map data structure. The keys of the outer map are the component types and the keys of the inner
are the component names. The values of the inner map are the components themselves.

The user can add, delete, and view the components in the build. The user can also
perform aggregate operations on the build such as viewing the total price of the build and viewing the total wattage of the build.
There are also check compatibility functions that check if the build's components are compatible with each other in different ways. Such as:
- Check if the power supply is compatible with the build's total power consumption
- Checking if the build's motherboard is compatible with the build's CPU and Cooler
- Checking if the form factor of the build's case is compatible with the build's motherboard
- Checking if the build's storage is compatible with the case expansion slots
- Checking if the number of the build's GPUs is compatible with the motherboard GPU slots
- Checking if the number of the build's RAM is compatible with the motherboard RAM slots

#### Components

### Storage

### Export

In our application export is a utility class. The user can export all builds or a specific build to a text file. The user can also export
all builds to a CSV file. 


## Product scope
### Target user profile

This product is targeted towards PC building enthusiasts and professional System builds who have a need and want to 
keep track of their PC builds. It is optimized for users to work with a Command Line Interface (CLI).

### Value proposition

This product helps builders to keep track of their PC builds and their components. It also helps them to keep track of
their total power consumption and the total cost of their builds. Compatibility of components is also checked to ensure
that the build is able to function properly..

## User Stories

| Version | As a ... | I want to ...              | So that I can ...                                           |
|---------|----------|----------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions     | refer to them when I forget how to use the application      |
 | v1.0    | user     | add a new build            | refer to when I want to track my build                      |
| v1.0    | user     | list all builds            | get a list of all builds in one place                       |
| v1.0    | user     | delete a build             | remove builds that I do not need anymore                    |
| v2.0    | user     | find a to-do item by name  | locate a to-do without having to go through the entire list |
| v2.0    | user     | add a component to a build | refer to when I want to track a component in my build       |
| v2.0    | user     | list all components        | get a list of all components of a build in one place        |
| v2.0    | user     | delete a component         | remove components that I do need anymore                    |
| v2.0    | user     | delete a component         | remove components that have mistakes                        |
| v2.0    | user     | check a build              | check the compatability of all components of a build        |
| v2.0    | user     | info                       | view the relevant information about a build                 | 
 | v2.0    | user     | export a build             | export a build to a text file                               |
 | v2.0    | user     | export a build             | export a build to a csv file                                |
 | v2.0    | user     | find a build               | locate a build without having to go through the entire list |
 | v2.0    | user     | filter builds              | find all builds that are within a certain price range       |
| v2.0    | user     | filter builds              | find all builds that are within a certain power range       |
| v2.0    | user     | filter builds              | find all builds that are compatible                         |

## Non-Functional Requirements

Product should work on any mainstream OS as long as it has Java 11 or above installed.

## Glossary

* *glossary item* - Definition
* 
| Terms       | Definition                                                                                         |
|-------------|----------------------------------------------------------------------------------------------------|
| CPU         | The component of a computer system that controls the interpretation and execution of instructions. |
| GPU         | Graphics processing unit, a specialized processor designed to accelerate graphics rendering.       |
| Drive       | Storage devices to store user information.                                                         |
| Memory      | A computer's short-term memory, where the data that the processor is currently using is stored.    |
| Motherboard | The main circuit board within a computer that the other components plug into to create a whole.    |
| Powersupply | A power supply is a hardware component that supplies power to the computer.                        |

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
