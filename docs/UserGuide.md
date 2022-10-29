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

| Formatting | Meaning                                                                                                                         |
|------------|---------------------------------------------------------------------------------------------------------------------------------|
| _italics_  | Text that has been _italicised_ indicates that it is a term specific to _RecipEditor_.                                          |
| **bold**   | Text that has been **bolded** indicates that it is important.                                                                   |
| `abc`      | Text with a `highlight` indicates that it is code that can be typed by you into the command line or displayed by _RecipEditor_. |
| ℹ️         | This symbol indicates important information.                                                                                    |
| ⏫          | This symbol indicates a shortcut to the content page. You may click it to quickly navigate back to the content page.            |


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
<hr/>

ℹ️ ***Command Format***
- Commands are not case-sensitive (e.g. `help`, `HELP`,`hElP` are all able to execute the `help` command)

- Words in upper case (e.g. `UPPER_CASE`) are used to signify parameters.

## Add Command

Adds new recipe to your recipe list. This feature allows you to record down the recipe title, recipe description,
ingredients used and the steps involved. To record the ingredients used, user can note down the ingredient name,
amount and the respective units. As for the steps involved, user will specify the steps in the order of execution.

ℹ This feature brings out a GUI editor that allows user to quickly type all the different sections at once.

Format: `/add`

### GUI Editor

A simple GUI text editor will open and loaded with a template file as shown below:

<p align="center" width="100%">
  <img width="70%" src="Desktop/CS2113/tp/tp/docs/images/UserGuide/TextEditorWithTemplate.png"/>
</p>

Edit the corresponding field according to the following convention:
- `# TITLE`: Only one line, which is the next line after `# TITLE`, will be accepted
- `# DESCRIPTION`: Multiple lines are supported
- `# INGREDIENTS`:
    - Format: `INDEX. INGREDIENT_NAME / AMOUNT / UNIT`
        - Example:
          <br /> `1. Egg / 2 / pieces`
          <br /> `2. Flour / 100 / gram`
- `# STEPS`
    - Format: `INDEX. STEP_DESCRIPTION`
        - Example:
          <br /> `1. Crack the eggs and stir`
          <br /> `2. Crack the eggs and stir`
  
Example:
```
# TITLE 
Carbonara

# DESCRIPTION
Carbonara is an Italian pasta dish from Rome made with eggs, hard cheese, cured pork and black pepper.
Hot pasta tossed with a creamy sauce of raw beaten eggs, accentuated with crisp bits of guanciale, and 
finished with a shower of grated aged Pecorino Romano cheese plus freshly ground black pepper.

# INGREDIENTS ingredient_name / amount / unit
1. Extra virgin olive oil / 1 / table spoon
2. Eggs / 3 / whole
3. Parmesan cheese / 1 / cup
4. Spaghetti / 0.9 / pound

# STEPS 
1. Heat the pasta water.
2. Sauté the pancetta or bacon and garlic.
3. In a small bowl, beat the eggs and mix in about half of the cheese.
4. Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil.
5. Add the beaten egg mixture.
6. Pour the mixture over the pasta.
```

ℹ After inputting all the relevant field, you can click on the "_Save and Exit_" button in the top left corner of the GUI editor.

ℹ Otherwise, if you decide not to save the information you have inputted, you can click on the "_Exit Only_" button to exit the add mode.

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
