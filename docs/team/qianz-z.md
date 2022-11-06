## Overview
RecipEditor is a hybrid CLI-GUI application that allows you to manage your recipes with the speed 
and convenience of command-line based tools. It helps users to store recipes, edit saved recipes, 
delete unused or outdated recipes, find recipes with relevant keywords, view and list recipes.

## Summary of Contributions
The following sections summarise what I have contributed to the project.

#### **New Feature**: Saving and loading of data from Storage
- What it does:

  It allows users to load all the recipe data upon startup of the application and
  updates recipe data in the individual recipe files and the AllRecipes text file depending on
  the command that is being executed.

- Justification:

  Having a storage utility is crucial in our app's functionality so that users can
  keep track of the recipes that they have added and allows them to update recipes that they have
  previously added. The storage feature also allows users to find and view recipes according to their
  search input quickly.

- Highlights:

  This feature saves all the recipes into individual recipe text files in the 'RecipeData/Recipes'
  folder according to the recipe title, for easy search of recipes by users in the folder. This allows for
  the editing of recipes easily in these recipe text files. Furthermore, it appends the recipe title into
  the AllRecipes text file in './RecipeData' folder. This provides a quick overview of all the recipes that
  the users previously saved.
- Pull requests: [#73](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/73)


#### **New Feature**: Find relevant Recipes according to user's input of recipe title or ingredient name
- What it does:

  It helps users to find relevant recipes quickly according to their input of recipe title or ingredient name.

- Justification:

  This makes it much more convenient for users to find recipes that they are interested in. Users can use this feature
  to check if the list of saved recipes contains recipe titles that they are looking out for. Furthermore, if users
  wants a dish with tomatoes, they can use this feature to find recipe titles that uses tomato as ingredient.
- Highlights:
  - Case-insensitive match:
    For example, if the user stores the ingredient name as "Tomato" in the recipe, the next time he/she tries to find
    the ingredient name as "tomato", the recipe title that contains "Tomato", "tomato", etc will automatically be retrieved.
    This is so that it is more convenient for the user and he/she does not need to worry if the names are an exact match.
  - Ability to find recipes with inputs of recipe title or ingredient name:
    This provides flexibility for user to find recipes according to either recipe titles or ingredient names.
- Pull requests: [#59](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/59)


#### **New Feature**: Delete Recipes
- What it does:

  It helps users to delete unused or outdated recipes to keep the recipe list organised.

- Justification:

  This feature allows users to delete recipes that they do not want to keep in their recipe list. It provides users the
  flexibility to organise their recipe list.

- Highlights:
  - Case-insensitive match:
    For example, if the user stores the recipe title as "Carbonara" in the recipe, the next time he/she tries to find
    the recipe with recipe title input of "carbonara", the recipe title that contains "Carbonara" will automatically be retrieved.
    This is so that it is more convenient for the user and he/she does not need to worry if the names are an exact match.
  - Ability to delete recipes with inputs of recipe title or recipe index:
    This provides flexibility for user to delete recipes according to either recipe titles or recipe index, whichever that
    is more convenient and easier.
- Pull requests: [#73](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/73)



#### **Code Contributed**: [Reposense Link](https://nus-cs2113-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=qianz-z&tabRepo=AY2223S1-CS2113-T18-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


#### **Project Management**:
- Created Github organization for the team
- Helped to set up Github team repository
- Created labels with relevant description, and milestones with due date
- Created issues and user stories for `v1.0`, `v2.0`
- Updated and resolved PE-D issues to team for `v2.1`
  ([#107](https://github.com/AY2223S1-CS2113-T18-2/tp/issues/107),
  [#104](https://github.com/AY2223S1-CS2113-T18-2/tp/issues/104),
  [#90](https://github.com/AY2223S1-CS2113-T18-2/tp/issues/90))
- Helped to find bugs and assign new issues to address bugs
  ([#149](https://github.com/AY2223S1-CS2113-T18-2/tp/issues/149)),
  ([#170](https://github.com/AY2223S1-CS2113-T18-2/tp/issues/170)),
  ([#171](https://github.com/AY2223S1-CS2113-T18-2/tp/issues/171))
- Helped to review, give comments, approve and merge Github pull requests. ([#168](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/168))

### **Enhancements To Existing Features**:

#### **Built the skeleton code for Command classes**
To start off the project, we needed a base structure to build on for parsing and execution of commands.
I set up the abstract `Command` class and the initial few `XYZCommand` classes for v1.0 to provide the
structure for the rest of the team to build on.
([#1](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/1))

#### **Storing Recipe Data in individual Recipe text files and updating AllRecipe text file**
For every recipe, it will be accompanied by an individual recipe text file that contains all the details of
recipe. This helps to make the editing, deletion and finding of recipes easier.
([#73](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/73))


#### **Documentation**:
- User Guide:
  - Created the skeleton of the User Guide
  - Added introduction, about page, and quick start description. ([#132](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/132))
  - Added description of command, format, code example and important notes to all commands. ([#132](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/132))
  - Update Data Management section and Command Summary ([#132](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/132))
  - Added shortcuts back to content page after every major section for easier navigation. (([#132](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/132)))
- Developer Guide:
  - Created the architecture diagram ()
  - Added documentation and class diagram for the [_`Storage` component_](https://github.com/AY2223S1-CS2113-T18-2/tp/blob/master/docs/DeveloperGuide.md#storage-component) ([#56](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/56))
  - Added documentation and class diagram for the [_`Ui` component_](https://github.com/AY2223S1-CS2113-T18-2/tp/blob/master/docs/DeveloperGuide.md#ui-component) ([#56](https://github.com/AY2223S1-CS2113-T18-2/tp/pull/56))
  - Standardised styling for PlantUML diagrams
  - Standardised the styling.