# User Guide

## Introduction

A command line / GUI application that allows you to manage your recipes 

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `RecipEditor` from [here]([https://github.com/AY2223S1-CS2113-T18-2/tp/releases]).

## Features 

### Adding recipe: `/add`

Adds a new recipe to the list.

Format: `/add`

This will bring you to the add mode of the recipEditor.

You will encounter Recipe Title, Recipe Description, Recipe Ingredients and Recipe Steps sections.
You will be prompted to fill in each section sequentially.
For the sections on ingredients and steps, you can add in however many you would like, until your enter 'done' in the CLI, and that will bring you to either the next section or exit the add mode for you.


### List all recipes: `/list`

Lists all recipe.

Format: `/list`

### Delete recipes: `/delete`

### Edit recipes: `/edit`

Edits an existing recipe from the list.

Format: `/edit [RECIPE_NAME,RECIPE_INDEX]`

CLI Mode: `[/add,/del,/swap,/change,/view,/done] [-t,-d,-i,-s] [index1] ...`

* `-t` flag changes the title
* `-d` flag changes the description
* `-i` flag changes the ingredients
* `-s` flag changes the steps

Example of usage:

`/edit fried rice`
`/change -i 1`
`rice / 100 / g`

`/edit 5`
`/swap -s 4 7`

### View recipes: `/view`

Views a recipe.

Format: `/view [index]`

Example of usage:

`/view 1`

### Find recipes: `/find`

### Exit program: `/exit`


