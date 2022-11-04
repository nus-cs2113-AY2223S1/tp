# Indraneel Paranjape - Project Portfolio Page

## Overview
myReviews is a desktop application allowing users to save, rate and track movies or TV shows that they
have watched easily. myReviews is fully written in Java, and users can interact with the application using
Command Line Interface (CLI).

Given below are my contributions to the project:

### Summary of Contributions

* **New Feature**: Driver code
  * What it does: This feature handles the overall run of duke. 
  * Justification: The overall executor needs to handle the set-up actions such as the creating of a file or
  reading in saved data, and then executing the main funtions of the program.
  * Highlights: The OOP model has been well-followed to make the structure of the program simple and intuitive. The
  driver code was written such that the instantiation of the Duke object instantiates the various helper classes (Parser, storage, Ui, ReviewList), allowing the smaller details to be abstracted away, resulting in clean concise and very readable
  driver code.

* **New Feature**: Parser
  * What it does: This feature resolves the user input into a command to execute.
  * Justification: The parser class is vital to the program, as it is responsible for proessing the user's string input
  and triggering the appropriate function. By having a Parser as a dedicated feature, the resolution of user input can be compartmentalised into a specific class, keeping the main code clean.
  * Highlights: The parser splits the user's input string using space " " as a delimiter. This resolves the first word of the command, triggering the specific execute function via the switch-case block. Then, the respective functions, where needed, obtain the arguments for the particular command by splitting the user input using "/". The presence of exact arguments are 
  also determined by checking for the presence of specific keywords. Within the execute functions, the manipulation of the ReviewList object is handed off to the Command classes (Eg AddCommand), again to keep a separation of concerns.

  * **New Feature**: Storage
  * What it does: This feature ensures the user's information can be saved into local storage and retrieved
  every time the program is run.
  * Justification: Storage is vital for the persistence of data. This ensures the user does not have to remember the information.
  * Highlights: The Storage class

