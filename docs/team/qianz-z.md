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

