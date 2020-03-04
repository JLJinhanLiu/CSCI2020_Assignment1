# CSCI 2020 Assignment 1
This is the git repository of Assignment 1 of CSCI 2020 Winter Semester. It covers four questions for each codes: **Displaying Three Cards**, **Investment Value Calculator**, **Dragging Points on Circle**, and **Histogram**.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine. Follow the instruction from installing the IDE and Library, and it should be running on your local machine.

### Pre-requisite
```
IntelliJ IDEA Community Version
JavaFX SDK 11.0.2
```
* JavaFX SDK: https://gluonhq.com/products/javafx/
* IntelliJ IDEA: https://www.jetbrains.com/idea/download/

### Installing

1. Download JavaFX SDK and pick the appropriate platform
2. Download IntelliJ IDEA Community Version and pick the appropriate platform 
3. Install IntelliJ IDEA on your local machine
4. Clone this repository
5. Open JavaFX and Open Project of this repository.
6. Once opened, go to File > Project Structure.
7. Go to Project > Project SDK as "`12`"
8. Go to Modules, select "`/src`" directory as Sources and "`/out`" directory as Excluded
9. Go to Libraries, select "+" icon, go to your JavaFX SDK root folder and select "`lib`" folder.
10. Click OK

## Running the Code
There are 4 codes in this repository. Each code has its own different requirement to run, but the beginning part is the same for each code.

1. Select Run > Edit Configuration...
2. Select Application > "+" symbol on the top left > Applcation
3. In VM Options textfield, write "`(JavaFX SDK Root Folder)\lib --add-modules javafx.controls,javafx.fxml`"
4. Put the appropriate code's name in the Name textfield


### Displaying Three Cards
* NOTE: This code requires the folder "Cards" within the code's directory. Inside of "`/Cards`" is .PNG files that will be used to display the images on the software.

5. In Main Class textfield, write "`Card`"

### Investment Value Calculator
5. In Main Class textfield, write "`Calculator`"

### Dragging Points on Circle
5. In Main Class textfield, write "`CircleTriangle`"

### Histogram
* NOTE: This code requires an additional file to load. It can accept any file format but .txt file is recommended. The code will read the file and count the letters within the file. If the user does not have any file, the sure can use the `the_egg.txt`" to test the code.
 
5. In Main Class textfield, write "`Histogram`"
6. Select the file that you want to count the letters on, and copy its path then paste it into Filename textfield

## Authors

* **Jinhan Liu** |  100721099
* **Kevin Chandra** | 100708570
