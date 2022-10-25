# User Guide

## Introduction

A command line / GUI application that allows you to manage your recipes 

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `RecipEditor` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding recipe: `/add`

Adds a new recipe to the list.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### List all recipes: `/list`

### Delete recipes: `/delete`

### Edit recipes: `/edit`

Edits an existing recipe from the list.

Format: `/edit [RECIPE_NAME, RECIPE_INDEX]`

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

### Find recipes: `/find`

### Exit program: `/exit`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
