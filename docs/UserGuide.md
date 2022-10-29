# RecipEditor
## **Introducing _RecipEditor_**
<hr />

_RecipEditor_ is a hybrid CLI-GUI application that allows you to manage your recipes

_RecipEditor_ can be used across all operating systems such as Windows, Mac OS X, Linux and Unix.
It is optimised for use via a complement of Command-Line Interface (CLI) and Graphical user interface (GUI).
Both the CLI and GUI are made to be as simple as possible so that it would be especially beneficial for
fast typers who needs a platform to keep track of the countless recipes.

If you type fast, and you need an easy and quick way to record your calories, _RecipEditor_ is the app for you! 💯

## **About This User Guide**
<hr />
This guide explains how you can use all the features available on _RecipEditor_ and maximise your user experience.

To preface, we do expect users to have a basic level of comfort with using computers,
and it would be even better if you are familiar with CLI to be using _RecipEditor_.
However, if you do not, do not worry as we have provided a comprehensive set of instructions to
[get started](#2-quick-start)!

Throughout this guide, we will be using some special formatting and symbols to bring your attention to certain aspects:

| Formatting | Meaning                                                                                                                    |
|------------|----------------------------------------------------------------------------------------------------------------------------|
| _italics_  | Text that has been _italicised_ indicates that it is a term specific to _RecipEditor_.                                     |
| **bold**   | Text that has been **bolded** indicates that it is important.                                                              |
| `abc`      | Text with a `highlight` indicates that it is code that can be typed by you into the command line or displayed by _Fitbot_. |
| ℹ️         | This symbol indicates important information.                                                                               |
| ⏫          | This symbol indicates a shortcut to the content page. You may click it to quickly navigate back to the content page.       |


## **Content Page**
<hr />

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

If successfully loaded, you will see this screen:

![Program Start Up Screen](./images/ProgramStartScreen.png)

[⏫ Back to content page](#content-page)

# Features
## Add Command

- Add new recipe to the model
- FORMAT: `/add`

### GUI Editor

- A simple GUI text editor will open and loaded with a template file as shown below:

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
  <img width="70%" src="Desktop/CS2113/tp/tp/docs/images/UserGuide/TextEditorWithTemplate.png"/>
</p>

- Edit the corresponding field according to the following convention:
    - `# TITLE`: Only one line (the line below '_# TITLE_') will be accepted
    - `# DESCRIPTION`: Multiple lines** are supported
    - `# INGREDIENTS`:
        - FORMAT: `INDEX. INGREDIENT_NAME / AMOUNT / UNIT`
            - EXAMPLE:
              <br /> `1. Egg / 2 / pieces`
              <br /> `2. Flour / 100 / gram`
    - `# STEPS`
        - FORMAT: `INDEX. STEP_DESCRIPTION`
            - EXAMPLE:
              <br /> `1. Crack the eggs and stir`
              <br /> `2. Crack the eggs and stir`

## Edit Command

- Edit existing recipe with given INDEX
- FORMAT: `/edit INDEX`
- EXAMPLE: `/edit 1`
- The GUI Editor workflow is similar to [GUI Editor](#gui-editor)

## Delete Command

- Delete existing recipe with given recipe title (case insensitive).
- FORMAT: `/delete RECIPE_TITLE`
- EXAMPLE: `/delete carbonara`

## List Command

- List the existing recipes previously added
- FORMAT: `/list`

## View Command

- View the full details of the specified recipe according to the index shown in the list
- FORMAT: `/view INDEX`
- EXAMPLE: `/view 1`

## Find Command

### Find Recipe with Title

- Find the recipe with title that contains USER_INPUT_STRING
- User can input recipe title or ingredient name (case insensitive).
- Program will return relevant recipe title.
- FORMAT: `/find USER_INPUT_STRING`
- EXAMPLE: `/find cake`

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

- If user tempered with the data files, these are the potential problems encountered when the program starts and reads
  the data
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
