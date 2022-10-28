
RecipEditor is a hybrid CLI-GUI application that allows you to manage your recipes 



- [Quick Start](#quick-start)
- [Features](#features)
    - [Add Command](#add-command)
    - [Edit Command](#edit-command)
    - [Delete Command](#delete-command)
    - [List Command](#list-command)
    - [View Command](#view-command)
    - [Find Command](#find-command)
    - [Help Command](#help-command)
    - [Exit Command](#exit-command)
    - [Storage](#storage)
      - [Editing Data File](#editing-data-file)
      - [Parsing Data File](#parsing-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

# Quick Start
1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `RecipEditor` from [here]([https://github.com/AY2223S1-CS2113-T18-2/tp/releases]).
3. Go to the directory of the `RecipEditor.jar`
4. Use command line to start the program `java -jar RecipEditor.jar`

# Features 
## Add Command
- Add new recipe to the model
- FORMAT: `/add`
### GUI Editor
- A simple GUI text editor will open and loaded with a template file
```
# TITLE 
Example Title 

# DESCRIPTION
Example Description

# INGREDIENTS ingredient_name / amount / unit
1. Example ingredient / 1.2 / example unit 

# STEPS 
1. Example step 
```

<p align="center" width="100%">
  <img width="80%" src="images/TextEditorWithTemplate.png"/>
</p>

- Edit the corresponding field according to the following convention
  - `# TITLE`: Only the first line will be accepted
  - `# DESCRIPTION`: Multiple lines are supported
  - `# INGREDIENTS`
    - FORMAT: `INDEX. INGREDIENT_NAME / AMOUNT / UNIT`
    - EXAMPLE: `1. Egg / 2 / pieces`
    - EXAMPLE: `2. Flour / 100 / gram`
  - `# STEPS`
    - FORMAT: `INDEX. STEP DESCRIPTION`
    - EXAMPLE: `1. Crack the eggs and stir`

- Template Recipe
## Edit Command
- Edit existing recipe with given INDEX
- FORMAT: `/edit INDEX`
- EXAMPLE: `/edit 1`
- The GUI Editor workflow is similar to [GUI Editor](#gui-editor)
## Delete Command
- Delete existing recipe with given INDEX 
- FORMAT: `/delete INDEX`
- EXAMPLE: `/delete 1`
## List Command
- List the existing recipes in the current running program
- FORMAT: `/list`
## View Command
- View the recipe with the index as shown in the list
- FORMAT: `/view INDEX`
- EXAMPLE: `/view 1`
## Find Command
### Find Recipe with Title
- Find the recipe with title that contains USER_INPUT_STRING
- FORMAT: `/find -t USER_INPUT_STRING`
- EXAMPLE: `/find -t Cake`
### Find Recipe with Ingredient
- Find the recipe with ingredient that contains USER_INPUT_STRING
- FORMAT: `/find -i USER_INPUT_STRING`
- EXAMPLE: `/find -i egg`
## Help Command
- Show the available commands
- FORMAT: `/help`
## Exit Command
- Exit the program
- FORMAT: `/exit`
## Storage
- There are 2 types of data files
  - **Overall File (OF)**: keep track of the number and the title of the recipes
  - **Individual Recipe File (IRF)**: each recipe is saved as an individual file
- These files are important at the start of the program, when data is loaded
### Editing Data File
- After `/add`
  - New IRF is saved
  - New Recipe Title is appended to OF
- After `/edit INDEX`
  - Update existing IRF at INDEX
  - Update OF (change Title)
- After `/delete INDEX`
  - Delete existing IRF at INDEX
  - Update OF (delete)
### Parsing Data File
- If user tempered with the data files, these are the potential problems encountered when the program starts and reads the data
  - Mismatch between IRF Title and OF Title
    - Delete IRF
  - IRF content cannot be parsed
    - Delete IRF
- New OF File is generated from the model contains the valid recipes loaded from IRFs

  

# FAQ

# Command Summary
| Command | Action                      | Format                | Example         |
|---------|-----------------------------|-----------------------|-----------------|
| add     | Add new recipe              | `/add`                | `/add`          |
| edit    | Edit recipe at INDEX        | `/edit INDEX`         | `/edit 1`       |
| list    | List all recipes            | `/list`               | `/list`         |
| view    | View recipe at INDEX        | `/view INDEX`         | `/view 1`       |
| find    | Find recipe with Title      | `/find -t Title`      | `/find -t Cake` |
| find    | Find recipe with Ingredient | `/find -i Ingredient` | `/find -t egg`  |
| help    | Show available commands     | `/help`               | `/help`         |
| exit    | Exit the program            | `/exit`               | `/exit`         |
